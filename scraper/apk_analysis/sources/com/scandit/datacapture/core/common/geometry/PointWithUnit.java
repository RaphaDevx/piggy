package com.scandit.datacapture.core.common.geometry;

/* loaded from: classes2.dex */
public final class PointWithUnit {
    final FloatWithUnit x;
    final FloatWithUnit y;

    public PointWithUnit(FloatWithUnit floatWithUnit, FloatWithUnit floatWithUnit2) {
        this.x = floatWithUnit;
        this.y = floatWithUnit2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PointWithUnit)) {
            return false;
        }
        PointWithUnit pointWithUnit = (PointWithUnit) obj;
        return this.x.equals(pointWithUnit.x) && this.y.equals(pointWithUnit.y);
    }

    public FloatWithUnit getX() {
        return this.x;
    }

    public FloatWithUnit getY() {
        return this.y;
    }

    public int hashCode() {
        return this.y.hashCode() + ((this.x.hashCode() + 527) * 31);
    }

    public String toString() {
        return "PointWithUnit{x=" + this.x + ",y=" + this.y + "}";
    }
}
