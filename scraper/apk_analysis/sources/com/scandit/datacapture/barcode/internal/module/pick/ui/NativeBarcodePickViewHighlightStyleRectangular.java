package com.scandit.datacapture.barcode.internal.module.pick.ui;

import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickState;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickViewHighlightStyleRectangular {

    public static final class CppProxy extends NativeBarcodePickViewHighlightStyleRectangular {
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

        private native NativeBrush native_brushForState(long j, NativeBarcodePickState nativeBarcodePickState);

        private native int native_getMinimumHighlightHeight(long j);

        private native int native_getMinimumHighlightWidth(long j);

        private native NativeBrush native_selectedBrushForState(long j, NativeBarcodePickState nativeBarcodePickState);

        private native void native_setBrushForState(long j, NativeBrush nativeBrush, NativeBarcodePickState nativeBarcodePickState);

        private native void native_setMinimumHighlightHeight(long j, int i);

        private native void native_setMinimumHighlightWidth(long j, int i);

        private native void native_setSelectedBrushForState(long j, NativeBrush nativeBrush, NativeBarcodePickState nativeBarcodePickState);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public NativeBarcodePickViewHighlightStyle asHighlightStyle() {
            return native_asHighlightStyle(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public NativeBrush brushForState(NativeBarcodePickState nativeBarcodePickState) {
            return native_brushForState(this.nativeRef, nativeBarcodePickState);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public int getMinimumHighlightHeight() {
            return native_getMinimumHighlightHeight(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public int getMinimumHighlightWidth() {
            return native_getMinimumHighlightWidth(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public NativeBrush selectedBrushForState(NativeBarcodePickState nativeBarcodePickState) {
            return native_selectedBrushForState(this.nativeRef, nativeBarcodePickState);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public void setBrushForState(NativeBrush nativeBrush, NativeBarcodePickState nativeBarcodePickState) {
            native_setBrushForState(this.nativeRef, nativeBrush, nativeBarcodePickState);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public void setMinimumHighlightHeight(int i) {
            native_setMinimumHighlightHeight(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public void setMinimumHighlightWidth(int i) {
            native_setMinimumHighlightWidth(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public void setSelectedBrushForState(NativeBrush nativeBrush, NativeBarcodePickState nativeBarcodePickState) {
            native_setSelectedBrushForState(this.nativeRef, nativeBrush, nativeBarcodePickState);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewHighlightStyleRectangular
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public static native NativeBarcodePickViewHighlightStyleRectangular create();

    public abstract NativeBarcodePickViewHighlightStyle asHighlightStyle();

    public abstract NativeBrush brushForState(NativeBarcodePickState nativeBarcodePickState);

    public abstract int getMinimumHighlightHeight();

    public abstract int getMinimumHighlightWidth();

    public abstract NativeBrush selectedBrushForState(NativeBarcodePickState nativeBarcodePickState);

    public abstract void setBrushForState(NativeBrush nativeBrush, NativeBarcodePickState nativeBarcodePickState);

    public abstract void setMinimumHighlightHeight(int i);

    public abstract void setMinimumHighlightWidth(int i);

    public abstract void setSelectedBrushForState(NativeBrush nativeBrush, NativeBarcodePickState nativeBarcodePickState);

    public abstract String toJson();
}
