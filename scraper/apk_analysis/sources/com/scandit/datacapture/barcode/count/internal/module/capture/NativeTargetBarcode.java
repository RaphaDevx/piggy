package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeTargetBarcode {

    public static final class CppProxy extends NativeTargetBarcode {
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

        private native String native_getData(long j);

        private native int native_getQuantity(long j);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeTargetBarcode
        public String getData() {
            return native_getData(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeTargetBarcode
        public int getQuantity() {
            return native_getQuantity(this.nativeRef);
        }
    }

    public static native NativeTargetBarcode create(String str, int i);

    public abstract String getData();

    public abstract int getQuantity();
}
