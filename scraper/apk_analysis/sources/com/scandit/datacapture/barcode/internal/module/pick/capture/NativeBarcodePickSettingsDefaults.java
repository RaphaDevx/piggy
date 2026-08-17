package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickSettingsDefaults {

    public static final class CppProxy extends NativeBarcodePickSettingsDefaults {
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

    public static native boolean enableCaching();

    public static native boolean hapticsEnabled();

    public static native boolean soundEnabled();
}
