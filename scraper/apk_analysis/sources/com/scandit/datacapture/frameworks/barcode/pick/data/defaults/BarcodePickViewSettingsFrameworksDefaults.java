package com.scandit.datacapture.frameworks.barcode.pick.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer;
import com.scandit.datacapture.barcode.internal.sdk.pick.ui.BarcodePickViewSettingsDefaults;
import com.scandit.datacapture.core.common.geometry.AnchorUtilsKt;
import com.scandit.datacapture.core.common.geometry.FloatWithUnitUtilsKt;
import com.scandit.datacapture.frameworks.barcode.data.defaults.BarcodeFilterHighlightSettingsDefaults;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodePickViewSettingsFrameworksDefaults.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0016¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickViewSettingsFrameworksDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "()V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickViewSettingsFrameworksDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ BarcodePickViewSettingsFrameworksDefaults(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private BarcodePickViewSettingsFrameworksDefaults() {
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to("HighlightStyle", BarcodePickViewSettingsDefaults.getHighlightStyle().toJson()), TuplesKt.to("initialGuidelineText", BarcodePickViewSettingsDefaults.getInitialGuidelineText()), TuplesKt.to("moveCloserGuidelineText", BarcodePickViewSettingsDefaults.getMoveCloserGuidelineText()), TuplesKt.to("loadingDialogTextForPicking", BarcodePickViewSettingsDefaults.getLoadingDialogTextForPicking()), TuplesKt.to("loadingDialogTextForUnpicking", BarcodePickViewSettingsDefaults.getLoadingDialogTextForUnpicking()), TuplesKt.to("showLoadingDialog", Boolean.valueOf(BarcodePickViewSettingsDefaults.getShowLoadingDialog())), TuplesKt.to("showZoomButton", Boolean.valueOf(BarcodePickViewSettingsDefaults.getShowZoomButton())), TuplesKt.to("onFirstItemPickCompletedHintText", BarcodePickViewSettingsDefaults.getOnFirstItemPickCompletedHintText()), TuplesKt.to("onFirstItemToPickFoundHintText", BarcodePickViewSettingsDefaults.getOnFirstItemToPickFoundHintText()), TuplesKt.to("onFirstItemUnpickCompletedHintText", BarcodePickViewSettingsDefaults.getOnFirstItemUnpickCompletedHintText()), TuplesKt.to("onFirstUnmarkedItemPickCompletedHintText", BarcodePickViewSettingsDefaults.getOnFirstUnmarkedItemPickCompletedHintText()), TuplesKt.to("showGuidelines", Boolean.valueOf(BarcodePickViewSettingsDefaults.getShowGuidelines())), TuplesKt.to("showHints", Boolean.valueOf(BarcodePickViewSettingsDefaults.getShowHints())), TuplesKt.to("showFinishButton", Boolean.valueOf(BarcodePickViewSettingsDefaults.getShowFinishButton())), TuplesKt.to("showPauseButton", Boolean.valueOf(BarcodePickViewSettingsDefaults.getShowPauseButton())), TuplesKt.to("zoomButtonPosition", AnchorUtilsKt.toJson(BarcodePickViewSettingsDefaults.getZoomButtonPosition())), TuplesKt.to("showTorchButton", Boolean.valueOf(BarcodePickViewSettingsDefaults.getShowTorchButton())), TuplesKt.to("torchButtonPosition", AnchorUtilsKt.toJson(BarcodePickViewSettingsDefaults.getTorchButtonPosition())), TuplesKt.to("tapShutterToPauseGuidelineText", BarcodePickViewSettingsDefaults.getTapShutterToPauseGuidelineText()), TuplesKt.to("uiButtonsOffset", FloatWithUnitUtilsKt.toJson(BarcodePickViewSettingsDefaults.getUiButtonsOffset())), TuplesKt.to(BarcodeCountViewDeserializer.KEY_HW_TRIGGER_ENABLED, Boolean.valueOf(BarcodePickViewSettingsDefaults.getHardwareTriggerEnabled())), TuplesKt.to(BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE, BarcodePickViewSettingsDefaults.getHardwareTriggerKeyCode()), TuplesKt.to("filterHighlightSettings", BarcodeFilterHighlightSettingsDefaults.INSTANCE.create(BarcodePickViewSettingsDefaults.getFilterHighlightSettings())));
    }

    /* compiled from: BarcodePickViewSettingsFrameworksDefaults.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/defaults/BarcodePickViewSettingsFrameworksDefaults$Companion;", "", "()V", "get", "", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Map<String, Object> get() {
            return new BarcodePickViewSettingsFrameworksDefaults(null).toMap();
        }
    }
}
