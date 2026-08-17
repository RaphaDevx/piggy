package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata;

import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.k;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e implements c {
    public final Size2 a;
    public final Function1 b;

    public e(Size2 minSize, com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.h quadrilateralMapper) {
        Intrinsics.checkNotNullParameter(minSize, "minSize");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        this.a = minSize;
        this.b = quadrilateralMapper;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.c
    public final a a(TrackedObject track, BarcodePickState pickState, BarcodePickState barcodePickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        return new a(track.getIdentifier(), QuadrilateralUtilsKt.boundingBox(k.a((Quadrilateral) this.b.invoke(track.getLocationIgnoringLicense$scandit_barcode_capture()), this.a)), pickState);
    }
}
