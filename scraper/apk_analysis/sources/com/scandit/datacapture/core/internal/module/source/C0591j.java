package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise;
import java.lang.ref.WeakReference;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.j, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0591j extends Lambda implements Function0 {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ C0595n b;
    public final /* synthetic */ NativeCameraDelegateSettings c;
    public final /* synthetic */ NativeWrappedPromise d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0591j(boolean z, C0595n c0595n, NativeCameraDelegateSettings nativeCameraDelegateSettings, NativeWrappedPromise nativeWrappedPromise) {
        super(0);
        this.a = z;
        this.b = c0595n;
        this.c = nativeCameraDelegateSettings;
        this.d = nativeWrappedPromise;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        if (this.a) {
            this.b.j = new WeakReference(this.c);
            this.b.a(new C0590i(this.d));
        } else {
            this.d.setError();
        }
        return Unit.INSTANCE;
    }
}
