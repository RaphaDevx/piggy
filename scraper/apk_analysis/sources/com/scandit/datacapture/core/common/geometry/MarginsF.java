package com.scandit.datacapture.core.common.geometry;

/* loaded from: classes2.dex */
public final class MarginsF {
    final float bottom;
    final float left;
    final float right;
    final float top;

    public MarginsF(float f, float f2, float f3, float f4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MarginsF)) {
            return false;
        }
        MarginsF marginsF = (MarginsF) obj;
        return this.left == marginsF.left && this.top == marginsF.top && this.right == marginsF.right && this.bottom == marginsF.bottom;
    }

    public float getBottom() {
        return this.bottom;
    }

    public float getLeft() {
        return this.left;
    }

    public float getRight() {
        return this.right;
    }

    public float getTop() {
        return this.top;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.bottom) + ((Float.floatToIntBits(this.right) + ((Float.floatToIntBits(this.top) + ((Float.floatToIntBits(this.left) + 527) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "MarginsF{left=" + this.left + ",top=" + this.top + ",right=" + this.right + ",bottom=" + this.bottom + "}";
    }
}
