package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.common.async.Callback;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class F extends Lambda implements Function1 {
    public final /* synthetic */ Callback a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(Callback callback) {
        super(1);
        this.a = callback;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        Callback callback = this.a;
        if (callback != null) {
            callback.run(bool);
        }
        return Unit.INSTANCE;
    }
}
