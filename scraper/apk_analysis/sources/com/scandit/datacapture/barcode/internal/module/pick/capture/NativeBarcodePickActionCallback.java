package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickActionCallback {

    public static final class CppProxy extends NativeBarcodePickActionCallback {
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

        private native void native_onResponse(long j, String str, boolean z);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickActionCallback
        public void onResponse(String str, boolean z) {
            native_onResponse(this.nativeRef, str, z);
        }
    }

    public abstract void onResponse(String str, boolean z);
}
