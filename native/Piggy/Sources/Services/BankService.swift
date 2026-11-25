import Foundation
import Supabase

/// Zustand des Kontoabgleichs. Wird beim Kontowechsel geleert.
struct BankSnapshot {
    var statements: [BankStatement] = []
    var transactions: [BankTransaction] = []
    var matches: [ReceiptMatch] = []
    var suggestions: [MatchSuggestion] = []
    var shared: [SharedBankItem] = []
    var loaded = false
}

extension PiggyStore {
    private var statementConsentKey: String? { userID.map { "piggy.statementConsent.\($0)" } }
    /// Eigene Einwilligung für den Kontoauszug-Upload (zusätzlich zur KI-Einwilligung).
    var statementConsent: Bool {
        get { statementConsentKey.map { UserDefaults.standard.bool(forKey: $0) } ?? false }
        set { if let key = statementConsentKey { UserDefaults.standard.set(newValue, forKey: key); objectWillChange.send() } }
    }
    var reconciliation: ReconciliationReport {
        Reconciliation.build(transactions: bank.transactions, matches: bank.matches, receipts: receipts, suggestions: bank.suggestions)
    }

    func loadBank() async throws {
        let uid = try requireUser(); let client = try requireClient()
        let statements: [BankStatement] = try await client.from("bank_statements")
            .select("id,account_label,account_type,file_name,file_path,period_start,period_end,status,stage,page_count,pages_done,pages_failed,transactions_imported,duplicates_skipped,error_message,created_at")
            .eq("user_id", value: uid.uuidString).order("created_at", ascending: false).execute().value
        var transactions: [BankTransaction] = []; var offset = 0
        while true {
            let page: [BankTransaction] = try await client.from("bank_transactions")
                .select("id,statement_id,booking_date,amount,currency,description,match_status")
                .eq("user_id", value: uid.uuidString).order("booking_date", ascending: false).order("id")
                .range(from: offset, to: offset + 499).execute().value
            transactions += page; if page.count < 500 { break }; offset += 500
        }
        let matches: [ReceiptMatch] = try await client.from("receipt_matches")
            .select("id,transaction_id,receipt_id,score,match_type").eq("user_id", value: uid.uuidString).execute().value
        let suggestions: [MatchSuggestion] = try await client.from("match_suggestions")
            .select("id,transaction_id,receipt_id,kind,diff_amount,score,status,note")
            .eq("user_id", value: uid.uuidString).eq("status", value: "open").execute().value
        guard userID == uid else { return }
        let keep = bank.shared
        bank = BankSnapshot(statements: statements, transactions: transactions, matches: matches, suggestions: suggestions, shared: keep, loaded: true)
    }

    /// Lädt die Datei in den privaten Bucket und reiht den Auszug für die Verarbeitung ein.
    func uploadStatement(fileURL: URL, label: String, accountType: String) async throws {
        let uid = try requireUser(); let client = try requireClient()
        guard aiConsent, statementConsent else {
            throw PiggyFailure.validation("Bitte stimme zuerst der Auswertung von Kontoauszügen zu.")
        }
        let scoped = fileURL.startAccessingSecurityScopedResource()
        defer { if scoped { fileURL.stopAccessingSecurityScopedResource() } }
        let data = try Data(contentsOf: fileURL)
        let fileName = fileURL.lastPathComponent
        let mime = try StatementFile.validate(fileName: fileName, byteCount: data.count)
        let path = "\(uid.uuidString.lowercased())/\(Int(Date().timeIntervalSince1970 * 1000))_\(StatementFile.safeName(fileName))"
        try await uploadObject(bucket: "bank-statements", path: path, data: data, contentType: mime)
        struct Inserted: Decodable { let id: UUID }
        let name = label.trimmingCharacters(in: .whitespacesAndNewlines)
        do {
            let _: Inserted = try await client.from("bank_statements").insert([
                "user_id": JSONValue.uuid(uid), "account_label": .string(name.isEmpty ? (accountType == "credit" ? "Kreditkarte" : "Konto") : name),
                "account_type": .string(accountType), "file_name": .string(fileName), "file_path": .string(path),
                "mime_type": .string(mime), "stage": .string("queued"), "status": .string("processing")
            ]).select("id").single().execute().value
        } catch {
            // Keine verwaiste Datei ohne Eintrag zurücklassen.
            try? await removeObject(bucket: "bank-statements", path: path)
            throw error
        }
        _ = try? await client.rpc("kick_statement_worker").execute()
        try await loadBank()
    }

