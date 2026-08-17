package com.scandit.datacapture.barcode.internal.module.serialization;

import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture;
import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSettings;
import com.scandit.datacapture.barcode.internal.module.ui.NativeBarcodeCaptureOverlay;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer;
import com.scandit.datacapture.core.internal.module.serialization.NativeLocationSelectionDeserializer;
import com.scandit.datacapture.core.internal.module.serialization.NativeViewfinderDeserializer;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeCaptureDeserializer {

    public static final class CppProxy extends NativeBarcodeCaptureDeserializer {
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

        private native NativeBarcodeCapture native_barcodeCaptureFromJson(long j, NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeCaptureOverlay native_barcodeCaptureOverlayFromJson(long j, NativeBarcodeCapture nativeBarcodeCapture, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeCaptureDeserializerHelper native_getHelper(long j);

        private native ArrayList<String> native_getWarnings(long j);

        private native void native_setListener(long j, NativeBarcodeCaptureDeserializerListener nativeBarcodeCaptureDeserializerListener);

        private native NativeBarcodeCaptureSettings native_settingsFromJson(long j, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeCapture native_updateBarcodeCaptureFromJson(long j, NativeBarcodeCapture nativeBarcodeCapture, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeCaptureOverlay native_updateBarcodeCaptureOverlayFromJson(long j, NativeBarcodeCaptureOverlay nativeBarcodeCaptureOverlay, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeCaptureSettings native_updateSettingsFromJson(long j, NativeBarcodeCaptureSettings nativeBarcodeCaptureSettings, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer() {
            return native_asDataCaptureModeDeserializer(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public NativeBarcodeCapture barcodeCaptureFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue) {
            return native_barcodeCaptureFromJson(this.nativeRef, nativeDataCaptureContext, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public NativeBarcodeCaptureOverlay barcodeCaptureOverlayFromJson(NativeBarcodeCapture nativeBarcodeCapture, NativeJsonValue nativeJsonValue) {
            return native_barcodeCaptureOverlayFromJson(this.nativeRef, nativeBarcodeCapture, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public NativeBarcodeCaptureDeserializerHelper getHelper() {
            return native_getHelper(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public void setListener(NativeBarcodeCaptureDeserializerListener nativeBarcodeCaptureDeserializerListener) {
            native_setListener(this.nativeRef, nativeBarcodeCaptureDeserializerListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public NativeBarcodeCaptureSettings settingsFromJson(NativeJsonValue nativeJsonValue) {
            return native_settingsFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public NativeBarcodeCapture updateBarcodeCaptureFromJson(NativeBarcodeCapture nativeBarcodeCapture, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeCaptureFromJson(this.nativeRef, nativeBarcodeCapture, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public NativeBarcodeCaptureOverlay updateBarcodeCaptureOverlayFromJson(NativeBarcodeCaptureOverlay nativeBarcodeCaptureOverlay, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeCaptureOverlayFromJson(this.nativeRef, nativeBarcodeCaptureOverlay, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.serialization.NativeBarcodeCaptureDeserializer
        public NativeBarcodeCaptureSettings updateSettingsFromJson(NativeBarcodeCaptureSettings nativeBarcodeCaptureSettings, NativeJsonValue nativeJsonValue) {
            return native_updateSettingsFromJson(this.nativeRef, nativeBarcodeCaptureSettings, nativeJsonValue);
        }
    }

    public static native NativeBarcodeCaptureDeserializer create(NativeLocationSelectionDeserializer nativeLocationSelectionDeserializer, NativeViewfinderDeserializer nativeViewfinderDeserializer, NativeBarcodeCaptureDeserializerHelper nativeBarcodeCaptureDeserializerHelper);

    public abstract NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer();

    public abstract NativeBarcodeCapture barcodeCaptureFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeCaptureOverlay barcodeCaptureOverlayFromJson(NativeBarcodeCapture nativeBarcodeCapture, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeCaptureDeserializerHelper getHelper();

    public abstract ArrayList<String> getWarnings();

    public abstract void setListener(NativeBarcodeCaptureDeserializerListener nativeBarcodeCaptureDeserializerListener);

    public abstract NativeBarcodeCaptureSettings settingsFromJson(NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeCapture updateBarcodeCaptureFromJson(NativeBarcodeCapture nativeBarcodeCapture, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeCaptureOverlay updateBarcodeCaptureOverlayFromJson(NativeBarcodeCaptureOverlay nativeBarcodeCaptureOverlay, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeCaptureSettings updateSettingsFromJson(NativeBarcodeCaptureSettings nativeBarcodeCaptureSettings, NativeJsonValue nativeJsonValue);
}
