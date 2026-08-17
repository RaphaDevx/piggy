package com.salesforce.marketingcloud.analytics.etanalytics;

import android.text.TextUtils;
import com.salesforce.marketingcloud.analytics.i;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.messages.inbox.InboxMessage;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.storage.h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

/* loaded from: classes2.dex */
public class a extends i {
    private static final int f = 0;
    private final h d;
    private final n e;

    /* renamed from: com.salesforce.marketingcloud.analytics.etanalytics.a$a, reason: collision with other inner class name */
    class C0046a extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ h c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C0046a(String str, Object[] objArr, h hVar) {
            super(str, objArr);
            this.c = hVar;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            this.c.h().b(0);
        }
    }

    public a(h hVar, n nVar) {
        this.d = hVar;
        this.e = nVar;
    }

    public static void a(h hVar, n nVar, boolean z) {
        if (z) {
            a(nVar, hVar);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.j
    public void b(NotificationMessage notificationMessage) {
        Region region = notificationMessage.region();
        if (TextUtils.isEmpty(notificationMessage.id()) || region == null) {
            return;
        }
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.h(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(new Date(), 0, 3, Arrays.asList(notificationMessage.id(), region.id()), notificationMessage.requestId(), true)));
    }

    private static void a(n nVar, h hVar) {
        nVar.b().execute(new C0046a("delete_analytics", new Object[0], hVar));
    }

    @Override // com.salesforce.marketingcloud.analytics.i
    public void a(boolean z) {
        if (z) {
            a(this.e, this.d);
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.j
    public void a(NotificationMessage notificationMessage, boolean z) {
        if (notificationMessage.region() != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(notificationMessage.id());
            arrayList.add(notificationMessage.region().id());
            com.salesforce.marketingcloud.analytics.b a = com.salesforce.marketingcloud.analytics.b.a(new Date(), 0, 17, arrayList, notificationMessage.requestId(), true);
            a.b(z ? 1 : 0);
            this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.h(), this.d.b(), a));
        }
    }

    @Override // com.salesforce.marketingcloud.analytics.i, com.salesforce.marketingcloud.analytics.g
    public void a(InboxMessage inboxMessage) {
        this.e.b().execute(new com.salesforce.marketingcloud.analytics.a(this.d.h(), this.d.b(), com.salesforce.marketingcloud.analytics.b.a(new Date(), 0, 14, Collections.singletonList(inboxMessage.id()), com.salesforce.marketingcloud.internal.d.b(inboxMessage), true)));
    }
}
