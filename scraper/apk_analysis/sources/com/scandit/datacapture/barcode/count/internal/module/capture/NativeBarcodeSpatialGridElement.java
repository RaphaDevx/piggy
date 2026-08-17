package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeSpatialGridElement {

    public static final class CppProxy extends NativeBarcodeSpatialGridElement {
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

        private native NativeBarcode native_mainBarcode(long j);

        private native NativeBarcode native_subBarcode(long j);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridElement
        public NativeBarcode mainBarcode() {
            return native_mainBarcode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeSpatialGridElement
        public NativeBarcode subBarcode() {
            return native_subBarcode(this.nativeRef);
        }
    }

    public abstract NativeBarcode mainBarcode();

    public abstract NativeBarcode subBarcode();
}
