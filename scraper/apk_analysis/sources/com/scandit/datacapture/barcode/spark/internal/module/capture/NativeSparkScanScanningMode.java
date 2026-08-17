package com.scandit.datacapture.barcode.spark.internal.module.capture;

import com.scandit.datacapture.barcode.spark.ui.SparkScanPreviewBehavior;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningBehavior;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSparkScanScanningMode {

    public static final class CppProxy extends NativeSparkScanScanningMode {
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

        private native SparkScanPreviewBehavior native_getPreviewBehavior(long j);

        private native SparkScanScanningBehavior native_getScanningBehavior(long j);

        private native NativeSparkScanScanningModeType native_getType(long j);

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode
        public SparkScanPreviewBehavior getPreviewBehavior() {
            return native_getPreviewBehavior(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode
        public SparkScanScanningBehavior getScanningBehavior() {
            return native_getScanningBehavior(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanScanningMode
        public NativeSparkScanScanningModeType getType() {
            return native_getType(this.nativeRef);
        }
    }

    public abstract SparkScanPreviewBehavior getPreviewBehavior();

    public abstract SparkScanScanningBehavior getScanningBehavior();

    public abstract NativeSparkScanScanningModeType getType();
}
