package com.scandit.datacapture.barcode.internal.module.ui.popover;

import android.util.Size;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class P {
    public final Size a;
    public final Size b;

    public P(Size horizontal, Size vertical) {
        Intrinsics.checkNotNullParameter(horizontal, "horizontal");
        Intrinsics.checkNotNullParameter(vertical, "vertical");
        this.a = horizontal;
        this.b = vertical;
    }
}
