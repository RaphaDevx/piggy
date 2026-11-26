import SwiftUI
import UIKit
import ImageIO

struct MascotView: View {
    var pose: String = "idle-happy"
    var size: CGFloat = 200
    @Environment(\.accessibilityReduceMotion) private var reducedMotion
    @Environment(\.scenePhase) private var scenePhase
    @Environment(\.displayScale) private var displayScale
    @State private var visible = false

    var body: some View {
        MascotAnimation(
            clip: MascotAsset.clip(for: pose),
            pixelSize: min(864, Int(ceil(size * MascotAsset.canvasScale * displayScale))),
            active: visible && scenePhase == .active,
            animated: !reducedMotion
        )
        // Transparent bleed extends beyond the existing layout without clipping
        // the shadow or changing the apparent size of Piggy's body.
        .frame(width: size * MascotAsset.canvasScale, height: size * MascotAsset.canvasScale)
        .frame(width: size, height: size)
        .allowsHitTesting(false)
        .accessibilityHidden(true)
        .onAppear { visible = true }
        .onDisappear { visible = false }
    }
}

private enum MascotAsset {
    static let canvasScale: CGFloat = 1.18
    static let directory = "Brand/3d/app-motion-v3"
    struct Manifest: Decodable { let clips: [Clip] }
    struct Clip: Decodable {
        let id: String
        let frameCount: Int
        let frameDurationsMs: [Double]
    }
    static let clips: [Clip] = {
        guard let url = Bundle.main.url(forResource: "manifest", withExtension: "json", subdirectory: directory),
              let data = try? Data(contentsOf: url),
              let manifest = try? JSONDecoder().decode(Manifest.self, from: data) else { return [] }
        return manifest.clips
    }()
    static func clip(for pose: String) -> String {
        pose.hasPrefix("cheer") ? "celebration" : pose.hasPrefix("catch") ? "scan" : "idle"
    }
}

/// All ImageIO access stays on one queue. At most six display-sized frames are
/// retained instead of decoding an entire Retina animation into animationImages.
private final class MascotFrameBuffer {
    private let queue = DispatchQueue(label: "piggy.mascot.frames", qos: .userInitiated)
    private let url: URL
    private let pixelSize: Int
    private let frameCount: Int
    private var frames: [Int: CGImage] = [:]
    private var cancelled = false
    static let capacity = 6

    init(url: URL, pixelSize: Int, frameCount: Int) {
        self.url = url
        self.pixelSize = max(1, pixelSize)
        self.frameCount = frameCount
    }

    func cancel() { queue.async { self.cancelled = true; self.frames.removeAll() } }
    func purge() { queue.async { self.frames.removeAll() } }

    func request(_ index: Int, animated: Bool, completion: @escaping (CGImage?) -> Void) {
        queue.async {
            guard !self.cancelled else { return }
            let window = Set((0..<(animated ? Self.capacity : 1)).map { (index + $0) % self.frameCount })
            self.frames = self.frames.filter { window.contains($0.key) }
            let image = self.decode(index)
            DispatchQueue.main.async { completion(image) }
            if animated {
                for offset in 1...3 { _ = self.decode((index + offset) % self.frameCount) }
            }
        }
    }

    private func decode(_ index: Int) -> CGImage? {
        if let image = frames[index] { return image }
        let path = url.appendingPathComponent(String(format: "%@_%04d.png", url.lastPathComponent, index))
        guard let source = CGImageSourceCreateWithURL(path as CFURL, [kCGImageSourceShouldCache: false] as CFDictionary),
              let image = CGImageSourceCreateThumbnailAtIndex(source, 0, [
                kCGImageSourceCreateThumbnailFromImageAlways: true,
                kCGImageSourceCreateThumbnailWithTransform: true,
                kCGImageSourceThumbnailMaxPixelSize: pixelSize,
                kCGImageSourceShouldCacheImmediately: true,
                kCGImageSourceShouldCache: false
              ] as CFDictionary) else { return nil }
        frames[index] = image
        return image
    }
}

