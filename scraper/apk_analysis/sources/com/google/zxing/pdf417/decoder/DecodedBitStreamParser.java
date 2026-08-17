package com.google.zxing.pdf417.decoder;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.ECIStringBuilder;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.math.BigInteger;
import java.util.Arrays;

/* loaded from: classes2.dex */
final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_ADDRESSEE = 4;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_CHECKSUM = 6;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_NAME = 0;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_FILE_SIZE = 5;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SEGMENT_COUNT = 1;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_SENDER = 3;
    private static final int MACRO_PDF417_OPTIONAL_FIELD_TIME_STAMP = 2;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(valueOf);
            i++;
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(int[] iArr, String str) throws FormatException {
        ECIStringBuilder eCIStringBuilder = new ECIStringBuilder(iArr.length * 2);
        int textCompaction = textCompaction(iArr, 1, eCIStringBuilder);
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        while (textCompaction < iArr[0]) {
            int i = textCompaction + 1;
            int i2 = iArr[textCompaction];
            if (i2 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (i2) {
                    case 900:
                        textCompaction = textCompaction(iArr, i, eCIStringBuilder);
                        continue;
                    case 901:
                        break;
                    case 902:
                        textCompaction = numericCompaction(iArr, i, eCIStringBuilder);
                        continue;
                    default:
                        switch (i2) {
                            case MACRO_PDF417_TERMINATOR /* 922 */:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /* 923 */:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /* 924 */:
                                break;
                            case ECI_USER_DEFINED /* 925 */:
                                textCompaction += 2;
                                continue;
                            case ECI_GENERAL_PURPOSE /* 926 */:
                                textCompaction += 3;
                                continue;
                            case ECI_CHARSET /* 927 */:
                                textCompaction += 2;
                                eCIStringBuilder.appendECI(iArr[i]);
                                continue;
                            case 928:
                                textCompaction = decodeMacroBlock(iArr, i, pDF417ResultMetadata);
                                continue;
                            default:
                                textCompaction = textCompaction(iArr, textCompaction, eCIStringBuilder);
                                continue;
                        }
                }
                textCompaction = byteCompaction(i2, iArr, i, eCIStringBuilder);
            } else {
                textCompaction += 2;
                eCIStringBuilder.append((char) iArr[i]);
            }
        }
        if (eCIStringBuilder.isEmpty() && pDF417ResultMetadata.getFileId() == null) {
            throw FormatException.getFormatInstance();
        }
        DecoderResult decoderResult = new DecoderResult(null, eCIStringBuilder.toString(), null, str);
        decoderResult.setOther(pDF417ResultMetadata);
        return decoderResult;
    }

    static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        int i2;
        if (i + 2 > iArr[0]) {
            throw FormatException.getFormatInstance();
        }
        int[] iArr2 = new int[2];
        int i3 = 0;
        while (i3 < 2) {
            iArr2[i3] = iArr[i];
            i3++;
            i++;
        }
        String decodeBase900toBase10 = decodeBase900toBase10(iArr2, 2);
        if (decodeBase900toBase10.isEmpty()) {
            pDF417ResultMetadata.setSegmentIndex(0);
        } else {
            try {
                pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10));
            } catch (NumberFormatException unused) {
                throw FormatException.getFormatInstance();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (i < iArr[0] && i < iArr.length && (i2 = iArr[i]) != MACRO_PDF417_TERMINATOR && i2 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
            sb.append(String.format("%03d", Integer.valueOf(i2)));
            i++;
        }
        if (sb.length() == 0) {
            throw FormatException.getFormatInstance();
        }
        pDF417ResultMetadata.setFileId(sb.toString());
        int i4 = iArr[i] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD ? i + 1 : -1;
        while (i < iArr[0]) {
            int i5 = iArr[i];
            if (i5 == MACRO_PDF417_TERMINATOR) {
                i++;
                pDF417ResultMetadata.setLastSegment(true);
            } else if (i5 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                switch (iArr[i + 1]) {
                    case 0:
                        ECIStringBuilder eCIStringBuilder = new ECIStringBuilder();
                        i = textCompaction(iArr, i + 2, eCIStringBuilder);
                        pDF417ResultMetadata.setFileName(eCIStringBuilder.toString());
                        break;
                    case 1:
                        ECIStringBuilder eCIStringBuilder2 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i + 2, eCIStringBuilder2);
                        try {
                            pDF417ResultMetadata.setSegmentCount(Integer.parseInt(eCIStringBuilder2.toString()));
                            break;
                        } catch (NumberFormatException unused2) {
                            throw FormatException.getFormatInstance();
                        }
                    case 2:
                        ECIStringBuilder eCIStringBuilder3 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i + 2, eCIStringBuilder3);
                        try {
                            pDF417ResultMetadata.setTimestamp(Long.parseLong(eCIStringBuilder3.toString()));
                            break;
                        } catch (NumberFormatException unused3) {
                            throw FormatException.getFormatInstance();
                        }
                    case 3:
                        ECIStringBuilder eCIStringBuilder4 = new ECIStringBuilder();
                        i = textCompaction(iArr, i + 2, eCIStringBuilder4);
                        pDF417ResultMetadata.setSender(eCIStringBuilder4.toString());
                        break;
                    case 4:
                        ECIStringBuilder eCIStringBuilder5 = new ECIStringBuilder();
                        i = textCompaction(iArr, i + 2, eCIStringBuilder5);
                        pDF417ResultMetadata.setAddressee(eCIStringBuilder5.toString());
                        break;
                    case 5:
                        ECIStringBuilder eCIStringBuilder6 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i + 2, eCIStringBuilder6);
                        try {
                            pDF417ResultMetadata.setFileSize(Long.parseLong(eCIStringBuilder6.toString()));
                            break;
                        } catch (NumberFormatException unused4) {
                            throw FormatException.getFormatInstance();
                        }
                    case 6:
                        ECIStringBuilder eCIStringBuilder7 = new ECIStringBuilder();
                        i = numericCompaction(iArr, i + 2, eCIStringBuilder7);
                        try {
                            pDF417ResultMetadata.setChecksum(Integer.parseInt(eCIStringBuilder7.toString()));
                            break;
                        } catch (NumberFormatException unused5) {
                            throw FormatException.getFormatInstance();
                        }
                    default:
                        throw FormatException.getFormatInstance();
                }
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (i4 != -1) {
            int i6 = i - i4;
            if (pDF417ResultMetadata.isLastSegment()) {
                i6--;
            }
            if (i6 > 0) {
                pDF417ResultMetadata.setOptionalData(Arrays.copyOfRange(iArr, i4, i6 + i4));
            }
        }
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:28:0x0039. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:29:0x003c. Please report as an issue. */
    private static int textCompaction(int[] iArr, int i, ECIStringBuilder eCIStringBuilder) throws FormatException {
        int i2 = iArr[0];
        int[] iArr2 = new int[(i2 - i) * 2];
        int[] iArr3 = new int[(i2 - i) * 2];
        Mode mode = Mode.ALPHA;
        boolean z = false;
        int i3 = 0;
        while (i < iArr[0] && !z) {
            int i4 = i + 1;
            int i5 = iArr[i];
            if (i5 < 900) {
                iArr2[i3] = i5 / 30;
                iArr2[i3 + 1] = i5 % 30;
                i3 += 2;
            } else if (i5 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                iArr2[i3] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i += 2;
                iArr3[i3] = iArr[i4];
                i3++;
            } else if (i5 != ECI_CHARSET) {
                if (i5 != 928) {
                    switch (i5) {
                        case 900:
                            iArr2[i3] = 900;
                            i3++;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i5) {
                            }
                    }
                }
                z = true;
            } else {
                Mode decodeTextCompaction = decodeTextCompaction(iArr2, iArr3, i3, eCIStringBuilder, mode);
                i += 2;
                eCIStringBuilder.appendECI(iArr[i4]);
                int i6 = iArr[0];
                if (i > i6) {
                    throw FormatException.getFormatInstance();
                }
                i3 = 0;
                mode = decodeTextCompaction;
                iArr3 = new int[(i6 - i) * 2];
                iArr2 = new int[(i6 - i) * 2];
            }
            i = i4;
        }
        decodeTextCompaction(iArr2, iArr3, i3, eCIStringBuilder, mode);
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Mode decodeTextCompaction(int[] iArr, int[] iArr2, int i, ECIStringBuilder eCIStringBuilder, Mode mode) {
        Mode mode2;
        int i2;
        char c;
        char c2;
        Mode mode3;
        Mode mode4 = mode;
        Mode mode5 = mode4;
        Mode mode6 = mode5;
        int i3 = 0;
        while (i3 < i) {
            int i4 = iArr[i3];
            char c3 = ' ';
            switch (AnonymousClass1.$SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[mode5.ordinal()]) {
                case 1:
                    if (i4 < 26) {
                        i2 = i4 + 65;
                        c = (char) i2;
                        Mode mode7 = mode6;
                        mode6 = mode5;
                        c2 = c;
                        mode3 = mode7;
                        break;
                    } else {
                        if (i4 != 900) {
                            if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                switch (i4) {
                                    case 27:
                                        mode4 = Mode.LOWER;
                                        break;
                                    case 28:
                                        mode4 = Mode.MIXED;
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                                        mode2 = Mode.PUNCT_SHIFT;
                                        c3 = 0;
                                        Mode mode8 = mode2;
                                        mode6 = mode5;
                                        mode5 = mode8;
                                        break;
                                }
                                mode3 = mode6;
                                mode6 = mode5;
                                c2 = c3;
                                break;
                            } else {
                                eCIStringBuilder.append((char) iArr2[i3]);
                            }
                            c3 = 0;
                            mode3 = mode6;
                            mode6 = mode5;
                            c2 = c3;
                        } else {
                            mode4 = Mode.ALPHA;
                        }
                        c3 = 0;
                        mode5 = mode4;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    }
                case 2:
                    if (i4 < 26) {
                        i2 = i4 + 97;
                        c = (char) i2;
                        Mode mode72 = mode6;
                        mode6 = mode5;
                        c2 = c;
                        mode3 = mode72;
                        break;
                    } else {
                        if (i4 != 900) {
                            if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                switch (i4) {
                                    case 27:
                                        mode2 = Mode.ALPHA_SHIFT;
                                        c3 = 0;
                                        Mode mode82 = mode2;
                                        mode6 = mode5;
                                        mode5 = mode82;
                                        break;
                                    case 28:
                                        mode4 = Mode.MIXED;
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                                        mode2 = Mode.PUNCT_SHIFT;
                                        c3 = 0;
                                        Mode mode822 = mode2;
                                        mode6 = mode5;
                                        mode5 = mode822;
                                        break;
                                }
                                mode3 = mode6;
                                mode6 = mode5;
                                c2 = c3;
                                break;
                            } else {
                                eCIStringBuilder.append((char) iArr2[i3]);
                            }
                            c3 = 0;
                            mode3 = mode6;
                            mode6 = mode5;
                            c2 = c3;
                        } else {
                            mode4 = Mode.ALPHA;
                        }
                        c3 = 0;
                        mode5 = mode4;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    }
                case 3:
                    if (i4 < 25) {
                        c = MIXED_CHARS[i4];
                        Mode mode722 = mode6;
                        mode6 = mode5;
                        c2 = c;
                        mode3 = mode722;
                        break;
                    } else {
                        if (i4 != 900) {
                            if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                                switch (i4) {
                                    case 25:
                                        mode4 = Mode.PUNCT;
                                        c3 = 0;
                                        mode5 = mode4;
                                        break;
                                    case 27:
                                        mode4 = Mode.LOWER;
                                        c3 = 0;
                                        mode5 = mode4;
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                                        mode2 = Mode.PUNCT_SHIFT;
                                        c3 = 0;
                                        Mode mode8222 = mode2;
                                        mode6 = mode5;
                                        mode5 = mode8222;
                                        break;
                                }
                                mode3 = mode6;
                                mode6 = mode5;
                                c2 = c3;
                                break;
                            } else {
                                eCIStringBuilder.append((char) iArr2[i3]);
                            }
                            c3 = 0;
                            mode3 = mode6;
                            mode6 = mode5;
                            c2 = c3;
                        }
                        mode4 = Mode.ALPHA;
                        c3 = 0;
                        mode5 = mode4;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    }
                case 4:
                    if (i4 >= 29) {
                        if (i4 == 29 || i4 == 900) {
                            mode4 = Mode.ALPHA;
                            mode5 = mode4;
                        } else if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            eCIStringBuilder.append((char) iArr2[i3]);
                        }
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = 0;
                        break;
                    } else {
                        c = PUNCT_CHARS[i4];
                        Mode mode7222 = mode6;
                        mode6 = mode5;
                        c2 = c;
                        mode3 = mode7222;
                        break;
                    }
                case 5:
                    if (i4 < 26) {
                        c2 = (char) (i4 + 65);
                        mode3 = mode6;
                        break;
                    } else {
                        if (i4 != 26) {
                            if (i4 == 900) {
                                mode5 = Mode.ALPHA;
                                c3 = 0;
                                mode3 = mode6;
                                mode6 = mode5;
                                c2 = c3;
                                break;
                            } else {
                                c3 = 0;
                            }
                        }
                        mode5 = mode6;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = c3;
                    }
                case 6:
                    if (i4 < 29) {
                        c2 = PUNCT_CHARS[i4];
                    } else if (i4 == 29 || i4 == 900) {
                        mode5 = Mode.ALPHA;
                        mode3 = mode6;
                        mode6 = mode5;
                        c2 = 0;
                        break;
                    } else {
                        if (i4 == MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                            eCIStringBuilder.append((char) iArr2[i3]);
                        }
                        c2 = 0;
                    }
                    mode3 = mode6;
                    break;
                default:
                    mode3 = mode6;
                    mode6 = mode5;
                    c2 = 0;
                    break;
            }
            if (c2 != 0) {
                eCIStringBuilder.append(c2);
            }
            i3++;
            mode5 = mode6;
            mode6 = mode3;
        }
        return mode4;
    }

    /* renamed from: com.google.zxing.pdf417.decoder.DecodedBitStreamParser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode = iArr;
            try {
                iArr[Mode.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode[Mode.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0046, code lost:
    
        if (r11 == com.google.zxing.pdf417.decoder.DecodedBitStreamParser.BYTE_COMPACTION_MODE_LATCH_6) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004a, code lost:
    
        if (r8 >= r12[0]) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x004e, code lost:
    
        if (r12[r8] >= 900) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0050, code lost:
    
        r13 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0052, code lost:
    
        if (r13 >= 6) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0054, code lost:
    
        r14.append((byte) (r6 >> ((5 - r13) * 8)));
        r13 = r13 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int byteCompaction(int r11, int[] r12, int r13, com.google.zxing.common.ECIStringBuilder r14) throws com.google.zxing.FormatException {
        /*
            r0 = 0
            r1 = r0
        L2:
            r2 = r12[r0]
            if (r13 >= r2) goto L87
            if (r1 != 0) goto L87
        L8:
            r2 = r12[r0]
            r3 = 927(0x39f, float:1.299E-42)
            if (r13 >= r2) goto L1c
            r4 = r12[r13]
            if (r4 != r3) goto L1c
            int r2 = r13 + 1
            r2 = r12[r2]
            r14.appendECI(r2)
            int r13 = r13 + 2
            goto L8
        L1c:
            r4 = 1
            if (r13 >= r2) goto L84
            r2 = r12[r13]
            r5 = 900(0x384, float:1.261E-42)
            if (r2 < r5) goto L27
            goto L84
        L27:
            r6 = 0
            r2 = r0
        L2a:
            r8 = 900(0x384, double:4.447E-321)
            long r6 = r6 * r8
            int r8 = r13 + 1
            r13 = r12[r13]
            long r9 = (long) r13
            long r6 = r6 + r9
            int r2 = r2 + r4
            r13 = 5
            if (r2 >= r13) goto L42
            r9 = r12[r0]
            if (r8 >= r9) goto L42
            r9 = r12[r8]
            if (r9 < r5) goto L40
            goto L42
        L40:
            r13 = r8
            goto L2a
        L42:
            if (r2 != r13) goto L62
            r13 = 924(0x39c, float:1.295E-42)
            if (r11 == r13) goto L50
            r13 = r12[r0]
            if (r8 >= r13) goto L62
            r13 = r12[r8]
            if (r13 >= r5) goto L62
        L50:
            r13 = r0
        L51:
            r2 = 6
            if (r13 >= r2) goto L81
            int r2 = 5 - r13
            int r2 = r2 * 8
            long r2 = r6 >> r2
            int r2 = (int) r2
            byte r2 = (byte) r2
            r14.append(r2)
            int r13 = r13 + 1
            goto L51
        L62:
            int r8 = r8 - r2
        L63:
            r13 = r12[r0]
            if (r8 >= r13) goto L81
            if (r1 != 0) goto L81
            int r13 = r8 + 1
            r2 = r12[r8]
            if (r2 >= r5) goto L75
            byte r2 = (byte) r2
            r14.append(r2)
            r8 = r13
            goto L63
        L75:
            if (r2 != r3) goto L7f
            int r8 = r8 + 2
            r13 = r12[r13]
            r14.appendECI(r13)
            goto L63
        L7f:
            r1 = r4
            goto L63
        L81:
            r13 = r8
            goto L2
        L84:
            r1 = r4
            goto L2
        L87:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.byteCompaction(int, int[], int, com.google.zxing.common.ECIStringBuilder):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
    
        r10.append(decodeBase900toBase10(r0, r3));
        r3 = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003c A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int numericCompaction(int[] r8, int r9, com.google.zxing.common.ECIStringBuilder r10) throws com.google.zxing.FormatException {
        /*
            r0 = 15
            int[] r0 = new int[r0]
            r1 = 0
            r2 = r1
            r3 = r2
        L7:
            r4 = r8[r1]
            if (r9 >= r4) goto L47
            if (r2 != 0) goto L47
            int r5 = r9 + 1
            r6 = r8[r9]
            r7 = 1
            if (r5 != r4) goto L15
            r2 = r7
        L15:
            r4 = 900(0x384, float:1.261E-42)
            if (r6 >= r4) goto L1e
            r0[r3] = r6
            int r3 = r3 + 1
            goto L2f
        L1e:
            if (r6 == r4) goto L31
            r4 = 901(0x385, float:1.263E-42)
            if (r6 == r4) goto L31
            r4 = 927(0x39f, float:1.299E-42)
            if (r6 == r4) goto L31
            r4 = 928(0x3a0, float:1.3E-42)
            if (r6 == r4) goto L31
            switch(r6) {
                case 922: goto L31;
                case 923: goto L31;
                case 924: goto L31;
                default: goto L2f;
            }
        L2f:
            r9 = r5
            goto L32
        L31:
            r2 = r7
        L32:
            int r4 = r3 % 15
            if (r4 == 0) goto L3c
            r4 = 902(0x386, float:1.264E-42)
            if (r6 == r4) goto L3c
            if (r2 == 0) goto L7
        L3c:
            if (r3 <= 0) goto L7
            java.lang.String r3 = decodeBase900toBase10(r0, r3)
            r10.append(r3)
            r3 = r1
            goto L7
        L47:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.numericCompaction(int[], int, com.google.zxing.common.ECIStringBuilder):int");
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) != '1') {
            throw FormatException.getFormatInstance();
        }
        return bigInteger2.substring(1);
    }
}
