package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview;

import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import com.scandit.datacapture.core.common.geometry.Size2;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements b {
    public final Size2 a;
    public final boolean b;
    public final Function1 c;
    public final BarcodePickViewSettings d;

    public c(Size2 minSize, boolean z, com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories.f quadrilateralMapper, BarcodePickViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(minSize, "minSize");
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        this.a = minSize;
        this.b = z;
        this.c = quadrilateralMapper;
        this.d = viewSettings;
    }
}
