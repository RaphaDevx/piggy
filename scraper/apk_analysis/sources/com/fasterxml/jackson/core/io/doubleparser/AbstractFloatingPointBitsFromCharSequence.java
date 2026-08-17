package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: classes3.dex */
abstract class AbstractFloatingPointBitsFromCharSequence extends AbstractFloatValueParser {
    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    abstract long nan();

    abstract long negativeInfinity();

    abstract long positiveInfinity();

    abstract long valueOfFloatLiteral(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    abstract long valueOfHexLiteral(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4);

    AbstractFloatingPointBitsFromCharSequence() {
    }

    private long parseDecFloatLiteral(CharSequence charSequence, int i, int i2, int i3, boolean z, boolean z2) {
        int i4;
        int i5;
        int i6;
        char charAt;
        int i7;
        long j;
        boolean z3;
        int i8;
        int tryToParseEightDigits;
        int i9 = -1;
        int i10 = i;
        long j2 = 0;
        char c = 0;
        boolean z4 = false;
        while (i10 < i3) {
            c = charSequence.charAt(i10);
            if (!isDigit(c)) {
                if (c != '.') {
                    break;
                }
                z4 |= i9 >= 0;
                int i11 = i10;
                while (i11 < i3 - 8 && (tryToParseEightDigits = tryToParseEightDigits(charSequence, i11 + 1)) >= 0) {
                    j2 = (j2 * 100000000) + tryToParseEightDigits;
                    i11 += 8;
                }
                int i12 = i10;
                i10 = i11;
                i9 = i12;
            } else {
                j2 = ((j2 * 10) + c) - 48;
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
        if (c == 'e' || c == 'E') {
            i6 = i10 + 1;
            charAt = i6 < i3 ? charSequence.charAt(i6) : (char) 0;
            boolean z5 = charAt == '-';
            if (z5 || charAt == '+') {
                i6 = i10 + 2;
                charAt = i6 < i3 ? charSequence.charAt(i6) : (char) 0;
            }
            z4 |= !isDigit(charAt);
            i7 = 0;
            do {
                if (i7 < 1024) {
                    i7 = ((i7 * 10) + charAt) - 48;
                }
                i6++;
                charAt = i6 < i3 ? charSequence.charAt(i6) : (char) 0;
            } while (isDigit(charAt));
            if (z5) {
                i7 = -i7;
            }
            i4 += i7;
        } else {
            charAt = c;
            i7 = 0;
            i6 = i10;
        }
        if (i6 < i3 && (charAt == 'd' || charAt == 'D' || charAt == 'f' || charAt == 'F')) {
            i6++;
        }
        int skipWhitespace = skipWhitespace(charSequence, i6, i3);
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
                char charAt2 = charSequence.charAt(i13);
                if (charAt2 != '.') {
                    if (Long.compareUnsigned(j3, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j3 = ((j3 * 10) + charAt2) - 48;
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
        return valueOfFloatLiteral(charSequence, i2, i3, z, j, i4, z3, i8);
    }

    public long parseFloatingPointLiteral(CharSequence charSequence, int i, int i2) {
        int skipWhitespace;
        int i3;
        int i4 = i + i2;
        if (i < 0 || i4 > charSequence.length() || (skipWhitespace = skipWhitespace(charSequence, i, i4)) == i4) {
            return -1L;
        }
        char charAt = charSequence.charAt(skipWhitespace);
        boolean z = charAt == '-';
        if (z || charAt == '+') {
            skipWhitespace++;
            charAt = skipWhitespace < i4 ? charSequence.charAt(skipWhitespace) : (char) 0;
            if (charAt == 0) {
                return -1L;
            }
        }
        if (charAt >= 'I') {
            if (charAt == 'N') {
                return parseNaN(charSequence, skipWhitespace, i4);
            }
            return parseInfinity(charSequence, skipWhitespace, i4, z);
        }
        boolean z2 = charAt == '0';
        if (z2) {
            int i5 = skipWhitespace + 1;
            char charAt2 = i5 < i4 ? charSequence.charAt(i5) : (char) 0;
            if (charAt2 == 'x' || charAt2 == 'X') {
                return parseHexFloatLiteral(charSequence, skipWhitespace + 2, i, i4, z);
            }
            i3 = i5;
        } else {
            i3 = skipWhitespace;
        }
        return parseDecFloatLiteral(charSequence, i3, i, i4, z, z2);
    }

    private long parseHexFloatLiteral(CharSequence charSequence, int i, int i2, int i3, boolean z) {
        int i4;
        int min;
        int i5;
        char c;
        int i6;
        int i7;
        boolean z2;
        int i8;
        int i9 = i;
        long j = 0;
        int i10 = -1;
        char c2 = 0;
        boolean z3 = false;
        while (true) {
            if (i9 >= i3) {
                break;
            }
            c2 = charSequence.charAt(i9);
            byte b = c2 > 127 ? (byte) -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[c2];
            if (b < 0) {
                if (b != -4) {
                    break;
                }
                z3 |= i10 >= 0;
                i10 = i9;
            } else {
                j = (j << 4) | b;
            }
            i9++;
        }
        if (i10 < 0) {
            i4 = i9 - i;
            i10 = i9;
            min = 0;
        } else {
            i4 = (i9 - i) - 1;
            min = Math.min((i10 - i9) + 1, 1024) * 4;
        }
        boolean z4 = c2 == 'p' || c2 == 'P';
        if (z4) {
            int i11 = i9 + 1;
            c = i11 < i3 ? charSequence.charAt(i11) : (char) 0;
            boolean z5 = c == '-';
            if (z5 || c == '+') {
                i11 = i9 + 2;
                c = i11 < i3 ? charSequence.charAt(i11) : (char) 0;
            }
            boolean z6 = (!isDigit(c)) | z3;
            int i12 = 0;
            do {
                if (i12 < 1024) {
                    i12 = ((i12 * 10) + c) - 48;
                }
                i11++;
                c = i11 < i3 ? charSequence.charAt(i11) : (char) 0;
            } while (isDigit(c));
            if (z5) {
                i12 = -i12;
            }
            int i13 = min + i12;
            int i14 = i12;
            z3 = z6;
            i5 = i11;
            i6 = i13;
            i7 = i14;
        } else {
            i5 = i9;
            c = c2;
            i6 = min;
            i7 = 0;
        }
        if (i5 < i3 && (c == 'd' || c == 'D' || c == 'f' || c == 'F')) {
            i5++;
        }
        int skipWhitespace = skipWhitespace(charSequence, i5, i3);
        if (z3 || skipWhitespace < i3 || i4 == 0 || !z4) {
            return -1L;
        }
        if (i4 > 16) {
            skipWhitespace = i;
            int i15 = 0;
            j = 0;
            while (skipWhitespace < i9) {
                char charAt = charSequence.charAt(skipWhitespace);
                byte b2 = charAt > 127 ? (byte) -1 : AbstractFloatValueParser.CHAR_TO_HEX_MAP[charAt];
                if (b2 < 0) {
                    i15++;
                } else {
                    if (Long.compareUnsigned(j, 1000000000000000000L) >= 0) {
                        break;
                    }
                    j = (j << 4) | b2;
                }
                skipWhitespace++;
            }
            z2 = skipWhitespace < i9;
            i8 = i15;
        } else {
            z2 = false;
            i8 = 0;
        }
        return valueOfHexLiteral(charSequence, i2, i3, z, j, i6, z2, (i10 - skipWhitespace) + i8 + i7);
    }

    private long parseInfinity(CharSequence charSequence, int i, int i2, boolean z) {
        int i3 = i + 7;
        if (i3 < i2 && charSequence.charAt(i) == 'I' && charSequence.charAt(i + 1) == 'n' && charSequence.charAt(i + 2) == 'f' && charSequence.charAt(i + 3) == 'i' && charSequence.charAt(i + 4) == 'n' && charSequence.charAt(i + 5) == 'i' && charSequence.charAt(i + 6) == 't' && charSequence.charAt(i3) == 'y' && skipWhitespace(charSequence, i + 8, i2) == i2) {
            return z ? negativeInfinity() : positiveInfinity();
        }
        return -1L;
    }

    private long parseNaN(CharSequence charSequence, int i, int i2) {
        int i3 = i + 2;
        if (i3 < i2 && charSequence.charAt(i + 1) == 'a' && charSequence.charAt(i3) == 'N' && skipWhitespace(charSequence, i + 3, i2) == i2) {
            return nan();
        }
        return -1L;
    }

    private int skipWhitespace(CharSequence charSequence, int i, int i2) {
        while (i < i2 && charSequence.charAt(i) <= ' ') {
            i++;
        }
        return i;
    }

    private int tryToParseEightDigits(CharSequence charSequence, int i) {
        return FastDoubleSwar.tryToParseEightDigitsUtf16(charSequence.charAt(i) | (charSequence.charAt(i + 1) << 16) | (charSequence.charAt(i + 2) << 32) | (charSequence.charAt(i + 3) << 48), (charSequence.charAt(i + 7) << 48) | charSequence.charAt(i + 4) | (charSequence.charAt(i + 5) << 16) | (charSequence.charAt(i + 6) << 32));
    }
}
