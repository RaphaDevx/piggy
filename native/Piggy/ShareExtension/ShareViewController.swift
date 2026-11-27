import UIKit
import UniformTypeIdentifiers

/// Deliberately no auth SDK, network upload or attempt to launch the containing app.
final class ShareViewController: UIViewController {
    private let message = UILabel()
    private let save = UIButton(type: .system)
    private var saving = false
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        let title = UILabel(); title.text = "Mit Piggy teilen"; title.font = .preferredFont(forTextStyle: .title2); title.adjustsFontForContentSizeCategory = true
        message.numberOfLines = 0; message.font = .preferredFont(forTextStyle: .body); message.adjustsFontForContentSizeCategory = true
        message.text = "PDFs und Fotos kommen zuerst in deinen lokalen Piggy-Eingang. Öffne danach Piggy, um sie als Beleg oder Coupon zu prüfen. Ein Weblink wird nur als Link vorgemerkt."
        save.setTitle("Im Piggy-Eingang ablegen", for: .normal); save.addTarget(self, action: #selector(saveItems), for: .touchUpInside)
        let cancel = UIButton(type: .system); cancel.setTitle("Schliessen", for: .normal); cancel.addTarget(self, action: #selector(close), for: .touchUpInside)
        let stack = UIStackView(arrangedSubviews: [title, message, save, cancel]); stack.axis = .vertical; stack.spacing = 20; stack.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(stack)
        NSLayoutConstraint.activate([stack.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 24), stack.trailingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.trailingAnchor, constant: -24), stack.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 24)])
        preferredContentSize = CGSize(width: 380, height: 340)
    }
    @objc private func close() { guard !saving else { return }; extensionContext?.completeRequest(returningItems: nil) }
    @objc private func saveItems() {
        guard !saving else { return }; saving = true; save.isEnabled = false
        Task { @MainActor in
            var saved = 0
            do {
                let providers = (extensionContext?.inputItems as? [NSExtensionItem] ?? []).flatMap { $0.attachments ?? [] }
                guard !providers.isEmpty, providers.count <= 10 else { throw CocoaError(.fileReadTooLarge) }
                let root = try ShareInbox.root(), owner = ShareInbox.activeOwner()
                for provider in providers {
                    if provider.hasItemConformingToTypeIdentifier(UTType.pdf.identifier) {
                        let data = try await file(provider, type: UTType.pdf.identifier)
                        _ = try ShareInbox.enqueue(data, kind: .pdf, name: provider.suggestedName ?? "Quittung.pdf", owner: owner, at: root)
                    } else if provider.hasItemConformingToTypeIdentifier(UTType.image.identifier) {
                        let data = try await file(provider, type: UTType.image.identifier)
                        guard UIImage(data: data) != nil else { throw CocoaError(.fileReadCorruptFile) }
                        _ = try ShareInbox.enqueue(data, kind: .image, name: provider.suggestedName ?? "Coupon oder Beleg", owner: owner, at: root)
                    } else if provider.hasItemConformingToTypeIdentifier(UTType.url.identifier) {
                        let url: URL = try await withCheckedThrowingContinuation { continuation in
                            provider.loadItem(forTypeIdentifier: UTType.url.identifier) { value, error in
                                if let error { continuation.resume(throwing: error) }
                                else if let url = value as? URL { continuation.resume(returning: url) }
                                else { continuation.resume(throwing: CocoaError(.fileReadCorruptFile)) }
                            }
                        }
                        _ = try ShareInbox.enqueue(Data(url.absoluteString.utf8), kind: .web, name: url.host ?? "Weblink", owner: owner, at: root)
                    } else { throw CocoaError(.fileReadUnknown) }
                    saved += 1
                }
                message.text = "\(saved) Datei(en) im Eingang. Öffne jetzt Piggy → Belege → Eingang. Es wurde noch kein Beleg und kein Coupon gespeichert."
                save.isHidden = true
            } catch {
                message.text = "\(saved) Datei(en) übernommen. Der Rest konnte nicht abgelegt werden (höchstens 10 Dateien, je 20 MB; PDF, Bild oder HTTPS-Link). Öffne Piggy und prüfe den Eingang. \(error.localizedDescription)"
            }
            saving = false
            // No retry button after partial success: it would enqueue already copied attachments twice.
            save.isHidden = true
        }
    }
    private func file(_ provider: NSItemProvider, type: String) async throws -> Data {
        try await withCheckedThrowingContinuation { continuation in
            provider.loadFileRepresentation(forTypeIdentifier: type) { url, error in
                do {
                    if let error { throw error }
                    guard let url else { throw CocoaError(.fileNoSuchFile) }
                    let access = url.startAccessingSecurityScopedResource(); defer { if access { url.stopAccessingSecurityScopedResource() } }
                    let attrs = try FileManager.default.attributesOfItem(atPath: url.path)
                    guard let size = attrs[.size] as? NSNumber, size.intValue <= ShareInbox.maxBytes else { throw CocoaError(.fileReadTooLarge) }
                    continuation.resume(returning: try Data(contentsOf: url))
                } catch { continuation.resume(throwing: error) }
            }
        }
    }
}
