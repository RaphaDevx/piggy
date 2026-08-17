package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import com.scandit.datacapture.core.common.geometry.Point;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeClusterLiveEditor {

    public static final class CppProxy extends NativeBarcodeClusterLiveEditor {
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

        private native void native_beginFreehand(long j);

        private native void native_dissolveCluster(long j, NativeCluster nativeCluster);

        private native void native_endEditing(long j);

        private native void native_endFreehand(long j);

        private native void native_updateFreehand(long j, ArrayList<Point> arrayList);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterLiveEditor
        public void beginFreehand() {
            native_beginFreehand(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterLiveEditor
        public void dissolveCluster(NativeCluster nativeCluster) {
            native_dissolveCluster(this.nativeRef, nativeCluster);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterLiveEditor
        public void endEditing() {
            native_endEditing(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterLiveEditor
        public void endFreehand() {
            native_endFreehand(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeClusterLiveEditor
        public void updateFreehand(ArrayList<Point> arrayList) {
            native_updateFreehand(this.nativeRef, arrayList);
        }
    }

    public abstract void beginFreehand();

    public abstract void dissolveCluster(NativeCluster nativeCluster);

    public abstract void endEditing();

    public abstract void endFreehand();

    public abstract void updateFreehand(ArrayList<Point> arrayList);
}
