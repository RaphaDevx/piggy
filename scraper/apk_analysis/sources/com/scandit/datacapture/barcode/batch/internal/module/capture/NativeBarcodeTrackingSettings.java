package com.scandit.datacapture.barcode.batch.internal.module.capture;

import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings;
import com.scandit.datacapture.barcode.internal.sdk.data.NativeArucoDictionary;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeObjectTrackerScenario;
import com.scandit.datacapture.core.internal.sdk.engine.NativeProperty;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeTrackingSettings {

    public static final class CppProxy extends NativeBarcodeTrackingSettings {
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

        private native String native_asJson(long j, boolean z);

        private native void native_enableSymbologies(long j, HashSet<Symbology> hashSet);

        private native boolean native_expectsOnlyUniqueBarcodes(long j);

        private native HashMap<String, ArrayList<NativeProperty>> native_getAllProperties(long j);

        private native Boolean native_getBoolProperty(long j, String str);

        private native HashSet<Symbology> native_getEnabledSymbologies(long j);

        private native Integer native_getIntProperty(long j, String str);

        private native NativeSymbologySettings native_getSymbologySettings(long j, Symbology symbology);

        private native void native_setArucoDictionary(long j, NativeArucoDictionary nativeArucoDictionary);

        private native void native_setBoolProperty(long j, String str, boolean z);

        private native void native_setExpectsOnlyUniqueBarcodes(long j, boolean z);

        private native void native_setIntProperty(long j, String str, int i);

        private native void native_setSymbologyEnabled(long j, Symbology symbology, boolean z);

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public String asJson(boolean z) {
            return native_asJson(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public void enableSymbologies(HashSet<Symbology> hashSet) {
            native_enableSymbologies(this.nativeRef, hashSet);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public boolean expectsOnlyUniqueBarcodes() {
            return native_expectsOnlyUniqueBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public HashMap<String, ArrayList<NativeProperty>> getAllProperties() {
            return native_getAllProperties(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public Boolean getBoolProperty(String str) {
            return native_getBoolProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public HashSet<Symbology> getEnabledSymbologies() {
            return native_getEnabledSymbologies(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public Integer getIntProperty(String str) {
            return native_getIntProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public NativeSymbologySettings getSymbologySettings(Symbology symbology) {
            return native_getSymbologySettings(this.nativeRef, symbology);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public void setArucoDictionary(NativeArucoDictionary nativeArucoDictionary) {
            native_setArucoDictionary(this.nativeRef, nativeArucoDictionary);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public void setBoolProperty(String str, boolean z) {
            native_setBoolProperty(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public void setExpectsOnlyUniqueBarcodes(boolean z) {
            native_setExpectsOnlyUniqueBarcodes(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public void setIntProperty(String str, int i) {
            native_setIntProperty(this.nativeRef, str, i);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings
        public void setSymbologyEnabled(Symbology symbology, boolean z) {
            native_setSymbologyEnabled(this.nativeRef, symbology, z);
        }
    }

    public static native NativeBarcodeTrackingSettings create();

    public static native NativeBarcodeTrackingSettings createForScenario(NativeObjectTrackerScenario nativeObjectTrackerScenario);

    public abstract String asJson(boolean z);

    public abstract void enableSymbologies(HashSet<Symbology> hashSet);

    public abstract boolean expectsOnlyUniqueBarcodes();

    public abstract HashMap<String, ArrayList<NativeProperty>> getAllProperties();

    public abstract Boolean getBoolProperty(String str);

    public abstract HashSet<Symbology> getEnabledSymbologies();

    public abstract Integer getIntProperty(String str);

    public abstract NativeSymbologySettings getSymbologySettings(Symbology symbology);

    public abstract void setArucoDictionary(NativeArucoDictionary nativeArucoDictionary);

    public abstract void setBoolProperty(String str, boolean z);

    public abstract void setExpectsOnlyUniqueBarcodes(boolean z);

    public abstract void setIntProperty(String str, int i);

    public abstract void setSymbologyEnabled(Symbology symbology, boolean z);
}
