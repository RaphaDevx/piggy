package com.scandit.datacapture.barcode.internal.module.find.serialization;

import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSettings;
import com.scandit.datacapture.core.internal.module.serialization.NativeDataCaptureModeDeserializer;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;
import com.snapchat.djinni.NativeObjectManager;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeFindDeserializer {

    public static final class CppProxy extends NativeBarcodeFindDeserializer {
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

        private native NativeBarcodeFind native_barcodeFindFromJson(long j, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeFindDeserializerHelper native_getHelper(long j);

        private native ArrayList<String> native_getWarnings(long j);

        private native void native_setListener(long j, NativeBarcodeFindDeserializerListener nativeBarcodeFindDeserializerListener);

        private native NativeBarcodeFindSettings native_settingsFromJson(long j, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeFind native_updateBarcodeFindFromJson(long j, NativeBarcodeFind nativeBarcodeFind, NativeJsonValue nativeJsonValue);

        private native NativeBarcodeFindSettings native_updateSettingsFromJson(long j, NativeBarcodeFindSettings nativeBarcodeFindSettings, NativeJsonValue nativeJsonValue);

        @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializer
        public NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer() {
            return native_asDataCaptureModeDeserializer(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializer
        public NativeBarcodeFind barcodeFindFromJson(NativeJsonValue nativeJsonValue) {
            return native_barcodeFindFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializer
        public NativeBarcodeFindDeserializerHelper getHelper() {
            return native_getHelper(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializer
        public ArrayList<String> getWarnings() {
            return native_getWarnings(this.nativeRef);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializer
        public void setListener(NativeBarcodeFindDeserializerListener nativeBarcodeFindDeserializerListener) {
            native_setListener(this.nativeRef, nativeBarcodeFindDeserializerListener);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializer
        public NativeBarcodeFindSettings settingsFromJson(NativeJsonValue nativeJsonValue) {
            return native_settingsFromJson(this.nativeRef, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializer
        public NativeBarcodeFind updateBarcodeFindFromJson(NativeBarcodeFind nativeBarcodeFind, NativeJsonValue nativeJsonValue) {
            return native_updateBarcodeFindFromJson(this.nativeRef, nativeBarcodeFind, nativeJsonValue);
        }

        @Override // com.scandit.datacapture.barcode.internal.module.find.serialization.NativeBarcodeFindDeserializer
        public NativeBarcodeFindSettings updateSettingsFromJson(NativeBarcodeFindSettings nativeBarcodeFindSettings, NativeJsonValue nativeJsonValue) {
            return native_updateSettingsFromJson(this.nativeRef, nativeBarcodeFindSettings, nativeJsonValue);
        }
    }

    public static native NativeBarcodeFindDeserializer create(NativeBarcodeFindDeserializerHelper nativeBarcodeFindDeserializerHelper);

    public abstract NativeDataCaptureModeDeserializer asDataCaptureModeDeserializer();

    public abstract NativeBarcodeFind barcodeFindFromJson(NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeFindDeserializerHelper getHelper();

    public abstract ArrayList<String> getWarnings();

    public abstract void setListener(NativeBarcodeFindDeserializerListener nativeBarcodeFindDeserializerListener);

    public abstract NativeBarcodeFindSettings settingsFromJson(NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeFind updateBarcodeFindFromJson(NativeBarcodeFind nativeBarcodeFind, NativeJsonValue nativeJsonValue);

    public abstract NativeBarcodeFindSettings updateSettingsFromJson(NativeBarcodeFindSettings nativeBarcodeFindSettings, NativeJsonValue nativeJsonValue);
}
