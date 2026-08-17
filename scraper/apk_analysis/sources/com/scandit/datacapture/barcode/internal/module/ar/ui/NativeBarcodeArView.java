package com.scandit.datacapture.barcode.internal.module.ar.ui;

import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeArView {

    public static final class CppProxy extends NativeBarcodeArView {
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

        private native Anchor native_getCameraSwitchControlPosition(long j);

        private native boolean native_getShouldShowCameraSwitchControl(long j);

        private native boolean native_getShouldShowTorchControl(long j);

        private native boolean native_getShouldShowZoomControl(long j);

        private native Anchor native_getTorchControlPosition(long j);

        private native Anchor native_getZoomControlPosition(long j);

        private native void native_setCameraSwitchControlPosition(long j, Anchor anchor);

        private native void native_setShouldShowCameraSwitchControl(long j, boolean z);

        private native void native_setShouldShowTorchControl(long j, boolean z);

        private native void native_setShouldShowZoomControl(long j, boolean z);

        private native void native_setTorchControlPosition(long j, Anchor anchor);

        private native void native_setZoomControlPosition(long j, Anchor anchor);

        private native void native_updateFromJson(long j, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public Anchor getCameraSwitchControlPosition() {
            return native_getCameraSwitchControlPosition(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public boolean getShouldShowCameraSwitchControl() {
            return native_getShouldShowCameraSwitchControl(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public boolean getShouldShowTorchControl() {
            return native_getShouldShowTorchControl(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public boolean getShouldShowZoomControl() {
            return native_getShouldShowZoomControl(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public Anchor getTorchControlPosition() {
            return native_getTorchControlPosition(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public Anchor getZoomControlPosition() {
            return native_getZoomControlPosition(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public void setCameraSwitchControlPosition(Anchor anchor) {
            native_setCameraSwitchControlPosition(this.nativeRef, anchor);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public void setShouldShowCameraSwitchControl(boolean z) {
            native_setShouldShowCameraSwitchControl(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public void setShouldShowTorchControl(boolean z) {
            native_setShouldShowTorchControl(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public void setShouldShowZoomControl(boolean z) {
            native_setShouldShowZoomControl(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public void setTorchControlPosition(Anchor anchor) {
            native_setTorchControlPosition(this.nativeRef, anchor);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public void setZoomControlPosition(Anchor anchor) {
            native_setZoomControlPosition(this.nativeRef, anchor);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArView
        public void updateFromJson(NativeJsonValue nativeJsonValue) {
            native_updateFromJson(this.nativeRef, nativeJsonValue);
        }
    }

    public static native NativeBarcodeArView create();

    public static native NativeBarcodeArView fromJson(NativeJsonValue nativeJsonValue);

    public abstract Anchor getCameraSwitchControlPosition();

    public abstract boolean getShouldShowCameraSwitchControl();

    public abstract boolean getShouldShowTorchControl();

    public abstract boolean getShouldShowZoomControl();

    public abstract Anchor getTorchControlPosition();

    public abstract Anchor getZoomControlPosition();

    public abstract void setCameraSwitchControlPosition(Anchor anchor);

    public abstract void setShouldShowCameraSwitchControl(boolean z);

    public abstract void setShouldShowTorchControl(boolean z);

    public abstract void setShouldShowZoomControl(boolean z);

    public abstract void setTorchControlPosition(Anchor anchor);

    public abstract void setZoomControlPosition(Anchor anchor);

    public abstract void updateFromJson(NativeJsonValue nativeJsonValue);
}
