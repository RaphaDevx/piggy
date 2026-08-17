package com.scandit.datacapture.barcode.internal.module.find.capture;

import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.snapchat.djinni.NativeObjectManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeFindSession {

    public static final class CppProxy extends NativeBarcodeFindSession {
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

        private native HashSet<NativeBarcodeFindItem> native_getAllFoundItems(long j);

        private native HashSet<NativeBarcodeFindItem> native_getFoundItemsInLastProcessedFrame(long j);

        private native HashMap<Integer, NativeTrackedBarcode> native_getFoundTrackedBarcodes(long j);

        private native HashMap<Integer, NativeTrackedBarcode> native_getTrackedBarcodes(long j);

        private native byte[] native_getTransformedDataForData(long j, byte[] bArr);

        private native String native_toPublicJson(long j);

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession
        public HashSet<NativeBarcodeFindItem> getAllFoundItems() {
            return native_getAllFoundItems(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession
        public HashSet<NativeBarcodeFindItem> getFoundItemsInLastProcessedFrame() {
            return native_getFoundItemsInLastProcessedFrame(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession
        public HashMap<Integer, NativeTrackedBarcode> getFoundTrackedBarcodes() {
            return native_getFoundTrackedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession
        public HashMap<Integer, NativeTrackedBarcode> getTrackedBarcodes() {
            return native_getTrackedBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession
        public byte[] getTransformedDataForData(byte[] bArr) {
            return native_getTransformedDataForData(this.nativeRef, bArr);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSession
        public String toPublicJson() {
            return native_toPublicJson(this.nativeRef);
        }
    }

    public abstract HashSet<NativeBarcodeFindItem> getAllFoundItems();

    public abstract HashSet<NativeBarcodeFindItem> getFoundItemsInLastProcessedFrame();

    public abstract HashMap<Integer, NativeTrackedBarcode> getFoundTrackedBarcodes();

    public abstract HashMap<Integer, NativeTrackedBarcode> getTrackedBarcodes();

    public abstract byte[] getTransformedDataForData(byte[] bArr);

    public abstract String toPublicJson();
}
