package uk.org.okapibarcode.backend;

import androidx.compose.runtime.ComposerKt;
import com.salesforce.marketingcloud.analytics.stats.b;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import uk.org.okapibarcode.graphics.Rectangle;

/* loaded from: classes4.dex */
public class CodablockF extends Symbol {
    private static final String[] C_128_TABLE = {"212222", "222122", "222221", "121223", "121322", "131222", "122213", "122312", "132212", "221213", "221312", "231212", "112232", "122132", "122231", "113222", "123122", "123221", "223211", "221132", "221231", "213212", "223112", "312131", "311222", "321122", "321221", "312212", "322112", "322211", "212123", "212321", "232121", "111323", "131123", "131321", "112313", "132113", "132311", "211313", "231113", "231311", "112133", "112331", "132131", "113123", "113321", "133121", "313121", "211331", "231131", "213113", "213311", "213131", "311123", "311321", "331121", "312113", "312311", "332111", "314111", "221411", "431111", "111224", "111422", "121124", "121421", "141122", "141221", "112214", "112412", "122114", "122411", "142112", "142211", "241211", "221114", "413111", "241112", "134111", "111242", "121142", "121241", "114212", "124112", "124211", "411212", "421112", "421211", "212141", "214121", "412121", "111143", "111341", "131141", "114113", "114311", "411113", "411311", "113141", "114131", "311141", "411131", "211412", "211214", "211232", "2331112"};
    private int columns_needed;
    private CfMode final_mode;
    private int rows_needed;
    private int[][] blockmatrix = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 44, 62);
    private CfMode[] subset_selector = new CfMode[44];

    private enum CfMode {
        MODEA,
        MODEB,
        MODEC
    }

    private enum Mode {
        SHIFTA,
        LATCHA,
        SHIFTB,
        LATCHB,
        SHIFTC,
        LATCHC,
        AORB,
        ABORC,
        CANDB,
        CANDBB
    }

    private int a3_convert(int i) {
        return i < 32 ? i + 64 : (i < 32 || i > 127) ? (i < 128 || i > 159) ? i - 160 : i - 64 : i - 32;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return true;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int i;
        int[] iArr = new int[44];
        int[] iArr2 = new int[44];
        this.final_mode = CfMode.MODEA;
        if (!this.content.matches("[\u0000-ÿ]+")) {
            throw new OkapiException("Invalid characters in input data");
        }
        this.inputData = toBytes(this.content, StandardCharsets.ISO_8859_1, 0);
        int length = this.inputData.length - 1;
        if (length > 5450) {
            throw new OkapiException("Input data too long");
        }
        Mode mode = Mode.AORB;
        double d = 0.0d;
        int i2 = 0;
        while (i2 < length) {
            Mode findSubset = findSubset(this.inputData[i2]);
            if (findSubset != mode) {
                d += 1.0d;
            }
            d = findSubset != Mode.ABORC ? d + 1.0d : d + 0.5d;
            if (this.inputData[i2] > 127) {
                d += 1.0d;
            }
            i2++;
            mode = findSubset;
        }
        double d2 = d + 2.0d;
        int sqrt = (int) (Math.sqrt(d2 / 1.45d) + 0.5d);
        this.rows_needed = sqrt;
        if (sqrt < 2) {
            this.rows_needed = 2;
        }
        if (this.rows_needed > 44) {
            this.rows_needed = 44;
        }
        int i3 = ((int) d2) / this.rows_needed;
        this.columns_needed = i3;
        if (i3 < 4) {
            this.columns_needed = 4;
        }
        if (this.columns_needed > 62) {
            throw new OkapiException("Input data too long");
        }
        data_encode_blockf();
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length; i6++) {
            if (this.inputData[i6] == -1) {
                i4 += (i6 + 1) * 29;
                i = i6 * 29;
            } else {
                i4 += (i6 + 1) * this.inputData[i6];
                i = this.inputData[i6] * i6;
            }
            i5 += i;
        }
        int i7 = i4 % 86;
        int i8 = i5 % 86;
        if (this.final_mode == CfMode.MODEA || this.final_mode == CfMode.MODEB) {
            int i9 = i7 + 64;
            i7 = i9 > 95 ? i7 - 32 : i9;
            int i10 = i8 + 64;
            i8 = i10 > 95 ? i8 - 32 : i10;
        }
        int[][] iArr3 = this.blockmatrix;
        int i11 = this.rows_needed;
        int[] iArr4 = iArr3[i11 - 1];
        int i12 = this.columns_needed;
        iArr4[i12 - 2] = i7;
        iArr3[i11 - 1][i12 - 1] = i8;
        if (this.subset_selector[0] == CfMode.MODEC) {
            iArr[0] = this.rows_needed - 2;
        } else {
            int i13 = this.rows_needed;
            int i14 = i13 + 62;
            iArr[0] = i14;
            if (i14 > 95) {
                iArr[0] = i13 - 33;
            }
        }
        for (int i15 = 1; i15 < this.rows_needed; i15++) {
            if (this.subset_selector[i15] == CfMode.MODEC) {
                iArr[i15] = i15 + 42;
            } else if (i15 < 6) {
                iArr[i15] = i15 + 10;
            } else {
                iArr[i15] = i15 + 20;
            }
        }
        int i16 = 0;
        while (true) {
            int i17 = this.rows_needed;
            int i18 = b.i;
            if (i16 >= i17) {
                break;
            }
            int i19 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$CodablockF$CfMode[this.subset_selector[i16].ordinal()];
            if (i19 == 1) {
                i18 = ComposerKt.providerKey;
            } else if (i19 == 2) {
                i18 = ComposerKt.providerValuesKey;
            } else if (i19 == 3) {
                i18 = ComposerKt.compositionLocalMapKey;
            }
            int i20 = i18 + (iArr[i16] * 2);
            for (int i21 = 0; i21 < this.columns_needed; i21++) {
                i20 += (i21 + 3) * this.blockmatrix[i16][i21];
            }
            iArr2[i16] = i20 % b.i;
            i16++;
        }
        this.readable = "";
        this.row_count = this.rows_needed;
        this.pattern = new String[this.row_count];
        this.row_height = new int[this.row_count];
        infoLine("Grid Size: " + this.columns_needed + " X " + this.rows_needed);
        StringBuilder sb = new StringBuilder("K1 Check Digit: ");
        sb.append(i7);
        infoLine(sb.toString());
        infoLine("K2 Check Digit: " + i8);
        info("Encoding: ");
        for (int i22 = 0; i22 < this.rows_needed; i22++) {
            StringBuilder sb2 = new StringBuilder("");
            String[] strArr = C_128_TABLE;
            sb2.append(strArr[103]);
            String sb3 = sb2.toString();
            int i23 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$CodablockF$CfMode[this.subset_selector[i22].ordinal()];
            if (i23 == 1) {
                sb3 = sb3 + strArr[98];
                info("MODEA ");
            } else if (i23 == 2) {
                sb3 = sb3 + strArr[100];
                info("MODEB ");
            } else if (i23 == 3) {
                sb3 = sb3 + strArr[99];
                info("MODEC ");
            }
            String str = sb3 + strArr[iArr[i22]];
            infoSpace(iArr[i22]);
            for (int i24 = 0; i24 < this.columns_needed; i24++) {
                str = str + C_128_TABLE[this.blockmatrix[i22][i24]];
                infoSpace(this.blockmatrix[i22][i24]);
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append(str);
            String[] strArr2 = C_128_TABLE;
            sb4.append(strArr2[iArr2[i22]]);
            String sb5 = sb4.toString();
            info("(" + iArr2[i22] + ") ");
            this.pattern[i22] = sb5 + strArr2[106];
            this.row_height[i22] = 15;
        }
        infoLine();
        this.symbol_height = this.rows_needed * 15;
    }

    /* renamed from: uk.org.okapibarcode.backend.CodablockF$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$CodablockF$CfMode;

        static {
            int[] iArr = new int[CfMode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$CodablockF$CfMode = iArr;
            try {
                iArr[CfMode.MODEA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodablockF$CfMode[CfMode.MODEB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodablockF$CfMode[CfMode.MODEC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private Mode findSubset(int i) {
        if (i == -1) {
            return Mode.AORB;
        }
        if (i <= 31) {
            return Mode.SHIFTA;
        }
        if (i >= 48 && i <= 57) {
            return Mode.ABORC;
        }
        if (i <= 95) {
            return Mode.AORB;
        }
        if (i <= 127) {
            return Mode.SHIFTB;
        }
        if (i <= 159) {
            return Mode.SHIFTA;
        }
        if (i <= 223) {
            return Mode.AORB;
        }
        return Mode.SHIFTB;
    }

    /* JADX WARN: Code restructure failed: missing block: B:115:0x0279, code lost:
    
        if (r19.inputData[r6] == (-1)) goto L121;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void data_encode_blockf() {
        /*
            Method dump skipped, instructions count: 1389
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.CodablockF.data_encode_blockf():void");
    }

    private CfMode character_subset_select(int i) {
        if (this.inputData[i] >= 48 && this.inputData[i] <= 57) {
            return CfMode.MODEC;
        }
        if (this.inputData[i] >= 128 && this.inputData[i] <= 160) {
            return CfMode.MODEA;
        }
        if (this.inputData[i] >= 0 && this.inputData[i] <= 31) {
            return CfMode.MODEA;
        }
        return CfMode.MODEB;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        int i;
        int i2;
        int i3;
        int i4;
        resetPlotElements();
        int i5 = 0;
        int i6 = 1;
        int i7 = 1;
        while (i5 < this.row_count) {
            int i8 = 0;
            boolean z = true;
            int i9 = 0;
            while (i8 < this.pattern[i5].length()) {
                if (z) {
                    int charAt = this.pattern[i5].charAt(i8) - '0';
                    if (this.row_height[i5] == -1) {
                        i3 = this.default_height;
                    } else {
                        i3 = this.row_height[i5];
                    }
                    if (charAt == 0 || i3 == 0) {
                        i = i5;
                        i2 = i6;
                        i4 = i3;
                    } else {
                        i = i5;
                        i2 = i6;
                        i4 = i3;
                        this.rectangles.add(new Rectangle(i9, i6, charAt, i3));
                    }
                    int i10 = charAt + i9;
                    if (i10 > this.symbol_width) {
                        this.symbol_width = i10;
                    }
                    i7 = i4;
                    z = false;
                } else {
                    i = i5;
                    i2 = i6;
                    z = true;
                }
                i9 += this.pattern[i].charAt(i8) - '0';
                i8++;
                i5 = i;
                i6 = i2;
            }
            int i11 = i5;
            i6 += i7;
            if (i6 > this.symbol_height) {
                this.symbol_height = i6;
            }
            if (i11 != this.row_count - 1) {
                this.rectangles.add(new Rectangle(11.0d, i6 - 1, this.symbol_width - 24, 2.0d));
            }
            i5 = i11 + 1;
        }
        this.rectangles.add(new Rectangle(0.0d, 0.0d, this.symbol_width, 2.0d));
        this.rectangles.add(new Rectangle(0.0d, i6 - 1, this.symbol_width, 2.0d));
        this.symbol_height++;
    }
}
