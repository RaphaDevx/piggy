#!/usr/bin/env python3
"""Deterministic, dependency-free generator for the checked-in Xcode project."""
import hashlib, json, plistlib
from pathlib import Path
from xml.sax.saxutils import escape
root = Path(__file__).resolve().parents[1]
def uid(s): return hashlib.sha256(s.encode()).hexdigest()[:24].upper()
def q(s): return json.dumps(str(s))
objects = {}
def obj(key, value):
    objects[uid(key)] = value
    return uid(key)
sources = sorted(p.relative_to(root).as_posix() for p in (root / 'Sources').rglob('*.swift'))
source_refs, builds = [], []
for path in sources:
    ref = obj(path, '{isa = PBXFileReference; lastKnownFileType = sourcecode.swift; path = ' + q(path) + '; sourceTree = SOURCE_ROOT;}')
    source_refs.append(ref)
    builds.append(obj('build:'+path, '{isa = PBXBuildFile; fileRef = '+ref+';}'))
resource_builds, resource_refs = [], []
for path, kind in [('Resources/Brand','folder'), ('Resources/Assets.xcassets','folder.assetcatalog'), ('Resources/PrivacyInfo.xcprivacy','text.xml')]:
    ref = obj(path, '{isa = PBXFileReference; lastKnownFileType = '+kind+'; path = '+q(path)+'; sourceTree = SOURCE_ROOT;}')
    resource_refs.append(ref)
    resource_builds.append(obj('build:'+path, '{isa = PBXBuildFile; fileRef = '+ref+';}'))
framework_builds, dependencies, packages = [], [], []
for name, url, version in [
    ('Supabase','https://github.com/supabase/supabase-swift.git','2.55.7'),
    # Includes the designated-initializer fix required by Xcode 27 (upstream #6949).
    ('RevenueCat','https://github.com/RevenueCat/purchases-ios.git','5.103.2'),
    ('GoogleMobileAds','https://github.com/googleads/swift-package-manager-google-mobile-ads.git','12.11.0'),
    ('GoogleUserMessagingPlatform','https://github.com/googleads/swift-package-manager-google-user-messaging-platform.git','3.0.0')]:
    package = obj('package:'+name, '{isa = XCRemoteSwiftPackageReference; repositoryURL = '+q(url)+'; requirement = {kind = exactVersion; version = '+version+';};}')
    dependency = obj('product:'+name, '{isa = XCSwiftPackageProductDependency; package = '+package+'; productName = '+name+';}')
    packages.append(package); dependencies.append(dependency)
    framework_builds.append(obj('framework:'+name, '{isa = PBXBuildFile; productRef = '+dependency+';}'))
def seq(items): return '('+','.join(items)+',)'
sources_phase = obj('sources','{isa = PBXSourcesBuildPhase; buildActionMask = 2147483647; files = '+seq(builds)+'; runOnlyForDeploymentPostprocessing = 0;}')
resources_phase = obj('resources','{isa = PBXResourcesBuildPhase; buildActionMask = 2147483647; files = '+seq(resource_builds)+'; runOnlyForDeploymentPostprocessing = 0;}')
frameworks_phase = obj('frameworks','{isa = PBXFrameworksBuildPhase; buildActionMask = 2147483647; files = '+seq(framework_builds)+'; runOnlyForDeploymentPostprocessing = 0;}')
app = obj('app','{isa = PBXFileReference; explicitFileType = wrapper.application; includeInIndex = 0; path = Piggy.app; sourceTree = BUILT_PRODUCTS_DIR;}')
share_product = obj('share-product', '{isa = PBXFileReference; explicitFileType = \"wrapper.app-extension\"; includeInIndex = 0; path = PiggyShare.appex; sourceTree = BUILT_PRODUCTS_DIR;}')
products = obj('products','{isa = PBXGroup; children = '+seq([app,share_product])+'; name = Products; sourceTree = "<group>";}')
configs = []
project_configs = []
for mode in ['Debug','Release']:
    cfg = obj('xcconfig:'+mode, '{isa = PBXFileReference; lastKnownFileType = text.xcconfig; path = Config/'+mode+'.xcconfig; sourceTree = SOURCE_ROOT;}')
    source_refs.append(cfg)
    configs.append(obj('targetconfig:'+mode, '{isa = XCBuildConfiguration; baseConfigurationReference = '+cfg+'; buildSettings = {PRODUCT_NAME = Piggy; PRODUCT_BUNDLE_IDENTIFIER = com.heartbeat.piggy; SDKROOT = iphoneos; SUPPORTED_PLATFORMS = "iphoneos iphonesimulator"; TARGETED_DEVICE_FAMILY = 1; IPHONEOS_DEPLOYMENT_TARGET = 17.0; SWIFT_VERSION = 5.0; SWIFT_STRICT_CONCURRENCY = targeted; GENERATE_INFOPLIST_FILE = NO; INFOPLIST_FILE = Config/Info.plist; CODE_SIGN_ENTITLEMENTS = Config/Piggy.entitlements; CODE_SIGN_STYLE = Automatic; DEVELOPMENT_TEAM = H6X9JDXF96; ASSETCATALOG_COMPILER_APPICON_NAME = AppIcon; MARKETING_VERSION = 1.0; CURRENT_PROJECT_VERSION = 28; LD_RUNPATH_SEARCH_PATHS = "$(inherited) @executable_path/Frameworks";}; name = '+mode+';}'))
    project_configs.append(obj('projectconfig:'+mode,'{isa = XCBuildConfiguration; buildSettings = {CLANG_ENABLE_MODULES = YES; SWIFT_OPTIMIZATION_LEVEL = '+q('-Onone' if mode == 'Debug' else '-O')+'; SWIFT_ACTIVE_COMPILATION_CONDITIONS = '+q('DEBUG $(inherited)' if mode == 'Debug' else '$(inherited)')+'; DEBUG_INFORMATION_FORMAT = '+q('dwarf' if mode == 'Debug' else 'dwarf-with-dsym')+';}; name = '+mode+';}'))
