package com.scandit.datacapture.barcode.spark.internal.module.capture;

import com.scandit.datacapture.core.internal.sdk.analytics.NativePropertyPushSource;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeFeedback;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSparkScan {

    public static final class CppProxy extends NativeSparkScan {
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

        private native void native_addListenerAsync(long j, NativeSparkScanListener nativeSparkScanListener);

        private native NativeWrappedFuture native_applySettingsWrapped(long j, NativeSparkScanSettings nativeSparkScanSettings);

        private native NativeDataCaptureMode native_asDataCaptureMode(long j);

        private native void native_enableSingleScanMode(long j, boolean z);

        private native NativeDataCaptureContext native_getContext(long j);

        private native NativeSparkScanSession native_getSession(long j);

        private native NativeSparkScanLicenseInfo native_getSparkScanLicenseInfo(long j);

        private native NativeSparkScanStateMachine native_getStateMachine(long j);

        private native NativeFeedback native_getSuccessFeedback(long j);

        private native boolean native_isEnabled(long j);

        private native boolean native_isSuppressed(long j);

        private native void native_removeListenerAsync(long j, NativeSparkScanListener nativeSparkScanListener);

        private native void native_selectBarcodeWithSmartScanSelectionAsync(long j);

        private native void native_setEnabled(long j, boolean z);

        private native void native_setPropertyPushSource(long j, NativePropertyPushSource nativePropertyPushSource);

        private native void native_setResultSuppressionEnabled(long j, boolean z);

        private native void native_setSuccessFeedback(long j, NativeFeedback nativeFeedback);

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public void addListenerAsync(NativeSparkScanListener nativeSparkScanListener) {
            native_addListenerAsync(this.nativeRef, nativeSparkScanListener);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public NativeWrappedFuture applySettingsWrapped(NativeSparkScanSettings nativeSparkScanSettings) {
            return native_applySettingsWrapped(this.nativeRef, nativeSparkScanSettings);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public NativeDataCaptureMode asDataCaptureMode() {
            return native_asDataCaptureMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public void enableSingleScanMode(boolean z) {
            native_enableSingleScanMode(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public NativeDataCaptureContext getContext() {
            return native_getContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public NativeSparkScanSession getSession() {
            return native_getSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public NativeSparkScanLicenseInfo getSparkScanLicenseInfo() {
            return native_getSparkScanLicenseInfo(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public NativeSparkScanStateMachine getStateMachine() {
            return native_getStateMachine(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public NativeFeedback getSuccessFeedback() {
            return native_getSuccessFeedback(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public boolean isEnabled() {
            return native_isEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public boolean isSuppressed() {
            return native_isSuppressed(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public void removeListenerAsync(NativeSparkScanListener nativeSparkScanListener) {
            native_removeListenerAsync(this.nativeRef, nativeSparkScanListener);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public void selectBarcodeWithSmartScanSelectionAsync() {
            native_selectBarcodeWithSmartScanSelectionAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public void setEnabled(boolean z) {
            native_setEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public void setPropertyPushSource(NativePropertyPushSource nativePropertyPushSource) {
            native_setPropertyPushSource(this.nativeRef, nativePropertyPushSource);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public void setResultSuppressionEnabled(boolean z) {
            native_setResultSuppressionEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan
        public void setSuccessFeedback(NativeFeedback nativeFeedback) {
            native_setSuccessFeedback(this.nativeRef, nativeFeedback);
        }
    }

    public static native NativeSparkScan create(NativeDataCaptureContext nativeDataCaptureContext, NativeSparkScanSettings nativeSparkScanSettings);

    public abstract void addListenerAsync(NativeSparkScanListener nativeSparkScanListener);

    public abstract NativeWrappedFuture applySettingsWrapped(NativeSparkScanSettings nativeSparkScanSettings);

    public abstract NativeDataCaptureMode asDataCaptureMode();

    public abstract void enableSingleScanMode(boolean z);

    public abstract NativeDataCaptureContext getContext();

    public abstract NativeSparkScanSession getSession();

    public abstract NativeSparkScanLicenseInfo getSparkScanLicenseInfo();

    public abstract NativeSparkScanStateMachine getStateMachine();

    public abstract NativeFeedback getSuccessFeedback();

    public abstract boolean isEnabled();

    public abstract boolean isSuppressed();

    public abstract void removeListenerAsync(NativeSparkScanListener nativeSparkScanListener);

    public abstract void selectBarcodeWithSmartScanSelectionAsync();

    public abstract void setEnabled(boolean z);

    public abstract void setPropertyPushSource(NativePropertyPushSource nativePropertyPushSource);

    public abstract void setResultSuppressionEnabled(boolean z);

    public abstract void setSuccessFeedback(NativeFeedback nativeFeedback);
}
