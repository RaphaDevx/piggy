package uk.org.okapibarcode.backend;

import uk.org.okapibarcode.graphics.Rectangle;

/* loaded from: classes4.dex */
public class Code16k extends Symbol {
    private int block_count;
    private static final String[] C16K_TABLE = {"212222", "222122", "222221", "121223", "121322", "131222", "122213", "122312", "132212", "221213", "221312", "231212", "112232", "122132", "122231", "113222", "123122", "123221", "223211", "221132", "221231", "213212", "223112", "312131", "311222", "321122", "321221", "312212", "322112", "322211", "212123", "212321", "232121", "111323", "131123", "131321", "112313", "132113", "132311", "211313", "231113", "231311", "112133", "112331", "132131", "113123", "113321", "133121", "313121", "211331", "231131", "213113", "213311", "213131", "311123", "311321", "331121", "312113", "312311", "332111", "314111", "221411", "431111", "111224", "111422", "121124", "121421", "141122", "141221", "112214", "112412", "122114", "122411", "142112", "142211", "241211", "221114", "413111", "241112", "134111", "111242", "121142", "121241", "114212", "124112", "124211", "411212", "421112", "421211", "212141", "214121", "412121", "111143", "111341", "131141", "114113", "114311", "411113", "411311", "113141", "114131", "311141", "411131", "211412", "211214", "211232", "211133"};
    private static final String[] C16K_START_STOP = {"3211", "2221", "2122", "1411", "1132", "1231", "1114", "3112"};
    private static final int[] C16K_START_VALUES = {0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7};
    private static final int[] C16K_STOP_VALUES = {0, 1, 2, 3, 4, 5, 6, 7, 4, 5, 6, 7, 0, 1, 2, 3};
    private Mode[] block_mode = new Mode[170];
    private int[] block_length = new int[170];

    private enum Mode {
        NULL,
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

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:210:0x0235, code lost:
    
        if (r12 == 'F') goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:220:0x024c, code lost:
    
        r2 = r2 + 2.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:229:0x024a, code lost:
    
        if (r5[r7 - 1] == 'F') goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:277:0x0342, code lost:
    
        if (r4[0] != 'B') goto L267;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x03bb, code lost:
    
        if (r9 == 'n') goto L309;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x03da, code lost:
    
