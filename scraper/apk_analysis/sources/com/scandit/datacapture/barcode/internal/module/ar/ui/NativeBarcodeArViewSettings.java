package com.scandit.datacapture.barcode.internal.module.ar.ui;

import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.source.CameraPosition;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeArViewSettings {

    public static final class CppProxy extends NativeBarcodeArViewSettings {
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

        private native CameraPosition native_getCameraPosition(long j);

        private native boolean native_getHapticEnabled(long j);

        private native boolean native_getSoundEnabled(long j);

        private native void native_setCameraPosition(long j, CameraPosition cameraPosition);

        private native void native_setHapticEnabled(long j, boolean z);

        private native void native_setSoundEnabled(long j, boolean z);

        private native void native_updateFromJson(long j, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings
        public CameraPosition getCameraPosition() {
            return native_getCameraPosition(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings
        public boolean getHapticEnabled() {
            return native_getHapticEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings
        public boolean getSoundEnabled() {
            return native_getSoundEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings
        public void setCameraPosition(CameraPosition cameraPosition) {
            native_setCameraPosition(this.nativeRef, cameraPosition);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings
        public void setHapticEnabled(boolean z) {
            native_setHapticEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings
        public void setSoundEnabled(boolean z) {
            native_setSoundEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArViewSettings
        public void updateFromJson(NativeJsonValue nativeJsonValue) {
            native_updateFromJson(this.nativeRef, nativeJsonValue);
        }
    }

    public static native NativeBarcodeArViewSettings create();

    public static native NativeBarcodeArViewSettings fromJson(NativeJsonValue nativeJsonValue);

    public abstract CameraPosition getCameraPosition();

    public abstract boolean getHapticEnabled();

    public abstract boolean getSoundEnabled();

    public abstract void setCameraPosition(CameraPosition cameraPosition);

    public abstract void setHapticEnabled(boolean z);

    public abstract void setSoundEnabled(boolean z);

    public abstract void updateFromJson(NativeJsonValue nativeJsonValue);
}
