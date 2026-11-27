import Foundation

struct SharedImport: Codable, Identifiable {
    enum Kind: String, Codable { case pdf, image, web }
    let id: UUID
    let owner: UUID?
    let createdAt: Date
    let kind: Kind
    let name: String
}
/// Used by both the app and its embedded extension. No network, auth token or receipt save here.
enum ShareInbox {
    static let group = "group.com.heartbeat.piggy"
    static let maxBytes = 20 * 1024 * 1024
    static let maxItems = 30
    static func root() throws -> URL {
        #if os(iOS)
        guard let url = FileManager.default.containerURL(forSecurityApplicationGroupIdentifier: group) else {
            throw CocoaError(.fileNoSuchFile, userInfo: [NSLocalizedDescriptionKey: "Der gemeinsame Piggy-Eingang ist nicht freigeschaltet. App-Group-Konfiguration prüfen."])
        }
        return url.appendingPathComponent("Inbox", isDirectory: true)
        #else
        throw CocoaError(.featureUnsupported)
        #endif
    }
    static func setActiveOwner(_ uid: UUID?) {
        #if os(iOS)
        let defaults = UserDefaults(suiteName: group)
        if let uid { defaults?.set(uid.uuidString, forKey: "activeOwner") }
        else { defaults?.removeObject(forKey: "activeOwner") }
        #endif
    }
    static func activeOwner() -> UUID? {
        #if os(iOS)
        return UserDefaults(suiteName: group)?.string(forKey: "activeOwner").flatMap(UUID.init(uuidString:))
        #else
        return nil
        #endif
    }
    static func enqueue(_ bytes: Data, kind: SharedImport.Kind, name: String, owner: UUID?, at root: URL) throws -> SharedImport {
        guard !bytes.isEmpty, bytes.count <= maxBytes else { throw CocoaError(.fileReadTooLarge) }
        if kind == .pdf && !bytes.starts(with: Data("%PDF-".utf8)) { throw CocoaError(.fileReadCorruptFile) }
        if kind == .web {
            guard bytes.count <= 4096, let raw = String(data: bytes, encoding: .utf8), MerchantBrand.catalogURL(raw) != nil else { throw CocoaError(.fileReadCorruptFile) }
        }
        try FileManager.default.createDirectory(at: root, withIntermediateDirectories: true)
        guard try FileManager.default.contentsOfDirectory(at: root, includingPropertiesForKeys: nil).filter({ !$0.lastPathComponent.hasPrefix(".") }).count < maxItems else { throw CocoaError(.fileWriteOutOfSpace) }
        let record = SharedImport(id: UUID(), owner: owner, createdAt: Date(), kind: kind, name: String(name.prefix(160)))
        let staging = root.appendingPathComponent("." + record.id.uuidString, isDirectory: true)
        try FileManager.default.createDirectory(at: staging, withIntermediateDirectories: false)
        do {
            try bytes.write(to: staging.appendingPathComponent("payload"), options: .atomic)
            try JSONEncoder().encode(record).write(to: staging.appendingPathComponent("record.json"), options: .atomic)
            #if os(iOS)
            for file in [staging, staging.appendingPathComponent("payload"), staging.appendingPathComponent("record.json")] {
                try FileManager.default.setAttributes([.protectionKey: FileProtectionType.completeUntilFirstUserAuthentication], ofItemAtPath: file.path)
            }
            #endif
            var values = URLResourceValues(); values.isExcludedFromBackup = true
            var folder = staging; try folder.setResourceValues(values)
            try FileManager.default.moveItem(at: staging, to: root.appendingPathComponent(record.id.uuidString))
        } catch { try? FileManager.default.removeItem(at: staging); throw error }
        return record
    }
    static func list(owner: UUID, at root: URL) throws -> [SharedImport] {
        guard FileManager.default.fileExists(atPath: root.path) else { return [] }
        return try FileManager.default.contentsOfDirectory(at: root, includingPropertiesForKeys: nil).compactMap { url -> SharedImport? in
            guard UUID(uuidString: url.lastPathComponent) != nil else { return nil }
            let record = try JSONDecoder().decode(SharedImport.self, from: Data(contentsOf: url.appendingPathComponent("record.json")))
            guard record.id.uuidString == url.lastPathComponent else { throw CocoaError(.fileReadCorruptFile) }
            return record.owner == nil || record.owner == owner ? record : nil
        }.sorted { $0.createdAt > $1.createdAt }
    }
    static func data(_ record: SharedImport, owner: UUID, at root: URL) throws -> Data {
        let persisted = try JSONDecoder().decode(SharedImport.self, from: Data(contentsOf: root.appendingPathComponent(record.id.uuidString).appendingPathComponent("record.json")))
        guard persisted.id == record.id, persisted.owner == nil || persisted.owner == owner else { throw CocoaError(.fileReadNoPermission) }
        let url = root.appendingPathComponent(record.id.uuidString).appendingPathComponent("payload")
        let attrs = try FileManager.default.attributesOfItem(atPath: url.path)
        guard let size = attrs[.size] as? NSNumber, size.intValue <= maxBytes else { throw CocoaError(.fileReadTooLarge) }
        return try Data(contentsOf: url)
    }
    static func remove(_ record: SharedImport, owner: UUID, at root: URL) throws {
        let persisted = try JSONDecoder().decode(SharedImport.self, from: Data(contentsOf: root.appendingPathComponent(record.id.uuidString).appendingPathComponent("record.json")))
        guard persisted.id == record.id, persisted.owner == nil || persisted.owner == owner else { throw CocoaError(.fileWriteNoPermission) }
        try FileManager.default.removeItem(at: root.appendingPathComponent(record.id.uuidString))
    }
    static func purge(owner: UUID) throws {
        let base = try root()
        for record in try list(owner: owner, at: base) where record.owner == owner { try remove(record, owner: owner, at: base) }
    }
}
