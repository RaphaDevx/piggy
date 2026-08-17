package com.tealium.internal.data;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class PublishSettings {
    public static final String KEY_BATTERY_SAVER = "battery_saver";
    public static final String KEY_COLLECT_ENABLED = "enable_collect";
    public static final String KEY_DISPATCH_EXPIRATION = "dispatch_expiration";
    public static final String KEY_EVENT_BATCH_SIZE = "event_batch_size";
    public static final String KEY_MINUTES_BETWEEN_REFRESH = "minutes_between_refresh";
    public static final String KEY_OFFLINE_DISPATCH_LIMIT = "offline_dispatch_limit";
    public static final String KEY_OVERRIDE_LOG = "override_log";
    public static final String KEY_TAG_MANAGEMENT_ENABLED = "enable_tag_management";
    public static final String KEY_WIFI_ONLY_SENDING = "wifi_only_sending";
    static final String l = null;
    private final String a;
    private final String b;
    private final boolean c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final float g;
    private final float h;
    private final int i;
    private final int j;
    private volatile int k;

    public static class DisabledLibraryException extends Exception {
    }

    private PublishSettings() {
        this.h = -1.0f;
        this.i = 100;
        this.j = 1;
        this.e = false;
        this.f = true;
        this.g = 15.0f;
        this.c = false;
        this.d = false;
        this.a = l;
        this.b = null;
    }

    private static boolean a(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-6f;
    }

    public static PublishSettings from(String str) throws DisabledLibraryException {
        if (str == null) {
            return new PublishSettings();
        }
        try {
            return new PublishSettings(new JSONObject(str), str);
        } catch (JSONException unused) {
            return new PublishSettings();
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PublishSettings)) {
            return super.equals(obj);
        }
        PublishSettings publishSettings = (PublishSettings) obj;
        return a(this.h, publishSettings.h) && this.i == publishSettings.i && this.j == publishSettings.j && this.e == publishSettings.e && this.f == publishSettings.f && TextUtils.equals(this.a, publishSettings.a) && this.c == publishSettings.c && this.d == publishSettings.d && this.e == publishSettings.e && a(this.g, publishSettings.g);
    }

    public float getDispatchExpiration() {
        return this.h;
    }

    public int getEventBatchSize() {
        return this.j;
    }

    public int getEventMaxBatchSize() {
        if (getEventBatchSize() > 10) {
            return 10;
        }
        return getEventBatchSize();
    }

    public int getGzipThreshold() {
        return 1000;
    }

    public float getMinutesBetweenRefresh() {
        return this.g;
    }

    public int getOfflineDispatchLimit() {
        return this.i;
    }

    public String getOverrideLog() {
        return this.a;
    }

    public String getSource() {
        return this.b;
    }

    public int hashCode() {
        int i = this.k;
        if (i != 0) {
            return i;
        }
        String str = this.a;
        int hashCode = (((((((((((((((((str == null ? 0 : str.hashCode()) + 527) * 31) + Float.floatToIntBits(this.g)) * 31) + Float.floatToIntBits(this.h)) * 31) + this.i) * 31) + this.j) * 31) + (this.c ? 1 : 0)) * 31) + (this.d ? 1 : 0)) * 31) + (this.e ? 1 : 0)) * 31) + (this.f ? 1 : 0);
        this.k = hashCode;
        return hashCode;
    }

    public boolean isBatchingEnabled() {
        return getEventBatchSize() > 1;
    }

    public boolean isBatterySaver() {
        return this.f;
    }

    public boolean isCollectEnabled() {
        return this.d;
    }

    public boolean isTagManagementEnabled() {
        return this.c;
    }

    public boolean isWifiOnlySending() {
        return this.e;
    }

    public String toString() {
        return toString(null);
    }

    public String toString(String str) {
        String str2;
        String property = System.getProperty("line.separator");
        if (str == null) {
            str = "";
        }
        if (str.length() == 0) {
            str2 = "    ";
        } else {
            str2 = str + str;
        }
        StringBuilder sb = new StringBuilder("{");
        sb.append(property);
        sb.append(str2);
        sb.append("battery_saver : ");
        sb.append(this.f);
        sb.append(',');
        sb.append(property);
        sb.append(str2);
        sb.append("dispatch_expiration : ");
        sb.append(this.h);
        sb.append(',');
        sb.append(property);
        sb.append(str2);
        sb.append("enable_collect : ");
        sb.append(this.d);
        sb.append(',');
        sb.append(property);
        sb.append(str2);
        sb.append("enable_tag_management : ");
        sb.append(this.c);
        sb.append(',');
        sb.append(property);
        sb.append(str2);
        sb.append("event_batch_size : ");
        sb.append(this.j);
        sb.append(',');
        sb.append(property);
        sb.append(str2);
        sb.append("minutes_between_refresh : ");
        sb.append(this.g);
        sb.append(',');
        sb.append(property);
        sb.append(str2);
        sb.append("offline_dispatch_limit : ");
        sb.append(this.i);
        sb.append(',');
        sb.append(property);
        sb.append(str2);
        sb.append("override_log : ");
        sb.append(TextUtils.isEmpty(this.a) ? "\"no override\"" : this.a);
        sb.append(',');
        sb.append(property);
        sb.append(str2);
        sb.append("wifi_only_sending : ");
        sb.append(this.e);
        sb.append(property);
        sb.append(str);
        sb.append('}');
        return sb.toString();
    }

    public static PublishSettings from(JSONObject jSONObject) throws DisabledLibraryException {
        if (jSONObject == null) {
            return new PublishSettings();
        }
        try {
            return new PublishSettings(jSONObject, jSONObject.toString());
        } catch (JSONException unused) {
            return new PublishSettings();
        }
    }

    private PublishSettings(JSONObject jSONObject, String str) throws JSONException, DisabledLibraryException {
        if (jSONObject.optBoolean("_is_enabled", true)) {
            this.h = (float) jSONObject.optDouble(KEY_DISPATCH_EXPIRATION, -1.0d);
            this.i = jSONObject.optInt(KEY_OFFLINE_DISPATCH_LIMIT, 100);
            this.j = jSONObject.optInt(KEY_EVENT_BATCH_SIZE, 1);
            this.e = jSONObject.optBoolean(KEY_WIFI_ONLY_SENDING, false);
            this.f = jSONObject.optBoolean(KEY_BATTERY_SAVER, true);
            this.g = (float) jSONObject.optDouble(KEY_MINUTES_BETWEEN_REFRESH, 15.0d);
            this.c = jSONObject.optBoolean(KEY_TAG_MANAGEMENT_ENABLED, false);
            this.d = jSONObject.optBoolean(KEY_COLLECT_ENABLED, false);
            this.a = jSONObject.optString(KEY_OVERRIDE_LOG, l);
            this.b = str;
            return;
        }
        throw new DisabledLibraryException();
    }
}
