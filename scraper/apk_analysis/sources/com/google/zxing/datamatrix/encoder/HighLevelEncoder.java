package com.google.zxing.datamatrix.encoder;

import com.google.zxing.Dimension;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class HighLevelEncoder {
    static final int ASCII_ENCODATION = 0;
    static final int BASE256_ENCODATION = 5;
    static final int C40_ENCODATION = 1;
    static final char C40_UNLATCH = 254;
    static final int EDIFACT_ENCODATION = 4;
    static final char LATCH_TO_ANSIX12 = 238;
    static final char LATCH_TO_BASE256 = 231;
    static final char LATCH_TO_C40 = 230;
    static final char LATCH_TO_EDIFACT = 240;
    static final char LATCH_TO_TEXT = 239;
    private static final char MACRO_05 = 236;
    static final String MACRO_05_HEADER = "[)>\u001e05\u001d";
    private static final char MACRO_06 = 237;
    static final String MACRO_06_HEADER = "[)>\u001e06\u001d";
    static final String MACRO_TRAILER = "\u001e\u0004";
    private static final char PAD = 129;
    static final int TEXT_ENCODATION = 2;
    static final char UPPER_SHIFT = 235;
    static final int X12_ENCODATION = 3;
    static final char X12_UNLATCH = 254;

    static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    static boolean isExtendedASCII(char c) {
        return c >= 128 && c <= 255;
    }

    static boolean isNativeC40(char c) {
        return c == ' ' || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z');
    }

    static boolean isNativeEDIFACT(char c) {
        return c >= ' ' && c <= '^';
    }

    static boolean isNativeText(char c) {
        return c == ' ' || (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z');
    }

    private static boolean isSpecialB256(char c) {
        return false;
    }

    private static boolean isX12TermSep(char c) {
        return c == '\r' || c == '*' || c == '>';
    }

    private HighLevelEncoder() {
    }

    private static char randomize253State(int i) {
        int i2 = (i * 149) % 253;
        int i3 = i2 + 130;
        if (i3 > 254) {
            i3 = i2 - 124;
        }
        return (char) i3;
    }

    public static String encodeHighLevel(String str) {
        return encodeHighLevel(str, SymbolShapeHint.FORCE_NONE, null, null, false);
    }

    public static String encodeHighLevel(String str, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2) {
        return encodeHighLevel(str, symbolShapeHint, dimension, dimension2, false);
    }

    public static String encodeHighLevel(String str, SymbolShapeHint symbolShapeHint, Dimension dimension, Dimension dimension2, boolean z) {
        C40Encoder c40Encoder = new C40Encoder();
        int i = 0;
        Encoder[] encoderArr = {new ASCIIEncoder(), c40Encoder, new TextEncoder(), new X12Encoder(), new EdifactEncoder(), new Base256Encoder()};
        EncoderContext encoderContext = new EncoderContext(str);
        encoderContext.setSymbolShape(symbolShapeHint);
        encoderContext.setSizeConstraints(dimension, dimension2);
        if (str.startsWith(MACRO_05_HEADER) && str.endsWith(MACRO_TRAILER)) {
            encoderContext.writeCodeword(MACRO_05);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += 7;
        } else if (str.startsWith(MACRO_06_HEADER) && str.endsWith(MACRO_TRAILER)) {
            encoderContext.writeCodeword(MACRO_06);
            encoderContext.setSkipAtEnd(2);
            encoderContext.pos += 7;
        }
        if (z) {
            c40Encoder.encodeMaximal(encoderContext);
            i = encoderContext.getNewEncoding();
            encoderContext.resetEncoderSignal();
        }
        while (encoderContext.hasMoreCharacters()) {
            encoderArr[i].encode(encoderContext);
            if (encoderContext.getNewEncoding() >= 0) {
                i = encoderContext.getNewEncoding();
                encoderContext.resetEncoderSignal();
            }
        }
        int codewordCount = encoderContext.getCodewordCount();
        encoderContext.updateSymbolInfo();
        int dataCapacity = encoderContext.getSymbolInfo().getDataCapacity();
        if (codewordCount < dataCapacity && i != 0 && i != 5 && i != 4) {
            encoderContext.writeCodeword((char) 254);
        }
        StringBuilder codewords = encoderContext.getCodewords();
        if (codewords.length() < dataCapacity) {
            codewords.append(PAD);
        }
        while (codewords.length() < dataCapacity) {
            codewords.append(randomize253State(codewords.length() + 1));
        }
        return encoderContext.getCodewords().toString();
    }

    static int lookAheadTest(CharSequence charSequence, int i, int i2) {
        int lookAheadTestIntern = lookAheadTestIntern(charSequence, i, i2);
        if (i2 == 3 && lookAheadTestIntern == 3) {
            int min = Math.min(i + 3, charSequence.length());
            while (i < min) {
                if (!isNativeX12(charSequence.charAt(i))) {
                    return 0;
                }
                i++;
            }
        } else if (i2 == 4 && lookAheadTestIntern == 4) {
            int min2 = Math.min(i + 4, charSequence.length());
            while (i < min2) {
                if (!isNativeEDIFACT(charSequence.charAt(i))) {
                    return 0;
                }
                i++;
            }
        }
        return lookAheadTestIntern;
    }

    static int lookAheadTestIntern(CharSequence charSequence, int i, int i2) {
        float[] fArr;
        int i3;
        int i4;
        int i5;
        int i6;
        if (i >= charSequence.length()) {
            return i2;
        }
        float f = 2.0f;
        int i7 = 5;
        int i8 = 2;
        int i9 = 4;
        int i10 = 3;
        if (i2 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            fArr = new float[6];
            fArr[0] = 1.0f;
            fArr[1] = 2.0f;
            fArr[2] = 2.0f;
            fArr[3] = 2.0f;
            fArr[4] = 2.0f;
            fArr[5] = 2.25f;
            fArr[i2] = 0.0f;
        }
        byte[] bArr = new byte[6];
        int[] iArr = new int[6];
        int i11 = 0;
        while (true) {
            int i12 = i + i11;
            if (i12 == charSequence.length()) {
                Arrays.fill(bArr, (byte) 0);
                Arrays.fill(iArr, 0);
                int findMinimums = findMinimums(fArr, iArr, Integer.MAX_VALUE, bArr);
                int minimumCount = getMinimumCount(bArr);
                if (iArr[0] == findMinimums) {
                    return 0;
                }
                if (minimumCount == 1) {
                    if (bArr[i7] > 0) {
                        return i7;
                    }
                    if (bArr[i9] > 0) {
                        return i9;
                    }
                    if (bArr[i8] > 0) {
                        return i8;
                    }
                    if (bArr[i10] > 0) {
                        return i10;
                    }
                }
                return 1;
            }
            char charAt = charSequence.charAt(i12);
            i11++;
            if (isDigit(charAt)) {
                fArr[0] = fArr[0] + 0.5f;
            } else if (isExtendedASCII(charAt)) {
                float ceil = (float) Math.ceil(fArr[0]);
                fArr[0] = ceil;
                fArr[0] = ceil + f;
            } else {
                float ceil2 = (float) Math.ceil(fArr[0]);
                fArr[0] = ceil2;
                fArr[0] = ceil2 + 1.0f;
            }
            if (isNativeC40(charAt)) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + 1.3333334f;
            }
            if (isNativeText(charAt)) {
                fArr[i8] = fArr[i8] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[i8] = fArr[i8] + 2.6666667f;
            } else {
                fArr[i8] = fArr[i8] + 1.3333334f;
            }
            if (isNativeX12(charAt)) {
                fArr[i10] = fArr[i10] + 0.6666667f;
            } else if (isExtendedASCII(charAt)) {
                fArr[i10] = fArr[i10] + 4.3333335f;
            } else {
                fArr[i10] = fArr[i10] + 3.3333333f;
            }
            if (isNativeEDIFACT(charAt)) {
                fArr[i9] = fArr[i9] + 0.75f;
            } else if (isExtendedASCII(charAt)) {
                fArr[i9] = fArr[i9] + 4.25f;
            } else {
                fArr[i9] = fArr[i9] + 3.25f;
            }
            if (isSpecialB256(charAt)) {
                i3 = 5;
                fArr[5] = fArr[5] + 4.0f;
            } else {
                i3 = 5;
                fArr[5] = fArr[5] + 1.0f;
            }
            if (i11 >= i9) {
                Arrays.fill(bArr, (byte) 0);
                Arrays.fill(iArr, 0);
                findMinimums(fArr, iArr, Integer.MAX_VALUE, bArr);
                if (iArr[0] < min(iArr[i3], iArr[1], iArr[i8], iArr[i10], iArr[i9])) {
                    return 0;
                }
                int i13 = iArr[i3];
                if (i13 < iArr[0] || i13 + 1 < min(iArr[1], iArr[2], iArr[3], iArr[i9])) {
                    return 5;
                }
                if (iArr[i9] + 1 < min(iArr[5], iArr[1], iArr[2], iArr[3], iArr[0])) {
                    return i9;
                }
                if (iArr[2] + 1 < min(iArr[5], iArr[1], iArr[i9], iArr[3], iArr[0])) {
                    return 2;
                }
                if (iArr[3] + 1 < min(iArr[5], iArr[1], iArr[4], iArr[2], iArr[0])) {
                    return 3;
                }
                i4 = 4;
                i6 = 2;
                if (iArr[1] + 1 < min(iArr[0], iArr[5], iArr[4], iArr[2])) {
                    int i14 = iArr[1];
                    int i15 = iArr[3];
                    if (i14 < i15) {
                        return 1;
                    }
                    if (i14 == i15) {
                        for (int i16 = i + i11 + 1; i16 < charSequence.length(); i16++) {
                            char charAt2 = charSequence.charAt(i16);
                            if (isX12TermSep(charAt2)) {
                                return 3;
                            }
                            if (!isNativeX12(charAt2)) {
                                break;
                            }
                        }
                        return 1;
                    }
                    i5 = 3;
                } else {
                    i5 = 3;
                }
                i7 = 5;
            } else {
                i7 = i3;
                i4 = i9;
                i5 = i10;
                i6 = i8;
            }
            i9 = i4;
            i8 = i6;
            i10 = i5;
            f = 2.0f;
        }
    }

    private static int min(int i, int i2, int i3, int i4, int i5) {
        return Math.min(min(i, i2, i3, i4), i5);
    }

    private static int min(int i, int i2, int i3, int i4) {
        return Math.min(i, Math.min(i2, Math.min(i3, i4)));
    }

    private static int findMinimums(float[] fArr, int[] iArr, int i, byte[] bArr) {
        for (int i2 = 0; i2 < 6; i2++) {
            int ceil = (int) Math.ceil(fArr[i2]);
            iArr[i2] = ceil;
            if (i > ceil) {
                Arrays.fill(bArr, (byte) 0);
                i = ceil;
            }
            if (i == ceil) {
                bArr[i2] = (byte) (bArr[i2] + 1);
            }
        }
        return i;
    }

    private static int getMinimumCount(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            i += bArr[i2];
        }
        return i;
    }

    static boolean isNativeX12(char c) {
        return isX12TermSep(c) || c == ' ' || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z');
    }

    public static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = i;
        while (i2 < length && isDigit(charSequence.charAt(i2))) {
            i2++;
        }
        return i2 - i;
    }

    static void illegalCharacter(char c) {
        String hexString = Integer.toHexString(c);
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - hexString.length()) + hexString) + ')');
    }
}
