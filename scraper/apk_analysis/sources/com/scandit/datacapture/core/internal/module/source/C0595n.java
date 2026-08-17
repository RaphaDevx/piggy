package com.scandit.datacapture.core.internal.module.source;

import android.hardware.Camera;
import android.util.Log;
import android.util.Range;
import androidx.core.app.NotificationManagerCompat;
import com.scandit.datacapture.core.common.geometry.Rect;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.common.geometry.Size2UtilsKt;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise;
import com.scandit.datacapture.core.internal.sdk.data.DisposableResource;
import com.scandit.datacapture.core.internal.sdk.data.NativeFloatRange;
import com.scandit.datacapture.core.internal.sdk.data.Subscription;
import com.scandit.datacapture.core.internal.sdk.extensions.CollectionsExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.GraphicsExtensionsKt;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.source.TorchState;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.core.internal.module.source.n, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0595n extends NativeCameraDelegate {
    public final InterfaceC0600t a;
    public final CameraProfile b;
    public final Function1 c;
    public final C0603w d;
    public HandlerC0554a e;
    public final DisposableResource f;
    public C0599s g;
    public Subscription h;
    public Size2 i;
    public WeakReference j;
    public float k;
    public NativeCameraDelegateSettings l;
    public boolean m;

    public C0595n(InterfaceC0600t cameraInfo, CameraProfile cameraProfile, H frameDataCallback) {
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        Intrinsics.checkNotNullParameter(cameraProfile, "cameraProfile");
        Intrinsics.checkNotNullParameter(frameDataCallback, "frameDataCallback");
        this.a = cameraInfo;
        this.b = cameraProfile;
        this.c = frameDataCallback;
        this.d = new C0603w();
        this.f = com.scandit.datacapture.core.internal.module.gl.g.b;
        this.i = new Size2(0.0f, 0.0f);
        this.j = new WeakReference(null);
    }

    public final void a(boolean z) {
        Subscription subscription = this.h;
        if (subscription != null) {
            subscription.use(new C0587f(z));
        }
        C0599s c0599s = this.g;
        if (c0599s == null) {
            return;
        }
        c0599s.j = z;
    }

    public final boolean b() {
        try {
            Camera.Parameters parameters = this.d.c;
            if (parameters != null) {
                parameters.setFlashMode("off");
                a(parameters);
            }
            C0603w c0603w = this.d;
            Camera camera = c0603w.a;
            if (camera != null) {
                camera.stopPreview();
            }
            c0603w.b.set(false);
            return true;
        } catch (Exception e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void bootUpWithSettings(NativeCameraDelegateSettings settings, NativeWrappedPromise whenDone) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        try {
            a(settings, new C0586e(whenDone, this));
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final NativeCameraApi getCameraApi() {
        return NativeCameraApi.CAMERA1;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final String getCameraId() {
        return ((c0) this.a).a;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final NativeCameraInfo getCameraInfo() {
        NativeFloatRange nativeFloatRange;
        Camera.Parameters parameters = this.d.c;
        if (parameters != null && parameters.isZoomSupported()) {
            List<Integer> zoomRatios = parameters.getZoomRatios();
            if (zoomRatios.size() > 0) {
                Intrinsics.checkNotNull(zoomRatios);
                nativeFloatRange = new NativeFloatRange(((Number) CollectionsKt.first((List) zoomRatios)).intValue() / 100.0f, ((Number) CollectionsKt.last((List) zoomRatios)).intValue() / 100.0f);
                return new NativeCameraInfo(nativeFloatRange, getFrameResolutions());
            }
        }
        nativeFloatRange = new NativeFloatRange(1.0f, 1.0f);
        return new NativeCameraInfo(nativeFloatRange, getFrameResolutions());
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final CameraPosition getCameraPosition() {
        int i = ((c0) this.a).c;
        if (i == 0) {
            return CameraPosition.WORLD_FACING;
        }
        if (i == 1) {
            return CameraPosition.USER_FACING;
        }
        throw new AssertionError("Unsupported Camera API 1 facing " + ((c0) this.a).c);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final int getCameraToNativeDeviceOrientation() {
        c0 c0Var = (c0) this.a;
        return c0Var.c == 0 ? c0Var.d : -c0Var.d;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final ArrayList getFrameResolutions() {
        try {
            Camera.Parameters parameters = this.d.c;
            List<Camera.Size> supportedPreviewSizes = parameters != null ? parameters.getSupportedPreviewSizes() : null;
            if (supportedPreviewSizes == null) {
                supportedPreviewSizes = CollectionsKt.emptyList();
            } else {
                Intrinsics.checkNotNull(supportedPreviewSizes);
            }
            ArrayList arrayList = new ArrayList();
            for (Camera.Size size : supportedPreviewSizes) {
                Size2 Size2 = size.width * size.height <= this.b.b() ? Size2UtilsKt.Size2(size.width, size.height) : null;
                if (Size2 != null) {
                    arrayList.add(Size2);
                }
            }
            return CollectionsExtensionsKt.toArrayList(arrayList);
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x001c A[SYNTHETIC] */
    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.EnumSet getSupportedFocusModesBits() {
        /*
            r6 = this;
            java.lang.Class<com.scandit.datacapture.core.internal.module.source.NativeFocusMode> r0 = com.scandit.datacapture.core.internal.module.source.NativeFocusMode.class
            java.util.EnumSet r0 = java.util.EnumSet.noneOf(r0)
            com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile r1 = r6.b
            com.scandit.datacapture.core.internal.module.device.profiles.camera.f r1 = r1.a()
            boolean r1 = r1.b
            com.scandit.datacapture.core.internal.module.source.w r2 = r6.d
            android.hardware.Camera$Parameters r2 = r2.c
            if (r2 == 0) goto L88
            java.util.List r2 = r2.getSupportedFocusModes()
            java.util.Iterator r2 = r2.iterator()
        L1c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L7b
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto L1c
            int r4 = r3.hashCode()
            r5 = -194628547(0xfffffffff466343d, float:-7.2954577E31)
            if (r4 == r5) goto L6a
            r5 = 3005871(0x2dddaf, float:4.212122E-39)
            if (r4 == r5) goto L5b
            r5 = 97445748(0x5cee774, float:1.945717E-35)
            if (r4 == r5) goto L4c
            r5 = 910005312(0x363d9440, float:2.8249488E-6)
            if (r4 == r5) goto L43
            goto L1c
        L43:
            java.lang.String r4 = "continuous-picture"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L1c
            goto L73
        L4c:
            java.lang.String r4 = "fixed"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L55
            goto L1c
        L55:
            com.scandit.datacapture.core.internal.module.source.NativeFocusMode r3 = com.scandit.datacapture.core.internal.module.source.NativeFocusMode.FIXED
            r0.add(r3)
            goto L1c
        L5b:
            java.lang.String r4 = "auto"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L64
            goto L1c
        L64:
            com.scandit.datacapture.core.internal.module.source.NativeFocusMode r3 = com.scandit.datacapture.core.internal.module.source.NativeFocusMode.AUTO
            r0.add(r3)
            goto L1c
        L6a:
            java.lang.String r4 = "continuous-video"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L73
            goto L1c
        L73:
            if (r1 != 0) goto L1c
            com.scandit.datacapture.core.internal.module.source.NativeFocusMode r3 = com.scandit.datacapture.core.internal.module.source.NativeFocusMode.AUTO
            r0.add(r3)
            goto L1c
        L7b:
            com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile r6 = r6.b
            boolean r6 = r6.g()
            if (r6 == 0) goto L88
            com.scandit.datacapture.core.internal.module.source.NativeFocusMode r6 = com.scandit.datacapture.core.internal.module.source.NativeFocusMode.FIXED
            r0.add(r6)
        L88:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.core.internal.module.source.C0595n.getSupportedFocusModesBits():java.util.EnumSet");
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean goToSleep() {
        try {
            if (this.k <= 0.0f) {
                return b();
            }
            try {
                a(false);
                long j = (long) (this.k * 1000);
                HandlerC0554a handlerC0554a = this.e;
                if (handlerC0554a != null) {
                    handlerC0554a.sendMessageDelayed(handlerC0554a.obtainMessage(4), j);
                }
                Camera.Parameters parameters = this.d.c;
                if (parameters != null) {
                    parameters.setFlashMode("off");
                    a(parameters);
                }
                return true;
            } catch (Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                e.printStackTrace();
                return false;
            }
        } catch (Exception e2) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e2);
            throw e2;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean hasManualLensPositionControl() {
        return false;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean hasNoFocusSystem() {
        Camera.Parameters parameters;
        try {
            if (!a() || (parameters = this.d.c) == null) {
                return false;
            }
            List<String> supportedFocusModes = parameters.getSupportedFocusModes();
            Intrinsics.checkNotNullExpressionValue(supportedFocusModes, "getSupportedFocusModes(...)");
            String str = "continuous-picture";
            if (!supportedFocusModes.contains("continuous-picture")) {
                str = "continuous-video";
                if (!supportedFocusModes.contains("continuous-video")) {
                    str = null;
                }
            }
            return str == null;
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean isTimestampRealtime() {
        return false;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean isTorchAvailable() {
        Camera.Parameters parameters = this.d.c;
        return (parameters != null ? parameters.getFlashMode() : null) != null;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void setBatterySavingMode(boolean z) {
        try {
            if (this.m == z) {
                return;
            }
            this.m = z;
            Camera.Parameters parameters = this.d.c;
            if (parameters == null) {
                return;
            }
            NativeCameraDelegateSettings nativeCameraDelegateSettings = this.l;
            a(parameters, z, nativeCameraDelegateSettings != null ? nativeCameraDelegateSettings.preferredFrameRateRange : null);
            a(parameters);
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean setFixedLensPosition(float f) {
        return false;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean shouldMirrorAroundYAxis() {
        return ((c0) this.a).c == 1;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean shouldUseContinuous(boolean z) {
        try {
            if (!this.b.a().b) {
                return true;
            }
            if (z) {
                if (this.b.h()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void shutDown() {
        try {
            try {
                HandlerC0554a handlerC0554a = this.e;
                if (handlerC0554a != null) {
                    handlerC0554a.removeMessages(4);
                }
                C0599s c0599s = this.g;
                if (c0599s != null) {
                    c0599s.b();
                }
                this.g = null;
                C0603w c0603w = this.d;
                Camera camera = c0603w.a;
                c0603w.a = null;
                c0603w.c = null;
                if (camera != null) {
                    camera.release();
                }
                c0603w.b.set(false);
            } catch (Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                e.printStackTrace();
            }
            Subscription subscription = this.h;
            if (subscription != null) {
                subscription.dispose();
            }
            this.l = null;
        } catch (Exception e2) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e2);
            throw e2;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean startContinuousFocusInArea(Rect rect) {
        Camera.Parameters parameters;
        try {
            if (!a() || (parameters = this.d.c) == null) {
                return false;
            }
            List<String> supportedFocusModes = parameters.getSupportedFocusModes();
            Intrinsics.checkNotNullExpressionValue(supportedFocusModes, "getSupportedFocusModes(...)");
            String str = "continuous-picture";
            if (!supportedFocusModes.contains("continuous-picture")) {
                str = "continuous-video";
                if (!supportedFocusModes.contains("continuous-video")) {
                    str = null;
                }
            }
            if (str == null) {
                return false;
            }
            parameters.setFocusMode(str);
            a(parameters, rect);
            return a(parameters);
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean startSingleShotFocusInArea(Rect rect) {
        Camera.Parameters parameters;
        try {
            if (!a() || (parameters = this.d.c) == null) {
                return false;
            }
            List<String> supportedFocusModes = parameters.getSupportedFocusModes();
            Intrinsics.checkNotNullExpressionValue(supportedFocusModes, "getSupportedFocusModes(...)");
            if (!supportedFocusModes.contains("auto")) {
                return false;
            }
            parameters.setFocusMode("auto");
            a(parameters, rect);
            a(parameters);
            C0603w c0603w = this.d;
            c0603w.getClass();
            try {
                Camera camera = c0603w.a;
                if (camera != null) {
                    camera.cancelAutoFocus();
                }
            } catch (Exception unused) {
                Intrinsics.checkNotNullParameter("cancelAutoFocus failed", "message");
                Log.i("sdc-core", "cancelAutoFocus failed");
            }
            try {
                Camera camera2 = c0603w.a;
                if (camera2 != null) {
                    camera2.autoFocus(null);
                }
                return true;
            } catch (Exception e) {
                com.scandit.datacapture.core.internal.module.utils.i.a("autoFocus failed", e);
                return false;
            }
        } catch (Exception e2) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e2);
            throw e2;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void startWithSettings(NativeCameraDelegateSettings settings, NativeWrappedPromise whenDone) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        try {
            a(settings, new C0592k(whenDone, this, settings));
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void updateSettings(NativeCameraDelegateSettings settings, FrameSourceState currentState) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(currentState, "currentState");
        this.l = settings;
        try {
            Size2 size2 = settings.frameResolution;
            boolean areEqual = Intrinsics.areEqual(size2, this.i);
            boolean z = !areEqual && currentState == FrameSourceState.ON;
            if (z) {
                b();
            }
            this.k = settings.stageOneStandbyDuration;
            C0599s c0599s = this.g;
            if (c0599s != null) {
                int width = (int) size2.getWidth();
                int height = (int) size2.getHeight();
                if (c0599s.c != width || c0599s.d != height) {
                    c0599s.b();
                    c0599s.c = width;
                    c0599s.d = height;
                    for (int i = 0; i < 3; i++) {
                        c0599s.a(new byte[((c0599s.c * c0599s.d) * 12) / 8]);
                    }
                }
            }
            Camera.Parameters parameters = this.d.c;
            if (parameters != null) {
                if (!areEqual) {
                    Size2 frameResolution = settings.frameResolution;
                    Intrinsics.checkNotNullExpressionValue(frameResolution, "frameResolution");
                    a(parameters, frameResolution);
                }
                a(parameters, settings.zoomFactor);
                parameters.setExposureCompensation(0);
                a(parameters, this.m, settings.preferredFrameRateRange);
                a(parameters);
            }
            TorchState torchState = settings.torchState;
            Intrinsics.checkNotNullExpressionValue(torchState, "torchState");
            int i2 = AbstractC0583b.a[torchState.ordinal()];
            if (i2 == 1) {
                b(false);
            } else if (i2 != 2) {
                Intrinsics.checkNotNullParameter("Automatic torch is not implemented in Camera 1", "message");
                Log.e("sdc-core", "Automatic torch is not implemented in Camera 1");
            } else {
                b(true);
            }
            if (z) {
                a(C0593l.a);
            }
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void wakeUp(NativeWrappedPromise whenDone) {
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        try {
            a(new C0594m(whenDone, this));
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    public final void a(Function1 function1) {
        HandlerC0554a handlerC0554a = this.e;
        if (handlerC0554a != null) {
            handlerC0554a.removeMessages(4);
        }
        Camera.Parameters parameters = this.d.c;
        if (parameters != null) {
            boolean z = this.m;
            NativeCameraDelegateSettings nativeCameraDelegateSettings = this.l;
            a(parameters, z, nativeCameraDelegateSettings != null ? nativeCameraDelegateSettings.preferredFrameRateRange : null);
            NativeCameraDelegateSettings nativeCameraDelegateSettings2 = this.l;
            if ((nativeCameraDelegateSettings2 != null ? nativeCameraDelegateSettings2.torchState : null) == TorchState.ON) {
                parameters.setFlashMode(a(parameters, true));
            }
            a(parameters);
        }
        C0599s c0599s = this.g;
        if (c0599s != null) {
            c0599s.a();
        }
        HandlerC0554a handlerC0554a2 = this.e;
        if (handlerC0554a2 != null) {
            handlerC0554a2.sendMessage(handlerC0554a2.obtainMessage(2, function1));
        }
    }

    public static final void a(C0595n c0595n, Function1 function1) {
        C0603w c0603w = c0595n.d;
        boolean z = false;
        if (c0603w.a != null) {
            try {
                if (!c0603w.b.get()) {
                    Camera camera = c0603w.a;
                    if (camera != null) {
                        camera.startPreview();
                    }
                    c0603w.b.set(true);
                }
                z = true;
            } catch (IOException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                e.printStackTrace();
            } catch (RuntimeException e2) {
                com.scandit.datacapture.core.internal.module.utils.i.a("Either the Camera object has been released or a hardware or other low-level error occurred", e2);
                Camera camera2 = c0603w.a;
                c0603w.a = null;
                c0603w.c = null;
                if (camera2 != null) {
                    camera2.release();
                }
                c0603w.b.set(false);
            }
        }
        function1.invoke(Boolean.valueOf(z));
    }

    public final void b(boolean z) {
        Camera.Parameters parameters;
        if (isTorchAvailable() && (parameters = this.d.c) != null) {
            try {
                parameters.setFlashMode(a(parameters, z));
                a(parameters);
            } catch (Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                e.printStackTrace();
            }
        }
    }

    public final boolean a(Camera.Parameters camParams) {
        try {
            C0603w c0603w = this.d;
            c0603w.getClass();
            Intrinsics.checkNotNullParameter(camParams, "camParams");
            Camera camera = c0603w.a;
            if (camera == null) {
                Intrinsics.checkNotNullParameter("No camera. failed to set camera parameters", "message");
                Log.e("sdc-core", "No camera. failed to set camera parameters");
            } else {
                try {
                    camera.setParameters(camParams);
                    c0603w.c = camParams;
                    Camera.Size previewSize = camParams.getPreviewSize();
                    this.i = new Size2(previewSize.width, previewSize.height);
                    return true;
                } catch (Exception e) {
                    com.scandit.datacapture.core.internal.module.utils.i.a("Failed to set camera parameters", e);
                }
            }
            return false;
        } catch (Exception unused) {
            Intrinsics.checkNotNullParameter("Failed to set camera parameters", "message");
            Log.e("sdc-core", "Failed to set camera parameters");
            return false;
        }
    }

    public static void a(Camera.Parameters parameters, float f) {
        Object obj;
        if (f < 1.0f || !parameters.isZoomSupported()) {
            return;
        }
        int i = (int) (f * 100.0f);
        List<Integer> zoomRatios = parameters.getZoomRatios();
        Intrinsics.checkNotNullExpressionValue(zoomRatios, "getZoomRatios(...)");
        Iterator it = CollectionsKt.withIndex(zoomRatios).iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                int abs = Math.abs(((Number) ((IndexedValue) next).getValue()).intValue() - i);
                do {
                    Object next2 = it.next();
                    int abs2 = Math.abs(((Number) ((IndexedValue) next2).getValue()).intValue() - i);
                    if (abs > abs2) {
                        next = next2;
                        abs = abs2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        IndexedValue indexedValue = (IndexedValue) obj;
        if (indexedValue != null) {
            parameters.setZoom(indexedValue.getIndex());
        }
    }

    public final boolean a() {
        Camera camera;
        try {
            if (this.d.a != null) {
                return true;
            }
            int a = AbstractC0602v.a(this.a);
            if (a < 0) {
                return false;
            }
            Camera.Parameters parameters = null;
            try {
                camera = Camera.open(a);
            } catch (Exception unused) {
                Intrinsics.checkNotNullParameter("failed to open camera", "message");
                Log.e("sdc-core", "failed to open camera");
                camera = null;
            }
            C0603w c0603w = this.d;
            c0603w.a = camera;
            if (camera == null) {
                c0603w.b.set(false);
            } else {
                try {
                    parameters = camera.getParameters();
                } catch (Exception e) {
                    com.scandit.datacapture.core.internal.module.utils.i.a("Failed to get camera parameters", e);
                }
                c0603w.c = parameters;
            }
            return this.d.a != null;
        } catch (Exception e2) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e2);
            throw e2;
        }
    }

    public static boolean a(Camera.Parameters parameters, Size2 size2) {
        Object obj;
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes != null) {
            Iterator<T> it = supportedPreviewSizes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                Camera.Size size = (Camera.Size) obj;
                if (size.height == size2.getHeight() && size.width == size2.getWidth()) {
                    break;
                }
            }
            Camera.Size size3 = (Camera.Size) obj;
            if (size3 != null) {
                parameters.setPreviewSize(size3.width, size3.height);
                return true;
            }
        }
        return false;
    }

    public final void a(NativeCameraDelegateSettings nativeCameraDelegateSettings, Function1 function1) {
        this.l = nativeCameraDelegateSettings;
        a();
        C0603w c0603w = this.d;
        if (c0603w.a != null) {
            Camera.Parameters parameters = c0603w.c;
            if (parameters == null) {
                function1.invoke(Boolean.FALSE);
                return;
            }
            Size2 frameResolution = nativeCameraDelegateSettings.frameResolution;
            Intrinsics.checkNotNullExpressionValue(frameResolution, "frameResolution");
            if (!a(parameters, frameResolution)) {
                function1.invoke(Boolean.FALSE);
                return;
            }
            Camera.Size previewSize = parameters.getPreviewSize();
            C0599s c0599s = new C0599s(this.d, this.a, previewSize.width, previewSize.height, this.c, this);
            c0599s.j = false;
            this.g = c0599s;
            parameters.setPreviewFormat(17);
            if (!a(parameters)) {
                function1.invoke(Boolean.FALSE);
                return;
            }
            if (this.e == null) {
                this.e = new HandlerC0554a(this);
            }
            Subscription start = this.f.start();
            start.use(new C0589h(nativeCameraDelegateSettings, this, function1));
            this.h = start;
            a(parameters, this.m, nativeCameraDelegateSettings.preferredFrameRateRange);
            this.b.a(parameters);
            a(parameters, nativeCameraDelegateSettings.zoomFactor);
            if (nativeCameraDelegateSettings.torchState == TorchState.ON) {
                parameters.setFlashMode(a(parameters, true));
            }
            parameters.setExposureCompensation(0);
            a(parameters);
            this.k = nativeCameraDelegateSettings.stageOneStandbyDuration;
            return;
        }
        function1.invoke(Boolean.FALSE);
    }

    public final void a(Camera.Parameters parameters, Rect rect) {
        ArrayList arrayList;
        if (this.b.a().e) {
            return;
        }
        if (rect == null) {
            arrayList = null;
        } else {
            List listOf = CollectionsKt.listOf(rect);
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOf, 10));
            Iterator it = listOf.iterator();
            while (it.hasNext()) {
                arrayList2.add(GraphicsExtensionsKt.toGraphicRect((Rect) it.next(), NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, 1000));
            }
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayList.add(new Camera.Area((android.graphics.Rect) it2.next(), 1000));
            }
        }
        if (parameters.getMaxNumFocusAreas() > 0) {
            parameters.setFocusAreas(arrayList);
        }
        if (parameters.getMaxNumMeteringAreas() > 0) {
            parameters.setMeteringAreas(arrayList);
        }
    }

    public static String a(Camera.Parameters parameters, boolean z) {
        List<String> supportedFlashModes;
        if (!z || (supportedFlashModes = parameters.getSupportedFlashModes()) == null) {
            return "off";
        }
        String str = "torch";
        if (!supportedFlashModes.contains("torch")) {
            str = "on";
            if (!supportedFlashModes.contains("on")) {
                return "off";
            }
        }
        return str;
    }

    public final void a(Camera.Parameters parameters, boolean z, NativePreferredFrameRateRange nativePreferredFrameRateRange) {
        NativeCameraDelegateSettings nativeCameraDelegateSettings = this.l;
        if (nativeCameraDelegateSettings != null) {
            float f = nativeCameraDelegateSettings.maxFrameRate;
            List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
            Intrinsics.checkNotNullExpressionValue(supportedPreviewFpsRange, "getSupportedPreviewFpsRange(...)");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(supportedPreviewFpsRange, 10));
            Iterator<T> it = supportedPreviewFpsRange.iterator();
            while (true) {
                int i = 0;
                if (!it.hasNext()) {
                    break;
                }
                int[] iArr = (int[]) it.next();
                Intrinsics.checkNotNull(iArr);
                Integer minOrNull = ArraysKt.minOrNull(iArr);
                Integer valueOf = Integer.valueOf(minOrNull != null ? minOrNull.intValue() : 0);
                Integer maxOrNull = ArraysKt.maxOrNull(iArr);
                if (maxOrNull != null) {
                    i = maxOrNull.intValue();
                }
                arrayList.add(new Range(valueOf, Integer.valueOf(i)));
            }
            Range a = S.a(this.b, f, (Range[]) arrayList.toArray(new Range[0]), z, NativeCameraApi.CAMERA1, nativePreferredFrameRateRange);
            if (a == null) {
                return;
            }
            Object lower = a.getLower();
            Intrinsics.checkNotNullExpressionValue(lower, "getLower(...)");
            int intValue = ((Number) lower).intValue();
            Object upper = a.getUpper();
            Intrinsics.checkNotNullExpressionValue(upper, "getUpper(...)");
            parameters.setPreviewFpsRange(intValue, ((Number) upper).intValue());
        }
    }
}
