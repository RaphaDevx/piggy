package com.scandit.datacapture.frameworks.barcode.spark.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.spark.feedback.SparkScanBarcodeFeedback;
import com.scandit.datacapture.core.time.TimeInterval;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: SparkScanFeedbackDefaults.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\n0\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/defaults/SparkScanFeedbackDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "successFeedback", "Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanBarcodeFeedback$Success;", "errorFeedback", "Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanBarcodeFeedback$Error;", "(Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanBarcodeFeedback$Success;Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanBarcodeFeedback$Error;)V", "toMap", "", "", "", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SparkScanFeedbackDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_ERROR_FEEDBACK = "error";
    private static final String FIELD_SUCCESS_FEEDBACK = "success";
    private final SparkScanBarcodeFeedback.Error errorFeedback;
    private final SparkScanBarcodeFeedback.Success successFeedback;

    public /* synthetic */ SparkScanFeedbackDefaults(SparkScanBarcodeFeedback.Success success, SparkScanBarcodeFeedback.Error error, DefaultConstructorMarker defaultConstructorMarker) {
        this(success, error);
    }

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    private SparkScanFeedbackDefaults(SparkScanBarcodeFeedback.Success success, SparkScanBarcodeFeedback.Error error) {
        this.successFeedback = success;
        this.errorFeedback = error;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to("success", this.successFeedback.toJson()), TuplesKt.to("error", this.errorFeedback.toJson()));
    }

    /* compiled from: SparkScanFeedbackDefaults.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/defaults/SparkScanFeedbackDefaults$Companion;", "", "()V", "FIELD_ERROR_FEEDBACK", "", "FIELD_SUCCESS_FEEDBACK", "get", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new SparkScanFeedbackDefaults(new SparkScanBarcodeFeedback.Success(0, null, null, 7, null), new SparkScanBarcodeFeedback.Error("ignore", TimeInterval.INSTANCE.seconds(1.0f), 0, null, null, 28, null), null).toMap();
        }
    }
}
