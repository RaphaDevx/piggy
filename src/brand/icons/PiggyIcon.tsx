// AUTO-GENERATED von K-Creative-Cloud/brand/piggy/tools/build.js — NICHT manuell bearbeiten.
// Quelle: brand/piggy/icons/sources.json + icons/style.json
// Neu erzeugen: bash K-Creative-Cloud/brand/piggy/build.sh --sync

import React from 'react';
import Svg, { Path, Circle, Rect, Ellipse } from 'react-native-svg';
import type { StyleProp, ViewStyle } from 'react-native';

export type PiggyIconName =
  | 'home'
  | 'receipt'
  | 'folder'
  | 'profile'
  | 'stats'
  | 'settings'
  | 'scan'
  | 'camera'
  | 'gallery'
  | 'split'
  | 'friends'
  | 'friend-add'
  | 'friend-remove'
  | 'add'
  | 'add-circle'
  | 'share'
  | 'send'
  | 'trash'
  | 'edit'
  | 'search'
  | 'filter'
  | 'swap'
  | 'check'
  | 'check-circle'
  | 'close'
  | 'close-circle'
  | 'alert'
  | 'shield-check'
  | 'chevron-back'
  | 'chevron-forward'
  | 'chevron-down'
  | 'arrow-up'
  | 'arrow-down'
  | 'card'
  | 'calendar'
  | 'qr-code'
  | 'link'
  | 'logout'
  | 'cloud'
  | 'sparkles'
  | 'document'
  | 'key'
  | 'phone'
  | 'tag'
  | 'wallet'
  | 'coin'
  | 'piggy'
  | 'piggy-bank';
export type PiggyIconMode = 'outline' | 'filled' | 'duotone';

/** Style-Definition zum Generierungszeitpunkt (brand/piggy/icons/style.json). */
export const PIGGY_ICON_STYLE = {
  name: 'piggy-soft',
  mode: 'duotone' as PiggyIconMode,
  strokeWidth: 2,
  cornerRadius: 3,
  primary: '#C77700',
  secondary: '#FFE4A8',
  onPrimary: '#FFFFFF',
} as const;

type Tag = 'path' | 'circle' | 'rect' | 'ellipse';
type Role = 'base' | 'detail' | 'dot';
/** [tag, geometrie, rolle, liegtAufBase?, dünn?] — Logik gespiegelt aus K-Creative lib/icons.js → iconElements() */
type Shape = [Tag, Record<string, string | number>, Role, (0 | 1)?, (0 | 1)?];

