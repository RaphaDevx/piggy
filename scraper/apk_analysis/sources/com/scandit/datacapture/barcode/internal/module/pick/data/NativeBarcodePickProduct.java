package com.scandit.datacapture.barcode.internal.module.pick.data;

/* loaded from: classes2.dex */
public final class NativeBarcodePickProduct {
    final String identifier;
    final int quantity;

    public NativeBarcodePickProduct(String str, int i) {
        this.identifier = str;
        this.quantity = i;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public String toString() {
        return "NativeBarcodePickProduct{identifier=" + this.identifier + ",quantity=" + this.quantity + "}";
    }
}
