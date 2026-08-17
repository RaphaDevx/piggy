package com.scandit.datacapture.barcode.internal.module.capture;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeEan13UpcaClassification {

    public static final class CppProxy extends NativeEan13UpcaClassification {
        private final long nativeRef;

        private CppProxy(long j) {
            new AtomicBoolean(false);
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);
    }

    public static native boolean isEan13(NativeBarcode nativeBarcode);

    public static native boolean isUpca(NativeBarcode nativeBarcode);
}
