package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeClusterEditor {

    public static final class CppProxy extends NativeBarcodeClusterEditor {
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

        private native void native_dissolveCluster(long j, NativeCluster nativeCluster);

        private native void native_endEditing(long j);

        private native void native_formCluster(long j, ArrayList<NativeBarcode> arrayList);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterEditor
        public void dissolveCluster(NativeCluster nativeCluster) {
            native_dissolveCluster(this.nativeRef, nativeCluster);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterEditor
        public void endEditing() {
            native_endEditing(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterEditor
        public void formCluster(ArrayList<NativeBarcode> arrayList) {
            native_formCluster(this.nativeRef, arrayList);
        }
    }

    public abstract void dissolveCluster(NativeCluster nativeCluster);

    public abstract void endEditing();

    public abstract void formCluster(ArrayList<NativeBarcode> arrayList);
}
