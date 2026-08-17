package uk.org.okapibarcode.backend;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.zxing.common.StringUtils;
import com.salesforce.marketingcloud.analytics.stats.b;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes4.dex */
public class MicroQrCode extends Symbol {
    private StringBuilder binary;
    private int[] eval;
    private int[] grid;
    private qrMode[] inputMode;
    private int preferredVersion;
    private static final char[] RHODIUM = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', '+', '-', ClassUtils.PACKAGE_SEPARATOR_CHAR, JsonPointer.SEPARATOR, ':'};
    private static final int[] QR_ANNEX_C1 = {17477, 16754, 20011, 19228, 21934, 20633, 24512, 23287, 26515, 25252, 28157, 26826, 30328, 29519, 31766, 31009, 1758, 1001, 3248, 2439, 5941, 4610, 7515, 6252, 9480, 8255, 12134, 10833, 13539, 12756, 16013, 15290};
    private static final int[] MICRO_QR_SIZES = {11, 13, 15, 17};
    private EccMode preferredEccLevel = EccMode.L;
    private int[] binaryCount = new int[4];

    public enum EccMode {
        L,
        M,
        Q,
        H
    }

    private enum qrMode {
        NULL,
        KANJI,
        BINARY,
        ALPHANUM,
        NUMERIC
    }

    private boolean isAlphanumeric(char c) {
        boolean z = c >= '0' && c <= '9';
        if (c >= 'A' && c <= 'Z') {
            z = true;
        }
        if (c == ' ' || c == ':' || c == '$' || c == '%' || c == '*' || c == '+') {
            return true;
        }
        switch (c) {
            case '-':
            case '.':
            case '/':
                return true;
            default:
                return z;
        }
    }

    public void setPreferredVersion(int i) {
        if (i < 0 || i > 4) {
            throw new IllegalArgumentException("Invalid version: " + i);
        }
        this.preferredVersion = i;
    }

    public int getPreferredVersion() {
        return this.preferredVersion;
    }

    public void setEccMode(EccMode eccMode) {
        this.preferredEccLevel = eccMode;
    }

