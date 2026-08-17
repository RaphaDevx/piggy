package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import android.content.Context;
import android.graphics.Paint;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import java.util.LinkedHashMap;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class s {
    public static n a(BarcodePickBasicOverlay container, Function1 onTrackTap, BarcodePickViewHighlightStyle highlightStyle, Function1 quadrilateralMapper, j drawSettings, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c elementsProvider, Function1 function1, int i) {
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a aVar = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a.a;
        Function1 statusIconViewDrawerProvider = (i & 64) != 0 ? o.a : function1;
        Context context = container.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(elementsProvider, "elementsProvider");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        i drawDataFactory = new i(context, elementsProvider, quadrilateralMapper, drawSettings);
        LinkedHashMap drawingData = new LinkedHashMap();
        com.scandit.datacapture.barcode.internal.module.ui.k touchEventHandler = new com.scandit.datacapture.barcode.internal.module.ui.k(new p(drawingData), q.a, new r(onTrackTap));
        Paint tapPaint = u.a;
        Paint boundingBoxPaint = u.b;
        Intrinsics.checkNotNullParameter(aVar, "<this>");
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(onTrackTap, "onTrackTap");
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        Intrinsics.checkNotNullParameter(elementsProvider, "elementsProvider");
        Intrinsics.checkNotNullParameter(statusIconViewDrawerProvider, "statusIconViewDrawerProvider");
        Intrinsics.checkNotNullParameter(drawDataFactory, "drawDataFactory");
        Intrinsics.checkNotNullParameter(drawingData, "drawingData");
        Intrinsics.checkNotNullParameter(touchEventHandler, "touchEventHandler");
        Intrinsics.checkNotNullParameter(tapPaint, "tapPaint");
        Intrinsics.checkNotNullParameter(boundingBoxPaint, "boundingBoxPaint");
        return new n(container, highlightStyle, drawDataFactory, drawSettings, touchEventHandler, drawingData, tapPaint, boundingBoxPaint, statusIconViewDrawerProvider);
    }
}
