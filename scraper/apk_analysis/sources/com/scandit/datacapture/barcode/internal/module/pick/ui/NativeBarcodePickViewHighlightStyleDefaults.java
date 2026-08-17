package com.scandit.datacapture.barcode.internal.module.pick.ui;

import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickState;
import com.scandit.datacapture.core.internal.sdk.ui.style.NativeBrush;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickViewHighlightStyleDefaults {

    public static final class CppProxy extends NativeBarcodePickViewHighlightStyleDefaults {
        private final long nativeRef;

        private CppProxy(long j) {
            new AtomicBoolean(false);
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
            NativeObjectManager.register(this, j);
        }

        public static native void nativeDestroy(long j);
    }

    public static native NativeBrush brushForPickState(NativeBarcodePickState nativeBarcodePickState, NativeBarcodePickViewHighlightType nativeBarcodePickViewHighlightType);

    public static native boolean fitViewsToBarcode();

    public static native int minimumHighlightHeight();

    public static native int minimumHighlightWidth();
}
