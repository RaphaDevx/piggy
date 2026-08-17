package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: classes3.dex */
final class DoubleBitsFromCharArray extends AbstractFloatingPointBitsFromCharArray {
    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long nan() {
        return Double.doubleToRawLongBits(Double.NaN);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long negativeInfinity() {
        return Double.doubleToRawLongBits(Double.NEGATIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long positiveInfinity() {
        return Double.doubleToRawLongBits(Double.POSITIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long valueOfFloatLiteral(char[] cArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        double tryDecFloatToDoubleTruncated = FastDoubleMath.tryDecFloatToDoubleTruncated(z, j, i3, z2, i4);
        if (Double.isNaN(tryDecFloatToDoubleTruncated)) {
            tryDecFloatToDoubleTruncated = Double.parseDouble(new String(cArr, i, i2 - i));
        }
        return Double.doubleToRawLongBits(tryDecFloatToDoubleTruncated);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharArray
    long valueOfHexLiteral(char[] cArr, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        double tryHexFloatToDoubleTruncated = FastDoubleMath.tryHexFloatToDoubleTruncated(z, j, i3, z2, i4);
        if (Double.isNaN(tryHexFloatToDoubleTruncated)) {
            tryHexFloatToDoubleTruncated = Double.parseDouble(new String(cArr, i, i2 - i));
        }
        return Double.doubleToRawLongBits(tryHexFloatToDoubleTruncated);
    }
}
