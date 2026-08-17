package com.scandit.datacapture.frameworks.core.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.async.Callback;
import com.scandit.datacapture.core.json.JsonValue;
import com.scandit.datacapture.core.source.BitmapFrameSource;
import com.scandit.datacapture.core.source.Camera;
import com.scandit.datacapture.core.source.CameraSettings;
import com.scandit.datacapture.core.source.FrameSource;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.source.TorchStateDeserializer;
import com.scandit.datacapture.core.source.serialization.FrameSourceDeserializer;
import com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerListener;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksFrameSourceDeserializer.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0006\u0010\u001a\u001a\u00020\u0013J \u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\r2\u0010\b\u0002\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001eR(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0005\u001a\u0004\u0018\u00010\u000e@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0010\u0010\u0011¨\u0006!"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceDeserializer;", "Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializerListener;", "frameSourceListener", "Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceListener;", "(Lcom/scandit/datacapture/frameworks/core/listeners/FrameworksFrameSourceListener;)V", "value", "Lcom/scandit/datacapture/core/source/Camera;", "camera", "getCamera", "()Lcom/scandit/datacapture/core/source/Camera;", "setCamera", "(Lcom/scandit/datacapture/core/source/Camera;)V", "cameraDesiredState", "Lcom/scandit/datacapture/core/source/FrameSourceState;", "Lcom/scandit/datacapture/core/source/BitmapFrameSource;", "imageFrameSource", "setImageFrameSource", "(Lcom/scandit/datacapture/core/source/BitmapFrameSource;)V", "onFrameSourceDeserializationFinished", "", "deserializer", "Lcom/scandit/datacapture/core/source/serialization/FrameSourceDeserializer;", "frameSource", "Lcom/scandit/datacapture/core/source/FrameSource;", "json", "Lcom/scandit/datacapture/core/json/JsonValue;", "releaseCurrentCamera", "switchCameraToState", "newState", "whenDone", "Lcom/scandit/datacapture/core/common/async/Callback;", "", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksFrameSourceDeserializer implements FrameSourceDeserializerListener {
    private static final String DESIRED_TORCH_STATE = "desiredTorchState";
    private Camera camera;
    private FrameSourceState cameraDesiredState;
    private final FrameworksFrameSourceListener frameSourceListener;
    private BitmapFrameSource imageFrameSource;

    public FrameworksFrameSourceDeserializer(FrameworksFrameSourceListener frameSourceListener) {
        Intrinsics.checkNotNullParameter(frameSourceListener, "frameSourceListener");
        this.frameSourceListener = frameSourceListener;
        this.cameraDesiredState = FrameSourceState.OFF;
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerListener
    @ProxyFunction
    public void onCameraSettingsDeserializationFinished(FrameSourceDeserializer frameSourceDeserializer, CameraSettings cameraSettings, JsonValue jsonValue) {
        FrameSourceDeserializerListener.DefaultImpls.onCameraSettingsDeserializationFinished(this, frameSourceDeserializer, cameraSettings, jsonValue);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerListener
    @ProxyFunction
    public void onCameraSettingsDeserializationStarted(FrameSourceDeserializer frameSourceDeserializer, CameraSettings cameraSettings, JsonValue jsonValue) {
        FrameSourceDeserializerListener.DefaultImpls.onCameraSettingsDeserializationStarted(this, frameSourceDeserializer, cameraSettings, jsonValue);
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerListener
    @ProxyFunction
    public void onFrameSourceDeserializationStarted(FrameSourceDeserializer frameSourceDeserializer, FrameSource frameSource, JsonValue jsonValue) {
        FrameSourceDeserializerListener.DefaultImpls.onFrameSourceDeserializationStarted(this, frameSourceDeserializer, frameSource, jsonValue);
    }

    public final Camera getCamera() {
        return this.camera;
    }

    private final void setCamera(Camera camera) {
        Camera camera2 = this.camera;
        if (camera2 != null) {
            camera2.removeListener(this.frameSourceListener);
        }
        Camera camera3 = this.camera;
        if (camera3 != null) {
            camera3.removeTorchListener(this.frameSourceListener);
        }
        if (camera != null) {
            camera.addListener(this.frameSourceListener);
            camera.addTorchListener(this.frameSourceListener);
        } else {
            camera = null;
        }
        this.camera = camera;
    }

    private final void setImageFrameSource(BitmapFrameSource bitmapFrameSource) {
        BitmapFrameSource bitmapFrameSource2 = this.imageFrameSource;
        if (bitmapFrameSource2 != null) {
            bitmapFrameSource2.removeListener(this.frameSourceListener);
        }
        if (bitmapFrameSource != null) {
            bitmapFrameSource.addListener(this.frameSourceListener);
        } else {
            bitmapFrameSource = null;
        }
        this.imageFrameSource = bitmapFrameSource;
    }

    @Override // com.scandit.datacapture.core.source.serialization.FrameSourceDeserializerListener
    public void onFrameSourceDeserializationFinished(FrameSourceDeserializer deserializer, FrameSource frameSource, JsonValue json) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(frameSource, "frameSource");
        Intrinsics.checkNotNullParameter(json, "json");
        BitmapFrameSource bitmapFrameSource = null;
        setCamera(frameSource instanceof Camera ? (Camera) frameSource : null);
        Camera camera = this.camera;
        if (camera != null) {
            if (json.contains(DESIRED_TORCH_STATE)) {
                camera.setDesiredTorchState(TorchStateDeserializer.fromJson(json.requireByKeyAsString(DESIRED_TORCH_STATE)));
            }
            camera.switchToDesiredState(this.cameraDesiredState);
        }
        BitmapFrameSource bitmapFrameSource2 = frameSource instanceof BitmapFrameSource ? (BitmapFrameSource) frameSource : null;
        if (bitmapFrameSource2 != null) {
            FrameSource.DefaultImpls.switchToDesiredState$default(bitmapFrameSource2, this.cameraDesiredState, null, 2, null);
            bitmapFrameSource = bitmapFrameSource2;
        }
        setImageFrameSource(bitmapFrameSource);
    }

    public final void releaseCurrentCamera() {
        Camera camera = this.camera;
        if (camera != null) {
            camera.switchToDesiredState(FrameSourceState.OFF);
        }
        setCamera(null);
        setImageFrameSource(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void switchCameraToState$default(FrameworksFrameSourceDeserializer frameworksFrameSourceDeserializer, FrameSourceState frameSourceState, Callback callback, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: switchCameraToState");
        }
        if ((i & 2) != 0) {
            callback = null;
        }
        frameworksFrameSourceDeserializer.switchCameraToState(frameSourceState, callback);
    }

    public final void switchCameraToState(FrameSourceState newState, Callback<? super Boolean> whenDone) {
        Intrinsics.checkNotNullParameter(newState, "newState");
        this.cameraDesiredState = newState;
        Camera camera = this.camera;
        if (camera == null && this.imageFrameSource == null) {
            if (whenDone != null) {
                whenDone.run(true);
            }
        } else {
            if (camera != null) {
                camera.switchToDesiredState(newState, whenDone);
            }
            BitmapFrameSource bitmapFrameSource = this.imageFrameSource;
            if (bitmapFrameSource != null) {
                bitmapFrameSource.switchToDesiredState(newState, whenDone);
            }
        }
    }
}
