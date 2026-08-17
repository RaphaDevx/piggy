package com.scandit.datacapture.core.internal.sdk.common.geometry;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeAndroidImageBufferDecoder {

    public static final class CppProxy extends NativeAndroidImageBufferDecoder {
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

    public static native NativeImageBuffer toARGB32ImageBuffer(byte[] bArr, int i, int i2, int i3);

    public static native NativeImageBuffer toBGRA32ImageBuffer(byte[] bArr, int i, int i2, int i3);

    public static native NativeImageBuffer toNV21ImageBuffer(byte[] bArr, int i, int i2);

    public static native NativeImageBuffer toRGBA32ImageBuffer(byte[] bArr, int i, int i2, int i3);
}
