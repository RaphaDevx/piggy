package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.common.async.Callback;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class L extends Lambda implements Function0 {
    public final /* synthetic */ Callback a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L(Callback callback) {
        super(0);
        this.a = callback;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Callback callback = this.a;
        if (callback != null) {
            callback.run(Boolean.FALSE);
        }
        return Unit.INSTANCE;
    }
}