    public EccMode getEccMode() {
        return this.preferredEccLevel;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int i;
        boolean[] zArr = new boolean[4];
        StringBuilder sb = new StringBuilder();
        if (this.content.length() > 35) {
            throw new OkapiException("Input data too long");
        }
        inputCharCheck();
        for (int i2 = 0; i2 < 4; i2++) {
            zArr[i2] = true;
        }
        this.inputMode = new qrMode[40];
        selectEncodingMode();
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < this.content.length(); i5++) {
            if (this.content.charAt(i5) >= '0' && this.content.charAt(i5) <= '9') {
                i4++;
            }
            if (isAlphanumeric(this.content.charAt(i5))) {
                i3++;
            }
        }
        if (i3 == this.content.length()) {
            for (int i6 = 0; i6 < this.content.length(); i6++) {
                this.inputMode[i6] = qrMode.ALPHANUM;
            }
        }
        if (i4 == this.content.length()) {
            for (int i7 = 0; i7 < this.content.length(); i7++) {
                this.inputMode[i7] = qrMode.NUMERIC;
            }
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (int i8 = 0; i8 < this.content.length(); i8++) {
            if (this.inputMode[i8] == qrMode.BINARY) {
                z = true;
            }
            if (this.inputMode[i8] == qrMode.ALPHANUM) {
                z2 = true;
            }
            if (this.inputMode[i8] == qrMode.KANJI) {
                z3 = true;
            }
        }
        getBinaryLength();
        if (z) {
            zArr[0] = false;
            zArr[1] = false;
        }
        if (z2) {
            zArr[0] = false;
        }
        if (z3) {
            zArr[0] = false;
            zArr[1] = false;
        }
        int[] iArr = this.binaryCount;
        if (iArr[0] > 20) {
            zArr[0] = false;
        }
        if (iArr[1] > 40) {
            zArr[1] = false;
        }
        if (iArr[2] > 84) {
            zArr[2] = false;
        }
        if (iArr[3] > 128) {
            throw new OkapiException("Input data too long");
        }
        EccMode eccMode = this.preferredEccLevel;
        if (eccMode == EccMode.H) {
            throw new OkapiException("Error correction level H not available");
        }
        if (eccMode == EccMode.Q) {
            zArr[0] = false;
            zArr[1] = false;
            zArr[2] = false;
            if (this.binaryCount[3] > 80) {
                throw new OkapiException("Input data too long");
            }
        }
        if (eccMode == EccMode.M) {
            zArr[0] = false;
            int[] iArr2 = this.binaryCount;
            if (iArr2[1] > 32) {
                zArr[1] = false;
            }
            if (iArr2[2] > 68) {
                zArr[2] = false;
            }
            if (iArr2[3] > 112) {
                throw new OkapiException("Input data too long");
            }
        }
        int i9 = zArr[2] ? 2 : 3;
        if (zArr[1]) {
            i9 = 1;
        }
        if (zArr[0]) {
            i9 = 0;
        }
        int i10 = this.preferredVersion;
        if (i10 >= 1 && i10 <= 4 && i10 - 1 >= i9) {
            i9 = i10 - 1;
        }
        if (i9 == 3) {
            if (this.binaryCount[3] <= 112) {
                eccMode = EccMode.M;
            }
            if (this.binaryCount[3] <= 80) {
                eccMode = EccMode.Q;
            }
        }
        if (i9 == 2 && this.binaryCount[2] <= 68) {
            eccMode = EccMode.M;
        }
        if (i9 == 1 && this.binaryCount[1] <= 32) {
            eccMode = EccMode.M;
        }
        this.binary = new StringBuilder();
        generateBinary(i9);
        if (this.binary.length() > 128) {
            throw new OkapiException("Input data too long");
        }
        if (i9 == 0) {
            generateM1Symbol();
            infoLine("Version: M1");
        } else if (i9 == 1) {
            generateM2Symbol(eccMode);
            infoLine("Version: M2");
            infoLine("ECC Level: " + levelToLetter(eccMode));
        } else if (i9 == 2) {
            generateM3Symbol(eccMode);
            infoLine("Version: M3");
            infoLine("ECC Level: " + levelToLetter(eccMode));
        } else if (i9 == 3) {
            generateM4Symbol(eccMode);
            infoLine("Version: M4");
            infoLine("ECC Level: " + levelToLetter(eccMode));
        }
        int i11 = MICRO_QR_SIZES[i9];
        this.grid = new int[i11 * i11];
        for (int i12 = 0; i12 < i11; i12++) {
            for (int i13 = 0; i13 < i11; i13++) {
                this.grid[(i12 * i11) + i13] = 0;
            }
        }
        setupBitGrid(i11);
        populateBitGrid(i11);
        int applyBitmask = applyBitmask(i11);
        infoLine("Mask Pattern: " + Integer.toBinaryString(applyBitmask));
        if (i9 == 1) {
            int i14 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$EccMode[eccMode.ordinal()];
            if (i14 != 1) {
                if (i14 == 2) {
                    i = 2;
                }
                i = 0;
            } else {
                i = 1;
            }
        } else if (i9 != 2) {
            if (i9 == 3) {
                int i15 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$EccMode[eccMode.ordinal()];
                if (i15 == 1) {
                    i = 5;
                } else if (i15 == 2) {
                    i = 6;
                } else if (i15 == 3) {
                    i = 7;
                }
            }
            i = 0;
        } else {
            int i16 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$EccMode[eccMode.ordinal()];
            if (i16 != 1) {
                if (i16 == 2) {
                    i = 4;
                }
                i = 0;
            } else {
                i = 3;
            }
        }
        int i17 = QR_ANNEX_C1[(i << 2) + applyBitmask];
        if ((i17 & 16384) != 0) {
            int[] iArr3 = this.grid;
            int i18 = (i11 * 8) + 1;
            iArr3[i18] = iArr3[i18] + 1;
        }
        if ((i17 & 8192) != 0) {
            int[] iArr4 = this.grid;
            int i19 = (i11 * 8) + 2;
            iArr4[i19] = iArr4[i19] + 1;
        }
        if ((i17 & 4096) != 0) {
            int[] iArr5 = this.grid;
            int i20 = (i11 * 8) + 3;
            iArr5[i20] = iArr5[i20] + 1;
        }
        if ((i17 & 2048) != 0) {
            int[] iArr6 = this.grid;
            int i21 = (i11 * 8) + 4;
            iArr6[i21] = iArr6[i21] + 1;
        }
        if ((i17 & 1024) != 0) {
            int[] iArr7 = this.grid;
            int i22 = (i11 * 8) + 5;
            iArr7[i22] = iArr7[i22] + 1;
        }
        if ((i17 & 512) != 0) {
            int[] iArr8 = this.grid;
            int i23 = (i11 * 8) + 6;
            iArr8[i23] = iArr8[i23] + 1;
        }
        if ((i17 & 256) != 0) {
            int[] iArr9 = this.grid;
            int i24 = (i11 * 8) + 7;
            iArr9[i24] = iArr9[i24] + 1;
        }
        if ((i17 & 128) != 0) {
            int[] iArr10 = this.grid;
            int i25 = (i11 * 8) + 8;
            iArr10[i25] = iArr10[i25] + 1;
        }
        if ((i17 & 64) != 0) {
            int[] iArr11 = this.grid;
            int i26 = (i11 * 7) + 8;
            iArr11[i26] = iArr11[i26] + 1;
        }
        if ((i17 & 32) != 0) {
            int[] iArr12 = this.grid;
            int i27 = (i11 * 6) + 8;
            iArr12[i27] = iArr12[i27] + 1;
        }
        if ((i17 & 16) != 0) {
            int[] iArr13 = this.grid;
            int i28 = (i11 * 5) + 8;
            iArr13[i28] = iArr13[i28] + 1;
        }
        if ((i17 & 8) != 0) {
            int[] iArr14 = this.grid;
            int i29 = (i11 * 4) + 8;
            iArr14[i29] = iArr14[i29] + 1;
        }
        if ((i17 & 4) != 0) {
            int[] iArr15 = this.grid;
            int i30 = (i11 * 3) + 8;
            iArr15[i30] = iArr15[i30] + 1;
        }
        if ((i17 & 2) != 0) {
            int[] iArr16 = this.grid;
            int i31 = (i11 * 2) + 8;
            iArr16[i31] = iArr16[i31] + 1;
        }
        if ((i17 & 1) != 0) {
            int[] iArr17 = this.grid;
            int i32 = i11 + 8;
            iArr17[i32] = iArr17[i32] + 1;
        }
        this.readable = "";
        this.pattern = new String[i11];
        this.row_count = i11;
        this.row_height = new int[i11];
        for (int i33 = 0; i33 < i11; i33++) {
            sb.setLength(0);
            for (int i34 = 0; i34 < i11; i34++) {
                if ((this.grid[(i33 * i11) + i34] & 1) != 0) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            this.pattern[i33] = bin2pat(sb);
            this.row_height[i33] = 1;
        }
    }

    private void inputCharCheck() {
        if (this.content.matches("[\u0000-ÿ]+")) {
            return;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.content.length(); i2++) {
            if (this.content.charAt(i2) == '?') {
                i++;
            }
        }
        try {
            int i3 = 0;
            for (byte b : this.content.getBytes(StringUtils.SHIFT_JIS)) {
                if (b == 63) {
                    i3++;
                }
            }
            if (i != i3) {
                throw new OkapiException("Invalid characters in input data");
            }
        } catch (UnsupportedEncodingException unused) {
            throw new OkapiException("Character encoding error");
        }
    }

    private char levelToLetter(EccMode eccMode) {
        int i = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$EccMode[eccMode.ordinal()];
        if (i == 1) {
            return 'L';
        }
        if (i == 2) {
            return 'M';
        }
        if (i != 3) {
            return i != 4 ? ' ' : 'H';
        }
        return 'Q';
    }

    private void selectEncodingMode() {
        int length = this.content.length();
        for (int i = 0; i < length; i++) {
            if (this.content.charAt(i) > 255) {
                this.inputMode[i] = qrMode.KANJI;
            } else {
                this.inputMode[i] = qrMode.BINARY;
                if (isAlphanumeric(this.content.charAt(i))) {
                    this.inputMode[i] = qrMode.ALPHANUM;
                }
                if (this.content.charAt(i) >= '0' && this.content.charAt(i) <= '9') {
                    this.inputMode[i] = qrMode.NUMERIC;
                }
            }
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (this.inputMode[i2] == qrMode.NUMERIC && ((i2 != 0 && this.inputMode[i2 - 1] != qrMode.NUMERIC) || i2 == 0)) {
                int i3 = 0;
                while (true) {
                    int i4 = i3 + i2;
                    if (i4 >= length || this.inputMode[i4] != qrMode.NUMERIC) {
                        break;
                    } else {
                        i3++;
                    }
                }
                if (i3 < 6) {
                    for (int i5 = 0; i5 < i3; i5++) {
                        this.inputMode[i2 + i5] = qrMode.ALPHANUM;
                    }
                }
            }
        }
        for (int i6 = 0; i6 < length; i6++) {
            if (this.inputMode[i6] == qrMode.ALPHANUM && ((i6 != 0 && this.inputMode[i6 - 1] != qrMode.ALPHANUM) || i6 == 0)) {
                int i7 = 0;
                while (true) {
                    int i8 = i7 + i6;
                    if (i8 >= length || this.inputMode[i8] != qrMode.ALPHANUM) {
                        break;
                    } else {
                        i7++;
                    }
                }
                if (i7 < 6) {
                    for (int i9 = 0; i9 < i7; i9++) {
                        this.inputMode[i6 + i9] = qrMode.BINARY;
                    }
                }
            }
        }
    }

    private String toBinary(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        while (i2 != 0) {
            if ((i & i2) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            i2 >>= 1;
        }
        return sb.toString();
    }

    private void getBinaryLength() {
        int i;
        int i2;
        int i3;
        qrMode qrmode = qrMode.NULL;
        for (int i4 = 0; i4 < 4; i4++) {
            this.binaryCount[i4] = 0;
        }
        for (int i5 = 0; i5 < this.content.length(); i5++) {
            if (qrmode != this.inputMode[i5]) {
                int i6 = 0;
                while (true) {
                    i = i6 + 1;
                    int i7 = i5 + i;
                    if (i7 >= this.content.length()) {
                        break;
                    }
                    qrMode[] qrmodeArr = this.inputMode;
                    if (qrmodeArr[i7] != qrmodeArr[i5]) {
                        break;
                    } else {
                        i6 = i;
                    }
                }
                int i8 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$qrMode[this.inputMode[i5].ordinal()];
                if (i8 == 1) {
                    int[] iArr = this.binaryCount;
                    int i9 = i * 13;
                    iArr[2] = iArr[2] + i9 + 5;
                    iArr[3] = iArr[3] + i9 + 7;
                } else if (i8 == 2) {
                    int[] iArr2 = this.binaryCount;
                    int i10 = i * 8;
                    iArr2[2] = iArr2[2] + i10 + 6;
                    iArr2[3] = iArr2[3] + i10 + 8;
                } else if (i8 == 3) {
                    if (i % 2 == 1) {
                        i2 = ((i6 / 2) * 11) + 6;
                    } else {
                        i2 = (i / 2) * 11;
                    }
                    int[] iArr3 = this.binaryCount;
                    iArr3[1] = iArr3[1] + i2 + 4;
                    iArr3[2] = iArr3[2] + i2 + 6;
                    iArr3[3] = iArr3[3] + i2 + 8;
                } else if (i8 == 4) {
                    int i11 = i % 3;
                    if (i11 == 1) {
                        i3 = ((i6 / 3) * 10) + 4;
                    } else if (i11 == 2) {
                        i3 = (((i6 - 1) / 3) * 10) + 7;
                    } else {
                        i3 = (i / 3) * 10;
                    }
                    int[] iArr4 = this.binaryCount;
                    iArr4[0] = iArr4[0] + i3 + 3;
                    iArr4[1] = iArr4[1] + i3 + 5;
                    iArr4[2] = iArr4[2] + i3 + 7;
                    iArr4[3] = iArr4[3] + i3 + 9;
                }
                qrmode = this.inputMode[i5];
            }
        }
        int[] iArr5 = this.binaryCount;
        int i12 = iArr5[1];
        if (i12 < 37) {
            iArr5[1] = i12 + 5;
        }
        int i13 = iArr5[2];
        if (i13 < 81) {
            iArr5[2] = i13 + 7;
        }
        int i14 = iArr5[3];
        if (i14 < 125) {
            iArr5[3] = i14 + 9;
        }
    }

    /* renamed from: uk.org.okapibarcode.backend.MicroQrCode$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$EccMode;
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$qrMode;

        static {
            int[] iArr = new int[qrMode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$qrMode = iArr;
            try {
                iArr[qrMode.KANJI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$qrMode[qrMode.BINARY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$qrMode[qrMode.ALPHANUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$qrMode[qrMode.NUMERIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[EccMode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$EccMode = iArr2;
            try {
                iArr2[EccMode.L.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$EccMode[EccMode.M.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$EccMode[EccMode.Q.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$MicroQrCode$EccMode[EccMode.H.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ba A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void generateBinary(int r15) {
        /*
            Method dump skipped, instructions count: 639
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.MicroQrCode.generateBinary(int):void");
    }

    private void generateM1Symbol() {
        int[] iArr = new int[4];
        int[] iArr2 = new int[3];
        ReedSolomon reedSolomon = new ReedSolomon();
        int length = 20 - this.binary.length();
        if (length <= 4) {
            for (int i = 0; i < length; i++) {
                this.binary.append("0");
            }
        } else {
            int length2 = 8 - (this.binary.length() % 8);
            if (length2 == 8) {
                length2 = 0;
            }
            for (int i2 = 0; i2 < length2; i2++) {
                this.binary.append("0");
            }
            int length3 = this.binary.length();
            if (20 - length3 > 4) {
                int i3 = (16 - length3) / 8;
                for (int i4 = 0; i4 < i3; i4++) {
                    if ((i4 & 1) != 0) {
                        this.binary.append("00010001");
                    } else {
                        this.binary.append("11101100");
                    }
                }
            }
            this.binary.append("0000");
        }
        for (int i5 = 0; i5 < 2; i5++) {
            iArr[i5] = 0;
            int i6 = i5 * 8;
            if (this.binary.charAt(i6) == '1') {
                iArr[i5] = iArr[i5] + 128;
            }
            if (this.binary.charAt(i6 + 1) == '1') {
                iArr[i5] = iArr[i5] + 64;
            }
            if (this.binary.charAt(i6 + 2) == '1') {
                iArr[i5] = iArr[i5] + 32;
            }
            if (this.binary.charAt(i6 + 3) == '1') {
                iArr[i5] = iArr[i5] + 16;
            }
            if (this.binary.charAt(i6 + 4) == '1') {
                iArr[i5] = iArr[i5] + 8;
            }
            if (this.binary.charAt(i6 + 5) == '1') {
                iArr[i5] = iArr[i5] + 4;
            }
            if (this.binary.charAt(i6 + 6) == '1') {
                iArr[i5] = iArr[i5] + 2;
            }
            if (this.binary.charAt(i6 + 7) == '1') {
                iArr[i5] = iArr[i5] + 1;
            }
        }
        iArr[2] = 0;
        if (this.binary.charAt(16) == '1') {
            iArr[2] = iArr[2] + 8;
        }
        if (this.binary.charAt(17) == '1') {
            iArr[2] = iArr[2] + 4;
        }
        if (this.binary.charAt(18) == '1') {
            iArr[2] = iArr[2] + 2;
        }
        if (this.binary.charAt(19) == '1') {
            iArr[2] = iArr[2] + 1;
        }
        info("Codewords: ");
        for (int i7 = 0; i7 < 3; i7++) {
            infoSpace(iArr[i7]);
        }
        infoLine();
        reedSolomon.init_gf(285);
        reedSolomon.init_code(2, 0);
        reedSolomon.encode(3, iArr);
        for (int i8 = 0; i8 < 2; i8++) {
            iArr2[i8] = reedSolomon.getResult(i8);
        }
        for (int i9 = 0; i9 < 2; i9++) {
            this.binary.append(toBinary(iArr2[1 - i9], 128));
        }
    }

    private void generateM2Symbol(EccMode eccMode) {
        int i;
        int i2;
        int[] iArr = new int[6];
        int[] iArr2 = new int[7];
        ReedSolomon reedSolomon = new ReedSolomon();
        int i3 = eccMode == EccMode.M ? 32 : 40;
        int length = 8 - (this.binary.length() % 8);
        if (length == 8) {
            length = 0;
        }
        for (int i4 = 0; i4 < length; i4++) {
            this.binary.append("0");
        }
        int length2 = (i3 - this.binary.length()) / 8;
        for (int i5 = 0; i5 < length2; i5++) {
            if ((i5 & 1) != 0) {
                this.binary.append("00010001");
            } else {
                this.binary.append("11101100");
            }
        }
        if (eccMode == EccMode.M) {
            i2 = 6;
            i = 4;
        } else {
            i = 5;
            i2 = 5;
        }
        for (int i6 = 0; i6 < i; i6++) {
            iArr[i6] = 0;
            int i7 = i6 * 8;
            if (this.binary.charAt(i7) == '1') {
                iArr[i6] = iArr[i6] + 128;
            }
            if (this.binary.charAt(i7 + 1) == '1') {
                iArr[i6] = iArr[i6] + 64;
            }
            if (this.binary.charAt(i7 + 2) == '1') {
                iArr[i6] = iArr[i6] + 32;
            }
            if (this.binary.charAt(i7 + 3) == '1') {
                iArr[i6] = iArr[i6] + 16;
            }
            if (this.binary.charAt(i7 + 4) == '1') {
                iArr[i6] = iArr[i6] + 8;
            }
            if (this.binary.charAt(i7 + 5) == '1') {
                iArr[i6] = iArr[i6] + 4;
            }
            if (this.binary.charAt(i7 + 6) == '1') {
                iArr[i6] = iArr[i6] + 2;
            }
            if (this.binary.charAt(i7 + 7) == '1') {
                iArr[i6] = iArr[i6] + 1;
            }
        }
        info("Codewords: ");
        for (int i8 = 0; i8 < i; i8++) {
            infoSpace(iArr[i8]);
        }
        infoLine();
        reedSolomon.init_gf(285);
        reedSolomon.init_code(i2, 0);
        reedSolomon.encode(i, iArr);
        for (int i9 = 0; i9 < i2; i9++) {
            iArr2[i9] = reedSolomon.getResult(i9);
        }
        for (int i10 = 0; i10 < i2; i10++) {
            this.binary.append(toBinary(iArr2[(i2 - i10) - 1], 128));
        }
    }

    private void generateM3Symbol(EccMode eccMode) {
        int i;
        int i2;
        int[] iArr = new int[12];
        int[] iArr2 = new int[12];
        ReedSolomon reedSolomon = new ReedSolomon();
        int i3 = eccMode == EccMode.M ? 68 : 84;
        int length = i3 - this.binary.length();
        if (length <= 4) {
            for (int i4 = 0; i4 < length; i4++) {
                this.binary.append("0");
            }
        } else {
            int length2 = 8 - (this.binary.length() % 8);
            if (length2 == 8) {
                length2 = 0;
            }
            for (int i5 = 0; i5 < length2; i5++) {
                this.binary.append("0");
            }
            int length3 = i3 - this.binary.length();
            if (length3 > 4) {
                int i6 = (length3 - 4) / 8;
                for (int i7 = 0; i7 < i6; i7++) {
                    if ((i7 & 1) != 0) {
                        this.binary.append("00010001");
                    } else {
                        this.binary.append("11101100");
                    }
                }
            }
            this.binary.append("0000");
        }
        if (eccMode == EccMode.M) {
            i2 = 9;
            i = 8;
        } else {
            i = 6;
            i2 = 11;
        }
        for (int i8 = 0; i8 < i2 - 1; i8++) {
            iArr[i8] = 0;
            int i9 = i8 * 8;
            if (this.binary.charAt(i9) == '1') {
                iArr[i8] = iArr[i8] + 128;
            }
            if (this.binary.charAt(i9 + 1) == '1') {
                iArr[i8] = iArr[i8] + 64;
            }
            if (this.binary.charAt(i9 + 2) == '1') {
                iArr[i8] = iArr[i8] + 32;
            }
            if (this.binary.charAt(i9 + 3) == '1') {
                iArr[i8] = iArr[i8] + 16;
            }
            if (this.binary.charAt(i9 + 4) == '1') {
                iArr[i8] = iArr[i8] + 8;
            }
            if (this.binary.charAt(i9 + 5) == '1') {
                iArr[i8] = iArr[i8] + 4;
            }
            if (this.binary.charAt(i9 + 6) == '1') {
                iArr[i8] = iArr[i8] + 2;
            }
            if (this.binary.charAt(i9 + 7) == '1') {
                iArr[i8] = iArr[i8] + 1;
            }
        }
        if (eccMode == EccMode.L) {
            iArr[10] = 0;
            if (this.binary.charAt(80) == '1') {
                iArr[10] = iArr[10] + 8;
            }
            if (this.binary.charAt(81) == '1') {
                iArr[10] = iArr[10] + 4;
            }
            if (this.binary.charAt(82) == '1') {
                iArr[10] = iArr[10] + 2;
            }
            if (this.binary.charAt(83) == '1') {
                iArr[10] = iArr[10] + 1;
            }
        }
        if (eccMode == EccMode.M) {
            iArr[8] = 0;
            if (this.binary.charAt(64) == '1') {
                iArr[8] = iArr[8] + 8;
            }
            if (this.binary.charAt(65) == '1') {
                iArr[8] = iArr[8] + 4;
            }
            if (this.binary.charAt(66) == '1') {
                iArr[8] = iArr[8] + 2;
            }
            if (this.binary.charAt(67) == '1') {
                iArr[8] = iArr[8] + 1;
            }
        }
        info("Codewords: ");
        for (int i10 = 0; i10 < i2; i10++) {
            infoSpace(iArr[i10]);
        }
        infoLine();
        reedSolomon.init_gf(285);
        reedSolomon.init_code(i, 0);
        reedSolomon.encode(i2, iArr);
        for (int i11 = 0; i11 < i; i11++) {
            iArr2[i11] = reedSolomon.getResult(i11);
        }
        for (int i12 = 0; i12 < i; i12++) {
            this.binary.append(toBinary(iArr2[(i - i12) - 1], 128));
        }
    }

    private void generateM4Symbol(EccMode eccMode) {
        int i;
        int i2;
        int[] iArr = new int[17];
        int[] iArr2 = new int[15];
        ReedSolomon reedSolomon = new ReedSolomon();
        int i3 = eccMode == EccMode.M ? b.p : 128;
        if (eccMode == EccMode.Q) {
            i3 = 80;
        }
        int length = 8 - (this.binary.length() % 8);
        if (length == 8) {
            length = 0;
        }
        for (int i4 = 0; i4 < length; i4++) {
            this.binary.append("0");
        }
        int length2 = (i3 - this.binary.length()) / 8;
        for (int i5 = 0; i5 < length2; i5++) {
            if ((i5 & 1) != 0) {
                this.binary.append("00010001");
            } else {
                this.binary.append("11101100");
            }
        }
        int i6 = 10;
        int i7 = 14;
        if (eccMode == EccMode.M) {
            i = 10;
            i2 = 14;
        } else {
            i = 8;
            i2 = 16;
        }
        if (eccMode != EccMode.Q) {
            i6 = i2;
            i7 = i;
        }
        for (int i8 = 0; i8 < i6; i8++) {
            iArr[i8] = 0;
            int i9 = i8 * 8;
            if (this.binary.charAt(i9) == '1') {
                iArr[i8] = iArr[i8] + 128;
            }
            if (this.binary.charAt(i9 + 1) == '1') {
                iArr[i8] = iArr[i8] + 64;
            }
            if (this.binary.charAt(i9 + 2) == '1') {
                iArr[i8] = iArr[i8] + 32;
            }
            if (this.binary.charAt(i9 + 3) == '1') {
                iArr[i8] = iArr[i8] + 16;
            }
            if (this.binary.charAt(i9 + 4) == '1') {
                iArr[i8] = iArr[i8] + 8;
            }
            if (this.binary.charAt(i9 + 5) == '1') {
                iArr[i8] = iArr[i8] + 4;
            }
            if (this.binary.charAt(i9 + 6) == '1') {
                iArr[i8] = iArr[i8] + 2;
            }
            if (this.binary.charAt(i9 + 7) == '1') {
                iArr[i8] = iArr[i8] + 1;
            }
        }
        info("Codewords: ");
        for (int i10 = 0; i10 < i6; i10++) {
            infoSpace(iArr[i10]);
        }
        infoLine();
        reedSolomon.init_gf(285);
        reedSolomon.init_code(i7, 0);
        reedSolomon.encode(i6, iArr);
        for (int i11 = 0; i11 < i7; i11++) {
            iArr2[i11] = reedSolomon.getResult(i11);
        }
        for (int i12 = 0; i12 < i7; i12++) {
            this.binary.append(toBinary(iArr2[(i7 - i12) - 1], 128));
        }
    }

    private void setupBitGrid(int i) {
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            if (z) {
                int[] iArr = this.grid;
                iArr[i2] = 33;
                iArr[i2 * i] = 33;
                z = false;
            } else {
                int[] iArr2 = this.grid;
                iArr2[i2] = 32;
                iArr2[i2 * i] = 32;
                z = true;
            }
        }
        placeFinderPattern(i, 0, 0);
        for (int i3 = 0; i3 < 7; i3++) {
            int[] iArr3 = this.grid;
            iArr3[(i * 7) + i3] = 16;
            iArr3[(i3 * i) + 7] = 16;
        }
        this.grid[(i * 7) + 7] = 16;
        for (int i4 = 0; i4 < 8; i4++) {
            int[] iArr4 = this.grid;
            int i5 = (i * 8) + i4;
            iArr4[i5] = iArr4[i5] + 32;
            int i6 = (i4 * i) + 8;
            iArr4[i6] = iArr4[i6] + 32;
        }
        int[] iArr5 = this.grid;
        int i7 = (i * 8) + 8;
        iArr5[i7] = iArr5[i7] + 32;
    }

    private void placeFinderPattern(int i, int i2, int i3) {
        int[] iArr = {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int i4 = 0; i4 < 7; i4++) {
            for (int i5 = 0; i5 < 7; i5++) {
                if (iArr[(i5 * 7) + i4] == 1) {
                    this.grid[((i5 + i3) * i) + i4 + i2] = 17;
                } else {
                    this.grid[((i5 + i3) * i) + i4 + i2] = 16;
                }
            }
        }
    }

    private void populateBitGrid(int i) {
        int length = this.binary.length();
        int i2 = i - 1;
        int i3 = i2;
        boolean z = true;
        int i4 = 0;
        int i5 = 0;
        do {
            int i6 = (i - 2) - (i4 * 2);
            int i7 = i3 * i;
            int i8 = i6 + 1 + i7;
            if ((this.grid[i8] & 240) == 0) {
                if (this.binary.charAt(i5) == '1') {
                    this.grid[i8] = 1;
                } else {
                    this.grid[i8] = 0;
                }
                i5++;
            }
            if (i5 < length) {
                int i9 = i7 + i6;
                if ((this.grid[i9] & 240) == 0) {
                    if (this.binary.charAt(i5) == '1') {
                        this.grid[i9] = 1;
                    } else {
                        this.grid[i9] = 0;
                    }
                    i5++;
                }
            }
            i3 = z ? i3 - 1 : i3 + 1;
            if (i3 == 0) {
                i4++;
                i3 = 1;
                z = false;
            }
            if (i3 == i) {
                i4++;
                i3 = i2;
                z = true;
            }
        } while (i5 < length);
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int applyBitmask(int r15) {
        /*
            Method dump skipped, instructions count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.MicroQrCode.applyBitmask(int):int");
    }

    private int evaluateBitmask(int i, int i2) {
        int i3;
        int i4 = 0;
        if (i2 != 0) {
            i3 = 2;
            if (i2 != 1) {
                i3 = i2 != 2 ? i2 != 3 ? 0 : 8 : 4;
            }
        } else {
            i3 = 1;
        }
        int i5 = 0;
        for (int i6 = 1; i6 < i; i6++) {
            int[] iArr = this.eval;
            if ((iArr[((i6 * i) + i) - 1] & i3) != 0) {
                i4++;
            }
            if ((iArr[((i - 1) * i) + i6] & i3) != 0) {
                i5++;
            }
        }
        return i4 <= i5 ? (i4 * 16) + i5 : i4 + (i5 * 16);
    }
}
