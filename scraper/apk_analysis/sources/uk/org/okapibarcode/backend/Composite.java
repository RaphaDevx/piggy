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
import com.google.zxing.pdf417.PDF417Common;
import com.salesforce.marketingcloud.analytics.stats.b;
import com.salesforce.marketingcloud.push.g;
import com.tealium.remotecommands.RemoteCommand;
import io.sentry.HttpStatusCodeRange;
import io.sentry.protocol.DebugImage;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.BooleanUtils;
import uk.org.okapibarcode.backend.DataBarExpanded;
import uk.org.okapibarcode.backend.Symbol;
import uk.org.okapibarcode.util.Strings;

/* loaded from: classes4.dex */
public class Composite extends Symbol {
    private StringBuilder binary_string;
    private CompositeMode cc_mode;
    private int cc_width;
    private int codeWordCount;
    private int ecc;
    private Integer guardPatternExtraHeight;
    private int[] inputData;
    private String linearContent;
    private Symbol.DataType linearDataType;
    private int linearWidth;
    private Integer preferredColumns;
    private int remainder;
    private int target_bitsize;
    private static final int[] CCA_COEFFS = {522, 568, 723, 809, 427, 919, 460, 155, 566, 861, 285, 19, 803, 17, 766, 76, 925, 537, 597, 784, 691, 437, 237, 308, 436, 284, 646, 653, 428, 379};
    private static final int[] COEFRS = {27, 917, 522, 568, 723, 809, 237, 308, 436, 284, 646, 653, 428, 379, 274, 562, 232, 755, HttpStatusCodeRange.DEFAULT_MAX, 524, 801, 132, 295, 116, 442, 428, 295, 42, 176, 65, 361, 575, 922, 525, 176, 586, 640, 321, 536, 742, 677, 742, 687, 284, 193, 517, 273, 494, 263, 147, 593, 800, 571, 320, 803, 133, 231, 390, 685, 330, 63, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, 539, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 6, 93, 862, 771, 453, b.l, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 287, b.m, TypedValues.PositionType.TYPE_SIZE_PERCENT, 733, 877, 381, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID, 723, 476, 462, 172, 430, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 858, 822, 543, 376, FrameMetricsAggregator.EVERY_DURATION, RemoteCommand.Response.STATUS_BAD_REQUEST, 672, 762, 283, 184, 440, 35, 519, 31, 460, 594, 225, 535, 517, 352, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 158, 651, ComposerKt.providerKey, 488, TypedValues.PositionType.TYPE_DRAWPATH, 648, 733, 717, 83, 404, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543, 521, 310, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, RemoteCommand.Response.STATUS_BAD_REQUEST, 925, 749, WalletConstants.ERROR_CODE_APP_LABEL_UNAVAILABLE, 822, 93, 217, 208, PDF417Common.MAX_CODEWORDS_IN_BARCODE, 244, 583, 620, 246, 148, 447, 631, 292, 908, 490, TypedValues.TransitionType.TYPE_AUTO_TRANSITION, 516, 258, 457, 907, 594, 723, 674, 292, 272, 96, 684, 432, 686, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, 860, 569, 193, 219, 129, 186, 236, 287, 192, 775, 278, 173, 40, 379, 712, 463, 646, 776, 171, 491, 297, 763, 156, 732, 95, 270, 447, 90, TypedValues.PositionType.TYPE_PERCENT_Y, 48, 228, 821, 808, 898, 784, 663, 627, 378, 382, 262, 380, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, 754, 336, 89, 614, 87, 432, 670, 616, 157, 374, 242, 726, 600, 269, 375, 898, 845, 454, 354, 130, 814, 587, 804, 34, 211, 330, 539, 297, 827, 865, 37, 517, 834, 315, 550, 86, 801, 4, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 539, 524, 894, 75, 766, 882, 857, 74, ComposerKt.providerMapsKey, 82, 586, 708, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, TypedValues.Custom.TYPE_DIMENSION, 786, 138, 720, 858, 194, 311, 913, 275, 190, 375, 850, 438, 733, 194, 280, ComposerKt.providerKey, 280, 828, 757, 710, 814, 919, 89, 68, 569, 11, ComposerKt.providerMapsKey, 796, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 540, 913, 801, TypedValues.TransitionType.TYPE_DURATION, 799, 137, 439, 418, 592, 668, 353, 859, 370, 694, 325, 240, 216, 257, 284, 549, 209, 884, 315, 70, 329, 793, 490, 274, 877, 162, 749, 812, 684, 461, 334, 376, 849, 521, 307, 291, 803, 712, 19, 358, 399, 908, b.i, FrameMetricsAggregator.EVERY_DURATION, 51, 8, 517, 225, 289, 470, 637, 731, 66, 255, 917, 269, 463, 830, 730, 433, 848, 585, 136, 538, TypedValues.Custom.TYPE_REFERENCE, 90, 2, 290, 743, 199, 655, TypedValues.Custom.TYPE_STRING, 329, 49, 802, 580, 355, 588, 188, 462, 10, 134, 628, 320, 479, 130, 739, 71, 263, TypedValues.AttributesType.TYPE_PIVOT_TARGET, 374, 601, 192, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 142, 673, 687, 234, 722, 384, 177, 752, TypedValues.MotionType.TYPE_PATHMOTION_ARC, 640, 455, 193, 689, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, 805, 641, 48, 60, 732, 621, 895, 544, 261, 852, 655, 309, 697, 755, 756, 60, 231, 773, 434, 421, 726, 528, TypedValues.PositionType.TYPE_PERCENT_WIDTH, 118, 49, 795, 32, 144, 500, 238, 836, 394, 280, 566, 319, 9, 647, 550, 73, 914, 342, WebSocketProtocol.PAYLOAD_SHORT, 32, 681, 331, 792, 620, 60, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 441, 180, 791, 893, 754, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 383, 228, 749, 760, 213, 54, 297, 134, 54, 834, 299, 922, 191, 910, 532, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 829, 189, 20, 167, 29, 872, 449, 83, 402, 41, 656, TypedValues.PositionType.TYPE_SIZE_PERCENT, 579, 481, 173, 404, 251, 688, 95, 497, 555, 642, 543, 307, 159, 924, 558, 648, 55, 497, 10, 352, 77, 373, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, 35, HttpStatusCodeRange.DEFAULT_MAX, 428, ComposerKt.reuseKey, WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR, 574, 118, 498, 285, 380, 350, 492, 197, 265, 920, 155, 914, 299, 229, 643, 294, 871, 306, 88, 87, 193, 352, 781, 846, 75, 327, 520, 435, 543, ComposerKt.providerValuesKey, 666, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, 408, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, 122, 272, 383, 800, 485, 98, 752, 472, 761, b.m, 784, 860, 658, 741, 290, ComposerKt.providerMapsKey, 681, 407, 855, 85, 99, 62, 482, 180, 20, 297, 451, 593, 913, 142, 808, 684, 287, 536, 561, 76, 653, 899, 729, 567, 744, 390, InputDeviceCompat.SOURCE_DPAD, 192, 516, 258, 240, 518, 794, 395, ViewUtils.EDGE_TO_EDGE_FLAGS, 848, 51, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 384, 168, 190, 826, 328, 596, 786, 303, 570, 381, WalletConstants.ERROR_CODE_APP_LABEL_UNAVAILABLE, 641, 156, 237, 151, 429, 531, ComposerKt.reuseKey, 676, 710, 89, 168, 304, 402, 40, 708, 575, 162, 864, 229, 65, 861, 841, 512, 164, 477, 221, 92, 358, 785, 288, 357, 850, 836, 827, 736, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, 94, 8, 494, 114, 521, 2, 499, 851, 543, 152, 729, 771, 95, 248, 361, 578, 323, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, TypedValues.Custom.TYPE_COLOR, 452, 167, 342, 244, 173, 35, 463, 651, 51, 699, 591, 452, 578, 37, 124, 298, 332, 552, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, 911, 283, 711, 472, TypedValues.CycleType.TYPE_EASING, 245, 288, 594, 394, FrameMetricsAggregator.EVERY_DURATION, 327, 589, 777, 699, 688, 43, 408, 842, 383, 721, 521, 560, 644, 714, 559, 62, 145, 873, 663, 713, 159, 672, 729, 624, 59, 193, 417, 158, 209, 563, 564, 343, 693, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, TypedValues.MotionType.TYPE_DRAW_PATH, 563, 365, 181, 772, 677, 310, 248, 353, 708, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, TypedValues.CycleType.TYPE_WAVE_OFFSET, 833, 77, 597, 346, 269, 757, 632, 695, 751, 331, 247, 184, 45, 787, 680, 18, 66, 407, 369, 54, 492, 228, 613, 830, 922, 437, 519, 644, TypedValues.Custom.TYPE_DIMENSION, 789, TypedValues.CycleType.TYPE_EASING, 305, 441, ComposerKt.reuseKey, 300, 892, 827, 141, 537, 381, 662, InputDeviceCompat.SOURCE_DPAD, 56, 252, 341, 242, 797, 838, 837, 720, 224, 307, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, TypedValues.CycleType.TYPE_WAVE_PHASE, 216, 548, 249, 321, 881, 699, 535, 673, 782, 210, 815, TypedValues.Custom.TYPE_DIMENSION, 303, 843, 922, 281, 73, 469, 791, 660, 162, 498, 308, 155, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 907, 817, 187, 62, 16, TypedValues.CycleType.TYPE_WAVE_PHASE, 535, 336, 286, 437, 375, 273, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 296, 183, 923, 116, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, 330, 5, 39, 923, 311, TypedValues.CycleType.TYPE_WAVE_OFFSET, 242, 749, 321, 54, 669, TypedValues.AttributesType.TYPE_PATH_ROTATE, 342, 299, 534, 105, 667, 488, 640, 672, 576, 540, TypedValues.AttributesType.TYPE_PATH_ROTATE, 486, 721, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 46, 656, 447, 171, 616, 464, 190, 531, 297, 321, 762, 752, 533, 175, 134, 14, 381, 433, 717, 45, b.o, 20, 596, 284, 736, 138, 646, WalletConstants.ERROR_CODE_AUTHENTICATION_FAILURE, 877, 669, 141, 919, 45, 780, 407, 164, 332, 899, 165, 726, 600, 325, 498, 655, 
    357, 752, ViewUtils.EDGE_TO_EDGE_FLAGS, 223, 849, 647, 63, 310, 863, 251, 366, 304, 282, 738, 675, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, 389, 244, 31, 121, 303, 263};
    private static final int[] CCA_VARIANTS = {5, 6, 7, 8, 9, 10, 12, 4, 5, 6, 7, 8, 3, 4, 5, 6, 7, 4, 4, 5, 5, 6, 6, 7, 4, 5, 6, 7, 7, 4, 5, 6, 7, 8, 0, 0, 4, 4, 9, 9, 15, 0, 4, 9, 15, 15, 0, 4, 9, 15, 22};
    private static final int[] A_RAP_TABLE = {39, 1, 32, 8, 14, 43, 20, 11, 1, 5, 15, 21, 40, 43, 46, 34, 29, 0, 0, 0, 0, 0, 0, 0, 43, 33, 37, 47, 1, 20, 23, 26, 14, 9, 19, 33, 12, 40, 46, 23, 52, 23, 13, 17, 27, 33, 52, 3, 6, 46, 41, 6, 0, 3, 3, 3, 0, 3, 3, 0, 3, 6, 6, 0, 0, 0, 0, 3};
    private static final String[] CODAGEMC = {"urA", "xfs", "ypy", "unk", "xdw", "yoz", "pDA", "uls", "pBk", "eBA", "pAs", "eAk", "prA", "uvs", "xhy", "pnk", "utw", "xgz", "fDA", "pls", "fBk", "frA", "pvs", "uxy", "fnk", "ptw", "uwz", "fls", "psy", "fvs", "pxy", "ftw", "pwz", "fxy", "yrx", "ufk", "xFw", "ymz", "onA", "uds", "xEy", "olk", "ucw", "dBA", "oks", "uci", "dAk", "okg", "dAc", "ovk", "uhw", "xaz", "dnA", "ots", "ugy", "dlk", "osw", "ugj", "dks", "osi", "dvk", "oxw", "uiz", "dts", "owy", "dsw", "owj", "dxw", "oyz", "dwy", "dwj", "ofA", "uFs", "xCy", "odk", "uEw", "xCj", "clA", "ocs", "uEi", "ckk", "ocg", "ckc", "ckE", "cvA", "ohs", "uay", "ctk", "ogw", "uaj", "css", "ogi", "csg", "csa", "cxs", "oiy", "cww", "oij", "cwi", "cyy", "oFk", "uCw", "xBj", "cdA", "oEs", "uCi", "cck", "oEg", "uCb", "ccc", "oEa", "ccE", "oED", "chk", "oaw", "uDj", "cgs", "oai", "cgg", "oab", "cga", "cgD", "obj", "cib", "cFA", "oCs", "uBi", "cEk", "oCg", "uBb", "cEc", "oCa", "cEE", "oCD", "cEC", "cas", "cag", "caa", "cCk", "uAr", "oBa", "oBD", "cCB", "tfk", "wpw", "yez", "mnA", "tds", "woy", "mlk", "tcw", "woj", "FBA", "mks", "FAk", "mvk", "thw", "wqz", "FnA", "mts", "tgy", "Flk", "msw", "Fks", "Fkg", "Fvk", "mxw", "tiz", "Fts", "mwy", "Fsw", "Fsi", "Fxw", "myz", "Fwy", "Fyz", "vfA", "xps", "yuy", "vdk", "xow", "yuj", "qlA", "vcs", "xoi", "qkk", "vcg", "xob", "qkc", "vca", "mfA", "tFs", "wmy", "qvA", "mdk", "tEw", "wmj", "qtk", "vgw", "xqj", "hlA", "Ekk", "mcg", "tEb", "hkk", "qsg", "hkc", "EvA", "mhs", "tay", "hvA", "Etk", "mgw", "taj", "htk", "qww", "vij", "hss", "Esg", "hsg", "Exs", "miy", "hxs", "Eww", "mij", "hww", "qyj", "hwi", "Eyy", "hyy", "Eyj", "hyj", "vFk", "xmw", "ytj", "qdA", "vEs", "xmi", "qck", "vEg", "xmb", "qcc", "vEa", "qcE", "qcC", "mFk", "tCw", "wlj", "qhk", "mEs", "tCi", "gtA", "Eck", "vai", "tCb", "gsk", "Ecc", "mEa", "gsc", "qga", "mED", "EcC", "Ehk", "maw", "tDj", "gxk", "Egs", "mai", "gws", "qii", "mab", "gwg", "Ega", "EgD", "Eiw", "mbj", "gyw", "Eii", "gyi", "Eib", "gyb", "gzj", "qFA", "vCs", "xli", "qEk", "vCg", "xlb", "qEc", "vCa", "qEE", "vCD", "qEC", "qEB", "EFA", "mCs", "tBi", "ghA", "EEk", "mCg", "tBb", "ggk", "qag", "vDb", "ggc", "EEE", "mCD", "ggE", "qaD", "ggC", "Eas", "mDi", "gis", "Eag", "mDb", "gig", "qbb", "gia", "EaD", "giD", "gji", "gjb", "qCk", "vBg", "xkr", "qCc", "vBa", "qCE", "vBD", "qCC", "qCB", "ECk", "mBg", "tAr", "gak", "ECc", "mBa", "gac", "qDa", "mBD", "gaE", "ECC", "gaC", "ECB", "EDg", "gbg", "gba", "gbD", "vAq", "vAn", "qBB", "mAq", "EBE", "gDE", "gDC", "gDB", "lfA", "sps", "wey", "ldk", "sow", "ClA", "lcs", "soi", "Ckk", "lcg", "Ckc", "CkE", "CvA", "lhs", "sqy", "Ctk", "lgw", "sqj", "Css", "lgi", "Csg", "Csa", "Cxs", "liy", "Cww", "lij", "Cwi", "Cyy", "Cyj", "tpk", "wuw", "yhj", "ndA", "tos", "wui", "nck", "tog", "wub", "ncc", "toa", "ncE", "toD", "lFk", "smw", "wdj", "nhk", "lEs", "smi", "atA", "Cck", "tqi", "smb", "ask", "ngg", "lEa", "asc", "CcE", "asE", "Chk", "law", "snj", "axk", "Cgs", "trj", "aws", "nii", "lab", "awg", "Cga", "awa", "Ciw", "lbj", "ayw", "Cii", "ayi", "Cib", "Cjj", "azj", "vpA", "xus", "yxi", "vok", "xug", "yxb", "voc", "xua", "voE", "xuD", "voC", "nFA", g.e, "wti", "rhA", "nEk", "xvi", "wtb", "rgk", "vqg", "xvb", "rgc", "nEE", "tmD", "rgE", "vqD", "nEB", "CFA", "lCs", "sli", "ahA", "CEk", "lCg", "slb", "ixA", "agk", "nag", "tnb", "iwk", "rig", "vrb", "lCD", "iwc", "agE", "naD", "iwE", "CEB", "Cas", "lDi", "ais", "Cag", "lDb", "iys", "aig", "nbb", "iyg", "rjb", "CaD", "aiD", "Cbi", "aji", "Cbb", "izi", "ajb", "vmk", "xtg", "ywr", "vmc", "xta", "vmE", "xtD", "vmC", "vmB", "nCk", "tlg", "wsr", "rak", "nCc", "xtr", "rac", "vna", "tlD", "raE", "nCC", "raC", "nCB", "raB", "CCk", "lBg", "skr", "aak", "CCc", "lBa", "iik", "aac", "nDa", "lBD", "iic", "rba", "CCC", "iiE", "aaC", "CCB", "aaB", "CDg", "lBr", "abg", "CDa", "ijg", "aba", "CDD", "ija", "abD", "CDr", "ijr", "vlc", "xsq", "vlE", "xsn", "vlC", "vlB", "nBc", "tkq", "rDc", "nBE", "tkn", "rDE", "vln", "rDC", "nBB", "rDB", "CBc", "lAq", "aDc", "CBE", "lAn", "ibc", "aDE", "nBn", "ibE", "rDn", "CBB", "ibC", "aDB", "ibB", "aDq", "ibq", "ibn", "xsf", "vkl", "tkf", "nAm", "nAl", "CAo", "aBo", "iDo", "CAl", "aBl", "kpk", "BdA", "kos", "Bck", "kog", "seb", "Bcc", "koa", "BcE", "koD", "Bhk", "kqw", "sfj", "Bgs", "kqi", "Bgg", "kqb", "Bga", "BgD", "Biw", "krj", "Bii", "Bib", "Bjj", "lpA", "sus", "whi", "lok", "sug", "loc", "sua", "loE", "suD", "loC", "BFA", "kms", "sdi", "DhA", "BEk", "svi", "sdb", "Dgk", "lqg", "svb", "Dgc", "BEE", "kmD", "DgE", "lqD", "BEB", "Bas", "kni", "Dis", "Bag", "knb", "Dig", "lrb", "Dia", "BaD", "Bbi", "Dji", "Bbb", "Djb", "tuk", "wxg", "yir", "tuc", "wxa", "tuE", "wxD", "tuC", "tuB", "lmk", "stg", "nqk", "lmc", "sta", "nqc", "tva", "stD", "nqE", "lmC", "nqC", "lmB", "nqB", "BCk", "klg", "Dak", "BCc", "str", "bik", "Dac", "lna", "klD", "bic", "nra", "BCC", "biE", "DaC", "BCB", "DaB", "BDg", "klr", "Dbg", "BDa", "bjg", "Dba", "BDD", "bja", "DbD", "BDr", "Dbr", "bjr", "xxc", "yyq", "xxE", "yyn", "xxC", "xxB", "ttc", "wwq", "vvc", "xxq", "wwn", "vvE", "xxn", "vvC", "ttB", "vvB", "llc", "ssq", "nnc", "llE", "ssn", "rrc", "nnE", "ttn", "rrE", "vvn", "llB", "rrC", "nnB", "rrB", "BBc", "kkq", "DDc", "BBE", "kkn", "bbc", "DDE", "lln", "jjc", "bbE", "nnn", "BBB", "jjE", "rrn", "DDB", "jjC", "BBq", "DDq", "BBn", "bbq", "DDn", "jjq", "bbn", "jjn", "xwo", "yyf", "xwm", "xwl", "tso", "wwf", "vto", "xwv", "vtm", "tsl", "vtl", "lko", "ssf", "nlo", "lkm", "rno", "nlm", "lkl", "rnm", "nll", "rnl", "BAo", "kkf", "DBo", "lkv", "bDo", "DBm", "BAl", "jbo", "bDm", "DBl", "jbm", "bDl", "jbl", "DBv", "jbv", "xwd", "vsu", "vst", "nku", "rlu", "rlt", "DAu", "bBu", "jDu", "jDt", "ApA", "Aok", "keg", "Aoc", "AoE", "AoC", "Aqs", "Aqg", "Aqa", "AqD", "Ari", "Arb", "kuk", "kuc", "sha", "kuE", "shD", "kuC", "kuB", "Amk", "kdg", "Bqk", "kvg", "kda", "Bqc", "kva", "BqE", "kvD", "BqC", "AmB", "BqB", "Ang", "kdr", "Brg", "kvr", "Bra", "AnD", "BrD", "Anr", "Brr", "sxc", "sxE", "sxC", "sxB", "ktc", "lvc", "sxq", "sgn", "lvE", "sxn", "lvC", "ktB", "lvB", "Alc", "Bnc", "AlE", "kcn", "Drc", "BnE", "AlC", "DrE", "BnC", "AlB", "DrC", "BnB", "Alq", "Bnq", "Aln", "Drq", "Bnn", "Drn", "wyo", "wym", "wyl", "swo", "txo", "wyv", "txm", "swl", "txl", "kso", "sgf", "lto", "swv", "nvo", "ltm", "ksl", "nvm", "ltl", "nvl", "Ako", "kcf", "Blo", "ksv", "Dno", "Blm", "Akl", "bro", "Dnm", "Bll", "brm", "Dnl", "Akv", "Blv", "Dnv", "brv", "yze", "yzd", "wye", "xyu", "wyd", "xyt", "swe", "twu", "swd", "vxu", "twt", "vxt", "kse", "lsu", "ksd", "ntu", "lst", "rvu", "ypk", "zew", "xdA", "yos", "zei", "xck", "yog", "zeb", "xcc", "yoa", "xcE", "yoD", "xcC", "xhk", "yqw", "zfj", "utA", "xgs", "yqi", "usk", "xgg", "yqb", "usc", "xga", "usE", "xgD", "usC", "uxk", "xiw", "yrj", "ptA", "uws", "xii", "psk", "uwg", "xib", "psc", "uwa", "psE", "uwD", "psC", "pxk", "uyw", "xjj", "ftA", "pws", "uyi", "fsk", "pwg", "uyb", "fsc", "pwa", "fsE", "pwD", "fxk", "pyw", "uzj", "fws", "pyi", "fwg", "pyb", "fwa", "fyw", "pzj", "fyi", "fyb", "xFA", "yms", "zdi", "xEk", "ymg", 
    "zdb", "xEc", "yma", "xEE", "ymD", "xEC", "xEB", "uhA", "xas", "yni", "ugk", "xag", "ynb", "ugc", "xaa", "ugE", "xaD", "ugC", "ugB", "oxA", "uis", "xbi", "owk", "uig", "xbb", "owc", "uia", "owE", "uiD", "owC", "owB", "dxA", "oys", "uji", "dwk", "oyg", "ujb", "dwc", "oya", "dwE", "oyD", "dwC", "dys", "ozi", "dyg", "ozb", "dya", "dyD", "dzi", "dzb", "xCk", "ylg", "zcr", "xCc", "yla", "xCE", "ylD", "xCC", "xCB", "uak", "xDg", "ylr", "uac", "xDa", "uaE", "xDD", "uaC", "uaB", "oik", "ubg", "xDr", "oic", "uba", "oiE", "ubD", "oiC", "oiB", "cyk", "ojg", "ubr", "cyc", "oja", "cyE", "ojD", "cyC", "cyB", "czg", "ojr", "cza", "czD", "czr", "xBc", "ykq", "xBE", "ykn", "xBC", "xBB", "uDc", "xBq", "uDE", "xBn", "uDC", "uDB", "obc", "uDq", "obE", "uDn", "obC", "obB", "cjc", "obq", "cjE", "obn", "cjC", "cjB", "cjq", "cjn", "xAo", "ykf", "xAm", "xAl", "uBo", "xAv", "uBm", "uBl", "oDo", "uBv", "oDm", "oDl", "cbo", "oDv", "cbm", "cbl", "xAe", "xAd", "uAu", "uAt", "oBu", "oBt", "wpA", BooleanUtils.YES, "zFi", "wok", "yeg", "zFb", "woc", "yea", "woE", "yeD", "woC", "woB", "thA", "wqs", "yfi", "tgk", "wqg", "yfb", "tgc", "wqa", "tgE", "wqD", "tgC", "tgB", "mxA", "tis", "wri", "mwk", "tig", "wrb", "mwc", "tia", "mwE", "tiD", "mwC", "mwB", "FxA", "mys", "tji", "Fwk", "myg", "tjb", "Fwc", "mya", "FwE", "myD", "FwC", "Fys", "mzi", "Fyg", "mzb", "Fya", "FyD", "Fzi", "Fzb", "yuk", "zhg", "hjs", "yuc", "zha", "hbw", "yuE", "zhD", "hDy", "yuC", "yuB", "wmk", "ydg", "zEr", "xqk", "wmc", "zhr", "xqc", "yva", "ydD", "xqE", "wmC", "xqC", "wmB", "xqB", "tak", "wng", "ydr", "vik", "tac", "wna", "vic", "xra", "wnD", "viE", "taC", "viC", "taB", "viB", "mik", "tbg", "wnr", "qyk", "mic", "tba", "qyc", "vja", "tbD", "qyE", "miC", "qyC", "miB", "qyB", "Eyk", "mjg", "tbr", "hyk", "Eyc", "mja", "hyc", "qza", "mjD", "hyE", "EyC", "hyC", "EyB", "Ezg", "mjr", "hzg", "Eza", "hza", "EzD", "hzD", "Ezr", "ytc", "zgq", "grw", "ytE", "zgn", "gny", "ytC", "glz", "ytB", "wlc", "ycq", "xnc", "wlE", "ycn", "xnE", "ytn", "xnC", "wlB", "xnB", "tDc", "wlq", "vbc", "tDE", "wln", "vbE", "xnn", "vbC", "tDB", "vbB", "mbc", "tDq", "qjc", "mbE", "tDn", "qjE", "vbn", "qjC", "mbB", "qjB", "Ejc", "mbq", "gzc", "EjE", "mbn", "gzE", "qjn", "gzC", "EjB", "gzB", "Ejq", "gzq", "Ejn", "gzn", "yso", "zgf", "gfy", "ysm", "gdz", "ysl", "wko", "ycf", "xlo", "ysv", "xlm", "wkl", "xll", "tBo", "wkv", "vDo", "tBm", "vDm", "tBl", "vDl", "mDo", "tBv", "qbo", "vDv", "qbm", "mDl", "qbl", "Ebo", "mDv", "gjo", "Ebm", "gjm", "Ebl", "gjl", "Ebv", "gjv", "yse", "gFz", "ysd", "wke", "xku", "wkd", "xkt", "tAu", "vBu", "tAt", "vBt", "mBu", "qDu", "mBt", "qDt", "EDu", "gbu", "EDt", "gbt", "ysF", "wkF", "xkh", "tAh", "vAx", "mAx", "qBx", "wek", "yFg", "zCr", "wec", "yFa", "weE", "yFD", "weC", "weB", "sqk", "wfg", "yFr", "sqc", "wfa", "sqE", "wfD", "sqC", "sqB", "lik", "srg", "wfr", g.b, "sra", "liE", "srD", "liC", "liB", "Cyk", "ljg", "srr", "Cyc", "lja", "CyE", "ljD", "CyC", "CyB", "Czg", "ljr", "Cza", "CzD", "Czr", "yhc", "zaq", "arw", "yhE", "zan", "any", "yhC", "alz", "yhB", "wdc", "yEq", "wvc", "wdE", "yEn", "wvE", "yhn", "wvC", "wdB", "wvB", "snc", "wdq", "trc", "snE", "wdn", "trE", "wvn", "trC", "snB", "trB", "lbc", "snq", "njc", "lbE", "snn", "njE", "trn", "njC", "lbB", "njB", "Cjc", "lbq", "azc", "CjE", "lbn", "azE", "njn", "azC", "CjB", "azB", "Cjq", "azq", "Cjn", "azn", "zio", "irs", "rfy", "zim", "inw", "rdz", "zil", "ily", "ikz", "ygo", "zaf", "afy", "yxo", "ziv", "ivy", "adz", "yxm", "ygl", "itz", "yxl", "wco", "yEf", "wto", "wcm", "xvo", "yxv", "wcl", "xvm", "wtl", "xvl", "slo", "wcv", "tno", "slm", "vro", "tnm", "sll", "vrm", "tnl", "vrl", "lDo", "slv", "nbo", "lDm", "rjo", "nbm", "lDl", "rjm", "nbl", "rjl", "Cbo", "lDv", "ajo", "Cbm", "izo", "ajm", "Cbl", "izm", "ajl", "izl", "Cbv", "ajv", "zie", "ifw", "rFz", "zid", "idy", "icz", "yge", "aFz", "ywu", "ygd", "ihz", "ywt", "wce", "wsu", "wcd", "xtu", "wst", "xtt", "sku", "tlu", "skt", "vnu", "tlt", "vnt", "lBu", "nDu", "lBt", "rbu", "nDt", "rbt", "CDu", "abu", "CDt", "iju", "abt", "ijt", "ziF", "iFy", "iEz", "ygF", "ywh", "wcF", "wsh", "xsx", "skh", "tkx", "vlx", "lAx", "nBx", "rDx", "CBx", "aDx", "ibx", "iCz", "wFc", "yCq", "wFE", "yCn", "wFC", "wFB", "sfc", "wFq", "sfE", "wFn", "sfC", "sfB", "krc", "sfq", "krE", "sfn", "krC", "krB", "Bjc", "krq", "BjE", "krn", "BjC", "BjB", "Bjq", "Bjn", "yao", "zDf", "Dfy", "yam", "Ddz", "yal", "wEo", "yCf", "who", "wEm", "whm", "wEl", "whl", "sdo", "wEv", "svo", "sdm", "svm", "sdl", "svl", "kno", "sdv", "lro", "knm", "lrm", "knl", "lrl", "Bbo", "knv", "Djo", "Bbm", "Djm", "Bbl", "Djl", "Bbv", "Djv", "zbe", "bfw", "npz", "zbd", "bdy", "bcz", "yae", "DFz", "yiu", "yad", "bhz", "yit", "wEe", "wgu", "wEd", "wxu", "wgt", "wxt", "scu", "stu", "sct", "tvu", "stt", "tvt", "klu", "lnu", "klt", "nru", "lnt", "nrt", "BDu", "Dbu", "BDt", "bju", "Dbt", "bjt", "jfs", "rpy", "jdw", "roz", "jcy", "jcj", "zbF", "bFy", "zjh", "jhy", "bEz", "jgz", "yaF", "yih", "yyx", "wEF", "wgh", "wwx", "xxx", "sch", "ssx", "ttx", "vvx", "kkx", "llx", "nnx", "rrx", "BBx", "DDx", "bbx", "jFw", "rmz", "jEy", "jEj", "bCz", "jaz", "jCy", "jCj", "jBj", "wCo", "wCm", "wCl", "sFo", "wCv", "sFm", "sFl", "kfo", "sFv", "kfm", "kfl", "Aro", "kfv", "Arm", "Arl", "Arv", "yDe", "Bpz", "yDd", "wCe", "wau", "wCd", "wat", "sEu", "shu", "sEt", "sht", "kdu", "kvu", "kdt", "kvt", "Anu", "Bru", "Ant", "Brt", "zDp", "Dpy", "Doz", "yDF", "ybh", "wCF", "wah", "wix", "sEh", "sgx", "sxx", "kcx", "ktx", "lvx", "Alx", "Bnx", "Drx", "bpw", "nuz", "boy", "boj", "Dmz", "bqz", "jps", "ruy", "jow", "ruj", "joi", "job", "bmy", "jqy", "bmj", "jqj", "jmw", "rtj", "jmi", "jmb", "blj", "jnj", "jli", "jlb", "jkr", "sCu", "sCt", "kFu", "kFt", "Afu", "Aft", "wDh", "sCh", "sax", "kEx", "khx", "Adx", "Avx", "Buz", "Duy", "Duj", "buw", "nxj", "bui", "bub", "Dtj", "bvj", "jus", "rxi", "jug", "rxb", "jua", "juD", "bti", "jvi", "btb", "jvb", "jtg", "rwr", "jta", "jtD", "bsr", "jtr", "jsq", "jsn", "Bxj", "Dxi", "Dxb", "bxg", "nyr", "bxa", "bxD", "Dwr", "bxr", "bwq", "bwn", "pjk", "urw", "ejA", "pbs", "uny", "ebk", "pDw", "ulz", "eDs", "pBy", "eBw", "zfc", "fjk", "prw", "zfE", "fbs", "pny", "zfC", "fDw", "plz", "zfB", "fBy", "yrc", "zfq", "frw", "yrE", "zfn", "fny", "yrC", "flz", "yrB", "xjc", "yrq", "xjE", "yrn", "xjC", "xjB", "uzc", "xjq", "uzE", "xjn", "uzC", "uzB", "pzc", "uzq", "pzE", "uzn", "pzC", "djA", "ors", "ufy", "dbk", "onw", "udz", "dDs", "oly", "dBw", "okz", "dAy", "zdo", "drs", "ovy", "zdm", "dnw", "otz", "zdl", "dly", "dkz", "yno", "zdv", "dvy", "ynm", "dtz", "ynl", "xbo", "ynv", "xbm", "xbl", "ujo", "xbv", "ujm", "ujl", "ozo", "ujv", "ozm", "ozl", "crk", "ofw", "uFz", "cns", "ody", "clw", "ocz", "cky", "ckj", "zcu", "cvw", "ohz", "zct", "cty", "csz", "ylu", "cxz", "ylt", "xDu", "xDt", "ubu", "ubt", "oju", "ojt", "cfs", "oFy", "cdw", "oEz", "ccy", "ccj", "zch", "chy", "cgz", "ykx", "xBx", "uDx", "cFw", "oCz", "cEy", "cEj", "caz", "cCy", "cCj", "FjA", "mrs", "tfy", "Fbk", "mnw", "tdz", "FDs", "mly", "FBw", "mkz", "FAy", "zFo", "Frs", 
    "mvy", "zFm", "Fnw", "mtz", "zFl", "Fly", "Fkz", "yfo", "zFv", "Fvy", "yfm", "Ftz", "yfl", "wro", "yfv", "wrm", "wrl", "tjo", "wrv", "tjm", "tjl", "mzo", "tjv", "mzm", "mzl", "qrk", "vfw", "xpz", "hbA", "qns", "vdy", "hDk", "qlw", "vcz", "hBs", "qky", "hAw", "qkj", "hAi", "Erk", "mfw", "tFz", "hrk", "Ens", "mdy", "hns", "qty", "mcz", "hlw", "Eky", "hky", "Ekj", "hkj", "zEu", "Evw", "mhz", "zhu", "zEt", "hvw", "Ety", "zht", "hty", "Esz", "hsz", "ydu", "Exz", "yvu", "ydt", "hxz", "yvt", "wnu", "xru", "wnt", "xrt", "tbu", "vju", "tbt", "vjt", "mju", "mjt", "grA", "qfs", "vFy", "gnk", "qdw", "vEz", "gls", "qcy", "gkw", "qcj", "gki", "gkb", "Efs", "mFy", "gvs", "Edw", "mEz", "gtw", "qgz", "gsy", "Ecj", "gsj", "zEh", "Ehy", "zgx", "gxy", "Egz", "gwz", "ycx", "ytx", "wlx", "xnx", "tDx", "vbx", "mbx", "gfk", "qFw", "vCz", "gds", "qEy", "gcw", "qEj", "gci", "gcb", "EFw", "mCz", "ghw", "EEy", "ggy", "EEj", "ggj", "Eaz", "giz", "gFs", "qCy", "gEw", "qCj", "gEi", "gEb", "ECy", "gay", "ECj", "gaj", "gCw", "qBj", "gCi", "gCb", "EBj", "gDj", "gBi", "gBb", "Crk", "lfw", "spz", "Cns", "ldy", "Clw", "lcz", "Cky", "Ckj", "zCu", "Cvw", "lhz", "zCt", "Cty", "Csz", "yFu", "Cxz", "yFt", "wfu", "wft", "sru", "srt", "lju", "ljt", "arA", "nfs", "tpy", "ank", "ndw", "toz", "als", "ncy", "akw", "ncj", "aki", "akb", "Cfs", "lFy", "avs", "Cdw", "lEz", "atw", "ngz", "asy", "Ccj", "asj", "zCh", "Chy", "zax", "axy", "Cgz", "awz", "yEx", "yhx", "wdx", "wvx", "snx", "trx", "lbx", "rfk", "vpw", "xuz", "inA", "rds", "voy", "ilk", "rcw", "voj", "iks", "rci", "ikg", "rcb", "ika", "afk", "nFw", "tmz", "ivk", "ads", "nEy", "its", "rgy", "nEj", "isw", "aci", "isi", "acb", "isb", "CFw", "lCz", "ahw", "CEy", "ixw", "agy", "CEj", "iwy", "agj", "iwj", "Caz", "aiz", "iyz", "ifA", "rFs", "vmy", "idk", "rEw", "vmj", "ics", "rEi", "icg", "rEb", "ica", "icD", "aFs", "nCy", "ihs", "aEw", "nCj", "igw", "raj", "igi", "aEb", "igb", "CCy", "aay", "CCj", "iiy", "aaj", "iij", "iFk", "rCw", "vlj", "iEs", "rCi", "iEg", "rCb", "iEa", "iED", "aCw", "nBj", "iaw", "aCi", "iai", "aCb", "iab", "CBj", "aDj", "ibj", "iCs", "rBi", "iCg", "rBb", "iCa", "iCD", "aBi", "iDi", "aBb", "iDb", "iBg", "rAr", "iBa", "iBD", "aAr", "iBr", "iAq", "iAn", "Bfs", "kpy", "Bdw", "koz", "Bcy", "Bcj", "Bhy", "Bgz", "yCx", "wFx", "sfx", "krx", "Dfk", "lpw", "suz", "Dds", "loy", "Dcw", "loj", "Dci", "Dcb", "BFw", "kmz", "Dhw", "BEy", "Dgy", "BEj", "Dgj", "Baz", "Diz", "bfA", "nps", "tuy", "bdk", "now", "tuj", "bcs", "noi", "bcg", "nob", "bca", "bcD", "DFs", "lmy", "bhs", "DEw", "lmj", "bgw", "DEi", "bgi", "DEb", "bgb", "BCy", "Day", "BCj", "biy", "Daj", "bij", "rpk", "vuw", "xxj", "jdA", "ros", "vui", "jck", "rog", "vub", "jcc", "roa", "jcE", "roD", "jcC", "bFk", "nmw", "ttj", "jhk", "bEs", "nmi", "jgs", "rqi", "nmb", "jgg", "bEa", "jga", "bED", "jgD", "DCw", "llj", "baw", "DCi", "jiw", "bai", "DCb", "jii", "bab", "jib", "BBj", "DDj", "bbj", "jjj", "jFA", "rms", "vti", "jEk", "rmg", "vtb", "jEc", "rma", "jEE", "rmD", "jEC", "jEB", "bCs", "nli", "jas", "bCg", "nlb", "jag", "rnb", "jaa", "bCD", "jaD", "DBi", "bDi", "DBb", "jbi", "bDb", "jbb", "jCk", "rlg", "vsr", "jCc", "rla", "jCE", "rlD", "jCC", "jCB", "bBg", "nkr", "jDg", "bBa", "jDa", "bBD", "jDD", "DAr", "bBr", "jDr", "jBc", "rkq", "jBE", "rkn", "jBC", "jBB", "bAq", "jBq", "bAn", "jBn", "jAo", "rkf", "jAm", "jAl", "bAf", "jAv", "Apw", "kez", "Aoy", "Aoj", "Aqz", "Bps", "kuy", "Bow", "kuj", "Boi", "Bob", "Amy", "Bqy", "Amj", "Bqj", "Dpk", "luw", "sxj", "Dos", "lui", "Dog", "lub", "Doa", "DoD", "Bmw", "ktj", "Dqw", "Bmi", "Dqi", "Bmb", "Dqb", "Alj", "Bnj", "Drj", "bpA", "nus", "txi", "bok", "nug", "txb", "boc", "nua", "boE", "nuD", "boC", "boB", "Dms", "lti", "bqs", "Dmg", "ltb", "bqg", "nvb", "bqa", "DmD", "bqD", "Bli", "Dni", "Blb", "bri", "Dnb", "brb", "ruk", "vxg", "xyr", "ruc", "vxa", "ruE", "vxD", "ruC", "ruB", "bmk", "ntg", "twr", "jqk", "bmc", "nta", "jqc", "rva", "ntD", "jqE", "bmC", "jqC", "bmB", "jqB", "Dlg", "lsr", "bng", "Dla", "jrg", "bna", "DlD", "jra", "bnD", "jrD", "Bkr", "Dlr", "bnr", "jrr", "rtc", "vwq", "rtE", "vwn", "rtC", "rtB", "blc", "nsq", "jnc", "blE", "nsn", "jnE", "rtn", "jnC", "blB", "jnB", "Dkq", "blq", "Dkn", "jnq", "bln", "jnn", "rso", "vwf", "rsm", "rsl", "bko", "nsf", "jlo", "bkm", "jlm", "bkl", "jll", "Dkf", "bkv", "jlv", "rse", "rsd", "bke", "jku", "bkd", "jkt", "Aey", "Aej", "Auw", "khj", "Aui", "Aub", "Adj", "Avj", "Bus", "kxi", "Bug", "kxb", "Bua", "BuD", "Ati", "Bvi", "Atb", "Bvb", "Duk", "lxg", "syr", "Duc", "lxa", "DuE", "lxD", "DuC", "DuB", "Btg", "kwr", "Dvg", "lxr", "Dva", "BtD", "DvD", "Asr", "Btr", "Dvr", "nxc", "tyq", "nxE", "tyn", "nxC", "nxB", "Dtc", "lwq", "bvc", "nxq", "lwn", "bvE", "DtC", "bvC", "DtB", "bvB", "Bsq", "Dtq", "Bsn", "bvq", "Dtn", "bvn", "vyo", "xzf", "vym", "vyl", "nwo", "tyf", "rxo", "nwm", "rxm", "nwl", "rxl", "Dso", "lwf", "bto", "Dsm", "jvo", "btm", "Dsl", DebugImage.JVM, "btl", "jvl", "Bsf", "Dsv", "btv", "jvv", "vye", "vyd", "nwe", "rwu", "nwd", "rwt", "Dse", "bsu", "Dsd", "jtu", "bst", "jtt", "vyF", "nwF", "rwh", "DsF", "bsh", "jsx", "Ahi", "Ahb", "Axg", "kir", "Axa", "AxD", "Agr", "Axr", "Bxc", "kyq", "BxE", "kyn", "BxC", "BxB", "Awq", "Bxq", "Awn", "Bxn", "lyo", "szf", "lym", "lyl", "Bwo", "kyf", "Dxo", "lyv", "Dxm", "Bwl", "Dxl", "Awf", "Bwv", "Dxv", "tze", "tzd", "lye", "nyu", "lyd", "nyt", "Bwe", "Dwu", "Bwd", "bxu", "Dwt", "bxt", "tzF", "lyF", "nyh", "BwF", "Dwh", "bwx", "Aiq", "Ain", "Ayo", "kjf", "Aym", "Ayl", "Aif", "Ayv", "kze", "kzd", "Aye", "Byu", "Ayd", "Byt", "szp"};
    private static final char[] BR_SET = {'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '*', '+', '-'};
    private static final String[] PDF_TTF = {"00000", "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010", "01011", "01100", "01101", "01110", "01111", "10000", "10001", "10010", "10011", "10100", "10101", "10110", "10111", "11000", "11001", "11010", "11011", "11100", "11101", "11110", "11111", "01", "1111111101010100", "11111101000101001"};
    private static final String[] RAPLR = {"", "221311", "311311", "312211", "222211", "213211", "214111", "223111", "313111", "322111", "412111", "421111", "331111", "241111", "232111", "231211", "321211", "411211", "411121", "411112", "321112", "312112", "311212", "311221", "311131", "311122", "311113", "221113", "221122", "221131", "221221", "222121", "312121", "321121", "231121", "231112", "222112", "213112", "212212", "212221", "212131", "212122", "212113", "211213", "211123", "211132", "211141", "211231", "211222", "211312", "211321", "211411", "212311"};
    private static final String[] RAPC = {"", "112231", "121231", "122131", "131131", "131221", "132121", "141121", "141211", "142111", "133111", "132211", "131311", "122311", "123211", "124111", "115111", "114211", "114121", "123121", "123112", "122212", "122221", "121321", "121411", "112411", "113311", "113221", "113212", "113122", "122122", "131122", "131113", "122113", "113113", "112213", "112222", "112312", "112321", "111421", "111331", "111322", "111232", "111223", "111133", "111124", "111214", "112114", "121114", "121123", "121132", "112132", "112141"};
    private static final int[] MICRO_VARIANTS = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 11, 14, 17, 20, 24, 28, 8, 11, 14, 17, 20, 23, 26, 6, 8, 10, 12, 15, 20, 26, 32, 38, 44, 4, 6, 8, 10, 12, 15, 20, 26, 32, 38, 44, 7, 7, 7, 8, 8, 8, 8, 9, 9, 10, 11, 13, 15, 12, 14, 16, 18, 21, 26, 32, 38, 44, 50, 8, 12, 14, 16, 18, 21, 26, 32, 38, 44, 50, 0, 0, 0, 7, 7, 7, 7, 15, 15, 24, 34, 57, 84, 45, 70, 99, 115, 133, 154, 180, 212, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 294, 7, 45, 70, 99, 115, 133, 154, 180, 212, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 294};
    private static final int[] MICROCOEFFS = {76, 925, 537, 597, 784, 691, 437, 237, 308, 436, 284, 646, 653, 428, 379, 567, 527, 622, 257, 289, 362, TypedValues.PositionType.TYPE_TRANSITION_EASING, 441, 205, 377, 457, 64, 244, 826, 841, 818, 691, 266, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID, 462, 45, 565, 708, 825, 213, 15, 68, 327, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, TypedValues.Custom.TYPE_BOOLEAN, 597, 864, 757, ComposerKt.providerKey, 646, 684, 347, 127, 388, 7, 69, 851, 764, 713, 342, 384, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, 583, 322, 592, 678, ComposerKt.providerMapsKey, 184, 394, 692, 669, 677, 154, 187, 241, 286, 274, 354, 478, 915, 691, 833, 105, 215, 460, 829, 476, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, TypedValues.Custom.TYPE_BOOLEAN, 664, 230, 5, 80, 74, 550, 575, 147, 868, 642, 274, 562, 232, 755, HttpStatusCodeRange.DEFAULT_MAX, 524, 801, 132, 295, 116, 442, 428, 295, 42, 176, 65, 279, 577, 315, 624, 37, 855, 275, 739, 120, 297, 312, ComposerKt.compositionLocalMapKey, 560, 321, 233, 756, 760, 573, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 519, 781, 534, 129, TypedValues.CycleType.TYPE_WAVE_PHASE, 681, 553, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 716, 763, 693, 624, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 310, 691, 347, 165, 193, 259, 568, 443, 284, 887, 544, 788, 93, 477, 760, 331, TypedValues.MotionType.TYPE_DRAW_PATH, 269, 121, 159, 830, 446, 893, 699, 245, 441, 454, 325, 858, 131, 847, 764, 169, 361, 575, 922, 525, 176, 586, 640, 321, 536, 742, 677, 742, 687, 284, 193, 517, 273, 494, 263, 147, 593, 800, 571, 320, 803, 133, 231, 390, 685, 330, 63, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, 234, 228, 438, 848, 133, 703, 529, 721, 788, 322, 280, 159, 738, 586, 388, 684, 445, 680, 245, 595, 614, 233, 812, 32, 284, 658, 745, 229, 95, 689, 920, 771, 554, 289, 231, 125, 117, 518, 476, 36, 659, 848, 678, 64, 764, 840, 157, 915, 470, 876, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 25, 632, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, 417, 436, 714, 60, 376, 97, WalletConstants.ERROR_CODE_UNKNOWN, TypedValues.TransitionType.TYPE_STAGGERED, 446, 21, 3, 773, 569, 267, 272, 213, 31, 560, 231, 758, b.i, 271, 572, 436, 339, 730, 82, 285, 923, 797, 576, 875, 156, TypedValues.TransitionType.TYPE_STAGGERED, 63, 81, 257, 874, WalletConstants.ERROR_CODE_AUTHENTICATION_FAILURE, 416, 778, 50, 205, 303, 188, 535, 909, 155, 637, 230, 534, 96, 575, 102, 264, 233, 919, 593, 865, 26, 579, 623, 766, 146, 10, 739, 246, 127, 71, 244, 211, 477, 920, 876, 427, 820, 718, 435};
    private static final int[] RAP_TABLE = {1, 8, 36, 19, 9, 25, 1, 1, 8, 36, 19, 9, 27, 1, 7, 15, 25, 37, 1, 1, 21, 15, 1, 47, 1, 7, 15, 25, 37, 1, 1, 21, 15, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 7, 15, 25, 37, 17, 9, 29, 31, 25, 19, 1, 7, 15, 25, 37, 17, 9, 29, 31, 25, 9, 8, 36, 19, 17, 33, 1, 9, 8, 36, 19, 17, 35, 1, 7, 15, 25, 37, 33, 17, 37, 47, 49, 43, 1, 7, 15, 25, 37, 33, 17, 37, 47, 49, 0, 3, 6, 0, 6, 0, 0, 0, 3, 6, 0, 6, 6, 0, 0, 6, 0, 0, 0, 0, 6, 6, 0, 3, 0, 0, 6, 0, 0, 0, 0, 6, 6, 0};
    private LinearEncoding symbology = LinearEncoding.CODE_128;
    private int[][] pwr928 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 69, 7);
    private int[] codeWords = new int[180];
    private int[] bitStr = new int[13];
    private CompositeMode userPreferredMode = CompositeMode.CC_A;
    private int separatorHeight = 1;

    public enum CompositeMode {
        CC_A,
        CC_B,
        CC_C
    }

    public enum LinearEncoding {
        UPCA,
        UPCE,
        EAN,
        CODE_128,
        DATABAR_14,
        DATABAR_14_STACK,
        DATABAR_14_STACK_OMNI,
        DATABAR_LIMITED,
        DATABAR_EXPANDED,
        DATABAR_EXPANDED_STACK
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected boolean gs1Supported() {
        return true;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
    }

    public Composite() {
        this.inputDataType = Symbol.DataType.GS1;
    }

    public void setSymbology(LinearEncoding linearEncoding) {
        this.symbology = linearEncoding;
    }

    public LinearEncoding getSymbology() {
        return this.symbology;
    }

    public void setLinearDataType(Symbol.DataType dataType) {
        this.linearDataType = dataType;
    }

    public Symbol.DataType getLinearDataType() {
        return this.linearDataType;
    }

    public void setLinearContent(String str) {
        this.linearContent = str;
    }

    public String getLinearContent() {
        return this.linearContent;
    }

    public void setPreferredMode(CompositeMode compositeMode) {
        this.userPreferredMode = compositeMode;
    }

    public CompositeMode getPreferredMode() {
        return this.userPreferredMode;
    }

    public CompositeMode getModeUsed() {
        return this.cc_mode;
    }

    public void setGuardPatternExtraHeight(int i) {
        this.guardPatternExtraHeight = Integer.valueOf(i);
    }

    public void setSeparatorHeight(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Invalid Composite separator height: " + i);
        }
        this.separatorHeight = i;
    }

    public void setPreferredColumns(int i) {
        if (i < 1 || i > 10) {
            throw new IllegalArgumentException("Invalid column count: " + i);
        }
        this.preferredColumns = Integer.valueOf(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0178 A[LOOP:0: B:24:0x0172->B:26:0x0178, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0220 A[LOOP:2: B:58:0x021a->B:60:0x0220, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0256  */
    @Override // uk.org.okapibarcode.backend.Symbol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void encode() {
        /*
            Method dump skipped, instructions count: 680
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.Composite.encode():void");
    }

    /* renamed from: uk.org.okapibarcode.backend.Composite$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$Composite$CompositeMode;
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding;

        static {
            int[] iArr = new int[LinearEncoding.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding = iArr;
            try {
                iArr[LinearEncoding.UPCA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[LinearEncoding.UPCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[LinearEncoding.EAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[LinearEncoding.CODE_128.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[LinearEncoding.DATABAR_14.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[LinearEncoding.DATABAR_14_STACK_OMNI.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[LinearEncoding.DATABAR_14_STACK.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[LinearEncoding.DATABAR_LIMITED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[LinearEncoding.DATABAR_EXPANDED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[LinearEncoding.DATABAR_EXPANDED_STACK.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr2 = new int[CompositeMode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$Composite$CompositeMode = iArr2;
            try {
                iArr2[CompositeMode.CC_A.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$CompositeMode[CompositeMode.CC_B.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Composite$CompositeMode[CompositeMode.CC_C.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }

    private void copyPropertiesTo(Symbol symbol) {
        symbol.setBarHeight(getBarHeight());
        symbol.setHumanReadableLocation(getHumanReadableLocation());
        symbol.setHumanReadableAlignment(getHumanReadableAlignment());
        symbol.setModuleWidth(getModuleWidth());
        symbol.setQuietZoneHorizontal(getQuietZoneHorizontal());
        symbol.setQuietZoneVertical(getQuietZoneVertical());
        if (getFont() != null) {
            symbol.setFont(getFont());
        } else {
            symbol.setFontName(getFontName());
            symbol.setFontSize(getFontSize());
        }
    }

    private void encodeComposite() {
        if (this.content.length() > 2990) {
            throw new OkapiException("2D component input data too long");
        }
        int[] bytes = toBytes(this.content, StandardCharsets.US_ASCII, new int[0]);
        this.inputData = bytes;
        if (bytes == null) {
            throw new OkapiException("Invalid characters in input data");
        }
        CompositeMode compositeMode = this.userPreferredMode;
        this.cc_mode = compositeMode;
        if (compositeMode == CompositeMode.CC_C && this.symbology != LinearEncoding.CODE_128) {
            throw new OkapiException("Invalid mode (CC-C only valid with GS1-128 linear component)");
        }
        switch (AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$Composite$LinearEncoding[this.symbology.ordinal()]) {
            case 1:
            case 4:
            case 5:
            case 9:
            case 10:
                this.cc_width = 4;
                break;
            case 2:
            case 6:
            case 7:
                this.cc_width = 2;
                break;
            case 3:
                if (eanCalculateVersion() == 8) {
                    this.cc_width = 3;
                    break;
                } else {
                    this.cc_width = 4;
                    break;
                }
            case 8:
                this.cc_width = 3;
                break;
        }
        infoLine("Composite Width: " + this.cc_width);
        if (this.cc_mode == CompositeMode.CC_A && !cc_binary_string()) {
            this.cc_mode = CompositeMode.CC_B;
        }
        if (this.cc_mode == CompositeMode.CC_B && !cc_binary_string()) {
            if (this.symbology != LinearEncoding.CODE_128) {
                throw new OkapiException("Input too long");
            }
            this.cc_mode = CompositeMode.CC_C;
        }
        if (this.cc_mode == CompositeMode.CC_C && !cc_binary_string()) {
            throw new OkapiException("Input too long");
        }
        int i = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$Composite$CompositeMode[this.cc_mode.ordinal()];
        if (i == 1) {
            cc_a();
            infoLine("Composite Type: CC-A");
        } else if (i == 2) {
            cc_b();
            infoLine("Composite Type: CC-B");
        } else if (i == 3) {
            cc_c();
            infoLine("Composite Type: CC-C");
        }
        super.plotSymbol();
    }

    private int eanCalculateVersion() {
        boolean z = true;
        int i = 0;
        for (int i2 = 0; i2 < this.linearContent.length(); i2++) {
            if (this.linearContent.charAt(i2) < '0' || this.linearContent.charAt(i2) > '9') {
                z = false;
            } else if (z) {
                i++;
            }
        }
        return i <= 7 ? 8 : 13;
    }

    private boolean calculateSymbolSize() {
        int i;
        int length = this.binary_string.length();
        if (this.cc_mode == CompositeMode.CC_A) {
            int i2 = this.cc_width;
            if (i2 == 2) {
                if (length > 167) {
                    return false;
                }
                if (length <= 167) {
                    this.target_bitsize = 167;
                }
                if (length <= 138) {
                    this.target_bitsize = 138;
                }
                if (length <= 118) {
                    this.target_bitsize = 118;
                }
                if (length <= 108) {
                    this.target_bitsize = AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR;
                }
                if (length <= 88) {
                    this.target_bitsize = 88;
                }
                if (length <= 78) {
                    this.target_bitsize = 78;
                }
                if (length <= 59) {
                    this.target_bitsize = 59;
                }
            } else if (i2 == 3) {
                if (length > 167) {
                    return false;
                }
                if (length <= 167) {
                    this.target_bitsize = 167;
                }
                if (length <= 138) {
                    this.target_bitsize = 138;
                }
                if (length <= 118) {
                    this.target_bitsize = 118;
                }
                if (length <= 98) {
                    this.target_bitsize = 98;
                }
                if (length <= 78) {
                    this.target_bitsize = 78;
                }
            } else if (i2 == 4) {
                if (length > 197) {
                    return false;
                }
                if (length <= 197) {
                    this.target_bitsize = 197;
                }
                if (length <= 167) {
                    this.target_bitsize = 167;
                }
                if (length <= 138) {
                    this.target_bitsize = 138;
                }
                if (length <= 108) {
                    this.target_bitsize = AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR;
                }
                if (length <= 78) {
                    this.target_bitsize = 78;
                }
            }
        }
        if (this.cc_mode == CompositeMode.CC_B) {
            int i3 = this.cc_width;
            if (i3 == 2) {
                if (length > 336) {
                    return false;
                }
                if (length <= 336) {
                    this.target_bitsize = 336;
                }
                if (length <= 296) {
                    this.target_bitsize = 296;
                }
                if (length <= 256) {
                    this.target_bitsize = 256;
                }
                if (length <= 208) {
                    this.target_bitsize = 208;
                }
                if (length <= 160) {
                    this.target_bitsize = 160;
                }
                if (length <= 104) {
                    this.target_bitsize = 104;
                }
                if (length <= 56) {
                    this.target_bitsize = 56;
                }
            } else if (i3 == 3) {
                if (length > 768) {
                    return false;
                }
                if (length <= 768) {
                    this.target_bitsize = ViewUtils.EDGE_TO_EDGE_FLAGS;
                }
                if (length <= 648) {
                    this.target_bitsize = 648;
                }
                if (length <= 536) {
                    this.target_bitsize = 536;
                }
                if (length <= 416) {
                    this.target_bitsize = 416;
                }
                if (length <= 304) {
                    this.target_bitsize = 304;
                }
                if (length <= 208) {
                    this.target_bitsize = 208;
                }
                if (length <= 152) {
                    this.target_bitsize = 152;
                }
                if (length <= 112) {
                    this.target_bitsize = b.p;
                }
                if (length <= 72) {
                    this.target_bitsize = 72;
                }
                if (length <= 32) {
                    this.target_bitsize = 32;
                }
            } else if (i3 == 4) {
                if (length > 1184) {
                    return false;
                }
                if (length <= 1184) {
                    this.target_bitsize = 1184;
                }
                if (length <= 1016) {
                    this.target_bitsize = PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW;
                }
                if (length <= 840) {
                    this.target_bitsize = 840;
                }
                if (length <= 672) {
                    this.target_bitsize = 672;
                }
                if (length <= 496) {
                    this.target_bitsize = 496;
                }
                if (length <= 352) {
                    this.target_bitsize = 352;
                }
                if (length <= 264) {
                    this.target_bitsize = 264;
                }
                if (length <= 208) {
                    this.target_bitsize = 208;
                }
                if (length <= 152) {
                    this.target_bitsize = 152;
                }
                if (length <= 96) {
                    this.target_bitsize = 96;
                }
                if (length <= 56) {
                    this.target_bitsize = 56;
                }
            }
        }
        if (this.cc_mode == CompositeMode.CC_C) {
            int i4 = length / 8;
            if (length % 8 != 0) {
                i4++;
            }
            int i5 = ((i4 / 6) * 5) + (i4 % 6);
            int i6 = i5 <= 1280 ? 6 : 7;
            if (i5 <= 640) {
                i6 = 5;
            }
            int i7 = i5 > 320 ? i6 : 4;
            if (i5 <= 160) {
                i7 = 3;
            }
            int i8 = i5 > 40 ? i7 : 2;
            this.ecc = i8;
            int i9 = 1;
            for (int i10 = 1; i10 <= i8 + 1; i10++) {
                i9 *= 2;
            }
            int i11 = i5 + i9 + 3;
            int i12 = this.linearWidth;
            if (i12 == 0) {
                this.cc_width = (int) (Math.sqrt(i11 / 3.0d) + 0.5d);
            } else {
                this.cc_width = (i12 - 53) / 17;
            }
            int i13 = this.cc_width;
            if (i11 / i13 > 90) {
                this.cc_width = i13 + 1;
            }
            int i14 = this.cc_width;
            int i15 = i11 / i14;
            if (i11 % i14 != 0) {
                i15++;
            }
            while (true) {
                i = this.cc_width;
                if (i <= i15 * 3) {
                    break;
                }
                int i16 = i - 1;
                this.cc_width = i16;
                i15 = i11 / i16;
                if (i11 % i16 != 0) {
                    i15++;
                }
            }
            int i17 = ((i * i15) - i9) - 3;
            this.target_bitsize = (((i17 / 5) * 6) + (i17 % 5)) * 8;
        }
        this.remainder = this.target_bitsize - length;
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean cc_binary_string() {
        int i;
        int i2;
        int i3;
        boolean z;
        int i4;
        boolean z2;
        int[] copyOfRange;
        int i5;
        boolean z3;
        int i6;
        char c;
        char c2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        this.ecc = 0;
        this.target_bitsize = 0;
        int[] iArr = this.inputData;
        char c3 = (iArr.length >= 8 && iArr[0] == 49 && ((i11 = iArr[1]) == 48 || i11 == 49 || i11 == 55)) ? (char) 2 : (char) 1;
        if (iArr.length >= 2 && iArr[0] == 57 && iArr[1] == 48) {
            c3 = 3;
        }
        info("Composite Encodation: ");
        if (c3 == 1) {
            infoLine("0");
        } else if (c3 == 2) {
            infoLine("10");
        } else if (c3 == 3) {
            infoLine("11");
        }
        StringBuilder sb = new StringBuilder();
        this.binary_string = sb;
        if (c3 == 1) {
            sb.append('0');
        }
        if (c3 == 2) {
            this.binary_string.append("10");
            int[] iArr2 = this.inputData;
            if (iArr2[1] == 48) {
                this.binary_string.append("11");
                i2 = 0;
            } else {
                Strings.binaryAppend(this.binary_string, ((((iArr2[2] - 48) * 10) + (iArr2[3] - 48)) * 384) + (((((iArr2[4] - 48) * 10) + (iArr2[5] - 48)) - 1) * 32) + ((iArr2[6] - 48) * 10) + (iArr2[7] - 48), 16);
                if (this.inputData[1] == 49) {
                    this.binary_string.append('0');
                } else {
                    this.binary_string.append('1');
                }
                i2 = 8;
            }
            int i12 = i2 + 2;
            int[] iArr3 = this.inputData;
            if (i12 < iArr3.length && iArr3[i2] == 49 && iArr3[i2 + 1] == 48) {
                i2 = i12;
                i = 0;
            } else {
                i = 1;
            }
        } else {
            i = 0;
            i2 = 0;
        }
        if (c3 == 3) {
            int[] iArr4 = this.inputData;
            int length = iArr4.length;
            for (int length2 = iArr4.length - 1; length2 > 2; length2--) {
                if (this.inputData[length2] == -1) {
                    length = length2;
                }
            }
            int[] copyOfRange2 = Arrays.copyOfRange(this.inputData, 2, length);
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            for (int i16 : copyOfRange2) {
                if (i16 >= 65 && i16 <= 90) {
                    i13++;
                }
                if (i16 >= 48 && i16 <= 57) {
                    i14++;
                }
                switch (i16) {
                    case 42:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                        i15++;
                        break;
                }
                if ((i16 < 48 || i16 > 57) && !((i16 >= 65 && i16 <= 90) || i16 == 42 || i16 == 44 || i16 == 45 || i16 == 46 || i16 == 47)) {
                    throw new OkapiException("Invalid AI 90 data");
                }
            }
            int i17 = -1;
            for (int i18 = 3; i18 >= 0; i18--) {
                if (i18 < copyOfRange2.length && (i10 = copyOfRange2[i18]) >= 65 && i10 <= 90) {
                    i17 = i18;
                }
            }
            boolean z4 = false;
            for (int i19 = 0; i19 < i17; i19++) {
                int i20 = copyOfRange2[i19];
                if (i20 < 48 || i20 > 57) {
                    z4 = true;
                }
            }
            boolean z5 = i17 >= 1 && copyOfRange2[0] == 48;
            if (i17 != -1 && !z4 && !z5) {
                this.binary_string.append("11");
                int i21 = i14 - i17;
                int i22 = i13 - 1;
                if (i15 > 0) {
                    this.binary_string.append('0');
                    c2 = 1;
                } else if (i22 <= i21) {
                    this.binary_string.append("10");
                    c2 = 3;
                } else {
                    this.binary_string.append("11");
                    c2 = 2;
                }
                int length3 = copyOfRange2.length;
                int i23 = length3 + 2;
                int[] iArr5 = this.inputData;
                if (i23 >= iArr5.length || iArr5[i23] != -1) {
                    i7 = 0;
                } else {
                    int i24 = length3 + 4;
                    i7 = (i24 < iArr5.length && iArr5[length3 + 3] == 50 && iArr5[i24] == 49) ? 1 : 0;
                    int i25 = length3 + 6;
                    if (i25 < iArr5.length && iArr5[length3 + 3] == 56 && iArr5[i24] == 48 && iArr5[length3 + 5] == 48 && iArr5[i25] == 52) {
                        i7 = 2;
                    }
                }
                if (i7 == 0) {
                    this.binary_string.append('0');
                } else if (i7 == 1) {
                    this.binary_string.append("10");
                } else if (i7 == 2) {
                    this.binary_string.append("11");
                }
                int i26 = 0;
                for (int i27 = 0; i27 < i17; i27++) {
                    i26 = (i26 * 10) + (copyOfRange2[i27] - 48);
                }
                int indexOf = i26 < 31 ? "BDHIJKLNPQRSTVWZ".indexOf(copyOfRange2[i17]) : -1;
                if (indexOf == -1) {
                    this.binary_string.append("11111");
                    Strings.binaryAppend(this.binary_string, i26, 10);
                    Strings.binaryAppend(this.binary_string, copyOfRange2[i17] - 65, 5);
                } else {
                    Strings.binaryAppend(this.binary_string, i26, 5);
                    Strings.binaryAppend(this.binary_string, indexOf, 4);
                }
                i6 = 3 + i17;
                c = 2;
            } else {
                this.binary_string.append('0');
                i6 = 0;
                c = 2;
                c2 = 0;
                i7 = 0;
            }
            if (c2 == c) {
                while (true) {
                    int i28 = this.inputData[i6];
                    if (i28 >= 48 && i28 <= 57) {
                        Strings.binaryAppend(this.binary_string, i28 + 4, 6);
                    }
                    int i29 = this.inputData[i6];
                    if (i29 >= 65 && i29 <= 90) {
                        Strings.binaryAppend(this.binary_string, i29 - 65, 5);
                    }
                    if (this.inputData[i6] == -1) {
                        this.binary_string.append("11111");
                    }
                    i9 = i6 + 1;
                    int[] iArr6 = this.inputData;
                    if (iArr6[i6] != -1 && i9 < iArr6.length) {
                        i6 = i9;
                    }
                }
                i6 = i9;
                z = true;
            } else {
                z = false;
            }
            if (c2 == 1) {
                while (true) {
                    int i30 = this.inputData[i6];
                    if (i30 >= 48 && i30 <= 57) {
                        Strings.binaryAppend(this.binary_string, i30 - 43, 5);
                    }
                    int i31 = this.inputData[i6];
                    if (i31 >= 65 && i31 <= 90) {
                        Strings.binaryAppend(this.binary_string, i31 - 33, 6);
                    }
                    int i32 = this.inputData[i6];
                    if (i32 != -1) {
                        if (i32 == 42) {
                            this.binary_string.append("111010");
                        } else {
                            switch (i32) {
                                case 44:
                                    this.binary_string.append("111011");
                                    break;
                                case 45:
                                    this.binary_string.append("111100");
                                    break;
                                case 46:
                                    this.binary_string.append("111101");
                                    break;
                                case 47:
                                    this.binary_string.append("111110");
                                    break;
                            }
                        }
                    } else {
                        this.binary_string.append("01111");
                    }
                    i8 = i6 + 1;
                    int[] iArr7 = this.inputData;
                    if (iArr7[i6] != -1 && i8 < iArr7.length) {
                        i6 = i8;
                    }
                }
                i6 = i8;
            }
            i3 = i6 + (i7 * 2);
        } else {
            i3 = i2;
            z = false;
        }
        int[] iArr8 = this.inputData;
        if (i3 >= iArr8.length || iArr8[i3] != -1) {
            i4 = 1;
            z2 = false;
        } else {
            i4 = 1;
            z2 = true;
        }
        if (i == i4 && !z2) {
            int length4 = iArr8.length - i3;
            copyOfRange = new int[length4 + 1];
            copyOfRange[0] = -1;
            System.arraycopy(iArr8, i3, copyOfRange, i4, length4);
        } else {
            copyOfRange = Arrays.copyOfRange(iArr8, i3, iArr8.length);
        }
        DataBarExpanded.EncodeMode encodeMode = DataBarExpanded.EncodeMode.NUMERIC;
        if (copyOfRange.length != 0) {
            DataBarExpanded.EncodeMode[] initialEncodeModes = DataBarExpanded.getInitialEncodeModes(copyOfRange);
            boolean applyGeneralFieldRules = DataBarExpanded.applyGeneralFieldRules(initialEncodeModes);
            i5 = 1;
            encodeMode = DataBarExpanded.appendToBinaryString(copyOfRange, initialEncodeModes, applyGeneralFieldRules, true, this.binary_string);
            z3 = applyGeneralFieldRules;
            z = false;
        } else {
            i5 = 1;
            z3 = false;
        }
        if (!calculateSymbolSize()) {
            return false;
        }
        if (z3) {
            int i33 = copyOfRange[copyOfRange.length - i5];
            if (i33 == -1) {
                this.binary_string.append("000001111");
            } else {
                int i34 = this.remainder;
                if (i34 >= 4 && i34 <= 6) {
                    Strings.binaryAppend(this.binary_string, i33 - 47, 4);
                } else {
                    Strings.binaryAppend(this.binary_string, ((i33 - 48) * 11) + 18, 7);
                }
            }
        }
        if (this.binary_string.length() > 11805) {
            throw new OkapiException("Input too long");
        }
        if (!calculateSymbolSize()) {
            return false;
        }
        infoLine("Composite Binary Length: " + this.binary_string.length());
        logBinaryString();
        if (this.binary_string.length() >= this.target_bitsize) {
            return true;
        }
        if (z) {
            this.binary_string.append("11111");
        }
        if (copyOfRange.length == 0 || encodeMode == DataBarExpanded.EncodeMode.NUMERIC) {
            this.binary_string.append("0000");
        }
        while (true) {
            int length5 = this.binary_string.length();
            int i35 = this.target_bitsize;
            if (length5 < i35) {
                this.binary_string.append("00100");
            } else {
                StringBuilder sb2 = this.binary_string;
                sb2.delete(i35, sb2.length());
                return true;
            }
        }
    }

    private void logBinaryString() {
        info("Composite Binary String: ");
        int i = 0;
        for (int i2 = 0; i2 < this.binary_string.length(); i2++) {
            int i3 = i2 % 4;
            if (i3 != 0) {
                if (i3 != 1) {
                    if (i3 != 2) {
                        if (i3 == 3) {
                            if (this.binary_string.charAt(i2) == '1') {
                                i++;
                            }
                            info(Integer.toHexString(i));
                            i = 0;
                        }
                    } else if (this.binary_string.charAt(i2) == '1') {
                        i += 2;
                    }
                } else if (this.binary_string.charAt(i2) == '1') {
                    i += 4;
                }
            } else if (this.binary_string.charAt(i2) == '1') {
                i += 8;
            }
        }
        if (this.binary_string.length() % 4 != 0) {
            info(Integer.toHexString(i));
        }
        infoLine();
    }

    private void cc_a() {
        int i;
        int i2;
        int i3;
        int[] iArr = new int[8];
        int[] iArr2 = new int[5];
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < 13; i4++) {
            this.bitStr[i4] = 0;
        }
        for (int i5 = 0; i5 < 28; i5++) {
            this.codeWords[i5] = 0;
        }
        StringBuilder sb2 = new StringBuilder(208);
        sb2.append((CharSequence) this.binary_string);
        for (int length = this.binary_string.length(); length < 208; length++) {
            sb2.append('0');
        }
        for (int i6 = 0; i6 < 13; i6++) {
            int i7 = i6 * 16;
            this.bitStr[i6] = 0;
            for (int i8 = 0; i8 < 16; i8++) {
                if (sb2.charAt(i7 + i8) == '1') {
                    int[] iArr3 = this.bitStr;
                    iArr3[i6] = iArr3[i6] + (32768 >> i8);
                }
            }
        }
        init928();
        int encode928 = encode928(this.binary_string.length());
        int i9 = this.cc_width;
        int i10 = 3;
        if (i9 == 2) {
            i = 6;
            if (encode928 != 6) {
                if (encode928 != 14) {
                    if (encode928 != 17) {
                        if (encode928 == 8) {
                            i2 = 1;
                        } else if (encode928 == 9) {
                            i2 = 2;
                        } else if (encode928 == 11) {
                            i2 = 3;
                        } else if (encode928 == 12) {
                            i2 = 4;
                        }
                    }
                    i2 = i;
                } else {
                    i2 = 5;
                }
            }
            i2 = 0;
        } else if (i9 != 3) {
            if (i9 == 4) {
                if (encode928 == 8) {
                    i2 = 12;
                } else if (encode928 == 11) {
                    i2 = 13;
                } else if (encode928 == 14) {
                    i2 = 14;
                } else if (encode928 == 17) {
                    i2 = 15;
                } else if (encode928 == 20) {
                    i2 = 16;
                }
            }
            i2 = 0;
        } else if (encode928 != 8) {
            i = 10;
            if (encode928 == 10) {
                i2 = 8;
            } else if (encode928 != 12) {
                if (encode928 != 14) {
                    if (encode928 == 17) {
                        i2 = 11;
                    }
                    i2 = 0;
                }
                i2 = i;
            } else {
                i2 = 9;
            }
        } else {
            i2 = 7;
        }
        int[] iArr4 = CCA_VARIANTS;
        int i11 = iArr4[i2];
        int i12 = i2 + 17;
        int i13 = iArr4[i12];
        int i14 = i2 + 34;
        int i15 = iArr4[i14];
        for (int i16 = 0; i16 < 8; i16++) {
            iArr[i16] = 0;
        }
        info("Composite Codewords: ");
        for (int i17 = 0; i17 < encode928; i17++) {
            int i18 = i13 - 1;
            int i19 = (this.codeWords[i17] + iArr[i18]) % PDF417Common.NUMBER_OF_CODEWORDS;
            while (i18 >= 0) {
                if (i18 == 0) {
                    iArr[i18] = (929 - ((i19 * CCA_COEFFS[i15 + i18]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS;
                } else {
                    iArr[i18] = ((iArr[i18 - 1] + PDF417Common.NUMBER_OF_CODEWORDS) - ((i19 * CCA_COEFFS[i15 + i18]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS;
                }
                i18--;
            }
            infoSpace(this.codeWords[i17]);
        }
        infoLine();
        for (int i20 = 0; i20 < i13; i20++) {
            int i21 = iArr[i20];
            if (i21 != 0) {
                iArr[i20] = 929 - i21;
            }
        }
        for (int i22 = i13 - 1; i22 >= 0; i22--) {
            this.codeWords[encode928] = iArr[i22];
            encode928++;
        }
        int[] iArr5 = A_RAP_TABLE;
        int i23 = iArr5[i2];
        int i24 = iArr5[i12];
        int i25 = iArr5[i14];
        int i26 = iArr5[i2 + 51] / 3;
        this.readable = "";
        this.row_count = i11;
        this.pattern = new String[this.row_count];
        this.row_height = new int[this.row_count];
        for (int i27 = 0; i27 < i11; i27++) {
            int i28 = i26 * PDF417Common.NUMBER_OF_CODEWORDS;
            for (int i29 = 0; i29 < 5; i29++) {
                iArr2[i29] = 0;
            }
            int i30 = 0;
            while (true) {
                i3 = this.cc_width;
                if (i30 >= i3) {
                    break;
                }
                int i31 = i30 + 1;
                iArr2[i31] = this.codeWords[(i3 * i27) + i30];
                i30 = i31;
            }
            String str = i3 == i10 ? "" : "" + RAPLR[i23];
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str + "1");
            String[] strArr = CODAGEMC;
            sb3.append(strArr[iArr2[1] + i28]);
            String str2 = sb3.toString() + "1";
            if (this.cc_width == i10) {
                str2 = str2 + RAPC[i24];
            }
            if (this.cc_width >= 2) {
                str2 = ((str2 + "1") + strArr[iArr2[2] + i28]) + "1";
            }
            if (this.cc_width == 4) {
                str2 = str2 + RAPC[i24];
            }
            if (this.cc_width >= 3) {
                str2 = ((str2 + "1") + strArr[iArr2[3] + i28]) + "1";
            }
            if (this.cc_width == 4) {
                str2 = ((str2 + "1") + strArr[i28 + iArr2[4]]) + "1";
            }
            String str3 = (str2 + RAPLR[i25]) + "1";
            sb.setLength(0);
            boolean z = true;
            for (int i32 = 0; i32 < str3.length(); i32++) {
                if (str3.charAt(i32) >= '0' && str3.charAt(i32) <= '9') {
                    for (int i33 = 0; i33 < str3.charAt(i32) - '0'; i33++) {
                        if (!z) {
                            sb.append('0');
                        } else {
                            sb.append('1');
                        }
                    }
                    z = !z;
                } else {
                    sb.append(PDF_TTF[uk.org.okapibarcode.util.Arrays.positionOf(str3.charAt(i32), BR_SET)]);
                }
            }
            this.row_height[i27] = 2;
            this.pattern[i27] = bin2pat(sb);
            i23++;
            i24++;
            i25++;
            i26++;
            if (i23 == 53) {
                i23 = 1;
            }
            if (i24 == 53) {
                i24 = 1;
            }
            if (i25 == 53) {
                i25 = 1;
            }
            i10 = 3;
            if (i26 == 3) {
                i26 = 0;
            }
        }
    }

    private void init928() {
        int[] iArr = new int[7];
        iArr[6] = 1;
        for (int i = 5; i >= 0; i--) {
            iArr[i] = 0;
        }
        for (int i2 = 0; i2 < 7; i2++) {
            this.pwr928[0][i2] = iArr[i2];
        }
        for (int i3 = 1; i3 < 69; i3++) {
            int i4 = 0;
            for (int i5 = 6; i5 >= 1; i5--) {
                i4 = (i4 / PDF417Common.MAX_CODEWORDS_IN_BARCODE) + (iArr[i5] * 2);
                int[] iArr2 = this.pwr928[i3];
                int i6 = i4 % PDF417Common.MAX_CODEWORDS_IN_BARCODE;
                iArr[i5] = i6;
                iArr2[i5] = i6;
            }
            int[] iArr3 = this.pwr928[i3];
            int i7 = (iArr[0] * 2) + (i4 / PDF417Common.MAX_CODEWORDS_IN_BARCODE);
            iArr[0] = i7;
            iArr3[0] = i7;
        }
    }

    private int encode928(int i) {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < i) {
            int min = Math.min(i - i2, 69);
            int i5 = min / 10;
            int i6 = i5 + 1;
            i3 += i6;
            for (int i7 = 0; i7 < i6; i7++) {
                this.codeWords[i4 + i7] = 0;
            }
            for (int i8 = 0; i8 < min; i8++) {
                if (getBit(((i2 + min) - i8) - 1)) {
                    for (int i9 = 0; i9 < i6; i9++) {
                        int[] iArr = this.codeWords;
                        int i10 = i4 + i9;
                        iArr[i10] = iArr[i10] + this.pwr928[i8][(i9 + 7) - i6];
                    }
                }
            }
            while (i5 > 0) {
                int[] iArr2 = this.codeWords;
                int i11 = i4 + i5;
                int i12 = i11 - 1;
                iArr2[i12] = iArr2[i12] + (iArr2[i11] / PDF417Common.MAX_CODEWORDS_IN_BARCODE);
                iArr2[i11] = iArr2[i11] % PDF417Common.MAX_CODEWORDS_IN_BARCODE;
                i5--;
            }
            i2 += 69;
            i4 += 7;
        }
        return i3;
    }

    private boolean getBit(int i) {
        return (this.bitStr[i >> 4] & (32768 >> (i & 15))) != 0;
    }

    private void cc_b() {
        int i;
        int i2;
        int[] iArr = new int[50];
        int[] iArr2 = new int[5];
        StringBuilder sb = new StringBuilder();
        this.inputData = new int[(this.binary_string.length() / 8) + 3];
        int length = this.binary_string.length() / 8;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i3 * 8;
            this.inputData[i3] = 0;
            for (int i5 = 0; i5 < 8; i5++) {
                if (this.binary_string.charAt(i4 + i5) == '1') {
                    int[] iArr3 = this.inputData;
                    iArr3[i3] = iArr3[i3] + (128 >> i5);
                }
            }
        }
        this.codeWordCount = 0;
        this.codeWords[0] = 920;
        this.codeWordCount = 1;
        byteprocess(0, length);
        int i6 = this.cc_width;
        int i7 = 24;
        if (i6 == 2) {
            int i8 = this.codeWordCount;
            int i9 = i8 <= 33 ? 12 : 13;
            if (i8 <= 29) {
                i9 = 11;
            }
            if (i8 <= 24) {
                i9 = 10;
            }
            if (i8 <= 19) {
                i9 = 9;
            }
            if (i8 <= 13) {
                i9 = 8;
            }
            i = i8 <= 8 ? 7 : i9;
        } else {
            i = 0;
        }
        if (i6 == 3) {
            int i10 = this.codeWordCount;
            int i11 = i10 <= 70 ? 22 : 23;
            if (i10 <= 58) {
                i11 = 21;
            }
            if (i10 <= 46) {
                i11 = 20;
            }
            int i12 = i10 <= 34 ? 19 : i11;
            if (i10 <= 24) {
                i12 = 18;
            }
            if (i10 <= 18) {
                i12 = 17;
            }
            if (i10 <= 14) {
                i12 = 16;
            }
            if (i10 <= 10) {
                i12 = 15;
            }
            i = i10 <= 6 ? 14 : i12;
        }
        if (i6 == 4) {
            int i13 = this.codeWordCount;
            int i14 = i13 <= 108 ? 33 : 34;
            if (i13 <= 90) {
                i14 = 32;
            }
            if (i13 <= 72) {
                i14 = 31;
            }
            if (i13 <= 54) {
                i14 = 30;
            }
            if (i13 <= 39) {
                i14 = 29;
            }
            if (i13 <= 30) {
                i14 = 28;
            }
            if (i13 <= 24) {
                i14 = 27;
            }
            if (i13 <= 18) {
                i14 = 26;
            }
            if (i13 <= 12) {
                i14 = 25;
            }
            if (i13 > 8) {
                i7 = i14;
            }
        } else {
            i7 = i;
        }
        int i15 = i7 - 1;
        int[] iArr4 = MICRO_VARIANTS;
        int i16 = iArr4[i15];
        int i17 = i7 + 33;
        int i18 = iArr4[i17];
        int i19 = i7 + 67;
        int i20 = iArr4[i19];
        int i21 = i7 + 101;
        int i22 = iArr4[i21];
        for (int i23 = ((i16 * i18) - i20) - this.codeWordCount; i23 > 0; i23--) {
            int[] iArr5 = this.codeWords;
            int i24 = this.codeWordCount;
            iArr5[i24] = 900;
            this.codeWordCount = i24 + 1;
        }
        int i25 = this.codeWordCount;
        for (int i26 = 0; i26 < 50; i26++) {
            iArr[i26] = 0;
        }
        info("Composite Codewords: ");
        int i27 = 0;
        while (i27 < i25) {
            int i28 = i20 - 1;
            int i29 = (this.codeWords[i27] + iArr[i28]) % PDF417Common.NUMBER_OF_CODEWORDS;
            while (i28 >= 0) {
                if (i28 == 0) {
                    iArr[i28] = (929 - ((i29 * MICROCOEFFS[i22 + i28]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS;
                    i2 = i22;
                } else {
                    i2 = i22;
                    iArr[i28] = ((iArr[i28 - 1] + PDF417Common.NUMBER_OF_CODEWORDS) - ((i29 * MICROCOEFFS[i22 + i28]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS;
                }
                i28--;
                i22 = i2;
            }
            infoSpace(this.codeWords[i27]);
            i27++;
            i22 = i22;
        }
        infoLine();
        for (int i30 = 0; i30 < i20; i30++) {
            int i31 = iArr[i30];
            if (i31 != 0) {
                iArr[i30] = 929 - i31;
            }
        }
        int i32 = 1;
        int i33 = i20 - 1;
        while (i33 >= 0) {
            int[] iArr6 = this.codeWords;
            int i34 = this.codeWordCount;
            iArr6[i34] = iArr[i33];
            this.codeWordCount = i34 + i32;
            i33--;
            i32 = 1;
        }
        int[] iArr7 = RAP_TABLE;
        int i35 = iArr7[i15];
        int i36 = iArr7[i17];
        int i37 = iArr7[i19];
        int i38 = iArr7[i21] / 3;
        this.readable = "";
        this.row_count = i18;
        this.pattern = new String[this.row_count];
        this.row_height = new int[this.row_count];
        int i39 = 0;
        while (i39 < i18) {
            int i40 = i38 * PDF417Common.NUMBER_OF_CODEWORDS;
            for (int i41 = 0; i41 < 5; i41++) {
                iArr2[i41] = 0;
            }
            int i42 = 0;
            while (i42 < i16) {
                int i43 = i42 + 1;
                iArr2[i43] = this.codeWords[(i39 * i16) + i42];
                i42 = i43;
            }
            StringBuilder sb2 = new StringBuilder("");
            String[] strArr = RAPLR;
            sb2.append(strArr[i35]);
            String str = sb2.toString() + "1";
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            String[] strArr2 = CODAGEMC;
            sb3.append(strArr2[i40 + iArr2[1]]);
            String str2 = sb3.toString() + "1";
            int i44 = i16;
            if (this.cc_width == 3) {
                str2 = str2 + RAPC[i36];
            }
            if (this.cc_width >= 2) {
                str2 = ((str2 + "1") + strArr2[iArr2[2] + i40]) + "1";
            }
            if (this.cc_width == 4) {
                str2 = str2 + RAPC[i36];
            }
            if (this.cc_width >= 3) {
                str2 = ((str2 + "1") + strArr2[iArr2[3] + i40]) + "1";
            }
            if (this.cc_width == 4) {
                str2 = ((str2 + "1") + strArr2[i40 + iArr2[4]]) + "1";
            }
            String str3 = (str2 + strArr[i37]) + "1";
            int i45 = 0;
            sb.setLength(0);
            int i46 = 0;
            int i47 = 1;
            while (i46 < str3.length()) {
                if (str3.charAt(i46) >= '0' && str3.charAt(i46) <= '9') {
                    for (int i48 = i45; i48 < str3.charAt(i46) - '0'; i48++) {
                        if (i47 == 0) {
                            sb.append('0');
                        } else {
                            sb.append('1');
                        }
                    }
                    i47 = i47 == 0 ? 1 : i45;
                } else {
                    sb.append(PDF_TTF[uk.org.okapibarcode.util.Arrays.positionOf(str3.charAt(i46), BR_SET)]);
                }
                i46++;
                i45 = 0;
            }
            this.pattern[i39] = bin2pat(sb);
            this.row_height[i39] = 2;
            i35++;
            i36++;
            i37++;
            i38++;
            if (i35 == 53) {
                i35 = 1;
            }
            if (i36 == 53) {
                i36 = 1;
            }
            if (i37 == 53) {
                i37 = 1;
            }
            if (i38 == 3) {
                i38 = 0;
            }
            i39++;
            i16 = i44;
        }
    }

    private void cc_c() {
        int i;
        int i2;
        int[] iArr = new int[520];
        int[] iArr2 = new int[35];
        StringBuilder sb = new StringBuilder();
        this.inputData = new int[(this.binary_string.length() / 8) + 4];
        int length = this.binary_string.length() / 8;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i3 * 8;
            this.inputData[i3] = 0;
            for (int i5 = 0; i5 < 8; i5++) {
                if (this.binary_string.charAt(i4 + i5) == '1') {
                    int[] iArr3 = this.inputData;
                    iArr3[i3] = iArr3[i3] + (128 >> i5);
                }
            }
        }
        this.codeWordCount = 0;
        int[] iArr4 = this.codeWords;
        iArr4[0] = 0;
        int i6 = 1;
        this.codeWordCount = 1;
        iArr4[1] = 920;
        this.codeWordCount = 1 + 1;
        byteprocess(0, length);
        this.codeWords[0] = this.codeWordCount;
        int i7 = 1;
        int i8 = 1;
        while (true) {
            i = this.ecc;
            if (i7 > i + 1) {
                break;
            }
            i8 *= 2;
            i7++;
        }
        int i9 = 30;
        switch (i) {
            case 1:
                i2 = 2;
                break;
            case 2:
                i2 = 6;
                break;
            case 3:
                i2 = 14;
                break;
            case 4:
                i2 = 30;
                break;
            case 5:
                i2 = 62;
                break;
            case 6:
                i2 = WebSocketProtocol.PAYLOAD_SHORT;
                break;
            case 7:
                i2 = 254;
                break;
            case 8:
                i2 = TypedValues.PositionType.TYPE_POSITION_TYPE;
                break;
            default:
                i2 = 0;
                break;
        }
        int i10 = this.codeWordCount;
        for (int i11 = 0; i11 < 520; i11++) {
            iArr[i11] = 0;
        }
        info("Composite Codewords: ");
        for (int i12 = 0; i12 < i10; i12++) {
            int i13 = i8 - 1;
            int i14 = (this.codeWords[i12] + iArr[i13]) % PDF417Common.NUMBER_OF_CODEWORDS;
            while (i13 >= 0) {
                if (i13 == 0) {
                    iArr[i13] = (929 - ((COEFRS[i2 + i13] * i14) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS;
                } else {
                    iArr[i13] = ((iArr[i13 - 1] + PDF417Common.NUMBER_OF_CODEWORDS) - ((i14 * COEFRS[i2 + i13]) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS;
                }
                i13--;
            }
            infoSpace(this.codeWords[i12]);
        }
        infoLine();
        for (int i15 = 0; i15 < i8; i15++) {
            int i16 = iArr[i15];
            if (i16 != 0) {
                iArr[i15] = 929 - i16;
            }
        }
        for (int i17 = i8 - 1; i17 >= 0; i17--) {
            int[] iArr5 = this.codeWords;
            int i18 = this.codeWordCount;
            iArr5[i18] = iArr[i17];
            this.codeWordCount = i18 + 1;
        }
        int i19 = this.codeWordCount;
        int i20 = this.cc_width;
        int i21 = ((i19 / i20) - 1) / 3;
        int i22 = (this.ecc * 3) + (((i19 / i20) - 1) % 3);
        int i23 = i20 - 1;
        this.readable = "";
        this.row_count = this.codeWordCount / this.cc_width;
        this.pattern = new String[this.row_count];
        this.row_height = new int[this.row_count];
        int i24 = 0;
        while (i24 <= (this.codeWordCount / this.cc_width) - i6) {
            int i25 = 0;
            while (true) {
                int i26 = this.cc_width;
                if (i25 < i26) {
                    int i27 = i25 + 1;
                    iArr2[i27] = this.codeWords[(i26 * i24) + i25];
                    i25 = i27;
                } else {
                    int i28 = (i24 / 3) * i9;
                    int i29 = i24 % 3;
                    if (i29 == 0) {
                        iArr2[0] = i28 + i21;
                        iArr2[i26 + 1] = i28 + i23;
                    } else if (i29 == i6) {
                        iArr2[0] = i28 + i22;
                        iArr2[i26 + 1] = i28 + i21;
                    } else if (i29 == 2) {
                        iArr2[0] = i28 + i23;
                        iArr2[i26 + 1] = i28 + i22;
                    }
                    String str = "+*";
                    for (int i30 = 0; i30 <= this.cc_width + i6; i30++) {
                        str = (str + CODAGEMC[(i29 != i6 ? i29 != 2 ? 0 : 1858 : 929) + iArr2[i30]]) + "*";
                    }
                    String str2 = str + "-";
                    sb.setLength(0);
                    for (int i31 = 0; i31 < str2.length(); i31++) {
                        sb.append(PDF_TTF[uk.org.okapibarcode.util.Arrays.positionOf(str2.charAt(i31), BR_SET)]);
                    }
                    this.pattern[i24] = bin2pat(sb);
                    this.row_height[i24] = 3;
                    i24++;
                    i9 = 30;
                    i6 = 1;
                }
            }
        }
    }

    private void byteprocess(int i, int i2) {
        if (this.binary_string.length() % 6 == 0) {
            int[] iArr = this.codeWords;
            int i3 = this.codeWordCount;
            this.codeWordCount = i3 + 1;
            iArr[i3] = 924;
        } else {
            int[] iArr2 = this.codeWords;
            int i4 = this.codeWordCount;
            this.codeWordCount = i4 + 1;
            iArr2[i4] = 901;
        }
        int i5 = 0;
        while (i5 < i2) {
            int i6 = i2 - i5;
            if (6 <= i6) {
                i5 += 6;
                BigInteger valueOf = BigInteger.valueOf(0L);
                int i7 = 6;
                while (true) {
                    int i8 = i7 - 1;
                    if (i7 == 0) {
                        break;
                    }
                    valueOf = valueOf.or(BigInteger.valueOf(this.inputData[i]).shiftLeft(i8 * 8));
                    i7 = i8;
                    i++;
                }
                int i9 = 5;
                while (true) {
                    int i10 = i9 - 1;
                    if (i9 == 0) {
                        break;
                    }
                    this.codeWords[this.codeWordCount + i10] = valueOf.mod(BigInteger.valueOf(900L)).intValue();
                    valueOf = valueOf.divide(BigInteger.valueOf(900L));
                    i9 = i10;
                }
                this.codeWordCount += 5;
            } else {
                i5 += i6;
                while (true) {
                    int i11 = i6 - 1;
                    if (i6 != 0) {
                        int[] iArr3 = this.codeWords;
                        int i12 = this.codeWordCount;
                        this.codeWordCount = i12 + 1;
                        iArr3[i12] = this.inputData[i];
                        i6 = i11;
                        i++;
                    }
                }
            }
        }
    }
}
