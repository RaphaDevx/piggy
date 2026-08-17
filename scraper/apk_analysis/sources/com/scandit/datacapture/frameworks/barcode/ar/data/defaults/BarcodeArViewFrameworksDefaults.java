package com.scandit.datacapture.frameworks.barcode.ar.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArCircleHighlightPreset;
import com.scandit.datacapture.core.common.geometry.AnchorUtilsKt;
import com.scandit.datacapture.core.source.CameraPositionUtilsKt;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import com.scandit.datacapture.core.ui.style.BrushSerializer;
import com.scandit.datacapture.frameworks.barcode.ar.extensions.SerializationExtensionsKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: BarcodeArViewFrameworksDefaults.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0016¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/data/defaults/BarcodeArViewFrameworksDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "()V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeArViewFrameworksDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        BarcodeArViewDefaults barcodeArViewDefaults = BarcodeArViewDefaults.INSTANCE;
        Pair[] pairArr = new Pair[47];
        pairArr[0] = TuplesKt.to("defaultCameraPosition", CameraPositionUtilsKt.toJson(BarcodeArViewDefaults.getDefaultCameraPosition()));
        pairArr[1] = TuplesKt.to("defaultSoundEnabled", Boolean.valueOf(BarcodeArViewDefaults.getDefaultSoundEnabled()));
        pairArr[2] = TuplesKt.to("defaultHapticsEnabled", Boolean.valueOf(BarcodeArViewDefaults.getDefaultHapticsEnabled()));
        pairArr[3] = TuplesKt.to("defaultTorchControlPosition", AnchorUtilsKt.toJson(BarcodeArViewDefaults.getDefaultTorchControlPosition()));
        pairArr[4] = TuplesKt.to("defaultZoomControlPosition", AnchorUtilsKt.toJson(BarcodeArViewDefaults.getDefaultZoomControlPosition()));
        pairArr[5] = TuplesKt.to("defaultCameraSwitchControlPosition", AnchorUtilsKt.toJson(BarcodeArViewDefaults.getDefaultCameraSwitchControlPosition()));
        pairArr[6] = TuplesKt.to("defaultShouldShowTorchControl", Boolean.valueOf(BarcodeArViewDefaults.getDefaultShouldShowTorchControl()));
        pairArr[7] = TuplesKt.to("defaultShouldShowZoomControl", Boolean.valueOf(BarcodeArViewDefaults.getDefaultShouldShowZoomControl()));
        pairArr[8] = TuplesKt.to("defaultShouldShowCameraSwitchControl", Boolean.valueOf(BarcodeArViewDefaults.getDefaultShouldShowCameraSwitchControl()));
        pairArr[9] = TuplesKt.to("defaultRectangleHighlightBrush", BrushSerializer.toJson(BarcodeArViewDefaults.getDefaultRectangleHighlightBrush()));
        pairArr[10] = TuplesKt.to("defaultPopoverAnnotationTrigger", SerializationExtensionsKt.toJson(BarcodeArViewDefaults.getDefaultPopoverAnnotationTrigger()));
        pairArr[11] = TuplesKt.to("defaultBarcodeArPopoverAnnotationButtonTextSize", Float.valueOf(BarcodeArViewDefaults.getDefaultBarcodeArPopoverAnnotationButtonTextSize()));
        pairArr[12] = TuplesKt.to("defaultBarcodeArPopoverAnnotationButtonTextColor", ExtentionsKt.getHexString(BarcodeArViewDefaults.getDefaultBarcodeArPopoverAnnotationButtonTextColor()));
        pairArr[13] = TuplesKt.to("defaultStatusIconAnnotationTrigger", SerializationExtensionsKt.toJson(BarcodeArViewDefaults.getDefaultStatusIconAnnotationTrigger()));
        pairArr[14] = TuplesKt.to("defaultStatusIconAnnotationHasTip", Boolean.valueOf(BarcodeArViewDefaults.getDefaultStatusIconAnnotationHasTip()));
        pairArr[15] = TuplesKt.to("defaultStatusIconAnnotationIcon", SerializationExtensionsKt.toJson(BarcodeArViewDefaults.getDefaultStatusIconAnnotationIcon()));
        pairArr[16] = TuplesKt.to("defaultStatusIconAnnotationTextColor", ExtentionsKt.getHexString(BarcodeArViewDefaults.getDefaultStatusIconAnnotationTextColor()));
        pairArr[17] = TuplesKt.to("defaultStatusIconAnnotationBackgroundColor", ExtentionsKt.getHexString(BarcodeArViewDefaults.getDefaultStatusIconAnnotationBackgroundColor()));
        pairArr[18] = TuplesKt.to("defaultInfoAnnotationWidth", SerializationExtensionsKt.toJson(BarcodeArViewDefaults.getDefaultInfoAnnotationWidth()));
        pairArr[19] = TuplesKt.to("defaultInfoAnnotationBackgroundColor", ExtentionsKt.getHexString(BarcodeArViewDefaults.getDefaultInfoAnnotationBackgroundColor()));
        pairArr[20] = TuplesKt.to("defaultInfoAnnotationHasTip", Boolean.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationHasTip()));
        pairArr[21] = TuplesKt.to("defaultInfoAnnotationAnchor", SerializationExtensionsKt.toJson(BarcodeArViewDefaults.getDefaultInfoAnnotationAnchor()));
        pairArr[22] = TuplesKt.to("defaultInfoAnnotationTrigger", SerializationExtensionsKt.toJson(BarcodeArViewDefaults.getDefaultInfoAnnotationTrigger()));
        pairArr[23] = TuplesKt.to("defaultInfoAnnotationHeaderBackgroundColor", ExtentionsKt.getHexString(BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderBackgroundColor()));
        pairArr[24] = TuplesKt.to("defaultInfoAnnotationHeaderTextSize", Float.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderTextSize()));
        pairArr[25] = TuplesKt.to("defaultInfoAnnotationHeaderTextColor", ExtentionsKt.getHexString(BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderTextColor()));
        pairArr[26] = TuplesKt.to("defaultInfoAnnotationFooterBackgroundColor", ExtentionsKt.getHexString(BarcodeArViewDefaults.getDefaultInfoAnnotationFooterBackgroundColor()));
        pairArr[27] = TuplesKt.to("defaultInfoAnnotationFooterTextSize", Float.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationFooterTextSize()));
        pairArr[28] = TuplesKt.to("defaultInfoAnnotationFooterTextColor", ExtentionsKt.getHexString(BarcodeArViewDefaults.getDefaultInfoAnnotationFooterTextColor()));
        pairArr[29] = TuplesKt.to("defaultInfoAnnotationBodyElementTextSize", Float.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementTextSize()));
        pairArr[30] = TuplesKt.to("defaultInfoAnnotationBodyElementTextColor", ExtentionsKt.getHexString(BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementTextColor()));
        pairArr[31] = TuplesKt.to("defaultInfoAnnotationBodyElementLeftIconTappable", Boolean.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementLeftIconTappable()));
        pairArr[32] = TuplesKt.to("defaultInfoAnnotationBodyElementRightIconTappable", Boolean.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementRightIconTappable()));
        ScanditIcon defaultHighlightIcon = BarcodeArViewDefaults.getDefaultHighlightIcon();
        pairArr[33] = TuplesKt.to("defaultHighlightIcon", defaultHighlightIcon != null ? SerializationExtensionsKt.toJson(defaultHighlightIcon) : null);
        pairArr[34] = TuplesKt.to("defaultIsEntirePopoverTappable", Boolean.valueOf(BarcodeArViewDefaults.getDefaultIsEntirePopoverTappable()));
        pairArr[35] = TuplesKt.to("defaultStatusIconAnnotationText", BarcodeArViewDefaults.getDefaultStatusIconAnnotationText());
        pairArr[36] = TuplesKt.to("circleHighlightPresets", INSTANCE.getDefaultCircleHighlightPresets());
        pairArr[37] = TuplesKt.to("defaultInfoAnnotationEntireAnnotationTappable", Boolean.valueOf(BarcodeArViewDefaults.getDefaultInfoAnnotationEntireAnnotationTappable()));
        ScanditIcon defaultInfoAnnotationHeaderIcon = BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderIcon();
        pairArr[38] = TuplesKt.to("defaultInfoAnnotationHeaderIcon", defaultInfoAnnotationHeaderIcon != null ? SerializationExtensionsKt.toJson(defaultInfoAnnotationHeaderIcon) : null);
        pairArr[39] = TuplesKt.to("defaultInfoAnnotationHeaderText", BarcodeArViewDefaults.getDefaultInfoAnnotationHeaderText());
        ScanditIcon defaultInfoAnnotationFooterIcon = BarcodeArViewDefaults.getDefaultInfoAnnotationFooterIcon();
        pairArr[40] = TuplesKt.to("defaultInfoAnnotationFooterIcon", defaultInfoAnnotationFooterIcon != null ? SerializationExtensionsKt.toJson(defaultInfoAnnotationFooterIcon) : null);
        pairArr[41] = TuplesKt.to("defaultInfoAnnotationFooterText", BarcodeArViewDefaults.getDefaultInfoAnnotationFooterText());
        pairArr[42] = TuplesKt.to("defaultInfoAnnotationBodyElementText", BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementText());
        pairArr[43] = TuplesKt.to("defaultInfoAnnotationBodyElementStyledText", BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementStyledText());
        ScanditIcon defaultInfoAnnotationBodyElementLeftIcon = BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementLeftIcon();
        pairArr[44] = TuplesKt.to("defaultInfoAnnotationBodyElementLeftIcon", defaultInfoAnnotationBodyElementLeftIcon != null ? SerializationExtensionsKt.toJson(defaultInfoAnnotationBodyElementLeftIcon) : null);
        ScanditIcon defaultInfoAnnotationBodyElementRightIcon = BarcodeArViewDefaults.getDefaultInfoAnnotationBodyElementRightIcon();
        pairArr[45] = TuplesKt.to("defaultInfoAnnotationBodyElementRightIcon", defaultInfoAnnotationBodyElementRightIcon != null ? SerializationExtensionsKt.toJson(defaultInfoAnnotationBodyElementRightIcon) : null);
        pairArr[46] = TuplesKt.to("defaultBarcodeArPopoverAnnotationButtonEnabled", Boolean.valueOf(BarcodeArViewDefaults.getDefaultBarcodeArPopoverAnnotationButtonEnabled()));
        return MapsKt.mapOf(pairArr);
    }

    /* compiled from: BarcodeArViewFrameworksDefaults.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0007J\u001e\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0016\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0002¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/data/defaults/BarcodeArViewFrameworksDefaults$Companion;", "", "()V", "get", "", "", "getDefaultCircleHighlightFromPreset", "preset", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArCircleHighlightPreset;", "getDefaultCircleHighlightPresets", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final Map<String, Object> getDefaultCircleHighlightFromPreset(BarcodeArCircleHighlightPreset preset) {
            return MapsKt.mapOf(TuplesKt.to("brush", BrushSerializer.toJson(BarcodeArViewDefaults.getDefaultCircleHighlightBrushFromPreset(preset))), TuplesKt.to(RRWebVideoEvent.JsonKeys.SIZE, Float.valueOf(BarcodeArViewDefaults.getDefaultCircleHighlightSizeFromPreset(preset))));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Map<String, Object> getDefaultCircleHighlightPresets() {
            BarcodeArCircleHighlightPreset[] values = BarcodeArCircleHighlightPreset.values();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(values.length), 16));
            for (BarcodeArCircleHighlightPreset barcodeArCircleHighlightPreset : values) {
                Pair pair = TuplesKt.to(SerializationExtensionsKt.toJson(barcodeArCircleHighlightPreset), BarcodeArViewFrameworksDefaults.INSTANCE.getDefaultCircleHighlightFromPreset(barcodeArCircleHighlightPreset));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            return linkedHashMap;
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeArViewFrameworksDefaults().toMap();
        }
    }
}
