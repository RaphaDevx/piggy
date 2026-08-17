package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise;
import com.scandit.datacapture.core.internal.sdk.common.async.WrappedPromiseUtilsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0590i extends Lambda implements Function1 {
    public final /* synthetic */ NativeWrappedPromise a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0590i(NativeWrappedPromise nativeWrappedPromise) {
        super(1);
        this.a = nativeWrappedPromise;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        WrappedPromiseUtilsKt.synchronizedSetValueIfNoValue(this.a, ((Boolean) obj).booleanValue());
        return Unit.INSTANCE;
    }
}
