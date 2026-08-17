package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: classes3.dex */
abstract class AbstractFloatingPointBitsFromCharArray extends AbstractFloatValueParser {
    private static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    abstract long nan();

    abstract long negativeInfinity();

    abstract long positiveInfinity();

    abstract long valueOfFloatLiteral(char[] cArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    abstract long valueOfHexLiteral(char[] cArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    AbstractFloatingPointBitsFromCharArray() {
    }

    private int skipWhitespace(char[] cArr, int i, int i2) {
        while (i < i2 && (cArr[i] & 255) <= 32) {
            i++;
        }
        return i;
    }

    private long parseDecFloatLiteral(char[] cArr, int i, int i2, int i3, boolean z, boolean z2) {
        int i4;
        int i5;
        int i6;
        char c;
        int i7;
        long j;
        boolean z3;
        int i8;
        int tryToParseEightDigits;
        int i9 = -1;
        int i10 = i;
        long j2 = 0;
        char c2 = 0;
        boolean z4 = false;
        while (i10 < i3) {
            c2 = cArr[i10];
            if (!isDigit(c2)) {
                if (c2 != '.') {
                    break;
                }
                z4 |= i9 >= 0;
                int i11 = i10;
                while (i11 < i3 - 8 && (tryToParseEightDigits = tryToParseEightDigits(cArr, i11 + 1)) >= 0) {
                    j2 = (j2 * 100000000) + tryToParseEightDigits;
                    i11 += 8;
                }
                int i12 = i10;
                i10 = i11;
                i9 = i12;
            } else {
                j2 = ((j2 * 10) + c2) - 48;
            }
            i10++;
        }
        if (i9 < 0) {
            i5 = i10 - i;
            i9 = i10;
            i4 = 0;
        } else {
            i4 = (i9 - i10) + 1;
            i5 = (i10 - i) - 1;
        }
        if (c2 == 'e' || c2 == 'E') {
            i6 = i10 + 1;
            c = i6 < i3 ? cArr[i6] : (char) 0;
            boolean z5 = c == '-';
            if (z5 || c == '+') {
                i6 = i10 + 2;
                c = i6 < i3 ? cArr[i6] : (char) 0;
            }
            z4 |= !isDigit(c);
            i7 = 0;
            do {
                if (i7 < 1024) {
                    i7 = ((i7 * 10) + c) - 48;
                }
                i6++;
                c = i6 < i3 ? cArr[i6] : (char) 0;
            } while (isDigit(c));
            if (z5) {
                i7 = -i7;
            }
            i4 += i7;
        } else {
            c = c2;
            i7 = 0;
            i6 = i10;
        }
        if (i6 < i3 && (c == 'd' || c == 'D' || c == 'f' || c == 'F')) {
            i6++;
        }
        int skipWhitespace = skipWhitespace(cArr, i6, i3);
        if (z4 || skipWhitespace < i3) {
            return -1L;
        }
        if (!z2 && i5 == 0) {
            return -1L;
        }
        if (i5 > 19) {
            int i13 = i;
            long j3 = 0;
            int i14 = 0;
            while (i13 < i10) {
                char c3 = cArr[i13];
                if (c3 != '.') {
                    if (Long.compareUnsigned(j3, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j3 = ((j3 * 10) + c3) - 48;
                } else {
                    i14++;
                }
                i13++;
            }
            j = j3;
            z3 = i13 < i10;
            i8 = (i9 - i13) + i14 + i7;
        } else {
            j = j2;
            z3 = false;
            i8 = 0;
        }
        return valueOfFloatLiteral(cArr, i2, i3, z, j, i4, z3, i8);
    }

    public long parseFloatingPointLiteral(char[] cArr, int i, int i2) {
        int skipWhitespace;
        int i3;
        int i4 = i + i2;
        if (i < 0 || i4 > cArr.length || (skipWhitespace = skipWhitespace(cArr, i, i4)) == i4) {
            return -1L;
        }
        char c = cArr[skipWhitespace];
        boolean z = c == '-';
        if (z || c == '+') {
            skipWhitespace++;
            c = skipWhitespace < i4 ? cArr[skipWhitespace] : (char) 0;
            if (c == 0) {
                return -1L;
            }
        }
        if (c >= 'I') {
            if (c == 'N') {
                return parseNaN(cArr, skipWhitespace, i4);
            }
            return parseInfinity(cArr, skipWhitespace, i4, z);
        }
        boolean z2 = c == '0';
        if (z2) {
            int i5 = skipWhitespace + 1;
            char c2 = i5 < i4 ? cArr[i5] : (char) 0;
            if (c2 == 'x' || c2 == 'X') {
                return parseHexFloatLiteral(cArr, skipWhitespace + 2, i, i4, z);
            }
            i3 = i5;
        } else {
            i3 = skipWhitespace;
        }
        return parseDecFloatLiteral(cArr, i3, i, i4, z, z2);
    }

    private long parseHexFloatLiteral(char[] cArr, int i, int i2, int i3, boolean z) {
        int i4;
        int min;
        int i5;
        char c;
        int i6;
        int i7;
        int i8;
        long j;
        boolean z2;
        int i9;
        int i10 = i;
        long j2 = 0;
        int i11 = -1;
        char c2 = 0;
        boolean z3 = false;
        while (true) {
            if (i10 >= i3) {
                break;
            }
            c2 = cArr[i10];
            byte b = c2 > 127 ? (byte) -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[c2];
            if (b < 0) {
                if (b != -4) {
                    break;
                }
                z3 |= i11 >= 0;
                i11 = i10;
            } else {
                j2 = (j2 << 4) | b;
            }
            i10++;
        }
        if (i11 < 0) {
            i4 = i10 - i;
            min = 0;
            i11 = i10;
        } else {
            i4 = (i10 - i) - 1;
            min = Math.min((i11 - i10) + 1, 1024) * 4;
        }
        boolean z4 = c2 == 'p' || c2 == 'P';
        if (z4) {
            i5 = i10 + 1;
            char c3 = i5 < i3 ? cArr[i5] : (char) 0;
            boolean z5 = c3 == '-';
            if (z5 || c3 == '+') {
                i5 = i10 + 2;
                c3 = i5 < i3 ? cArr[i5] : (char) 0;
            }
            boolean z6 = (!isDigit(c3)) | z3;
            int i12 = 0;
            do {
                if (i12 < 1024) {
                    i12 = ((i12 * 10) + c3) - 48;
                }
                i5++;
                c3 = i5 < i3 ? cArr[i5] : (char) 0;
            } while (isDigit(c3));
            if (z5) {
                i12 = -i12;
            }
            c = c3;
            i6 = min + i12;
            i7 = i12;
            z3 = z6;
        } else {
            i5 = i10;
            c = c2;
            i6 = min;
            i7 = 0;
        }
        if (i5 < i3 && (c == 'd' || c == 'D' || c == 'f' || c == 'F')) {
            i5++;
        }
        int skipWhitespace = skipWhitespace(cArr, i5, i3);
        if (z3 || skipWhitespace < i3 || i4 == 0 || !z4) {
            return -1L;
        }
        if (i4 > 16) {
            i8 = i;
            int i13 = 0;
            long j3 = 0;
            while (i8 < i10) {
                char c4 = cArr[i8];
                byte b2 = c4 > 127 ? (byte) -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[c4];
                if (b2 < 0) {
                    i13++;
                } else {
                    if (Long.compareUnsigned(j3, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j3 = (j3 << 4) | b2;
                }
                i8++;
            }
            i9 = i13;
            j = j3;
            z2 = i8 < i10;
        } else {
            i8 = skipWhitespace;
            j = j2;
            z2 = false;
            i9 = 0;
        }
        return valueOfHexLiteral(cArr, i2, i3, z, j, i6, z2, (i11 - i8) + i9 + i7);
    }

    private long parseInfinity(char[] cArr, int i, int i2, boolean z) {
        int i3 = i + 7;
        if (i3 < i2 && cArr[i] == 'I' && cArr[i + 1] == 'n' && cArr[i + 2] == 'f' && cArr[i + 3] == 'i' && cArr[i + 4] == 'n' && cArr[i + 5] == 'i' && cArr[i + 6] == 't' && cArr[i3] == 'y' && skipWhitespace(cArr, i + 8, i2) == i2) {
            return z ? negativeInfinity() : positiveInfinity();
        }
        return -1L;
    }

    private long parseNaN(char[] cArr, int i, int i2) {
        int i3 = i + 2;
        if (i3 < i2 && cArr[i + 1] == 'a' && cArr[i3] == 'N' && skipWhitespace(cArr, i + 3, i2) == i2) {
            return nan();
        }
        return -1L;
    }

    private int tryToParseEightDigits(char[] cArr, int i) {
        return FastDoubleSwar.tryToParseEightDigitsUtf16(cArr, i);
    }
}
