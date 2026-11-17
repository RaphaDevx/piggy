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
def copy(relative):
    src = source / relative
    dst = target / relative
    dst.parent.mkdir(parents=True, exist_ok=True)
    shutil.copy2(src, dst)
    records.append({'path': relative, 'sha256': hashlib.sha256(dst.read_bytes()).hexdigest(), 'bytes': dst.stat().st_size})
copy('variants/index.json')
variants = json.loads((source / 'variants/index.json').read_text())
for row in variants:
    for name in ['signet@3x.png', 'logo@3x.png']:
        copy('variants/' + row['id'] + '/' + name)
for pose in ['idle-happy', 'catch-surprised', 'cheer-happy']:
    copy('3d/sprites/piggy-mascot-' + pose + '@3x.png')
manifest = json.loads((source / '3d/app-motion-v2/manifest.json').read_text())
assert manifest.get('verified') and all(c['verification']['loopSeamPass'] for c in manifest['clips'])
for name in ['idle.png', 'scan.png', 'celebration.png', 'manifest.json']:
    copy('3d/app-motion-v2/' + name)
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
