package io.sentry.util;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes3.dex */
public final class Random implements Serializable {
    private static final double DOUBLE_MASK = 9.007199254740992E15d;
    private static final float FLOAT_UNIT = 1.6777216E7f;
    private static final long INTEGER_MASK = 4294967295L;
    private static final long MULT_64 = 6364136223846793005L;
    static final AtomicLong UNIQUE_SEED = new AtomicLong(System.nanoTime());
    private static final long serialVersionUID = -4257915988930727506L;
    private boolean gausAvailable;
    private long inc;
    private double nextGaus;
    private long state;

    public Random() {
        this(getRandomSeed(), getRandomSeed());
    }

    public Random(long j, long j2) {
        setSeed(j, j2);
    }

    private Random(long j, long j2, boolean z) {
        setState(j);
        setInc(j2);
    }

    public void setSeed(long j, long j2) {
        long j3 = (j2 << 1) | 1;
        this.inc = j3;
        this.state = j3 + j;
    }

    public byte nextByte() {
        this.state = (this.state * MULT_64) + this.inc;
        return (byte) ((((r0 >>> 22) ^ r0) >>> ((int) ((r0 >>> 61) + 22))) >>> 24);
    }

    public void nextBytes(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            this.state = (this.state * MULT_64) + this.inc;
            bArr[i] = (byte) ((((r1 >>> 22) ^ r1) >>> ((int) ((r1 >>> 61) + 22))) >>> 24);
        }
    }

    public char nextChar() {
        this.state = (this.state * MULT_64) + this.inc;
        return (char) ((((r0 >>> 22) ^ r0) >>> ((int) ((r0 >>> 61) + 22))) >>> 16);
    }

    public short nextShort() {
        this.state = (this.state * MULT_64) + this.inc;
        return (short) ((((r0 >>> 22) ^ r0) >>> ((int) ((r0 >>> 61) + 22))) >>> 16);
    }

    public int nextInt() {
        long j = (this.state * MULT_64) + this.inc;
        this.state = j;
        return (int) (((j >>> 22) ^ j) >>> ((int) ((j >>> 61) + 22)));
    }

    public int nextInt(int i) {
        long j = (this.state * MULT_64) + this.inc;
        this.state = j;
        int i2 = ((int) (((j >>> 22) ^ j) >>> ((int) ((j >>> 61) + 22)))) >>> 1;
        int i3 = i - 1;
        if ((i & i3) == 0) {
            return (int) ((i * i2) >> 31);
        }
        while (true) {
            int i4 = i2 % i;
            if ((i2 - i4) + i3 >= 0) {
                return i4;
            }
            long j2 = (this.state * MULT_64) + this.inc;
            this.state = j2;
            i2 = ((int) (((j2 >>> 22) ^ j2) >>> ((int) ((j2 >>> 61) + 22)))) >>> 1;
        }
    }

    public boolean nextBoolean() {
        long j = (this.state * MULT_64) + this.inc;
        this.state = j;
        return (((((j >>> 22) ^ j) >>> ((int) ((j >>> 61) + 22))) & INTEGER_MASK) >>> 31) != 0;
    }

    public boolean nextBoolean(double d) {
        if (d < 0.0d || d > 1.0d) {
            throw new IllegalArgumentException("probability must be between 0.0 and 1.0 inclusive.");
        }
        if (d == 0.0d) {
            return false;
        }
        if (d == 1.0d) {
            return true;
        }
        long j = this.state * MULT_64;
        long j2 = this.inc;
        long j3 = j + j2;
        long j4 = (((j3 >>> 22) ^ j3) >>> ((int) ((j3 >>> 61) + 22))) & INTEGER_MASK;
        long j5 = (j3 * MULT_64) + j2;
        this.state = j5;
        return ((double) (((j4 >>> 6) << 27) + (((((j5 >>> 22) ^ j5) >>> ((int) ((j5 >>> 61) + 22))) & INTEGER_MASK) >>> 5))) / DOUBLE_MASK < d;
    }

    public long nextLong() {
        long j = this.state * MULT_64;
        long j2 = this.inc;
        long j3 = j + j2;
        long j4 = ((j3 >>> 22) ^ j3) >>> ((int) ((j3 >>> 61) + 22));
        this.state = (j3 * MULT_64) + j2;
        return (j4 << 32) + ((int) (((r0 >>> 22) ^ r0) >>> ((int) ((r0 >>> 61) + 22))));
    }

    public long nextLong(long j) {
        long j2;
        long j3;
        if (j == 0) {
            throw new IllegalArgumentException("n has to be greater than 0");
        }
        do {
            long j4 = this.state * MULT_64;
            long j5 = this.inc;
            long j6 = j4 + j5;
            long j7 = ((j6 >>> 22) ^ j6) >>> ((int) ((j6 >>> 61) + 22));
            this.state = (j6 * MULT_64) + j5;
            j2 = ((j7 << 32) + ((int) (((r3 >>> 22) ^ r3) >>> ((int) ((r3 >>> 61) + 22))))) >>> 1;
            j3 = j2 % j;
        } while ((j2 - j3) + (j - 1) < 0);
        return j3;
    }

    public double nextDouble() {
        long j = this.state * MULT_64;
        long j2 = this.inc;
        long j3 = j + j2;
        long j4 = (((j3 >>> 22) ^ j3) >>> ((int) ((j3 >>> 61) + 22))) & INTEGER_MASK;
        this.state = (j3 * MULT_64) + j2;
        return (((j4 >>> 6) << 27) + (((((r0 >>> 22) ^ r0) >>> ((int) ((r0 >>> 61) + 22))) & INTEGER_MASK) >>> 5)) / DOUBLE_MASK;
    }

    public double nextDouble(boolean z, boolean z2) {
        double d;
        while (true) {
            long j = this.state * MULT_64;
            long j2 = this.inc;
            long j3 = j + j2;
            long j4 = (((j3 >>> 22) ^ j3) >>> ((int) ((j3 >>> 61) + 22))) & INTEGER_MASK;
            long j5 = (j3 * MULT_64) + j2;
            this.state = j5;
            d = (((j4 >>> 6) << 27) + (((((j5 >>> 22) ^ j5) >>> ((int) ((j5 >>> 61) + 22))) & INTEGER_MASK) >>> 5)) / DOUBLE_MASK;
            if (z2) {
                long j6 = (j5 * MULT_64) + j2;
                this.state = j6;
                if ((((((j6 >>> 22) ^ j6) >>> ((int) ((j6 >>> 61) + 22))) & INTEGER_MASK) >>> 31) != 0) {
                    d += 1.0d;
                }
            }
            if (d > 1.0d || (!z && d == 0.0d)) {
            }
        }
        return d;
    }

    public float nextFloat() {
        this.state = (this.state * MULT_64) + this.inc;
        return (((((r0 >>> 22) ^ r0) >>> ((int) ((r0 >>> 61) + 22))) & INTEGER_MASK) >>> 8) / FLOAT_UNIT;
    }

    public float nextFloat(boolean z, boolean z2) {
        float f;
        while (true) {
            long j = this.state * MULT_64;
            long j2 = this.inc;
            long j3 = j + j2;
            this.state = j3;
            f = (((((j3 >>> 22) ^ j3) >>> ((int) ((j3 >>> 61) + 22))) & INTEGER_MASK) >>> 8) / FLOAT_UNIT;
            if (z2) {
                long j4 = (j3 * MULT_64) + j2;
                this.state = j4;
                if ((((((j4 >>> 22) ^ j4) >>> ((int) ((j4 >>> 61) + 22))) & INTEGER_MASK) >>> 31) != 0) {
                    f += 1.0f;
                }
            }
            if (f > 1.0f || (!z && f == 0.0f)) {
            }
        }
        return f;
    }

    private void setInc(long j) {
        if (j == 0 || j % 2 == 0) {
            throw new IllegalArgumentException("Increment may not be 0 or even. Value: " + j);
        }
        this.inc = j;
    }

    private void setState(long j) {
        this.state = j;
    }

    private static long getRandomSeed() {
        AtomicLong atomicLong;
        long j;
        long j2;
        do {
            atomicLong = UNIQUE_SEED;
            j = atomicLong.get();
            long j3 = (j >> 12) ^ j;
            long j4 = j3 ^ (j3 << 25);
            j2 = (j4 ^ (j4 >> 27)) * 2685821657736338717L;
        } while (!atomicLong.compareAndSet(j, j2));
        return j2;
    }
}
