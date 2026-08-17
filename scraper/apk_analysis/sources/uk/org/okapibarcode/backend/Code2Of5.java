package uk.org.okapibarcode.backend;

import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.graphics.TextBox;

/* loaded from: classes4.dex */
public class Code2Of5 extends Symbol {
    private ToFMode mode = ToFMode.MATRIX;
    private double moduleWidthRatio = 3.0d;
    private static final String[] C25_MATRIX_TABLE = {"113311", "311131", "131131", "331111", "113131", "313111", "133111", "111331", "311311", "131311"};
    private static final String[] C25_INDUSTRIAL_TABLE = {"1111313111", "3111111131", "1131111131", "3131111111", "1111311131", "3111311111", "1131311111", "1111113131", "3111113111", "1131113111"};
    private static final String[] C25_INTERLEAVED_TABLE = {"11331", "31113", "13113", "33111", "11313", "31311", "13311", "11133", "31131", "13131"};

    public enum ToFMode {
        MATRIX,
        INDUSTRIAL,
        IATA,
        DATA_LOGIC,
        INTERLEAVED,
        INTERLEAVED_WITH_CHECK_DIGIT,
        ITF14,
        DP_LEITCODE,
        DP_IDENTCODE
    }

    public void setMode(ToFMode toFMode) {
        this.mode = toFMode;
    }

    public ToFMode getMode() {
        return this.mode;
    }

    public void setModuleWidthRatio(double d) {
        this.moduleWidthRatio = d;
    }

    public double getModuleWidthRatio() {
        return this.moduleWidthRatio;
    }

