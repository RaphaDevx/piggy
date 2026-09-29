// AUTO-GENERATED von K-Creative-Cloud/brand/piggy/tools/build.js — NICHT manuell bearbeiten.
// Quelle: brand/piggy/3d/build_piggy.py (Blender)
// Neu erzeugen: bash K-Creative-Cloud/brand/piggy/build.sh --sync

import type { ImageSourcePropType } from 'react-native';

/** Posen/Ausdrücke als transparente Sprites, Basisgrösse 256 pt (@1x/@2x/@3x). Schlüssel: <pose>-<ausdruck>. */
export const MASCOT_SPRITES = {
  'catch-surprised': require('./piggy-mascot-catch-surprised.png') as ImageSourcePropType,
  'cheer-happy': require('./piggy-mascot-cheer-happy.png') as ImageSourcePropType,
  'idle-happy': require('./piggy-mascot-idle-happy.png') as ImageSourcePropType,
  'idle-neutral': require('./piggy-mascot-idle-neutral.png') as ImageSourcePropType,
  'idle-sad': require('./piggy-mascot-idle-sad.png') as ImageSourcePropType,
  'idle-surprised': require('./piggy-mascot-idle-surprised.png') as ImageSourcePropType,
  'idle-wink': require('./piggy-mascot-idle-wink.png') as ImageSourcePropType,
} as const;
export type MascotSprite = keyof typeof MASCOT_SPRITES;
export const MASCOT_SPRITE_SIZE = 256;

/**
 * 3D-Modell: ./piggy.glb (glTF 2.0, Rig + Shape Keys + Aktionen pose_idle/pose_cheer/pose_catch/anim_idle).
 * Bewusst NICHT per require() eingebunden: Metro kennt .glb erst nach assetExts-Erweiterung (siehe INTEGRATION.md).
 * Morph Targets: eye.* closed|wide|sad · brow.* up|sad|happy|angry · mouth open|O|frown|smirk · tongue open|O
 */
export const MASCOT_GLB_PATH = 'src/brand/mascot/piggy.glb';
