package uk.org.okapibarcode.backend;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.runtime.ComposerKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.InputDeviceCompat;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.wallet.WalletConstants;
import com.google.android.material.internal.ViewUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.salesforce.marketingcloud.analytics.stats.b;
import java.math.BigInteger;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Utf8;
import uk.org.okapibarcode.graphics.Rectangle;
import uk.org.okapibarcode.graphics.TextAlignment;
import uk.org.okapibarcode.graphics.TextBox;

/* loaded from: classes4.dex */
public class UspsOneCode extends Symbol {
    private static final int[] APPX_D_I = {31, 7936, 47, 7808, 55, 7552, 59, 7040, 61, 6016, 62, Utf8.MASK_2BYTES, 79, 7744, 87, 7488, 91, 6976, 93, 5952, 94, 3904, b.i, 7360, b.m, 6848, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 5824, b.n, 3776, 115, 6592, 117, 5568, 118, 3520, 121, 5056, 122, 3008, 124, 1984, 143, 7712, 151, 7456, 155, 6944, 157, 5920, 158, 3872, 167, 7328, 171, 6816, 173, 5792, 174, 3744, 179, 6560, 181, 5536, 182, 3488, 185, 5024, 186, 2976, 188, 1952, 199, 7264, ComposerKt.providerValuesKey, 6752, 205, 5728, ComposerKt.referenceKey, 3680, 211, 6496, 213, 5472, 214, 3424, 217, 4960, 218, 2912, 220, 1888, 227, 6368, 229, 5344, 230, 3296, 233, 4832, 234, 2784, 236, 1760, 241, 4576, 242, 2528, 244, 1504, 248, 992, 271, 7696, 279, 7440, 283, 6928, 285, 5904, 286, 3856, 295, 7312, 299, 6800, 301, 5776, 302, 3728, 307, 6544, 309, 5520, 310, 3472, 313, 5008, 314, 2960, TypedValues.AttributesType.TYPE_PATH_ROTATE, 1936, 327, 7248, 331, 6736, 333, 5712, 334, 3664, 339, 6480, 341, 5456, 342, 3408, 345, 4944, 346, 2896, 348, 1872, 355, 6352, 357, 5328, 358, 3280, 361, 4816, 362, 2768, 364, 1744, 369, 4560, 370, 2512, 372, 1488, 376, 976, 391, 7216, 395, 6704, 397, 5680, 398, 3632, TypedValues.CycleType.TYPE_ALPHA, 6448, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR, 5424, WalletConstants.ERROR_CODE_SPENDING_LIMIT_EXCEEDED, 3376, WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR, 4912, WalletConstants.ERROR_CODE_INVALID_TRANSACTION, 2864, WalletConstants.ERROR_CODE_UNSUPPORTED_API_VERSION, 1840, 419, 6320, 421, 5296, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 3248, TypedValues.CycleType.TYPE_WAVE_PHASE, 4784, 426, 2736, 428, 1712, 433, 4528, 434, 2480, 436, 1456, 440, 944, 451, 6256, 453, 5232, 454, 3184, 457, 4720, 458, 2672, 460, 1648, 465, 4464, 466, 2416, 468, 1392, 472, 880, 481, 4336, 482, 2288, 484, 1264, 488, 752, 527, 7688, 535, 7432, 539, 6920, 541, 5896, 542, 3848, 551, 7304, 555, 6792, 557, 5768, 558, 3720, 563, 6536, 565, 5512, 566, 3464, 569, 5000, 570, 2952, 572, 1928, 583, 7240, 587, 6728, 589, 5704, 590, 3656, 595, 6472, 597, 5448, 598, 3400, 601, 4936, TypedValues.MotionType.TYPE_QUANTIZE_MOTION_PHASE, 2888, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR, 1864, TypedValues.MotionType.TYPE_QUANTIZE_INTERPOLATOR_TYPE, 6344, 613, 5320, 614, 3272, 617, 4808, 618, 2760, 620, 1736, 625, 4552, 626, 2504, 628, 1480, 632, 968, 647, 7208, 651, 6696, 653, 5672, 654, 3624, 659, 6440, 661, 5416, 662, 3368, 665, 4904, 666, 2856, 668, 1832, 675, 6312, 677, 5288, 678, 3240, 681, 4776, 682, 2728, 684, 1704, 689, 4520, 690, 2472, 692, 1448, 696, 936, TypedValues.TransitionType.TYPE_TRANSITION_FLAGS, 6248, 709, 5224, 710, 3176, 713, 4712, 714, 2664, 716, 1640, 721, 4456, 722, 2408, 724, 1384, 728, 872, 737, 4328, 738, 2280, 740, 1256, 775, 7192, 779, 6680, 781, 5656, 782, 3608, 787, 6424, 789, 5400, 790, 3352, 793, 4888, 794, 2840, 796, 1816, 803, 6296, 805, 5272, 806, 3224, 809, 4760, 810, 2712, 812, 1688, 817, 4504, 818, 2456, 820, 1432, 824, 920, 835, 6232, 837, 5208, 838, 3160, 841, 4696, 842, 2648, 844, 1624, 849, 4440, 850, 2392, 852, 1368, 865, 4312, 866, 2264, 868, 1240, 899, 6200, TypedValues.Custom.TYPE_FLOAT, 5176, TypedValues.Custom.TYPE_COLOR, 3128, TypedValues.Custom.TYPE_DIMENSION, 4664, TypedValues.Custom.TYPE_REFERENCE, 2616, 908, 1592, 913, 4408, 914, 2360, 916, 1336, PDF417Common.NUMBER_OF_CODEWORDS, 4280, 930, 2232, 932, 1208, 961, 4216, 962, 2168, 964, 1144, 1039, 7684, 1047, 7428, 1051, 6916, 1053, 5892, 1054, 3844, 1063, 7300, 1067, 6788, 1069, 5764, 1070, 3716, 1075, 6532, 1077, 5508, 1078, 3460, 1081, 4996, 1082, 2948, 1084, 1924, 1095, 7236, 1099, 6724, 1101, 5700, 1102, 3652, 1107, 6468, 1109, 5444, 1110, 3396, 1113, 4932, 1114, 2884, 1116, 1860, 1123, 6340, 1125, 5316, 1126, 3268, 1129, 4804, 1130, 2756, 1132, 1732, 1137, 4548, 1138, 2500, 1140, 1476, 1159, 7204, 1163, 6692, 1165, 5668, 1166, 3620, 1171, 6436, 1173, 5412, 1174, 3364, 1177, 4900, 1178, 2852, 1180, 1828, 1187, 6308, 1189, 5284, 1190, 3236, 1193, 4772, 1194, 2724, 1196, 1700, 1201, 4516, 1202, 2468, 1204, 1444, 1219, 6244, 1221, 5220, 1222, 3172, 1225, 4708, 1226, 2660, 1228, 1636, 1233, 4452, 1234, 2404, 1236, 1380, 1249, 4324, 1250, 2276, 1287, 7188, 1291, 6676, 1293, 5652, 1294, 3604, 1299, 6420, 1301, 5396, 1302, 3348, 1305, 4884, 1306, 2836, 1308, 1812, 1315, 6292, 1317, 5268, 1318, 3220, 1321, 4756, 1322, 2708, 1324, 1684, 1329, 4500, 1330, 2452, 1332, 1428, 1347, 6228, 1349, 5204, 1350, 3156, 1353, 4692, 1354, 2644, 1356, 1620, 1361, 4436, 1362, 2388, 1377, 4308, 1378, 2260, 1411, 6196, 1413, 5172, 1414, 3124, 1417, 4660, 1418, 2612, 1420, 1588, 1425, 4404, 1426, 2356, 1441, 4276, 1442, 2228, 1473, 4212, 1474, 2164, 1543, 7180, 1547, 6668, 1549, 5644, 1550, 3596, 1555, 6412, 1557, 5388, 1558, 3340, 1561, 4876, 1562, 2828, 1564, 1804, 1571, 6284, 1573, 5260, 1574, 3212, 1577, 4748, 1578, 2700, 1580, 1676, 1585, 4492, 1586, 2444, 1603, 6220, 1605, 5196, 1606, 3148, 1609, 4684, 1610, 2636, 1617, 4428, 1618, 2380, 1633, 4300, 1634, 2252, 1667, 6188, 1669, 5164, 1670, 3116, 1673, 4652, 1674, 2604, 1681, 4396, 1682, 2348, 1697, 4268, 1698, 2220, 1729, 4204, 1730, 2156, 1795, 6172, 1797, 5148, 1798, 3100, 1801, 4636, 1802, 2588, 1809, 4380, 1810, 2332, 1825, 4252, 1826, 2204, 1857, 4188, 1858, 2140, 1921, 4156, 1922, 2108, 2063, 7682, 2071, 7426, 2075, 6914, 2077, 5890, 2078, 3842, 2087, 7298, 2091, 6786, 2093, 5762, 2094, 3714, 2099, 6530, 2101, 5506, 2102, 3458, 2105, 4994, 2106, 2946, 2119, 7234, 2123, 6722, 2125, 5698, 2126, 3650, 2131, 6466, 2133, 5442, 2134, 3394, 2137, 4930, 2138, 2882, 2147, 6338, 2149, 5314, 2150, 3266, 2153, 4802, 2154, 2754, 2161, 4546, 2162, 2498, 2183, 7202, 2187, 6690, 2189, 5666, 2190, 3618, 2195, 6434, 2197, 5410, 2198, 3362, 2201, 4898, 2202, 2850, 2211, 6306, 2213, 5282, 2214, 3234, 2217, 4770, 2218, 2722, 2225, 4514, 2226, 2466, 2243, 6242, 2245, 5218, 2246, 3170, 2249, 4706, 2250, 2658, 2257, 4450, 2258, 2402, 2273, 4322, 2311, 7186, 2315, 6674, 2317, 5650, 2318, 3602, 2323, 6418, 2325, 5394, 2326, 3346, 2329, 4882, 2330, 2834, 2339, 6290, 2341, 5266, 2342, 3218, 2345, 4754, 2346, 2706, 2353, 4498, 2354, 2450, 2371, 6226, 2373, 5202, 2374, 3154, 2377, 4690, 2378, 2642, 2385, 4434, 2401, 4306, 2435, 6194, 2437, 5170, 2438, 3122, 2441, 4658, 2442, 2610, 2449, 4402, 2465, 4274, 2497, 4210, 2567, 7178, 2571, 6666, 2573, 5642, 2574, 3594, 2579, 6410, 2581, 5386, 2582, 3338, 2585, 4874, 2586, 2826, 2595, 6282, 2597, 5258, 2598, 3210, 2601, 4746, 2602, 2698, 2609, 4490, 2627, 6218, 2629, 5194, 2630, 3146, 2633, 4682, 
    2641, 4426, 2657, 4298, 2691, 6186, 2693, 5162, 2694, 3114, 2697, 4650, 2705, 4394, 2721, 4266, 2753, 4202, 2819, 6170, 2821, 5146, 2822, 3098, 2825, 4634, 2833, 4378, 2849, 4250, 2881, 4186, 2945, 4154, 3079, 7174, 3083, 6662, 3085, 5638, 3086, 3590, 3091, 6406, 3093, 5382, 3094, 3334, 3097, 4870, 3107, 6278, 3109, 5254, 3110, 3206, 3113, 4742, 3121, 4486, 3139, 6214, 3141, 5190, 3145, 4678, 3153, 4422, 3169, 4294, 3203, 6182, 3205, 5158, 3209, 4646, 3217, 4390, 3233, 4262, 3265, 4198, 3331, 6166, 3333, 5142, 3337, 4630, 3345, 4374, 3361, 4246, 3393, 4182, 3457, 4150, 3587, 6158, 3589, 5134, 3593, 4622, 3601, 4366, 3617, 4238, 3649, 4174, 3713, 4142, 3841, 4126, 4111, 7681, 4119, 7425, 4123, 6913, 4125, 5889, 4135, 7297, 4139, 6785, 4141, 5761, 4147, 6529, 4149, 5505, 4153, 4993, 4167, 7233, 4171, 6721, 4173, 5697, 4179, 6465, 4181, 5441, 4185, 4929, 4195, 6337, 4197, 5313, 4201, 4801, 4209, 4545, 4231, 7201, 4235, 6689, 4237, 5665, 4243, 6433, 4245, 5409, 4249, 4897, 4259, 6305, 4261, 5281, 4265, 4769, 4273, 4513, 4291, 6241, 4293, 5217, 4297, 4705, 4305, 4449, 4359, 7185, 4363, 6673, 4365, 5649, 4371, 6417, 4373, 5393, 4377, 4881, 4387, 6289, 4389, 5265, 4393, 4753, 4401, 4497, 4419, 6225, 4421, 5201, 4425, 4689, 4483, 6193, 4485, 5169, 4489, 4657, 4615, 7177, 4619, 6665, 4621, 5641, 4627, 6409, 4629, 5385, 4633, 4873, 4643, 6281, 4645, 5257, 4649, 4745, 4675, 6217, 4677, 5193, 4739, 6185, 4741, 5161, 4867, 6169, 4869, 5145, 5127, 7173, 5131, 6661, 5133, 5637, 5139, 6405, 5141, 5381, 5155, 6277, 5157, 5253, 5187, 6213, 5251, 6181, 5379, 6165, 5635, 6157, 6151, 7171, 6155, 6659, 6163, 6403, 6179, 6275, 6211, 5189, 4681, 4433, 4321, 3142, 2634, 2386, 2274, 1612, 1364, 1252, 856, 744, 496};
    private static final int[] APPX_D_II = {3, 6144, 5, 5120, 6, 3072, 9, 4608, 10, 2560, 12, 1536, 17, 4352, 18, 2304, 20, 1280, 24, ViewUtils.EDGE_TO_EDGE_FLAGS, 33, 4224, 34, 2176, 36, 1152, 40, 640, 48, 384, 65, 4160, 66, 2112, 68, 1088, 72, 576, 80, 320, 96, 192, 129, 4128, 130, 2080, 132, 1056, 136, 544, 144, 288, 257, 4112, 258, 2064, 260, 1040, 264, 528, InputDeviceCompat.SOURCE_DPAD, 4104, 514, 2056, 516, 1032, InputDeviceCompat.SOURCE_GAMEPAD, FragmentTransaction.TRANSIT_FRAGMENT_MATCH_ACTIVITY_OPEN, 1026, 2052, 2049, InputDeviceCompat.SOURCE_TOUCHSCREEN, FragmentTransaction.TRANSIT_FRAGMENT_OPEN, 2050, 1028, 520, 272, 160};
    private static final int[] APPX_D_IV = {67, 6, 78, 16, 86, 95, 34, 40, 45, 113, 117, 121, 62, 87, 18, 104, 41, 76, 57, 119, 115, 72, 97, 2, 127, 26, 105, 35, 122, 52, 114, 7, 24, 82, 68, 63, 94, 44, 77, b.p, 70, 100, 39, 30, b.m, 15, 125, 85, 10, 65, 54, 88, 20, b.l, 46, 66, 8, 116, 29, 61, 99, 80, 90, 37, 123, 51, 25, 84, 129, 56, 4, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 96, 28, 36, 47, 11, 71, 33, 102, 21, 9, 17, 49, 124, 79, 64, 91, 42, 69, 53, 60, 14, 1, 27, b.i, WebSocketProtocol.PAYLOAD_SHORT, 75, 89, 50, 120, 19, 32, b.n, 92, b.o, 130, 59, 31, 12, 81, 43, 55, 5, 74, 22, 101, 128, 58, 118, 48, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 38, 98, 93, 23, 83, 13, 73, 3};
    private double longHeightPercentage;
    private double moduleWidthRatio;
    private double shortHeightPercentage;

