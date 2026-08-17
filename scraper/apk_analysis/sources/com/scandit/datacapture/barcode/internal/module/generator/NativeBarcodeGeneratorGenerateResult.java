package com.scandit.datacapture.barcode.internal.module.generator;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeGeneratorGenerateResult {

    public static final class CppProxy extends NativeBarcodeGeneratorGenerateResult {
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

        private native String native_error(long j);

        private native boolean native_ok(long j);

        private native NativeBarcodeGeneratorImage native_value(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.generator.NativeBarcodeGeneratorGenerateResult
        public String error() {
            return native_error(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.generator.NativeBarcodeGeneratorGenerateResult
        public boolean ok() {
            return native_ok(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.generator.NativeBarcodeGeneratorGenerateResult
        public NativeBarcodeGeneratorImage value() {
            return native_value(this.nativeRef);
        }
    }

    public abstract String error();

    public abstract boolean ok();

    public abstract NativeBarcodeGeneratorImage value();
}
