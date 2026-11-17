// swift-tools-version: 6.1
import PackageDescription
let package = Package(
    name: "PiggyCore",
    platforms: [.iOS(.v17), .macOS(.v14)],
    products: [.library(name: "PiggyCore", targets: ["PiggyCore"])],
    targets: [
        .target(name: "PiggyCore", path: "Sources/Core"),
        .testTarget(name: "PiggyCoreTests", dependencies: ["PiggyCore"], path: "Tests/CoreTests")
    ],
    swiftLanguageModes: [.v5]
)
