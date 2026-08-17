package com.salesforce.marketingcloud.storage;

import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.List;

/* loaded from: classes2.dex */
public interface i {
    int a(String str);

    int a(String str, int i);

    Message a(String str, Crypto crypto);

    List<Message> a(Crypto crypto);

    void a(Message message, Crypto crypto) throws Exception;

    List<Message> b(Crypto crypto);

    int e(int i);
}