    public UspsOneCode() {
        this.default_height = 8;
        this.humanReadableLocation = HumanReadableLocation.NONE;
        this.humanReadableAlignment = TextAlignment.LEFT;
        this.moduleWidthRatio = 1.43d;
        this.shortHeightPercentage = 0.25d;
        this.longHeightPercentage = 0.625d;
    }

    public void setModuleWidthRatio(double d) {
        this.moduleWidthRatio = d;
    }

    public double getModuleWidthRatio() {
        return this.moduleWidthRatio;
    }

    public void setShortHeightPercentage(double d) {
        if (d < 0.0d || d > 1.0d) {
            throw new IllegalArgumentException("Height percentage must be between 0 and 1.");
        }
        this.shortHeightPercentage = d;
    }

    public double getShortHeightPercentage() {
        return this.shortHeightPercentage;
    }

    public void setLongHeightPercentage(double d) {
        if (d < 0.0d || d > 1.0d) {
            throw new IllegalArgumentException("Height percentage must be between 0 and 1.");
        }
        this.longHeightPercentage = d;
    }

    public double getLongHeightPercentage() {
        return this.longHeightPercentage;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        BigInteger bigInteger;
        int length = this.content.length();
        int[] iArr = new int[10];
        int[] iArr2 = new int[10];
        boolean[] zArr = new boolean[130];
        if (!this.content.matches("[0-9-]+")) {
            throw new OkapiException("Invalid characters in input");
        }
        if (length > 32) {
            throw new OkapiException("Input too long");
        }
        boolean z = false;
        String str = "";
        String str2 = str;
        for (int i = 0; i < length; i++) {
            if (this.content.charAt(i) == '-') {
                z = true;
            } else if (z) {
                str2 = str2 + this.content.charAt(i);
            } else {
                str = str + this.content.charAt(i);
            }
        }
        if (str.length() != 20) {
            throw new OkapiException("Invalid length tracking code");
        }
        if (str2.length() > 11) {
            throw new OkapiException("Invalid ZIP code");
        }
        String str3 = "0";
        if (str2.length() > 0) {
            bigInteger = new BigInteger(str2);
        } else {
            bigInteger = new BigInteger("0");
        }
        if (str2.length() > 9) {
            str3 = "1000100001";
        } else if (str2.length() > 5) {
            str3 = "100001";
        } else if (str2.length() > 0) {
            str3 = "1";
        }
        long j = 10;
        BigInteger add = new BigInteger(str3).add(bigInteger).multiply(BigInteger.valueOf(10L)).add(BigInteger.valueOf(Character.getNumericValue(str.charAt(0)))).multiply(BigInteger.valueOf(5L)).add(BigInteger.valueOf(Character.getNumericValue(str.charAt(1))));
        int i2 = 2;
        while (i2 < str.length()) {
            add = add.multiply(BigInteger.valueOf(j)).add(BigInteger.valueOf(Character.getNumericValue(str.charAt(i2))));
            i2++;
            j = 10;
        }
        int[] iArr3 = new int[13];
        int i3 = 0;
        for (int i4 = 13; i3 < i4; i4 = 13) {
            iArr3[i3] = add.shiftRight(96 - (i3 * 8)).and(new BigInteger("255")).intValue();
            i3++;
        }
        int USPS_MSB_Math_CRC11GenerateFrameCheckSequence = USPS_MSB_Math_CRC11GenerateFrameCheckSequence(iArr3);
        BigInteger mod = add.mod(BigInteger.valueOf(636L));
        iArr[9] = mod.intValue();
        BigInteger divide = add.subtract(mod).divide(BigInteger.valueOf(636L));
        for (int i5 = 8; i5 >= 0; i5--) {
            BigInteger mod2 = divide.mod(BigInteger.valueOf(1365L));
            iArr[i5] = mod2.intValue();
            divide = divide.subtract(mod2).divide(BigInteger.valueOf(1365L));
        }
        for (int i6 = 0; i6 < 9; i6++) {
            if (iArr[i6] == 1365) {
                iArr[i6] = 0;
            }
        }
        iArr[9] = iArr[9] * 2;
        if (USPS_MSB_Math_CRC11GenerateFrameCheckSequence >= 1024) {
            iArr[0] = iArr[0] + 659;
        }
        info("Codewords: ");
        for (int i7 = 0; i7 < 10; i7++) {
            infoSpace(iArr[i7]);
        }
        infoLine();
        for (int i8 = 0; i8 < 10; i8++) {
            int i9 = iArr[i8];
            if (i9 < 1287) {
                iArr2[i8] = APPX_D_I[i9];
            } else {
                iArr2[i8] = APPX_D_II[i9 - 1287];
            }
        }
        for (int i10 = 0; i10 < 10; i10++) {
            if (((1 << i10) & USPS_MSB_Math_CRC11GenerateFrameCheckSequence) != 0) {
                iArr2[i10] = 8191 - iArr2[i10];
            }
        }
        for (int i11 = 0; i11 < 10; i11++) {
            for (int i12 = 0; i12 < 13; i12++) {
                if ((iArr2[i11] & (1 << i12)) == 0) {
                    zArr[APPX_D_IV[(i11 * 13) + i12] - 1] = false;
                } else {
                    zArr[APPX_D_IV[(i11 * 13) + i12] - 1] = true;
                }
            }
        }
        this.readable = formatHumanReadableText(this.content);
        this.pattern = new String[1];
        this.row_count = 1;
        this.row_height = new int[1];
        this.row_height[0] = -1;
        this.pattern[0] = "";
        int i13 = 0;
        while (true) {
            if (i13 >= 65) {
                infoLine("Encoding: " + this.pattern[0]);
                return;
            }
            boolean z2 = zArr[i13];
            char c = z2 ? 'D' : 'T';
            boolean z3 = zArr[i13 + 65];
            char c2 = z3 ? 'A' : c;
            if (z2 && z3) {
                c2 = 'F';
            }
            StringBuilder sb = new StringBuilder();
            String[] strArr = this.pattern;
            sb.append(strArr[0]);
            sb.append(c2);
            strArr[0] = sb.toString();
            i13++;
        }
    }

