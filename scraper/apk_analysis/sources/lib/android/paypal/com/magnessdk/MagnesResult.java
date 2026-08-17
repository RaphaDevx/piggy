package lib.android.paypal.com.magnessdk;

import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class MagnesResult {
    private JSONObject deviceInfo;
    private String paypalclientmetadataid;

    public JSONObject getDeviceInfo() {
        return this.deviceInfo;
    }

    public String getPaypalClientMetaDataId() {
        return this.paypalclientmetadataid;
    }

    MagnesResult setDeviceInfo(JSONObject jSONObject) {
        this.deviceInfo = jSONObject;
        return this;
    }

    MagnesResult setPaypalClientMetaDataId(String str) {
        this.paypalclientmetadataid = str;
        return this;
    }
}
