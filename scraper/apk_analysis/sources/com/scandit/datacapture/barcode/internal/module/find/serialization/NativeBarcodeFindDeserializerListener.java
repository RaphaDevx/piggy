package com.scandit.datacapture.barcode.internal.module.find.serialization;

import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSettings;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeFindDeserializerListener {
    public abstract void onModeDeserializationFinished(NativeBarcodeFindDeserializer nativeBarcodeFindDeserializer, NativeBarcodeFind nativeBarcodeFind, NativeJsonValue nativeJsonValue);

    public abstract void onModeDeserializationStarted(NativeBarcodeFindDeserializer nativeBarcodeFindDeserializer, NativeBarcodeFind nativeBarcodeFind, NativeJsonValue nativeJsonValue);

    public abstract void onSettingsDeserializationFinished(NativeBarcodeFindDeserializer nativeBarcodeFindDeserializer, NativeBarcodeFindSettings nativeBarcodeFindSettings, NativeJsonValue nativeJsonValue);

    public abstract void onSettingsDeserializationStarted(NativeBarcodeFindDeserializer nativeBarcodeFindDeserializer, NativeBarcodeFindSettings nativeBarcodeFindSettings, NativeJsonValue nativeJsonValue);
}
