package com.scandit.datacapture.barcode.spark.internal.module.capture;

import com.scandit.datacapture.barcode.spark.ui.SparkScanPreviewBehavior;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningBehavior;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSparkScanScanningModeTarget {

    public static final class CppProxy extends NativeSparkScanScanningModeTarget {
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

        private native NativeSparkScanScanningMode native_asScanningMode(long j);

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningModeTarget
        public NativeSparkScanScanningMode asScanningMode() {
            return native_asScanningMode(this.nativeRef);
        }
    }

    public static native NativeSparkScanScanningModeTarget create(SparkScanScanningBehavior sparkScanScanningBehavior, SparkScanPreviewBehavior sparkScanPreviewBehavior);

    public abstract NativeSparkScanScanningMode asScanningMode();
}
