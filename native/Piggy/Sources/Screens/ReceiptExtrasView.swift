import SwiftUI

struct ReceiptBankSection: View {
    @EnvironmentObject private var store: PiggyStore
    let receiptID: UUID
    @State private var error: String?
    private var transactions: [BankTransaction] {
        let ids = Set(store.bank.matches.filter { $0.receipt_id == receiptID }.map(\.transaction_id))
        return store.bank.transactions.filter { ids.contains($0.id) }
    }
    var body: some View {
        Section("Bankbeleg") {
            if let error { Text(error).foregroundStyle(.secondary) }
            else if !store.bank.loaded { ProgressView("Zuordnungen werden geladen …") }
            else if transactions.isEmpty { Text("Kein Bankbeleg zugeordnet").foregroundStyle(.secondary) }
            ForEach(transactions) { transaction in
                VStack(alignment: .leading, spacing: 4) {
                    Text(transaction.description)
                    Text("\(receiptDate(transaction.booking_date)) · \(money(abs(transaction.amount), transaction.currency))").font(.subheadline)
                }
            }
            NavigationLink("Abgleich öffnen") { BankHomeView() }
        }
        .task { do { try await store.loadBank(); error = nil } catch { self.error = error.localizedDescription } }
    }
}

struct ReceiptExtrasEditor: View {
    @Binding var extras: [String: String?]?
    let rawText: String?
    let receiptDate: String?
    @State private var showParking = false
    private var parking: ParkingDetails { ParkingDetails.read(extras: extras, raw: rawText, receiptDate: receiptDate) }
    var body: some View {
        Section("Zusatzangaben") {
            ForEach(ReceiptExtras.labels, id: \.0) { key, title in
                LabeledContent(title) {
                    TextField(title, text: Binding(get: { (extras?[key] ?? nil) ?? "" }, set: { value in
                        var fields = extras ?? [:]; fields[key] = .some(value.isEmpty ? nil : value); extras = fields
                    })).multilineTextAlignment(.trailing)
                }
            }
        }
        Section("Parkbeleg") {
            Toggle("Parkdetails bearbeiten", isOn: $showParking)
            if showParking || parking.hasDetails {
                parkingInput("Ankunft", key: "parking_arrival", value: parking.arrival, hint: "JJJJ-MM-TT HH:mm")
                parkingInput("Abfahrt", key: "parking_departure", value: parking.departure, hint: "JJJJ-MM-TT HH:mm")
                parkingInput("Gedruckte Dauer in Minuten", key: "parking_duration_minutes", value: parking.printedMinutes, hint: "Optional")
                parkingInput("Parkplatz", key: "parking_location", value: parking.location, hint: "Optional")
                parkingInput("Kennzeichen", key: "parking_plate", value: parking.plate, hint: "Optional")
                Text("Nur aufgedruckte Angaben übernehmen. Ohne eindeutiges Datum wird kein Tageswechsel angenommen. Eine gedruckte Dauer hat Vorrang, auch bei Sommer-/Winterzeitwechsel.")
                    .font(.footnote).foregroundStyle(.secondary)
            }
        }
    }
    private func parkingInput(_ title: String, key: String, value: String, hint: String) -> some View {
        VStack(alignment: .leading, spacing: 6) {
            Text(title).font(.subheadline).foregroundStyle(.secondary)
            TextField(hint, text: Binding(get: { value }, set: { text in
                var fields = parking.applying(to: extras)
                fields[key] = .some(text.isEmpty ? nil : text); extras = fields
            })).autocorrectionDisabled().textInputAutocapitalization(.never)
        }
    }
}

struct ParkingSummary: View {
    let details: ParkingDetails
    let total: Decimal?
    let currency: String
    var body: some View {
        Section("Parkdetails") {
            if !details.location.isEmpty { LabeledContent("Parkplatz", value: details.location) }
            if !details.plate.isEmpty { LabeledContent("Kennzeichen", value: details.plate) }
            if !details.arrival.isEmpty { LabeledContent("Ankunft", value: details.arrival) }
            if !details.departure.isEmpty { LabeledContent("Abfahrt", value: details.departure) }
            if let minutes = details.minutes {
                LabeledContent("Dauer", value: "\(AmountInput.text(minutes)) Minuten")
                if let rate = details.perMinute(total: total) {
                    LabeledContent("Bezahlt für diese Dauer", value: money(total, currency))
                    LabeledContent("Durchschnitt je Minute", value: preciseRate(rate))
                    LabeledContent("Durchschnitt je Stunde", value: money(rate * 60, currency))
                    Text("Durchschnitt aus Gesamtbetrag und Dauer, kein zugesicherter Parktarif.").font(.footnote).foregroundStyle(.secondary)
                }
            } else { Text("Dauer nicht eindeutig. Prüfe Ankunft, Abfahrt und Datum unter Bearbeiten.").font(.footnote).foregroundStyle(.secondary) }
        }
    }
    private func preciseRate(_ rate: Decimal) -> String {
        let formatter = NumberFormatter(); formatter.locale = Locale(identifier: "de_CH")
        formatter.minimumFractionDigits = 2; formatter.maximumFractionDigits = 4
        return "\(formatter.string(from: NSDecimalNumber(decimal: rate)) ?? "–") \(currency)"
    }
}
