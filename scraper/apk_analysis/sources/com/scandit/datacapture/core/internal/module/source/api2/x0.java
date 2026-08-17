package com.scandit.datacapture.core.internal.module.source.api2;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraDevice;
import android.os.Handler;
import com.scandit.datacapture.core.internal.sdk.data.Subscription;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class x0 extends Lambda implements Function1 {
    public final /* synthetic */ Handler a;
    public final /* synthetic */ v0 b;
    public final /* synthetic */ Subscription c;
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x0(Handler handler, C0564g c0564g, Subscription subscription, int i) {
        super(1);
        this.a = handler;
        this.b = c0564g;
        this.c = subscription;
        this.d = i;
    }

    public final void a(final SurfaceTexture surfaceTexture) {
        Intrinsics.checkNotNullParameter(surfaceTexture, "surfaceTexture");
        Handler handler = this.a;
        final v0 v0Var = this.b;
        final Subscription subscription = this.c;
        final int i = this.d;
        handler.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.source.api2.x0$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                x0.a(v0.this, subscription, surfaceTexture, i);
            }
        });
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        a((SurfaceTexture) obj);
        return Unit.INSTANCE;
    }

    public static final void a(v0 callback, Subscription renderSubscription, SurfaceTexture surfaceTexture, int i) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(renderSubscription, "$renderSubscription");
        Intrinsics.checkNotNullParameter(surfaceTexture, "$surfaceTexture");
        u0 holder = new u0(renderSubscription, surfaceTexture, i);
        C0564g c0564g = (C0564g) callback;
        c0564g.getClass();
        Intrinsics.checkNotNullParameter(holder, "holder");
        C0573p c0573p = c0564g.a;
        c0573p.j = holder;
        i0 i0Var = c0564g.b;
        Function1 function1 = c0564g.c;
        CameraDevice cameraDevice = c0573p.k;
        Handler a = c0573p.a();
        if (cameraDevice == null || a == null) {
            return;
        }
        InterfaceC0558b0 interfaceC0558b0 = c0573p.l;
        if (interfaceC0558b0 != null) {
            ((d0) interfaceC0558b0).b();
        }
        ((D) c0573p.f).a(a, cameraDevice, c0573p.a, holder, i0Var, new C0570m(c0573p, function1, holder));
    }
}
