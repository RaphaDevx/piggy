package uk.org.okapibarcode.backend;

/* loaded from: classes4.dex */
public class ChannelCode extends Symbol {
    private int currentValue;
    private int targetValue;
    private int preferredNumberOfChannels = 3;
    private int[] space = new int[11];
    private int[] bar = new int[11];

    public void setPreferredNumberOfChannels(int i) {
        if (i < 3 || i > 8) {
            throw new IllegalArgumentException("Invalid number of channels: " + i);
        }
        this.preferredNumberOfChannels = i;
    }

    public int getPreferredNumberOfChannels() {
        return this.preferredNumberOfChannels;
    }

    @Override // uk.org.okapibarcode.backend.Symbol
    protected void encode() {
        if (this.content.length() > 7) {
            throw new OkapiException("Input data too long");
        }
        if (!this.content.matches("[0-9]+")) {
            throw new OkapiException("Invalid characters in data");
        }
        int i = this.preferredNumberOfChannels;
        int parseInt = Integer.parseInt(this.content);
        this.targetValue = parseInt;
        switch (i) {
            case 3:
                if (parseInt > 26) {
                    i++;
                }
            case 4:
                if (parseInt > 292) {
                    i++;
                }
            case 5:
                if (parseInt > 3493) {
                    i++;
                }
            case 6:
                if (parseInt > 44072) {
                    i++;
                }
            case 7:
                if (parseInt > 576688) {
                    i++;
                }
            case 8:
                if (parseInt > 7742862) {
                    i++;
                    break;
                }
                break;
        }
        if (i == 9) {
            throw new OkapiException("Value out of range");
        }
        infoLine("Channels Used: " + i);
        for (int i2 = 0; i2 < 11; i2++) {
            this.bar[i2] = 0;
            this.space[i2] = 0;
        }
        int[] iArr = this.bar;
        int[] iArr2 = this.space;
        iArr[2] = 1;
        iArr2[2] = 1;
        iArr[1] = 1;
        iArr2[1] = 1;
        iArr[0] = 1;
        this.currentValue = 0;
        this.pattern = new String[1];
        nextSpace(i, 3, i, i);
        StringBuilder sb = new StringBuilder();
        int length = (i - 1) - this.content.length();
        for (int i3 = 0; i3 < length; i3++) {
            sb.append('0');
        }
        sb.append(this.content);
        this.readable = sb.toString();
        this.row_count = 1;
        this.row_height = new int[]{-1};
    }

    private void nextSpace(int i, int i2, int i3, int i4) {
        for (int i5 = i2 < i + 2 ? 1 : i3; i5 <= i3; i5++) {
            this.space[i2] = i5;
            nextBar(i, i2, i4, (i3 + 1) - i5);
        }
    }

    private void nextBar(int i, int i2, int i3, int i4) {
        int[] iArr = this.space;
        int i5 = iArr[i2];
        int[] iArr2 = this.bar;
        int i6 = i2 - 1;
        int i7 = ((i5 + iArr2[i6]) + iArr[i6]) + iArr2[i2 + (-2)] > 4 ? 1 : 2;
        if (i2 < i + 2) {
            while (i7 <= i3) {
                this.bar[i2] = i7;
                nextSpace(i, i2 + 1, i4, (i3 + 1) - i7);
                i7++;
            }
            return;
        }
        if (i7 <= i3) {
            iArr2[i2] = i3;
            checkIfDone();
            this.currentValue++;
        }
    }

    private void checkIfDone() {
        if (this.currentValue == this.targetValue) {
            StringBuilder sb = new StringBuilder("11110");
            for (int i = 0; i < 11; i++) {
                sb.append((char) (this.space[i] + 48));
                sb.append((char) (this.bar[i] + 48));
            }
            this.pattern[0] = sb.toString();
        }
    }
}
