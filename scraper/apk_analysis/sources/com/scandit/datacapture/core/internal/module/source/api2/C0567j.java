package com.scandit.datacapture.core.internal.module.source.api2;

import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.j, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0567j extends Lambda implements Function0 {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ C0573p b;
    public final /* synthetic */ NativeWrappedPromise c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0567j(boolean z, C0573p c0573p, NativeWrappedPromise nativeWrappedPromise) {
        super(0);
        this.a = z;
        this.b = c0573p;
        this.c = nativeWrappedPromise;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        if (this.a) {
            C0573p c0573p = this.b;
            C0566i c0566i = new C0566i(this.c);
            KProperty[] kPropertyArr = C0573p.v;
            c0573p.a(c0566i);
        } else {
            this.c.setError();
        }
        return Unit.INSTANCE;
    }
}
