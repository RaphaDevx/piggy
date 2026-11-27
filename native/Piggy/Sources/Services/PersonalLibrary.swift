import Foundation
import UIKit
import Combine

/// Account-scoped device storage. No merchant trust claim, automatic cloud sync or advertising fetch.
@MainActor
final class PersonalLibrary: ObservableObject {
    @Published private(set) var brands: [MerchantBrand] = []
    @Published private(set) var coupons: [SavedCoupon] = []
    @Published var error: String?
    private(set) var owner: UUID?
    private struct Contents: Codable { var brands: [MerchantBrand]; var coupons: [SavedCoupon] }
    private func file(_ uid: UUID) throws -> URL {
        let dir = try FileManager.default.url(for: .applicationSupportDirectory, in: .userDomainMask, appropriateFor: nil, create: true)
            .appendingPathComponent("PersonalLibrary", isDirectory: true).appendingPathComponent(uid.uuidString.lowercased(), isDirectory: true)
        try FileManager.default.createDirectory(at: dir, withIntermediateDirectories: true, attributes: [.protectionKey: FileProtectionType.completeUntilFirstUserAuthentication])
        return dir.appendingPathComponent("library.json")
    }
    func activate(_ uid: UUID?) {
        guard owner != uid else { return }
        owner = uid; brands = []; coupons = []; error = nil
        guard let uid else { return }
        do {
            let url = try file(uid)
            guard FileManager.default.fileExists(atPath: url.path) else { return }
            let value = try JSONDecoder().decode(Contents.self, from: Data(contentsOf: url))
            brands = value.brands; coupons = value.coupons
        } catch { self.error = "Die persönliche Ablage konnte nicht geladen werden. Vorhandene Dateien werden nicht überschrieben." }
    }
    func brand(for name: String, owner uid: UUID?) -> MerchantBrand? {
        guard let uid, uid == owner else { return nil }
        return brands.first { $0.id == ReceiptRules.merchantKey(name) }
    }
    func saveBrand(_ brand: MerchantBrand, owner uid: UUID) throws {
        guard owner == uid, error == nil else { throw PiggyFailure.signedOut }
        var next = brands; next.removeAll { $0.id == brand.id }; next.append(brand)
        try write(brands: next, coupons: coupons, uid: uid); brands = next
    }
    func saveCoupon(_ coupon: SavedCoupon, owner uid: UUID) throws {
        guard owner == uid, error == nil else { throw PiggyFailure.signedOut }
        var next = coupons; next.removeAll { $0.id == coupon.id }; next.insert(coupon, at: 0)
        try write(brands: brands, coupons: next, uid: uid); coupons = next
    }
    private func write(brands: [MerchantBrand], coupons: [SavedCoupon], uid: UUID) throws {
        let data = try JSONEncoder().encode(Contents(brands: brands, coupons: coupons))
        try data.write(to: file(uid), options: [.atomic, .completeFileProtectionUntilFirstUserAuthentication])
    }
    static func logo(_ image: UIImage) -> Data? {
        let format = UIGraphicsImageRendererFormat(); format.scale = 1
        return UIGraphicsImageRenderer(size: CGSize(width: 160, height: 160), format: format).pngData { _ in
            let scale = min(160 / image.size.width, 160 / image.size.height)
            let size = CGSize(width: image.size.width * scale, height: image.size.height * scale)
            image.draw(in: CGRect(x: (160-size.width)/2, y: (160-size.height)/2, width: size.width, height: size.height))
        }
    }
    static func couponImage(_ image: UIImage) -> Data? {
        let scale = min(1, 1800 / max(image.size.width, image.size.height))
        let format = UIGraphicsImageRendererFormat(); format.scale = 1; format.opaque = true
        return UIGraphicsImageRenderer(size: CGSize(width: image.size.width*scale, height: image.size.height*scale), format: format).jpegData(withCompressionQuality: 0.9) { _ in
            image.draw(in: CGRect(x: 0, y: 0, width: image.size.width*scale, height: image.size.height*scale))
        }
    }
    static func purge(_ uid: UUID) throws {
        let base = try FileManager.default.url(for: .applicationSupportDirectory, in: .userDomainMask, appropriateFor: nil, create: false)
            .appendingPathComponent("PersonalLibrary").appendingPathComponent(uid.uuidString.lowercased())
        if FileManager.default.fileExists(atPath: base.path) { try FileManager.default.removeItem(at: base) }
    }
}
