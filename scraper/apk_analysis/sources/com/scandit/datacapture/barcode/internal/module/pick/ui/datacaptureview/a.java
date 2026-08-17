package com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview;

import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.ui.control.Control;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a {
    public final Control a;
    public final boolean b;
    public final Anchor c;

    public a(Control control, boolean z, Anchor anchor) {
        Intrinsics.checkNotNullParameter(control, "control");
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        this.a = control;
        this.b = z;
        this.c = anchor;
    }
}
