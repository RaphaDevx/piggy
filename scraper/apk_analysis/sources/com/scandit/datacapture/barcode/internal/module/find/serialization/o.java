package com.scandit.datacapture.barcode.internal.module.find.serialization;

import com.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindViewSettings;
import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategory;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import com.scandit.datacapture.core.json.JsonValue;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class o {
    public final ArrayList a = new ArrayList();

    public final BarcodeFindViewSettings a(JsonValue json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.a.clear();
        BarcodeFindViewSettings barcodeFindViewSettings = new BarcodeFindViewSettings(0, 0, false, false, 15, (DefaultConstructorMarker) null);
        int inListItemColor = barcodeFindViewSettings.getInListItemColor();
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter("inListItemColor", "key");
        NativeColor colorForKeyOrDefault = json.getA().getColorForKeyOrDefault("inListItemColor", NativeColorExtensionsKt.toNativeColor(inListItemColor));
        Intrinsics.checkNotNullExpressionValue(colorForKeyOrDefault, "getColorForKeyOrDefault(...)");
        int i = NativeColorExtensionsKt.toInt(colorForKeyOrDefault);
        int notInListItemColor = barcodeFindViewSettings.getNotInListItemColor();
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter("notInListItemColor", "key");
        NativeColor colorForKeyOrDefault2 = json.getA().getColorForKeyOrDefault("notInListItemColor", NativeColorExtensionsKt.toNativeColor(notInListItemColor));
        Intrinsics.checkNotNullExpressionValue(colorForKeyOrDefault2, "getColorForKeyOrDefault(...)");
        int i2 = NativeColorExtensionsKt.toInt(colorForKeyOrDefault2);
        int progressBarStartColor = barcodeFindViewSettings.getProgressBarStartColor();
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter("progressBarStartColor", "key");
        NativeColor colorForKeyOrDefault3 = json.getA().getColorForKeyOrDefault("progressBarStartColor", NativeColorExtensionsKt.toNativeColor(progressBarStartColor));
        Intrinsics.checkNotNullExpressionValue(colorForKeyOrDefault3, "getColorForKeyOrDefault(...)");
        int i3 = NativeColorExtensionsKt.toInt(colorForKeyOrDefault3);
        int progressBarFinishColor = barcodeFindViewSettings.getProgressBarFinishColor();
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter("progressBarFinishColor", "key");
        NativeColor colorForKeyOrDefault4 = json.getA().getColorForKeyOrDefault("progressBarFinishColor", NativeColorExtensionsKt.toNativeColor(progressBarFinishColor));
        Intrinsics.checkNotNullExpressionValue(colorForKeyOrDefault4, "getColorForKeyOrDefault(...)");
        int i4 = NativeColorExtensionsKt.toInt(colorForKeyOrDefault4);
        boolean byKeyAsBoolean = json.getByKeyAsBoolean("soundEnabled", barcodeFindViewSettings.getSoundEnabled());
        boolean byKeyAsBoolean2 = json.getByKeyAsBoolean("hapticEnabled", barcodeFindViewSettings.getHapticEnabled());
        boolean byKeyAsBoolean3 = json.getByKeyAsBoolean(BarcodeCountViewDeserializer.KEY_HW_TRIGGER_ENABLED, barcodeFindViewSettings.getCom.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer.KEY_HW_TRIGGER_ENABLED java.lang.String());
        Integer valueOf = json.contains(BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE) ? Integer.valueOf(json.getByKeyAsInt(BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE, 0)) : null;
        ScreenSizeCategory fromJson = json.contains("screenCategory") ? ScreenSizeCategory.INSTANCE.fromJson(json.requireByKeyAsString("screenCategory")) : null;
        BarcodeFindViewSettings barcodeFindViewSettings2 = new BarcodeFindViewSettings(i, i2, i3, i4, byKeyAsBoolean, byKeyAsBoolean2, byKeyAsBoolean3, valueOf);
        if (fromJson != null) {
            barcodeFindViewSettings2._setProperty("screenCategory", fromJson.getA());
        }
        this.a.addAll(json._getWarnings());
        return barcodeFindViewSettings2;
    }
}
