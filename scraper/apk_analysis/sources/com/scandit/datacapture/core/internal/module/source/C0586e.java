package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise;
import com.scandit.datacapture.core.internal.sdk.common.async.WrappedPromiseUtilsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0586e extends Lambda implements Function1 {
    public final /* synthetic */ NativeWrappedPromise a;
    public final /* synthetic */ C0595n b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0586e(NativeWrappedPromise nativeWrappedPromise, C0595n c0595n) {
        super(1);
        this.a = nativeWrappedPromise;
        this.b = c0595n;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        NativeWrappedPromise nativeWrappedPromise = this.a;
        WrappedPromiseUtilsKt.synchronizedHasNoValue(nativeWrappedPromise, new C0585d(booleanValue, this.b, nativeWrappedPromise));
        return Unit.INSTANCE;
    }
}
