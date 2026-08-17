package com.scandit.datacapture.core.internal.module.ui;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES10;
import android.view.MotionEvent;
import android.view.TextureView;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.module.ui.video.NativePreviewShaderFormat;
import com.scandit.datacapture.core.internal.module.ui.video.NativeVideoGeometry;
import com.scandit.datacapture.core.internal.module.ui.video.NativeVideoPreview;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeTextureBinding;
import com.scandit.datacapture.core.internal.sdk.data.DisposableResource;
import com.scandit.datacapture.core.internal.sdk.data.Subscription;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.ui.DataCaptureView;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class DataCaptureTextureView extends TextureView {
    private final DataCaptureView a;
    private final NativeVideoPreview b;
    private final Lazy c;
    private final AtomicBoolean d;
    private final DisposableResource e;
    private Subscription f;
    private final AtomicBoolean g;
    private final p h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataCaptureTextureView(Context context, DataCaptureView parentView, NativeVideoPreview videoPreview) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parentView, "parentView");
        Intrinsics.checkNotNullParameter(videoPreview, "videoPreview");
        this.a = parentView;
        this.b = videoPreview;
        Lazy lazy = LazyKt.lazy(new c(this));
        this.c = lazy;
        this.d = new AtomicBoolean(false);
        Lazy lazy2 = com.scandit.datacapture.core.internal.module.gl.g.a;
        this.e = com.scandit.datacapture.core.internal.module.gl.g.b;
        this.g = new AtomicBoolean(false);
        p pVar = new p(context, ((Number) lazy.getValue()).floatValue());
        this.h = pVar;
        setSurfaceTextureListener(new i(this));
        parentView._setGestureRecognizer(pVar);
    }

    public static final void e(DataCaptureTextureView dataCaptureTextureView) {
        if (dataCaptureTextureView.f == null) {
            dataCaptureTextureView.f = dataCaptureTextureView.e.start();
        }
        Subscription subscription = dataCaptureTextureView.f;
        if (subscription != null) {
            subscription.use(new h(dataCaptureTextureView));
        }
    }

    @Override // android.view.TextureView, android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.a._impl().getVideoGeometry().setViewSize(new Size2(getWidth() / ((Number) this.c.getValue()).floatValue(), getHeight() / ((Number) this.c.getValue()).floatValue()));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        p pVar = this.h;
        pVar.getClass();
        Intrinsics.checkNotNullParameter(event, "event");
        return pVar.b.onTouchEvent(event) || super.onTouchEvent(event);
    }

    @Override // android.view.View
    protected final void onWindowVisibilityChanged(int i) {
        if (i == 0 && isAvailable()) {
            if (this.f == null) {
                this.f = this.e.start();
            }
            Subscription subscription = this.f;
            if (subscription != null) {
                subscription.use(new h(this));
            }
            this.a.onSurfaceTextureAvailable$scandit_capture_core();
        }
    }

    public static final void b(DataCaptureTextureView dataCaptureTextureView, SurfaceTexture surfaceTexture) {
        Subscription subscription = dataCaptureTextureView.f;
        dataCaptureTextureView.f = null;
        if (subscription != null) {
            subscription.use(new k(surfaceTexture, dataCaptureTextureView, subscription));
        }
    }

    public final void a(FrameSource frameSource) {
        Subscription subscription = this.f;
        if (subscription != null) {
            subscription.use(new b(frameSource));
        }
    }

    public static final void a(DataCaptureTextureView dataCaptureTextureView, SurfaceTexture surfaceTexture) {
        Subscription subscription = dataCaptureTextureView.f;
        if (subscription != null) {
            subscription.use(new d(surfaceTexture));
        }
    }

    public static final void a(DataCaptureTextureView dataCaptureTextureView, com.scandit.datacapture.core.internal.module.gl.j jVar) {
        NativePreviewShaderFormat nativePreviewShaderFormat;
        if (dataCaptureTextureView.d.compareAndSet(false, true)) {
            NativeVideoGeometry videoGeometry = dataCaptureTextureView.a._impl().getVideoGeometry();
            videoGeometry.setViewSize(new Size2(dataCaptureTextureView.getWidth() / ((Number) dataCaptureTextureView.c.getValue()).floatValue(), dataCaptureTextureView.getHeight() / ((Number) dataCaptureTextureView.c.getValue()).floatValue()));
            videoGeometry.setFrameSize(jVar.c);
            NativeVideoPreview nativeVideoPreview = dataCaptureTextureView.b;
            nativeVideoPreview.prepareForFormat(NativePreviewShaderFormat.RGBA);
            nativeVideoPreview.prepareForFormat(NativePreviewShaderFormat.OES_EXTERNAL);
            GLES10.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        }
        GLES10.glViewport(0, 0, dataCaptureTextureView.getWidth(), dataCaptureTextureView.getHeight());
        GLES10.glClear(17408);
        if (dataCaptureTextureView.g.get() && jVar.d) {
            ArrayList<NativeTextureBinding> arrayList = new ArrayList<>();
            arrayList.add(jVar.b);
            dataCaptureTextureView.b.setTextureCoordinateTransformation(jVar.a);
            if (jVar.b.getTarget() == 36197) {
                nativePreviewShaderFormat = NativePreviewShaderFormat.OES_EXTERNAL;
            } else {
                nativePreviewShaderFormat = NativePreviewShaderFormat.RGBA;
            }
            dataCaptureTextureView.b.draw(nativePreviewShaderFormat, arrayList, dataCaptureTextureView.a._impl().getVideoGeometry());
        }
        dataCaptureTextureView.a._impl().draw();
    }
}