    /* renamed from: uk.org.okapibarcode.backend.Code2Of5$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode;

        static {
            int[] iArr = new int[ToFMode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode = iArr;
            try {
                iArr[ToFMode.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode[ToFMode.INDUSTRIAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode[ToFMode.IATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode[ToFMode.INTERLEAVED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode[ToFMode.INTERLEAVED_WITH_CHECK_DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode[ToFMode.DATA_LOGIC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode[ToFMode.ITF14.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode[ToFMode.DP_LEITCODE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode[ToFMode.DP_IDENTCODE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        switch (AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$Code2Of5$ToFMode[this.mode.ordinal()]) {
            case 1:
                dataMatrix();
                break;
            case 2:
                industrial();
                break;
            case 3:
                iata();
                break;
            case 4:
                interleaved(false);
                break;
            case 5:
                interleaved(true);
                break;
            case 6:
                dataLogic();
                break;
            case 7:
                itf14();
                break;
            case 8:
                deutschePostLeitcode();
                break;
            case 9:
                deutschePostIdentcode();
                break;
        }
    }

    private void dataMatrix() {
        if (!this.content.matches("[0-9]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        String str = "311111";
        for (int i = 0; i < this.content.length(); i++) {
            str = str + C25_MATRIX_TABLE[Character.getNumericValue(this.content.charAt(i))];
        }
        this.readable = this.content;
        this.pattern = new String[]{str + "31111"};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private void industrial() {
        if (!this.content.matches("[0-9]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        String str = "313111";
        for (int i = 0; i < this.content.length(); i++) {
            str = str + C25_INDUSTRIAL_TABLE[Character.getNumericValue(this.content.charAt(i))];
        }
        this.readable = this.content;
        this.pattern = new String[]{str + "31113"};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private void iata() {
        if (!this.content.matches("[0-9]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        String str = "1111";
        for (int i = 0; i < this.content.length(); i++) {
            str = str + C25_INDUSTRIAL_TABLE[Character.getNumericValue(this.content.charAt(i))];
        }
        this.readable = this.content;
        this.pattern = new String[]{str + "311"};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private void dataLogic() {
        if (!this.content.matches("[0-9]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        String str = "1111";
        for (int i = 0; i < this.content.length(); i++) {
            str = str + C25_MATRIX_TABLE[Character.getNumericValue(this.content.charAt(i))];
        }
        this.readable = this.content;
        this.pattern = new String[]{str + "311"};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private void interleaved(boolean z) {
        this.readable = this.content;
        if (!this.readable.matches("[0-9]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        if (z) {
            char checkDigit = checkDigit(this.readable, 1, 3);
            this.readable += checkDigit;
            infoLine("Check Digit: " + checkDigit);
        }
        if ((this.readable.length() & 1) != 0) {
            this.readable = "0" + this.readable;
        }
        String str = "1111";
        for (int i = 0; i < this.readable.length(); i += 2) {
            str = str + interlace(i, i + 1);
        }
        this.pattern = new String[]{str + "311"};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private String interlace(int i, int i2) {
        char charAt = this.readable.charAt(i);
        char charAt2 = this.readable.charAt(i2);
        String[] strArr = C25_INTERLEAVED_TABLE;
        String str = strArr[Character.getNumericValue(charAt)];
        String str2 = strArr[Character.getNumericValue(charAt2)];
        StringBuilder sb = new StringBuilder(10);
        for (int i3 = 0; i3 < 5; i3++) {
            sb.append(str.charAt(i3));
            sb.append(str2.charAt(i3));
        }
        return sb.toString();
    }

    private void itf14() {
        int length = this.content.length();
        if (!this.content.matches("[0-9]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        if (length > 13) {
            throw new OkapiException("Input data too long");
        }
        this.readable = "";
        while (length < 13) {
            this.readable += "0";
            length++;
        }
        this.readable += this.content;
        char checkDigit = checkDigit(this.readable, 1, 3);
        this.readable += checkDigit;
        infoLine("Check Digit: " + checkDigit);
        String str = "1111";
        for (int i = 0; i < this.readable.length(); i += 2) {
            str = str + interlace(i, i + 1);
        }
        this.pattern = new String[]{str + "311"};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private void deutschePostLeitcode() {
        int length = this.content.length();
        if (!this.content.matches("[0-9]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        if (length > 13) {
            throw new OkapiException("Input data too long");
        }
        this.readable = "";
        while (length < 13) {
            this.readable += "0";
            length++;
        }
        this.readable += this.content;
        char checkDigit = checkDigit(this.readable, 9, 4);
        this.readable += checkDigit;
        infoLine("Check digit: " + checkDigit);
        String str = "1111";
        for (int i = 0; i < this.readable.length(); i += 2) {
            str = str + interlace(i, i + 1);
        }
        this.pattern = new String[]{str + "311"};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private void deutschePostIdentcode() {
        int length = this.content.length();
        if (!this.content.matches("[0-9]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        if (length > 11) {
            throw new OkapiException("Input data too long");
        }
        this.readable = "";
        while (length < 11) {
            this.readable += "0";
            length++;
        }
        this.readable += this.content;
        char checkDigit = checkDigit(this.readable, 9, 4);
        this.readable += checkDigit;
        infoLine("Check Digit: " + checkDigit);
        String str = "1111";
        for (int i = 0; i < this.readable.length(); i += 2) {
            str = str + interlace(i, i + 1);
        }
        this.pattern = new String[]{str + "311"};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private static char checkDigit(String str, int i, int i2) {
        int charAt;
        int i3 = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            if ((length & 1) != 0) {
                charAt = (str.charAt(length) - '0') * i;
            } else {
                charAt = (str.charAt(length) - '0') * i2;
            }
            i3 += charAt;
        }
        return (char) (((10 - (i3 % 10)) % 10) + 48);
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        int i;
        resetPlotElements();
        char c = 0;
        int theoreticalHumanReadableHeight = this.humanReadableLocation == HumanReadableLocation.TOP ? getTheoreticalHumanReadableHeight() : 0;
        int i2 = this.mode == ToFMode.ITF14 ? 20 : 0;
        double d = 0.0d;
        boolean z = true;
        int i3 = 0;
        int i4 = 0;
        double d2 = 0.0d;
        while (i3 < this.pattern[c].length()) {
            double moduleWidth = getModuleWidth(this.pattern[c].charAt(i3) - '0') * this.moduleWidth;
            if (z) {
                if (this.row_height[c] == -1) {
                    i4 = this.default_height;
                } else {
                    i4 = this.row_height[c];
                }
                if (moduleWidth != d && i4 != 0) {
                    this.rectangles.add(new Rectangle(d2 + i2, theoreticalHumanReadableHeight, moduleWidth, i4));
                }
                this.symbol_width = (int) Math.ceil(d2 + moduleWidth + (i2 * 2));
            }
            z = !z;
            d2 += moduleWidth;
            i3++;
            c = 0;
            d = 0.0d;
        }
        this.symbol_height = i4;
        if (this.mode == ToFMode.ITF14) {
            double d3 = theoreticalHumanReadableHeight;
            Rectangle rectangle = new Rectangle(0.0d, d3, this.symbol_width, 4.0d);
            Rectangle rectangle2 = new Rectangle(0.0d, (theoreticalHumanReadableHeight + this.symbol_height) - 4, this.symbol_width, 4.0d);
            Rectangle rectangle3 = new Rectangle(0.0d, d3, 4.0d, this.symbol_height);
            Rectangle rectangle4 = new Rectangle(this.symbol_width - 4, d3, 4.0d, this.symbol_height);
            this.rectangles.add(rectangle);
            this.rectangles.add(rectangle2);
            this.rectangles.add(rectangle3);
            this.rectangles.add(rectangle4);
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

    @Override // uk.org.okapibarcode.backend.Symbol
    protected double getModuleWidth(int i) {
        if (i == 1) {
            return 1.0d;
        }
        return this.moduleWidthRatio;
    }
}
