package com.scandit.datacapture.core.internal.module.source;

import android.graphics.SurfaceTexture;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0588g extends Lambda implements Function1 {
    public final /* synthetic */ C0595n a;
    public final /* synthetic */ Function1 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0588g(C0595n c0595n, Function1 function1) {
        super(1);
        this.a = c0595n;
        this.b = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        SurfaceTexture it = (SurfaceTexture) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        C0595n c0595n = this.a;
        Function1 function1 = this.b;
        c0595n.getClass();
        Pair pair = new Pair(it, function1);
        HandlerC0554a handlerC0554a = c0595n.e;
        if (handlerC0554a != null) {
            handlerC0554a.sendMessage(handlerC0554a.obtainMessage(1, pair));
        }
        return Unit.INSTANCE;
    }
}
