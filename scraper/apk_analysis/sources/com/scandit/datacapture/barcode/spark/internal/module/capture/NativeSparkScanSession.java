package com.scandit.datacapture.barcode.spark.internal.module.capture;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSparkScanSession {

    public static final class CppProxy extends NativeSparkScanSession {
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

        private native NativeBarcode native_getNewlyRecognizedBarcode(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSession
        public void clear() {
            native_clear(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSession
        public long getFrameSeqIdAndroid() {
            return native_getFrameSeqIdAndroid(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSession
        public NativeBarcode getNewlyRecognizedBarcode() {
            return native_getNewlyRecognizedBarcode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSession
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract void clear();

    public abstract long getFrameSeqIdAndroid();

    public abstract NativeBarcode getNewlyRecognizedBarcode();

    public abstract String toJson();
}
