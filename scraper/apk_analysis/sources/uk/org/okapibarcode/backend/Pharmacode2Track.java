package uk.org.okapibarcode.backend;

import uk.org.okapibarcode.graphics.Rectangle;

/* loaded from: classes4.dex */
public class Pharmacode2Track extends Symbol {
    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (this.content.length() > 8) {
            throw new OkapiException("Input too long");
        }
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        int parseInt = Integer.parseInt(this.content);
        if (parseInt < 4 || parseInt > 64570080) {
            throw new OkapiException("Data out of range");
        }
        StringBuilder sb = new StringBuilder();
        do {
            int i = parseInt % 3;
            if (i == 0) {
                sb.append('F');
                parseInt = (parseInt - 3) / 3;
            } else if (i == 1) {
                sb.append('D');
                parseInt = (parseInt - 1) / 3;
            } else if (i == 2) {
                sb.append('A');
                parseInt = (parseInt - 2) / 3;
            }
        } while (parseInt != 0);
        sb.reverse();
        infoLine("Encoding: " + ((Object) sb));
        this.readable = "";
        this.pattern = new String[]{sb.toString()};
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void plotSymbol() {
        resetPlotElements();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < this.pattern[0].length(); i4++) {
            char charAt = this.pattern[0].charAt(i4);
            if (charAt == 'A') {
                i3 = this.default_height / 2;
            } else {
                if (charAt == 'D') {
                    i2 = this.default_height / 2;
                    i3 = this.default_height / 2;
                } else if (charAt == 'F') {
                    i3 = this.default_height;
                }
                this.rectangles.add(new Rectangle(i, i2, 1, i3));
                i += 2;
            }
            i2 = 0;
            this.rectangles.add(new Rectangle(i, i2, 1, i3));
            i += 2;
        }
        this.symbol_width = this.pattern[0].length() * 2;
        this.symbol_height = this.default_height;
    }
}
