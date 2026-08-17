package com.scandit.datacapture.barcode.internal.module.ar.ui;

import com.scandit.datacapture.core.common.geometry.Anchor;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeArViewDefaults {

    public static final class CppProxy extends NativeBarcodeArViewDefaults {
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

    public static native Anchor defaultCameraSwitchControlPosition();

    public static native boolean defaultShouldShowCameraSwitchControl();

    public static native boolean defaultShouldShowTorchControl();

    public static native boolean defaultShouldShowZoomControl();

    public static native Anchor defaultTorchControlPosition();

    public static native Anchor defaultZoomControlPosition();
}
