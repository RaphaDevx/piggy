package uk.org.okapibarcode.backend;

import uk.org.okapibarcode.backend.Symbol;

/* loaded from: classes4.dex */
public class Nve18 extends Symbol {
    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (this.content.length() > 17) {
            throw new OkapiException("Input data too long");
        }
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        StringBuilder sb = new StringBuilder();
        int length = 17 - this.content.length();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        sb.append(this.content);
        int i2 = 0;
        int i3 = 0;
        for (int length2 = sb.length() - 1; length2 >= 0; length2--) {
            int numericValue = Character.getNumericValue(sb.charAt(length2));
            if (i3 % 2 == 0) {
                numericValue *= 3;
            }
            i2 += numericValue;
            i3++;
        }
        int i4 = 10 - (i2 % 10);
        int i5 = i4 != 10 ? i4 : 0;
        infoLine("NVE Check Digit: " + i5);
        this.content = "[00]" + ((Object) sb) + i5;
        Code128 code128 = new Code128();
        code128.setDataType(Symbol.DataType.GS1);
        code128.setHumanReadableLocation(this.humanReadableLocation);
        code128.setContent(this.content);
        this.readable = code128.readable;
        this.pattern = code128.pattern;
        this.row_count = code128.row_count;
        this.row_height = code128.row_height;
        this.symbol_height = code128.symbol_height;
        this.symbol_width = code128.symbol_width;
        this.rectangles = code128.rectangles;
        this.texts = code128.texts;
        info(code128.encodeInfo);
    }
}
