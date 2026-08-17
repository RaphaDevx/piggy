package uk.org.okapibarcode.backend;

import java.util.Locale;
import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class KixCode extends Symbol {
    private static final String[] ROYAL_TABLE = {"TTFF", "TDAF", "TDFA", "DTAF", "DTFA", "DDAA", "TADF", "TFTF", "TFDA", "DATF", "DADA", "DFTA", "TAFD", "TFAD", "TFFT", "DAAD", "DAFT", "DFAT", "ATDF", "ADTF", "ADDA", "FTTF", "FTDA", "FDTA", "ATFD", "ADAD", "ADFT", "FTAD", "FTFT", "FDAT", "AADD", "AFTD", "AFDT", "FATD", "FADT", "FFTT"};
    private static final char[] KR_SET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        this.content = this.content.toUpperCase(Locale.ENGLISH);
        if (!this.content.matches("[0-9A-Z]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        StringBuilder sb = new StringBuilder(this.content.length());
        for (int i = 0; i < this.content.length(); i++) {
            sb.append(ROYAL_TABLE[Arrays.positionOf(this.content.charAt(i), KR_SET)]);
        }
        String sb2 = sb.toString();
        infoLine("Encoding: " + sb2);
        this.readable = "";
        this.pattern = new String[]{sb2};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        int i;
        resetPlotElements();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int i5 = 8;
            if (i3 < this.pattern[i2].length()) {
                char charAt = this.pattern[i2].charAt(i3);
                if (charAt != 'A') {
                    i = 3;
                    if (charAt != 'D') {
                        if (charAt == 'F') {
                            i = i2;
                        } else {
                            if (charAt != 'T') {
                                throw new IllegalStateException("Unknown pattern character: " + charAt);
                            }
                            i5 = 2;
                        }
                        this.rectangles.add(new Rectangle(i4, i, 1, i5));
                        i4 += 2;
                        i3++;
                        i2 = 0;
                    }
                } else {
                    i = i2;
                }
                i5 = 5;
                this.rectangles.add(new Rectangle(i4, i, 1, i5));
                i4 += 2;
                i3++;
                i2 = 0;
            } else {
                this.symbol_width = ((this.pattern[0].length() - 1) * 2) + 1;
                this.symbol_height = 8;
                return;
            }
        }
    }
}
