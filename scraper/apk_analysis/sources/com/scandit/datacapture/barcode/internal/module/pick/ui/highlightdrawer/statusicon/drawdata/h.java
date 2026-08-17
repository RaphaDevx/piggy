package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata;

import android.content.Context;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.k;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.j;
import com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.n;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h implements c {
    public static final Lazy e = LazyKt.lazy(g.a);
    public static final Lazy f = LazyKt.lazy(f.a);
    public final Context a;
    public final Function1 b;
    public final j c;
    public final BarcodePickViewHighlightStyle d;

    public h(Context context, n quadrilateralMapper, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c cache, BarcodePickViewHighlightStyle highlightStyle) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        this.a = context;
        this.b = quadrilateralMapper;
        this.c = cache;
        this.d = highlightStyle;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.c
    public final a a(TrackedObject track, BarcodePickState pickState, BarcodePickState barcodePickState) {
        int intValue;
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        boolean z = false;
        boolean z2 = ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.g) this.c.a(track.getData(), pickState, barcodePickState)).a.a(this.a) != null;
        BarcodePickViewHighlightStyle barcodePickViewHighlightStyle = this.d;
        Lazy lazy = k.a;
        Intrinsics.checkNotNullParameter(barcodePickViewHighlightStyle, "<this>");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        if (!z2 && (((barcodePickViewHighlightStyle instanceof BarcodePickViewHighlightStyle.Dot) || (barcodePickViewHighlightStyle instanceof BarcodePickViewHighlightStyle.DotWithIcons)) && (pickState == BarcodePickState.IGNORE || pickState == BarcodePickState.UNKNOWN))) {
            z = true;
        }
        if (z) {
            intValue = ((Number) e.getValue()).intValue();
        } else {
            if (z) {
                throw new NoWhenBranchMatchedException();
            }
            intValue = ((Number) f.getValue()).intValue();
        }
        Point center = QuadrilateralUtilsKt.getCenter(QuadrilateralUtilsKt.boundingBox((Quadrilateral) this.b.invoke(track.getLocationIgnoringLicense$scandit_barcode_capture())));
        float f2 = intValue;
        return new a(track.getIdentifier(), new Quadrilateral(new Point(center.getX() - f2, center.getY() - f2), new Point(center.getX() + f2, center.getY() - f2), new Point(center.getX() + f2, center.getY() + f2), new Point(center.getX() - f2, center.getY() + f2)), pickState);
    }
}
