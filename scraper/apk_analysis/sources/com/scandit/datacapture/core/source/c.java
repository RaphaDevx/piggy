package com.scandit.datacapture.core.source;

import com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function1 {
    public final /* synthetic */ CameraBehaviorDelegate a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(com.scandit.datacapture.core.internal.module.source.camera.behavior.c cVar) {
        super(1);
        this.a = cVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        CameraSettings it = (CameraSettings) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.a(it, (Runnable) null);
        return Unit.INSTANCE;
    }
}
