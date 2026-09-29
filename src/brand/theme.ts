// AUTO-GENERATED von K-Creative-Cloud/brand/piggy/tools/build.js — NICHT manuell bearbeiten.
// Quelle: brand/tokens/piggy.json
// Neu erzeugen: bash K-Creative-Cloud/brand/piggy/build.sh --sync

/** Schicht 1 — reine Werte (Farbrampen, 4pt-Abstände, Radien, Modular Scale 16×1.25^n). */
export const primitive = {
  "color": {
    "white": "#FFFFFF",
    "sand": {
      "50": "#FAFAF9",
      "100": "#F5F3EF",
      "200": "#EDE9E3",
      "300": "#D9D3CA"
    },
    "gray": {
      "400": "#9CA3AF",
      "500": "#6B7280",
      "600": "#4B5563",
      "900": "#1A1A1A"
    },
    "pink": {
      "50": "#FFF0F4",
      "100": "#FFDCE6",
      "200": "#FFCAD8",
      "300": "#FFB8C6",
      "400": "#FF9DB6",
      "500": "#F57C9D",
      "600": "#E0587F",
      "700": "#A8325A",
      "800": "#7E2244"
    },
    "gold": {
      "50": "#FFF5E6",
      "100": "#FFE4A8",
      "300": "#FFC247",
      "500": "#FF9F0A",
      "600": "#C77700",
      "700": "#9A5700"
    },
    "cocoa": {
      "700": "#6B3A4A",
      "900": "#3A1E2A"
    },
    "green": {
      "500": "#34C759",
      "700": "#1E7B37"
    },
    "red": {
      "500": "#FF3B30",
      "700": "#C4221A"
    },
    "swiss": {
      "red": "#DA291C"
    },
    "sky": {
      "200": "#CDEBFF",
      "400": "#6CC3FF",
      "600": "#2B8AD6"
    },
    "mint": {
      "300": "#9FE3C1",
      "500": "#3CB88A"
    },
    "violet": {
      "400": "#A98BFF",
      "700": "#5B3FB0"
    },
    "leaf": {
      "400": "#F2A33A",
      "600": "#D9622B",
      "800": "#9C3A1E"
    }
  },
  "space": {
    "0": 0,
    "1": 4,
    "2": 8,
    "3": 12,
    "4": 16,
    "5": 20,
    "6": 24,
    "8": 32,
    "10": 40,
    "12": 48,
    "16": 64
  },
  "radius": {
    "4": 4,
    "8": 8,
    "12": 12,
    "16": 16,
    "20": 20,
    "24": 24,
    "32": 32,
    "full": 9999
  },
  "type": {
    "base": 16,
    "ratio": 1.25,
    "ratioName": "Major Third",
    "scale": {
      "0": {
        "size": 16,
        "lineHeight": 24
      },
      "1": {
        "size": 20,
        "lineHeight": 28
      },
      "2": {
        "size": 25,
        "lineHeight": 32
      },
      "3": {
        "size": 31,
        "lineHeight": 40
      },
      "4": {
        "size": 39,
        "lineHeight": 48
      },
      "5": {
        "size": 49,
        "lineHeight": 56
      },
      "-2": {
        "size": 10,
        "lineHeight": 16
      },
      "-1": {
        "size": 13,
        "lineHeight": 20
      }
    },
    "weight": {
      "regular": "400",
      "semibold": "600",
      "bold": "700",
      "heavy": "800"
    }
  },
  "size": {
    "16": 16,
    "24": 24,
    "32": 32,
    "40": 40,
    "48": 48,
    "56": 56,
    "64": 64
  }
} as const;

/** Schicht 2 — Bedeutung (bg/text/brand/icon/status/border/mascot). Screens sollten nur diese Ebene nutzen. */
export const semantic = {
  "color": {
    "bg": {
      "canvas": "#FAFAF9",
      "surface": "#FFFFFF",
      "subtle": "#F5F3EF",
      "accent": "#FFF5E6",
      "brand": "#FFF0F4"
    },
    "text": {
      "primary": "#1A1A1A",
      "secondary": "#6B7280",
      "tertiary": "#9CA3AF",
      "strong": "#4B5563",
      "brand": "#9A5700",
      "brandPink": "#A8325A",
      "onBrand": "#1A1A1A",
      "onDark": "#FFFFFF"
    },
    "brand": {
      "primary": "#FF9F0A",
      "primarySoft": "#FFE4A8",
      "primaryStrong": "#C77700",
      "secondary": "#FFB8C6",
      "secondarySoft": "#FFDCE6",
      "secondaryStrong": "#A8325A"
    },
    "icon": {
      "active": "#C77700",
      "muted": "#6B7280",
      "default": "#1A1A1A",
      "soft": "#FFE4A8",
      "onFill": "#FFFFFF"
    },
    "status": {
      "success": "#34C759",
      "successText": "#1E7B37",
      "error": "#FF3B30",
      "errorText": "#C4221A",
      "warning": "#FF9F0A",
      "warningText": "#9A5700"
    },
    "border": {
      "default": "#EDE9E3",
      "subtle": "#F5F3EF"
    },
    "mascot": {
      "skin": "#FF9DB6",
      "skinLight": "#FFCAD8",
      "skinShade": "#F57C9D",
      "snout": "#FFB8C6",
      "nostril": "#E0587F",
      "earInner": "#F57C9D",
      "cheek": "#E0587F",
      "hoof": "#F57C9D",
      "eye": "#3A1E2A",
      "eyeHighlight": "#FFFFFF",
      "mouth": "#3A1E2A",
      "tongue": "#E0587F",
      "slot": "#6B3A4A",
      "coin": "#FFC247",
      "coinShade": "#FF9F0A"
    },
    "accessory": {
      "red": "#DA291C",
      "white": "#FFFFFF",
      "dark": "#3A1E2A",
      "gold": "#FFC247",
      "blue": "#6CC3FF",
      "blueDark": "#2B8AD6",
      "mint": "#9FE3C1",
      "green": "#3CB88A",
      "violet": "#A98BFF",
      "violetDark": "#5B3FB0",
      "orange": "#F2A33A",
      "rust": "#D9622B",
      "brown": "#9C3A1E",
      "pink": "#F57C9D"
    }
  },
  "space": {
    "inset": {
      "xs": 4,
      "sm": 8,
      "md": 16,
      "lg": 24
    },
    "stack": {
      "xs": 4,
      "sm": 8,
      "md": 16,
      "lg": 24,
      "xl": 32,
      "section": 48,
      "page": 64
    },
    "gutter": 24
  },
  "radius": {
    "control": 12,
    "card": 24,
    "sheet": 32,
    "pill": 9999
  },
  "font": {
    "caption": {
      "size": 10,
      "lineHeight": 16
    },
    "footnote": {
      "size": 13,
      "lineHeight": 20
    },
    "body": {
      "size": 16,
      "lineHeight": 24
    },
    "title3": {
      "size": 20,
      "lineHeight": 28
    },
    "title2": {
      "size": 25,
      "lineHeight": 32
    },
    "title1": {
      "size": 31,
      "lineHeight": 40
    },
    "display": {
      "size": 39,
      "lineHeight": 48
    },
    "hero": {
      "size": 49,
      "lineHeight": 56
    }
  }
} as const;

