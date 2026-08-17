package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements b {
    public final Function1 a;
    public final f b;

    public c(Function1 quadrilateralMapper, f drawSettings) {
        Intrinsics.checkNotNullParameter(quadrilateralMapper, "quadrilateralMapper");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        this.a = quadrilateralMapper;
        this.b = drawSettings;
    }
}
