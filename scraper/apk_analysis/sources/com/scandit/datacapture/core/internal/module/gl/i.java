package com.scandit.datacapture.core.internal.module.gl;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.Message;
import android.view.Choreographer;
import androidx.work.Data;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.module.ui.video.NativeCopiedCameraTexture;
import com.scandit.datacapture.core.logger.GlRenderThreadDisposedEvent;
import com.scandit.datacapture.core.logger.GlRenderThreadObtainSurfaceEvent;
import com.scandit.datacapture.core.logger.GlRenderThreadPreparedEvent;
import com.scandit.datacapture.core.logger.SdcLogger;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i extends Handler {
    public final WeakReference a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(l parent) {
        super(parent.getLooper());
        Intrinsics.checkNotNullParameter(parent, "parent");
        this.a = new WeakReference(parent);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        l lVar = (l) this.a.get();
        if (lVar == null) {
            return;
        }
        switch (msg.what) {
            case 1:
                Object obj = msg.obj;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.scandit.datacapture.core.internal.module.gl.GlRenderThread.SurfaceTextureData");
                k kVar = (k) obj;
                lVar.a = new Size2(kVar.b, kVar.c);
                a aVar = lVar.i;
                if (aVar != null) {
                    aVar.b();
                }
                SurfaceTexture surfaceTexture = lVar.c;
                if (surfaceTexture != null) {
                    kVar.a.invoke(surfaceTexture);
                }
                SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new GlRenderThreadObtainSurfaceEvent(lVar.a()));
                return;
            case 2:
                n nVar = lVar.z;
                nVar.b.clear();
                Iterator it = nVar.c.iterator();
                while (it.hasNext()) {
                    ((NativeCopiedCameraTexture) it.next()).releaseGlResources();
                }
                nVar.c.clear();
                nVar.d = null;
                nVar.e = null;
                com.scandit.datacapture.core.internal.module.utils.f fVar = lVar.v;
                synchronized (fVar) {
                    com.scandit.datacapture.core.internal.module.utils.c cVar = fVar.b;
                    cVar.b.clear();
                    cVar.c = 0.0d;
                    cVar.d = false;
                    com.scandit.datacapture.core.internal.module.utils.c cVar2 = fVar.c;
                    cVar2.b.clear();
                    cVar2.c = 0.0d;
                    cVar2.d = false;
                    fVar.e = 0L;
                    Unit unit = Unit.INSTANCE;
                }
                lVar.t.set(false);
                SurfaceTexture surfaceTexture2 = lVar.c;
                if (surfaceTexture2 != null) {
                    surfaceTexture2.setOnFrameAvailableListener(null);
                }
                SurfaceTexture surfaceTexture3 = lVar.c;
                if (surfaceTexture3 != null) {
                    surfaceTexture3.release();
                }
                lVar.c = null;
                a aVar2 = lVar.i;
                if (aVar2 != null) {
                    aVar2.a();
                }
                lVar.i = null;
                lVar.s.set(true);
                SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new GlRenderThreadDisposedEvent(lVar.a()));
                return;
            case 3:
                Choreographer choreographer = lVar.k;
                if (choreographer != null) {
                    choreographer.postFrameCallback(lVar);
                    return;
                }
                return;
            case 4:
                Choreographer choreographer2 = lVar.k;
                if (choreographer2 != null) {
                    Object obj2 = msg.obj;
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Long");
                    choreographer2.postFrameCallbackDelayed(lVar, ((Long) obj2).longValue());
                    return;
                }
                return;
            case 5:
                lVar.s.set(false);
                a aVar3 = new a();
                lVar.i = aVar3;
                aVar3.b();
                int[] iArr = new int[1];
                GLES20.glGenTextures(1, iArr, 0);
                int i = iArr[0];
                GLES20.glBindTexture(36197, i);
                GLES20.glTexParameteri(36197, 10241, 9729);
                GLES20.glTexParameteri(36197, Data.MAX_DATA_BYTES, 9729);
                GLES20.glTexParameteri(36197, 10242, 33071);
                GLES20.glTexParameteri(36197, 10243, 33071);
                lVar.f = i;
                SurfaceTexture surfaceTexture4 = new SurfaceTexture(lVar.f);
                lVar.c = surfaceTexture4;
                surfaceTexture4.setOnFrameAvailableListener(new h(lVar));
                SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new GlRenderThreadPreparedEvent(lVar.a()));
                return;
            case 6:
                Object obj3 = msg.obj;
                Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type android.graphics.SurfaceTexture");
                SurfaceTexture surfaceTexture5 = (SurfaceTexture) obj3;
                if (lVar.j.get()) {
                    a aVar4 = lVar.i;
                    if (aVar4 != null) {
                        aVar4.a(lVar.e);
                    }
                    a aVar5 = lVar.i;
                    if (aVar5 != null) {
                        aVar5.b();
                    }
                    a aVar6 = lVar.i;
                    lVar.e = aVar6 != null ? aVar6.a(surfaceTexture5) : null;
                    lVar.d = surfaceTexture5;
                    return;
                }
                return;
            default:
                return;
        }
    }
}
