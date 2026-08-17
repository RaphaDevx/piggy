package uk.org.okapibarcode.backend;

import com.google.android.gms.common.ConnectionResult;
import com.salesforce.marketingcloud.analytics.stats.b;
import java.lang.reflect.Array;
import java.math.BigInteger;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes4.dex */
public class CodeOne extends Symbol {
    private static final int[] C40_SHIFT = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
    private static final int[] C40_VALUE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 22, 23, 24, 25, 26, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private static final int[] TEXT_SHIFT = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3};
    private static final int[] TEXT_VALUE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 22, 23, 24, 25, 26, 0, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 27, 28, 29, 30, 31};
    private static final int[] C1_HEIGHT = {16, 22, 28, 40, 52, 70, 104, 148};
    private static final int[] C1_WIDTH = {18, 22, 32, 42, 54, 76, 98, 134};
    private static final int[] C1_DATA_LENGTH = {10, 19, 44, 91, 182, 370, 732, 1480};
    private static final int[] C1_ECC_LENGTH = {10, 16, 26, 44, 70, 140, 280, 560};
    private static final int[] C1_BLOCKS = {1, 1, 1, 1, 1, 2, 4, 8};
    private static final int[] C1_DATA_BLOCKS = {10, 19, 44, 91, 182, 185, 183, 185};
    private static final int[] C1_ECC_BLOCKS = {10, 16, 26, 44, 70, 70, 70, 70};
    private static final int[] C1_GRID_WIDTH = {4, 5, 7, 9, 12, 17, 22, 30};
    private static final int[] C1_GRID_HEIGHT = {5, 7, 10, 15, 21, 30, 46, 68};
    private Version preferredVersion = Version.NONE;
    private int[] data = new int[ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED];
    private int[][] datagrid = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 136, 120);
    private boolean[][] outputGrid = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, 148, 134);

    private enum Mode {
        C1_ASCII,
        C1_C40,
        C1_DECIMAL,
        C1_TEXT,
        C1_EDI,
        C1_BYTE
    }

    public enum Version {
        NONE,
        A,
        B,
        C,
        D,
        E,
        F,
        G,
        H,
        S,
        T
    }

    private boolean isEdiEncodable(int i) {
        boolean z = i == 13;
        if (i == 42) {
            z = true;
        }
        if (i == 62) {
            z = true;
        }
        if (i == 32) {
            z = true;
        }
        if (i >= 48 && i <= 57) {
            z = true;
        }
        if (i < 65 || i > 90) {
            return z;
        }
        return true;
    }

    private double roundUpToNextInteger(double d) {
        double d2 = d - ((int) d);
        return d2 > 0.01d ? (d - d2) + 1.0d : d;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return true;
    }

    public void setPreferredVersion(Version version) {
        this.preferredVersion = version;
    }

    public Version getPreferredVersion() {
        return this.preferredVersion;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int i;
        int i2;
        int i3;
        int[] iArr;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int[] iArr2 = new int[600];
        int[] iArr3 = new int[2100];
        int length = this.content.length();
        ReedSolomon reedSolomon = new ReedSolomon();
        int[] iArr4 = new int[190];
        StringBuilder sb = new StringBuilder();
        if (!this.content.matches("[\u0000-ÿ]+")) {
            throw new OkapiException("Invalid characters in input data");
        }
        if (this.preferredVersion == Version.S) {
            infoLine("Version: S");
            if (length > 18) {
                throw new OkapiException("Input data too long");
            }
            if (!this.content.matches("[0-9]+?")) {
                throw new OkapiException("Invalid characters in input");
            }
            if (length <= 12) {
                i2 = 2;
                i10 = 8;
                i9 = 4;
            } else {
                i9 = 6;
                i10 = 12;
                i2 = 3;
            }
            if (length <= 6) {
                i2 = 1;
                i11 = 2;
                i10 = 4;
            } else {
                i11 = i9;
            }
            BigInteger bigInteger = new BigInteger(this.content);
            for (int i12 = 0; i12 < i10; i12++) {
                this.data[(i10 - i12) - 1] = bigInteger.shiftRight(i12 * 5).and(BigInteger.valueOf(31L)).intValue();
            }
            logCodewords(i10);
            reedSolomon.init_gf(37);
            reedSolomon.init_code(i10, 1);
            reedSolomon.encode(i10, this.data);
            infoLine("ECC Codeword Count: " + i10);
            for (int i13 = 0; i13 < i10; i13++) {
                iArr3[i13] = this.data[i13];
                iArr3[i13 + i10] = reedSolomon.getResult((i10 - i13) - 1);
            }
            for (int i14 = 0; i14 < 136; i14++) {
                for (int i15 = 0; i15 < 120; i15++) {
                    this.datagrid[i14][i15] = 48;
                }
            }
            int i16 = 0;
            for (int i17 = 0; i17 < 2; i17++) {
                for (int i18 = 0; i18 < i11; i18++) {
                    if ((iArr3[i16] & 16) != 0) {
                        this.datagrid[i17 * 2][i18 * 5] = 49;
                    }
                    if ((iArr3[i16] & 8) != 0) {
                        this.datagrid[i17 * 2][(i18 * 5) + 1] = 49;
                    }
                    if ((iArr3[i16] & 4) != 0) {
                        this.datagrid[i17 * 2][(i18 * 5) + 2] = 49;
                    }
                    if ((iArr3[i16] & 2) != 0) {
                        this.datagrid[(i17 * 2) + 1][i18 * 5] = 49;
                    }
                    if ((iArr3[i16] & 1) != 0) {
                        this.datagrid[(i17 * 2) + 1][(i18 * 5) + 1] = 49;
                    }
                    int i19 = i16 + 1;
                    if ((iArr3[i19] & 16) != 0) {
                        this.datagrid[i17 * 2][(i18 * 5) + 3] = 49;
                    }
                    if ((iArr3[i19] & 8) != 0) {
                        this.datagrid[i17 * 2][(i18 * 5) + 4] = 49;
                    }
                    if ((iArr3[i19] & 4) != 0) {
                        this.datagrid[(i17 * 2) + 1][(i18 * 5) + 2] = 49;
                    }
                    if ((iArr3[i19] & 2) != 0) {
                        this.datagrid[(i17 * 2) + 1][(i18 * 5) + 3] = 49;
                    }
                    if ((iArr3[i19] & 1) != 0) {
                        this.datagrid[(i17 * 2) + 1][(i18 * 5) + 4] = 49;
                    }
                    i16 += 2;
                }
            }
            infoLine("Grid Size: " + i11 + " X 2");
            this.row_count = 8;
            this.symbol_width = (i2 * 10) + 1;
            i = 9;
        } else {
            i = 1;
            i2 = 0;
        }
        int i20 = 10;
        if (this.preferredVersion == Version.T) {
            infoLine("Version: T");
            for (int i21 = 0; i21 < 40; i21++) {
                this.data[i21] = 0;
            }
            int encodeAsCode1Data = encodeAsCode1Data();
            if (encodeAsCode1Data > 38) {
                throw new OkapiException("Input data too long");
            }
            if (encodeAsCode1Data <= 24) {
                i6 = 2;
                i5 = 24;
                i7 = 8;
                i8 = 16;
            } else {
                i5 = 38;
                i6 = 3;
                i7 = 12;
                i8 = 22;
            }
            if (encodeAsCode1Data <= 10) {
                i6 = 1;
                i5 = 10;
                i7 = 4;
            } else {
                i20 = i8;
            }
            logCodewords(encodeAsCode1Data);
            while (encodeAsCode1Data < i5) {
                this.data[encodeAsCode1Data] = 129;
                encodeAsCode1Data++;
            }
            reedSolomon.init_gf(301);
            reedSolomon.init_code(i20, 1);
            reedSolomon.encode(i5, this.data);
            infoLine("ECC Codeword Count: " + i20);
            for (int i22 = 0; i22 < i5; i22++) {
                iArr3[i22] = this.data[i22];
            }
            for (int i23 = 0; i23 < i20; i23++) {
                iArr3[i5 + i23] = reedSolomon.getResult((i20 - i23) - 1);
            }
            for (int i24 = 0; i24 < 136; i24++) {
                for (int i25 = 0; i25 < 120; i25++) {
                    this.datagrid[i24][i25] = 48;
                }
            }
            int i26 = 0;
            for (int i27 = 0; i27 < 5; i27++) {
                for (int i28 = 0; i28 < i7; i28++) {
                    if ((iArr3[i26] & 128) != 0) {
                        this.datagrid[i27 * 2][i28 * 4] = 49;
                    }
                    if ((iArr3[i26] & 64) != 0) {
                        this.datagrid[i27 * 2][(i28 * 4) + 1] = 49;
                    }
                    if ((iArr3[i26] & 32) != 0) {
                        this.datagrid[i27 * 2][(i28 * 4) + 2] = 49;
                    }
                    if ((iArr3[i26] & 16) != 0) {
                        this.datagrid[i27 * 2][(i28 * 4) + 3] = 49;
                    }
                    if ((iArr3[i26] & 8) != 0) {
                        this.datagrid[(i27 * 2) + 1][i28 * 4] = 49;
                    }
                    if ((iArr3[i26] & 4) != 0) {
                        this.datagrid[(i27 * 2) + 1][(i28 * 4) + 1] = 49;
                    }
                    if ((iArr3[i26] & 2) != 0) {
                        this.datagrid[(i27 * 2) + 1][(i28 * 4) + 2] = 49;
                    }
                    if ((iArr3[i26] & 1) != 0) {
                        this.datagrid[(i27 * 2) + 1][(i28 * 4) + 3] = 49;
                    }
                    i26++;
                }
            }
            infoLine("Grid Size: " + i7 + " X 5");
            this.row_count = 16;
            this.symbol_width = (i6 * 16) + 1;
            i2 = i6;
            i = 10;
        }
        if (this.preferredVersion != Version.S && this.preferredVersion != Version.T) {
            for (int i29 = 0; i29 < 1500; i29++) {
                this.data[i29] = 0;
            }
            int encodeAsCode1Data2 = encodeAsCode1Data();
            for (int i30 = 7; i30 >= 0; i30--) {
                if (C1_DATA_LENGTH[i30] >= encodeAsCode1Data2) {
                    i = i30 + 1;
                }
            }
            if (getSize(this.preferredVersion) > i) {
                i = getSize(this.preferredVersion);
            }
            int i31 = i - 1;
            infoLine("Version: " + ((char) (i + 64)));
            logCodewords(encodeAsCode1Data2);
            while (true) {
                i3 = C1_DATA_LENGTH[i31];
                if (encodeAsCode1Data2 >= i3) {
                    break;
                }
                this.data[encodeAsCode1Data2] = 129;
                encodeAsCode1Data2++;
            }
            int i32 = C1_BLOCKS[i31];
            reedSolomon.init_gf(301);
            reedSolomon.init_code(C1_ECC_BLOCKS[i31], 0);
            for (int i33 = 0; i33 < i32; i33++) {
                int i34 = 0;
                while (true) {
                    i4 = C1_DATA_BLOCKS[i31];
                    if (i34 >= i4) {
                        break;
                    }
                    iArr4[i34] = this.data[(i34 * i32) + i33];
                    i34++;
                }
                reedSolomon.encode(i4, iArr4);
                for (int i35 = 0; i35 < C1_ECC_BLOCKS[i31]; i35++) {
                    iArr2[(C1_ECC_LENGTH[i31] - ((i35 * i32) + i33)) - 1] = reedSolomon.getResult(i35);
                }
            }
            infoLine("ECC Codeword Count: " + C1_ECC_LENGTH[i31]);
            for (int i36 = 0; i36 < i3; i36++) {
                iArr3[i36] = this.data[i36];
            }
            for (int i37 = 0; i37 < C1_ECC_LENGTH[i31]; i37++) {
                iArr3[i3 + i37] = iArr2[i37];
            }
            for (int i38 = 0; i38 < 136; i38++) {
                for (int i39 = 0; i39 < 120; i39++) {
                    this.datagrid[i38][i39] = 48;
                }
            }
            int i40 = 0;
            int i41 = 0;
            while (true) {
                iArr = C1_GRID_HEIGHT;
                if (i40 >= iArr[i31]) {
                    break;
                }
                for (int i42 = 0; i42 < C1_GRID_WIDTH[i31]; i42++) {
                    if ((iArr3[i41] & 128) != 0) {
                        this.datagrid[i40 * 2][i42 * 4] = 49;
                    }
                    if ((iArr3[i41] & 64) != 0) {
                        this.datagrid[i40 * 2][(i42 * 4) + 1] = 49;
                    }
                    if ((iArr3[i41] & 32) != 0) {
                        this.datagrid[i40 * 2][(i42 * 4) + 2] = 49;
                    }
                    if ((iArr3[i41] & 16) != 0) {
                        this.datagrid[i40 * 2][(i42 * 4) + 3] = 49;
                    }
                    if ((iArr3[i41] & 8) != 0) {
                        this.datagrid[(i40 * 2) + 1][i42 * 4] = 49;
                    }
                    if ((iArr3[i41] & 4) != 0) {
                        this.datagrid[(i40 * 2) + 1][(i42 * 4) + 1] = 49;
                    }
                    if ((iArr3[i41] & 2) != 0) {
                        this.datagrid[(i40 * 2) + 1][(i42 * 4) + 2] = 49;
                    }
                    if ((iArr3[i41] & 1) != 0) {
                        this.datagrid[(i40 * 2) + 1][(i42 * 4) + 3] = 49;
                    }
                    i41++;
                }
                i40++;
            }
            infoLine("Grid Size: " + C1_GRID_WIDTH[i31] + " X " + iArr[i31]);
            this.row_count = C1_HEIGHT[i31];
            this.symbol_width = C1_WIDTH[i31];
        }
        for (int i43 = 0; i43 < 148; i43++) {
            for (int i44 = 0; i44 < 134; i44++) {
                this.outputGrid[i43][i44] = false;
            }
        }
        switch (i) {
            case 1:
                plotCentralFinder(6, 3, 1);
                plotVerticalBar(4, 6, 1);
                plotVerticalBar(12, 5, 0);
                setGridModule(5, 12);
                plotSpigot(0);
                plotSpigot(15);
                plotDataBlock(0, 0, 5, 4, 0, 0);
                plotDataBlock(0, 4, 5, 12, 0, 2);
                plotDataBlock(5, 0, 5, 12, 6, 0);
                plotDataBlock(5, 12, 5, 4, 6, 2);
                break;
            case 2:
                plotCentralFinder(8, 4, 1);
                plotVerticalBar(4, 8, 1);
                plotVerticalBar(16, 7, 0);
                setGridModule(7, 16);
                plotSpigot(0);
                plotSpigot(21);
                plotDataBlock(0, 0, 7, 4, 0, 0);
                plotDataBlock(0, 4, 7, 16, 0, 2);
                plotDataBlock(7, 0, 7, 16, 8, 0);
                plotDataBlock(7, 16, 7, 4, 8, 2);
                break;
            case 3:
                plotCentralFinder(11, 4, 2);
                plotVerticalBar(4, 11, 1);
                plotVerticalBar(26, 13, 1);
                plotVerticalBar(4, 10, 0);
                plotVerticalBar(26, 10, 0);
                plotSpigot(0);
                plotSpigot(27);
                plotDataBlock(0, 0, 10, 4, 0, 0);
                plotDataBlock(0, 4, 10, 20, 0, 2);
                plotDataBlock(0, 24, 10, 4, 0, 4);
                plotDataBlock(10, 0, 10, 4, 8, 0);
                plotDataBlock(10, 4, 10, 20, 8, 2);
                plotDataBlock(10, 24, 10, 4, 8, 4);
                break;
            case 4:
                plotCentralFinder(16, 5, 1);
                plotVerticalBar(4, 16, 1);
                plotVerticalBar(20, 16, 1);
                plotVerticalBar(36, 16, 1);
                plotVerticalBar(4, 15, 0);
                plotVerticalBar(20, 15, 0);
                plotVerticalBar(36, 15, 0);
                plotSpigot(0);
                plotSpigot(12);
                plotSpigot(27);
                plotSpigot(39);
                plotDataBlock(0, 0, 15, 4, 0, 0);
                plotDataBlock(0, 4, 15, 14, 0, 2);
                plotDataBlock(0, 18, 15, 14, 0, 4);
                plotDataBlock(0, 32, 15, 4, 0, 6);
                plotDataBlock(15, 0, 15, 4, 10, 0);
                plotDataBlock(15, 4, 15, 14, 10, 2);
                plotDataBlock(15, 18, 15, 14, 10, 4);
                plotDataBlock(15, 32, 15, 4, 10, 6);
                break;
            case 5:
                plotCentralFinder(22, 5, 2);
                plotVerticalBar(4, 22, 1);
                plotVerticalBar(26, 24, 1);
                plotVerticalBar(48, 22, 1);
                plotVerticalBar(4, 21, 0);
                plotVerticalBar(26, 21, 0);
                plotVerticalBar(48, 21, 0);
                plotSpigot(0);
                plotSpigot(12);
                plotSpigot(39);
                plotSpigot(51);
                plotDataBlock(0, 0, 21, 4, 0, 0);
                plotDataBlock(0, 4, 21, 20, 0, 2);
                plotDataBlock(0, 24, 21, 20, 0, 4);
                plotDataBlock(0, 44, 21, 4, 0, 6);
                plotDataBlock(21, 0, 21, 4, 10, 0);
                plotDataBlock(21, 4, 21, 20, 10, 2);
                plotDataBlock(21, 24, 21, 20, 10, 4);
                plotDataBlock(21, 44, 21, 4, 10, 6);
                break;
            case 6:
                plotCentralFinder(31, 5, 3);
                plotVerticalBar(4, 31, 1);
                plotVerticalBar(26, 35, 1);
                plotVerticalBar(48, 31, 1);
                plotVerticalBar(70, 35, 1);
                plotVerticalBar(4, 30, 0);
                plotVerticalBar(26, 30, 0);
                plotVerticalBar(48, 30, 0);
                plotVerticalBar(70, 30, 0);
                plotSpigot(0);
                plotSpigot(12);
                plotSpigot(24);
                plotSpigot(45);
                plotSpigot(57);
                plotSpigot(69);
                plotDataBlock(0, 0, 30, 4, 0, 0);
                plotDataBlock(0, 4, 30, 20, 0, 2);
                plotDataBlock(0, 24, 30, 20, 0, 4);
                plotDataBlock(0, 44, 30, 20, 0, 6);
                plotDataBlock(0, 64, 30, 4, 0, 8);
                plotDataBlock(30, 0, 30, 4, 10, 0);
                plotDataBlock(30, 4, 30, 20, 10, 2);
                plotDataBlock(30, 24, 30, 20, 10, 4);
                plotDataBlock(30, 44, 30, 20, 10, 6);
                plotDataBlock(30, 64, 30, 4, 10, 8);
                break;
            case 7:
                plotCentralFinder(47, 6, 2);
                plotVerticalBar(6, 47, 1);
                plotVerticalBar(27, 49, 1);
                plotVerticalBar(48, 47, 1);
                plotVerticalBar(69, 49, 1);
                plotVerticalBar(90, 47, 1);
                plotVerticalBar(6, 46, 0);
                plotVerticalBar(27, 46, 0);
                plotVerticalBar(48, 46, 0);
                plotVerticalBar(69, 46, 0);
                plotVerticalBar(90, 46, 0);
                plotSpigot(0);
                plotSpigot(12);
                plotSpigot(24);
                plotSpigot(36);
                plotSpigot(67);
                plotSpigot(79);
                plotSpigot(91);
                plotSpigot(b.i);
                plotDataBlock(0, 0, 46, 6, 0, 0);
                plotDataBlock(0, 6, 46, 19, 0, 2);
                plotDataBlock(0, 25, 46, 19, 0, 4);
                plotDataBlock(0, 44, 46, 19, 0, 6);
                plotDataBlock(0, 63, 46, 19, 0, 8);
                plotDataBlock(0, 82, 46, 6, 0, 10);
                plotDataBlock(46, 0, 46, 6, 12, 0);
                plotDataBlock(46, 6, 46, 19, 12, 2);
                plotDataBlock(46, 25, 46, 19, 12, 4);
                plotDataBlock(46, 44, 46, 19, 12, 6);
                plotDataBlock(46, 63, 46, 19, 12, 8);
                plotDataBlock(46, 82, 46, 6, 12, 10);
                break;
            case 8:
                plotCentralFinder(69, 6, 3);
                plotVerticalBar(6, 69, 1);
                plotVerticalBar(26, 73, 1);
                plotVerticalBar(46, 69, 1);
                plotVerticalBar(66, 73, 1);
                plotVerticalBar(86, 69, 1);
                plotVerticalBar(b.l, 73, 1);
                plotVerticalBar(WebSocketProtocol.PAYLOAD_SHORT, 69, 1);
                plotVerticalBar(6, 68, 0);
                plotVerticalBar(26, 68, 0);
                plotVerticalBar(46, 68, 0);
                plotVerticalBar(66, 68, 0);
                plotVerticalBar(86, 68, 0);
                plotVerticalBar(b.l, 68, 0);
                plotVerticalBar(WebSocketProtocol.PAYLOAD_SHORT, 68, 0);
                plotSpigot(0);
                plotSpigot(12);
                plotSpigot(24);
                plotSpigot(36);
                plotSpigot(48);
                plotSpigot(60);
                plotSpigot(87);
                plotSpigot(99);
                plotSpigot(b.o);
                plotSpigot(123);
                plotSpigot(135);
                plotSpigot(147);
                plotDataBlock(0, 0, 68, 6, 0, 0);
                plotDataBlock(0, 6, 68, 18, 0, 2);
                plotDataBlock(0, 24, 68, 18, 0, 4);
                plotDataBlock(0, 42, 68, 18, 0, 6);
                plotDataBlock(0, 60, 68, 18, 0, 8);
                plotDataBlock(0, 78, 68, 18, 0, 10);
                plotDataBlock(0, 96, 68, 18, 0, 12);
                plotDataBlock(0, 114, 68, 6, 0, 14);
                plotDataBlock(68, 0, 68, 6, 12, 0);
                plotDataBlock(68, 6, 68, 18, 12, 2);
                plotDataBlock(68, 24, 68, 18, 12, 4);
                plotDataBlock(68, 42, 68, 18, 12, 6);
                plotDataBlock(68, 60, 68, 18, 12, 8);
                plotDataBlock(68, 78, 68, 18, 12, 10);
                plotDataBlock(68, 96, 68, 18, 12, 12);
                plotDataBlock(68, 114, 68, 6, 12, 14);
                break;
            case 9:
                plotHorizontalBar(5, 1);
                plotHorizontalBar(7, 1);
                setGridModule(6, 0);
                setGridModule(6, this.symbol_width - 1);
                resetGridModule(7, 1);
                resetGridModule(7, this.symbol_width - 2);
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3) {
                            setGridModule(0, 15);
                            setGridModule(4, 15);
                            setGridModule(6, 15);
                            plotDataBlock(0, 0, 4, 15, 0, 0);
                            plotDataBlock(0, 15, 4, 15, 0, 1);
                            break;
                        }
                    } else {
                        setGridModule(0, 10);
                        setGridModule(4, 10);
                        plotDataBlock(0, 0, 4, 10, 0, 0);
                        plotDataBlock(0, 10, 4, 10, 0, 1);
                        break;
                    }
                } else {
                    setGridModule(0, 5);
                    plotDataBlock(0, 0, 4, 5, 0, 0);
                    plotDataBlock(0, 5, 4, 5, 0, 1);
                    break;
                }
                break;
            case 10:
                plotHorizontalBar(11, 1);
                plotHorizontalBar(13, 1);
                plotHorizontalBar(15, 1);
                setGridModule(12, 0);
                setGridModule(12, this.symbol_width - 1);
                setGridModule(14, 0);
                setGridModule(14, this.symbol_width - 1);
                resetGridModule(13, 1);
                resetGridModule(13, this.symbol_width - 2);
                resetGridModule(15, 1);
                resetGridModule(15, this.symbol_width - 2);
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 == 3) {
                            setGridModule(0, 24);
                            setGridModule(10, 24);
                            setGridModule(12, 24);
                            setGridModule(14, 24);
                            plotDataBlock(0, 0, 10, 24, 0, 0);
                            plotDataBlock(0, 24, 10, 24, 0, 1);
                            break;
                        }
                    } else {
                        setGridModule(0, 16);
                        setGridModule(10, 16);
                        setGridModule(12, 16);
                        plotDataBlock(0, 0, 10, 16, 0, 0);
                        plotDataBlock(0, 16, 10, 16, 0, 1);
                        break;
                    }
                } else {
                    setGridModule(0, 8);
                    setGridModule(10, 8);
                    plotDataBlock(0, 0, 10, 8, 0, 0);
                    plotDataBlock(0, 8, 10, 8, 0, 1);
                    break;
                }
                break;
        }
        this.readable = "";
        this.pattern = new String[this.row_count];
        this.row_height = new int[this.row_count];
        for (int i45 = 0; i45 < this.row_count; i45++) {
            sb.setLength(0);
            for (int i46 = 0; i46 < this.symbol_width; i46++) {
                if (this.outputGrid[i45][i46]) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            this.pattern[i45] = bin2pat(sb);
            this.row_height[i45] = 1;
        }
    }

    private void logCodewords(int i) {
        info("Codewords: ");
        for (int i2 = 0; i2 < i; i2++) {
            infoSpace(this.data[i2]);
        }
        infoLine();
    }

    /* JADX WARN: Code restructure failed: missing block: B:568:0x099a, code lost:
    
        if (r3 == 6) goto L540;
     */
    /* JADX WARN: Removed duplicated region for block: B:110:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0559  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x061e  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0675  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x07e2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x0789  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x083b  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:633:0x0a73 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:645:0x08b1  */
    /* JADX WARN: Removed duplicated region for block: B:661:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:666:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:671:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x011d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int encodeAsCode1Data() {
        /*
            Method dump skipped, instructions count: 2683
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.CodeOne.encodeAsCode1Data():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x016a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private uk.org.okapibarcode.backend.CodeOne.Mode lookAheadTest(int r31, int r32, uk.org.okapibarcode.backend.CodeOne.Mode r33) {
        /*
            Method dump skipped, instructions count: 558
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.CodeOne.lookAheadTest(int, int, uk.org.okapibarcode.backend.CodeOne$Mode):uk.org.okapibarcode.backend.CodeOne$Mode");
    }

    private boolean preferEdi(int i, int i2) {
        int i3;
        int i4 = i2;
        while (true) {
            i3 = i2 + i4;
            if (!isEdiEncodable(this.inputData[i3]) || i3 >= i) {
                break;
            }
            i4++;
        }
        if (i3 == i) {
            return false;
        }
        int i5 = i3 - 1;
        return this.inputData[i5] == 13 || this.inputData[i5] == 42 || this.inputData[i5] == 62;
    }

    private void plotCentralFinder(int i, int i2, int i3) {
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 < i3) {
                plotHorizontalBar((i4 * 2) + i, 1);
            } else {
                int i5 = (i4 * 2) + i;
                plotHorizontalBar(i5, 0);
                if (i4 != i2 - 1) {
                    int i6 = i5 + 1;
                    setGridModule(i6, 1);
                    setGridModule(i6, this.symbol_width - 2);
                }
            }
        }
    }

    private void plotHorizontalBar(int i, int i2) {
        if (i2 != 0) {
            for (int i3 = 0; i3 < this.symbol_width; i3++) {
                setGridModule(i, i3);
            }
            return;
        }
        for (int i4 = 1; i4 < this.symbol_width - 1; i4++) {
            setGridModule(i, i4);
        }
    }

    private void plotVerticalBar(int i, int i2, int i3) {
        int i4 = 0;
        if (i3 != 0) {
            while (i4 < i2) {
                setGridModule(i4, i);
                i4++;
            }
        } else {
            while (i4 < i2) {
                setGridModule((this.row_count - i4) - 1, i);
                i4++;
            }
        }
    }

    private void plotSpigot(int i) {
        for (int i2 = this.symbol_width - 1; i2 > 0; i2--) {
            if (this.outputGrid[i][i2 - 1]) {
                setGridModule(i, i2);
            }
        }
    }

    private void plotDataBlock(int i, int i2, int i3, int i4, int i5, int i6) {
        for (int i7 = i; i7 < i + i3; i7++) {
            for (int i8 = i2; i8 < i2 + i4; i8++) {
                if (this.datagrid[i7][i8] == 49) {
                    setGridModule(i7 + i5, i8 + i6);
                }
            }
        }
    }

    private void setGridModule(int i, int i2) {
        this.outputGrid[i][i2] = true;
    }

    private void resetGridModule(int i, int i2) {
        this.outputGrid[i][i2] = false;
    }

    /* renamed from: uk.org.okapibarcode.backend.CodeOne$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Mode;
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version;

        static {
            int[] iArr = new int[Version.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version = iArr;
            try {
                iArr[Version.A.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version[Version.B.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version[Version.C.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version[Version.D.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version[Version.E.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version[Version.F.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version[Version.G.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version[Version.H.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            int[] iArr2 = new int[Mode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Mode = iArr2;
            try {
                iArr2[Mode.C1_C40.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Mode[Mode.C1_TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Mode[Mode.C1_EDI.ordinal()] = 3;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$CodeOne$Mode[Mode.C1_BYTE.ordinal()] = 4;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    private static int getSize(Version version) {
        switch (AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$CodeOne$Version[version.ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 8;
            default:
                return 0;
        }
    }
}
