package com.scandit.datacapture.barcode.internal.sdk.data;

import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.snapchat.djinni.NativeObjectManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeLocalizedOnlyBarcode {

    public static final class CppProxy extends NativeLocalizedOnlyBarcode {
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

        private native int native_getFrameId(long j);

        private native Quadrilateral native_getLocation(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeLocalizedOnlyBarcode
        public int getFrameId() {
            return native_getFrameId(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeLocalizedOnlyBarcode
        public Quadrilateral getLocation() {
            return native_getLocation(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeLocalizedOnlyBarcode
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract int getFrameId();

    public abstract Quadrilateral getLocation();

    public abstract String toJson();
}
