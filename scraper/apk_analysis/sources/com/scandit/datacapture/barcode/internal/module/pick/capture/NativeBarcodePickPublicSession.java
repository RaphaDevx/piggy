package com.scandit.datacapture.barcode.internal.module.pick.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedObject;
import com.snapchat.djinni.NativeObjectManager;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodePickPublicSession {

    public static final class CppProxy extends NativeBarcodePickPublicSession {
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

        private native HashSet<String> native_getAddedItems(long j);

        private native HashSet<NativeTrackedObject> native_getAddedObjects(long j);

        private native HashSet<String> native_getTrackedItems(long j);

        private native HashSet<NativeTrackedObject> native_getTrackedObjects(long j);

        private native String native_toJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicSession
        public HashSet<String> getAddedItems() {
            return native_getAddedItems(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicSession
        public HashSet<NativeTrackedObject> getAddedObjects() {
            return native_getAddedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicSession
        public HashSet<String> getTrackedItems() {
            return native_getTrackedItems(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicSession
        public HashSet<NativeTrackedObject> getTrackedObjects() {
            return native_getTrackedObjects(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePickPublicSession
        public String toJson() {
            return native_toJson(this.nativeRef);
        }
    }

    public abstract HashSet<String> getAddedItems();

    public abstract HashSet<NativeTrackedObject> getAddedObjects();

    public abstract HashSet<String> getTrackedItems();

    public abstract HashSet<NativeTrackedObject> getTrackedObjects();

    public abstract String toJson();
}
