package com.scandit.datacapture.barcode.internal.module.find.capture;

import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.capture.NativeFeedback;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.snapchat.djinni.NativeObjectManager;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeFind {

    public static final class CppProxy extends NativeBarcodeFind {
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

        private native void native_addListenerAsync(long j, NativeBarcodeFindListener nativeBarcodeFindListener, int i);

        private native NativeWrappedFuture native_applySettingsWrapped(long j, NativeBarcodeFindSettings nativeBarcodeFindSettings);

        private native NativeDataCaptureMode native_asDataCaptureMode(long j);

        private native NativeBarcodeFindSession native_getSession(long j);

        private native boolean native_isAttachedToContext(long j);

        private native boolean native_isEnabled(long j);

        private native void native_pauseAsync(long j);

        private native void native_removeListenerAsync(long j, NativeBarcodeFindListener nativeBarcodeFindListener);

        private native void native_setBarcodeFindTransformerAsync(long j, NativeBarcodeFindTransformer nativeBarcodeFindTransformer);

        private native void native_setEnabled(long j, boolean z);

        private native void native_setFeedback(long j, NativeFeedback nativeFeedback);

        private native void native_setItemListAsync(long j, HashSet<NativeBarcodeFindItem> hashSet);

        private native void native_setItemListUpdatedFeedback(long j, NativeFeedback nativeFeedback);

        private native void native_startAsync(long j);

        private native void native_stopAsync(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void addListenerAsync(NativeBarcodeFindListener nativeBarcodeFindListener, int i) {
            native_addListenerAsync(this.nativeRef, nativeBarcodeFindListener, i);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public NativeWrappedFuture applySettingsWrapped(NativeBarcodeFindSettings nativeBarcodeFindSettings) {
            return native_applySettingsWrapped(this.nativeRef, nativeBarcodeFindSettings);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public NativeDataCaptureMode asDataCaptureMode() {
            return native_asDataCaptureMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public NativeBarcodeFindSession getSession() {
            return native_getSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public boolean isAttachedToContext() {
            return native_isAttachedToContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public boolean isEnabled() {
            return native_isEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void pauseAsync() {
            native_pauseAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void removeListenerAsync(NativeBarcodeFindListener nativeBarcodeFindListener) {
            native_removeListenerAsync(this.nativeRef, nativeBarcodeFindListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void setBarcodeFindTransformerAsync(NativeBarcodeFindTransformer nativeBarcodeFindTransformer) {
            native_setBarcodeFindTransformerAsync(this.nativeRef, nativeBarcodeFindTransformer);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void setEnabled(boolean z) {
            native_setEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void setFeedback(NativeFeedback nativeFeedback) {
            native_setFeedback(this.nativeRef, nativeFeedback);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void setItemListAsync(HashSet<NativeBarcodeFindItem> hashSet) {
            native_setItemListAsync(this.nativeRef, hashSet);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void setItemListUpdatedFeedback(NativeFeedback nativeFeedback) {
            native_setItemListUpdatedFeedback(this.nativeRef, nativeFeedback);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void startAsync() {
            native_startAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind
        public void stopAsync() {
            native_stopAsync(this.nativeRef);
        }
    }

    public static native NativeBarcodeFind create(NativeDataCaptureContext nativeDataCaptureContext, NativeBarcodeFindSettings nativeBarcodeFindSettings);

    public abstract void addListenerAsync(NativeBarcodeFindListener nativeBarcodeFindListener, int i);

    public abstract NativeWrappedFuture applySettingsWrapped(NativeBarcodeFindSettings nativeBarcodeFindSettings);

    public abstract NativeDataCaptureMode asDataCaptureMode();

    public abstract NativeBarcodeFindSession getSession();

    public abstract boolean isAttachedToContext();

    public abstract boolean isEnabled();

    public abstract void pauseAsync();

    public abstract void removeListenerAsync(NativeBarcodeFindListener nativeBarcodeFindListener);

    public abstract void setBarcodeFindTransformerAsync(NativeBarcodeFindTransformer nativeBarcodeFindTransformer);

    public abstract void setEnabled(boolean z);

    public abstract void setFeedback(NativeFeedback nativeFeedback);

    public abstract void setItemListAsync(HashSet<NativeBarcodeFindItem> hashSet);

    public abstract void setItemListUpdatedFeedback(NativeFeedback nativeFeedback);

    public abstract void startAsync();

    public abstract void stopAsync();
}
