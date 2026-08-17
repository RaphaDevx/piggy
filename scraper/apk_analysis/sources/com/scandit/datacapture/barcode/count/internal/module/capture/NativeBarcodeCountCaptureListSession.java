package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountCaptureListSession {

    public static final class CppProxy extends NativeBarcodeCountCaptureListSession {
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

        private native ArrayList<NativeTrackedBarcode> native_getAcceptedBarcodesList(long j);

        private native ArrayList<NativeBarcode> native_getAdditionalBarcodes(long j);

        private native ArrayList<NativeTrackedBarcode> native_getCorrectBarcodes(long j);

        private native ArrayList<NativeTargetBarcode> native_getMissingBarcodes(long j);

        private native ArrayList<NativeTrackedBarcode> native_getRejectedBarcodesList(long j);

        private native ArrayList<NativeTrackedBarcode> native_getWrongBarcodes(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListSession
        public ArrayList<NativeTrackedBarcode> getAcceptedBarcodesList() {
            return native_getAcceptedBarcodesList(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListSession
        public ArrayList<NativeBarcode> getAdditionalBarcodes() {
            return native_getAdditionalBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListSession
        public ArrayList<NativeTrackedBarcode> getCorrectBarcodes() {
            return native_getCorrectBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListSession
        public ArrayList<NativeTargetBarcode> getMissingBarcodes() {
            return native_getMissingBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListSession
        public ArrayList<NativeTrackedBarcode> getRejectedBarcodesList() {
            return native_getRejectedBarcodesList(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListSession
        public ArrayList<NativeTrackedBarcode> getWrongBarcodes() {
            return native_getWrongBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureListSession
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract ArrayList<NativeTrackedBarcode> getAcceptedBarcodesList();

    public abstract ArrayList<NativeBarcode> getAdditionalBarcodes();

    public abstract ArrayList<NativeTrackedBarcode> getCorrectBarcodes();

    public abstract ArrayList<NativeTargetBarcode> getMissingBarcodes();

    public abstract ArrayList<NativeTrackedBarcode> getRejectedBarcodesList();

    public abstract ArrayList<NativeTrackedBarcode> getWrongBarcodes();

    public abstract String toJson();
}