target_list = obj('targetconfigs','{isa = XCConfigurationList; buildConfigurations = '+seq(configs)+'; defaultConfigurationIsVisible = 0; defaultConfigurationName = Release;}')
project_list = obj('projectconfigs','{isa = XCConfigurationList; buildConfigurations = '+seq(project_configs)+'; defaultConfigurationIsVisible = 0; defaultConfigurationName = Release;}')
share_builds = []
for path in ['Sources/Core/ShareInbox.swift', 'Sources/Core/MerchantAndCoupon.swift', 'ShareExtension/ShareViewController.swift']:
    if uid(path) not in objects:
        source_refs.append(obj(path, '{isa = PBXFileReference; lastKnownFileType = sourcecode.swift; path = '+q(path)+'; sourceTree = SOURCE_ROOT;}'))
    share_builds.append(obj('sharebuild:'+path, '{isa = PBXBuildFile; fileRef = '+uid(path)+';}'))
for path in ['ShareExtension/Info.plist','ShareExtension/Share.entitlements']:
    source_refs.append(obj(path, '{isa = PBXFileReference; lastKnownFileType = text.plist.xml; path = '+q(path)+'; sourceTree = SOURCE_ROOT;}'))
share_sources = obj('share-sources','{isa = PBXSourcesBuildPhase; buildActionMask = 2147483647; files = '+seq(share_builds)+'; runOnlyForDeploymentPostprocessing = 0;}')
share_privacy_ref = obj('ShareExtension/PrivacyInfo.xcprivacy', '{isa = PBXFileReference; lastKnownFileType = text.xml; path = ShareExtension/PrivacyInfo.xcprivacy; sourceTree = SOURCE_ROOT;}')
source_refs.append(share_privacy_ref)
share_privacy_build = obj('share-privacy-build', '{isa = PBXBuildFile; fileRef = '+share_privacy_ref+';}')
share_resources = obj('share-resources', '{isa = PBXResourcesBuildPhase; buildActionMask = 2147483647; files = '+seq([share_privacy_build])+'; runOnlyForDeploymentPostprocessing = 0;}')
share_frameworks = obj('share-frameworks','{isa = PBXFrameworksBuildPhase; buildActionMask = 2147483647; files = (); runOnlyForDeploymentPostprocessing = 0;}')
share_configs = []
for mode in ['Debug','Release']:
    share_configs.append(obj('share-config:'+mode,'{isa = XCBuildConfiguration; buildSettings = {PRODUCT_NAME = PiggyShare; PRODUCT_BUNDLE_IDENTIFIER = com.heartbeat.piggy.share; SDKROOT = iphoneos; SUPPORTED_PLATFORMS = "iphoneos iphonesimulator"; TARGETED_DEVICE_FAMILY = 1; IPHONEOS_DEPLOYMENT_TARGET = 17.0; SWIFT_VERSION = 5.0; APPLICATION_EXTENSION_API_ONLY = YES; SKIP_INSTALL = YES; GENERATE_INFOPLIST_FILE = NO; INFOPLIST_FILE = ShareExtension/Info.plist; CODE_SIGN_ENTITLEMENTS = ShareExtension/Share.entitlements; CODE_SIGN_STYLE = Automatic; DEVELOPMENT_TEAM = H6X9JDXF96; MARKETING_VERSION = 1.0; CURRENT_PROJECT_VERSION = 28; LD_RUNPATH_SEARCH_PATHS = "$(inherited) @executable_path/Frameworks @executable_path/../../Frameworks";}; name = '+mode+';}'))
