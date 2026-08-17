package uk.org.okapibarcode.backend;

import com.fasterxml.jackson.core.JsonPointer;
import org.apache.commons.lang3.ClassUtils;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class Codabar extends Symbol {
    private double moduleWidthRatio = 2.0d;
    private static final String[] CODABAR_TABLE = {"11111221", "11112211", "11121121", "22111111", "11211211", "21111211", "12111121", "12112111", "12211111", "21121111", "11122111", "11221111", "21112121", "21211121", "21212111", "11212121", "11221211", "12121121", "11121221", "11122211"};
    private static final char[] CHARACTER_SET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '$', ':', JsonPointer.SEPARATOR, ClassUtils.PACKAGE_SEPARATOR_CHAR, '+', 'A', 'B', 'C', 'D'};

    public void setModuleWidthRatio(double d) {
        this.moduleWidthRatio = d;
    }

    public double getModuleWidthRatio() {
        return this.moduleWidthRatio;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[A-D]{1}[0-9:/\\$\\.\\+-]+[A-D]{1}")) {
            throw new OkapiException("Invalid characters in input");
        }
        int length = this.content.length();
        String str = "";
        for (int i = 0; i < length; i++) {
            str = str + CODABAR_TABLE[Arrays.positionOf(this.content.charAt(i), CHARACTER_SET)];
        }
        this.readable = this.content;
        this.pattern = new String[]{str};
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

    @Override // uk.org.okapibarcode.backend.Symbol
    protected int[] getCodewords() {
        return getPatternAsCodewords(8);
    }
}
