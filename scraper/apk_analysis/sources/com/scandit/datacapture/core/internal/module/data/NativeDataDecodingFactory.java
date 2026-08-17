package com.scandit.datacapture.core.internal.module.data;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeDataDecodingFactory {

    public static final class CppProxy extends NativeDataDecodingFactory {
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

    public static native NativeDataDecoding getDataDecoding();

    public static native void setDataDecoding(NativeDataDecoding nativeDataDecoding);
}
