package com.scandit.datacapture.barcode.internal.module.pick.ui;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickStatusIconSettings {

    public static final class CppProxy extends NativeBarcodePickStatusIconSettings {
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

        private native int native_getMaxSize(long j);

        private native int native_getMinSize(long j);

        private native float native_getSizeToHighlightSizeRatio(long j);

        private native void native_setMaxSize(long j, int i);

        private native void native_setMinSize(long j, int i);

        private native void native_setSizeToHighlightSizeRatio(long j, float f);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickStatusIconSettings
        public int getMaxSize() {
            return native_getMaxSize(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickStatusIconSettings
        public int getMinSize() {
            return native_getMinSize(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickStatusIconSettings
        public float getSizeToHighlightSizeRatio() {
            return native_getSizeToHighlightSizeRatio(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickStatusIconSettings
        public void setMaxSize(int i) {
            native_setMaxSize(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickStatusIconSettings
        public void setMinSize(int i) {
            native_setMinSize(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickStatusIconSettings
        public void setSizeToHighlightSizeRatio(float f) {
            native_setSizeToHighlightSizeRatio(this.nativeRef, f);
        }
    }

    public static native NativeBarcodePickStatusIconSettings create();

    public abstract int getMaxSize();

    public abstract int getMinSize();

    public abstract float getSizeToHighlightSizeRatio();

    public abstract void setMaxSize(int i);

    public abstract void setMinSize(int i);

    public abstract void setSizeToHighlightSizeRatio(float f);
}
