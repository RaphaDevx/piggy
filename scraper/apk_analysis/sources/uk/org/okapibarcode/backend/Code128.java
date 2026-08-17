package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class Code128 extends Symbol {
    protected static final String[] CODE128_TABLE = {"212222", "222122", "222221", "121223", "121322", "131222", "122213", "122312", "132212", "221213", "221312", "231212", "112232", "122132", "122231", "113222", "123122", "123221", "223211", "221132", "221231", "213212", "223112", "312131", "311222", "321122", "321221", "312212", "322112", "322211", "212123", "212321", "232121", "111323", "131123", "131321", "112313", "132113", "132311", "211313", "231113", "231311", "112133", "112331", "132131", "113123", "113321", "133121", "313121", "211331", "231131", "213113", "213311", "213131", "311123", "311321", "331121", "312113", "312311", "332111", "314111", "221411", "431111", "111224", "111422", "121124", "121421", "141122", "141221", "112214", "112412", "122114", "122411", "142112", "142211", "241211", "221114", "413111", "241112", "134111", "111242", "121142", "121241", "114212", "124112", "124211", "411212", "421112", "421211", "212141", "214121", "412121", "111143", "111341", "131141", "114113", "114311", "411113", "411311", "113141", "114131", "311141", "411131", "211412", "211214", "211232", "2331112"};
    private boolean suppressModeC = false;
    private Composite compositeMode = Composite.OFF;

    private enum Composite {
        OFF,
        CCA,
        CCB,
        CCC
    }

    private enum FMode {
        SHIFTN,
        LATCHN,
        SHIFTF,
        LATCHF
    }

    private enum Mode {
        NULL,
        SHIFTA,
        LATCHA,
        SHIFTB,
        LATCHB,
        SHIFTC,
        LATCHC,
        AORB,
        ABORC
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return true;
    }

    public void setSuppressModeC(boolean z) {
        this.suppressModeC = z;
    }

    public boolean getSuppressModeC() {
        return this.suppressModeC;
    }

    protected void setCca() {
        this.compositeMode = Composite.CCA;
    }

    protected void setCcb() {
        this.compositeMode = Composite.CCB;
    }

    protected void setCcc() {
        this.compositeMode = Composite.CCC;
    }

    public void unsetCc() {
        this.compositeMode = Composite.OFF;
    }

    /* JADX WARN: Code restructure failed: missing block: B:129:0x0199, code lost:
    
        if (r2 >= 3) goto L386;
     */
    /* JADX WARN: Code restructure failed: missing block: B:131:0x019d, code lost:
    
        if (r3 != (r11 - 1)) goto L387;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x01f3, code lost:
    
        if (r0[r4] == uk.org.okapibarcode.backend.Code128.FMode.LATCHF) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x01f5, code lost:
    
        r2 = r2 + 2.0d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x0218, code lost:
    
        if (r0[r4 - 1] == uk.org.okapibarcode.backend.Code128.FMode.LATCHF) goto L143;
     */
    /* JADX WARN: Code restructure failed: missing block: B:264:0x0405, code lost:
    
        if (r8 != 5) goto L261;
     */
    @Override // uk.org.okapibarcode.backend.Symbol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void encode() {
        /*
            Method dump skipped, instructions count: 1583
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.Code128.encode():void");
    }

    /* renamed from: uk.org.okapibarcode.backend.Code128$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$Code128$Composite;
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$Code128$Mode;

        static {
            int[] iArr = new int[Composite.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$Code128$Composite = iArr;
            try {
                iArr[Composite.CCA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code128$Composite[Composite.CCB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code128$Composite[Composite.CCC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Mode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$Code128$Mode = iArr2;
            try {
                iArr2[Mode.LATCHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code128$Mode[Mode.LATCHB.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code128$Mode[Mode.LATCHC.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code128$Mode[Mode.SHIFTA.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Code128$Mode[Mode.SHIFTB.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private static String removeFncEscapeSequences(String str) {
        return str.replace("\\<FNC1>", "").replace("\\<FNC2>", "").replace("\\<FNC3>", "").replace("\\<FNC4>", "");
    }

    private void resolveOddCs(Mode[] modeArr, int i, int i2, int i3, int i4) {
        Mode mode;
        int i5;
        Mode mode2;
        Mode mode3;
        Mode mode4;
        if ((i3 & 1) != 0) {
            if (i - i2 == 0 || i4 > 0) {
                int i6 = i - 1;
                if (i < modeArr.length && (mode3 = modeArr[i]) != null && mode3 != Mode.LATCHC) {
                    mode = modeArr[i];
                } else {
                    mode = Mode.LATCHB;
                }
                Mode mode5 = mode;
                i5 = i6;
                mode2 = mode5;
            } else {
                i5 = i - i3;
                int i7 = i5 - 1;
                if (i7 >= 0 && (mode4 = modeArr[i7]) != null && mode4 != Mode.LATCHC) {
                    mode2 = modeArr[i7];
                } else {
                    mode2 = Mode.LATCHB;
                }
            }
            modeArr[i5] = mode2;
        }
    }

    private Mode findSubset(int i, int i2) {
        Mode mode;
        if (i == -1) {
            if (i2 % 2 == 0) {
                mode = Mode.ABORC;
            } else {
                mode = Mode.AORB;
            }
        } else if (i == -2 || i == -3 || i == -4) {
            mode = Mode.AORB;
        } else if (i <= 31) {
            mode = Mode.SHIFTA;
        } else if (i >= 48 && i <= 57) {
            mode = Mode.ABORC;
        } else if (i <= 95) {
            mode = Mode.AORB;
        } else if (i <= 127) {
            mode = Mode.SHIFTB;
        } else if (i <= 159) {
            mode = Mode.SHIFTA;
        } else if (i <= 223) {
            mode = Mode.AORB;
        } else {
            mode = Mode.SHIFTB;
        }
        return (this.suppressModeC && mode == Mode.ABORC) ? Mode.AORB : mode;
    }

    private int length(int i, Mode mode) {
        return (i == -1 && mode == Mode.ABORC) ? 2 : 1;
    }

    private int reduceSubsetChanges(Mode[] modeArr, int[] iArr, int i) {
        Mode mode;
        Mode mode2;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Mode mode3 = modeArr[i3];
            int i4 = iArr[i3];
            if (i3 != 0) {
                mode = modeArr[i3 - 1];
            } else {
                mode = Mode.NULL;
            }
            if (i3 != i - 1) {
                mode2 = modeArr[i3 + 1];
            } else {
                mode2 = Mode.NULL;
            }
            int i5 = 0;
            for (int i6 = 0; i6 < i4 - i5; i6++) {
                if (length(this.inputData[i2 + i6], mode3) == 2) {
                    i5++;
                }
            }
            if (i3 == 0) {
                if (i == 1 && i4 == 2 && mode3 == Mode.ABORC) {
                    modeArr[i3] = Mode.LATCHC;
                    mode3 = Mode.LATCHC;
                }
                if (mode3 == Mode.ABORC) {
                    if (i4 >= 4) {
                        modeArr[i3] = Mode.LATCHC;
                        mode3 = Mode.LATCHC;
                    } else {
                        modeArr[i3] = Mode.AORB;
                        mode3 = Mode.AORB;
                    }
                }
                if (mode3 == Mode.SHIFTA) {
                    modeArr[i3] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.AORB && mode2 == Mode.SHIFTA) {
                    modeArr[i3] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.AORB) {
                    modeArr[i3] = Mode.LATCHB;
                    Mode mode4 = Mode.LATCHB;
                }
            } else {
                if (mode3 == Mode.ABORC && i4 >= 4) {
                    modeArr[i3] = Mode.LATCHC;
                    mode3 = Mode.LATCHC;
                }
                if (mode3 == Mode.ABORC) {
                    modeArr[i3] = Mode.AORB;
                    mode3 = Mode.AORB;
                }
                if (mode3 == Mode.AORB && mode == Mode.LATCHA) {
                    modeArr[i3] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.AORB && mode == Mode.LATCHB) {
                    modeArr[i3] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.AORB && mode2 == Mode.SHIFTA) {
                    modeArr[i3] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.AORB && mode2 == Mode.SHIFTB) {
                    modeArr[i3] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.AORB) {
                    modeArr[i3] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.SHIFTA && i4 > 1) {
                    modeArr[i3] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.SHIFTB && i4 > 1) {
                    modeArr[i3] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.SHIFTA && mode == Mode.LATCHA) {
                    modeArr[i3] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.SHIFTB && mode == Mode.LATCHB) {
                    modeArr[i3] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.SHIFTA && mode2 == Mode.AORB) {
                    modeArr[i3] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.SHIFTB && mode2 == Mode.AORB) {
                    modeArr[i3] = Mode.LATCHB;
                    mode3 = Mode.LATCHB;
                }
                if (mode3 == Mode.SHIFTA && mode == Mode.LATCHC) {
                    modeArr[i3] = Mode.LATCHA;
                    mode3 = Mode.LATCHA;
                }
                if (mode3 == Mode.SHIFTB && mode == Mode.LATCHC) {
                    modeArr[i3] = Mode.LATCHB;
                    Mode mode5 = Mode.LATCHB;
                }
            }
            int i7 = iArr[i3] - i5;
            iArr[i3] = i7;
            i2 += i7;
        }
        return combineSubsetBlocks(modeArr, iArr, i);
    }

    private int combineSubsetBlocks(Mode[] modeArr, int[] iArr, int i) {
        if (i > 1) {
            int i2 = 1;
            while (i2 < i) {
                int i3 = i2 - 1;
                if (modeArr[i3] == modeArr[i2]) {
                    iArr[i3] = iArr[i3] + iArr[i2];
                    for (int i4 = i2 + 1; i4 < i; i4++) {
                        int i5 = i4 - 1;
                        iArr[i5] = iArr[i4];
                        modeArr[i5] = modeArr[i4];
                    }
                    i--;
                    i2--;
                }
                i2++;
            }
        }
        return i;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected int[] getCodewords() {
        return getPatternAsCodewords(6);
    }
}
