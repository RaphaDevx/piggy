package uk.org.okapibarcode.backend;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.zxing.common.StringUtils;
import java.awt.Font;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.UByte;
import org.apache.commons.lang3.ClassUtils;
import uk.org.okapibarcode.graphics.Circle;
import uk.org.okapibarcode.graphics.Hexagon;
import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.graphics.TextAlignment;
import uk.org.okapibarcode.graphics.TextBox;
import uk.org.okapibarcode.util.Arrays;
import uk.org.okapibarcode.util.Doubles;
import uk.org.okapibarcode.util.EciMode;
import uk.org.okapibarcode.util.Gs1;

/* loaded from: classes4.dex */
public abstract class Symbol {
    protected static final int FNC1 = -1;
    protected static final String FNC1_STRING = "\\<FNC1>";
    protected static final int FNC2 = -2;
    protected static final String FNC2_STRING = "\\<FNC2>";
    protected static final int FNC3 = -3;
    protected static final String FNC3_STRING = "\\<FNC3>";
    protected static final int FNC4 = -4;
    protected static final String FNC4_STRING = "\\<FNC4>";
    private static char[] HIBC_CHAR_TABLE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-', ClassUtils.PACKAGE_SEPARATOR_CHAR, ' ', '$', JsonPointer.SEPARATOR, '+', '%'};
    protected String content;
    protected Font font;
    protected int[] inputData;
    protected String[] pattern;
    protected boolean readerInit;
    protected int[] row_height;
    protected DataType inputDataType = DataType.ECI;
    protected int default_height = 40;
    protected int quietZoneHorizontal = 0;
    protected int quietZoneVertical = 0;
    protected int moduleWidth = 1;
    protected String fontName = "Helvetica";
    protected int fontSize = 8;
    protected HumanReadableLocation humanReadableLocation = HumanReadableLocation.BOTTOM;
    protected TextAlignment humanReadableAlignment = TextAlignment.CENTER;
    protected boolean emptyContentAllowed = false;
    protected int eciMode = -1;
    protected String readable = "";
    protected int row_count = 0;
    protected int symbol_height = 0;
    protected int symbol_width = 0;
    protected StringBuilder encodeInfo = new StringBuilder();
    protected List<Rectangle> rectangles = new ArrayList();
    protected List<TextBox> texts = new ArrayList();
    protected List<Hexagon> hexagons = new ArrayList();
    protected List<Circle> target = new ArrayList();

    public enum DataType {
        ECI,
        GS1,
        HIBC
    }

    protected abstract void encode();

    protected double getModuleWidth(int i) {
        return i;
    }

    protected boolean gs1Supported() {
        return false;
    }

    public void setDataType(DataType dataType) {
        if (dataType == DataType.GS1 && !gs1Supported()) {
            throw new IllegalArgumentException("This symbology type does not support GS1 data");
        }
        this.inputDataType = dataType;
    }

    public DataType getDataType() {
        return this.inputDataType;
    }

    public void setReaderInit(boolean z) {
        this.readerInit = z;
    }

    public boolean getReaderInit() {
        return this.readerInit;
    }

    public void setBarHeight(int i) {
        this.default_height = i;
    }

    public int getBarHeight() {
        return this.default_height;
    }

    public void setModuleWidth(int i) {
        this.moduleWidth = i;
    }

    public int getModuleWidth() {
        return this.moduleWidth;
    }

    public void setQuietZoneHorizontal(int i) {
        this.quietZoneHorizontal = i;
    }

    public int getQuietZoneHorizontal() {
        return this.quietZoneHorizontal;
    }

    public void setQuietZoneVertical(int i) {
        this.quietZoneVertical = i;
    }

    public int getQuietZoneVertical() {
        return this.quietZoneVertical;
    }

    public void setFont(Font font) {
        this.font = font;
        this.fontName = font.getFontName();
        this.fontSize = font.getSize();
    }

    public Font getFont() {
        return this.font;
    }

    public void setFontName(String str) {
        this.fontName = (String) Objects.requireNonNull(str, "font name may not be null");
        this.font = null;
    }

