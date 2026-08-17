package com.scandit.datacapture.barcode.batch.internal.module.serialization;

import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking;
import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSettings;
import com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingAdvancedOverlay;
import com.scandit.datacapture.barcode.batch.internal.module.ui.overlay.NativeBarcodeTrackingBasicOverlay;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeTrackingDeserializer {

    public static final class CppProxy extends NativeBarcodeTrackingDeserializer {
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

        private native NativeBarcodeTrackingAdvancedOverlay native_barcodeTrackingAdvancedOverlayFromJson(long j, NativeBarcodeTracking nativeBarcodeTracking, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeTrackingBasicOverlay native_barcodeTrackingBasicOverlayFromJson(long j, NativeBarcodeTracking nativeBarcodeTracking, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeTracking native_barcodeTrackingFromJson(long j, NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeTrackingDeserializerHelper native_getHelper(long j);

        private native ArrayList<String> native_getWarnings(long j);

        private native void native_setListener(long j, NativeBarcodeTrackingDeserializerListener nativeBarcodeTrackingDeserializerListener);

        private native NativeBarcodeTrackingSettings native_settingsFromJson(long j, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeTrackingAdvancedOverlay native_updateBarcodeTrackingAdvancedOverlayFromJson(long j, NativeBarcodeTrackingAdvancedOverlay nativeBarcodeTrackingAdvancedOverlay, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeTrackingBasicOverlay native_updateBarcodeTrackingBasicOverlayFromJson(long j, NativeBarcodeTrackingBasicOverlay nativeBarcodeTrackingBasicOverlay, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeTracking native_updateBarcodeTrackingFromJson(long j, NativeBarcodeTracking nativeBarcodeTracking, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeTrackingSettings native_updateSettingsFromJson(long j, NativeBarcodeTrackingSettings nativeBarcodeTrackingSettings, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer() {
            return native_asDataCaptureModeDeserializer(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeBarcodeTrackingAdvancedOverlay barcodeTrackingAdvancedOverlayFromJson(NativeBarcodeTracking nativeBarcodeTracking, NativeJsonValue nativeJsonValue) {
            return native_barcodeTrackingAdvancedOverlayFromJson(this.nativeRef, nativeBarcodeTracking, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeBarcodeTrackingBasicOverlay barcodeTrackingBasicOverlayFromJson(NativeBarcodeTracking nativeBarcodeTracking, NativeJsonValue nativeJsonValue) {
            return native_barcodeTrackingBasicOverlayFromJson(this.nativeRef, nativeBarcodeTracking, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeBarcodeTracking barcodeTrackingFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue) {
            return native_barcodeTrackingFromJson(this.nativeRef, nativeDataCaptureContext, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeBarcodeTrackingDeserializerHelper getHelper() {
            return native_getHelper(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public void setListener(NativeBarcodeTrackingDeserializerListener nativeBarcodeTrackingDeserializerListener) {
            native_setListener(this.nativeRef, nativeBarcodeTrackingDeserializerListener);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeBarcodeTrackingSettings settingsFromJson(NativeJsonValue nativeJsonValue) {
            return native_settingsFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeBarcodeTrackingAdvancedOverlay updateBarcodeTrackingAdvancedOverlayFromJson(NativeBarcodeTrackingAdvancedOverlay nativeBarcodeTrackingAdvancedOverlay, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeTrackingAdvancedOverlayFromJson(this.nativeRef, nativeBarcodeTrackingAdvancedOverlay, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeBarcodeTrackingBasicOverlay updateBarcodeTrackingBasicOverlayFromJson(NativeBarcodeTrackingBasicOverlay nativeBarcodeTrackingBasicOverlay, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeTrackingBasicOverlayFromJson(this.nativeRef, nativeBarcodeTrackingBasicOverlay, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeBarcodeTracking updateBarcodeTrackingFromJson(NativeBarcodeTracking nativeBarcodeTracking, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeTrackingFromJson(this.nativeRef, nativeBarcodeTracking, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.batch.internal.module.serialization.NativeBarcodeTrackingDeserializer
        public NativeBarcodeTrackingSettings updateSettingsFromJson(NativeBarcodeTrackingSettings nativeBarcodeTrackingSettings, NativeJsonValue nativeJsonValue) {
            return native_updateSettingsFromJson(this.nativeRef, nativeBarcodeTrackingSettings, nativeJsonValue);
        }
    }

    public static native NativeBarcodeTrackingDeserializer create(NativeBarcodeTrackingDeserializerHelper nativeBarcodeTrackingDeserializerHelper);

    public abstract NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer();

    public abstract NativeBarcodeTrackingAdvancedOverlay barcodeTrackingAdvancedOverlayFromJson(NativeBarcodeTracking nativeBarcodeTracking, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeTrackingBasicOverlay barcodeTrackingBasicOverlayFromJson(NativeBarcodeTracking nativeBarcodeTracking, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeTracking barcodeTrackingFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeTrackingDeserializerHelper getHelper();

    public abstract ArrayList<String> getWarnings();

    public abstract void setListener(NativeBarcodeTrackingDeserializerListener nativeBarcodeTrackingDeserializerListener);

    public abstract NativeBarcodeTrackingSettings settingsFromJson(NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeTrackingAdvancedOverlay updateBarcodeTrackingAdvancedOverlayFromJson(NativeBarcodeTrackingAdvancedOverlay nativeBarcodeTrackingAdvancedOverlay, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeTrackingBasicOverlay updateBarcodeTrackingBasicOverlayFromJson(NativeBarcodeTrackingBasicOverlay nativeBarcodeTrackingBasicOverlay, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeTracking updateBarcodeTrackingFromJson(NativeBarcodeTracking nativeBarcodeTracking, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeTrackingSettings updateSettingsFromJson(NativeBarcodeTrackingSettings nativeBarcodeTrackingSettings, NativeJsonValue nativeJsonValue);
}
