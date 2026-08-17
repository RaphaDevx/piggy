package com.fasterxml.jackson.core.io.doubleparser;

/* loaded from: classes3.dex */
final class DoubleBitsFromCharSequence extends AbstractFloatingPointBitsFromCharSequence {
    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharSequence
    long nan() {
        return Double.doubleToRawLongBits(Double.NaN);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharSequence
    long negativeInfinity() {
        return Double.doubleToRawLongBits(Double.NEGATIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharSequence
    long positiveInfinity() {
        return Double.doubleToRawLongBits(Double.POSITIVE_INFINITY);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharSequence
    long valueOfFloatLiteral(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        double tryDecFloatToDoubleTruncated = FastDoubleMath.tryDecFloatToDoubleTruncated(z, j, i3, z2, i4);
        if (Double.isNaN(tryDecFloatToDoubleTruncated)) {
            tryDecFloatToDoubleTruncated = Double.parseDouble(charSequence.subSequence(i, i2).toString());
        }
        return Double.doubleToRawLongBits(tryDecFloatToDoubleTruncated);
    }

    @Override // com.fasterxml.jackson.core.io.doubleparser.AbstractFloatingPointBitsFromCharSequence
    long valueOfHexLiteral(CharSequence charSequence, int i, int i2, boolean z, long j, int i3, boolean z2, int i4) {
        double tryHexFloatToDoubleTruncated = FastDoubleMath.tryHexFloatToDoubleTruncated(z, j, i3, z2, i4);
        if (Double.isNaN(tryHexFloatToDoubleTruncated)) {
            tryHexFloatToDoubleTruncated = Double.parseDouble(charSequence.subSequence(i, i2).toString());
        }
        return Double.doubleToRawLongBits(tryHexFloatToDoubleTruncated);
    }
}
