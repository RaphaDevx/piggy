package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer;

import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class h {
    public static final float a(BarcodePickState barcodePickState) {
        Intrinsics.checkNotNullParameter(barcodePickState, "<this>");
        return g.a[barcodePickState.ordinal()] == 1 ? 6.0f : 5.0f;
    }
}
