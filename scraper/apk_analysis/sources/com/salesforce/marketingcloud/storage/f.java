package com.salesforce.marketingcloud.storage;

import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.util.Crypto;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public interface f {

    public enum a {
        UNREAD,
        READ,
        DELETED,
        NOT_DELETED
    }

    public static class b {
        public final String a;
        public final String b;
        public final Date c;
        public final boolean d;
        public final boolean e;
        public final boolean f;

        public b(String str, String str2, Date date, boolean z, boolean z2, boolean z3) {
            this.a = str;
            this.b = str2;
            this.c = date;
            this.d = z;
            this.e = z2;
            this.f = z3;
        }
    }

    int a(a aVar);

    int a(List<String> list);

    InboxMessage a(String str, Crypto crypto);

    List<InboxMessage> a(Crypto crypto, a aVar);

    void a(InboxMessage inboxMessage, Crypto crypto);

    void b();

    void b(String[] strArr);

    void c(String str);

    void d(String str);

    boolean e(String str);

    b f(String str);

    int h();

    List<b> i();

    void j();

    List<InboxMessage> n(Crypto crypto);
}
