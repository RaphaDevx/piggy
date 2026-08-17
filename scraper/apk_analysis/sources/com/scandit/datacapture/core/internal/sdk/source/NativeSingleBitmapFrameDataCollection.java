package com.scandit.datacapture.core.internal.sdk.source;

import com.scandit.datacapture.core.internal.sdk.data.NativeFrameData;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSingleBitmapFrameDataCollection {

    public static final class CppProxy extends NativeSingleBitmapFrameDataCollection {
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

        private native NativeFrameData native_get(long j, int i);

        private native int native_getSize(long j);

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeSingleBitmapFrameDataCollection
        public NativeFrameData get(int i) {
            return native_get(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.core.internal.sdk.source.NativeSingleBitmapFrameDataCollection
        public int getSize() {
            return native_getSize(this.nativeRef);
        }
    }

    public static native NativeSingleBitmapFrameDataCollection create(NativeBitmapInfo nativeBitmapInfo);

    public abstract NativeFrameData get(int i);

    public abstract int getSize();
}
