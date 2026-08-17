package com.scandit.datacapture.frameworks.barcode.selection.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelection;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionSettings;
import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlay;
import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayStyle;
import com.scandit.datacapture.barcode.selection.ui.overlay.BarcodeSelectionBasicOverlayStyleUtilsKt;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.BrushDefaults;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodeSelectionBasicOverlayDefaults.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00062\u0006\u0010\t\u001a\u00020\nH\u0002J\u0014\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/defaults/BarcodeSelectionBasicOverlayDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "overlay", "Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlay;", "(Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlay;)V", "createBrushDefaultsForStyle", "", "", "", "style", "Lcom/scandit/datacapture/barcode/selection/ui/overlay/BarcodeSelectionBasicOverlayStyle;", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeSelectionBasicOverlayDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_AIMED_BRUSH = "DefaultAimedBrush";
    private static final String FIELD_DEFAULT_STYLE = "defaultStyle";
    private static final String FIELD_FROZEN_BACKGROUND_COLOR = "frozenBackgroundColor";
    private static final String FIELD_SELECTED_BRUSH = "DefaultSelectedBrush";
    private static final String FIELD_SELECTING_BRUSH = "DefaultSelectingBrush";
    private static final String FIELD_SHOULD_SHOW_HINTS = "shouldShowHints";
    private static final String FIELD_STYLES = "styles";
    private static final String FIELD_TRACKED_BRUSH = "DefaultTrackedBrush";
    private final BarcodeSelectionBasicOverlay overlay;

    public /* synthetic */ BarcodeSelectionBasicOverlayDefaults(BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay, DefaultConstructorMarker defaultConstructorMarker) {
        this(barcodeSelectionBasicOverlay);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private BarcodeSelectionBasicOverlayDefaults(BarcodeSelectionBasicOverlay barcodeSelectionBasicOverlay) {
        this.overlay = barcodeSelectionBasicOverlay;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_DEFAULT_STYLE, BarcodeSelectionBasicOverlayStyleUtilsKt.toJson(this.overlay.getStyle())), TuplesKt.to(FIELD_STYLES, MapsKt.mapOf(TuplesKt.to(BarcodeSelectionBasicOverlayStyleUtilsKt.toJson(BarcodeSelectionBasicOverlayStyle.DOT), createBrushDefaultsForStyle(BarcodeSelectionBasicOverlayStyle.DOT)), TuplesKt.to(BarcodeSelectionBasicOverlayStyleUtilsKt.toJson(BarcodeSelectionBasicOverlayStyle.FRAME), createBrushDefaultsForStyle(BarcodeSelectionBasicOverlayStyle.FRAME)))), TuplesKt.to(FIELD_SHOULD_SHOW_HINTS, Boolean.valueOf(this.overlay.getShouldShowHints())), TuplesKt.to(FIELD_FROZEN_BACKGROUND_COLOR, ExtentionsKt.getHexString(this.overlay.getFrozenBackgroundColor())));
    }

    private final Map<String, Object> createBrushDefaultsForStyle(BarcodeSelectionBasicOverlayStyle style) {
        BarcodeSelectionBasicOverlay newInstance = BarcodeSelectionBasicOverlay.INSTANCE.newInstance(BarcodeSelection.INSTANCE.forDataCaptureContext(null, new BarcodeSelectionSettings()), null, style);
        return MapsKt.mapOf(TuplesKt.to(FIELD_AIMED_BRUSH, BrushDefaults.INSTANCE.get(newInstance.getAimedBrush())), TuplesKt.to(FIELD_SELECTED_BRUSH, BrushDefaults.INSTANCE.get(newInstance.getSelectedBrush())), TuplesKt.to(FIELD_SELECTING_BRUSH, BrushDefaults.INSTANCE.get(newInstance.getSelectingBrush())), TuplesKt.to(FIELD_TRACKED_BRUSH, BrushDefaults.INSTANCE.get(newInstance.getTrackedBrush())));
    }

    /* compiled from: BarcodeSelectionBasicOverlayDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/defaults/BarcodeSelectionBasicOverlayDefaults$Companion;", "", "()V", "FIELD_AIMED_BRUSH", "", "FIELD_DEFAULT_STYLE", "FIELD_FROZEN_BACKGROUND_COLOR", "FIELD_SELECTED_BRUSH", "FIELD_SELECTING_BRUSH", "FIELD_SHOULD_SHOW_HINTS", "FIELD_STYLES", "FIELD_TRACKED_BRUSH", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeSelectionBasicOverlayDefaults(BarcodeSelectionBasicOverlay.INSTANCE.newInstance(BarcodeSelection.INSTANCE.forDataCaptureContext(null, new BarcodeSelectionSettings()), null), null).toMap();
        }
    }
}
