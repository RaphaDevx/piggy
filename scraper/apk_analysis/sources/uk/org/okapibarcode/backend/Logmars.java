package uk.org.okapibarcode.backend;

import com.fasterxml.jackson.core.JsonPointer;
import org.apache.commons.lang3.ClassUtils;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class Logmars extends Symbol {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String[] CODE39LM = {"1113313111", "3113111131", "1133111131", "3133111111", "1113311131", "3113311111", "1133311111", "1113113131", "3113113111", "1133113111", "3111131131", "1131131131", "3131131111", "1111331131", "3111331111", "1131331111", "1111133131", "3111133111", "1131133111", "1111333111", "3111111331", "1131111331", "3131111311", "1111311331", "3111311311", "1131311311", "1111113331", "3111113311", "1131113311", "1111313311", "3311111131", "1331111131", "3331111111", "1311311131", "3311311111", "1331311111", "1311113131", "3311113111", "1331113111", "1313131111", "1313111311", "1311131311", "1113131311"};
    private static final char[] LOOKUP = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-', ClassUtils.PACKAGE_SEPARATOR_CHAR, ' ', '$', JsonPointer.SEPARATOR, '+', '%'};
    private double moduleWidthRatio = 3.0d;

    public void setModuleWidthRatio(double d) {
        this.moduleWidthRatio = d;
    }

    public double getModuleWidthRatio() {
        return this.moduleWidthRatio;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected double getModuleWidth(int i) {
        if (i == 1) {
            return 1.0d;
        }
        return this.moduleWidthRatio;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[0-9A-Z\\. \\-$/+%]*")) {
            throw new OkapiException("Invalid characters in data");
        }
        StringBuilder sb = new StringBuilder((this.content.length() * 10) + 29);
        sb.append("1311313111");
        int i = 0;
        for (int i2 = 0; i2 < this.content.length(); i2++) {
            int positionOf = Arrays.positionOf(this.content.charAt(i2), LOOKUP);
            i += positionOf;
            sb.append(CODE39LM[positionOf]);
        }
        int i3 = i % 43;
        char c = LOOKUP[i3];
        infoLine("Check Digit: " + c);
        sb.append(CODE39LM[i3]);
        sb.append("131131311");
        this.readable = this.content + c;
        this.pattern = new String[]{sb.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }
}
