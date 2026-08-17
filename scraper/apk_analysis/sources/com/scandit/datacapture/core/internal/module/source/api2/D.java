package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.media.ImageReader;
import android.os.Handler;
import android.util.Rational;
import android.util.Size;
import android.view.Surface;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class D implements InterfaceC0580x {
    public static final A a = new A();

    public static Surface a(p0 p0Var, List previewResolutions, i0 targetFrameSize) {
        Object obj;
        Intrinsics.checkNotNullParameter(previewResolutions, "previewResolutions");
        Intrinsics.checkNotNullParameter(targetFrameSize, "targetFrameSize");
        Rational rational = new Rational(targetFrameSize.a, targetFrameSize.b);
        int i = targetFrameSize.c;
        List sortedWith = CollectionsKt.sortedWith(previewResolutions, new C0581y());
        Iterator it = sortedWith.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Size size = (Size) obj;
            if (Intrinsics.areEqual(new Size(targetFrameSize.a, targetFrameSize.b), size)) {
                break;
            }
            int min = Math.min(size.getWidth(), size.getHeight());
            if (Intrinsics.areEqual(new Rational(size.getWidth(), size.getHeight()), rational) && i != 0 && min >= i) {
                break;
            }
        }
        r3 = (Size) obj;
        if (r3 == null) {
            for (Size size2 : CollectionsKt.sortedWith(sortedWith, new C0582z(rational.floatValue()))) {
                if (Math.min(size2.getWidth(), size2.getHeight()) >= i) {
                }
            }
            throw new NoSuchElementException("Collection contains no element matching the predicate.");
        }
        u0 u0Var = (u0) p0Var;
        u0Var.b.setDefaultBufferSize(size2.getWidth(), size2.getHeight());
        return (Surface) u0Var.d.getValue();
    }

    public void a(Handler handler, CameraDevice cameraDevice, N cameraInfo, p0 surfaceHolder, i0 frameSize, InterfaceC0579w callback) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(cameraDevice, "cameraDevice");
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        Intrinsics.checkNotNullParameter(surfaceHolder, "surfaceHolder");
        Intrinsics.checkNotNullParameter(frameSize, "frameSize");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            Surface a2 = a(surfaceHolder, ((O) cameraInfo).g(), frameSize);
            A a3 = a;
            ImageReader a4 = A.a(frameSize);
            C0 c0 = new C0(callback);
            a4.setOnImageAvailableListener(c0, handler);
            Surface surface = a4.getSurface();
            Intrinsics.checkNotNullExpressionValue(surface, "getSurface(...)");
            CaptureRequest.Builder a5 = E.a(cameraDevice, a2, surface);
            C c = new C(callback, cameraDevice, a2, a4, c0, handler, a3.a(cameraDevice, a2));
            CaptureRequest build = a5.build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            SessionConfiguration sessionConfiguration = new SessionConfiguration(0, CollectionsKt.listOf((Object[]) new OutputConfiguration[]{new OutputConfiguration(a2), new OutputConfiguration(a4.getSurface())}), new B(handler), c);
            sessionConfiguration.setSessionParameters(build);
            cameraDevice.createCaptureSession(sessionConfiguration);
        } catch (CameraAccessException e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
            ((C0570m) callback).a(I.a);
        } catch (IllegalArgumentException e2) {
            Intrinsics.checkNotNullParameter(e2, "e");
            e2.printStackTrace();
            ((C0570m) callback).a(I.a);
        } catch (IllegalStateException e3) {
            Intrinsics.checkNotNullParameter(e3, "e");
            e3.printStackTrace();
            ((C0570m) callback).a(I.a);
        }
    }
}
