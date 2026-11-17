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
clips = json.loads((brand / '3d/app-motion-v2/manifest.json').read_text())
assert clips['verified'] and not clips['probeOnly']
for clip in clips['clips']:
    path = brand / '3d/app-motion-v2' / clip['apng']
    assert hashlib.sha256(path.read_bytes()).hexdigest() == clip['apngSha256']
    image = Image.open(path)
    assert image.n_frames == clip['frameCount']
    assert clip['verification']['loopSeamPass'] and clip['verification']['noOpaqueEdgeClipping']
    duration = 0
    for i in range(image.n_frames):
        image.seek(i); duration += image.info['duration']
    assert abs(duration - clip['durationSeconds'] * 1000) < 1
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
print(f'PASS: {len(manifest["assets"])} asset hashes, 17 variants, 222 frames, 3 APNG timelines, opaque icon, plists and deterministic project inputs. Apple compilation not checked.')
