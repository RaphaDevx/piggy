package com.scandit.datacapture.core.source;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public final /* synthetic */ Camera a;
    public final /* synthetic */ CameraSettings b;
    public final /* synthetic */ Runnable c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Camera camera, CameraSettings cameraSettings, Runnable runnable) {
        super(0);
        this.a = camera;
        this.b = cameraSettings;
        this.c = runnable;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.a.getBehaviorDelegate().a(this.b, this.c);
        return Unit.INSTANCE;
    }
}
