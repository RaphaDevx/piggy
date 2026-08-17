package com.scandit.datacapture.core.internal.sdk.data;

/* loaded from: classes2.dex */
public final class NativeFloatRange {
    final float max;
    final float min;

    public NativeFloatRange(float f, float f2) {
        this.min = f;
        this.max = f2;
    }

    public float getMax() {
        return this.max;
    }

    public float getMin() {
        return this.min;
    }

    public String toString() {
        return "NativeFloatRange{min=" + this.min + ",max=" + this.max + "}";
    }
}
