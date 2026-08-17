package com.scandit.datacapture.core.internal.module.ui;

import android.graphics.SurfaceTexture;
import android.view.TextureView;
import com.scandit.datacapture.core.ui.DataCaptureView;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i implements TextureView.SurfaceTextureListener {
    public final /* synthetic */ DataCaptureTextureView a;

    public i(DataCaptureTextureView dataCaptureTextureView) {
        this.a = dataCaptureTextureView;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surface, int i, int i2) {
        DataCaptureView dataCaptureView;
        Intrinsics.checkNotNullParameter(surface, "surface");
        DataCaptureTextureView.e(this.a);
        dataCaptureView = this.a.a;
        dataCaptureView.onSurfaceTextureAvailable$scandit_capture_core();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        DataCaptureView dataCaptureView;
        Intrinsics.checkNotNullParameter(surface, "surface");
        DataCaptureTextureView.b(this.a, surface);
        dataCaptureView = this.a.a;
        dataCaptureView.onSurfaceTextureDestroyed$scandit_capture_core();
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surface, int i, int i2) {
        DataCaptureView dataCaptureView;
        Intrinsics.checkNotNullParameter(surface, "surface");
        DataCaptureTextureView.a(this.a, surface);
        dataCaptureView = this.a.a;
        dataCaptureView._setNeedsRedraw();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surface) {
        DataCaptureView dataCaptureView;
        Intrinsics.checkNotNullParameter(surface, "surface");
        dataCaptureView = this.a.a;
        dataCaptureView.onSurfaceTextureUpdated$scandit_capture_core();
    }
}
