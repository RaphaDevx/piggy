package uk.org.okapibarcode.backend;

import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.graphics.TextBox;

/* loaded from: classes4.dex */
public class Postnet extends Symbol {
    private Mode mode = Mode.POSTNET;
    private double moduleWidthRatio = 1.5d;
    private static final String[] PN_TABLE = {"LLSSS", "SSSLL", "SSLSL", "SSLLS", "SLSSL", "SLSLS", "SLLSS", "LSSSL", "LSSLS", "LSLSS"};
    private static final String[] PL_TABLE = {"SSLLL", "LLLSS", "LLSLS", "LLSSL", "LSLLS", "LSLSL", "LSSLL", "SLLLS", "SLLSL", "SLSLL"};

    public enum Mode {
        PLANET,
        POSTNET
    }

    public Postnet() {
        this.default_height = 12;
        this.humanReadableLocation = HumanReadableLocation.NONE;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setModuleWidthRatio(double d) {
        this.moduleWidthRatio = d;
    }

    public double getModuleWidthRatio() {
        return this.moduleWidthRatio;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        encode(this.mode == Mode.POSTNET ? PN_TABLE : PL_TABLE);
    }

    private void encode(String[] strArr) {
        if (this.content.length() > 38) {
            throw new OkapiException("Input data too long");
        }
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in input");
        }
        String str = "L";
        int i = 0;
        for (int i2 = 0; i2 < this.content.length(); i2++) {
            str = str + strArr[this.content.charAt(i2) - '0'];
            i += this.content.charAt(i2) - '0';
        }
        int i3 = (10 - (i % 10)) % 10;
        infoLine("Check Digit: " + i3);
        String str2 = (str + strArr[i3]) + "L";
        infoLine("Encoding: " + str2);
        this.readable = this.content;
        this.pattern = new String[]{str2};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        int i;
        double d;
        double d2;
        resetPlotElements();
        int theoreticalHumanReadableHeight = this.humanReadableLocation == HumanReadableLocation.TOP ? getTheoreticalHumanReadableHeight() : 0;
        double d3 = this.moduleWidth;
        double d4 = (this.moduleWidthRatio + 1.0d) * d3;
        int i2 = (int) (this.default_height * 0.4d);
        int i3 = 0;
        double d5 = 0.0d;
        for (char c = 0; i3 < this.pattern[c].length(); c = 0) {
            if (this.pattern[c].charAt(i3) == 'L') {
                d = theoreticalHumanReadableHeight;
                d2 = this.default_height;
            } else {
                d = (this.default_height + theoreticalHumanReadableHeight) - i2;
                d2 = i2;
            }
            this.rectangles.add(new Rectangle(d5, d, d3, d2));
            d5 += d4;
            i3++;
            theoreticalHumanReadableHeight = theoreticalHumanReadableHeight;
        }
        this.symbol_width = (int) Math.ceil(((this.pattern[0].length() - 1) * d4) + d3);
        this.symbol_height = this.default_height;
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
}
