package com.scandit.datacapture.core.data;

/* loaded from: classes2.dex */
public final class Range {
    final int maximum;
    final int minimum;
    final int step;

    public Range(int i, int i2, int i3) {
        this.minimum = i;
        this.maximum = i2;
        this.step = i3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Range)) {
            return false;
        }
        Range range = (Range) obj;
        return this.minimum == range.minimum && this.maximum == range.maximum && this.step == range.step;
    }

    public int getMaximum() {
        return this.maximum;
    }

    public int getMinimum() {
        return this.minimum;
    }

    public int getStep() {
        return this.step;
    }

    public int hashCode() {
        return ((((this.minimum + 527) * 31) + this.maximum) * 31) + this.step;
    }

    public String toString() {
        return "Range{minimum=" + this.minimum + ",maximum=" + this.maximum + ",step=" + this.step + "}";
    }
}
