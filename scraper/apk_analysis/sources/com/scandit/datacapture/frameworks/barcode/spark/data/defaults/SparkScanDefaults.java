package com.scandit.datacapture.frameworks.barcode.spark.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SparkScanDefaults.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0000\u0018\u0000 \n2\u00020\u0001:\u0001\nBG\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0016R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/defaults/SparkScanDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "sparkScanFeedbackDefaults", "", "", "", "sparkScanViewDefaults", "sparkScanSettingsDefaults", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SparkScanDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_SPARK_SCAN_FEEDBACK = "Feedback";
    private static final String FIELD_SPARK_SCAN_SETTINGS = "SparkScanSettings";
    private static final String FIELD_SPARK_SCAN_VIEW = "SparkScanView";
    private Map<String, ? extends Object> sparkScanFeedbackDefaults;
    private final Map<String, Object> sparkScanSettingsDefaults;
    private final Map<String, Object> sparkScanViewDefaults;

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    public SparkScanDefaults(Map<String, ? extends Object> sparkScanFeedbackDefaults, Map<String, ? extends Object> sparkScanViewDefaults, Map<String, ? extends Object> sparkScanSettingsDefaults) {
        Intrinsics.checkNotNullParameter(sparkScanFeedbackDefaults, "sparkScanFeedbackDefaults");
        Intrinsics.checkNotNullParameter(sparkScanViewDefaults, "sparkScanViewDefaults");
        Intrinsics.checkNotNullParameter(sparkScanSettingsDefaults, "sparkScanSettingsDefaults");
        this.sparkScanFeedbackDefaults = sparkScanFeedbackDefaults;
        this.sparkScanViewDefaults = sparkScanViewDefaults;
        this.sparkScanSettingsDefaults = sparkScanSettingsDefaults;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to(FIELD_SPARK_SCAN_FEEDBACK, this.sparkScanFeedbackDefaults), TuplesKt.to(FIELD_SPARK_SCAN_SETTINGS, this.sparkScanSettingsDefaults), TuplesKt.to(FIELD_SPARK_SCAN_VIEW, this.sparkScanViewDefaults));
    }

    /* compiled from: SparkScanDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/defaults/SparkScanDefaults$Companion;", "", "()V", "FIELD_SPARK_SCAN_FEEDBACK", "", "FIELD_SPARK_SCAN_SETTINGS", "FIELD_SPARK_SCAN_VIEW", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new SparkScanDefaults(SparkScanFeedbackDefaults.INSTANCE.get(), SparkScanViewDefaults.INSTANCE.get(), SparkScanSettingsDefaults.INSTANCE.get()).toMap();
        }
    }
}
