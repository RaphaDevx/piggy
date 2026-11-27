import SwiftUI
import PhotosUI

struct MerchantLogo: View {
    @EnvironmentObject private var library: PersonalLibrary
    @EnvironmentObject private var store: PiggyStore
    let name: String
    var body: some View {
        Group {
            if let bytes = library.brand(for: name, owner: store.userID)?.logo, let image = UIImage(data: bytes) {
                Image(uiImage: image).resizable().scaledToFit()
            } else { Image(systemName: "storefront").font(.title3).foregroundStyle(.secondary) }
        }.frame(width: 32, height: 32).padding(5)
            .background(Color(uiColor: .tertiarySystemGroupedBackground), in: RoundedRectangle(cornerRadius: 10))
            .accessibilityHidden(true)
    }
}
struct MerchantBrandEditor: View {
    @EnvironmentObject private var store: PiggyStore
    @EnvironmentObject private var library: PersonalLibrary
    @Environment(\.dismiss) private var dismiss
    let name: String
    @State private var info = ""
    @State private var catalog = ""
    @State private var logo: Data?
    @State private var photo: PhotosPickerItem?
    @State private var error: String?
    var body: some View {
        Form {
            Section(name) {
                if let logo, let image = UIImage(data: logo) { Image(uiImage: image).resizable().scaledToFit().frame(height: 80) }
                PhotosPicker("Kleines Logo auswählen", selection: $photo, matching: .images)
                if logo != nil { Button("Logo entfernen", role: .destructive) { logo = nil } }
                TextField("Kurze Anbieterinfo", text: $info, axis: .vertical)
            }
            Section("Optionaler Produktkatalog") {
                TextField("https://…", text: $catalog).keyboardType(.URL).textInputAutocapitalization(.never).autocorrectionDisabled()
                Text("Der Katalog öffnet sich erst, wenn du ihn antippst. Es werden keine Produkte oder Werbeinhalte automatisch geladen.").font(.footnote)
            }
            Section {
                Text("Diese Zuordnung gilt auf diesem Gerät für deine Belege mit demselben normalisierten Geschäftsnamen. Sie ist persönlich hinterlegt und kein verifiziertes Anbieterprofil.").font(.footnote).foregroundStyle(.secondary)
                Button("Speichern") {
                    do {
                        let uid = try store.requireUser()
                        guard catalog.isEmpty || MerchantBrand.catalogURL(catalog) != nil else { throw PiggyFailure.validation("Bitte eine vollständige HTTPS-Adresse ohne Zugangsdaten eingeben.") }
                        try library.saveBrand(MerchantBrand(id: ReceiptRules.merchantKey(name), name: name, information: info, logo: logo, catalog: catalog), owner: uid)
                        dismiss()
                    } catch { self.error = error.localizedDescription }
                }
            }
            if let error { ScreenError(message: error) }
        }.navigationTitle("Anbieter & Logo")
            .task { if let brand = library.brand(for: name, owner: store.userID) { info = brand.information; logo = brand.logo; catalog = brand.catalog } }
            .onChange(of: photo) { _, value in Task {
                do { if let data = try await value?.loadTransferable(type: Data.self), let image = UIImage(data: data) { logo = PersonalLibrary.logo(image) } }
                catch { self.error = error.localizedDescription }
            } }
    }
}
