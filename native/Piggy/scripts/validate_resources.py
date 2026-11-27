#!/usr/bin/env python3
"""Check shipped assets and project inputs; never presented as an Xcode build."""
import hashlib, json, plistlib, re, subprocess
from pathlib import Path
from PIL import Image
root = Path(__file__).resolve().parents[1]
brand = root / 'Resources/Brand'
manifest = json.loads((brand / 'provenance.json').read_text())
for row in manifest['assets']:
    file = brand / row['path']
    assert hashlib.sha256(file.read_bytes()).hexdigest() == row['sha256'], row['path']
clips = json.loads((brand / '3d/app-motion-v3/manifest.json').read_text())
assert clips['verified'] and not clips['probeOnly']
assert clips['render']['width'] == clips['render']['height'] == 864
assert clips['canvasScale'] == 1.18
assert {c['id']: c['frameCount'] for c in clips['clips']} == {'idle': 90, 'scan': 72, 'celebration': 60}
for clip in clips['clips']:
    assert clip['verification']['loopSeamPass'] and clip['verification']['noShadowEdgeClipping']
    assert len(clip['frames']) == clip['frameCount']
    assert len(clip['frameDurationsMs']) == clip['frameCount']
    assert abs(sum(clip['frameDurationsMs']) - clip['durationSeconds'] * 1000) < 1
    for frame in clip['frames']:
        path = brand / '3d/app-motion-v3' / frame
        assert hashlib.sha256(path.read_bytes()).hexdigest() == clip['frameSha256'][frame]
        image = Image.open(path)
        assert image.size == (864, 864) and image.mode == 'RGBA'
        alpha = image.getchannel('A')
        assert all(alpha.crop(box).getextrema() == (0, 0) for box in [
            (0,0,864,16), (0,848,864,864), (0,0,16,864), (848,0,864,864)
        ]), 'Nontransparent canvas edge: ' + frame
for pose, clip in [('idle-happy', 'idle'), ('catch-surprised', 'scan'), ('cheer-happy', 'celebration')]:
    assert (brand / f'3d/sprites/piggy-mascot-{pose}@3x.png').read_bytes() == (brand / f'3d/app-motion-v3/{clip}/{clip}_0000.png').read_bytes()
variants = json.loads((brand / 'variants/index.json').read_text())
assert len(variants) == 17 and len({v['id'] for v in variants}) == 17
icon = Image.open(root / 'Resources/Assets.xcassets/AppIcon.appiconset/AppIcon.png')
assert icon.size == (1024,1024) and icon.mode == 'RGB'
for path in [root / 'Config/Info.plist', root / 'Config/Piggy.entitlements', root / 'Resources/PrivacyInfo.xcprivacy']:
    plistlib.loads(path.read_bytes())
project = (root / 'Piggy.xcodeproj/project.pbxproj').read_bytes()
subprocess.run(['python3',str(root / 'scripts/generate_project.py')],check=True,capture_output=True)
assert project == (root / 'Piggy.xcodeproj/project.pbxproj').read_bytes(), 'Regenerate the project after adding sources'
assert all(str(p.relative_to(root)) in project.decode() for p in (root / 'Sources').rglob('*.swift'))
print(f'PASS: {len(manifest["assets"])} asset hashes, 17 variants, 222 Retina frames, transparent borders, 3 exact timelines and identical static fallbacks, opaque icon, plists and deterministic project inputs. Apple compilation not checked.')

# The extension calls group UserDefaults and reads file metadata; its own bundle needs its manifest.
for relative in ['Resources/PrivacyInfo.xcprivacy','ShareExtension/PrivacyInfo.xcprivacy']:
    privacy = plistlib.loads((root / relative).read_bytes())
    reasons = {row['NSPrivacyAccessedAPIType']: set(row['NSPrivacyAccessedAPITypeReasons']) for row in privacy['NSPrivacyAccessedAPITypes']}
    assert '1C8F.1' in reasons['NSPrivacyAccessedAPICategoryUserDefaults'], relative
    assert {'C617.1','3B52.1'} <= reasons['NSPrivacyAccessedAPICategoryFileTimestamp'], relative
assert 'ShareExtension/PrivacyInfo.xcprivacy' in project.decode()
print('PASS: app and share extension privacy manifests declare group defaults and file metadata reasons.')
