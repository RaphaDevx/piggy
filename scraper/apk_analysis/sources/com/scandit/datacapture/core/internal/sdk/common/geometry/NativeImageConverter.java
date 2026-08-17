package com.scandit.datacapture.core.internal.sdk.common.geometry;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeImageConverter {

    public static final class CppProxy extends NativeImageConverter {
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

    public static native NativeImageConverterInstance getInstance();

    public static native void setInstance(NativeImageConverterInstance nativeImageConverterInstance);
}
