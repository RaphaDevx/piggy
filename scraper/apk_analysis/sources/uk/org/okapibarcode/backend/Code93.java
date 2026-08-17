package uk.org.okapibarcode.backend;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.JsonPointer;
import com.tealium.library.BuildConfig;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import uk.org.okapibarcode.util.Arrays;

/* loaded from: classes4.dex */
public class Code93 extends Symbol {
    private static final String[] CODE_93_CTRL = {"bU", "aA", "aB", "aC", "aD", "aE", "aF", "aG", "aH", "aI", "aJ", "aK", "aL", "aM", "aN", "aO", "aP", "aQ", "aR", "aS", "aT", "aU", "aV", "aW", "aX", "aY", "aZ", "bA", "bB", "bC", "bD", "bE", StringUtils.SPACE, "cA", "cB", "cC", "$", "%", "cF", "cG", "cH", "cI", "cJ", "+", "cL", "-", ".", "/", "0", "1", ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", BuildConfig.PUBLISH_SETTINGS_VERSION, "6", "7", "8", "9", "cZ", "bF", "bG", "bH", "bI", "bJ", "bV", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "bK", "bL", "bM", "bN", "bO", "bW", "dA", "dB", "dC", "dD", "dE", "dF", "dG", "dH", "dI", "dJ", "dK", "dL", "dM", "dN", "dO", "dP", "dQ", "dR", "dS", "dT", "dU", "dV", "dW", "dX", "dY", "dZ", "bP", "bQ", "bR", "bS", "bT"};
    private static final char[] CODE_93_LOOKUP = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '-', ClassUtils.PACKAGE_SEPARATOR_CHAR, ' ', '$', JsonPointer.SEPARATOR, '+', '%', 'a', 'b', 'c', 'd'};
    private static final String[] CODE_93_TABLE = {"131112", "111213", "111312", "111411", "121113", "121212", "121311", "111114", "131211", "141111", "211113", "211212", "211311", "221112", "221211", "231111", "112113", "112212", "112311", "122112", "132111", "111123", "111222", "111321", "121122", "131121", "212112", "212211", "211122", "211221", "221121", "222111", "112122", "112221", "122121", "123111", "121131", "311112", "311211", "321111", "112131", "113121", "211131", "121221", "312111", "311121", "122211"};
    private boolean showCheckDigits = true;
    private Character startStopDelimiter;

    public void setShowCheckDigits(boolean z) {
        this.showCheckDigits = z;
    }

    public boolean getShowCheckDigits() {
        return this.showCheckDigits;
    }

    public void setStartStopDelimiter(Character ch2) {
        this.startStopDelimiter = ch2;
    }

    public Character getStartStopDelimiter() {
        return this.startStopDelimiter;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[\u0000-\u007f]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        char[] controlChars = toControlChars(this.content);
        int length = controlChars.length;
        int[] iArr = new int[controlChars.length + 2];
        for (int i = 0; i < length; i++) {
            iArr[i] = Arrays.positionOf(controlChars[i], CODE_93_LOOKUP);
        }
        int calculateCheckDigitC = calculateCheckDigitC(iArr, length);
        iArr[length] = calculateCheckDigitC;
        int i2 = length + 1;
        int calculateCheckDigitK = calculateCheckDigitK(iArr, i2);
        iArr[i2] = calculateCheckDigitK;
        this.readable = this.content;
        if (this.showCheckDigits) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.readable);
            char[] cArr = CODE_93_LOOKUP;
            sb.append(cArr[calculateCheckDigitC]);
            sb.append(cArr[calculateCheckDigitK]);
            this.readable = sb.toString();
        }
        if (this.startStopDelimiter != null) {
            this.readable = this.startStopDelimiter + this.readable + this.startStopDelimiter;
        }
        infoLine("Check Digit C: " + calculateCheckDigitC);
        infoLine("Check Digit K: " + calculateCheckDigitK);
        this.pattern = new String[]{toPattern(iArr)};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private static char[] toControlChars(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(CODE_93_CTRL[c]);
        }
        return sb.toString().toCharArray();
    }

    private static int calculateCheckDigitC(int[] iArr, int i) {
        int i2 = 0;
        int i3 = 1;
        for (int i4 = i - 1; i4 >= 0; i4--) {
            i2 += iArr[i4] * i3;
            i3++;
            if (i3 == 21) {
                i3 = 1;
            }
        }
        return i2 % 47;
    }

    private static int calculateCheckDigitK(int[] iArr, int i) {
        int i2 = 0;
        int i3 = 1;
        for (int i4 = i - 1; i4 >= 0; i4--) {
            i2 += iArr[i4] * i3;
            i3++;
            if (i3 == 16) {
                i3 = 1;
            }
        }
        return i2 % 47;
    }

    private static String toPattern(int[] iArr) {
        StringBuilder sb = new StringBuilder("111141");
        for (int i : iArr) {
            sb.append(CODE_93_TABLE[i]);
        }
        sb.append("1111411");
        return sb.toString();
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected int[] getCodewords() {
        return getPatternAsCodewords(6);
    }
}
