package com.scandit.datacapture.core.internal.module.gl;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import com.scandit.datacapture.core.logger.CreateDummySurface;
import com.scandit.datacapture.core.logger.SdcLogger;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes2.dex */
public final class a {
    public final EGLDisplay a;
    public final EGLContext b;
    public final EGLConfig c;
    public final EGLSurface d;

    public a() {
        List split$default;
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        Intrinsics.checkNotNullExpressionValue(eglGetDisplay, "eglGetDisplay(...)");
        this.a = eglGetDisplay;
        if (eglGetDisplay == EGL14.EGL_NO_DISPLAY) {
            throw new IllegalStateException("unable to get EGL14 display".toString());
        }
        int[] iArr = new int[2];
        if (!EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1)) {
            throw new IllegalStateException("unable to initialize EGL14".toString());
        }
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        EGLSurface eGLSurface = null;
        EGLConfig eGLConfig = EGL14.eglChooseConfig(eglGetDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 8, 12352, 4, 12344}, 0, eGLConfigArr, 0, 1, new int[1], 0) ? eGLConfigArr[0] : null;
        if (eGLConfig == null) {
            throw new IllegalStateException("Unable to find a suitable EGLConfig".toString());
        }
        this.c = eGLConfig;
        EGLContext eglCreateContext = EGL14.eglCreateContext(eglGetDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        Intrinsics.checkNotNullExpressionValue(eglCreateContext, "eglCreateContext(...)");
        this.b = eglCreateContext;
        a("eglCreateContext");
        String eglQueryString = EGL14.eglQueryString(eglGetDisplay, 12373);
        List emptyList = (eglQueryString == null || (emptyList = StringsKt.split$default((CharSequence) eglQueryString, new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null)) == null) ? CollectionsKt.emptyList() : emptyList;
        if (!emptyList.contains("EGL_KHR_surfaceless_context")) {
            String eglQueryString2 = EGL14.eglQueryString(eglGetDisplay, 12371);
            String eglQueryString3 = EGL14.eglQueryString(eglGetDisplay, 12372);
            String eglQueryString4 = EGL14.eglQueryString(eglGetDisplay, 12429);
            SdcLogger.INSTANCE.get().onEvent$scandit_capture_core(new CreateDummySurface(eglQueryString2, eglQueryString3, (eglQueryString4 == null || (split$default = StringsKt.split$default((CharSequence) eglQueryString4, new String[]{StringUtils.SPACE}, false, 0, 6, (Object) null)) == null) ? CollectionsKt.emptyList() : split$default, emptyList));
            eGLSurface = EGL14.eglCreatePbufferSurface(eglGetDisplay, eGLConfig, new int[]{12375, 32, 12374, 32, 12344}, 0);
            a("createOffscreenSurface");
            Intrinsics.checkNotNull(eGLSurface);
        }
        this.d = eGLSurface;
    }

    public final void a() {
        EGLSurface eGLSurface = this.d;
        if (eGLSurface != null) {
            EGL14.eglDestroySurface(this.a, eGLSurface);
        }
        EGL14.eglDestroyContext(this.a, this.b);
    }

    public final void b() {
        EGLSurface eGLSurface = this.d;
        if (eGLSurface == null) {
            EGLDisplay eGLDisplay = this.a;
            EGLSurface eGLSurface2 = EGL14.EGL_NO_SURFACE;
            EGL14.eglMakeCurrent(eGLDisplay, eGLSurface2, eGLSurface2, this.b);
        } else {
            EGL14.eglMakeCurrent(this.a, eGLSurface, eGLSurface, this.b);
        }
        a("makeCurrentWithoutSurface");
    }

    public final void c(EGLSurface surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        EGL14.eglSwapBuffers(this.a, surface);
    }

    public static void a(String str) {
        c cVar;
        int eglGetError = EGL14.eglGetError();
        if (eglGetError == 12288) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        c.c.getClass();
        c[] values = c.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                cVar = null;
                break;
            }
            cVar = values[i];
            if (cVar.a == eglGetError) {
                break;
            } else {
                i++;
            }
        }
        if (cVar != null) {
            StringBuilder sb2 = new StringBuilder("EglError ");
            sb2.append(cVar.name());
            sb2.append('(');
            b bVar = c.c;
            int i2 = cVar.a;
            bVar.getClass();
            sb2.append("0x" + Integer.toHexString(i2));
            sb2.append("): ");
            sb2.append(cVar.b);
            sb.append(sb2.toString());
            throw new IllegalStateException(sb.toString().toString());
        }
        StringBuilder sb3 = new StringBuilder("Unknown EGL error code: ");
        c.c.getClass();
        sb3.append("0x" + Integer.toHexString(eglGetError));
        throw new IllegalStateException(sb3.toString().toString());
    }

    public final boolean b(EGLSurface readAndWriteSurface) {
        Intrinsics.checkNotNullParameter(readAndWriteSurface, "readAndWriteSurface");
        EGL14.eglMakeCurrent(this.a, readAndWriteSurface, readAndWriteSurface, this.b);
        return EGL14.eglGetError() == 12288;
    }

    public final EGLSurface a(SurfaceTexture surface) {
        Intrinsics.checkNotNullParameter(surface, "surface");
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(this.a, this.c, surface, new int[]{12344}, 0);
        a("createWindowSurface");
        if (eglCreateWindowSurface != null) {
            return eglCreateWindowSurface;
        }
        throw new IllegalStateException("returned EGL surface is null".toString());
    }

    public final void a(EGLSurface eGLSurface) {
        if (eGLSurface != null) {
            EGL14.eglDestroySurface(this.a, eGLSurface);
            a("destroySurface");
        }
    }
}
