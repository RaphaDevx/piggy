package com.scandit.datacapture.core.source;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.scandit.datacapture.core.common.async.Callback;
import com.scandit.datacapture.core.internal.module.device.profiles.camera.CameraProfile;
import com.scandit.datacapture.core.internal.module.source.J;
import com.scandit.datacapture.core.internal.module.source.NativeCameraApi;
import com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate;
import com.scandit.datacapture.core.internal.module.source.NativeCameraSettingsDefaults;
import com.scandit.datacapture.core.internal.module.source.NativeEdgeEnhancement;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.module.source.NativeNoiseReduction;
import com.scandit.datacapture.core.internal.module.source.NativeShadingMode;
import com.scandit.datacapture.core.internal.module.source.b0;
import com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate;
import com.scandit.datacapture.core.internal.module.source.dynamicresolution.j;
import com.scandit.datacapture.core.internal.module.source.dynamicresolution.l;
import com.scandit.datacapture.core.internal.module.source.dynamicresolution.m;
import com.scandit.datacapture.core.internal.module.source.dynamicresolution.z;
import com.scandit.datacapture.core.internal.sdk.AppAndroidEnvironment;
import com.scandit.datacapture.core.internal.sdk.annotations.Mockable;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.source.CameraFactory;
import com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera;
import com.scandit.datacapture.core.source.serialization.FrameSourceDeserializer;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 T2\u00020\u00012\u00020\u0002:\u0002TUJ\u0018\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0097\u0001¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0097\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u0097\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fH\u0097\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0012H\u0097\u0001¢\u0006\u0004\b\u0014\u0010\u0015J(\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u00122\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0096\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010 \u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001cH\u0016¢\u0006\u0004\b \u0010\u001fJ#\u0010\"\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010!H\u0007¢\u0006\u0004\b\"\u0010#J+\u0010(\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010%\u001a\u00020$2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010!H\u0000¢\u0006\u0004\b&\u0010'J\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u001a\u0010)J\u0015\u0010,\u001a\u00020\u00192\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b,\u0010-J#\u00100\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00190.¢\u0006\u0004\b0\u00101J\u0015\u00103\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u000202¢\u0006\u0004\b3\u00104J\u0015\u00105\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u000202¢\u0006\u0004\b5\u00104J\u0017\u00107\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u000202H\u0000¢\u0006\u0004\b6\u00104J\u0017\u00109\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u000202H\u0000¢\u0006\u0004\b8\u00104R\u001a\u0010?\u001a\u00020:8\u0001X\u0080\u0004¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R\u0014\u0010B\u001a\u00020\u00128WX\u0096\u0005¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0014\u0010\u0013\u001a\u00020\u00128WX\u0096\u0005¢\u0006\u0006\u001a\u0004\bC\u0010AR\u0014\u0010D\u001a\u00020\u00178WX\u0096\u0005¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0014\u0010I\u001a\u00020F8WX\u0096\u0005¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0014\u0010M\u001a\u00020J8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR$\u0010S\u001a\u00020\b2\u0006\u0010N\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010R¨\u0006V"}, d2 = {"Lcom/scandit/datacapture/core/source/Camera;", "Lcom/scandit/datacapture/core/source/FrameSource;", "Lcom/scandit/datacapture/core/source/AndroidCameraProxy;", "Lcom/scandit/datacapture/core/source/CameraSettings;", "settings", "Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "_applySettings", "(Lcom/scandit/datacapture/core/source/CameraSettings;)Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "Lcom/scandit/datacapture/core/source/TorchState;", "torchState", "_applyTorchStateAsync", "(Lcom/scandit/datacapture/core/source/TorchState;)Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "Lcom/scandit/datacapture/core/internal/module/source/NativeFrameSource;", "_frameSourceImpl", "()Lcom/scandit/datacapture/core/internal/module/source/NativeFrameSource;", "Lcom/scandit/datacapture/core/internal/sdk/source/NativeAndroidCamera;", "_impl", "()Lcom/scandit/datacapture/core/internal/sdk/source/NativeAndroidCamera;", "Lcom/scandit/datacapture/core/source/FrameSourceState;", "desiredState", "_switchToDesiredState", "(Lcom/scandit/datacapture/core/source/FrameSourceState;)Lcom/scandit/datacapture/core/internal/sdk/common/async/NativeWrappedFuture;", "Lcom/scandit/datacapture/core/common/async/Callback;", "", "whenDone", "", "switchToDesiredState", "(Lcom/scandit/datacapture/core/source/FrameSourceState;Lcom/scandit/datacapture/core/common/async/Callback;)V", "Lcom/scandit/datacapture/core/source/FrameSourceListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "addListener", "(Lcom/scandit/datacapture/core/source/FrameSourceListener;)V", "removeListener", "Ljava/lang/Runnable;", "applySettings", "(Lcom/scandit/datacapture/core/source/CameraSettings;Ljava/lang/Runnable;)V", "Lcom/scandit/datacapture/core/internal/sdk/source/CameraFactory;", "cameraFactory", "applySettingsInternal$scandit_capture_core", "(Lcom/scandit/datacapture/core/source/CameraSettings;Lcom/scandit/datacapture/core/internal/sdk/source/CameraFactory;Ljava/lang/Runnable;)V", "applySettingsInternal", "(Lcom/scandit/datacapture/core/source/FrameSourceState;)V", "", "jsonData", "updateFromJson", "(Ljava/lang/String;)V", "Lkotlin/Function0;", "onDone", "_switchToDesiredTorchState", "(Lcom/scandit/datacapture/core/source/TorchState;Lkotlin/jvm/functions/Function0;)V", "Lcom/scandit/datacapture/core/source/TorchListener;", "addTorchListener", "(Lcom/scandit/datacapture/core/source/TorchListener;)V", "removeTorchListener", "_addWeakTorchListener$scandit_capture_core", "_addWeakTorchListener", "_removeWeakTorchListener$scandit_capture_core", "_removeWeakTorchListener", "Lcom/scandit/datacapture/core/internal/module/source/camera/behavior/CameraBehaviorDelegate;", "a", "Lcom/scandit/datacapture/core/internal/module/source/camera/behavior/CameraBehaviorDelegate;", "getBehaviorDelegate$scandit_capture_core", "()Lcom/scandit/datacapture/core/internal/module/source/camera/behavior/CameraBehaviorDelegate;", "behaviorDelegate", "getCurrentState", "()Lcom/scandit/datacapture/core/source/FrameSourceState;", "currentState", "getDesiredState", "isTorchAvailable", "()Z", "Lcom/scandit/datacapture/core/source/CameraPosition;", "getPosition", "()Lcom/scandit/datacapture/core/source/CameraPosition;", "position", "Lcom/scandit/datacapture/core/internal/module/source/NativeCameraApi;", "getApi$scandit_capture_core", "()Lcom/scandit/datacapture/core/internal/module/source/NativeCameraApi;", "api", "value", "getDesiredTorchState", "()Lcom/scandit/datacapture/core/source/TorchState;", "setDesiredTorchState", "(Lcom/scandit/datacapture/core/source/TorchState;)V", "desiredTorchState", "Companion", "com/scandit/datacapture/core/source/d", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
@Mockable
/* loaded from: classes2.dex */
public final class Camera implements FrameSource, AndroidCameraProxy {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final J f = new J((CameraProfile) com.scandit.datacapture.core.internal.module.device.profiles.camera.d.a.getValue());

    /* renamed from: a, reason: from kotlin metadata */
    private final CameraBehaviorDelegate behaviorDelegate;
    private final com.scandit.datacapture.core.internal.module.source.dynamicresolution.f b;
    private final /* synthetic */ AndroidCameraProxy c;
    private final CopyOnWriteArraySet d;
    private final CopyOnWriteArraySet e;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001J\u001d\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J%\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lcom/scandit/datacapture/core/source/Camera$Companion;", "", "Lcom/scandit/datacapture/core/source/CameraSettings;", "settings", "Lcom/scandit/datacapture/core/source/Camera;", "getDefaultCamera", "(Lcom/scandit/datacapture/core/source/CameraSettings;)Lcom/scandit/datacapture/core/source/Camera;", "Lcom/scandit/datacapture/core/source/CameraPosition;", "position", "getCamera", "(Lcom/scandit/datacapture/core/source/CameraPosition;Lcom/scandit/datacapture/core/source/CameraSettings;)Lcom/scandit/datacapture/core/source/Camera;", "", "jsonData", "fromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/core/source/Camera;", "Lcom/scandit/datacapture/core/internal/module/source/NativeCameraDelegate;", "delegate", "create$scandit_capture_core", "(Lcom/scandit/datacapture/core/internal/module/source/NativeCameraDelegate;)Lcom/scandit/datacapture/core/source/Camera;", "create", "Lcom/scandit/datacapture/core/internal/sdk/source/CameraFactory;", "cameraFactory", "Lcom/scandit/datacapture/core/internal/sdk/source/CameraFactory;", "scandit-capture-core"}, k = 1, mv = {1, 9, 0})
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public static /* synthetic */ Camera getCamera$default(Companion companion, CameraPosition cameraPosition, CameraSettings cameraSettings, int i, Object obj) {
            if ((i & 2) != 0) {
                cameraSettings = null;
            }
            return companion.getCamera(cameraPosition, cameraSettings);
        }

        public static /* synthetic */ Camera getDefaultCamera$default(Companion companion, CameraSettings cameraSettings, int i, Object obj) {
            if ((i & 1) != 0) {
                cameraSettings = null;
            }
            return companion.getDefaultCamera(cameraSettings);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final Camera create$scandit_capture_core(NativeCameraDelegate delegate) {
            Intrinsics.checkNotNullParameter(delegate, "delegate");
            NativeAndroidCamera create = NativeAndroidCamera.create(delegate, delegate.getCameraPosition(), delegate.getCameraId());
            Intrinsics.checkNotNull(create);
            AndroidCameraProxyAdapter androidCameraProxyAdapter = new AndroidCameraProxyAdapter(create, null, 2, 0 == true ? 1 : 0);
            return new Camera(androidCameraProxyAdapter, new com.scandit.datacapture.core.internal.module.source.camera.behavior.c(androidCameraProxyAdapter));
        }

        @JvmStatic
        public final Camera fromJson(String jsonData) {
            Intrinsics.checkNotNullParameter(jsonData, "jsonData");
            FrameSource frameSourceFromJson = new FrameSourceDeserializer(CollectionsKt.emptyList()).frameSourceFromJson(jsonData);
            if (frameSourceFromJson instanceof Camera) {
                return (Camera) frameSourceFromJson;
            }
            return null;
        }

        @JvmStatic
        public final Camera getCamera(CameraPosition position) {
            Intrinsics.checkNotNullParameter(position, "position");
            return getCamera$default(this, position, null, 2, null);
        }

        @JvmStatic
        public final Camera getDefaultCamera() {
            return getDefaultCamera$default(this, null, 1, null);
        }

        @JvmStatic
        public final Camera getCamera(CameraPosition position, CameraSettings settings) {
            Intrinsics.checkNotNullParameter(position, "position");
            return Camera.f.getCamera(position, settings);
        }

        @JvmStatic
        public final Camera getDefaultCamera(CameraSettings settings) {
            return Camera.f.getDefaultCamera(settings);
        }
    }

    Camera(AndroidCameraProxyAdapter androidCameraProxyAdapter, com.scandit.datacapture.core.internal.module.source.camera.behavior.c cVar) {
        b applyResolution = new b(cVar);
        c applySettings = new c(cVar);
        Intrinsics.checkNotNullParameter(applyResolution, "applyResolution");
        Intrinsics.checkNotNullParameter(applySettings, "applySettings");
        com.scandit.datacapture.core.internal.module.source.camera.c cVar2 = com.scandit.datacapture.core.internal.module.source.camera.a.a;
        if (cVar2 == null) {
            cVar2 = new com.scandit.datacapture.core.internal.module.source.camera.c(AppAndroidEnvironment.INSTANCE.getApplicationContext());
            com.scandit.datacapture.core.internal.module.source.camera.a.a = cVar2;
        }
        com.scandit.datacapture.core.internal.module.source.camera.c cVar3 = cVar2;
        com.scandit.datacapture.core.internal.module.source.dynamicresolution.d store = new com.scandit.datacapture.core.internal.module.source.dynamicresolution.d();
        Intrinsics.checkNotNullParameter(store, "store");
        com.scandit.datacapture.core.internal.module.source.dynamicresolution.b bVar = new com.scandit.datacapture.core.internal.module.source.dynamicresolution.b(store);
        l lVar = l.a;
        j jVar = new j(applyResolution, applySettings, cVar3, bVar, new com.scandit.datacapture.core.internal.module.source.dynamicresolution.e());
        this.behaviorDelegate = cVar;
        this.b = jVar;
        this.c = androidCameraProxyAdapter;
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
        this.d = copyOnWriteArraySet;
        this.e = new CopyOnWriteArraySet();
        getA().addListenerAsync(new b0(new d(this), this));
        copyOnWriteArraySet.add(jVar);
    }

    public static /* synthetic */ void applySettings$default(Camera camera, CameraSettings cameraSettings, Runnable runnable, int i, Object obj) {
        if ((i & 2) != 0) {
            runnable = null;
        }
        camera.applySettings(cameraSettings, runnable);
    }

    public static /* synthetic */ void applySettingsInternal$scandit_capture_core$default(Camera camera, CameraSettings cameraSettings, CameraFactory cameraFactory, Runnable runnable, int i, Object obj) {
        if ((i & 4) != 0) {
            runnable = null;
        }
        camera.applySettingsInternal$scandit_capture_core(cameraSettings, cameraFactory, runnable);
    }

    @JvmStatic
    public static final Camera fromJson(String str) {
        return INSTANCE.fromJson(str);
    }

    @JvmStatic
    public static final Camera getCamera(CameraPosition cameraPosition) {
        return INSTANCE.getCamera(cameraPosition);
    }

    @JvmStatic
    public static final Camera getDefaultCamera() {
        return INSTANCE.getDefaultCamera();
    }

    public final void _addWeakTorchListener$scandit_capture_core(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.behaviorDelegate.b(listener);
    }

    @Override // com.scandit.datacapture.core.source.AndroidCameraProxy
    @ProxyFunction(nativeName = "applySettingsAsyncAndroid")
    public NativeWrappedFuture _applySettings(CameraSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        return this.c._applySettings(settings);
    }

    @Override // com.scandit.datacapture.core.source.AndroidCameraProxy
    @ProxyFunction(nativeName = "applyTorchStateAsyncWrapped")
    public NativeWrappedFuture _applyTorchStateAsync(TorchState torchState) {
        Intrinsics.checkNotNullParameter(torchState, "torchState");
        return this.c._applyTorchStateAsync(torchState);
    }

    @Override // com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    @NativeImpl
    /* renamed from: _frameSourceImpl */
    public NativeFrameSource getC() {
        return this.c.getC();
    }

    @Override // com.scandit.datacapture.core.source.AndroidCameraProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeAndroidCamera getA() {
        return this.c.getA();
    }

    public final void _removeWeakTorchListener$scandit_capture_core(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.behaviorDelegate.a(listener);
    }

    @Override // com.scandit.datacapture.core.source.AndroidCameraProxy
    @ProxyFunction(nativeName = "switchToDesiredStateAsyncAndroid")
    public NativeWrappedFuture _switchToDesiredState(FrameSourceState desiredState) {
        Intrinsics.checkNotNullParameter(desiredState, "desiredState");
        return this.c._switchToDesiredState(desiredState);
    }

    public final void _switchToDesiredTorchState(TorchState torchState, Function0<Unit> onDone) {
        Intrinsics.checkNotNullParameter(torchState, "torchState");
        Intrinsics.checkNotNullParameter(onDone, "onDone");
        this.behaviorDelegate.a(torchState, onDone);
    }

    @Override // com.scandit.datacapture.core.source.FrameSource
    public void addListener(FrameSourceListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.e.add(listener)) {
            listener.onObservationStarted(this);
        }
    }

    public final void addTorchListener(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.behaviorDelegate.c(listener);
    }

    public final void applySettings(CameraSettings settings) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        applySettings$default(this, settings, null, 2, null);
    }

    public final void applySettingsInternal$scandit_capture_core(CameraSettings settings, CameraFactory cameraFactory, Runnable whenDone) {
        m mVar;
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(cameraFactory, "cameraFactory");
        j jVar = (j) this.b;
        jVar.getClass();
        Intrinsics.checkNotNullParameter(this, "camera");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Object property = settings.getProperty("dynamicResolutionReset");
        Unit unit = null;
        Boolean bool = property instanceof Boolean ? (Boolean) property : null;
        Boolean bool2 = Boolean.TRUE;
        if (Intrinsics.areEqual(bool, bool2)) {
            l lVar = l.a;
            l.a("Reset requested - clearing stored resolution");
            ((com.scandit.datacapture.core.internal.module.source.camera.c) jVar.c).a();
            z zVar = jVar.k;
            synchronized (zVar) {
                zVar.c = false;
                zVar.a();
            }
        }
        Object property2 = settings.getProperty("dynamicResolution");
        if (Intrinsics.areEqual(property2 instanceof Boolean ? (Boolean) property2 : null, bool2)) {
            Object property3 = settings.getProperty("dynamicResolutionLowFps");
            Integer num = property3 instanceof Integer ? (Integer) property3 : null;
            Object property4 = settings.getProperty("dynamicResolutionMeasuringIntervalMillis");
            Integer num2 = property4 instanceof Integer ? (Integer) property4 : null;
            int intValue = num != null ? num.intValue() : NativeCameraSettingsDefaults.dynamicResolutionLowFps();
            int intValue2 = num2 != null ? num2.intValue() : NativeCameraSettingsDefaults.dynamicResolutionMeasuringIntervalMillis();
            String json = VideoResolutionUtilsKt.toJson(VideoResolution.UHD4K);
            CameraSettings cameraSettings = new CameraSettings();
            cameraSettings.updateWithNativeObject$scandit_capture_core(settings._impl$scandit_capture_core());
            mVar = new m(cameraSettings, intValue, intValue2, json);
        } else {
            mVar = null;
        }
        jVar.f = mVar;
        if (mVar != null) {
            l lVar2 = l.a;
            l.a("Enabled\n\tLow fps         -> " + mVar.b + "\n\tMonitoring time -> " + mVar.c);
            String resolution = ((com.scandit.datacapture.core.internal.module.source.camera.c) jVar.c).b();
            if (resolution != null) {
                l.b("Restoring " + resolution + " resolution");
                Intrinsics.checkNotNullParameter(settings, "settings");
                Intrinsics.checkNotNullParameter(resolution, "resolution");
                Boolean bool3 = Boolean.FALSE;
                settings.setProperty("quadHd", bool3);
                settings.setProperty("arbitraryVideoResolution", null);
                settings.setProperty("closestResolutionTo12MPForFourToThreeAspectRatio", bool3);
                settings.setProperty("overwriteWithHighestResolution", bool3);
                if (Intrinsics.areEqual(resolution, "quadHd")) {
                    settings.setProperty("quadHd", bool2);
                } else {
                    try {
                        settings.setPreferredResolution(VideoResolutionDeserializer.fromJson(resolution));
                    } catch (Throwable unused) {
                        l lVar3 = l.a;
                        l.a(resolution.concat(" is not a valid resolution"));
                    }
                }
                jVar.k.c();
            } else {
                Intrinsics.checkNotNullParameter(settings, "settings");
                settings.setPreferredResolution(VideoResolution.UHD4K);
                settings.setProperty("api", 2);
                Boolean bool4 = Boolean.FALSE;
                settings.setProperty("quadHd", bool4);
                settings.setProperty("arbitraryVideoResolution", null);
                settings.setProperty("closestResolutionTo12MPForFourToThreeAspectRatio", bool4);
                settings.setProperty("overwriteWithHighestResolution", bool4);
                settings.setProperty("noiseReductionMode", NativeNoiseReduction.OFF);
                settings.setProperty("edgeEnhancementMode", NativeEdgeEnhancement.OFF);
                settings.setProperty("enableSensorPixelModeMaximumResolution", bool4);
                settings.setProperty("shadingMode", NativeShadingMode.OFF);
                jVar.k.a(mVar);
            }
        } else {
            jVar.k.b();
        }
        final e eVar = new e(this, settings, whenDone);
        NativeCameraApi nativeCameraApi = cameraFactory.getNativeCameraApi(settings);
        if (nativeCameraApi != getApi$scandit_capture_core()) {
            NativeCameraDelegate cameraDelegate = cameraFactory.getCameraDelegate(getPosition(), nativeCameraApi, settings);
            if (cameraDelegate != null) {
                NativeWrappedFuture delegateAsync = getA().setDelegateAsync(cameraDelegate);
                Intrinsics.checkNotNullExpressionValue(delegateAsync, "setDelegateAsync(...)");
                unit = NativeExtensionsKt.andThen(delegateAsync, new Callback<Boolean>() { // from class: com.scandit.datacapture.core.source.Camera$updateDelegateIfNeeded$1$1$1
                    @Override // com.scandit.datacapture.core.common.async.Callback
                    public /* bridge */ /* synthetic */ void run(Boolean bool5) {
                        run(bool5.booleanValue());
                    }

                    public void run(boolean result) {
                        eVar.invoke();
                    }
                });
            }
        } else {
            eVar.invoke();
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            eVar.invoke();
        }
    }

    public final NativeCameraApi getApi$scandit_capture_core() {
        NativeCameraApi api = getA().getApi();
        Intrinsics.checkNotNullExpressionValue(api, "getApi(...)");
        return api;
    }

    /* renamed from: getBehaviorDelegate$scandit_capture_core, reason: from getter */
    public final CameraBehaviorDelegate getBehaviorDelegate() {
        return this.behaviorDelegate;
    }

    @Override // com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    @ProxyFunction(property = "currentState")
    public FrameSourceState getCurrentState() {
        return this.c.getCurrentState();
    }

    @Override // com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    @ProxyFunction(property = "desiredState")
    public FrameSourceState getDesiredState() {
        return this.c.getDesiredState();
    }

    public final TorchState getDesiredTorchState() {
        return this.behaviorDelegate.b();
    }

    @Override // com.scandit.datacapture.core.source.AndroidCameraProxy
    @ProxyFunction(nativeName = "getPosition", property = "position")
    public CameraPosition getPosition() {
        return this.c.getPosition();
    }

    @Override // com.scandit.datacapture.core.source.AndroidCameraProxy
    @ProxyFunction(nativeName = "isTorchAvailable", property = "isTorchAvailable")
    public boolean isTorchAvailable() {
        return this.c.isTorchAvailable();
    }

    @Override // com.scandit.datacapture.core.source.FrameSource
    public void removeListener(FrameSourceListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        if (this.e.remove(listener)) {
            listener.onObservationStopped(this);
        }
    }

    public final void removeTorchListener(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.behaviorDelegate.d(listener);
    }

    public final void setDesiredTorchState(TorchState value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.behaviorDelegate.a(value, com.scandit.datacapture.core.internal.module.source.camera.behavior.a.a);
    }

    @Override // com.scandit.datacapture.core.source.FrameSource, com.scandit.datacapture.core.source.BitmapFrameSourceProxy
    public void switchToDesiredState(FrameSourceState desiredState, Callback<? super Boolean> whenDone) {
        Intrinsics.checkNotNullParameter(desiredState, "desiredState");
        this.c.switchToDesiredState(desiredState, whenDone);
    }

    public final void updateFromJson(String jsonData) {
        Intrinsics.checkNotNullParameter(jsonData, "jsonData");
        new FrameSourceDeserializer(CollectionsKt.emptyList()).updateFrameSourceFromJson(this, jsonData);
    }

    @JvmStatic
    public static final Camera getCamera(CameraPosition cameraPosition, CameraSettings cameraSettings) {
        return INSTANCE.getCamera(cameraPosition, cameraSettings);
    }

    @JvmStatic
    public static final Camera getDefaultCamera(CameraSettings cameraSettings) {
        return INSTANCE.getDefaultCamera(cameraSettings);
    }

    public final void applySettings(CameraSettings settings, Runnable whenDone) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        applySettingsInternal$scandit_capture_core(settings, f, whenDone);
    }

    public final void switchToDesiredState(FrameSourceState desiredState) {
        Intrinsics.checkNotNullParameter(desiredState, "desiredState");
        switchToDesiredState(desiredState, null);
    }
}
