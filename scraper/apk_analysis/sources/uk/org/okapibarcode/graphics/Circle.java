package uk.org.okapibarcode.graphics;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class Circle {
    public final double centreX;
    public final double centreY;
    public final double radius;

    public Circle(double d, double d2, double d3) {
        this.centreX = d;
        this.centreY = d2;
        this.radius = d3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Circle)) {
            return false;
        }
        Circle circle = (Circle) obj;
        return this.centreX == circle.centreX && this.centreY == circle.centreY && this.radius == circle.radius;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.centreX), Double.valueOf(this.centreY), Double.valueOf(this.radius));
    }

    public String toString() {
        return "Circle[centreX=" + this.centreX + ", centreY=" + this.centreY + ", radius=" + this.radius + "]";
    }
}
