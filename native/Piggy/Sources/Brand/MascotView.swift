import SwiftUI
import UIKit
import ImageIO

struct MascotView: View {
    var pose: String = "idle-happy"
    var size: CGFloat = 200
    @Environment(\.accessibilityReduceMotion) private var reducedMotion
    @Environment(\.scenePhase) private var scenePhase
    @State private var frames: [UIImage] = []
    @State private var duration: Double = 3
    @State private var visible = false
    var body: some View {
        Group {
            if !reducedMotion && !frames.isEmpty {
                MascotAnimation(images: frames, duration: duration, playing: scenePhase == .active)
            } else if let image = BrandResources.sprite(pose) {
                Image(uiImage: image).resizable().scaledToFit()
            } else {
                Image(systemName: "sparkles").resizable().scaledToFit().foregroundStyle(Theme.pink).padding(40)
            }
        }
        .frame(width: size, height: size)
        .accessibilityHidden(true)
        .onAppear { visible = true }
        .onDisappear { visible = false; frames = [] }
        .task(id: "\(pose)-\(reducedMotion)-\(scenePhase)-\(visible)") {
            guard !reducedMotion, scenePhase == .active, visible else { frames = []; return }
            let clip = pose.hasPrefix("cheer") ? "celebration" : pose.hasPrefix("catch") ? "scan" : "idle"
            guard let url = Bundle.main.url(forResource: "\(clip)", withExtension: "png", subdirectory: "Brand/3d/app-motion-v2") else { return }
            let loaded = await Task.detached(priority: .utility) { MascotFrames.loadAPNG(url: url) }.value
            guard !Task.isCancelled, scenePhase == .active, visible else { return }
            frames = loaded.images
            duration = loaded.duration
        }
    }
}

private enum MascotFrames {
    static func loadAPNG(url: URL) -> (images: [UIImage], duration: Double) {
        guard let source = CGImageSourceCreateWithURL(url as CFURL, nil) else { return ([], 3) }
        let count = CGImageSourceGetCount(source)
        var frames: [UIImage] = []
        var duration = 0.0
        for index in 0..<count {
            guard let cg = CGImageSourceCreateImageAtIndex(source, index, [kCGImageSourceShouldCacheImmediately: true] as CFDictionary) else { continue }
            frames.append(UIImage(cgImage: cg))
            let props = CGImageSourceCopyPropertiesAtIndex(source, index, nil) as? [CFString: Any]
            let png = props?[kCGImagePropertyPNGDictionary] as? [CFString: Any]
            duration += png?[kCGImagePropertyAPNGUnclampedDelayTime] as? Double ?? 1.0 / 30
        }
        return (frames, max(duration, 0.1))
    }
}

private struct MascotAnimation: UIViewRepresentable {
    let images: [UIImage]
    let duration: Double
    let playing: Bool
    func makeUIView(context: Context) -> UIImageView {
        let view = UIImageView()
        view.contentMode = .scaleAspectFit
        view.isAccessibilityElement = false
        view.setContentHuggingPriority(.defaultLow, for: .horizontal)
        view.setContentHuggingPriority(.defaultLow, for: .vertical)
        return view
    }
    func updateUIView(_ view: UIImageView, context: Context) {
        if view.animationImages?.first !== images.first || view.animationDuration != duration {
            view.stopAnimating()
            view.animationImages = images
            view.animationDuration = duration
            view.image = images.first
        }
        if playing { if !view.isAnimating { view.startAnimating() } }
        else { view.stopAnimating() }
    }
    static func dismantleUIView(_ uiView: UIImageView, coordinator: ()) { uiView.stopAnimating(); uiView.animationImages = nil }
}
