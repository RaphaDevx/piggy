#!/usr/bin/env python3
"""Copy only runtime brand exports, with hashes and production provenance."""
import argparse, hashlib, json, shutil
from PIL import Image
from pathlib import Path
p = argparse.ArgumentParser()
p.add_argument('--brand', type=Path, default=Path('/storage/Public/K-Dev/Projekte/K-Creative-Cloud/07_Brands/Piggy'))
a = p.parse_args()
root = Path(__file__).resolve().parents[1]
source = a.brand / 'dist'
target = root / 'Resources/Brand'
records = []
def copy(relative, destination=None):
    src = source / relative
    runtime_path = destination or relative
    dst = target / runtime_path
    dst.parent.mkdir(parents=True, exist_ok=True)
    shutil.copy2(src, dst)
    records.append({'path': runtime_path, 'sourcePath': relative, 'sha256': hashlib.sha256(dst.read_bytes()).hexdigest(), 'bytes': dst.stat().st_size})
copy('variants/index.json')
variants = json.loads((source / 'variants/index.json').read_text())
for row in variants:
    for name in ['signet@3x.png', 'logo@3x.png']:
        copy('variants/' + row['id'] + '/' + name)
motion = '3d/app-motion-v3/'
manifest = json.loads((source / motion / 'manifest.json').read_text())
assert manifest.get('verified') and not manifest.get('probeOnly')
assert manifest['render']['width'] == manifest['render']['height'] == 864
assert manifest['canvasScale'] == 1.18
for clip in manifest['clips']:
    assert clip['verification']['loopSeamPass'] and clip['verification']['noShadowEdgeClipping']
    assert len(clip['frames']) == clip['frameCount']
    for frame in clip['frames']:
        assert hashlib.sha256((source / motion / frame).read_bytes()).hexdigest() == clip['frameSha256'][frame]
        copy(motion + frame)
copy(motion + 'manifest.json')
# Reduced Motion and error fallbacks use the exact same camera, resolution and
# first pose as the animation, avoiding a flash of a differently framed sprite.
for pose, clip in [('idle-happy', 'idle'), ('catch-surprised', 'scan'), ('cheer-happy', 'celebration')]:
    copy(motion + clip + '/' + clip + '_0000.png', '3d/sprites/piggy-mascot-' + pose + '@3x.png')
(target / 'provenance.json').write_text(json.dumps({'source': str(source), 'assets': records}, indent=2) + '\n')
icons = root / 'Resources/Assets.xcassets/AppIcon.appiconset'
icons.mkdir(parents=True, exist_ok=True)
# This is an existing opaque, authored App Store icon; no mascot redesign.
icon = Image.open(source / 'variants/base/piggy-appicon-base-1024.png').convert('RGBA')
assert icon.getchannel('A').getextrema() == (255, 255), 'App icon must be opaque'
icon.convert('RGB').save(icons / 'AppIcon.png')
(icons / 'Contents.json').write_text(json.dumps({'images': [{'filename': 'AppIcon.png', 'idiom': 'universal', 'platform': 'ios', 'size': '1024x1024'}], 'info': {'author': 'xcode', 'version': 1}}, indent=2) + '\n')
(icons.parent / 'Contents.json').write_text('{"info":{"author":"xcode","version":1}}\n')
print(f'Copied {len(records)} runtime assets from {len(variants)} variants; {sum(x["bytes"] for x in records):,} bytes.')
