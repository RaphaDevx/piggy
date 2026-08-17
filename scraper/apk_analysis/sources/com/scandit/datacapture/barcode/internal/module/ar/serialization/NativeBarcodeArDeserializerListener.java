package com.scandit.datacapture.barcode.internal.module.ar.serialization;

import com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeAr;
import com.scandit.datacapture.barcode.internal.module.ar.capture.NativeBarcodeArSettings;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeArDeserializerListener {
    public abstract void onModeDeserializationFinished(NativeBarcodeArDeserializer nativeBarcodeArDeserializer, NativeBarcodeAr nativeBarcodeAr, NativeJsonValue nativeJsonValue);

    public abstract void onModeDeserializationStarted(NativeBarcodeArDeserializer nativeBarcodeArDeserializer, NativeBarcodeAr nativeBarcodeAr, NativeJsonValue nativeJsonValue);

    public abstract void onSettingsDeserializationFinished(NativeBarcodeArDeserializer nativeBarcodeArDeserializer, NativeBarcodeArSettings nativeBarcodeArSettings, NativeJsonValue nativeJsonValue);

    public abstract void onSettingsDeserializationStarted(NativeBarcodeArDeserializer nativeBarcodeArDeserializer, NativeBarcodeArSettings nativeBarcodeArSettings, NativeJsonValue nativeJsonValue);
}
