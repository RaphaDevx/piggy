package com.scandit.datacapture.barcode.internal.sdk.data;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeArucoMarker {

    public static final class CppProxy extends NativeArucoMarker {
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

        private native byte[] native_getBits(long j);

        private native int native_getSize(long j);

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeArucoMarker
        public byte[] getBits() {
            return native_getBits(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeArucoMarker
        public int getSize() {
            return native_getSize(this.nativeRef);
        }
    }

    public static native NativeArucoMarker create(int i, byte[] bArr);

    public abstract byte[] getBits();

    public abstract int getSize();
}
