package com.scandit.datacapture.frameworks.barcode.capture.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.capture.BarcodeCapture;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.CameraSettingsDefaults;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeCaptureDefaults.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H\u0016R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/data/defaults/BarcodeCaptureDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "recommendedCameraSettings", "Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraSettingsDefaults;", "barcodeCaptureSettings", "", "", "", "barcodeCaptureOverlay", "(Lcom/scandit/datacapture/frameworks/core/data/defaults/CameraSettingsDefaults;Ljava/util/Map;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCaptureDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BARCODE_CAPTURE_OVERLAY = "BarcodeCaptureOverlay";
    private static final String FIELD_BARCODE_CAPTURE_SETTINGS = "BarcodeCaptureSettings";
    private static final String FIELD_RECOMMENDED_CAMERA_SETTINGS = "RecommendedCameraSettings";
    private final Map<String, Object> barcodeCaptureOverlay;
    private final Map<String, Object> barcodeCaptureSettings;
    private final CameraSettingsDefaults recommendedCameraSettings;

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    public BarcodeCaptureDefaults(CameraSettingsDefaults recommendedCameraSettings, Map<String, ? extends Object> barcodeCaptureSettings, Map<String, ? extends Object> barcodeCaptureOverlay) {
        Intrinsics.checkNotNullParameter(recommendedCameraSettings, "recommendedCameraSettings");
        Intrinsics.checkNotNullParameter(barcodeCaptureSettings, "barcodeCaptureSettings");
        Intrinsics.checkNotNullParameter(barcodeCaptureOverlay, "barcodeCaptureOverlay");
        this.recommendedCameraSettings = recommendedCameraSettings;
        this.barcodeCaptureSettings = barcodeCaptureSettings;
        this.barcodeCaptureOverlay = barcodeCaptureOverlay;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_RECOMMENDED_CAMERA_SETTINGS, this.recommendedCameraSettings.toMap()), TuplesKt.to(FIELD_BARCODE_CAPTURE_SETTINGS, this.barcodeCaptureSettings), TuplesKt.to(FIELD_BARCODE_CAPTURE_OVERLAY, MapsKt.toMap(this.barcodeCaptureOverlay)));
    }

    /* compiled from: BarcodeCaptureDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/capture/data/defaults/BarcodeCaptureDefaults$Companion;", "", "()V", "FIELD_BARCODE_CAPTURE_OVERLAY", "", "FIELD_BARCODE_CAPTURE_SETTINGS", "FIELD_RECOMMENDED_CAMERA_SETTINGS", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeCaptureDefaults(CameraSettingsDefaults.INSTANCE.create(BarcodeCapture.INSTANCE.createRecommendedCameraSettings()), BarcodeCaptureSettingsDefaults.INSTANCE.get(), BarcodeCaptureOverlayDefaults.INSTANCE.get()).toMap();
        }
    }
}
