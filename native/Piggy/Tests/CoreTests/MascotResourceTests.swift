#if canImport(ImageIO) && canImport(CoreGraphics)
import XCTest
import ImageIO
import CoreGraphics

final class MascotResourceTests: XCTestCase {
    private var root: URL {
        URL(fileURLWithPath: #filePath).deletingLastPathComponent().deletingLastPathComponent()
            .deletingLastPathComponent().appendingPathComponent("Resources/Brand/3d/app-motion-v3")
    }

    func testAppleDecoderSupportsRetinaFramesAndTransparentEdges() throws {
        var elapsed: [Double] = []
        for (clip, count) in [("idle", 90), ("scan", 72), ("celebration", 60)] {
            var pixels: [Data] = []
            for index in [0, count / 2, count - 1] {
                let path = root.appendingPathComponent(clip).appendingPathComponent(String(format: "%@_%04d.png", clip, index))
                let start = Date()
                let source = try XCTUnwrap(CGImageSourceCreateWithURL(path as CFURL, [kCGImageSourceShouldCache: false] as CFDictionary))
                let properties = try XCTUnwrap(CGImageSourceCopyPropertiesAtIndex(source, 0, nil) as? [CFString: Any])
                XCTAssertEqual(properties[kCGImagePropertyPixelWidth] as? Int, 864)
                XCTAssertEqual(properties[kCGImagePropertyPixelHeight] as? Int, 864)
                let image = try XCTUnwrap(CGImageSourceCreateThumbnailAtIndex(source, 0, [
                    kCGImageSourceCreateThumbnailFromImageAlways: true,
                    kCGImageSourceCreateThumbnailWithTransform: true,
                    kCGImageSourceThumbnailMaxPixelSize: 850,
                    kCGImageSourceShouldCacheImmediately: true,
                    kCGImageSourceShouldCache: false
                ] as CFDictionary))
                XCTAssertEqual(image.width, 850)
                XCTAssertEqual(image.height, 850)
                let row = image.width * 4
                var rgba = [UInt8](repeating: 0, count: row * image.height)
                try rgba.withUnsafeMutableBytes { buffer in
                    let context = try XCTUnwrap(CGContext(data: buffer.baseAddress, width: image.width, height: image.height,
                        bitsPerComponent: 8, bytesPerRow: row, space: CGColorSpaceCreateDeviceRGB(),
                        bitmapInfo: CGImageAlphaInfo.premultipliedLast.rawValue | CGBitmapInfo.byteOrder32Big.rawValue))
                    context.draw(image, in: CGRect(x: 0, y: 0, width: CGFloat(image.width), height: CGFloat(image.height)))
                }
                elapsed.append(Date().timeIntervalSince(start) * 1000)
                for x in 0..<image.width {
                    XCTAssertEqual(rgba[x * 4 + 3], 0)
                    XCTAssertEqual(rgba[(image.height - 1) * row + x * 4 + 3], 0)
                }
                for y in 0..<image.height {
                    XCTAssertEqual(rgba[y * row + 3], 0)
                    XCTAssertEqual(rgba[y * row + (image.width - 1) * 4 + 3], 0)
                }
                XCTAssertTrue(rgba.enumerated().contains { $0.offset % 4 == 3 && $0.element == 255 })
                pixels.append(Data(rgba))
            }
            XCTAssertNotEqual(pixels[0], pixels[1], "Different 3D poses must not decode to the same frame")
            XCTAssertNotEqual(pixels[0], pixels[2])
        }
        print("Piggy Retina PNG decode + pixel audit (Mac; not an iPhone FPS measurement): \(elapsed.map { String(format: "%.1f", $0) }.joined(separator: ", ")) ms")
    }
}
#endif
