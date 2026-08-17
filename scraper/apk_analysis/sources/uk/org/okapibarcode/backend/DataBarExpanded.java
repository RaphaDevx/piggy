package uk.org.okapibarcode.backend;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.runtime.ComposerKt;
import com.salesforce.marketingcloud.analytics.stats.b;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.ws.WebSocketProtocol;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.util.Strings;

/* loaded from: classes4.dex */
public class DataBarExpanded extends Symbol {
    private boolean linkageFlag;
    private int preferredColumns = 2;
    private boolean stacked = true;
    private static final int[] G_SUM_EXP = {0, 348, 1388, 2948, 3988};
    private static final int[] T_EVEN_EXP = {4, 20, 52, 104, ComposerKt.providerMapsKey};
    private static final int[] MODULES_ODD_EXP = {12, 10, 8, 6, 4};
    private static final int[] MODULES_EVEN_EXP = {5, 7, 9, 11, 13};
    private static final int[] WIDEST_ODD_EXP = {7, 5, 4, 3, 1};
    private static final int[] WIDEST_EVEN_EXP = {2, 4, 5, 6, 8};
    private static final int[] CHECKSUM_WEIGHT_EXP = {1, 3, 9, 27, 81, 32, 96, 77, 20, 60, 180, 118, 143, 7, 21, 63, 189, 145, 13, 39, 117, 140, 209, 205, 193, 157, 49, 147, 19, 57, 171, 91, 62, 186, 136, 197, 169, 85, 44, 132, 185, 133, 188, 142, 4, 12, 36, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 113, 128, 173, 97, 80, 29, 87, 50, 150, 28, 84, 41, 123, 158, 52, 156, 46, 138, ComposerKt.providerValuesKey, 187, 139, ComposerKt.referenceKey, 196, 166, 76, 17, 51, 153, 37, b.o, 122, 155, 43, 129, 176, b.l, b.m, b.n, 119, 146, 16, 48, 144, 10, 30, 90, 59, 177, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 116, 137, 200, 178, b.p, 125, 164, 70, 210, 208, ComposerKt.compositionLocalMapKey, 184, 130, 179, 115, 134, 191, 151, 31, 93, 68, ComposerKt.providerMapsKey, 190, 148, 22, 66, 198, 172, 94, 71, 2, 6, 18, 54, 162, 64, 192, 154, 40, 120, 149, 25, 75, 14, 42, WebSocketProtocol.PAYLOAD_SHORT, 167, 79, 26, 78, 23, 69, ComposerKt.reuseKey, 199, 175, b.i, 98, 83, 38, 114, 131, 182, 124, 161, 61, 183, 127, 170, 88, 53, 159, 55, 165, 73, 8, 24, 72, 5, 15, 45, 135, 194, 160, 58, 174, 100, 89};
    private static final int[] FINDER_PATTERN_EXP = {1, 8, 4, 1, 1, 1, 1, 4, 8, 1, 3, 6, 4, 1, 1, 1, 1, 4, 6, 3, 3, 4, 6, 1, 1, 1, 1, 6, 4, 3, 3, 2, 8, 1, 1, 1, 1, 8, 2, 3, 2, 6, 5, 1, 1, 1, 1, 5, 6, 2, 2, 2, 9, 1, 1, 1, 1, 9, 2, 2};
    private static final int[] FINDER_SEQUENCE = {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 3, 0, 0, 0, 0, 0, 0, 0, 0, 1, 6, 3, 8, 0, 0, 0, 0, 0, 0, 0, 1, 10, 3, 8, 5, 0, 0, 0, 0, 0, 0, 1, 10, 3, 8, 7, 12, 0, 0, 0, 0, 0, 1, 10, 3, 8, 9, 12, 11, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 10, 9, 0, 0, 1, 2, 3, 4, 5, 6, 7, 10, 11, 12, 0, 1, 2, 3, 4, 5, 8, 7, 10, 9, 12, 11};
    private static final int[] WEIGHT_ROWS = {0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 6, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 10, 3, 4, 13, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 18, 3, 4, 13, 14, 7, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 18, 3, 4, 13, 14, 11, 12, 21, 22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 17, 18, 3, 4, 13, 14, 15, 16, 21, 22, 19, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 17, 18, 15, 16, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 17, 18, 19, 20, 21, 22, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 14, 11, 12, 17, 18, 15, 16, 21, 22, 19, 20};

