package com.scandit.datacapture.core.internal.module;

import com.snapchat.djinni.NativeObjectManager;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeAllocation {

    public static final class CppProxy extends NativeAllocation {
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

        private native ByteBuffer native_getBuffer(long j);

        private native void native_release(long j);

        @Override // com.scandit.datacapture.core.internal.module.NativeAllocation
        public ByteBuffer getBuffer() {
            return native_getBuffer(this.nativeRef);
        }

        @Override // com.scandit.datacapture.core.internal.module.NativeAllocation
        public void release() {
            native_release(this.nativeRef);
        }
    }

    public static native NativeAllocation allocate(int i);

    public abstract ByteBuffer getBuffer();

    public abstract void release();
}
