import AVFoundation
import CoreImage
import UIKit
import Vision
import OSLog

/// The confirmation button belongs to Piggy. Both shutters enter the same review state.
final class ReceiptCaptureController: UIViewController, AVCapturePhotoCaptureDelegate,
  AVCaptureVideoDataOutputSampleBufferDelegate {
  private enum Stage { case scanning, capturing, review, finished }
  private var stage: Stage = .scanning
  private let session = AVCaptureSession()
  private let sessionQueue = DispatchQueue(label: "piggy.receipt.camera")
  private let detectionQueue = DispatchQueue(label: "piggy.receipt.detection")
  private let photos = AVCapturePhotoOutput()
  private var preview: AVCaptureVideoPreviewLayer!
  private var capturedImage: UIImage?
  private var cropped = false
  private let logger = Logger(subsystem: "com.heartbeat.piggy", category: "ReceiptCapture")
  private var stableSince: TimeInterval?
  private var previousBounds: CGRect?
  private var lastDetection: TimeInterval = 0
  private var autoAfter: TimeInterval = 0
  private let minimumStableDuration: TimeInterval = 1.0
  private let detectionInterval: TimeInterval = 0.3
  private let imageView = UIImageView()
  private let guide = UIView()
  private let header = UILabel()
  private let hint = UILabel()
  private let cancelButton = UIButton(type: .system)
  private let shutter = UIButton(type: .system)
  private let confirmButton = UIButton(type: .system)
  private let retakeButton = UIButton(type: .system)
  private let autoSwitch = UISwitch()
  private let autoLabel = UILabel()
  private var bottomStack: UIStackView!
  var onComplete: ((Result<[String: Any], Error>) -> Void)?

  override var supportedInterfaceOrientations: UIInterfaceOrientationMask { .portrait }
  override var preferredStatusBarStyle: UIStatusBarStyle { .lightContent }

  override func viewDidLoad() {
    super.viewDidLoad()
    view.backgroundColor = .black
    preview = AVCaptureVideoPreviewLayer(session: session)
    preview.videoGravity = .resizeAspectFill
    view.layer.addSublayer(preview)
    buildControls()
    NotificationCenter.default.addObserver(self, selector: #selector(pauseCamera), name: UIApplication.willResignActiveNotification, object: nil)
    NotificationCenter.default.addObserver(self, selector: #selector(resumeCamera), name: UIApplication.didBecomeActiveNotification, object: nil)
    startCamera()
  }

  deinit { NotificationCenter.default.removeObserver(self) }

  override func viewDidLayoutSubviews() {
    super.viewDidLayoutSubviews()
    preview.frame = view.bounds
    if let connection = preview.connection, connection.isVideoOrientationSupported {
      connection.videoOrientation = .portrait
    }
  }

  override func viewDidDisappear(_ animated: Bool) {
    super.viewDidDisappear(animated)
    sessionQueue.async { [self] in if session.isRunning { session.stopRunning() } }
  }

  private func button(_ button: UIButton, title: String, symbol: String, primary: Bool = false) {
    var config = primary ? UIButton.Configuration.filled() : .tinted()
    config.title = title
    config.image = UIImage(systemName: symbol)
    config.imagePadding = 8
    config.cornerStyle = .capsule
    config.baseBackgroundColor = primary ? .systemBlue : .darkGray
    config.baseForegroundColor = .white
    config.contentInsets = NSDirectionalEdgeInsets(top: 15, leading: 18, bottom: 15, trailing: 18)
    button.configuration = config
    button.titleLabel?.adjustsFontForContentSizeCategory = true
    button.heightAnchor.constraint(greaterThanOrEqualToConstant: 52).isActive = true
  }

  private func buildControls() {
    imageView.contentMode = .scaleAspectFit
    imageView.backgroundColor = .black
    imageView.isHidden = true
    imageView.accessibilityLabel = "Aufgenommene Quittung"
    imageView.isAccessibilityElement = true
    view.addSubview(imageView)
    imageView.translatesAutoresizingMaskIntoConstraints = false

    let top = UIVisualEffectView(effect: UIBlurEffect(style: .systemChromeMaterialDark))
    top.layer.cornerRadius = 24
    top.clipsToBounds = true
    view.addSubview(top)
    top.translatesAutoresizingMaskIntoConstraints = false
    button(cancelButton, title: "Abbrechen", symbol: "xmark")
    cancelButton.addTarget(self, action: #selector(cancel), for: .touchUpInside)
    header.text = "Quittung scannen"
    header.font = .preferredFont(forTextStyle: .headline)
    header.adjustsFontForContentSizeCategory = true
    header.textColor = .white
    header.numberOfLines = 0
    let topStack = UIStackView(arrangedSubviews: [header, cancelButton])
    topStack.axis = .vertical
    topStack.spacing = 8
    top.contentView.addSubview(topStack)
    topStack.translatesAutoresizingMaskIntoConstraints = false

    guide.layer.borderWidth = 2
    guide.layer.borderColor = UIColor.white.withAlphaComponent(0.65).cgColor
    guide.layer.cornerRadius = 20
    guide.isUserInteractionEnabled = false
    view.addSubview(guide)
    guide.translatesAutoresizingMaskIntoConstraints = false

    let bottom = UIVisualEffectView(effect: UIBlurEffect(style: .systemChromeMaterialDark))
    bottom.layer.cornerRadius = 26
    bottom.clipsToBounds = true
    view.addSubview(bottom)
    bottom.translatesAutoresizingMaskIntoConstraints = false
    hint.text = "Quittung ruhig und vollständig ins Bild halten."
    hint.font = .preferredFont(forTextStyle: .subheadline)
    hint.adjustsFontForContentSizeCategory = true
    hint.textColor = .white
    hint.numberOfLines = 0
    hint.textAlignment = .center
    autoLabel.text = "Automatisch aufnehmen"
    autoLabel.font = .preferredFont(forTextStyle: .body)
    autoLabel.adjustsFontForContentSizeCategory = true
    autoLabel.textColor = .white
    autoLabel.numberOfLines = 0
    autoSwitch.isOn = true
    autoSwitch.accessibilityLabel = "Automatisch aufnehmen"
    autoSwitch.addTarget(self, action: #selector(autoChanged), for: .valueChanged)
    let autoRow = UIStackView(arrangedSubviews: [autoLabel, autoSwitch])
    autoRow.spacing = 12
    autoRow.alignment = .center
    button(shutter, title: "Foto aufnehmen", symbol: "camera", primary: true)
    shutter.addTarget(self, action: #selector(manualCapture), for: .touchUpInside)
    button(confirmButton, title: "Scan übernehmen", symbol: "checkmark", primary: true)
    confirmButton.addTarget(self, action: #selector(confirm), for: .touchUpInside)
    confirmButton.isHidden = true
    button(retakeButton, title: "Neu aufnehmen", symbol: "arrow.counterclockwise")
    retakeButton.addTarget(self, action: #selector(retake), for: .touchUpInside)
    retakeButton.isHidden = true
    bottomStack = UIStackView(arrangedSubviews: [hint, autoRow, shutter, confirmButton, retakeButton])
    bottomStack.axis = .vertical
    bottomStack.spacing = 12
    bottom.contentView.addSubview(bottomStack)
    bottomStack.translatesAutoresizingMaskIntoConstraints = false
    let safe = view.safeAreaLayoutGuide
    NSLayoutConstraint.activate([
      top.topAnchor.constraint(equalTo: safe.topAnchor, constant: 8),
      top.leadingAnchor.constraint(equalTo: safe.leadingAnchor, constant: 16),
      top.trailingAnchor.constraint(equalTo: safe.trailingAnchor, constant: -16),
      topStack.topAnchor.constraint(equalTo: top.contentView.topAnchor, constant: 12),
      topStack.bottomAnchor.constraint(equalTo: top.contentView.bottomAnchor, constant: -12),
      topStack.leadingAnchor.constraint(equalTo: top.contentView.leadingAnchor, constant: 16),
      topStack.trailingAnchor.constraint(equalTo: top.contentView.trailingAnchor, constant: -16),
      bottom.bottomAnchor.constraint(equalTo: safe.bottomAnchor, constant: -8),
      bottom.leadingAnchor.constraint(equalTo: safe.leadingAnchor, constant: 16),
      bottom.trailingAnchor.constraint(equalTo: safe.trailingAnchor, constant: -16),
      bottomStack.topAnchor.constraint(equalTo: bottom.contentView.topAnchor, constant: 16),
      bottomStack.bottomAnchor.constraint(equalTo: bottom.contentView.bottomAnchor, constant: -16),
      bottomStack.leadingAnchor.constraint(equalTo: bottom.contentView.leadingAnchor, constant: 16),
      bottomStack.trailingAnchor.constraint(equalTo: bottom.contentView.trailingAnchor, constant: -16),
      guide.topAnchor.constraint(equalTo: top.bottomAnchor, constant: 24),
      guide.bottomAnchor.constraint(equalTo: bottom.topAnchor, constant: -24),
      guide.centerXAnchor.constraint(equalTo: view.centerXAnchor),
      guide.widthAnchor.constraint(equalTo: view.widthAnchor, multiplier: 0.76),
      imageView.topAnchor.constraint(equalTo: top.bottomAnchor, constant: 12),
      imageView.bottomAnchor.constraint(equalTo: bottom.topAnchor, constant: -12),
      imageView.leadingAnchor.constraint(equalTo: safe.leadingAnchor, constant: 12),
      imageView.trailingAnchor.constraint(equalTo: safe.trailingAnchor, constant: -12)
    ])
  }

  private func startCamera() {
    switch AVCaptureDevice.authorizationStatus(for: .video) {
    case .authorized: configureCamera()
    case .notDetermined:
      AVCaptureDevice.requestAccess(for: .video) { [weak self] allowed in
        DispatchQueue.main.async {
          if allowed { self?.configureCamera() } else { self?.cameraError("Bitte erlaube den Kamera-Zugriff in den iPhone-Einstellungen.") }
        }
      }
    default: cameraError("Bitte erlaube den Kamera-Zugriff in den iPhone-Einstellungen.")
    }
  }

  private func configureCamera() {
    guard stage != .finished else { return }
    sessionQueue.async { [self] in
      do {
        guard let device = AVCaptureDevice.default(.builtInWideAngleCamera, for: .video, position: .back) else {
          throw NSError(domain: "PiggyScanner", code: 1, userInfo: [NSLocalizedDescriptionKey: "Keine Kamera verfügbar."])
        }
        let input = try AVCaptureDeviceInput(device: device)
        session.beginConfiguration()
        session.sessionPreset = .photo
        let video = AVCaptureVideoDataOutput()
        video.alwaysDiscardsLateVideoFrames = true
        video.setSampleBufferDelegate(self, queue: detectionQueue)
        guard session.canAddInput(input), session.canAddOutput(photos), session.canAddOutput(video) else {
          session.commitConfiguration()
          throw NSError(domain: "PiggyScanner", code: 2, userInfo: [NSLocalizedDescriptionKey: "Kamera konnte nicht gestartet werden."])
        }
        session.addInput(input)
        session.addOutput(photos)
        session.addOutput(video)
        for connection in [video.connection(with: .video), photos.connection(with: .video)] {
          if connection?.isVideoOrientationSupported == true { connection?.videoOrientation = .portrait }
        }
        session.commitConfiguration()
        session.startRunning()
      } catch { DispatchQueue.main.async { self.cameraError(error.localizedDescription) } }
    }
  }

  func captureOutput(_ output: AVCaptureOutput, didOutput sampleBuffer: CMSampleBuffer,
                     from connection: AVCaptureConnection) {
    let now = ProcessInfo.processInfo.systemUptime
    guard now - lastDetection >= detectionInterval else { return }
    lastDetection = now
    guard let pixelBuffer = CMSampleBufferGetImageBuffer(sampleBuffer) else { return }
    let request = VNDetectRectanglesRequest()
    request.maximumObservations = 1
    request.minimumConfidence = 0.8
    request.minimumAspectRatio = 0.1
    request.minimumSize = 0.2
    do {
      try VNImageRequestHandler(cvPixelBuffer: pixelBuffer, orientation: .up).perform([request])
      let rectangle = request.results?.first
      DispatchQueue.main.async { [weak self] in self?.detected(rectangle, at: now) }
    } catch {
      // Manual capture remains available when a frame cannot be analysed.
    }
  }

  private func detected(_ rectangle: VNRectangleObservation?, at now: TimeInterval) {
    guard stage == .scanning else { return }
    guard let rectangle else {
      stableSince = nil
      previousBounds = nil
      hint.text = "Quittung ruhig und vollständig ins Bild halten."
      return
    }
    let bounds = rectangle.boundingBox
    if let previous = previousBounds,
       abs(previous.midX - bounds.midX) < 0.025, abs(previous.midY - bounds.midY) < 0.025,
       abs(previous.width - bounds.width) < 0.025, abs(previous.height - bounds.height) < 0.025 {
      if stableSince == nil { stableSince = now }
    } else { stableSince = now }
    previousBounds = bounds
    hint.text = autoSwitch.isOn ? "Quittung erkannt. Kurz ruhig halten …" : "Quittung erkannt. Foto aufnehmen."
    if autoSwitch.isOn, now >= autoAfter, let since = stableSince, now - since >= minimumStableDuration {
      capture(automatic: true)
    }
  }

  @objc private func autoChanged() { stableSince = nil }
  @objc private func manualCapture() { capture(automatic: false) }

  private func capture(automatic: Bool) {
    guard stage == .scanning, session.isRunning else { return }
    stage = .capturing
    logger.info("capture.request automatic=\(automatic)")
    shutter.isEnabled = false
    hint.text = "Foto wird aufgenommen …"
    photos.capturePhoto(with: AVCapturePhotoSettings(), delegate: self)
  }

  func photoOutput(_ output: AVCapturePhotoOutput, didFinishProcessingPhoto photo: AVCapturePhoto,
                   error: Error?) {
    DispatchQueue.main.async { [self] in
      guard stage == .capturing else { return }
      guard error == nil, let data = photo.fileDataRepresentation(), let image = UIImage(data: data) else {
        stage = .scanning
        shutter.isEnabled = true
        autoAfter = ProcessInfo.processInfo.systemUptime + 2
        hint.text = "Aufnahme fehlgeschlagen. Bitte erneut aufnehmen."
        return
      }
      let format = UIGraphicsImageRendererFormat()
      format.scale = 1
      let normalized = UIGraphicsImageRenderer(size: image.size, format: format).image { _ in image.draw(in: CGRect(origin: .zero, size: image.size)) }
      cropped = false
      // Detect boundaries in the actual photo, not a potentially stale preview frame.
      capturedImage = corrected(normalized) ?? normalized
      imageView.image = capturedImage
      imageView.isHidden = false
      guide.isHidden = true
      shutter.isHidden = true
      autoSwitch.superview?.isHidden = true
      confirmButton.isHidden = false
      confirmButton.isEnabled = true
      retakeButton.isHidden = false
      header.text = "Scan prüfen"
      hint.text = "Ist die ganze Quittung gut lesbar?"
      stage = .review
      logger.info("capture.review")
      sessionQueue.async { [self] in session.stopRunning() }
      UIAccessibility.post(notification: .screenChanged, argument: confirmButton)
    }
  }

  private func corrected(_ image: UIImage) -> UIImage? {
    guard let cgImage = image.cgImage else { return nil }
    let request = VNDetectRectanglesRequest()
    request.maximumObservations = 1
    request.minimumConfidence = 0.8
    request.minimumAspectRatio = 0.1
    request.minimumSize = 0.2
    do { try VNImageRequestHandler(cgImage: cgImage, orientation: .up).perform([request]) }
    catch { return nil }
    guard let rectangle = request.results?.first else { return nil }
    let input = CIImage(cgImage: cgImage)
    let size = input.extent.size
    func point(_ p: CGPoint) -> CIVector { CIVector(x: p.x * size.width, y: p.y * size.height) }
    guard let filter = CIFilter(name: "CIPerspectiveCorrection", parameters: [
      kCIInputImageKey: input,
      "inputTopLeft": point(rectangle.topLeft), "inputTopRight": point(rectangle.topRight),
      "inputBottomLeft": point(rectangle.bottomLeft), "inputBottomRight": point(rectangle.bottomRight)
    ]), let output = filter.outputImage,
      let result = CIContext().createCGImage(output, from: output.extent) else { return nil }
    cropped = true
    return UIImage(cgImage: result)
  }

  @objc private func retake() {
    guard stage == .review else { return }
    capturedImage = nil
    imageView.image = nil
    imageView.isHidden = true
    confirmButton.isHidden = true
    retakeButton.isHidden = true
    guide.isHidden = false
    shutter.isHidden = false
    shutter.isEnabled = true
    autoSwitch.superview?.isHidden = false
    header.text = "Quittung scannen"
    hint.text = "Quittung ruhig und vollständig ins Bild halten."
    stableSince = nil
    previousBounds = nil
    autoAfter = ProcessInfo.processInfo.systemUptime + 1.5
    stage = .scanning
    logger.info("capture.retake")
    sessionQueue.async { [self] in session.startRunning() }
  }

  @objc private func confirm() {
    guard stage == .review, let image = capturedImage else { return }
    confirmButton.isEnabled = false
    do {
      guard let data = image.jpegData(compressionQuality: 0.95) else {
        throw NSError(domain: "PiggyScanner", code: 3, userInfo: [NSLocalizedDescriptionKey: "Bild konnte nicht gespeichert werden."])
      }
      let url = FileManager.default.temporaryDirectory.appendingPathComponent("piggy-scan-\(UUID().uuidString).jpg")
      try data.write(to: url, options: .atomic)
      logger.info("capture.confirm")
      finish(.success(["status": "success", "uri": url.absoluteString, "precropped": cropped]))
    } catch {
      confirmButton.isEnabled = true
      hint.text = "Bild konnte nicht gespeichert werden. Bitte erneut versuchen."
    }
  }

  @objc private func cancel() {
    logger.info("capture.cancel")
    finish(.success(["status": "cancel"]))
  }

  @objc private func pauseCamera() {
    stableSince = nil
    sessionQueue.async { [self] in if session.isRunning { session.stopRunning() } }
  }

  @objc private func resumeCamera() {
    guard stage == .scanning else { return }
    stableSince = nil
    autoAfter = ProcessInfo.processInfo.systemUptime + 1.5
    sessionQueue.async { [self] in
      if !session.inputs.isEmpty && !session.isRunning { session.startRunning() }
    }
  }

  private func cameraError(_ message: String) {
    guard stage != .finished else { return }
    shutter.isEnabled = false
    hint.text = message
  }

  private func finish(_ result: Result<[String: Any], Error>) {
    guard stage != .finished else { return }
    stage = .finished
    sessionQueue.async { [self] in if session.isRunning { session.stopRunning() } }
    onComplete?(result)
    onComplete = nil
  }
}
