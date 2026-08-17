package com.scandit.datacapture.frameworks.barcode.find.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: BarcodeFindViewCreationData.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cBO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0011R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017¨\u0006\u001d"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/data/BarcodeFindViewCreationData;", "", "modeJson", "", "viewJson", BarcodeFindViewCreationData.ITEMS_TO_FIND_KEY, "Lcom/scandit/datacapture/frameworks/barcode/find/data/BarcodeFindItemsData;", BarcodeFindViewCreationData.START_SEARCHING_KEY, "", BarcodeFindViewCreationData.HAS_BARCODE_TRANSFORMER_KEY, "isModeEnabled", "hasModeListeners", "hasViewListener", BarcodeFindViewCreationData.VIEW_ID_KEY, "", "(Ljava/lang/String;Ljava/lang/String;Lcom/scandit/datacapture/frameworks/barcode/find/data/BarcodeFindItemsData;ZZZZZI)V", "getHasBarcodeTransformer", "()Z", "getHasModeListeners", "getHasViewListener", "getItemsToFind", "()Lcom/scandit/datacapture/frameworks/barcode/find/data/BarcodeFindItemsData;", "getModeJson", "()Ljava/lang/String;", "getStartSearching", "getViewId", "()I", "getViewJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeFindViewCreationData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String HAS_BARCODE_TRANSFORMER_KEY = "hasBarcodeTransformer";
    private static final String HAS_MODE_LISTENERS_KEY = "hasListeners";
    private static final String HAS_VIEW_LISTENER_KEY = "hasListener";
    private static final String ITEMS_TO_FIND_KEY = "itemsToFind";
    private static final String MODE_ENABLED_KEY = "enabled";
    private static final String MODE_KEY = "BarcodeFind";
    private static final String START_SEARCHING_KEY = "startSearching";
    private static final String VIEW_ID_KEY = "viewId";
    private static final String VIEW_KEY = "View";
    private final boolean hasBarcodeTransformer;
    private final boolean hasModeListeners;
    private final boolean hasViewListener;
    private final boolean isModeEnabled;
    private final BarcodeFindItemsData itemsToFind;
    private final String modeJson;
    private final boolean startSearching;
    private final int viewId;
    private final String viewJson;

    public BarcodeFindViewCreationData(String modeJson, String viewJson, BarcodeFindItemsData barcodeFindItemsData, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i) {
        Intrinsics.checkNotNullParameter(modeJson, "modeJson");
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        this.modeJson = modeJson;
        this.viewJson = viewJson;
        this.itemsToFind = barcodeFindItemsData;
        this.startSearching = z;
        this.hasBarcodeTransformer = z2;
        this.isModeEnabled = z3;
        this.hasModeListeners = z4;
        this.hasViewListener = z5;
        this.viewId = i;
    }

    public final String getModeJson() {
        return this.modeJson;
    }

    public final String getViewJson() {
        return this.viewJson;
    }

    public final BarcodeFindItemsData getItemsToFind() {
        return this.itemsToFind;
    }

    public final boolean getStartSearching() {
        return this.startSearching;
    }

    public final boolean getHasBarcodeTransformer() {
        return this.hasBarcodeTransformer;
    }

    /* renamed from: isModeEnabled, reason: from getter */
    public final boolean getIsModeEnabled() {
        return this.isModeEnabled;
    }

    public final boolean getHasModeListeners() {
        return this.hasModeListeners;
    }

    public final boolean getHasViewListener() {
        return this.hasViewListener;
    }

    public final int getViewId() {
        return this.viewId;
    }

    /* compiled from: BarcodeFindViewCreationData.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/data/BarcodeFindViewCreationData$Companion;", "", "()V", "HAS_BARCODE_TRANSFORMER_KEY", "", "HAS_MODE_LISTENERS_KEY", "HAS_VIEW_LISTENER_KEY", "ITEMS_TO_FIND_KEY", "MODE_ENABLED_KEY", "MODE_KEY", "START_SEARCHING_KEY", "VIEW_ID_KEY", "VIEW_KEY", "fromJson", "Lcom/scandit/datacapture/frameworks/barcode/find/data/BarcodeFindViewCreationData;", "viewJson", "fromModeJsonOnly", "modeJson", "fromViewJsonOnly", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BarcodeFindViewCreationData fromJson(String viewJson) {
            BarcodeFindItemsData barcodeFindItemsData;
            Intrinsics.checkNotNullParameter(viewJson, "viewJson");
            JSONObject jSONObject = new JSONObject(viewJson);
            if (!jSONObject.has(BarcodeFindViewCreationData.MODE_KEY) || !jSONObject.has(BarcodeFindViewCreationData.VIEW_KEY)) {
                throw new IllegalArgumentException("Unable to create a BarcodeFindView. The given json doesn't contain all the required fields. Required fields = [BarcodeFind, View]");
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(BarcodeFindViewCreationData.VIEW_KEY);
            JSONObject jSONObject3 = jSONObject.getJSONObject(BarcodeFindViewCreationData.MODE_KEY);
            if (jSONObject3.has(BarcodeFindViewCreationData.ITEMS_TO_FIND_KEY)) {
                String string = jSONObject3.getString(BarcodeFindViewCreationData.ITEMS_TO_FIND_KEY);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                barcodeFindItemsData = new BarcodeFindItemsData(string);
            } else {
                barcodeFindItemsData = null;
            }
            String jSONObject4 = jSONObject3.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject4, "toString(...)");
            String jSONObject5 = jSONObject2.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject5, "toString(...)");
            return new BarcodeFindViewCreationData(jSONObject4, jSONObject5, barcodeFindItemsData, jSONObject2.optBoolean(BarcodeFindViewCreationData.START_SEARCHING_KEY, false), jSONObject3.optBoolean(BarcodeFindViewCreationData.HAS_BARCODE_TRANSFORMER_KEY, false), jSONObject3.optBoolean("enabled", false), jSONObject3.optBoolean(BarcodeFindViewCreationData.HAS_MODE_LISTENERS_KEY, false), jSONObject2.optBoolean(BarcodeFindViewCreationData.HAS_VIEW_LISTENER_KEY, false), jSONObject2.getInt(BarcodeFindViewCreationData.VIEW_ID_KEY));
        }

        public final BarcodeFindViewCreationData fromModeJsonOnly(String modeJson) {
            BarcodeFindItemsData barcodeFindItemsData;
            Intrinsics.checkNotNullParameter(modeJson, "modeJson");
            JSONObject jSONObject = new JSONObject(modeJson);
            if (jSONObject.has(BarcodeFindViewCreationData.ITEMS_TO_FIND_KEY)) {
                String string = jSONObject.getString(BarcodeFindViewCreationData.ITEMS_TO_FIND_KEY);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                barcodeFindItemsData = new BarcodeFindItemsData(string);
            } else {
                barcodeFindItemsData = null;
            }
            return new BarcodeFindViewCreationData(modeJson, "{}", barcodeFindItemsData, false, jSONObject.optBoolean(BarcodeFindViewCreationData.HAS_BARCODE_TRANSFORMER_KEY, false), jSONObject.optBoolean("enabled", false), jSONObject.optBoolean(BarcodeFindViewCreationData.HAS_MODE_LISTENERS_KEY, false), false, -1);
        }

        public final BarcodeFindViewCreationData fromViewJsonOnly(String viewJson) {
            BarcodeFindItemsData barcodeFindItemsData;
            Intrinsics.checkNotNullParameter(viewJson, "viewJson");
            JSONObject jSONObject = new JSONObject(viewJson);
            if (jSONObject.has(BarcodeFindViewCreationData.ITEMS_TO_FIND_KEY)) {
                String string = jSONObject.getString(BarcodeFindViewCreationData.ITEMS_TO_FIND_KEY);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                barcodeFindItemsData = new BarcodeFindItemsData(string);
            } else {
                barcodeFindItemsData = null;
            }
            return new BarcodeFindViewCreationData("{}", viewJson, barcodeFindItemsData, jSONObject.optBoolean(BarcodeFindViewCreationData.START_SEARCHING_KEY, false), false, false, false, jSONObject.optBoolean(BarcodeFindViewCreationData.HAS_VIEW_LISTENER_KEY, false), jSONObject.getInt(BarcodeFindViewCreationData.VIEW_ID_KEY));
        }
    }
}
