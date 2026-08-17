package com.scandit.datacapture.barcode.internal.module.ar.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeArSession {

    public static final class CppProxy extends NativeBarcodeArSession {
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

        private native ArrayList<NativeTrackedBarcode> native_getAddedTrackedBarcodes(long j);

        private native ArrayList<Integer> native_getRemovedTrackedBarcodes(long j);

        private native HashMap<Integer, NativeTrackedBarcode> native_getTrackedBarcodes(long j);

        private native void native_reset(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSession
        public ArrayList<NativeTrackedBarcode> getAddedTrackedBarcodes() {
            return native_getAddedTrackedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSession
        public ArrayList<Integer> getRemovedTrackedBarcodes() {
            return native_getRemovedTrackedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSession
        public HashMap<Integer, NativeTrackedBarcode> getTrackedBarcodes() {
            return native_getTrackedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSession
        public void reset() {
            native_reset(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSession
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract ArrayList<NativeTrackedBarcode> getAddedTrackedBarcodes();

    public abstract ArrayList<Integer> getRemovedTrackedBarcodes();

    public abstract HashMap<Integer, NativeTrackedBarcode> getTrackedBarcodes();

    public abstract void reset();

    public abstract String toJson();
}
