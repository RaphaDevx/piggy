package com.scandit.datacapture.core.internal.sdk.common.graphics;

/* loaded from: classes2.dex */
public final class NativeColor {
    final float a;
    final float b;
    final float g;
    final float r;

    public NativeColor(float f, float f2, float f3, float f4) {
        this.r = f;
        this.g = f2;
        this.b = f3;
        this.a = f4;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NativeColor)) {
            return false;
        }
        NativeColor nativeColor = (NativeColor) obj;
        return this.r == nativeColor.r && this.g == nativeColor.g && this.b == nativeColor.b && this.a == nativeColor.a;
    }

    public float getA() {
        return this.a;
    }

    public float getB() {
        return this.b;
    }

    public float getG() {
        return this.g;
    }

    public float getR() {
        return this.r;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.a) + ((Float.floatToIntBits(this.b) + ((Float.floatToIntBits(this.g) + ((Float.floatToIntBits(this.r) + 527) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "NativeColor{r=" + this.r + ",g=" + this.g + ",b=" + this.b + ",a=" + this.a + "}";
    }
}