    public String getFontName() {
        return this.fontName;
    }

    public void setFontSize(int i) {
        this.fontSize = i;
        this.font = null;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public int getWidth() {
        return this.symbol_width + (this.quietZoneHorizontal * 2);
    }

    public int getHeight() {
        return this.symbol_height + getHumanReadableHeight() + (this.quietZoneVertical * 2);
    }

    public int getHumanReadableHeight() {
        if (this.texts.isEmpty()) {
            return 0;
        }
        return getTheoreticalHumanReadableHeight();
    }

    protected int getTheoreticalHumanReadableHeight() {
        return (int) Math.ceil(this.fontSize * 1.2d);
    }

    public String getEncodeInfo() {
        return this.encodeInfo.toString();
    }

    public int getEciMode() {
        return this.eciMode;
    }

    public void setHumanReadableLocation(HumanReadableLocation humanReadableLocation) {
        this.humanReadableLocation = humanReadableLocation;
    }

    public HumanReadableLocation getHumanReadableLocation() {
        return this.humanReadableLocation;
    }

    public void setHumanReadableAlignment(TextAlignment textAlignment) {
        this.humanReadableAlignment = textAlignment;
    }

    public TextAlignment getHumanReadableAlignment() {
        return this.humanReadableAlignment;
    }

    public List<Rectangle> getRectangles() {
        return this.rectangles;
    }

    public List<TextBox> getTexts() {
        return this.texts;
    }

    public List<Hexagon> getHexagons() {
        return this.hexagons;
    }

    public List<Circle> getTarget() {
        return this.target;
    }

    protected static String bin2pat(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        int i = 0;
        boolean z = true;
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (z) {
                if (charSequence.charAt(i2) != '1') {
                    sb.append((char) (i + 48));
                    z = false;
                    i = 1;
                }
                i++;
            } else {
                if (charSequence.charAt(i2) != '0') {
                    sb.append((char) (i + 48));
                    i = 1;
                    z = true;
                }
                i++;
            }
        }
        sb.append((char) (i + 48));
        return sb.toString();
    }

    public void setEmptyContentAllowed(boolean z) {
        this.emptyContentAllowed = z;
    }

    public boolean getEmptyContentAllowed() {
        return this.emptyContentAllowed;
    }

    public void setContent(String str) {
        if (this.readerInit && this.inputDataType == DataType.GS1) {
            throw new OkapiException("Cannot use both GS1 mode and Reader Initialisation");
        }
        if (str == null) {
            str = "";
        }
        this.encodeInfo.setLength(0);
        int i = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$Symbol$DataType[this.inputDataType.ordinal()];
        if (i == 1) {
            this.content = Gs1.verify(str, FNC1_STRING);
            this.readable = str.replace('[', '(').replace(']', ')');
        } else if (i == 2) {
            this.content = hibcProcess(str);
        } else {
            this.content = str;
        }
        if (this.content.isEmpty() && !this.emptyContentAllowed) {
            throw new OkapiException("No input data");
        }
        encode();
        plotSymbol();
        mergeVerticalBlocks();
    }

