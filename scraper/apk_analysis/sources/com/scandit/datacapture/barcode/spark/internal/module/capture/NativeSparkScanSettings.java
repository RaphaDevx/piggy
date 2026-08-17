package com.scandit.datacapture.barcode.spark.internal.module.capture;

import com.scandit.datacapture.barcode.data.CapturePreset;
import com.scandit.datacapture.barcode.data.Symbology;
import com.scandit.datacapture.barcode.internal.module.capture.NativeSymbologySettings;
import com.scandit.datacapture.core.internal.sdk.area.NativeLocationSelection;
import com.scandit.datacapture.core.internal.sdk.engine.NativeProperty;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.scandit.datacapture.core.source.ScanIntention;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeSparkScanSettings {

    public static final class CppProxy extends NativeSparkScanSettings {
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

        private native HashMap<String, ArrayList<NativeProperty>> native_getAllProperties(long j);

        private native Boolean native_getBoolProperty(long j, String str);

        private native int native_getCodeDuplicateFilterMilliseconds(long j);

        private native HashSet<Symbology> native_getEnabledSymbologies(long j);

        private native Integer native_getIntProperty(long j, String str);

        private native NativeLocationSelection native_getLocationSelection(long j);

        private native ScanIntention native_getScanIntention(long j);

        private native NativeSymbologySettings native_getSymbologySettings(long j, Symbology symbology);

        private native void native_setBoolProperty(long j, String str, boolean z);

        private native void native_setCodeDuplicateFilterMilliseconds(long j, int i);

        private native void native_setFloatProperty(long j, String str, float f);

        private native void native_setIntProperty(long j, String str, int i);

        private native void native_setLocationSelection(long j, NativeLocationSelection nativeLocationSelection);

        private native void native_setScanIntention(long j, ScanIntention scanIntention);

        private native void native_setStringProperty(long j, String str, String str2);

        private native void native_setSymbologyEnabled(long j, Symbology symbology, boolean z);

        private native void native_updateForTargetModeEnabled(long j, boolean z);

        private native void native_updateFromJson(long j, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void enableSymbologies(HashSet<Symbology> hashSet) {
            native_enableSymbologies(this.nativeRef, hashSet);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public HashMap<String, ArrayList<NativeProperty>> getAllProperties() {
            return native_getAllProperties(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public Boolean getBoolProperty(String str) {
            return native_getBoolProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public int getCodeDuplicateFilterMilliseconds() {
            return native_getCodeDuplicateFilterMilliseconds(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public HashSet<Symbology> getEnabledSymbologies() {
            return native_getEnabledSymbologies(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public Integer getIntProperty(String str) {
            return native_getIntProperty(this.nativeRef, str);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public NativeLocationSelection getLocationSelection() {
            return native_getLocationSelection(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public ScanIntention getScanIntention() {
            return native_getScanIntention(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public NativeSymbologySettings getSymbologySettings(Symbology symbology) {
            return native_getSymbologySettings(this.nativeRef, symbology);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void setBoolProperty(String str, boolean z) {
            native_setBoolProperty(this.nativeRef, str, z);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void setCodeDuplicateFilterMilliseconds(int i) {
            native_setCodeDuplicateFilterMilliseconds(this.nativeRef, i);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void setFloatProperty(String str, float f) {
            native_setFloatProperty(this.nativeRef, str, f);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void setIntProperty(String str, int i) {
            native_setIntProperty(this.nativeRef, str, i);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void setLocationSelection(NativeLocationSelection nativeLocationSelection) {
            native_setLocationSelection(this.nativeRef, nativeLocationSelection);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void setScanIntention(ScanIntention scanIntention) {
            native_setScanIntention(this.nativeRef, scanIntention);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void setStringProperty(String str, String str2) {
            native_setStringProperty(this.nativeRef, str, str2);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void setSymbologyEnabled(Symbology symbology, boolean z) {
            native_setSymbologyEnabled(this.nativeRef, symbology, z);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void updateForTargetModeEnabled(boolean z) {
            native_updateForTargetModeEnabled(this.nativeRef, z);
        }

        @Override // com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanSettings
        public void updateFromJson(NativeJsonValue nativeJsonValue) {
            native_updateFromJson(this.nativeRef, nativeJsonValue);
        }
    }

    public static native NativeSparkScanSettings create();

    public static native NativeSparkScanSettings createWithPresets(HashSet<CapturePreset> hashSet);

    public abstract void enableSymbologies(HashSet<Symbology> hashSet);

    public abstract HashMap<String, ArrayList<NativeProperty>> getAllProperties();

    public abstract Boolean getBoolProperty(String str);

    public abstract int getCodeDuplicateFilterMilliseconds();

    public abstract HashSet<Symbology> getEnabledSymbologies();

    public abstract Integer getIntProperty(String str);

    public abstract NativeLocationSelection getLocationSelection();

    public abstract ScanIntention getScanIntention();

    public abstract NativeSymbologySettings getSymbologySettings(Symbology symbology);

    public abstract void setBoolProperty(String str, boolean z);

    public abstract void setCodeDuplicateFilterMilliseconds(int i);

    public abstract void setFloatProperty(String str, float f);

    public abstract void setIntProperty(String str, int i);

    public abstract void setLocationSelection(NativeLocationSelection nativeLocationSelection);

    public abstract void setScanIntention(ScanIntention scanIntention);

    public abstract void setStringProperty(String str, String str2);

    public abstract void setSymbologyEnabled(Symbology symbology, boolean z);

    public abstract void updateForTargetModeEnabled(boolean z);

    public abstract void updateFromJson(NativeJsonValue nativeJsonValue);
}
