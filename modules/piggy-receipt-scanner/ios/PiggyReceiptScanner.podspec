require 'json'
package = JSON.parse(File.read(File.join(__dir__, '..', 'package.json')))
Pod::Spec.new do |s|
  s.name = 'PiggyReceiptScanner'
  s.version = package['version']
  s.summary = package['description']
  s.description = package['description']
  s.license = package['license']
  s.author = 'Piggy'
  s.homepage = 'https://github.com/RaphaDevx/piggy'
  s.platforms = { :ios => '16.4' }
  s.source = { :git => 'https://github.com/RaphaDevx/piggy.git' }
  s.static_framework = true
  s.dependency 'ExpoModulesCore'
  s.frameworks = 'AVFoundation', 'Vision', 'CoreImage', 'UIKit'
  s.source_files = '**/*.swift'
end