    /// Entfernt Datei und Auszug samt Buchungen und Zuordnungen (kaskadierend). Erst die Datei, dann die Zeile.
    func deleteStatement(_ statement: BankStatement) async throws {
        let client = try requireClient()
        if let path = statement.file_path, !path.isEmpty {
            try await removeObject(bucket: "bank-statements", path: path)
        }
        try await client.from("bank_statements").delete().eq("id", value: statement.id.uuidString).execute()
        try await loadBank()
    }

    func retryStatement(_ statement: BankStatement) async throws {
        try await requireClient().rpc("retry_statement", params: ["p_statement_id": statement.id.uuidString]).execute()
        try await loadBank()
    }

    func rematchAll() async throws {
        try await requireClient().rpc("enqueue_rematch").execute()
        try await loadBank()
    }

    func acceptSuggestion(_ item: OpenSuggestion) async throws {
        let params: [String: JSONValue] = [
            "p_suggestion_id": .uuid(item.suggestion.id), "p_kind": .string(item.suggestion.kind),
            "p_amount": .number(item.suggestion.diff_amount), "p_note": .optional(item.suggestion.note)
        ]
        try await requireClient().rpc("accept_match_suggestion", params: params).execute()
        await reload(); try await loadBank()
    }

    func rejectSuggestion(_ item: OpenSuggestion) async throws {
        try await requireClient().from("match_suggestions").update([
            "status": JSONValue.string("rejected"), "resolved_at": .string(ISO8601DateFormatter().string(from: Date()))
        ]).eq("id", value: item.suggestion.id.uuidString).execute()
        try await loadBank()
    }

    func manualMatch(transactionID: UUID, receiptID: UUID) async throws {
        let uid = try requireUser(); let client = try requireClient()
        try await client.from("receipt_matches").insert([
            "user_id": JSONValue.uuid(uid), "transaction_id": .uuid(transactionID), "receipt_id": .uuid(receiptID),
            "score": .number(1), "match_type": .string("manual")
        ]).execute()
        do {
            try await client.from("bank_transactions").update(["match_status": "matched"]).eq("id", value: transactionID.uuidString).execute()
        } catch {
            // Zuordnung zurücknehmen, damit Buchung und Zuordnung nicht auseinanderlaufen.
            _ = try? await client.from("receipt_matches").delete().eq("transaction_id", value: transactionID.uuidString).eq("receipt_id", value: receiptID.uuidString).execute()
            throw error
        }
        try await loadBank()
    }

    func removeMatch(transactionID: UUID) async throws {
        let client = try requireClient()
        try await client.from("receipt_matches").delete().eq("transaction_id", value: transactionID.uuidString).execute()
        try await client.from("bank_transactions").update(["match_status": "unmatched"]).eq("id", value: transactionID.uuidString).execute()
        try await loadBank()
    }

    func ignoreTransaction(_ id: UUID) async throws {
        try await requireClient().from("bank_transactions").update(["match_status": "ignored"]).eq("id", value: id.uuidString).execute()
        try await loadBank()
    }
}

// MARK: - Ausgaben an Freunde senden (Option C, hinter PIGGY_SHARE_BANK_ITEMS)

extension PiggyStore {
    func loadSharedItems() async throws {
        guard BankFeature.sharing else { return }
        let uid = try requireUser()
        let rows: [SharedBankItem] = try await requireClient().from("shared_bank_items")
            .select("*,sender:profiles!sender_id(*),recipient:profiles!recipient_id(*)")
            .or("sender_id.eq.\(uid),recipient_id.eq.\(uid)").order("created_at", ascending: false).execute().value
        guard userID == uid else { return }
        bank.shared = rows
    }

    func sendBankItem(_ pair: ConfirmedPair, to friend: Profile, note: String) async throws {
        let uid = try requireUser()
        guard friends.contains(where: { $0.profile.id == friend.id }) else {
            throw PiggyFailure.validation("Die Person ist kein bestätigter Freund.")
        }
        try await requireClient().from("shared_bank_items").insert([
            "sender_id": JSONValue.uuid(uid), "recipient_id": .uuid(friend.id), "title": .string(pair.receipt.store_name),
            "booking_date": .string(pair.transaction.booking_date), "amount": .number(abs(pair.transaction.amount)),
            "currency": .string(pair.transaction.currency), "note": .optional(SharedBankItem.cleanNote(note))
        ]).execute()
        try await loadSharedItems()
    }

    func respond(to item: SharedBankItem, accept: Bool) async throws {
        try await requireClient().rpc("respond_shared_bank_item", params: [
            "p_id": JSONValue.uuid(item.id), "p_accept": .bool(accept)
        ]).execute()
        try await loadSharedItems()
    }

    func withdraw(_ item: SharedBankItem) async throws {
        try await requireClient().from("shared_bank_items").delete().eq("id", value: item.id.uuidString).execute()
        try await loadSharedItems()
    }
}