    /* renamed from: uk.org.okapibarcode.backend.Symbol$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$Symbol$DataType;

        static {
            int[] iArr = new int[DataType.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$Symbol$DataType = iArr;
            try {
                iArr[DataType.GS1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Symbol$DataType[DataType.HIBC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public String getContent() {
        return this.content;
    }

    public String getHumanReadableText() {
        return this.readable;
    }

    protected void eciProcess() {
        EciMode or = EciMode.of(this.content, "ISO8859_1", 3).or(this.content, "ISO8859_2", 4).or(this.content, "ISO8859_3", 5).or(this.content, "ISO8859_4", 6).or(this.content, "ISO8859_5", 7).or(this.content, "ISO8859_6", 8).or(this.content, "ISO8859_7", 9).or(this.content, "ISO8859_8", 10).or(this.content, "ISO8859_9", 11).or(this.content, "ISO8859_10", 12).or(this.content, "ISO8859_11", 13).or(this.content, "ISO8859_13", 15).or(this.content, "ISO8859_14", 16).or(this.content, "ISO8859_15", 17).or(this.content, "ISO8859_16", 18).or(this.content, "Windows_1250", 21).or(this.content, "Windows_1251", 22).or(this.content, "Windows_1252", 23).or(this.content, "Windows_1256", 24).or(this.content, StringUtils.SHIFT_JIS, 20).or(this.content, "UTF8", 26);
        if (EciMode.NONE.equals(or)) {
            throw new OkapiException("Unable to determine ECI mode.");
        }
        this.eciMode = or.mode;
        this.inputData = toBytes(this.content, or.charset, new int[0]);
        infoLine("ECI Mode: " + or.mode);
        infoLine("ECI Charset: " + or.charset.name());
    }

    protected static int[] toBytes(String str, Charset charset, int... iArr) {
        int length;
        if (!charset.newEncoder().canEncode(str)) {
            return null;
        }
        byte[] bytes = FNC1_STRING.getBytes(charset);
        byte[] bytes2 = FNC2_STRING.getBytes(charset);
        byte[] bytes3 = FNC3_STRING.getBytes(charset);
        byte[] bytes4 = FNC4_STRING.getBytes(charset);
        byte[] bytes5 = str.getBytes(charset);
        int[] iArr2 = new int[bytes5.length + iArr.length];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < bytes5.length) {
            if (Arrays.containsAt(bytes5, bytes, i2)) {
                iArr2[i3] = -1;
                length = bytes.length;
            } else if (Arrays.containsAt(bytes5, bytes2, i2)) {
                iArr2[i3] = -2;
                length = bytes.length;
            } else if (Arrays.containsAt(bytes5, bytes3, i2)) {
                iArr2[i3] = -3;
                length = bytes.length;
            } else if (Arrays.containsAt(bytes5, bytes4, i2)) {
                iArr2[i3] = -4;
                length = bytes.length;
            } else {
                iArr2[i3] = bytes5[i2] & UByte.MAX_VALUE;
                i2++;
                i3++;
            }
            i2 += length - 1;
            i2++;
            i3++;
        }
        while (i < iArr.length) {
            iArr2[i3 + i] = iArr[i];
            i++;
        }
        int i4 = i3 + i;
        return i4 < i2 ? java.util.Arrays.copyOf(iArr2, i4) : iArr2;
    }

    protected void plotSymbol() {
        int i;
        int i2;
        resetPlotElements();
        double theoreticalHumanReadableHeight = this.humanReadableLocation == HumanReadableLocation.TOP ? getTheoreticalHumanReadableHeight() : 0;
        double d = theoreticalHumanReadableHeight;
        double d2 = 0.0d;
        for (int i3 = 0; i3 < this.row_count; i3++) {
            double d3 = 0.0d;
            boolean z = true;
            int i4 = 0;
            while (i4 < this.pattern[i3].length()) {
                double moduleWidth = getModuleWidth(this.pattern[i3].charAt(i4) - '0') * this.moduleWidth;
                if (z) {
                    int i5 = this.row_height[i3];
                    if (i5 == -1) {
                        i5 = this.default_height;
                    }
                    double d4 = i5;
                    if (moduleWidth == 0.0d || d4 == 0.0d) {
                        i2 = i4;
                    } else {
                        i2 = i4;
                        this.rectangles.add(new Rectangle(d3, d, moduleWidth, d4));
                    }
                    double d5 = d3 + moduleWidth;
                    if (d5 > this.symbol_width) {
                        this.symbol_width = (int) Math.ceil(d5);
                    }
                    d2 = d4;
                } else {
                    i2 = i4;
                }
                z = !z;
                d3 += moduleWidth;
                i4 = i2 + 1;
            }
            double d6 = (d - theoreticalHumanReadableHeight) + d2;
            if (d6 > this.symbol_height) {
                this.symbol_height = (int) Math.ceil(d6);
            }
            d += d2;
        }
        if (this.humanReadableLocation == HumanReadableLocation.NONE || this.readable.isEmpty()) {
            return;
        }
        if (this.humanReadableLocation == HumanReadableLocation.TOP) {
            i = this.fontSize;
        } else {
            i = this.symbol_height + this.fontSize;
        }
        this.texts.add(new TextBox(0.0d, i, this.symbol_width, this.readable, this.humanReadableAlignment));
    }

    protected void resetPlotElements() {
        this.symbol_height = 0;
        this.symbol_width = 0;
        this.rectangles.clear();
        this.texts.clear();
        this.hexagons.clear();
        this.target.clear();
    }

    protected void mergeVerticalBlocks() {
        int size = this.rectangles.size();
        for (int size2 = this.rectangles.size() - 1; size2 >= 0; size2--) {
            Rectangle rectangle = this.rectangles.get(size2);
            int i = size2 - 1;
            while (true) {
                if (i >= 0) {
                    Rectangle rectangle2 = this.rectangles.get(i);
                    if (Doubles.roughlyEqual(rectangle.y, rectangle2.y + rectangle2.height)) {
                        if (!Doubles.roughlyEqual(rectangle.x, rectangle2.x) || !Doubles.roughlyEqual(rectangle.width, rectangle2.width)) {
                            if (rectangle2.x < rectangle.x) {
                                break;
                            }
                        } else {
                            rectangle2.height += rectangle.height;
                            this.rectangles.remove(size2);
                            break;
                        }
                    }
                    i--;
                }
            }
        }
        int size3 = this.rectangles.size();
        if (size != size3) {
            infoLine("Blocks Merged: " + size + " -> " + size3);
        }
    }

    private String hibcProcess(String str) {
        if (str.length() > 110) {
            throw new OkapiException("Data too long for HIBC LIC");
        }
        String upperCase = str.toUpperCase();
        if (!upperCase.matches("[A-Z0-9-\\. \\$/+\\%]+?")) {
            throw new OkapiException("Invalid characters in input");
        }
        int i = 41;
        for (int i2 = 0; i2 < upperCase.length(); i2++) {
            i += Arrays.positionOf(upperCase.charAt(i2), HIBC_CHAR_TABLE);
        }
        int i3 = i % 43;
        char c = HIBC_CHAR_TABLE[i3];
        infoLine("HIBC Check Digit Counter: " + i3);
        infoLine("HIBC Check Digit: " + c);
        return "+" + upperCase + c;
    }

    protected int[] getCodewords() {
        throw new UnsupportedOperationException();
    }

    protected int[] getPatternAsCodewords(int i) {
        if (i >= 10) {
            throw new IllegalArgumentException("Pattern groups of 10 or more digits are likely to be too large to parse as integers.");
        }
        String[] strArr = this.pattern;
        if (strArr == null || strArr.length == 0) {
            return new int[0];
        }
        int ceil = (int) Math.ceil(strArr[0].length() / i);
        int[] iArr = new int[this.pattern.length * ceil];
        int i2 = 0;
        while (true) {
            String[] strArr2 = this.pattern;
            if (i2 >= strArr2.length) {
                return iArr;
            }
            String str = strArr2[i2];
            int i3 = 0;
            while (i3 < ceil) {
                int i4 = i3 + 1;
                iArr[(i2 * ceil) + i3] = Integer.parseInt(str.substring(i3 * i, Math.min(i4 * i, str.length())));
                i3 = i4;
            }
            i2++;
        }
    }

    protected void info(char c) {
        this.encodeInfo.append(c);
    }

    protected void info(CharSequence charSequence) {
        this.encodeInfo.append(charSequence);
    }

    protected void infoSpace(int i) {
        StringBuilder sb = this.encodeInfo;
        sb.append(i);
        sb.append(' ');
    }

    protected void infoSpace(char c) {
        StringBuilder sb = this.encodeInfo;
        sb.append(c);
        sb.append(' ');
    }

    protected void infoLine(CharSequence charSequence) {
        StringBuilder sb = this.encodeInfo;
        sb.append(charSequence);
        sb.append('\n');
    }

    protected void infoLine() {
        this.encodeInfo.append('\n');
    }
}
