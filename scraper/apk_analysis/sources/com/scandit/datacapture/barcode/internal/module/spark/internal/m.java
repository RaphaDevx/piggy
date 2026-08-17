package com.scandit.datacapture.barcode.internal.module.spark.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class m extends Lambda implements Function0 {
    public final /* synthetic */ SparkScanInternal a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(SparkScanInternal sparkScanInternal) {
        super(0);
        this.a = sparkScanInternal;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.a;
    }
}
