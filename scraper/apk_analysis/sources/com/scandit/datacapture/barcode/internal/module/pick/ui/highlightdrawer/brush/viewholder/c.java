package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.j;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.g;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements b {
    public final a a;
    public com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.a b;

    public c(View container, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.a initialDrawData, j drawSettings, Paint tapPaint, Paint boundingBoxPaint) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(initialDrawData, "initialDrawData");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        Intrinsics.checkNotNullParameter(tapPaint, "tapPaint");
        Intrinsics.checkNotNullParameter(boundingBoxPaint, "boundingBoxPaint");
        Context context = container.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        this.a = new a(context, initialDrawData, drawSettings, tapPaint, boundingBoxPaint);
        this.b = initialDrawData;
    }

    public final void a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.a drawData) {
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        BarcodePickState barcodePickState = this.b.e;
        this.b = drawData;
        a aVar = this.a;
        aVar.getClass();
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        aVar.a = drawData;
        aVar.invalidate();
        a aVar2 = this.a;
        BarcodePickState barcodePickState2 = drawData.e;
        Intrinsics.checkNotNullParameter(barcodePickState2, "<this>");
        aVar2.setElevation(PixelExtensionsKt.pxFromDp$default(g.a[barcodePickState2.ordinal()] == 1 ? 6.0f : 5.0f, (Context) null, 1, (Object) null));
    }
}
