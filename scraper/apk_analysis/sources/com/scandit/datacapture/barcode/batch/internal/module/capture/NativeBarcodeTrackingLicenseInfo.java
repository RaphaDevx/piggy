package com.scandit.datacapture.barcode.batch.internal.module.capture;

import com.scandit.datacapture.barcode.data.Symbology;
import com.snapchat.djinni.NativeObjectManager;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeTrackingLicenseInfo {

    public static final class CppProxy extends NativeBarcodeTrackingLicenseInfo {
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

        private native HashSet<Symbology> native_getLicensedSymbologies(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingLicenseInfo
        public HashSet<Symbology> getLicensedSymbologies() {
            return native_getLicensedSymbologies(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingLicenseInfo
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract HashSet<Symbology> getLicensedSymbologies();

    public abstract String toJson();
}
