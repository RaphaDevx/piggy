package com.scandit.datacapture.barcode.internal.module.pick.ui;

import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickViewHighlightStyleCustomView {

    public static final class CppProxy extends NativeBarcodePickViewHighlightStyleCustomView {
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

        private native NativeBarcodePickViewHighlightStyle native_asHighlightStyle(long j);

        private native boolean native_getFitViewsToBarcode(long j);

        private native int native_getMinimumHighlightHeight(long j);

        private native int native_getMinimumHighlightWidth(long j);

        private native NativeBarcodePickStatusIconSettings native_getStatusIconSettings(long j);

        private native void native_setFitViewsToBarcode(long j, boolean z);

        private native void native_setMinimumHighlightHeight(long j, int i);

        private native void native_setMinimumHighlightWidth(long j, int i);

        private native void native_setStatusIconSettings(long j, NativeBarcodePickStatusIconSettings nativeBarcodePickStatusIconSettings);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public NativeBarcodePickViewHighlightStyle asHighlightStyle() {
            return native_asHighlightStyle(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public boolean getFitViewsToBarcode() {
            return native_getFitViewsToBarcode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public int getMinimumHighlightHeight() {
            return native_getMinimumHighlightHeight(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public int getMinimumHighlightWidth() {
            return native_getMinimumHighlightWidth(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public NativeBarcodePickStatusIconSettings getStatusIconSettings() {
            return native_getStatusIconSettings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public void setFitViewsToBarcode(boolean z) {
            native_setFitViewsToBarcode(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public void setMinimumHighlightHeight(int i) {
            native_setMinimumHighlightHeight(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public void setMinimumHighlightWidth(int i) {
            native_setMinimumHighlightWidth(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public void setStatusIconSettings(NativeBarcodePickStatusIconSettings nativeBarcodePickStatusIconSettings) {
            native_setStatusIconSettings(this.nativeRef, nativeBarcodePickStatusIconSettings);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleCustomView
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public static native NativeBarcodePickViewHighlightStyleCustomView create();

    public abstract NativeBarcodePickViewHighlightStyle asHighlightStyle();

    public abstract boolean getFitViewsToBarcode();

    public abstract int getMinimumHighlightHeight();

    public abstract int getMinimumHighlightWidth();

    public abstract NativeBarcodePickStatusIconSettings getStatusIconSettings();

    public abstract void setFitViewsToBarcode(boolean z);

    public abstract void setMinimumHighlightHeight(int i);

    public abstract void setMinimumHighlightWidth(int i);

    public abstract void setStatusIconSettings(NativeBarcodePickStatusIconSettings nativeBarcodePickStatusIconSettings);

    public abstract String toJson();
}
