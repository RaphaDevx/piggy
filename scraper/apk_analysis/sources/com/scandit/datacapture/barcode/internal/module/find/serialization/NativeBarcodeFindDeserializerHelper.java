package com.scandit.datacapture.barcode.internal.module.find.serialization;

import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFind;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindSettings;
import com.scandit.datacapture.core.internal.sdk.json.NativeJsonValue;

/* loaded from: classes2.dex */
public abstract class NativeBarcodeFindDeserializerHelper {
    public abstract void applySettings(NativeBarcodeFind nativeBarcodeFind, NativeBarcodeFindSettings nativeBarcodeFindSettings);

    public abstract NativeBarcodeFind createMode();

    public abstract NativeBarcodeFindSettings createSettings();

    public abstract void updateModeFromJson(NativeBarcodeFind nativeBarcodeFind, NativeJsonValue nativeJsonValue);

    public abstract void updateSettingsFromJson(NativeBarcodeFindSettings nativeBarcodeFindSettings, NativeJsonValue nativeJsonValue);
}
