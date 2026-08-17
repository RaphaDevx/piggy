package com.scandit.datacapture.core.internal.sdk.source;

import com.scandit.datacapture.core.internal.module.source.NativeAbstractCamera;
import com.scandit.datacapture.core.internal.module.source.NativeCameraApi;
import com.scandit.datacapture.core.internal.module.source.NativeCameraDelegate;
import com.scandit.datacapture.core.internal.module.source.NativeCameraInfo;
import com.scandit.datacapture.core.internal.module.source.NativeCameraSettings;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSourceListener;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.FrameSourceState;
import com.scandit.datacapture.core.source.TorchState;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeAndroidCamera {

    public static final class CppProxy extends NativeAndroidCamera {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean a = new AtomicBoolean(false);
        private final long nativeRef;

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);

        private native void native_addListenerAsync(long j, NativeFrameSourceListener nativeFrameSourceListener);

        private native NativeWrappedFuture native_applySettingsAsyncAndroid(long j, NativeCameraSettings nativeCameraSettings);

        private native NativeWrappedFuture native_applyTorchStateAsyncWrapped(long j, TorchState torchState);

        private native NativeAbstractCamera native_asAbstractCamera(long j);

        private native NativeFrameSource native_asFrameSource(long j);

        private native void native_flushPendingTasks(long j);

        private native NativeCameraApi native_getApi(long j);

        private native NativeCameraInfo native_getCameraInfo(long j);

        private native FrameSourceState native_getCurrentState(long j);

        private native FrameSourceState native_getDesiredState(long j);

        private native CameraPosition native_getPosition(long j);

        private native boolean native_isTimestampRealtime(long j);

        private native boolean native_isTorchAvailable(long j);

        private native void native_onFrameOutputAndroid(long j, NativeCameraFrameData nativeCameraFrameData);

        private native void native_onFrameOutputAndroidAsync(long j, NativeCameraFrameData nativeCameraFrameData);

        private native void native_removeListenerAsync(long j, NativeFrameSourceListener nativeFrameSourceListener);

        private native void native_setBatterySavingMode(long j, boolean z);

        private native void native_setDelegate(long j, NativeCameraDelegate nativeCameraDelegate);

        private native NativeWrappedFuture native_setDelegateAsync(long j, NativeCameraDelegate nativeCameraDelegate);

        private native NativeWrappedFuture native_switchToDesiredStateAsyncAndroid(long j, FrameSourceState frameSourceState);

        private native NativeWrappedFuture native_switchToPriorityStateAsyncAndroid(long j, FrameSourceState frameSourceState);

        private native void native_triggerAutoFocus(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public void addListenerAsync(NativeFrameSourceListener nativeFrameSourceListener) {
            native_addListenerAsync(this.nativeRef, nativeFrameSourceListener);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public NativeWrappedFuture applySettingsAsyncAndroid(NativeCameraSettings nativeCameraSettings) {
            return native_applySettingsAsyncAndroid(this.nativeRef, nativeCameraSettings);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public NativeWrappedFuture applyTorchStateAsyncWrapped(TorchState torchState) {
            return native_applyTorchStateAsyncWrapped(this.nativeRef, torchState);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public NativeAbstractCamera asAbstractCamera() {
            return native_asAbstractCamera(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public NativeFrameSource asFrameSource() {
            return native_asFrameSource(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public void flushPendingTasks() {
            native_flushPendingTasks(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public NativeCameraApi getApi() {
            return native_getApi(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public NativeCameraInfo getCameraInfo() {
            return native_getCameraInfo(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public FrameSourceState getCurrentState() {
            return native_getCurrentState(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public FrameSourceState getDesiredState() {
            return native_getDesiredState(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public CameraPosition getPosition() {
            return native_getPosition(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public boolean isTimestampRealtime() {
            return native_isTimestampRealtime(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public boolean isTorchAvailable() {
            return native_isTorchAvailable(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public void onFrameOutputAndroid(NativeCameraFrameData nativeCameraFrameData) {
            native_onFrameOutputAndroid(this.nativeRef, nativeCameraFrameData);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public void onFrameOutputAndroidAsync(NativeCameraFrameData nativeCameraFrameData) {
            native_onFrameOutputAndroidAsync(this.nativeRef, nativeCameraFrameData);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public void removeListenerAsync(NativeFrameSourceListener nativeFrameSourceListener) {
            native_removeListenerAsync(this.nativeRef, nativeFrameSourceListener);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public void setBatterySavingMode(boolean z) {
            native_setBatterySavingMode(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public void setDelegate(NativeCameraDelegate nativeCameraDelegate) {
            native_setDelegate(this.nativeRef, nativeCameraDelegate);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public NativeWrappedFuture setDelegateAsync(NativeCameraDelegate nativeCameraDelegate) {
            return native_setDelegateAsync(this.nativeRef, nativeCameraDelegate);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public NativeWrappedFuture switchToDesiredStateAsyncAndroid(FrameSourceState frameSourceState) {
            return native_switchToDesiredStateAsyncAndroid(this.nativeRef, frameSourceState);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public NativeWrappedFuture switchToPriorityStateAsyncAndroid(FrameSourceState frameSourceState) {
            return native_switchToPriorityStateAsyncAndroid(this.nativeRef, frameSourceState);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera
        public void triggerAutoFocus() {
            native_triggerAutoFocus(this.nativeRef);
        }
    }

    public static native NativeAndroidCamera create(NativeCameraDelegate nativeCameraDelegate, CameraPosition cameraPosition, String str);

    public abstract void addListenerAsync(NativeFrameSourceListener nativeFrameSourceListener);

    public abstract NativeWrappedFuture applySettingsAsyncAndroid(NativeCameraSettings nativeCameraSettings);

    public abstract NativeWrappedFuture applyTorchStateAsyncWrapped(TorchState torchState);

    public abstract NativeAbstractCamera asAbstractCamera();

    public abstract NativeFrameSource asFrameSource();

    public abstract void flushPendingTasks();

    public abstract NativeCameraApi getApi();

    public abstract NativeCameraInfo getCameraInfo();

    public abstract FrameSourceState getCurrentState();

    public abstract FrameSourceState getDesiredState();

    public abstract CameraPosition getPosition();

    public abstract boolean isTimestampRealtime();

    public abstract boolean isTorchAvailable();

    public abstract void onFrameOutputAndroid(NativeCameraFrameData nativeCameraFrameData);

    public abstract void onFrameOutputAndroidAsync(NativeCameraFrameData nativeCameraFrameData);

    public abstract void removeListenerAsync(NativeFrameSourceListener nativeFrameSourceListener);

    public abstract void setBatterySavingMode(boolean z);

    public abstract void setDelegate(NativeCameraDelegate nativeCameraDelegate);

    public abstract NativeWrappedFuture setDelegateAsync(NativeCameraDelegate nativeCameraDelegate);

    public abstract NativeWrappedFuture switchToDesiredStateAsyncAndroid(FrameSourceState frameSourceState);

    public abstract NativeWrappedFuture switchToPriorityStateAsyncAndroid(FrameSourceState frameSourceState);

    public abstract void triggerAutoFocus();
}
