package com.scandit.datacapture.barcode.internal.module.spark.serialization;

import com.scandit.datacapture.barcode.data.CapturePreset;
import com.scandit.datacapture.barcode.spark.capture.SparkScanSettings;
import com.scandit.datacapture.barcode.spark.serialization.BatterySavingModeDeserializer;
import com.scandit.datacapture.core.json.JsonValue;
import java.util.LinkedHashSet;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements SparkScanSettingsDeserializer {
    @Override // com.scandit.datacapture.barcode.internal.module.spark.serialization.SparkScanSettingsDeserializer
    public final void a(SparkScanSettings settings, JsonValue json) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(json, "json");
        settings.getA().updateFromJson(json.getA());
        if (json.contains("batterySaving")) {
            settings.setBatterySaving(BatterySavingModeDeserializer.fromJson(json.requireByKeyAsString("batterySaving")));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.serialization.SparkScanSettingsDeserializer
    public final LinkedHashSet a(JsonValue json) {
        CapturePreset capturePreset;
        Intrinsics.checkNotNullParameter(json, "json");
        if (!json.contains("capturePresets")) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        JsonValue requireByKeyAsArray = json.requireByKeyAsArray("capturePresets");
        int size = (int) requireByKeyAsArray.getSize();
        for (int i = 0; i < size; i++) {
            JsonValue requireByIndex = requireByKeyAsArray.requireByIndex(i);
            CapturePreset[] values = CapturePreset.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    capturePreset = null;
                    break;
                }
                capturePreset = values[i2];
                String lowerCase = capturePreset.name().toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                if (Intrinsics.areEqual(lowerCase, requireByIndex.asString())) {
                    break;
                }
                i2++;
            }
            if (capturePreset != null) {
                linkedHashSet.add(capturePreset);
            }
        }
        return linkedHashSet;
    }
}
