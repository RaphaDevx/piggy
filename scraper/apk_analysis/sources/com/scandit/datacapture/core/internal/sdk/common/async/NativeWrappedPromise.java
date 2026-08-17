package com.scandit.datacapture.core.internal.sdk.common.async;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeWrappedPromise {

    public static final class CppProxy extends NativeWrappedPromise {
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

        private native boolean native_hasValue(long j);

        private native void native_setDone(long j);

        private native void native_setError(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise
        public boolean hasValue() {
            return native_hasValue(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise
        public void setDone() {
            native_setDone(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.common.async.NativeWrappedPromise
        public void setError() {
            native_setError(this.nativeRef);
        }
    }

    public abstract boolean hasValue();

    public abstract void setDone();

    public abstract void setError();
}
