import SwiftUI

struct ReceiptArticleLine: View {
    let item: ReceiptItem
    let currency: String
    var body: some View {
        HStack(spacing: 10) {
            Text(item.name).lineLimit(1).truncationMode(.tail).frame(maxWidth: .infinity, alignment: .leading)
            Text(ReceiptItemPresentation.quantity(item.quantity, unit: item.unit)).font(.subheadline).monospacedDigit()
                .lineLimit(1).fixedSize(horizontal: true, vertical: false).foregroundStyle(.secondary)
            Text(money(item.total_price, currency)).monospacedDigit().lineLimit(1)
                .fixedSize(horizontal: true, vertical: false).layoutPriority(1)
        }
        .frame(minHeight: 44)
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(item.name)
        .accessibilityValue("\(ReceiptItemPresentation.quantity(item.quantity, unit: item.unit)), \(money(item.total_price, currency))")
    }
}

struct ReceiptArticleEditor: View {
    @Binding var name: String
    @Binding var quantity: Decimal
    @Binding var total: Decimal
    @Binding var unit: String
    @Binding var unitPrice: Decimal
    @Binding var category: String
    @State private var details = false
    var body: some View {
        HStack(spacing: 8) {
            TextField("Artikel", text: $name).lineLimit(1).accessibilityLabel("Artikelname")
                .frame(maxWidth: .infinity, alignment: .leading)
            HStack(spacing: 2) {
                DecimalField(title: "Menge für \(name)", value: $quantity, compact: true).frame(minWidth: 32)
                if !unit.isEmpty { Text(unit).font(.caption).lineLimit(1).truncationMode(.tail).frame(maxWidth: 28) }
            }.frame(width: 72)
            DecimalField(title: "Betrag für \(name)", value: $total, compact: true, minimumFractionDigits: 2).frame(width: 76)
            Button { details = true } label: { Image(systemName: "ellipsis.circle").frame(width: 28, height: 44) }
                .buttonStyle(.borderless).accessibilityLabel("Details für \(name)")
        }
        .sheet(isPresented: $details) {
            NavigationStack {
                Form {
                    Section("Artikel") {
                        TextField("Vollständiger Artikelname", text: $name)
                        DecimalField(title: "Menge", value: $quantity)
                        TextField("Einheit, z. B. Stk, kg oder L", text: $unit)
                        DecimalField(title: "Einzelpreis", value: $unitPrice, minimumFractionDigits: 2)
                        DecimalField(title: "Artikelbetrag", value: $total, minimumFractionDigits: 2)
                        CategoryPicker(category: $category)
                    }
                }
                .navigationTitle("Artikeldetails")
                .toolbar { ToolbarItem(placement: .confirmationAction) { Button("Fertig") { details = false } } }
            }
        }
    }
}

struct ReceiptArticleHeader: View {
    var title = "Artikel"
    var body: some View {
        HStack(spacing: 8) {
            Text(title).frame(maxWidth: .infinity, alignment: .leading)
            Text("Menge").frame(width: 72, alignment: .trailing)
            Text("Betrag").frame(width: 76, alignment: .trailing)
            Color.clear.frame(width: 28, height: 1).accessibilityHidden(true)
        }
    }
}
