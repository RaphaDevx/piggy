package com.scandit.datacapture.core.common.buffer;

/* loaded from: classes2.dex */
public final class EncodingRange {
    final int endIndex;
    final String ianaName;
    final int startIndex;

    public EncodingRange(String str, int i, int i2) {
        this.ianaName = str;
        this.startIndex = i;
        this.endIndex = i2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EncodingRange)) {
            return false;
        }
        EncodingRange encodingRange = (EncodingRange) obj;
        return this.ianaName.equals(encodingRange.ianaName) && this.startIndex == encodingRange.startIndex && this.endIndex == encodingRange.endIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public String getIanaName() {
        return this.ianaName;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public int hashCode() {
        return ((((this.ianaName.hashCode() + 527) * 31) + this.startIndex) * 31) + this.endIndex;
    }

    public String toString() {
        return "EncodingRange{ianaName=" + this.ianaName + ",startIndex=" + this.startIndex + ",endIndex=" + this.endIndex + "}";
    }
}
