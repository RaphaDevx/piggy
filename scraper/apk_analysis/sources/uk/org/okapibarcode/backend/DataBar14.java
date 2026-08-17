package uk.org.okapibarcode.backend;

import java.lang.reflect.Array;
import java.math.BigInteger;
import okhttp3.internal.ws.WebSocketProtocol;
import uk.org.okapibarcode.backend.Symbol;

/* loaded from: classes4.dex */
public class DataBar14 extends Symbol {
    private boolean linkageFlag;
    private static final int[] G_SUM_TABLE = {0, 161, 961, 2015, 2715, 0, 336, 1036, 1516};
    private static final int[] T_TABLE = {1, 10, 34, 70, WebSocketProtocol.PAYLOAD_SHORT, 4, 20, 48, 81};
    private static final int[] MODULES_ODD = {12, 10, 8, 6, 4, 5, 7, 9, 11};
    private static final int[] MODULES_EVEN = {4, 6, 8, 10, 12, 10, 8, 6, 4};
    private static final int[] WIDEST_ODD = {8, 6, 4, 3, 1, 2, 4, 6, 8};
    private static final int[] WIDEST_EVEN = {1, 3, 5, 6, 8, 7, 5, 3, 1};
    private static final int[] CHECKSUM_WEIGHT = {1, 3, 9, 27, 2, 6, 18, 54, 4, 12, 36, 29, 8, 24, 72, 58, 16, 48, 65, 37, 32, 17, 51, 74, 64, 34, 23, 69, 49, 68, 46, 59};
    private static final int[] FINDER_PATTERN = {3, 8, 2, 1, 1, 3, 5, 5, 1, 1, 3, 3, 7, 1, 1, 3, 1, 9, 1, 1, 2, 7, 4, 1, 1, 2, 5, 6, 1, 1, 2, 3, 8, 1, 1, 1, 5, 7, 1, 1, 1, 3, 9, 1, 1};
    private int separatorHeight = 1;
    private Mode mode = Mode.LINEAR;

    public enum Mode {
        LINEAR,
        OMNI,
        STACKED
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return false;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    public void setDataType(Symbol.DataType dataType) {
    }

    protected void setLinkageFlag(boolean z) {
        this.linkageFlag = z;
    }

    protected boolean getLinkageFlag() {
        return this.linkageFlag;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setSeparatorHeight(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Invalid DataBar-14 Stacked separator height: " + i);
        }
        this.separatorHeight = i;
    }

