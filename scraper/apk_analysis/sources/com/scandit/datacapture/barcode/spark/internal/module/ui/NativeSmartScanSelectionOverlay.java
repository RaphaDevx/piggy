package com.scandit.datacapture.barcode.spark.internal.module.ui;

import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScan;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSmartScanSelectionOverlay {

    public static final class CppProxy extends NativeSmartScanSelectionOverlay {
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

        private native void native_onErrorFeedbackEmitted(long j, boolean z);

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSmartScanSelectionOverlay
        public NativeDataCaptureOverlay asDataCaptureOverlay() {
            return native_asDataCaptureOverlay(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSmartScanSelectionOverlay
        public void onErrorFeedbackEmitted(boolean z) {
            native_onErrorFeedbackEmitted(this.nativeRef, z);
        }
    }

    public static native NativeSmartScanSelectionOverlay create(NativeSparkScan nativeSparkScan);

    public abstract NativeDataCaptureOverlay asDataCaptureOverlay();

    public abstract void onErrorFeedbackEmitted(boolean z);
}
