package com.scandit.datacapture.core.internal.sdk.capture;

import com.scandit.datacapture.core.common.geometry.SizeWithUnit;
import com.scandit.datacapture.core.internal.module.capture.NativeRecognitionContextSettings;
import com.scandit.datacapture.core.internal.module.source.NativeFrameSource;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeDataCaptureContext {

    public static final class CppProxy extends NativeDataCaptureContext {
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

        private native void native_addFrameListenerAsync(long j, NativeDataCaptureContextFrameListener nativeDataCaptureContextFrameListener);

        private native void native_addInertialMeasurementAsyncAndroid(long j, NativeInertialMeasurementAndroid nativeInertialMeasurementAndroid, NativeInertialMeasurementType nativeInertialMeasurementType);

        private native void native_addListenerAsync(long j, NativeDataCaptureContextListener nativeDataCaptureContextListener, int i);

        private native NativeWrappedFuture native_addModeAsyncWrapped(long j, NativeDataCaptureMode nativeDataCaptureMode);

        private native void native_addRotationVectorAsyncAndroid(long j, NativeImuDataRotationVectorAndroid nativeImuDataRotationVectorAndroid);

        private native void native_applySettings(long j, NativeDataCaptureContextSettings nativeDataCaptureContextSettings);

        private native void native_disposeAsync(long j);

        private native void native_flushPendingTasks(long j);

        private native NativeFeatureAvailability native_getFeatureAvailability(long j, NativeLicensedFeature nativeLicensedFeature);

        private native NativeFrameOfReference native_getFrameOfReference(long j);

        private native NativeFrameSource native_getFrameSource(long j);

        private native NativeLicenseInfo native_getLicenseInfo(long j);

        private native NativeRecognitionContextSettings native_getSettings(long j);

        private native boolean native_isCurrentMode(long j, NativeDataCaptureMode nativeDataCaptureMode);

        private native void native_onActivityStopped(long j);

        private native NativeWrappedFuture native_removeAllModesAsyncWrapped(long j);

        private native NativeWrappedFuture native_removeCurrentModeAsyncWrapped(long j);

        private native void native_removeFrameListenerAsync(long j, NativeDataCaptureContextFrameListener nativeDataCaptureContextFrameListener);

        private native void native_removeListenerAsync(long j, NativeDataCaptureContextListener nativeDataCaptureContextListener);

        private native NativeWrappedFuture native_removeModeAsyncWrapped(long j, NativeDataCaptureMode nativeDataCaptureMode);

        private native void native_saveFrameProcessingTimesToJson(long j, String str);

        private native void native_setFrameOfReferenceViewSizeAndOrientation(long j, SizeWithUnit sizeWithUnit, float f, int i);

        private native NativeWrappedFuture native_setFrameSourceAsyncWrapped(long j, NativeFrameSource nativeFrameSource);

        private native NativeWrappedFuture native_setModeAsyncWrapped(long j, NativeDataCaptureMode nativeDataCaptureMode);

        private native void native_setShouldUseImuDataAsync(long j, boolean z);

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void addFrameListenerAsync(NativeDataCaptureContextFrameListener nativeDataCaptureContextFrameListener) {
            native_addFrameListenerAsync(this.nativeRef, nativeDataCaptureContextFrameListener);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void addInertialMeasurementAsyncAndroid(NativeInertialMeasurementAndroid nativeInertialMeasurementAndroid, NativeInertialMeasurementType nativeInertialMeasurementType) {
            native_addInertialMeasurementAsyncAndroid(this.nativeRef, nativeInertialMeasurementAndroid, nativeInertialMeasurementType);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void addListenerAsync(NativeDataCaptureContextListener nativeDataCaptureContextListener, int i) {
            native_addListenerAsync(this.nativeRef, nativeDataCaptureContextListener, i);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeWrappedFuture addModeAsyncWrapped(NativeDataCaptureMode nativeDataCaptureMode) {
            return native_addModeAsyncWrapped(this.nativeRef, nativeDataCaptureMode);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void addRotationVectorAsyncAndroid(NativeImuDataRotationVectorAndroid nativeImuDataRotationVectorAndroid) {
            native_addRotationVectorAsyncAndroid(this.nativeRef, nativeImuDataRotationVectorAndroid);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void applySettings(NativeDataCaptureContextSettings nativeDataCaptureContextSettings) {
            native_applySettings(this.nativeRef, nativeDataCaptureContextSettings);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void disposeAsync() {
            native_disposeAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void flushPendingTasks() {
            native_flushPendingTasks(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeFeatureAvailability getFeatureAvailability(NativeLicensedFeature nativeLicensedFeature) {
            return native_getFeatureAvailability(this.nativeRef, nativeLicensedFeature);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeFrameOfReference getFrameOfReference() {
            return native_getFrameOfReference(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeFrameSource getFrameSource() {
            return native_getFrameSource(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeLicenseInfo getLicenseInfo() {
            return native_getLicenseInfo(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeRecognitionContextSettings getSettings() {
            return native_getSettings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public boolean isCurrentMode(NativeDataCaptureMode nativeDataCaptureMode) {
            return native_isCurrentMode(this.nativeRef, nativeDataCaptureMode);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void onActivityStopped() {
            native_onActivityStopped(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeWrappedFuture removeAllModesAsyncWrapped() {
            return native_removeAllModesAsyncWrapped(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeWrappedFuture removeCurrentModeAsyncWrapped() {
            return native_removeCurrentModeAsyncWrapped(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void removeFrameListenerAsync(NativeDataCaptureContextFrameListener nativeDataCaptureContextFrameListener) {
            native_removeFrameListenerAsync(this.nativeRef, nativeDataCaptureContextFrameListener);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void removeListenerAsync(NativeDataCaptureContextListener nativeDataCaptureContextListener) {
            native_removeListenerAsync(this.nativeRef, nativeDataCaptureContextListener);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeWrappedFuture removeModeAsyncWrapped(NativeDataCaptureMode nativeDataCaptureMode) {
            return native_removeModeAsyncWrapped(this.nativeRef, nativeDataCaptureMode);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void saveFrameProcessingTimesToJson(String str) {
            native_saveFrameProcessingTimesToJson(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void setFrameOfReferenceViewSizeAndOrientation(SizeWithUnit sizeWithUnit, float f, int i) {
            native_setFrameOfReferenceViewSizeAndOrientation(this.nativeRef, sizeWithUnit, f, i);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeWrappedFuture setFrameSourceAsyncWrapped(NativeFrameSource nativeFrameSource) {
            return native_setFrameSourceAsyncWrapped(this.nativeRef, nativeFrameSource);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public NativeWrappedFuture setModeAsyncWrapped(NativeDataCaptureMode nativeDataCaptureMode) {
            return native_setModeAsyncWrapped(this.nativeRef, nativeDataCaptureMode);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext
        public void setShouldUseImuDataAsync(boolean z) {
            native_setShouldUseImuDataAsync(this.nativeRef, z);
        }
    }

    public static native NativeDataCaptureContext createWithDeviceName(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, ArrayList<String> arrayList, NativeResourceLoader nativeResourceLoader, boolean z, NativeDataCaptureContextSettings nativeDataCaptureContextSettings);

    public static native String getEngineCommitHash();

    public static native String getEngineVersionNumber();

    public static native int getListenerPriorityOverlay();

    public static native int getListenerPriorityPlatform();

    public static native int getListenerPriorityUser();

    public static native String getVersion();

    public static native NativeDataCaptureContext initialize(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, ArrayList<String> arrayList, NativeResourceLoader nativeResourceLoader, boolean z, NativeDataCaptureContextSettings nativeDataCaptureContextSettings);

    public abstract void addFrameListenerAsync(NativeDataCaptureContextFrameListener nativeDataCaptureContextFrameListener);

    public abstract void addInertialMeasurementAsyncAndroid(NativeInertialMeasurementAndroid nativeInertialMeasurementAndroid, NativeInertialMeasurementType nativeInertialMeasurementType);

    public abstract void addListenerAsync(NativeDataCaptureContextListener nativeDataCaptureContextListener, int i);

    public abstract NativeWrappedFuture addModeAsyncWrapped(NativeDataCaptureMode nativeDataCaptureMode);

    public abstract void addRotationVectorAsyncAndroid(NativeImuDataRotationVectorAndroid nativeImuDataRotationVectorAndroid);

    public abstract void applySettings(NativeDataCaptureContextSettings nativeDataCaptureContextSettings);

    public abstract void disposeAsync();

    public abstract void flushPendingTasks();

    public abstract NativeFeatureAvailability getFeatureAvailability(NativeLicensedFeature nativeLicensedFeature);

    public abstract NativeFrameOfReference getFrameOfReference();

    public abstract NativeFrameSource getFrameSource();

    public abstract NativeLicenseInfo getLicenseInfo();

    public abstract NativeRecognitionContextSettings getSettings();

    public abstract boolean isCurrentMode(NativeDataCaptureMode nativeDataCaptureMode);

    public abstract void onActivityStopped();

    public abstract NativeWrappedFuture removeAllModesAsyncWrapped();

    public abstract NativeWrappedFuture removeCurrentModeAsyncWrapped();

    public abstract void removeFrameListenerAsync(NativeDataCaptureContextFrameListener nativeDataCaptureContextFrameListener);

    public abstract void removeListenerAsync(NativeDataCaptureContextListener nativeDataCaptureContextListener);

    public abstract NativeWrappedFuture removeModeAsyncWrapped(NativeDataCaptureMode nativeDataCaptureMode);

    public abstract void saveFrameProcessingTimesToJson(String str);

    public abstract void setFrameOfReferenceViewSizeAndOrientation(SizeWithUnit sizeWithUnit, float f, int i);

    public abstract NativeWrappedFuture setFrameSourceAsyncWrapped(NativeFrameSource nativeFrameSource);

    public abstract NativeWrappedFuture setModeAsyncWrapped(NativeDataCaptureMode nativeDataCaptureMode);

    public abstract void setShouldUseImuDataAsync(boolean z);
}
