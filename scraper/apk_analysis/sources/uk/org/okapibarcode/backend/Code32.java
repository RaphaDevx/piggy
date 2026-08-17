package uk.org.okapibarcode.backend;

import androidx.exifinterface.media.ExifInterface;

/* loaded from: classes4.dex */
public class Code32 extends Symbol {
    private static final char[] TABLE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (this.content.length() > 8) {
            throw new OkapiException("Input too long");
        }
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in input");
        }
        String str = "";
        String str2 = "";
        for (int length = this.content.length(); length < 8; length++) {
            str2 = str2 + "0";
        }
        String str3 = str2 + this.content;
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            int i3 = i2 * 2;
            int numericValue = i + Character.getNumericValue(str3.charAt(i3));
            int numericValue2 = Character.getNumericValue(str3.charAt(i3 + 1)) * 2;
            if (numericValue2 >= 10) {
                numericValue2 -= 9;
            }
            i = numericValue + numericValue2;
        }
        char c = (char) ((i % 10) + 48);
        String str4 = str3 + c;
        infoLine("Check Digit: " + c);
        int i4 = 0;
        for (int i5 = 0; i5 < str4.length(); i5++) {
            i4 = (i4 * 10) + Character.getNumericValue(str4.charAt(i5));
        }
        int[] iArr = new int[6];
        int i6 = 33554432;
        for (int i7 = 5; i7 >= 0; i7--) {
            iArr[i7] = i4 / i6;
            i4 %= i6;
            i6 /= 32;
        }
        for (int i8 = 5; i8 >= 0; i8--) {
            str = str + TABLE[iArr[i8]];
        }
        this.readable = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS + str4;
        this.pattern = new String[1];
        this.row_count = 1;
        this.row_height = new int[]{-1};
        infoLine("Code 39 Equivalent: " + str);
        Code3Of9 code3Of9 = new Code3Of9();
        code3Of9.setContent(str);
        this.pattern[0] = code3Of9.pattern[0];
    }
}
