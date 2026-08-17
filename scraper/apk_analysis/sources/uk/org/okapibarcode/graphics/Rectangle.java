package uk.org.okapibarcode.graphics;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class Rectangle {
    public double height;
    public final double width;
    public final double x;
    public final double y;

    public Rectangle(double d, double d2, double d3, double d4) {
        this.x = d;
        this.y = d2;
        this.width = d3;
        this.height = d4;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Rectangle)) {
            return false;
        }
        Rectangle rectangle = (Rectangle) obj;
        return this.x == rectangle.x && this.y == rectangle.y && this.width == rectangle.width && this.height == rectangle.height;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.width), Double.valueOf(this.height));
    }

    public String toString() {
        return "Rectangle[x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + "]";
    }
}
