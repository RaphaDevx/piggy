package com.scandit.datacapture.barcode.batch.internal.module.data;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeTrackedObject {

    public static final class CppProxy extends NativeTrackedObject {
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

        private native Quadrilateral native_getLocation(long j);

        private native Quadrilateral native_getLocationIgnoringLicense(long j);

        private native String native_getUtf8String(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject
        public ArrayList<NativeBarcode> getBarcodes() {
            return native_getBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject
        public int getIdentifier() {
            return native_getIdentifier(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject
        public Quadrilateral getLocation() {
            return native_getLocation(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject
        public Quadrilateral getLocationIgnoringLicense() {
            return native_getLocationIgnoringLicense(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject
        public String getUtf8String() {
            return native_getUtf8String(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract ArrayList<NativeBarcode> getBarcodes();

    public abstract int getIdentifier();

    public abstract Quadrilateral getLocation();

    public abstract Quadrilateral getLocationIgnoringLicense();

    public abstract String getUtf8String();

    public abstract String toJson();
}
