package com.scandit.datacapture.core.internal.module.ui;

import android.graphics.SurfaceTexture;
import com.scandit.datacapture.core.internal.module.ui.video.NativeVideoPreview;
import com.scandit.datacapture.core.internal.sdk.data.Subscription;
import com.scandit.datacapture.core.ui.DataCaptureView;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class j extends Lambda implements Function0 {
    public final /* synthetic */ DataCaptureTextureView a;
    public final /* synthetic */ Subscription b;
    public final /* synthetic */ SurfaceTexture c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(DataCaptureTextureView dataCaptureTextureView, Subscription subscription, SurfaceTexture surfaceTexture) {
        super(0);
        this.a = dataCaptureTextureView;
        this.b = subscription;
        this.c = surfaceTexture;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        AtomicBoolean atomicBoolean;
        DataCaptureView dataCaptureView;
        NativeVideoPreview nativeVideoPreview;
        atomicBoolean = this.a.d;
        atomicBoolean.set(false);
        dataCaptureView = this.a.a;
        dataCaptureView._impl().releaseGlResources();
        nativeVideoPreview = this.a.b;
        nativeVideoPreview.releaseGlResources();
        this.b.dispose();
        this.c.release();
        return Unit.INSTANCE;
    }
}
