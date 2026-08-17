package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class AztecRune extends Symbol {
    private static final int[] BIT_PLACEMENT_MAP = {1, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 29, 1, 0, 0, 0, 0, 0, 0, 0, 1, 9, 28, 1, 0, 1, 1, 1, 1, 1, 0, 1, 10, 27, 1, 0, 1, 0, 0, 0, 1, 0, 1, 11, 26, 1, 0, 1, 0, 1, 0, 1, 0, 1, 12, 25, 1, 0, 1, 0, 0, 0, 1, 0, 1, 13, 24, 1, 0, 1, 1, 1, 1, 1, 0, 1, 14, 23, 1, 0, 0, 0, 0, 0, 0, 0, 1, 15, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 22, 21, 20, 19, 18, 17, 16, 0, 0};

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        int charAt;
        int charAt2;
        char charAt3;
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid input data");
        }
        int length = this.content.length();
        if (length != 1) {
            if (length == 2) {
                charAt2 = (this.content.charAt(0) - '0') * 10;
                charAt3 = this.content.charAt(1);
            } else if (length == 3) {
                charAt2 = ((this.content.charAt(0) - '0') * 100) + ((this.content.charAt(1) - '0') * 10);
                charAt3 = this.content.charAt(2);
            } else {
                throw new OkapiException("Input too large");
            }
            charAt = charAt2 + (charAt3 - '0');
        } else {
            charAt = this.content.charAt(0) - '0';
        }
        if (charAt > 255) {
            throw new OkapiException("Input too large");
        }
        StringBuilder sb = new StringBuilder(28);
        for (int i = 128; i > 0; i >>= 1) {
            if ((charAt & i) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        int[] iArr = new int[3];
        iArr[0] = 0;
        iArr[1] = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            int i3 = i2 * 4;
            if (sb.charAt(i3) == '1') {
                iArr[i2] = iArr[i2] + 8;
            }
            if (sb.charAt(i3 + 1) == '1') {
                iArr[i2] = iArr[i2] + 4;
            }
            if (sb.charAt(i3 + 2) == '1') {
                iArr[i2] = iArr[i2] + 2;
            }
            if (sb.charAt(i3 + 3) == '1') {
                iArr[i2] = iArr[i2] + 1;
            }
        }
        int[] iArr2 = new int[6];
        ReedSolomon reedSolomon = new ReedSolomon();
        reedSolomon.init_gf(19);
        reedSolomon.init_code(5, 1);
        reedSolomon.encode(2, iArr);
        for (int i4 = 0; i4 < 5; i4++) {
            iArr2[i4] = reedSolomon.getResult(i4);
        }
        for (int i5 = 0; i5 < 5; i5++) {
            int i6 = 4 - i5;
            if ((iArr2[i6] & 8) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            if ((iArr2[i6] & 4) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            if ((iArr2[i6] & 2) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
            if ((iArr2[i6] & 1) != 0) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        StringBuilder sb2 = new StringBuilder(28);
        for (int i7 = 0; i7 < sb.length(); i7++) {
            if ((i7 & 1) == 0) {
                if (sb.charAt(i7) == '0') {
                    sb2.append('1');
                } else {
                    sb2.append('0');
                }
            } else {
                sb2.append(sb.charAt(i7));
            }
        }
        infoLine("Binary: " + ((Object) sb2));
        this.readable = "";
        this.pattern = new String[11];
        this.row_count = 11;
        this.row_height = new int[11];
        for (int i8 = 0; i8 < 11; i8++) {
            StringBuilder sb3 = new StringBuilder(11);
            for (int i9 = 0; i9 < 11; i9++) {
                int[] iArr3 = BIT_PLACEMENT_MAP;
                int i10 = (i8 * 11) + i9;
                if (iArr3[i10] == 1) {
                    sb3.append('1');
                }
                if (iArr3[i10] == 0) {
                    sb3.append('0');
                }
                int i11 = iArr3[i10];
                if (i11 >= 2) {
                    sb3.append(sb2.charAt(i11 - 2));
                }
            }
            this.pattern[i8] = bin2pat(sb3);
            this.row_height[i8] = 1;
        }
    }
}
