package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata;

import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.k;
import com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.s;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i implements c {
    public final Function1 a;
    public final Size2 b;

    public i(s quadrilateralMapper, Size2 minSize) {
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(minSize, "minSize");
        this.a = quadrilateralMapper;
        this.b = minSize;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.c
    public final a a(TrackedObject track, BarcodePickState pickState, BarcodePickState barcodePickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        return new a(track.getIdentifier(), k.a((Quadrilateral) this.a.invoke(track.getLocationIgnoringLicense$scandit_barcode_capture()), this.b), pickState);
    }
}
