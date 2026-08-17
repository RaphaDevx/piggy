package com.scandit.datacapture.core.internal.module.source.api2;

import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise;
import com.scandit.datacapture.core.internal.sdk.common.async.WrappedPromiseUtilsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.k, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0568k extends Lambda implements Function1 {
    public final /* synthetic */ NativeWrappedPromise a;
    public final /* synthetic */ C0573p b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0568k(NativeWrappedPromise nativeWrappedPromise, C0573p c0573p) {
        super(1);
        this.a = nativeWrappedPromise;
        this.b = c0573p;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        NativeWrappedPromise nativeWrappedPromise = this.a;
        WrappedPromiseUtilsKt.synchronizedHasNoValue(nativeWrappedPromise, new C0567j(booleanValue, this.b, nativeWrappedPromise));
        return Unit.INSTANCE;
    }
}
