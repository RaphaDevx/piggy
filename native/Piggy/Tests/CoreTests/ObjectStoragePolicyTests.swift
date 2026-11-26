import XCTest
@testable import PiggyCore

final class ObjectStoragePolicyTests: XCTestCase {
    func testLegacyReceiptURLsAreReSignedWithoutTrustingForeignHosts() throws {
        let user = UUID()
        let path = "\(user.uuidString.lowercased())/old.jpg"
        let base = "https://piggy.supabase.co"
        XCTAssertEqual(try ObjectStoragePolicy.receiptPath(base + "/storage/v1/object/public/receipt-images/" + path + "?token=old", supabaseURL: base, userID: user), path)
        XCTAssertEqual(try ObjectStoragePolicy.receiptPath(path, supabaseURL: base, userID: user), path)
        XCTAssertThrowsError(try ObjectStoragePolicy.receiptPath("https://evil.example/storage/v1/object/public/receipt-images/" + path, supabaseURL: base, userID: user))
        XCTAssertThrowsError(try ObjectStoragePolicy.receiptPath(base + "/storage/v1/object/public/receipt-images/" + UUID().uuidString.lowercased() + "/foreign.jpg", supabaseURL: base, userID: user))
        XCTAssertThrowsError(try ObjectStoragePolicy.receiptPath("file:///private/secret.jpg", supabaseURL: base, userID: user))
    }
    func testOwnPathAcceptedAndForeignAndTraversalRejected() throws {
        let user = UUID(); let path = "\(user.uuidString.lowercased())/beleg.jpg"
        XCTAssertNoThrow(try ObjectStoragePolicy.validate(bucket: "receipt-images", path: path, userID: user))
        XCTAssertThrowsError(try ObjectStoragePolicy.validate(bucket: "receipt-images", path: "\(UUID().uuidString.lowercased())/beleg.jpg", userID: user))
        for suffix in ["../other.jpg", "./other.jpg", "/other.jpg", "bad\\file.jpg", "bad\nfile.jpg"] {
            XCTAssertThrowsError(try ObjectStoragePolicy.validate(bucket: "receipt-images", path: "\(user.uuidString.lowercased())/\(suffix)", userID: user))
        }
        XCTAssertThrowsError(try ObjectStoragePolicy.validate(bucket: "arbitrary", path: path, userID: user))
    }
    func testSignedURLDoesNotPermitHttpOrOtherHostsOrCredentialInjection() throws {
        XCTAssertNoThrow(try ObjectStoragePolicy.signedURL("https://account.eu.r2.cloudflarestorage.com/bucket/file?X-Amz-Signature=test"))
        for value in ["http://account.eu.r2.cloudflarestorage.com/file", "https://evil.example/file", "https://account.eu.r2.cloudflarestorage.com.evil.example/file", "https://user:password@account.eu.r2.cloudflarestorage.com/file"] {
            XCTAssertThrowsError(try ObjectStoragePolicy.signedURL(value))
        }
    }
    func testOnlyExplicitLegacyModePermitsReadFallback() {
        XCTAssertTrue(ObjectStorageBackend(configuration: "r2-with-fallback").usesR2)
        XCTAssertTrue(ObjectStorageBackend(configuration: "r2-with-fallback").allowsLegacyRead)
        XCTAssertFalse(ObjectStorageBackend(configuration: "r2").allowsLegacyRead)
        XCTAssertEqual(ObjectStorageBackend(configuration: "invalid"), .supabase)
    }
}
