package com.scandit.datacapture.barcode.internal.module.spark.internal;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class i extends Lambda implements Function0 {
    public final /* synthetic */ NativeSparkScan a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(NativeSparkScan nativeSparkScan) {
        super(0);
        this.a = nativeSparkScan;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        NativeSparkScanSession session = this.a.getSession();
        Intrinsics.checkNotNullExpressionValue(session, "getSession(...)");
        return session;
    }
}