export const PIGGY_ICONS: Record<PiggyIconName, Shape[]> = {"home":[["path",{"d":"M6.33,8.61 L9.67,5.89 Q12,4 14.33,5.89 L17.67,8.61 Q20,10.5 20,13.5 L20,17 Q20,20 17,20 L7,20 Q4,20 4,17 L4,13.5 Q4,10.5 6.33,8.61 Z"},"base"],["path",{"d":"M12,15.5 L12,20"},"detail",1]],"receipt":[["path",{"d":"M7,3 L17,3 Q18.5,3 18.5,4.5 L18.5,19.67 Q18.5,21 17.4,20.25 L17.37,20.23 Q16.3,19.5 15.25,20.25 L15.25,20.25 Q14.2,21 13.13,20.27 L13.1,20.25 Q12,19.5 10.9,20.25 L10.87,20.27 Q9.8,21 8.75,20.25 L8.75,20.25 Q7.7,19.5 6.63,20.23 L6.6,20.25 Q5.5,21 5.5,19.67 L5.5,4.5 Q5.5,3 7,3 Z"},"base"],["path",{"d":"M9,8 L15,8"},"detail",1],["path",{"d":"M9,12 L15,12"},"detail",1],["path",{"d":"M9,16 L12.5,16"},"detail",1]],"folder":[["path",{"d":"M5.5,5.5 L7.9,5.5 Q9.5,5.5 10.5,6.75 L10.5,6.75 Q11.5,8 13.1,8 L18.5,8 Q21,8 21,10.5 L21,16.5 Q21,19 18.5,19 L5.5,19 Q3,19 3,16.5 L3,8 Q3,5.5 5.5,5.5 Z"},"base"],["path",{"d":"M3.5,11.5 L20.5,11.5"},"detail",1]],"profile":[["circle",{"cx":12,"cy":8,"r":3.8},"base"],["path",{"d":"M4.5,20.5 C4.5,16.4 7.9,13.5 12,13.5 C16.1,13.5 19.5,16.4 19.5,20.5 Z"},"base"]],"stats":[["rect",{"x":3.5,"y":12,"width":4.5,"height":8.5,"rx":2.25,"ry":2.25},"base"],["rect",{"x":9.75,"y":7,"width":4.5,"height":13.5,"rx":2.25,"ry":2.25},"base"],["rect",{"x":16,"y":3.5,"width":4.5,"height":17,"rx":2.25,"ry":2.25},"base"]],"settings":[["path",{"d":"M11.37,2.64 L12.63,2.64 Q13.63,2.64 13.55,3.64 L13.53,3.95 Q13.45,4.95 14.37,5.33 L15.04,5.61 Q15.96,5.99 16.61,5.23 L16.81,4.99 Q17.46,4.23 18.17,4.93 L19.07,5.83 Q19.77,6.54 19.01,7.19 L18.77,7.39 Q18.01,8.04 18.39,8.96 L18.67,9.63 Q19.05,10.55 20.05,10.47 L20.36,10.45 Q21.36,10.37 21.36,11.37 L21.36,12.63 Q21.36,13.63 20.36,13.55 L20.05,13.53 Q19.05,13.45 18.67,14.37 L18.39,15.04 Q18.01,15.96 18.77,16.61 L19.01,16.81 Q19.77,17.46 19.07,18.17 L18.17,19.07 Q17.46,19.77 16.81,19.01 L16.61,18.77 Q15.96,18.01 15.04,18.39 L14.37,18.67 Q13.45,19.05 13.53,20.05 L13.55,20.36 Q13.63,21.36 12.63,21.36 L11.37,21.36 Q10.37,21.36 10.45,20.36 L10.47,20.05 Q10.55,19.05 9.63,18.67 L8.96,18.39 Q8.04,18.01 7.39,18.77 L7.19,19.01 Q6.54,19.77 5.83,19.07 L4.93,18.17 Q4.23,17.46 4.99,16.81 L5.23,16.61 Q5.99,15.96 5.61,15.04 L5.33,14.37 Q4.95,13.45 3.95,13.53 L3.64,13.55 Q2.64,13.63 2.64,12.63 L2.64,11.37 Q2.64,10.37 3.64,10.45 L3.95,10.47 Q4.95,10.55 5.33,9.63 L5.61,8.96 Q5.99,8.04 5.23,7.39 L4.99,7.19 Q4.23,6.54 4.93,5.83 L5.83,4.93 Q6.54,4.23 7.19,4.99 L7.39,5.23 Q8.04,5.99 8.96,5.61 L9.63,5.33 Q10.55,4.95 10.47,3.95 L10.45,3.64 Q10.37,2.64 11.37,2.64 Z"},"base"],["circle",{"cx":12,"cy":12,"r":2.8},"detail",1]],"scan":[["path",{"d":"M3.5,8.5 V6.5 A3,3 0 0 1 6.5,3.5 H8.5"},"detail"],["path",{"d":"M15.5,3.5 H17.5 A3,3 0 0 1 20.5,6.5 V8.5"},"detail"],["path",{"d":"M20.5,15.5 V17.5 A3,3 0 0 1 17.5,20.5 H15.5"},"detail"],["path",{"d":"M8.5,20.5 H6.5 A3,3 0 0 1 3.5,17.5 V15.5"},"detail"],["rect",{"x":8,"y":7,"width":8,"height":10,"rx":1.5,"ry":1.5},"base"],["path",{"d":"M6,12 L18,12"},"detail"]],"camera":[["path",{"d":"M5.25,8 L5.86,8 Q7.5,8 8.35,6.6 L8.35,6.6 Q9.2,5.2 10.84,5.2 L13.16,5.2 Q14.8,5.2 15.65,6.6 L15.65,6.6 Q16.5,8 18.14,8 L18.75,8 Q21,8 21,10.25 L21,17 Q21,19.5 18.5,19.5 L5.5,19.5 Q3,19.5 3,17 L3,10.25 Q3,8 5.25,8 Z"},"base"],["circle",{"cx":12,"cy":13.5,"r":3.4},"detail",1],["circle",{"cx":17.6,"cy":10.8,"r":0.9},"dot",1]],"gallery":[["rect",{"x":3,"y":4.5,"width":18,"height":15,"rx":3,"ry":3},"base"],["path",{"d":"M4,17 L8.15,12.85 Q9,12 9.85,12.85 L12.15,15.15 Q13,16 13.85,15.15 L14.65,14.35 Q15.5,13.5 16.35,14.35 L20,18"},"detail",1],["circle",{"cx":15.5,"cy":9,"r":1.7},"dot",1]],"split":[["path",{"d":"M10.8,3.6 A8.5,8.5 0 0 0 10.8,20.4 Z"},"base"],["path",{"d":"M13.2,3.6 A8.5,8.5 0 0 1 13.2,20.4 Z"},"base"],["circle",{"cx":7.3,"cy":12,"r":1.2},"dot",1],["circle",{"cx":16.7,"cy":12,"r":1.2},"dot",1]],"friends":[["circle",{"cx":16.5,"cy":8.5,"r":2.8},"base"],["path",{"d":"M14.2,19.5 C14.2,16 15.3,13.6 17,13.6 C19.4,13.6 21.5,15.8 21.5,19.5 Z"},"base"],["circle",{"cx":9,"cy":8.5,"r":3.5},"base"],["path",{"d":"M2.5,20 C2.5,16.2 5.4,13.5 9,13.5 C12.6,13.5 15.5,16.2 15.5,20 Z"},"base"]],"friend-add":[["circle",{"cx":9.5,"cy":8,"r":3.6},"base"],["path",{"d":"M2.5,20 C2.5,16.2 5.6,13.5 9.5,13.5 C13.4,13.5 16.5,16.2 16.5,20 Z"},"base"],["path",{"d":"M19,6 L19,12"},"detail"],["path",{"d":"M16,9 L22,9"},"detail"]],"friend-remove":[["circle",{"cx":9.5,"cy":8,"r":3.6},"base"],["path",{"d":"M2.5,20 C2.5,16.2 5.6,13.5 9.5,13.5 C13.4,13.5 16.5,16.2 16.5,20 Z"},"base"],["path",{"d":"M16,9 L22,9"},"detail"]],"add":[["path",{"d":"M12,5 L12,19"},"detail"],["path",{"d":"M5,12 L19,12"},"detail"]],"add-circle":[["circle",{"cx":12,"cy":12,"r":9},"base"],["path",{"d":"M12,8 L12,16"},"detail",1],["path",{"d":"M8,12 L16,12"},"detail",1]],"share":[["rect",{"x":5,"y":10,"width":14,"height":11,"rx":3,"ry":3},"base"],["path",{"d":"M12,3.5 L12,9"},"detail"],["path",{"d":"M12,10.5 L12,14.5"},"detail",1],["path",{"d":"M8.8,6.5 L11.43,3.87 Q12,3.3 12.57,3.87 L15.2,6.5"},"detail"]],"send":[["path",{"d":"M4.87,10.39 L19.13,4.11 Q20.5,3.5 19.93,4.89 L14.07,19.11 Q13.5,20.5 13.03,19.08 L11.47,14.42 Q11,13 9.55,12.61 L4.95,11.39 Q3.5,11 4.87,10.39 Z"},"base"],["path",{"d":"M11,13 L16,8"},"detail",1]],"trash":[["path",{"d":"M4,6.5 L20,6.5"},"detail"],["path",{"d":"M9,6.5 V5 A1.5,1.5 0 0 1 10.5,3.5 H13.5 A1.5,1.5 0 0 1 15,5 V6.5"},"detail"],["path",{"d":"M8,6.5 L16,6.5 Q18,6.5 17.86,8.49 L17.14,18.51 Q17,20.5 15,20.5 L9,20.5 Q7,20.5 6.86,18.51 L6.14,8.49 Q6,6.5 8,6.5 Z"},"base"],["path",{"d":"M10,10.5 L10,16.5"},"detail",1],["path",{"d":"M14,10.5 L14,16.5"},"detail",1]],"edit":[["path",{"d":"M15.85,5.35 L18.65,8.15 Q19.5,9 18.65,9.85 L9.85,18.65 Q9,19.5 7.8,19.5 L5.7,19.5 Q4.5,19.5 4.5,18.3 L4.5,16.2 Q4.5,15 5.35,14.15 L14.15,5.35 Q15,4.5 15.85,5.35 Z"},"base"],["path",{"d":"M13,6.5 L17.5,11"},"detail",1]],"search":[["circle",{"cx":10.5,"cy":10.5,"r":6.5},"base"],["path",{"d":"M15.5,15.5 L20,20"},"detail"]],"filter":[["path",{"d":"M5.5,5 L18.5,5 Q20,5 19.06,6.17 L14.94,11.33 Q14,12.5 14,14 L14,17.5 Q14,19 12.6,18.47 L11.4,18.03 Q10,17.5 10,16 L10,14 Q10,12.5 9.06,11.33 L4.94,6.17 Q4,5 5.5,5 Z"},"base"]],"swap":[["path",{"d":"M8,19 L8,5"},"detail"],["path",{"d":"M4.5,8.5 L7.43,5.57 Q8,5 8.57,5.57 L11.5,8.5"},"detail"],["path",{"d":"M16,5 L16,19"},"detail"],["path",{"d":"M12.5,15.5 L15.43,18.43 Q16,19 16.57,18.43 L19.5,15.5"},"detail"]],"check":[["path",{"d":"M5,12.5 L9.29,16.79 Q10,17.5 10.65,16.74 L19,7"},"detail"]],"check-circle":[["circle",{"cx":12,"cy":12,"r":9},"base"],["path",{"d":"M8,12.3 L10.43,14.73 Q11,15.3 11.52,14.69 L16.2,9.2"},"detail",1]],"close":[["path",{"d":"M6.5,6.5 L17.5,17.5"},"detail"],["path",{"d":"M17.5,6.5 L6.5,17.5"},"detail"]],"close-circle":[["circle",{"cx":12,"cy":12,"r":9},"base"],["path",{"d":"M9,9 L15,15"},"detail",1],["path",{"d":"M15,9 L9,15"},"detail",1]],"alert":[["circle",{"cx":12,"cy":12,"r":9},"base"],["path",{"d":"M12,7.5 L12,12.5"},"detail",1],["circle",{"cx":12,"cy":16.2,"r":1.3},"dot",1]],"shield-check":[["path",{"d":"M12,3 L19,6 V11.5 C19,15.8 16,19.2 12,20.8 C8,19.2 5,15.8 5,11.5 V6 Z"},"base"],["path",{"d":"M8.8,12 L10.63,13.83 Q11.2,14.4 11.74,13.81 L15.4,9.8"},"detail",1]],"chevron-back":[["path",{"d":"M15,5.5 L9.21,11.29 Q8.5,12 9.21,12.71 L15,18.5"},"detail"]],"chevron-forward":[["path",{"d":"M9,5.5 L14.79,11.29 Q15.5,12 14.79,12.71 L9,18.5"},"detail"]],"chevron-down":[["path",{"d":"M5.5,9 L11.29,14.79 Q12,15.5 12.71,14.79 L18.5,9"},"detail"]],"arrow-up":[["path",{"d":"M12,19 L12,5"},"detail"],["path",{"d":"M6.5,10.5 L11.29,5.71 Q12,5 12.71,5.71 L17.5,10.5"},"detail"]],"arrow-down":[["path",{"d":"M12,5 L12,19"},"detail"],["path",{"d":"M6.5,13.5 L11.29,18.29 Q12,19 12.71,18.29 L17.5,13.5"},"detail"]],"card":[["rect",{"x":2.5,"y":5.5,"width":19,"height":13,"rx":3,"ry":3},"base"],["path",{"d":"M3,10 L21,10"},"detail",1],["path",{"d":"M6.5,14.5 L10,14.5"},"detail",1]],"calendar":[["rect",{"x":3.5,"y":5.5,"width":17,"height":15,"rx":3,"ry":3},"base"],["path",{"d":"M8,3.5 L8,7"},"detail"],["path",{"d":"M16,3.5 L16,7"},"detail"],["path",{"d":"M4,10.5 L20,10.5"},"detail",1],["circle",{"cx":8.5,"cy":14.5,"r":1.1},"dot",1],["circle",{"cx":12,"cy":14.5,"r":1.1},"dot",1],["circle",{"cx":15.5,"cy":14.5,"r":1.1},"dot",1]],"qr-code":[["rect",{"x":3.5,"y":3.5,"width":7,"height":7,"rx":2,"ry":2},"base"],["rect",{"x":13.5,"y":3.5,"width":7,"height":7,"rx":2,"ry":2},"base"],["rect",{"x":3.5,"y":13.5,"width":7,"height":7,"rx":2,"ry":2},"base"],["rect",{"x":13.5,"y":13.5,"width":3,"height":3,"rx":0.8,"ry":0.8},"dot"],["rect",{"x":17.5,"y":17.5,"width":3,"height":3,"rx":0.8,"ry":0.8},"dot"],["rect",{"x":17.5,"y":13.5,"width":3,"height":2,"rx":0.8,"ry":0.8},"dot"],["rect",{"x":13.5,"y":18.5,"width":2,"height":2,"rx":0.8,"ry":0.8},"dot"]],"link":[["path",{"d":"M10,14 A4,4 0 0 0 15.66,14 L18.5,11.16 A4,4 0 0 0 12.84,5.5 L11.5,6.84"},"detail"],["path",{"d":"M14,10 A4,4 0 0 0 8.34,10 L5.5,12.84 A4,4 0 0 0 11.16,18.5 L12.5,17.16"},"detail"]],"logout":[["path",{"d":"M10,4 H7 A2.5,2.5 0 0 0 4.5,6.5 V17.5 A2.5,2.5 0 0 0 7,20 H10"},"detail"],["path",{"d":"M10,12 L20,12"},"detail"],["path",{"d":"M16.5,8.5 L19.43,11.43 Q20,12 19.43,12.57 L16.5,15.5"},"detail"]],"cloud":[["path",{"d":"M7,18.5 A4.2,4.2 0 0 1 6.5,10.1 A5.6,5.6 0 0 1 17.3,8.6 A4.9,4.9 0 0 1 17,18.5 Z"},"base"]],"sparkles":[["path",{"d":"M10.32,5.45 L11.59,9.14 Q11.91,10.09 12.86,10.41 L16.55,11.68 Q17.5,12 16.55,12.32 L12.86,13.59 Q11.91,13.91 11.59,14.86 L10.32,18.55 Q10,19.5 9.68,18.55 L8.41,14.86 Q8.09,13.91 7.14,13.59 L3.45,12.32 Q2.5,12 3.45,11.68 L7.14,10.41 Q8.09,10.09 8.41,9.14 L9.68,5.45 Q10,4.5 10.32,5.45 Z"},"base"],["path",{"d":"M18.37,3.73 L18.54,4.16 Q18.91,5.09 19.84,5.46 L20.27,5.63 Q21.2,6 20.27,6.37 L19.84,6.54 Q18.91,6.91 18.54,7.84 L18.37,8.27 Q18,9.2 17.63,8.27 L17.46,7.84 Q17.09,6.91 16.16,6.54 L15.73,6.37 Q14.8,6 15.73,5.63 L16.16,5.46 Q17.09,5.09 17.46,4.16 L17.63,3.73 Q18,2.8 18.37,3.73 Z"},"base"]],"document":[["path",{"d":"M8,3 L12,3 Q14,3 15.41,4.41 L17.59,6.59 Q19,8 19,10 L19,19 Q19,21 17,21 L8,21 Q6,21 6,19 L6,5 Q6,3 8,3 Z"},"base"],["path",{"d":"M13.5,3.5 V8.5 H18.5"},"detail",1],["path",{"d":"M9.5,13 L15.5,13"},"detail",1],["path",{"d":"M9.5,16.5 L13.5,16.5"},"detail",1]],"key":[["circle",{"cx":8,"cy":12,"r":4.2},"base"],["path",{"d":"M12.2,12 L20.5,12"},"detail"],["path",{"d":"M17,12 L17,15"},"detail"],["path",{"d":"M20.5,12 L20.5,14.5"},"detail"],["circle",{"cx":8,"cy":12,"r":1.2},"dot",1]],"phone":[["rect",{"x":6.5,"y":2.5,"width":11,"height":19,"rx":3,"ry":3},"base"],["path",{"d":"M10.5,18 L13.5,18"},"detail",1]],"tag":[["path",{"d":"M5.48,4.24 L9.02,3.76 Q11,3.5 12.41,4.91 L19.09,11.59 Q20.5,13 19.09,14.41 L14.41,19.09 Q13,20.5 11.59,19.09 L4.91,12.41 Q3.5,11 3.5,9 L3.5,6.5 Q3.5,4.5 5.48,4.24 Z"},"base"],["circle",{"cx":8,"cy":8,"r":1.6},"dot",1]],"wallet":[["rect",{"x":3,"y":6,"width":18,"height":14,"rx":3,"ry":3},"base"],["path",{"d":"M5,6 L15.5,3.5 A1.5,1.5 0 0 1 17.3,5 V6"},"detail"],["rect",{"x":14.5,"y":11,"width":6.5,"height":4.5,"rx":2,"ry":2},"detail",1],["circle",{"cx":17,"cy":13.25,"r":0.9},"dot",1]],"coin":[["circle",{"cx":12,"cy":12,"r":9},"base"],["circle",{"cx":12,"cy":12,"r":5.5},"detail",1,1],["path",{"d":"M12,9.5 L12,14.5"},"detail",1]],"piggy":[["path",{"d":"M4.33,7.81 L4.67,4.79 Q4.8,3.6 5.82,4.23 L7.98,5.57 Q9,6.2 7.96,6.8 L5.24,8.4 Q4.2,9 4.33,7.81 Z"},"base"],["path",{"d":"M19.67,7.81 L19.33,4.79 Q19.2,3.6 18.18,4.23 L16.02,5.57 Q15,6.2 16.04,6.8 L18.76,8.4 Q19.8,9 19.67,7.81 Z"},"base"],["ellipse",{"cx":12,"cy":13,"rx":8.5,"ry":7.5},"base"],["path",{"d":"M10.3,7.4 L13.7,7.4"},"detail",1],["ellipse",{"cx":12,"cy":15.3,"rx":3.1,"ry":2.2},"detail",1,1],["circle",{"cx":8.6,"cy":11.6,"r":1.15},"dot",1],["circle",{"cx":15.4,"cy":11.6,"r":1.15},"dot",1]],"piggy-bank":[["path",{"d":"M4,12.5 C4,8.4 7.6,5.5 12,5.5 C15,5.5 17.4,6.7 18.6,8.6 L21,8 L20.2,11.4 C20.6,12.3 20.5,13.6 20,14.7 L19,15.5 V19 H16.2 L15.6,17.4 C13.4,18 11,18 8.6,17.4 L8,19 H5.4 V15.6 C4.5,14.8 4,13.7 4,12.5 Z"},"base"],["path",{"d":"M10,8.3 L13.5,8.3"},"detail",1],["circle",{"cx":16.5,"cy":11,"r":1},"dot",1]]};

