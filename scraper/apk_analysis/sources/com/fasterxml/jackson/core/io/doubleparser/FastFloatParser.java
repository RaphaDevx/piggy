package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: classes3.dex */
public class FastFloatParser {
    private FastFloatParser() {
    }

    public static float parseFloat(CharSequence charSequence) throws NumberFormatException {
        return parseFloat(charSequence, 0, charSequence.length());
    }

    public static float parseFloat(CharSequence charSequence, int i, int i2) throws NumberFormatException {
        long parseFloatingPointLiteral = new FloatBitsFromCharSequence().parseFloatingPointLiteral(charSequence, i, i2);
        if (parseFloatingPointLiteral == -1) {
            throw new NumberFormatException("Illegal input");
        }
        return Float.intBitsToFloat((int) parseFloatingPointLiteral);
    }

    public static float parseFloat(char[] cArr) throws NumberFormatException {
        return parseFloat(cArr, 0, cArr.length);
    }

    public static float parseFloat(char[] cArr, int i, int i2) throws NumberFormatException {
        long parseFloatingPointLiteral = new FloatBitsFromCharArray().parseFloatingPointLiteral(cArr, i, i2);
        if (parseFloatingPointLiteral == -1) {
            throw new NumberFormatException("Illegal input");
        }
        return Float.intBitsToFloat((int) parseFloatingPointLiteral);
    }

    public static long parseFloatBits(CharSequence charSequence, int i, int i2) {
        return new FloatBitsFromCharSequence().parseFloatingPointLiteral(charSequence, i, i2);
    }

    public static long parseFloatBits(char[] cArr, int i, int i2) {
        return new FloatBitsFromCharArray().parseFloatingPointLiteral(cArr, i, i2);
    }
}