    private static int USPS_MSB_Math_CRC11GenerateFrameCheckSequence(int[] iArr) {
        int i = iArr[0] << 5;
        int i2 = 2047;
        for (int i3 = 2; i3 < 8; i3++) {
            i2 = (((i2 ^ i) & 1024) != 0 ? (i2 << 1) ^ 3893 : i2 << 1) & 2047;
            i <<= 1;
        }
        int i4 = 1;
        for (int i5 = 1; i5 < 13; i5++) {
            int i6 = iArr[i4] << 3;
            i4++;
            for (int i7 = 0; i7 < 8; i7++) {
                i2 = (((i2 ^ i6) & 1024) != 0 ? (i2 << 1) ^ 3893 : i2 << 1) & 2047;
                i6 <<= 1;
            }
        }
        return i2;
    }

    protected static String formatHumanReadableText(String str) {
        StringBuilder sb = new StringBuilder(50);
        boolean z = true;
        boolean z2 = true;
        int i = 0;
        boolean z3 = false;
        boolean z4 = false;
        for (char c : str.toCharArray()) {
            if (c >= '0' && c <= '9') {
                if (i == 5 && c == '9') {
                    z4 = true;
                }
                if ((i == 2 && c != '0') || ((i == 3 && c != '5') || (i == 4 && c != '0' && c != '2'))) {
                    z2 = false;
                }
                if ((i == 0 && c != '9') || (i == 1 && c != '4')) {
                    z = false;
                }
                if (i == 5 && c == '5') {
                    z3 = true;
                }
                if (i == 2 || i == 5 || ((i == 6 && z) || ((i == 10 && z) || ((i == 13 && z && !z3) || ((i == 15 && z && !z3) || ((i == 11 && !z4 && !z2 && !z) || ((i == 14 && z4 && !z2 && !z) || i == 20 || i == 25 || i == 29))))))) {
                    sb.append(' ');
                }
                sb.append(c);
                i++;
            }
        }
        return sb.toString().trim();
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        int i;
        resetPlotElements();
        int theoreticalHumanReadableHeight = this.humanReadableLocation == HumanReadableLocation.TOP ? getTheoreticalHumanReadableHeight() : 0;
        double d = this.moduleWidth;
        double d2 = (this.moduleWidthRatio + 1.0d) * d;
        int i2 = (int) (this.shortHeightPercentage * this.default_height);
        int i3 = (int) (this.longHeightPercentage * this.default_height);
        double d3 = 0.0d;
        int i4 = 0;
        double d4 = 0.0d;
        double d5 = 0.0d;
        for (char c = 0; i4 < this.pattern[c].length(); c = 0) {
            char charAt = this.pattern[c].charAt(i4);
            if (charAt == 'A') {
                d3 = theoreticalHumanReadableHeight;
            } else if (charAt == 'D') {
                d3 = (this.default_height + theoreticalHumanReadableHeight) - i3;
            } else {
                if (charAt == 'F') {
                    d3 = theoreticalHumanReadableHeight;
                    d4 = this.default_height;
                } else if (charAt == 'T') {
                    d3 = (this.default_height + theoreticalHumanReadableHeight) - i3;
                    d4 = i2;
                }
                double d6 = d3;
                double d7 = d4;
                this.rectangles.add(new Rectangle(d5, d6, d, d7));
                d5 += d2;
                i4++;
                d3 = d6;
                d4 = d7;
                i3 = i3;
            }
            d4 = i3;
            double d62 = d3;
            double d72 = d4;
            this.rectangles.add(new Rectangle(d5, d62, d, d72));
            d5 += d2;
            i4++;
            d3 = d62;
            d4 = d72;
            i3 = i3;
        }
        this.symbol_width = (int) Math.ceil(((this.pattern[0].length() - 1) * d2) + d);
        this.symbol_height = this.default_height;
        if (this.humanReadableLocation == HumanReadableLocation.NONE || this.readable.isEmpty()) {
            return;
        }
        if (this.humanReadableLocation == HumanReadableLocation.TOP) {
            i = this.fontSize;
        } else {
            i = this.symbol_height + this.fontSize;
        }
        this.texts.add(new TextBox(0.0d, i, this.symbol_width, this.readable, this.humanReadableAlignment));
    }
}
