package com.scandit.datacapture.barcode.internal.module.spark.data;

import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.j, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0431j implements com.scandit.datacapture.barcode.internal.module.spark.internal.u {
    public final WeakReference a;

    public C0431j(SparkScanStateManager owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.a = new WeakReference(owner);
    }
}
