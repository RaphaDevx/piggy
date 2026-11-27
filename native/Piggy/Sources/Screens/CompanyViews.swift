import SwiftUI

struct CompanyHomeView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var companies: [CompanySummary] = []
    @State private var name = ""
    @State private var code = ""
    @State private var invitation: String?
    @State private var busy = false
    @State private var error: String?
    var body: some View {
        Form {
            Section {
                Text("Geschäftliche Belege einreichen und von einer anderen berechtigten Person prüfen lassen.")
                Text("Abacus-Anbindung und Auszahlung sind noch nicht eingerichtet.").font(.footnote).foregroundStyle(.secondary)
            }
            Section("Meine Firmen") {
                ForEach(companies) { company in
                    NavigationLink(company.name) { CompanyDetailView(companyID: company.id).id(store.userID) }
                }
                if companies.isEmpty { Text("Du bist noch keiner Firma beigetreten.").foregroundStyle(.secondary) }
            }
            Section("Firma erstellen") {
                TextField("Firmenname", text: $name)
                Text("Firmenwährung: CHF. Du wirst Firmenadmin. Für deine eigenen Spesen brauchst du eine weitere prüfberechtigte Person.").font(.footnote)
                Button("Firmenbereich erstellen") { run {
                    let reply: CompanyInvitation = try await store.companyAction("create", input:["name":.string(name),"currency":.string("CHF")])
                    invitation = reply.invite_code; name = ""; try await load()
                } }.disabled(busy || name.trimmingCharacters(in:.whitespacesAndNewlines).count < 2)
            }
            Section("Mit Einladung beitreten") {
                TextField("Einladungscode", text: $code).textInputAutocapitalization(.never).autocorrectionDisabled()
                Button("Firma beitreten") { run {
                    let _: CompanyInvitation = try await store.companyAction("join",input:["invite_code":.string(code)])
                    code = ""; try await load()
                } }.disabled(busy || code.isEmpty)
            }
            if let invitation { Section("Einladung · 7 Tage gültig") { ShareLink(item:invitation); Text(invitation).font(.caption.monospaced()).textSelection(.enabled) } }
            if busy { ProgressView() }
            if let error { ScreenError(message:error) }
        }
        .navigationTitle("Firmen & Spesen")
        .task(id:store.userID) { companies=[]; invitation=nil; run { try await load() } }
        .refreshable { do { try await load() } catch { self.error=error.localizedDescription } }
    }
    private func load() async throws { let reply: CompanyList = try await store.companyAction("list"); companies=reply.companies }
    private func run(_ work:@escaping @MainActor () async throws -> Void) {
        busy=true; error=nil; Task { do { try await work() } catch { self.error=error.localizedDescription }; busy=false }
    }
}

struct CompanyDetailView: View {
    @EnvironmentObject private var store: PiggyStore
    let companyID: UUID
    @State private var workspace: CompanyWorkspace?
    @State private var invite: String?
    @State private var submitting=false
    @State private var busy=false
    @State private var error: String?
    var body: some View {
        Form {
            if let workspace {
                Section {
                    Text(workspace.company.name).font(.headline)
                    Text("Freigabe bedeutet noch keine Erstattung. Abacus ist noch nicht verbunden.").font(.footnote).foregroundStyle(.secondary)
                }
                Section("Spesen") {
                    Button("Beleg einreichen") { submitting=true }.disabled(busy)
                    ForEach(workspace.submissions) { submission in
                        NavigationLink { CompanySubmissionView(companyID:companyID,submissionID:submission.id) } label: {
                            VStack(alignment:.leading) {
                                Text(submission.snapshot.store_name ?? "Beleg").lineLimit(1)
                                Text("\(AmountInput.text(submission.snapshot.total_amount)) \(submission.snapshot.currency) · \(submission.statusTitle)").font(.subheadline).foregroundStyle(.secondary)
                            }
                        }
                    }
                    if workspace.submissions.isEmpty { Text("Noch keine Einreichungen").foregroundStyle(.secondary) }
                }
                Section("Mitglieder") {
                    ForEach(workspace.members) { member in
                        if workspace.company.role == "admin" {
                            Menu {
                                ForEach(["member","accountant","admin"],id:\.self) { role in
                                    Button(roleTitle(role)) { run {
                                        let _:CompanyOK = try await store.companyAction("set_role",input:["company_id":.uuid(companyID),"user_id":.uuid(member.user_id),"role":.string(role)])
                                        try await load()
                                    } }
                                }
                            } label: { LabeledContent(member.name,value:roleTitle(member.role)) }.disabled(busy)
                        } else { LabeledContent(member.name,value:roleTitle(member.role)) }
                    }
                }
                if workspace.company.role == "admin" {
                    Section("Einladung") {
                        Button("Neuen Einladungscode erstellen") { run {
                            let reply:CompanyInvitation = try await store.companyAction("rotate_invite",input:["company_id":.uuid(companyID)])
                            invite=reply.invite_code
                        } }.disabled(busy)
                        Text("Ein neuer Code ersetzt den bisherigen. Wer den Code erhält, kann sieben Tage lang als Mitglied beitreten.").font(.footnote)
                        if let invite { ShareLink(item:invite); Text(invite).font(.caption.monospaced()).textSelection(.enabled) }
                    }
                }
            }
            if busy { ProgressView() }
            if let error { ScreenError(message:error) }
        }.navigationTitle("Firmenbereich")
        .task { run { try await load() } }
        .refreshable { do { try await load() } catch { self.error=error.localizedDescription } }
        .sheet(isPresented:$submitting,onDismiss:{ run { try await load() } }) {
            CompanySubmitView(companyID:companyID).environmentObject(store)
        }
    }
    private func roleTitle(_ role:String)->String { role == "admin" ? "Admin" : role == "accountant" ? "Buchhaltung" : "Mitglied" }
    private func load() async throws { workspace = try await store.companyAction("workspace",input:["company_id":.uuid(companyID)]) }
    private func run(_ work:@escaping @MainActor () async throws -> Void) { busy=true; error=nil; Task { do { try await work() } catch { self.error=error.localizedDescription }; busy=false } }
}