    protected enum EncodeMode {
        NUMERIC,
        ALPHA,
        ISOIEC,
        ANY_ENC,
        ALPHA_OR_ISO
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return true;
    }

    public DataBarExpanded() {
        this.inputDataType = Symbol.DataType.GS1;
    }

    public void setPreferredColumns(int i) {
        if (i < 1 || i > 10) {
            throw new IllegalArgumentException("Invalid column count: " + i);
        }
        this.preferredColumns = i;
    }

    public int getPreferredColumns() {
        return this.preferredColumns;
    }

    public void setStacked(boolean z) {
        this.stacked = z;
    }

    public boolean isStacked() {
        return this.stacked;
    }

    protected void setLinkageFlag(boolean z) {
        this.linkageFlag = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0398 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x03bf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x03cd A[LOOP:17: B:163:0x03cb->B:164:0x03cd, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0438  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0457 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x03a4 A[ADDED_TO_REGION, EDGE_INSN: B:187:0x03a4->B:158:0x03a4 BREAK  A[LOOP:15: B:141:0x0354->B:156:0x039b], SYNTHETIC] */
    @Override // uk.org.okapibarcode.backend.Symbol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void encode() {
        /*
            Method dump skipped, instructions count: 1181
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.DataBarExpanded.encode():void");
    }

    private static String separator(CharSequence charSequence, int i, boolean z, boolean z2, boolean z3, boolean z4, AtomicBoolean atomicBoolean) {
        int i2;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        boolean z5 = true;
        int i3 = 0;
        while (i3 < charSequence.length()) {
            int charAt = charSequence.charAt(i3) - '0';
            for (int i4 = 0; i4 < charAt; i4++) {
                sb.append(z5 ? '1' : '0');
                sb2.append(z5 ? '0' : '1');
            }
            i3++;
            z5 = !z5;
        }
        for (int i5 = 0; i5 < 4; i5++) {
            sb2.setCharAt(i5, '0');
            sb2.setCharAt((sb2.length() - 1) - i5, '0');
        }
        boolean z6 = atomicBoolean.get();
        int i6 = 0;
        boolean z7 = false;
        while (i6 < i) {
            int i7 = (i6 * 49) + 19 + (z2 ? 1 : 0);
            if (z3) {
                int i8 = z6 ? 15 : 13;
                for (i2 = z6 ? 2 : 0; i2 < i8; i2++) {
                    int i9 = i2 + i7;
                    if (i9 < sb.length()) {
                        if (sb.charAt(i9) == '1') {
                            sb2.setCharAt(i9, '0');
                            z7 = false;
                        } else {
                            sb2.setCharAt(i9, z7 ? '0' : '1');
                            z7 = !z7;
                        }
                    }
                }
            } else {
                if (z4) {
                    i7 -= 17;
                }
                i2 = z6 ? 2 : 0;
                for (int i10 = z6 ? 14 : 12; i10 >= i2; i10--) {
                    int i11 = i10 + i7;
                    if (i11 < sb.length()) {
                        if (sb.charAt(i11) == '1') {
                            sb2.setCharAt(i11, '0');
                            z7 = false;
                        } else {
                            sb2.setCharAt(i11, z7 ? '0' : '1');
                            z7 = !z7;
                        }
                    }
                }
            }
            z6 = !z6;
            i6++;
            z7 = z7;
        }
        if (z) {
            atomicBoolean.set(z6);
        }
        return bin2pat(sb2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:191:0x0347, code lost:
    
        if (r5 == 6) goto L215;
     */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0353  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int calculateBinaryString(boolean r26, int r27, int[] r28, java.lang.StringBuilder r29) {
        /*
            Method dump skipped, instructions count: 876
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.DataBarExpanded.calculateBinaryString(boolean, int, int[], java.lang.StringBuilder):int");
    }

    private static int calculateRemainder(int i, boolean z, int i2) {
        int i3 = 12 - (i % 12);
        if (i3 == 12) {
            i3 = 0;
        }
        if (i < 36) {
            i3 = 36 - i;
        }
        return (z && (((i + i3) / 12) + 1) % (i2 * 2) == 1) ? i3 + 12 : i3;
    }

    private void logBinaryStringInfo(StringBuilder sb) {
        infoLine("Binary Length: " + sb.length());
        info("Binary String: ");
        int i = 0;
        for (int i2 = 0; i2 < sb.length(); i2++) {
            int i3 = i2 % 4;
            if (i3 != 0) {
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 == 3) {
                            if (sb.charAt(i2) == '1') {
                                i++;
                            }
                            info(Integer.toHexString(i));
                            i = 0;
                        }
                    } else if (sb.charAt(i2) == '1') {
                        i += 2;
                    }
                } else if (sb.charAt(i2) == '1') {
                    i += 4;
                }
            } else if (sb.charAt(i2) == '1') {
                i += 8;
            }
        }
        if (sb.length() % 4 != 0) {
            info(Integer.toHexString(i));
        }
        infoLine();
    }

    protected static EncodeMode[] getInitialEncodeModes(int[] iArr) {
        EncodeMode encodeMode;
        EncodeMode[] encodeModeArr = new EncodeMode[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            if (i2 == -1) {
                encodeMode = EncodeMode.ANY_ENC;
            } else if (i2 >= 48 && i2 <= 57) {
                encodeMode = EncodeMode.ANY_ENC;
            } else if ((i2 >= 65 && i2 <= 90) || i2 == 42 || i2 == 44 || i2 == 45 || i2 == 46 || i2 == 47) {
                encodeMode = EncodeMode.ALPHA_OR_ISO;
            } else if ((i2 >= 97 && i2 <= 122) || i2 == 33 || i2 == 34 || i2 == 37 || i2 == 38 || i2 == 39 || i2 == 40 || i2 == 41 || i2 == 43 || i2 == 58 || i2 == 59 || i2 == 60 || i2 == 61 || i2 == 62 || i2 == 63 || i2 == 95 || i2 == 32) {
                encodeMode = EncodeMode.ISOIEC;
            } else {
                throw new OkapiException("Invalid characters in input data");
            }
            encodeModeArr[i] = encodeMode;
        }
        for (int i3 = 0; i3 < iArr.length - 1; i3++) {
            if (encodeModeArr[i3] == EncodeMode.ISOIEC) {
                int i4 = i3 + 1;
                if (iArr[i4] == -1) {
                    encodeModeArr[i4] = EncodeMode.ISOIEC;
                }
            }
        }
        for (int i5 = 0; i5 < iArr.length - 1; i5++) {
            if (encodeModeArr[i5] == EncodeMode.ALPHA_OR_ISO) {
                int i6 = i5 + 1;
                if (iArr[i6] == -1) {
                    encodeModeArr[i6] = EncodeMode.ALPHA_OR_ISO;
                }
            }
        }
        return encodeModeArr;
    }

    protected static boolean applyGeneralFieldRules(EncodeMode[] encodeModeArr) {
        int i;
        int i2;
        int[] iArr = new int[200];
        EncodeMode[] encodeModeArr2 = new EncodeMode[200];
        iArr[0] = 1;
        encodeModeArr2[0] = encodeModeArr[0];
        int i3 = 0;
        for (int i4 = 1; i4 < encodeModeArr.length; i4++) {
            EncodeMode encodeMode = encodeModeArr[i4];
            if (encodeMode == encodeModeArr[i4 - 1]) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                i3++;
                iArr[i3] = 1;
                encodeModeArr2[i3] = encodeMode;
            }
        }
        int i5 = i3 + 1;
        int i6 = 0;
        while (i6 < i5) {
            EncodeMode encodeMode2 = encodeModeArr2[i6];
            int i7 = i6 + 1;
            EncodeMode encodeMode3 = encodeModeArr2[i7];
            if (encodeMode2 == EncodeMode.ISOIEC && i6 != i3) {
                if (encodeMode3 == EncodeMode.ANY_ENC && iArr[i7] >= 4) {
                    encodeModeArr2[i7] = EncodeMode.NUMERIC;
                }
                if (encodeMode3 == EncodeMode.ANY_ENC && iArr[i7] < 4) {
                    encodeModeArr2[i7] = EncodeMode.ISOIEC;
                }
                if (encodeMode3 == EncodeMode.ALPHA_OR_ISO && iArr[i7] >= 5) {
                    encodeModeArr2[i7] = EncodeMode.ALPHA;
                }
                if (encodeMode3 == EncodeMode.ALPHA_OR_ISO && iArr[i7] < 5) {
                    encodeModeArr2[i7] = EncodeMode.ISOIEC;
                }
            }
            if (encodeMode2 == EncodeMode.ALPHA_OR_ISO) {
                encodeModeArr2[i6] = EncodeMode.ALPHA;
                encodeMode2 = EncodeMode.ALPHA;
            }
            if (encodeMode2 == EncodeMode.ALPHA && i6 != i3) {
                if (encodeMode3 == EncodeMode.ANY_ENC && iArr[i7] >= 6) {
                    encodeModeArr2[i7] = EncodeMode.NUMERIC;
                }
                if (encodeMode3 == EncodeMode.ANY_ENC && (i2 = iArr[i7]) < 6) {
                    if (i6 == i3 - 1 && i2 >= 4) {
                        encodeModeArr2[i7] = EncodeMode.NUMERIC;
                    } else {
                        encodeModeArr2[i7] = EncodeMode.ALPHA;
                    }
                }
            }
            if (encodeMode2 == EncodeMode.ANY_ENC) {
                encodeModeArr2[i6] = EncodeMode.NUMERIC;
            }
            i6 = i7;
        }
        if (i5 > 1) {
            int i8 = 1;
            while (i8 < i5) {
                int i9 = i8 - 1;
                if (encodeModeArr2[i9] == encodeModeArr2[i8]) {
                    iArr[i9] = iArr[i9] + iArr[i8];
                    for (int i10 = i8 + 1; i10 < i5; i10++) {
                        int i11 = i10 - 1;
                        iArr[i11] = iArr[i10];
                        encodeModeArr2[i11] = encodeModeArr2[i10];
                    }
                    i5--;
                    i8--;
                }
                i8++;
            }
        }
        int i12 = 0;
        while (true) {
            i = i5 - 1;
            if (i12 >= i) {
                break;
            }
            if (encodeModeArr2[i12] == EncodeMode.NUMERIC) {
                int i13 = iArr[i12];
                if ((i13 & 1) != 0) {
                    iArr[i12] = i13 - 1;
                    int i14 = i12 + 1;
                    iArr[i14] = iArr[i14] + 1;
                }
            }
            i12++;
        }
        int i15 = 0;
        for (int i16 = 0; i16 < i5; i16++) {
            for (int i17 = 0; i17 < iArr[i16]; i17++) {
                encodeModeArr[i15] = encodeModeArr2[i16];
                i15++;
            }
        }
        return encodeModeArr2[i] == EncodeMode.NUMERIC && (iArr[i] & 1) != 0;
    }

    protected static EncodeMode appendToBinaryString(int[] iArr, EncodeMode[] encodeModeArr, boolean z, boolean z2, StringBuilder sb) {
        EncodeMode encodeMode = EncodeMode.NUMERIC;
        if (encodeModeArr[0] == EncodeMode.ALPHA) {
            sb.append("0000");
            encodeMode = EncodeMode.ALPHA;
        }
        if (encodeModeArr[0] == EncodeMode.ISOIEC) {
            sb.append("0000");
            sb.append("00100");
            encodeMode = EncodeMode.ISOIEC;
        }
        int i = 1;
        int i2 = 0;
        int i3 = z ? 1 : 0;
        while (i3 < iArr.length) {
            int i4 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$DataBarExpanded$EncodeMode[encodeModeArr[i2].ordinal()];
            if (i4 != i) {
                if (i4 == 2) {
                    if (i2 != 0) {
                        if (encodeMode == EncodeMode.NUMERIC) {
                            sb.append("0000");
                        }
                        if (encodeMode == EncodeMode.ISOIEC) {
                            sb.append("00100");
                        }
                    }
                    int i5 = iArr[i2];
                    if (i5 >= 48 && i5 <= 57) {
                        Strings.binaryAppend(sb, i5 - 43, 5);
                    }
                    int i6 = iArr[i2];
                    if (i6 >= 65 && i6 <= 90) {
                        Strings.binaryAppend(sb, i6 - 33, 6);
                    }
                    encodeMode = EncodeMode.ALPHA;
                    if (iArr[i2] == -1) {
                        sb.append("01111");
                        if (z2) {
                            encodeMode = EncodeMode.NUMERIC;
                        }
                    }
                    if (iArr[i2] == 42) {
                        sb.append("111010");
                    }
                    if (iArr[i2] == 44) {
                        sb.append("111011");
                    }
                    if (iArr[i2] == 45) {
                        sb.append("111100");
                    }
                    if (iArr[i2] == 46) {
                        sb.append("111101");
                    }
                    if (iArr[i2] == 47) {
                        sb.append("111110");
                    }
                } else if (i4 == 3) {
                    if (i2 != 0) {
                        if (encodeMode == EncodeMode.NUMERIC) {
                            sb.append("0000");
                            sb.append("00100");
                        }
                        if (encodeMode == EncodeMode.ALPHA) {
                            sb.append("00100");
                        }
                    }
                    int i7 = iArr[i2];
                    if (i7 >= 48 && i7 <= 57) {
                        Strings.binaryAppend(sb, i7 - 43, 5);
                    }
                    int i8 = iArr[i2];
                    if (i8 >= 65 && i8 <= 90) {
                        Strings.binaryAppend(sb, i8 - 1, 7);
                    }
                    int i9 = iArr[i2];
                    if (i9 >= 97 && i9 <= 122) {
                        Strings.binaryAppend(sb, i9 - 7, 7);
                    }
                    encodeMode = EncodeMode.ISOIEC;
                    if (iArr[i2] == -1) {
                        sb.append("01111");
                        if (z2) {
                            encodeMode = EncodeMode.NUMERIC;
                        }
                    }
                    if (iArr[i2] == 33) {
                        sb.append("11101000");
                    }
                    if (iArr[i2] == 34) {
                        sb.append("11101001");
                    }
                    if (iArr[i2] == 37) {
                        sb.append("11101010");
                    }
                    if (iArr[i2] == 38) {
                        sb.append("11101011");
                    }
                    if (iArr[i2] == 39) {
                        sb.append("11101100");
                    }
                    if (iArr[i2] == 40) {
                        sb.append("11101101");
                    }
                    if (iArr[i2] == 41) {
                        sb.append("11101110");
                    }
                    if (iArr[i2] == 42) {
                        sb.append("11101111");
                    }
                    if (iArr[i2] == 43) {
                        sb.append("11110000");
                    }
                    if (iArr[i2] == 44) {
                        sb.append("11110001");
                    }
                    if (iArr[i2] == 45) {
                        sb.append("11110010");
                    }
                    if (iArr[i2] == 46) {
                        sb.append("11110011");
                    }
                    if (iArr[i2] == 47) {
                        sb.append("11110100");
                    }
                    if (iArr[i2] == 58) {
                        sb.append("11110101");
                    }
                    if (iArr[i2] == 59) {
                        sb.append("11110110");
                    }
                    if (iArr[i2] == 60) {
                        sb.append("11110111");
                    }
                    if (iArr[i2] == 61) {
                        sb.append("11111000");
                    }
                    if (iArr[i2] == 62) {
                        sb.append("11111001");
                    }
                    if (iArr[i2] == 63) {
                        sb.append("11111010");
                    }
                    if (iArr[i2] == 95) {
                        sb.append("11111011");
                    }
                    if (iArr[i2] == 32) {
                        sb.append("11111100");
                    }
                }
                i2++;
            } else {
                if (encodeMode != EncodeMode.NUMERIC) {
                    sb.append("000");
                }
                int i10 = iArr[i2];
                int i11 = i10 != -1 ? i10 - 48 : 10;
                int i12 = iArr[i2 + 1];
                Strings.binaryAppend(sb, (i11 * 11) + (i12 != -1 ? i12 - 48 : 10) + 8, 7);
                i2 += 2;
                encodeMode = EncodeMode.NUMERIC;
            }
            i3 = z ? i2 + 1 : i2;
            i = 1;
        }
        return encodeMode;
    }

    /* renamed from: uk.org.okapibarcode.backend.DataBarExpanded$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$DataBarExpanded$EncodeMode;

        static {
            int[] iArr = new int[EncodeMode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$DataBarExpanded$EncodeMode = iArr;
            try {
                iArr[EncodeMode.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$DataBarExpanded$EncodeMode[EncodeMode.ALPHA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$DataBarExpanded$EncodeMode[EncodeMode.ISOIEC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static int parseInt(int[] iArr, int i, int i2) {
        int pow = (int) Math.pow(10.0d, i2 - 1);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += (iArr[i + i4] - 48) * pow;
            pow /= 10;
        }
        return i3;
    }
}
