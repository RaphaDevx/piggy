package uk.org.okapibarcode.backend;

import com.fasterxml.jackson.core.JsonPointer;
import org.apache.commons.lang3.ClassUtils;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class Code3Of9 extends Symbol {
    private static final String[] CODE_39 = {"1112212111", "2112111121", "1122111121", "2122111111", "1112211121", "2112211111", "1122211111", "1112112121", "2112112111", "1122112111", "2111121121", "1121121121", "2121121111", "1111221121", "2111221111", "1121221111", "1111122121", "2111122111", "1121122111", "1111222111", "2111111221", "1121111221", "2121111211", "1111211221", "2111211211", "1121211211", "1111112221", "2111112211", "1121112211", "1111212211", "2211111121", "1221111121", "2221111111", "1211211121", "2211211111", "1221211111", "1211112121", "2211112111", "1221112111", "1212121111", "1212111211", "1211121211", "1112121211"};
    private static final char[] LOOKUP = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-', ClassUtils.PACKAGE_SEPARATOR_CHAR, ' ', '$', JsonPointer.SEPARATOR, '+', '%'};
    private CheckDigit checkOption = CheckDigit.NONE;
    private double moduleWidthRatio = 2.0d;

    public enum CheckDigit {
        NONE,
        MOD43
    }

    public void setModuleWidthRatio(double d) {
        this.moduleWidthRatio = d;
    }

    public double getModuleWidthRatio() {
        return this.moduleWidthRatio;
    }

    public void setCheckDigit(CheckDigit checkDigit) {
        this.checkOption = checkDigit;
    }

    public CheckDigit getCheckDigit() {
        return this.checkOption;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[0-9A-Z\\. \\-$/+%]*")) {
            throw new OkapiException("Invalid characters in input");
        }
        StringBuilder sb = new StringBuilder(19 + (this.content.length() * 10) + (this.checkOption != CheckDigit.MOD43 ? 0 : 10));
        sb.append("1211212111");
        int i = 0;
        for (int i2 = 0; i2 < this.content.length(); i2++) {
            int positionOf = Arrays.positionOf(this.content.charAt(i2), LOOKUP);
            sb.append(CODE_39[positionOf]);
            i += positionOf;
        }
        char c = ' ';
        if (this.checkOption == CheckDigit.MOD43) {
            char[] cArr = LOOKUP;
            char c2 = cArr[i % 43];
            sb.append(CODE_39[Arrays.positionOf(c2, cArr)]);
            if (c2 == ' ') {
                c2 = '_';
            }
            c = c2;
            infoLine("Check Digit: " + c);
        }
        sb.append("121121211");
        if (this.checkOption == CheckDigit.MOD43) {
            this.readable = "*" + this.content + c + "*";
        } else {
            this.readable = "*" + this.content + "*";
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
        return this.moduleWidthRatio;
    }
}
