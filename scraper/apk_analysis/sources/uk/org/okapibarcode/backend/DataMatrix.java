package uk.org.okapibarcode.backend;

import androidx.compose.runtime.ComposerKt;
import org.apache.commons.lang3.StringUtils;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class DataMatrix extends Symbol {
    private static final int DM_SIZES_COUNT;
    private static final int[] MATRIX_H;
    private int binary_length;
    private int codewordCount;
    private Mode last_mode;
    private int[] places;
    private int preferredSize;
    private int process_p;
    private boolean separatorGs;
    private static final int[] C40_SHIFT = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
    private static final int[] C40_VALUE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 22, 23, 24, 25, 26, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private static final int[] TEXT_SHIFT = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3};
    private static final int[] TEXT_VALUE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 22, 23, 24, 25, 26, 0, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 27, 28, 29, 30, 31};
    private static final int[] INT_SYMBOL = {0, 1, 3, 5, 7, 8, 10, 12, 13, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 2, 4, 6, 9, 11, 14};
    private static final int[] MATRIX_W = {10, 12, 18, 14, 32, 16, 26, 18, 20, 36, 22, 36, 24, 26, 48, 32, 36, 40, 44, 48, 52, 64, 72, 80, 88, 96, 104, 120, 132, 144};
    private static final int[] MATRIX_FH = {10, 12, 8, 14, 8, 16, 12, 18, 20, 12, 22, 16, 24, 26, 16, 16, 18, 20, 22, 24, 26, 16, 18, 20, 22, 24, 26, 20, 22, 24};
    private static final int[] MATRIX_FW = {10, 12, 18, 14, 16, 16, 26, 18, 20, 18, 22, 18, 24, 26, 24, 16, 18, 20, 22, 24, 26, 16, 18, 20, 22, 24, 26, 20, 22, 24};
    private static final int[] MATRIX_BYTES = {3, 5, 5, 8, 10, 12, 16, 18, 22, 22, 30, 32, 36, 44, 49, 62, 86, 114, 144, 174, ComposerKt.providerMapsKey, 280, 368, 456, 576, 696, 816, 1050, 1304, 1558};
    private static final int[] MATRIX_DATA_BLOCK = {3, 5, 5, 8, 10, 12, 16, 18, 22, 22, 30, 32, 36, 44, 49, 62, 86, 114, 144, 174, 102, 140, 92, 114, 144, 174, 136, 175, 163, 156};
    private static final int[] MATRIX_RS_BLOCK = {5, 7, 7, 10, 11, 12, 14, 14, 18, 18, 20, 24, 24, 28, 28, 36, 42, 48, 56, 68, 42, 56, 36, 48, 56, 68, 56, 68, 62, 62};
    private ForceMode forceMode = ForceMode.NONE;
    private int structuredAppendFileId = 1;
    private int structuredAppendPosition = 1;
    private int structuredAppendTotal = 1;
    private int actualSize = -1;
    private int[] target = new int[2200];
    private int[] binary = new int[2200];
    private int[] process_buffer = new int[8];

    public enum ForceMode {
        NONE,
        SQUARE,
        RECTANGULAR
    }

    private enum Mode {
        NULL,
        DM_ASCII,
        DM_C40,
        DM_TEXT,
        DM_X12,
        DM_EDIFACT,
        DM_BASE256
    }

    private boolean isX12(int i) {
        return i == 13 || i == 42 || i == 62 || i == 32 || (i >= 48 && i <= 57) || (i >= 65 && i <= 90);
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return true;
    }

    static {
        int[] iArr = {10, 12, 8, 14, 8, 16, 12, 18, 20, 12, 22, 16, 24, 26, 16, 32, 36, 40, 44, 48, 52, 64, 72, 80, 88, 96, 104, 120, 132, 144};
        MATRIX_H = iArr;
        DM_SIZES_COUNT = iArr.length;
    }

    public void setForceMode(ForceMode forceMode) {
        this.forceMode = forceMode;
    }

    public ForceMode getForceMode() {
        return this.forceMode;
    }

    public void setPreferredSize(int i) {
        this.preferredSize = i;
    }

    public int getPreferredSize() {
        return this.preferredSize;
    }

    public int getActualSize() {
        int i = this.actualSize;
        if (i != -1) {
            return i;
        }
        throw new IllegalStateException("Actual size not calculated until symbol is encoded.");
    }

    public int getActualWidth() {
        return MATRIX_W[INT_SYMBOL[getActualSize() - 1]];
    }

    public int getActualHeight() {
        return MATRIX_H[INT_SYMBOL[getActualSize() - 1]];
    }

    public void setStructuredAppendPosition(int i) {
        if (i < 1 || i > 16) {
            throw new IllegalArgumentException("Invalid Data Matrix structured append position: " + i);
        }
        this.structuredAppendPosition = i;
    }

    public int getStructuredAppendPosition() {
        return this.structuredAppendPosition;
    }

    public void setStructuredAppendTotal(int i) {
        if (i < 1 || i > 16) {
            throw new IllegalArgumentException("Invalid Data Matrix structured append total: " + i);
        }
        this.structuredAppendTotal = i;
    }

    public int getStructuredAppendTotal() {
        return this.structuredAppendTotal;
    }

    public void setStructuredAppendFileId(int i) {
        if (i < 1 || i > 64516) {
            throw new IllegalArgumentException("Invalid Data Matrix structured append file ID: " + i);
        }
        this.structuredAppendFileId = i;
    }

    public int getStructuredAppendFileId() {
        return this.structuredAppendFileId;
    }

    public void setGs1SeparatorGs(boolean z) {
        this.separatorGs = z;
    }

    public boolean getGs1SeparatorGs() {
        return this.separatorGs;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int i;
        StringBuilder sb = new StringBuilder();
        eciProcess();
        int generateCodewords = generateCodewords();
        int i2 = this.preferredSize;
        int i3 = 1;
        int i4 = (i2 < 1 || i2 > DM_SIZES_COUNT) ? -1 : INT_SYMBOL[i2 - 1];
        int i5 = this.process_p + generateCodewords;
        if (this.last_mode == Mode.DM_X12 && this.process_p == 2) {
            i5++;
        }
        int i6 = DM_SIZES_COUNT;
        int i7 = i6 - 1;
        for (int i8 = i6 - 1; i8 > -1; i8--) {
            if (MATRIX_BYTES[i8] >= i5) {
                i7 = i8;
            }
        }
        if (i4 == -1) {
            if (this.forceMode == ForceMode.SQUARE) {
                while (i7 < DM_SIZES_COUNT && MATRIX_H[i7] != MATRIX_W[i7]) {
                    i7++;
                }
            } else if (this.forceMode == ForceMode.RECTANGULAR) {
                while (i7 < DM_SIZES_COUNT && MATRIX_H[i7] == MATRIX_W[i7]) {
                    i7++;
                }
            }
            i4 = i7;
            if (i4 >= DM_SIZES_COUNT) {
                throw new OkapiException("Data too long to fit in symbol");
            }
        } else if (i7 > i4) {
            throw new OkapiException("Input too long for selected symbol size");
        }
        int[] iArr = MATRIX_BYTES;
        int encodeRemainder = encodeRemainder(iArr[i4] - generateCodewords, generateCodewords);
        int i9 = iArr[i4];
        if (encodeRemainder > i9) {
            throw new OkapiException("Data too long to fit in symbol");
        }
        int i10 = MATRIX_H[i4];
        int i11 = MATRIX_W[i4];
        int i12 = MATRIX_FH[i4];
        int i13 = MATRIX_FW[i4];
        int i14 = MATRIX_DATA_BLOCK[i4];
        int i15 = MATRIX_RS_BLOCK[i4];
        this.codewordCount = i14 + i15;
        int i16 = i9 - encodeRemainder;
        if (i16 != 0) {
            addPadBits(encodeRemainder, i16);
        }
        int i17 = 0;
        calculateErrorCorrection(i9, i14, i15, i4 == 29 ? 1 : 0);
        int i18 = i11 - ((i11 / i13) * 2);
        int i19 = i10 - ((i10 / i12) * 2);
        this.places = new int[i18 * i19];
        placeData(i19, i18);
        int i20 = i11 * i10;
        int[] iArr2 = new int[i20];
        for (int i21 = 0; i21 < i20; i21++) {
            iArr2[i21] = 0;
        }
        for (int i22 = 0; i22 < i10; i22 += i12) {
            for (int i23 = 0; i23 < i11; i23++) {
                iArr2[(i22 * i11) + i23] = 1;
            }
            for (int i24 = 0; i24 < i11; i24 += 2) {
                iArr2[(((i22 + i12) - 1) * i11) + i24] = 1;
            }
        }
        for (int i25 = 0; i25 < i11; i25 += i13) {
            for (int i26 = 0; i26 < i10; i26++) {
                iArr2[(i26 * i11) + i25] = 1;
            }
            for (int i27 = 0; i27 < i10; i27 += 2) {
                iArr2[(((i27 * i11) + i25) + i13) - 1] = 1;
            }
        }
        int i28 = 0;
        while (i28 < i19) {
            int i29 = i17;
            while (i29 < i18) {
                int i30 = this.places[(((i19 - i28) - 1) * i18) + i29];
                if (i30 == i3 || (i30 > 7 && (this.target[(i30 >> 3) - 1] & (1 << (i30 & 7))) != 0)) {
                    i = 1;
                    iArr2[((i28 + 1 + ((i28 / (i12 - 2)) * 2)) * i11) + 1 + i29 + ((i29 / (i13 - 2)) * 2)] = 1;
                } else {
                    i = 1;
                }
                i29++;
                i3 = i;
            }
            i28++;
            i17 = 0;
        }
        this.actualSize = Arrays.positionOf(i4, INT_SYMBOL) + 1;
        this.readable = "";
        this.pattern = new String[i10];
        this.row_count = i10;
        this.row_height = new int[i10];
        for (int i31 = i10 - 1; i31 >= 0; i31--) {
            sb.setLength(0);
            for (int i32 = 0; i32 < i11; i32++) {
                if (iArr2[(i11 * i31) + i32] == 1) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            int i33 = (i10 - i31) - 1;
            this.pattern[i33] = bin2pat(sb);
            this.row_height[i33] = this.moduleWidth;
        }
        infoLine("Grid Size: " + i11 + " X " + i10);
        StringBuilder sb2 = new StringBuilder("Data Codewords: ");
        sb2.append(i14);
        infoLine(sb2.toString());
        infoLine("ECC Codewords: " + i15);
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected int[] getCodewords() {
        return java.util.Arrays.copyOf(this.target, this.codewordCount);
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x05c9  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x06e5  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x080b  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x083b A[LOOP:1: B:60:0x0222->B:176:0x083b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x084d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x07fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int generateCodewords() {
        /*
            Method dump skipped, instructions count: 2254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.DataMatrix.generateCodewords():int");
    }

    /* renamed from: uk.org.okapibarcode.backend.DataMatrix$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$DataMatrix$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$DataMatrix$Mode = iArr;
            try {
                iArr[Mode.DM_C40.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$DataMatrix$Mode[Mode.DM_TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$DataMatrix$Mode[Mode.DM_X12.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$DataMatrix$Mode[Mode.DM_EDIFACT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$DataMatrix$Mode[Mode.DM_BASE256.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private int encodeRemainder(int i, int i2) {
        int length = this.inputData.length;
        int i3 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$DataMatrix$Mode[this.last_mode.ordinal()];
        if (i3 == 1 || i3 == 2) {
            int i4 = this.process_p;
            if (i4 == 1) {
                if (i > 1) {
                    this.target[i2] = 254;
                    i2++;
                    info("ASC ");
                }
                this.target[i2] = this.inputData[length - 1] + 1;
                infoSpace(this.target[i2] - 1);
                i2++;
            } else if (i4 == 2) {
                int[] iArr = this.process_buffer;
                int i5 = (iArr[0] * 1600) + (iArr[1] * 40) + 1;
                int[] iArr2 = this.target;
                iArr2[i2] = i5 / 256;
                iArr2[i2 + 1] = i5 % 256;
                int i6 = i2 + 2;
                info("(" + this.process_buffer[0] + StringUtils.SPACE + this.process_buffer[1] + " 0) ");
                if (i > 2) {
                    this.target[i6] = 254;
                    i2 += 3;
                    info("ASC ");
                } else {
                    i2 = i6;
                }
            } else if (i > 0) {
                this.target[i2] = 254;
                i2++;
                info("ASC ");
            }
        } else if (i3 == 3) {
            if (i == 1 && this.process_p == 1) {
                this.target[i2] = this.inputData[length - 1] + 1;
                infoSpace(this.target[i2] - 1);
            } else {
                if (i > 0) {
                    this.target[i2] = 254;
                    i2++;
                    info("ASC ");
                }
                int i7 = this.process_p;
                if (i7 == 1) {
                    this.target[i2] = this.inputData[length - 1] + 1;
                    infoSpace(this.target[i2] - 1);
                } else if (i7 == 2) {
                    this.target[i2] = this.inputData[length - 2] + 1;
                    infoSpace(this.target[i2] - 1);
                    int i8 = i2 + 1;
                    this.target[i8] = this.inputData[length - 1] + 1;
                    infoSpace(this.target[i8] - 1);
                    i2 += 2;
                }
            }
            i2++;
        } else if (i3 == 4) {
            if (i <= 2) {
                if (this.process_p == 1) {
                    this.target[i2] = this.inputData[length - 1] + 1;
                    infoSpace(this.target[i2] - 1);
                    i2++;
                }
                if (this.process_p == 2) {
                    this.target[i2] = this.inputData[length - 2] + 1;
                    infoSpace(this.target[i2] - 1);
                    int i9 = i2 + 1;
                    this.target[i9] = this.inputData[length - 1] + 1;
                    infoSpace(this.target[i9] - 1);
                    i2 += 2;
                }
            } else {
                int i10 = this.process_p;
                if (i10 == 0) {
                    this.target[i2] = 124;
                    i2++;
                    info("(31 0 0 0) ");
                } else if (i10 == 1) {
                    int[] iArr3 = this.target;
                    iArr3[i2] = (this.process_buffer[0] << 2) + 1;
                    iArr3[i2 + 1] = 240;
                    i2 += 2;
                    info("(" + this.process_buffer[0] + " 31 0 0) ");
                } else if (i10 == 2) {
                    int[] iArr4 = this.target;
                    int[] iArr5 = this.process_buffer;
                    iArr4[i2] = (iArr5[0] << 2) + ((iArr5[1] & 48) >> 4);
                    iArr4[i2 + 1] = ((iArr5[1] & 15) << 4) + 7;
                    iArr4[i2 + 2] = 192;
                    i2 += 3;
                    info("(" + this.process_buffer[0] + StringUtils.SPACE + this.process_buffer[1] + " 31 0) ");
                } else if (i10 == 3) {
                    int[] iArr6 = this.target;
                    int[] iArr7 = this.process_buffer;
                    iArr6[i2] = (iArr7[0] << 2) + ((iArr7[1] & 48) >> 4);
                    iArr6[i2 + 1] = ((iArr7[1] & 15) << 4) + ((iArr7[2] & 60) >> 2);
                    iArr6[i2 + 2] = ((iArr7[2] & 3) << 6) + 31;
                    i2 += 3;
                    info("(" + this.process_buffer[0] + StringUtils.SPACE + this.process_buffer[1] + StringUtils.SPACE + this.process_buffer[2] + " 31) ");
                }
            }
        }
        infoLine();
        info("Codewords: ");
        for (int i11 = 0; i11 < i2; i11++) {
            infoSpace(this.target[i11]);
        }
        infoLine();
        return i2;
    }

    private boolean isTwoDigits(int i) {
        int i2 = i + 1;
        return i2 < this.inputData.length && Character.isDigit((char) this.inputData[i]) && Character.isDigit((char) this.inputData[i2]);
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x02a9 A[LOOP:0: B:20:0x004e->B:117:0x02a9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02a8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private uk.org.okapibarcode.backend.DataMatrix.Mode lookAheadTest(int r34, uk.org.okapibarcode.backend.DataMatrix.Mode r35) {
        /*
            Method dump skipped, instructions count: 685
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.DataMatrix.lookAheadTest(int, uk.org.okapibarcode.backend.DataMatrix$Mode):uk.org.okapibarcode.backend.DataMatrix$Mode");
    }

    private boolean p_r_6_2_1(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i < i2) {
            if (i3 == 0 && !isX12(this.inputData[i])) {
                i3 = i;
            }
            if (i4 == 0 && (this.inputData[i] == 13 || this.inputData[i] == 42 || this.inputData[i] == 62)) {
                i4 = i;
            }
            i++;
        }
        return (i3 == 0 || i4 == 0 || i4 >= i3) ? false : true;
    }

    private void calculateErrorCorrection(int i, int i2, int i3, int i4) {
        int i5;
        int i6 = (i + 2) / i2;
        ReedSolomon reedSolomon = new ReedSolomon();
        reedSolomon.init_gf(301);
        reedSolomon.init_code(i3, 1);
        for (int i7 = 0; i7 < i6; i7++) {
            int[] iArr = new int[256];
            int[] iArr2 = new int[256];
            int i8 = 0;
            int i9 = i7;
            while (i9 < i) {
                iArr[i8] = this.target[i9];
                i9 += i6;
                i8++;
            }
            reedSolomon.encode(i8, iArr);
            for (int i10 = 0; i10 < i3; i10++) {
                iArr2[i10] = reedSolomon.getResult(i10);
            }
            int i11 = i3 - 1;
            for (int i12 = i7; i12 < i3 * i6; i12 += i6) {
                if (i4 != 1) {
                    i5 = i11 - 1;
                    this.target[i + i12] = iArr2[i11];
                } else if (i7 < 8) {
                    i5 = i11 - 1;
                    this.target[i + i12 + 2] = iArr2[i11];
                } else {
                    this.target[(i + i12) - 8] = iArr2[i11];
                    i11--;
                }
                i11 = i5;
            }
        }
    }

    private void insertAt(int i, char c) {
        for (int i2 = this.binary_length; i2 > i; i2--) {
            int[] iArr = this.binary;
            iArr[i2] = iArr[i2 - 1];
        }
        this.binary[i] = c;
        this.binary_length++;
    }

    private void insertValueAt(int i, int i2, char c) {
        while (i2 > i) {
            int[] iArr = this.target;
            iArr[i2] = iArr[i2 - 1];
            i2--;
        }
        this.target[i] = c;
    }

    private void addPadBits(int i, int i2) {
        for (int i3 = i2; i3 > 0; i3--) {
            if (i3 == i2) {
                this.target[i] = 129;
                i++;
            } else {
                int i4 = i + 1;
                int i5 = (i4 * 149) % 253;
                int i6 = i5 + 130;
                if (i6 <= 254) {
                    this.target[i] = i6;
                } else {
                    this.target[i] = i5 - 124;
                }
                i = i4;
            }
        }
    }

    private void placeData(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6 = 0;
        for (int i7 = 0; i7 < i; i7++) {
            for (int i8 = 0; i8 < i2; i8++) {
                this.places[(i7 * i2) + i8] = 0;
            }
        }
        int i9 = 4;
        int i10 = 1;
        while (true) {
            if (i9 == i && i6 == 0) {
                placeCornerA(i, i2, i10);
                i10++;
            }
            int i11 = i - 2;
            if (i9 == i11 && i6 == 0 && i2 % 4 != 0) {
                placeCornerB(i, i2, i10);
                i10++;
            }
            if (i9 == i11 && i6 == 0 && i2 % 8 == 4) {
                placeCornerC(i, i2, i10);
                i10++;
            }
            if (i9 == i + 4 && i6 == 2 && i2 % 8 == 0) {
                placeCornerD(i, i2, i10);
                i3 = i6;
                i4 = i9;
                i5 = i10 + 1;
            } else {
                i3 = i6;
                i4 = i9;
                i5 = i10;
            }
            while (true) {
                if (i4 < i && i3 >= 0 && this.places[(i4 * i2) + i3] == 0) {
                    placeBlock(i, i2, i4, i3, i5);
                    i5++;
                }
                int i12 = i4 - 2;
                int i13 = i3 + 2;
                if (i12 < 0 || i13 >= i2) {
                    break;
                }
                i3 = i13;
                i4 = i12;
            }
            int i14 = i4 - 1;
            int i15 = i3 + 5;
            while (true) {
                if (i14 >= 0 && i15 < i2 && this.places[(i14 * i2) + i15] == 0) {
                    placeBlock(i, i2, i14, i15, i5);
                    i5++;
                }
                int i16 = i14 + 2;
                int i17 = i15 - 2;
                if (i16 >= i || i17 < 0) {
                    break;
                }
                i14 = i16;
                i15 = i17;
            }
            i9 = i14 + 5;
            i6 = i15 - 1;
            if (i9 >= i && i6 >= i2) {
                break;
            } else {
                i10 = i5;
            }
        }
        int[] iArr = this.places;
        int i18 = i * i2;
        int i19 = i18 - 1;
        if (iArr[i19] == 0) {
            iArr[(i18 - i2) - 2] = 1;
            iArr[i19] = 1;
        }
    }

    private void placeCornerA(int i, int i2, int i3) {
        int i4 = i - 1;
        placeBit(i, i2, i4, 0, i3, 7);
        placeBit(i, i2, i4, 1, i3, 6);
        placeBit(i, i2, i4, 2, i3, 5);
        placeBit(i, i2, 0, i2 - 2, i3, 4);
        int i5 = i2 - 1;
        placeBit(i, i2, 0, i5, i3, 3);
        placeBit(i, i2, 1, i5, i3, 2);
        placeBit(i, i2, 2, i5, i3, 1);
        placeBit(i, i2, 3, i5, i3, 0);
    }

    private void placeCornerB(int i, int i2, int i3) {
        placeBit(i, i2, i - 3, 0, i3, 7);
        placeBit(i, i2, i - 2, 0, i3, 6);
        placeBit(i, i2, i - 1, 0, i3, 5);
        placeBit(i, i2, 0, i2 - 4, i3, 4);
        placeBit(i, i2, 0, i2 - 3, i3, 3);
        placeBit(i, i2, 0, i2 - 2, i3, 2);
        int i4 = i2 - 1;
        placeBit(i, i2, 0, i4, i3, 1);
        placeBit(i, i2, 1, i4, i3, 0);
    }

    private void placeCornerC(int i, int i2, int i3) {
        placeBit(i, i2, i - 3, 0, i3, 7);
        placeBit(i, i2, i - 2, 0, i3, 6);
        placeBit(i, i2, i - 1, 0, i3, 5);
        placeBit(i, i2, 0, i2 - 2, i3, 4);
        int i4 = i2 - 1;
        placeBit(i, i2, 0, i4, i3, 3);
        placeBit(i, i2, 1, i4, i3, 2);
        placeBit(i, i2, 2, i4, i3, 1);
        placeBit(i, i2, 3, i4, i3, 0);
    }

    private void placeCornerD(int i, int i2, int i3) {
        int i4 = i - 1;
        placeBit(i, i2, i4, 0, i3, 7);
        int i5 = i2 - 1;
        placeBit(i, i2, i4, i5, i3, 6);
        int i6 = i2 - 3;
        placeBit(i, i2, 0, i6, i3, 5);
        int i7 = i2 - 2;
        placeBit(i, i2, 0, i7, i3, 4);
        placeBit(i, i2, 0, i5, i3, 3);
        placeBit(i, i2, 1, i6, i3, 2);
        placeBit(i, i2, 1, i7, i3, 1);
        placeBit(i, i2, 1, i5, i3, 0);
    }

    private void placeBlock(int i, int i2, int i3, int i4, int i5) {
        int i6 = i3 - 2;
        int i7 = i4 - 2;
        placeBit(i, i2, i6, i7, i5, 7);
        int i8 = i4 - 1;
        placeBit(i, i2, i6, i8, i5, 6);
        int i9 = i3 - 1;
        placeBit(i, i2, i9, i7, i5, 5);
        placeBit(i, i2, i9, i8, i5, 4);
        placeBit(i, i2, i9, i4, i5, 3);
        placeBit(i, i2, i3, i7, i5, 2);
        placeBit(i, i2, i3, i8, i5, 1);
        placeBit(i, i2, i3, i4, i5, 0);
    }

    private void placeBit(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i3 < 0) {
            i3 += i;
            i4 += 4 - ((i + 4) % 8);
        }
        if (i4 < 0) {
            i4 += i2;
            i3 += 4 - ((i2 + 4) % 8);
        }
        this.places[(i3 * i2) + i4] = (i5 << 3) + i6;
    }
}
