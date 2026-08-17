package com.scandit.datacapture.barcode.internal.module.capture;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeLocalizedOnlyBarcode;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCaptureSession {

    public static final class CppProxy extends NativeBarcodeCaptureSession {
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

        private native void native_clear(long j);

        private native long native_getFrameSeqIdAndroid(long j);

        private native ArrayList<NativeLocalizedOnlyBarcode> native_getNewlyLocalizedBarcodes(long j);

        private native NativeBarcode native_getNewlyRecognizedBarcode(long j);

        private native ArrayList<NativeBarcode> native_getNewlyRecognizedBarcodes(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSession
        public void clear() {
            native_clear(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSession
        public long getFrameSeqIdAndroid() {
            return native_getFrameSeqIdAndroid(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSession
        public ArrayList<NativeLocalizedOnlyBarcode> getNewlyLocalizedBarcodes() {
            return native_getNewlyLocalizedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSession
        public NativeBarcode getNewlyRecognizedBarcode() {
            return native_getNewlyRecognizedBarcode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSession
        public ArrayList<NativeBarcode> getNewlyRecognizedBarcodes() {
            return native_getNewlyRecognizedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSession
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract void clear();

    public abstract long getFrameSeqIdAndroid();

    public abstract ArrayList<NativeLocalizedOnlyBarcode> getNewlyLocalizedBarcodes();

    public abstract NativeBarcode getNewlyRecognizedBarcode();

    public abstract ArrayList<NativeBarcode> getNewlyRecognizedBarcodes();

    public abstract String toJson();
}
