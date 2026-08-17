package com.salesforce.marketingcloud.storage;

import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import com.salesforce.marketingcloud.util.Crypto;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes2.dex */
public interface e {
    public static final int a = 1;
    public static final int b = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    int a(InAppMessage inAppMessage, Crypto crypto) throws Exception;

    int a(Collection<String> collection);

    InAppMessage a(String str, Crypto crypto);

    InAppMessage a(Collection<String> collection, Crypto crypto);

    void a(InAppMessage inAppMessage);

    void b(String str, int i);

    JSONArray d(Crypto crypto);

    List<String> e(Crypto crypto);
}
