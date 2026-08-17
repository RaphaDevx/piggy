package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview;

import android.view.View;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.v;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.w;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.common.geometry.Rect;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a {
    public final View a;
    public final Rect b;
    public final v c;
    public final BarcodePickState d;
    public final TrackedObject e;

    public a(View view, Rect rect, w tapData, BarcodePickState pickState, TrackedObject trackedObject) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(rect, "rect");
        Intrinsics.checkNotNullParameter(tapData, "tapData");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        Intrinsics.checkNotNullParameter(trackedObject, "trackedObject");
        this.a = view;
        this.b = rect;
        this.c = tapData;
        this.d = pickState;
        this.e = trackedObject;
    }
}
