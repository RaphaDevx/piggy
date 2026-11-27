import Foundation
import SwiftUI
import UIKit
import PDFKit
import UniformTypeIdentifiers

@MainActor
enum SharedImportService {
    static func enqueue(file url: URL, owner: UUID?) throws {
        let access = url.startAccessingSecurityScopedResource(); defer { if access { url.stopAccessingSecurityScopedResource() } }
        let values = try url.resourceValues(forKeys: [.fileSizeKey, .contentTypeKey, .isRegularFileKey])
        guard values.isRegularFile == true, let size = values.fileSize, size <= ShareInbox.maxBytes else { throw PiggyFailure.validation("Bitte eine Datei mit höchstens 20 MB wählen.") }
        let bytes = try Data(contentsOf: url)
        let kind: SharedImport.Kind
        if bytes.starts(with: Data("%PDF-".utf8)) { kind = .pdf }
        else if values.contentType?.conforms(to: .image) == true, UIImage(data: bytes) != nil { kind = .image }
        else { throw PiggyFailure.validation("Piggy nimmt hier PDFs und Bilder entgegen.") }
        _ = try ShareInbox.enqueue(bytes, kind: kind, name: url.lastPathComponent, owner: owner, at: ShareInbox.root())
    }
    static func document(_ data: Data) throws -> PDFDocument {
        guard let document = PDFDocument(data: data), !document.isLocked, document.pageCount > 0, document.pageCount <= 12 else { throw PiggyFailure.validation("Bitte ein unverschlüsseltes PDF mit 1 bis 12 Seiten verwenden. Grössere Dateien zuerst aufteilen.") }
        return document
    }
    static func image(_ page: PDFPage) -> UIImage {
        page.thumbnail(of: CGSize(width: 1600, height: 2200), for: .mediaBox)
    }
    static func draft(data: Data, kind: SharedImport.Kind, store: PiggyStore) async throws -> (ReceiptDraft, UIImage, Data?) {
        let uid = try store.requireUser()
        if kind == .image {
            guard let image = UIImage(data: data) else { throw PiggyFailure.validation("Das Bild konnte nicht gelesen werden.") }
            let draft = try await store.processImage(image: image, allowAI: store.aiConsent)
            guard store.userID == uid else { throw PiggyFailure.signedOut }
            return (draft, image, nil)
        }
        guard kind == .pdf else { throw PiggyFailure.validation("Bitte die PDF-Datei statt eines Webseitenlinks teilen.") }
        let document = try document(data)
        guard let first = document.page(at: 0) else { throw PiggyFailure.validation("Das PDF besitzt keine lesbare Seite.") }
        var parts: [String] = []
        for index in 0..<document.pageCount {
            guard let page = document.page(at: index) else { continue }
            let text = page.string?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
            if !text.isEmpty { parts.append(text) }
            else { parts.append(try await store.processImage(image: image(page), allowAI: false).raw_ocr_text) }
        }
        let draft = try await store.processText(parts.joined(separator: "\n\n"), allowAI: store.aiConsent)
        guard store.userID == uid else { throw PiggyFailure.signedOut }
        return (draft, image(first), data)
    }
}
struct PDFOriginalView: UIViewRepresentable {
    let data: Data
    func makeUIView(context: Context) -> PDFView { let view = PDFView(); view.autoScales = true; view.displayMode = .singlePageContinuous; view.document = PDFDocument(data: data); return view }
    func updateUIView(_ uiView: PDFView, context: Context) {}
}
