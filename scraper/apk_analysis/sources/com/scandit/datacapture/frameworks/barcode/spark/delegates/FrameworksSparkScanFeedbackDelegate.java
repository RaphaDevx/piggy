package com.scandit.datacapture.frameworks.barcode.spark.delegates;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.spark.feedback.SparkScanBarcodeFeedback;
import com.scandit.datacapture.barcode.spark.feedback.SparkScanFeedbackDelegate;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: FrameworksSparkScanFeedbackDelegate.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tJ\u0012\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/delegates/FrameworksSparkScanFeedbackDelegate;", "Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanFeedbackDelegate;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksSparkScanFeedbackDelegate.FIELD_VIEW_ID, "", "onGetFeedbackForBarcode", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;ILcom/scandit/datacapture/frameworks/core/events/EventForResult;)V", "getFeedbackForBarcode", "Lcom/scandit/datacapture/barcode/spark/feedback/SparkScanBarcodeFeedback;", FrameworksSparkScanFeedbackDelegate.FIELD_BARCODE, "Lcom/scandit/datacapture/barcode/data/Barcode;", "submitFeedback", "", "feedbackJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksSparkScanFeedbackDelegate implements SparkScanFeedbackDelegate {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FIELD_BARCODE = "barcode";
    private static final String FIELD_EVENT = "event";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_GET_FEEDBACK_FOR_BARCODE = "SparkScanFeedbackDelegate.feedbackForBarcode";
    private final Emitter emitter;
    private final EventForResult<String> onGetFeedbackForBarcode;
    private final int viewId;

    @JvmStatic
    public static final FrameworksSparkScanFeedbackDelegate create(Emitter emitter, int i) {
        return INSTANCE.create(emitter, i);
    }

    public FrameworksSparkScanFeedbackDelegate(Emitter emitter, int i, EventForResult<String> onGetFeedbackForBarcode) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(onGetFeedbackForBarcode, "onGetFeedbackForBarcode");
        this.emitter = emitter;
        this.viewId = i;
        this.onGetFeedbackForBarcode = onGetFeedbackForBarcode;
    }

    public /* synthetic */ FrameworksSparkScanFeedbackDelegate(Emitter emitter, int i, EventForResult eventForResult, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(emitter, i, (i2 & 4) != 0 ? new EventForResult(ON_GET_FEEDBACK_FOR_BARCODE, null, null, 6, null) : eventForResult);
    }

    @Override // com.scandit.datacapture.barcode.spark.feedback.SparkScanFeedbackDelegate
    public SparkScanBarcodeFeedback getFeedbackForBarcode(Barcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        if (!this.emitter.hasViewSpecificListenersForEvent(this.viewId, ON_GET_FEEDBACK_FOR_BARCODE)) {
            return null;
        }
        String emit = this.onGetFeedbackForBarcode.emit(this.emitter, MapsKt.mutableMapOf(TuplesKt.to("event", ON_GET_FEEDBACK_FOR_BARCODE), TuplesKt.to(FIELD_BARCODE, barcode.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))), null, -1L);
        String str = emit;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        return SparkScanBarcodeFeedback.INSTANCE.fromJson(emit);
    }

    public final void submitFeedback(String feedbackJson) {
        this.onGetFeedbackForBarcode.submitResult(feedbackJson);
    }

    /* compiled from: FrameworksSparkScanFeedbackDelegate.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/delegates/FrameworksSparkScanFeedbackDelegate$Companion;", "", "()V", "FIELD_BARCODE", "", "FIELD_EVENT", "FIELD_VIEW_ID", "ON_GET_FEEDBACK_FOR_BARCODE", "create", "Lcom/scandit/datacapture/frameworks/barcode/spark/delegates/FrameworksSparkScanFeedbackDelegate;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksSparkScanFeedbackDelegate.FIELD_VIEW_ID, "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FrameworksSparkScanFeedbackDelegate create(Emitter emitter, int viewId) {
            Intrinsics.checkNotNullParameter(emitter, "emitter");
            return new FrameworksSparkScanFeedbackDelegate(emitter, viewId, null, 4, null);
        }
    }
}
