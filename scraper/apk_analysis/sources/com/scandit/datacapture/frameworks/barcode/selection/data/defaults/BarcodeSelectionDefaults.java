package com.scandit.datacapture.frameworks.barcode.selection.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelection;
import com.scandit.datacapture.barcode.selection.feedback.BarcodeSelectionFeedback;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.CameraSettingsDefaults;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeSelectionDefaults.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0000\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fBm\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u0012\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u0016R\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/defaults/BarcodeSelectionDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "defaultFeedback", "Lcom/scandit/datacapture/barcode/selection/feedback/BarcodeSelectionFeedback;", "recommendedCameraSettingsDefaults", "Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraSettingsDefaults;", "settingsDefaults", "", "", "", "tapSelectionDefaults", "aimerSelectionDefaults", "overlayDefaults", "(Lcom/scandit/datacapture/barcode/selection/feedback/BarcodeSelectionFeedback;Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraSettingsDefaults;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeSelectionDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_AIMER_SELECTION = "BarcodeSelectionAimerSelection";
    private static final String FIELD_CAMERA_SETTINGS = "RecommendedCameraSettings";
    private static final String FIELD_FEEDBACK = "Feedback";
    private static final String FIELD_OVERLAY = "BarcodeSelectionBasicOverlay";
    private static final String FIELD_SETTINGS = "BarcodeSelectionSettings";
    private static final String FIELD_TAP_SELECTION = "BarcodeSelectionTapSelection";
    private final Map<String, Object> aimerSelectionDefaults;
    private final BarcodeSelectionFeedback defaultFeedback;
    private final Map<String, Object> overlayDefaults;
    private final CameraSettingsDefaults recommendedCameraSettingsDefaults;
    private final Map<String, Object> settingsDefaults;
    private final Map<String, Object> tapSelectionDefaults;

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    public BarcodeSelectionDefaults(BarcodeSelectionFeedback defaultFeedback, CameraSettingsDefaults recommendedCameraSettingsDefaults, Map<String, ? extends Object> settingsDefaults, Map<String, ? extends Object> tapSelectionDefaults, Map<String, ? extends Object> aimerSelectionDefaults, Map<String, ? extends Object> overlayDefaults) {
        Intrinsics.checkNotNullParameter(defaultFeedback, "defaultFeedback");
        Intrinsics.checkNotNullParameter(recommendedCameraSettingsDefaults, "recommendedCameraSettingsDefaults");
        Intrinsics.checkNotNullParameter(settingsDefaults, "settingsDefaults");
        Intrinsics.checkNotNullParameter(tapSelectionDefaults, "tapSelectionDefaults");
        Intrinsics.checkNotNullParameter(aimerSelectionDefaults, "aimerSelectionDefaults");
        Intrinsics.checkNotNullParameter(overlayDefaults, "overlayDefaults");
        this.defaultFeedback = defaultFeedback;
        this.recommendedCameraSettingsDefaults = recommendedCameraSettingsDefaults;
        this.settingsDefaults = settingsDefaults;
        this.tapSelectionDefaults = tapSelectionDefaults;
        this.aimerSelectionDefaults = aimerSelectionDefaults;
        this.overlayDefaults = overlayDefaults;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_OVERLAY, MapsKt.toMap(this.overlayDefaults)), TuplesKt.to(FIELD_SETTINGS, MapsKt.toMap(this.settingsDefaults)), TuplesKt.to(FIELD_FEEDBACK, this.defaultFeedback.toJson()), TuplesKt.to(FIELD_CAMERA_SETTINGS, this.recommendedCameraSettingsDefaults.toMap()), TuplesKt.to(FIELD_TAP_SELECTION, MapsKt.toMap(this.tapSelectionDefaults)), TuplesKt.to(FIELD_AIMER_SELECTION, MapsKt.toMap(this.aimerSelectionDefaults)));
    }

    /* compiled from: BarcodeSelectionDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/data/defaults/BarcodeSelectionDefaults$Companion;", "", "()V", "FIELD_AIMER_SELECTION", "", "FIELD_CAMERA_SETTINGS", "FIELD_FEEDBACK", "FIELD_OVERLAY", "FIELD_SETTINGS", "FIELD_TAP_SELECTION", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeSelectionDefaults(BarcodeSelectionFeedback.INSTANCE.defaultFeedback(), CameraSettingsDefaults.INSTANCE.create(BarcodeSelection.INSTANCE.createRecommendedCameraSettings()), BarcodeSelectionSettingsDefaults.INSTANCE.get(), BarcodeSelectionTapSelectionDefaults.INSTANCE.get(), BarcodeSelectionAimerSelectionDefaults.INSTANCE.get(), BarcodeSelectionBasicOverlayDefaults.INSTANCE.get()).toMap();
        }
    }
}
