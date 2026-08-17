package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: classes3.dex */
class FloatBitsFromCharArray extends AbstractFloatingPointBitsFromCharArray {
    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long nan() {
        return Float.floatToRawIntBits(Float.NaN);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long negativeInfinity() {
        return Float.floatToRawIntBits(Float.NEGATIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long positiveInfinity() {
        return Float.floatToRawIntBits(Float.POSITIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long valueOfFloatLiteral(char[] cArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        float decFloatLiteralToFloat = FastFloatMath.decFloatLiteralToFloat(z, j, i3, z2, i4);
        if (Float.isNaN(decFloatLiteralToFloat)) {
            decFloatLiteralToFloat = Float.parseFloat(new String(cArr, i, i2 - i));
        }
        return Float.floatToRawIntBits(decFloatLiteralToFloat);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long valueOfHexLiteral(char[] cArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        float hexFloatLiteralToFloat = FastFloatMath.hexFloatLiteralToFloat(z, j, i3, z2, i4);
        if (Float.isNaN(hexFloatLiteralToFloat)) {
            hexFloatLiteralToFloat = Float.parseFloat(new String(cArr, i, i2 - i));
        }
        return Float.floatToRawIntBits(hexFloatLiteralToFloat);
    }
}