export const PIGGY_ICON_NAMES = Object.keys(PIGGY_ICONS) as PiggyIconName[];

export type PiggyIconProps = {
  name: PiggyIconName;
  size?: number;
  mode?: PiggyIconMode;
  /** Primärfarbe (Kontur/Füllung). Default: component.icon.primary */
  color?: string;
  /** Duotone-Füllung. Default: component.icon.secondary */
  secondaryColor?: string;
  /** Aussparungen im filled-Modus. Default: component.icon.onPrimary */
  onColor?: string;
  strokeWidth?: number;
  style?: StyleProp<ViewStyle>;
};

const TAGS = { path: Path, circle: Circle, rect: Rect, ellipse: Ellipse } as const;

export function PiggyIcon({
  name, size = 24, mode = PIGGY_ICON_STYLE.mode,
  color = PIGGY_ICON_STYLE.primary, secondaryColor = PIGGY_ICON_STYLE.secondary, onColor = PIGGY_ICON_STYLE.onPrimary,
  strokeWidth = PIGGY_ICON_STYLE.strokeWidth, style,
}: PiggyIconProps) {
  const shapes = PIGGY_ICONS[name];
  if (!shapes) return null;
  return (
    <Svg width={size} height={size} viewBox="0 0 24 24" style={style}>
      {shapes.map(([tag, g, role, inside, thin], i) => {
        const El = TAGS[tag] as React.ComponentType<any>;
        const sw = thin ? strokeWidth * 0.8 : strokeWidth;
        const stroke = { strokeWidth: sw, strokeLinecap: 'round' as const, strokeLinejoin: 'round' as const };
        let p: Record<string, unknown>;
        if (role === 'base') {
          p = mode === 'outline' ? { fill: 'none', stroke: color, ...stroke }
            : mode === 'filled' ? { fill: color, stroke: color, ...stroke }
            : { fill: secondaryColor, stroke: color, ...stroke };
        } else if (role === 'detail') {
          p = { fill: 'none', stroke: mode === 'filled' && inside ? onColor : color, ...stroke };
        } else {
          p = { fill: mode === 'filled' && inside ? onColor : color };
        }
        return <El key={i} {...g} {...p} />;
      })}
    </Svg>
  );
}

export default PiggyIcon;
