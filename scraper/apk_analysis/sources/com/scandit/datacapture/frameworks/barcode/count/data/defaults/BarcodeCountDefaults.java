package com.scandit.datacapture.frameworks.barcode.count.data.defaults;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.feedback.BarcodeCountFeedback;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.CameraSettingsDefaults;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeCountDefaults.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \f2\u00020\u0001:\u0001\fBQ\b\u0002\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeCountDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "recommendedCameraSettings", "", "", "", "barcodeCountSettings", "barcodeCountFeedback", "Lcom/scandit/datacapture/barcode/count/feedback/BarcodeCountFeedback;", "barcodeCountView", "(Ljava/util/Map;Ljava/util/Map;Lcom/scandit/datacapture/barcode/count/feedback/BarcodeCountFeedback;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BARCODE_COUNT_FEEDBACK = "BarcodeCountFeedback";
    private static final String FIELD_BARCODE_COUNT_SETTINGS = "BarcodeCountSettings";
    private static final String FIELD_BARCODE_COUNT_VIEW = "BarcodeCountView";
    private static final String FIELD_RECOMMENDED_CAMERA_SETTINGS = "RecommendedCameraSettings";
    private final BarcodeCountFeedback barcodeCountFeedback;
    private final Map<String, Object> barcodeCountSettings;
    private final Map<String, Object> barcodeCountView;
    private final Map<String, Object> recommendedCameraSettings;

    public /* synthetic */ BarcodeCountDefaults(Map map, Map map2, BarcodeCountFeedback barcodeCountFeedback, Map map3, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, map2, barcodeCountFeedback, map3);
    }

    @JvmStatic
    public static final Map<String, Object> get(Context context) {
        return INSTANCE.get(context);
    }

    private BarcodeCountDefaults(Map<String, ? extends Object> map, Map<String, ? extends Object> map2, BarcodeCountFeedback barcodeCountFeedback, Map<String, ? extends Object> map3) {
        this.recommendedCameraSettings = map;
        this.barcodeCountSettings = map2;
        this.barcodeCountFeedback = barcodeCountFeedback;
        this.barcodeCountView = map3;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_RECOMMENDED_CAMERA_SETTINGS, this.recommendedCameraSettings), TuplesKt.to(FIELD_BARCODE_COUNT_SETTINGS, this.barcodeCountSettings), TuplesKt.to(FIELD_BARCODE_COUNT_FEEDBACK, this.barcodeCountFeedback.toJson()), TuplesKt.to(FIELD_BARCODE_COUNT_VIEW, this.barcodeCountView));
    }

    /* compiled from: BarcodeCountDefaults.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/defaults/BarcodeCountDefaults$Companion;", "", "()V", "FIELD_BARCODE_COUNT_FEEDBACK", "", "FIELD_BARCODE_COUNT_SETTINGS", "FIELD_BARCODE_COUNT_VIEW", "FIELD_RECOMMENDED_CAMERA_SETTINGS", "get", "", "context", "Landroid/content/Context;", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new BarcodeCountDefaults(CameraSettingsDefaults.INSTANCE.create(BarcodeCount.INSTANCE.createRecommendedCameraSettings()).toMap(), BarcodeCountSettingsDefaults.INSTANCE.get(), BarcodeCountFeedback.INSTANCE.defaultFeedback(), BarcodeCountViewDefaults.INSTANCE.get(context), null).toMap();
        }
    }
}