        if (r9 == 'b') goto L323;
     */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0521 A[LOOP:17: B:270:0x032d->B:318:0x0521, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x042a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:348:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x03c4  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x03b1  */
    @Override // uk.org.okapibarcode.backend.Symbol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void encode() {
        /*
            Method dump skipped, instructions count: 1374
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.Code16k.encode():void");
    }

    /* renamed from: uk.org.okapibarcode.backend.Code16k$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$Code16k$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$Code16k$Mode = iArr;
            try {
                iArr[Mode.SHIFTA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code16k$Mode[Mode.LATCHA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code16k$Mode[Mode.SHIFTB.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code16k$Mode[Mode.LATCHB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code16k$Mode[Mode.LATCHC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private void getValueSubsetA(int i, int[] iArr, int i2) {
        if (i > 127) {
            if (i < 160) {
                iArr[i2] = i - 64;
                return;
            } else {
                iArr[i2] = i - 160;
                return;
            }
        }
        if (i < 32) {
            iArr[i2] = i + 64;
        } else {
            iArr[i2] = i - 32;
        }
    }

    private void getValueSubsetB(int i, int[] iArr, int i2) {
        if (i > 127) {
            iArr[i2] = i - 160;
        } else {
            iArr[i2] = i - 32;
        }
    }

    private void getValueSubsetC(int i, int i2, int[] iArr, int i3) {
        iArr[i3] = (Character.getNumericValue(i) * 10) + Character.getNumericValue(i2);
    }

    private Mode findSubset(int i) {
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

    private void reduceSubsetChanges(int i) {
        Mode mode;
        Mode mode2;
        for (int i2 = 0; i2 < i; i2++) {
            Mode[] modeArr = this.block_mode;
            Mode mode3 = modeArr[i2];
            int i3 = this.block_length[i2];
            if (i2 != 0) {
                mode = modeArr[i2 - 1];
            } else {
                mode = Mode.NULL;
            }
            if (i2 != i - 1) {
                mode2 = this.block_mode[i2 + 1];
            } else {
                mode2 = Mode.NULL;
            }
            if (i2 == 0) {
                if (i == 1 && i3 == 2 && mode3 == Mode.ABORC) {
                    this.block_mode[i2] = Mode.LATCHC;
                }
                if (mode3 == Mode.ABORC) {
                    if (i3 >= 4) {
                        this.block_mode[i2] = Mode.LATCHC;
                    } else {
                        this.block_mode[i2] = Mode.AORB;
                        mode3 = Mode.AORB;
                    }
                }
                if (mode3 == Mode.SHIFTA) {
                    this.block_mode[i2] = Mode.LATCHA;
                }
                if (mode3 == Mode.AORB && mode2 == Mode.SHIFTA) {
                    this.block_mode[i2] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.AORB) {
                    this.block_mode[i2] = Mode.LATCHB;
                }
            } else {
                if (mode3 == Mode.ABORC && i3 >= 4) {
                    this.block_mode[i2] = Mode.LATCHC;
                    mode3 = Mode.LATCHC;
                }
                if (mode3 == Mode.ABORC) {
                    this.block_mode[i2] = Mode.AORB;
                    mode3 = Mode.AORB;
                }
                if (mode3 == Mode.AORB && mode == Mode.LATCHA) {
                    this.block_mode[i2] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.AORB && mode == Mode.LATCHB) {
                    this.block_mode[i2] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.AORB && mode2 == Mode.SHIFTA) {
                    this.block_mode[i2] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.AORB && mode2 == Mode.SHIFTB) {
                    this.block_mode[i2] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.AORB) {
                    this.block_mode[i2] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.SHIFTA && i3 > 1) {
                    this.block_mode[i2] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.SHIFTB && i3 > 1) {
                    this.block_mode[i2] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.SHIFTA && mode == Mode.LATCHA) {
                    this.block_mode[i2] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.SHIFTB && mode == Mode.LATCHB) {
                    this.block_mode[i2] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.SHIFTA && mode == Mode.LATCHC) {
                    this.block_mode[i2] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.SHIFTB && mode == Mode.LATCHC) {
                    this.block_mode[i2] = Mode.LATCHB;
                    Mode mode4 = Mode.LATCHB;
                }
            }
        }
        combineSubsetBlocks(i);
    }

    private void combineSubsetBlocks(int i) {
        if (i > 1) {
            int i2 = 1;
            while (i2 < i) {
                Mode[] modeArr = this.block_mode;
                int i3 = i2 - 1;
                if (modeArr[i3] == modeArr[i2]) {
                    int[] iArr = this.block_length;
                    iArr[i3] = iArr[i3] + iArr[i2];
                    for (int i4 = i2 + 1; i4 < i; i4++) {
                        int[] iArr2 = this.block_length;
                        int i5 = i4 - 1;
                        iArr2[i5] = iArr2[i4];
                        Mode[] modeArr2 = this.block_mode;
                        modeArr2[i5] = modeArr2[i4];
                    }
                    i--;
                    i2--;
                }
                i2++;
            }
        }
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        int i;
        int i2;
        int i3;
        resetPlotElements();
        int i4 = 1;
        int i5 = 1;
        for (int i6 = 0; i6 < this.row_count; i6++) {
            int i7 = 0;
            boolean z = true;
            int i8 = 15;
            while (i7 < this.pattern[i6].length()) {
                if (z) {
                    int charAt = this.pattern[i6].charAt(i7) - '0';
                    if (this.row_height[i6] == -1) {
                        i2 = this.default_height;
                    } else {
                        i2 = this.row_height[i6];
                    }
                    if (charAt == 0 || i2 == 0) {
                        i = i7;
                        i3 = i2;
                    } else {
                        i = i7;
                        i3 = i2;
                        this.rectangles.add(new Rectangle(i8, i4, charAt, i2));
                    }
                    int i9 = charAt + i8;
                    if (i9 > this.symbol_width) {
                        this.symbol_width = i9;
                    }
                    i5 = i3;
                    z = false;
                } else {
                    i = i7;
                    z = true;
                }
                i8 += this.pattern[i6].charAt(r2) - '0';
                i7 = i + 1;
            }
            i4 += i5;
            if (i4 > this.symbol_height) {
                this.symbol_height = i4;
            }
            if (i6 != this.row_count - 1) {
                this.rectangles.add(new Rectangle(15.0d, i4 - 1, this.symbol_width - 15, 2.0d));
            }
        }
        this.rectangles.add(new Rectangle(0.0d, 0.0d, this.symbol_width + 15, 2.0d));
        this.rectangles.add(new Rectangle(0.0d, i4 - 1, this.symbol_width + 15, 2.0d));
        this.symbol_width += 15;
        this.symbol_height++;
    }
}
