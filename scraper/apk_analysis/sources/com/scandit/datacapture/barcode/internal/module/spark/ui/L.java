package com.scandit.datacapture.barcode.internal.module.spark.ui;

import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class L implements F {
    public final WeakReference a;

    public L(SparkScanViewPresenter owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }
}
