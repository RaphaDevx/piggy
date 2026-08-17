package com.scandit.datacapture.frameworks.barcode.pick.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: FinishPickActionData.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/FinishPickActionData;", "", "finishDataJson", "", "(Ljava/lang/String;)V", "finishData", "Lorg/json/JSONObject;", "itemData", "getItemData", "()Ljava/lang/String;", "result", "", "getResult", "()Z", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FinishPickActionData {
    private final JSONObject finishData;

    public FinishPickActionData(String finishDataJson) {
        Intrinsics.checkNotNullParameter(finishDataJson, "finishDataJson");
        this.finishData = new JSONObject(finishDataJson);
    }

    public final String getItemData() {
        return this.finishData.get("itemData").toString();
    }

    public final boolean getResult() {
        Object obj = this.finishData.get("result");
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
        return ((Boolean) obj).booleanValue();
    }
}
