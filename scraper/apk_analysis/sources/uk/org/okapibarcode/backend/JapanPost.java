package uk.org.okapibarcode.backend;

import java.util.Locale;
import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class JapanPost extends Symbol {
    private static final String[] JAPAN_TABLE = {"FFT", "FDA", "DFA", "FAD", "FTF", "DAF", "AFD", "ADF", "TFF", "FTT", "TFT", "DAT", "DTA", "ADT", "TDA", "ATD", "TAD", "TTF", "FFF"};
    private static final char[] KASUT_SET = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    private static final char[] CH_KASUT_SET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        this.content = this.content.toUpperCase(Locale.ENGLISH);
        if (!this.content.matches("[0-9A-Z\\-]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.content.length() && sb.length() < 20; i++) {
            char charAt = this.content.charAt(i);
            if ((charAt >= '0' && charAt <= '9') || charAt == '-') {
                sb.append(charAt);
            } else if (charAt >= 'A' && charAt <= 'J') {
                sb.append('a');
                sb.append(CH_KASUT_SET[charAt - 'A']);
            } else if (charAt >= 'K' && charAt <= 'O') {
                sb.append('b');
                sb.append(CH_KASUT_SET[charAt - 'K']);
            } else if (charAt >= 'U' && charAt <= 'Z') {
                sb.append('c');
                sb.append(CH_KASUT_SET[charAt - 'U']);
            }
        }
        for (int length = sb.length(); length < 20; length++) {
            sb.append('d');
        }
        StringBuilder sb2 = new StringBuilder("FD");
        int i2 = 0;
        for (int i3 = 0; i3 < 20; i3++) {
            sb2.append(JAPAN_TABLE[Arrays.positionOf(sb.charAt(i3), KASUT_SET)]);
            i2 += Arrays.positionOf(sb.charAt(i3), CH_KASUT_SET);
        }
        int i4 = 19 - (i2 % 19);
        int i5 = i4 != 19 ? i4 : 0;
        sb2.append(JAPAN_TABLE[Arrays.positionOf(CH_KASUT_SET[i5], KASUT_SET)]);
        sb2.append("DF");
        infoLine("Encoding: " + ((Object) sb2));
        infoLine("Check Digit: " + i5);
        this.readable = "";
        this.pattern = new String[]{sb2.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        resetPlotElements();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i < this.pattern[i5].length(); i5 = 0) {
            char charAt = this.pattern[i5].charAt(i);
            if (charAt != 'A') {
                if (charAt == 'D') {
                    i4 = 5;
                } else if (charAt == 'F') {
                    i3 = i5;
                    i4 = 8;
                } else if (charAt == 'T') {
                    i4 = 2;
                }
                i3 = 3;
            } else {
                i3 = i5;
                i4 = 5;
            }
            this.rectangles.add(new Rectangle(i2, i3, 1, i4));
            i2 += 2;
            i++;
        }
        this.symbol_width = this.pattern[0].length() * 2;
        this.symbol_height = 8;
    }
}
