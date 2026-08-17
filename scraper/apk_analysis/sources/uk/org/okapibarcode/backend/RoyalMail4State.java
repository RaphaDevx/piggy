package uk.org.okapibarcode.backend;

import androidx.exifinterface.media.ExifInterface;
import java.util.Locale;
import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class RoyalMail4State extends Symbol {
    private static final String[] ROYAL_TABLE = {"TTFF", "TDAF", "TDFA", "DTAF", "DTFA", "DDAA", "TADF", "TFTF", "TFDA", "DATF", "DADA", "DFTA", "TAFD", "TFAD", "TFFT", "DAAD", "DAFT", "DFAT", "ATDF", "ADTF", "ADDA", "FTTF", "FTDA", "FDTA", "ATFD", "ADAD", "ADFT", "FTAD", "FTFT", "FDAT", "AADD", "AFTD", "AFDT", "FATD", "FADT", "FFTT"};
    private static final int[][] ROYAL_VALUES = {new int[]{1, 1}, new int[]{1, 2}, new int[]{1, 3}, new int[]{1, 4}, new int[]{1, 5}, new int[]{1, 0}, new int[]{2, 1}, new int[]{2, 2}, new int[]{2, 3}, new int[]{2, 4}, new int[]{2, 5}, new int[]{2, 0}, new int[]{3, 1}, new int[]{3, 2}, new int[]{3, 3}, new int[]{3, 4}, new int[]{3, 5}, new int[]{3, 0}, new int[]{4, 1}, new int[]{4, 2}, new int[]{4, 3}, new int[]{4, 4}, new int[]{4, 5}, new int[]{4, 0}, new int[]{5, 1}, new int[]{5, 2}, new int[]{5, 3}, new int[]{5, 4}, new int[]{5, 5}, new int[]{5, 0}, new int[]{0, 1}, new int[]{0, 2}, new int[]{0, 3}, new int[]{0, 4}, new int[]{0, 5}, new int[]{0, 0}};
    private static final char[] KR_SET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        this.content = this.content.toUpperCase(Locale.ENGLISH);
        if (!this.content.matches("[0-9A-Z]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        StringBuilder sb = new StringBuilder(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.content.length(); i3++) {
            int positionOf = Arrays.positionOf(this.content.charAt(i3), KR_SET);
            sb.append(ROYAL_TABLE[positionOf]);
            int[] iArr = ROYAL_VALUES[positionOf];
            i += iArr[0];
            i2 += iArr[1];
        }
        int i4 = (i % 6) - 1;
        int i5 = (i2 % 6) - 1;
        if (i4 == -1) {
            i4 = 5;
        }
        if (i5 == -1) {
            i5 = 5;
        }
        int i6 = (i4 * 6) + i5;
        sb.append(ROYAL_TABLE[i6]);
        infoLine("Check Digit: " + i6);
        sb.append('F');
        infoLine("Encoding: " + ((Object) sb));
        this.readable = "";
        this.pattern = new String[]{sb.toString()};
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
