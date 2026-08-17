package com.scandit.datacapture.barcode.selection.internal.module.serialization;

import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSettings;
import com.scandit.datacapture.barcode.selection.internal.module.ui.overlay.NativeBarcodeSelectionBasicOverlay;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer;
import com.scandit.datacapture.core.internal.module.serialization.NativeViewfinderDeserializer;
import com.scandit.datacapture.core.internal.sdk.capture.NativeDataCaptureContext;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeSelectionDeserializer {

    public static final class CppProxy extends NativeBarcodeSelectionDeserializer {
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

        private native NativeBarcodeSelectionBasicOverlay native_barcodeSelectionBasicOverlayFromJson(long j, NativeBarcodeSelection nativeBarcodeSelection, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeSelection native_barcodeSelectionFromJson(long j, NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeSelectionDeserializerHelper native_getHelper(long j);

        private native ArrayList<String> native_getWarnings(long j);

        private native void native_setListener(long j, NativeBarcodeSelectionDeserializerListener nativeBarcodeSelectionDeserializerListener);

        private native NativeBarcodeSelectionSettings native_settingsFromJson(long j, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeSelectionBasicOverlay native_updateBarcodeSelectionBasicOverlayFromJson(long j, NativeBarcodeSelectionBasicOverlay nativeBarcodeSelectionBasicOverlay, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeSelection native_updateBarcodeSelectionFromJson(long j, NativeBarcodeSelection nativeBarcodeSelection, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeSelectionSettings native_updateSettingsFromJson(long j, NativeBarcodeSelectionSettings nativeBarcodeSelectionSettings, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer() {
            return native_asDataCaptureModeDeserializer(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public NativeBarcodeSelectionBasicOverlay barcodeSelectionBasicOverlayFromJson(NativeBarcodeSelection nativeBarcodeSelection, NativeJsonValue nativeJsonValue) {
            return native_barcodeSelectionBasicOverlayFromJson(this.nativeRef, nativeBarcodeSelection, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public NativeBarcodeSelection barcodeSelectionFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue) {
            return native_barcodeSelectionFromJson(this.nativeRef, nativeDataCaptureContext, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public NativeBarcodeSelectionDeserializerHelper getHelper() {
            return native_getHelper(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public void setListener(NativeBarcodeSelectionDeserializerListener nativeBarcodeSelectionDeserializerListener) {
            native_setListener(this.nativeRef, nativeBarcodeSelectionDeserializerListener);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public NativeBarcodeSelectionSettings settingsFromJson(NativeJsonValue nativeJsonValue) {
            return native_settingsFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public NativeBarcodeSelectionBasicOverlay updateBarcodeSelectionBasicOverlayFromJson(NativeBarcodeSelectionBasicOverlay nativeBarcodeSelectionBasicOverlay, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeSelectionBasicOverlayFromJson(this.nativeRef, nativeBarcodeSelectionBasicOverlay, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public NativeBarcodeSelection updateBarcodeSelectionFromJson(NativeBarcodeSelection nativeBarcodeSelection, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeSelectionFromJson(this.nativeRef, nativeBarcodeSelection, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.selection.internal.module.serialization.NativeBarcodeSelectionDeserializer
        public NativeBarcodeSelectionSettings updateSettingsFromJson(NativeBarcodeSelectionSettings nativeBarcodeSelectionSettings, NativeJsonValue nativeJsonValue) {
            return native_updateSettingsFromJson(this.nativeRef, nativeBarcodeSelectionSettings, nativeJsonValue);
        }
    }

    public static native NativeBarcodeSelectionDeserializer create(NativeViewfinderDeserializer nativeViewfinderDeserializer, NativeBarcodeSelectionDeserializerHelper nativeBarcodeSelectionDeserializerHelper);

    public abstract NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer();

    public abstract NativeBarcodeSelectionBasicOverlay barcodeSelectionBasicOverlayFromJson(NativeBarcodeSelection nativeBarcodeSelection, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeSelection barcodeSelectionFromJson(NativeDataCaptureContext nativeDataCaptureContext, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeSelectionDeserializerHelper getHelper();

    public abstract ArrayList<String> getWarnings();

    public abstract void setListener(NativeBarcodeSelectionDeserializerListener nativeBarcodeSelectionDeserializerListener);

    public abstract NativeBarcodeSelectionSettings settingsFromJson(NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeSelectionBasicOverlay updateBarcodeSelectionBasicOverlayFromJson(NativeBarcodeSelectionBasicOverlay nativeBarcodeSelectionBasicOverlay, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeSelection updateBarcodeSelectionFromJson(NativeBarcodeSelection nativeBarcodeSelection, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeSelectionSettings updateSettingsFromJson(NativeBarcodeSelectionSettings nativeBarcodeSelectionSettings, NativeJsonValue nativeJsonValue);
}