/** Schicht 3 — Komponenten (tabBar, button, card, icon, mascot, logo, appIcon, splash). */
export const component = {
  "tabBar": {
    "bg": "#FFFFFF",
    "border": "#EDE9E3",
    "iconActive": "#C77700",
    "iconInactive": "#6B7280",
    "labelActive": "#9A5700",
    "labelInactive": "#6B7280",
    "iconSize": 24,
    "paddingTop": 8,
    "scanFab": {
      "bg": "#FF9F0A",
      "size": 64,
      "radius": 20
    }
  },
  "button": {
    "primary": {
      "bg": "#FF9F0A",
      "text": "#1A1A1A",
      "radius": 12,
      "paddingY": 12,
      "paddingX": 24,
      "minHeight": 48
    },
    "secondary": {
      "bg": "#FFDCE6",
      "text": "#A8325A",
      "radius": 12,
      "paddingY": 12,
      "paddingX": 24,
      "minHeight": 48
    }
  },
  "card": {
    "bg": "#FFFFFF",
    "radius": 24,
    "padding": 24,
    "gap": 16
  },
  "icon": {
    "size": 24,
    "primary": "#C77700",
    "secondary": "#FFE4A8",
    "onPrimary": "#FFFFFF"
  },
  "mascot": {
    "skin": "#FF9DB6",
    "skinLight": "#FFCAD8",
    "skinShade": "#F57C9D",
    "snout": "#FFB8C6",
    "nostril": "#E0587F",
    "earInner": "#F57C9D",
    "cheek": "#E0587F",
    "hoof": "#F57C9D",
    "eye": "#3A1E2A",
    "eyeHighlight": "#FFFFFF",
    "mouth": "#3A1E2A",
    "tongue": "#E0587F",
    "slot": "#6B3A4A",
    "coin": "#FFC247",
    "coinShade": "#FF9F0A"
  },
  "logo": {
    "wordmark": "#A8325A",
    "wordmarkDepth": "#7E2244",
    "iDot": "#FFC247",
    "iDotShade": "#FF9F0A"
  },
  "appIcon": {
    "bgFrom": "#FFE4A8",
    "bgTo": "#FFC247",
    "bgRim": "#FF9F0A"
  },
  "splash": {
    "bg": "#FAFAF9",
    "wordmark": "#A8325A",
    "wordmarkDepth": "#7E2244"
  }
} as const;

/**
 * Legacy-Aliase — identisch zu src/constants/design.ts (C/R/S). Ermöglicht einen schrittweisen Umstieg:
 *   import { C } from '@/constants/design'  →  import { legacy } from '@/brand/theme'; const { C } = legacy;
 */
export const legacy = {
  C: {
  "bg": "#FAFAF9",
  "bgCard": "#FFFFFF",
  "bgSoft": "#F5F3EF",
  "bgAccent": "#FFF5E6",
  "bgPink": "#FFF0F4",
  "textPrimary": "#1A1A1A",
  "textSecondary": "#6B7280",
  "textTertiary": "#9CA3AF",
  "gold": "#FF9F0A",
  "goldSoft": "#FFE4A8",
  "pink": "#FFB8C6",
  "pinkSoft": "#FFDCE6",
  "success": "#34C759",
  "error": "#FF3B30",
  "warning": "#FF9F0A",
  "border": "#EDE9E3",
  "borderSoft": "#F5F3EF"
},
  R: {
  "xs": 8,
  "sm": 12,
  "md": 16,
  "lg": 20,
  "xl": 24,
  "xxl": 32
},
  S: {
  "xs": 11,
  "sm": 13,
  "md": 15,
  "lg": 17,
  "xl": 22,
  "xxl": 28,
  "hero": 42
},
} as const;

export const theme = { primitive, semantic, component } as const;
export type PiggyTheme = typeof theme;
export default theme;
