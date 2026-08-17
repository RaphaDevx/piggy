package uk.org.okapibarcode.backend;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.material.internal.ViewUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.salesforce.marketingcloud.analytics.stats.b;
import com.salesforce.marketingcloud.push.g;
import com.tealium.remotecommands.RemoteCommand;
import io.sentry.HttpStatusCodeRange;
import io.sentry.protocol.DebugImage;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.BooleanUtils;
import uk.org.okapibarcode.util.EciMode;

/* loaded from: classes4.dex */
public class Pdf417 extends Symbol {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_NUMERIC_COMPACTION_BLOCK_SIZE = 44;
    private int codeWordCount;
    private Integer columns;
    private Integer rows;
    private String structuredAppendFileName;
    private boolean structuredAppendIncludeSegmentCount;
    private static final int[] COEFRS = {27, 917, 522, 568, 723, 809, 237, 308, 436, 284, 646, 653, 428, 379, 274, 562, 232, 755, HttpStatusCodeRange.DEFAULT_MAX, 524, 801, 132, 295, 116, 442, 428, 295, 42, 176, 65, 361, 575, 922, 525, 176, 586, 640, 321, 536, 742, 677, 742, 687, 284, 193, 517, 273, 494, 263, 147, 593, 800, 571, 320, 803, 133, 231, 390, 685, 330, 63, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, 539, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 6, 93, 862, 771, 453, b.l, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 287, b.m, TypedValues.PositionType.TYPE_SIZE_PERCENT, 733, 877, 381, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID, 723, 476, 462, 172, 430, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 858, 822, 543, 376, FrameMetricsAggregator.EVERY_DURATION, RemoteCommand.Response.STATUS_BAD_REQUEST, 672, 762, 283, 184, 440, 35, 519, 31, 460, 594, 225, 535, 517, 352, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 158, 651, ComposerKt.providerKey, 488, TypedValues.PositionType.TYPE_DRAWPATH, 648, 733, 717, 83, 404, 97, 280, 771, 840, 629, 4, 381, 843, 623, 264, 543, 521, 310, 864, 547, 858, 580, 296, 379, 53, 779, 897, 444, RemoteCommand.Response.STATUS_BAD_REQUEST, 925, 749, WalletConstants.ERROR_CODE_APP_LABEL_UNAVAILABLE, 822, 93, 217, 208, PDF417Common.MAX_CODEWORDS_IN_BARCODE, 244, 583, 620, 246, 148, 447, 631, 292, 908, 490, TypedValues.TransitionType.TYPE_AUTO_TRANSITION, 516, 258, 457, 907, 594, 723, 674, 292, 272, 96, 684, 432, 686, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, 860, 569, 193, 219, 129, 186, 236, 287, 192, 775, 278, 173, 40, 379, 712, 463, 646, 776, 171, 491, 297, 763, 156, 732, 95, 270, 447, 90, TypedValues.PositionType.TYPE_PERCENT_Y, 48, 228, 821, 808, 898, 784, 663, 627, 378, 382, 262, 380, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, 754, 336, 89, 614, 87, 432, 670, 616, 157, 374, 242, 726, 600, 269, 375, 898, 845, 454, 354, 130, 814, 587, 804, 34, 211, 330, 539, 297, 827, 865, 37, 517, 834, 315, 550, 86, 801, 4, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 539, 524, 894, 75, 766, 882, 857, 74, ComposerKt.providerMapsKey, 82, 586, 708, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, TypedValues.Custom.TYPE_DIMENSION, 786, 138, 720, 858, 194, 311, 913, 275, 190, 375, 850, 438, 733, 194, 280, ComposerKt.providerKey, 280, 828, 757, 710, 814, 919, 89, 68, 569, 11, ComposerKt.providerMapsKey, 796, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 540, 913, 801, TypedValues.TransitionType.TYPE_DURATION, 799, 137, 439, 418, 592, 668, 353, 859, 370, 694, 325, 240, 216, 257, 284, 549, 209, 884, 315, 70, 329, 793, 490, 274, 877, 162, 749, 812, 684, 461, 334, 376, 849, 521, 307, 291, 803, 712, 19, 358, 399, 908, b.i, FrameMetricsAggregator.EVERY_DURATION, 51, 8, 517, 225, 289, 470, 637, 731, 66, 255, 917, 269, 463, 830, 730, 433, 848, 585, 136, 538, TypedValues.Custom.TYPE_REFERENCE, 90, 2, 290, 743, 199, 655, TypedValues.Custom.TYPE_STRING, 329, 49, 802, 580, 355, 588, 188, 462, 10, 134, 628, 320, 479, 130, 739, 71, 263, TypedValues.AttributesType.TYPE_PIVOT_TARGET, 374, 601, 192, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 142, 673, 687, 234, 722, 384, 177, 752, TypedValues.MotionType.TYPE_PATHMOTION_ARC, 640, 455, 193, 689, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, 805, 641, 48, 60, 732, 621, 895, 544, 261, 852, 655, 309, 697, 755, 756, 60, 231, 773, 434, 421, 726, 528, TypedValues.PositionType.TYPE_PERCENT_WIDTH, 118, 49, 795, 32, 144, 500, 238, 836, 394, 280, 566, 319, 9, 647, 550, 73, 914, 342, WebSocketProtocol.PAYLOAD_SHORT, 32, 681, 331, 792, 620, 60, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 441, 180, 791, 893, 754, TypedValues.MotionType.TYPE_ANIMATE_RELATIVE_TO, 383, 228, 749, 760, 213, 54, 297, 134, 54, 834, 299, 922, 191, 910, 532, TypedValues.MotionType.TYPE_POLAR_RELATIVETO, 829, 189, 20, 167, 29, 872, 449, 83, 402, 41, 656, TypedValues.PositionType.TYPE_SIZE_PERCENT, 579, 481, 173, 404, 251, 688, 95, 497, 555, 642, 543, 307, 159, 924, 558, 648, 55, 497, 10, 352, 77, 373, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, 35, HttpStatusCodeRange.DEFAULT_MAX, 428, ComposerKt.reuseKey, WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR, 574, 118, 498, 285, 380, 350, 492, 197, 265, 920, 155, 914, 299, 229, 643, 294, 871, 306, 88, 87, 193, 352, 781, 846, 75, 327, 520, 435, 543, ComposerKt.providerValuesKey, 666, 249, 346, 781, 621, 640, 268, 794, 534, 539, 781, 408, 390, 644, 102, 476, 499, 290, 632, 545, 37, 858, 916, 552, 41, 542, 289, 122, 272, 383, 800, 485, 98, 752, 472, 761, b.m, 784, 860, 658, 741, 290, ComposerKt.providerMapsKey, 681, 407, 855, 85, 99, 62, 482, 180, 20, 297, 451, 593, 913, 142, 808, 684, 287, 536, 561, 76, 653, 899, 729, 567, 744, 390, InputDeviceCompat.SOURCE_DPAD, 192, 516, 258, 240, 518, 794, 395, ViewUtils.EDGE_TO_EDGE_FLAGS, 848, 51, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 384, 168, 190, 826, 328, 596, 786, 303, 570, 381, WalletConstants.ERROR_CODE_APP_LABEL_UNAVAILABLE, 641, 156, 237, 151, 429, 531, ComposerKt.reuseKey, 676, 710, 89, 168, 304, 402, 40, 708, 575, 162, 864, 229, 65, 861, 841, 512, 164, 477, 221, 92, 358, 785, 288, 357, 850, 836, 827, 736, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, 94, 8, 494, 114, 521, 2, 499, 851, 543, 152, 729, 771, 95, 248, 361, 578, 323, 856, 797, 289, 51, 684, 466, 533, 820, 669, 45, TypedValues.Custom.TYPE_COLOR, 452, 167, 342, 244, 173, 35, 463, 651, 51, 699, 591, 452, 578, 37, 124, 298, 332, 552, 43, 427, 119, 662, 777, 475, 850, 764, 364, 578, 911, 283, 711, 472, TypedValues.CycleType.TYPE_EASING, 245, 288, 594, 394, FrameMetricsAggregator.EVERY_DURATION, 327, 589, 777, 699, 688, 43, 408, 842, 383, 721, 521, 560, 644, 714, 559, 62, 145, 873, 663, 713, 159, 672, 729, 624, 59, 193, 417, 158, 209, 563, 564, 343, 693, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, TypedValues.MotionType.TYPE_DRAW_PATH, 563, 365, 181, 772, 677, 310, 248, 353, 708, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, 579, 870, 617, 841, 632, 860, 289, 536, 35, 777, 618, 586, TypedValues.CycleType.TYPE_WAVE_OFFSET, 833, 77, 597, 346, 269, 757, 632, 695, 751, 331, 247, 184, 45, 787, 680, 18, 66, 407, 369, 54, 492, 228, 613, 830, 922, 437, 519, 644, TypedValues.Custom.TYPE_DIMENSION, 789, TypedValues.CycleType.TYPE_EASING, 305, 441, ComposerKt.reuseKey, 300, 892, 827, 141, 537, 381, 662, InputDeviceCompat.SOURCE_DPAD, 56, 252, 341, 242, 797, 838, 837, 720, 224, 307, 631, 61, 87, 560, 310, 756, 665, 397, 808, 851, 309, 473, 795, 378, 31, 647, 915, 459, 806, 590, 731, TypedValues.CycleType.TYPE_WAVE_PHASE, 216, 548, 249, 321, 881, 699, 535, 673, 782, 210, 815, TypedValues.Custom.TYPE_DIMENSION, 303, 843, 922, 281, 73, 469, 791, 660, 162, 498, 308, 155, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 907, 817, 187, 62, 16, TypedValues.CycleType.TYPE_WAVE_PHASE, 535, 336, 286, 437, 375, 273, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 296, 183, 923, 116, 667, 751, 353, 62, 366, 691, 379, 687, 842, 37, 357, 720, 742, 330, 5, 39, 923, 311, TypedValues.CycleType.TYPE_WAVE_OFFSET, 242, 749, 321, 54, 669, TypedValues.AttributesType.TYPE_PATH_ROTATE, 342, 299, 534, 105, 667, 488, 640, 672, 576, 540, TypedValues.AttributesType.TYPE_PATH_ROTATE, 486, 721, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 46, 656, 447, 171, 616, 464, 190, 531, 297, 321, 762, 752, 533, 175, 134, 14, 381, 433, 717, 45, b.o, 20, 596, 284, 736, 138, 646, WalletConstants.ERROR_CODE_AUTHENTICATION_FAILURE, 877, 669, 141, 919, 45, 780, 407, 164, 332, 899, 165, 726, 600, 325, 498, 655, 
    357, 752, ViewUtils.EDGE_TO_EDGE_FLAGS, 223, 849, 647, 63, 310, 863, 251, 366, 304, 282, 738, 675, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, 389, 244, 31, 121, 303, 263};
    private static final String[] CODAGEMC = {"urA", "xfs", "ypy", "unk", "xdw", "yoz", "pDA", "uls", "pBk", "eBA", "pAs", "eAk", "prA", "uvs", "xhy", "pnk", "utw", "xgz", "fDA", "pls", "fBk", "frA", "pvs", "uxy", "fnk", "ptw", "uwz", "fls", "psy", "fvs", "pxy", "ftw", "pwz", "fxy", "yrx", "ufk", "xFw", "ymz", "onA", "uds", "xEy", "olk", "ucw", "dBA", "oks", "uci", "dAk", "okg", "dAc", "ovk", "uhw", "xaz", "dnA", "ots", "ugy", "dlk", "osw", "ugj", "dks", "osi", "dvk", "oxw", "uiz", "dts", "owy", "dsw", "owj", "dxw", "oyz", "dwy", "dwj", "ofA", "uFs", "xCy", "odk", "uEw", "xCj", "clA", "ocs", "uEi", "ckk", "ocg", "ckc", "ckE", "cvA", "ohs", "uay", "ctk", "ogw", "uaj", "css", "ogi", "csg", "csa", "cxs", "oiy", "cww", "oij", "cwi", "cyy", "oFk", "uCw", "xBj", "cdA", "oEs", "uCi", "cck", "oEg", "uCb", "ccc", "oEa", "ccE", "oED", "chk", "oaw", "uDj", "cgs", "oai", "cgg", "oab", "cga", "cgD", "obj", "cib", "cFA", "oCs", "uBi", "cEk", "oCg", "uBb", "cEc", "oCa", "cEE", "oCD", "cEC", "cas", "cag", "caa", "cCk", "uAr", "oBa", "oBD", "cCB", "tfk", "wpw", "yez", "mnA", "tds", "woy", "mlk", "tcw", "woj", "FBA", "mks", "FAk", "mvk", "thw", "wqz", "FnA", "mts", "tgy", "Flk", "msw", "Fks", "Fkg", "Fvk", "mxw", "tiz", "Fts", "mwy", "Fsw", "Fsi", "Fxw", "myz", "Fwy", "Fyz", "vfA", "xps", "yuy", "vdk", "xow", "yuj", "qlA", "vcs", "xoi", "qkk", "vcg", "xob", "qkc", "vca", "mfA", "tFs", "wmy", "qvA", "mdk", "tEw", "wmj", "qtk", "vgw", "xqj", "hlA", "Ekk", "mcg", "tEb", "hkk", "qsg", "hkc", "EvA", "mhs", "tay", "hvA", "Etk", "mgw", "taj", "htk", "qww", "vij", "hss", "Esg", "hsg", "Exs", "miy", "hxs", "Eww", "mij", "hww", "qyj", "hwi", "Eyy", "hyy", "Eyj", "hyj", "vFk", "xmw", "ytj", "qdA", "vEs", "xmi", "qck", "vEg", "xmb", "qcc", "vEa", "qcE", "qcC", "mFk", "tCw", "wlj", "qhk", "mEs", "tCi", "gtA", "Eck", "vai", "tCb", "gsk", "Ecc", "mEa", "gsc", "qga", "mED", "EcC", "Ehk", "maw", "tDj", "gxk", "Egs", "mai", "gws", "qii", "mab", "gwg", "Ega", "EgD", "Eiw", "mbj", "gyw", "Eii", "gyi", "Eib", "gyb", "gzj", "qFA", "vCs", "xli", "qEk", "vCg", "xlb", "qEc", "vCa", "qEE", "vCD", "qEC", "qEB", "EFA", "mCs", "tBi", "ghA", "EEk", "mCg", "tBb", "ggk", "qag", "vDb", "ggc", "EEE", "mCD", "ggE", "qaD", "ggC", "Eas", "mDi", "gis", "Eag", "mDb", "gig", "qbb", "gia", "EaD", "giD", "gji", "gjb", "qCk", "vBg", "xkr", "qCc", "vBa", "qCE", "vBD", "qCC", "qCB", "ECk", "mBg", "tAr", "gak", "ECc", "mBa", "gac", "qDa", "mBD", "gaE", "ECC", "gaC", "ECB", "EDg", "gbg", "gba", "gbD", "vAq", "vAn", "qBB", "mAq", "EBE", "gDE", "gDC", "gDB", "lfA", "sps", "wey", "ldk", "sow", "ClA", "lcs", "soi", "Ckk", "lcg", "Ckc", "CkE", "CvA", "lhs", "sqy", "Ctk", "lgw", "sqj", "Css", "lgi", "Csg", "Csa", "Cxs", "liy", "Cww", "lij", "Cwi", "Cyy", "Cyj", "tpk", "wuw", "yhj", "ndA", "tos", "wui", "nck", "tog", "wub", "ncc", "toa", "ncE", "toD", "lFk", "smw", "wdj", "nhk", "lEs", "smi", "atA", "Cck", "tqi", "smb", "ask", "ngg", "lEa", "asc", "CcE", "asE", "Chk", "law", "snj", "axk", "Cgs", "trj", "aws", "nii", "lab", "awg", "Cga", "awa", "Ciw", "lbj", "ayw", "Cii", "ayi", "Cib", "Cjj", "azj", "vpA", "xus", "yxi", "vok", "xug", "yxb", "voc", "xua", "voE", "xuD", "voC", "nFA", g.e, "wti", "rhA", "nEk", "xvi", "wtb", "rgk", "vqg", "xvb", "rgc", "nEE", "tmD", "rgE", "vqD", "nEB", "CFA", "lCs", "sli", "ahA", "CEk", "lCg", "slb", "ixA", "agk", "nag", "tnb", "iwk", "rig", "vrb", "lCD", "iwc", "agE", "naD", "iwE", "CEB", "Cas", "lDi", "ais", "Cag", "lDb", "iys", "aig", "nbb", "iyg", "rjb", "CaD", "aiD", "Cbi", "aji", "Cbb", "izi", "ajb", "vmk", "xtg", "ywr", "vmc", "xta", "vmE", "xtD", "vmC", "vmB", "nCk", "tlg", "wsr", "rak", "nCc", "xtr", "rac", "vna", "tlD", "raE", "nCC", "raC", "nCB", "raB", "CCk", "lBg", "skr", "aak", "CCc", "lBa", "iik", "aac", "nDa", "lBD", "iic", "rba", "CCC", "iiE", "aaC", "CCB", "aaB", "CDg", "lBr", "abg", "CDa", "ijg", "aba", "CDD", "ija", "abD", "CDr", "ijr", "vlc", "xsq", "vlE", "xsn", "vlC", "vlB", "nBc", "tkq", "rDc", "nBE", "tkn", "rDE", "vln", "rDC", "nBB", "rDB", "CBc", "lAq", "aDc", "CBE", "lAn", "ibc", "aDE", "nBn", "ibE", "rDn", "CBB", "ibC", "aDB", "ibB", "aDq", "ibq", "ibn", "xsf", "vkl", "tkf", "nAm", "nAl", "CAo", "aBo", "iDo", "CAl", "aBl", "kpk", "BdA", "kos", "Bck", "kog", "seb", "Bcc", "koa", "BcE", "koD", "Bhk", "kqw", "sfj", "Bgs", "kqi", "Bgg", "kqb", "Bga", "BgD", "Biw", "krj", "Bii", "Bib", "Bjj", "lpA", "sus", "whi", "lok", "sug", "loc", "sua", "loE", "suD", "loC", "BFA", "kms", "sdi", "DhA", "BEk", "svi", "sdb", "Dgk", "lqg", "svb", "Dgc", "BEE", "kmD", "DgE", "lqD", "BEB", "Bas", "kni", "Dis", "Bag", "knb", "Dig", "lrb", "Dia", "BaD", "Bbi", "Dji", "Bbb", "Djb", "tuk", "wxg", "yir", "tuc", "wxa", "tuE", "wxD", "tuC", "tuB", "lmk", "stg", "nqk", "lmc", "sta", "nqc", "tva", "stD", "nqE", "lmC", "nqC", "lmB", "nqB", "BCk", "klg", "Dak", "BCc", "str", "bik", "Dac", "lna", "klD", "bic", "nra", "BCC", "biE", "DaC", "BCB", "DaB", "BDg", "klr", "Dbg", "BDa", "bjg", "Dba", "BDD", "bja", "DbD", "BDr", "Dbr", "bjr", "xxc", "yyq", "xxE", "yyn", "xxC", "xxB", "ttc", "wwq", "vvc", "xxq", "wwn", "vvE", "xxn", "vvC", "ttB", "vvB", "llc", "ssq", "nnc", "llE", "ssn", "rrc", "nnE", "ttn", "rrE", "vvn", "llB", "rrC", "nnB", "rrB", "BBc", "kkq", "DDc", "BBE", "kkn", "bbc", "DDE", "lln", "jjc", "bbE", "nnn", "BBB", "jjE", "rrn", "DDB", "jjC", "BBq", "DDq", "BBn", "bbq", "DDn", "jjq", "bbn", "jjn", "xwo", "yyf", "xwm", "xwl", "tso", "wwf", "vto", "xwv", "vtm", "tsl", "vtl", "lko", "ssf", "nlo", "lkm", "rno", "nlm", "lkl", "rnm", "nll", "rnl", "BAo", "kkf", "DBo", "lkv", "bDo", "DBm", "BAl", "jbo", "bDm", "DBl", "jbm", "bDl", "jbl", "DBv", "jbv", "xwd", "vsu", "vst", "nku", "rlu", "rlt", "DAu", "bBu", "jDu", "jDt", "ApA", "Aok", "keg", "Aoc", "AoE", "AoC", "Aqs", "Aqg", "Aqa", "AqD", "Ari", "Arb", "kuk", "kuc", "sha", "kuE", "shD", "kuC", "kuB", "Amk", "kdg", "Bqk", "kvg", "kda", "Bqc", "kva", "BqE", "kvD", "BqC", "AmB", "BqB", "Ang", "kdr", "Brg", "kvr", "Bra", "AnD", "BrD", "Anr", "Brr", "sxc", "sxE", "sxC", "sxB", "ktc", "lvc", "sxq", "sgn", "lvE", "sxn", "lvC", "ktB", "lvB", "Alc", "Bnc", "AlE", "kcn", "Drc", "BnE", "AlC", "DrE", "BnC", "AlB", "DrC", "BnB", "Alq", "Bnq", "Aln", "Drq", "Bnn", "Drn", "wyo", "wym", "wyl", "swo", "txo", "wyv", "txm", "swl", "txl", "kso", "sgf", "lto", "swv", "nvo", "ltm", "ksl", "nvm", "ltl", "nvl", "Ako", "kcf", "Blo", "ksv", "Dno", "Blm", "Akl", "bro", "Dnm", "Bll", "brm", "Dnl", "Akv", "Blv", "Dnv", "brv", "yze", "yzd", "wye", "xyu", "wyd", "xyt", "swe", "twu", "swd", "vxu", "twt", "vxt", "kse", "lsu", "ksd", "ntu", "lst", "rvu", "ypk", "zew", "xdA", "yos", "zei", "xck", "yog", "zeb", "xcc", "yoa", "xcE", "yoD", "xcC", "xhk", "yqw", "zfj", "utA", "xgs", "yqi", "usk", "xgg", "yqb", "usc", "xga", "usE", "xgD", "usC", "uxk", "xiw", "yrj", "ptA", "uws", "xii", "psk", "uwg", "xib", "psc", "uwa", "psE", "uwD", "psC", "pxk", "uyw", "xjj", "ftA", "pws", "uyi", "fsk", "pwg", "uyb", "fsc", "pwa", "fsE", "pwD", "fxk", "pyw", "uzj", "fws", "pyi", "fwg", "pyb", "fwa", "fyw", "pzj", "fyi", "fyb", "xFA", "yms", "zdi", "xEk", "ymg", 
    "zdb", "xEc", "yma", "xEE", "ymD", "xEC", "xEB", "uhA", "xas", "yni", "ugk", "xag", "ynb", "ugc", "xaa", "ugE", "xaD", "ugC", "ugB", "oxA", "uis", "xbi", "owk", "uig", "xbb", "owc", "uia", "owE", "uiD", "owC", "owB", "dxA", "oys", "uji", "dwk", "oyg", "ujb", "dwc", "oya", "dwE", "oyD", "dwC", "dys", "ozi", "dyg", "ozb", "dya", "dyD", "dzi", "dzb", "xCk", "ylg", "zcr", "xCc", "yla", "xCE", "ylD", "xCC", "xCB", "uak", "xDg", "ylr", "uac", "xDa", "uaE", "xDD", "uaC", "uaB", "oik", "ubg", "xDr", "oic", "uba", "oiE", "ubD", "oiC", "oiB", "cyk", "ojg", "ubr", "cyc", "oja", "cyE", "ojD", "cyC", "cyB", "czg", "ojr", "cza", "czD", "czr", "xBc", "ykq", "xBE", "ykn", "xBC", "xBB", "uDc", "xBq", "uDE", "xBn", "uDC", "uDB", "obc", "uDq", "obE", "uDn", "obC", "obB", "cjc", "obq", "cjE", "obn", "cjC", "cjB", "cjq", "cjn", "xAo", "ykf", "xAm", "xAl", "uBo", "xAv", "uBm", "uBl", "oDo", "uBv", "oDm", "oDl", "cbo", "oDv", "cbm", "cbl", "xAe", "xAd", "uAu", "uAt", "oBu", "oBt", "wpA", BooleanUtils.YES, "zFi", "wok", "yeg", "zFb", "woc", "yea", "woE", "yeD", "woC", "woB", "thA", "wqs", "yfi", "tgk", "wqg", "yfb", "tgc", "wqa", "tgE", "wqD", "tgC", "tgB", "mxA", "tis", "wri", "mwk", "tig", "wrb", "mwc", "tia", "mwE", "tiD", "mwC", "mwB", "FxA", "mys", "tji", "Fwk", "myg", "tjb", "Fwc", "mya", "FwE", "myD", "FwC", "Fys", "mzi", "Fyg", "mzb", "Fya", "FyD", "Fzi", "Fzb", "yuk", "zhg", "hjs", "yuc", "zha", "hbw", "yuE", "zhD", "hDy", "yuC", "yuB", "wmk", "ydg", "zEr", "xqk", "wmc", "zhr", "xqc", "yva", "ydD", "xqE", "wmC", "xqC", "wmB", "xqB", "tak", "wng", "ydr", "vik", "tac", "wna", "vic", "xra", "wnD", "viE", "taC", "viC", "taB", "viB", "mik", "tbg", "wnr", "qyk", "mic", "tba", "qyc", "vja", "tbD", "qyE", "miC", "qyC", "miB", "qyB", "Eyk", "mjg", "tbr", "hyk", "Eyc", "mja", "hyc", "qza", "mjD", "hyE", "EyC", "hyC", "EyB", "Ezg", "mjr", "hzg", "Eza", "hza", "EzD", "hzD", "Ezr", "ytc", "zgq", "grw", "ytE", "zgn", "gny", "ytC", "glz", "ytB", "wlc", "ycq", "xnc", "wlE", "ycn", "xnE", "ytn", "xnC", "wlB", "xnB", "tDc", "wlq", "vbc", "tDE", "wln", "vbE", "xnn", "vbC", "tDB", "vbB", "mbc", "tDq", "qjc", "mbE", "tDn", "qjE", "vbn", "qjC", "mbB", "qjB", "Ejc", "mbq", "gzc", "EjE", "mbn", "gzE", "qjn", "gzC", "EjB", "gzB", "Ejq", "gzq", "Ejn", "gzn", "yso", "zgf", "gfy", "ysm", "gdz", "ysl", "wko", "ycf", "xlo", "ysv", "xlm", "wkl", "xll", "tBo", "wkv", "vDo", "tBm", "vDm", "tBl", "vDl", "mDo", "tBv", "qbo", "vDv", "qbm", "mDl", "qbl", "Ebo", "mDv", "gjo", "Ebm", "gjm", "Ebl", "gjl", "Ebv", "gjv", "yse", "gFz", "ysd", "wke", "xku", "wkd", "xkt", "tAu", "vBu", "tAt", "vBt", "mBu", "qDu", "mBt", "qDt", "EDu", "gbu", "EDt", "gbt", "ysF", "wkF", "xkh", "tAh", "vAx", "mAx", "qBx", "wek", "yFg", "zCr", "wec", "yFa", "weE", "yFD", "weC", "weB", "sqk", "wfg", "yFr", "sqc", "wfa", "sqE", "wfD", "sqC", "sqB", "lik", "srg", "wfr", g.b, "sra", "liE", "srD", "liC", "liB", "Cyk", "ljg", "srr", "Cyc", "lja", "CyE", "ljD", "CyC", "CyB", "Czg", "ljr", "Cza", "CzD", "Czr", "yhc", "zaq", "arw", "yhE", "zan", "any", "yhC", "alz", "yhB", "wdc", "yEq", "wvc", "wdE", "yEn", "wvE", "yhn", "wvC", "wdB", "wvB", "snc", "wdq", "trc", "snE", "wdn", "trE", "wvn", "trC", "snB", "trB", "lbc", "snq", "njc", "lbE", "snn", "njE", "trn", "njC", "lbB", "njB", "Cjc", "lbq", "azc", "CjE", "lbn", "azE", "njn", "azC", "CjB", "azB", "Cjq", "azq", "Cjn", "azn", "zio", "irs", "rfy", "zim", "inw", "rdz", "zil", "ily", "ikz", "ygo", "zaf", "afy", "yxo", "ziv", "ivy", "adz", "yxm", "ygl", "itz", "yxl", "wco", "yEf", "wto", "wcm", "xvo", "yxv", "wcl", "xvm", "wtl", "xvl", "slo", "wcv", "tno", "slm", "vro", "tnm", "sll", "vrm", "tnl", "vrl", "lDo", "slv", "nbo", "lDm", "rjo", "nbm", "lDl", "rjm", "nbl", "rjl", "Cbo", "lDv", "ajo", "Cbm", "izo", "ajm", "Cbl", "izm", "ajl", "izl", "Cbv", "ajv", "zie", "ifw", "rFz", "zid", "idy", "icz", "yge", "aFz", "ywu", "ygd", "ihz", "ywt", "wce", "wsu", "wcd", "xtu", "wst", "xtt", "sku", "tlu", "skt", "vnu", "tlt", "vnt", "lBu", "nDu", "lBt", "rbu", "nDt", "rbt", "CDu", "abu", "CDt", "iju", "abt", "ijt", "ziF", "iFy", "iEz", "ygF", "ywh", "wcF", "wsh", "xsx", "skh", "tkx", "vlx", "lAx", "nBx", "rDx", "CBx", "aDx", "ibx", "iCz", "wFc", "yCq", "wFE", "yCn", "wFC", "wFB", "sfc", "wFq", "sfE", "wFn", "sfC", "sfB", "krc", "sfq", "krE", "sfn", "krC", "krB", "Bjc", "krq", "BjE", "krn", "BjC", "BjB", "Bjq", "Bjn", "yao", "zDf", "Dfy", "yam", "Ddz", "yal", "wEo", "yCf", "who", "wEm", "whm", "wEl", "whl", "sdo", "wEv", "svo", "sdm", "svm", "sdl", "svl", "kno", "sdv", "lro", "knm", "lrm", "knl", "lrl", "Bbo", "knv", "Djo", "Bbm", "Djm", "Bbl", "Djl", "Bbv", "Djv", "zbe", "bfw", "npz", "zbd", "bdy", "bcz", "yae", "DFz", "yiu", "yad", "bhz", "yit", "wEe", "wgu", "wEd", "wxu", "wgt", "wxt", "scu", "stu", "sct", "tvu", "stt", "tvt", "klu", "lnu", "klt", "nru", "lnt", "nrt", "BDu", "Dbu", "BDt", "bju", "Dbt", "bjt", "jfs", "rpy", "jdw", "roz", "jcy", "jcj", "zbF", "bFy", "zjh", "jhy", "bEz", "jgz", "yaF", "yih", "yyx", "wEF", "wgh", "wwx", "xxx", "sch", "ssx", "ttx", "vvx", "kkx", "llx", "nnx", "rrx", "BBx", "DDx", "bbx", "jFw", "rmz", "jEy", "jEj", "bCz", "jaz", "jCy", "jCj", "jBj", "wCo", "wCm", "wCl", "sFo", "wCv", "sFm", "sFl", "kfo", "sFv", "kfm", "kfl", "Aro", "kfv", "Arm", "Arl", "Arv", "yDe", "Bpz", "yDd", "wCe", "wau", "wCd", "wat", "sEu", "shu", "sEt", "sht", "kdu", "kvu", "kdt", "kvt", "Anu", "Bru", "Ant", "Brt", "zDp", "Dpy", "Doz", "yDF", "ybh", "wCF", "wah", "wix", "sEh", "sgx", "sxx", "kcx", "ktx", "lvx", "Alx", "Bnx", "Drx", "bpw", "nuz", "boy", "boj", "Dmz", "bqz", "jps", "ruy", "jow", "ruj", "joi", "job", "bmy", "jqy", "bmj", "jqj", "jmw", "rtj", "jmi", "jmb", "blj", "jnj", "jli", "jlb", "jkr", "sCu", "sCt", "kFu", "kFt", "Afu", "Aft", "wDh", "sCh", "sax", "kEx", "khx", "Adx", "Avx", "Buz", "Duy", "Duj", "buw", "nxj", "bui", "bub", "Dtj", "bvj", "jus", "rxi", "jug", "rxb", "jua", "juD", "bti", "jvi", "btb", "jvb", "jtg", "rwr", "jta", "jtD", "bsr", "jtr", "jsq", "jsn", "Bxj", "Dxi", "Dxb", "bxg", "nyr", "bxa", "bxD", "Dwr", "bxr", "bwq", "bwn", "pjk", "urw", "ejA", "pbs", "uny", "ebk", "pDw", "ulz", "eDs", "pBy", "eBw", "zfc", "fjk", "prw", "zfE", "fbs", "pny", "zfC", "fDw", "plz", "zfB", "fBy", "yrc", "zfq", "frw", "yrE", "zfn", "fny", "yrC", "flz", "yrB", "xjc", "yrq", "xjE", "yrn", "xjC", "xjB", "uzc", "xjq", "uzE", "xjn", "uzC", "uzB", "pzc", "uzq", "pzE", "uzn", "pzC", "djA", "ors", "ufy", "dbk", "onw", "udz", "dDs", "oly", "dBw", "okz", "dAy", "zdo", "drs", "ovy", "zdm", "dnw", "otz", "zdl", "dly", "dkz", "yno", "zdv", "dvy", "ynm", "dtz", "ynl", "xbo", "ynv", "xbm", "xbl", "ujo", "xbv", "ujm", "ujl", "ozo", "ujv", "ozm", "ozl", "crk", "ofw", "uFz", "cns", "ody", "clw", "ocz", "cky", "ckj", "zcu", "cvw", "ohz", "zct", "cty", "csz", "ylu", "cxz", "ylt", "xDu", "xDt", "ubu", "ubt", "oju", "ojt", "cfs", "oFy", "cdw", "oEz", "ccy", "ccj", "zch", "chy", "cgz", "ykx", "xBx", "uDx", "cFw", "oCz", "cEy", "cEj", "caz", "cCy", "cCj", "FjA", "mrs", "tfy", "Fbk", "mnw", "tdz", "FDs", "mly", "FBw", "mkz", "FAy", "zFo", "Frs", 
    "mvy", "zFm", "Fnw", "mtz", "zFl", "Fly", "Fkz", "yfo", "zFv", "Fvy", "yfm", "Ftz", "yfl", "wro", "yfv", "wrm", "wrl", "tjo", "wrv", "tjm", "tjl", "mzo", "tjv", "mzm", "mzl", "qrk", "vfw", "xpz", "hbA", "qns", "vdy", "hDk", "qlw", "vcz", "hBs", "qky", "hAw", "qkj", "hAi", "Erk", "mfw", "tFz", "hrk", "Ens", "mdy", "hns", "qty", "mcz", "hlw", "Eky", "hky", "Ekj", "hkj", "zEu", "Evw", "mhz", "zhu", "zEt", "hvw", "Ety", "zht", "hty", "Esz", "hsz", "ydu", "Exz", "yvu", "ydt", "hxz", "yvt", "wnu", "xru", "wnt", "xrt", "tbu", "vju", "tbt", "vjt", "mju", "mjt", "grA", "qfs", "vFy", "gnk", "qdw", "vEz", "gls", "qcy", "gkw", "qcj", "gki", "gkb", "Efs", "mFy", "gvs", "Edw", "mEz", "gtw", "qgz", "gsy", "Ecj", "gsj", "zEh", "Ehy", "zgx", "gxy", "Egz", "gwz", "ycx", "ytx", "wlx", "xnx", "tDx", "vbx", "mbx", "gfk", "qFw", "vCz", "gds", "qEy", "gcw", "qEj", "gci", "gcb", "EFw", "mCz", "ghw", "EEy", "ggy", "EEj", "ggj", "Eaz", "giz", "gFs", "qCy", "gEw", "qCj", "gEi", "gEb", "ECy", "gay", "ECj", "gaj", "gCw", "qBj", "gCi", "gCb", "EBj", "gDj", "gBi", "gBb", "Crk", "lfw", "spz", "Cns", "ldy", "Clw", "lcz", "Cky", "Ckj", "zCu", "Cvw", "lhz", "zCt", "Cty", "Csz", "yFu", "Cxz", "yFt", "wfu", "wft", "sru", "srt", "lju", "ljt", "arA", "nfs", "tpy", "ank", "ndw", "toz", "als", "ncy", "akw", "ncj", "aki", "akb", "Cfs", "lFy", "avs", "Cdw", "lEz", "atw", "ngz", "asy", "Ccj", "asj", "zCh", "Chy", "zax", "axy", "Cgz", "awz", "yEx", "yhx", "wdx", "wvx", "snx", "trx", "lbx", "rfk", "vpw", "xuz", "inA", "rds", "voy", "ilk", "rcw", "voj", "iks", "rci", "ikg", "rcb", "ika", "afk", "nFw", "tmz", "ivk", "ads", "nEy", "its", "rgy", "nEj", "isw", "aci", "isi", "acb", "isb", "CFw", "lCz", "ahw", "CEy", "ixw", "agy", "CEj", "iwy", "agj", "iwj", "Caz", "aiz", "iyz", "ifA", "rFs", "vmy", "idk", "rEw", "vmj", "ics", "rEi", "icg", "rEb", "ica", "icD", "aFs", "nCy", "ihs", "aEw", "nCj", "igw", "raj", "igi", "aEb", "igb", "CCy", "aay", "CCj", "iiy", "aaj", "iij", "iFk", "rCw", "vlj", "iEs", "rCi", "iEg", "rCb", "iEa", "iED", "aCw", "nBj", "iaw", "aCi", "iai", "aCb", "iab", "CBj", "aDj", "ibj", "iCs", "rBi", "iCg", "rBb", "iCa", "iCD", "aBi", "iDi", "aBb", "iDb", "iBg", "rAr", "iBa", "iBD", "aAr", "iBr", "iAq", "iAn", "Bfs", "kpy", "Bdw", "koz", "Bcy", "Bcj", "Bhy", "Bgz", "yCx", "wFx", "sfx", "krx", "Dfk", "lpw", "suz", "Dds", "loy", "Dcw", "loj", "Dci", "Dcb", "BFw", "kmz", "Dhw", "BEy", "Dgy", "BEj", "Dgj", "Baz", "Diz", "bfA", "nps", "tuy", "bdk", "now", "tuj", "bcs", "noi", "bcg", "nob", "bca", "bcD", "DFs", "lmy", "bhs", "DEw", "lmj", "bgw", "DEi", "bgi", "DEb", "bgb", "BCy", "Day", "BCj", "biy", "Daj", "bij", "rpk", "vuw", "xxj", "jdA", "ros", "vui", "jck", "rog", "vub", "jcc", "roa", "jcE", "roD", "jcC", "bFk", "nmw", "ttj", "jhk", "bEs", "nmi", "jgs", "rqi", "nmb", "jgg", "bEa", "jga", "bED", "jgD", "DCw", "llj", "baw", "DCi", "jiw", "bai", "DCb", "jii", "bab", "jib", "BBj", "DDj", "bbj", "jjj", "jFA", "rms", "vti", "jEk", "rmg", "vtb", "jEc", "rma", "jEE", "rmD", "jEC", "jEB", "bCs", "nli", "jas", "bCg", "nlb", "jag", "rnb", "jaa", "bCD", "jaD", "DBi", "bDi", "DBb", "jbi", "bDb", "jbb", "jCk", "rlg", "vsr", "jCc", "rla", "jCE", "rlD", "jCC", "jCB", "bBg", "nkr", "jDg", "bBa", "jDa", "bBD", "jDD", "DAr", "bBr", "jDr", "jBc", "rkq", "jBE", "rkn", "jBC", "jBB", "bAq", "jBq", "bAn", "jBn", "jAo", "rkf", "jAm", "jAl", "bAf", "jAv", "Apw", "kez", "Aoy", "Aoj", "Aqz", "Bps", "kuy", "Bow", "kuj", "Boi", "Bob", "Amy", "Bqy", "Amj", "Bqj", "Dpk", "luw", "sxj", "Dos", "lui", "Dog", "lub", "Doa", "DoD", "Bmw", "ktj", "Dqw", "Bmi", "Dqi", "Bmb", "Dqb", "Alj", "Bnj", "Drj", "bpA", "nus", "txi", "bok", "nug", "txb", "boc", "nua", "boE", "nuD", "boC", "boB", "Dms", "lti", "bqs", "Dmg", "ltb", "bqg", "nvb", "bqa", "DmD", "bqD", "Bli", "Dni", "Blb", "bri", "Dnb", "brb", "ruk", "vxg", "xyr", "ruc", "vxa", "ruE", "vxD", "ruC", "ruB", "bmk", "ntg", "twr", "jqk", "bmc", "nta", "jqc", "rva", "ntD", "jqE", "bmC", "jqC", "bmB", "jqB", "Dlg", "lsr", "bng", "Dla", "jrg", "bna", "DlD", "jra", "bnD", "jrD", "Bkr", "Dlr", "bnr", "jrr", "rtc", "vwq", "rtE", "vwn", "rtC", "rtB", "blc", "nsq", "jnc", "blE", "nsn", "jnE", "rtn", "jnC", "blB", "jnB", "Dkq", "blq", "Dkn", "jnq", "bln", "jnn", "rso", "vwf", "rsm", "rsl", "bko", "nsf", "jlo", "bkm", "jlm", "bkl", "jll", "Dkf", "bkv", "jlv", "rse", "rsd", "bke", "jku", "bkd", "jkt", "Aey", "Aej", "Auw", "khj", "Aui", "Aub", "Adj", "Avj", "Bus", "kxi", "Bug", "kxb", "Bua", "BuD", "Ati", "Bvi", "Atb", "Bvb", "Duk", "lxg", "syr", "Duc", "lxa", "DuE", "lxD", "DuC", "DuB", "Btg", "kwr", "Dvg", "lxr", "Dva", "BtD", "DvD", "Asr", "Btr", "Dvr", "nxc", "tyq", "nxE", "tyn", "nxC", "nxB", "Dtc", "lwq", "bvc", "nxq", "lwn", "bvE", "DtC", "bvC", "DtB", "bvB", "Bsq", "Dtq", "Bsn", "bvq", "Dtn", "bvn", "vyo", "xzf", "vym", "vyl", "nwo", "tyf", "rxo", "nwm", "rxm", "nwl", "rxl", "Dso", "lwf", "bto", "Dsm", "jvo", "btm", "Dsl", DebugImage.JVM, "btl", "jvl", "Bsf", "Dsv", "btv", "jvv", "vye", "vyd", "nwe", "rwu", "nwd", "rwt", "Dse", "bsu", "Dsd", "jtu", "bst", "jtt", "vyF", "nwF", "rwh", "DsF", "bsh", "jsx", "Ahi", "Ahb", "Axg", "kir", "Axa", "AxD", "Agr", "Axr", "Bxc", "kyq", "BxE", "kyn", "BxC", "BxB", "Awq", "Bxq", "Awn", "Bxn", "lyo", "szf", "lym", "lyl", "Bwo", "kyf", "Dxo", "lyv", "Dxm", "Bwl", "Dxl", "Awf", "Bwv", "Dxv", "tze", "tzd", "lye", "nyu", "lyd", "nyt", "Bwe", "Dwu", "Bwd", "bxu", "Dwt", "bxt", "tzF", "lyF", "nyh", "BwF", "Dwh", "bwx", "Aiq", "Ain", "Ayo", "kjf", "Aym", "Ayl", "Aif", "Ayv", "kze", "kzd", "Aye", "Byu", "Ayd", "Byt", "szp"};
    private static final char[] BR_SET = {'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '*', '+', '-'};
    private static final String[] PDF_TTF = {"00000", "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010", "01011", "01100", "01101", "01110", "01111", "10000", "10001", "10010", "10011", "10100", "10101", "10110", "10111", "11000", "11001", "11010", "11011", "11100", "11101", "11110", "11111", "01", "1111111101010100", "11111101000101001"};
    private static final int[] ASCII_X = {7, 8, 8, 4, 12, 4, 4, 8, 8, 8, 12, 4, 12, 12, 12, 12, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 12, 8, 8, 4, 8, 8, 8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 8, 8, 8, 4, 8, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 8, 8, 8};
    private static final int[] ASCII_Y = {26, 10, 20, 15, 18, 21, 10, 28, 23, 24, 22, 20, 13, 16, 17, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 14, 0, 1, 23, 2, 25, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 4, 5, 6, 24, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 21, 27, 9};
    private static final int[] MICRO_AUTOSIZE = {4, 6, 7, 8, 8, 10, 10, 12, 12, 13, 14, 16, 18, 18, 19, 20, 24, 24, 24, 29, 30, 33, 34, 37, 39, 46, 54, 58, 70, 72, 82, 90, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, WebSocketProtocol.PAYLOAD_SHORT, 1, 14, 2, 7, 24, 3, 15, 25, 4, 8, 16, 5, 17, 26, 9, 6, 10, 18, 27, 11, 28, 12, 19, 13, 29, 20, 30, 21, 22, 31, 23, 32, 33, 34};
    private static final int[] MICRO_VARIANTS = {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 11, 14, 17, 20, 24, 28, 8, 11, 14, 17, 20, 23, 26, 6, 8, 10, 12, 15, 20, 26, 32, 38, 44, 4, 6, 8, 10, 12, 15, 20, 26, 32, 38, 44, 7, 7, 7, 8, 8, 8, 8, 9, 9, 10, 11, 13, 15, 12, 14, 16, 18, 21, 26, 32, 38, 44, 50, 8, 12, 14, 16, 18, 21, 26, 32, 38, 44, 50, 0, 0, 0, 7, 7, 7, 7, 15, 15, 24, 34, 57, 84, 45, 70, 99, 115, 133, 154, 180, 212, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 294, 7, 45, 70, 99, 115, 133, 154, 180, 212, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 294};
    private static final int[] RAP_TABLE = {1, 8, 36, 19, 9, 25, 1, 1, 8, 36, 19, 9, 27, 1, 7, 15, 25, 37, 1, 1, 21, 15, 1, 47, 1, 7, 15, 25, 37, 1, 1, 21, 15, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 7, 15, 25, 37, 17, 9, 29, 31, 25, 19, 1, 7, 15, 25, 37, 17, 9, 29, 31, 25, 9, 8, 36, 19, 17, 33, 1, 9, 8, 36, 19, 17, 35, 1, 7, 15, 25, 37, 33, 17, 37, 47, 49, 43, 1, 7, 15, 25, 37, 33, 17, 37, 47, 49, 0, 3, 6, 0, 6, 0, 0, 0, 3, 6, 0, 6, 6, 0, 0, 6, 0, 0, 0, 0, 6, 6, 0, 3, 0, 0, 6, 0, 0, 0, 0, 6, 6, 0};
    private static final String[] RAPLR = {"", "221311", "311311", "312211", "222211", "213211", "214111", "223111", "313111", "322111", "412111", "421111", "331111", "241111", "232111", "231211", "321211", "411211", "411121", "411112", "321112", "312112", "311212", "311221", "311131", "311122", "311113", "221113", "221122", "221131", "221221", "222121", "312121", "321121", "231121", "231112", "222112", "213112", "212212", "212221", "212131", "212122", "212113", "211213", "211123", "211132", "211141", "211231", "211222", "211312", "211321", "211411", "212311"};
    private static final String[] RAPC = {"", "112231", "121231", "122131", "131131", "131221", "132121", "141121", "141211", "142111", "133111", "132211", "131311", "122311", "123211", "124111", "115111", "114211", "114121", "123121", "123112", "122212", "122221", "121321", "121411", "112411", "113311", "113221", "113212", "113122", "122122", "131122", "131113", "122113", "113113", "112213", "112222", "112312", "112321", "111421", "111331", "111322", "111232", "111223", "111133", "111124", "111214", "112114", "121114", "121123", "121132", "112132", "112141"};
    private static final int[] MICRO_COEFFS = {76, 925, 537, 597, 784, 691, 437, 237, 308, 436, 284, 646, 653, 428, 379, 567, 527, 622, 257, 289, 362, TypedValues.PositionType.TYPE_TRANSITION_EASING, 441, 205, 377, 457, 64, 244, 826, 841, 818, 691, 266, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_ID, 462, 45, 565, 708, 825, 213, 15, 68, 327, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, TypedValues.Custom.TYPE_BOOLEAN, 597, 864, 757, ComposerKt.providerKey, 646, 684, 347, 127, 388, 7, 69, 851, 764, 713, 342, 384, TypedValues.MotionType.TYPE_ANIMATE_CIRCLEANGLE_TO, 583, 322, 592, 678, ComposerKt.providerMapsKey, 184, 394, 692, 669, 677, 154, 187, 241, 286, 274, 354, 478, 915, 691, 833, 105, 215, 460, 829, 476, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, TypedValues.Custom.TYPE_BOOLEAN, 664, 230, 5, 80, 74, 550, 575, 147, 868, 642, 274, 562, 232, 755, HttpStatusCodeRange.DEFAULT_MAX, 524, 801, 132, 295, 116, 442, 428, 295, 42, 176, 65, 279, 577, 315, 624, 37, 855, 275, 739, 120, 297, 312, ComposerKt.compositionLocalMapKey, 560, 321, 233, 756, 760, 573, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 519, 781, 534, 129, TypedValues.CycleType.TYPE_WAVE_PHASE, 681, 553, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 716, 763, 693, 624, TypedValues.MotionType.TYPE_QUANTIZE_MOTIONSTEPS, 310, 691, 347, 165, 193, 259, 568, 443, 284, 887, 544, 788, 93, 477, 760, 331, TypedValues.MotionType.TYPE_DRAW_PATH, 269, 121, 159, 830, 446, 893, 699, 245, 441, 454, 325, 858, 131, 847, 764, 169, 361, 575, 922, 525, 176, 586, 640, 321, 536, 742, 677, 742, 687, 284, 193, 517, 273, 494, 263, 147, 593, 800, 571, 320, 803, 133, 231, 390, 685, 330, 63, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, 234, 228, 438, 848, 133, 703, 529, 721, 788, 322, 280, 159, 738, 586, 388, 684, 445, 680, 245, 595, 614, 233, 812, 32, 284, 658, 745, 229, 95, 689, 920, 771, 554, 289, 231, 125, 117, 518, 476, 36, 659, 848, 678, 64, 764, 840, 157, 915, 470, 876, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 25, 632, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, 417, 436, 714, 60, 376, 97, WalletConstants.ERROR_CODE_UNKNOWN, TypedValues.TransitionType.TYPE_STAGGERED, 446, 21, 3, 773, 569, 267, 272, 213, 31, 560, 231, 758, b.i, 271, 572, 436, 339, 730, 82, 285, 923, 797, 576, 875, 156, TypedValues.TransitionType.TYPE_STAGGERED, 63, 81, 257, 874, WalletConstants.ERROR_CODE_AUTHENTICATION_FAILURE, 416, 778, 50, 205, 303, 188, 535, 909, 155, 637, 230, 534, 96, 575, 102, 264, 233, 919, 593, 865, 26, 579, 623, 766, 146, 10, 739, 246, 127, 71, 244, 211, 477, 920, 876, 427, 820, 718, 435};
    private int[] codeWords = new int[2700];
    private Mode symbolMode = Mode.NORMAL;
    private int preferredEccLevel = -1;
    private int structuredAppendFileId = 0;
    private int structuredAppendPosition = 1;
    private int structuredAppendTotal = 1;

    private enum EncodingMode {
        FALSE,
        TEX,
        BYT,
        NUM
    }

    public enum Mode {
        NORMAL,
        TRUNCATED,
        MICRO
    }

    public Pdf417() {
        setBarHeight(3);
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    public void setBarHeight(int i) {
        super.setBarHeight(i);
    }

    public void setDataColumns(int i) {
        this.columns = Integer.valueOf(i);
    }

    public Integer getDataColumns() {
        return this.columns;
    }

    public void setRows(int i) {
        this.rows = Integer.valueOf(i);
    }

    public Integer getRows() {
        return this.rows;
    }

    public void setPreferredEccLevel(int i) {
        if (i < 0 || i > 8) {
            throw new IllegalArgumentException("ECC level must be between 0 and 8.");
        }
        this.preferredEccLevel = i;
    }

    public int getPreferredEccLevel() {
        return this.preferredEccLevel;
    }

    public void setVariant(int i) {
        if (this.symbolMode != Mode.MICRO) {
            throw new IllegalArgumentException("Can only set variant when using MICRO mode.");
        }
        if (i < 1 || i > 34) {
            throw new IllegalArgumentException("Variant must be between 1 and 34.");
        }
        int[] iArr = MICRO_VARIANTS;
        this.columns = Integer.valueOf(iArr[i - 1]);
        this.rows = Integer.valueOf(iArr[i + 33]);
    }

    public void setStructuredAppendPosition(int i) {
        if (i < 1 || i > 99999) {
            throw new IllegalArgumentException("Invalid PDF417 structured append position: " + i);
        }
        this.structuredAppendPosition = i;
    }

    public int getStructuredAppendPosition() {
        return this.structuredAppendPosition;
    }

    public void setStructuredAppendTotal(int i) {
        if (i < 1 || i > 99999) {
            throw new IllegalArgumentException("Invalid PDF417 structured append total: " + i);
        }
        this.structuredAppendTotal = i;
    }

    public int getStructuredAppendTotal() {
        return this.structuredAppendTotal;
    }

    public void setStructuredAppendFileId(int i) {
        if (i < 0 || i > 899) {
            throw new IllegalArgumentException("Invalid PDF417 structured append file ID: " + i);
        }
        this.structuredAppendFileId = i;
    }

    public int getStructuredAppendFileId() {
        return this.structuredAppendFileId;
    }

    public void setStructuredAppendFileName(String str) {
        this.structuredAppendFileName = str;
    }

    public String getStructuredAppendFileName() {
        return this.structuredAppendFileName;
    }

    public void setStructuredAppendIncludeSegmentCount(boolean z) {
        this.structuredAppendIncludeSegmentCount = z;
    }

    public boolean getStructuredAppendIncludeSegmentCount() {
        return this.structuredAppendIncludeSegmentCount;
    }

    public void setMode(Mode mode) {
        this.symbolMode = mode;
    }

    public Mode getMode() {
        return this.symbolMode;
    }

    public void setContent(byte[] bArr) {
        super.setContent(new String(bArr, StandardCharsets.ISO_8859_1));
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        eciProcess();
        if (AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$Pdf417$Mode[this.symbolMode.ordinal()] == 1) {
            processMicroPdf417();
        } else {
            processPdf417();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01a7 A[LOOP:1: B:52:0x01a5->B:53:0x01a7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01c7 A[LOOP:2: B:56:0x01c3->B:58:0x01c7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x025a A[LOOP:3: B:64:0x0258->B:65:0x025a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void processPdf417() {
        /*
            Method dump skipped, instructions count: 1050
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.Pdf417.processPdf417():void");
    }

    /* renamed from: uk.org.okapibarcode.backend.Pdf417$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$Pdf417$EncodingMode;
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$Pdf417$Mode;

        static {
            int[] iArr = new int[EncodingMode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$Pdf417$EncodingMode = iArr;
            try {
                iArr[EncodingMode.TEX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Pdf417$EncodingMode[EncodingMode.BYT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Pdf417$EncodingMode[EncodingMode.NUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Mode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$Pdf417$Mode = iArr2;
            try {
                iArr2[Mode.MICRO.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Pdf417$Mode[Mode.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$Pdf417$Mode[Mode.TRUNCATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void processMicroPdf417() {
        int i;
        int[] iArr = new int[5];
        int[] iArr2 = new int[50];
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        List<Block> createBlocks = createBlocks(this.inputData);
        this.codeWordCount = 0;
        if (this.readerInit) {
            int[] iArr3 = this.codeWords;
            int i2 = this.codeWordCount;
            iArr3[i2] = 921;
            this.codeWordCount = i2 + 1;
        }
        processEci(this.eciMode);
        int i3 = 0;
        int i4 = 0;
        while (i3 < createBlocks.size()) {
            Block block = createBlocks.get(i3);
            int i5 = AnonymousClass1.$SwitchMap$uk$org$okapibarcode$backend$Pdf417$EncodingMode[block.mode.ordinal()];
            if (i5 == 1) {
                processText(this.inputData, i4, block.length, false);
            } else if (i5 == 2) {
                processBytes(this.inputData, i4, block.length, i3 == 0 ? EncodingMode.TEX : createBlocks.get(i3 - 1).mode);
            } else if (i5 == 3) {
                processNumbers(this.inputData, i4, block.length, false);
            } else {
                throw new OkapiException("Unknown block type: " + block.mode);
            }
            i4 += block.length;
            i3++;
        }
        int i6 = this.codeWordCount;
        int addMacroCodewords = addMacroCodewords();
        validateRows(4, 44);
        validateColumns(1, 4);
        Integer num = this.columns;
        if (num != null) {
            int intValue = num.intValue();
            if (intValue == 1) {
                i = 20;
            } else if (intValue == 2) {
                i = 37;
            } else if (intValue == 3) {
                i = 82;
            } else {
                if (intValue != 4) {
                    throw new OkapiException("Invalid column count: " + this.columns);
                }
                i = WebSocketProtocol.PAYLOAD_SHORT;
            }
            if (this.codeWordCount > i) {
                throw new OkapiException("Too few columns (" + this.columns + ") to hold data codewords (" + this.codeWordCount + ")");
            }
        }
        int microPdf417Variant = getMicroPdf417Variant(this.codeWordCount, this.columns, this.rows);
        int i7 = microPdf417Variant - 1;
        int[] iArr4 = MICRO_VARIANTS;
        this.columns = Integer.valueOf(iArr4[i7]);
        int i8 = microPdf417Variant + 33;
        this.rows = Integer.valueOf(iArr4[i8]);
        int i9 = microPdf417Variant + 67;
        int i10 = iArr4[i9];
        int intValue2 = (this.columns.intValue() * this.rows.intValue()) - i10;
        int i11 = this.codeWordCount;
        int i12 = intValue2 - i11;
        int i13 = microPdf417Variant + 101;
        int i14 = iArr4[i13];
        int i15 = i11 - addMacroCodewords;
        int[] iArr5 = this.codeWords;
        StringBuilder sb3 = sb2;
        int i16 = i15 + i12;
        System.arraycopy(iArr5, i15, iArr5, i16, addMacroCodewords);
        Arrays.fill(this.codeWords, i15, i16, TypedValues.Custom.TYPE_INT);
        this.codeWordCount += i12;
        info("Codewords: ");
        for (int i17 = 0; i17 < this.codeWordCount; i17++) {
            infoSpace(this.codeWords[i17]);
        }
        infoLine();
        infoLine("Data Codewords: " + i6);
        infoLine("Padding Codewords: " + i12);
        infoLine("Macro Codewords: " + addMacroCodewords);
        infoLine("ECC Codewords: " + i10);
        infoLine("Total Codewords: " + (this.columns.intValue() * this.rows.intValue()));
        int i18 = this.codeWordCount;
        for (int i19 = 0; i19 < 50; i19++) {
            iArr2[i19] = 0;
        }
        for (int i20 = 0; i20 < i18; i20++) {
            int i21 = i10 - 1;
            int i22 = (this.codeWords[i20] + iArr2[i21]) % PDF417Common.NUMBER_OF_CODEWORDS;
            while (i21 >= 0) {
                if (i21 == 0) {
                    iArr2[i21] = (929 - ((MICRO_COEFFS[i14 + i21] * i22) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS;
                } else {
                    iArr2[i21] = ((iArr2[i21 - 1] + PDF417Common.NUMBER_OF_CODEWORDS) - ((MICRO_COEFFS[i14 + i21] * i22) % PDF417Common.NUMBER_OF_CODEWORDS)) % PDF417Common.NUMBER_OF_CODEWORDS;
                }
                i21--;
            }
        }
        for (int i23 = 0; i23 < i10; i23++) {
            int i24 = iArr2[i23];
            if (i24 != 0) {
                iArr2[i23] = 929 - i24;
            }
        }
        int i25 = 1;
        int i26 = i10 - 1;
        while (i26 >= 0) {
            int[] iArr6 = this.codeWords;
            int i27 = this.codeWordCount;
            iArr6[i27] = iArr2[i26];
            this.codeWordCount = i27 + i25;
            i26--;
            i25 = 1;
        }
        int[] iArr7 = RAP_TABLE;
        int i28 = iArr7[i7];
        int i29 = iArr7[i8];
        int i30 = iArr7[i9];
        int i31 = iArr7[i13] / 3;
        this.readable = "";
        this.pattern = new String[this.rows.intValue()];
        this.row_count = this.rows.intValue();
        this.row_height = new int[this.rows.intValue()];
        infoLine("Grid Size: " + this.columns + " X " + this.row_count);
        int i32 = 0;
        while (i32 < this.rows.intValue()) {
            int i33 = 0;
            sb.setLength(0);
            int i34 = i31 * PDF417Common.NUMBER_OF_CODEWORDS;
            int i35 = 0;
            while (i35 < 5) {
                iArr[i35] = i33;
                i35++;
                i33 = 0;
            }
            int i36 = 0;
            while (i36 < this.columns.intValue()) {
                int i37 = i36 + 1;
                iArr[i37] = this.codeWords[(this.columns.intValue() * i32) + i36];
                i36 = i37;
            }
            String[] strArr = RAPLR;
            sb.append(strArr[i28]);
            sb.append('1');
            String[] strArr2 = CODAGEMC;
            sb.append(strArr2[iArr[1] + i34]);
            sb.append('1');
            if (this.columns.intValue() == 3) {
                sb.append(RAPC[i29]);
            }
            if (this.columns.intValue() >= 2) {
                sb.append('1');
                sb.append(strArr2[iArr[2] + i34]);
                sb.append('1');
            }
            if (this.columns.intValue() == 4) {
                sb.append(RAPC[i29]);
            }
            if (this.columns.intValue() >= 3) {
                sb.append('1');
                sb.append(strArr2[iArr[3] + i34]);
                sb.append('1');
            }
            if (this.columns.intValue() == 4) {
                sb.append('1');
                sb.append(strArr2[i34 + iArr[4]]);
                sb.append('1');
            }
            sb.append(strArr[i30]);
            sb.append('1');
            StringBuilder sb4 = sb3;
            sb4.setLength(0);
            boolean z = true;
            for (int i38 = 0; i38 < sb.length(); i38++) {
                if (sb.charAt(i38) >= '0' && sb.charAt(i38) <= '9') {
                    for (int i39 = 0; i39 < Character.getNumericValue(sb.charAt(i38)); i39++) {
                        if (!z) {
                            sb4.append('0');
                        } else {
                            sb4.append('1');
                        }
                    }
                    z = !z;
                } else {
                    sb4.append(PDF_TTF[uk.org.okapibarcode.util.Arrays.positionOf(sb.charAt(i38), BR_SET)]);
                }
            }
            this.pattern[i32] = bin2pat(sb4);
            this.row_height[i32] = this.default_height;
            i28++;
            i29++;
            i30++;
            i31++;
            if (i28 == 53) {
                i28 = 1;
            }
            if (i29 == 53) {
                i29 = 1;
            }
            if (i30 == 53) {
                i30 = 1;
            }
            if (i31 == 3) {
                i31 = 0;
            }
            i32++;
            sb3 = sb4;
        }
    }

    private void validateRows(int i, int i2) {
        Integer num = this.rows;
        if (num != null) {
            if (num.intValue() < i) {
                throw new OkapiException("Too few rows (" + this.rows + ")");
            }
            if (this.rows.intValue() <= i2) {
                return;
            }
            throw new OkapiException("Too many rows (" + this.rows + ")");
        }
    }

    private void validateColumns(int i, int i2) {
        Integer num = this.columns;
        if (num != null) {
            if (num.intValue() < i) {
                throw new OkapiException("Too few columns (" + this.columns + ")");
            }
            if (this.columns.intValue() <= i2) {
                return;
            }
            throw new OkapiException("Too many columns (" + this.columns + ")");
        }
    }

    private static EncodingMode chooseMode(int i) {
        if (i >= 48 && i <= 57) {
            return EncodingMode.NUM;
        }
        if (i == 9 || i == 10 || i == 13 || (i >= 32 && i <= 126)) {
            return EncodingMode.TEX;
        }
        return EncodingMode.BYT;
    }

    private static int getMicroPdf417Variant(int i, Integer num, Integer num2) {
        for (int i2 = 0; i2 < 34; i2++) {
            int[] iArr = MICRO_AUTOSIZE;
            if (i <= iArr[i2]) {
                int i3 = iArr[i2 + 34];
                int[] iArr2 = MICRO_VARIANTS;
                int i4 = iArr2[i3 - 1];
                int i5 = iArr2[i3 + 33];
                if ((num == null || num.intValue() == i4) && (num2 == null || num2.intValue() == i5)) {
                    return i3;
                }
            }
        }
        throw new OkapiException("Unable to determine MicroPDF417 variant for " + i + " codewords");
    }

    private static List<Block> createBlocks(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        Block block = null;
        for (int i : iArr) {
            EncodingMode chooseMode = chooseMode(i);
            if (block != null && block.mode == chooseMode && (chooseMode != EncodingMode.NUM || block.length < 44)) {
                block.length++;
            } else {
                block = new Block(chooseMode);
                arrayList.add(block);
            }
        }
        smoothBlocks(arrayList);
        return arrayList;
    }

    private static void smoothBlocks(List<Block> list) {
        int i = 0;
        int i2 = 0;
        while (i2 < list.size()) {
            Block block = list.get(i2);
            EncodingMode encodingMode = i2 > 0 ? list.get(i2 - 1).mode : EncodingMode.FALSE;
            EncodingMode encodingMode2 = i2 < list.size() - 1 ? list.get(i2 + 1).mode : EncodingMode.FALSE;
            if (block.mode == EncodingMode.NUM) {
                if (i2 == 0) {
                    if (encodingMode2 == EncodingMode.TEX && block.length < 8) {
                        block.mode = EncodingMode.TEX;
                    } else if (encodingMode2 == EncodingMode.BYT && block.length == 1) {
                        block.mode = EncodingMode.BYT;
                    }
                } else if (i2 == list.size() - 1) {
                    if (encodingMode == EncodingMode.TEX && block.length < 7) {
                        block.mode = EncodingMode.TEX;
                    } else if (encodingMode == EncodingMode.BYT && block.length == 1) {
                        block.mode = EncodingMode.BYT;
                    }
                } else if (encodingMode == EncodingMode.BYT && encodingMode2 == EncodingMode.BYT && block.length < 4) {
                    block.mode = EncodingMode.BYT;
                } else if (encodingMode == EncodingMode.BYT && encodingMode2 == EncodingMode.TEX && block.length < 4) {
                    block.mode = EncodingMode.TEX;
                } else if (encodingMode == EncodingMode.TEX && encodingMode2 == EncodingMode.BYT && block.length < 5) {
                    block.mode = EncodingMode.TEX;
                } else if (encodingMode == EncodingMode.TEX && encodingMode2 == EncodingMode.TEX && block.length < 8) {
                    block.mode = EncodingMode.TEX;
                } else if (encodingMode == EncodingMode.NUM && encodingMode2 == EncodingMode.TEX && block.length < 8) {
                    block.mode = EncodingMode.TEX;
                }
            }
            i2++;
        }
        mergeBlocks(list);
        while (i < list.size()) {
            Block block2 = list.get(i);
            EncodingMode encodingMode3 = i > 0 ? list.get(i - 1).mode : EncodingMode.FALSE;
            EncodingMode encodingMode4 = i < list.size() - 1 ? list.get(i + 1).mode : EncodingMode.FALSE;
            if (block2.mode == EncodingMode.TEX && i > 0) {
                if (i == list.size() - 1) {
                    if (encodingMode3 == EncodingMode.BYT && block2.length == 1) {
                        block2.mode = EncodingMode.BYT;
                    }
                } else {
                    if (encodingMode3 == EncodingMode.BYT && encodingMode4 == EncodingMode.BYT && block2.length < 5) {
                        block2.mode = EncodingMode.BYT;
                    }
                    if (((encodingMode3 == EncodingMode.BYT && encodingMode4 != EncodingMode.BYT) || (encodingMode3 != EncodingMode.BYT && encodingMode4 == EncodingMode.BYT)) && block2.length < 3) {
                        block2.mode = EncodingMode.BYT;
                    }
                }
            }
            i++;
        }
        mergeBlocks(list);
    }

    private static void mergeBlocks(List<Block> list) {
        int i = 1;
        while (i < list.size()) {
            Block block = list.get(i - 1);
            Block block2 = list.get(i);
            if (block.mode == block2.mode && (block.mode != EncodingMode.NUM || block.length + block2.length <= 44)) {
                block.length += block2.length;
                list.remove(i);
                i--;
            }
            i++;
        }
    }

    private void processEci(int i) {
        if (i == 3) {
            return;
        }
        if (i <= 899) {
            int[] iArr = this.codeWords;
            int i2 = this.codeWordCount;
            int i3 = i2 + 1;
            this.codeWordCount = i3;
            iArr[i2] = 927;
            this.codeWordCount = i2 + 2;
            iArr[i3] = i;
            return;
        }
        if (i < 900 || i > 810899) {
            if (i < 810900 || i > 811799) {
                return;
            }
            int[] iArr2 = this.codeWords;
            int i4 = this.codeWordCount;
            int i5 = i4 + 1;
            this.codeWordCount = i5;
            iArr2[i4] = 925;
            this.codeWordCount = i4 + 2;
            iArr2[i5] = i - 810900;
            return;
        }
        int[] iArr3 = this.codeWords;
        int i6 = this.codeWordCount;
        int i7 = i6 + 1;
        this.codeWordCount = i7;
        iArr3[i6] = 926;
        int i8 = i6 + 2;
        this.codeWordCount = i8;
        iArr3[i7] = (i / TypedValues.Custom.TYPE_INT) - 1;
        this.codeWordCount = i6 + 3;
        iArr3[i8] = i % TypedValues.Custom.TYPE_INT;
    }

    private void processText(int[] iArr, int i, int i2, boolean z) {
        int i3;
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        int[] iArr4 = new int[i2 * 4];
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = iArr[i + i4];
            if (i5 == 9) {
                iArr2[i4] = 12;
                iArr3[i4] = 12;
            } else if (i5 == 10) {
                iArr2[i4] = 8;
                iArr3[i4] = 15;
            } else if (i5 == 13) {
                iArr2[i4] = 12;
                iArr3[i4] = 11;
            } else {
                int i6 = i5 - 32;
                iArr2[i4] = ASCII_X[i6];
                iArr3[i4] = ASCII_Y[i6];
            }
        }
        int i7 = 1;
        int i8 = 0;
        int i9 = 0;
        while (i8 < i2) {
            int i10 = iArr2[i8];
            if ((i10 & i7) != 0) {
                iArr4[i9] = iArr3[i8];
                i9++;
            } else {
                int i11 = i2 - 1;
                boolean z2 = i8 == i11 || (iArr2[i8 + 1] & i10) == 0;
                if (z2) {
                    if ((i10 & 1) != 0 && i7 == 2) {
                        iArr4[i9] = 27;
                        iArr4[i9 + 1] = iArr3[i8];
                        i9 += 2;
                    }
                    if ((iArr2[i8] & 8) != 0) {
                        iArr4[i9] = 29;
                        iArr4[i9 + 1] = iArr3[i8];
                        i9 += 2;
                    }
                    int i12 = iArr2[i8];
                    if (((i12 & 1) == 0 || i7 != 2) && (i12 & 8) == 0) {
                        z2 = false;
                    }
                }
                if (!z2) {
                    if (i8 == i11) {
                        i3 = iArr2[i8];
                    } else {
                        i3 = iArr2[i8];
                        int i13 = iArr2[i8 + 1];
                        if ((i3 & i13) != 0) {
                            i3 &= i13;
                        }
                    }
                    switch (i3) {
                        case 3:
                        case 5:
                        case 7:
                        case 9:
                        case 11:
                        case 13:
                        case 15:
                            i3 = 1;
                            break;
                        case 6:
                        case 10:
                        case 14:
                            i3 = 2;
                            break;
                        case 12:
                            i3 = 4;
                            break;
                    }
                    if (i7 == 1) {
                        if (i3 == 2) {
                            iArr4[i9] = 27;
                        } else if (i3 == 4) {
                            iArr4[i9] = 28;
                        } else {
                            if (i3 == 8) {
                                iArr4[i9] = 28;
                                iArr4[i9 + 1] = 25;
                                i9 += 2;
                            }
                            iArr4[i9] = iArr3[i8];
                            i9++;
                            i7 = i3;
                        }
                        i9++;
                        iArr4[i9] = iArr3[i8];
                        i9++;
                        i7 = i3;
                    } else if (i7 == 2) {
                        if (i3 == 1) {
                            iArr4[i9] = 28;
                            iArr4[i9 + 1] = 28;
                        } else if (i3 == 4) {
                            iArr4[i9] = 28;
                            i9++;
                            iArr4[i9] = iArr3[i8];
                            i9++;
                            i7 = i3;
                        } else {
                            if (i3 == 8) {
                                iArr4[i9] = 28;
                                iArr4[i9 + 1] = 25;
                            }
                            iArr4[i9] = iArr3[i8];
                            i9++;
                            i7 = i3;
                        }
                        i9 += 2;
                        iArr4[i9] = iArr3[i8];
                        i9++;
                        i7 = i3;
                    } else if (i7 != 4) {
                        if (i7 == 8) {
                            if (i3 != 1) {
                                if (i3 == 2) {
                                    iArr4[i9] = 29;
                                    iArr4[i9 + 1] = 27;
                                } else if (i3 == 4) {
                                    iArr4[i9] = 29;
                                    iArr4[i9 + 1] = 28;
                                }
                                i9 += 2;
                            } else {
                                iArr4[i9] = 29;
                                i9++;
                            }
                        }
                        iArr4[i9] = iArr3[i8];
                        i9++;
                        i7 = i3;
                    } else {
                        if (i3 == 1) {
                            iArr4[i9] = 28;
                        } else if (i3 == 2) {
                            iArr4[i9] = 27;
                        } else {
                            if (i3 == 8) {
                                iArr4[i9] = 25;
                            }
                            iArr4[i9] = iArr3[i8];
                            i9++;
                            i7 = i3;
                        }
                        i9++;
                        iArr4[i9] = iArr3[i8];
                        i9++;
                        i7 = i3;
                    }
                }
            }
            i8++;
        }
        if ((i9 & 1) != 0) {
            iArr4[i9] = 29;
            i9++;
        }
        if (!z) {
            int[] iArr5 = this.codeWords;
            int i14 = this.codeWordCount;
            iArr5[i14] = 900;
            this.codeWordCount = i14 + 1;
        }
        for (int i15 = 0; i15 < i9; i15 += 2) {
            int i16 = (iArr4[i15] * 30) + iArr4[i15 + 1];
            int[] iArr6 = this.codeWords;
            int i17 = this.codeWordCount;
            iArr6[i17] = i16;
            this.codeWordCount = i17 + 1;
        }
    }

    private void processBytes(int[] iArr, int i, int i2, EncodingMode encodingMode) {
        new BigInteger("0");
        new BigInteger("0");
        if (i2 == 1 && encodingMode == EncodingMode.TEX) {
            int[] iArr2 = this.codeWords;
            int i3 = this.codeWordCount;
            int i4 = i3 + 1;
            this.codeWordCount = i4;
            iArr2[i3] = 913;
            this.codeWordCount = i3 + 2;
            iArr2[i4] = iArr[i];
            return;
        }
        if (i2 % 6 == 0) {
            int[] iArr3 = this.codeWords;
            int i5 = this.codeWordCount;
            this.codeWordCount = i5 + 1;
            iArr3[i5] = 924;
        } else {
            int[] iArr4 = this.codeWords;
            int i6 = this.codeWordCount;
            this.codeWordCount = i6 + 1;
            iArr4[i6] = 901;
        }
        int i7 = 0;
        while (i7 < i2) {
            int i8 = i2 - i7;
            int i9 = 6;
            if (6 <= i8) {
                i7 += 6;
                BigInteger valueOf = BigInteger.valueOf(0L);
                while (true) {
                    int i10 = i9 - 1;
                    if (i9 == 0) {
                        break;
                    }
                    valueOf = valueOf.or(BigInteger.valueOf(iArr[i]).shiftLeft(i10 * 8));
                    i++;
                    i9 = i10;
                }
                int i11 = 5;
                while (true) {
                    int i12 = i11 - 1;
                    if (i11 == 0) {
                        break;
                    }
                    this.codeWords[this.codeWordCount + i12] = valueOf.mod(BigInteger.valueOf(900L)).intValue();
                    valueOf = valueOf.divide(BigInteger.valueOf(900L));
                    i11 = i12;
                }
                this.codeWordCount += 5;
            } else {
                i7 += i8;
                while (true) {
                    int i13 = i8 - 1;
                    if (i8 != 0) {
                        int[] iArr5 = this.codeWords;
                        int i14 = this.codeWordCount;
                        this.codeWordCount = i14 + 1;
                        iArr5[i14] = iArr[i];
                        i8 = i13;
                        i++;
                    }
                }
            }
        }
    }

    private void processNumbers(int[] iArr, int i, int i2, boolean z) {
        int[] iArr2 = new int[16];
        if (!z) {
            int[] iArr3 = this.codeWords;
            int i3 = this.codeWordCount;
            this.codeWordCount = i3 + 1;
            iArr3[i3] = 902;
        }
        StringBuilder sb = new StringBuilder(i2 + 1);
        sb.append('1');
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            sb.append((char) iArr[i + i5]);
        }
        BigInteger bigInteger = new BigInteger(sb.toString());
        while (true) {
            iArr2[i4] = bigInteger.mod(BigInteger.valueOf(900L)).intValue();
            bigInteger = bigInteger.divide(BigInteger.valueOf(900L));
            int i6 = i4 + 1;
            if (bigInteger.compareTo(BigInteger.ZERO) != 1) {
                break;
            } else {
                i4 = i6;
            }
        }
        while (i4 >= 0) {
            int[] iArr4 = this.codeWords;
            int i7 = this.codeWordCount;
            this.codeWordCount = i7 + 1;
            iArr4[i7] = iArr2[i4];
            i4--;
        }
    }

    private void processFiveDigits(int i) {
        int[] iArr = new int[5];
        for (int i2 = 4; i2 >= 0; i2--) {
            iArr[i2] = (i % 10) + 48;
            i /= 10;
        }
        processNumbers(iArr, 0, 5, true);
    }

    private int addMacroCodewords() {
        if (this.structuredAppendTotal == 1) {
            return 0;
        }
        int i = this.codeWordCount;
        int[] iArr = this.codeWords;
        this.codeWordCount = i + 1;
        iArr[i] = 928;
        processFiveDigits(this.structuredAppendPosition - 1);
        int[] iArr2 = this.codeWords;
        int i2 = this.codeWordCount;
        this.codeWordCount = i2 + 1;
        iArr2[i2] = this.structuredAppendFileId;
        String str = this.structuredAppendFileName;
        if (str != null && !str.isEmpty()) {
            int[] iArr3 = this.codeWords;
            int i3 = this.codeWordCount;
            int i4 = i3 + 1;
            this.codeWordCount = i4;
            iArr3[i3] = 923;
            this.codeWordCount = i3 + 2;
            iArr3[i4] = 0;
            EciMode or = EciMode.of(this.structuredAppendFileName, "ISO8859_1", 3).or(this.structuredAppendFileName, "UTF8", 26);
            int[] bytes = toBytes(this.structuredAppendFileName, or.charset, new int[0]);
            processEci(or.mode);
            processText(bytes, 0, bytes.length, true);
        }
        if (this.structuredAppendIncludeSegmentCount) {
            int[] iArr4 = this.codeWords;
            int i5 = this.codeWordCount;
            int i6 = i5 + 1;
            this.codeWordCount = i6;
            iArr4[i5] = 923;
            this.codeWordCount = i5 + 2;
            iArr4[i6] = 1;
            processFiveDigits(this.structuredAppendTotal);
        }
        if (this.structuredAppendPosition == this.structuredAppendTotal) {
            int[] iArr5 = this.codeWords;
            int i7 = this.codeWordCount;
            this.codeWordCount = i7 + 1;
            iArr5[i7] = 922;
        }
        return this.codeWordCount - i;
    }

    private static class Block {
        public int length = 1;
        public EncodingMode mode;

        public Block(EncodingMode encodingMode) {
            this.mode = encodingMode;
        }

        public String toString() {
            return this.mode + "x" + this.length;
        }
    }
}
