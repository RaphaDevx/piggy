package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.count.capture.BarcodeCountState;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeFeedback;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCount {

    public static final class CppProxy extends NativeBarcodeCount {
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

        private native void native_activateState(long j, BarcodeCountState barcodeCountState);

        private native void native_addListenerAsync(long j, NativeBarcodeCountListener nativeBarcodeCountListener, int i);

        private native void native_addStateListenerAsync(long j, NativeBarcodeCountStateListener nativeBarcodeCountStateListener, int i);

        private native NativeWrappedFuture native_applySettingsWrapped(long j, NativeBarcodeCountSettings nativeBarcodeCountSettings);

        private native NativeDataCaptureMode native_asDataCaptureMode(long j);

        private native NativeBarcodeClusterEditor native_beginClusterEditing(long j);

        private native void native_clearAdditionalBarcodes(long j);

        private native void native_clearHighlightsAndResetSessionAsync(long j);

        private native void native_didAcceptNotInListBarcode(long j, NativeTrackedBarcode nativeTrackedBarcode);

        private native void native_didRejectNotInListBarcode(long j, NativeTrackedBarcode nativeTrackedBarcode);

        private native NativeDataCaptureContext native_getContext(long j);

        private native NativeBarcodeCountSession native_getSession(long j);

        private native boolean native_isAttachedToContext(long j);

        private native boolean native_isEnabled(long j);

        private native boolean native_isTapToUncountEnabled(long j);

        private native void native_notifyListeners(long j, NativeFrameData nativeFrameData);

        private native void native_removeListenerAsync(long j, NativeBarcodeCountListener nativeBarcodeCountListener);

        private native void native_removeStateListenerAsync(long j, NativeBarcodeCountStateListener nativeBarcodeCountStateListener);

        private native void native_reset(long j);

        private native void native_setAdditionalBarcodes(long j, ArrayList<NativeBarcode> arrayList);

        private native void native_setBarcodeCountCaptureList(long j, NativeBarcodeCountCaptureList nativeBarcodeCountCaptureList);

        private native void native_setEnabled(long j, boolean z);

        private native void native_setFailureFeedback(long j, NativeFeedback nativeFeedback);

        private native void native_setShouldShowStatusIconsOnScan(long j, boolean z);

        private native void native_setStatusListener(long j, NativeBarcodeCountStatusListener nativeBarcodeCountStatusListener);

        private native void native_setStatusProvider(long j, NativeBarcodeCountStatusProvider nativeBarcodeCountStatusProvider);

        private native void native_setSuccessFeedback(long j, NativeFeedback nativeFeedback);

        private native void native_setTapToUncountEnabled(long j, boolean z);

        private native void native_setUnrecognizedFeedback(long j, NativeFeedback nativeFeedback);

        private native boolean native_shouldDisableModeWhenCaptureListCompleted(long j);

        private native boolean native_shouldShowStatusIconsOnScan(long j);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void activateState(BarcodeCountState barcodeCountState) {
            native_activateState(this.nativeRef, barcodeCountState);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void addListenerAsync(NativeBarcodeCountListener nativeBarcodeCountListener, int i) {
            native_addListenerAsync(this.nativeRef, nativeBarcodeCountListener, i);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void addStateListenerAsync(NativeBarcodeCountStateListener nativeBarcodeCountStateListener, int i) {
            native_addStateListenerAsync(this.nativeRef, nativeBarcodeCountStateListener, i);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public NativeWrappedFuture applySettingsWrapped(NativeBarcodeCountSettings nativeBarcodeCountSettings) {
            return native_applySettingsWrapped(this.nativeRef, nativeBarcodeCountSettings);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public NativeDataCaptureMode asDataCaptureMode() {
            return native_asDataCaptureMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public NativeBarcodeClusterEditor beginClusterEditing() {
            return native_beginClusterEditing(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void clearAdditionalBarcodes() {
            native_clearAdditionalBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void clearHighlightsAndResetSessionAsync() {
            native_clearHighlightsAndResetSessionAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void didAcceptNotInListBarcode(NativeTrackedBarcode nativeTrackedBarcode) {
            native_didAcceptNotInListBarcode(this.nativeRef, nativeTrackedBarcode);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void didRejectNotInListBarcode(NativeTrackedBarcode nativeTrackedBarcode) {
            native_didRejectNotInListBarcode(this.nativeRef, nativeTrackedBarcode);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public NativeDataCaptureContext getContext() {
            return native_getContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public NativeBarcodeCountSession getSession() {
            return native_getSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public boolean isAttachedToContext() {
            return native_isAttachedToContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public boolean isEnabled() {
            return native_isEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public boolean isTapToUncountEnabled() {
            return native_isTapToUncountEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void notifyListeners(NativeFrameData nativeFrameData) {
            native_notifyListeners(this.nativeRef, nativeFrameData);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void removeListenerAsync(NativeBarcodeCountListener nativeBarcodeCountListener) {
            native_removeListenerAsync(this.nativeRef, nativeBarcodeCountListener);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void removeStateListenerAsync(NativeBarcodeCountStateListener nativeBarcodeCountStateListener) {
            native_removeStateListenerAsync(this.nativeRef, nativeBarcodeCountStateListener);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void reset() {
            native_reset(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setAdditionalBarcodes(ArrayList<NativeBarcode> arrayList) {
            native_setAdditionalBarcodes(this.nativeRef, arrayList);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setBarcodeCountCaptureList(NativeBarcodeCountCaptureList nativeBarcodeCountCaptureList) {
            native_setBarcodeCountCaptureList(this.nativeRef, nativeBarcodeCountCaptureList);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setEnabled(boolean z) {
            native_setEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setFailureFeedback(NativeFeedback nativeFeedback) {
            native_setFailureFeedback(this.nativeRef, nativeFeedback);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setShouldShowStatusIconsOnScan(boolean z) {
            native_setShouldShowStatusIconsOnScan(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setStatusListener(NativeBarcodeCountStatusListener nativeBarcodeCountStatusListener) {
            native_setStatusListener(this.nativeRef, nativeBarcodeCountStatusListener);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setStatusProvider(NativeBarcodeCountStatusProvider nativeBarcodeCountStatusProvider) {
            native_setStatusProvider(this.nativeRef, nativeBarcodeCountStatusProvider);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setSuccessFeedback(NativeFeedback nativeFeedback) {
            native_setSuccessFeedback(this.nativeRef, nativeFeedback);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setTapToUncountEnabled(boolean z) {
            native_setTapToUncountEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public void setUnrecognizedFeedback(NativeFeedback nativeFeedback) {
            native_setUnrecognizedFeedback(this.nativeRef, nativeFeedback);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public boolean shouldDisableModeWhenCaptureListCompleted() {
            return native_shouldDisableModeWhenCaptureListCompleted(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount
        public boolean shouldShowStatusIconsOnScan() {
            return native_shouldShowStatusIconsOnScan(this.nativeRef);
        }
    }

    public static native NativeBarcodeCount create(NativeDataCaptureContext nativeDataCaptureContext, NativeBarcodeCountSettings nativeBarcodeCountSettings);

    public abstract void activateState(BarcodeCountState barcodeCountState);

    public abstract void addListenerAsync(NativeBarcodeCountListener nativeBarcodeCountListener, int i);

    public abstract void addStateListenerAsync(NativeBarcodeCountStateListener nativeBarcodeCountStateListener, int i);

    public abstract NativeWrappedFuture applySettingsWrapped(NativeBarcodeCountSettings nativeBarcodeCountSettings);

    public abstract NativeDataCaptureMode asDataCaptureMode();

    public abstract NativeBarcodeClusterEditor beginClusterEditing();

    public abstract void clearAdditionalBarcodes();

    public abstract void clearHighlightsAndResetSessionAsync();

    public abstract void didAcceptNotInListBarcode(NativeTrackedBarcode nativeTrackedBarcode);

    public abstract void didRejectNotInListBarcode(NativeTrackedBarcode nativeTrackedBarcode);

    public abstract NativeDataCaptureContext getContext();

    public abstract NativeBarcodeCountSession getSession();

    public abstract boolean isAttachedToContext();

    public abstract boolean isEnabled();

    public abstract boolean isTapToUncountEnabled();

    public abstract void notifyListeners(NativeFrameData nativeFrameData);

    public abstract void removeListenerAsync(NativeBarcodeCountListener nativeBarcodeCountListener);

    public abstract void removeStateListenerAsync(NativeBarcodeCountStateListener nativeBarcodeCountStateListener);

    public abstract void reset();

    public abstract void setAdditionalBarcodes(ArrayList<NativeBarcode> arrayList);

    public abstract void setBarcodeCountCaptureList(NativeBarcodeCountCaptureList nativeBarcodeCountCaptureList);

    public abstract void setEnabled(boolean z);

    public abstract void setFailureFeedback(NativeFeedback nativeFeedback);

    public abstract void setShouldShowStatusIconsOnScan(boolean z);

    public abstract void setStatusListener(NativeBarcodeCountStatusListener nativeBarcodeCountStatusListener);

    public abstract void setStatusProvider(NativeBarcodeCountStatusProvider nativeBarcodeCountStatusProvider);

    public abstract void setSuccessFeedback(NativeFeedback nativeFeedback);

    public abstract void setTapToUncountEnabled(boolean z);

    public abstract void setUnrecognizedFeedback(NativeFeedback nativeFeedback);

    public abstract boolean shouldDisableModeWhenCaptureListCompleted();

    public abstract boolean shouldShowStatusIconsOnScan();
}
