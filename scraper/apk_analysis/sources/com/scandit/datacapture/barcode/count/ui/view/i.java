package com.scandit.datacapture.barcode.count.ui.view;

import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.t;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i implements t {
    public final WeakReference a;

    public i(BarcodeCountView owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }
}
