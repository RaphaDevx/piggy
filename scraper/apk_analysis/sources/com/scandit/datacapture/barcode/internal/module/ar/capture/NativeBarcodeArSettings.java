package com.scandit.datacapture.barcode.internal.module.ar.capture;

import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeArSettings {

    public static final class CppProxy extends NativeBarcodeArSettings {
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

        private native boolean native_expectsOnlyUniqueBarcodes(long j);

        private native Boolean native_getBoolProperty(long j, String str);

        private native HashSet<Symbology> native_getEnabledSymbologies(long j);

        private native Integer native_getIntProperty(long j, String str);

        private native NativeSymbologySettings native_getSymbologySettings(long j, Symbology symbology);

        private native void native_setBoolProperty(long j, String str, boolean z);

        private native void native_setExpectsOnlyUniqueBarcodes(long j, boolean z);

        private native void native_setIntProperty(long j, String str, int i);

        private native void native_setSymbologyEnabled(long j, Symbology symbology, boolean z);

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public void enableSymbologies(HashSet<Symbology> hashSet) {
            native_enableSymbologies(this.nativeRef, hashSet);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public boolean expectsOnlyUniqueBarcodes() {
            return native_expectsOnlyUniqueBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public Boolean getBoolProperty(String str) {
            return native_getBoolProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public HashSet<Symbology> getEnabledSymbologies() {
            return native_getEnabledSymbologies(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public Integer getIntProperty(String str) {
            return native_getIntProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public NativeSymbologySettings getSymbologySettings(Symbology symbology) {
            return native_getSymbologySettings(this.nativeRef, symbology);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public void setBoolProperty(String str, boolean z) {
            native_setBoolProperty(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public void setExpectsOnlyUniqueBarcodes(boolean z) {
            native_setExpectsOnlyUniqueBarcodes(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public void setIntProperty(String str, int i) {
            native_setIntProperty(this.nativeRef, str, i);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings
        public void setSymbologyEnabled(Symbology symbology, boolean z) {
            native_setSymbologyEnabled(this.nativeRef, symbology, z);
        }
    }

    public static native NativeBarcodeArSettings create();

    public static native NativeBarcodeArSettings fromJson(NativeJsonValue nativeJsonValue);

    public abstract void enableSymbologies(HashSet<Symbology> hashSet);

    public abstract boolean expectsOnlyUniqueBarcodes();

    public abstract Boolean getBoolProperty(String str);

    public abstract HashSet<Symbology> getEnabledSymbologies();

    public abstract Integer getIntProperty(String str);

    public abstract NativeSymbologySettings getSymbologySettings(Symbology symbology);

    public abstract void setBoolProperty(String str, boolean z);

    public abstract void setExpectsOnlyUniqueBarcodes(boolean z);

    public abstract void setIntProperty(String str, int i);

    public abstract void setSymbologyEnabled(Symbology symbology, boolean z);
}
