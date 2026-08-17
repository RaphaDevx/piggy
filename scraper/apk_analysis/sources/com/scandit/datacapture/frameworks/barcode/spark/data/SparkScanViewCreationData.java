package com.scandit.datacapture.frameworks.barcode.spark.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: SparkScanViewCreationData.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0006¢\u0006\u0002\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012¨\u0006\u0017"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/SparkScanViewCreationData;", "", "modeJson", "", "viewJson", "hasModeListener", "", SparkScanViewCreationData.HAS_FEEDBACK_DELEGATE_KEY, "hasUIListener", SparkScanViewCreationData.VIEW_ID_KEY, "", "isModeEnabled", "(Ljava/lang/String;Ljava/lang/String;ZZZIZ)V", "getHasFeedbackDelegate", "()Z", "getHasModeListener", "getHasUIListener", "getModeJson", "()Ljava/lang/String;", "getViewId", "()I", "getViewJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SparkScanViewCreationData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String HAS_FEEDBACK_DELEGATE_KEY = "hasFeedbackDelegate";
    private static final String HAS_MODE_LISTENER_KEY = "hasListeners";
    private static final String HAS_UI_EXTENDED_LISTENER_KEY = "hasExtendedUiListener";
    private static final String HAS_UI_LISTENER_KEY = "hasUiListener";
    private static final String MODE_ENABLED_KEY = "isEnabled";
    private static final String MODE_KEY = "SparkScan";
    private static final String VIEW_ID_KEY = "viewId";
    private static final String VIEW_KEY = "SparkScanView";
    private final boolean hasFeedbackDelegate;
    private final boolean hasModeListener;
    private final boolean hasUIListener;
    private final boolean isModeEnabled;
    private final String modeJson;
    private final int viewId;
    private final String viewJson;

    public SparkScanViewCreationData(String modeJson, String viewJson, boolean z, boolean z2, boolean z3, int i, boolean z4) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        this.modeJson = modeJson;
        this.viewJson = viewJson;
        this.hasModeListener = z;
        this.hasFeedbackDelegate = z2;
        this.hasUIListener = z3;
        this.viewId = i;
        this.isModeEnabled = z4;
    }

    public final String getModeJson() {
        return this.modeJson;
    }

    public final String getViewJson() {
        return this.viewJson;
    }

    public final boolean getHasModeListener() {
        return this.hasModeListener;
    }

    public final boolean getHasFeedbackDelegate() {
        return this.hasFeedbackDelegate;
    }

    public final boolean getHasUIListener() {
        return this.hasUIListener;
    }

    public final int getViewId() {
        return this.viewId;
    }

    /* renamed from: isModeEnabled, reason: from getter */
    public final boolean getIsModeEnabled() {
        return this.isModeEnabled;
    }

    /* compiled from: SparkScanViewCreationData.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/spark/data/SparkScanViewCreationData$Companion;", "", "()V", "HAS_FEEDBACK_DELEGATE_KEY", "", "HAS_MODE_LISTENER_KEY", "HAS_UI_EXTENDED_LISTENER_KEY", "HAS_UI_LISTENER_KEY", "MODE_ENABLED_KEY", "MODE_KEY", "VIEW_ID_KEY", "VIEW_KEY", "fromJson", "Lcom/scandit/datacapture/frameworks/barcode/spark/data/SparkScanViewCreationData;", "viewJson", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SparkScanViewCreationData fromJson(String viewJson) {
            Intrinsics.checkNotNullParameter(viewJson, "viewJson");
            JSONObject jSONObject = new JSONObject(viewJson);
            JSONObject optJSONObject = jSONObject.optJSONObject(SparkScanViewCreationData.VIEW_KEY);
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject(SparkScanViewCreationData.MODE_KEY);
            if (optJSONObject2 == null) {
                optJSONObject2 = new JSONObject();
            }
            String jSONObject2 = optJSONObject2.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
            String jSONObject3 = optJSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject3, "toString(...)");
            return new SparkScanViewCreationData(jSONObject2, jSONObject3, optJSONObject2.optBoolean(SparkScanViewCreationData.HAS_MODE_LISTENER_KEY, false), optJSONObject.optBoolean(SparkScanViewCreationData.HAS_FEEDBACK_DELEGATE_KEY, false), optJSONObject.optBoolean(SparkScanViewCreationData.HAS_UI_LISTENER_KEY, false) || optJSONObject.optBoolean(SparkScanViewCreationData.HAS_UI_EXTENDED_LISTENER_KEY, false), optJSONObject.optInt(SparkScanViewCreationData.VIEW_ID_KEY, 0), optJSONObject2.optBoolean(SparkScanViewCreationData.MODE_ENABLED_KEY, true));
        }
    }
}
