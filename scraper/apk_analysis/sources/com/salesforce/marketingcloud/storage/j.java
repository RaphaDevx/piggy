package com.salesforce.marketingcloud.storage;

import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.List;

/* loaded from: classes2.dex */
public interface j {
    Region a(String str, Crypto crypto);

    List<Region> a(int i, Crypto crypto);

    void a(Region region, Crypto crypto) throws Exception;

    void a(String str, boolean z);

    List<String> c(String str, int i);

    List<String> d(int i);

    int f(int i);

    void l();

    Region m(Crypto crypto);
}
