import XCTest
@testable import PiggyCore

final class NativeAuthPolicyTests: XCTestCase {
    func testGoogleUsesNativeClientCallbackAndPKCE() throws {
        let url = NativeAuthPolicy.googleURL(state: "test-state", nonceHash: "hash", challenge: "challenge")
        let params = try XCTUnwrap(URLComponents(url: url, resolvingAgainstBaseURL: false)?.queryItems)
        func value(_ name: String) -> String? { params.first { $0.name == name }?.value }
        XCTAssertEqual(url.host, "accounts.google.com")
        XCTAssertEqual(value("redirect_uri"), NativeAuthPolicy.googleRedirect)
        XCTAssertFalse(try XCTUnwrap(value("redirect_uri")).contains("supabase.co"))
        XCTAssertEqual(value("client_id"), NativeAuthPolicy.googleClientID)
        XCTAssertEqual(value("code_challenge_method"), "S256")
        XCTAssertEqual(value("code_challenge"), "challenge")
        XCTAssertEqual(value("nonce"), "hash")
        XCTAssertFalse(params.contains { $0.name == "client_secret" })
    }
    func testCallbackRejectsWrongStateHostPathAndDuplicates() throws {
        let valid = NativeAuthPolicy.googleRedirect + "?state=s&code=4%2Fabc%2Bdef"
        XCTAssertEqual(try NativeAuthPolicy.googleCode(from: URL(string: valid)!, expectedState: "s"), "4/abc+def")
        for raw in [
            NativeAuthPolicy.googleRedirect + "?state=wrong&code=c",
            NativeAuthPolicy.googleRedirect + "?state=s&state=s&code=c",
            NativeAuthPolicy.googleRedirect + "?state=s&code=c&code=d",
            "evil:/oauthredirect?state=s&code=c",
            NativeAuthPolicy.googleScheme + "://evil/oauthredirect?state=s&code=c",
            NativeAuthPolicy.googleScheme + ":/wrong?state=s&code=c"
        ] { XCTAssertThrowsError(try NativeAuthPolicy.googleCode(from: URL(string: raw)!, expectedState: "s")) }
    }
    func testGoogleDenialIsCancellation() {
        let url = URL(string: NativeAuthPolicy.googleRedirect + "?state=s&error=access_denied")!
        XCTAssertThrowsError(try NativeAuthPolicy.googleCode(from: url, expectedState: "s")) { XCTAssertTrue($0 is CancellationError) }
    }
    func testEmailPreservesAddressAndPasswordFormEncoding() throws {
        XCTAssertEqual(try NativeAuthPolicy.email("  test+piggy@outlook.de \n"), "test+piggy@outlook.de")
        for email in ["", "test", "a@@outlook.de", "a b@outlook.de", "a@.outlook.de"] { XCTAssertThrowsError(try NativeAuthPolicy.email(email)) }
        XCTAssertEqual(String(data: NativeAuthPolicy.form(["code": "a+b/c=&"]), encoding: .utf8), "code=a%2Bb%2Fc%3D%26")
    }
    func testRecoveryAcceptsOnlyOwnExactCallback() {
        XCTAssertTrue(NativeAuthPolicy.isRecoveryCallback(URL(string: "piggy://auth/reset-password?code=abc")!))
        for value in ["https://auth/reset-password?code=abc", "piggy://other/reset-password", "piggy://auth/callback", "piggy://user@auth/reset-password"] {
            XCTAssertFalse(NativeAuthPolicy.isRecoveryCallback(URL(string: value)!))
        }
    }
}
