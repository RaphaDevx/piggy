import SwiftUI
import UIKit

func money(_ amount: Decimal?, _ currency: String = "CHF") -> String {
    guard let amount else { return "–" }
    let formatter = NumberFormatter()
    formatter.numberStyle = .currency
    formatter.currencyCode = currency
    formatter.locale = Locale(identifier: "de_CH")
    return formatter.string(from: NSDecimalNumber(decimal: amount)) ?? "\(amount) \(currency)"
}

func receiptDate(_ value: String?) -> String {
    guard let value, !value.isEmpty else { return "Datum fehlt" }
    let parser = DateFormatter()
    parser.locale = Locale(identifier: "en_US_POSIX")
    parser.dateFormat = "yyyy-MM-dd"
    guard let date = parser.date(from: String(value.prefix(10))) else { return value }
    return date.formatted(date: .abbreviated, time: .omitted)
}

func monthKey(_ date: Date) -> String {
    let formatter = DateFormatter()
    formatter.locale = Locale(identifier: "en_US_POSIX")
    formatter.dateFormat = "yyyy-MM"
    return formatter.string(from: date)
}

struct PiggyCard<Content: View>: View {
    @ViewBuilder var content: Content
    var body: some View {
        VStack(alignment: .leading, spacing: 14) { content }
            .padding(20)
            .frame(maxWidth: .infinity, alignment: .leading)
            .background(Theme.surface, in: RoundedRectangle(cornerRadius: 24, style: .continuous))
    }
}

struct EmptyPiggy: View {
    let title: String
    let message: String
    var pose = "idle-happy"
    var body: some View {
        VStack(spacing: 12) {
            MascotView(pose: pose, size: 150).accessibilityHidden(true)
            Text(title).font(.title3.bold()).multilineTextAlignment(.center)
            Text(message).foregroundStyle(Theme.secondary).multilineTextAlignment(.center)
        }
        .padding(24)
        .frame(maxWidth: .infinity)
        .accessibilityElement(children: .combine)
    }
}

struct ScreenError: View {
    let message: String
    var retry: (() -> Void)? = nil
    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            Label("Das hat nicht geklappt", systemImage: "exclamationmark.circle")
                .font(.headline)
            Text(message).font(.body)
            if let retry { Button("Erneut versuchen", action: retry).buttonStyle(.bordered) }
        }
        .foregroundStyle(Theme.text)
        .padding(16)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color.orange.opacity(0.12), in: RoundedRectangle(cornerRadius: 16))
        .accessibilityElement(children: .contain)
    }
}

struct ReceiptRow: View {
    let receipt: Receipt
    var body: some View {
        HStack(alignment: .center, spacing: 14) {
            Image(systemName: "receipt")
                .font(.title3)
                .foregroundStyle(Theme.text)
                .frame(width: 44, height: 44)
                .background(Theme.pink.opacity(0.22), in: RoundedRectangle(cornerRadius: 14))
                .accessibilityHidden(true)
            VStack(alignment: .leading, spacing: 4) {
                Text(receipt.store_name).font(.headline)
                Text(receiptDate(receipt.receipt_date)).font(.subheadline).foregroundStyle(Theme.secondary)
            }
            Spacer(minLength: 8)
            Text(money(receipt.total_amount, receipt.currency))
                .font(.body.monospacedDigit().weight(.semibold))
                .multilineTextAlignment(.trailing)
                .fixedSize(horizontal: false, vertical: true)
        }
        .padding(.vertical, 6)
        .accessibilityElement(children: .combine)
    }
}

struct BusyButton: View {
    let title: String
    let busy: Bool
    var disabled = false
    let action: () -> Void
    var body: some View {
        Button(action: action) {
            HStack(spacing: 10) {
                if busy { ProgressView().tint(Theme.text) }
                Text(title).fontWeight(.semibold)
            }
            .frame(maxWidth: .infinity, minHeight: 44)
        }
        .buttonStyle(.borderedProminent)
        .tint(Theme.accent)
        .foregroundStyle(Theme.text)
        .disabled(busy || disabled)
    }
}

struct DecimalField: View {
    let title: String
    @Binding var value: Decimal
    @State private var text = ""
    @FocusState private var focused: Bool
    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            Text(title).font(.subheadline).foregroundStyle(.secondary)
            HStack {
                TextField(title, text: $text)
                    .keyboardType(.decimalPad)
                    .focused($focused)
                    .monospacedDigit()
                    .accessibilityLabel(title)
                    .onChange(of: text) { _, new in value = AmountInput.parse(new) ?? .nan }
                if focused && !text.isEmpty {
                    Button { text = "" } label: { Image(systemName: "xmark.circle.fill") }
                        .foregroundStyle(.secondary).accessibilityLabel("\(title) leeren")
                        .frame(minWidth: 44, minHeight: 44)
                }
            }
            if AmountInput.parse(text) == nil {
                Text("Bitte eine Zahl mit Komma oder Punkt eingeben.").font(.footnote).foregroundStyle(.red)
            }
        }
        .onAppear { text = AmountInput.text(value) }
        .onChange(of: value) { _, new in
            if !focused && !new.isNaN { text = AmountInput.text(new) }
        }
        .toolbar {
            if focused {
                ToolbarItemGroup(placement: .keyboard) {
                    Button("+/−") {
                        if text.hasPrefix("-") { text.removeFirst() } else { text = "-" + text }
                    }.accessibilityLabel("Vorzeichen ändern")
                    Spacer()
                    Button("Fertig") { focused = false }
                }
            }
        }
    }
}

extension Receipt {
    var shareText: String {
        var lines = [store_name, receiptDate(receipt_date), "Gesamt: \(money(total_amount, currency))"]
        lines.append(contentsOf: receipt_items.map { "\($0.name): \(money($0.total_price, currency))" })
        if let notes, !notes.isEmpty { lines.append(notes) }
        return lines.joined(separator: "\n")
    }
}
