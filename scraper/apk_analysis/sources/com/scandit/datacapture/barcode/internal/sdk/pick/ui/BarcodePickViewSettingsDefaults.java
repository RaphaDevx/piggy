package com.scandit.datacapture.barcode.internal.sdk.pick.ui;

import com.scandit.datacapture.barcode.count.serialization.BarcodeCountViewDeserializer;
import com.scandit.datacapture.barcode.filter.ui.overlay.BarcodeFilterHighlightSettings;
import com.scandit.datacapture.barcode.internal.module.pick.ui.NativeBarcodePickViewSettingsDefaults;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0014\bÆ\u0002\u0018\u00002\u00020\u0001R \u0010\t\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R \u0010\u0010\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u0012\u0004\b\u000f\u0010\b\u001a\u0004\b\r\u0010\u000eR \u0010\u0014\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0011\u0010\u0004\u0012\u0004\b\u0013\u0010\b\u001a\u0004\b\u0012\u0010\u0006R\u001a\u0010\u0017\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0016\u0010\b\u001a\u0004\b\u0015\u0010\u000eR\u001a\u0010\u001c\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\b\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001f\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001e\u0010\b\u001a\u0004\b\u001d\u0010\u001aR\u001a\u0010\"\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\b\u001a\u0004\b \u0010\u000eR\u001a\u0010%\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b$\u0010\b\u001a\u0004\b#\u0010\u001aR\u001a\u0010(\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b'\u0010\b\u001a\u0004\b&\u0010\u001aR\u001a\u0010+\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\b*\u0010\b\u001a\u0004\b)\u0010\u000eR\u001a\u0010.\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b-\u0010\b\u001a\u0004\b,\u0010\u001aR\u001a\u00101\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b0\u0010\b\u001a\u0004\b/\u0010\u001aR\u001a\u00104\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b3\u0010\b\u001a\u0004\b2\u0010\u001aR\u001a\u00107\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b6\u0010\b\u001a\u0004\b5\u0010\u001aR\u001a\u0010:\u001a\u00020\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b9\u0010\b\u001a\u0004\b8\u0010\u001aR\u001a\u0010=\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\b<\u0010\b\u001a\u0004\b;\u0010\u000eR\u001a\u0010@\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\b?\u0010\b\u001a\u0004\b>\u0010\u000eR\u001a\u0010C\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\bB\u0010\b\u001a\u0004\bA\u0010\u000eR\u001a\u0010H\u001a\u00020D8FX\u0087\u0004¢\u0006\f\u0012\u0004\bG\u0010\b\u001a\u0004\bE\u0010FR\u001a\u0010M\u001a\u00020I8FX\u0087\u0004¢\u0006\f\u0012\u0004\bL\u0010\b\u001a\u0004\bJ\u0010KR\u001a\u0010P\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\bO\u0010\b\u001a\u0004\bN\u0010\u000eR\u001c\u0010U\u001a\u0004\u0018\u00010Q8FX\u0087\u0004¢\u0006\f\u0012\u0004\bT\u0010\b\u001a\u0004\bR\u0010SR\"\u0010W\u001a\u0004\u0018\u00010V8\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\bW\u0010X\u0012\u0004\b[\u0010\b\u001a\u0004\bY\u0010ZR\u001a\u0010`\u001a\u00020\\8FX\u0087\u0004¢\u0006\f\u0012\u0004\b_\u0010\b\u001a\u0004\b]\u0010^R\u001a\u0010c\u001a\u00020\\8FX\u0087\u0004¢\u0006\f\u0012\u0004\bb\u0010\b\u001a\u0004\ba\u0010^R\u001a\u0010f\u001a\u00020\\8FX\u0087\u0004¢\u0006\f\u0012\u0004\be\u0010\b\u001a\u0004\bd\u0010^R\u001a\u0010i\u001a\u00020\\8FX\u0087\u0004¢\u0006\f\u0012\u0004\bh\u0010\b\u001a\u0004\bg\u0010^R\u001a\u0010l\u001a\u00020\\8FX\u0087\u0004¢\u0006\f\u0012\u0004\bk\u0010\b\u001a\u0004\bj\u0010^R\u001a\u0010o\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\bn\u0010\b\u001a\u0004\bm\u0010\u000e¨\u0006p"}, d2 = {"Lcom/scandit/datacapture/barcode/internal/sdk/pick/ui/BarcodePickViewSettingsDefaults;", "", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "a", "Lcom/scandit/datacapture/core/common/geometry/Anchor;", "getZoomButtonPosition", "()Lcom/scandit/datacapture/core/common/geometry/Anchor;", "getZoomButtonPosition$annotations", "()V", "zoomButtonPosition", "", "b", "Z", "getShowTorchButton", "()Z", "getShowTorchButton$annotations", "showTorchButton", "c", "getTorchButtonPosition", "getTorchButtonPosition$annotations", "torchButtonPosition", "getShowLoadingDialog", "getShowLoadingDialog$annotations", "showLoadingDialog", "", "getLoadingDialogTextForPicking", "()Ljava/lang/String;", "getLoadingDialogTextForPicking$annotations", "loadingDialogTextForPicking", "getLoadingDialogTextForUnpicking", "getLoadingDialogTextForUnpicking$annotations", "loadingDialogTextForUnpicking", "getShowGuidelines", "getShowGuidelines$annotations", "showGuidelines", "getInitialGuidelineText", "getInitialGuidelineText$annotations", "initialGuidelineText", "getMoveCloserGuidelineText", "getMoveCloserGuidelineText$annotations", "moveCloserGuidelineText", "getShowHints", "getShowHints$annotations", "showHints", "getOnFirstItemToPickFoundHintText", "getOnFirstItemToPickFoundHintText$annotations", "onFirstItemToPickFoundHintText", "getOnFirstItemPickCompletedHintText", "getOnFirstItemPickCompletedHintText$annotations", "onFirstItemPickCompletedHintText", "getOnFirstUnmarkedItemPickCompletedHintText", "getOnFirstUnmarkedItemPickCompletedHintText$annotations", "onFirstUnmarkedItemPickCompletedHintText", "getOnFirstItemUnpickCompletedHintText", "getOnFirstItemUnpickCompletedHintText$annotations", "onFirstItemUnpickCompletedHintText", "getTapShutterToPauseGuidelineText", "getTapShutterToPauseGuidelineText$annotations", "tapShutterToPauseGuidelineText", "getShowPauseButton", "getShowPauseButton$annotations", "showPauseButton", "getShowFinishButton", "getShowFinishButton$annotations", "showFinishButton", "getShowZoomButton", "getShowZoomButton$annotations", "showZoomButton", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "getUiButtonsOffset", "()Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "getUiButtonsOffset$annotations", "uiButtonsOffset", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle;", "getHighlightStyle", "()Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyle;", "getHighlightStyle$annotations", "highlightStyle", "getHardwareTriggerEnabled", "getHardwareTriggerEnabled$annotations", BarcodeCountViewDeserializer.KEY_HW_TRIGGER_ENABLED, "", "getHardwareTriggerKeyCode", "()Ljava/lang/Integer;", "getHardwareTriggerKeyCode$annotations", BarcodeCountViewDeserializer.KEY_HW_TRIGGER_KEY_CODE, "Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;", "filterHighlightSettings", "Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;", "getFilterHighlightSettings", "()Lcom/scandit/datacapture/barcode/filter/ui/overlay/BarcodeFilterHighlightSettings;", "getFilterHighlightSettings$annotations", "", "getTapAreaMinSize", "()F", "getTapAreaMinSize$annotations", "tapAreaMinSize", "getHighlightSizeToTapAreaFactor", "getHighlightSizeToTapAreaFactor$annotations", "highlightSizeToTapAreaFactor", "getMaxIconSizeForRectangularStyle", "getMaxIconSizeForRectangularStyle$annotations", "maxIconSizeForRectangularStyle", "getIconSizeForDotStyle", "getIconSizeForDotStyle$annotations", "iconSizeForDotStyle", "getIconPadding", "getIconPadding$annotations", "iconPadding", "getDrawDebugInfo", "getDrawDebugInfo$annotations", "drawDebugInfo", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodePickViewSettingsDefaults {
    public static final BarcodePickViewSettingsDefaults INSTANCE = new BarcodePickViewSettingsDefaults();

    /* renamed from: a, reason: from kotlin metadata */
    private static final Anchor zoomButtonPosition;

    /* renamed from: b, reason: from kotlin metadata */
    private static final boolean showTorchButton;

    /* renamed from: c, reason: from kotlin metadata */
    private static final Anchor torchButtonPosition;

    static {
        Anchor zoomButtonPosition2 = NativeBarcodePickViewSettingsDefaults.zoomButtonPosition();
        Intrinsics.checkNotNullExpressionValue(zoomButtonPosition2, "zoomButtonPosition(...)");
        zoomButtonPosition = zoomButtonPosition2;
        showTorchButton = NativeBarcodePickViewSettingsDefaults.showTorchButton();
        Anchor anchor = NativeBarcodePickViewSettingsDefaults.torchButtonPosition();
        Intrinsics.checkNotNullExpressionValue(anchor, "torchButtonPosition(...)");
        torchButtonPosition = anchor;
    }

    private BarcodePickViewSettingsDefaults() {
    }

    public static final boolean getDrawDebugInfo() {
        return false;
    }

    @JvmStatic
    public static /* synthetic */ void getDrawDebugInfo$annotations() {
    }

    public static final BarcodeFilterHighlightSettings getFilterHighlightSettings() {
        return null;
    }

    @JvmStatic
    public static /* synthetic */ void getFilterHighlightSettings$annotations() {
    }

    public static final boolean getHardwareTriggerEnabled() {
        return NativeBarcodePickViewSettingsDefaults.hardwareTriggerEnabled();
    }

    @JvmStatic
    public static /* synthetic */ void getHardwareTriggerEnabled$annotations() {
    }

    public static final Integer getHardwareTriggerKeyCode() {
        return NativeBarcodePickViewSettingsDefaults.hardwareTriggerKeyCode();
    }

    @JvmStatic
    public static /* synthetic */ void getHardwareTriggerKeyCode$annotations() {
    }

    public static final float getHighlightSizeToTapAreaFactor() {
        return 1.5f;
    }

    @JvmStatic
    public static /* synthetic */ void getHighlightSizeToTapAreaFactor$annotations() {
    }

    public static final BarcodePickViewHighlightStyle getHighlightStyle() {
        return new BarcodePickViewHighlightStyle.RectangularWithIcons();
    }

    @JvmStatic
    public static /* synthetic */ void getHighlightStyle$annotations() {
    }

    public static final float getIconPadding() {
        return 8.0f;
    }

    @JvmStatic
    public static /* synthetic */ void getIconPadding$annotations() {
    }

    public static final float getIconSizeForDotStyle() {
        return 18.0f;
    }

    @JvmStatic
    public static /* synthetic */ void getIconSizeForDotStyle$annotations() {
    }

    public static final String getInitialGuidelineText() {
        String initialGuidelineText = NativeBarcodePickViewSettingsDefaults.initialGuidelineText();
        Intrinsics.checkNotNullExpressionValue(initialGuidelineText, "initialGuidelineText(...)");
        return initialGuidelineText;
    }

    @JvmStatic
    public static /* synthetic */ void getInitialGuidelineText$annotations() {
    }

    public static final String getLoadingDialogTextForPicking() {
        String loadingDialogTextForPicking = NativeBarcodePickViewSettingsDefaults.loadingDialogTextForPicking();
        Intrinsics.checkNotNullExpressionValue(loadingDialogTextForPicking, "loadingDialogTextForPicking(...)");
        return loadingDialogTextForPicking;
    }

    @JvmStatic
    public static /* synthetic */ void getLoadingDialogTextForPicking$annotations() {
    }

    public static final String getLoadingDialogTextForUnpicking() {
        String loadingDialogTextForUnpicking = NativeBarcodePickViewSettingsDefaults.loadingDialogTextForUnpicking();
        Intrinsics.checkNotNullExpressionValue(loadingDialogTextForUnpicking, "loadingDialogTextForUnpicking(...)");
        return loadingDialogTextForUnpicking;
    }

    @JvmStatic
    public static /* synthetic */ void getLoadingDialogTextForUnpicking$annotations() {
    }

    public static final float getMaxIconSizeForRectangularStyle() {
        return 24.0f;
    }

    @JvmStatic
    public static /* synthetic */ void getMaxIconSizeForRectangularStyle$annotations() {
    }

    public static final String getMoveCloserGuidelineText() {
        String moveCloserGuidelineText = NativeBarcodePickViewSettingsDefaults.moveCloserGuidelineText();
        Intrinsics.checkNotNullExpressionValue(moveCloserGuidelineText, "moveCloserGuidelineText(...)");
        return moveCloserGuidelineText;
    }

    @JvmStatic
    public static /* synthetic */ void getMoveCloserGuidelineText$annotations() {
    }

    public static final String getOnFirstItemPickCompletedHintText() {
        String onFirstItemPickCompletedHintText = NativeBarcodePickViewSettingsDefaults.onFirstItemPickCompletedHintText();
        Intrinsics.checkNotNullExpressionValue(onFirstItemPickCompletedHintText, "onFirstItemPickCompletedHintText(...)");
        return onFirstItemPickCompletedHintText;
    }

    @JvmStatic
    public static /* synthetic */ void getOnFirstItemPickCompletedHintText$annotations() {
    }

    public static final String getOnFirstItemToPickFoundHintText() {
        String onFirstItemToPickFoundHintText = NativeBarcodePickViewSettingsDefaults.onFirstItemToPickFoundHintText();
        Intrinsics.checkNotNullExpressionValue(onFirstItemToPickFoundHintText, "onFirstItemToPickFoundHintText(...)");
        return onFirstItemToPickFoundHintText;
    }

    @JvmStatic
    public static /* synthetic */ void getOnFirstItemToPickFoundHintText$annotations() {
    }

    public static final String getOnFirstItemUnpickCompletedHintText() {
        String onFirstItemUnpickCompletedHintText = NativeBarcodePickViewSettingsDefaults.onFirstItemUnpickCompletedHintText();
        Intrinsics.checkNotNullExpressionValue(onFirstItemUnpickCompletedHintText, "onFirstItemUnpickCompletedHintText(...)");
        return onFirstItemUnpickCompletedHintText;
    }

    @JvmStatic
    public static /* synthetic */ void getOnFirstItemUnpickCompletedHintText$annotations() {
    }

    public static final String getOnFirstUnmarkedItemPickCompletedHintText() {
        String onFirstUnmarkedItemPickCompletedHintText = NativeBarcodePickViewSettingsDefaults.onFirstUnmarkedItemPickCompletedHintText();
        Intrinsics.checkNotNullExpressionValue(onFirstUnmarkedItemPickCompletedHintText, "onFirstUnmarkedItemPickCompletedHintText(...)");
        return onFirstUnmarkedItemPickCompletedHintText;
    }

    @JvmStatic
    public static /* synthetic */ void getOnFirstUnmarkedItemPickCompletedHintText$annotations() {
    }

    public static final boolean getShowFinishButton() {
        return NativeBarcodePickViewSettingsDefaults.showFinishButton();
    }

    @JvmStatic
    public static /* synthetic */ void getShowFinishButton$annotations() {
    }

    public static final boolean getShowGuidelines() {
        return NativeBarcodePickViewSettingsDefaults.showGuidelines();
    }

    @JvmStatic
    public static /* synthetic */ void getShowGuidelines$annotations() {
    }

    public static final boolean getShowHints() {
        return NativeBarcodePickViewSettingsDefaults.showHints();
    }

    @JvmStatic
    public static /* synthetic */ void getShowHints$annotations() {
    }

    public static final boolean getShowLoadingDialog() {
        return NativeBarcodePickViewSettingsDefaults.showLoadingDialog();
    }

    @JvmStatic
    public static /* synthetic */ void getShowLoadingDialog$annotations() {
    }

    public static final boolean getShowPauseButton() {
        return NativeBarcodePickViewSettingsDefaults.showPauseButton();
    }

    @JvmStatic
    public static /* synthetic */ void getShowPauseButton$annotations() {
    }

    public static final boolean getShowTorchButton() {
        return showTorchButton;
    }

    @JvmStatic
    public static /* synthetic */ void getShowTorchButton$annotations() {
    }

    public static final boolean getShowZoomButton() {
        return NativeBarcodePickViewSettingsDefaults.showZoomButton();
    }

    @JvmStatic
    public static /* synthetic */ void getShowZoomButton$annotations() {
    }

    public static final float getTapAreaMinSize() {
        return 60.0f;
    }

    @JvmStatic
    public static /* synthetic */ void getTapAreaMinSize$annotations() {
    }

    public static final String getTapShutterToPauseGuidelineText() {
        String tapShutterToPauseGuidelineText = NativeBarcodePickViewSettingsDefaults.tapShutterToPauseGuidelineText();
        Intrinsics.checkNotNullExpressionValue(tapShutterToPauseGuidelineText, "tapShutterToPauseGuidelineText(...)");
        return tapShutterToPauseGuidelineText;
    }

    @JvmStatic
    public static /* synthetic */ void getTapShutterToPauseGuidelineText$annotations() {
    }

    public static final Anchor getTorchButtonPosition() {
        return torchButtonPosition;
    }

    @JvmStatic
    public static /* synthetic */ void getTorchButtonPosition$annotations() {
    }

    public static final FloatWithUnit getUiButtonsOffset() {
        FloatWithUnit uiButtonsOffset = NativeBarcodePickViewSettingsDefaults.uiButtonsOffset();
        Intrinsics.checkNotNullExpressionValue(uiButtonsOffset, "uiButtonsOffset(...)");
        return uiButtonsOffset;
    }

    @JvmStatic
    public static /* synthetic */ void getUiButtonsOffset$annotations() {
    }

    public static final Anchor getZoomButtonPosition() {
        return zoomButtonPosition;
    }

    @JvmStatic
    public static /* synthetic */ void getZoomButtonPosition$annotations() {
    }
}
