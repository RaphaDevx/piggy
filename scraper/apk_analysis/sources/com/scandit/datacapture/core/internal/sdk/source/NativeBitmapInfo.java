package com.scandit.datacapture.core.internal.sdk.source;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBitmapInfo {

    public static final class CppProxy extends NativeBitmapInfo {
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

    public static native NativeBitmapInfo create(NativeBitmapConfig nativeBitmapConfig, NativeByteOrder nativeByteOrder, byte[] bArr, int i, int i2, int i3, int i4);
}
