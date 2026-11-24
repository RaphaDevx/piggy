import SwiftUI

struct SocialHomeView: View {
    @EnvironmentObject private var store: PiggyStore
    @State private var code = ""
    @State private var name = ""
    @State private var projectDescription = ""
    @State private var creating = false
    @State private var busy = false
    @State private var error: String?
    var body: some View {
        List {
            if let profile = store.profile {
                Section("Dein Piggy-Code") {
                    HStack { Text(profile.piggy_code).font(.title3.monospaced().bold()); Spacer(); ShareLink(item: profile.piggy_code) { Image(systemName: "square.and.arrow.up") }.accessibilityLabel("Piggy-Code teilen") }
                    Text("Mit diesem Code können Freunde dich hinzufügen.").foregroundStyle(.secondary)
                }
            }
            Section("Freund hinzufügen") {
                TextField("Piggy-Code", text: $code).textInputAutocapitalization(.characters).autocorrectionDisabled()
                Button("Anfrage senden") { run { try await store.sendFriendRequest(code: code); code = "" } }.disabled(busy || code.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty)
            }
            if !store.requests.isEmpty {
                Section("Anfragen") {
                    ForEach(store.requests) { friend in
                        VStack(alignment: .leading, spacing: 8) {
                            Text(friend.profile.name).font(.headline)
                            if friend.outgoing { Text("Anfrage gesendet").foregroundStyle(.secondary) }
                            else { Button("Annehmen") { run { try await store.respondToFriend(id: friend.id, accept: true) } } }
                            Button(friend.outgoing ? "Anfrage zurücknehmen" : "Ablehnen", role: .destructive) { run { try await store.respondToFriend(id: friend.id, accept: false) } }
                        }.disabled(busy)
                    }
                }
            }
            Section("Freunde") {
                if store.friends.isEmpty { Text("Deine Freunde erscheinen hier, sobald eine Anfrage angenommen wurde.").foregroundStyle(.secondary) }
                ForEach(store.friends) { friend in
                    Text(friend.profile.name)
                        .swipeActions { Button("Entfernen", role: .destructive) { run { try await store.removeFriend(id: friend.id) } } }
                }
            }
            Section("Projekte") {
                ForEach(store.projects) { project in NavigationLink { ProjectDetailView(project: project) } label: { Label(project.name, systemImage: "folder") } }
                Button("Neues Projekt") { creating = true }
            }
            Section("Offene Beträge") {
                if store.balances.isEmpty { Text("Alles ausgeglichen").foregroundStyle(.secondary) }
                ForEach(store.balances) { balance in
                    VStack(alignment: .leading) {
                        Text(balance.otherName).font(.headline)
                        Text("\(balance.owedToMe ? "Schuldet dir" : "Du schuldest"): \(money(balance.amount, balance.currency))")
                        Text(balance.receiptName).font(.caption).foregroundStyle(.secondary)
                        Button("Als bezahlt markieren") { run { try await store.settle(participantID: balance.id) } }.disabled(busy)
                    }
                }
            }
            if !store.settledEntries.isEmpty {
                Section("Beglichen") {
                    ForEach(store.settledEntries.prefix(20)) { entry in
                        VStack(alignment: .leading, spacing: 2) {
                            Text(entry.otherName).font(.headline)
                            Text("\(entry.owedToMe ? "Hat dir bezahlt" : "Du hast bezahlt"): \(money(entry.amount, entry.currency))")
                            Text(entry.receiptName + (entry.settledAt.map { " · " + receiptDate($0) } ?? "")).font(.caption).foregroundStyle(.secondary)
                        }
                        .accessibilityElement(children: .combine)
                    }
                }
            }
            SharedItemsSection()
            if let error { Section { ScreenError(message: error) } }
        }
        .navigationTitle("Gemeinsam")
        .refreshable { await store.reload(); try? await store.loadSharedItems() }
        .task { try? await store.loadSharedItems() }
        .sheet(isPresented: $creating) {
            NavigationStack {
                Form {
                    TextField("Projektname", text: $name)
                    TextField("Beschreibung", text: $projectDescription, axis: .vertical)
                    if let error { Text(error).foregroundStyle(.red) }
                    BusyButton(title: "Projekt erstellen", busy: busy, disabled: name.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty) {
                        run { try await store.createProject(name: name, description: projectDescription, friendIDs: []); name = ""; projectDescription = ""; creating = false }
                    }
                }.navigationTitle("Neues Projekt").toolbar { ToolbarItem(placement: .cancellationAction) { Button("Abbrechen") { creating = false }.disabled(busy) } }
            }.interactiveDismissDisabled(busy)
        }
    }
    private func run(_ work: @escaping @MainActor () async throws -> Void) {
        busy = true; error = nil
        Task { do { try await work() } catch { self.error = error.localizedDescription }; busy = false }
    }
}

