import Foundation
import UIKit
import Vision
import Supabase

extension PiggyStore {
    func processImage(image: UIImage, allowAI: Bool) async throws -> ReceiptDraft {
        let maxSide: CGFloat = 2200
        let scale = min(1, maxSide / max(image.size.width, image.size.height))
        let size = CGSize(width: image.size.width * scale, height: image.size.height * scale)
        let format = UIGraphicsImageRendererFormat(); format.scale = 1; format.opaque = true
        let normalized = UIGraphicsImageRenderer(size: size, format: format).image { _ in image.draw(in: CGRect(origin: .zero, size: size)) }
        guard let cgImage = normalized.cgImage else { throw PiggyFailure.validation("Das Bild konnte nicht gelesen werden.") }
        let text = try await Task.detached(priority: .userInitiated) {
            let request = VNRecognizeTextRequest()
            request.recognitionLevel = .accurate
            request.recognitionLanguages = ["de-DE", "fr-FR", "it-IT", "en-US"]
            request.usesLanguageCorrection = true
            try VNImageRequestHandler(cgImage: cgImage).perform([request])
            return (request.results ?? []).compactMap { $0.topCandidates(1).first?.string }.joined(separator: "\n")
        }.value
        guard !text.isEmpty else { throw PiggyFailure.validation("Kein lesbarer Text erkannt. Bitte fotografiere den Beleg bei gutem Licht erneut.") }
        guard allowAI && aiConsent else { return LocalReceiptParser.parse(text) }
        struct ScanReply: Decodable { let result: ParsedReceipt }
        let uid = try requireUser()
        do {
            let reply: ScanReply = try await requireClient().functions.invoke("scan-receipt", options: .init(body: ["ocr_text": text]))
            guard userID == uid else { throw PiggyFailure.signedOut }
            return reply.result.draft(rawText: text)
        } catch {
            guard userID == uid else { throw PiggyFailure.signedOut }
            var draft = LocalReceiptParser.parse(text)
            draft.recognition_warning = "Die KI-Erkennung konnte nicht abgeschlossen werden (z. B. Verbindung oder Scanlimit). Der lokal erkannte Text bleibt erhalten. Bitte prüfe und ergänze den Entwurf."
            return draft
        }
    }
    func duplicateCandidates(for draft: ReceiptDraft) -> [Receipt] { ReceiptRules.duplicates(draft, in: receipts) }
    private func upload(_ image: UIImage?, user: UUID) async throws -> String? {
        guard let image else { return nil }
        guard let data = image.jpegData(compressionQuality: 0.85) else { throw PiggyFailure.validation("Das Belegbild konnte nicht vorbereitet werden.") }
        let path = "\(user.uuidString.lowercased())/\(UUID().uuidString.lowercased()).jpg"
        try await uploadObject(bucket: "receipt-images", path: path, data: data, contentType: "image/jpeg")
        return path
    }
    func scanPayload(_ draft: ReceiptDraft, path: String?) -> JSONValue {
        .object([
            "store_name": .string(draft.store_name), "receipt_date": .string(draft.receipt_date),
            "store_category": .optional(draft.store_category), "total_amount": .number(draft.total_amount),
            "currency": .string(draft.currency), "payment_method": .string(draft.payment_method),
            "payment_card": .optional(PaymentCard.normalized(draft.payment_card, method: draft.payment_method, cards: cards)), "notes": .string(draft.notes),
            "raw_ocr_text": .string(draft.raw_ocr_text), "image_url": .optional(path),
            "extra_fields": .object(ParkingDetails.read(extras: draft.extra_fields, raw: draft.raw_ocr_text, receiptDate: draft.receipt_date).applying(to: ReceiptMetadata.enriched(draft.extra_fields, raw: draft.raw_ocr_text)).mapValues(JSONValue.optional)),
            "receipt_items": .array(draft.items.map { .object([
                "name": .string($0.name), "quantity": .number($0.quantity), "unit": .string($0.unit),
                "unit_price": .number($0.unit_price), "total_price": .number($0.total_price),
                "tags": .array($0.tags.map(JSONValue.string)), "subcategory": .optional($0.subcategory)
            ]) })
        ])
    }
    func save(draft: ReceiptDraft, image: UIImage?, separateFrom: [UUID]) async throws -> UUID {
        try ReceiptRules.validate(draft)
        let uid = try requireUser(); let client = try requireClient()
        let path = try await upload(image, user: uid)
        do {
            guard userID == uid else { throw PiggyFailure.signedOut }
            let id: UUID = try await client.rpc("save_separate_receipt_scan", params: [
                "p_scan": scanPayload(draft, path: path), "p_separate_ids": .array(separateFrom.map(JSONValue.uuid))
            ]).execute().value
            await reload(); return id
        } catch {
            // A lost HTTP response can follow a committed RPC. Keep the uploaded image
            // and reload before retrying so we never remove a now-referenced receipt image.
            await reload()
            throw PiggyFailure.service("Die Speicherbestätigung fehlt. Prüfe zuerst deine Belegliste, bevor du erneut speicherst. " + error.localizedDescription)
        }
    }
    func merge(draft: ReceiptDraft, image: UIImage?, target: Receipt, choices: [String: JSONValue] = [:]) async throws -> UUID {
        try ReceiptRules.validate(draft)
        let uid = try requireUser(); let client = try requireClient()
        // Upload only when the user chose the new image, or when the existing image is missing.
        let useNewImage: Bool
        if case .string("source")? = choices["image"] { useNewImage = true }
        else { useNewImage = target.image_url == nil }
        let path = useNewImage ? try await upload(image, user: uid) : nil
        let id: UUID = try await client.rpc("merge_receipt_scan", params: [
            "p_target_id": .uuid(target.id), "p_scan": scanPayload(draft, path: path), "p_choices": .object(choices)
        ]).execute().value
        guard userID == uid else { throw PiggyFailure.signedOut }
        await reload(); return id
    }
    func signedImageURL(path: String) async throws -> URL {
        let normalized = try ObjectStoragePolicy.receiptPath(path, supabaseURL: LaunchConfiguration.value("SUPABASE_URL"), userID: requireUser())
        return try await signedObjectURL(bucket: "receipt-images", path: normalized)
    }
    func delete(receipt: Receipt) async throws {
        try await requireClient().rpc("delete_reviewed_receipt", params: ["p_receipt_id": receipt.id]).execute()
        await reload()
    }
    func assign(receiptID: UUID, projectID: UUID?) async throws {
        try await requireClient().from("receipts").update(["project_id": projectID.map(JSONValue.uuid) ?? .null]).eq("id", value: receiptID.uuidString).execute()
        await reload()
    }
    func update(receipt: Receipt) async throws {
        let client = try requireClient(); let uid = try requireUser()
        guard receipt.user_id == uid else { throw PiggyFailure.signedOut }
        var draft = ReceiptDraft()
        draft.store_name = receipt.store_name; draft.receipt_date = receipt.receipt_date ?? ""
        draft.total_amount = receipt.total_amount ?? 0; draft.currency = receipt.currency
        draft.payment_card = receipt.payment_card
        draft.items = receipt.receipt_items.map { item in
            var row = DraftItem()
            row.name = item.name; row.quantity = item.quantity ?? 1
            row.total_price = item.total_price ?? 0; row.unit_price = item.unit_price ?? 0
            return row
        }
        try ReceiptRules.validate(draft, allowMissingDate: true)
        let current: Receipt = try await client.from("receipts").select("*,receipt_items(*)")
            .eq("id", value: receipt.id.uuidString).eq("user_id", value: uid.uuidString).single().execute().value
        let existingIDs = Set(current.receipt_items.map(\.id))
        let retainedIDs = Set(receipt.receipt_items.map(\.id))
        let removed = current.receipt_items.filter { !retainedIDs.contains($0.id) }
        guard !removed.contains(where: { $0.is_adjustment == true }),
              current.receipt_items.filter({ $0.is_adjustment == true }).allSatisfy({ old in receipt.receipt_items.contains(old) }) else {
            throw PiggyFailure.validation("Bank-Ausgleichsposten können hier nicht verändert oder gelöscht werden. Prüfe zuerst den Abgleich.")
        }
        if !removed.isEmpty {
            let splits: [MutationID] = try await client.from("splits").select("id").eq("receipt_id", value: receipt.id.uuidString).execute().value
            guard splits.isEmpty else { throw PiggyFailure.validation("Dieser Beleg ist aufgeteilt. Artikel können erst nach Prüfung der Aufteilung entfernt werden.") }
        }
        // All receipt metadata is one update; item edits are individually acknowledged.
        do {
            let _: MutationID = try await client.from("receipts").update([
                "store_name": JSONValue.string(receipt.store_name), "receipt_date": .optional(receipt.receipt_date?.isEmpty == true ? nil : receipt.receipt_date),
                "total_amount": receipt.total_amount.map(JSONValue.number) ?? .null,
                "currency": .string(receipt.currency), "notes": .optional(receipt.notes),
                "store_category": .optional(receipt.store_category),
                "extra_fields": .object((receipt.extra_fields ?? [:]).mapValues(JSONValue.optional)),
                "payment_method": .optional(receipt.payment_method),
                "payment_card": .optional(PaymentCard.normalized(receipt.payment_card, method: receipt.payment_method, cards: cards))
            ]).eq("id", value: receipt.id.uuidString).eq("user_id", value: uid.uuidString).select("id").single().execute().value
            for item in removed {
                let _: MutationID = try await client.from("receipt_items").delete()
                    .eq("id", value: item.id.uuidString).eq("receipt_id", value: receipt.id.uuidString).select("id").single().execute().value
            }
            for item in receipt.receipt_items where item.is_adjustment != true {
                let payload: [String: JSONValue] = [
                    "receipt_id": .uuid(receipt.id), "name": .string(item.name), "unit": .optional(item.unit),
                    "tags": .array(item.tags.map(JSONValue.string)), "subcategory": .optional(item.subcategory),
                    "total_price": item.total_price.map(JSONValue.number) ?? .null, "quantity": item.quantity.map(JSONValue.number) ?? .null,
                    "unit_price": item.unit_price.map(JSONValue.number) ?? .null,
                    "classified_at": .string(ISO8601DateFormatter().string(from: Date()))
                ]
                if !existingIDs.contains(item.id) {
                    let _: MutationID = try await client.from("receipt_items").insert(payload).select("id").single().execute().value
                    continue
                }
                let _: MutationID = try await client.from("receipt_items").update([
                    "name": JSONValue.string(item.name), "unit": .optional(item.unit), "tags": .array(item.tags.map(JSONValue.string)),
                    "subcategory": .optional(item.subcategory), "total_price": item.total_price.map(JSONValue.number) ?? .null,
                    "quantity": item.quantity.map(JSONValue.number) ?? .null, "unit_price": item.unit_price.map(JSONValue.number) ?? .null,
                    "classified_at": .string(ISO8601DateFormatter().string(from: Date()))
                ]).eq("id", value: item.id.uuidString).eq("receipt_id", value: receipt.id.uuidString).select("id").single().execute().value
            }
        } catch {
            await reload()
            throw PiggyFailure.service("Nicht alle Änderungen konnten gespeichert werden. Bitte prüfe den neu geladenen Beleg und versuche es erneut.")
        }
        await reload()
    }
}

