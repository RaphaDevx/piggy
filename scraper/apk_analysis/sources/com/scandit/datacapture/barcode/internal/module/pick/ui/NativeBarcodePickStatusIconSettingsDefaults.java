package com.scandit.datacapture.barcode.internal.module.pick.ui;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickStatusIconSettingsDefaults {

    public static final class CppProxy extends NativeBarcodePickStatusIconSettingsDefaults {
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

    public static native int maxSize();

    public static native int minSize();

    public static native float sizeToHighlightSizeRatio();
}