private struct CompanySubmitView: View {
    @EnvironmentObject private var store:PiggyStore
    @Environment(\.dismiss) private var dismiss
    let companyID:UUID
    @State private var receiptID:UUID?
    @State private var category="meal"
    @State private var purpose=""
    @State private var consent=false
    @State private var busy=false
    @State private var error:String?
    var body:some View {
        NavigationStack {
            Form {
                Section("Spesenbeleg") {
                    Picker("Beleg",selection:$receiptID) {
                        Text("Auswählen").tag(Optional<UUID>.none)
                        ForEach(store.receipts) { receipt in Text("\(receipt.store_name) · \(receipt.receipt_date ?? "") · \(AmountInput.text(receipt.total_amount ?? 0)) \(receipt.currency)").tag(Optional(receipt.id)) }
                    }
                    Picker("Spesenart",selection:$category) {
                        Text("Verpflegung").tag("meal"); Text("Transport").tag("transport"); Text("Übernachtung").tag("accommodation"); Text("Material").tag("material"); Text("Sonstiges").tag("other")
                    }
                    TextField("Geschäftlicher Grund",text:$purpose,axis:.vertical).lineLimit(2...4)
                }
                Section {
                    Toggle("Belegdaten mit dieser Firma teilen",isOn:$consent)
                    Text("Originalbild, Betrag, Artikel, Zahlungsangaben und gesamter erkannter Belegtext werden für die Prüfung in den Firmenbereich kopiert. Prüfe den Beleg vor dem Einreichen. Deine privaten Notizen werden nicht übernommen. Die Firmenkopie bleibt bei späteren Änderungen am privaten Beleg bestehen.").font(.footnote)
                    Button("Verbindlich einreichen") {
                        guard let receiptID else { return }; busy=true; error=nil
                        Task {
                            do {
                                let reply:CompanySubmitReply = try await store.companyAction("submit",input:["company_id":.uuid(companyID),"receipt_id":.uuid(receiptID),"category":.string(category),"note":.string(purpose)])
                                do { try await store.archiveCompanySubmission(reply.submission_id); dismiss() }
                                catch { self.error="Einreichung gespeichert. Das Original ist noch nicht archiviert. Öffne die Einreichung und wiederhole die Archivierung. \(error.localizedDescription)" }
                            } catch { self.error=error.localizedDescription }
                            busy=false
                        }
                    }.disabled(busy || receiptID == nil || !consent || purpose.trimmingCharacters(in:.whitespacesAndNewlines).count < 3)
                }
                if busy { ProgressView("Beleg wird eingereicht …") }
                if let error { ScreenError(message:error) }
            }.navigationTitle("Spesen einreichen")
                .toolbar { ToolbarItem(placement:.cancellationAction) { Button("Schliessen") { dismiss() }.disabled(busy) } }
        }.interactiveDismissDisabled(busy)
    }
}