private struct MutationID: Decodable { let id: UUID }

private struct ParsedReceipt: Decodable {
    var store_name: String?; var date: String?; var total_amount: Decimal?; var currency: String?
    var payment_method: String?; var payment_card: String?; var store_category: String?
    var extra: ReceiptMetadata.Values?
    var items: [ParsedItem]?
    struct ParsedItem: Decodable {
        var name: String?; var quantity: Decimal?; var unit: String?; var unit_price: Decimal?
        var total_price: Decimal?; var tags: [String]?; var subcategory: String?
    }
    func draft(rawText: String) -> ReceiptDraft {
        var draft = ReceiptDraft()
        draft.store_name = store_name ?? ""; draft.receipt_date = date ?? ""
        draft.total_amount = total_amount ?? 0; draft.currency = currency ?? "CHF"
        draft.payment_method = payment_method ?? "Unbekannt"
        draft.payment_card = PaymentCard.normalized(payment_card, method: payment_method, cards: []) ?? PaymentCard.fromOCR(rawText)
        draft.store_category = store_category; draft.raw_ocr_text = rawText; draft.extra_fields = ReceiptMetadata.enriched(extra?.fields, raw: rawText)
        draft.items = (items ?? []).map { item in
            var row = DraftItem(); row.name = item.name ?? "Artikel"; row.quantity = item.quantity ?? 1
            row.unit = item.unit ?? "Stk"; row.unit_price = item.unit_price ?? 0
            row.total_price = item.total_price ?? 0; row.tags = item.tags ?? ["Diverses"]
            row.subcategory = item.subcategory; return row
        }
        return draft
    }
}
