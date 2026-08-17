package uk.org.okapibarcode.backend;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.PointerIconCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.wallet.WalletConstants;
import com.salesforce.marketingcloud.analytics.stats.b;
import com.tealium.remotecommands.RemoteCommand;
import io.sentry.HttpStatusCodeRange;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import javax.ws.rs.Priorities;
import okhttp3.internal.ws.WebSocketProtocol;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class AztecCode extends Symbol {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private String structuredAppendMessageId;
    private static final int[] COMPACT_AZTEC_MAP = {TypedValues.MotionType.TYPE_POLAR_RELATIVETO, TypedValues.MotionType.TYPE_DRAW_PATH, WalletConstants.ERROR_CODE_AUTHENTICATION_FAILURE, WalletConstants.ERROR_CODE_UNKNOWN, WalletConstants.ERROR_CODE_APP_LABEL_UNAVAILABLE, 417, 419, 421, TypedValues.CycleType.TYPE_WAVE_PERIOD, TypedValues.CycleType.TYPE_WAVE_PHASE, 427, 429, 431, 433, 435, 437, 439, 441, 443, 445, 447, 449, 451, 453, 455, 457, 459, TypedValues.MotionType.TYPE_PATHMOTION_ARC, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, WalletConstants.ERROR_CODE_UNSUPPORTED_API_VERSION, WalletConstants.ERROR_CODE_ILLEGAL_CALLER, 416, 418, TypedValues.CycleType.TYPE_EASING, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, TypedValues.CycleType.TYPE_WAVE_OFFSET, 426, 428, 430, 432, 434, 436, 438, 440, 442, 444, 446, 448, 450, 452, 454, 456, 458, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR, WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR, 408, 243, 245, 247, 249, 251, 253, 255, 257, 259, 261, 263, 265, 267, 269, 271, 273, 275, 277, 279, 281, 283, 460, 461, TypedValues.MotionType.TYPE_EASING, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, 407, WalletConstants.ERROR_CODE_SPENDING_LIMIT_EXCEEDED, 242, 244, 246, 248, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 252, 254, 256, 258, 260, 262, 264, 266, 268, 270, 272, 274, 276, 278, 280, 282, 462, 463, 601, 600, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, 404, 241, 240, b.m, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, b.o, 113, 115, 117, 119, 121, 123, 125, 127, 129, 131, 133, 135, 137, 139, 284, 285, 464, 465, HttpStatusCodeRange.DEFAULT_MAX, 598, TypedValues.CycleType.TYPE_ALPHA, 402, 239, 238, b.l, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, b.n, b.p, 114, 116, 118, 120, 122, 124, WebSocketProtocol.PAYLOAD_SHORT, 128, 130, 132, 134, 136, 138, 286, 287, 466, 467, 597, 596, TypedValues.CycleType.TYPE_CURVE_FIT, RemoteCommand.Response.STATUS_BAD_REQUEST, 237, 236, 105, 104, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 140, 141, 288, 289, 468, 469, 595, 594, 399, 398, 235, 234, b.i, 102, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 142, 143, 290, 291, 470, 471, 593, 592, 397, 396, 233, 232, 101, 100, 1, 1, Priorities.AUTHORIZATION, 2001, 2002, 2003, 2004, 2005, 2006, 0, 1, 28, 29, 144, 145, 292, 293, 472, 473, 591, 590, 395, 394, 231, 230, 99, 98, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 30, 31, 146, 147, 294, 295, 474, 475, 589, 588, 393, 392, 229, 228, 97, 96, 2027, 1, 0, 0, 0, 0, 0, 0, 0, 1, 2007, 32, 33, 148, 149, 296, 297, 476, 477, 587, 586, 391, 390, 227, 226, 95, 94, 2026, 1, 0, 1, 1, 1, 1, 1, 0, 1, 2008, 34, 35, 150, 151, 298, 299, 478, 479, 585, 584, 389, 388, 225, 224, 93, 92, 2025, 1, 0, 1, 0, 0, 0, 1, 0, 1, 2009, 36, 37, 152, 153, 300, 301, 480, 481, 583, 582, 387, 386, 223, 222, 91, 90, 2024, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2010, 38, 39, 154, 155, 302, 303, 482, 483, 581, 580, 385, 384, 221, 220, 89, 88, 2023, 1, 0, 1, 0, 0, 0, 1, 0, 1, 2011, 40, 41, 156, 157, 304, 305, 484, 485, 579, 578, 383, 382, 219, 218, 87, 86, 2022, 1, 0, 1, 1, 1, 1, 1, 0, 1, 2012, 42, 43, 158, 159, 306, 307, 486, 487, 577, 576, 381, 380, 217, 216, 85, 84, 2021, 1, 0, 0, 0, 0, 0, 0, 0, 1, 2013, 44, 45, 160, 161, 308, 309, 488, 489, 575, 574, 379, 378, 215, 214, 83, 82, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 46, 47, 162, 163, 310, 311, 490, 491, 573, 572, 377, 376, 213, 212, 81, 80, 0, 0, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 0, 0, 48, 49, 164, 165, 312, 313, 492, 493, 571, 570, 375, 374, 211, 210, 78, 76, 74, 72, 70, 68, 66, 64, 62, 60, 58, 56, 54, 50, 51, 166, 167, 314, 315, 494, 495, 569, 568, 373, 372, 209, 208, 79, 77, 75, 73, 71, 69, 67, 65, 63, 61, 59, 57, 55, 52, 53, 168, 169, TypedValues.AttributesType.TYPE_PATH_ROTATE, TypedValues.AttributesType.TYPE_EASING, 496, 497, 567, 566, 371, 370, ComposerKt.referenceKey, ComposerKt.providerMapsKey, ComposerKt.compositionLocalMapKey, 200, 198, 196, 194, 192, 190, 188, 186, 184, 182, 180, 178, 176, 174, 170, 171, TypedValues.AttributesType.TYPE_PIVOT_TARGET, 319, 498, 499, 565, 564, 369, 368, ComposerKt.reuseKey, 205, ComposerKt.providerValuesKey, ComposerKt.providerKey, 199, 197, 195, 193, 191, 189, 187, 185, 183, 181, 179, 177, 175, 172, 173, 320, 321, 500, TypedValues.PositionType.TYPE_TRANSITION_EASING, 563, 562, 366, 364, 362, 360, 358, 356, 354, 352, 350, 348, 346, 344, 342, 340, 338, 336, 334, 332, 330, 328, 326, 322, 323, TypedValues.PositionType.TYPE_DRAWPATH, TypedValues.PositionType.TYPE_PERCENT_WIDTH, 561, 560, 367, 365, 363, 361, 359, 357, 355, 353, 351, 349, 347, 345, 343, 341, 339, 337, 335, 333, 331, 329, 327, 324, 325, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, TypedValues.PositionType.TYPE_SIZE_PERCENT, 558, 556, 554, 552, 550, 548, 546, 544, 542, 540, 538, 536, 534, 532, 530, 528, 526, 524, 522, 520, 518, 516, 514, 512, TypedValues.PositionType.TYPE_POSITION_TYPE, TypedValues.PositionType.TYPE_PERCENT_X, TypedValues.PositionType.TYPE_PERCENT_Y, 559, 557, 555, 553, 551, 549, 547, 545, 543, 541, 539, 537, 535, 533, 531, 529, 527, 525, 523, 521, 519, 517, 515, InputDeviceCompat.SOURCE_DPAD, FrameMetricsAggregator.EVERY_DURATION, TypedValues.PositionType.TYPE_CURVE_FIT, 509};
    private static final int[][] AZTEC_MAP = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 151, 151);
    private static final int[] AZTEC_CODE_SET = {32, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 12, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 4, 4, 4, 4, 4, 23, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 24, 8, 24, 8, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 8, 8, 8, 8, 8, 8, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 4, 8, 4, 4, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 4, 8, 4, 4};
    private static final int[] AZTEC_SYMBOL_CHAR = {0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 300, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 15, 16, 17, 18, 19, 1, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 301, 18, 302, 20, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 21, 22, 23, 24, 25, 26, 20, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 27, 21, 28, 22, 23, 24, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 29, 25, 30, 26, 27};
    private static final String[] PENTBIT = {"00000", "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010", "01011", "01100", "01101", "01110", "01111", "10000", "10001", "10010", "10011", "10100", "10101", "10110", "10111", "11000", "11001", "11010", "11011", "11100", "11101", "11110", "11111"};
    private static final String[] QUADBIT = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
    private static final String[] TRIBIT = {"000", "001", "010", "011", "100", "101", "110", "111"};
    private static final int[] AZTEC_SIZES = {21, 48, 60, 88, 120, 156, 196, 240, 230, 272, TypedValues.AttributesType.TYPE_PATH_ROTATE, 364, 416, 470, 528, 588, 652, 720, 790, 864, 940, PointerIconCompat.TYPE_GRAB, 920, 992, 1066, 1144, 1224, 1306, 1392, 1480, 1570, 1664};
    private static final int[] AZTEC_COMPACT_SIZES = {17, 40, 51, 76};
    private static final int[] AZTEC_10_DATA_SIZES = {96, 246, 408, 616, 840, 1104, 1392, 1704, 2040, 2420, 2820, 3250, 3720, 4200, 4730, 5270, 5840, 6450, 7080, 7750, 8430, 9150, 9900, 10680, 11484, 12324, 13188, 14076, 15000, 15948, 16920, 17940};
    private static final int[] AZTEC_23_DATA_SIZES = {84, ComposerKt.providerMapsKey, 352, 520, 720, 944, 1184, 1456, 1750, 2070, 2410, 2780, 3180, 3590, 4040, 4500, 5000, 5520, 6060, 6630, 7210, 7830, 8472, 9132, 9816, 10536, 11280, 12036, 12828, 13644, 14472, 15348};
    private static final int[] AZTEC_36_DATA_SIZES = {66, 168, 288, 432, 592, 776, 984, 1208, 1450, 1720, Priorities.AUTHORIZATION, 2300, 2640, 2980, 3350, 3740, 4150, 4580, 5030, 5500, 5990, 6500, 7032, 7584, 8160, 8760, 9372, 9996, 10656, 11340, 12024, 12744};
    private static final int[] AZTEC_50_DATA_SIZES = {48, WebSocketProtocol.PAYLOAD_SHORT, 216, 328, 456, 600, 760, 936, 1120, 1330, 1550, 1790, 2050, 2320, 2610, 2910, 3230, 3570, 3920, 4290, 4670, 5070, 5484, 5916, 6360, 6828, 7308, 7800, 8316, 8844, 9384, 9948};
    private static final int[] AZTEC_COMPACT_10_DATA_SIZES = {78, 198, 336, 520};
    private static final int[] AZTEC_COMPACT_23_DATA_SIZES = {66, 168, 288, 440};
    private static final int[] AZTEC_COMPACT_36_DATA_SIZES = {48, 138, 232, 360};
    private static final int[] AZTEC_COMPACT_50_DATA_SIZES = {36, 102, 176, 280};
    private static final int[] AZTEC_OFFSET = {66, 64, 62, 60, 57, 55, 53, 51, 49, 47, 45, 42, 40, 38, 36, 34, 32, 30, 28, 25, 23, 21, 19, 17, 15, 13, 10, 8, 6, 4, 2, 0};
    private static final int[] AZTEC_COMPACT_OFFSET = {6, 4, 2, 0};
    private int preferredSize = 0;
    private int preferredEccLevel = 2;
    private int structuredAppendPosition = 1;
    private int structuredAppendTotal = 1;

    private static int avoidReferenceGrid(int i) {
        if (i > 10) {
            i++;
        }
        if (i > 26) {
            i++;
        }
        if (i > 42) {
            i++;
        }
        if (i > 58) {
            i++;
        }
        if (i > 74) {
            i++;
        }
        if (i > 90) {
            i++;
        }
        if (i > 106) {
            i++;
        }
        if (i > 122) {
            i++;
        }
        return i > 138 ? i + 1 : i;
    }

    private static int getCodewordSize(int i) {
        if (i >= 23) {
            return 12;
        }
        if (i < 9 || i > 22) {
            return (i < 3 || i > 8) ? 6 : 8;
        }
        return 10;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return true;
    }

    static {
        int i;
        int i2;
        int i3;
        for (int i4 = 0; i4 < 151; i4++) {
            for (int i5 = 0; i5 < 151; i5++) {
                AZTEC_MAP[i4][i5] = 0;
            }
        }
        for (int i6 = 1; i6 < 33; i6++) {
            int i7 = i6 - 1;
            int i8 = (i7 * b.p) + (i7 * 16 * i7) + 2;
            int i9 = (i7 * 4) + 28 + (i6 * 4);
            int i10 = i7 * 2;
            int i11 = 64 - i10;
            int i12 = 63 - i10;
            int i13 = 0;
            int i14 = i8;
            while (true) {
                i = i8 + i9;
                if (i14 >= i) {
                    break;
                }
                int[][] iArr = AZTEC_MAP;
                int i15 = i11 + i13;
                iArr[avoidReferenceGrid(i15)][avoidReferenceGrid(i12)] = i14;
                iArr[avoidReferenceGrid(i15)][avoidReferenceGrid(62 - i10)] = i14 + 1;
                i13++;
                i14 += 2;
            }
            int i16 = i10 + 78;
            int i17 = 0;
            while (true) {
                i2 = (i9 * 2) + i8;
                if (i >= i2) {
                    break;
                }
                int[][] iArr2 = AZTEC_MAP;
                int i18 = i11 + i17;
                iArr2[avoidReferenceGrid(i16)][avoidReferenceGrid(i18)] = i;
                iArr2[avoidReferenceGrid(i10 + 79)][avoidReferenceGrid(i18)] = i + 1;
                i17++;
                i += 2;
            }
            int i19 = i10 + 77;
            int i20 = 0;
            while (true) {
                i3 = (i9 * 3) + i8;
                if (i2 >= i3) {
                    break;
                }
                int[][] iArr3 = AZTEC_MAP;
                int i21 = i19 - i20;
                iArr3[avoidReferenceGrid(i21)][avoidReferenceGrid(i16)] = i2;
                iArr3[avoidReferenceGrid(i21)][avoidReferenceGrid(i10 + 79)] = i2 + 1;
                i20++;
                i2 += 2;
            }
            int i22 = 0;
            while (i3 < (i9 * 4) + i8) {
                int[][] iArr4 = AZTEC_MAP;
                int i23 = i19 - i22;
                iArr4[avoidReferenceGrid(i12)][avoidReferenceGrid(i23)] = i3;
                iArr4[avoidReferenceGrid(62 - i10)][avoidReferenceGrid(i23)] = i3 + 1;
                i22++;
                i3 += 2;
            }
        }
        for (int i24 = 69; i24 <= 81; i24++) {
            for (int i25 = 69; i25 <= 81; i25++) {
                AZTEC_MAP[i25][i24] = 1;
            }
        }
        for (int i26 = 70; i26 <= 80; i26++) {
            for (int i27 = 70; i27 <= 80; i27++) {
                AZTEC_MAP[i27][i26] = 0;
            }
        }
        for (int i28 = 71; i28 <= 79; i28++) {
            for (int i29 = 71; i29 <= 79; i29++) {
                AZTEC_MAP[i29][i28] = 1;
            }
        }
        for (int i30 = 72; i30 <= 78; i30++) {
            for (int i31 = 72; i31 <= 78; i31++) {
                AZTEC_MAP[i31][i30] = 0;
            }
        }
        for (int i32 = 73; i32 <= 77; i32++) {
            for (int i33 = 73; i33 <= 77; i33++) {
                AZTEC_MAP[i33][i32] = 1;
            }
        }
        for (int i34 = 74; i34 <= 76; i34++) {
            for (int i35 = 74; i35 <= 76; i35++) {
                AZTEC_MAP[i35][i34] = 0;
            }
        }
        for (int i36 = 11; i36 < 151; i36 += 16) {
            for (int i37 = 1; i37 < 151; i37 += 2) {
                int[][] iArr5 = AZTEC_MAP;
                iArr5[i37][i36] = 1;
                iArr5[i36][i37] = 1;
            }
        }
        for (int i38 = 0; i38 < 10; i38++) {
            AZTEC_MAP[avoidReferenceGrid(i38 + 66)][avoidReferenceGrid(64)] = i38 + 20000;
        }
        for (int i39 = 0; i39 < 10; i39++) {
            AZTEC_MAP[avoidReferenceGrid(77)][avoidReferenceGrid(i39 + 66)] = i39 + 20010;
        }
        for (int i40 = 0; i40 < 10; i40++) {
            AZTEC_MAP[avoidReferenceGrid(75 - i40)][avoidReferenceGrid(77)] = i40 + 20020;
        }
        for (int i41 = 0; i41 < 10; i41++) {
            AZTEC_MAP[avoidReferenceGrid(64)][avoidReferenceGrid(75 - i41)] = i41 + 20030;
        }
        int[][] iArr6 = AZTEC_MAP;
        iArr6[avoidReferenceGrid(64)][avoidReferenceGrid(64)] = 1;
        iArr6[avoidReferenceGrid(65)][avoidReferenceGrid(64)] = 1;
        iArr6[avoidReferenceGrid(64)][avoidReferenceGrid(65)] = 1;
        iArr6[avoidReferenceGrid(77)][avoidReferenceGrid(64)] = 1;
        iArr6[avoidReferenceGrid(77)][avoidReferenceGrid(65)] = 1;
        iArr6[avoidReferenceGrid(77)][avoidReferenceGrid(76)] = 1;
    }

    public void setPreferredSize(int i) {
        if (i < 1 || i > 36) {
            throw new IllegalArgumentException("Invalid size: " + i);
        }
        this.preferredSize = i;
    }

    public int getPreferredSize() {
        return this.preferredSize;
    }

    public void setPreferredEccLevel(int i) {
        if (i < 1 || i > 4) {
            throw new IllegalArgumentException("Invalid ECC level: " + i);
        }
        this.preferredEccLevel = i;
    }

    public int getPreferredEccLevel() {
        return this.preferredEccLevel;
    }

    public void setStructuredAppendPosition(int i) {
        if (i < 1 || i > 26) {
            throw new IllegalArgumentException("Invalid Aztec Code structured append position: " + i);
        }
        this.structuredAppendPosition = i;
    }

    public int getStructuredAppendPosition() {
        return this.structuredAppendPosition;
    }

    public void setStructuredAppendTotal(int i) {
        if (i < 1 || i > 26) {
            throw new IllegalArgumentException("Invalid Aztec Code structured append total: " + i);
        }
        this.structuredAppendTotal = i;
    }

    public int getStructuredAppendTotal() {
        return this.structuredAppendTotal;
    }

    public void setStructuredAppendMessageId(String str) {
        if (str != null && !str.matches("^[\\x21-\\x7F]+$")) {
            throw new IllegalArgumentException("Invalid Aztec Code structured append message ID: " + str);
        }
        this.structuredAppendMessageId = str;
    }

    public String getStructuredAppendMessageId() {
        return this.structuredAppendMessageId;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        boolean z;
        StringBuilder adjustBinaryString;
        int i;
        int i2;
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        eciProcess();
        if (this.structuredAppendTotal != 1) {
            StringBuilder sb = new StringBuilder();
            if (this.structuredAppendMessageId != null) {
                sb.append(' ');
                sb.append(this.structuredAppendMessageId);
                sb.append(' ');
            }
            sb.append((char) (this.structuredAppendPosition + 64));
            sb.append((char) (this.structuredAppendTotal + 64));
            this.inputData = Arrays.insertArray(this.inputData, 0, toBytes(sb.toString(), StandardCharsets.US_ASCII, new int[0]));
        }
        String generateAztecBinary = generateAztecBinary();
        int length = generateAztecBinary.length();
        int i3 = this.preferredSize;
        if (i3 == 0) {
            int i4 = this.readerInit ? 1 : 4;
            int i5 = 0;
            while (true) {
                int i6 = this.preferredEccLevel;
                if (i6 == 1) {
                    iArr3 = AZTEC_10_DATA_SIZES;
                    iArr4 = AZTEC_COMPACT_10_DATA_SIZES;
                } else if (i6 == 2) {
                    iArr3 = AZTEC_23_DATA_SIZES;
                    iArr4 = AZTEC_COMPACT_23_DATA_SIZES;
                } else if (i6 == 3) {
                    iArr3 = AZTEC_36_DATA_SIZES;
                    iArr4 = AZTEC_COMPACT_36_DATA_SIZES;
                } else if (i6 == 4) {
                    iArr3 = AZTEC_50_DATA_SIZES;
                    iArr4 = AZTEC_COMPACT_50_DATA_SIZES;
                } else {
                    throw new OkapiException("Unrecognized ECC level: " + this.preferredEccLevel);
                }
                i = 0;
                for (int i7 = 32; i7 > 0; i7--) {
                    int i8 = iArr3[i7 - 1];
                    if (length < i8) {
                        i = i7;
                        i5 = i8;
                    }
                }
                z = false;
                for (int i9 = i4; i9 > 0; i9--) {
                    int i10 = iArr4[i9 - 1];
                    if (length < i10) {
                        z = true;
                        i = i9;
                        i5 = i10;
                    }
                }
                if (i == 0) {
                    throw new OkapiException("Input too long (too many bits for selected ECC)");
                }
                adjustBinaryString = adjustBinaryString(generateAztecBinary, z, i);
                int length2 = adjustBinaryString.length();
                if (length2 <= i5) {
                    break;
                } else {
                    length = length2;
                }
            }
        } else {
            if (i3 < 1 || i3 > 4) {
                i3 -= 4;
                z = false;
            } else {
                z = true;
            }
            adjustBinaryString = adjustBinaryString(generateAztecBinary, z, i3);
            if (adjustBinaryString.length() > getCodewordSize(i3) * ((z ? AZTEC_COMPACT_SIZES : AZTEC_SIZES)[i3 - 1] - 3)) {
                throw new OkapiException("Data too long for specified Aztec Code symbol size");
            }
            i = i3;
        }
        if (this.readerInit && z && i > 1) {
            throw new OkapiException("Symbol is too large for reader initialization");
        }
        if (this.readerInit && i > 22) {
            throw new OkapiException("Symbol is too large for reader initialization");
        }
        int codewordSize = getCodewordSize(i);
        int length3 = adjustBinaryString.length() / codewordSize;
        if (z) {
            i2 = AZTEC_COMPACT_SIZES[i - 1];
        } else {
            i2 = AZTEC_SIZES[i - 1];
        }
        int i11 = i2 - length3;
        infoLine("Compact Mode: " + z);
        infoLine("Layers: " + i);
        infoLine("Codeword Length: " + codewordSize + " bits");
        StringBuilder sb2 = new StringBuilder("Data Codewords: ");
        sb2.append(length3);
        infoLine(sb2.toString());
        infoLine("ECC Codewords: " + i11);
        addErrorCorrection(adjustBinaryString, codewordSize, length3, i11);
        for (int i12 = 0; i12 < adjustBinaryString.length() / 2; i12++) {
            int length4 = (adjustBinaryString.length() - i12) - 1;
            char charAt = adjustBinaryString.charAt(i12);
            adjustBinaryString.setCharAt(i12, adjustBinaryString.charAt(length4));
            adjustBinaryString.setCharAt(length4, charAt);
        }
        String createDescriptor = createDescriptor(z, i, length3);
        if (z) {
            this.readable = "";
            int[] iArr5 = AZTEC_COMPACT_OFFSET;
            int i13 = i - 1;
            this.row_count = 27 - (iArr5[i13] * 2);
            this.row_height = new int[this.row_count];
            this.row_height[0] = -1;
            this.pattern = new String[this.row_count];
            int i14 = iArr5[i13];
            while (true) {
                int[] iArr6 = AZTEC_COMPACT_OFFSET;
                if (i14 >= 27 - iArr6[i13]) {
                    return;
                }
                StringBuilder sb3 = new StringBuilder(27);
                int i15 = iArr6[i13];
                while (true) {
                    iArr2 = AZTEC_COMPACT_OFFSET;
                    if (i15 < 27 - iArr2[i13]) {
                        int i16 = COMPACT_AZTEC_MAP[(i14 * 27) + i15];
                        if (i16 == 0) {
                            sb3.append('0');
                        }
                        if (i16 == 1) {
                            sb3.append('1');
                        }
                        if (i16 >= 2) {
                            int i17 = i16 - 2;
                            if (i17 < adjustBinaryString.length()) {
                                sb3.append(adjustBinaryString.charAt(i17));
                            } else if (i16 >= 2000) {
                                sb3.append(createDescriptor.charAt(i16 - 2000));
                            } else {
                                sb3.append('0');
                            }
                        }
                        i15++;
                    }
                }
                this.row_height[i14 - iArr2[i13]] = 1;
                this.pattern[i14 - iArr2[i13]] = bin2pat(sb3);
                i14++;
            }
        } else {
            this.readable = "";
            int[] iArr7 = AZTEC_OFFSET;
            int i18 = i - 1;
            this.row_count = 151 - (iArr7[i18] * 2);
            this.row_height = new int[this.row_count];
            this.row_height[0] = -1;
            this.pattern = new String[this.row_count];
            int i19 = iArr7[i18];
            while (true) {
                int[] iArr8 = AZTEC_OFFSET;
                if (i19 >= 151 - iArr8[i18]) {
                    return;
                }
                StringBuilder sb4 = new StringBuilder(151);
                int i20 = iArr8[i18];
                while (true) {
                    iArr = AZTEC_OFFSET;
                    if (i20 < 151 - iArr[i18]) {
                        int i21 = AZTEC_MAP[i20][i19];
                        if (i21 == 1) {
                            sb4.append('1');
                        }
                        if (i21 == 0) {
                            sb4.append('0');
                        }
                        if (i21 >= 2) {
                            int i22 = i21 - 2;
                            if (i22 < adjustBinaryString.length()) {
                                sb4.append(adjustBinaryString.charAt(i22));
                            } else if (i21 >= 20000) {
                                sb4.append(createDescriptor.charAt(i21 - 20000));
                            } else {
                                sb4.append('0');
                            }
                        }
                        i20++;
                    }
                }
                this.row_height[i19 - iArr[i18]] = 1;
                this.pattern[i19 - iArr[i18]] = bin2pat(sb4);
                i19++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:357:0x0584 A[LOOP:17: B:355:0x0533->B:357:0x0584, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:358:0x053d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String generateAztecBinary() {
        /*
            Method dump skipped, instructions count: 2034
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.AztecCode.generateAztecBinary():java.lang.String");
    }

    private StringBuilder adjustBinaryString(String str, boolean z, int i) {
        StringBuilder sb = new StringBuilder();
        int codewordSize = getCodewordSize(i);
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            if ((sb.length() + 1) % codewordSize == 0) {
                if (i3 == codewordSize - 1) {
                    sb.append('0');
                } else if (i3 == 0) {
                    sb.append('1');
                } else {
                    sb.append(str.charAt(i2));
                    i3 = 0;
                }
                i2--;
                i3 = 0;
            } else {
                sb.append(str.charAt(i2));
                if (str.charAt(i2) == '1') {
                    i3++;
                }
            }
            i2++;
        }
        int length = codewordSize - (sb.length() % codewordSize);
        if (length == codewordSize) {
            length = 0;
        }
        for (int i4 = 0; i4 < length; i4++) {
            sb.append('1');
        }
        int length2 = sb.length();
        int i5 = 0;
        for (int i6 = length2 - codewordSize; i6 < length2 && i6 >= 0; i6++) {
            if (sb.charAt(i6) == '1') {
                i5++;
            }
        }
        if (i5 == codewordSize) {
            sb.setCharAt(length2 - 1, '0');
        }
        info("Codewords: ");
        for (int i7 = 0; i7 < length2 / codewordSize; i7++) {
            int i8 = 1 << (codewordSize - 1);
            int i9 = 0;
            for (int i10 = 0; i10 < codewordSize; i10++) {
                if (sb.charAt((i7 * codewordSize) + i10) == '1') {
                    i9 += i8;
                }
                i8 >>= 1;
            }
            infoSpace(i9);
        }
        infoLine();
        return sb;
    }

    private String eciToBinary() {
        String num = Integer.toString(this.eciMode);
        StringBuilder sb = new StringBuilder(num.length() * 4);
        for (int i = 0; i < num.length(); i++) {
            sb.append(QUADBIT[num.charAt(i) - '.']);
            infoSpace(num.charAt(i));
        }
        return sb.toString();
    }

    private String createDescriptor(boolean z, int i, int i2) {
        int i3;
        StringBuilder sb = new StringBuilder();
        int i4 = 16;
        if (z) {
            int i5 = i - 1;
            if ((i5 & 2) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            if ((i5 & 1) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            if (this.readerInit) {
                sb.append('1');
            } else if (((i2 - 1) & 32) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            while (i4 > 0) {
                if (((i2 - 1) & i4) != 0) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
                i4 >>= 1;
            }
            i3 = 2;
        } else {
            while (i4 > 0) {
                if (((i - 1) & i4) != 0) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
                i4 >>= 1;
            }
            if (this.readerInit) {
                sb.append('1');
            } else if (((i2 - 1) & 1024) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            for (int i6 = 512; i6 > 0; i6 >>= 1) {
                if (((i2 - 1) & i6) != 0) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            i3 = 4;
        }
        infoLine("Mode Message: " + ((Object) sb));
        int[] iArr = new int[i3];
        int i7 = 0;
        for (int i8 = 0; i8 < i3; i8++) {
            for (int i9 = 0; i9 < 4; i9++) {
                if (sb.charAt((i8 * 4) + i9) == '1') {
                    iArr[i8] = iArr[i8] + (8 >> i9);
                }
            }
        }
        ReedSolomon reedSolomon = new ReedSolomon();
        reedSolomon.init_gf(19);
        if (z) {
            reedSolomon.init_code(5, 1);
            reedSolomon.encode(2, iArr);
            int[] iArr2 = new int[6];
            for (int i10 = 0; i10 < 5; i10++) {
                iArr2[i10] = reedSolomon.getResult(i10);
            }
            while (i7 < 5) {
                for (int i11 = 8; i11 > 0; i11 >>= 1) {
                    if ((iArr2[4 - i7] & i11) != 0) {
                        sb.append('1');
                    } else {
                        sb.append('0');
                    }
                }
                i7++;
            }
        } else {
            reedSolomon.init_code(6, 1);
            reedSolomon.encode(4, iArr);
            int[] iArr3 = new int[6];
            for (int i12 = 0; i12 < 6; i12++) {
                iArr3[i12] = reedSolomon.getResult(i12);
            }
            while (i7 < 6) {
                for (int i13 = 8; i13 > 0; i13 >>= 1) {
                    if ((iArr3[5 - i7] & i13) != 0) {
                        sb.append('1');
                    } else {
                        sb.append('0');
                    }
                }
                i7++;
            }
        }
        return sb.toString();
    }

    private void addErrorCorrection(StringBuilder sb, int i, int i2, int i3) {
        int i4;
        int i5;
        if (i == 6) {
            i4 = 32;
            i5 = 67;
        } else if (i == 8) {
            i4 = 128;
            i5 = 301;
        } else if (i == 10) {
            i4 = 512;
            i5 = 1033;
        } else {
            if (i != 12) {
                throw new OkapiException("Unrecognized codeword size: " + i);
            }
            i4 = 2048;
            i5 = 4201;
        }
        int i6 = i5;
        int i7 = i4;
        ReedSolomon reedSolomon = new ReedSolomon();
        int[] iArr = new int[i2 + 3];
        int[] iArr2 = new int[i3 + 3];
        for (int i8 = 0; i8 < i2; i8++) {
            for (int i9 = 0; i9 < i; i9++) {
                if (sb.charAt((i8 * i) + i9) == '1') {
                    iArr[i8] = iArr[i8] + (i4 >> i9);
                }
            }
        }
        reedSolomon.init_gf(i6);
        reedSolomon.init_code(i3, 1);
        reedSolomon.encode(i2, iArr);
        for (int i10 = 0; i10 < i3; i10++) {
            iArr2[i10] = reedSolomon.getResult(i10);
        }
        for (int i11 = i3 - 1; i11 >= 0; i11--) {
            for (int i12 = i7; i12 > 0; i12 >>= 1) {
                if ((iArr2[i11] & i12) != 0) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
        }
    }
}
