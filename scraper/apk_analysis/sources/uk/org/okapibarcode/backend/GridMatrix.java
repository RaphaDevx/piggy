package uk.org.okapibarcode.backend;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.PointerIconCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.android.gms.wallet.WalletConstants;
import com.google.zxing.common.StringUtils;
import com.salesforce.marketingcloud.analytics.stats.b;
import com.tealium.remotecommands.RemoteCommand;
import io.sentry.HttpStatusCodeRange;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import kotlin.UByte;
import kotlin.text.Typography;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;

/* loaded from: classes4.dex */
public class GridMatrix extends Symbol {
    private StringBuilder binary;
    private boolean[] grid;
    private static final char[] SHIFT_SET = {0, 1, 2, 3, 4, 5, 6, 7, '\b', '\t', '\n', 11, '\f', CharUtils.CR, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, '!', '\"', '#', '$', '%', Typography.amp, '\'', '(', ')', '*', '+', ',', '-', ClassUtils.PACKAGE_SEPARATOR_CHAR, JsonPointer.SEPARATOR, ':', ';', Typography.less, '=', Typography.greater, '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'};
    private static final int[] GM_RECOMMEND_CW = {9, 30, 59, 114, 170, 237, 315, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, TypedValues.PositionType.TYPE_PERCENT_X, 618, 741, 875, PointerIconCompat.TYPE_GRABBING};
    private static final int[] GM_MAX_CW = {11, 40, 79, 146, 218, 305, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, 521, 650, 794, 953, 1125, 1313};
    private static final int[] GM_DATA_CODEWORDS = {0, 15, 13, 11, 9, 45, 40, 35, 30, 25, 89, 79, 69, 59, 49, 146, 130, 114, 98, 81, 218, 194, 170, 146, 121, 305, 271, 237, ComposerKt.providerValuesKey, 169, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, 360, 315, 270, 225, 521, 463, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, 347, 289, 650, 578, TypedValues.PositionType.TYPE_PERCENT_X, 434, 361, 794, TypedValues.TransitionType.TYPE_STAGGERED, 618, 530, 441, 953, 847, 741, 635, 529, 1125, 1000, 875, 750, 625, 1313, 1167, PointerIconCompat.TYPE_GRABBING, 875, 729};
    private static final int[] GM_N1 = {18, 50, 98, 81, 121, 113, 113, 116, 121, WebSocketProtocol.PAYLOAD_SHORT, 118, 125, 122};
    private static final int[] GM_B1 = {1, 1, 1, 2, 2, 2, 2, 3, 2, 7, 5, 10, 6};
    private static final int[] GM_B2 = {0, 0, 0, 0, 0, 1, 2, 2, 4, 0, 4, 0, 6};
    private static final int[] GM_EBEB = {0, 0, 0, 0, 3, 1, 0, 0, 5, 1, 0, 0, 7, 1, 0, 0, 9, 1, 0, 0, 5, 1, 0, 0, 10, 1, 0, 0, 15, 1, 0, 0, 20, 1, 0, 0, 25, 1, 0, 0, 9, 1, 0, 0, 19, 1, 0, 0, 29, 1, 0, 0, 39, 1, 0, 0, 49, 1, 0, 0, 8, 2, 0, 0, 16, 2, 0, 0, 24, 2, 0, 0, 32, 2, 0, 0, 41, 1, 10, 1, 12, 2, 0, 0, 24, 2, 0, 0, 36, 2, 0, 0, 48, 2, 0, 0, 61, 1, 60, 1, 11, 3, 0, 0, 23, 1, 22, 2, 34, 2, 33, 1, 45, 3, 0, 0, 57, 1, 56, 2, 12, 1, 11, 3, 23, 2, 22, 2, 34, 3, 33, 1, 45, 4, 0, 0, 57, 1, 56, 3, 12, 2, 11, 3, 23, 5, 0, 0, 35, 3, 34, 2, 47, 1, 46, 4, 58, 4, 57, 1, 12, 6, 0, 0, 24, 6, 0, 0, 36, 6, 0, 0, 48, 6, 0, 0, 61, 1, 60, 5, 13, 4, 12, 3, 26, 1, 25, 6, 38, 5, 37, 2, 51, 2, 50, 5, 63, 7, 0, 0, 12, 6, 11, 3, 24, 4, 23, 5, 36, 2, 35, 7, 47, 9, 0, 0, 59, 7, 58, 2, 13, 5, 12, 5, 25, 10, 0, 0, 38, 5, 37, 5, 50, 10, 0, 0, 63, 5, 62, 5, 13, 1, 12, 11, 25, 3, 24, 9, 37, 5, 36, 7, 49, 7, 48, 5, 61, 9, 60, 3};
    private static final int[] GM_MACRO_MATRIX = {728, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 727, 624, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 651, 726, 623, 528, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 553, 652, 725, 622, 527, 440, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 463, 554, 653, 724, 621, 526, 439, 360, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 381, 464, 555, 654, 723, 620, 525, 438, 359, 288, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 307, 382, 465, 556, 655, 722, 619, 524, 437, 358, 287, 224, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 241, 308, 383, 466, 557, 656, 721, 618, 523, 436, 357, 286, 223, 168, 121, 122, 123, 124, 125, WebSocketProtocol.PAYLOAD_SHORT, 127, 128, 129, 130, 131, 132, 183, 242, 309, 384, 467, 558, 657, 720, 617, 522, 435, 356, 285, 222, 167, 120, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 133, 184, 243, 310, 385, 468, 559, 658, 719, 616, 521, 434, 355, 284, 221, 166, 119, 80, 49, 50, 51, 52, 53, 54, 55, 56, 91, 134, 185, 244, 311, 386, 469, 560, 659, 718, 615, 520, 433, 354, 283, 220, 165, 118, 79, 48, 25, 26, 27, 28, 29, 30, 57, 92, 135, 186, 245, 312, 387, 470, 561, 660, 717, 614, 519, 432, 353, 282, 219, 164, 117, 78, 47, 24, 9, 10, 11, 12, 31, 58, 93, 136, 187, 246, 313, 388, 471, 562, 661, 716, 613, 518, 431, 352, 281, 218, 163, 116, 77, 46, 23, 8, 1, 2, 13, 32, 59, 94, 137, 188, 247, 314, 389, 472, 563, 662, 715, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID, 517, 430, 351, 280, 217, 162, 115, 76, 45, 22, 7, 0, 3, 14, 33, 60, 95, 138, 189, 248, 315, 390, 473, 564, 663, 714, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_TYPE, 516, 429, 350, 279, 216, 161, 114, 75, 44, 21, 6, 5, 4, 15, 34, 61, 96, 139, 190, 249, TypedValues.AttributesType.TYPE_PATH_ROTATE, 391, 474, 565, 664, 713, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 515, 428, 349, 278, 215, 160, 113, 74, 43, 20, 19, 18, 17, 16, 35, 62, 97, 140, 191, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, TypedValues.AttributesType.TYPE_EASING, 392, 475, 566, 665, 712, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 514, 427, 348, 277, 214, 159, b.p, 73, 42, 41, 40, 39, 38, 37, 36, 63, 98, 141, 192, 251, TypedValues.AttributesType.TYPE_PIVOT_TARGET, 393, 476, 567, 666, 711, TypedValues.MotionType.TYPE_DRAW_PATH, InputDeviceCompat.SOURCE_DPAD, 426, 347, 276, 213, 158, b.o, 72, 71, 70, 69, 68, 67, 66, 65, 64, 99, 142, 193, 252, 319, 394, 477, 568, 667, 710, TypedValues.MotionType.TYPE_PATHMOTION_ARC, 512, TypedValues.CycleType.TYPE_WAVE_PHASE, 346, 275, 212, 157, b.n, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, b.m, b.l, 105, 104, b.i, 102, 101, 100, 143, 194, 253, 320, 395, 478, 569, 668, 709, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, FrameMetricsAggregator.EVERY_DURATION, TypedValues.CycleType.TYPE_WAVE_OFFSET, 345, 274, 211, 156, 155, 154, 153, 152, 151, 150, 149, 148, 147, 146, 145, 144, 195, 254, 321, 396, 479, 570, 669, 708, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, TypedValues.PositionType.TYPE_POSITION_TYPE, TypedValues.CycleType.TYPE_WAVE_PERIOD, 344, 273, 210, 209, 208, ComposerKt.reuseKey, ComposerKt.referenceKey, 205, ComposerKt.providerMapsKey, ComposerKt.providerValuesKey, ComposerKt.compositionLocalMapKey, ComposerKt.providerKey, 200, 199, 198, 197, 196, 255, 322, 397, 480, 571, 670, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR, 509, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 343, 272, 271, 270, 269, 268, 267, 266, 265, 264, 263, 262, 261, 260, 259, 258, 257, 256, 323, 398, 481, 572, 671, TypedValues.TransitionType.TYPE_STAGGERED, TypedValues.MotionType.TYPE_EASING, TypedValues.PositionType.TYPE_CURVE_FIT, 421, 342, 341, 340, 339, 338, 337, 336, 335, 334, 333, 332, 331, 330, 329, 328, 327, 326, 325, 324, 399, 482, 573, 672, TypedValues.TransitionType.TYPE_INTERPOLATOR, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, TypedValues.PositionType.TYPE_PERCENT_Y, TypedValues.CycleType.TYPE_EASING, 419, 418, 417, 416, WalletConstants.ERROR_CODE_APP_LABEL_UNAVAILABLE, WalletConstants.ERROR_CODE_ILLEGAL_CALLER, WalletConstants.ERROR_CODE_UNKNOWN, WalletConstants.ERROR_CODE_UNSUPPORTED_API_VERSION, WalletConstants.ERROR_CODE_AUTHENTICATION_FAILURE, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR, 408, 407, WalletConstants.ERROR_CODE_SPENDING_LIMIT_EXCEEDED, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, 404, TypedValues.CycleType.TYPE_ALPHA, 402, TypedValues.CycleType.TYPE_CURVE_FIT, RemoteCommand.Response.STATUS_BAD_REQUEST, 483, 574, 673, TypedValues.TransitionType.TYPE_AUTO_TRANSITION, 601, TypedValues.PositionType.TYPE_PERCENT_X, TypedValues.PositionType.TYPE_SIZE_PERCENT, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, TypedValues.PositionType.TYPE_PERCENT_WIDTH, TypedValues.PositionType.TYPE_DRAWPATH, TypedValues.PositionType.TYPE_TRANSITION_EASING, 500, 499, 498, 497, 496, 495, 494, 493, 492, 491, 490, 489, 488, 487, 486, 485, 484, 575, 674, 703, 600, HttpStatusCodeRange.DEFAULT_MAX, 598, 597, 596, 595, 594, 593, 592, 591, 590, 589, 588, 587, 586, 585, 584, 583, 582, 581, 580, 579, 578, 577, 576, 675, TypedValues.TransitionType.TYPE_TO, TypedValues.TransitionType.TYPE_FROM, TypedValues.TransitionType.TYPE_DURATION, 699, 698, 697, 696, 695, 694, 693, 692, 691, 690, 689, 688, 687, 686, 685, 684, 683, 682, 681, 680, 679, 678, 677, 676};
    private static final char[] MIXED_ALPHANUM_SET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};
    private int[] word = new int[1460];
    private Mode appxDnextSection = Mode.NULL;
    private Mode appxDlastSection = Mode.NULL;
    private int preferredVersion = 0;
    private int preferredEccLevel = -1;

    private enum Mode {
        NULL,
        GM_NUMBER,
        GM_LOWER,
        GM_UPPER,
        GM_MIXED,
        GM_CONTROL,
        GM_BYTE,
        GM_CHINESE
    }

    public void setPreferredVersion(int i) {
        this.preferredVersion = i;
    }

    public void setPreferredEccLevel(int i) {
        this.preferredEccLevel = i;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int length;
        boolean z;
        int i;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 1460; i2++) {
            this.word[i2] = 0;
        }
        try {
            Charset forName = Charset.forName(StringUtils.GB2312);
            if (forName.newEncoder().canEncode(this.content)) {
                byte[] bytes = this.content.getBytes(forName);
                this.inputData = new int[bytes.length];
                int i3 = 0;
                length = 0;
                while (i3 < bytes.length) {
                    byte b = bytes[i3];
                    if ((b & UByte.MAX_VALUE) >= 161 && (b & UByte.MAX_VALUE) <= 247) {
                        int[] iArr = this.inputData;
                        int i4 = (bytes[i3] & UByte.MAX_VALUE) * 256;
                        i3++;
                        iArr[length] = i4 + (bytes[i3] & UByte.MAX_VALUE);
                    } else {
                        this.inputData[length] = bytes[i3] & UByte.MAX_VALUE;
                    }
                    length++;
                    i3++;
                }
                infoLine("Using GB2312 character encoding");
                this.eciMode = 29;
            } else {
                eciProcess();
                length = this.inputData.length;
            }
            if (encodeGridMatrixBinary(length, this.readerInit) != 0) {
                throw new OkapiException("Input data too long");
            }
            int length2 = this.binary.length() / 7;
            int i5 = 1;
            for (int i6 = 0; i6 < 13; i6++) {
                if (GM_RECOMMEND_CW[i6] < length2) {
                    i5 = i6 + 1;
                }
            }
            int i7 = 13;
            for (int i8 = 12; i8 > 0; i8--) {
                if (GM_MAX_CW[i8 - 1] >= length2) {
                    i7 = i8;
                }
            }
            int i9 = i5 == 1 ? 5 : 3;
            if (i5 == 2 || i5 == 3) {
                i9 = 4;
            }
            int i10 = i5 == 1 ? 4 : 1;
            if (i5 == 2 || i5 == 3) {
                i10 = 2;
            }
            int i11 = this.preferredVersion;
            if (i11 < 1 || i11 > 13) {
                z = false;
            } else if (i11 > i7) {
                z = true;
                i5 = i11;
            } else {
                i5 = i7;
                z = true;
            }
            if (z) {
                i9 = i5 == 1 ? 5 : 3;
                if (i5 == 2 || i5 == 3) {
                    i9 = 4;
                }
                if (length2 > GM_DATA_CODEWORDS[((i5 - 1) * 5) + (i9 - 1)]) {
                    i5++;
                }
            }
            if (!z) {
                int i12 = this.preferredEccLevel;
                if (i12 < 1 || i12 > 5) {
                    i10 = i9;
                } else if (i12 > i10) {
                    i10 = i12;
                }
                int i13 = i10 - 1;
                if (length2 > GM_DATA_CODEWORDS[((i5 - 1) * 5) + i13]) {
                    while (true) {
                        i = i5 + 1;
                        if (length2 <= GM_DATA_CODEWORDS[(i5 * 5) + i13] || i > 13) {
                            break;
                        } else {
                            i5 = i;
                        }
                    }
                    i5 = i;
                }
                i9 = i10;
            }
            if (length2 > (i9 != 2 ? i9 != 3 ? i9 != 4 ? i9 != 5 ? 1313 : 729 : 875 : PointerIconCompat.TYPE_GRABBING : 1167)) {
                throw new OkapiException("Input data too long");
            }
            addErrorCorrection(length2, i5, i9);
            int i14 = (i5 * 12) + 6;
            int i15 = (i5 * 2) + 1;
            infoLine("Layers: " + i5);
            infoLine("ECC Level: " + i9);
            infoLine("Data Codewords: " + length2);
            infoLine("ECC Codewords: " + GM_DATA_CODEWORDS[((i5 + (-1)) * 5) + i9 + (-1)]);
            infoLine("Grid Size: " + i15 + " X " + i15);
            this.grid = new boolean[i14 * i14];
            for (int i16 = 0; i16 < i14; i16++) {
                for (int i17 = 0; i17 < i14; i17++) {
                    this.grid[(i17 * i14) + i16] = false;
                }
            }
            placeDataInGrid(i15, i14);
            addLayerId(i14, i5, i15, i9);
            for (int i18 = 0; i18 < i15; i18++) {
                int i19 = 1 - (i18 & 1);
                for (int i20 = 0; i20 < i15; i20++) {
                    if (i19 == 1) {
                        for (int i21 = 0; i21 < 5; i21++) {
                            boolean[] zArr = this.grid;
                            int i22 = i20 * 6;
                            int i23 = i18 * 6;
                            zArr[(i22 * i14) + i23 + i21] = true;
                            zArr[((i22 + 5) * i14) + i23 + i21] = true;
                            int i24 = ((i22 + i21) * i14) + i23;
                            zArr[i24] = true;
                            zArr[i24 + 5] = true;
                        }
                        this.grid[(((i20 * 6) + 5) * i14) + (i18 * 6) + 5] = true;
                        i19 = 0;
                    } else {
                        i19 = 1;
                    }
                }
            }
            this.symbol_width = i14;
            this.row_count = i14;
            this.row_height = new int[this.row_count];
            this.pattern = new String[this.row_count];
            for (int i25 = 0; i25 < i14; i25++) {
                sb.setLength(0);
                for (int i26 = 0; i26 < i14; i26++) {
                    if (this.grid[(i25 * i14) + i26]) {
                        sb.append('1');
                    } else {
                        sb.append('0');
                    }
                }
                this.row_height[i25] = 1;
                this.pattern[i25] = bin2pat(sb);
            }
        } catch (UnsupportedCharsetException unused) {
            throw new OkapiException("Byte conversion encoding error");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:66:0x02bd. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0498 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:258:0x04a4 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int encodeGridMatrixBinary(int r23, boolean r24) {
        /*
            Method dump skipped, instructions count: 1786
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.GridMatrix.encodeGridMatrixBinary(int, boolean):int");
    }

    /* renamed from: uk.org.okapibarcode.backend.GridMatrix$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode = iArr;
            try {
                iArr[Mode.GM_CHINESE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[Mode.GM_NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[Mode.GM_LOWER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[Mode.GM_UPPER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[Mode.GM_MIXED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[Mode.GM_BYTE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[Mode.NULL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[Mode.GM_CONTROL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private Mode[] calculateModeMap(int i) {
        int i2;
        int i3;
        int[] iArr;
        Mode mode;
        Mode[] modeArr = new Mode[i];
        boolean z = false;
        for (int i4 = 0; i4 < i; i4++) {
            modeArr[i4] = Mode.NULL;
            if (this.inputData[i4] > 255) {
                modeArr[i4] = Mode.GM_CHINESE;
            }
        }
        if (i > 3) {
            int i5 = 1;
            do {
                if (this.inputData[i5] == 13) {
                    int i6 = i5 + 1;
                    if (this.inputData[i6] == 10) {
                        if (modeArr[i5 - 1] == Mode.GM_CHINESE) {
                            modeArr[i5] = Mode.GM_CHINESE;
                            modeArr[i6] = Mode.GM_CHINESE;
                        }
                        i5 += 2;
                    }
                }
                i5++;
            } while (i5 < i - 1);
            int i7 = i - 3;
            do {
                if (this.inputData[i7] == 13) {
                    int i8 = i7 + 1;
                    if (this.inputData[i8] == 10) {
                        if (modeArr[i7 + 2] == Mode.GM_CHINESE) {
                            modeArr[i7] = Mode.GM_CHINESE;
                            modeArr[i8] = Mode.GM_CHINESE;
                        }
                        i7 -= 2;
                    }
                }
                i7--;
            } while (i7 > 0);
        }
        int i9 = 0;
        boolean z2 = false;
        int i10 = 0;
        int i11 = 1;
        while (true) {
            i2 = i - 1;
            if (i11 >= i2) {
                break;
            }
            if (this.inputData[i11] < 48 || this.inputData[i11] > 57) {
                if (z2) {
                    if (i9 % 2 == 0 && modeArr[i10 - 1] == Mode.GM_CHINESE && modeArr[i11] == Mode.GM_CHINESE) {
                        for (int i12 = 0; i12 < i9; i12++) {
                            modeArr[(i11 - i12) - 1] = Mode.GM_CHINESE;
                        }
                    }
                    z2 = false;
                }
            } else if (z2) {
                i9++;
            } else {
                i10 = i11;
                i9 = 1;
                z2 = true;
            }
            i11++;
        }
        for (int i13 = 0; i13 < i; i13++) {
            if (this.inputData[i13] >= 97 && this.inputData[i13] <= 122) {
                modeArr[i13] = Mode.GM_LOWER;
            }
        }
        for (int i14 = 0; i14 < i; i14++) {
            if (this.inputData[i14] >= 65 && this.inputData[i14] <= 90) {
                modeArr[i14] = Mode.GM_UPPER;
            }
        }
        boolean z3 = false;
        int i15 = 0;
        int i16 = 0;
        for (int i17 = 1; i17 < i2; i17++) {
            if (this.inputData[i17] == 32) {
                if (z3) {
                    i16++;
                } else {
                    i15 = i17;
                    z3 = true;
                    i16 = 1;
                }
            } else if (z3) {
                Mode mode2 = modeArr[i15 - 1];
                Mode mode3 = modeArr[i17];
                if (mode2 == Mode.GM_LOWER || mode2 == Mode.GM_UPPER) {
                    for (int i18 = 0; i18 < i16; i18++) {
                        modeArr[(i17 - i18) - 1] = mode2;
                    }
                } else if (mode3 == Mode.GM_LOWER || mode3 == Mode.GM_UPPER) {
                    for (int i19 = 0; i19 < i16; i19++) {
                        modeArr[(i17 - i19) - 1] = mode3;
                    }
                }
                z3 = false;
            }
        }
        for (int i20 = 0; i20 < i; i20++) {
            if (modeArr[i20] == Mode.NULL) {
                if (this.inputData[i20] >= 48 && this.inputData[i20] <= 57) {
                    modeArr[i20] = Mode.GM_NUMBER;
                } else {
                    int i21 = this.inputData[i20];
                    if (i21 != 13) {
                        if (i21 != 32) {
                            switch (i21) {
                            }
                        }
                        modeArr[i20] = Mode.GM_NUMBER;
                    } else if (i20 < i2) {
                        int i22 = i20 + 1;
                        if (this.inputData[i22] == 10) {
                            modeArr[i20] = Mode.GM_NUMBER;
                            modeArr[i22] = Mode.GM_NUMBER;
                        }
                    }
                }
            }
        }
        for (int i23 = 0; i23 < i; i23++) {
            if (modeArr[i23] == Mode.NULL) {
                modeArr[i23] = Mode.GM_BYTE;
            }
        }
        int[] iArr2 = new int[i];
        Mode[] modeArr2 = new Mode[i];
        int[] iArr3 = new int[i];
        iArr2[0] = 1;
        modeArr2[0] = modeArr[0];
        iArr3[0] = 0;
        int i24 = 0;
        for (int i25 = 1; i25 < i; i25++) {
            Mode mode4 = modeArr[i25];
            if (mode4 == modeArr[i25 - 1]) {
                iArr2[i24] = iArr2[i24] + 1;
            } else {
                i24++;
                iArr2[i24] = 1;
                modeArr2[i24] = mode4;
                iArr3[i24] = i25;
            }
        }
        if (i24 > 1) {
            for (int i26 = 1; i26 < i24; i26++) {
                if (iArr2[i26] <= 3 && modeArr2[i26 - 1] != Mode.GM_CHINESE) {
                    boolean z4 = true;
                    for (int i27 = 0; i27 < iArr2[i26]; i27++) {
                        boolean z5 = false;
                        for (int i28 = 0; i28 < 63; i28++) {
                            if (this.inputData[iArr3[i26] + i27] == SHIFT_SET[i28]) {
                                z5 = true;
                            }
                        }
                        if (!z5) {
                            z4 = false;
                        }
                    }
                    if (z4) {
                        modeArr2[i26] = Mode.GM_CONTROL;
                    }
                }
            }
        }
        if (i24 >= 3) {
            int i29 = 0;
            while (i29 < i24 - 1) {
                if (i29 == 0) {
                    mode = Mode.NULL;
                } else {
                    mode = modeArr2[i29 - 1];
                }
                Mode mode5 = mode;
                Mode mode6 = modeArr2[i29];
                int i30 = iArr2[i29];
                int i31 = i29 + 1;
                Mode mode7 = modeArr2[i31];
                int i32 = iArr2[i31];
                int i33 = i29 + 2;
                Mode mode8 = modeArr2[i33];
                int i34 = iArr2[i33];
                int i35 = iArr3[i29];
                boolean z6 = i33 == i24 ? true : z;
                int i36 = i29;
                int i37 = i24;
                int[] iArr4 = iArr3;
                Mode bestMode = getBestMode(mode5, mode6, mode7, mode8, i30, i32, i34, i35, z6);
                modeArr2[i36] = bestMode;
                if (bestMode == Mode.GM_CONTROL) {
                    modeArr2[i36] = modeArr2[i36 - 1];
                }
                i24 = i37;
                i29 = i31;
                iArr3 = iArr4;
                z = false;
            }
            int i38 = i29;
            i3 = i24;
            iArr = iArr3;
            modeArr2[i38] = this.appxDnextSection;
            int i39 = i38 + 1;
            modeArr2[i39] = this.appxDlastSection;
            if (modeArr2[i38] == Mode.GM_CONTROL) {
                modeArr2[i38] = modeArr2[i38 - 1];
            }
            if (modeArr2[i39] == Mode.GM_CONTROL) {
                modeArr2[i39] = modeArr2[i38];
            }
        } else {
            i3 = i24;
            iArr = iArr3;
        }
        for (int i40 = 0; i40 < i3; i40++) {
            for (int i41 = 0; i41 < iArr2[i40]; i41++) {
                modeArr[iArr[i40] + i41] = modeArr2[i40];
            }
        }
        return modeArr;
    }

    private boolean isTransitionValid(Mode mode, Mode mode2) {
        int i;
        int i2 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[mode.ordinal()];
        if (i2 == 1) {
            int i3 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[mode2.ordinal()];
            if (i3 == 1 || i3 == 6) {
                return true;
            }
        } else if (i2 == 2) {
            int i4 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[mode2.ordinal()];
            if (i4 == 1 || i4 == 2 || i4 == 5 || i4 == 6) {
                return true;
            }
        } else if (i2 == 3) {
            int i5 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[mode2.ordinal()];
            if (i5 == 1 || i5 == 3 || i5 == 5 || i5 == 6) {
                return true;
            }
        } else if (i2 == 4) {
            int i6 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[mode2.ordinal()];
            if (i6 == 1 || i6 == 4 || i6 == 5 || i6 == 6) {
                return true;
            }
        } else if (i2 == 6) {
            int i7 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[mode2.ordinal()];
            if (i7 == 1 || i7 == 6) {
                return true;
            }
        } else if (i2 == 8 && ((i = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[mode2.ordinal()]) == 1 || i == 6 || i == 8)) {
            return true;
        }
        return false;
    }

    private Mode intToMode(int i) {
        switch (i) {
            case 1:
                return Mode.GM_CHINESE;
            case 2:
                return Mode.GM_BYTE;
            case 3:
                return Mode.GM_CONTROL;
            case 4:
                return Mode.GM_MIXED;
            case 5:
                return Mode.GM_UPPER;
            case 6:
                return Mode.GM_LOWER;
            case 7:
                return Mode.GM_NUMBER;
            default:
                return Mode.NULL;
        }
    }

    private Mode getBestMode(Mode mode, Mode mode2, Mode mode3, Mode mode4, int i, int i2, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7 = Integer.MAX_VALUE;
        Mode mode5 = mode2;
        int i8 = 1;
        while (true) {
            int i9 = 8;
            if (i8 >= 8) {
                return mode5;
            }
            if (isTransitionValid(mode2, intToMode(i8))) {
                int i10 = 1;
                while (i10 < i9) {
                    if (isTransitionValid(mode3, intToMode(i10))) {
                        int i11 = i7;
                        Mode mode6 = mode5;
                        int i12 = 1;
                        while (i12 < i9) {
                            if (isTransitionValid(mode4, intToMode(i12))) {
                                i5 = i12;
                                i6 = i11;
                                int binaryLength = getBinaryLength(mode, intToMode(i8), intToMode(i10), intToMode(i12), i, i2, i3, i4, z);
                                if (binaryLength <= i6) {
                                    mode6 = intToMode(i8);
                                    this.appxDnextSection = intToMode(i10);
                                    this.appxDlastSection = intToMode(i5);
                                    i11 = binaryLength;
                                    i12 = i5 + 1;
                                    i9 = 8;
                                }
                            } else {
                                i5 = i12;
                                i6 = i11;
                            }
                            i11 = i6;
                            i12 = i5 + 1;
                            i9 = 8;
                        }
                        i7 = i11;
                        mode5 = mode6;
                    }
                    i10++;
                    i9 = 8;
                }
            }
            i8++;
        }
    }

    private int getBinaryLength(Mode mode, Mode mode2, Mode mode3, Mode mode4, int i, int i2, int i3, int i4, boolean z) {
        int chunkLength = getChunkLength(mode, mode2, i, i4);
        int i5 = i4 + i;
        int chunkLength2 = chunkLength + getChunkLength(mode2, mode3, i2, i5) + getChunkLength(mode3, mode4, i3, i5 + i2);
        if (!z) {
            return chunkLength2;
        }
        switch (AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode[mode4.ordinal()]) {
            case 1:
                return chunkLength2 + 13;
            case 2:
            case 5:
                return chunkLength2 + 10;
            case 3:
            case 4:
                return chunkLength2 + 5;
            case 6:
                return chunkLength2 + 4;
            default:
                return chunkLength2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0046, code lost:
    
        if (r10 != uk.org.okapibarcode.backend.GridMatrix.Mode.GM_BYTE) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004b, code lost:
    
        if (r10 != uk.org.okapibarcode.backend.GridMatrix.Mode.GM_MIXED) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004d, code lost:
    
        r8 = r8 + 10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0062, code lost:
    
        if (r11 != 8) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007d, code lost:
    
        if (r11 != 8) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
    
        if (r10 != uk.org.okapibarcode.backend.GridMatrix.Mode.GM_CONTROL) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getChunkLength(uk.org.okapibarcode.backend.GridMatrix.Mode r9, uk.org.okapibarcode.backend.GridMatrix.Mode r10, int r11, int r12) {
        /*
            r8 = this;
            int[] r0 = uk.org.okapibarcode.backend.GridMatrix.AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode
            int r1 = r10.ordinal()
            r0 = r0[r1]
            r1 = 3
            r2 = 6
            r3 = 8
            r4 = 1
            r5 = 4
            r6 = 2
            r7 = 5
            if (r0 == r4) goto L31
            if (r0 == r6) goto L2c
            if (r0 == r1) goto L29
            if (r0 == r5) goto L29
            if (r0 == r7) goto L24
            if (r0 == r3) goto L21
            int r8 = r8.calcByteLength(r12, r11)
            goto L35
        L21:
            int r8 = r11 * 6
            goto L35
        L24:
            int r8 = r8.calcMixedLength(r12, r11)
            goto L35
        L29:
            int r8 = r11 * 5
            goto L35
        L2c:
            int r8 = r8.calcNumberLength(r12, r11)
            goto L35
        L31:
            int r8 = r8.calcChineseLength(r12, r11)
        L35:
            int[] r11 = uk.org.okapibarcode.backend.GridMatrix.AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode
            int r12 = r9.ordinal()
            r11 = r11[r12]
            switch(r11) {
                case 1: goto L89;
                case 2: goto L80;
                case 3: goto L6b;
                case 4: goto L50;
                case 5: goto L49;
                case 6: goto L44;
                case 7: goto L41;
                default: goto L40;
            }
        L40:
            goto L93
        L41:
            int r8 = r8 + 4
            goto L93
        L44:
            uk.org.okapibarcode.backend.GridMatrix$Mode r11 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_BYTE
            if (r10 == r11) goto L93
            goto L41
        L49:
            uk.org.okapibarcode.backend.GridMatrix$Mode r11 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_MIXED
            if (r10 == r11) goto L93
        L4d:
            int r8 = r8 + 10
            goto L93
        L50:
            int[] r11 = uk.org.okapibarcode.backend.GridMatrix.AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode
            int r12 = r10.ordinal()
            r11 = r11[r12]
            if (r11 == r4) goto L68
            if (r11 == r6) goto L68
            if (r11 == r1) goto L68
            if (r11 == r7) goto L65
            if (r11 == r2) goto L65
            if (r11 == r3) goto L65
            goto L93
        L65:
            int r8 = r8 + 7
            goto L93
        L68:
            int r8 = r8 + 5
            goto L93
        L6b:
            int[] r11 = uk.org.okapibarcode.backend.GridMatrix.AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$GridMatrix$Mode
            int r12 = r10.ordinal()
            r11 = r11[r12]
            if (r11 == r4) goto L68
            if (r11 == r6) goto L68
            if (r11 == r5) goto L68
            if (r11 == r7) goto L65
            if (r11 == r2) goto L65
            if (r11 == r3) goto L65
            goto L93
        L80:
            uk.org.okapibarcode.backend.GridMatrix$Mode r11 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_CHINESE
            if (r10 == r11) goto L93
            uk.org.okapibarcode.backend.GridMatrix$Mode r11 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_CONTROL
            if (r10 == r11) goto L93
            goto L4d
        L89:
            uk.org.okapibarcode.backend.GridMatrix$Mode r11 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_CHINESE
            if (r10 == r11) goto L93
            uk.org.okapibarcode.backend.GridMatrix$Mode r11 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_CONTROL
            if (r10 == r11) goto L93
            int r8 = r8 + 13
        L93:
            uk.org.okapibarcode.backend.GridMatrix$Mode r11 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_BYTE
            if (r9 == r11) goto L9d
            uk.org.okapibarcode.backend.GridMatrix$Mode r11 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_BYTE
            if (r10 != r11) goto L9d
            int r8 = r8 + 9
        L9d:
            uk.org.okapibarcode.backend.GridMatrix$Mode r11 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_NUMBER
            if (r9 == r11) goto La7
            uk.org.okapibarcode.backend.GridMatrix$Mode r9 = uk.org.okapibarcode.backend.GridMatrix.Mode.GM_NUMBER
            if (r10 != r9) goto La7
            int r8 = r8 + 2
        La7:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.GridMatrix.getChunkLength(uk.org.okapibarcode.backend.GridMatrix$Mode, uk.org.okapibarcode.backend.GridMatrix$Mode, int, int):int");
    }

    private int calcChineseLength(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        do {
            i3 += 13;
            if (i4 < i2) {
                int i5 = i + i4;
                if (this.inputData[i5] == 13 && this.inputData[i5 + 1] == 10) {
                    i4++;
                }
                int i6 = i + i4;
                if (this.inputData[i6] >= 48 && this.inputData[i6] <= 57) {
                    int i7 = i6 + 1;
                    if (this.inputData[i7] >= 48 && this.inputData[i7] <= 57) {
                        i4++;
                    }
                }
            }
            i4++;
        } while (i4 < i2);
        return i3;
    }

    private int calcMixedLength(int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 += 6;
            for (int i5 = 0; i5 < 63; i5++) {
                if (this.inputData[i + i4] == SHIFT_SET[i5]) {
                    i3 += 10;
                }
            }
        }
        return i3;
    }

    private int calcNumberLength(int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 < i2) {
            int i7 = i + i3;
            if (this.inputData[i7] < 48 || this.inputData[i7] > 57) {
                i5++;
            } else {
                i4++;
            }
            if (i3 != 0 && this.inputData[i7] == 10 && this.inputData[i7 - 1] == 13) {
                i5--;
            }
            if (i4 == 3) {
                i6 = i5 == 1 ? i6 + 20 : i6 + 10;
                if (i5 > 1) {
                    i6 += 100;
                }
                i4 = 0;
                i5 = 0;
            }
            i3++;
        }
        if (i4 > 0) {
            i6 = i5 == 1 ? i6 + 20 : i6 + 10;
        }
        if (i5 > 1) {
            i6 += 100;
        }
        int i8 = (i + i3) - 1;
        return (this.inputData[i8] < 48 || this.inputData[i8] > 57) ? i6 + 100 : i6;
    }

    private int calcByteLength(int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            i3 = this.inputData[i + i4] <= 255 ? i3 + 8 : i3 + 16;
        }
        return i3;
    }

    private void addByteCount(int i, int i2) {
        for (int i3 = 0; i3 < 9; i3++) {
            if (((256 >> i3) & i2) != 0) {
                this.binary.insert(i + i3, '0');
            } else {
                this.binary.insert(i + i3, '1');
            }
        }
    }

    void addShiftCharacter(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 64; i3++) {
            if (SHIFT_SET[i3] == i) {
                i2 = i3;
            }
        }
        info("SHT/");
        infoSpace(i2);
        for (int i4 = 32; i4 > 0; i4 >>= 1) {
            if ((i2 & i4) != 0) {
                this.binary.append('1');
            } else {
                this.binary.append('0');
            }
        }
    }

    private void addErrorCorrection(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int[] iArr = new int[1320];
        int[] iArr2 = new int[130];
        int[] iArr3 = new int[115];
        int[] iArr4 = new int[70];
        ReedSolomon reedSolomon = new ReedSolomon();
        int i7 = i2 - 1;
        int i8 = i3 - 1;
        int i9 = GM_DATA_CODEWORDS[(i7 * 5) + i8];
        for (int i10 = 0; i10 < 1320; i10++) {
            iArr[i10] = 0;
        }
        for (int i11 = 0; i11 < i; i11++) {
            for (int i12 = 0; i12 < 7; i12++) {
                if (this.binary.charAt((i11 * 7) + i12) == '1') {
                    iArr[i11] = iArr[i11] + (64 >> i12);
                }
            }
        }
        info("Codewords: ");
        for (int i13 = 0; i13 < i; i13++) {
            infoSpace(iArr[i13]);
        }
        infoLine();
        iArr[i] = 0;
        for (int i14 = i + 1; i14 < i9; i14++) {
            if ((i14 & 1) != 0) {
                iArr[i14] = 126;
            } else {
                iArr[i14] = 0;
            }
        }
        int i15 = GM_N1[i7];
        int i16 = GM_B1[i7];
        int i17 = i15 - 1;
        int i18 = GM_B2[i7];
        int[] iArr5 = GM_EBEB;
        int i19 = (i7 * 20) + (i8 * 4);
        int i20 = iArr5[i19];
        int i21 = iArr5[i19 + 1];
        int i22 = iArr5[i19 + 2];
        int i23 = 0;
        int i24 = 0;
        while (true) {
            int i25 = i16 + i18;
            if (i23 >= i25) {
                return;
            }
            int i26 = i23 < i16 ? i15 : i17;
            if (i23 < i21) {
                i4 = i16;
                i5 = i22;
                i6 = i20;
            } else {
                i4 = i16;
                i5 = i22;
                i6 = i5;
            }
            int i27 = i26 - i6;
            int i28 = i20;
            for (int i29 = 0; i29 < i27; i29++) {
                iArr3[i29] = iArr[i24];
                i24++;
            }
            reedSolomon.init_gf(137);
            reedSolomon.init_code(i6, 1);
            reedSolomon.encode(i27, iArr3);
            for (int i30 = 0; i30 < i6; i30++) {
                iArr4[i30] = reedSolomon.getResult(i30);
            }
            for (int i31 = 0; i31 < i27; i31++) {
                iArr2[i31] = iArr3[i31];
            }
            for (int i32 = 0; i32 < i6; i32++) {
                iArr2[i32 + i27] = iArr4[(i6 - i32) - 1];
            }
            for (int i33 = 0; i33 < i17; i33++) {
                this.word[(i25 * i33) + i23] = iArr2[i33];
            }
            if (i26 == i15) {
                this.word[(i25 * i17) + i23] = iArr2[i17];
            }
            i23++;
            i16 = i4;
            i22 = i5;
            i20 = i28;
        }
    }

    private void placeDataInGrid(int i, int i2) {
        int i3 = 13 - ((i - 1) / 2);
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = GM_MACRO_MATRIX[((i4 + i3) * 27) + i5 + i3];
                int[] iArr = this.word;
                int i7 = i6 * 2;
                placeMacroModule(i5, i4, iArr[i7], iArr[i7 + 1], i2);
            }
        }
    }

    void placeMacroModule(int i, int i2, int i3, int i4, int i5) {
        int i6 = (i * 6) + 1;
        int i7 = i2 * 6;
        int i8 = i7 + 1;
        if ((i4 & 64) != 0) {
            this.grid[(i8 * i5) + i6 + 2] = true;
        }
        if ((i4 & 32) != 0) {
            this.grid[(i8 * i5) + i6 + 3] = true;
        }
        if ((i4 & 16) != 0) {
            this.grid[((i7 + 2) * i5) + i6] = true;
        }
        if ((i4 & 8) != 0) {
            this.grid[((i7 + 2) * i5) + i6 + 1] = true;
        }
        if ((i4 & 4) != 0) {
            this.grid[((i7 + 2) * i5) + i6 + 2] = true;
        }
        if ((i4 & 2) != 0) {
            this.grid[((i7 + 2) * i5) + i6 + 3] = true;
        }
        if ((i4 & 1) != 0) {
            this.grid[((i7 + 3) * i5) + i6] = true;
        }
        if ((i3 & 64) != 0) {
            this.grid[((i7 + 3) * i5) + i6 + 1] = true;
        }
        if ((i3 & 32) != 0) {
            this.grid[((i7 + 3) * i5) + i6 + 2] = true;
        }
        if ((i3 & 16) != 0) {
            this.grid[((i7 + 3) * i5) + i6 + 3] = true;
        }
        if ((i3 & 8) != 0) {
            this.grid[((i7 + 4) * i5) + i6] = true;
        }
        if ((i3 & 4) != 0) {
            this.grid[((i7 + 4) * i5) + i6 + 1] = true;
        }
        if ((i3 & 2) != 0) {
            this.grid[((i7 + 4) * i5) + i6 + 2] = true;
        }
        if ((i3 & 1) != 0) {
            this.grid[((i7 + 4) * i5) + i6 + 3] = true;
        }
    }

    private void addLayerId(int i, int i2, int i3, int i4) {
        int[] iArr = new int[i2 + 1];
        int[] iArr2 = new int[i3 * i3];
        for (int i5 = 0; i5 <= i2; i5++) {
            if (i4 == 1) {
                iArr[i5] = 3 - (i5 % 4);
            } else {
                iArr[i5] = ((i5 + 5) - i4) % 4;
            }
        }
        for (int i6 = 0; i6 < i3; i6++) {
            for (int i7 = 0; i7 < i3; i7++) {
                iArr2[(i6 * i3) + i7] = 0;
            }
        }
        int i8 = i3 / 2;
        int i9 = i8;
        for (int i10 = 0; i10 <= i2; i10++) {
            for (int i11 = i8; i11 <= i9; i11++) {
                iArr2[(i8 * i3) + i11] = iArr[i10];
                int i12 = i11 * i3;
                iArr2[i12 + i8] = iArr[i10];
                int i13 = (i3 - i8) - 1;
                iArr2[(i13 * i3) + i11] = iArr[i10];
                iArr2[i12 + i13] = iArr[i10];
            }
            i8--;
            i9++;
        }
        for (int i14 = 0; i14 < i3; i14++) {
            for (int i15 = 0; i15 < i3; i15++) {
                int i16 = iArr2[(i14 * i3) + i15];
                if ((i16 & 2) != 0) {
                    this.grid[(((i14 * 6) + 1) * i) + (i15 * 6) + 1] = true;
                }
                if ((i16 & 1) != 0) {
                    this.grid[(((i14 * 6) + 1) * i) + (i15 * 6) + 2] = true;
                }
            }
        }
    }
}
