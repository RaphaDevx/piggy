package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickScanningSession {

    public static final class CppProxy extends NativeBarcodePickScanningSession {
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

        private native HashSet<String> native_getPickedObjects(long j);

        private native HashSet<String> native_getScannedObjects(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickScanningSession
        public HashSet<String> getPickedObjects() {
            return native_getPickedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickScanningSession
        public HashSet<String> getScannedObjects() {
            return native_getScannedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickScanningSession
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract HashSet<String> getPickedObjects();

    public abstract HashSet<String> getScannedObjects();

    public abstract String toJson();
}
