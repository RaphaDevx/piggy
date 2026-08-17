package com.salesforce.marketingcloud.messages.inbox;

import com.salesforce.marketingcloud.internal.o;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class b {
    private static final String A = "messageType";
    private static final String B = "contentType";
    private static final String C = "notificationMessage";
    private static final int D = 1;
    public static final String a = "requestId";
    public static final String b = "title";
    public static final String c = "alert";
    public static final String d = "sound";
    public static final String e = "media";
    public static final String f = "url";
    public static final String g = "custom";
    public static final String h = "keys";
    public static final String i = "subtitle";
    public static final String j = "type";
    public static final String k = "androidUrl";
    public static final String l = "alt";
    public static final String m = "richFeatures";
    public static final String n = "trigger";
    public static final String o = "id";
    private static final String p = "hash";
    private static final String q = "subject";
    private static final String r = "startDateUtc";
    private static final String s = "endDateUtc";
    private static final String t = "_endDt";
    private static final String u = "sendDateUtc";
    private static final String v = "viewCount";
    private static final String w = "isDeleted";
    private static final String x = "inboxMessage";
    private static final String y = "inboxSubtitle";
    private static final String z = "calculatedType";

    public static final JSONObject a(InboxMessage.Media media) {
        Intrinsics.checkNotNullParameter(media, "<this>");
        JSONObject jSONObject = new JSONObject();
        if (media.getUrl() != null) {
            jSONObject.put(k, media.getUrl());
        }
        if (media.getAltText() != null) {
            jSONObject.put(l, media.getAltText());
        }
        return jSONObject;
    }

    public static final InboxMessage.Media a(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        String optString = jSONObject.optString(k);
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        String b2 = o.b(optString);
        String optString2 = jSONObject.optString(l);
        Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
        String b3 = o.b(optString2);
        if (b2 == null && b3 == null) {
            return null;
        }
        if (b2 == null) {
            b2 = "";
        }
        return new InboxMessage.Media(b2, b3);
    }
}
