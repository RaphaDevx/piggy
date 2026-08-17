package uk.org.okapibarcode.backend;

import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class Code11 extends Symbol {
    private Character startDelimiter;
    private Character stopDelimiter;
    private static final String[] CODE_11_TABLE = {"111121", "211121", "121121", "221111", "112121", "212111", "122111", "111221", "211211", "211111", "112111"};
    private static final char[] CHARACTER_SET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'};
    private double moduleWidthRatio = 2.0d;
    private int checkDigitCount = 2;

    public void setModuleWidthRatio(double d) {
        this.moduleWidthRatio = d;
    }

    public double getModuleWidthRatio() {
        return this.moduleWidthRatio;
    }

    public void setCheckDigitCount(int i) {
        if (i < 1 || i > 2) {
            throw new IllegalArgumentException("Check digit count must be 1 or 2.");
        }
        this.checkDigitCount = i;
    }

    public int getCheckDigitCount() {
        return this.checkDigitCount;
    }

    public void setStartDelimiter(Character ch2) {
        this.startDelimiter = ch2;
    }

    public Character getStartDelimiter() {
        return this.startDelimiter;
    }

    public void setStopDelimiter(Character ch2) {
        this.stopDelimiter = ch2;
    }

    public Character getStopDelimiter() {
        return this.stopDelimiter;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[0-9-]+")) {
            throw new OkapiException("Invalid characters in input");
        }
        String str = this.content;
        int length = this.content.length();
        int i = length + 1;
        int[] iArr = new int[i];
        String str2 = "112211";
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = Arrays.positionOf(this.content.charAt(i2), CHARACTER_SET);
            str2 = str2 + CODE_11_TABLE[iArr[i2]];
        }
        int checkDigitC = getCheckDigitC(iArr, length);
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        String[] strArr = CODE_11_TABLE;
        sb.append(strArr[checkDigitC]);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str);
        char[] cArr = CHARACTER_SET;
        sb3.append(cArr[checkDigitC]);
        String sb4 = sb3.toString();
        infoLine("Check Digit C: " + checkDigitC);
        if (this.checkDigitCount == 2) {
            iArr[length] = checkDigitC;
            int checkDigitK = getCheckDigitK(iArr, i);
            sb2 = sb2 + strArr[checkDigitK];
            sb4 = sb4 + cArr[checkDigitK];
            infoLine("Check Digit K: " + checkDigitK);
        }
        String str3 = sb2 + "112211";
        this.readable = sb4;
        if (this.startDelimiter != null) {
            this.readable = this.startDelimiter + this.readable;
        }
        if (this.stopDelimiter != null) {
            this.readable += this.stopDelimiter;
        }
        this.pattern = new String[]{str3};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private static int getCheckDigitC(int[] iArr, int i) {
        int i2 = 0;
        int i3 = 1;
        for (int i4 = i - 1; i4 >= 0; i4--) {
            i2 += iArr[i4] * i3;
            i3++;
            if (i3 > 10) {
                i3 = 1;
            }
        }
        return i2 % 11;
    }

    private static int getCheckDigitK(int[] iArr, int i) {
        int i2 = 0;
        int i3 = 1;
        for (int i4 = i - 1; i4 >= 0; i4--) {
            i2 += iArr[i4] * i3;
            i3++;
            if (i3 > 9) {
                i3 = 1;
            }
        }
        return i2 % 11;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected double getModuleWidth(int i) {
        if (i == 1) {
            return 1.0d;
        }
        return this.moduleWidthRatio;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected int[] getCodewords() {
        return getPatternAsCodewords(6);
    }
}
