package com.scandit.datacapture.barcode.batch.internal.module.ui.overlay;

import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking;
import com.scandit.datacapture.core.internal.sdk.capture.NativeFeatureAvailability;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeTrackingAdvancedOverlay {

    public static final class CppProxy extends NativeBarcodeTrackingAdvancedOverlay {
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

        private native boolean native_getShouldShowScanAreaGuides(long j);

        private native NativeFeatureAvailability native_requireArFeatureAvailabilityFromContext(long j);

        private native void native_setShouldShowScanAreaGuides(long j, boolean z);

        @Override // com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingAdvancedOverlay
        public NativeDataCaptureOverlay asDataCaptureOverlay() {
            return native_asDataCaptureOverlay(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingAdvancedOverlay
        public boolean getShouldShowScanAreaGuides() {
            return native_getShouldShowScanAreaGuides(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingAdvancedOverlay
        public NativeFeatureAvailability requireArFeatureAvailabilityFromContext() {
            return native_requireArFeatureAvailabilityFromContext(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingAdvancedOverlay
        public void setShouldShowScanAreaGuides(boolean z) {
            native_setShouldShowScanAreaGuides(this.nativeRef, z);
        }
    }

    public static native NativeBarcodeTrackingAdvancedOverlay create(NativeBarcodeTracking nativeBarcodeTracking);

    public abstract NativeDataCaptureOverlay asDataCaptureOverlay();

    public abstract boolean getShouldShowScanAreaGuides();

    public abstract NativeFeatureAvailability requireArFeatureAvailabilityFromContext();

    public abstract void setShouldShowScanAreaGuides(boolean z);
}
