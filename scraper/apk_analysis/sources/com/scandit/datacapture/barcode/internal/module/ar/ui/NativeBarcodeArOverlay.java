package com.scandit.datacapture.barcode.internal.module.ar.ui;

import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeArOverlay {

    public static final class CppProxy extends NativeBarcodeArOverlay {
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

        private native NativeDataCaptureOverlay native_asDataCaptureOverlay(long j);

        private native NativeBarcodeArAnnotationVariation native_getAnnotationVariationForQuadPxSize(long j, Quadrilateral quadrilateral, float f);

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArOverlay
        public NativeDataCaptureOverlay asDataCaptureOverlay() {
            return native_asDataCaptureOverlay(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArOverlay
        public NativeBarcodeArAnnotationVariation getAnnotationVariationForQuadPxSize(Quadrilateral quadrilateral, float f) {
            return native_getAnnotationVariationForQuadPxSize(this.nativeRef, quadrilateral, f);
        }
    }

    public static native NativeBarcodeArOverlay create();

    public abstract NativeDataCaptureOverlay asDataCaptureOverlay();

    public abstract NativeBarcodeArAnnotationVariation getAnnotationVariationForQuadPxSize(Quadrilateral quadrilateral, float f);
}
