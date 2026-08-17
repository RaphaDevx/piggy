package com.scandit.datacapture.barcode.internal.module.spark.ui;

import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class K implements com.scandit.datacapture.barcode.internal.module.spark.ui.toast.o {
    public final WeakReference a;

    public K(SparkScanViewPresenter owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }
}
