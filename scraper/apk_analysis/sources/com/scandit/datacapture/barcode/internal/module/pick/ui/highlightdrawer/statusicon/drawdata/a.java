package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata;

import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a {
    public final int a;
    public final Quadrilateral b;
    public final BarcodePickState c;

    public a(int i, Quadrilateral referenceQuad, BarcodePickState pickState) {
        Intrinsics.checkNotNullParameter(referenceQuad, "referenceQuad");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        this.a = i;
        this.b = referenceQuad;
        this.c = pickState;
    }
}
