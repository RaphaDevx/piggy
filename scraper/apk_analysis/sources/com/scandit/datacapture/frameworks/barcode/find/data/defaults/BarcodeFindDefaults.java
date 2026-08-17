package com.scandit.datacapture.frameworks.barcode.find.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.barcode.find.feedback.BarcodeFindFeedback;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.CameraSettingsDefaults;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BarcodeFindDefaults.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \f2\u00020\u0001:\u0001\fBQ\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/data/defaults/BarcodeFindDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "recommendedCameraSettings", "", "", "", "barcodeFindFeedback", "Lcom/scandit/datacapture/barcode/find/feedback/BarcodeFindFeedback;", "barcodeFindView", "barcodeFindViewSettings", "(Ljava/util/Map;Lcom/scandit/datacapture/barcode/find/feedback/BarcodeFindFeedback;Ljava/util/Map;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeFindDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BARCODE_FIND_FEEDBACK = "BarcodeFindFeedback";
    private static final String FIELD_BARCODE_FIND_VIEW = "BarcodeFindView";
    private static final String FIELD_BARCODE_FIND_VIEW_SETTINGS = "BarcodeFindViewSettings";
    private static final String FIELD_RECOMMENDED_CAMERA_SETTINGS = "RecommendedCameraSettings";
    private final BarcodeFindFeedback barcodeFindFeedback;
    private final Map<String, Object> barcodeFindView;
    private final Map<String, Object> barcodeFindViewSettings;
    private final Map<String, Object> recommendedCameraSettings;

    public /* synthetic */ BarcodeFindDefaults(Map map, BarcodeFindFeedback barcodeFindFeedback, Map map2, Map map3, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, barcodeFindFeedback, map2, map3);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private BarcodeFindDefaults(Map<String, ? extends Object> map, BarcodeFindFeedback barcodeFindFeedback, Map<String, ? extends Object> map2, Map<String, ? extends Object> map3) {
        this.recommendedCameraSettings = map;
        this.barcodeFindFeedback = barcodeFindFeedback;
        this.barcodeFindView = map2;
        this.barcodeFindViewSettings = map3;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_RECOMMENDED_CAMERA_SETTINGS, this.recommendedCameraSettings), TuplesKt.to(FIELD_BARCODE_FIND_FEEDBACK, this.barcodeFindFeedback.toJson()), TuplesKt.to(FIELD_BARCODE_FIND_VIEW, this.barcodeFindView), TuplesKt.to(FIELD_BARCODE_FIND_VIEW_SETTINGS, this.barcodeFindViewSettings));
    }

    /* compiled from: BarcodeFindDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/data/defaults/BarcodeFindDefaults$Companion;", "", "()V", "FIELD_BARCODE_FIND_FEEDBACK", "", "FIELD_BARCODE_FIND_VIEW", "FIELD_BARCODE_FIND_VIEW_SETTINGS", "FIELD_RECOMMENDED_CAMERA_SETTINGS", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeFindDefaults(CameraSettingsDefaults.INSTANCE.create(BarcodeFind.INSTANCE.getRecommendedCameraSettings()).toMap(), BarcodeFindFeedback.INSTANCE.defaultFeedback(), BarcodeFindViewFrameworksDefaults.INSTANCE.get(), BarcodeFindViewSettingsDefaults.INSTANCE.get(), null).toMap();
        }
    }
}
