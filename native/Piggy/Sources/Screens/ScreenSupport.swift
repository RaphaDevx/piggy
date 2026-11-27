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
    var compact = false
    var minimumFractionDigits = 0
    var body: some View {
        VStack(alignment: .leading, spacing: compact ? 0 : 6) {
            if !compact { Text(title).font(.subheadline).foregroundStyle(.secondary) }
            NativeDecimalInput(value: $value, title: title, minimumFractionDigits: minimumFractionDigits)
                .frame(minHeight: 44)
            if value.isNaN {
                if compact { Image(systemName: "exclamationmark.circle").foregroundStyle(.red).accessibilityLabel("Ungültige Zahl") }
                else { Text("Bitte eine Zahl mit Komma oder Punkt eingeben.").font(.footnote).foregroundStyle(.red) }
            }
        }
    }
}

/// The decimal keyboard has no return key. Its native input accessory exists for every
/// numeric field, including fields inside sheets, independent of SwiftUI toolbar focus.
private struct NativeDecimalInput: UIViewRepresentable {
    @Binding var value: Decimal
    @Environment(\.isEnabled) private var isEnabled
    let title: String
    let minimumFractionDigits: Int
    func makeCoordinator() -> Coordinator { Coordinator(self) }
    func makeUIView(context: Context) -> UITextField {
        let field = UITextField()
        field.keyboardType = .decimalPad
        field.font = .preferredFont(forTextStyle: .body)
        field.adjustsFontForContentSizeCategory = true
        field.textAlignment = .right
        field.clearButtonMode = .whileEditing
        field.accessibilityLabel = title
        field.placeholder = "0"
        field.setContentCompressionResistancePriority(.defaultLow, for: .horizontal)
        field.delegate = context.coordinator
        field.addTarget(context.coordinator, action: #selector(Coordinator.changed(_:)), for: .editingChanged)
        let bar = UIToolbar()
        bar.items = [
            UIBarButtonItem(title: "+/−", style: .plain, target: context.coordinator, action: #selector(Coordinator.toggleSign)),
            UIBarButtonItem(systemItem: .flexibleSpace),
            UIBarButtonItem(title: "Fertig", style: .done, target: context.coordinator, action: #selector(Coordinator.done))
        ]
        bar.items?.first?.accessibilityLabel = "Vorzeichen ändern"
        bar.items?.last?.accessibilityIdentifier = "piggy.number.done"
        bar.sizeToFit()
        field.inputAccessoryView = bar
        context.coordinator.field = field
        return field
    }
    func updateUIView(_ uiView: UITextField, context: Context) {
        context.coordinator.parent = self
        uiView.accessibilityLabel = title
        uiView.isEnabled = isEnabled
        if !uiView.isFirstResponder && !value.isNaN {
            uiView.text = AmountInput.editorText(value, minimumFractionDigits: minimumFractionDigits)
        }
    }
    final class Coordinator: NSObject, UITextFieldDelegate {
        var parent: NativeDecimalInput
        weak var field: UITextField?
        init(_ parent: NativeDecimalInput) { self.parent = parent }
        @objc func changed(_ sender: UITextField) { parent.value = AmountInput.parse(sender.text ?? "") ?? .nan }
        @objc func toggleSign() {
            guard let field else { return }
            let text = field.text ?? ""
            field.text = text.hasPrefix("-") ? String(text.dropFirst()) : "-" + text
            changed(field)
        }
        @objc func done() { field?.resignFirstResponder() }
        func textFieldDidEndEditing(_ textField: UITextField) {
            changed(textField)
            if !parent.value.isNaN { textField.text = AmountInput.editorText(parent.value, minimumFractionDigits: parent.minimumFractionDigits) }
        }
        func textFieldShouldReturn(_ textField: UITextField) -> Bool { textField.resignFirstResponder(); return true }
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
