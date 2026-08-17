package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class KoreaPost extends Symbol {
    private static final String[] KOREA_TABLE = {"1313150613", "0713131313", "0417131313", "1506131313", "0413171313", "17171313", "1315061313", "0413131713", "17131713", "13171713"};

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        if (this.content.length() > 6) {
            throw new OkapiException("Input data too long");
        }
        StringBuilder sb = new StringBuilder(6);
        for (int length = this.content.length(); length < 6; length++) {
            sb.append('0');
        }
        sb.append(this.content);
        String str = "";
        int i = 0;
        for (int i2 = 0; i2 < sb.length(); i2++) {
            int numericValue = Character.getNumericValue(sb.charAt(i2));
            str = str + KOREA_TABLE[numericValue];
            i += numericValue;
        }
        int i3 = 10 - (i % 10);
        int i4 = i3 != 10 ? i3 : 0;
        String str2 = str + KOREA_TABLE[i4];
        infoLine("Check Digit: " + i4);
        this.readable = sb.toString() + i4;
        this.pattern = new String[]{str2};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }
}
