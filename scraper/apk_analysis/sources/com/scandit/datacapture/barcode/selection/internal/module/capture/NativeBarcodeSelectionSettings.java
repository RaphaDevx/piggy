package com.scandit.datacapture.barcode.selection.internal.module.capture;

import com.scandit.datacapture.barcode.data.CapturePreset;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings;
import com.snapchat.djinni.NativeObjectManager;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeSelectionSettings {

    public static final class CppProxy extends NativeBarcodeSelectionSettings {
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

        private native void native_enableSymbologies(long j, HashSet<Symbology> hashSet);

        private native Boolean native_getBoolProperty(long j, String str);

        private native int native_getCodeDuplicateFilterMilliseconds(long j);

        private native HashSet<Symbology> native_getEnabledSymbologies(long j);

        private native Integer native_getIntProperty(long j, String str);

        private native NativeSelectionType native_getSelectionType(long j);

        private native boolean native_getSingleBarcodeAutoDetectionEnabled(long j);

        private native boolean native_getSwipeGesturesEnabled(long j);

        private native NativeSymbologySettings native_getSymbologySettings(long j, Symbology symbology);

        private native boolean native_getTapGestureForSelectionEnabled(long j);

        private native void native_setBoolProperty(long j, String str, boolean z);

        private native void native_setCodeDuplicateFilterMilliseconds(long j, int i);

        private native void native_setFloatProperty(long j, String str, float f);

        private native void native_setIntProperty(long j, String str, int i);

        private native void native_setSelectionType(long j, NativeSelectionType nativeSelectionType);

        private native void native_setSingleBarcodeAutoDetectionEnabled(long j, boolean z);

        private native void native_setStringProperty(long j, String str, String str2);

        private native void native_setSwipeGesturesEnabled(long j, boolean z);

        private native void native_setSymbologyEnabled(long j, Symbology symbology, boolean z);

        private native void native_setTapGestureForSelectionEnabled(long j, boolean z);

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void enableSymbologies(HashSet<Symbology> hashSet) {
            native_enableSymbologies(this.nativeRef, hashSet);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public Boolean getBoolProperty(String str) {
            return native_getBoolProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public int getCodeDuplicateFilterMilliseconds() {
            return native_getCodeDuplicateFilterMilliseconds(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public HashSet<Symbology> getEnabledSymbologies() {
            return native_getEnabledSymbologies(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public Integer getIntProperty(String str) {
            return native_getIntProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public NativeSelectionType getSelectionType() {
            return native_getSelectionType(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public boolean getSingleBarcodeAutoDetectionEnabled() {
            return native_getSingleBarcodeAutoDetectionEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public boolean getSwipeGesturesEnabled() {
            return native_getSwipeGesturesEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public NativeSymbologySettings getSymbologySettings(Symbology symbology) {
            return native_getSymbologySettings(this.nativeRef, symbology);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public boolean getTapGestureForSelectionEnabled() {
            return native_getTapGestureForSelectionEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setBoolProperty(String str, boolean z) {
            native_setBoolProperty(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setCodeDuplicateFilterMilliseconds(int i) {
            native_setCodeDuplicateFilterMilliseconds(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setFloatProperty(String str, float f) {
            native_setFloatProperty(this.nativeRef, str, f);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setIntProperty(String str, int i) {
            native_setIntProperty(this.nativeRef, str, i);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setSelectionType(NativeSelectionType nativeSelectionType) {
            native_setSelectionType(this.nativeRef, nativeSelectionType);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setSingleBarcodeAutoDetectionEnabled(boolean z) {
            native_setSingleBarcodeAutoDetectionEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setStringProperty(String str, String str2) {
            native_setStringProperty(this.nativeRef, str, str2);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setSwipeGesturesEnabled(boolean z) {
            native_setSwipeGesturesEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setSymbologyEnabled(Symbology symbology, boolean z) {
            native_setSymbologyEnabled(this.nativeRef, symbology, z);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings
        public void setTapGestureForSelectionEnabled(boolean z) {
            native_setTapGestureForSelectionEnabled(this.nativeRef, z);
        }
    }

    public static native NativeBarcodeSelectionSettings create();

    public static native NativeBarcodeSelectionSettings createWithPresets(HashSet<CapturePreset> hashSet);

    public abstract void enableSymbologies(HashSet<Symbology> hashSet);

    public abstract Boolean getBoolProperty(String str);

    public abstract int getCodeDuplicateFilterMilliseconds();

    public abstract HashSet<Symbology> getEnabledSymbologies();

    public abstract Integer getIntProperty(String str);

    public abstract NativeSelectionType getSelectionType();

    public abstract boolean getSingleBarcodeAutoDetectionEnabled();

    public abstract boolean getSwipeGesturesEnabled();

    public abstract NativeSymbologySettings getSymbologySettings(Symbology symbology);

    public abstract boolean getTapGestureForSelectionEnabled();

    public abstract void setBoolProperty(String str, boolean z);

    public abstract void setCodeDuplicateFilterMilliseconds(int i);

    public abstract void setFloatProperty(String str, float f);

    public abstract void setIntProperty(String str, int i);

    public abstract void setSelectionType(NativeSelectionType nativeSelectionType);

    public abstract void setSingleBarcodeAutoDetectionEnabled(boolean z);

    public abstract void setStringProperty(String str, String str2);

    public abstract void setSwipeGesturesEnabled(boolean z);

    public abstract void setSymbologyEnabled(Symbology symbology, boolean z);

    public abstract void setTapGestureForSelectionEnabled(boolean z);
}
