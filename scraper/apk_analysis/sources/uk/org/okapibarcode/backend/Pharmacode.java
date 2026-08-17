package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class Pharmacode extends Symbol {
    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (this.content.length() > 6) {
            throw new OkapiException("Input too long");
        }
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        int parseInt = Integer.parseInt(this.content);
        if (parseInt < 3 || parseInt > 131070) {
            throw new OkapiException("Data out of range");
        }
        StringBuilder sb = new StringBuilder();
        do {
            if ((parseInt & 1) == 0) {
                sb.append('W');
                parseInt = (parseInt - 2) / 2;
            } else {
                sb.append('N');
                parseInt = (parseInt - 1) / 2;
            }
        } while (parseInt != 0);
        StringBuilder sb2 = new StringBuilder(sb.length() * 2);
        for (int length = sb.length() - 1; length >= 0; length--) {
            if (sb.charAt(length) == 'W') {
                sb2.append("32");
            } else {
                sb2.append("12");
            }
        }
        this.readable = "";
        this.pattern = new String[]{sb2.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }
}
