package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: classes3.dex */
public class FastDoubleParser {
    private FastDoubleParser() {
    }

    public static double parseDouble(CharSequence charSequence) throws NumberFormatException {
        return parseDouble(charSequence, 0, charSequence.length());
    }

    public static double parseDouble(CharSequence charSequence, int i, int i2) throws NumberFormatException {
        long parseFloatingPointLiteral = new DoubleBitsFromCharSequence().parseFloatingPointLiteral(charSequence, i, i2);
        if (parseFloatingPointLiteral == -1) {
            throw new NumberFormatException("Illegal input");
        }
        return Double.longBitsToDouble(parseFloatingPointLiteral);
    }

    public static double parseDouble(char[] cArr) throws NumberFormatException {
        return parseDouble(cArr, 0, cArr.length);
    }

    public static double parseDouble(char[] cArr, int i, int i2) throws NumberFormatException {
        long parseFloatingPointLiteral = new DoubleBitsFromCharArray().parseFloatingPointLiteral(cArr, i, i2);
        if (parseFloatingPointLiteral == -1) {
            throw new NumberFormatException("Illegal input");
        }
        return Double.longBitsToDouble(parseFloatingPointLiteral);
    }

    public static long parseDoubleBits(CharSequence charSequence, int i, int i2) {
        return new DoubleBitsFromCharSequence().parseFloatingPointLiteral(charSequence, i, i2);
    }

    public static long parseDoubleBits(char[] cArr, int i, int i2) {
        return new DoubleBitsFromCharArray().parseFloatingPointLiteral(cArr, i, i2);
    }
}
