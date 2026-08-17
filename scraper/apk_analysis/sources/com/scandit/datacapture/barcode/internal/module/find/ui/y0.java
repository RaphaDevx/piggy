package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.graphics.PointF;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItem;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class y0 {
    public final PointF a;
    public final BarcodeFindItem b;
    public final String c;

    public y0(PointF position, BarcodeFindItem barcodeFindItem, String barcodeData) {
        Intrinsics.checkNotNullParameter(position, "position");
        Intrinsics.checkNotNullParameter(barcodeData, "barcodeData");
        this.a = position;
        this.b = barcodeFindItem;
        this.c = barcodeData;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y0)) {
            return false;
        }
        y0 y0Var = (y0) obj;
        return Intrinsics.areEqual(this.a, y0Var.a) && Intrinsics.areEqual(this.b, y0Var.b) && Intrinsics.areEqual(this.c, y0Var.c);
    }

    public final int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        BarcodeFindItem barcodeFindItem = this.b;
        return this.c.hashCode() + ((hashCode + (barcodeFindItem == null ? 0 : barcodeFindItem.hashCode())) * 31);
    }

    public final String toString() {
        return "DotInfo(position=" + this.a + ", barcodeFindItem=" + this.b + ", barcodeData=" + this.c + ')';
    }
}
