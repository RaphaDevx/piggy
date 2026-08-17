package com.salesforce.marketingcloud.analytics;

import org.json.JSONObject;

/* loaded from: classes2.dex */
public interface l {

    public enum a {
        SYNC_API("deviceSyncCallTimeMs"),
        TRIGGER_PROCESS("inAppMsgProcessingTimeMs");

        private final String b;

        a(String str) {
            this.b = str;
        }

        public String b() {
            return this.b;
        }
    }

    void a(a aVar, JSONObject jSONObject);
}
