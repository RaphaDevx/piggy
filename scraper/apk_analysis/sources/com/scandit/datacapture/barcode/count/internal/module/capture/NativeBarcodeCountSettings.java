package com.scandit.datacapture.barcode.count.internal.module.capture;

import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.filter.capture.NativeBarcodeFilterSettings;
import com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings;
import com.scandit.datacapture.core.data.ClusteringMode;
import com.scandit.datacapture.core.internal.sdk.engine.NativeProperty;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountSettings {

    public static final class CppProxy extends NativeBarcodeCountSettings {
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

        private native HashMap<String, ArrayList<NativeProperty>> native_getAllProperties(long j);

        private native Boolean native_getBoolProperty(long j, String str);

        private native ClusteringMode native_getClusteringMode(long j);

        private native boolean native_getEnableUnrecognizedBarcodeDetection(long j);

        private native HashSet<Symbology> native_getEnabledSymbologies(long j);

        private native NativeBarcodeFilterSettings native_getFilterSettings(long j);

        private native Integer native_getIntProperty(long j, String str);

        private native boolean native_getMappingEnabled(long j);

        private native boolean native_getShouldUseIMUData(long j);

        private native NativeSymbologySettings native_getSymbologySettings(long j, Symbology symbology);

        private native void native_setBoolProperty(long j, String str, boolean z);

        private native void native_setClusteringMode(long j, ClusteringMode clusteringMode);

        private native void native_setEnableUnrecognizedBarcodeDetection(long j, boolean z);

        private native void native_setExpectsOnlyUniqueBarcodes(long j, boolean z);

        private native void native_setIntProperty(long j, String str, int i);

        private native void native_setMappingEnabled(long j, boolean z);

        private native void native_setShouldDisableModeWhenCaptureListCompleted(long j, boolean z);

        private native void native_setShouldUseIMUData(long j, boolean z);

        private native void native_setSymbologyEnabled(long j, Symbology symbology, boolean z);

        private native boolean native_shouldDisableModeWhenCaptureListCompleted(long j);

        private native void native_updateFromJson(long j, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void enableSymbologies(HashSet<Symbology> hashSet) {
            native_enableSymbologies(this.nativeRef, hashSet);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public boolean expectsOnlyUniqueBarcodes() {
            return native_expectsOnlyUniqueBarcodes(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public HashMap<String, ArrayList<NativeProperty>> getAllProperties() {
            return native_getAllProperties(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public Boolean getBoolProperty(String str) {
            return native_getBoolProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public ClusteringMode getClusteringMode() {
            return native_getClusteringMode(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public boolean getEnableUnrecognizedBarcodeDetection() {
            return native_getEnableUnrecognizedBarcodeDetection(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public HashSet<Symbology> getEnabledSymbologies() {
            return native_getEnabledSymbologies(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public NativeBarcodeFilterSettings getFilterSettings() {
            return native_getFilterSettings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public Integer getIntProperty(String str) {
            return native_getIntProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public boolean getMappingEnabled() {
            return native_getMappingEnabled(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public boolean getShouldUseIMUData() {
            return native_getShouldUseIMUData(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public NativeSymbologySettings getSymbologySettings(Symbology symbology) {
            return native_getSymbologySettings(this.nativeRef, symbology);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void setBoolProperty(String str, boolean z) {
            native_setBoolProperty(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void setClusteringMode(ClusteringMode clusteringMode) {
            native_setClusteringMode(this.nativeRef, clusteringMode);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void setEnableUnrecognizedBarcodeDetection(boolean z) {
            native_setEnableUnrecognizedBarcodeDetection(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void setExpectsOnlyUniqueBarcodes(boolean z) {
            native_setExpectsOnlyUniqueBarcodes(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void setIntProperty(String str, int i) {
            native_setIntProperty(this.nativeRef, str, i);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void setMappingEnabled(boolean z) {
            native_setMappingEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void setShouldDisableModeWhenCaptureListCompleted(boolean z) {
            native_setShouldDisableModeWhenCaptureListCompleted(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void setShouldUseIMUData(boolean z) {
            native_setShouldUseIMUData(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void setSymbologyEnabled(Symbology symbology, boolean z) {
            native_setSymbologyEnabled(this.nativeRef, symbology, z);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public boolean shouldDisableModeWhenCaptureListCompleted() {
            return native_shouldDisableModeWhenCaptureListCompleted(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings
        public void updateFromJson(NativeJsonValue nativeJsonValue) {
            native_updateFromJson(this.nativeRef, nativeJsonValue);
        }
    }

    public static native NativeBarcodeCountSettings create();

    public static native NativeBarcodeCountSettings fromJson(NativeJsonValue nativeJsonValue);

    public abstract void enableSymbologies(HashSet<Symbology> hashSet);

    public abstract boolean expectsOnlyUniqueBarcodes();

    public abstract HashMap<String, ArrayList<NativeProperty>> getAllProperties();

    public abstract Boolean getBoolProperty(String str);

    public abstract ClusteringMode getClusteringMode();

    public abstract boolean getEnableUnrecognizedBarcodeDetection();

    public abstract HashSet<Symbology> getEnabledSymbologies();

    public abstract NativeBarcodeFilterSettings getFilterSettings();

    public abstract Integer getIntProperty(String str);

    public abstract boolean getMappingEnabled();

    public abstract boolean getShouldUseIMUData();

    public abstract NativeSymbologySettings getSymbologySettings(Symbology symbology);

    public abstract void setBoolProperty(String str, boolean z);

    public abstract void setClusteringMode(ClusteringMode clusteringMode);

    public abstract void setEnableUnrecognizedBarcodeDetection(boolean z);

    public abstract void setExpectsOnlyUniqueBarcodes(boolean z);

    public abstract void setIntProperty(String str, int i);

    public abstract void setMappingEnabled(boolean z);

    public abstract void setShouldDisableModeWhenCaptureListCompleted(boolean z);

    public abstract void setShouldUseIMUData(boolean z);

    public abstract void setSymbologyEnabled(Symbology symbology, boolean z);

    public abstract boolean shouldDisableModeWhenCaptureListCompleted();

    public abstract void updateFromJson(NativeJsonValue nativeJsonValue);
}
