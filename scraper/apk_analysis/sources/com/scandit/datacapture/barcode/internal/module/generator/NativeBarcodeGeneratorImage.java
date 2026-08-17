package com.scandit.datacapture.barcode.internal.module.generator;

import com.scandit.datacapture.core.internal.sdk.common.geometry.NativeImageBuffer;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeGeneratorImage {

    public static final class CppProxy extends NativeBarcodeGeneratorImage {
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

        private native NativeImageBuffer native_getImageBuffer(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.generator.NativeBarcodeGeneratorImage
        public NativeImageBuffer getImageBuffer() {
            return native_getImageBuffer(this.nativeRef);
        }
    }

    public abstract NativeImageBuffer getImageBuffer();
}
