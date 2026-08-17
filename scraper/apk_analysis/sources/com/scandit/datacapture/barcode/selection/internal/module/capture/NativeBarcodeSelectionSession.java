package com.scandit.datacapture.barcode.selection.internal.module.capture;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeSelectionSession {

    public static final class CppProxy extends NativeBarcodeSelectionSession {
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

        private native int native_getCount(long j, NativeBarcode nativeBarcode);

        private native long native_getFrameSeqIdAndroid(long j);

        private native int native_getLastProcessedFrameId(long j);

        private native ArrayList<NativeBarcode> native_getNewlySelectedBarcodes(long j);

        private native ArrayList<NativeBarcode> native_getNewlyUnselectedBarcodes(long j);

        private native ArrayList<NativeBarcode> native_getSelectedBarcodes(long j);

        private native void native_reset(long j);

        private native void native_selectAllUnselectedBarcodes(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession
        public int getCount(NativeBarcode nativeBarcode) {
            return native_getCount(this.nativeRef, nativeBarcode);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession
        public long getFrameSeqIdAndroid() {
            return native_getFrameSeqIdAndroid(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession
        public int getLastProcessedFrameId() {
            return native_getLastProcessedFrameId(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession
        public ArrayList<NativeBarcode> getNewlySelectedBarcodes() {
            return native_getNewlySelectedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession
        public ArrayList<NativeBarcode> getNewlyUnselectedBarcodes() {
            return native_getNewlyUnselectedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession
        public ArrayList<NativeBarcode> getSelectedBarcodes() {
            return native_getSelectedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession
        public void reset() {
            native_reset(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession
        public void selectAllUnselectedBarcodes() {
            native_selectAllUnselectedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract int getCount(NativeBarcode nativeBarcode);

    public abstract long getFrameSeqIdAndroid();

    public abstract int getLastProcessedFrameId();

    public abstract ArrayList<NativeBarcode> getNewlySelectedBarcodes();

    public abstract ArrayList<NativeBarcode> getNewlyUnselectedBarcodes();

    public abstract ArrayList<NativeBarcode> getSelectedBarcodes();

    public abstract void reset();

    public abstract void selectAllUnselectedBarcodes();

    public abstract String toJson();
}
