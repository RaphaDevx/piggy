package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountNotInListActionSettings {

    public static final class CppProxy extends NativeBarcodeCountNotInListActionSettings {
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

        private native String native_getAcceptButtonAccessibilityLabel(long j);

        private native String native_getAcceptButtonText(long j);

        private native String native_getAcceptedHintText(long j);

        private native String native_getCancelButtonAccessibilityLabel(long j);

        private native String native_getCancelButtonText(long j);

        private native String native_getRejectButtonAccessibilityLabel(long j);

        private native String native_getRejectButtonText(long j);

        private native String native_getRejectedHintText(long j);

        private native boolean native_isEnabled(long j);

        private native void native_setAcceptButtonText(long j, String str);

        private native void native_setAcceptedHintText(long j, String str);

        private native void native_setCancelButtonText(long j, String str);

        private native void native_setEnabled(long j, boolean z);

        private native void native_setRejectButtonText(long j, String str);

        private native void native_setRejectedHintText(long j, String str);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public String getAcceptButtonAccessibilityLabel() {
            return native_getAcceptButtonAccessibilityLabel(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public String getAcceptButtonText() {
            return native_getAcceptButtonText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public String getAcceptedHintText() {
            return native_getAcceptedHintText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public String getCancelButtonAccessibilityLabel() {
            return native_getCancelButtonAccessibilityLabel(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public String getCancelButtonText() {
            return native_getCancelButtonText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public String getRejectButtonAccessibilityLabel() {
            return native_getRejectButtonAccessibilityLabel(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public String getRejectButtonText() {
            return native_getRejectButtonText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public String getRejectedHintText() {
            return native_getRejectedHintText(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public boolean isEnabled() {
            return native_isEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public void setAcceptButtonText(String str) {
            native_setAcceptButtonText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public void setAcceptedHintText(String str) {
            native_setAcceptedHintText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public void setCancelButtonText(String str) {
            native_setCancelButtonText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public void setEnabled(boolean z) {
            native_setEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public void setRejectButtonText(String str) {
            native_setRejectButtonText(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListActionSettings
        public void setRejectedHintText(String str) {
            native_setRejectedHintText(this.nativeRef, str);
        }
    }

    public static native NativeBarcodeCountNotInListActionSettings create();

    public abstract String getAcceptButtonAccessibilityLabel();

    public abstract String getAcceptButtonText();

    public abstract String getAcceptedHintText();

    public abstract String getCancelButtonAccessibilityLabel();

    public abstract String getCancelButtonText();

    public abstract String getRejectButtonAccessibilityLabel();

    public abstract String getRejectButtonText();

    public abstract String getRejectedHintText();

    public abstract boolean isEnabled();

    public abstract void setAcceptButtonText(String str);

    public abstract void setAcceptedHintText(String str);

    public abstract void setCancelButtonText(String str);

    public abstract void setEnabled(boolean z);

    public abstract void setRejectButtonText(String str);

    public abstract void setRejectedHintText(String str);
}
