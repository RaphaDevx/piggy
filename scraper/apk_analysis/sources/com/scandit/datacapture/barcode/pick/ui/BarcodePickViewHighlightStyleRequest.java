package com.scandit.datacapture.barcode.pick.ui;

import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B#\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\r\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\nR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\f\u001a\u0004\b\u000f\u0010\nR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleRequest;", "", "", "itemData", "productIdentifier", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickState;", SentryThread.JsonKeys.STATE, "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickState;)V", "toJson", "()Ljava/lang/String;", "a", "Ljava/lang/String;", "getItemData", "b", "getProductIdentifier", "c", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickState;", "getState", "()Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickState;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodePickViewHighlightStyleRequest {

    /* renamed from: a, reason: from kotlin metadata */
    private final String itemData;

    /* renamed from: b, reason: from kotlin metadata */
    private final String productIdentifier;

    /* renamed from: c, reason: from kotlin metadata */
    private final BarcodePickState state;

    public BarcodePickViewHighlightStyleRequest(String itemData, String str, BarcodePickState state) {
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(state, "state");
        this.itemData = itemData;
        this.productIdentifier = str;
        this.state = state;
    }

    public final String getItemData() {
        return this.itemData;
    }

    public final String getProductIdentifier() {
        return this.productIdentifier;
    }

    public final BarcodePickState getState() {
        return this.state;
    }

    public final String toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("itemData", this.itemData);
        String str = this.productIdentifier;
        if (str != null) {
            jSONObject.put("productIdentifier", str);
        }
        jSONObject.put(SentryThread.JsonKeys.STATE, this.state.toJsonString());
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return jSONObject2;
    }
}
