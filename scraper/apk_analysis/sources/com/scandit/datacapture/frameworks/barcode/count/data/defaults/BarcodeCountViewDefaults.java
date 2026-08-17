package com.scandit.datacapture.frameworks.barcode.count.data.defaults;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewStyleExtensionKt;
import com.scandit.datacapture.core.common.geometry.AnchorUtilsKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.BrushDefaults;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeCountViewDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeCountViewDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountViewDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELDS_SHOULD_SHOW_STATUS_MODE_BUTTON = "shouldShowStatusModeButton";
    private static final String FIELD_ACCEPTED_BRUSH = "acceptedBrush";
    private static final String FIELD_CLEAR_HIGHLIGHTS_BUTTON_CONTENT_DESCRIPTION = "clearHighlightsButtonContentDescription";
    private static final String FIELD_CLEAR_HIGHLIGHT_BUTTON_TEXT = "clearHighlightsButtonText";
    private static final String FIELD_EXIT_BUTTON_CONTENT_DESCRIPTION = "exitButtonContentDescription";
    private static final String FIELD_EXIT_BUTTON_TEXT = "exitButtonText";
    private static final String FIELD_FLOATING_SHUTTER_BUTTON_CONTENT_DESCRIPTION = "floatingShutterButtonContentDescription";
    private static final String FIELD_HARDWARE_TRIGGER_SUPPORTED = "hardwareTriggerSupported";
    private static final String FIELD_LIST_BUTTON_CONTENT_DESCRIPTION = "listButtonContentDescription";
    private static final String FIELD_NOT_IN_LIST_BRUSH = "notInListBrush";
    private static final String FIELD_RECOGNIZED_BRUSH = "recognizedBrush";
    private static final String FIELD_REJECTED_BRUSH = "rejectedBrush";
    private static final String FIELD_SHOULD_CLEAR_HIGHLIGHTS_BUTTON = "shouldShowClearHighlightsButton";
    private static final String FIELD_SHOULD_SHOW_EXIT_BUTTON = "shouldShowExitButton";
    private static final String FIELD_SHOULD_SHOW_FLOATING_SHUTTER_BUTTON = "shouldShowFloatingShutterButton";
    private static final String FIELD_SHOULD_SHOW_HINTS = "shouldShowHints";
    private static final String FIELD_SHOULD_SHOW_LIST_BUTTON = "shouldShowListButton";
    private static final String FIELD_SHOULD_SHOW_LIST_PROGRESS_BAR = "shouldShowListProgressBar";
    private static final String FIELD_SHOULD_SHOW_SCAN_AREA_GUIDES = "shouldShowScanAreaGuides";
    private static final String FIELD_SHOULD_SHOW_SHUTTER_BUTTON = "shouldShowShutterButton";
    private static final String FIELD_SHOULD_SHOW_SINGLE_SCAN_BUTTON = "shouldShowSingleScanButton";
    private static final String FIELD_SHOULD_SHOW_TOOLBAR = "shouldShowToolbar";
    private static final String FIELD_SHOULD_SHOW_TORCH_CONTROL = "shouldShowTorchControl";
    private static final String FIELD_SHOULD_SHOW_USER_GUIDANCE_VIEW = "shouldShowUserGuidanceView";
    private static final String FIELD_SHUTTER_BUTTON_CONTENT_DESCRIPTION = "shutterButtonContentDescription";
    private static final String FIELD_SINGLE_SCAN_BUTTON_CONTENT_DESCRIPTION = "singleScanButtonContentDescription";
    private static final String FIELD_STYLE = "style";
    private static final String FIELD_TAP_TO_UNCOUNT = "tapToUncountEnabled";
    private static final String FIELD_TEXT_FOR_SCANNING_HINT = "textForScanningHint";
    private static final String FIELD_TEXT_FOR_TAP_SHUTTER_TO_SCAN_HINT = "textForTapShutterToScanHint";
    private static final String FIELD_TEXT_MOVE_CLOSER_AND_RESCAN_HINT = "textForMoveCloserAndRescanHint";
    private static final String FIELD_TEXT_MOVE_FURTHER_AND_RESCAN_HINT = "textForMoveFurtherAndRescanHint";
    private static final String FIELD_TEXT_TAP_TO_UNCOUNT_HINT = "textForTapToUncountHint";
    private static final String FIELD_TOOLBAR_SETTINGS = "toolbarSettings";
    private static final String FIELD_TORCH_CONTROL_POSITION = "torchControlPosition";
    private final Context context;

    public /* synthetic */ BarcodeCountViewDefaults(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @JvmStatic
    public static final Map<String, Object> get(Context context) {
        return INSTANCE.get(context);
    }

    private BarcodeCountViewDefaults(Context context) {
        this.context = context;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_STYLE, BarcodeCountViewStyleExtensionKt.toJson(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getStyle())), TuplesKt.to(FIELD_SHOULD_SHOW_USER_GUIDANCE_VIEW, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowUserGuidanceView())), TuplesKt.to(FIELD_SHOULD_SHOW_LIST_BUTTON, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowListButton())), TuplesKt.to(FIELD_SHOULD_SHOW_EXIT_BUTTON, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowExitButton())), TuplesKt.to(FIELD_SHOULD_SHOW_SHUTTER_BUTTON, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowShutterButton())), TuplesKt.to(FIELD_SHOULD_SHOW_HINTS, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowHints())), TuplesKt.to(FIELD_SHOULD_CLEAR_HIGHLIGHTS_BUTTON, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowClearHighlightsButton())), TuplesKt.to(FIELD_SHOULD_SHOW_FLOATING_SHUTTER_BUTTON, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowFloatingShutterButton())), TuplesKt.to(FIELD_NOT_IN_LIST_BRUSH, BrushDefaults.INSTANCE.get(BarcodeCountView.INSTANCE.defaultNotInListBrush())), TuplesKt.to(FIELD_RECOGNIZED_BRUSH, BrushDefaults.INSTANCE.get(BarcodeCountView.INSTANCE.defaultRecognizedBrush())), TuplesKt.to(FIELD_ACCEPTED_BRUSH, BrushDefaults.INSTANCE.get(BarcodeCountView.INSTANCE.defaultAcceptedBrush())), TuplesKt.to(FIELD_REJECTED_BRUSH, BrushDefaults.INSTANCE.get(BarcodeCountView.INSTANCE.defaultRejectedBrush())), TuplesKt.to(FIELD_SHOULD_SHOW_SCAN_AREA_GUIDES, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowScanAreaGuides())), TuplesKt.to(FIELD_SHOULD_SHOW_SINGLE_SCAN_BUTTON, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowSingleScanButton())), TuplesKt.to(FIELD_SHOULD_SHOW_TOOLBAR, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowToolbar())), TuplesKt.to(FIELD_CLEAR_HIGHLIGHT_BUTTON_TEXT, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getClearHighlightsButtonText()), TuplesKt.to(FIELD_EXIT_BUTTON_TEXT, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getExitButtonText()), TuplesKt.to(FIELD_TEXT_FOR_TAP_SHUTTER_TO_SCAN_HINT, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getTextForTapShutterToScanHint()), TuplesKt.to(FIELD_TEXT_FOR_SCANNING_HINT, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getTextForScanningHint()), TuplesKt.to(FIELD_TEXT_MOVE_CLOSER_AND_RESCAN_HINT, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getTextForMoveCloserAndRescanHint()), TuplesKt.to(FIELD_TEXT_MOVE_FURTHER_AND_RESCAN_HINT, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getTextForMoveFurtherAndRescanHint()), TuplesKt.to(FIELD_TOOLBAR_SETTINGS, BarcodeCountToolbarSettingsDefaults.INSTANCE.get(this.context)), TuplesKt.to(FIELD_LIST_BUTTON_CONTENT_DESCRIPTION, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getListButtonContentDescription()), TuplesKt.to(FIELD_EXIT_BUTTON_CONTENT_DESCRIPTION, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getExitButtonContentDescription()), TuplesKt.to(FIELD_SHUTTER_BUTTON_CONTENT_DESCRIPTION, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShutterButtonContentDescription()), TuplesKt.to(FIELD_FLOATING_SHUTTER_BUTTON_CONTENT_DESCRIPTION, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getFloatingShutterButtonContentDescription()), TuplesKt.to(FIELD_CLEAR_HIGHLIGHTS_BUTTON_CONTENT_DESCRIPTION, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getClearHighlightsButtonContentDescription()), TuplesKt.to(FIELD_SINGLE_SCAN_BUTTON_CONTENT_DESCRIPTION, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getSingleScanButtonContentDescription()), TuplesKt.to(FIELD_SHOULD_SHOW_LIST_PROGRESS_BAR, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowListProgressBar())), TuplesKt.to(FIELD_SHOULD_SHOW_TORCH_CONTROL, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowTorchControl())), TuplesKt.to(FIELD_TORCH_CONTROL_POSITION, AnchorUtilsKt.toJson(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getTorchControlPosition())), TuplesKt.to(FIELD_TAP_TO_UNCOUNT, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getTapToUncountEnabled())), TuplesKt.to(FIELD_TEXT_TAP_TO_UNCOUNT_HINT, com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getTextForTapToUncountHint()), TuplesKt.to(FIELDS_SHOULD_SHOW_STATUS_MODE_BUTTON, Boolean.valueOf(com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults.INSTANCE.getShouldShowStatusModeButton())), TuplesKt.to(FIELD_HARDWARE_TRIGGER_SUPPORTED, Boolean.valueOf(BarcodeCountView.INSTANCE.getHardwareTriggerSupported())));
    }

    /* compiled from: BarcodeCountViewDefaults.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010(2\u0006\u0010)\u001a\u00020*H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeCountViewDefaults$Companion;", "", "()V", "FIELDS_SHOULD_SHOW_STATUS_MODE_BUTTON", "", "FIELD_ACCEPTED_BRUSH", "FIELD_CLEAR_HIGHLIGHTS_BUTTON_CONTENT_DESCRIPTION", "FIELD_CLEAR_HIGHLIGHT_BUTTON_TEXT", "FIELD_EXIT_BUTTON_CONTENT_DESCRIPTION", "FIELD_EXIT_BUTTON_TEXT", "FIELD_FLOATING_SHUTTER_BUTTON_CONTENT_DESCRIPTION", "FIELD_HARDWARE_TRIGGER_SUPPORTED", "FIELD_LIST_BUTTON_CONTENT_DESCRIPTION", "FIELD_NOT_IN_LIST_BRUSH", "FIELD_RECOGNIZED_BRUSH", "FIELD_REJECTED_BRUSH", "FIELD_SHOULD_CLEAR_HIGHLIGHTS_BUTTON", "FIELD_SHOULD_SHOW_EXIT_BUTTON", "FIELD_SHOULD_SHOW_FLOATING_SHUTTER_BUTTON", "FIELD_SHOULD_SHOW_HINTS", "FIELD_SHOULD_SHOW_LIST_BUTTON", "FIELD_SHOULD_SHOW_LIST_PROGRESS_BAR", "FIELD_SHOULD_SHOW_SCAN_AREA_GUIDES", "FIELD_SHOULD_SHOW_SHUTTER_BUTTON", "FIELD_SHOULD_SHOW_SINGLE_SCAN_BUTTON", "FIELD_SHOULD_SHOW_TOOLBAR", "FIELD_SHOULD_SHOW_TORCH_CONTROL", "FIELD_SHOULD_SHOW_USER_GUIDANCE_VIEW", "FIELD_SHUTTER_BUTTON_CONTENT_DESCRIPTION", "FIELD_SINGLE_SCAN_BUTTON_CONTENT_DESCRIPTION", "FIELD_STYLE", "FIELD_TAP_TO_UNCOUNT", "FIELD_TEXT_FOR_SCANNING_HINT", "FIELD_TEXT_FOR_TAP_SHUTTER_TO_SCAN_HINT", "FIELD_TEXT_MOVE_CLOSER_AND_RESCAN_HINT", "FIELD_TEXT_MOVE_FURTHER_AND_RESCAN_HINT", "FIELD_TEXT_TAP_TO_UNCOUNT_HINT", "FIELD_TOOLBAR_SETTINGS", "FIELD_TORCH_CONTROL_POSITION", "get", "", "context", "Landroid/content/Context;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new BarcodeCountViewDefaults(context, null).toMap();
        }
    }
}
