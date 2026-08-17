package com.scandit.datacapture.frameworks.barcode.pick.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.pick.data.BarcodePickProductProviderCallbackItem;
import com.scandit.datacapture.frameworks.core.extensions.ExtentionsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BarcodePickProductProviderCallbackItemData.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/data/BarcodePickProductProviderCallbackItemData;", "", "barcodePickProductProviderCallbackItemsJson", "", "(Ljava/lang/String;)V", "getItem", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProviderCallbackItem;", "item", "Lorg/json/JSONObject;", "getItems", "", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodePickProductProviderCallbackItemData {
    private final String barcodePickProductProviderCallbackItemsJson;

    public BarcodePickProductProviderCallbackItemData(String barcodePickProductProviderCallbackItemsJson) {
        Intrinsics.checkNotNullParameter(barcodePickProductProviderCallbackItemsJson, "barcodePickProductProviderCallbackItemsJson");
        this.barcodePickProductProviderCallbackItemsJson = barcodePickProductProviderCallbackItemsJson;
    }

    public final List<BarcodePickProductProviderCallbackItem> getItems() {
        JSONArray jSONArray = new JSONArray(this.barcodePickProductProviderCallbackItemsJson);
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            Intrinsics.checkNotNull(jSONObject);
            arrayList.add(getItem(jSONObject));
        }
        return arrayList;
    }

    private final BarcodePickProductProviderCallbackItem getItem(JSONObject item) {
        String string = item.getString("itemData");
        String orNull = ExtentionsKt.getOrNull(item, "productIdentifier");
        Intrinsics.checkNotNull(string);
        return new BarcodePickProductProviderCallbackItem(string, orNull);
    }
}
