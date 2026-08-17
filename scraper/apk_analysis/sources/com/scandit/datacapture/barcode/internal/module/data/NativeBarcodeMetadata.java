package com.scandit.datacapture.barcode.internal.module.data;

/* loaded from: classes2.dex */
public final class NativeBarcodeMetadata {
    final int errorCorrection;
    final int moduleCountX;
    final int moduleCountY;

    public NativeBarcodeMetadata(int i, int i2, int i3) {
        this.errorCorrection = i;
        this.moduleCountX = i2;
        this.moduleCountY = i3;
    }

    public int getErrorCorrection() {
        return this.errorCorrection;
    }

    public int getModuleCountX() {
        return this.moduleCountX;
    }

    public int getModuleCountY() {
        return this.moduleCountY;
    }

    public String toString() {
        return "NativeBarcodeMetadata{errorCorrection=" + this.errorCorrection + ",moduleCountX=" + this.moduleCountX + ",moduleCountY=" + this.moduleCountY + "}";
    }
}
