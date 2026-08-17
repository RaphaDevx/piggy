package com.scandit.datacapture.core.internal.module.source.api2;

import android.os.Handler;
import com.scandit.datacapture.core.internal.sdk.data.Subscription;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class y0 extends Lambda implements Function1 {
    public final /* synthetic */ i0 a;
    public final /* synthetic */ Handler b;
    public final /* synthetic */ v0 c;
    public final /* synthetic */ Subscription d;
    public final /* synthetic */ int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(i0 i0Var, Handler handler, C0564g c0564g, Subscription subscription, int i) {
        super(1);
        this.a = i0Var;
        this.b = handler;
        this.c = c0564g;
        this.d = subscription;
        this.e = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.core.internal.module.gl.l use = (com.scandit.datacapture.core.internal.module.gl.l) obj;
        Intrinsics.checkNotNullParameter(use, "$this$use");
        x0 surfaceCallback = new x0(this.b, (C0564g) this.c, this.d, this.e);
        i0 i0Var = this.a;
        int i = i0Var.a;
        int i2 = i0Var.b;
        use.getClass();
        Intrinsics.checkNotNullParameter(surfaceCallback, "surfaceCallback");
        com.scandit.datacapture.core.internal.module.gl.k kVar = new com.scandit.datacapture.core.internal.module.gl.k(surfaceCallback, i, i2);
        com.scandit.datacapture.core.internal.module.gl.i iVar = use.g;
        iVar.sendMessage(iVar.obtainMessage(1, kVar));
        return Unit.INSTANCE;
    }
}
