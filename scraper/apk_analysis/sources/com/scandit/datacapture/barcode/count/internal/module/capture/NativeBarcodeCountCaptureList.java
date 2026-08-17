package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.internal.module.common.NativeBarcodeDataTransformer;
import com.snapchat.djinni.NativeObjectManager;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountCaptureList {

    public static final class CppProxy extends NativeBarcodeCountCaptureList {
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

        private native void native_addListenerAsync(long j, NativeBarcodeCountCaptureListListener nativeBarcodeCountCaptureListListener);

        private native NativeBarcodeCountCaptureListSession native_getSession(long j);

        private native int native_getTargetBarcodesQuantity(long j);

        private native void native_setBarcodeCountTransformer(long j, NativeBarcodeDataTransformer nativeBarcodeDataTransformer);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureList
        public void addListenerAsync(NativeBarcodeCountCaptureListListener nativeBarcodeCountCaptureListListener) {
            native_addListenerAsync(this.nativeRef, nativeBarcodeCountCaptureListListener);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureList
        public NativeBarcodeCountCaptureListSession getSession() {
            return native_getSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureList
        public int getTargetBarcodesQuantity() {
            return native_getTargetBarcodesQuantity(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountCaptureList
        public void setBarcodeCountTransformer(NativeBarcodeDataTransformer nativeBarcodeDataTransformer) {
            native_setBarcodeCountTransformer(this.nativeRef, nativeBarcodeDataTransformer);
        }
    }

    public static native NativeBarcodeCountCaptureList createForAndroid(HashSet<NativeTargetBarcode> hashSet);

    public abstract void addListenerAsync(NativeBarcodeCountCaptureListListener nativeBarcodeCountCaptureListListener);

    public abstract NativeBarcodeCountCaptureListSession getSession();

    public abstract int getTargetBarcodesQuantity();

    public abstract void setBarcodeCountTransformer(NativeBarcodeDataTransformer nativeBarcodeDataTransformer);
}
