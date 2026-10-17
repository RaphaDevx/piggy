import ExpoModulesCore
import UIKit

public class PiggyReceiptScannerModule: Module {
  private var scanner: ReceiptCaptureController?

  public func definition() -> ModuleDefinition {
    Name("PiggyReceiptScanner")

    AsyncFunction("scanReceipt") { (promise: Promise) in
      guard self.scanner == nil else {
        promise.reject("SCAN_BUSY", "Ein Scan ist bereits geöffnet.")
        return
      }
      guard let presenter = self.appContext?.utilities?.currentViewController(),
            presenter.view.window != nil, !presenter.isBeingDismissed else {
        promise.reject("SCAN_PRESENTATION", "Die Kamera konnte noch nicht geöffnet werden. Bitte erneut versuchen.")
        return
      }
      let controller = ReceiptCaptureController()
      controller.modalPresentationStyle = .fullScreen
      self.scanner = controller
      controller.onComplete = { [weak self, weak controller] result in
        controller?.dismiss(animated: true) {
          self?.scanner = nil
          switch result {
          case .success(let capture): promise.resolve(capture)
          case .failure(let error): promise.reject("SCAN_FAILED", error.localizedDescription)
          }
        }
      }
      presenter.present(controller, animated: true)
    }.runOnQueue(.main)
  }
}
