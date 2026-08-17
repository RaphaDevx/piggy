package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered;

import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import java.util.LinkedHashMap;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class e {
    public static d a(BarcodePickBasicOverlay container, Function1 quadrilateralMapper, f drawSettings) {
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a aVar = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a.a;
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        c drawDataFactory = new c(quadrilateralMapper, drawSettings);
        LinkedHashMap drawingData = new LinkedHashMap();
        Intrinsics.checkNotNullParameter(aVar, "<this>");
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        Intrinsics.checkNotNullParameter(drawDataFactory, "drawDataFactory");
        Intrinsics.checkNotNullParameter(drawingData, "drawingData");
        return new d(container, drawDataFactory, drawingData);
    }
}
