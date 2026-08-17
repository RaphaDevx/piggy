package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise;
import com.scandit.datacapture.core.internal.sdk.common.async.WrappedPromiseUtilsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0594m extends Lambda implements Function1 {
    public final /* synthetic */ NativeWrappedPromise a;
    public final /* synthetic */ C0595n b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0594m(NativeWrappedPromise nativeWrappedPromise, C0595n c0595n) {
        super(1);
        this.a = nativeWrappedPromise;
        this.b = c0595n;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (booleanValue) {
            WrappedPromiseUtilsKt.synchronizedSetValueIfNoValue(this.a, booleanValue);
        } else {
            C0595n c0595n = this.b;
            NativeWrappedPromise nativeWrappedPromise = this.a;
            NativeCameraDelegateSettings nativeCameraDelegateSettings = (NativeCameraDelegateSettings) c0595n.j.get();
            if (nativeCameraDelegateSettings != null) {
                c0595n.shutDown();
                c0595n.startWithSettings(nativeCameraDelegateSettings, nativeWrappedPromise);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                WrappedPromiseUtilsKt.synchronizedSetValueIfNoValue(nativeWrappedPromise, false);
            }
        }
        return Unit.INSTANCE;
    }
}
