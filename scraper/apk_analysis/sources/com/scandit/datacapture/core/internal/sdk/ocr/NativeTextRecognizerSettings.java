package com.scandit.datacapture.core.internal.sdk.ocr;

/* loaded from: classes2.dex */
public final class NativeTextRecognizerSettings {
    final boolean combineCapturedTextsIntoSingleResult;
    final float maximumLineHeight;
    final float minimumLineHeight;

    public NativeTextRecognizerSettings(float f, float f2, boolean z) {
        this.minimumLineHeight = f;
        this.maximumLineHeight = f2;
        this.combineCapturedTextsIntoSingleResult = z;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NativeTextRecognizerSettings)) {
            return false;
        }
        NativeTextRecognizerSettings nativeTextRecognizerSettings = (NativeTextRecognizerSettings) obj;
        return this.minimumLineHeight == nativeTextRecognizerSettings.minimumLineHeight && this.maximumLineHeight == nativeTextRecognizerSettings.maximumLineHeight && this.combineCapturedTextsIntoSingleResult == nativeTextRecognizerSettings.combineCapturedTextsIntoSingleResult;
    }

    public boolean getCombineCapturedTextsIntoSingleResult() {
        return this.combineCapturedTextsIntoSingleResult;
    }

    public float getMaximumLineHeight() {
        return this.maximumLineHeight;
    }

    public float getMinimumLineHeight() {
        return this.minimumLineHeight;
    }

    public int hashCode() {
        return ((Float.floatToIntBits(this.maximumLineHeight) + ((Float.floatToIntBits(this.minimumLineHeight) + 527) * 31)) * 31) + (this.combineCapturedTextsIntoSingleResult ? 1 : 0);
    }

    public String toString() {
        return "NativeTextRecognizerSettings{minimumLineHeight=" + this.minimumLineHeight + ",maximumLineHeight=" + this.maximumLineHeight + ",combineCapturedTextsIntoSingleResult=" + this.combineCapturedTextsIntoSingleResult + "}";
    }
}
