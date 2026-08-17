package com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories;

import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickElementsCache;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import com.scandit.datacapture.core.common.geometry.Size2;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class j extends Lambda implements Function1 {
    public final /* synthetic */ com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.k a;
    public final /* synthetic */ BarcodePickViewHighlightStyle.CustomView b;
    public final /* synthetic */ BarcodePickElementsCache c;
    public final /* synthetic */ BarcodePickViewSettings d;
    public final /* synthetic */ BarcodePickDataCaptureViewWrapper e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l lVar, BarcodePickViewHighlightStyle.CustomView customView, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.f fVar, BarcodePickViewSettings barcodePickViewSettings, com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j jVar) {
        super(1);
        this.a = lVar;
        this.b = customView;
        this.c = fVar;
        this.d = barcodePickViewSettings;
        this.e = jVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.c dVar;
        FrameLayout container = (FrameLayout) obj;
        Intrinsics.checkNotNullParameter(container, "container");
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.j a = ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l) this.a).a(this.b);
        BarcodePickElementsCache barcodePickElementsCache = this.c;
        Intrinsics.checkNotNull(barcodePickElementsCache, "null cannot be cast to non-null type com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickStyleElementProvider<com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickStyleElement?>");
        BarcodePickViewHighlightStyle.CustomView customView = this.b;
        boolean fitViewsToBarcode = customView.getFitViewsToBarcode();
        if (fitViewsToBarcode) {
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.b bVar = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.b.a;
            Size2 minSize = new Size2(this.d.getMinimumHighlightWidthPx$scandit_barcode_capture(), this.d.getMinimumHighlightHeightPx$scandit_barcode_capture());
            h quadrilateralMapper = new h((com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j) this.e);
            Intrinsics.checkNotNullParameter(bVar, "<this>");
            Intrinsics.checkNotNullParameter(minSize, "minSize");
            Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
            dVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.e(minSize, quadrilateralMapper);
        } else {
            if (fitViewsToBarcode) {
                throw new NoWhenBranchMatchedException();
            }
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.b bVar2 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.b.a;
            i quadrilateralMapper2 = new i((com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j) this.e);
            BarcodePickElementsCache cache = this.c;
            Intrinsics.checkNotNullParameter(bVar2, "<this>");
            Intrinsics.checkNotNullParameter(quadrilateralMapper2, "quadrilateralMapper");
            Intrinsics.checkNotNullParameter(cache, "cache");
            dVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.d((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.f) cache, quadrilateralMapper2);
        }
        return new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e(container, a, barcodePickElementsCache, customView, dVar);
    }
}