    public int getSeparatorHeight() {
        return this.separatorHeight;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int i;
        int i2;
        char c;
        char c2;
        boolean[][] zArr = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, 5, 100);
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        int[][] iArr4 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 8, 4);
        int[] iArr5 = new int[46];
        StringBuilder sb = new StringBuilder();
        if (this.content.length() > 13) {
            throw new OkapiException("Input too long");
        }
        if (!this.content.matches("[0-9]+?")) {
            throw new OkapiException("Invalid characters in input");
        }
        BigInteger bigInteger = new BigInteger(this.content);
        if (this.linkageFlag) {
            bigInteger = bigInteger.add(new BigInteger("10000000000000"));
            i = 1;
        } else {
            i = 0;
        }
        BigInteger divide = bigInteger.divide(new BigInteger("4537077"));
        BigInteger mod = bigInteger.mod(new BigInteger("4537077"));
        int[] iArr6 = {divide.divide(new BigInteger("1597")).intValue(), divide.mod(new BigInteger("1597")).intValue(), mod.divide(new BigInteger("1597")).intValue(), mod.mod(new BigInteger("1597")).intValue()};
        info("Data Characters: ");
        for (int i3 = 0; i3 < 4; i3++) {
            infoSpace(iArr6[i3]);
        }
        infoLine();
        int i4 = iArr6[0];
        if (i4 >= 0 && i4 <= 160) {
            iArr[0] = 0;
        }
        int i5 = iArr6[0];
        if (i5 >= 161 && i5 <= 960) {
            iArr[0] = 1;
        }
        int i6 = iArr6[0];
        if (i6 >= 961 && i6 <= 2014) {
            iArr[0] = 2;
        }
        int i7 = iArr6[0];
        if (i7 >= 2015 && i7 <= 2714) {
            iArr[0] = 3;
        }
        int i8 = iArr6[0];
        if (i8 >= 2715 && i8 <= 2840) {
            iArr[0] = 4;
        }
        int i9 = iArr6[1];
        if (i9 >= 0 && i9 <= 335) {
            iArr[1] = 5;
        }
        int i10 = iArr6[1];
        if (i10 >= 336 && i10 <= 1035) {
            iArr[1] = 6;
        }
        int i11 = iArr6[1];
        if (i11 >= 1036 && i11 <= 1515) {
            iArr[1] = 7;
        }
        int i12 = iArr6[1];
        if (i12 >= 1516 && i12 <= 1596) {
            iArr[1] = 8;
        }
        int i13 = iArr6[3];
        if (i13 >= 0 && i13 <= 335) {
            iArr[3] = 5;
        }
        int i14 = iArr6[3];
        if (i14 >= 336 && i14 <= 1035) {
            iArr[3] = 6;
        }
        int i15 = iArr6[3];
        if (i15 >= 1036 && i15 <= 1515) {
            iArr[3] = 7;
        }
        int i16 = iArr6[3];
        if (i16 >= 1516 && i16 <= 1596) {
            iArr[3] = 8;
        }
        int i17 = iArr6[2];
        if (i17 >= 0 && i17 <= 160) {
            iArr[2] = 0;
        }
        int i18 = iArr6[2];
        if (i18 >= 161 && i18 <= 960) {
            iArr[2] = 1;
        }
        int i19 = iArr6[2];
        if (i19 >= 961 && i19 <= 2014) {
            iArr[2] = 2;
        }
        int i20 = iArr6[2];
        if (i20 >= 2015 && i20 <= 2714) {
            iArr[2] = 3;
        }
        int i21 = iArr6[2];
        if (i21 >= 2715 && i21 <= 2840) {
            iArr[2] = 4;
        }
        int i22 = iArr6[0];
        int[] iArr7 = G_SUM_TABLE;
        int i23 = iArr[0];
        int i24 = i22 - iArr7[i23];
        int[] iArr8 = T_TABLE;
        iArr2[0] = i24 / iArr8[i23];
        int i25 = iArr6[0];
        int i26 = iArr[0];
        iArr3[0] = (i25 - iArr7[i26]) % iArr8[i26];
        int i27 = iArr6[1];
        int i28 = iArr[1];
        iArr2[1] = (i27 - iArr7[i28]) % iArr8[i28];
        int i29 = iArr6[1];
        int i30 = iArr[1];
        iArr3[1] = (i29 - iArr7[i30]) / iArr8[i30];
        int i31 = iArr6[3];
        int i32 = iArr[3];
        iArr2[3] = (i31 - iArr7[i32]) % iArr8[i32];
        int i33 = iArr6[3];
        int i34 = iArr[3];
        iArr3[3] = (i33 - iArr7[i34]) / iArr8[i34];
        int i35 = 2;
        int i36 = iArr6[2];
        int i37 = iArr[2];
        iArr2[2] = (i36 - iArr7[i37]) / iArr8[i37];
        int i38 = iArr6[2];
        int i39 = iArr[2];
        iArr3[2] = (i38 - iArr7[i39]) % iArr8[i39];
        int i40 = 0;
        while (i40 < 4) {
            if (i40 == 0 || i40 == i35) {
                int i41 = iArr2[i40];
                int[] iArr9 = MODULES_ODD;
                int i42 = iArr[i40];
                int[] widths = DataBarLimited.getWidths(i41, iArr9[i42], 4, WIDEST_ODD[i42], 1);
                iArr4[0][i40] = widths[0];
                iArr4[2][i40] = widths[1];
                iArr4[4][i40] = widths[2];
                iArr4[6][i40] = widths[3];
                int i43 = iArr3[i40];
                int[] iArr10 = MODULES_EVEN;
                int i44 = iArr[i40];
                int[] widths2 = DataBarLimited.getWidths(i43, iArr10[i44], 4, WIDEST_EVEN[i44], 0);
                iArr4[1][i40] = widths2[0];
                iArr4[3][i40] = widths2[1];
                iArr4[5][i40] = widths2[2];
                iArr4[7][i40] = widths2[3];
            } else {
                int i45 = iArr2[i40];
                int[] iArr11 = MODULES_ODD;
                int i46 = iArr[i40];
                int[] widths3 = DataBarLimited.getWidths(i45, iArr11[i46], 4, WIDEST_ODD[i46], 0);
                iArr4[0][i40] = widths3[0];
                iArr4[2][i40] = widths3[1];
                iArr4[4][i40] = widths3[2];
                iArr4[6][i40] = widths3[3];
                int i47 = iArr3[i40];
                int[] iArr12 = MODULES_EVEN;
                int i48 = iArr[i40];
                int[] widths4 = DataBarLimited.getWidths(i47, iArr12[i48], 4, WIDEST_EVEN[i48], 1);
                iArr4[1][i40] = widths4[0];
                iArr4[3][i40] = widths4[1];
                iArr4[5][i40] = widths4[2];
                iArr4[7][i40] = widths4[3];
            }
            i40++;
            i35 = 2;
        }
        int i49 = 0;
        for (int i50 = 0; i50 < 8; i50++) {
            int[] iArr13 = CHECKSUM_WEIGHT;
            int i51 = iArr13[i50];
            int[] iArr14 = iArr4[i50];
            i49 = i49 + (i51 * iArr14[0]) + (iArr13[i50 + 8] * iArr14[1]) + (iArr13[i50 + 16] * iArr14[2]) + (iArr13[i50 + 24] * iArr14[3]);
        }
        int i52 = i49 % 79;
        if (i52 >= 8) {
            i52++;
        }
        if (i52 >= 72) {
            i52++;
        }
        int i53 = i52 / 9;
        int i54 = i52 % 9;
        infoLine("Checksum: " + i52);
        char c3 = 0;
        iArr5[0] = 1;
        iArr5[1] = 1;
        iArr5[44] = 1;
        iArr5[45] = 1;
        int i55 = 0;
        while (i55 < 8) {
            int[] iArr15 = iArr4[i55];
            iArr5[i55 + 2] = iArr15[c3];
            int[] iArr16 = iArr4[7 - i55];
            iArr5[i55 + 15] = iArr16[1];
            iArr5[i55 + 23] = iArr15[3];
            iArr5[i55 + 36] = iArr16[2];
            i55++;
            c3 = 0;
        }
        for (int i56 = 0; i56 < 5; i56++) {
            int[] iArr17 = FINDER_PATTERN;
            iArr5[i56 + 10] = iArr17[(i53 * 5) + i56];
            iArr5[i56 + 31] = iArr17[(4 - i56) + (i54 * 5)];
        }
        boolean z = false;
        this.row_count = 0;
        boolean[] zArr2 = new boolean[100];
        int i57 = 0;
        while (i57 < 100) {
            zArr2[i57] = z;
            i57++;
            z = false;
        }
        if (this.mode == Mode.LINEAR) {
            char c4 = '0';
            int i58 = 0;
            int i59 = 0;
            while (i58 < 46) {
                for (int i60 = 0; i60 < iArr5[i58]; i60++) {
                    if (c4 == '1') {
                        zArr[this.row_count][i59] = true;
                    }
                    i59++;
                }
                i58++;
                c4 = c4 == '1' ? '0' : '1';
            }
            if (this.symbol_width < i59) {
                this.symbol_width = i59;
            }
            if (this.linkageFlag) {
                for (int i61 = 4; i61 < 92; i61++) {
                    zArr2[i61] = !zArr[0][i61];
                }
                boolean z2 = false;
                boolean z3 = true;
                char c5 = '1';
                int i62 = 16;
                while (i62 < 32) {
                    if (zArr[0][i62]) {
                        zArr2[i62] = false;
                    } else if (c5 == '1') {
                        zArr2[i62] = z3;
                        c2 = '0';
                        i62++;
                        z3 = true;
                        c5 = c2;
                    } else {
                        zArr2[i62] = false;
                    }
                    c2 = '1';
                    i62++;
                    z3 = true;
                    c5 = c2;
                }
                int i63 = 63;
                char c6 = '1';
                while (i63 < 78) {
                    if (zArr[z2 ? 1 : 0][i63]) {
                        zArr2[i63] = z2;
                    } else if (c6 == '1') {
                        zArr2[i63] = true;
                        c = '0';
                        i63++;
                        z2 = false;
                        c6 = c;
                    } else {
                        zArr2[i63] = z2;
                    }
                    c = '1';
                    i63++;
                    z2 = false;
                    c6 = c;
                }
            }
            this.row_count++;
            StringBuilder sb2 = new StringBuilder(14);
            for (int length = this.content.length(); length < 13; length++) {
                sb2.append('0');
            }
            sb2.append(this.content);
            int i64 = 0;
            for (int i65 = 0; i65 < 13; i65++) {
                i64 += sb2.charAt(i65) - '0';
                if ((i65 & 1) == 0) {
                    i64 += (sb2.charAt(i65) - '0') * 2;
                }
            }
            int i66 = 10 - (i64 % 10);
            if (i66 == 10) {
                i66 = 0;
            }
            infoLine("Check Digit: " + i66);
            sb2.append((char) (i66 + 48));
            this.readable = "(01)" + ((Object) sb2);
        }
        if (this.mode == Mode.STACKED) {
            char c7 = '0';
            int i67 = 0;
            for (int i68 = 0; i68 < 23; i68++) {
                for (int i69 = 0; i69 < iArr5[i68]; i69++) {
                    zArr[this.row_count][i67] = c7 == '1';
                    i67++;
                }
                c7 = c7 == '1' ? '0' : '1';
            }
            zArr[this.row_count][i67] = true;
            zArr[this.row_count][i67 + 1] = false;
            this.row_count += 2;
            zArr[this.row_count][0] = true;
            zArr[this.row_count][1] = false;
            char c8 = '1';
            int i70 = 0;
            for (int i71 = 23; i71 < 46; i71++) {
                for (int i72 = 0; i72 < iArr5[i71]; i72++) {
                    zArr[this.row_count][i70 + 2] = c8 == '1';
                    i70++;
                }
                c8 = c8 == '1' ? '0' : '1';
            }
            int i73 = 1;
            for (int i74 = 46; i73 < i74; i74 = 46) {
                if (zArr[this.row_count - 2][i73] == zArr[this.row_count][i73]) {
                    if (!zArr[this.row_count - 2][i73]) {
                        zArr[this.row_count - 1][i73] = true;
                    }
                } else if (!zArr[this.row_count - 1][i73 - 1]) {
                    zArr[this.row_count - 1][i73] = true;
                }
                i73++;
            }
            int i75 = 1;
            int i76 = 0;
            while (i76 < 4) {
                zArr[this.row_count - i75][i76] = false;
                i76++;
                i75 = 1;
            }
            boolean z4 = false;
            if (this.linkageFlag) {
                for (int i77 = 4; i77 < 46; i77++) {
                    zArr2[i77] = !zArr[0][i77];
                }
                boolean z5 = true;
                char c9 = '1';
                int i78 = 16;
                while (i78 < 32) {
                    if (zArr[z4 ? 1 : 0][i78]) {
                        zArr2[i78] = z4;
                    } else if (c9 == '1') {
                        zArr2[i78] = z5;
                        c9 = '0';
                        i78++;
                        z4 = false;
                        z5 = true;
                    } else {
                        zArr2[i78] = z4;
                    }
                    c9 = '1';
                    i78++;
                    z4 = false;
                    z5 = true;
                }
            }
            this.row_count++;
            if (this.symbol_width < 50) {
                this.symbol_width = 50;
            }
        }
        if (this.mode == Mode.OMNI) {
            char c10 = '0';
            int i79 = 0;
            for (int i80 = 0; i80 < 23; i80++) {
                for (int i81 = 0; i81 < iArr5[i80]; i81++) {
                    zArr[this.row_count][i79] = c10 == '1';
                    i79++;
                }
                c10 = c10 == '1' ? '0' : '1';
            }
            zArr[this.row_count][i79] = true;
            zArr[this.row_count][i79 + 1] = false;
            this.row_count += 4;
            zArr[this.row_count][0] = true;
            zArr[this.row_count][1] = false;
            char c11 = '1';
            int i82 = 0;
            for (int i83 = 23; i83 < 46; i83++) {
                for (int i84 = 0; i84 < iArr5[i83]; i84++) {
                    zArr[this.row_count][i82 + 2] = c11 == '1';
                    i82++;
                }
                c11 = c11 == '1' ? '0' : '1';
            }
            for (int i85 = 5; i85 < 46; i85 += 2) {
                zArr[this.row_count - 2][i85] = true;
            }
            int i86 = 4;
            for (int i87 = 46; i86 < i87; i87 = 46) {
                if (!zArr[this.row_count - 4][i86]) {
                    zArr[this.row_count - 3][i86] = true;
                }
                i86++;
            }
            char c12 = '1';
            for (int i88 = 17; i88 < 33; i88++) {
                if (zArr[this.row_count - 4][i88]) {
                    zArr[this.row_count - 3][i88] = false;
                } else if (c12 == '1') {
                    zArr[this.row_count - 3][i88] = true;
                    c12 = '0';
                } else {
                    zArr[this.row_count - 3][i88] = false;
                }
                c12 = '1';
            }
            for (int i89 = 4; i89 < 46; i89++) {
                if (!zArr[this.row_count][i89]) {
                    zArr[this.row_count - 1][i89] = true;
                }
            }
            char c13 = '1';
            for (int i90 = 16; i90 < 32; i90++) {
                if (zArr[this.row_count][i90]) {
                    zArr[this.row_count - 1][i90] = false;
                } else if (c13 == '1') {
                    zArr[this.row_count - 1][i90] = true;
                    c13 = '0';
                } else {
                    zArr[this.row_count - 1][i90] = false;
                }
                c13 = '1';
            }
            if (this.symbol_width < 50) {
                this.symbol_width = 50;
            }
            if (this.linkageFlag) {
                for (int i91 = 4; i91 < 46; i91++) {
                    zArr2[i91] = !zArr[0][i91];
                }
                boolean z6 = false;
                boolean z7 = true;
                char c14 = '1';
                int i92 = 16;
                while (i92 < 32) {
                    if (zArr[z6 ? 1 : 0][i92]) {
                        zArr2[i92] = z6;
                    } else if (c14 == '1') {
                        zArr2[i92] = z7;
                        c14 = '0';
                        i92++;
                        z6 = false;
                        z7 = true;
                    } else {
                        zArr2[i92] = z6;
                    }
                    c14 = '1';
                    i92++;
                    z6 = false;
                    z7 = true;
                }
            }
            this.row_count++;
        }
        this.pattern = new String[this.row_count + i];
        this.row_height = new int[this.row_count + i];
        if (this.linkageFlag) {
            sb.setLength(0);
            for (int i93 = 0; i93 < this.symbol_width; i93++) {
                if (zArr2[i93]) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            i2 = 0;
            this.pattern[0] = bin2pat(sb);
            this.row_height[0] = 1;
        } else {
            i2 = 0;
        }
        for (int i94 = i2; i94 < this.row_count; i94++) {
            sb.setLength(i2);
            for (int i95 = i2; i95 < this.symbol_width; i95++) {
                if (zArr[i94][i95]) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            this.pattern[i94 + i] = bin2pat(sb);
        }
        if (this.mode == Mode.LINEAR) {
            this.row_height[i] = -1;
        }
        if (this.mode == Mode.STACKED) {
            this.row_height[i] = 5;
            this.row_height[i + 1] = this.separatorHeight;
            this.row_height[i + 2] = 7;
        }
        if (this.mode == Mode.OMNI) {
            this.row_height[i] = -1;
            this.row_height[i + 1] = this.separatorHeight;
            this.row_height[i + 2] = this.separatorHeight;
            this.row_height[i + 3] = this.separatorHeight;
            this.row_height[i + 4] = -1;
        }
        if (this.linkageFlag) {
            this.row_count++;
        }
    }
}
