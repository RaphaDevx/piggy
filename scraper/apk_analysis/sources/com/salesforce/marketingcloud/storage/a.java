package com.salesforce.marketingcloud.storage;

import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.List;

/* loaded from: classes2.dex */
public interface a {
    int a();

    int a(int i);

    int a(String[] strArr);

    void a(com.salesforce.marketingcloud.analytics.b bVar, Crypto crypto) throws Exception;

    int b(int i);

    int b(com.salesforce.marketingcloud.analytics.b bVar, Crypto crypto) throws Exception;

    List<com.salesforce.marketingcloud.analytics.b> b(Region region, Crypto crypto);

    List<com.salesforce.marketingcloud.analytics.b> c(Crypto crypto);

    boolean c(int i);

    int d();

    int e();

    int g(int i);

    List<com.salesforce.marketingcloud.analytics.b> g(Crypto crypto);

    List<com.salesforce.marketingcloud.analytics.b> h(Crypto crypto);

    List<com.salesforce.marketingcloud.analytics.b> i(Crypto crypto);

    List<com.salesforce.marketingcloud.analytics.b> o(Crypto crypto);
}
