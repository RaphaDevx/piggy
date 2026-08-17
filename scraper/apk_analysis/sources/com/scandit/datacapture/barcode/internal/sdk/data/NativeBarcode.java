package com.scandit.datacapture.barcode.internal.sdk.data;

import com.scandit.datacapture.barcode.data.CompositeFlag;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.internal.module.data.NativeBarcodeMetadata;
import com.scandit.datacapture.barcode.internal.module.data.NativeBarcodeRecord;
import com.scandit.datacapture.core.common.buffer.EncodingRange;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcode {

    public static final class CppProxy extends NativeBarcode {
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

        private native NativeBarcodeRecord native_asBarcodeRecord(long j);

        private native String native_getAddOnData(long j);

        private native String native_getCompositeData(long j);

        private native CompositeFlag native_getCompositeFlag(long j);

        private native byte[] native_getCompositeRawData(long j);

        private native byte[] native_getData(long j);

        private native ArrayList<EncodingRange> native_getDataEncoding(long j);

        private native int native_getFrameId(long j);

        private native Quadrilateral native_getLocation(long j);

        private native NativeBarcodeMetadata native_getMetadata(long j);

        private native float native_getPixelPerElement(long j);

        private native NativeAbstractStructuredAppendData native_getStructuredAppendData(long j);

        private native int native_getSymbolCount(long j);

        private native Symbology native_getSymbology(long j);

        private native String native_getUtf8String(long j);

        private native boolean native_isColorInverted(long j);

        private native boolean native_isGs1DataCarrier(long j);

        private native boolean native_isStructuredAppend(long j);

        private native String native_toJson(long j);

        private native String native_uniqueHash(long j);

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public NativeBarcodeRecord asBarcodeRecord() {
            return native_asBarcodeRecord(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public String getAddOnData() {
            return native_getAddOnData(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public String getCompositeData() {
            return native_getCompositeData(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public CompositeFlag getCompositeFlag() {
            return native_getCompositeFlag(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public byte[] getCompositeRawData() {
            return native_getCompositeRawData(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public byte[] getData() {
            return native_getData(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public ArrayList<EncodingRange> getDataEncoding() {
            return native_getDataEncoding(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public int getFrameId() {
            return native_getFrameId(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public Quadrilateral getLocation() {
            return native_getLocation(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public NativeBarcodeMetadata getMetadata() {
            return native_getMetadata(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public float getPixelPerElement() {
            return native_getPixelPerElement(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public NativeAbstractStructuredAppendData getStructuredAppendData() {
            return native_getStructuredAppendData(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public int getSymbolCount() {
            return native_getSymbolCount(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public Symbology getSymbology() {
            return native_getSymbology(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public String getUtf8String() {
            return native_getUtf8String(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public boolean isColorInverted() {
            return native_isColorInverted(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public boolean isGs1DataCarrier() {
            return native_isGs1DataCarrier(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public boolean isStructuredAppend() {
            return native_isStructuredAppend(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public String toJson() {
            return native_toJson(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.sdk.data.NativeBarcode
        public String uniqueHash() {
            return native_uniqueHash(this.nativeRef);
        }
    }

    public abstract NativeBarcodeRecord asBarcodeRecord();

    public abstract String getAddOnData();

    public abstract String getCompositeData();

    public abstract CompositeFlag getCompositeFlag();

    public abstract byte[] getCompositeRawData();

    public abstract byte[] getData();

    public abstract ArrayList<EncodingRange> getDataEncoding();

    public abstract int getFrameId();

    public abstract Quadrilateral getLocation();

    public abstract NativeBarcodeMetadata getMetadata();

    public abstract float getPixelPerElement();

    public abstract NativeAbstractStructuredAppendData getStructuredAppendData();

    public abstract int getSymbolCount();

    public abstract Symbology getSymbology();

    public abstract String getUtf8String();

    public abstract boolean isColorInverted();

    public abstract boolean isGs1DataCarrier();

    public abstract boolean isStructuredAppend();

    public abstract String toJson();

    public abstract String uniqueHash();
}
