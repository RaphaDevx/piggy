package com.scandit.datacapture.core.internal.module.source;

import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0585d extends Lambda implements Function0 {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ C0595n b;
    public final /* synthetic */ NativeWrappedPromise c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0585d(boolean z, C0595n c0595n, NativeWrappedPromise nativeWrappedPromise) {
        super(0);
        this.a = z;
        this.b = c0595n;
        this.c = nativeWrappedPromise;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        if (this.a) {
            C0595n c0595n = this.b;
            C0584c c0584c = new C0584c(this.c);
            HandlerC0554a handlerC0554a = c0595n.e;
            if (handlerC0554a != null) {
                handlerC0554a.sendMessage(handlerC0554a.obtainMessage(3, c0584c));
            }
        } else {
            this.c.setError();
        }
        return Unit.INSTANCE;
    }
}
