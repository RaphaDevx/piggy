package uk.org.okapibarcode.backend;

import androidx.constraintlayout.widget.ConstraintLayout;
import uk.org.okapibarcode.graphics.Rectangle;

/* loaded from: classes4.dex */
public class AustraliaPost extends Symbol {
    private Mode mode = Mode.POST;
    private static final char[] CHARACTER_SET = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', '#'};
    private static final String[] N_ENCODING_TABLE = {"00", "01", "02", "10", "11", "12", "20", "21", "22", "30"};
    private static final String[] C_ENCODING_TABLE = {"222", "300", "301", "302", "310", "311", "312", "320", "321", "322", "000", "001", "002", "010", "011", "012", "020", "021", "022", "100", "101", "102", "110", "111", "112", "120", "121", "122", "200", "201", "202", "210", "211", "212", "220", "221", "023", "030", "031", "032", "033", "103", "113", "123", "130", "131", "132", "133", "203", "213", "223", "230", "231", "232", "233", "303", "313", "323", "330", "331", "332", "333", "003", "013"};
    private static final String[] BAR_VALUE_TABLE = {"000", "001", "002", "003", "010", "011", "012", "013", "020", "021", "022", "023", "030", "031", "032", "033", "100", "101", "102", "103", "110", "111", "112", "113", "120", "121", "122", "123", "130", "131", "132", "133", "200", "201", "202", "203", "210", "211", "212", "213", "220", "221", "222", "223", "230", "231", "232", "233", "300", "301", "302", "303", "310", "311", "312", "313", "320", "321", "322", "323", "330", "331", "332", "333"};

    public enum Mode {
        POST,
        REPLY,
        ROUTE,
        REDIRECT
    }

    private int barStateToDecimal(char c, int i) {
        return (c - 48) << i;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode() {
        return this.mode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0126, code lost:
    
        if (r1 != 23) goto L75;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x019d  */
    @Override // uk.org.okapibarcode.backend.Symbol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void encode() {
        /*
            Method dump skipped, instructions count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: uk.org.okapibarcode.backend.AustraliaPost.encode():void");
    }

    /* renamed from: uk.org.okapibarcode.backend.AustraliaPost$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$uk$org$okapibarcode$backend$AustraliaPost$Mode;

        static {
            int[] iArr = new int[Mode.values().length];
            $SwitchMap$uk$org$okapibarcode$backend$AustraliaPost$Mode = iArr;
            try {
                iArr[Mode.POST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$AustraliaPost$Mode[Mode.REPLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$AustraliaPost$Mode[Mode.ROUTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$uk$org$okapibarcode$backend$AustraliaPost$Mode[Mode.REDIRECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private CharSequence calcReedSolomon(CharSequence charSequence) {
        int i;
        int[] iArr = new int[31];
        int i2 = 0;
        int i3 = 2;
        while (true) {
            if (i3 >= charSequence.length()) {
                break;
            }
            iArr[i2] = barStateToDecimal(charSequence.charAt(i3), 4) + barStateToDecimal(charSequence.charAt(i3 + 1), 2) + barStateToDecimal(charSequence.charAt(i3 + 2), 0);
            i3 += 3;
            i2++;
        }
        ReedSolomon reedSolomon = new ReedSolomon();
        reedSolomon.init_gf(67);
        reedSolomon.init_code(4, 1);
        reedSolomon.encode(i2, iArr);
        StringBuilder sb = new StringBuilder();
        for (i = 4; i > 0; i--) {
            sb.append(BAR_VALUE_TABLE[reedSolomon.getResult(i - 1)]);
        }
        return sb;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        resetPlotElements();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i < this.pattern[i5].length(); i5 = 0) {
            switch (this.pattern[i5].charAt(i)) {
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                    i3 = i5;
                    i4 = 8;
                    continue;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    i3 = i5;
                    break;
                case '2':
                    i3 = 3;
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                    i4 = 2;
                    i3 = 3;
                    continue;
            }
            i4 = 5;
            this.rectangles.add(new Rectangle(i2, i3, 1, i4));
            i2 += 2;
            i++;
        }
        this.symbol_width = ((this.pattern[0].length() - 1) * 2) + 1;
        this.symbol_height = 8;
    }
}
