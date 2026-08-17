package com.scandit.datacapture.frameworks.core.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: DataCaptureViewCreationData.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\u0002\u0010\tR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/DataCaptureViewCreationData;", "", DataCaptureViewCreationData.VIEW_ID_KEY, "", "viewJson", "", DataCaptureViewCreationData.PARENT_ID_KEY, "overlaysJson", "", "(ILjava/lang/String;Ljava/lang/Integer;Ljava/util/List;)V", "getOverlaysJson", "()Ljava/util/List;", "getParentId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getViewId", "()I", "getViewJson", "()Ljava/lang/String;", "Companion", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DataCaptureViewCreationData {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String OVERLAYS_KEY = "overlays";
    private static final String PARENT_ID_KEY = "parentId";
    private static final String VIEW_ID_KEY = "viewId";
    private final List<String> overlaysJson;
    private final Integer parentId;
    private final int viewId;
    private final String viewJson;

    public DataCaptureViewCreationData(int i, String viewJson, Integer num, List<String> overlaysJson) {
        Intrinsics.checkNotNullParameter(viewJson, "viewJson");
        Intrinsics.checkNotNullParameter(overlaysJson, "overlaysJson");
        this.viewId = i;
        this.viewJson = viewJson;
        this.parentId = num;
        this.overlaysJson = overlaysJson;
    }

    public final int getViewId() {
        return this.viewId;
    }

    public final String getViewJson() {
        return this.viewJson;
    }

    public final Integer getParentId() {
        return this.parentId;
    }

    public final List<String> getOverlaysJson() {
        return this.overlaysJson;
    }

    /* compiled from: DataCaptureViewCreationData.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000b2\u0006\u0010\t\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/core/data/DataCaptureViewCreationData$Companion;", "", "()V", "OVERLAYS_KEY", "", "PARENT_ID_KEY", "VIEW_ID_KEY", "fromJson", "Lcom/scandit/datacapture/frameworks/core/data/DataCaptureViewCreationData;", "viewJson", "getOverlaysFromViewJson", "", "Lorg/json/JSONObject;", "scandit-datacapture-frameworks-core_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DataCaptureViewCreationData fromJson(String viewJson) {
            Intrinsics.checkNotNullParameter(viewJson, "viewJson");
            JSONObject jSONObject = new JSONObject(viewJson);
            List<String> overlaysFromViewJson = getOverlaysFromViewJson(jSONObject);
            Integer valueOf = jSONObject.has(DataCaptureViewCreationData.PARENT_ID_KEY) ? Integer.valueOf(jSONObject.getInt(DataCaptureViewCreationData.PARENT_ID_KEY)) : null;
            jSONObject.remove(DataCaptureViewCreationData.OVERLAYS_KEY);
            int i = jSONObject.getInt(DataCaptureViewCreationData.VIEW_ID_KEY);
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
            return new DataCaptureViewCreationData(i, jSONObject2, valueOf, overlaysFromViewJson);
        }

        private final List<String> getOverlaysFromViewJson(JSONObject viewJson) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = viewJson.has(DataCaptureViewCreationData.OVERLAYS_KEY) ? viewJson.getJSONArray(DataCaptureViewCreationData.OVERLAYS_KEY) : null;
            if (jSONArray != null) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    String jSONObject = jSONArray.getJSONObject(i).toString();
                    Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                    arrayList.add(jSONObject);
                }
            }
            return arrayList;
        }
    }
}
