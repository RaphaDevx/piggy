package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountStatusResultError {

    public static final class CppProxy extends NativeBarcodeCountStatusResultError {
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

    public static native NativeBarcodeCountStatusResult create(ArrayList<NativeBarcodeCountStatusItem> arrayList, String str, String str2);
}
