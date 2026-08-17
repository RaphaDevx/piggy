package com.scandit.datacapture.frameworks.barcode.count.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: BarcodeCountViewCreationData.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018BG\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006¢\u0006\u0002\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014¨\u0006\u0019"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountViewCreationData;", "", "modeJson", "", "viewJson", "hasModeListener", "", "hasViewListener", "hasUIListener", BarcodeCountViewCreationData.VIEW_ID_KEY, "", "isModeEnabled", BarcodeCountViewCreationData.HAS_STATUS_PROVIDER_KEY, "(Ljava/lang/String;Ljava/lang/String;ZZZIZZ)V", "getHasModeListener", "()Z", "getHasStatusProvider", "getHasUIListener", "getHasViewListener", "getModeJson", "()Ljava/lang/String;", "getViewId", "()I", "getViewJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountViewCreationData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String HAS_MODE_LISTENER_KEY = "hasListeners";
    private static final String HAS_STATUS_PROVIDER_KEY = "hasStatusProvider";
    private static final String HAS_UI_LISTENER_KEY = "hasUiListener";
    private static final String HAS_VIEW_LISTENER_KEY = "hasListeners";
    private static final String MODE_ENABLED_KEY = "isEnabled";
    private static final String MODE_KEY = "BarcodeCount";
    private static final String VIEW_ID_KEY = "viewId";
    private static final String VIEW_KEY = "View";
    private final boolean hasModeListener;
    private final boolean hasStatusProvider;
    private final boolean hasUIListener;
    private final boolean hasViewListener;
    private final boolean isModeEnabled;
    private final String modeJson;
    private final int viewId;
    private final String viewJson;

    public /* synthetic */ BarcodeCountViewCreationData(String str, String str2, boolean z, boolean z2, boolean z3, int i, boolean z4, boolean z5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, z2, z3, i, z4, z5);
    }

    private BarcodeCountViewCreationData(String str, String str2, boolean z, boolean z2, boolean z3, int i, boolean z4, boolean z5) {
        this.modeJson = str;
        this.viewJson = str2;
        this.hasModeListener = z;
        this.hasViewListener = z2;
        this.hasUIListener = z3;
        this.viewId = i;
        this.isModeEnabled = z4;
        this.hasStatusProvider = z5;
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

    public final boolean getHasViewListener() {
        return this.hasViewListener;
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

    public final boolean getHasStatusProvider() {
        return this.hasStatusProvider;
    }

    /* compiled from: BarcodeCountViewCreationData.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountViewCreationData$Companion;", "", "()V", "HAS_MODE_LISTENER_KEY", "", "HAS_STATUS_PROVIDER_KEY", "HAS_UI_LISTENER_KEY", "HAS_VIEW_LISTENER_KEY", "MODE_ENABLED_KEY", "MODE_KEY", "VIEW_ID_KEY", "VIEW_KEY", "fromJson", "Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountViewCreationData;", "viewJson", "fromModeJsonOnly", "modeJson", "fromViewJsonOnly", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BarcodeCountViewCreationData fromJson(String viewJson) {
            Intrinsics.checkNotNullParameter(viewJson, "viewJson");
            JSONObject jSONObject = new JSONObject(viewJson);
            if (!jSONObject.has(BarcodeCountViewCreationData.MODE_KEY) || !jSONObject.has(BarcodeCountViewCreationData.VIEW_KEY)) {
                throw new IllegalArgumentException("Unable to create a BarcodeCountView. The given json doesn't contain all the required fields. Required fields = [BarcodeCount, View]");
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(BarcodeCountViewCreationData.VIEW_KEY);
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject(BarcodeCountViewCreationData.MODE_KEY);
            if (optJSONObject2 == null) {
                optJSONObject2 = new JSONObject();
            }
            String jSONObject2 = jSONObject.getJSONObject(BarcodeCountViewCreationData.MODE_KEY).toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
            String jSONObject3 = jSONObject.getJSONObject(BarcodeCountViewCreationData.VIEW_KEY).toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject3, "toString(...)");
            return new BarcodeCountViewCreationData(jSONObject2, jSONObject3, optJSONObject2.optBoolean("hasListeners", false), optJSONObject.optBoolean("hasListeners", false), optJSONObject.optBoolean(BarcodeCountViewCreationData.HAS_UI_LISTENER_KEY, false), optJSONObject.getInt(BarcodeCountViewCreationData.VIEW_ID_KEY), optJSONObject2.optBoolean(BarcodeCountViewCreationData.MODE_ENABLED_KEY, false), optJSONObject.optBoolean(BarcodeCountViewCreationData.HAS_STATUS_PROVIDER_KEY, false), null);
        }

        public final BarcodeCountViewCreationData fromViewJsonOnly(String viewJson) {
            Intrinsics.checkNotNullParameter(viewJson, "viewJson");
            JSONObject jSONObject = new JSONObject(viewJson);
            return new BarcodeCountViewCreationData("{}", viewJson, false, jSONObject.optBoolean("hasListeners", false), jSONObject.optBoolean(BarcodeCountViewCreationData.HAS_UI_LISTENER_KEY, false), jSONObject.getInt(BarcodeCountViewCreationData.VIEW_ID_KEY), false, jSONObject.optBoolean(BarcodeCountViewCreationData.HAS_STATUS_PROVIDER_KEY, false), null);
        }

        public final BarcodeCountViewCreationData fromModeJsonOnly(String modeJson) {
            Intrinsics.checkNotNullParameter(modeJson, "modeJson");
            JSONObject jSONObject = new JSONObject(modeJson);
            return new BarcodeCountViewCreationData(modeJson, "{}", jSONObject.optBoolean("hasListeners", false), false, false, -1, jSONObject.optBoolean(BarcodeCountViewCreationData.MODE_ENABLED_KEY, false), false, null);
        }
    }
}
