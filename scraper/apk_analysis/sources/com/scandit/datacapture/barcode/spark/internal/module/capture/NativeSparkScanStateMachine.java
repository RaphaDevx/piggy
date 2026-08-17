package com.scandit.datacapture.barcode.spark.internal.module.capture;

import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSparkScanStateMachine {

    public static final class CppProxy extends NativeSparkScanStateMachine {
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

        private native void native_addScanningModeListenerAsync(long j, NativeSparkScanScanningModeHandlerListener nativeSparkScanScanningModeHandlerListener);

        private native void native_addSparkScanViewUiListenerAsync(long j, NativeSparkScanViewUiListener nativeSparkScanViewUiListener);

        private native void native_forceViewState(long j, SparkScanViewState sparkScanViewState);

        private native NativeSparkScanScanningMode native_getScanningMode(long j);

        private native SparkScanViewState native_getViewState(long j);

        private native void native_onCameraSwitchButtonTapped(long j);

        private native void native_onScanningBehaviorButtonTapped(long j);

        private native void native_onTargetModeButtonTapped(long j);

        private native void native_onTorchButtonTapped(long j);

        private native void native_onZoomIn(long j);

        private native void native_onZoomOut(long j);

        private native void native_removeScanningModeListenerAsync(long j, NativeSparkScanScanningModeHandlerListener nativeSparkScanScanningModeHandlerListener);

        private native void native_setScanningMode(long j, NativeSparkScanScanningMode nativeSparkScanScanningMode);

        private native void native_setToastPresenterDelegate(long j, NativeSparkScanToastPresenterDelegate nativeSparkScanToastPresenterDelegate);

        private native void native_setViewHandlerDelegate(long j, NativeSparkScanViewHandlerDelegate nativeSparkScanViewHandlerDelegate);

        private native void native_setViewState(long j, SparkScanViewState sparkScanViewState);

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void addScanningModeListenerAsync(NativeSparkScanScanningModeHandlerListener nativeSparkScanScanningModeHandlerListener) {
            native_addScanningModeListenerAsync(this.nativeRef, nativeSparkScanScanningModeHandlerListener);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void addSparkScanViewUiListenerAsync(NativeSparkScanViewUiListener nativeSparkScanViewUiListener) {
            native_addSparkScanViewUiListenerAsync(this.nativeRef, nativeSparkScanViewUiListener);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void forceViewState(SparkScanViewState sparkScanViewState) {
            native_forceViewState(this.nativeRef, sparkScanViewState);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public NativeSparkScanScanningMode getScanningMode() {
            return native_getScanningMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public SparkScanViewState getViewState() {
            return native_getViewState(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void onCameraSwitchButtonTapped() {
            native_onCameraSwitchButtonTapped(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void onScanningBehaviorButtonTapped() {
            native_onScanningBehaviorButtonTapped(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void onTargetModeButtonTapped() {
            native_onTargetModeButtonTapped(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void onTorchButtonTapped() {
            native_onTorchButtonTapped(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void onZoomIn() {
            native_onZoomIn(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void onZoomOut() {
            native_onZoomOut(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void removeScanningModeListenerAsync(NativeSparkScanScanningModeHandlerListener nativeSparkScanScanningModeHandlerListener) {
            native_removeScanningModeListenerAsync(this.nativeRef, nativeSparkScanScanningModeHandlerListener);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void setScanningMode(NativeSparkScanScanningMode nativeSparkScanScanningMode) {
            native_setScanningMode(this.nativeRef, nativeSparkScanScanningMode);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void setToastPresenterDelegate(NativeSparkScanToastPresenterDelegate nativeSparkScanToastPresenterDelegate) {
            native_setToastPresenterDelegate(this.nativeRef, nativeSparkScanToastPresenterDelegate);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void setViewHandlerDelegate(NativeSparkScanViewHandlerDelegate nativeSparkScanViewHandlerDelegate) {
            native_setViewHandlerDelegate(this.nativeRef, nativeSparkScanViewHandlerDelegate);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanStateMachine
        public void setViewState(SparkScanViewState sparkScanViewState) {
            native_setViewState(this.nativeRef, sparkScanViewState);
        }
    }

    public static native NativeSparkScanStateMachine createForTests();

    public abstract void addScanningModeListenerAsync(NativeSparkScanScanningModeHandlerListener nativeSparkScanScanningModeHandlerListener);

    public abstract void addSparkScanViewUiListenerAsync(NativeSparkScanViewUiListener nativeSparkScanViewUiListener);

    public abstract void forceViewState(SparkScanViewState sparkScanViewState);

    public abstract NativeSparkScanScanningMode getScanningMode();

    public abstract SparkScanViewState getViewState();

    public abstract void onCameraSwitchButtonTapped();

    public abstract void onScanningBehaviorButtonTapped();

    public abstract void onTargetModeButtonTapped();

    public abstract void onTorchButtonTapped();

    public abstract void onZoomIn();

    public abstract void onZoomOut();

    public abstract void removeScanningModeListenerAsync(NativeSparkScanScanningModeHandlerListener nativeSparkScanScanningModeHandlerListener);

    public abstract void setScanningMode(NativeSparkScanScanningMode nativeSparkScanScanningMode);

    public abstract void setToastPresenterDelegate(NativeSparkScanToastPresenterDelegate nativeSparkScanToastPresenterDelegate);

    public abstract void setViewHandlerDelegate(NativeSparkScanViewHandlerDelegate nativeSparkScanViewHandlerDelegate);

    public abstract void setViewState(SparkScanViewState sparkScanViewState);
}
