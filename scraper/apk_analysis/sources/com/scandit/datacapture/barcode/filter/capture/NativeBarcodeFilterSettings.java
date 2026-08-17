package com.scandit.datacapture.barcode.filter.capture;

import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeFilterSettings {

    public static final class CppProxy extends NativeBarcodeFilterSettings {
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

        private native String native_getExcludedCodesRegex(long j);

        private native HashMap<Symbology, HashSet<Short>> native_getExcludedSymbolCounts(long j);

        private native HashSet<Symbology> native_getExcludedSymbologies(long j);

        private native boolean native_isExcludeEan13(long j);

        private native boolean native_isExcludeUpca(long j);

        private native void native_setExcludeEan13(long j, boolean z);

        private native void native_setExcludeUpca(long j, boolean z);

        private native void native_setExcludedCodesRegex(long j, String str);

        private native void native_setExcludedSymbolCounts(long j, HashMap<Symbology, HashSet<Short>> hashMap);

        private native void native_setExcludedSymbologies(long j, HashSet<Symbology> hashSet);

        private native void native_updateFromJson(long j, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public String getExcludedCodesRegex() {
            return native_getExcludedCodesRegex(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public HashMap<Symbology, HashSet<Short>> getExcludedSymbolCounts() {
            return native_getExcludedSymbolCounts(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public HashSet<Symbology> getExcludedSymbologies() {
            return native_getExcludedSymbologies(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public boolean isExcludeEan13() {
            return native_isExcludeEan13(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public boolean isExcludeUpca() {
            return native_isExcludeUpca(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public void setExcludeEan13(boolean z) {
            native_setExcludeEan13(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public void setExcludeUpca(boolean z) {
            native_setExcludeUpca(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public void setExcludedCodesRegex(String str) {
            native_setExcludedCodesRegex(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public void setExcludedSymbolCounts(HashMap<Symbology, HashSet<Short>> hashMap) {
            native_setExcludedSymbolCounts(this.nativeRef, hashMap);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public void setExcludedSymbologies(HashSet<Symbology> hashSet) {
            native_setExcludedSymbologies(this.nativeRef, hashSet);
        }

        @Override // com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings
        public void updateFromJson(NativeJsonValue nativeJsonValue) {
            native_updateFromJson(this.nativeRef, nativeJsonValue);
        }
    }

    public static native NativeBarcodeFilterSettings create();

    public static native NativeBarcodeFilterSettings fromJson(NativeJsonValue nativeJsonValue);

    public abstract String getExcludedCodesRegex();

    public abstract HashMap<Symbology, HashSet<Short>> getExcludedSymbolCounts();

    public abstract HashSet<Symbology> getExcludedSymbologies();

    public abstract boolean isExcludeEan13();

    public abstract boolean isExcludeUpca();

    public abstract void setExcludeEan13(boolean z);

    public abstract void setExcludeUpca(boolean z);

    public abstract void setExcludedCodesRegex(String str);

    public abstract void setExcludedSymbolCounts(HashMap<Symbology, HashSet<Short>> hashMap);

    public abstract void setExcludedSymbologies(HashSet<Symbology> hashSet);

    public abstract void updateFromJson(NativeJsonValue nativeJsonValue);
}
