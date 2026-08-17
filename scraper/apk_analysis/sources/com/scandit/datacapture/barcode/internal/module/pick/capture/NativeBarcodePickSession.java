package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickSession {

    public static final class CppProxy extends NativeBarcodePickSession {
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

        private native ArrayList<NativeTrackedObject> native_getAddedTrackedObjects(long j);

        private native HashMap<Integer, NativeTrackedObject> native_getFilteredTrackedObjects(long j);

        private native long native_getFrameSeqIdAndroid(long j);

        private native HashSet<String> native_getPickedObjects(long j);

        private native NativeBarcodePickPublicSession native_getPublicSession(long j);

        private native ArrayList<Integer> native_getRemovedTrackedObjects(long j);

        private native NativeBarcodePickScanningSession native_getScanningSession(long j);

        private native HashSet<String> native_getToPickObjects(long j);

        private native HashMap<Integer, NativeTrackedObject> native_getTrackedObjects(long j);

        private native HashSet<String> native_getUndefinedObjects(long j);

        private native ArrayList<NativeTrackedObject> native_getUpdatedTrackedObjects(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public ArrayList<NativeTrackedObject> getAddedTrackedObjects() {
            return native_getAddedTrackedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public HashMap<Integer, NativeTrackedObject> getFilteredTrackedObjects() {
            return native_getFilteredTrackedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public long getFrameSeqIdAndroid() {
            return native_getFrameSeqIdAndroid(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public HashSet<String> getPickedObjects() {
            return native_getPickedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public NativeBarcodePickPublicSession getPublicSession() {
            return native_getPublicSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public ArrayList<Integer> getRemovedTrackedObjects() {
            return native_getRemovedTrackedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public NativeBarcodePickScanningSession getScanningSession() {
            return native_getScanningSession(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public HashSet<String> getToPickObjects() {
            return native_getToPickObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public HashMap<Integer, NativeTrackedObject> getTrackedObjects() {
            return native_getTrackedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public HashSet<String> getUndefinedObjects() {
            return native_getUndefinedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickSession
        public ArrayList<NativeTrackedObject> getUpdatedTrackedObjects() {
            return native_getUpdatedTrackedObjects(this.nativeRef);
        }
    }

    public abstract ArrayList<NativeTrackedObject> getAddedTrackedObjects();

    public abstract HashMap<Integer, NativeTrackedObject> getFilteredTrackedObjects();

    public abstract long getFrameSeqIdAndroid();

    public abstract HashSet<String> getPickedObjects();

    public abstract NativeBarcodePickPublicSession getPublicSession();

    public abstract ArrayList<Integer> getRemovedTrackedObjects();

    public abstract NativeBarcodePickScanningSession getScanningSession();

    public abstract HashSet<String> getToPickObjects();

    public abstract HashMap<Integer, NativeTrackedObject> getTrackedObjects();

    public abstract HashSet<String> getUndefinedObjects();

    public abstract ArrayList<NativeTrackedObject> getUpdatedTrackedObjects();
}
