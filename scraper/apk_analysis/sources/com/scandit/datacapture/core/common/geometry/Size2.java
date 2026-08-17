package com.scandit.datacapture.core.common.geometry;

/* loaded from: classes2.dex */
public final class Size2 {
    final float height;
    final float width;

    public Size2(float f, float f2) {
        this.width = f;
        this.height = f2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Size2)) {
            return false;
        }
        Size2 size2 = (Size2) obj;
        return this.width == size2.width && this.height == size2.height;
    }

    public float getHeight() {
        return this.height;
    }

    public float getWidth() {
        return this.width;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.height) + ((Float.floatToIntBits(this.width) + 527) * 31);
    }

    public String toString() {
        return "Size2{width=" + this.width + ",height=" + this.height + "}";
    }
}
