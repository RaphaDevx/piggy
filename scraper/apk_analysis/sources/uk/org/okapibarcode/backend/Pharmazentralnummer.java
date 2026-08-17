package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class Pharmazentralnummer extends Symbol {
    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int length = this.content.length();
        if (length > 7) {
            throw new OkapiException("Input data too long");
        }
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        StringBuilder sb = new StringBuilder();
        sb.append('-');
        int i = 8 - length;
        for (int i2 = 1; i2 < i; i2++) {
            sb.append('0');
        }
        sb.append(this.content);
        int i3 = 0;
        for (int i4 = 1; i4 < 8; i4++) {
            i3 += Character.getNumericValue(sb.charAt(i4)) * i4;
        }
        int i5 = i3 % 11;
        if (i5 == 10) {
            throw new OkapiException("Not a valid PZN identifier, check digit is 10");
        }
        infoLine("Check Digit: " + i5);
        sb.append((char) (i5 + 48));
        Code3Of9 code3Of9 = new Code3Of9();
        code3Of9.setContent(sb.toString());
        this.readable = "PZN" + ((Object) sb);
        this.pattern = new String[1];
        this.pattern[0] = code3Of9.pattern[0];
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }
}
