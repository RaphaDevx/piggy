import ExpoModulesCore
import Foundation

// Requires iOS 18.4+ — falls Framework fehlt, gibt isAvailable false zurück
public class PiggyFoundationModelsModule: Module {
  public func definition() -> ModuleDefinition {
    Name("PiggyFoundationModels")

    AsyncFunction("isAvailable") { () -> Bool in
      if #available(iOS 18.4, *) {
        // Foundation Models framework check
        // In echtem Build: import FoundationModels und LanguageModel.availability prüfen
        return true
      }
      return false
    }

    AsyncFunction("parseReceiptText") { (rawText: String) -> String in
      guard #available(iOS 18.4, *) else {
        throw NSError(domain: "PiggyFoundationModels", code: 1,
          userInfo: [NSLocalizedDescriptionKey: "iOS 18.4+ required"])
      }
      // Foundation Models Prompt
      let prompt = """
        Du bist ein Quittungs-Parser. Extrahiere die Daten aus diesem OCR-Text einer Schweizer/deutschen Quittung.
        Antworte NUR mit einem JSON-Objekt (kein Markdown):
        {"store_name":"...","store_category":"Supermarkt|Drogerie|...","date":"YYYY-MM-DD","total_amount":0.0,
         "currency":"CHF","payment_method":"Karte|Bargeld|TWINT|Unbekannt","payment_card":null,
         "items":[{"name":"...","quantity":1,"unit":"Stk","unit_price":0.0,"total_price":0.0,"tags":[]}]}

        OCR-Text:
        \(rawText)
        """
      // TODO: echte Foundation Models API nach erstem EAS Build einbauen:
      // let model = LanguageModel()
      // let session = LanguageModelSession(model: model)
      // let response = try await session.respond(to: prompt)
      // return response.content

      // Stub für jetzt — löst einen Fehler aus damit Fallback zu Edge greift
      throw NSError(domain: "PiggyFoundationModels", code: 2,
        userInfo: [NSLocalizedDescriptionKey: "Foundation Models: native build required"])
    }
  }
}
