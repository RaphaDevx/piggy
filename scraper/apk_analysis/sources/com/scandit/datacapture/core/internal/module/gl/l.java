package com.scandit.datacapture.core.internal.module.gl;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.opengl.EGLSurface;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.view.Choreographer;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.module.ui.video.NativeCopiedCameraTexture;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeTextureBinding;
import com.scandit.datacapture.core.internal.sdk.ui.NeedsRedrawListener;
import com.scandit.datacapture.core.logger.GlRenderThreadState;
import com.scandit.datacapture.core.logger.SdcLogger;
import com.scandit.datacapture.core.logger.UpdateTexImageCrashEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class l extends HandlerThread implements Choreographer.FrameCallback, NeedsRedrawListener {
    public Size2 a;
    public volatile int b;
    public SurfaceTexture c;
    public SurfaceTexture d;
    public EGLSurface e;
    public int f;
    public final i g;
    public Function1 h;
    public a i;
    public final AtomicBoolean j;
    public Choreographer k;
    public boolean l;
    public final AtomicBoolean m;
    public long n;
    public final float[] o;
    public final ArrayList p;
    public final float[] q;
    public final Matrix r;
    public final AtomicBoolean s;
    public final AtomicBoolean t;
    public Function0 u;
    public final com.scandit.datacapture.core.internal.module.utils.f v;
    public volatile boolean w;
    public volatile boolean x;
    public final ArrayList y;
    public final n z;

    public l() {
        super("com.scandit.gl-render-thread");
        Float valueOf = Float.valueOf(0.0f);
        this.a = new Size2(0.0f, 0.0f);
        this.j = new AtomicBoolean(false);
        this.m = new AtomicBoolean(false);
        this.n = -1L;
        this.o = new float[16];
        this.p = new ArrayList(16);
        this.q = new float[9];
        this.r = new Matrix();
        this.s = new AtomicBoolean(false);
        this.t = new AtomicBoolean(false);
        this.v = new com.scandit.datacapture.core.internal.module.utils.f();
        start();
        this.g = new i(this);
        Float valueOf2 = Float.valueOf(1.0f);
        this.y = CollectionsKt.arrayListOf(valueOf2, valueOf, valueOf, valueOf, valueOf, valueOf2, valueOf, valueOf, valueOf, valueOf, valueOf2, valueOf, valueOf, valueOf, valueOf, valueOf2);
        this.z = new n();
    }

    public static final void a(l lVar, SurfaceTexture surfaceTexture) {
        lVar.t.set(true);
        if (lVar.s.get()) {
            return;
        }
        try {
            surfaceTexture.updateTexImage();
            com.scandit.datacapture.core.internal.module.utils.f fVar = lVar.v;
            long timestamp = surfaceTexture.getTimestamp();
            long uptimeMillis = SystemClock.uptimeMillis();
            synchronized (fVar) {
                fVar.c.a(timestamp, uptimeMillis);
                Unit unit = Unit.INSTANCE;
            }
            fVar.a();
            n nVar = lVar.z;
            NativeCopiedCameraTexture texture = (NativeCopiedCameraTexture) CollectionsKt.removeLastOrNull(nVar.b);
            if (texture == null) {
                texture = (NativeCopiedCameraTexture) nVar.a.invoke();
                nVar.c.add(texture);
            }
            surfaceTexture.getTransformMatrix(lVar.o);
            float[] fArr = lVar.q;
            float[] fArr2 = lVar.o;
            fArr[0] = fArr2[0];
            fArr[1] = fArr2[4];
            fArr[2] = fArr2[12];
            fArr[3] = fArr2[1];
            fArr[4] = fArr2[5];
            fArr[5] = fArr2[13];
            fArr[6] = fArr2[2];
            fArr[7] = fArr2[6];
            fArr[8] = fArr2[15];
            float f = lVar.b;
            lVar.r.setValues(fArr);
            lVar.r.postTranslate(-0.5f, -0.5f);
            lVar.r.postRotate(f);
            lVar.r.postTranslate(0.5f, 0.5f);
            lVar.r.getValues(fArr);
            float[] fArr3 = lVar.o;
            float[] fArr4 = lVar.q;
            fArr3[0] = fArr4[0];
            fArr3[4] = fArr4[1];
            fArr3[12] = fArr4[2];
            fArr3[1] = fArr4[3];
            fArr3[5] = fArr4[4];
            fArr3[13] = fArr4[5];
            fArr3[2] = fArr4[6];
            fArr3[6] = fArr4[7];
            fArr3[15] = fArr4[8];
            lVar.p.clear();
            for (float f2 : lVar.o) {
                lVar.p.add(Float.valueOf(f2));
            }
            texture.update(lVar.a, lVar.f, lVar.p);
            n nVar2 = lVar.z;
            nVar2.getClass();
            Intrinsics.checkNotNullParameter(texture, "texture");
            if (!Intrinsics.areEqual(texture, nVar2.d)) {
                NativeCopiedCameraTexture nativeCopiedCameraTexture = nVar2.d;
                nVar2.d = texture;
                if (nativeCopiedCameraTexture != null && !Intrinsics.areEqual(nativeCopiedCameraTexture, nVar2.e) && !Intrinsics.areEqual(nativeCopiedCameraTexture, nVar2.d)) {
                    nVar2.b.add(nativeCopiedCameraTexture);
                }
            }
            lVar.t.set(false);
        } catch (Throwable th) {
            SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new UpdateTexImageCrashEvent(th, SystemClock.elapsedRealtimeNanos(), lVar.a()));
        }
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j) {
        a aVar;
        NativeCopiedCameraTexture texture;
        if (this.s.get()) {
            return;
        }
        if (this.l) {
            this.m.set(false);
            if (j <= this.n) {
                return;
            } else {
                this.n = j;
            }
        } else {
            Choreographer choreographer = this.k;
            if (choreographer != null) {
                choreographer.postFrameCallback(this);
            }
        }
        EGLSurface eGLSurface = this.e;
        if (eGLSurface != null && (aVar = this.i) != null && this.j.get() && aVar.b(eGLSurface)) {
            SurfaceTexture surfaceTexture = this.c;
            if (surfaceTexture != null && this.t.get()) {
                try {
                    surfaceTexture.updateTexImage();
                    this.t.set(false);
                } catch (Throwable th) {
                    SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new UpdateTexImageCrashEvent(th, j, a()));
                }
            }
            if (this.x || !this.w || (texture = this.z.e) == null) {
                texture = this.z.d;
            }
            if (texture == null) {
                return;
            }
            Function1 function1 = this.h;
            if (function1 != null) {
                function1.invoke(new j(this.y, new NativeTextureBinding(3553, texture.getTextureId()), this.a, this.w));
            }
            n nVar = this.z;
            nVar.getClass();
            Intrinsics.checkNotNullParameter(texture, "texture");
            if (!Intrinsics.areEqual(texture, nVar.e)) {
                NativeCopiedCameraTexture nativeCopiedCameraTexture = nVar.e;
                nVar.e = texture;
                if (nativeCopiedCameraTexture != null && !Intrinsics.areEqual(nativeCopiedCameraTexture, texture) && !Intrinsics.areEqual(nativeCopiedCameraTexture, nVar.d)) {
                    nVar.b.add(nativeCopiedCameraTexture);
                }
            }
            if (this.j.get()) {
                aVar.c(eGLSurface);
            }
        }
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.NeedsRedrawListener
    public final void setNeedsRedrawInMillis(int i) {
        if (this.s.get()) {
            return;
        }
        if (i == 0 && this.m.compareAndSet(false, true)) {
            this.g.sendEmptyMessage(3);
        } else {
            i iVar = this.g;
            iVar.sendMessage(iVar.obtainMessage(4, Long.valueOf(i)));
        }
    }

    public final void a(final SurfaceTexture surface, final com.scandit.datacapture.core.internal.module.ui.e doOnFrame, final com.scandit.datacapture.core.internal.module.ui.f doOnFrameAvailable, final boolean z, final com.scandit.datacapture.core.internal.module.ui.g setNeedsRedrawListener) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        Intrinsics.checkNotNullParameter(doOnFrame, "doOnFrame");
        Intrinsics.checkNotNullParameter(doOnFrameAvailable, "doOnFrameAvailable");
        Intrinsics.checkNotNullParameter(setNeedsRedrawListener, "setNeedsRedrawListener");
        this.g.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.gl.l$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                l.a(l.this, surface, doOnFrame, doOnFrameAvailable, z, setNeedsRedrawListener);
            }
        });
    }

    public static final void a(l this$0, SurfaceTexture surface, Function1 doOnFrame, Function0 doOnFrameAvailable, boolean z, Function1 setNeedsRedrawListener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(surface, "$surface");
        Intrinsics.checkNotNullParameter(doOnFrame, "$doOnFrame");
        Intrinsics.checkNotNullParameter(doOnFrameAvailable, "$doOnFrameAvailable");
        Intrinsics.checkNotNullParameter(setNeedsRedrawListener, "$setNeedsRedrawListener");
        if (this$0.s.get()) {
            return;
        }
        SurfaceTexture surfaceTexture = this$0.d;
        if (surfaceTexture != null) {
            this$0.a(surfaceTexture);
        }
        this$0.k = Choreographer.getInstance();
        a aVar = this$0.i;
        if (aVar != null) {
            aVar.b();
        }
        a aVar2 = this$0.i;
        this$0.e = aVar2 != null ? aVar2.a(surface) : null;
        this$0.d = surface;
        this$0.j.set(true);
        Choreographer choreographer = this$0.k;
        if (choreographer != null) {
            choreographer.postFrameCallback(this$0);
        }
        this$0.h = doOnFrame;
        this$0.l = z;
        if (z) {
            setNeedsRedrawListener.invoke(this$0);
        }
        this$0.u = doOnFrameAvailable;
    }

    public final void a(final SurfaceTexture surfaceTexture, final com.scandit.datacapture.core.internal.module.ui.j andThen) {
        Intrinsics.checkNotNullParameter(andThen, "andThen");
        this.g.post(new Runnable() { // from class: com.scandit.datacapture.core.internal.module.gl.l$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                l.a(l.this, surfaceTexture, andThen);
            }
        });
    }

    public static final void a(l this$0, SurfaceTexture surfaceTexture, Function0 andThen) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(andThen, "$andThen");
        if (this$0.s.get()) {
            return;
        }
        this$0.a(surfaceTexture);
        a aVar = this$0.i;
        if (aVar != null) {
            aVar.b();
        }
        andThen.invoke();
    }

    public final void a(SurfaceTexture surfaceTexture) {
        if (Intrinsics.areEqual(this.d, surfaceTexture)) {
            Choreographer choreographer = this.k;
            if (choreographer != null) {
                choreographer.removeFrameCallback(this);
            }
            a aVar = this.i;
            if (aVar != null) {
                aVar.a(this.e);
            }
            this.e = null;
            this.d = null;
            this.j.set(false);
            this.h = null;
            this.u = null;
            this.k = null;
        }
    }

    public final GlRenderThreadState a() {
        boolean z = this.x;
        boolean z2 = this.w;
        boolean z3 = this.m.get();
        boolean z4 = this.j.get();
        long j = this.n;
        Lazy lazy = g.a;
        return new GlRenderThreadState(z, z2, z3, z4, j, g.b.getCounter(), this.k, this.c);
    }
}
