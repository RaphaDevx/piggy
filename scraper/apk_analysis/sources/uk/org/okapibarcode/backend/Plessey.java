package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class Plessey extends Symbol {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean checkDigitInHumanReadableText = true;
    private double moduleWidthRatio = 2.0d;

    public void setCheckDigitInHumanReadableText(boolean z) {
        this.checkDigitInHumanReadableText = z;
    }

    public boolean getCheckDigitInHumanReadableText() {
        return this.checkDigitInHumanReadableText;
    }

    public void setModuleWidthRatio(double d) {
        this.moduleWidthRatio = d;
    }

    public double getModuleWidthRatio() {
        return this.moduleWidthRatio;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[0-9A-F]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        int length = this.content.length();
        StringBuilder sb = new StringBuilder((length * 8) + 35);
        sb.append("21211221");
        int i = (length + 2) * 4;
        int[] iArr = new int[i];
        String[] strArr = {"12", "21"};
        for (int i2 = 0; i2 < length; i2++) {
            int numericValue = Character.getNumericValue(this.content.charAt(i2));
            int i3 = numericValue & 1;
            sb.append(strArr[i3]);
            int i4 = (numericValue >> 1) & 1;
            sb.append(strArr[i4]);
            int i5 = (numericValue >> 2) & 1;
            sb.append(strArr[i5]);
            int i6 = 1 & (numericValue >> 3);
            sb.append(strArr[i6]);
            int i7 = i2 * 4;
            iArr[i7] = i3;
            iArr[i7 + 1] = i4;
            iArr[i7 + 2] = i5;
            iArr[i7 + 3] = i6;
        }
        int[] iArr2 = {1, 1, 1, 1, 0, 1, 0, 0, 1};
        for (int i8 = 0; i8 < length * 4; i8++) {
            if (iArr[i8] == 1) {
                for (int i9 = 0; i9 < 9; i9++) {
                    int i10 = i8 + i9;
                    iArr[i10] = iArr[i10] ^ iArr2[i9];
                }
            }
        }
        int i11 = i - 8;
        for (int i12 = i11; i12 < i; i12++) {
            sb.append(strArr[iArr[i12]]);
        }
        sb.append("20121211212");
        this.readable = this.content;
        if (this.checkDigitInHumanReadableText) {
            int i13 = iArr[i11] | (iArr[i - 7] << 1) | (iArr[i - 6] << 2) | (iArr[i - 5] << 3);
            int i14 = (iArr[i - 1] << 3) | iArr[i - 4] | (iArr[i - 3] << 1) | (iArr[i - 2] << 2);
            this.readable += Integer.toHexString(i13).toUpperCase();
            this.readable += Integer.toHexString(i14).toUpperCase();
        }
        this.pattern = new String[]{sb.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected double getModuleWidth(int i) {
        if (i == 1) {
            return 1.0d;
        }
        if (i == 0) {
            return 0.0d;
        }
        return this.moduleWidthRatio;
    }
}
