package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeProductProviderCallbackItem {

    public static final class CppProxy extends NativeProductProviderCallbackItem {
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

        private native String native_getToBeTransformedData(long j);

        private native String native_getTransformationResult(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProviderCallbackItem
        public String getToBeTransformedData() {
            return native_getToBeTransformedData(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeProductProviderCallbackItem
        public String getTransformationResult() {
            return native_getTransformationResult(this.nativeRef);
        }
    }

    public static native NativeProductProviderCallbackItem create(String str, String str2);

    public abstract String getToBeTransformedData();

    public abstract String getTransformationResult();
}
