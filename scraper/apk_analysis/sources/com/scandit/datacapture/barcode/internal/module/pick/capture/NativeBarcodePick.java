package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeFeedback;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePick {

    public static final class CppProxy extends NativeBarcodePick {
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

        private native void native_addActionListenerAsync(long j, NativeBarcodePickActionListener nativeBarcodePickActionListener);

        private native void native_addListenerAsync(long j, NativeBarcodePickListener nativeBarcodePickListener);

        private native void native_addPublicListenerAsync(long j, NativeBarcodePickPublicListener nativeBarcodePickPublicListener);

        private native void native_addScanningListenerAsync(long j, NativeBarcodePickScanningListener nativeBarcodePickScanningListener);

        private native void native_addStatusListenerAsync(long j, NativeBarcodePickStatusListener nativeBarcodePickStatusListener);

        private native NativeWrappedFuture native_applySettingsWrapped(long j, NativeBarcodePickSettings nativeBarcodePickSettings);

        private native NativeDataCaptureMode native_asDataCaptureMode(long j);

        private native void native_completeActionForItem(long j, String str, boolean z);

        private native void native_freezeAsync(long j);

        private native NativeFeedback native_getCodePickedFeedback(long j);

        private native NativeDataCaptureContext native_getContext(long j);

        private native NativeBarcodePickSession native_getSession(long j);

        private native NativeFeedback native_getToPickCodeScannedFeedback(long j);

        private native void native_handleTapAsync(long j, NativeTrackedObject nativeTrackedObject, NativeBarcodePickSelectItemActionCallback nativeBarcodePickSelectItemActionCallback);

        private native boolean native_isEnabled(long j);

        private native void native_notifyStatusShownWhilePausedUpdatedAsync(long j, boolean z);

        private native void native_pauseAsync(long j);

        private native void native_removeActionListenerAsync(long j, NativeBarcodePickActionListener nativeBarcodePickActionListener);

        private native void native_removeListenerAsync(long j, NativeBarcodePickListener nativeBarcodePickListener);

        private native void native_removePublicListenerAsync(long j, NativeBarcodePickPublicListener nativeBarcodePickPublicListener);

        private native void native_removeScanningListenerAsync(long j, NativeBarcodePickScanningListener nativeBarcodePickScanningListener);

        private native void native_removeStatusListenerAsync(long j, NativeBarcodePickStatusListener nativeBarcodePickStatusListener);

        private native void native_reset(long j);

        private native void native_setCodePickedFeedback(long j, NativeFeedback nativeFeedback);

        private native void native_setEnabled(long j, boolean z);

        private native void native_setToPickCodeScannedFeedback(long j, NativeFeedback nativeFeedback);

        private native void native_startAsync(long j);

        private native void native_stopAsync(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void addActionListenerAsync(NativeBarcodePickActionListener nativeBarcodePickActionListener) {
            native_addActionListenerAsync(this.nativeRef, nativeBarcodePickActionListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void addListenerAsync(NativeBarcodePickListener nativeBarcodePickListener) {
            native_addListenerAsync(this.nativeRef, nativeBarcodePickListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void addPublicListenerAsync(NativeBarcodePickPublicListener nativeBarcodePickPublicListener) {
            native_addPublicListenerAsync(this.nativeRef, nativeBarcodePickPublicListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void addScanningListenerAsync(NativeBarcodePickScanningListener nativeBarcodePickScanningListener) {
            native_addScanningListenerAsync(this.nativeRef, nativeBarcodePickScanningListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void addStatusListenerAsync(NativeBarcodePickStatusListener nativeBarcodePickStatusListener) {
            native_addStatusListenerAsync(this.nativeRef, nativeBarcodePickStatusListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public NativeWrappedFuture applySettingsWrapped(NativeBarcodePickSettings nativeBarcodePickSettings) {
            return native_applySettingsWrapped(this.nativeRef, nativeBarcodePickSettings);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public NativeDataCaptureMode asDataCaptureMode() {
            return native_asDataCaptureMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void completeActionForItem(String str, boolean z) {
            native_completeActionForItem(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void freezeAsync() {
            native_freezeAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public NativeFeedback getCodePickedFeedback() {
            return native_getCodePickedFeedback(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public NativeDataCaptureContext getContext() {
            return native_getContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public NativeBarcodePickSession getSession() {
            return native_getSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public NativeFeedback getToPickCodeScannedFeedback() {
            return native_getToPickCodeScannedFeedback(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void handleTapAsync(NativeTrackedObject nativeTrackedObject, NativeBarcodePickSelectItemActionCallback nativeBarcodePickSelectItemActionCallback) {
            native_handleTapAsync(this.nativeRef, nativeTrackedObject, nativeBarcodePickSelectItemActionCallback);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public boolean isEnabled() {
            return native_isEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void notifyStatusShownWhilePausedUpdatedAsync(boolean z) {
            native_notifyStatusShownWhilePausedUpdatedAsync(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void pauseAsync() {
            native_pauseAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void removeActionListenerAsync(NativeBarcodePickActionListener nativeBarcodePickActionListener) {
            native_removeActionListenerAsync(this.nativeRef, nativeBarcodePickActionListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void removeListenerAsync(NativeBarcodePickListener nativeBarcodePickListener) {
            native_removeListenerAsync(this.nativeRef, nativeBarcodePickListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void removePublicListenerAsync(NativeBarcodePickPublicListener nativeBarcodePickPublicListener) {
            native_removePublicListenerAsync(this.nativeRef, nativeBarcodePickPublicListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void removeScanningListenerAsync(NativeBarcodePickScanningListener nativeBarcodePickScanningListener) {
            native_removeScanningListenerAsync(this.nativeRef, nativeBarcodePickScanningListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void removeStatusListenerAsync(NativeBarcodePickStatusListener nativeBarcodePickStatusListener) {
            native_removeStatusListenerAsync(this.nativeRef, nativeBarcodePickStatusListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void reset() {
            native_reset(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void setCodePickedFeedback(NativeFeedback nativeFeedback) {
            native_setCodePickedFeedback(this.nativeRef, nativeFeedback);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void setEnabled(boolean z) {
            native_setEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void setToPickCodeScannedFeedback(NativeFeedback nativeFeedback) {
            native_setToPickCodeScannedFeedback(this.nativeRef, nativeFeedback);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void startAsync() {
            native_startAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick
        public void stopAsync() {
            native_stopAsync(this.nativeRef);
        }
    }

    public static native NativeBarcodePick create(NativeDataCaptureContext nativeDataCaptureContext, NativeBarcodePickSettings nativeBarcodePickSettings, NativeProductProvider nativeProductProvider);

    public abstract void addActionListenerAsync(NativeBarcodePickActionListener nativeBarcodePickActionListener);

    public abstract void addListenerAsync(NativeBarcodePickListener nativeBarcodePickListener);

    public abstract void addPublicListenerAsync(NativeBarcodePickPublicListener nativeBarcodePickPublicListener);

    public abstract void addScanningListenerAsync(NativeBarcodePickScanningListener nativeBarcodePickScanningListener);

    public abstract void addStatusListenerAsync(NativeBarcodePickStatusListener nativeBarcodePickStatusListener);

    public abstract NativeWrappedFuture applySettingsWrapped(NativeBarcodePickSettings nativeBarcodePickSettings);

    public abstract NativeDataCaptureMode asDataCaptureMode();

    public abstract void completeActionForItem(String str, boolean z);

    public abstract void freezeAsync();

    public abstract NativeFeedback getCodePickedFeedback();

    public abstract NativeDataCaptureContext getContext();

    public abstract NativeBarcodePickSession getSession();

    public abstract NativeFeedback getToPickCodeScannedFeedback();

    public abstract void handleTapAsync(NativeTrackedObject nativeTrackedObject, NativeBarcodePickSelectItemActionCallback nativeBarcodePickSelectItemActionCallback);

    public abstract boolean isEnabled();

    public abstract void notifyStatusShownWhilePausedUpdatedAsync(boolean z);

    public abstract void pauseAsync();

    public abstract void removeActionListenerAsync(NativeBarcodePickActionListener nativeBarcodePickActionListener);

    public abstract void removeListenerAsync(NativeBarcodePickListener nativeBarcodePickListener);

    public abstract void removePublicListenerAsync(NativeBarcodePickPublicListener nativeBarcodePickPublicListener);

    public abstract void removeScanningListenerAsync(NativeBarcodePickScanningListener nativeBarcodePickScanningListener);

    public abstract void removeStatusListenerAsync(NativeBarcodePickStatusListener nativeBarcodePickStatusListener);

    public abstract void reset();

    public abstract void setCodePickedFeedback(NativeFeedback nativeFeedback);

    public abstract void setEnabled(boolean z);

    public abstract void setToPickCodeScannedFeedback(NativeFeedback nativeFeedback);

    public abstract void startAsync();

    public abstract void stopAsync();
}
