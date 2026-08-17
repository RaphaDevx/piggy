package com.scandit.datacapture.core.internal.sdk.engine;

/* loaded from: classes2.dex */
public final class NativePropertyDataFloat {
    final float defaultValue;
    final float maxValue;
    final float minValue;

    public NativePropertyDataFloat(float f, float f2, float f3) {
        this.defaultValue = f;
        this.minValue = f2;
        this.maxValue = f3;
    }

    public float getDefaultValue() {
        return this.defaultValue;
    }

    public float getMaxValue() {
        return this.maxValue;
    }

    public float getMinValue() {
        return this.minValue;
    }

    public String toString() {
        return "NativePropertyDataFloat{defaultValue=" + this.defaultValue + ",minValue=" + this.minValue + ",maxValue=" + this.maxValue + "}";
    }
}
