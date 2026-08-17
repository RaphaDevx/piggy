package com.scandit.datacapture.frameworks.barcode.batch.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.batch.capture.BarcodeBatch;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.CameraSettingsDefaults;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodeBatchDefaults.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB3\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\u0002\u0010\u0007J\"\u0010\b\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00030\u0003H\u0016R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/defaults/BarcodeBatchDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "recommendedCameraSettings", "", "", "", "batchBasicOverlayDefaults", "(Ljava/util/Map;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeBatchDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_RECOMMENDED_CAMERA_SETTINGS = "RecommendedCameraSettings";
    private static final String FIELD_TRACKING_BASIC_OVERLAY = "BarcodeBatchBasicOverlay";
    private final Map<String, Object> batchBasicOverlayDefaults;
    private final Map<String, Object> recommendedCameraSettings;

    public /* synthetic */ BarcodeBatchDefaults(Map map, Map map2, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, map2);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private BarcodeBatchDefaults(Map<String, ? extends Object> map, Map<String, ? extends Object> map2) {
        this.recommendedCameraSettings = map;
        this.batchBasicOverlayDefaults = map2;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Map<String, Object>> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_RECOMMENDED_CAMERA_SETTINGS, MapsKt.toMap(this.recommendedCameraSettings)), TuplesKt.to(FIELD_TRACKING_BASIC_OVERLAY, this.batchBasicOverlayDefaults));
    }

    /* compiled from: BarcodeBatchDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/batch/data/defaults/BarcodeBatchDefaults$Companion;", "", "()V", "FIELD_RECOMMENDED_CAMERA_SETTINGS", "", "FIELD_TRACKING_BASIC_OVERLAY", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeBatchDefaults(CameraSettingsDefaults.INSTANCE.create(BarcodeBatch.INSTANCE.createRecommendedCameraSettings()).toMap(), BarcodeBatchBasicOverlayDefaults.INSTANCE.get(), null).toMap();
        }
    }
}
