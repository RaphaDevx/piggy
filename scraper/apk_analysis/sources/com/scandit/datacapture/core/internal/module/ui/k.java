package com.scandit.datacapture.core.internal.module.ui;

import android.graphics.SurfaceTexture;
import com.scandit.datacapture.core.internal.sdk.data.Subscription;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class k extends Lambda implements Function1 {
    public final /* synthetic */ SurfaceTexture a;
    public final /* synthetic */ DataCaptureTextureView b;
    public final /* synthetic */ Subscription c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(SurfaceTexture surfaceTexture, DataCaptureTextureView dataCaptureTextureView, Subscription subscription) {
        super(1);
        this.a = surfaceTexture;
        this.b = dataCaptureTextureView;
        this.c = subscription;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.core.internal.module.gl.l use = (com.scandit.datacapture.core.internal.module.gl.l) obj;
        Intrinsics.checkNotNullParameter(use, "$this$use");
        SurfaceTexture surfaceTexture = this.a;
        use.a(surfaceTexture, new j(this.b, this.c, surfaceTexture));
        return Unit.INSTANCE;
    }
}