private struct MascotAnimation: UIViewRepresentable {
    let clip: String
    let pixelSize: Int
    let active: Bool
    let animated: Bool
    func makeUIView(context: Context) -> MascotPlayerView { MascotPlayerView() }
    func updateUIView(_ view: MascotPlayerView, context: Context) {
        view.configure(clip: clip, pixelSize: pixelSize, active: active, animated: animated)
    }
    static func dismantleUIView(_ view: MascotPlayerView, coordinator: ()) { view.releaseFrames() }
}

private final class MascotDisplayLinkTarget: NSObject {
    weak var owner: MascotPlayerView?
    @objc func tick(_ link: CADisplayLink) { owner?.tick(link) }
}

private final class MascotPlayerView: UIView {
    private let imageView = UIImageView()
    private let target = MascotDisplayLinkTarget()
    private var displayLink: CADisplayLink?
    private var buffer: MascotFrameBuffer?
    private var timeline: MascotTimeline?
    private var configuration = ""
    private var generation = UUID()
    private var startedAt: CFTimeInterval = 0
    private var loading = false
    private var displayedFrame = -1
    private var wasVisible = true

    override init(frame: CGRect) {
        super.init(frame: frame)
        imageView.contentMode = .scaleAspectFit
        imageView.isAccessibilityElement = false
        imageView.backgroundColor = .clear
        backgroundColor = .clear
        isOpaque = false
        clipsToBounds = false
        addSubview(imageView)
        target.owner = self
    }
    required init?(coder: NSCoder) { fatalError("init(coder:) has not been implemented") }
    override func layoutSubviews() { super.layoutSubviews(); imageView.frame = bounds }

    func configure(clip: String, pixelSize: Int, active: Bool, animated: Bool) {
        guard active else { releaseFrames(); return }
        let key = "\(clip)-\(pixelSize)-\(animated)"
        guard configuration != key else { return }
        releaseFrames()
        configuration = key
        guard let asset = MascotAsset.clips.first(where: { $0.id == clip }),
              asset.frameCount == asset.frameDurationsMs.count,
              let clock = MascotTimeline(durations: asset.frameDurationsMs.map { $0 / 1000 }),
              let root = Bundle.main.url(forResource: "manifest", withExtension: "json", subdirectory: MascotAsset.directory) else {
            imageView.image = BrandResources.sprite(clip == "scan" ? "catch-surprised" : clip == "celebration" ? "cheer-happy" : "idle-happy")
            return
        }
        let url = root.deletingLastPathComponent().appendingPathComponent(clip, isDirectory: true)
        timeline = clock
        buffer = MascotFrameBuffer(url: url, pixelSize: pixelSize, frameCount: asset.frameCount)
        request(0, animated: animated)
        if animated {
            startedAt = CACurrentMediaTime()
            let link = CADisplayLink(target: target, selector: #selector(MascotDisplayLinkTarget.tick(_:)))
            link.preferredFrameRateRange = CAFrameRateRange(minimum: 30, maximum: 30, preferred: 30)
            link.add(to: .main, forMode: .common)
            displayLink = link
        }
    }

    func tick(_ link: CADisplayLink) {
        let visible = window.map { !isHidden && convert(bounds, to: $0).intersects($0.bounds) } ?? false
        if !visible {
            if wasVisible { buffer?.purge() }
            wasVisible = false
            return
        }
        wasVisible = true
        guard let timeline else { return }
        let index = timeline.frameIndex(elapsed: link.timestamp - startedAt)
        if index != displayedFrame && !loading { request(index, animated: true) }
    }

    private func request(_ index: Int, animated: Bool) {
        guard !loading else { return }
        loading = true
        let token = generation
        buffer?.request(index, animated: animated) { [weak self] image in
            guard let self, self.generation == token else { return }
            self.loading = false
            guard let image else {
                self.displayLink?.invalidate()
                self.displayLink = nil
                return
            }
            self.imageView.image = UIImage(cgImage: image)
            self.displayedFrame = index
        }
    }

    func releaseFrames() {
        displayLink?.invalidate()
        displayLink = nil
        buffer?.cancel()
        buffer = nil
        imageView.image = nil
        timeline = nil
        generation = UUID()
        configuration = ""
        displayedFrame = -1
        loading = false
        wasVisible = true
    }
}
