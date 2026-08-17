package com.scandit.datacapture.core.internal.module.source.api2;

import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import com.scandit.datacapture.core.common.geometry.Rect;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.common.geometry.Size2UtilsKt;
import com.scandit.datacapture.core.internal.module.source.NativeCameraApi;
import com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate;
import com.scandit.datacapture.core.internal.module.source.NativeCameraDelegateSettings;
import com.scandit.datacapture.core.internal.module.source.NativeCameraInfo;
import com.scandit.datacapture.core.internal.module.source.NativeFocusMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise;
import com.scandit.datacapture.core.internal.sdk.data.NativeFloatRange;
import com.scandit.datacapture.core.internal.sdk.data.Subscription;
import com.scandit.datacapture.core.internal.sdk.extensions.CollectionsExtensionsKt;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.FrameSourceState;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.ClosedRange;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.p, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0573p extends NativeCameraDelegate {
    public static final /* synthetic */ KProperty[] v = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(C0573p.class), "backgroundHandler", "getBackgroundHandler()Landroid/os/Handler;"))};
    public final N a;
    public final Function1 b;
    public final Function1 c;
    public final S d;
    public final w0 e;
    public final InterfaceC0580x f;
    public final n0 g;
    public final g0 h;
    public final com.scandit.datacapture.core.internal.module.utils.h i;
    public p0 j;
    public CameraDevice k;
    public InterfaceC0558b0 l;
    public NativeCameraDelegateSettings m;
    public Size2 n;
    public int o;
    public Rect p;
    public final m0 q;
    public float r;
    public boolean s;
    public boolean t;
    public final Runnable u;

    public C0573p(O cameraInfo, com.scandit.datacapture.core.internal.module.source.H frameDataCallback, com.scandit.datacapture.core.internal.module.source.I priorityCameraSwitchStateCallback, U cameraProvider, z0 surfaceProvider, D cameraCapture, n0 sceneChangeDetector, g0 frameDataConverter, Function0 handlerProvider) {
        Intrinsics.checkNotNullParameter(cameraInfo, "cameraInfo");
        Intrinsics.checkNotNullParameter(frameDataCallback, "frameDataCallback");
        Intrinsics.checkNotNullParameter(priorityCameraSwitchStateCallback, "priorityCameraSwitchStateCallback");
        Intrinsics.checkNotNullParameter(cameraProvider, "cameraProvider");
        Intrinsics.checkNotNullParameter(surfaceProvider, "surfaceProvider");
        Intrinsics.checkNotNullParameter(cameraCapture, "cameraCapture");
        Intrinsics.checkNotNullParameter(sceneChangeDetector, "sceneChangeDetector");
        Intrinsics.checkNotNullParameter(frameDataConverter, "frameDataConverter");
        Intrinsics.checkNotNullParameter(handlerProvider, "handlerProvider");
        this.a = cameraInfo;
        this.b = frameDataCallback;
        this.c = priorityCameraSwitchStateCallback;
        this.d = cameraProvider;
        this.e = surfaceProvider;
        this.f = cameraCapture;
        this.g = sceneChangeDetector;
        this.h = frameDataConverter;
        this.i = new com.scandit.datacapture.core.internal.module.utils.h(handlerProvider);
        this.n = new Size2(0.0f, 0.0f);
        this.q = new m0(cameraInfo);
        if (cameraInfo.b.isTimestampRealtime()) {
            Intrinsics.checkNotNullParameter("CameraApi2 delegate. Timestamp source is realtime", "message");
            Log.i("sdc-core", "CameraApi2 delegate. Timestamp source is realtime");
        } else {
            Intrinsics.checkNotNullParameter("CameraApi2 delegate. Timestamp source is not realtime", "message");
            Log.i("sdc-core", "CameraApi2 delegate. Timestamp source is not realtime");
        }
        this.u = new Runnable() { // from class: com.scandit.datacapture.core.internal.module.source.api2.p$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                C0573p.a(C0573p.this);
            }
        };
    }

    public final Handler a() {
        Object obj;
        com.scandit.datacapture.core.internal.module.utils.h hVar = this.i;
        KProperty property = v[0];
        hVar.getClass();
        Intrinsics.checkNotNullParameter(this, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        synchronized (hVar) {
            obj = hVar.b;
            if (obj == null) {
                Function0 function0 = hVar.a;
                Intrinsics.checkNotNull(function0);
                obj = function0.invoke();
                if (obj != null) {
                    hVar.b = obj;
                    hVar.a = null;
                }
            }
        }
        return (Handler) obj;
    }

    public final void b() {
        InterfaceC0558b0 interfaceC0558b0 = this.l;
        if (interfaceC0558b0 != null) {
            ((d0) interfaceC0558b0).b();
        }
        this.l = null;
        CameraDevice cameraDevice = this.k;
        if (cameraDevice != null) {
            cameraDevice.close();
        }
        this.k = null;
        p0 p0Var = this.j;
        if (p0Var != null) {
            u0 u0Var = (u0) p0Var;
            u0Var.a.dispose();
            ((Surface) u0Var.d.getValue()).release();
        }
        this.j = null;
        this.s = false;
        this.m = null;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void bootUpWithSettings(NativeCameraDelegateSettings settings, NativeWrappedPromise whenDone) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        a(settings, new C0563f(whenDone));
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final NativeCameraApi getCameraApi() {
        return NativeCameraApi.CAMERA2;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final String getCameraId() {
        return ((O) this.a).b.getId();
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final NativeCameraInfo getCameraInfo() {
        ClosedRange b = ((O) this.a).b();
        ArrayList c = ((O) this.a).c();
        ArrayList arrayList = new ArrayList();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            arrayList.add(Size2UtilsKt.Size2(size.getWidth(), size.getHeight()));
        }
        return new NativeCameraInfo(new NativeFloatRange(((Number) b.getStart()).floatValue(), ((Number) b.getEndInclusive()).floatValue()), arrayList);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final CameraPosition getCameraPosition() {
        O o = (O) this.a;
        int facing = o.b.getFacing();
        if (facing == 0) {
            return CameraPosition.USER_FACING;
        }
        if (facing == 1) {
            return CameraPosition.WORLD_FACING;
        }
        throw new AssertionError("Unsupported Camera API 2 facing " + o.d());
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final int getCameraToNativeDeviceOrientation() {
        O o = (O) this.a;
        return o.d() == 1 ? o.h() : -o.h();
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final ArrayList getFrameResolutions() {
        ArrayList c = ((O) this.a).c();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(c, 10));
        Iterator it = c.iterator();
        while (it.hasNext()) {
            Size size = (Size) it.next();
            arrayList.add(Size2UtilsKt.Size2(size.getWidth(), size.getHeight()));
        }
        return CollectionsExtensionsKt.toArrayList(arrayList);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final EnumSet getSupportedFocusModesBits() {
        O o = (O) this.a;
        o.getClass();
        EnumSet noneOf = EnumSet.noneOf(NativeFocusMode.class);
        boolean z = o.a.a().b;
        for (int i : o.a()) {
            if (i == 0) {
                noneOf.add(NativeFocusMode.FIXED);
            } else if (i == 1) {
                noneOf.add(NativeFocusMode.AUTO);
            } else if ((i == 3 || i == 4) && !z) {
                noneOf.add(NativeFocusMode.AUTO);
            }
        }
        if (o.a.g()) {
            noneOf.add(NativeFocusMode.FIXED);
        }
        Intrinsics.checkNotNull(noneOf);
        return noneOf;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean goToSleep() {
        if (this.r <= 0.0f) {
            a(false);
            InterfaceC0558b0 interfaceC0558b0 = this.l;
            boolean a = interfaceC0558b0 != null ? ((d0) interfaceC0558b0).a() : true;
            this.s = false;
            return a;
        }
        a(false);
        long j = (long) (this.r * 1000);
        Handler a2 = a();
        if (a2 != null) {
            a2.postDelayed(this.u, j);
        }
        this.q.a(Boolean.FALSE);
        return b(this);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean hasManualLensPositionControl() {
        return ((O) this.a).i();
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean hasNoFocusSystem() {
        int[] a = ((O) this.a).a();
        ArrayList arrayList = new ArrayList();
        for (int i : a) {
            if (i != 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList.isEmpty();
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean isTimestampRealtime() {
        return ((O) this.a).b.isTimestampRealtime();
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean isTorchAvailable() {
        return ((O) this.a).j();
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void setBatterySavingMode(boolean z) {
        if (this.t == z) {
            return;
        }
        this.t = z;
        try {
            m0 m0Var = this.q;
            m0Var.h = z;
            NativeCameraDelegateSettings nativeCameraDelegateSettings = m0Var.e;
            if (nativeCameraDelegateSettings != null) {
                m0Var.a(nativeCameraDelegateSettings, (O) m0Var.a, z);
            }
            b(this);
        } catch (Exception e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean setFixedLensPosition(float f) {
        if (((O) this.a).i()) {
            this.q.a(f);
            return b(this);
        }
        Intrinsics.checkNotNullParameter("setFixedLensPosition() has no effect on a device without manual lens position control", "message");
        Log.i("sdc-core", "setFixedLensPosition() has no effect on a device without manual lens position control");
        return false;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean shouldMirrorAroundYAxis() {
        return ((O) this.a).d() == 0;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean shouldUseContinuous(boolean z) {
        O o = (O) this.a;
        o.getClass();
        try {
            if (o.a.a().b) {
                if (z) {
                    if (o.a.h()) {
                    }
                }
                return false;
            }
            return true;
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void shutDown() {
        try {
            b();
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean startContinuousFocusInArea(Rect rect) {
        this.q.b(rect);
        this.p = rect;
        return b(this);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final boolean startSingleShotFocusInArea(Rect rect) {
        InterfaceC0558b0 interfaceC0558b0 = this.l;
        if (interfaceC0558b0 == null) {
            return false;
        }
        this.q.a();
        d0 d0Var = (d0) interfaceC0558b0;
        if (!d0Var.a(this.q)) {
            return false;
        }
        this.q.c(rect);
        this.p = rect;
        if (!d0Var.a(this.q)) {
            return false;
        }
        this.q.b();
        return b(this);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void startWithSettings(NativeCameraDelegateSettings settings, NativeWrappedPromise whenDone) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        try {
            a(settings, new C0568k(whenDone, this));
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    @Override // com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate
    public final void updateSettings(NativeCameraDelegateSettings settings, FrameSourceState currentState) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(currentState, "currentState");
        try {
            this.m = settings;
            this.q.a(settings);
            n0 n0Var = this.g;
            boolean sceneChangeDetection = settings.getSceneChangeDetection();
            o0 o0Var = (o0) n0Var;
            o0Var.a = sceneChangeDetection;
            if (sceneChangeDetection) {
                String modelName = Build.MODEL;
                Intrinsics.checkNotNullExpressionValue(modelName, "MODEL");
                Intrinsics.checkNotNullParameter(modelName, "modelName");
                Locale ENGLISH = Locale.ENGLISH;
                Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                String lowerCase = modelName.toLowerCase(ENGLISH);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                String obj = StringsKt.trim((CharSequence) StringsKt.replace$default(lowerCase, "samsung-", "", false, 4, (Object) null)).toString();
                o0Var.b.getClass();
                Intrinsics.checkNotNullParameter("CAMCTRL Scene Change Detection enabled", "message");
                Log.i("sdc-core", "CAMCTRL Scene Change Detection enabled");
                com.scandit.datacapture.core.internal.module.utils.i.a("CAMCTRL device model: " + obj + ", SCD version: 0.6.8");
            }
            this.r = settings.getStageOneStandbyDuration();
            if (Intrinsics.areEqual(settings.getFrameResolution(), this.n)) {
                if (currentState == FrameSourceState.ON) {
                    b(this);
                    return;
                }
                return;
            }
            int i = AbstractC0562e.a[currentState.ordinal()];
            if (i == 1) {
                this.c.invoke(FrameSourceState.OFF);
                this.c.invoke(FrameSourceState.ON);
            } else {
                if (i != 2) {
                    return;
                }
                this.c.invoke(FrameSourceState.OFF);
                this.c.invoke(FrameSourceState.STANDBY);
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
            a(new C0572o(whenDone));
        } catch (Exception e) {
            com.scandit.datacapture.core.internal.module.utils.i.a("Exception caught in listener method. Rethrowing...", e);
            throw e;
        }
    }

    public static boolean b(C0573p c0573p) {
        C0571n c0571n = C0571n.a;
        if (c0573p.k != null) {
            m0 m0Var = c0573p.q;
            if (m0Var.l.hashCode() == m0Var.k) {
                return true;
            }
            InterfaceC0558b0 interfaceC0558b0 = c0573p.l;
            if (interfaceC0558b0 != null) {
                return ((d0) interfaceC0558b0).a(c0573p.q, new C0559c(c0573p, c0571n));
            }
        }
        return false;
    }

    public final void a(NativeCameraDelegateSettings nativeCameraDelegateSettings, Function1 function1) {
        if (this.k != null) {
            function1.invoke(Boolean.FALSE);
            return;
        }
        n0 n0Var = this.g;
        boolean sceneChangeDetection = nativeCameraDelegateSettings.getSceneChangeDetection();
        o0 o0Var = (o0) n0Var;
        o0Var.a = sceneChangeDetection;
        if (sceneChangeDetection) {
            String modelName = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(modelName, "MODEL");
            Intrinsics.checkNotNullParameter(modelName, "modelName");
            Locale ENGLISH = Locale.ENGLISH;
            Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
            String lowerCase = modelName.toLowerCase(ENGLISH);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String obj = StringsKt.trim((CharSequence) StringsKt.replace$default(lowerCase, "samsung-", "", false, 4, (Object) null)).toString();
            o0Var.b.getClass();
            Intrinsics.checkNotNullParameter("CAMCTRL Scene Change Detection enabled", "message");
            Log.i("sdc-core", "CAMCTRL Scene Change Detection enabled");
            com.scandit.datacapture.core.internal.module.utils.i.a("CAMCTRL device model: " + obj + ", SCD version: 0.6.8");
        }
        this.q.a(nativeCameraDelegateSettings);
        this.q.c();
        this.m = nativeCameraDelegateSettings;
        Size2 frameResolution = nativeCameraDelegateSettings.getFrameResolution();
        Intrinsics.checkNotNullExpressionValue(frameResolution, "getFrameResolution(...)");
        this.n = frameResolution;
        this.r = nativeCameraDelegateSettings.getStageOneStandbyDuration();
        Handler handler = a();
        if (handler == null) {
            function1.invoke(Boolean.FALSE);
            return;
        }
        Intrinsics.checkNotNullParameter(nativeCameraDelegateSettings, "<this>");
        i0 frameSize = new i0((int) nativeCameraDelegateSettings.getFrameResolution().getWidth(), (int) nativeCameraDelegateSettings.getFrameResolution().getHeight(), nativeCameraDelegateSettings.getMinPreviewShortSide());
        w0 w0Var = this.e;
        int h = ((O) this.a).h();
        C0564g callback = new C0564g(this, frameSize, function1);
        z0 z0Var = (z0) w0Var;
        z0Var.getClass();
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(frameSize, "frameSize");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Subscription start = z0Var.a.start();
        start.use(new y0(frameSize, handler, callback, start, h));
        ((U) this.d).a(handler, ((O) this.a).b.getId(), new C0565h(this, frameSize, function1));
    }

    public static final void a(C0573p this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.a(false);
        InterfaceC0558b0 interfaceC0558b0 = this$0.l;
        if (interfaceC0558b0 != null) {
            ((d0) interfaceC0558b0).a();
        }
        this$0.s = false;
    }

    public final void a(Function1 function1) {
        try {
            Handler a = a();
            if (a != null) {
                a.removeCallbacks(this.u);
            }
            a(true);
            boolean z = this.t;
            m0 m0Var = this.q;
            m0Var.h = z;
            NativeCameraDelegateSettings nativeCameraDelegateSettings = m0Var.e;
            if (nativeCameraDelegateSettings != null) {
                m0Var.a(nativeCameraDelegateSettings, (O) m0Var.a, z);
            }
            NativeCameraDelegateSettings nativeCameraDelegateSettings2 = this.m;
            if (nativeCameraDelegateSettings2 != null) {
                this.q.a(nativeCameraDelegateSettings2);
            }
            b(this);
            if (this.s) {
                function1.invoke(Boolean.TRUE);
                return;
            }
            InterfaceC0558b0 interfaceC0558b0 = this.l;
            if (interfaceC0558b0 != null) {
                ((d0) interfaceC0558b0).a(this.q, new C0559c(this, function1));
            } else {
                Intrinsics.checkNotNullParameter("No camera capture session to wake up", "message");
                Log.e("sdc-core", "No camera capture session to wake up");
                function1.invoke(Boolean.FALSE);
            }
        } catch (Exception e) {
            Intrinsics.checkNotNullParameter(e, "e");
            e.printStackTrace();
            function1.invoke(Boolean.FALSE);
        }
    }

    public final void a(boolean z) {
        p0 p0Var = this.j;
        if (p0Var != null) {
            u0 u0Var = (u0) p0Var;
            u0Var.a.use(new s0(u0Var, z));
        }
        InterfaceC0558b0 interfaceC0558b0 = this.l;
        if (interfaceC0558b0 != null) {
            ((d0) interfaceC0558b0).e.c = z;
        }
    }
}
