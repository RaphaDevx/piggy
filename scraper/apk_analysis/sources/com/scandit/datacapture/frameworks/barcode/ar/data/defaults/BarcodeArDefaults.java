package com.scandit.datacapture.frameworks.barcode.ar.data.defaults;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.feedback.BarcodeArFeedback;
import com.scandit.datacapture.frameworks.core.data.SerializableData;
import com.scandit.datacapture.frameworks.core.data.defaults.CameraSettingsDefaults;
import com.tealium.library.DataSources;
import io.sentry.protocol.Feedback;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeArDefaults.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB9\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003¢\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/data/defaults/BarcodeArDefaults;", "Lcom/scandit/datacapture/frameworks/core/data/SerializableData;", "recommendedCameraSettings", "", "", "", Feedback.TYPE, "Lcom/scandit/datacapture/barcode/ar/feedback/BarcodeArFeedback;", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "(Ljava/util/Map;Lcom/scandit/datacapture/barcode/ar/feedback/BarcodeArFeedback;Ljava/util/Map;)V", "toMap", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeArDefaults implements SerializableData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BarcodeArFeedback feedback;
    private final Map<String, Object> recommendedCameraSettings;
    private final Map<String, Object> view;

    @JvmStatic
    public static final Map<String, Object> get() {
        return INSTANCE.get();
    }

    public BarcodeArDefaults(Map<String, ? extends Object> recommendedCameraSettings, BarcodeArFeedback feedback, Map<String, ? extends Object> view) {
        Intrinsics.checkNotNullParameter(recommendedCameraSettings, "recommendedCameraSettings");
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        Intrinsics.checkNotNullParameter(view, "view");
        this.recommendedCameraSettings = recommendedCameraSettings;
        this.feedback = feedback;
        this.view = view;
    }

    @Override // com.scandit.datacapture.frameworks.core.data.SerializableData
    public Map<String, Object> toMap() {
        return MapsKt.mapOf(TuplesKt.to("RecommendedCameraSettings", this.recommendedCameraSettings), TuplesKt.to("barcodeArFeedback", this.feedback.toJson()), TuplesKt.to("BarcodeArView", this.view));
    }

    /* compiled from: BarcodeArDefaults.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004H\u0007¨\u0006\u0006"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/data/defaults/BarcodeArDefaults$Companion;", "", "()V", "get", "", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Map<String, Object> get() {
            return new BarcodeArDefaults(CameraSettingsDefaults.INSTANCE.create(BarcodeAr.INSTANCE.createRecommendedCameraSettings()).toMap(), BarcodeArFeedback.INSTANCE.defaultFeedback(), BarcodeArViewFrameworksDefaults.INSTANCE.get()).toMap();
        }
    }
}
