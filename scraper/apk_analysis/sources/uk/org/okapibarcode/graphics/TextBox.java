package uk.org.okapibarcode.graphics;

import java.util.Objects;

/* loaded from: classes4.dex */
public final class TextBox {
    public final TextAlignment alignment;
    public final String text;
    public final double width;
    public final double x;
    public final double y;

    public TextBox(double d, double d2, double d3, String str, TextAlignment textAlignment) {
        this.x = d;
        this.y = d2;
        this.width = d3;
        this.text = (String) Objects.requireNonNull(str);
        this.alignment = (TextAlignment) Objects.requireNonNull(textAlignment);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TextBox)) {
            return false;
        }
        TextBox textBox = (TextBox) obj;
        return this.x == textBox.x && this.y == textBox.y && this.width == textBox.width && Objects.equals(this.text, textBox.text) && this.alignment == textBox.alignment;
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.x), Double.valueOf(this.y), Double.valueOf(this.width), this.text, this.alignment);
    }

    public String toString() {
        return "TextBox[x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", text=" + this.text + ", alignment=" + this.alignment + "]";
    }
}
