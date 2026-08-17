package com.scandit.datacapture.core.internal.module;

import com.snapchat.djinni.NativeObjectManager;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeByteArrayTest {

    public static final class CppProxy extends NativeByteArrayTest {
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

    public static native int getArraySize(byte[] bArr);

    public static native byte getElementAtIndex(byte[] bArr, int i);

    public static native void multiply(ByteBuffer byteBuffer, int i);
}
