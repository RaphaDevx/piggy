package uk.org.okapibarcode.backend;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.PointerIconCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.material.internal.ViewUtils;
import com.salesforce.marketingcloud.analytics.stats.b;
import com.tealium.remotecommands.RemoteCommand;
import io.sentry.HttpStatusCodeRange;
import java.lang.reflect.Array;
import java.util.Arrays;
import okhttp3.internal.ws.WebSocketProtocol;
import uk.org.okapibarcode.graphics.Circle;
import uk.org.okapibarcode.graphics.Hexagon;

/* loaded from: classes4.dex */
public class MaxiCode extends Symbol {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int[] MAXICODE_GRID = {122, 121, 128, 127, 134, 133, 140, 139, 146, 145, 152, 151, 158, 157, 164, 163, 170, 169, 176, 175, 182, 181, 188, 187, 194, 193, 200, 199, 0, 0, 124, 123, 130, 129, 136, 135, 142, 141, 148, 147, 154, 153, 160, 159, 166, 165, 172, 171, 178, 177, 184, 183, 190, 189, 196, 195, ComposerKt.compositionLocalMapKey, ComposerKt.providerKey, 817, 0, WebSocketProtocol.PAYLOAD_SHORT, 125, 132, 131, 138, 137, 144, 143, 150, 149, 156, 155, 162, 161, 168, 167, 174, 173, 180, 179, 186, 185, 192, 191, 198, 197, ComposerKt.providerMapsKey, ComposerKt.providerValuesKey, 819, 818, 284, 283, 278, 277, 272, 271, 266, 265, 260, 259, 254, 253, 248, 247, 242, 241, 236, 235, 230, 229, 224, 223, 218, 217, 212, 211, ComposerKt.referenceKey, 205, 820, 0, 286, 285, 280, 279, 274, 273, 268, 267, 262, 261, 256, 255, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 249, 244, 243, 238, 237, 232, 231, 226, 225, 220, 219, 214, 213, 208, ComposerKt.reuseKey, 822, 821, 288, 287, 282, 281, 276, 275, 270, 269, 264, 263, 258, 257, 252, 251, 246, 245, 240, 239, 234, 233, 228, 227, 222, 221, 216, 215, 210, 209, 823, 0, 290, 289, 296, 295, 302, 301, 308, 307, 314, 313, 320, 319, 326, 325, 332, 331, 338, 337, 344, 343, 350, 349, 356, 355, 362, 361, 368, 367, 825, 824, 292, 291, 298, 297, 304, 303, 310, 309, TypedValues.AttributesType.TYPE_PATH_ROTATE, 315, 322, 321, 328, 327, 334, 333, 340, 339, 346, 345, 352, 351, 358, 357, 364, 363, 370, 369, 826, 0, 294, 293, 300, 299, 306, 305, 312, 311, TypedValues.AttributesType.TYPE_PIVOT_TARGET, TypedValues.AttributesType.TYPE_EASING, 324, 323, 330, 329, 336, 335, 342, 341, 348, 347, 354, 353, 360, 359, 366, 365, 372, 371, 828, 827, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR, 404, TypedValues.CycleType.TYPE_ALPHA, 398, 397, 392, 391, 80, 79, 0, 0, 14, 13, 38, 37, 3, 0, 45, 44, b.n, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 386, 385, 380, 379, 374, 373, 829, 0, WalletConstants.ERROR_CODE_UNSUPPORTED_API_VERSION, WalletConstants.ERROR_CODE_AUTHENTICATION_FAILURE, WalletConstants.ERROR_CODE_SPENDING_LIMIT_EXCEEDED, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, RemoteCommand.Response.STATUS_BAD_REQUEST, 399, 394, 393, 82, 81, 41, 0, 16, 15, 40, 39, 4, 0, 0, 46, b.p, b.o, 388, 387, 382, 381, 376, 375, 831, 830, WalletConstants.ERROR_CODE_ILLEGAL_CALLER, WalletConstants.ERROR_CODE_UNKNOWN, 408, 407, 402, TypedValues.CycleType.TYPE_CURVE_FIT, 396, 395, 84, 83, 42, 0, 0, 0, 0, 0, 6, 5, 48, 47, 114, 113, 390, 389, 384, 383, 378, 377, 832, 0, 416, WalletConstants.ERROR_CODE_APP_LABEL_UNAVAILABLE, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 421, 428, 427, 104, b.i, 56, 55, 17, 0, 0, 0, 0, 0, 0, 0, 21, 20, 86, 85, 434, 433, 440, 439, 446, 445, 834, 833, 418, 417, TypedValues.CycleType.TYPE_WAVE_OFFSET, TypedValues.CycleType.TYPE_WAVE_PERIOD, 430, 429, b.l, 105, 58, 57, 0, 0, 0, 0, 0, 0, 0, 0, 23, 22, 88, 87, 436, 435, 442, 441, 448, 447, 835, 0, TypedValues.CycleType.TYPE_EASING, 419, 426, TypedValues.CycleType.TYPE_WAVE_PHASE, 432, 431, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, b.m, 60, 59, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 90, 89, 438, 437, 444, 443, 450, 449, 837, 836, 482, 481, 476, 475, 470, 469, 49, 0, 31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 54, 53, 464, 463, 458, 457, 452, 451, 838, 0, 484, 483, 478, 477, 472, 471, 50, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 466, 465, 460, 459, 454, 453, 840, 839, 486, 485, 480, 479, 474, 473, 52, 51, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 43, 468, 467, 462, 461, 456, 455, 841, 0, 488, 487, 494, 493, 500, 499, 98, 97, 62, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 27, 92, 91, TypedValues.PositionType.TYPE_PERCENT_X, TypedValues.PositionType.TYPE_SIZE_PERCENT, 512, FrameMetricsAggregator.EVERY_DURATION, 518, 517, 843, 842, 490, 489, 496, 495, TypedValues.PositionType.TYPE_DRAWPATH, TypedValues.PositionType.TYPE_TRANSITION_EASING, 100, 99, 64, 63, 0, 0, 0, 0, 0, 0, 0, 0, 29, 28, 94, 93, TypedValues.PositionType.TYPE_CURVE_FIT, TypedValues.PositionType.TYPE_PERCENT_Y, 514, InputDeviceCompat.SOURCE_DPAD, 520, 519, 844, 0, 492, 491, 498, 497, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, TypedValues.PositionType.TYPE_PERCENT_WIDTH, 102, 101, 66, 65, 18, 0, 0, 0, 0, 0, 0, 0, 19, 30, 96, 95, TypedValues.PositionType.TYPE_POSITION_TYPE, 509, 516, 515, 522, 521, 846, 845, 560, 559, 554, 553, 548, 547, 542, 541, 74, 73, 33, 0, 0, 0, 0, 0, 0, 11, 68, 67, 116, 115, 536, 535, 530, 529, 524, 523, 847, 0, 562, 561, 556, 555, 550, 549, 544, 543, 76, 75, 0, 0, 8, 7, 36, 35, 12, 0, 70, 69, 118, 117, 538, 537, 532, 531, 526, 525, 849, 848, 564, 563, 558, 557, 552, 551, 546, 545, 78, 77, 0, 34, 10, 9, 26, 25, 0, 0, 72, 71, 120, 119, 540, 539, 534, 533, 528, 527, 850, 0, 566, 565, 572, 571, 578, 577, 584, 583, 590, 589, 596, 595, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, 601, TypedValues.MotionType.TYPE_DRAW_PATH, TypedValues.MotionType.TYPE_PATHMOTION_ARC, 614, 613, 620, 619, 626, 625, 632, 631, 638, 637, 644, 643, 852, 851, 568, 567, 574, 573, 580, 579, 586, 585, 592, 591, 598, 597, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR, TypedValues.MotionType.TYPE_EASING, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 616, 615, 622, 621, 628, 627, 634, 633, 640, 639, 646, 645, 853, 0, 570, 569, 576, 575, 582, 581, 588, 587, 594, 593, 600, HttpStatusCodeRange.DEFAULT_MAX, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_TYPE, 618, 617, 624, 623, 630, 629, 636, 635, 642, 641, 648, 647, 855, 854, 728, 727, 722, 721, 716, 715, 710, 709, TypedValues.TransitionType.TYPE_AUTO_TRANSITION, 703, 698, 697, 692, 691, 686, 685, 680, 679, 674, 673, 668, 667, 662, 661, 656, 655, 650, 649, 856, 0, 730, 729, 724, 723, 718, 717, 712, 711, TypedValues.TransitionType.TYPE_STAGGERED, TypedValues.TransitionType.TYPE_INTERPOLATOR, TypedValues.TransitionType.TYPE_DURATION, 699, 694, 693, 688, 687, 682, 681, 676, 675, 670, 669, 664, 663, 658, 657, 652, 651, 858, 857, 732, 731, 726, 725, 720, 719, 714, 713, 708, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, TypedValues.TransitionType.TYPE_TO, TypedValues.TransitionType.TYPE_FROM, 696, 695, 690, 689, 684, 683, 678, 677, 672, 671, 666, 665, 660, 659, 654, 653, 859, 0, 734, 733, 740, 739, 746, 745, 752, 751, 758, 757, 764, 763, 770, 769, 776, 775, 782, 781, 788, 787, 794, 793, 800, 799, 806, 805, 812, 811, 861, 860, 736, 735, 742, 741, 748, 747, 754, 753, 760, 759, 766, 765, 772, 771, 778, 777, 784, 783, 790, 789, 796, 795, 802, 801, 808, 807, 814, 813, 862, 0, 738, 737, 744, 743, 750, 749, 756, 755, 762, 761, ViewUtils.EDGE_TO_EDGE_FLAGS, 767, 774, 773, 780, 779, 786, 785, 792, 791, 798, 797, 804, 803, 810, 809, 816, 815, 864, 863};
    private static final int[] MAXICODE_SET = {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 5, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5, 5, 4, 5, 3, 4, 3, 5, 5, 4, 4, 3, 3, 3, 4, 3, 5, 4, 4, 3, 3, 4, 3, 3, 3, 4, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
    private static final int[] MAXICODE_SYMBOL_CHAR = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 30, 28, 29, 30, 35, 32, 53, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 37, 38, 39, 40, 41, 52, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 42, 43, 44, 45, 46, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 32, 54, 34, 35, 36, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 36, 37, 37, 38, 39, 40, 41, 42, 43, 38, 44, 37, 39, 38, 45, 46, 40, 41, 39, 40, 41, 42, 42, 47, 43, 44, 43, 44, 45, 45, 46, 47, 46, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 32, 33, 34, 35, 36, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 32, 33, 34, 35, 36};
    private int[] codewords;
    private int mode;
    private int structuredAppendPosition = 1;
    private int structuredAppendTotal = 1;
    private String primaryData = "";
    private int[] set = new int[144];
    private int[] character = new int[144];
    private boolean[][] grid = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, 33, 30);

    public void setMode(int i) {
        if (i < 2 || i > 6) {
            throw new IllegalArgumentException("Invalid MaxiCode mode: " + i);
        }
        this.mode = i;
    }

    public int getMode() {
        return this.mode;
    }

    public void setStructuredAppendPosition(int i) {
        if (i < 1 || i > 8) {
            throw new IllegalArgumentException("Invalid MaxiCode structured append position: " + i);
        }
        this.structuredAppendPosition = i;
    }

    public int getStructuredAppendPosition() {
        return this.structuredAppendPosition;
    }

    public void setStructuredAppendTotal(int i) {
        if (i < 1 || i > 8) {
            throw new IllegalArgumentException("Invalid MaxiCode structured append total: " + i);
        }
        this.structuredAppendTotal = i;
    }

    public int getStructuredAppendTotal() {
        return this.structuredAppendTotal;
    }

    public void setPrimary(String str) {
        this.primaryData = str;
    }

    public String getPrimary() {
        return this.primaryData;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int i;
        int i2;
        eciProcess();
        if (this.mode == 2) {
            for (int i3 = 0; i3 < 10 && i3 < this.primaryData.length(); i3++) {
                if (this.primaryData.charAt(i3) < '0' || this.primaryData.charAt(i3) > '9') {
                    this.mode = 3;
                    break;
                }
            }
        }
        processText();
        int[] iArr = this.character;
        int[] copyOf = Arrays.copyOf(iArr, iArr.length);
        this.codewords = copyOf;
        int i4 = this.mode;
        if (i4 == 2 || i4 == 3) {
            this.codewords = uk.org.okapibarcode.util.Arrays.insertArray(this.codewords, 0, getPrimaryCodewords());
        } else {
            this.codewords = uk.org.okapibarcode.util.Arrays.insertArray(copyOf, 0, new int[]{i4});
        }
        int i5 = this.structuredAppendTotal;
        if (i5 > 1) {
            int[] iArr2 = {33, (i5 - 1) | ((this.structuredAppendPosition - 1) << 3)};
            int i6 = this.mode;
            this.codewords = uk.org.okapibarcode.util.Arrays.insertArray(this.codewords, (i6 == 2 || i6 == 3) ? 10 : 1, iArr2);
        }
        if (this.mode == 5) {
            i = 68;
            i2 = 56;
        } else {
            i = 84;
            i2 = 40;
        }
        int i7 = i + 10;
        int[] iArr3 = this.codewords;
        if (iArr3.length > i7) {
            this.codewords = Arrays.copyOfRange(iArr3, 0, i7);
        }
        int[] insertArray = uk.org.okapibarcode.util.Arrays.insertArray(this.codewords, 10, getErrorCorrection(Arrays.copyOfRange(this.codewords, 0, 10), 10));
        this.codewords = insertArray;
        int[] copyOfRange = Arrays.copyOfRange(insertArray, 20, insertArray.length);
        int[] iArr4 = new int[copyOfRange.length / 2];
        int[] iArr5 = new int[copyOfRange.length / 2];
        for (int i8 = 0; i8 < copyOfRange.length; i8++) {
            if ((i8 & 1) != 0) {
                iArr4[(i8 - 1) / 2] = copyOfRange[i8];
            } else {
                iArr5[i8 / 2] = copyOfRange[i8];
            }
        }
        int i9 = i2 / 2;
        int[] errorCorrection = getErrorCorrection(iArr4, i9);
        int[] errorCorrection2 = getErrorCorrection(iArr5, i9);
        int[] iArr6 = this.codewords;
        this.codewords = Arrays.copyOf(iArr6, iArr6.length + errorCorrection.length + errorCorrection2.length);
        for (int i10 = 0; i10 < errorCorrection.length; i10++) {
            this.codewords[i + 20 + (i10 * 2) + 1] = errorCorrection[i10];
        }
        for (int i11 = 0; i11 < errorCorrection2.length; i11++) {
            this.codewords[i + 20 + (i11 * 2)] = errorCorrection2[i11];
        }
        infoLine("Mode: " + this.mode);
        infoLine("ECC Codewords: " + i2);
        info("Codewords: ");
        int i12 = 0;
        while (true) {
            int[] iArr7 = this.codewords;
            if (i12 >= iArr7.length) {
                break;
            }
            infoSpace(iArr7[i12]);
            i12++;
        }
        infoLine();
        int[] iArr8 = new int[7];
        int i13 = 0;
        while (true) {
            if (i13 >= 33) {
                break;
            }
            int i14 = 0;
            for (int i15 = 30; i14 < i15; i15 = 30) {
                int i16 = MAXICODE_GRID[(i13 * 30) + i14];
                int i17 = (i16 + 5) / 6;
                int i18 = (i16 + 5) % 6;
                if (i17 != 0) {
                    int[] iArr9 = this.codewords;
                    int i19 = i17 - 1;
                    iArr8[0] = (iArr9[i19] & 32) >> 5;
                    iArr8[1] = (iArr9[i19] & 16) >> 4;
                    iArr8[2] = (iArr9[i19] & 8) >> 3;
                    iArr8[3] = (iArr9[i19] & 4) >> 2;
                    iArr8[4] = (iArr9[i19] & 2) >> 1;
                    iArr8[5] = iArr9[i19] & 1;
                    if (iArr8[i18] != 0) {
                        this.grid[i13][i14] = true;
                    } else {
                        this.grid[i13][i14] = false;
                    }
                }
                i14++;
            }
            i13++;
        }
        boolean[][] zArr = this.grid;
        boolean[] zArr2 = zArr[0];
        zArr2[28] = true;
        zArr2[29] = true;
        boolean[] zArr3 = zArr[9];
        zArr3[10] = true;
        zArr3[11] = true;
        zArr[10][11] = true;
        zArr[15][7] = true;
        boolean[] zArr4 = zArr[16];
        zArr4[8] = true;
        zArr4[20] = true;
        zArr[17][20] = true;
        boolean[] zArr5 = zArr[22];
        zArr5[10] = true;
        boolean[] zArr6 = zArr[23];
        zArr6[10] = true;
        zArr5[17] = true;
        zArr6[17] = true;
        this.row_count = 33;
        this.readable = "";
        this.pattern = new String[33];
        this.row_height = new int[33];
        for (int i20 = 0; i20 < 33; i20++) {
            StringBuilder sb = new StringBuilder(30);
            for (int i21 = 0; i21 < 30; i21++) {
                if (this.grid[i20][i21]) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            this.pattern[i20] = bin2pat(sb);
            this.row_height[i20] = 1;
        }
    }

    private int[] getPrimaryCodewords() {
        String substring;
        if (this.primaryData.length() != 15) {
            throw new OkapiException("Invalid Primary String");
        }
        for (int i = 9; i < 15; i++) {
            if (this.primaryData.charAt(i) < '0' || this.primaryData.charAt(i) > '9') {
                throw new OkapiException("Invalid Primary String");
            }
        }
        if (this.mode == 2) {
            substring = this.primaryData.substring(0, 9);
            int indexOf = substring.indexOf(32);
            if (indexOf != -1) {
                substring = substring.substring(0, indexOf);
            }
        } else {
            substring = this.primaryData.substring(0, 6);
        }
        int parseInt = Integer.parseInt(this.primaryData.substring(9, 12));
        int parseInt2 = Integer.parseInt(this.primaryData.substring(12, 15));
        infoLine("Postal Code: " + substring);
        infoLine("Country Code: " + parseInt);
        infoLine("Service: " + parseInt2);
        if (this.mode == 2) {
            return getMode2PrimaryCodewords(substring, parseInt, parseInt2);
        }
        return getMode3PrimaryCodewords(substring, parseInt, parseInt2);
    }

    private static int[] getMode2PrimaryCodewords(String str, int i, int i2) {
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (str.charAt(i3) < '0' || str.charAt(i3) > '9') {
                str = str.substring(0, i3);
                break;
            }
        }
        int parseInt = Integer.parseInt(str);
        return new int[]{((parseInt & 3) << 4) | 2, (parseInt & 252) >> 2, (parseInt & 16128) >> 8, (1032192 & parseInt) >> 14, (66060288 & parseInt) >> 20, ((parseInt & 1006632960) >> 26) | ((str.length() & 3) << 4), ((str.length() & 60) >> 2) | ((i & 3) << 4), (i & 252) >> 2, ((i & ViewUtils.EDGE_TO_EDGE_FLAGS) >> 8) | ((i2 & 15) << 2), (i2 & PointerIconCompat.TYPE_TEXT) >> 4};
    }

    private static int[] getMode3PrimaryCodewords(String str, int i, int i2) {
        int length = str.length();
        int[] iArr = new int[length];
        String upperCase = str.toUpperCase();
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = upperCase.charAt(i3);
            if (upperCase.charAt(i3) >= 'A' && upperCase.charAt(i3) <= 'Z') {
                iArr[i3] = iArr[i3] - 64;
            }
            int i4 = iArr[i3];
            if (i4 == 27 || i4 == 31 || i4 == 33 || i4 >= 59) {
                iArr[i3] = 32;
            }
        }
        int i5 = iArr[5];
        int i6 = ((i5 & 3) << 4) | 3;
        int i7 = iArr[4];
        int i8 = ((i7 & 3) << 4) | ((i5 & 60) >> 2);
        int i9 = iArr[3];
        int i10 = ((i7 & 60) >> 2) | ((i9 & 3) << 4);
        int i11 = iArr[2];
        int i12 = ((i11 & 3) << 4) | ((i9 & 60) >> 2);
        int i13 = iArr[1];
        int i14 = iArr[0];
        return new int[]{i6, i8, i10, i12, ((i13 & 3) << 4) | ((i11 & 60) >> 2), ((i14 & 3) << 4) | ((i13 & 60) >> 2), ((i14 & 60) >> 2) | ((i & 3) << 4), (i & 252) >> 2, ((i & ViewUtils.EDGE_TO_EDGE_FLAGS) >> 8) | ((i2 & 15) << 2), (i2 & PointerIconCompat.TYPE_TEXT) >> 4};
    }

    private void processText() {
        int i;
        int i2;
        int i3;
        int length = this.content.length();
        if (length > 138) {
            throw new OkapiException("Input data too long");
        }
        for (int i4 = 0; i4 < 144; i4++) {
            this.set[i4] = -1;
            this.character[i4] = 0;
        }
        for (int i5 = 0; i5 < length; i5++) {
            this.set[i5] = MAXICODE_SET[this.inputData[i5]];
            this.character[i5] = MAXICODE_SYMBOL_CHAR[this.inputData[i5]];
        }
        int[] iArr = this.set;
        int i6 = 13;
        if (iArr[0] == 0) {
            int[] iArr2 = this.character;
            if (iArr2[0] == 13) {
                iArr2[0] = 0;
            }
            iArr[0] = 1;
        }
        int i7 = 1;
        while (i7 < length) {
            int[] iArr3 = this.set;
            if (iArr3[i7] == 0) {
                int i8 = this.character[i7];
                if (i8 == i6) {
                    iArr3[i7] = bestSurroundingSet(i7, length, 1, 5);
                    if (this.set[i7] == 5) {
                        this.character[i7] = i6;
                    } else {
                        this.character[i7] = 0;
                    }
                } else if (i8 == 28) {
                    iArr3[i7] = bestSurroundingSet(i7, length, 1, 2, 3, 4, 5);
                    if (this.set[i7] == 5) {
                        this.character[i7] = 32;
                    }
                } else if (i8 == 29) {
                    iArr3[i7] = bestSurroundingSet(i7, length, 1, 2, 3, 4, 5);
                    if (this.set[i7] == 5) {
                        this.character[i7] = 33;
                    }
                } else if (i8 == 30) {
                    iArr3[i7] = bestSurroundingSet(i7, length, 1, 2, 3, 4, 5);
                    if (this.set[i7] == 5) {
                        this.character[i7] = 34;
                    }
                } else if (i8 == 32) {
                    iArr3[i7] = bestSurroundingSet(i7, length, 1, 2, 3, 4, 5);
                    int i9 = this.set[i7];
                    if (i9 == 1) {
                        this.character[i7] = 32;
                    } else if (i9 == 2) {
                        this.character[i7] = 47;
                    } else {
                        this.character[i7] = 59;
                    }
                } else if (i8 == 44) {
                    iArr3[i7] = bestSurroundingSet(i7, length, 1, 2);
                    if (this.set[i7] == 2) {
                        this.character[i7] = 48;
                    }
                } else if (i8 == 46) {
                    iArr3[i7] = bestSurroundingSet(i7, length, 1, 2);
                    if (this.set[i7] == 2) {
                        this.character[i7] = 49;
                    }
                } else if (i8 == 47) {
                    iArr3[i7] = bestSurroundingSet(i7, length, 1, 2);
                    if (this.set[i7] == 2) {
                        this.character[i7] = 50;
                    }
                } else if (i8 == 58) {
                    iArr3[i7] = bestSurroundingSet(i7, length, 1, 2);
                    if (this.set[i7] == 2) {
                        this.character[i7] = 51;
                    }
                }
            }
            i7++;
            i6 = 13;
        }
        int i10 = length;
        while (true) {
            int[] iArr4 = this.set;
            if (i10 >= iArr4.length) {
                break;
            }
            if (iArr4[length - 1] == 2) {
                iArr4[i10] = 2;
            } else {
                iArr4[i10] = 1;
            }
            this.character[i10] = 33;
            i10++;
        }
        int i11 = this.mode;
        int i12 = (i11 == 2 || i11 == 3) ? 9 : 0;
        int i13 = 0;
        while (true) {
            i = 6;
            if (i12 >= 143) {
                break;
            }
            int[] iArr5 = this.set;
            i13 = (iArr5[i12] != 1 || (i3 = this.character[i12]) < 48 || i3 > 57) ? 0 : i13 + 1;
            if (i13 == 9) {
                iArr5[i12] = 6;
                iArr5[i12 - 1] = 6;
                iArr5[i12 - 2] = 6;
                iArr5[i12 - 3] = 6;
                iArr5[i12 - 4] = 6;
                iArr5[i12 - 5] = 6;
                iArr5[i12 - 6] = 6;
                iArr5[i12 - 7] = 6;
                iArr5[i12 - 8] = 6;
                i13 = 0;
            }
            i12++;
        }
        int i14 = 1;
        int i15 = 0;
        while (true) {
            int[] iArr6 = this.set;
            int i16 = iArr6[i15];
            if (i16 != i14 && i16 != i) {
                if (i16 == 1) {
                    int i17 = i15 + 1;
                    if (i17 < iArr6.length && iArr6[i17] == 1) {
                        int i18 = i15 + 2;
                        if (i18 < iArr6.length && iArr6[i18] == 1) {
                            int i19 = i15 + 3;
                            if (i19 < iArr6.length && iArr6[i19] == 1) {
                                insert(i15, 63);
                                length++;
                                i15 = i19;
                                i14 = 1;
                            } else {
                                insert(i15, 57);
                                length++;
                                i15 = i18;
                            }
                        } else {
                            insert(i15, 56);
                            length++;
                            i15 = i17;
                        }
                    } else {
                        insert(i15, 59);
                        length++;
                        i15++;
                    }
                } else if (i16 == 2) {
                    int i20 = i15 + 1;
                    if (i20 < iArr6.length && iArr6[i20] == 2) {
                        insert(i15, 63);
                        length++;
                        i15 = i20;
                        i14 = 2;
                    } else {
                        insert(i15, 59);
                        length++;
                    }
                } else if (i16 == 3) {
                    int i21 = i15 + 3;
                    if (i21 < iArr6.length && iArr6[i15 + 1] == 3 && iArr6[i15 + 2] == 3 && iArr6[i21] == 3) {
                        insert(i15, 60);
                        insert(i15, 60);
                        length++;
                        i15 = i21;
                        i14 = 3;
                    } else {
                        insert(i15, 60);
                        length++;
                    }
                } else if (i16 == 4) {
                    int i22 = i15 + 3;
                    if (i22 < iArr6.length && iArr6[i15 + 1] == 4 && iArr6[i15 + 2] == 4 && iArr6[i22] == 4) {
                        insert(i15, 61);
                        insert(i15, 61);
                        length++;
                        i15 = i22;
                        i14 = 4;
                    } else {
                        insert(i15, 61);
                        length++;
                    }
                } else if (i16 == 5) {
                    int i23 = i15 + 3;
                    if (i23 < iArr6.length && iArr6[i15 + 1] == 5 && iArr6[i15 + 2] == 5 && iArr6[i23] == 5) {
                        insert(i15, 62);
                        insert(i15, 62);
                        length++;
                        i15 = i23;
                        i14 = 5;
                    } else {
                        insert(i15, 62);
                        length++;
                    }
                } else {
                    throw new OkapiException("Unexpected set " + this.set[i15] + " at index " + i15 + ".");
                }
                i15++;
            }
            i15++;
            if (i15 >= this.set.length) {
                int i24 = 0;
                do {
                    if (this.set[i24] == 6) {
                        int i25 = 0;
                        for (int i26 = 0; i26 < 9; i26++) {
                            i25 = (i25 * 10) + (this.character[i24 + i26] - 48);
                        }
                        int[] iArr7 = this.character;
                        iArr7[i24] = 31;
                        iArr7[i24 + 1] = (1056964608 & i25) >> 24;
                        iArr7[i24 + 2] = (16515072 & i25) >> 18;
                        iArr7[i24 + 3] = (258048 & i25) >> 12;
                        iArr7[i24 + 4] = (i25 & 4032) >> 6;
                        iArr7[i24 + 5] = i25 & 63;
                        i24 += 6;
                        for (int i27 = i24; i27 < 140; i27++) {
                            int[] iArr8 = this.set;
                            int i28 = i27 + 3;
                            iArr8[i27] = iArr8[i28];
                            int[] iArr9 = this.character;
                            iArr9[i27] = iArr9[i28];
                        }
                        length -= 3;
                    } else {
                        i24++;
                    }
                } while (i24 < this.set.length);
                if (this.eciMode != 3) {
                    i2 = 0;
                    insert(0, 27);
                    if (this.eciMode >= 0 && this.eciMode <= 31) {
                        insert(1, this.eciMode & 31);
                        length += 2;
                    }
                    if (this.eciMode >= 32 && this.eciMode <= 1023) {
                        insert(1, (this.eciMode >> 6) + 32);
                        insert(2, this.eciMode & 63);
                        length += 3;
                    }
                    if (this.eciMode >= 1024 && this.eciMode <= 32767) {
                        insert(1, (this.eciMode >> 12) + 48);
                        insert(2, (this.eciMode >> 6) & 63);
                        insert(3, this.eciMode & 63);
                        length += 4;
                    }
                    if (this.eciMode >= 32768 && this.eciMode <= 999999) {
                        insert(1, (this.eciMode >> 18) + 56);
                        insert(2, (this.eciMode >> 12) & 63);
                        insert(3, (this.eciMode >> 6) & 63);
                        insert(4, this.eciMode & 63);
                        length += 5;
                    }
                } else {
                    i2 = 0;
                }
                int i29 = this.mode;
                if (length > ((i29 == 2 || i29 == 3) ? 84 : (i29 == 4 || i29 == 6) ? 93 : i29 == 5 ? 77 : i2)) {
                    throw new OkapiException("Input data too long");
                }
                return;
            }
            i = 6;
        }
    }

    private int bestSurroundingSet(int i, int i2, int... iArr) {
        int[] iArr2 = this.set;
        int i3 = iArr2[i - 1];
        int i4 = i + 1;
        if (i4 >= i2) {
            return uk.org.okapibarcode.util.Arrays.contains(iArr, i3) ? i3 : iArr[0];
        }
        int i5 = iArr2[i4];
        if (uk.org.okapibarcode.util.Arrays.contains(iArr, i3) && uk.org.okapibarcode.util.Arrays.contains(iArr, i5)) {
            return Math.min(i3, i5);
        }
        return uk.org.okapibarcode.util.Arrays.contains(iArr, i3) ? i3 : uk.org.okapibarcode.util.Arrays.contains(iArr, i5) ? i5 : iArr[0];
    }

    private void insert(int i, int i2) {
        for (int i3 = 143; i3 > i; i3--) {
            int[] iArr = this.set;
            int i4 = i3 - 1;
            iArr[i3] = iArr[i4];
            int[] iArr2 = this.character;
            iArr2[i3] = iArr2[i4];
        }
        this.character[i] = i2;
    }

    private static int[] getErrorCorrection(int[] iArr, int i) {
        ReedSolomon reedSolomon = new ReedSolomon();
        reedSolomon.init_gf(67);
        reedSolomon.init_code(i, 1);
        reedSolomon.encode(iArr.length, iArr);
        int[] iArr2 = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr2[i2] = reedSolomon.getResult((i - 1) - i2);
        }
        return iArr2;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        resetPlotElements();
        this.symbol_height = 72;
        this.symbol_width = 74;
        for (int i = 0; i < 33; i++) {
            for (int i2 = 0; i2 < 30; i2++) {
                if (this.grid[i][i2]) {
                    double d = (i2 * 2.46d) + 1.23d;
                    if ((i & 1) != 0) {
                        d += 1.23d;
                    }
                    this.hexagons.add(new Hexagon(d, (i * 2.135d) + 1.43d));
                }
            }
        }
        double[] dArr = {10.85d, 8.97d, 7.1d, 5.22d, 3.31d, 1.43d};
        for (int i3 = 0; i3 < 6; i3++) {
            this.target.add(new Circle(35.76d, 35.6d, dArr[i3]));
        }
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected int[] getCodewords() {
        return this.codewords;
    }
}
