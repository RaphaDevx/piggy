package uk.org.okapibarcode.backend;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.PointerIconCompat;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.android.gms.wallet.WalletConstants;
import com.salesforce.marketingcloud.analytics.stats.b;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.ClassUtils;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class QrCode extends Symbol {
    private EccLevel preferredEccLevel = EccLevel.L;
    private int preferredVersion;
    private static final char[] RHODIUM = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', '+', '-', ClassUtils.PACKAGE_SEPARATOR_CHAR, JsonPointer.SEPARATOR, ':'};
    private static final int[] QR_DATA_CODEWORDS_L = {19, 34, 55, 80, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 136, 156, 194, 232, 274, 324, 370, 428, 461, 523, 589, 647, 721, 795, 861, 932, PointerIconCompat.TYPE_CELL, 1094, 1174, 1276, 1370, 1468, 1531, 1631, 1735, 1843, 1955, 2071, 2191, 2306, 2434, 2566, 2702, 2812, 2956};
    private static final int[] QR_DATA_CODEWORDS_M = {16, 28, 44, 64, 86, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 124, 154, 182, 216, 254, 290, 334, 365, WalletConstants.ERROR_CODE_APP_LABEL_UNAVAILABLE, 453, TypedValues.PositionType.TYPE_PERCENT_Y, 563, 627, 669, 714, 782, 860, 914, 1000, 1062, 1128, 1193, 1267, 1373, 1455, 1541, 1631, 1725, 1812, 1914, 1992, 2102, 2216, 2334};
    private static final int[] QR_DATA_CODEWORDS_Q = {13, 22, 34, 48, 62, 76, 88, b.n, 132, 154, 180, ComposerKt.referenceKey, 244, 261, 295, 325, 367, 397, 445, 485, 512, 568, 614, 664, 718, 754, 808, 871, 911, 985, 1033, 1115, 1171, 1231, 1286, 1354, 1426, 1502, 1582, 1666};
    private static final int[] QR_DATA_CODEWORDS_H = {9, 16, 26, 36, 46, 60, 66, 86, 100, 122, 140, 158, 180, 197, 223, 253, 283, 313, 341, 385, WalletConstants.ERROR_CODE_SPENDING_LIMIT_EXCEEDED, 442, 464, 514, 538, 596, 628, 661, TypedValues.TransitionType.TYPE_FROM, 745, 793, 845, TypedValues.Custom.TYPE_FLOAT, 961, 986, 1054, 1096, 1142, 1222, 1276};
    private static final int[] QR_BLOCKS_L = {1, 1, 1, 1, 1, 2, 2, 2, 2, 4, 4, 4, 4, 4, 6, 6, 6, 6, 7, 8, 8, 9, 9, 10, 12, 12, 12, 13, 14, 15, 16, 17, 18, 19, 19, 20, 21, 22, 24, 25};
    private static final int[] QR_BLOCKS_M = {1, 1, 1, 2, 2, 4, 4, 4, 5, 5, 5, 8, 9, 9, 10, 10, 11, 13, 14, 16, 17, 17, 18, 20, 21, 23, 25, 26, 28, 29, 31, 33, 35, 37, 38, 40, 43, 45, 47, 49};
    private static final int[] QR_BLOCKS_Q = {1, 1, 2, 2, 4, 4, 6, 6, 8, 8, 8, 10, 12, 16, 12, 17, 16, 18, 21, 20, 23, 23, 25, 27, 29, 34, 34, 35, 38, 40, 43, 45, 48, 51, 53, 56, 59, 62, 65, 68};
    private static final int[] QR_BLOCKS_H = {1, 1, 2, 4, 4, 4, 5, 6, 8, 8, 11, 11, 16, 16, 18, 16, 19, 21, 25, 25, 25, 34, 30, 32, 35, 37, 40, 42, 45, 48, 51, 54, 57, 60, 63, 66, 70, 74, 77, 81};
    private static final int[] QR_TOTAL_CODEWORDS = {26, 44, 70, 100, 134, 172, 196, 242, 292, 346, 404, 466, 532, 581, 655, 733, 815, TypedValues.Custom.TYPE_FLOAT, 991, 1085, 1156, 1258, 1364, 1474, 1588, 1706, 1828, 1921, 2051, 2185, 2323, 2465, 2611, 2761, 2876, 3034, 3196, 3362, 3532, 3706};
    private static final int[] QR_SIZES = {21, 25, 29, 33, 37, 41, 45, 49, 53, 57, 61, 65, 69, 73, 77, 81, 85, 89, 93, 97, 101, 105, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 113, 117, 121, 125, 129, 133, 137, 141, 145, 149, 153, 157, 161, 165, 169, 173, 177};
    private static final int[] QR_ALIGN_LOOPSIZE = {0, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7};
    private static final int[] QR_TABLE_E1 = {6, 18, 0, 0, 0, 0, 0, 6, 22, 0, 0, 0, 0, 0, 6, 26, 0, 0, 0, 0, 0, 6, 30, 0, 0, 0, 0, 0, 6, 34, 0, 0, 0, 0, 0, 6, 22, 38, 0, 0, 0, 0, 6, 24, 42, 0, 0, 0, 0, 6, 26, 46, 0, 0, 0, 0, 6, 28, 50, 0, 0, 0, 0, 6, 30, 54, 0, 0, 0, 0, 6, 32, 58, 0, 0, 0, 0, 6, 34, 62, 0, 0, 0, 0, 6, 26, 46, 66, 0, 0, 0, 6, 26, 48, 70, 0, 0, 0, 6, 26, 50, 74, 0, 0, 0, 6, 30, 54, 78, 0, 0, 0, 6, 30, 56, 82, 0, 0, 0, 6, 30, 58, 86, 0, 0, 0, 6, 34, 62, 90, 0, 0, 0, 6, 28, 50, 72, 94, 0, 0, 6, 26, 50, 74, 98, 0, 0, 6, 30, 54, 78, 102, 0, 0, 6, 28, 54, 80, b.l, 0, 0, 6, 32, 58, 84, b.n, 0, 0, 6, 30, 58, 86, 114, 0, 0, 6, 34, 62, 90, 118, 0, 0, 6, 26, 50, 74, 98, 122, 0, 6, 30, 54, 78, 102, WebSocketProtocol.PAYLOAD_SHORT, 0, 6, 26, 52, 78, 104, 130, 0, 6, 30, 56, 82, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 134, 0, 6, 34, 60, 86, b.p, 138, 0, 6, 30, 58, 86, 114, 142, 0, 6, 34, 62, 90, 118, 146, 0, 6, 30, 54, 78, 102, WebSocketProtocol.PAYLOAD_SHORT, 150, 6, 24, 50, 76, 102, 128, 154, 6, 28, 54, 80, b.l, 132, 158, 6, 32, 58, 84, b.n, 136, 162, 6, 26, 54, 82, b.n, 138, 166, 6, 30, 58, 86, 114, 142, 170};
    private static final int[] QR_ANNEX_C = {21522, 20773, 24188, 23371, 17913, 16590, 20375, 19104, 30660, 29427, 32170, 30877, 26159, 25368, 27713, 26998, 5769, 5054, 7399, 6608, 1890, 597, 3340, 2107, 13663, 12392, 16177, 14854, 9396, 8579, 11994, 11245};
    private static final int[] QR_ANNEX_D = {31892, 34236, 39577, 42195, 48118, 51042, 55367, 58893, 63784, 68472, 70749, 76311, 79154, 84390, 87683, 92361, 96236, 102084, 102881, 110507, 110734, 117786, 119615, 126325, 127568, 133589, 136944, 141498, 145311, 150283, 152622, 158308, 161089, 167017};

    public enum EccLevel {
        L,
        M,
        Q,
        H
    }

    private enum QrMode {
        NULL,
        KANJI,
        BINARY,
        ALPHANUM,
        NUMERIC
    }

    private static boolean isAlpha(int i) {
        return (i >= 48 && i <= 57) || (i >= 65 && i <= 90) || i == 32 || i == 36 || i == 37 || i == 42 || i == 43 || i == 45 || i == 46 || i == 47 || i == 58;
    }

    private static boolean isNumeric(int i) {
        return i >= 48 && i <= 57;
    }

    private static int tribus(int i, int i2, int i3, int i4) {
        return i < 10 ? i2 : (i < 10 || i > 26) ? i4 : i3;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return true;
    }

    public void setPreferredVersion(int i) {
        this.preferredVersion = i;
    }

    public int getPreferredVersion() {
        return this.preferredVersion;
    }

    public void setPreferredEccLevel(EccLevel eccLevel) {
        this.preferredEccLevel = eccLevel;
    }

    public EccLevel getPreferredEccLevel() {
        return this.preferredEccLevel;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int[] iArr;
        boolean z = this.inputDataType == Symbol.DataType.GS1;
        eciProcess();
        if (this.eciMode == 20) {
            Charset forName = Charset.forName("Shift_JIS");
            this.inputData = new int[this.content.length()];
            int i = 0;
            while (i < this.inputData.length) {
                int i2 = i + 1;
                byte[] array = forName.encode(CharBuffer.wrap(this.content, i, i2)).array();
                this.inputData[i] = array.length == 2 ? (array[1] & 255) | ((array[0] & 255) << 8) : array[0];
                i = i2;
            }
        }
        QrMode[] qrModeArr = new QrMode[this.inputData.length];
        defineMode(qrModeArr, this.inputData);
        int binaryLength = getBinaryLength(40, qrModeArr, this.inputData, z, this.eciMode);
        EccLevel eccLevel = this.preferredEccLevel;
        int i3 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel[this.preferredEccLevel.ordinal()];
        if (binaryLength > (i3 != 2 ? i3 != 3 ? i3 != 4 ? 2956 : 1276 : 1666 : 2334) * 8) {
            throw new OkapiException("Input too long for selected error correction level");
        }
        int i4 = 40;
        for (int i5 = 39; i5 >= 0; i5--) {
            int i6 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel[eccLevel.ordinal()];
            if (i6 == 2) {
                iArr = QR_DATA_CODEWORDS_M;
            } else if (i6 == 3) {
                iArr = QR_DATA_CODEWORDS_Q;
            } else if (i6 != 4) {
                iArr = QR_DATA_CODEWORDS_L;
            } else {
                iArr = QR_DATA_CODEWORDS_H;
            }
            int i7 = i5 + 1;
            int binaryLength2 = getBinaryLength(i7, qrModeArr, this.inputData, z, this.eciMode);
            if (iArr[i5] * 8 >= binaryLength2) {
                binaryLength = binaryLength2;
                i4 = i7;
            }
        }
        QrMode[] applyOptimisation = applyOptimisation(i4, qrModeArr);
        int i8 = this.preferredVersion;
        if (i8 >= 1 && i8 <= 40) {
            if (i8 > i4) {
                binaryLength = getBinaryLength(i8, applyOptimisation, this.inputData, z, this.eciMode);
                applyOptimisation = applyOptimisation(i8, applyOptimisation);
                i4 = i8;
            }
            if (this.preferredVersion < i4) {
                throw new OkapiException("Input too long for selected symbol size");
            }
        }
        QrMode[] qrModeArr2 = applyOptimisation;
        int i9 = binaryLength;
        int[] iArr2 = QR_DATA_CODEWORDS_M;
        int i10 = i4 - 1;
        if (i9 <= iArr2[i10] * 8) {
            eccLevel = EccLevel.M;
        }
        int[] iArr3 = QR_DATA_CODEWORDS_Q;
        if (i9 <= iArr3[i10] * 8) {
            eccLevel = EccLevel.Q;
        }
        int[] iArr4 = QR_DATA_CODEWORDS_H;
        if (i9 <= iArr4[i10] * 8) {
            eccLevel = EccLevel.H;
        }
        EccLevel eccLevel2 = eccLevel;
        int i11 = QR_DATA_CODEWORDS_L[i10];
        int i12 = QR_BLOCKS_L[i10];
        int i13 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel[eccLevel2.ordinal()];
        if (i13 == 2) {
            i11 = iArr2[i10];
            i12 = QR_BLOCKS_M[i10];
        } else if (i13 == 3) {
            i11 = iArr3[i10];
            i12 = QR_BLOCKS_Q[i10];
        } else if (i13 == 4) {
            i11 = iArr4[i10];
            i12 = QR_BLOCKS_H[i10];
        }
        int i14 = i11;
        int[] iArr5 = new int[i14 + 1];
        int[] iArr6 = QR_TOTAL_CODEWORDS;
        int[] iArr7 = new int[iArr6[i10] + 1];
        qrBinary(iArr5, i4, i14, qrModeArr2, this.inputData, z, this.eciMode, i9);
        addEcc(iArr7, iArr5, i4, i14, i12);
        int i15 = QR_SIZES[i10];
        int[] iArr8 = new int[i15 * i15];
        infoLine("Version: " + i4);
        infoLine("ECC Level: " + eccLevel2.name());
        setupGrid(iArr8, i15, i4);
        populateGrid(iArr8, i15, iArr7, iArr6[i10]);
        if (i4 >= 7) {
            addVersionInfo(iArr8, i15, i4);
        }
        int applyBitmask = applyBitmask(iArr8, i15, eccLevel2);
        infoLine("Mask Pattern: " + Integer.toBinaryString(applyBitmask));
        addFormatInfo(iArr8, i15, eccLevel2, applyBitmask);
        this.readable = "";
        this.pattern = new String[i15];
        this.row_count = i15;
        this.row_height = new int[i15];
        for (int i16 = 0; i16 < i15; i16++) {
            StringBuilder sb = new StringBuilder(i15);
            for (int i17 = 0; i17 < i15; i17++) {
                if ((iArr8[(i16 * i15) + i17] & 1) != 0) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            this.pattern[i16] = bin2pat(sb);
            this.row_height[i16] = 1;
        }
    }

    private static void defineMode(QrMode[] qrModeArr, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            if (iArr[i] > 255) {
                qrModeArr[i] = QrMode.KANJI;
            } else {
                qrModeArr[i] = QrMode.BINARY;
                if (isAlpha(iArr[i])) {
                    qrModeArr[i] = QrMode.ALPHANUM;
                }
                if (iArr[i] == -1) {
                    qrModeArr[i] = QrMode.ALPHANUM;
                }
                if (isNumeric(iArr[i])) {
                    qrModeArr[i] = QrMode.NUMERIC;
                }
            }
        }
    }

    private static int getBinaryLength(int i, QrMode[] qrModeArr, int[] iArr, boolean z, int i2) {
        int blockLength;
        int length = qrModeArr.length;
        QrMode[] applyOptimisation = applyOptimisation(i, qrModeArr);
        QrMode qrMode = QrMode.NULL;
        int i3 = z ? 4 : 0;
        if (i2 != 3) {
            i3 += 12;
        }
        int i4 = i3;
        int i5 = 0;
        for (int i6 = 0; i6 < length; i6++) {
            if (applyOptimisation[i6] != qrMode) {
                i4 += 4;
                int i7 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$QrCode$QrMode[applyOptimisation[i6].ordinal()];
                if (i7 == 1) {
                    i4 += tribus(i, 8, 10, 12);
                    blockLength = blockLength(i6, applyOptimisation) * 13;
                } else {
                    if (i7 == 2) {
                        i4 += tribus(i, 8, 16, 16);
                        for (int i8 = i6; i8 < blockLength(i6, applyOptimisation) + i6; i8++) {
                            i4 = iArr[i8] > 255 ? i4 + 16 : i4 + 8;
                        }
                    } else if (i7 == 3) {
                        i4 += tribus(i, 9, 11, 13);
                        int blockLength2 = blockLength(i6, applyOptimisation);
                        if (z) {
                            for (int i9 = i6; i9 < i6 + blockLength2; i9++) {
                                if (iArr[i9] == 37) {
                                    i5++;
                                }
                            }
                        }
                        int i10 = blockLength2 + i5;
                        int i11 = i10 % 2;
                        if (i11 == 0) {
                            blockLength = (i10 / 2) * 11;
                        } else if (i11 == 1) {
                            i4 = i4 + (((i10 - 1) / 2) * 11) + 6;
                        }
                    } else if (i7 == 4) {
                        i4 += tribus(i, 10, 12, 14);
                        int blockLength3 = blockLength(i6, applyOptimisation) % 3;
                        if (blockLength3 == 0) {
                            blockLength = (blockLength(i6, applyOptimisation) / 3) * 10;
                        } else if (blockLength3 == 1) {
                            i4 = i4 + (((blockLength(i6, applyOptimisation) - 1) / 3) * 10) + 4;
                        } else if (blockLength3 == 2) {
                            i4 = i4 + (((blockLength(i6, applyOptimisation) - 2) / 3) * 10) + 7;
                        }
                    }
                    qrMode = applyOptimisation[i6];
                }
                i4 += blockLength;
                qrMode = applyOptimisation[i6];
            }
        }
        return i4;
    }

    /* renamed from: uk.org.okapibarcode.backend.QrCode$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel;
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$QrCode$QrMode;

        static {
            int[] iArr = new int[QrMode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$QrCode$QrMode = iArr;
            try {
                iArr[QrMode.KANJI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$QrCode$QrMode[QrMode.BINARY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$QrCode$QrMode[QrMode.ALPHANUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$QrCode$QrMode[QrMode.NUMERIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[EccLevel.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel = iArr2;
            try {
                iArr2[EccLevel.L.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel[EccLevel.M.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel[EccLevel.Q.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel[EccLevel.H.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private static QrMode[] applyOptimisation(int i, QrMode[] qrModeArr) {
        int i2;
        int i3;
        int length = qrModeArr.length;
        QrMode qrMode = QrMode.NULL;
        int i4 = 0;
        for (QrMode qrMode2 : qrModeArr) {
            if (qrMode2 != qrMode) {
                i4++;
                qrMode = qrMode2;
            }
        }
        int[] iArr = new int[i4];
        QrMode[] qrModeArr2 = new QrMode[i4];
        QrMode qrMode3 = QrMode.NULL;
        int i5 = -1;
        int i6 = 0;
        while (true) {
            i2 = 1;
            if (i6 >= length) {
                break;
            }
            QrMode qrMode4 = qrModeArr[i6];
            if (qrMode4 != qrMode3) {
                i5++;
                iArr[i5] = 1;
                qrModeArr2[i5] = qrMode4;
                qrMode3 = qrModeArr[i6];
            } else {
                iArr[i5] = iArr[i5] + 1;
            }
            i6++;
        }
        if (i4 > 1) {
            int i7 = 0;
            while (true) {
                i3 = 4;
                if (i7 > i4 - 2) {
                    break;
                }
                if (qrModeArr2[i7] == QrMode.BINARY) {
                    int i8 = i7 + 1;
                    int i9 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$QrCode$QrMode[qrModeArr2[i8].ordinal()];
                    if (i9 != 1) {
                        if (i9 == 3) {
                            if (iArr[i8] < tribus(i, 7, 8, 9)) {
                                qrModeArr2[i8] = QrMode.BINARY;
                            }
                        } else if (i9 == 4 && iArr[i8] < tribus(i, 3, 4, 5)) {
                            qrModeArr2[i8] = QrMode.BINARY;
                        }
                    } else if (iArr[i8] < tribus(i, 4, 5, 6)) {
                        qrModeArr2[i8] = QrMode.BINARY;
                    }
                }
                if (qrModeArr2[i7] == QrMode.ALPHANUM) {
                    int i10 = i7 + 1;
                    if (qrModeArr2[i10] == QrMode.NUMERIC && iArr[i10] < tribus(i, 6, 8, 10)) {
                        qrModeArr2[i10] = QrMode.ALPHANUM;
                    }
                }
                i7++;
            }
            int i11 = i4 - 1;
            while (i11 > 0) {
                if (qrModeArr2[i11] == QrMode.BINARY) {
                    int i12 = i11 - 1;
                    int i13 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$QrCode$QrMode[qrModeArr2[i12].ordinal()];
                    if (i13 != i2) {
                        if (i13 == 3) {
                            if (iArr[i12] < tribus(i, 7, 8, 9)) {
                                qrModeArr2[i12] = QrMode.BINARY;
                            }
                        } else if (i13 == i3 && iArr[i12] < tribus(i, 3, i3, 5)) {
                            qrModeArr2[i12] = QrMode.BINARY;
                        }
                    } else if (iArr[i12] < tribus(i, i3, 5, 6)) {
                        qrModeArr2[i12] = QrMode.BINARY;
                    }
                }
                if (qrModeArr2[i11] == QrMode.ALPHANUM) {
                    int i14 = i11 - 1;
                    if (qrModeArr2[i14] == QrMode.NUMERIC) {
                        if (iArr[i14] < tribus(i, 6, 8, 10)) {
                            qrModeArr2[i14] = QrMode.ALPHANUM;
                        }
                        i11--;
                        i3 = 4;
                        i2 = 1;
                    }
                }
                i11--;
                i3 = 4;
                i2 = 1;
            }
        }
        QrMode[] qrModeArr3 = new QrMode[qrModeArr.length];
        int i15 = 0;
        for (int i16 = 0; i16 < i4; i16++) {
            QrMode qrMode5 = qrModeArr2[i16];
            for (int i17 = 0; i17 < iArr[i16]; i17++) {
                qrModeArr3[i15] = qrMode5;
                i15++;
            }
        }
        return qrModeArr3;
    }

    private static int blockLength(int i, QrMode[] qrModeArr) {
        int i2;
        QrMode qrMode = qrModeArr[i];
        int i3 = 0;
        do {
            i3++;
            i2 = i + i3;
            if (i2 >= qrModeArr.length) {
                break;
            }
        } while (qrModeArr[i2] == qrMode);
        return i3;
    }

    private void qrBinary(int[] iArr, int i, int i2, QrMode[] qrModeArr, int[] iArr2, boolean z, int i3, int i4) {
        int i5;
        char c;
        int i6;
        int i7;
        int i8;
        StringBuilder sb = new StringBuilder(i4 + 12);
        if (z) {
            sb.append("0101");
        }
        char c2 = 16;
        int i9 = 3;
        int i10 = 8;
        if (i3 != 3) {
            sb.append("0111");
            if (i3 <= 127) {
                binaryAppend(i3, 8, sb);
            } else if (i3 <= 16383) {
                binaryAppend(i3 + 32768, 16, sb);
            } else {
                binaryAppend(i3 + 12582912, 24, sb);
            }
        }
        info("Encoding: ");
        int i11 = 0;
        while (true) {
            QrMode qrMode = qrModeArr[i11];
            int i12 = 0;
            do {
                i12++;
                i5 = i12 + i11;
                if (i5 >= qrModeArr.length) {
                    break;
                }
            } while (qrModeArr[i5] == qrMode);
            int i13 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$QrCode$QrMode[qrMode.ordinal()];
            if (i13 == 1) {
                c = c2;
                sb.append("1000");
                binaryAppend(i12, tribus(i, i10, 10, 12), sb);
                info("KNJI ");
                for (int i14 = 0; i14 < i12; i14++) {
                    int i15 = iArr2[i11 + i14];
                    if (i15 < 33088 || i15 > 40956) {
                        i6 = (i15 >= 57408 && i15 <= 60351) ? 49472 : 33088;
                        int i16 = ((i15 >> 8) * 192) + (i15 & 255);
                        binaryAppend(i16, 13, sb);
                        infoSpace(i16);
                    }
                    i15 -= i6;
                    int i162 = ((i15 >> 8) * 192) + (i15 & 255);
                    binaryAppend(i162, 13, sb);
                    infoSpace(i162);
                }
            } else if (i13 != 2) {
                if (i13 == i9) {
                    sb.append("0010");
                    if (z) {
                        i7 = 0;
                        for (int i17 = 0; i17 < i12; i17++) {
                            if (iArr2[i11 + i17] == 37) {
                                i7++;
                            }
                        }
                    } else {
                        i7 = 0;
                    }
                    int i18 = i7 + i12;
                    int[] iArr3 = new int[i18];
                    int i19 = 0;
                    for (int i20 = 0; i20 < i12; i20++) {
                        int i21 = iArr2[i11 + i20];
                        if (i21 == -1) {
                            iArr3[i20 + i19] = 37;
                        } else {
                            iArr3[i20 + i19] = i21;
                            if (z && i21 == 37) {
                                i19++;
                                iArr3[i20 + i19] = i21;
                            }
                        }
                    }
                    binaryAppend(i18, tribus(i, 9, 11, 13), sb);
                    info("ALPH ");
                    int i22 = 0;
                    while (true) {
                        int i23 = i22 + 1;
                        if (i23 >= i18) {
                            break;
                        }
                        char c3 = (char) iArr3[i22];
                        char[] cArr = RHODIUM;
                        int positionOf = (Arrays.positionOf(c3, cArr) * 45) + Arrays.positionOf((char) iArr3[i23], cArr);
                        binaryAppend(positionOf, 11, sb);
                        infoSpace(positionOf);
                        i22 += 2;
                    }
                    if (i18 % 2 != 0) {
                        int positionOf2 = Arrays.positionOf((char) iArr3[i18 - 1], RHODIUM);
                        binaryAppend(positionOf2, 6, sb);
                        infoSpace(positionOf2);
                    }
                } else if (i13 == 4) {
                    sb.append("0001");
                    binaryAppend(i12, tribus(i, 10, 12, 14), sb);
                    info("NUMB ");
                    int i24 = 0;
                    while (i24 < i12) {
                        int i25 = i11 + i24;
                        int numericValue = Character.getNumericValue(iArr2[i25]);
                        if (i24 + 1 < i12) {
                            numericValue = (numericValue * 10) + Character.getNumericValue(iArr2[i25 + 1]);
                            if (i24 + 2 < i12) {
                                numericValue = (numericValue * 10) + Character.getNumericValue(iArr2[i25 + 2]);
                                i8 = i9;
                            } else {
                                i8 = 2;
                            }
                        } else {
                            i8 = 1;
                        }
                        binaryAppend(numericValue, (i8 * 3) + 1, sb);
                        infoSpace(numericValue);
                        i24 += i8;
                    }
                }
                c = 16;
            } else {
                sb.append("0100");
                c = 16;
                binaryAppend(i12, tribus(i, 8, 16, 16), sb);
                info("BYTE ");
                for (int i26 = 0; i26 < i12; i26++) {
                    int i27 = iArr2[i11 + i26];
                    if (i27 == -1) {
                        i27 = 29;
                    }
                    binaryAppend(i27, 8, sb);
                    infoSpace(i27);
                }
            }
            if (i5 >= qrModeArr.length) {
                break;
            }
            c2 = c;
            i11 = i5;
            i9 = 3;
            i10 = 8;
        }
        infoLine();
        sb.append("0000");
        int length = sb.length();
        int i28 = 8 - (length % 8);
        if (i28 == 8) {
            i28 = 0;
        }
        int i29 = (length + i28) / 8;
        for (int i30 = 0; i30 < i28; i30++) {
            sb.append('0');
        }
        for (int i31 = 0; i31 < i29; i31++) {
            iArr[i31] = 0;
            for (int i32 = 0; i32 < 8; i32++) {
                if (sb.charAt((i31 * 8) + i32) == '1') {
                    iArr[i31] = iArr[i31] + (128 >> i32);
                }
            }
        }
        boolean z2 = false;
        while (i29 < i2) {
            if (!z2) {
                iArr[i29] = 236;
                z2 = true;
            } else {
                iArr[i29] = 17;
                z2 = false;
            }
            i29++;
        }
        info("Codewords: ");
        for (int i33 = 0; i33 < i2; i33++) {
            infoSpace(iArr[i33]);
        }
        infoLine();
    }

    private static void binaryAppend(int i, int i2, StringBuilder sb) {
        int i3 = 1 << (i2 - 1);
        for (int i4 = 0; i4 < i2; i4++) {
            if (((i3 >> i4) & i) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
    }

    private static void addEcc(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int i4 = QR_TOTAL_CODEWORDS[i - 1] - i2;
        int i5 = i2 / i3;
        int i6 = i3 - (i2 % i3);
        int i7 = i4 / i3;
        int[] iArr3 = new int[i5 + 2];
        int[] iArr4 = new int[i7 + 2];
        int[] iArr5 = new int[i2 + 2];
        int[] iArr6 = new int[i4 + 2];
        int i8 = 0;
        int i9 = 0;
        while (i8 < i3) {
            int i10 = i8 < i6 ? i5 : i5 + 1;
            for (int i11 = 0; i11 < i7; i11++) {
                iArr4[i11] = 0;
            }
            for (int i12 = 0; i12 < i10; i12++) {
                iArr3[i12] = iArr2[i9 + i12];
            }
            ReedSolomon reedSolomon = new ReedSolomon();
            reedSolomon.init_gf(285);
            reedSolomon.init_code(i7, 0);
            reedSolomon.encode(i10, iArr3);
            for (int i13 = 0; i13 < i7; i13++) {
                iArr4[i13] = reedSolomon.getResult(i13);
            }
            for (int i14 = 0; i14 < i5; i14++) {
                iArr5[(i14 * i3) + i8] = iArr3[i14];
            }
            if (i8 >= i6) {
                iArr5[(i5 * i3) + (i8 - i6)] = iArr3[i5];
            }
            for (int i15 = 0; i15 < i7; i15++) {
                iArr6[(i15 * i3) + i8] = iArr4[(i7 - i15) - 1];
            }
            i9 += i10;
            i8++;
        }
        for (int i16 = 0; i16 < i2; i16++) {
            iArr[i16] = iArr5[i16];
        }
        for (int i17 = 0; i17 < i4; i17++) {
            iArr[i17 + i2] = iArr6[i17];
        }
    }

    private static void setupGrid(int[] iArr, int i, int i2) {
        boolean z = true;
        for (int i3 = 0; i3 < i; i3++) {
            if (z) {
                iArr[(i * 6) + i3] = 33;
                iArr[(i3 * i) + 6] = 33;
                z = false;
            } else {
                iArr[(i * 6) + i3] = 32;
                iArr[(i3 * i) + 6] = 32;
                z = true;
            }
        }
        placeFinder(iArr, i, 0, 0);
        int i4 = i - 7;
        placeFinder(iArr, i, 0, i4);
        placeFinder(iArr, i, i4, 0);
        for (int i5 = 0; i5 < 7; i5++) {
            int i6 = i * 7;
            iArr[i6 + i5] = 16;
            int i7 = i5 * i;
            iArr[i7 + 7] = 16;
            int i8 = (i - 1) - i5;
            iArr[i6 + i8] = 16;
            int i9 = i - 8;
            iArr[i7 + i9] = 16;
            iArr[(i9 * i) + i5] = 16;
            iArr[(i8 * i) + 7] = 16;
        }
        int i10 = i * 7;
        iArr[i10 + 7] = 16;
        int i11 = i - 8;
        iArr[i10 + i11] = 16;
        iArr[(i11 * i) + 7] = 16;
        if (i2 != 1) {
            int i12 = QR_ALIGN_LOOPSIZE[i2 - 1];
            for (int i13 = 0; i13 < i12; i13++) {
                for (int i14 = 0; i14 < i12; i14++) {
                    int[] iArr2 = QR_TABLE_E1;
                    int i15 = (i2 - 2) * 7;
                    int i16 = iArr2[i15 + i13];
                    int i17 = iArr2[i15 + i14];
                    if ((iArr[(i17 * i) + i16] & 16) == 0) {
                        placeAlign(iArr, i, i16, i17);
                    }
                }
            }
        }
        for (int i18 = 0; i18 < 8; i18++) {
            int i19 = i * 8;
            int i20 = i19 + i18;
            iArr[i20] = iArr[i20] + 32;
            int i21 = (i18 * i) + 8;
            iArr[i21] = iArr[i21] + 32;
            int i22 = (i - 1) - i18;
            iArr[i19 + i22] = 32;
            iArr[(i22 * i) + 8] = 32;
        }
        int i23 = (i * 8) + 8;
        iArr[i23] = iArr[i23] + 32;
        iArr[((i - 8) * i) + 8] = 33;
        if (i2 >= 7) {
            for (int i24 = 0; i24 < 6; i24++) {
                int i25 = i - 9;
                iArr[(i25 * i) + i24] = 32;
                int i26 = i - 10;
                iArr[(i26 * i) + i24] = 32;
                int i27 = i - 11;
                iArr[(i27 * i) + i24] = 32;
                int i28 = i24 * i;
                iArr[i25 + i28] = 32;
                iArr[i26 + i28] = 32;
                iArr[i28 + i27] = 32;
            }
        }
    }

    private static void placeFinder(int[] iArr, int i, int i2, int i3) {
        int[] iArr2 = {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};
        for (int i4 = 0; i4 < 7; i4++) {
            for (int i5 = 0; i5 < 7; i5++) {
                if (iArr2[(i5 * 7) + i4] == 1) {
                    iArr[((i5 + i3) * i) + i4 + i2] = 17;
                } else {
                    iArr[((i5 + i3) * i) + i4 + i2] = 16;
                }
            }
        }
    }

    private static void placeAlign(int[] iArr, int i, int i2, int i3) {
        int[] iArr2 = {1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        int i4 = i2 - 2;
        int i5 = i3 - 2;
        for (int i6 = 0; i6 < 5; i6++) {
            for (int i7 = 0; i7 < 5; i7++) {
                if (iArr2[(i7 * 5) + i6] == 1) {
                    iArr[((i7 + i5) * i) + i6 + i4] = 17;
                } else {
                    iArr[((i7 + i5) * i) + i6 + i4] = 16;
                }
            }
        }
    }

    private static void populateGrid(int[] iArr, int i, int[] iArr2, int i2) {
        int i3 = i2 * 8;
        int i4 = i - 1;
        int i5 = i4;
        boolean z = true;
        int i6 = 0;
        int i7 = 0;
        do {
            int i8 = (i - 2) - (i6 * 2);
            if (i8 < 6) {
                i8--;
            }
            int i9 = i5 * i;
            int i10 = i8 + 1 + i9;
            if ((iArr[i10] & 240) == 0) {
                if (cwbit(iArr2, i7)) {
                    iArr[i10] = 1;
                } else {
                    iArr[i10] = 0;
                }
                i7++;
            }
            if (i7 < i3) {
                int i11 = i9 + i8;
                if ((iArr[i11] & 240) == 0) {
                    if (cwbit(iArr2, i7)) {
                        iArr[i11] = 1;
                    } else {
                        iArr[i11] = 0;
                    }
                    i7++;
                }
            }
            i5 = z ? i5 - 1 : i5 + 1;
            if (i5 == -1) {
                i6++;
                i5 = 0;
                z = false;
            }
            if (i5 == i) {
                i6++;
                i5 = i4;
                z = true;
            }
        } while (i7 < i3);
    }

    private static boolean cwbit(int[] iArr, int i) {
        return (iArr[i / 8] & (128 >> (i % 8))) != 0;
    }

    private static int applyBitmask(int[] iArr, int i, EccLevel eccLevel) {
        int[] iArr2 = new int[8];
        int i2 = i * i;
        byte[] bArr = new byte[i2];
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = (i4 * i) + i3;
                bArr[i5] = 0;
                if ((iArr[i5] & 240) == 0) {
                    int i6 = i4 + i3;
                    int i7 = i6 & 1;
                    if (i7 == 0) {
                        bArr[i5] = (byte) 1;
                    }
                    if ((i4 & 1) == 0) {
                        bArr[i5] = (byte) (bArr[i5] + 2);
                    }
                    if (i3 % 3 == 0) {
                        bArr[i5] = (byte) (bArr[i5] + 4);
                    }
                    if (i6 % 3 == 0) {
                        bArr[i5] = (byte) (bArr[i5] + 8);
                    }
                    if ((((i4 / 2) + (i3 / 3)) & 1) == 0) {
                        bArr[i5] = (byte) (bArr[i5] + 16);
                    }
                    int i8 = i4 * i3;
                    int i9 = i8 & 1;
                    int i10 = i8 % 3;
                    int i11 = i9 + i10;
                    if (i11 == 0) {
                        bArr[i5] = (byte) (bArr[i5] + 32);
                    }
                    if ((i11 & 1) == 0) {
                        bArr[i5] = (byte) (bArr[i5] + 64);
                    }
                    if (((i7 + i10) & 1) == 0) {
                        bArr[i5] = (byte) (bArr[i5] + ByteCompanionObject.MIN_VALUE);
                    }
                }
            }
        }
        for (int i12 = 0; i12 < i; i12++) {
            for (int i13 = 0; i13 < i; i13++) {
                int i14 = (i13 * i) + i12;
                bArr2[i14] = (byte) (((iArr[i14] & 1) != 0 ? UByte.MAX_VALUE : (byte) 0) ^ bArr[i14]);
            }
        }
        for (int i15 = 0; i15 < 8; i15++) {
            addFormatInfoEval(bArr2, i, eccLevel, i15);
            iArr2[i15] = evaluate(bArr2, i, i15);
        }
        int i16 = iArr2[0];
        int i17 = 0;
        for (int i18 = 1; i18 < 8; i18++) {
            int i19 = iArr2[i18];
            if (i19 < i16) {
                i17 = i18;
                i16 = i19;
            }
        }
        for (int i20 = 0; i20 < i; i20++) {
            for (int i21 = 0; i21 < i; i21++) {
                int i22 = (i21 * i) + i20;
                if ((bArr[i22] & (1 << i17)) != 0) {
                    if ((iArr[i22] & 1) != 0) {
                        iArr[i22] = 0;
                    } else {
                        iArr[i22] = 1;
                    }
                }
            }
        }
        return i17;
    }

    private static void addFormatInfoEval(byte[] bArr, int i, EccLevel eccLevel, int i2) {
        int i3 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel[eccLevel.ordinal()];
        int i4 = QR_ANNEX_C[i3 != 1 ? i3 != 3 ? i3 != 4 ? i2 : i2 | 16 : i2 | 24 : i2 | 8];
        for (int i5 = 0; i5 < 6; i5++) {
            bArr[(i5 * i) + 8] = (byte) (((i4 >> i5) & 1) != 0 ? 1 >> i2 : 0);
        }
        for (int i6 = 0; i6 < 8; i6++) {
            bArr[(i * 8) + ((i - i6) - 1)] = (byte) (((i4 >> i6) & 1) != 0 ? 1 >> i2 : 0);
        }
        for (int i7 = 0; i7 < 6; i7++) {
            bArr[(i * 8) + (5 - i7)] = (byte) (((i4 >> (i7 + 9)) & 1) != 0 ? 1 >> i2 : 0);
        }
        for (int i8 = 0; i8 < 7; i8++) {
            bArr[(((i - 7) + i8) * i) + 8] = (byte) (((i4 >> (i8 + 8)) & 1) != 0 ? 1 >> i2 : 0);
        }
        bArr[(i * 7) + 8] = (byte) (((i4 >> 6) & 1) != 0 ? 1 >> i2 : 0);
        int i9 = i * 8;
        bArr[i9 + 8] = (byte) (((i4 >> 7) & 1) != 0 ? 1 >> i2 : 0);
        bArr[i9 + 7] = (byte) (((i4 >> 8) & 1) != 0 ? 1 >> i2 : 0);
    }

    private static int evaluate(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i * i;
        byte[] bArr2 = new byte[i4];
        for (int i5 = 0; i5 < i; i5++) {
            for (int i6 = 0; i6 < i; i6++) {
                int i7 = (i6 * i) + i5;
                if ((bArr[i7] & (1 << i2)) != 0) {
                    bArr2[i7] = 49;
                } else {
                    bArr2[i7] = 48;
                }
            }
        }
        int i8 = 0;
        for (int i9 = 0; i9 < i; i9++) {
            byte b = bArr2[i9];
            int i10 = 0;
            for (int i11 = 0; i11 < i; i11++) {
                byte b2 = bArr2[(i11 * i) + i9];
                if (b2 == b) {
                    i10++;
                } else {
                    if (i10 > 5) {
                        i8 += i10 - 2;
                    }
                    b = b2;
                    i10 = 0;
                }
            }
            if (i10 > 5) {
                i8 += i10 - 2;
            }
        }
        for (int i12 = 0; i12 < i; i12++) {
            int i13 = i12 * i;
            byte b3 = bArr2[i13];
            int i14 = 0;
            for (int i15 = 0; i15 < i; i15++) {
                byte b4 = bArr2[i13 + i15];
                if (b4 == b3) {
                    i14++;
                } else {
                    if (i14 > 5) {
                        i8 += i14 - 2;
                    }
                    b3 = b4;
                    i14 = 0;
                }
            }
            if (i14 > 5) {
                i8 += i14 - 2;
            }
        }
        int i16 = 0;
        while (true) {
            int i17 = i - 1;
            if (i16 >= i17) {
                break;
            }
            int i18 = 0;
            while (i18 < i17) {
                int i19 = i18 * i;
                byte b5 = bArr2[i19 + i16];
                i18++;
                int i20 = i18 * i;
                if (b5 == bArr2[i20 + i16]) {
                    int i21 = i16 + 1;
                    if (b5 == bArr2[i19 + i21] && b5 == bArr2[i20 + i21]) {
                        i8 += 3;
                    }
                }
            }
            i16++;
        }
        for (int i22 = 0; i22 < i; i22++) {
            for (int i23 = 0; i23 < i - 7; i23++) {
                int i24 = 0;
                for (int i25 = 0; i25 < 7; i25++) {
                    if (bArr2[((i23 + i25) * i) + i22] == 49) {
                        i24 += 64 >> i25;
                    }
                }
                if (i24 == 93) {
                    int i26 = i23 - 4;
                    int i27 = 0;
                    while (i26 < i23) {
                        i27 = (i26 >= 0 && bArr2[(i26 * i) + i22] != 48) ? 0 : i27 + 1;
                        i26++;
                    }
                    int i28 = i23 + 7;
                    int i29 = 0;
                    while (i28 <= i23 + 10) {
                        i29 = (i28 < i && bArr2[(i28 * i) + i22] != 48) ? 0 : i29 + 1;
                        i28++;
                    }
                    if (i27 == 4 || i29 == 4) {
                        i8 += 40;
                    }
                }
            }
        }
        for (int i30 = 0; i30 < i; i30++) {
            for (int i31 = 0; i31 < i - 7; i31++) {
                int i32 = 0;
                for (int i33 = 0; i33 < 7; i33++) {
                    if (bArr2[(i30 * i) + i31 + i33] == 49) {
                        i32 += 64 >> i33;
                    }
                }
                if (i32 == 93) {
                    int i34 = i31 - 4;
                    int i35 = 0;
                    while (i34 < i31) {
                        i35 = (i34 >= 0 && bArr2[(i30 * i) + i34] != 48) ? 0 : i35 + 1;
                        i34++;
                    }
                    int i36 = i31 + 7;
                    int i37 = 0;
                    while (i36 <= i31 + 10) {
                        i37 = (i36 < i && bArr2[(i30 * i) + i36] != 48) ? 0 : i37 + 1;
                        i36++;
                    }
                    if (i35 == 4 || i37 == 4) {
                        i8 += 40;
                    }
                }
            }
        }
        int i38 = 0;
        for (int i39 = 0; i39 < i; i39++) {
            for (int i40 = 0; i40 < i; i40++) {
                if (bArr2[(i40 * i) + i39] == 49) {
                    i38++;
                }
            }
        }
        int i41 = (i38 / i4) * 100;
        if (i41 <= 50) {
            i3 = (50 - i41) / 5;
        } else {
            i3 = (i41 - 50) / 5;
        }
        return i8 + (i3 * 10);
    }

    private static void addFormatInfo(int[] iArr, int i, EccLevel eccLevel, int i2) {
        int i3 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$QrCode$EccLevel[eccLevel.ordinal()];
        if (i3 == 1) {
            i2 |= 8;
        } else if (i3 == 3) {
            i2 |= 24;
        } else if (i3 == 4) {
            i2 |= 16;
        }
        int i4 = QR_ANNEX_C[i2];
        for (int i5 = 0; i5 < 6; i5++) {
            int i6 = (i5 * i) + 8;
            iArr[i6] = iArr[i6] + ((i4 >> i5) & 1);
        }
        for (int i7 = 0; i7 < 8; i7++) {
            int i8 = (i * 8) + ((i - i7) - 1);
            iArr[i8] = iArr[i8] + ((i4 >> i7) & 1);
        }
        for (int i9 = 0; i9 < 6; i9++) {
            int i10 = (i * 8) + (5 - i9);
            iArr[i10] = iArr[i10] + ((i4 >> (i9 + 9)) & 1);
        }
        for (int i11 = 0; i11 < 7; i11++) {
            int i12 = (((i - 7) + i11) * i) + 8;
            iArr[i12] = iArr[i12] + ((i4 >> (i11 + 8)) & 1);
        }
        int i13 = (i * 7) + 8;
        iArr[i13] = iArr[i13] + ((i4 >> 6) & 1);
        int i14 = i * 8;
        int i15 = i14 + 8;
        iArr[i15] = iArr[i15] + ((i4 >> 7) & 1);
        int i16 = i14 + 7;
        iArr[i16] = iArr[i16] + ((i4 >> 8) & 1);
    }

    private static void addVersionInfo(int[] iArr, int i, int i2) {
        int i3 = QR_ANNEX_D[i2 - 7];
        for (int i4 = 0; i4 < 6; i4++) {
            int i5 = i - 11;
            int i6 = (i5 * i) + i4;
            int i7 = i4 * 3;
            int i8 = (i3 >> i7) & 1;
            iArr[i6] = iArr[i6] + i8;
            int i9 = i - 10;
            int i10 = (i9 * i) + i4;
            int i11 = (i3 >> (i7 + 1)) & 1;
            iArr[i10] = iArr[i10] + i11;
            int i12 = i - 9;
            int i13 = (i12 * i) + i4;
            int i14 = (i3 >> (i7 + 2)) & 1;
            iArr[i13] = iArr[i13] + i14;
            int i15 = i4 * i;
            int i16 = i5 + i15;
            iArr[i16] = iArr[i16] + i8;
            int i17 = i9 + i15;
            iArr[i17] = iArr[i17] + i11;
            int i18 = i15 + i12;
            iArr[i18] = iArr[i18] + i14;
        }
    }
}
