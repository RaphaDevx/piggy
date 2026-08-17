package com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview;

import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.ui.control.Control;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class k {
    public final Control a;
    public final Anchor b;
    public final PointWithUnit c;

    public k(Control control, Anchor anchor, PointWithUnit offset) {
        Intrinsics.checkNotNullParameter(control, "control");
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        Intrinsics.checkNotNullParameter(offset, "offset");
        this.a = control;
        this.b = anchor;
        this.c = offset;
    }
}
