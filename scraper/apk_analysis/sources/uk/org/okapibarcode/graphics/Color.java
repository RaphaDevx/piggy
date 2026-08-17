package uk.org.okapibarcode.graphics;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class Color {
    public final int blue;
    public final int green;
    public final int red;
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);

    public Color(int i, int i2, int i3) {
        this.red = i;
        this.green = i2;
        this.blue = i3;
    }

    public Color(int i) {
        this.red = (i >> 16) & 255;
        this.green = (i >> 8) & 255;
        this.blue = i & 255;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Color)) {
            return false;
        }
        Color color = (Color) obj;
        return this.red == color.red && this.green == color.green && this.blue == color.blue;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.red), Integer.valueOf(this.green), Integer.valueOf(this.blue));
    }

    public String toString() {
        return "Color[red=" + this.red + ", green=" + this.green + ", blue=" + this.blue + "]";
    }
}
