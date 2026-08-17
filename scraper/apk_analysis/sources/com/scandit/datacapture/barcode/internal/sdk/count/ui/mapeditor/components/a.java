package com.scandit.datacapture.barcode.internal.sdk.count.ui.mapeditor.components;

/* loaded from: classes2.dex */
public final class a {
    public final float a;
    public final float b;

    public a(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final float a(float f) {
        return (f * this.b) + ((1.0f - f) * this.a);
    }
}
