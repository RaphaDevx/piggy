package com.scandit.datacapture.core.internal.sdk.common.async;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function0 {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ NativeWrappedPromise b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(boolean z, NativeWrappedPromise nativeWrappedPromise) {
        super(0);
        this.a = z;
        this.b = nativeWrappedPromise;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        if (this.a) {
            this.b.setDone();
        } else {
            this.b.setError();
        }
        return Unit.INSTANCE;
    }
}
