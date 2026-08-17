package com.scandit.datacapture.frameworks.barcode.ar.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: BarcodeArViewCreationData.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014¨\u0006\u0019"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/data/BarcodeArViewCreationData;", "", "modeJson", "", "viewJson", BarcodeArViewCreationData.HAS_IS_STARTED_KEY, "", BarcodeArViewCreationData.HAS_UI_LISTENER_KEY, BarcodeArViewCreationData.HAS_HIGHLIGHT_PROVIDER_KEY, BarcodeArViewCreationData.HAS_ANNOTATION_PROVIDER_KEY, BarcodeArViewCreationData.HAS_MODE_LISTENER_KEY, BarcodeArViewCreationData.VIEW_ID_KEY, "", "(Ljava/lang/String;Ljava/lang/String;ZZZZZI)V", "getHasAnnotationProvider", "()Z", "getHasHighlightProvider", "getHasModeListener", "getHasUiListener", "getModeJson", "()Ljava/lang/String;", "getViewId", "()I", "getViewJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeArViewCreationData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String HAS_ANNOTATION_PROVIDER_KEY = "hasAnnotationProvider";
    private static final String HAS_HIGHLIGHT_PROVIDER_KEY = "hasHighlightProvider";
    private static final String HAS_IS_STARTED_KEY = "isStarted";
    private static final String HAS_MODE_LISTENER_KEY = "hasModeListener";
    private static final String HAS_UI_LISTENER_KEY = "hasUiListener";
    private static final String MODE_KEY = "BarcodeAr";
    private static final String VIEW_ID_KEY = "viewId";
    private static final String VIEW_KEY = "View";
    private final boolean hasAnnotationProvider;
    private final boolean hasHighlightProvider;
    private final boolean hasModeListener;
    private final boolean hasUiListener;
    private final boolean isStarted;
    private final String modeJson;
    private final int viewId;
    private final String viewJson;

    public BarcodeArViewCreationData(String modeJson, String viewJson, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        this.modeJson = modeJson;
        this.viewJson = viewJson;
        this.isStarted = z;
        this.hasUiListener = z2;
        this.hasHighlightProvider = z3;
        this.hasAnnotationProvider = z4;
        this.hasModeListener = z5;
        this.viewId = i;
    }

    public /* synthetic */ BarcodeArViewCreationData(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, z2, z3, z4, z5, (i2 & 128) != 0 ? -1 : i);
    }

    public final String getModeJson() {
        return this.modeJson;
    }

    public final String getViewJson() {
        return this.viewJson;
    }

    /* renamed from: isStarted, reason: from getter */
    public final boolean getIsStarted() {
        return this.isStarted;
    }

    public final boolean getHasUiListener() {
        return this.hasUiListener;
    }

    public final boolean getHasHighlightProvider() {
        return this.hasHighlightProvider;
    }

    public final boolean getHasAnnotationProvider() {
        return this.hasAnnotationProvider;
    }

    public final boolean getHasModeListener() {
        return this.hasModeListener;
    }

    public final int getViewId() {
        return this.viewId;
    }

    /* compiled from: BarcodeArViewCreationData.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/data/BarcodeArViewCreationData$Companion;", "", "()V", "HAS_ANNOTATION_PROVIDER_KEY", "", "HAS_HIGHLIGHT_PROVIDER_KEY", "HAS_IS_STARTED_KEY", "HAS_MODE_LISTENER_KEY", "HAS_UI_LISTENER_KEY", "MODE_KEY", "VIEW_ID_KEY", "VIEW_KEY", "fromJson", "Lcom/scandit/datacapture/frameworks/barcode/ar/data/BarcodeArViewCreationData;", "viewJson", "fromModeJsonOnly", "modeJson", "fromViewJsonOnly", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BarcodeArViewCreationData fromJson(String viewJson) {
            Intrinsics.checkNotNullParameter(viewJson, "viewJson");
            JSONObject jSONObject = new JSONObject(viewJson);
            if (!jSONObject.has(BarcodeArViewCreationData.MODE_KEY) || !jSONObject.has(BarcodeArViewCreationData.VIEW_KEY)) {
                throw new IllegalArgumentException("Unable to create a BarcodeArView. The given json doesn't contain all the required fields. Required fields = [BarcodeAr, View]");
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(BarcodeArViewCreationData.MODE_KEY);
            JSONObject jSONObject3 = jSONObject.getJSONObject(BarcodeArViewCreationData.VIEW_KEY);
            boolean optBoolean = jSONObject3.optBoolean(BarcodeArViewCreationData.HAS_IS_STARTED_KEY, false);
            boolean optBoolean2 = jSONObject3.optBoolean(BarcodeArViewCreationData.HAS_UI_LISTENER_KEY, false);
            boolean optBoolean3 = jSONObject3.optBoolean(BarcodeArViewCreationData.HAS_HIGHLIGHT_PROVIDER_KEY, false);
            boolean optBoolean4 = jSONObject3.optBoolean(BarcodeArViewCreationData.HAS_ANNOTATION_PROVIDER_KEY, false);
            boolean optBoolean5 = jSONObject2.optBoolean(BarcodeArViewCreationData.HAS_MODE_LISTENER_KEY, false);
            int optInt = jSONObject3.optInt(BarcodeArViewCreationData.VIEW_ID_KEY, -1);
            String jSONObject4 = jSONObject2.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject4, "toString(...)");
            String jSONObject5 = jSONObject3.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject5, "toString(...)");
            return new BarcodeArViewCreationData(jSONObject4, jSONObject5, optBoolean, optBoolean2, optBoolean3, optBoolean4, optBoolean5, optInt);
        }

        public final BarcodeArViewCreationData fromViewJsonOnly(String viewJson) {
            Intrinsics.checkNotNullParameter(viewJson, "viewJson");
            JSONObject jSONObject = new JSONObject(viewJson);
            return new BarcodeArViewCreationData("{}", viewJson, false, jSONObject.optBoolean(BarcodeArViewCreationData.HAS_UI_LISTENER_KEY, false), jSONObject.optBoolean(BarcodeArViewCreationData.HAS_HIGHLIGHT_PROVIDER_KEY, false), jSONObject.optBoolean(BarcodeArViewCreationData.HAS_ANNOTATION_PROVIDER_KEY, false), false, jSONObject.optInt(BarcodeArViewCreationData.VIEW_ID_KEY, -1));
        }

        public final BarcodeArViewCreationData fromModeJsonOnly(String modeJson) {
            Intrinsics.checkNotNullParameter(modeJson, "modeJson");
            return new BarcodeArViewCreationData(modeJson, "{}", false, false, false, false, new JSONObject(modeJson).optBoolean(BarcodeArViewCreationData.HAS_MODE_LISTENER_KEY, false), -1);
        }
    }
}
