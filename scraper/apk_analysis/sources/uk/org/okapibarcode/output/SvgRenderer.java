package uk.org.okapibarcode.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Text;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.graphics.Circle;
import uk.org.okapibarcode.graphics.Color;
import uk.org.okapibarcode.graphics.Hexagon;
import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.graphics.TextAlignment;
import uk.org.okapibarcode.graphics.TextBox;

/* loaded from: classes4.dex */
public class SvgRenderer implements SymbolRenderer {
    private final Color ink;
    private final double magnification;
    private final OutputStream out;
    private final Color paper;
    private final boolean xmlProlog;

    public SvgRenderer(OutputStream outputStream, double d, Color color, Color color2, boolean z) {
        this.out = outputStream;
        this.magnification = d;
        this.paper = color;
        this.ink = color2;
        this.xmlProlog = z;
    }

    @Override // uk.org.okapibarcode.output.SymbolRenderer
    public void render(Symbol symbol) throws IOException {
        String str;
        String str2;
        String str3;
        String str4;
        double d;
        String str5;
        String str6 = StringUtils.SPACE;
        String str7 = "\">\n";
        String str8 = "\" />\n";
        String str9 = "\" fill=\"#";
        String content = symbol.getContent();
        int width = (int) (symbol.getWidth() * this.magnification);
        int height = (int) (symbol.getHeight() * this.magnification);
        int quietZoneHorizontal = (int) (symbol.getQuietZoneHorizontal() * this.magnification);
        int quietZoneVertical = (int) (symbol.getQuietZoneVertical() * this.magnification);
        if (content == null || content.isEmpty()) {
            content = "OkapiBarcode Generated Symbol";
        }
        String str10 = String.format("%02X", Integer.valueOf(this.ink.red)) + String.format("%02X", Integer.valueOf(this.ink.green)) + String.format("%02X", Integer.valueOf(this.ink.blue));
        String str11 = String.format("%02X", Integer.valueOf(this.paper.red)) + String.format("%02X", Integer.valueOf(this.paper.green)) + String.format("%02X", Integer.valueOf(this.paper.blue));
        ExtendedOutputStreamWriter extendedOutputStreamWriter = new ExtendedOutputStreamWriter(this.out, "%.2f");
        try {
            if (this.xmlProlog) {
                extendedOutputStreamWriter.append((CharSequence) "<?xml version=\"1.0\" standalone=\"no\"?>\n");
                extendedOutputStreamWriter.append((CharSequence) "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n");
                extendedOutputStreamWriter.append((CharSequence) "   \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n");
            }
            extendedOutputStreamWriter.append((CharSequence) "<svg width=\"").appendInt(width).append((CharSequence) "\" height=\"").appendInt(height).append((CharSequence) "\" version=\"1.1").append((CharSequence) "\" xmlns=\"http://www.w3.org/2000/svg\">\n");
            extendedOutputStreamWriter.append((CharSequence) "   <desc>").append((CharSequence) clean(content)).append((CharSequence) "</desc>\n");
            extendedOutputStreamWriter.append((CharSequence) "   <g id=\"barcode\" fill=\"#").append((CharSequence) str10).append((CharSequence) "\">\n");
            extendedOutputStreamWriter.append((CharSequence) "      <rect x=\"0\" y=\"0\" width=\"").appendInt(width).append((CharSequence) "\" height=\"").appendInt(height).append((CharSequence) "\" fill=\"#").append((CharSequence) str11).append((CharSequence) "\" />\n");
            int i = 0;
            while (i < symbol.getRectangles().size()) {
                Rectangle rectangle = symbol.getRectangles().get(i);
                extendedOutputStreamWriter.append((CharSequence) "      <rect x=\"").append((rectangle.x * this.magnification) + quietZoneHorizontal).append((CharSequence) "\" y=\"").append((rectangle.y * this.magnification) + quietZoneVertical).append((CharSequence) "\" width=\"").append(rectangle.width * this.magnification).append((CharSequence) "\" height=\"").append(rectangle.height * this.magnification).append((CharSequence) "\" />\n");
                i++;
                str6 = str6;
                str7 = str7;
            }
            String str12 = str6;
            String str13 = str7;
            int i2 = 0;
            while (i2 < symbol.getTexts().size()) {
                TextBox textBox = symbol.getTexts().get(i2);
                TextAlignment textAlignment = (textBox.alignment == TextAlignment.JUSTIFY && textBox.text.length() == 1) ? TextAlignment.CENTER : textBox.alignment;
                int i3 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$graphics$TextAlignment[textAlignment.ordinal()];
                if (i3 == 1 || i3 == 2) {
                    str = str8;
                    str2 = str9;
                    str3 = str10;
                    str4 = str11;
                    d = quietZoneHorizontal + (this.magnification * textBox.x);
                    str5 = "start";
                } else if (i3 == 3) {
                    str = str8;
                    str2 = str9;
                    str3 = str10;
                    str4 = str11;
                    d = quietZoneHorizontal + (this.magnification * textBox.x) + (this.magnification * textBox.width);
                    str5 = "end";
                } else if (i3 == 4) {
                    str3 = str10;
                    str4 = str11;
                    str = str8;
                    str2 = str9;
                    d = (this.magnification * textBox.x) + ((this.magnification * textBox.width) / 2.0d) + quietZoneHorizontal;
                    str5 = "middle";
                } else {
                    throw new IllegalStateException("Unknown alignment: " + textAlignment);
                }
                extendedOutputStreamWriter.append((CharSequence) "      <text x=\"").append(d).append((CharSequence) "\" y=\"").append((textBox.y * this.magnification) + quietZoneVertical).append((CharSequence) "\" text-anchor=\"").append((CharSequence) str5).append((CharSequence) "\"\n");
                if (textAlignment == TextAlignment.JUSTIFY) {
                    extendedOutputStreamWriter.append((CharSequence) "         textLength=\"").append(textBox.width * this.magnification).append((CharSequence) "\" lengthAdjust=\"spacing\"\n");
                }
                str9 = str2;
                String str14 = str13;
                extendedOutputStreamWriter.append((CharSequence) "         font-family=\"").append((CharSequence) clean(symbol.getFontName())).append((CharSequence) "\" font-size=\"").append(symbol.getFontSize() * this.magnification).append((CharSequence) str9).append((CharSequence) str3).append((CharSequence) str14);
                extendedOutputStreamWriter.append((CharSequence) "         ").append((CharSequence) clean(textBox.text)).append((CharSequence) StringUtils.LF);
                extendedOutputStreamWriter.append((CharSequence) "      </text>\n");
                i2++;
                str13 = str14;
                str10 = str3;
                str11 = str4;
                str8 = str;
            }
            String str15 = str8;
            String str16 = str10;
            String str17 = str11;
            int i4 = 0;
            while (i4 < symbol.getTarget().size()) {
                Circle circle = symbol.getTarget().get(i4);
                String str18 = str15;
                extendedOutputStreamWriter.append((CharSequence) "      <circle cx=\"").append((circle.centreX * this.magnification) + quietZoneHorizontal).append((CharSequence) "\" cy=\"").append((circle.centreY * this.magnification) + quietZoneVertical).append((CharSequence) "\" r=\"").append(circle.radius * this.magnification).append((CharSequence) str9).append((CharSequence) ((i4 & 1) == 0 ? str16 : str17)).append((CharSequence) str18);
                i4++;
                str15 = str18;
            }
            int i5 = 0;
            while (i5 < symbol.getHexagons().size()) {
                Hexagon hexagon = symbol.getHexagons().get(i5);
                extendedOutputStreamWriter.append((CharSequence) "      <path d=\"");
                int i6 = 0;
                while (i6 < 6) {
                    if (i6 == 0) {
                        extendedOutputStreamWriter.append((CharSequence) "M ");
                    } else {
                        extendedOutputStreamWriter.append((CharSequence) "L ");
                    }
                    String str19 = str12;
                    extendedOutputStreamWriter.append((hexagon.pointX[i6] * this.magnification) + quietZoneHorizontal).append((CharSequence) str19).append((hexagon.pointY[i6] * this.magnification) + quietZoneVertical).append((CharSequence) str19);
                    i6++;
                    str12 = str19;
                }
                extendedOutputStreamWriter.append((CharSequence) "Z\" />\n");
                i5++;
                str12 = str12;
            }
            extendedOutputStreamWriter.append((CharSequence) "   </g>\n");
            extendedOutputStreamWriter.append((CharSequence) "</svg>\n");
            extendedOutputStreamWriter.close();
        } finally {
        }
    }

    /* renamed from: uk.org.okapibarcode.output.SvgRenderer$1, reason: invalid class name */
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

    protected String clean(String str) {
        String replaceAll = str.replaceAll("[\u0000-\u001f]", "");
        try {
            Text createTextNode = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument().createTextNode(replaceAll);
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            DOMSource dOMSource = new DOMSource(createTextNode);
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            newTransformer.setOutputProperty("omit-xml-declaration", BooleanUtils.YES);
            newTransformer.transform(dOMSource, streamResult);
            return stringWriter.toString();
        } catch (ParserConfigurationException | TransformerException | TransformerFactoryConfigurationError unused) {
            return replaceAll;
        }
    }
}
