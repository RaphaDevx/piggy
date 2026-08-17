package com.scandit.datacapture.barcode.internal.module.find.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeFindItem {

    public static final class CppProxy extends NativeBarcodeFindItem {
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

        private native int native_getCount(long j);

        private native byte[] native_getFindableData(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindItem
        public int getCount() {
            return native_getCount(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindItem
        public byte[] getFindableData() {
            return native_getFindableData(this.nativeRef);
        }
    }

    public static native NativeBarcodeFindItem create(String str);

    public static native NativeBarcodeFindItem createWithRaw(byte[] bArr);

    public abstract int getCount();

    public abstract byte[] getFindableData();
}
