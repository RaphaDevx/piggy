package com.scandit.datacapture.core.internal.module.source.camera.behavior;

import com.scandit.datacapture.core.common.async.Callback;
import com.scandit.datacapture.core.extensions.LambdaExtensionsKt;
import com.scandit.datacapture.core.internal.module.source.dynamicresolution.l;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeExtensionsKt;
import com.scandit.datacapture.core.source.AndroidCameraProxy;
import com.scandit.datacapture.core.source.AndroidCameraProxyAdapter;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.TorchListener;
import com.scandit.datacapture.core.source.TorchState;
import com.scandit.datacapture.core.source.VideoResolutionDeserializer;
import java.util.Collection;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a, CameraBehaviorDelegate {
    public final AndroidCameraProxy a;
    public final /* synthetic */ com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a b;
    public CameraSettings c;
    public TorchState d;

    public c(AndroidCameraProxyAdapter nativeCameraProxy) {
        com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.b torchListenersHolder = new com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.b();
        Intrinsics.checkNotNullParameter(nativeCameraProxy, "nativeCameraProxy");
        Intrinsics.checkNotNullParameter(torchListenersHolder, "torchListenersHolder");
        this.a = nativeCameraProxy;
        this.b = torchListenersHolder;
        TorchState torchState = TorchState.OFF;
        this.d = torchState;
        nativeCameraProxy._applyTorchStateAsync(torchState);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a
    public final Collection a() {
        return this.b.a();
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a, com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void b(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.b(listener);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a, com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void c(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.c(listener);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a, com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void d(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.d(listener);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a, com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void a(TorchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.b.a(listener);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final TorchState b() {
        return this.d;
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.torch.a
    public final Collection c() {
        return this.b.c();
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void a(CameraSettings settings, Runnable runnable) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.c = settings;
        NativeExtensionsKt.andThen(this.a._applySettings(settings), runnable);
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void a(TorchState state, Function0 whenDone) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(whenDone, "whenDone");
        if (this.d != state) {
            NativeWrappedFuture _applyTorchStateAsync = this.a._applyTorchStateAsync(state);
            if (_applyTorchStateAsync != null) {
                NativeExtensionsKt.andThen(_applyTorchStateAsync, (Callback<? super Boolean>) LambdaExtensionsKt.Callback(new b(this, state, whenDone)));
                return;
            }
            return;
        }
        whenDone.invoke();
    }

    @Override // com.scandit.datacapture.core.internal.module.source.camera.behavior.CameraBehaviorDelegate
    public final void a(String resolution) {
        Intrinsics.checkNotNullParameter(resolution, "resolution");
        CameraSettings settings = this.c;
        if (settings == null) {
            settings = new CameraSettings();
        }
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(resolution, "resolution");
        Boolean bool = Boolean.FALSE;
        settings.setProperty("quadHd", bool);
        settings.setProperty("arbitraryVideoResolution", null);
        settings.setProperty("closestResolutionTo12MPForFourToThreeAspectRatio", bool);
        settings.setProperty("overwriteWithHighestResolution", bool);
        if (Intrinsics.areEqual(resolution, "quadHd")) {
            settings.setProperty("quadHd", Boolean.TRUE);
        } else {
            try {
                settings.setPreferredResolution(VideoResolutionDeserializer.fromJson(resolution));
            } catch (Throwable unused) {
                l lVar = l.a;
                l.a(resolution + " is not a valid resolution");
            }
        }
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.c = settings;
        NativeExtensionsKt.andThen(this.a._applySettings(settings), (Runnable) null);
    }
}
