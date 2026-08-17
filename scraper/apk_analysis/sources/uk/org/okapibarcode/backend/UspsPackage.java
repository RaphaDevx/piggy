package uk.org.okapibarcode.backend;

import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.graphics.TextBox;

/* loaded from: classes4.dex */
public class UspsPackage extends Symbol {
    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[0-9\\[\\]]+")) {
            throw new OkapiException("Invalid IMpb data");
        }
        if (this.content.length() % 2 != 0) {
            throw new OkapiException("Invalid IMpb data");
        }
        Code128 code128 = new Code128();
        code128.unsetCc();
        code128.setDataType(Symbol.DataType.GS1);
        code128.setContent(this.content);
        boolean z = this.content.length() >= 5 && this.content.charAt(0) == '[' && this.content.charAt(1) == '4' && this.content.charAt(2) == '2' && this.content.charAt(3) == '0' && this.content.charAt(4) == ']';
        StringBuilder sb = new StringBuilder();
        int indexOf = z ? this.content.indexOf(91, 5) : 0;
        if (indexOf != -1) {
            int i = 0;
            while (indexOf < this.content.length()) {
                char charAt = this.content.charAt(indexOf);
                if (charAt >= '0' && charAt <= '9') {
                    sb.append(charAt);
                    i++;
                    if (i % 4 == 0) {
                        sb.append(' ');
                    }
                }
                indexOf++;
            }
        }
        this.encodeInfo = code128.encodeInfo;
        this.readable = sb.toString();
        this.pattern = new String[]{code128.pattern[0]};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        int i;
        boolean z;
        resetPlotElements();
        int i2 = this.default_height;
        boolean z2 = true;
        int i3 = 0;
        int i4 = 0;
        for (char c = 0; i3 < this.pattern[c].length(); c = 0) {
            int charAt = this.pattern[c].charAt(i3) - '0';
            if (z2) {
                z = z2;
                i = i2;
                this.rectangles.add(new Rectangle(i4 + 20, 15, charAt, i2));
                this.symbol_width = i4 + charAt + 40;
            } else {
                i = i2;
                z = z2;
            }
            z2 = !z;
            i4 += charAt;
            i3++;
            i2 = i;
        }
        this.symbol_height = i2 + 30;
        Rectangle rectangle = new Rectangle(0.0d, 0.0d, this.symbol_width, 2.0d);
        Rectangle rectangle2 = new Rectangle(0.0d, this.symbol_height - 2, this.symbol_width, 2.0d);
        this.rectangles.add(rectangle);
        this.rectangles.add(rectangle2);
        this.texts.add(new TextBox(0.0d, 12.0d, this.symbol_width, "USPS TRACKING #", this.humanReadableAlignment));
        this.texts.add(new TextBox(0.0d, this.symbol_height - 6.0d, this.symbol_width, this.readable, this.humanReadableAlignment));
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    public int getHeight() {
        return this.symbol_height;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected int[] getCodewords() {
        return getPatternAsCodewords(6);
    }
}
