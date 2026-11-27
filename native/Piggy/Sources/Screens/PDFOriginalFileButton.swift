import SwiftUI

struct PDFOriginalFileButton: View {
    @EnvironmentObject private var store: PiggyStore
    let path: String
    @State private var data: Data?
    @State private var showing = false
    @State private var busy = false
    @State private var error: String?
    var body: some View {
        VStack(alignment: .leading) {
            Button("Original-PDF öffnen") {
                busy = true; error = nil
                Task {
                    do {
                        let uid = try store.requireUser()
                        let url = try await store.signedImageURL(path: path)
                        let (bytes, response) = try await URLSession.shared.data(from: url)
                        guard store.userID == uid else { throw PiggyFailure.signedOut }
                        guard (response as? HTTPURLResponse)?.statusCode == 200, bytes.count <= ShareInbox.maxBytes,
                              bytes.starts(with: Data("%PDF-".utf8)) else { throw PiggyFailure.service("Das Original-PDF konnte nicht geladen werden.") }
                        data = bytes; showing = true
                    } catch { self.error = error.localizedDescription }
                    busy = false
                }
            }.disabled(busy)
            if busy { ProgressView() }
            if let error { ScreenError(message: error) }
        }.sheet(isPresented: $showing) { if let data { NavigationStack { PDFOriginalView(data: data).navigationTitle("Original-PDF").toolbar { ToolbarItem(placement: .cancellationAction) { Button("Schliessen") { showing = false } } } } } }
    }
}
