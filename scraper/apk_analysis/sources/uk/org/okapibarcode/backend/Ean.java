package uk.org.okapibarcode.backend;

import uk.org.okapibarcode.graphics.TextAlignment;

/* loaded from: classes4.dex */
public class Ean extends Symbol {
    private static final String[] EAN13_PARITY = {"AAAAAA", "AABABB", "AABBAB", "AABBBA", "ABAABB", "ABBAAB", "ABBBAA", "ABABAB", "ABABBA", "ABBABA"};
    private static final String[] EAN_SET_A = {"3211", "2221", "2122", "1411", "1132", "1231", "1114", "1312", "1213", "3112"};
    private static final String[] EAN_SET_B = {"1123", "1222", "2212", "1141", "2311", "1321", "4111", "2131", "3121", "2113"};
    private EanUpcAddOn addOn;
    private boolean linkageFlag;
    private Mode mode = Mode.EAN13;
    private int guardPatternExtraHeight = 5;

    public enum Mode {
        EAN8,
        EAN13
    }

    public Ean() {
        this.humanReadableAlignment = TextAlignment.JUSTIFY;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setGuardPatternExtraHeight(int i) {
        this.guardPatternExtraHeight = i;
    }

    public int getGuardPatternExtraHeight() {
        return this.guardPatternExtraHeight;
    }

    protected void setLinkageFlag(boolean z) {
        this.linkageFlag = z;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        separateContent();
        if (this.content.isEmpty()) {
            throw new OkapiException("Missing EAN data");
        }
        if (this.mode == Mode.EAN8) {
            ean8();
        } else {
            ean13();
        }
    }

    private void separateContent() {
        int indexOf = this.content.indexOf(43);
        if (indexOf == -1) {
            this.addOn = null;
            return;
        }
        if (indexOf == this.content.length() - 1) {
            throw new OkapiException("Invalid add-on data");
        }
        EanUpcAddOn eanUpcAddOn = new EanUpcAddOn();
        this.addOn = eanUpcAddOn;
        eanUpcAddOn.font = this.font;
        this.addOn.fontName = this.fontName;
        this.addOn.fontSize = this.fontSize;
        this.addOn.humanReadableLocation = this.humanReadableLocation == HumanReadableLocation.NONE ? HumanReadableLocation.NONE : HumanReadableLocation.TOP;
        this.addOn.moduleWidth = this.moduleWidth;
        this.addOn.default_height = (this.default_height + this.guardPatternExtraHeight) - 8;
        this.addOn.setContent(this.content.substring(indexOf + 1));
        this.content = this.content.substring(0, indexOf);
    }

    private void ean13() {
        this.content = validateAndPad(this.content, 12);
        char calcDigit = calcDigit(this.content);
        infoLine("Check Digit: " + calcDigit);
        String str = this.content + calcDigit;
        char charAt = str.charAt(0);
        String str2 = EAN13_PARITY[charAt - '0'];
        infoLine("Parity Digit: " + charAt);
        StringBuilder sb = new StringBuilder("111");
        for (int i = 1; i < 13; i++) {
            if (i == 7) {
                sb.append("11111");
            }
            if (i <= 6) {
                if (str2.charAt(i - 1) == 'B') {
                    sb.append(EAN_SET_B[str.charAt(i) - '0']);
                } else {
                    sb.append(EAN_SET_A[str.charAt(i) - '0']);
                }
            } else {
                sb.append(EAN_SET_A[str.charAt(i) - '0']);
            }
        }
        sb.append("111");
        this.readable = str;
        this.pattern = new String[]{sb.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private void ean8() {
        this.content = validateAndPad(this.content, 7);
        char calcDigit = calcDigit(this.content);
        infoLine("Check Digit: " + calcDigit);
        String str = this.content + calcDigit;
        StringBuilder sb = new StringBuilder("111");
        for (int i = 0; i < 8; i++) {
            if (i == 4) {
                sb.append("11111");
            }
            sb.append(EAN_SET_A[str.charAt(i) - '0']);
        }
        sb.append("111");
        this.readable = str;
        this.pattern = new String[]{sb.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    protected static String validateAndPad(String str, int i) {
        if (!str.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in input");
        }
        if (str.length() > i) {
            throw new OkapiException("Input data too long");
        }
        if (str.length() < i) {
            for (int length = str.length(); length < i; length++) {
                str = "0" + str;
            }
        }
        return str;
    }

    protected static char calcDigit(String str) {
        int i = 0;
        int i2 = 0;
        for (int length = str.length() - 1; length >= 0; length--) {
            int numericValue = Character.getNumericValue(str.charAt(length));
            if (i2 % 2 == 0) {
                numericValue *= 3;
            }
            i += numericValue;
            i2++;
        }
        int i3 = 10 - (i % 10);
        return (char) ((i3 != 10 ? i3 : 0) + 48);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0058, code lost:
    
        if (r7 == 94) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0072, code lost:
    
        if (r7 == 66) goto L42;
     */
    @Override // uk.org.okapibarcode.backend.Symbol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void plotSymbol() {
        /*
            Method dump skipped, instructions count: 857
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.Ean.plotSymbol():void");
    }

    private int scale(int i) {
        return this.moduleWidth * i;
    }
}