private struct CompanySubmissionView:View {
    @EnvironmentObject private var store:PiggyStore
    let companyID:UUID; let submissionID:UUID
    @State private var workspace:CompanyWorkspace?
    @State private var reason=""
    @State private var image:URL?
    @State private var exportFile:URL?
    @State private var busy=false
    @State private var error:String?
    private var submission:CompanySubmission? { workspace?.submissions.first { $0.id == submissionID } }
    var body:some View {
        Form {
            if let submission, let workspace {
                Section("Eingereichter Belegstand") {
                    LabeledContent(submission.snapshot.store_name ?? "Beleg",value:"\(AmountInput.text(submission.snapshot.total_amount)) \(submission.snapshot.currency)")
                    LabeledContent("Datum",value:submission.snapshot.receipt_date ?? "–")
                    LabeledContent("Status",value:submission.statusTitle)
                    if let method=submission.snapshot.payment_method { LabeledContent("Bezahlt mit",value:method) }
                    if let card=submission.snapshot.payment_card { LabeledContent("Karte",value:card) }
                    Text(submission.note ?? "")
                    if submission.export_prepared { Text("Übergabepaket vorbereitet. Keine bestätigte Abacus-Übertragung oder Auszahlung.").font(.footnote) }
                    if submission.image_state == "archived" {
                        Button("Originalbeleg laden") { run { image = try await store.companyImage(submissionID) } }.disabled(busy)
                        if let image { AsyncImage(url:image) { phase in if let picture=phase.image { picture.resizable().scaledToFit() } else if phase.error != nil { Text("Bild konnte nicht geladen werden. Erneut laden.") } else { ProgressView() } } }
                    } else if submission.submitter_id == store.userID {
                        Button("Original archivieren / erneut versuchen") { run { try await store.archiveCompanySubmission(submissionID); try await load() } }.disabled(busy)
                    } else { Text("Die einreichende Person muss das Original zuerst archivieren.").font(.footnote) }
                }
                Section("Alle erkannten Belegangaben") {
                    if let text=submission.snapshot.raw_ocr_text { Text(text).font(.caption.monospaced()).textSelection(.enabled) }
                    DisclosureGroup("Strukturierte Belegdaten") { Text(snapshotText(submission.snapshot)).font(.caption.monospaced()).textSelection(.enabled) }
                }
                if submission.canReview(user:store.userID,company:workspace.company) {
                    Section("Prüfung") {
                        TextField("Notiz / Ablehnungsgrund",text:$reason,axis:.vertical)
                        Button("Freigeben") { review("approved") }.disabled(busy || submission.image_state != "archived")
                        Button("Ablehnen",role:.destructive) { review("rejected") }.disabled(busy || reason.trimmingCharacters(in:.whitespacesAndNewlines).count < 3)
                    }
                }
                if submission.status == "pending" && submission.submitter_id == store.userID {
                    Section { Text("Eine andere berechtigte Person muss deine Spesen prüfen.").font(.footnote)
                        Button("Einreichung zurückziehen",role:.destructive) { run { let _:CompanyOK = try await store.companyAction("withdraw",input:input); try await load() } }.disabled(busy)
                    }
                }
                if submission.status == "approved" && workspace.company.canReview {
                    Section("Übergabe vorbereiten") {
                        Text("Dieses JSON-Paket ist ein Piggy-Datenauszug. Es ist noch kein Abacus-Import und löst keine Zahlung aus.").font(.footnote)
                        Button("Übergabepaket erstellen") { run {
                            let reply:CompanyExportReply = try await store.companyAction("prepare_export",input:input)
                            let encoder=JSONEncoder(); encoder.outputFormatting=[.prettyPrinted,.sortedKeys]
                            let file=FileManager.default.temporaryDirectory.appendingPathComponent("piggy-expense-\(reply.export_id).json")
                            try encoder.encode(reply.payload).write(to:file,options:[.atomic,.completeFileProtection])
                            exportFile=file; try await load()
                        } }.disabled(busy)
                        if let exportFile { ShareLink("Übergabepaket teilen",item:exportFile) }
                    }
                }
                Section("Verlauf") {
                    ForEach(submission.events) { event in VStack(alignment:.leading) {
                        Text(event.title); Text(event.created_at).font(.caption).foregroundStyle(.secondary)
                        if let actor=event.actor_id { Text(workspace.members.first { $0.user_id == actor }?.name ?? actor.uuidString).font(.caption) }
                        Text(jsonText(event.detail)).font(.caption).textSelection(.enabled)
                    } }
                }
            }
            if busy { ProgressView() }
            if let error { ScreenError(message:error) }
        }.navigationTitle("Spesenbeleg")
            .task { run { try await load() } }
            .onDisappear { if let exportFile { try? FileManager.default.removeItem(at:exportFile) } }
    }
    private var input:[String:JSONValue] { ["company_id":.uuid(companyID),"submission_id":.uuid(submissionID)] }
    private func review(_ decision:String) { run { var args=input; args["decision"] = .string(decision); args["note"] = .string(reason); let _:CompanyOK = try await store.companyAction("review",input:args); try await load() } }
    private func load() async throws { workspace = try await store.companyAction("workspace",input:["company_id":.uuid(companyID)]) }
    private func run(_ work:@escaping @MainActor () async throws -> Void) { busy=true; error=nil; Task { do { try await work() } catch { self.error=error.localizedDescription }; busy=false } }
    private func jsonText(_ value:JSONValue)->String { let encoder=JSONEncoder();encoder.outputFormatting=[.prettyPrinted,.sortedKeys];return (try? encoder.encode(value)).flatMap { String(data:$0,encoding:.utf8) } ?? "" }
    private func snapshotText(_ snapshot:CompanySnapshot)->String {
        jsonText(.object(["Artikel":.array(snapshot.receipt_items),"Weitere Angaben":snapshot.extra_fields ?? .null]))
    }
}
