package com.scandit.datacapture.frameworks.barcode.spark.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.spark.ui.SparkScanView;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewSettings;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.BrushDefaults;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: SparkScanViewDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/defaults/SparkScanViewDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "viewSettings", "Lcom/scandit/datacapture/barcode/spark/ui/SparkScanViewSettings;", "(Lcom/scandit/datacapture/barcode/spark/ui/SparkScanViewSettings;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SparkScanViewDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BARCODE_COUNT_BUTTON_VISIBLE = "barcodeCountButtonVisible";
    private static final String FIELD_BARCODE_FIND_BUTTON_VISIBLE = "barcodeFindButtonVisible";
    private static final String FIELD_BRUSH = "brush";
    private static final String FIELD_CAMERA_SWITCH_BUTTON_VISIBLE = "cameraSwitchButtonVisible";
    private static final String FIELD_CONTINUOUS_MODE_DISABLED_MESSAGE = "continuousModeDisabledMessage";
    private static final String FIELD_CONTINUOUS_MODE_ENABLED_MESSAGE = "continuousModeEnabledMessage";
    private static final String FIELD_HARDWARE_TRIGGER_SUPPORTED = "hardwareTriggerSupported";
    private static final String FIELD_LABEL_CAPTURE_BUTTON_VISIBLE = "labelCaptureButtonVisible";
    private static final String FIELD_PREVIEW_CLOSE_CONTROL_VISIBLE = "previewCloseControlVisible";
    private static final String FIELD_PREVIEW_SIZE_CONTROL_VISIBLE = "previewSizeControlVisible";
    private static final String FIELD_SCANNING_BEHAVIOR_BUTTON_VISIBLE = "scanningBehaviorButtonVisible";
    private static final String FIELD_SPARK_SCAN_VIEW_SETTINGS = "SparkScanViewSettings";
    private static final String FIELD_TARGET_MODE_BUTTON_VISIBLE = "targetModeButtonVisible";
    private static final String FIELD_TARGET_MODE_DISABLED_MESSAGE = "targetModeDisabledMessage";
    private static final String FIELD_TARGET_MODE_ENABLED_MESSAGE = "targetModeEnabledMessage";
    private static final String FIELD_TOAST_BACKGROUND_COLOR = "toastBackgroundColor";
    private static final String FIELD_TOAST_ENABLED = "toastEnabled";
    private static final String FIELD_TOAST_TEST_COLOR = "toastTextColor";
    private static final String FIELD_TOOLBAR_BACKGROUND_COLOR = "toolbarBackgroundColor";
    private static final String FIELD_TOOLBAR_ICON_ACTIVE_TINT_COLOR = "toolbarIconActiveTintColor";
    private static final String FIELD_TOOLBAR_ICON_INACTIVE_TINT_COLOR = "toolbarIconInactiveTintColor";
    private static final String FIELD_TORCH_CONTROL_VISIBLE = "torchControlVisible";
    private static final String FIELD_TRIGGER_BUTTON_ANIMATION_COLOR = "triggerButtonAnimationColor";
    private static final String FIELD_TRIGGER_BUTTON_COLLAPSED_COLOR = "triggerButtonCollapsedColor";
    private static final String FIELD_TRIGGER_BUTTON_EXPANDED_COLOR = "triggerButtonExpandedColor";
    private static final String FIELD_TRIGGER_BUTTON_IMAGE = "triggerButtonImage";
    private static final String FIELD_TRIGGER_BUTTON_TINT_COLOR = "triggerButtonTintColor";
    private static final String FIELD_TRIGGER_BUTTON_VISIBLE = "triggerButtonVisible";
    private static final String FIELD_ZOOM_SWITCH_CONTROL_VISIBLE = "zoomSwitchControlVisible";
    private final SparkScanViewSettings viewSettings;

    public /* synthetic */ SparkScanViewDefaults(SparkScanViewSettings sparkScanViewSettings, DefaultConstructorMarker defaultConstructorMarker) {
        this(sparkScanViewSettings);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private SparkScanViewDefaults(SparkScanViewSettings sparkScanViewSettings) {
        this.viewSettings = sparkScanViewSettings;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_BRUSH, BrushDefaults.INSTANCE.get(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultBrush())), TuplesKt.to(FIELD_TORCH_CONTROL_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTorchControlVisible())), TuplesKt.to(FIELD_TRIGGER_BUTTON_IMAGE, ExtensionsKt.toBase64(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTriggerButtonImage())), TuplesKt.to(FIELD_SCANNING_BEHAVIOR_BUTTON_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultScanningBehaviorButtonVisible())), TuplesKt.to(FIELD_BARCODE_COUNT_BUTTON_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultBarcodeCountButtonVisible())), TuplesKt.to(FIELD_BARCODE_FIND_BUTTON_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultBarcodeFindButtonVisible())), TuplesKt.to(FIELD_TARGET_MODE_BUTTON_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTargetModeButtonVisible())), TuplesKt.to(FIELD_TOOLBAR_BACKGROUND_COLOR, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultToolbarBackgroundColor()), TuplesKt.to(FIELD_TOOLBAR_ICON_ACTIVE_TINT_COLOR, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultToolbarIconActiveTintColor()), TuplesKt.to(FIELD_TOOLBAR_ICON_INACTIVE_TINT_COLOR, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultToolbarIconInactiveTintColor()), TuplesKt.to(FIELD_SPARK_SCAN_VIEW_SETTINGS, this.viewSettings.toJson()), TuplesKt.to(FIELD_ZOOM_SWITCH_CONTROL_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultZoomSwitchControlVisible())), TuplesKt.to(FIELD_HARDWARE_TRIGGER_SUPPORTED, Boolean.valueOf(SparkScanView.INSTANCE.getHardwareTriggerSupported())), TuplesKt.to(FIELD_TOAST_ENABLED, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultToastEnabled())), TuplesKt.to(FIELD_TOAST_BACKGROUND_COLOR, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultToastBackgroundColor()), TuplesKt.to(FIELD_TOAST_TEST_COLOR, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultToastTextColor()), TuplesKt.to(FIELD_TARGET_MODE_ENABLED_MESSAGE, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTargetModeEnabledMessage()), TuplesKt.to(FIELD_TARGET_MODE_DISABLED_MESSAGE, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTargetModeDisabledMessage()), TuplesKt.to(FIELD_CONTINUOUS_MODE_ENABLED_MESSAGE, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultContinuousModeEnabledMessage()), TuplesKt.to(FIELD_CONTINUOUS_MODE_DISABLED_MESSAGE, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultContinuousModeDisabledMessage()), TuplesKt.to(FIELD_PREVIEW_SIZE_CONTROL_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultPreviewSizeControlVisible())), TuplesKt.to(FIELD_CAMERA_SWITCH_BUTTON_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultCameraSwitchButtonVisible())), TuplesKt.to(FIELD_TRIGGER_BUTTON_COLLAPSED_COLOR, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTriggerButtonCollapsedColor()), TuplesKt.to(FIELD_TRIGGER_BUTTON_EXPANDED_COLOR, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTriggerButtonExpandedColor()), TuplesKt.to(FIELD_TRIGGER_BUTTON_ANIMATION_COLOR, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTriggerButtonAnimationColor()), TuplesKt.to(FIELD_TRIGGER_BUTTON_TINT_COLOR, com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTriggerButtonTintColor()), TuplesKt.to(FIELD_PREVIEW_CLOSE_CONTROL_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultPreviewCloseControlVisible())), TuplesKt.to(FIELD_TRIGGER_BUTTON_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultTriggerButtonVisible())), TuplesKt.to(FIELD_LABEL_CAPTURE_BUTTON_VISIBLE, Boolean.valueOf(com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults.getDefaultLabelCaptureButtonVisible())));
    }

    /* compiled from: SparkScanViewDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/defaults/SparkScanViewDefaults$Companion;", "", "()V", "FIELD_BARCODE_COUNT_BUTTON_VISIBLE", "", "FIELD_BARCODE_FIND_BUTTON_VISIBLE", "FIELD_BRUSH", "FIELD_CAMERA_SWITCH_BUTTON_VISIBLE", "FIELD_CONTINUOUS_MODE_DISABLED_MESSAGE", "FIELD_CONTINUOUS_MODE_ENABLED_MESSAGE", "FIELD_HARDWARE_TRIGGER_SUPPORTED", "FIELD_LABEL_CAPTURE_BUTTON_VISIBLE", "FIELD_PREVIEW_CLOSE_CONTROL_VISIBLE", "FIELD_PREVIEW_SIZE_CONTROL_VISIBLE", "FIELD_SCANNING_BEHAVIOR_BUTTON_VISIBLE", "FIELD_SPARK_SCAN_VIEW_SETTINGS", "FIELD_TARGET_MODE_BUTTON_VISIBLE", "FIELD_TARGET_MODE_DISABLED_MESSAGE", "FIELD_TARGET_MODE_ENABLED_MESSAGE", "FIELD_TOAST_BACKGROUND_COLOR", "FIELD_TOAST_ENABLED", "FIELD_TOAST_TEST_COLOR", "FIELD_TOOLBAR_BACKGROUND_COLOR", "FIELD_TOOLBAR_ICON_ACTIVE_TINT_COLOR", "FIELD_TOOLBAR_ICON_INACTIVE_TINT_COLOR", "FIELD_TORCH_CONTROL_VISIBLE", "FIELD_TRIGGER_BUTTON_ANIMATION_COLOR", "FIELD_TRIGGER_BUTTON_COLLAPSED_COLOR", "FIELD_TRIGGER_BUTTON_EXPANDED_COLOR", "FIELD_TRIGGER_BUTTON_IMAGE", "FIELD_TRIGGER_BUTTON_TINT_COLOR", "FIELD_TRIGGER_BUTTON_VISIBLE", "FIELD_ZOOM_SWITCH_CONTROL_VISIBLE", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new SparkScanViewDefaults(new SparkScanViewSettings(), null).toMap();
        }
    }
}
