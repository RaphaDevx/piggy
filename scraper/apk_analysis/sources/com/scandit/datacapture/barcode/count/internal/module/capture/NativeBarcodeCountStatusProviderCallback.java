package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountStatusProviderCallback {

    public static final class CppProxy extends NativeBarcodeCountStatusProviderCallback {
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

        private native void native_onStatusReady(long j, NativeBarcodeCountStatusResult nativeBarcodeCountStatusResult);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusProviderCallback
        public void onStatusReady(NativeBarcodeCountStatusResult nativeBarcodeCountStatusResult) {
            native_onStatusReady(this.nativeRef, nativeBarcodeCountStatusResult);
        }
    }

    public abstract void onStatusReady(NativeBarcodeCountStatusResult nativeBarcodeCountStatusResult);
}
