package com.scandit.datacapture.barcode.spark.internal.module.ui;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSparkScanOverlay {

    public static final class CppProxy extends NativeSparkScanOverlay {
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

        private native void native_enableSingleScanMode(long j, boolean z);

        private native NativeBrush native_getBrushForRecognizedBarcodes(long j);

        private native boolean native_getShouldDrawViewFinder(long j);

        private native boolean native_getShouldShowScanAreaGuides(long j);

        private native NativeSparkScanOverlayStyle native_getStyle(long j);

        private native void native_hideMiniPreview(long j);

        private native void native_onErrorFeedbackEmitted(long j, boolean z);

        private native void native_setBrushForErrorBarcodes(long j, NativeBrush nativeBrush);

        private native void native_setBrushForLocalizedOnlyBarcodes(long j, NativeBrush nativeBrush);

        private native void native_setBrushForRecognizedBarcodes(long j, NativeBrush nativeBrush);

        private native void native_setShouldDrawViewFinder(long j, boolean z);

        private native void native_setShouldShowScanAreaGuides(long j, boolean z);

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public NativeDataCaptureOverlay asDataCaptureOverlay() {
            return native_asDataCaptureOverlay(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public void enableSingleScanMode(boolean z) {
            native_enableSingleScanMode(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public NativeBrush getBrushForRecognizedBarcodes() {
            return native_getBrushForRecognizedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public boolean getShouldDrawViewFinder() {
            return native_getShouldDrawViewFinder(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public boolean getShouldShowScanAreaGuides() {
            return native_getShouldShowScanAreaGuides(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public NativeSparkScanOverlayStyle getStyle() {
            return native_getStyle(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public void hideMiniPreview() {
            native_hideMiniPreview(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public void onErrorFeedbackEmitted(boolean z) {
            native_onErrorFeedbackEmitted(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public void setBrushForErrorBarcodes(NativeBrush nativeBrush) {
            native_setBrushForErrorBarcodes(this.nativeRef, nativeBrush);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public void setBrushForLocalizedOnlyBarcodes(NativeBrush nativeBrush) {
            native_setBrushForLocalizedOnlyBarcodes(this.nativeRef, nativeBrush);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public void setBrushForRecognizedBarcodes(NativeBrush nativeBrush) {
            native_setBrushForRecognizedBarcodes(this.nativeRef, nativeBrush);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public void setShouldDrawViewFinder(boolean z) {
            native_setShouldDrawViewFinder(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlay
        public void setShouldShowScanAreaGuides(boolean z) {
            native_setShouldShowScanAreaGuides(this.nativeRef, z);
        }
    }

    public static native NativeSparkScanOverlay create(NativeSparkScan nativeSparkScan, NativeSparkScanOverlayStyle nativeSparkScanOverlayStyle);

    public static native NativeSparkScanOverlay createWithDefaultStyle(NativeSparkScan nativeSparkScan);

    public static native NativeBrush defaultBrushForLocalizedOnlyBarcodes();

    public static native NativeBrush defaultBrushForRecognizedBarcodes();

    public static native NativeBrush getDefaultBrushForStyle(NativeSparkScanOverlayStyle nativeSparkScanOverlayStyle);

    public static native NativeBrush getDefaultErrorBrushForStyle(NativeSparkScanOverlayStyle nativeSparkScanOverlayStyle);

    public abstract NativeDataCaptureOverlay asDataCaptureOverlay();

    public abstract void enableSingleScanMode(boolean z);

    public abstract NativeBrush getBrushForRecognizedBarcodes();

    public abstract boolean getShouldDrawViewFinder();

    public abstract boolean getShouldShowScanAreaGuides();

    public abstract NativeSparkScanOverlayStyle getStyle();

    public abstract void hideMiniPreview();

    public abstract void onErrorFeedbackEmitted(boolean z);

    public abstract void setBrushForErrorBarcodes(NativeBrush nativeBrush);

    public abstract void setBrushForLocalizedOnlyBarcodes(NativeBrush nativeBrush);

    public abstract void setBrushForRecognizedBarcodes(NativeBrush nativeBrush);

    public abstract void setShouldDrawViewFinder(boolean z);

    public abstract void setShouldShowScanAreaGuides(boolean z);
}
