package uk.org.okapibarcode.graphics;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class Hexagon {
    private static final double INK_SPREAD = 1.25d;
    private static final double[] OFFSET_X = {0.0d, 0.86d, 0.86d, 0.0d, -0.86d, -0.86d};
    private static final double[] OFFSET_Y = {1.0d, 0.5d, -0.5d, -1.0d, -0.5d, 0.5d};
    public final double centreX;
    public final double centreY;
    public final double[] pointX = new double[6];
    public final double[] pointY = new double[6];

    public Hexagon(double d, double d2) {
        this.centreX = d;
        this.centreY = d2;
        for (int i = 0; i < 6; i++) {
            this.pointX[i] = (OFFSET_X[i] * INK_SPREAD) + d;
            this.pointY[i] = (OFFSET_Y[i] * INK_SPREAD) + d2;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Hexagon)) {
            return false;
        }
        Hexagon hexagon = (Hexagon) obj;
        return this.centreX == hexagon.centreX && this.centreY == hexagon.centreY;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.centreX), Double.valueOf(this.centreY));
    }

    public String toString() {
        return "Hexagon[centreX=" + this.centreX + ", centreY=" + this.centreY + "]";
    }
}
