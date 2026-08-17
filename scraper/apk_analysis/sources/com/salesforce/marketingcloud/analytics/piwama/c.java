package com.salesforce.marketingcloud.analytics.piwama;

import com.salesforce.marketingcloud.internal.o;
import com.tealium.library.DataSources;
import java.util.Date;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public interface c {
    String a();

    default void a(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        jSONObject.put("analyticType", b());
        jSONObject.put("api_endpoint", d());
        if (a().length() > 0) {
            jSONObject.put(DataSources.Key.EVENT_NAME, a());
        }
        jSONObject.put("timestamp", o.a(e()));
    }

    int b();

    JSONObject c();

    String d();

    Date e();

    default String a(String str, String fieldName, boolean z) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
        String obj = StringsKt.trim((CharSequence) str).toString();
        int length = obj.length();
        if (length == 0) {
            throw new IllegalArgumentException("PiEvent must contain a " + fieldName + ".");
        }
        if (length <= 1024) {
            return obj;
        }
        String substring = obj.substring(0, 1024);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return StringsKt.trim((CharSequence) substring).toString();
    }
}
