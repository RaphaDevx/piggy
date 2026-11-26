import Foundation

/// Uses APNG's encoded 33/33/34 ms durations without Timer drift after UI stalls.
struct MascotTimeline {
    let duration: TimeInterval
    private let ends: [TimeInterval]

    init?(durations: [TimeInterval]) {
        guard !durations.isEmpty, durations.allSatisfy({ $0.isFinite && $0 > 0 }) else { return nil }
        var sum: TimeInterval = 0
        ends = durations.map { sum += $0; return sum }
        guard sum.isFinite else { return nil }
        duration = sum
    }

    func frameIndex(elapsed: TimeInterval) -> Int {
        guard elapsed.isFinite, elapsed > 0 else { return 0 }
        let time = elapsed.truncatingRemainder(dividingBy: duration)
        return ends.firstIndex(where: { time < $0 }) ?? 0
    }
}
