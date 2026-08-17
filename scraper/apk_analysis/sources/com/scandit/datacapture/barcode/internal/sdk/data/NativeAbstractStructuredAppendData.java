package com.scandit.datacapture.barcode.internal.sdk.data;

import com.scandit.datacapture.core.common.buffer.EncodingRange;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeAbstractStructuredAppendData {

    public static final class CppProxy extends NativeAbstractStructuredAppendData {
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

        private native String native_getBarcodeSetId(long j);

        private native ArrayList<EncodingRange> native_getCompleteDataEncodings(long j);

        private native byte[] native_getCompleteDataRaw(long j);

        private native String native_getCompleteDataUtf8String(long j);

        private native int native_getScannedSegmentCount(long j);

        private native int native_getTotalSegmentCount(long j);

        private native boolean native_isComplete(long j);

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeAbstractStructuredAppendData
        public String getBarcodeSetId() {
            return native_getBarcodeSetId(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeAbstractStructuredAppendData
        public ArrayList<EncodingRange> getCompleteDataEncodings() {
            return native_getCompleteDataEncodings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeAbstractStructuredAppendData
        public byte[] getCompleteDataRaw() {
            return native_getCompleteDataRaw(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeAbstractStructuredAppendData
        public String getCompleteDataUtf8String() {
            return native_getCompleteDataUtf8String(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeAbstractStructuredAppendData
        public int getScannedSegmentCount() {
            return native_getScannedSegmentCount(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeAbstractStructuredAppendData
        public int getTotalSegmentCount() {
            return native_getTotalSegmentCount(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeAbstractStructuredAppendData
        public boolean isComplete() {
            return native_isComplete(this.nativeRef);
        }
    }

    public abstract String getBarcodeSetId();

    public abstract ArrayList<EncodingRange> getCompleteDataEncodings();

    public abstract byte[] getCompleteDataRaw();

    public abstract String getCompleteDataUtf8String();

    public abstract int getScannedSegmentCount();

    public abstract int getTotalSegmentCount();

    public abstract boolean isComplete();
}
