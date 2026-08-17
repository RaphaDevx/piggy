package uk.org.okapibarcode.backend;

import androidx.compose.runtime.ComposerKt;
import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes4.dex */
public class DataBarLimited extends Symbol {
    private boolean linkageFlag;
    private static final int[] T_EVEN_LTD = {28, 728, 6454, ComposerKt.providerValuesKey, 2408, 1, 16632};
    private static final int[] MODULES_ODD_LTD = {17, 13, 9, 15, 11, 19, 7};
    private static final int[] MODULES_EVEN_LTD = {9, 13, 17, 11, 15, 7, 19};
    private static final int[] WIDEST_ODD_LTD = {6, 5, 3, 5, 4, 8, 1};
    private static final int[] WIDEST_EVEN_LTD = {3, 4, 6, 4, 5, 1, 8};
    private static final int[] CHECKSUM_WEIGHT_LTD = {1, 3, 9, 27, 81, 65, 17, 51, 64, 14, 42, 37, 22, 66, 20, 60, 2, 6, 18, 54, 73, 41, 34, 13, 39, 28, 84, 74};
    private static final int[] FINDER_PATTERN_LTD = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 3, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 3, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 3, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 3, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 3, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 
    1, 1, 1, 1, 2, 3, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 3, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 3, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 3, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 3, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 1, 2, 1, 1};

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return false;
    }

    protected void setLinkageFlag() {
        this.linkageFlag = true;
    }

    protected void unsetLinkageFlag() {
        this.linkageFlag = false;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int[] iArr = new int[14];
        int[] iArr2 = new int[46];
        if (this.content.length() > 13) {
            throw new OkapiException("Input too long");
        }
        if (!this.content.matches("[0-9]+?")) {
            throw new OkapiException("Invalid characters in input");
        }
        if (this.content.length() == 13 && this.content.charAt(0) != '0' && this.content.charAt(0) != '1') {
            throw new OkapiException("Input out of range");
        }
        BigInteger bigInteger = new BigInteger(this.content);
        if (this.linkageFlag) {
            bigInteger = bigInteger.add(new BigInteger("2015133531096"));
        }
        BigInteger divide = bigInteger.divide(new BigInteger("2013571"));
        BigInteger mod = bigInteger.mod(new BigInteger("2013571"));
        int i = divide.compareTo(new BigInteger("183063")) == 1 ? 1 : 0;
        if (divide.compareTo(new BigInteger("820063")) == 1) {
            i = 2;
        }
        if (divide.compareTo(new BigInteger("1000775")) == 1) {
            i = 3;
        }
        if (divide.compareTo(new BigInteger("1491020")) == 1) {
            i = 4;
        }
        if (divide.compareTo(new BigInteger("1979844")) == 1) {
            i = 5;
        }
        if (divide.compareTo(new BigInteger("1996938")) == 1) {
            i = 6;
        }
        int i2 = mod.compareTo(new BigInteger("183063")) == 1 ? 1 : 0;
        if (mod.compareTo(new BigInteger("820063")) == 1) {
            i2 = 2;
        }
        if (mod.compareTo(new BigInteger("1000775")) == 1) {
            i2 = 3;
        }
        if (mod.compareTo(new BigInteger("1491020")) == 1) {
            i2 = 4;
        }
        if (mod.compareTo(new BigInteger("1979844")) == 1) {
            i2 = 5;
        }
        if (mod.compareTo(new BigInteger("1996938")) == 1) {
            i2 = 6;
        }
        infoLine("Data Characters: " + (i + 1) + StringUtils.SPACE + (i2 + 1));
        switch (i) {
            case 1:
                divide = divide.subtract(new BigInteger("183064"));
                break;
            case 2:
                divide = divide.subtract(new BigInteger("820064"));
                break;
            case 3:
                divide = divide.subtract(new BigInteger("1000776"));
                break;
            case 4:
                divide = divide.subtract(new BigInteger("1491021"));
                break;
            case 5:
                divide = divide.subtract(new BigInteger("1979845"));
                break;
            case 6:
                divide = divide.subtract(new BigInteger("1996939"));
                break;
        }
        switch (i2) {
            case 1:
                mod = mod.subtract(new BigInteger("183064"));
                break;
            case 2:
                mod = mod.subtract(new BigInteger("820064"));
                break;
            case 3:
                mod = mod.subtract(new BigInteger("1000776"));
                break;
            case 4:
                mod = mod.subtract(new BigInteger("1491021"));
                break;
            case 5:
                mod = mod.subtract(new BigInteger("1979845"));
                break;
            case 6:
                mod = mod.subtract(new BigInteger("1996939"));
                break;
        }
        int intValue = divide.intValue();
        int intValue2 = mod.intValue();
        int[] iArr3 = T_EVEN_LTD;
        int i3 = iArr3[i];
        int i4 = intValue / i3;
        int i5 = intValue % i3;
        int i6 = iArr3[i2];
        int i7 = intValue2 / i6;
        int i8 = intValue2 % i6;
        int[] iArr4 = MODULES_ODD_LTD;
        int i9 = iArr4[i];
        int[] iArr5 = WIDEST_ODD_LTD;
        int[] widths = getWidths(i4, i9, 7, iArr5[i], 1);
        int i10 = widths[0];
        int i11 = widths[1];
        int i12 = widths[2];
        int i13 = widths[3];
        int i14 = widths[4];
        int i15 = widths[5];
        int i16 = widths[6];
        int[] iArr6 = MODULES_EVEN_LTD;
        int i17 = iArr6[i];
        int[] iArr7 = WIDEST_EVEN_LTD;
        int[] widths2 = getWidths(i5, i17, 7, iArr7[i], 0);
        int[] iArr8 = {i10, widths2[0], i11, widths2[1], i12, widths2[2], i13, widths2[3], i14, widths2[4], i15, widths2[5], i16, widths2[6]};
        int[] widths3 = getWidths(i7, iArr4[i2], 7, iArr5[i2], 1);
        int i18 = widths3[0];
        int i19 = widths3[1];
        int i20 = widths3[2];
        int i21 = widths3[3];
        int i22 = widths3[4];
        int i23 = widths3[5];
        int i24 = widths3[6];
        int[] widths4 = getWidths(i8, iArr6[i2], 7, iArr7[i2], 0);
        int[] iArr9 = {i18, widths4[0], i19, widths4[1], i20, widths4[2], i21, widths4[3], i22, widths4[4], i23, widths4[5], i24, widths4[6]};
        int i25 = 0;
        for (int i26 = 0; i26 < 14; i26++) {
            int[] iArr10 = CHECKSUM_WEIGHT_LTD;
            i25 = i25 + (iArr10[i26] * iArr8[i26]) + (iArr10[i26 + 14] * iArr9[i26]);
        }
        int i27 = i25 % 89;
        infoLine("Checksum: " + i27);
        for (int i28 = 0; i28 < 14; i28++) {
            iArr[i28] = FINDER_PATTERN_LTD[(i27 * 14) + i28];
        }
        iArr2[0] = 1;
        iArr2[1] = 1;
        iArr2[44] = 1;
        iArr2[45] = 1;
        for (int i29 = 0; i29 < 14; i29++) {
            iArr2[i29 + 2] = iArr8[i29];
            iArr2[i29 + 16] = iArr[i29];
            iArr2[i29 + 30] = iArr9[i29];
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i30 = 0;
        boolean z = false;
        while (i30 < 46) {
            for (int i31 = 0; i31 < iArr2[i30]; i31++) {
                if (z) {
                    sb.append('1');
                    sb2.append('0');
                } else {
                    sb.append('0');
                    sb2.append('1');
                }
            }
            i30++;
            z = !z;
        }
        this.readable = "(01)";
        String str = "";
        for (int length = this.content.length(); length < 13; length++) {
            str = str + "0";
        }
        String str2 = str + this.content;
        int i32 = 0;
        for (int i33 = 0; i33 < 13; i33++) {
            i32 += str2.charAt(i33) - '0';
            if ((i33 & 1) == 0) {
                i32 += (str2.charAt(i33) - '0') * 2;
            }
        }
        int i34 = 10 - (i32 % 10);
        if (i34 == 10) {
            i34 = 0;
        }
        this.readable += (str2 + ((char) (i34 + 48)));
        boolean z2 = this.linkageFlag;
        int i35 = (z2 ? 1 : 0) + 1;
        this.row_count = i35;
        this.row_height = new int[i35];
        this.row_height[z2 ? 1 : 0] = -1;
        this.pattern = new String[i35];
        this.pattern[z2 ? 1 : 0] = bin2pat(sb);
        if (this.linkageFlag) {
            sb2.delete(70, sb2.length());
            sb2.delete(0, 4);
            this.row_height[0] = 1;
            this.pattern[0] = "04" + bin2pat(sb2);
        }
    }

    private static int getCombinations(int i, int i2) {
        int i3 = i - i2;
        if (i3 > i2) {
            i3 = i2;
            i2 = i3;
        }
        int i4 = 1;
        int i5 = 1;
        while (i > i2) {
            i4 *= i;
            if (i5 <= i3) {
                i4 /= i5;
                i5++;
            }
            i--;
        }
        while (i5 <= i3) {
            i4 /= i5;
            i5++;
        }
        return i4;
    }

    static int[] getWidths(int i, int i2, int i3, int i4, int i5) {
        int i6;
        int combinations;
        int i7 = i3;
        int[] iArr = new int[i7];
        int i8 = i;
        int i9 = i2;
        int i10 = 0;
        int i11 = 0;
        while (true) {
            int i12 = i7 - 1;
            if (i10 < i12) {
                int i13 = 1 << i10;
                i11 |= i13;
                int i14 = 1;
                while (true) {
                    i6 = i9 - i14;
                    int i15 = i7 - i10;
                    int i16 = i15 - 2;
                    combinations = getCombinations(i6 - 1, i16);
                    if (i5 == 0 && i11 == 0) {
                        int i17 = i15 - 1;
                        if (i6 - i17 >= i17) {
                            combinations -= getCombinations(i6 - i15, i16);
                        }
                    }
                    if (i15 - 1 > 1) {
                        int i18 = 0;
                        for (int i19 = i6 - i16; i19 > i4; i19--) {
                            i18 += getCombinations((i6 - i19) - 1, i15 - 3);
                        }
                        combinations -= i18 * (i12 - i10);
                    } else if (i6 > i4) {
                        combinations--;
                    }
                    i8 -= combinations;
                    if (i8 < 0) {
                        break;
                    }
                    i14++;
                    i11 &= ~i13;
                    i7 = i3;
                }
                i8 += combinations;
                iArr[i10] = i14;
                i10++;
                i7 = i3;
                i9 = i6;
            } else {
                iArr[i10] = i9;
                return iArr;
            }
        }
    }
}
