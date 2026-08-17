package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class EanUpcAddOn extends Symbol {
    private static final String[] EAN_SET_A = {"3211", "2221", "2122", "1411", "1132", "1231", "1114", "1312", "1213", "3112"};
    private static final String[] EAN_SET_B = {"1123", "1222", "2212", "1141", "2311", "1321", "4111", "2131", "3121", "2113"};
    private static final String[] EAN2_PARITY = {"AA", "AB", "BA", "BB"};
    private static final String[] EAN5_PARITY = {"BBAAA", "BABAA", "BAABA", "BAAAB", "ABBAA", "AABBA", "AAABB", "ABABA", "ABAAB", "AABAB"};

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        if (this.content.length() > 5) {
            throw new OkapiException("Input data too long");
        }
        int i = this.content.length() <= 2 ? 2 : 5;
        if (this.content.length() < i) {
            for (int length = this.content.length(); length < i; length++) {
                this.content = "0" + this.content;
            }
        }
        String str = this.content;
        String ean2 = i == 2 ? ean2(str) : ean5(str);
        this.readable = this.content;
        this.pattern = new String[]{ean2};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private static String ean2(String str) {
        String str2 = EAN2_PARITY[(((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0')) % 4];
        StringBuilder sb = new StringBuilder("112");
        for (int i = 0; i < 2; i++) {
            int charAt = str.charAt(i) - '0';
            if (str2.charAt(i) == 'B') {
                sb.append(EAN_SET_B[charAt]);
            } else {
                sb.append(EAN_SET_A[charAt]);
            }
            if (i != 1) {
                sb.append("11");
            }
        }
        return sb.toString();
    }

    private static String ean5(String str) {
        int charAt;
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            if (i2 % 2 == 0) {
                charAt = (str.charAt(i2) - '0') * 3;
            } else {
                charAt = (str.charAt(i2) - '0') * 9;
            }
            i += charAt;
        }
        String str2 = EAN5_PARITY[i % 10];
        StringBuilder sb = new StringBuilder("112");
        for (int i3 = 0; i3 < 5; i3++) {
            int charAt2 = str.charAt(i3) - '0';
            if (str2.charAt(i3) == 'B') {
                sb.append(EAN_SET_B[charAt2]);
            } else {
                sb.append(EAN_SET_A[charAt2]);
            }
            if (i3 != 4) {
                sb.append("11");
            }
        }
        return sb.toString();
    }
}
