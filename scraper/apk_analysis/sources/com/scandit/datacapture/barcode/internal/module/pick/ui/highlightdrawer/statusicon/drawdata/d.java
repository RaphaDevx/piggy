package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata;

import android.view.View;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickElementsCache;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements c {
    public final BarcodePickElementsCache a;
    public final Function1 b;

    public d(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.f customViewCache, com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.i quadrilateralMapper) {
        Intrinsics.checkNotNullParameter(customViewCache, "customViewCache");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        this.a = customViewCache;
        this.b = quadrilateralMapper;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.c
    public final a a(TrackedObject track, BarcodePickState pickState, BarcodePickState barcodePickState) {
        View view;
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        Quadrilateral boundingBox = QuadrilateralUtilsKt.boundingBox((Quadrilateral) this.b.invoke(track.getLocationIgnoringLicense$scandit_barcode_capture()));
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.h hVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.h) this.a.a(track.getData(), pickState, barcodePickState);
        if (hVar == null || (view = hVar.a) == null) {
            return new a(track.getIdentifier(), boundingBox, pickState);
        }
        Intrinsics.checkNotNullParameter(view, "view");
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        Size2 size2 = new Size2(view.getMeasuredWidth(), view.getMeasuredHeight());
        float f = 2;
        float width = size2.getWidth() / f;
        float height = size2.getHeight() / f;
        Point center = QuadrilateralUtilsKt.getCenter(boundingBox);
        return new a(track.getIdentifier(), new Quadrilateral(new Point(center.getX() - width, center.getY() - height), new Point(center.getX() + width, center.getY() - height), new Point(center.getX() + width, center.getY() + height), new Point(center.getX() - width, center.getY() + height)), pickState);
    }
}
