package com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories;

import android.content.Context;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickElementsCache;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class o extends Lambda implements Function1 {
    public final /* synthetic */ com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.k a;
    public final /* synthetic */ BarcodePickViewHighlightStyle.DotWithIcons b;
    public final /* synthetic */ BarcodePickElementsCache c;
    public final /* synthetic */ Context d;
    public final /* synthetic */ BarcodePickDataCaptureViewWrapper e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l lVar, BarcodePickViewHighlightStyle.DotWithIcons dotWithIcons, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c cVar, Context context, com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j jVar) {
        super(1);
        this.a = lVar;
        this.b = dotWithIcons;
        this.c = cVar;
        this.d = context;
        this.e = jVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        FrameLayout container = (FrameLayout) obj;
        Intrinsics.checkNotNullParameter(container, "container");
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.j a = ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.l) this.a).a(this.b);
        BarcodePickElementsCache barcodePickElementsCache = this.c;
        Intrinsics.checkNotNull(barcodePickElementsCache, "null cannot be cast to non-null type com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickStyleElementProvider<com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickStyleElement?>");
        BarcodePickViewHighlightStyle.DotWithIcons dotWithIcons = this.b;
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.b bVar = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.b.a;
        Context context = this.d;
        Intrinsics.checkNotNullExpressionValue(context, "$context");
        n quadrilateralMapper = new n((com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j) this.e);
        BarcodePickElementsCache elementsProvider = this.c;
        BarcodePickViewHighlightStyle.DotWithIcons highlightStyle = this.b;
        Intrinsics.checkNotNullParameter(bVar, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(elementsProvider, "elementsProvider");
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        return new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e(container, a, barcodePickElementsCache, dotWithIcons, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.h(context, quadrilateralMapper, (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c) elementsProvider, highlightStyle));
    }
}
