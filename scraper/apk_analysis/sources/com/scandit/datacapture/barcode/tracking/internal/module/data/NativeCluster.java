package com.scandit.datacapture.barcode.tracking.internal.module.data;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeCluster {

    public static final class CppProxy extends NativeCluster {
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

        private native ArrayList<NativeBarcode> native_getBarcodes(long j);

        private native int native_getIdentifier(long j);

        private native Quadrilateral native_getLocationIgnoringLicense(long j);

        private native String native_getUtf8String(long j);

        @Override // com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster
        public ArrayList<NativeBarcode> getBarcodes() {
            return native_getBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster
        public int getIdentifier() {
            return native_getIdentifier(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster
        public Quadrilateral getLocationIgnoringLicense() {
            return native_getLocationIgnoringLicense(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster
        public String getUtf8String() {
            return native_getUtf8String(this.nativeRef);
        }
    }

    public abstract ArrayList<NativeBarcode> getBarcodes();

    public abstract int getIdentifier();

    public abstract Quadrilateral getLocationIgnoringLicense();

    public abstract String getUtf8String();
}
