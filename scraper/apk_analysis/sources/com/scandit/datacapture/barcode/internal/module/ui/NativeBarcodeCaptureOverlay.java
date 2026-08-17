package com.scandit.datacapture.barcode.internal.module.ui;

import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture;
import com.scandit.datacapture.barcode.ui.overlay.NativeBarcodeCaptureOverlayStyle;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.scandit.datacapture.core.internal.sdk.ui.viewfinder.NativeViewfinder;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCaptureOverlay {

    public static final class CppProxy extends NativeBarcodeCaptureOverlay {
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

        private native NativeDataCaptureOverlay native_asDataCaptureOverlay(long j);

        private native NativeBrush native_getBrushForRecognizedBarcodes(long j);

        private native boolean native_getShouldShowScanAreaGuides(long j);

        private native NativeBarcodeCaptureOverlayStyle native_getStyle(long j);

        private native NativeViewfinder native_getViewfinder(long j);

        private native void native_setBrushForLocalizedOnlyBarcodes(long j, NativeBrush nativeBrush);

        private native void native_setBrushForRecognizedBarcodes(long j, NativeBrush nativeBrush);

        private native void native_setShouldDrawLocalizedOnlyBarcodes(long j, boolean z);

        private native void native_setShouldShowScanAreaGuides(long j, boolean z);

        private native void native_setViewfinder(long j, NativeViewfinder nativeViewfinder);

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public NativeDataCaptureOverlay asDataCaptureOverlay() {
            return native_asDataCaptureOverlay(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public NativeBrush getBrushForRecognizedBarcodes() {
            return native_getBrushForRecognizedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public boolean getShouldShowScanAreaGuides() {
            return native_getShouldShowScanAreaGuides(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public NativeBarcodeCaptureOverlayStyle getStyle() {
            return native_getStyle(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public NativeViewfinder getViewfinder() {
            return native_getViewfinder(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public void setBrushForLocalizedOnlyBarcodes(NativeBrush nativeBrush) {
            native_setBrushForLocalizedOnlyBarcodes(this.nativeRef, nativeBrush);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public void setBrushForRecognizedBarcodes(NativeBrush nativeBrush) {
            native_setBrushForRecognizedBarcodes(this.nativeRef, nativeBrush);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public void setShouldDrawLocalizedOnlyBarcodes(boolean z) {
            native_setShouldDrawLocalizedOnlyBarcodes(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public void setShouldShowScanAreaGuides(boolean z) {
            native_setShouldShowScanAreaGuides(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay
        public void setViewfinder(NativeViewfinder nativeViewfinder) {
            native_setViewfinder(this.nativeRef, nativeViewfinder);
        }
    }

    public static native NativeBarcodeCaptureOverlay create(NativeBarcodeCapture nativeBarcodeCapture, NativeBarcodeCaptureOverlayStyle nativeBarcodeCaptureOverlayStyle);

    public static native NativeBarcodeCaptureOverlay createWithDefaultStyle(NativeBarcodeCapture nativeBarcodeCapture);

    public static native NativeBrush defaultBrushForLocalizedOnlyBarcodes();

    public static native NativeBrush defaultBrushForRecognizedBarcodes();

    public static native NativeBrush getDefaultBrushForStyle(NativeBarcodeCaptureOverlayStyle nativeBarcodeCaptureOverlayStyle);

    public abstract NativeDataCaptureOverlay asDataCaptureOverlay();

    public abstract NativeBrush getBrushForRecognizedBarcodes();

    public abstract boolean getShouldShowScanAreaGuides();

    public abstract NativeBarcodeCaptureOverlayStyle getStyle();

    public abstract NativeViewfinder getViewfinder();

    public abstract void setBrushForLocalizedOnlyBarcodes(NativeBrush nativeBrush);

    public abstract void setBrushForRecognizedBarcodes(NativeBrush nativeBrush);

    public abstract void setShouldDrawLocalizedOnlyBarcodes(boolean z);

    public abstract void setShouldShowScanAreaGuides(boolean z);

    public abstract void setViewfinder(NativeViewfinder nativeViewfinder);
}