struct ProjectDetailView: View {
    let project: Project
    @EnvironmentObject private var store: PiggyStore
    var body: some View {
        List {
            if let description = project.description, !description.isEmpty { Section { Text(description) } }
            Section("Mitglieder") {
                ForEach(project.project_members ?? []) { member in Text(member.profile?.name ?? "Mitglied") }
            }
            Section("Belege") {
                ForEach(store.receipts.filter { $0.project_id == project.id }) { receipt in
                    NavigationLink { ReceiptDetailView(receiptID: receipt.id) } label: { ReceiptRow(receipt: receipt) }
                }
                Text("Ordne Belege über deren Detailansicht diesem Projekt zu.").font(.footnote).foregroundStyle(.secondary)
            }
        }.navigationTitle(project.name)
    }
}

struct SplitReceiptView: View {
    let receipt: Receipt
    @EnvironmentObject private var store: PiggyStore
    @Environment(\.dismiss) private var dismiss
    @State private var selected: Set<UUID> = []
    @State private var excluded: [UUID: Set<UUID>] = [:]
    @State private var busy = false
    @State private var error: String?
    private var people: [UUID] { Array(selected.union(store.userID.map { [$0] } ?? [])) }
    private var shares: [UUID: Decimal] { (try? ReceiptRules.shares(items: receipt.receipt_items, total: receipt.total_amount ?? 0, participants: people, exclusions: excluded)) ?? [:] }
    var body: some View {
        Form {
            Section { Text(receipt.store_name).font(.headline); Text(money(receipt.total_amount, receipt.currency)) }
            Section("Personen auswählen") {
                Text("Du hast bezahlt und bist immer beteiligt.").foregroundStyle(.secondary)
                ForEach(store.friends) { friend in
                    Toggle(friend.profile.name, isOn: Binding(get: { selected.contains(friend.profile.id) }, set: { on in
                        if on { selected.insert(friend.profile.id) } else { selected.remove(friend.profile.id); excluded[friend.profile.id] = nil }
                    }))
                }
                if store.friends.isEmpty { Text("Füge zuerst Freunde unter «Gemeinsam» hinzu.") }
            }
            if !receipt.receipt_items.isEmpty {
                Section("Artikel zuordnen") {
                    ForEach(receipt.receipt_items) { item in
                        DisclosureGroup(item.name) {
                            ForEach(people.sorted { $0.uuidString < $1.uuidString }, id: \.self) { person in
                                Toggle(person == store.userID ? "Du" : store.friends.first(where: { $0.profile.id == person })?.profile.name ?? "Kontakt", isOn: Binding(
                                    get: { !(excluded[person]?.contains(item.id) ?? false) },
                                    set: { included in if included { excluded[person, default: []].remove(item.id) } else { excluded[person, default: []].insert(item.id) } }
                                ))
                            }
                        }
                    }
                }
            }
            Section("Anteile") {
                ForEach(people.sorted { $0.uuidString < $1.uuidString }, id: \.self) { person in
                    LabeledContent(person == store.userID ? "Du" : store.friends.first(where: { $0.profile.id == person })?.profile.name ?? "Kontakt", value: money(shares[person], receipt.currency))
                }
            }
            if let error { Section { ScreenError(message: error) } }
            BusyButton(title: "Aufteilung speichern", busy: busy, disabled: selected.isEmpty) {
                busy = true
                Task { do { try await store.split(receipt: receipt, participantIDs: people, exclusions: excluded); dismiss() } catch { self.error = error.localizedDescription }; busy = false }
            }
        }.navigationTitle("Ausgabe aufteilen").disabled(busy)
    }
}
