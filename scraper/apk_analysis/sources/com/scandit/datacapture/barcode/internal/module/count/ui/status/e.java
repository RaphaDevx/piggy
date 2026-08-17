package com.scandit.datacapture.barcode.internal.module.count.ui.status;

import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountStatus;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e extends b {
    public final BarcodeCountStatus a;
    public final Integer b;

    public e(BarcodeCountStatus status, Integer num) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.a = status;
        this.b = num;
    }
}
