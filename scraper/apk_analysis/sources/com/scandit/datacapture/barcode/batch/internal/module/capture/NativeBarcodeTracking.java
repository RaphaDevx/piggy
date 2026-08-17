package com.scandit.datacapture.barcode.batch.internal.module.capture;

import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureMode;
import com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedFuture;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeTracking {

    public static final class CppProxy extends NativeBarcodeTracking {
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

        private native void native_activateState(long j, String str);

        private native void native_addListenerAsync(long j, NativeBarcodeTrackingListener nativeBarcodeTrackingListener, int i);

        private native NativeWrappedFuture native_applySettingsWrapped(long j, NativeBarcodeTrackingSettings nativeBarcodeTrackingSettings);

        private native NativeDataCaptureMode native_asDataCaptureMode(long j);

        private native void native_clear(long j);

        private native NativeBarcodeTrackingLicenseInfo native_getBarcodeTrackingLicenseInfo(long j);

        private native NativeDataCaptureContext native_getContext(long j);

        private native NativeBarcodeTrackingSession native_getSession(long j);

        private native boolean native_isAttachedToContext(long j);

        private native boolean native_isEnabled(long j);

        private native void native_removeListenerAsync(long j, NativeBarcodeTrackingListener nativeBarcodeTrackingListener);

        private native void native_setEnabled(long j, boolean z);

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public void activateState(String str) {
            native_activateState(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public void addListenerAsync(NativeBarcodeTrackingListener nativeBarcodeTrackingListener, int i) {
            native_addListenerAsync(this.nativeRef, nativeBarcodeTrackingListener, i);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public NativeWrappedFuture applySettingsWrapped(NativeBarcodeTrackingSettings nativeBarcodeTrackingSettings) {
            return native_applySettingsWrapped(this.nativeRef, nativeBarcodeTrackingSettings);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public NativeDataCaptureMode asDataCaptureMode() {
            return native_asDataCaptureMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public void clear() {
            native_clear(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public NativeBarcodeTrackingLicenseInfo getBarcodeTrackingLicenseInfo() {
            return native_getBarcodeTrackingLicenseInfo(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public NativeDataCaptureContext getContext() {
            return native_getContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public NativeBarcodeTrackingSession getSession() {
            return native_getSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public boolean isAttachedToContext() {
            return native_isAttachedToContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public boolean isEnabled() {
            return native_isEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public void removeListenerAsync(NativeBarcodeTrackingListener nativeBarcodeTrackingListener) {
            native_removeListenerAsync(this.nativeRef, nativeBarcodeTrackingListener);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking
        public void setEnabled(boolean z) {
            native_setEnabled(this.nativeRef, z);
        }
    }

    public static native NativeBarcodeTracking create(NativeDataCaptureContext nativeDataCaptureContext, NativeBarcodeTrackingSettings nativeBarcodeTrackingSettings);

    public abstract void activateState(String str);

    public abstract void addListenerAsync(NativeBarcodeTrackingListener nativeBarcodeTrackingListener, int i);

    public abstract NativeWrappedFuture applySettingsWrapped(NativeBarcodeTrackingSettings nativeBarcodeTrackingSettings);

    public abstract NativeDataCaptureMode asDataCaptureMode();

    public abstract void clear();

    public abstract NativeBarcodeTrackingLicenseInfo getBarcodeTrackingLicenseInfo();

    public abstract NativeDataCaptureContext getContext();

    public abstract NativeBarcodeTrackingSession getSession();

    public abstract boolean isAttachedToContext();

    public abstract boolean isEnabled();

    public abstract void removeListenerAsync(NativeBarcodeTrackingListener nativeBarcodeTrackingListener);

    public abstract void setEnabled(boolean z);
}
