package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountStatus;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountStatusItem {

    public static final class CppProxy extends NativeBarcodeCountStatusItem {
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

        private native BarcodeCountStatus native_getStatus(long j);

        private native NativeTrackedBarcode native_getTrackedBarcode(long j);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusItem
        public BarcodeCountStatus getStatus() {
            return native_getStatus(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusItem
        public NativeTrackedBarcode getTrackedBarcode() {
            return native_getTrackedBarcode(this.nativeRef);
        }
    }

    public static native NativeBarcodeCountStatusItem create(NativeTrackedBarcode nativeTrackedBarcode, BarcodeCountStatus barcodeCountStatus);

    public abstract BarcodeCountStatus getStatus();

    public abstract NativeTrackedBarcode getTrackedBarcode();
}
