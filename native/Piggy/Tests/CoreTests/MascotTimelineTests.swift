import XCTest
@testable import PiggyCore

final class MascotTimelineTests: XCTestCase {
    func testEncodedAPNGTimingAndLoopBoundary() throws {
        let clock = try XCTUnwrap(MascotTimeline(durations: [0.033, 0.033, 0.034]))
        XCTAssertEqual(clock.duration, 0.1, accuracy: 0.000001)
        XCTAssertEqual(clock.frameIndex(elapsed: 0.032), 0)
        XCTAssertEqual(clock.frameIndex(elapsed: 0.034), 1)
        XCTAssertEqual(clock.frameIndex(elapsed: 0.099), 2)
        XCTAssertEqual(clock.frameIndex(elapsed: 0.100), 0)
        XCTAssertEqual(clock.frameIndex(elapsed: 0.134), 1)
    }
    func testStalledUIResumesAtElapsedFrameInsteadOfSlowingDown() throws {
        let clock = try XCTUnwrap(MascotTimeline(durations: (0..<90).map { $0 % 3 == 2 ? 0.034 : 0.033 }))
        XCTAssertEqual(clock.frameIndex(elapsed: 1.201), 36)
        XCTAssertEqual(clock.frameIndex(elapsed: 30.201), 6)
        XCTAssertEqual(clock.frameIndex(elapsed: -.infinity), 0)
        XCTAssertEqual(clock.frameIndex(elapsed: .nan), 0)
        XCTAssertEqual(clock.frameIndex(elapsed: -1), 0)
    }
    func testRejectsInvalidTimelines() {
        let invalid: [[Double]] = [[], [0], [-0.1], [.nan], [.infinity], [.greatestFiniteMagnitude, .greatestFiniteMagnitude]]
        for values in invalid { XCTAssertNil(MascotTimeline(durations: values)) }
    }
}
