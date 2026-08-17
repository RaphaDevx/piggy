package com.scandit.internal.sdk.bar;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class Callable {

    public static final class CppProxy extends Callable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean a = new AtomicBoolean(false);
        private final long nativeRef;

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);

        private native void native_run(long j);

        @Override // com.scandit.internal.sdk.bar.Callable
        public void run() {
            native_run(this.nativeRef);
        }
    }

    public abstract void run();
}
