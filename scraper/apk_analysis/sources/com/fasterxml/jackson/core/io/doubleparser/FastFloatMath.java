package com.fasterxml.jackson.core.io.doubleparser;

import com.fasterxml.jackson.core.io.doubleparser.FastDoubleMath;

/* loaded from: classes3.dex */
class FastFloatMath {
    private static final int FLOAT_EXPONENT_BIAS = 127;
    private static final int FLOAT_MAX_EXPONENT_POWER_OF_TEN = 38;
    private static final int FLOAT_MAX_EXPONENT_POWER_OF_TWO = 127;
    private static final int FLOAT_MIN_EXPONENT_POWER_OF_TEN = -45;
    private static final int FLOAT_MIN_EXPONENT_POWER_OF_TWO = -126;
    private static final float[] FLOAT_POWER_OF_TEN = {1.0f, 10.0f, 100.0f, 1000.0f, 10000.0f, 100000.0f, 1000000.0f, 1.0E7f, 1.0E8f, 1.0E9f, 1.0E10f};
    private static final int FLOAT_SIGNIFICAND_WIDTH = 24;

    private FastFloatMath() {
    }

    static float decFloatLiteralToFloat(boolean z, long j, int i, boolean z2, int i2) {
        if (j == 0) {
            return z ? -0.0f : 0.0f;
        }
        if (!z2) {
            if (FLOAT_MIN_EXPONENT_POWER_OF_TEN > i || i > 38) {
                return Float.NaN;
            }
            return tryDecToFloatWithFastAlgorithm(z, j, i);
        }
        if (FLOAT_MIN_EXPONENT_POWER_OF_TEN > i2 || i2 > 38) {
            return Float.NaN;
        }
        float tryDecToFloatWithFastAlgorithm = tryDecToFloatWithFastAlgorithm(z, j, i2);
        float tryDecToFloatWithFastAlgorithm2 = tryDecToFloatWithFastAlgorithm(z, j + 1, i2);
        if (Float.isNaN(tryDecToFloatWithFastAlgorithm) || tryDecToFloatWithFastAlgorithm2 != tryDecToFloatWithFastAlgorithm) {
            return Float.NaN;
        }
        return tryDecToFloatWithFastAlgorithm;
    }

    static float hexFloatLiteralToFloat(boolean z, long j, int i, boolean z2, int i2) {
        if (j == 0) {
            return z ? -0.0f : 0.0f;
        }
        if (!z2) {
            if (FLOAT_MIN_EXPONENT_POWER_OF_TWO > i || i > 127) {
                return Float.NaN;
            }
            return tryHexToFloatWithFastAlgorithm(z, j, i);
        }
        if (FLOAT_MIN_EXPONENT_POWER_OF_TWO > i2 || i2 > 127) {
            return Float.NaN;
        }
        float tryHexToFloatWithFastAlgorithm = tryHexToFloatWithFastAlgorithm(z, j, i2);
        float tryHexToFloatWithFastAlgorithm2 = tryHexToFloatWithFastAlgorithm(z, j + 1, i2);
        if (Double.isNaN(tryHexToFloatWithFastAlgorithm) || tryHexToFloatWithFastAlgorithm2 != tryHexToFloatWithFastAlgorithm) {
            return Float.NaN;
        }
        return tryHexToFloatWithFastAlgorithm;
    }

    static float tryDecToFloatWithFastAlgorithm(boolean z, long j, int i) {
        float f;
        if (-10 <= i && i <= 10 && Long.compareUnsigned(j, 16777215L) <= 0) {
            float f2 = j;
            if (i < 0) {
                f = f2 / FLOAT_POWER_OF_TEN[-i];
            } else {
                f = f2 * FLOAT_POWER_OF_TEN[i];
            }
            return z ? -f : f;
        }
        int i2 = i + 325;
        long j2 = FastDoubleMath.MANTISSA_64[i2];
        long j3 = ((i * 217706) >> 16) + 191;
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j);
        long j4 = j << numberOfLeadingZeros;
        FastDoubleMath.UInt128 fullMultiplication = FastDoubleMath.fullMultiplication(j4, j2);
        long j5 = fullMultiplication.low;
        long j6 = fullMultiplication.high;
        if ((j6 & 274877906943L) == 274877906943L && Long.compareUnsigned(j5 + j4, j5) < 0) {
            FastDoubleMath.UInt128 fullMultiplication2 = FastDoubleMath.fullMultiplication(j4, FastDoubleMath.MANTISSA_128[i2]);
            long j7 = fullMultiplication2.low;
            long j8 = fullMultiplication2.high + j5;
            if (Long.compareUnsigned(j8, j5) < 0) {
                j6++;
            }
            if (j8 + 1 == 0 && (j6 & 549755813887L) == 549755813887L && j7 + Long.compareUnsigned(j4, j7) < 0) {
                return Float.NaN;
            }
        }
        long j9 = j6 >>> 63;
        long j10 = j6 >>> ((int) (38 + j9));
        int i3 = numberOfLeadingZeros + ((int) (j9 ^ 1));
        long j11 = j6 & 274877906943L;
        if (j11 == 274877906943L) {
            return Float.NaN;
        }
        if (j11 == 0 && (3 & j10) == 1) {
            return Float.NaN;
        }
        long j12 = (j10 + 1) >>> 1;
        if (j12 >= 16777216) {
            i3--;
            j12 = 8388608;
        }
        long j13 = j12 & (-8388609);
        long j14 = j3 - i3;
        if (j14 < 1 || j14 > 254) {
            return Float.NaN;
        }
        return Float.intBitsToFloat((int) ((j14 << 23) | j13 | (z ? 2147483648L : 0L)));
    }

    static float tryHexToFloatWithFastAlgorithm(boolean z, long j, int i) {
        if (j == 0 || i < -180) {
            return z ? -0.0f : 0.0f;
        }
        if (i > 127) {
            return z ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
        }
        if (Long.compareUnsigned(j, 9007199254740991L) > 0) {
            return Float.NaN;
        }
        float scalb = j * Math.scalb(1.0f, i);
        return z ? -scalb : scalb;
    }
}
