package com.scandit.datacapture.barcode.internal.module.ar.capture;

import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeAr {

    public static final class CppProxy extends NativeBarcodeAr {
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

        private native void native_addListenerAsync(long j, NativeBarcodeArListener nativeBarcodeArListener);

        private native NativeWrappedFuture native_applySettingsWrapped(long j, NativeBarcodeArSettings nativeBarcodeArSettings);

        private native NativeDataCaptureMode native_asDataCaptureMode(long j);

        private native NativeDataCaptureContext native_getContext(long j);

        private native NativeBarcodeArSession native_getSession(long j);

        private native boolean native_isEnabled(long j);

        private native void native_pauseAsync(long j);

        private native void native_removeListenerAsync(long j, NativeBarcodeArListener nativeBarcodeArListener);

        private native void native_setEnabled(long j, boolean z);

        private native void native_startAsync(long j);

        private native void native_stopAsync(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public void addListenerAsync(NativeBarcodeArListener nativeBarcodeArListener) {
            native_addListenerAsync(this.nativeRef, nativeBarcodeArListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public NativeWrappedFuture applySettingsWrapped(NativeBarcodeArSettings nativeBarcodeArSettings) {
            return native_applySettingsWrapped(this.nativeRef, nativeBarcodeArSettings);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public NativeDataCaptureMode asDataCaptureMode() {
            return native_asDataCaptureMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public NativeDataCaptureContext getContext() {
            return native_getContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public NativeBarcodeArSession getSession() {
            return native_getSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public boolean isEnabled() {
            return native_isEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public void pauseAsync() {
            native_pauseAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public void removeListenerAsync(NativeBarcodeArListener nativeBarcodeArListener) {
            native_removeListenerAsync(this.nativeRef, nativeBarcodeArListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public void setEnabled(boolean z) {
            native_setEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public void startAsync() {
            native_startAsync(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr
        public void stopAsync() {
            native_stopAsync(this.nativeRef);
        }
    }

    public static native NativeBarcodeAr create(NativeDataCaptureContext nativeDataCaptureContext, NativeBarcodeArSettings nativeBarcodeArSettings);

    public abstract void addListenerAsync(NativeBarcodeArListener nativeBarcodeArListener);

    public abstract NativeWrappedFuture applySettingsWrapped(NativeBarcodeArSettings nativeBarcodeArSettings);

    public abstract NativeDataCaptureMode asDataCaptureMode();

    public abstract NativeDataCaptureContext getContext();

    public abstract NativeBarcodeArSession getSession();

    public abstract boolean isEnabled();

    public abstract void pauseAsync();

    public abstract void removeListenerAsync(NativeBarcodeArListener nativeBarcodeArListener);

    public abstract void setEnabled(boolean z);

    public abstract void startAsync();

    public abstract void stopAsync();
}
