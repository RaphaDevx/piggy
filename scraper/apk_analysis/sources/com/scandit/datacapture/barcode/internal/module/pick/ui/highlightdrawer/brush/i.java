package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickBrush;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i implements b {
    public static final List e = CollectionsKt.listOf((Object[]) new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.l[]{new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.l(((Number) LazyKt.lazy(e.a).getValue()).floatValue(), ((Number) LazyKt.lazy(d.a).getValue()).floatValue(), ((Number) LazyKt.lazy(c.a).getValue()).intValue()), new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.l(((Number) LazyKt.lazy(h.a).getValue()).floatValue(), ((Number) LazyKt.lazy(g.a).getValue()).floatValue(), ((Number) LazyKt.lazy(f.a).getValue()).intValue())});
    public final Context a;
    public final com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.j b;
    public final Function1 c;
    public final j d;

    public i(Context context, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c elementsProvider, Function1 quadrilateralMapper, j drawSettings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(elementsProvider, "elementsProvider");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        this.a = context;
        this.b = elementsProvider;
        this.c = quadrilateralMapper;
        this.d = drawSettings;
    }

    public final a a(TrackedObject track, BarcodePickState pickState, BarcodePickViewHighlightStyle highlightStyle) {
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.m a;
        v tVar;
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.g gVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.g) this.b.a(track.getData(), pickState, null);
        Drawable a2 = gVar.a.a(this.a);
        Quadrilateral quadrilateral = (Quadrilateral) this.c.invoke(track.getLocationIgnoringLicense$scandit_barcode_capture());
        Quadrilateral a3 = k.a(quadrilateral, this.d.f);
        Quadrilateral boundingBox = QuadrilateralUtilsKt.boundingBox(quadrilateral);
        Quadrilateral a4 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.n.a(quadrilateral, highlightStyle, this.d.b);
        BarcodePickBrush barcodePickBrush = gVar.b;
        boolean z = highlightStyle instanceof BarcodePickViewHighlightStyle.Dot;
        if (z ? true : highlightStyle instanceof BarcodePickViewHighlightStyle.DotWithIcons) {
            a = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.a.a(barcodePickBrush, e);
        } else {
            if (!(highlightStyle instanceof BarcodePickViewHighlightStyle.Rectangular ? true : highlightStyle instanceof BarcodePickViewHighlightStyle.RectangularWithIcons)) {
                if (highlightStyle instanceof BarcodePickViewHighlightStyle.CustomView) {
                    throw new IllegalStateException("Cannot create brush data for CustomView style".toString());
                }
                throw new NoWhenBranchMatchedException();
            }
            Intrinsics.checkNotNullParameter(barcodePickBrush, "<this>");
            a = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.a.a(barcodePickBrush, CollectionsKt.emptyList());
        }
        boolean z2 = false;
        boolean z3 = a2 != null;
        if (highlightStyle instanceof BarcodePickViewHighlightStyle.Rectangular ? true : highlightStyle instanceof BarcodePickViewHighlightStyle.RectangularWithIcons) {
            tVar = new w(a3);
        } else {
            if (!(z ? true : highlightStyle instanceof BarcodePickViewHighlightStyle.DotWithIcons)) {
                if (highlightStyle instanceof BarcodePickViewHighlightStyle.CustomView) {
                    throw new IllegalStateException("Cannot create brush DrawData for CustomView style".toString());
                }
                throw new NoWhenBranchMatchedException();
            }
            Lazy lazy = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.k.a;
            Intrinsics.checkNotNullParameter(highlightStyle, "<this>");
            Intrinsics.checkNotNullParameter(pickState, "pickState");
            if (!z3 && ((z || (highlightStyle instanceof BarcodePickViewHighlightStyle.DotWithIcons)) && (pickState == BarcodePickState.IGNORE || pickState == BarcodePickState.UNKNOWN))) {
                z2 = true;
            }
            if (z2) {
                tVar = new x(a3);
            } else {
                if (z2) {
                    throw new NoWhenBranchMatchedException();
                }
                tVar = new t(a3);
            }
        }
        return new a(a, tVar, new w(a4), new w(boundingBox), pickState, track, a2);
    }
}
