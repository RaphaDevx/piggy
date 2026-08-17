package com.scandit.datacapture.barcode.internal.module.spark.data;

import android.content.SharedPreferences;
import com.scandit.datacapture.barcode.internal.sdk.BarcodeNativeTypeFactory;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine;
import com.scandit.datacapture.barcode.spark.ui.SparkScanMiniPreviewSize;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningBehavior;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningModeKt;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewSettings;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.extensions.ContextExtensionsKt;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.TorchState;
import com.scandit.datacapture.core.ui.orientation.DeviceOrientation;
import com.scandit.datacapture.core.ui.orientation.DeviceOrientationMapper;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class SparkScanStateManager {
    static final /* synthetic */ KProperty[] u = {com.scandit.datacapture.barcode.ar.ui.annotations.a.a(SparkScanStateManager.class, "torchState", "getTorchState()Lcom/scandit/datacapture/core/source/TorchState;", 0), com.scandit.datacapture.barcode.ar.ui.annotations.a.a(SparkScanStateManager.class, "cameraPosition", "getCameraPosition()Lcom/scandit/datacapture/core/source/CameraPosition;", 0)};
    private final SparkScanViewSettings a;
    private final NativeSparkScanStateMachine b;
    private final DeviceOrientationMapper c;
    private final SharedPreferences d;
    private final com.scandit.datacapture.barcode.internal.module.spark.internal.b e;
    private final C0433l f;
    private final com.scandit.datacapture.barcode.internal.module.spark.internal.b g;
    private final com.scandit.datacapture.barcode.internal.module.spark.internal.b h;
    private final com.scandit.datacapture.barcode.internal.module.spark.internal.b i;
    private SparkScanScanningMode j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private SparkScanMiniPreviewSize o;
    private SparkScanMiniPreviewSize p;
    private boolean q;
    private final com.scandit.datacapture.barcode.internal.module.spark.internal.b r;
    private final C0434m s;
    private final CopyOnWriteArraySet t;

    public SparkScanStateManager(SparkScanViewSettings settings, NativeSparkScanStateMachine nativeStateMachine) {
        C0430i sparkScanMigrationManager = new C0430i();
        DeviceOrientationMapper deviceOrientationMapper = new DeviceOrientationMapper();
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(nativeStateMachine, "nativeStateMachine");
        Intrinsics.checkNotNullParameter(sparkScanMigrationManager, "sparkScanMigrationManager");
        Intrinsics.checkNotNullParameter(deviceOrientationMapper, "deviceOrientationMapper");
        this.a = settings;
        this.b = nativeStateMachine;
        this.c = deviceOrientationMapper;
        SharedPreferences sharedPreferences = AppAndroidEnvironment.INSTANCE.getApplicationContext().getSharedPreferences("com.scandit.barcode.spark_capture", 0);
        Intrinsics.checkNotNull(sharedPreferences);
        sparkScanMigrationManager.a(sharedPreferences);
        this.d = sharedPreferences;
        this.e = new com.scandit.datacapture.barcode.internal.module.spark.internal.b(settings.getDefaultTorchState());
        Delegates delegates = Delegates.INSTANCE;
        this.f = new C0433l(settings.getDefaultTorchState(), this);
        this.g = new com.scandit.datacapture.barcode.internal.module.spark.internal.b(Boolean.valueOf(v()));
        this.h = new com.scandit.datacapture.barcode.internal.module.spark.internal.b(Boolean.valueOf(e()));
        this.i = new com.scandit.datacapture.barcode.internal.module.spark.internal.b(settings.getDefaultScanningMode());
        this.j = settings.getDefaultScanningMode();
        SparkScanMiniPreviewSize defaultMiniPreviewSize = settings.getDefaultMiniPreviewSize();
        this.o = defaultMiniPreviewSize;
        this.p = defaultMiniPreviewSize;
        this.r = new com.scandit.datacapture.barcode.internal.module.spark.internal.b(settings.getDefaultCameraPosition());
        this.s = new C0434m(settings.getDefaultCameraPosition(), this);
        this.t = new CopyOnWriteArraySet();
        a(settings.getDefaultScanningMode());
        nativeStateMachine.addScanningModeListenerAsync(new com.scandit.datacapture.barcode.internal.module.spark.internal.v(new C0431j(this), this));
    }

    public final float A() {
        boolean C = C();
        if (C) {
            return this.d.getFloat("spark-capture-y-location", -1.0f);
        }
        if (C) {
            throw new NoWhenBranchMatchedException();
        }
        return this.d.getFloat("spark-capture-y-location-landscape", -1.0f);
    }

    public final boolean B() {
        return this.n;
    }

    public final boolean C() {
        DeviceOrientation mapRotationToOrientation = this.c.mapRotationToOrientation(ContextExtensionsKt.getRotation(AppAndroidEnvironment.INSTANCE.getApplicationContext()));
        return (mapRotationToOrientation == DeviceOrientation.LANDSCAPE_LEFT || mapRotationToOrientation == DeviceOrientation.LANDSCAPE_RIGHT) ? false : true;
    }

    public final void D() {
        this.o = this.p;
    }

    public final void b(SparkScanScanningMode sparkScanScanningMode) {
        Intrinsics.checkNotNullParameter(sparkScanScanningMode, "<set-?>");
        this.j = sparkScanScanningMode;
    }

    public final void c(float f) {
        boolean C = C();
        if (C) {
            this.d.edit().putFloat("spark-capture-x-location-mini-preview", f).apply();
        } else {
            if (C) {
                return;
            }
            this.d.edit().putFloat("spark-capture-x-location-mini-preview-landscape", f).apply();
        }
    }

    public final void d(float f) {
        boolean C = C();
        if (C) {
            this.d.edit().putFloat("spark-capture-y-location-mini-preview", f).apply();
        } else {
            if (C) {
                return;
            }
            this.d.edit().putFloat("spark-capture-y-location-mini-preview-landscape", f).apply();
        }
    }

    public final boolean e() {
        return this.d.getBoolean("spark-scan-feedback-haptic-enabled", this.a.getHapticEnabled());
    }

    public final com.scandit.datacapture.barcode.internal.module.spark.internal.b f() {
        return this.h;
    }

    public final long g() {
        return this.a.getTriggerButtonCollapseTimeout().asMillis();
    }

    public final boolean h() {
        return this.q;
    }

    public final long i() {
        return this.a.getInactiveStateTimeout().asMillis();
    }

    public final boolean j() {
        return this.k;
    }

    public final SparkScanMiniPreviewSize k() {
        return this.p;
    }

    public final boolean l() {
        return this.m;
    }

    public final float m() {
        boolean C = C();
        if (C) {
            return this.d.getFloat("spark-capture-x-location-mini-preview-collapsed", -1.0f);
        }
        if (C) {
            throw new NoWhenBranchMatchedException();
        }
        return this.d.getFloat("spark-capture-x-location-mini-preview-landscape-collapsed", -1.0f);
    }

    public final float n() {
        boolean C = C();
        if (C) {
            return this.d.getFloat("spark-capture-y-location-mini-preview-collapsed", -1.0f);
        }
        if (C) {
            throw new NoWhenBranchMatchedException();
        }
        return this.d.getFloat("spark-capture-y-location-mini-preview-landscape-collapsed", -1.0f);
    }

    public final SparkScanMiniPreviewSize o() {
        return this.o;
    }

    public final float p() {
        boolean C = C();
        if (C) {
            return this.d.getFloat("spark-capture-x-location-mini-preview", -1.0f);
        }
        if (C) {
            throw new NoWhenBranchMatchedException();
        }
        return this.d.getFloat("spark-capture-x-location-mini-preview-landscape", -1.0f);
    }

    public final float q() {
        boolean C = C();
        if (C) {
            return this.d.getFloat("spark-capture-y-location-mini-preview", -1.0f);
        }
        if (C) {
            throw new NoWhenBranchMatchedException();
        }
        return this.d.getFloat("spark-capture-y-location-mini-preview-landscape", -1.0f);
    }

    public final DeviceOrientation r() {
        return this.c.mapRotationToOrientation(ContextExtensionsKt.getRotation(AppAndroidEnvironment.INSTANCE.getApplicationContext()));
    }

    public final SparkScanScanningMode s() {
        BarcodeNativeTypeFactory barcodeNativeTypeFactory = BarcodeNativeTypeFactory.INSTANCE;
        NativeSparkScanScanningMode scanningMode = this.b.getScanningMode();
        Intrinsics.checkNotNullExpressionValue(scanningMode, "getScanningMode(...)");
        return barcodeNativeTypeFactory.convert(scanningMode);
    }

    public final SparkScanScanningMode t() {
        return this.j;
    }

    public final com.scandit.datacapture.barcode.internal.module.spark.internal.b u() {
        return this.i;
    }

    public final boolean v() {
        return this.d.getBoolean("spark-scan-feedback-sound-enabled", this.a.getSoundEnabled());
    }

    public final com.scandit.datacapture.barcode.internal.module.spark.internal.b w() {
        return this.g;
    }

    public final TorchState x() {
        return (TorchState) this.f.getValue(this, u[0]);
    }

    public final com.scandit.datacapture.barcode.internal.module.spark.internal.b y() {
        return this.e;
    }

    public final float z() {
        boolean C = C();
        if (C) {
            return this.d.getFloat("spark-capture-x-location", -1.0f);
        }
        if (C) {
            throw new NoWhenBranchMatchedException();
        }
        return this.d.getFloat("spark-capture-x-location-landscape", -1.0f);
    }

    public final void a(TorchState torchState) {
        Intrinsics.checkNotNullParameter(torchState, "<set-?>");
        this.f.setValue(this, u[0], torchState);
    }

    public final void b(float f) {
        boolean C = C();
        if (C) {
            this.d.edit().putFloat("spark-capture-y-location-mini-preview-collapsed", f).apply();
        } else {
            if (C) {
                return;
            }
            this.d.edit().putFloat("spark-capture-y-location-mini-preview-landscape-collapsed", f).apply();
        }
    }

    public final void f(float f) {
        boolean C = C();
        if (C) {
            this.d.edit().putFloat("spark-capture-y-location", f).apply();
        } else {
            if (C) {
                return;
            }
            this.d.edit().putFloat("spark-capture-y-location-landscape", f).apply();
        }
    }

    public final void a(SparkScanScanningMode value) {
        Intrinsics.checkNotNullParameter(value, "value");
        com.scandit.datacapture.barcode.internal.module.spark.internal.b bVar = this.i;
        bVar.a.setValue(bVar, com.scandit.datacapture.barcode.internal.module.spark.internal.b.c[0], value);
        this.b.setScanningMode(BarcodeNativeTypeFactory.INSTANCE.convert(value));
    }

    public final void e(float f) {
        boolean C = C();
        if (C) {
            this.d.edit().putFloat("spark-capture-x-location", f).apply();
        } else {
            if (C) {
                return;
            }
            this.d.edit().putFloat("spark-capture-x-location-landscape", f).apply();
        }
    }

    public final void a(float f) {
        boolean C = C();
        if (C) {
            this.d.edit().putFloat("spark-capture-x-location-mini-preview-collapsed", f).apply();
        } else {
            if (C) {
                return;
            }
            this.d.edit().putFloat("spark-capture-x-location-mini-preview-landscape-collapsed", f).apply();
        }
    }

    public final void c(boolean z) {
        if (z) {
            this.j = s();
            a(SparkScanScanningModeKt.copyWith$default(s(), SparkScanScanningBehavior.CONTINUOUS, null, 2, null));
        } else if (!z) {
            a(this.j);
        }
        this.k = z;
    }

    public final boolean d() {
        return this.l;
    }

    public final void b(SparkScanMiniPreviewSize sparkScanMiniPreviewSize) {
        Intrinsics.checkNotNullParameter(sparkScanMiniPreviewSize, "<set-?>");
        this.o = sparkScanMiniPreviewSize;
    }

    public final void d(boolean z) {
        this.m = z;
    }

    public final void b(boolean z) {
        this.q = z;
    }

    public final com.scandit.datacapture.barcode.internal.module.spark.internal.b b() {
        return this.r;
    }

    public final void b(InterfaceC0432k listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.t.remove(listener);
    }

    public final void e(boolean z) {
        this.n = z;
    }

    public final void a(boolean z) {
        this.l = z;
    }

    public final void a(SparkScanMiniPreviewSize sparkScanMiniPreviewSize) {
        Intrinsics.checkNotNullParameter(sparkScanMiniPreviewSize, "<set-?>");
        this.p = sparkScanMiniPreviewSize;
    }

    public final CameraPosition a() {
        return (CameraPosition) this.s.getValue(this, u[1]);
    }

    public final void a(CameraPosition cameraPosition) {
        Intrinsics.checkNotNullParameter(cameraPosition, "<set-?>");
        this.s.setValue(this, u[1], cameraPosition);
    }

    public final void a(InterfaceC0432k listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.t.add(listener);
    }

    public final boolean c() {
        return !this.q || (s() instanceof SparkScanScanningMode.Target);
    }
}
