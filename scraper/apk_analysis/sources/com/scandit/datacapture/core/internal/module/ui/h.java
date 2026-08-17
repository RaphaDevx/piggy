package com.scandit.datacapture.core.internal.module.ui;

import android.graphics.SurfaceTexture;
import com.scandit.datacapture.core.ui.DataCaptureView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class h extends Lambda implements Function1 {
    public final /* synthetic */ DataCaptureTextureView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(DataCaptureTextureView dataCaptureTextureView) {
        super(1);
        this.a = dataCaptureTextureView;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DataCaptureView dataCaptureView;
        com.scandit.datacapture.core.internal.module.gl.l use = (com.scandit.datacapture.core.internal.module.gl.l) obj;
        Intrinsics.checkNotNullParameter(use, "$this$use");
        SurfaceTexture surfaceTexture = this.a.getSurfaceTexture();
        if (surfaceTexture == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        Intrinsics.checkNotNullExpressionValue(surfaceTexture, "checkNotNull(...)");
        e eVar = new e(this.a);
        f fVar = new f(this.a);
        dataCaptureView = this.a.a;
        use.a(surfaceTexture, eVar, fVar, dataCaptureView.get_optimizesRendering(), new g(this.a));
        return Unit.INSTANCE;
    }
}
