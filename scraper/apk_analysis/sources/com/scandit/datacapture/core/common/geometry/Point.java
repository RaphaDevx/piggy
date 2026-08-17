package com.scandit.datacapture.core.common.geometry;

/* loaded from: classes2.dex */
public final class Point {
    final float x;
    final float y;

    public Point(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point point = (Point) obj;
        return this.x == point.x && this.y == point.y;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.y) + ((Float.floatToIntBits(this.x) + 527) * 31);
    }

    public String toString() {
        return "Point{x=" + this.x + ",y=" + this.y + "}";
    }
}