share_list = obj('share-configs','{isa = XCConfigurationList; buildConfigurations = '+seq(share_configs)+'; defaultConfigurationIsVisible = 0; defaultConfigurationName = Release;}')
share_target = obj('share-target','{isa = PBXNativeTarget; buildConfigurationList = '+share_list+'; buildPhases = '+seq([share_sources,share_frameworks,share_resources])+'; buildRules = (); dependencies = (); name = PiggyShare; productName = PiggyShare; productReference = '+share_product+'; productType = "com.apple.product-type.app-extension";}')
proxy = obj('share-proxy','{isa = PBXContainerItemProxy; containerPortal = '+uid('project')+'; proxyType = 1; remoteGlobalIDString = '+share_target+'; remoteInfo = PiggyShare;}')
share_dependency = obj('share-dependency','{isa = PBXTargetDependency; target = '+share_target+'; targetProxy = '+proxy+';}')
embed_build = obj('embed-share','{isa = PBXBuildFile; fileRef = '+share_product+'; settings = {ATTRIBUTES = (RemoveHeadersOnCopy,);};}')
embed_phase = obj('embed-phase','{isa = PBXCopyFilesBuildPhase; buildActionMask = 2147483647; dstPath = ""; dstSubfolderSpec = 13; files = '+seq([embed_build])+'; name = "Embed App Extensions"; runOnlyForDeploymentPostprocessing = 0;}')
target = obj('target','{isa = PBXNativeTarget; buildConfigurationList = '+target_list+'; buildPhases = '+seq([sources_phase,frameworks_phase,resources_phase,embed_phase])+'; buildRules = (); dependencies = '+seq([share_dependency])+'; name = PiggyNative; packageProductDependencies = '+seq(dependencies)+'; productName = Piggy; productReference = '+app+'; productType = "com.apple.product-type.application";}')
group = obj('rootgroup','{isa = PBXGroup; children = '+seq(source_refs+resource_refs+[products])+'; sourceTree = "<group>";}')
project = obj('project','{isa = PBXProject; attributes = {BuildIndependentTargetsInParallel = YES; LastUpgradeCheck = 2600; TargetAttributes = {'+target+' = {CreatedOnToolsVersion = 26.0; SystemCapabilities = {com.apple.SignInWithApple = {enabled = 1;};};};};}; buildConfigurationList = '+project_list+'; compatibilityVersion = "Xcode 14.0"; developmentRegion = de; hasScannedForEncodings = 0; knownRegions = (de,en,Base,); mainGroup = '+group+'; packageReferences = '+seq(packages)+'; productRefGroup = '+products+'; projectDirPath = ""; projectRoot = ""; targets = '+seq([target,share_target])+';}')
folder = root / 'Piggy.xcodeproj'
folder.mkdir(exist_ok=True)
(folder / 'project.pbxproj').write_text('// !$*UTF8*$!\n{archiveVersion = 1; classes = {}; objectVersion = 56; objects = {\n'+'\n'.join(k+' = '+v+';' for k,v in objects.items())+'\n}; rootObject = '+project+';}\n')
schemes = folder / 'xcshareddata/xcschemes'; schemes.mkdir(parents=True, exist_ok=True)
ref = f'<BuildableReference BuildableIdentifier="primary" BlueprintIdentifier="{target}" BuildableName="Piggy.app" BlueprintName="PiggyNative" ReferencedContainer="container:Piggy.xcodeproj"/>'
(schemes / 'PiggyNative.xcscheme').write_text(f'''<?xml version="1.0" encoding="UTF-8"?>
<Scheme LastUpgradeVersion="2600" version="1.3"><BuildAction parallelizeBuildables="YES" buildImplicitDependencies="YES"><BuildActionEntries><BuildActionEntry buildForTesting="YES" buildForRunning="YES" buildForProfiling="YES" buildForArchiving="YES" buildForAnalyzing="YES">{ref}</BuildActionEntry></BuildActionEntries></BuildAction><TestAction buildConfiguration="Debug" shouldUseLaunchSchemeArgsEnv="YES"/><LaunchAction buildConfiguration="Debug" selectedDebuggerIdentifier="Xcode.DebuggerFoundation.Debugger.LLDB" selectedLauncherIdentifier="Xcode.IDEFoundation.Launcher.LLDB" launchStyle="0" useCustomWorkingDirectory="NO" ignoresPersistentStateOnLaunch="NO" debugDocumentVersioning="YES" debugServiceExtension="internal" allowLocationSimulation="YES"><BuildableProductRunnable runnableDebuggingMode="0">{ref}</BuildableProductRunnable></LaunchAction><ProfileAction buildConfiguration="Release" shouldUseLaunchSchemeArgsEnv="YES" savedToolIdentifier="" useCustomWorkingDirectory="NO" debugDocumentVersioning="YES"><BuildableProductRunnable runnableDebuggingMode="0">{ref}</BuildableProductRunnable></ProfileAction><AnalyzeAction buildConfiguration="Debug"/><ArchiveAction buildConfiguration="Release" revealArchiveInOrganizer="YES"/></Scheme>''')
print(f'Generated PiggyNative with {len(sources)} Swift source files and {len(packages)} pinned dependencies.')
