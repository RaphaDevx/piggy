import SwiftUI
import UIKit

enum Theme {
    static let background = Color(uiColor: .systemGroupedBackground)
    static let surface = Color(uiColor: .secondarySystemGroupedBackground)
    static let accent = Color(red: 1, green: 0.624, blue: 0.039)
    static let pink = Color(red: 1, green: 0.72, blue: 0.78)
    static let text = Color.primary
    static let secondary = Color.secondary
}

struct BrandHeader: View {
    @EnvironmentObject private var gold: GoldManager
    @AppStorage("piggy.variant") private var variant = "automatic"
    var body: some View {
        HStack(spacing: 8) {
            if let image = BrandResources.variantImage(variant) {
                Image(uiImage: image).resizable().scaledToFit().frame(width: 42, height: 42)
            }
            Text("Piggy").font(.title.bold())
            if gold.isGold { Text("GOLD").font(.caption.bold()).padding(6).background(Theme.accent.opacity(0.25), in: Capsule()) }
        }
        .accessibilityElement(children: .ignore)
        .accessibilityLabel(gold.isGold ? "Piggy Gold" : "Piggy")
    }
}

struct BrandVariant: Decodable, Identifiable {
    let id: String
    let kind: String
    let name: String
    let valid: Window?
    struct Window: Decodable { let from: String; let to: String }
}

enum BrandResources {
    static let variants: [BrandVariant] = {
        guard let url = Bundle.main.url(forResource: "index", withExtension: "json", subdirectory: "Brand/variants"),
              let data = try? Data(contentsOf: url), let rows = try? JSONDecoder().decode([BrandVariant].self, from: data) else { return [] }
        return rows
    }()
    static func variantID(_ selected: String, date: Date = Date(), calendar: Calendar = .current) -> String {
        if selected != "automatic" { return variants.contains(where: { $0.id == selected }) ? selected : "base" }
        let parts = calendar.dateComponents([.month, .day], from: date)
        let current = String(format: "%02d-%02d", parts.month ?? 1, parts.day ?? 1)
        // Festival dates may move each year. Automatic mode uses only the authored season windows.
        return variants.first(where: { variant in
            guard variant.kind == "season", let valid = variant.valid else { return false }
            return valid.from <= valid.to ? (valid.from...valid.to).contains(current) : current >= valid.from || current <= valid.to
        })?.id ?? "base"
    }
    static func variantImage(_ selected: String) -> UIImage? {
        let id = variantID(selected)
        guard let url = Bundle.main.url(forResource: "signet@3x", withExtension: "png", subdirectory: "Brand/variants/\(id)") else { return nil }
        return UIImage(contentsOfFile: url.path)
    }
    static func sprite(_ pose: String) -> UIImage? {
        for name in ["piggy-mascot-\(pose)@3x", "piggy-mascot-idle-happy@3x"] {
            if let url = Bundle.main.url(forResource: name, withExtension: "png", subdirectory: "Brand/3d/sprites"), let image = UIImage(contentsOfFile: url.path) { return image }
        }
        return nil
    }
}
