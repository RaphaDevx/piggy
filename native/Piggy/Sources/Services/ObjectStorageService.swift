import Foundation
import Supabase

extension PiggyStore {
    private var storageBackend: ObjectStorageBackend {
        ObjectStorageBackend(configuration: LaunchConfiguration.value("STORAGE_BACKEND"))
    }
    private func storageURL(op: String, bucket: String, path: String, verify: Bool = false) async throws -> URL? {
        let uid = try requireUser()
        try ObjectStoragePolicy.validate(bucket: bucket, path: path, userID: uid)
        struct Payload: Encodable { let op: String; let bucket: String; let paths: [String]; let verify: Bool }
        struct Reply: Decodable {
            struct Result: Decodable { let path: String; let url: String?; let found: Bool?; let error: String? }
            let results: [Result]; let expiresIn: Int
        }
        let reply: Reply = try await requireClient().functions.invoke("storage-url", options: .init(body: Payload(op: op, bucket: bucket, paths: [path], verify: verify)))
        guard userID == uid else { throw PiggyFailure.signedOut }
        guard reply.expiresIn > 0, reply.expiresIn <= 600, reply.results.count == 1,
              let result = reply.results.first, result.path == path else {
            throw PiggyFailure.service("Die Speicheranfrage konnte nicht bestätigt werden.")
        }
        if result.found == false && op == "get" { return nil }
        guard result.error == nil, let raw = result.url else { throw PiggyFailure.service("Der Dateispeicher ist gerade nicht erreichbar.") }
        return try ObjectStoragePolicy.signedURL(raw)
    }
    func uploadObject(bucket: String, path: String, data: Data, contentType: String) async throws {
        let uid = try requireUser()
        try ObjectStoragePolicy.validate(bucket: bucket, path: path, userID: uid)
        if !storageBackend.usesR2 {
            try await requireClient().storage.from(bucket).upload(path, data: data, options: FileOptions(contentType: contentType))
            return
        }
        guard let url = try await storageURL(op: "put", bucket: bucket, path: path) else { throw PiggyFailure.service("Keine Upload-Adresse erhalten.") }
        var request = URLRequest(url: url); request.httpMethod = "PUT"
        request.setValue(contentType, forHTTPHeaderField: "Content-Type")
        // No Supabase token or other authorization header is sent to R2.
        do {
            let (_, response) = try await URLSession.shared.upload(for: request, from: data)
            guard let http = response as? HTTPURLResponse, (200...299).contains(http.statusCode) else { throw PiggyFailure.service("Die Datei konnte nicht gespeichert werden.") }
        } catch { throw PiggyFailure.service("Die Datei konnte nicht auf R2 gespeichert werden. Bitte versuche es erneut.") }
        guard userID == uid else { throw PiggyFailure.signedOut }
    }
    func signedObjectURL(bucket: String, path: String) async throws -> URL {
        let uid = try requireUser()
        try ObjectStoragePolicy.validate(bucket: bucket, path: path, userID: uid)
        if storageBackend.usesR2 {
            do {
                if let url = try await storageURL(op: "get", bucket: bucket, path: path, verify: storageBackend.allowsLegacyRead) { return url }
            } catch {
                guard userID == uid else { throw PiggyFailure.signedOut }
                if !storageBackend.allowsLegacyRead { throw PiggyFailure.service("Das Belegbild konnte nicht geladen werden.") }
            }
            if !storageBackend.allowsLegacyRead { throw PiggyFailure.service("Die Datei wurde nicht gefunden.") }
        }
        guard userID == uid else { throw PiggyFailure.signedOut }
        return try await requireClient().storage.from(bucket).createSignedURL(path: path, expiresIn: 600)
    }
    func removeObject(bucket: String, path: String) async throws {
        try ObjectStoragePolicy.validate(bucket: bucket, path: path, userID: try requireUser())
        if storageBackend.usesR2 {
            guard let url = try await storageURL(op: "delete", bucket: bucket, path: path) else { throw PiggyFailure.service("Keine Speicheradresse erhalten.") }
            var request = URLRequest(url: url); request.httpMethod = "DELETE"
            do {
                let (_, response) = try await URLSession.shared.data(for: request)
                guard let http = response as? HTTPURLResponse,
                      (200...299).contains(http.statusCode) || http.statusCode == 404 else { throw PiggyFailure.service("Datei konnte nicht entfernt werden.") }
            } catch { throw PiggyFailure.service("Datei konnte nicht entfernt werden.") }
        }
        if storageBackend != .r2 {
            try await requireClient().storage.from(bucket).remove(paths: [path])
        }
    }
}
