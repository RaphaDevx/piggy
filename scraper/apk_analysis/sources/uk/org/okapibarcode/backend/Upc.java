package uk.org.okapibarcode.backend;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import uk.org.okapibarcode.graphics.TextAlignment;

/* loaded from: classes4.dex */
public class Upc extends Symbol {
    private static final String[] SET_AC = {"3211", "2221", "2122", "1411", "1132", "1231", "1114", "1312", "1213", "3112"};
    private static final String[] SET_B = {"1123", "1222", "2212", "1141", "2311", "1321", "4111", "2131", "3121", "2113"};
    private static final String[] UPC_PARITY_0 = {"BBBAAA", "BBABAA", "BBAABA", "BBAAAB", "BABBAA", "BAABBA", "BAAABB", "BABABA", "BABAAB", "BAABAB"};
    private static final String[] UPC_PARITY_1 = {"AAABBB", "AABABB", "AABBAB", "AABBBA", "ABAABB", "ABBAAB", "ABBBAA", "ABABAB", "ABABBA", "ABBABA"};
    private EanUpcAddOn addOn;
    private boolean linkageFlag;
    private Mode mode = Mode.UPCA;
    private boolean showCheckDigit = true;
    private int guardPatternExtraHeight = 5;

    public enum Mode {
        UPCA,
        UPCE
    }

    public Upc() {
        this.humanReadableAlignment = TextAlignment.JUSTIFY;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setShowCheckDigit(boolean z) {
        this.showCheckDigit = z;
    }

    public boolean getShowCheckDigit() {
        return this.showCheckDigit;
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
            throw new OkapiException("Missing UPC data");
        }
        if (this.mode == Mode.UPCA) {
            upca();
        } else {
            upce();
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

    private void upca() {
        this.content = Ean.validateAndPad(this.content, 11);
        char calcDigit = Ean.calcDigit(this.content);
        infoLine("Check Digit: " + calcDigit);
        String str = this.content + calcDigit;
        StringBuilder sb = new StringBuilder("111");
        for (int i = 0; i < 12; i++) {
            if (i == 6) {
                sb.append("11111");
            }
            sb.append(SET_AC[str.charAt(i) - '0']);
        }
        sb.append("111");
        this.readable = str;
        this.pattern = new String[]{sb.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private void upce() {
        this.content = Ean.validateAndPad(this.content, 7);
        String expandToEquivalentUpcA = expandToEquivalentUpcA(this.content, true);
        infoLine("UPC-A Equivalent: " + expandToEquivalentUpcA);
        char calcDigit = Ean.calcDigit(expandToEquivalentUpcA);
        infoLine("Check Digit: " + calcDigit);
        String str = this.content + calcDigit;
        String str2 = (getNumberSystem(this.content) == 1 ? UPC_PARITY_1 : UPC_PARITY_0)[calcDigit - '0'];
        StringBuilder sb = new StringBuilder("111");
        for (int i = 0; i < 6; i++) {
            if (str2.charAt(i) == 'A') {
                sb.append(SET_AC[this.content.charAt(i + 1) - '0']);
            } else {
                sb.append(SET_B[this.content.charAt(i + 1) - '0']);
            }
        }
        sb.append("111111");
        this.readable = str;
        this.pattern = new String[]{sb.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    protected String expandToEquivalentUpcA(String str, boolean z) {
        char c;
        char[] charArray = str.toCharArray();
        char[] cArr = new char[11];
        Arrays.fill(cArr, '0');
        cArr[0] = charArray[0];
        cArr[1] = charArray[1];
        cArr[2] = charArray[2];
        char c2 = charArray[6];
        switch (c2) {
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
            case '2':
                cArr[3] = c2;
                cArr[8] = charArray[3];
                cArr[9] = charArray[4];
                cArr[10] = charArray[5];
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                cArr[3] = charArray[3];
                cArr[9] = charArray[4];
                cArr[10] = charArray[5];
                if (z && ((c = charArray[3]) == '0' || c == '1' || c == '2')) {
                    throw new OkapiException("Invalid UPC-E data");
                }
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_TOP_OF /* 52 */:
                cArr[3] = charArray[3];
                cArr[4] = charArray[4];
                cArr[10] = charArray[5];
                if (z && charArray[4] == '0') {
                    throw new OkapiException("Invalid UPC-E data");
                }
                break;
            default:
                cArr[3] = charArray[3];
                cArr[4] = charArray[4];
                cArr[5] = charArray[5];
                cArr[10] = c2;
                if (z && charArray[5] == '0') {
                    throw new OkapiException("Invalid UPC-E data");
                }
                break;
        }
        return new String(cArr);
    }

    private static int getNumberSystem(String str) {
        char charAt = str.charAt(0);
        if (charAt == '0') {
            return 0;
        }
        if (charAt == '1') {
            return 1;
        }
        throw new OkapiException("Invalid input data");
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x005b, code lost:
    
        if (r8 == 94) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x006b, code lost:
    
        if (r8 == 50) goto L37;
     */
    @Override // uk.org.okapibarcode.backend.Symbol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void plotSymbol() {
        /*
            Method dump skipped, instructions count: 993
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.Upc.plotSymbol():void");
    }

    private int scale(int i) {
        return this.moduleWidth * i;
    }
}
