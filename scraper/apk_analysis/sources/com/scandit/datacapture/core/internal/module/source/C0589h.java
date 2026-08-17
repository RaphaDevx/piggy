package com.scandit.datacapture.core.internal.module.source;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.h, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0589h extends Lambda implements Function1 {
    public final /* synthetic */ NativeCameraDelegateSettings a;
    public final /* synthetic */ C0595n b;
    public final /* synthetic */ Function1 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0589h(NativeCameraDelegateSettings nativeCameraDelegateSettings, C0595n c0595n, Function1 function1) {
        super(1);
        this.a = nativeCameraDelegateSettings;
        this.b = c0595n;
        this.c = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.core.internal.module.gl.l use = (com.scandit.datacapture.core.internal.module.gl.l) obj;
        Intrinsics.checkNotNullParameter(use, "$this$use");
        C0588g surfaceCallback = new C0588g(this.b, this.c);
        int width = (int) this.a.frameResolution.getWidth();
        int height = (int) this.a.frameResolution.getHeight();
        use.getClass();
        Intrinsics.checkNotNullParameter(surfaceCallback, "surfaceCallback");
        com.scandit.datacapture.core.internal.module.gl.k kVar = new com.scandit.datacapture.core.internal.module.gl.k(surfaceCallback, width, height);
        com.scandit.datacapture.core.internal.module.gl.i iVar = use.g;
        iVar.sendMessage(iVar.obtainMessage(1, kVar));
        use.x = false;
        return Unit.INSTANCE;
    }
}
