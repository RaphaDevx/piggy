package uk.org.okapibarcode.output;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.font.TextAttribute;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.graphics.Circle;
import uk.org.okapibarcode.graphics.Color;
import uk.org.okapibarcode.graphics.Hexagon;
import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.graphics.TextAlignment;
import uk.org.okapibarcode.graphics.TextBox;

/* loaded from: classes4.dex */
public class Java2DRenderer implements SymbolRenderer {
    private final Graphics2D g2d;
    private final Color ink;
    private final double magnification;
    private final Color paper;

    public Java2DRenderer(Graphics2D graphics2D, double d, Color color, Color color2) {
        this.g2d = graphics2D;
        this.magnification = d;
        this.paper = color;
        this.ink = color2;
    }

    @Override // uk.org.okapibarcode.output.SymbolRenderer
    public void render(Symbol symbol) {
        Font deriveFont;
        Font font;
        Iterator<TextBox> it;
        double d;
        float f;
        int quietZoneHorizontal = (int) (symbol.getQuietZoneHorizontal() * this.magnification);
        int quietZoneVertical = (int) (symbol.getQuietZoneVertical() * this.magnification);
        Font font2 = symbol.getFont();
        if (font2 != null) {
            deriveFont = font2.deriveFont((float) (font2.getSize2D() * this.magnification));
        } else {
            deriveFont = new Font(symbol.getFontName(), 0, (int) (symbol.getFontSize() * this.magnification)).deriveFont(Collections.singletonMap(TextAttribute.TRACKING, 0));
        }
        Font font3 = this.g2d.getFont();
        java.awt.Color color = this.g2d.getColor();
        if (this.paper != null) {
            int width = (int) (symbol.getWidth() * this.magnification);
            int height = (int) (symbol.getHeight() * this.magnification);
            this.g2d.setColor(new java.awt.Color(this.paper.red, this.paper.green, this.paper.blue));
            this.g2d.fillRect(0, 0, width, height);
        }
        this.g2d.setColor(new java.awt.Color(this.ink.red, this.ink.green, this.ink.blue));
        for (Iterator<Rectangle> it2 = symbol.getRectangles().iterator(); it2.hasNext(); it2 = it2) {
            Rectangle next = it2.next();
            this.g2d.fillRect((int) ((next.x * this.magnification) + quietZoneHorizontal), (int) ((next.y * this.magnification) + quietZoneVertical), (int) (next.width * this.magnification), (int) (next.height * this.magnification));
            font3 = font3;
        }
        Font font4 = font3;
        Iterator<TextBox> it3 = symbol.getTexts().iterator();
        while (it3.hasNext()) {
            TextBox next2 = it3.next();
            TextAlignment textAlignment = (next2.alignment == TextAlignment.JUSTIFY && next2.text.length() == 1) ? TextAlignment.CENTER : next2.alignment;
            this.g2d.setFont(textAlignment != TextAlignment.JUSTIFY ? deriveFont : addTracking(deriveFont, next2.width * this.magnification, next2.text, this.g2d));
            Rectangle2D stringBounds = this.g2d.getFontMetrics().getStringBounds(next2.text, this.g2d);
            float f2 = ((float) (next2.y * this.magnification)) + quietZoneVertical;
            int i = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$graphics$TextAlignment[textAlignment.ordinal()];
            if (i == 1 || i == 2) {
                font = deriveFont;
                it = it3;
                d = this.magnification * next2.x;
            } else if (i == 3) {
                font = deriveFont;
                it = it3;
                d = ((this.magnification * next2.x) + (this.magnification * next2.width)) - stringBounds.getWidth();
            } else if (i == 4) {
                font = deriveFont;
                it = it3;
                f = (float) ((((this.magnification * next2.x) + ((this.magnification * next2.width) / 2.0d)) - (stringBounds.getWidth() / 2.0d)) + quietZoneHorizontal);
                this.g2d.drawString(next2.text, f, f2);
                deriveFont = font;
                it3 = it;
            } else {
                throw new IllegalStateException("Unknown alignment: " + textAlignment);
            }
            f = (float) (d + quietZoneHorizontal);
            this.g2d.drawString(next2.text, f, f2);
            deriveFont = font;
            it3 = it;
        }
        for (Hexagon hexagon : symbol.getHexagons()) {
            Polygon polygon = new Polygon();
            for (int i2 = 0; i2 < 6; i2++) {
                polygon.addPoint((int) ((hexagon.pointX[i2] * this.magnification) + quietZoneHorizontal), (int) ((hexagon.pointY[i2] * this.magnification) + quietZoneVertical));
            }
            this.g2d.fill(polygon);
        }
        List<Circle> target = symbol.getTarget();
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            if (i4 < target.size()) {
                Ellipse2D.Double adjust = adjust(target.get(i3), this.magnification, quietZoneHorizontal, quietZoneVertical);
                Ellipse2D.Double adjust2 = adjust(target.get(i4), this.magnification, quietZoneHorizontal, quietZoneVertical);
                Area area = new Area(adjust);
                area.subtract(new Area(adjust2));
                this.g2d.fill(area);
                i3 += 2;
            } else {
                this.g2d.setFont(font4);
                this.g2d.setColor(color);
                return;
            }
        }
    }

    /* renamed from: uk.org.okapibarcode.output.Java2DRenderer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment;

        static {
            int[] iArr = new int[TextAlignment.values().length];
            $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment = iArr;
            try {
                iArr[TextAlignment.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment[TextAlignment.JUSTIFY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment[TextAlignment.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$graphics$TextAlignment[TextAlignment.CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static Ellipse2D.Double adjust(Circle circle, double d, int i, int i2) {
        return new Ellipse2D.Double(i + ((circle.centreX - circle.radius) * d), i2 + ((circle.centreY - circle.radius) * d), circle.radius * 2.0d * d, circle.radius * 2.0d * d);
    }

    private static Font addTracking(Font font, double d, String str, Graphics2D graphics2D) {
        return font.deriveFont(Collections.singletonMap(TextAttribute.TRACKING, Double.valueOf(((d - font.getStringBounds(str, graphics2D.getFontRenderContext()).getWidth()) / (str.length() - 1)) / (font.getSize2D() * (font.isTransformed() ? font.getTransform().getScaleX() : 1.0d)))));
    }
}
