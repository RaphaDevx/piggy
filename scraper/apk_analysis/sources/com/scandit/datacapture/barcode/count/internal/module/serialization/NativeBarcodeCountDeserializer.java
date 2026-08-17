package com.scandit.datacapture.barcode.count.internal.module.serialization;

import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCount;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountSettings;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer;
import com.scandit.datacapture.core.internal.module.source.NativeCameraSettings;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCountDeserializer {

    public static final class CppProxy extends NativeBarcodeCountDeserializer {
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

        private native NativeDataCaptureModeDeserializer native_asDataCaptureModeDeserializer(long j);

        private native NativeBarcodeCountBasicOverlay native_barcodeCountBasicOverlayFromJson(long j, NativeBarcodeCount nativeBarcodeCount, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeCount native_barcodeCountFromJson(long j, NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

        private native NativeCameraSettings native_createRecommendedCameraSettings(long j);

        private native NativeBarcodeCountDeserializerHelper native_getHelper(long j);

        private native ArrayList<String> native_getWarnings(long j);

        private native void native_setListener(long j, NativeBarcodeCountDeserializerListener nativeBarcodeCountDeserializerListener);

        private native NativeBarcodeCountSettings native_settingsFromJson(long j, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeCountBasicOverlay native_updateBarcodeCountBasicOverlayFromJson(long j, NativeBarcodeCountBasicOverlay nativeBarcodeCountBasicOverlay, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeCount native_updateBarcodeCountFromJson(long j, NativeBarcodeCount nativeBarcodeCount, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeCountSettings native_updateSettingsFromJson(long j, NativeBarcodeCountSettings nativeBarcodeCountSettings, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer() {
            return native_asDataCaptureModeDeserializer(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public NativeBarcodeCountBasicOverlay barcodeCountBasicOverlayFromJson(NativeBarcodeCount nativeBarcodeCount, NativeJsonValue nativeJsonValue) {
            return native_barcodeCountBasicOverlayFromJson(this.nativeRef, nativeBarcodeCount, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public NativeBarcodeCount barcodeCountFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue) {
            return native_barcodeCountFromJson(this.nativeRef, nativeDataCaptureContext, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public NativeCameraSettings createRecommendedCameraSettings() {
            return native_createRecommendedCameraSettings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public NativeBarcodeCountDeserializerHelper getHelper() {
            return native_getHelper(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public void setListener(NativeBarcodeCountDeserializerListener nativeBarcodeCountDeserializerListener) {
            native_setListener(this.nativeRef, nativeBarcodeCountDeserializerListener);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public NativeBarcodeCountSettings settingsFromJson(NativeJsonValue nativeJsonValue) {
            return native_settingsFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public NativeBarcodeCountBasicOverlay updateBarcodeCountBasicOverlayFromJson(NativeBarcodeCountBasicOverlay nativeBarcodeCountBasicOverlay, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeCountBasicOverlayFromJson(this.nativeRef, nativeBarcodeCountBasicOverlay, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public NativeBarcodeCount updateBarcodeCountFromJson(NativeBarcodeCount nativeBarcodeCount, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeCountFromJson(this.nativeRef, nativeBarcodeCount, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.count.internal.module.serialization.NativeBarcodeCountDeserializer
        public NativeBarcodeCountSettings updateSettingsFromJson(NativeBarcodeCountSettings nativeBarcodeCountSettings, NativeJsonValue nativeJsonValue) {
            return native_updateSettingsFromJson(this.nativeRef, nativeBarcodeCountSettings, nativeJsonValue);
        }
    }

    public static native NativeBarcodeCountDeserializer create(NativeBarcodeCountDeserializerHelper nativeBarcodeCountDeserializerHelper);

    public abstract NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer();

    public abstract NativeBarcodeCountBasicOverlay barcodeCountBasicOverlayFromJson(NativeBarcodeCount nativeBarcodeCount, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeCount barcodeCountFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

    public abstract NativeCameraSettings createRecommendedCameraSettings();

    public abstract NativeBarcodeCountDeserializerHelper getHelper();

    public abstract ArrayList<String> getWarnings();

    public abstract void setListener(NativeBarcodeCountDeserializerListener nativeBarcodeCountDeserializerListener);

    public abstract NativeBarcodeCountSettings settingsFromJson(NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeCountBasicOverlay updateBarcodeCountBasicOverlayFromJson(NativeBarcodeCountBasicOverlay nativeBarcodeCountBasicOverlay, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeCount updateBarcodeCountFromJson(NativeBarcodeCount nativeBarcodeCount, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeCountSettings updateSettingsFromJson(NativeBarcodeCountSettings nativeBarcodeCountSettings, NativeJsonValue nativeJsonValue);
}
