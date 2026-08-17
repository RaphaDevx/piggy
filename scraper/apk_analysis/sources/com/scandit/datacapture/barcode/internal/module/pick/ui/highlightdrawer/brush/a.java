package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import android.graphics.drawable.Drawable;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a {
    public final com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.m a;
    public final v b;
    public final v c;
    public final v d;
    public final BarcodePickState e;
    public final TrackedObject f;
    public final Drawable g;

    public a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.m mVar, v drawData, w wVar, w boundingBoxData, BarcodePickState pickState, TrackedObject trackedObject, Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        Intrinsics.checkNotNullParameter(boundingBoxData, "boundingBoxData");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        Intrinsics.checkNotNullParameter(trackedObject, "trackedObject");
        this.a = mVar;
        this.b = drawData;
        this.c = wVar;
        this.d = boundingBoxData;
        this.e = pickState;
        this.f = trackedObject;
        this.g = drawable;
    }
}
