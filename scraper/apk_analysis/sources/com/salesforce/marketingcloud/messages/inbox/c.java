package com.salesforce.marketingcloud.messages.inbox;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.UrlHandler;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.messages.inbox.InboxMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.storage.f;
import com.tealium.remotecommands.firebase.FirebaseConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
class c implements InboxMessageManager {
    private static final String p = "inbox_watermark_key";
    final com.salesforce.marketingcloud.storage.h d;
    final com.salesforce.marketingcloud.analytics.g e;
    final com.salesforce.marketingcloud.http.d f;
    final MarketingCloudConfig g;
    final String h;
    private final com.salesforce.marketingcloud.alarms.b j;
    private final n k;
    private InboxMessageManager.InboxRefreshListener n;
    private boolean o;
    private final Set<InboxMessageManager.InboxResponseListener> i = new ArraySet();
    private final Object l = new Object();
    private final Object m = new Object();

    class a extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ String c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.c = str2;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            c.this.d.l().b(TextUtils.split(this.c, FirebaseConstants.SEPARATOR));
        }
    }

    class b extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ boolean c;

        class a implements MarketingCloudSdk.WhenReadyListener {
            final /* synthetic */ com.salesforce.marketingcloud.http.a a;

            a(com.salesforce.marketingcloud.http.a aVar) {
                this.a = aVar;
            }

            @Override // com.salesforce.marketingcloud.MarketingCloudSdk.WhenReadyListener
            public void ready(MarketingCloudSdk marketingCloudSdk) {
                c cVar = c.this;
                com.salesforce.marketingcloud.http.d dVar = cVar.f;
                com.salesforce.marketingcloud.http.a aVar = this.a;
                MarketingCloudConfig marketingCloudConfig = cVar.g;
                com.salesforce.marketingcloud.storage.b c = cVar.d.c();
                String applicationId = c.this.g.applicationId();
                c cVar2 = c.this;
                dVar.a(aVar.a(marketingCloudConfig, c, com.salesforce.marketingcloud.http.a.a(applicationId, cVar2.h, cVar2.d())));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, boolean z) {
            super(str, objArr);
            this.c = z;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            MarketingCloudSdk.requestSdk(new a(this.c ? com.salesforce.marketingcloud.http.a.l : com.salesforce.marketingcloud.http.a.k));
        }
    }

    /* renamed from: com.salesforce.marketingcloud.messages.inbox.c$c, reason: collision with other inner class name */
    class C0069c extends com.salesforce.marketingcloud.internal.i {
        C0069c(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            List<f.b> i = c.this.d.l().i();
            int size = i.size();
            if (size > 0) {
                ArrayList arrayList = new ArrayList(size);
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("deviceId", c.this.h);
                    String a = com.salesforce.marketingcloud.util.j.a(new Date());
                    for (f.b bVar : i) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("actionParameters", jSONObject);
                        jSONObject2.put("messageId", bVar.a);
                        jSONObject2.put("actionDate", a);
                        jSONObject2.put(UrlHandler.ACTION, bVar.e ? "Deleted" : "Viewed");
                        jSONArray.put(jSONObject2);
                        arrayList.add(bVar.a);
                    }
                    com.salesforce.marketingcloud.http.a aVar = com.salesforce.marketingcloud.http.a.m;
                    c cVar = c.this;
                    com.salesforce.marketingcloud.http.b a2 = aVar.a(cVar.g, cVar.d.c(), com.salesforce.marketingcloud.http.a.a(c.this.g.applicationId()), jSONArray.toString());
                    a2.a(TextUtils.join(FirebaseConstants.SEPARATOR, arrayList));
                    c.this.f.a(a2);
                } catch (JSONException e) {
                    com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e, "Failed to create Inbox status payload.  Status updates not sent to Marketing Cloud", new Object[0]);
                }
            }
        }
    }

    class d extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ com.salesforce.marketingcloud.storage.f c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, com.salesforce.marketingcloud.storage.f fVar) {
            super(str, objArr);
            this.c = fVar;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            this.c.h();
        }
    }

    class e extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ InboxMessage c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, InboxMessage inboxMessage) {
            super(str, objArr);
            this.c = inboxMessage;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            c.this.d.l().a(this.c, c.this.d.b());
        }
    }

    class f extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ String c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.c = str2;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            c.this.setMessageRead(this.c);
        }
    }

    class g extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ String c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        g(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.c = str2;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            c.this.d.l().c(this.c);
        }
    }

    class h extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ String c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        h(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.c = str2;
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            c.this.d.l().d(this.c);
        }
    }

    class i extends com.salesforce.marketingcloud.internal.i {
        i(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            c.this.d.l().j();
        }
    }

    class j extends com.salesforce.marketingcloud.internal.i {
        j(String str, Object... objArr) {
            super(str, objArr);
        }

        @Override // com.salesforce.marketingcloud.internal.i
        protected void a() {
            c.this.d.l().b();
        }
    }

    class k implements Runnable {
        k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.b(false);
        }
    }

    class l extends com.salesforce.marketingcloud.internal.i {
        final /* synthetic */ List c;

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                l lVar = l.this;
                c.this.a(lVar.c);
                c.this.b(true);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        l(String str, Object[] objArr, List list) {
            super(str, objArr);
            this.c = list;
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x0086  */
        @Override // com.salesforce.marketingcloud.internal.i
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected void a() {
            /*
                r9 = this;
                com.salesforce.marketingcloud.messages.inbox.c r0 = com.salesforce.marketingcloud.messages.inbox.c.this
                com.salesforce.marketingcloud.storage.h r0 = r0.d
                com.salesforce.marketingcloud.storage.f r0 = r0.l()
                com.salesforce.marketingcloud.messages.inbox.c r1 = com.salesforce.marketingcloud.messages.inbox.c.this
                com.salesforce.marketingcloud.storage.h r1 = r1.d
                com.salesforce.marketingcloud.util.Crypto r1 = r1.b()
                java.util.List r2 = r9.c
                boolean r2 = r2.isEmpty()
                if (r2 != 0) goto Lac
                java.util.List r2 = java.util.Collections.emptyList()
                java.util.List r3 = r9.c
                java.util.Iterator r3 = r3.iterator()
            L22:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto La9
                java.lang.Object r4 = r3.next()
                com.salesforce.marketingcloud.messages.inbox.InboxMessage r4 = (com.salesforce.marketingcloud.messages.inbox.InboxMessage) r4
                boolean r5 = r4.getDeleted()
                if (r5 == 0) goto L45
                boolean r5 = r2.isEmpty()
                if (r5 == 0) goto L3f
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>()
            L3f:
                java.lang.String r4 = r4.id
                r2.add(r4)
                goto L22
            L45:
                java.lang.String r5 = r4.id()
                com.salesforce.marketingcloud.storage.f$b r5 = r0.f(r5)
                r6 = 1
                if (r5 == 0) goto L9b
                java.lang.String r7 = r5.b
                if (r7 != 0) goto L5f
                boolean r7 = r5.e
                com.salesforce.marketingcloud.internal.d.a(r4, r7)
                boolean r7 = r5.d
                com.salesforce.marketingcloud.internal.d.c(r4, r7)
                goto L7a
            L5f:
                java.lang.String r8 = com.salesforce.marketingcloud.internal.d.a(r4)
                boolean r7 = r7.equals(r8)
                if (r7 == 0) goto L7a
                boolean r7 = r5.e
                com.salesforce.marketingcloud.internal.d.a(r4, r7)
                boolean r7 = r5.d
                com.salesforce.marketingcloud.internal.d.c(r4, r7)
                java.util.Date r7 = r5.c
                if (r7 != 0) goto L78
                goto L7a
            L78:
                r7 = 0
                goto L7b
            L7a:
                r7 = r6
            L7b:
                boolean r8 = r5.f
                com.salesforce.marketingcloud.internal.d.b(r4, r8)
                int r8 = com.salesforce.marketingcloud.internal.d.c(r4)
                if (r8 <= 0) goto L89
                com.salesforce.marketingcloud.internal.d.c(r4, r6)
            L89:
                boolean r8 = r5.e
                if (r8 != 0) goto L97
                boolean r5 = r5.d
                if (r5 == 0) goto L9a
                int r5 = com.salesforce.marketingcloud.internal.d.c(r4)
                if (r5 != 0) goto L9a
            L97:
                com.salesforce.marketingcloud.internal.d.b(r4, r6)
            L9a:
                r6 = r7
            L9b:
                r0.a(r4, r1)
                if (r6 == 0) goto L22
                com.salesforce.marketingcloud.messages.inbox.c r5 = com.salesforce.marketingcloud.messages.inbox.c.this
                com.salesforce.marketingcloud.analytics.g r5 = r5.e
                r5.a(r4)
                goto L22
            La9:
                r0.a(r2)
            Lac:
                android.os.Handler r0 = new android.os.Handler
                android.os.Looper r1 = android.os.Looper.getMainLooper()
                r0.<init>(r1)
                com.salesforce.marketingcloud.messages.inbox.c$l$a r1 = new com.salesforce.marketingcloud.messages.inbox.c$l$a
                r1.<init>()
                r0.post(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.inbox.c.l.a():void");
        }
    }

    c(MarketingCloudConfig marketingCloudConfig, com.salesforce.marketingcloud.storage.h hVar, String str, com.salesforce.marketingcloud.alarms.b bVar, com.salesforce.marketingcloud.http.d dVar, com.salesforce.marketingcloud.analytics.g gVar, n nVar) {
        this.g = marketingCloudConfig;
        this.d = hVar;
        this.h = str;
        this.j = bVar;
        this.f = dVar;
        this.e = gVar;
        this.k = nVar;
    }

    static void a(com.salesforce.marketingcloud.storage.h hVar, com.salesforce.marketingcloud.alarms.b bVar, n nVar, boolean z) {
        bVar.d(a.EnumC0042a.g);
        if (z) {
            nVar.b().execute(new d("inbox_shutdown", new Object[0], hVar.l()));
        }
    }

    void b() {
        this.o = true;
        a(false);
    }

    JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.salesforce.marketingcloud.storage.db.g.e, this.d.l().n(this.d.b()));
            return jSONObject;
        } catch (JSONException e2) {
            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "Failed to create our component state JSONObject.", new Object[0]);
            return null;
        }
    }

    String d() {
        String string;
        synchronized (this.l) {
            string = this.d.e().getString(p, com.salesforce.marketingcloud.util.j.a(new Date(0L)));
        }
        return string;
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void deleteMessage(InboxMessage inboxMessage) {
        if (inboxMessage == null) {
            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, "InboxMessage was null and could not be updated.  Call to deleteMessage() ignored.", new Object[0]);
        } else {
            com.salesforce.marketingcloud.internal.d.a(inboxMessage, true);
            deleteMessage(inboxMessage.id());
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void disableInbox() {
    }

    void e() {
        this.k.b().execute(new C0069c("send_inbox_message_status", new Object[0]));
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void enableInbox() {
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public int getDeletedMessageCount() {
        return this.d.l().a(f.a.DELETED);
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public List<InboxMessage> getDeletedMessages() {
        return this.d.l().a(this.d.b(), f.a.DELETED);
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public int getMessageCount() {
        return this.d.l().a(f.a.NOT_DELETED);
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public List<InboxMessage> getMessages() {
        return this.d.l().a(this.d.b(), f.a.NOT_DELETED);
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public int getReadMessageCount() {
        return this.d.l().a(f.a.READ);
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public List<InboxMessage> getReadMessages() {
        return this.d.l().a(this.d.b(), f.a.READ);
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public int getUnreadMessageCount() {
        return this.d.l().a(f.a.UNREAD);
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public List<InboxMessage> getUnreadMessages() {
        return this.d.l().a(this.d.b(), f.a.UNREAD);
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public boolean isInboxEnabled() {
        return true;
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void markAllMessagesDeleted() {
        this.k.b().execute(new j("delete_all", new Object[0]));
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void markAllMessagesRead() {
        this.k.b().execute(new i("mark_all_read", new Object[0]));
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void refreshInbox(InboxMessageManager.InboxRefreshListener inboxRefreshListener) {
        synchronized (this.m) {
            if (this.n == null) {
                this.n = inboxRefreshListener;
                com.salesforce.marketingcloud.g.d(InboxMessageManager.TAG, "Refreshing inbox messages", new Object[0]);
                a(true);
            } else {
                com.salesforce.marketingcloud.g.d(InboxMessageManager.TAG, "Refresh already in progress.", new Object[0]);
                try {
                    inboxRefreshListener.onRefreshComplete(false);
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "Error delivering Refresh Complete result to %s", inboxRefreshListener.getClass().getName());
                }
            }
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void registerInboxResponseListener(InboxMessageManager.InboxResponseListener inboxResponseListener) {
        if (inboxResponseListener != null) {
            synchronized (this.i) {
                this.i.add(inboxResponseListener);
            }
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void setMessageRead(InboxMessage inboxMessage) {
        if (inboxMessage == null) {
            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, "InboxMessage was null and could not be updated.  Call to setMessageRead() ignored.", new Object[0]);
        } else {
            com.salesforce.marketingcloud.internal.d.c(inboxMessage, true);
            setMessageRead(inboxMessage.id());
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void unregisterInboxResponseListener(InboxMessageManager.InboxResponseListener inboxResponseListener) {
        synchronized (this.i) {
            this.i.remove(inboxResponseListener);
        }
    }

    void b(boolean z) {
        synchronized (this.m) {
            InboxMessageManager.InboxRefreshListener inboxRefreshListener = this.n;
            if (inboxRefreshListener != null) {
                try {
                    inboxRefreshListener.onRefreshComplete(z);
                } catch (Exception e2) {
                    com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "InboxRefreshListener threw an exception", new Object[0]);
                }
                this.n = null;
            }
        }
    }

    void a() {
        this.o = false;
        e();
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void deleteMessage(String str) {
        this.k.b().execute(new g("inbox_delete", new Object[0], str));
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void setMessageRead(String str) {
        this.k.b().execute(new h("mark_read", new Object[0], str));
    }

    void a(InboxMessage inboxMessage) {
        this.k.b().execute(new e("inbox_push_received", new Object[0], inboxMessage));
        if (this.o) {
            a(false);
        }
    }

    void b(List<InboxMessage> list) {
        this.k.b().execute(new l("inbox_updated", new Object[0], list));
    }

    void b(int i2, String str) {
        com.salesforce.marketingcloud.g.c(InboxMessageManager.TAG, "Request failed: %d - %s", Integer.valueOf(i2), str);
        this.j.b(a.EnumC0042a.g);
    }

    void a(NotificationMessage notificationMessage) {
        if (this.g.markMessageReadOnInboxNotificationOpen()) {
            this.k.b().execute(new f("inbox_notification_opened", new Object[0], notificationMessage.id()));
        }
    }

    void a(com.salesforce.marketingcloud.http.e eVar) {
        int length;
        try {
            JSONObject jSONObject = new JSONObject(eVar.i());
            JSONArray optJSONArray = jSONObject.optJSONArray(com.salesforce.marketingcloud.storage.db.i.e);
            List<InboxMessage> emptyList = Collections.emptyList();
            if (optJSONArray != null && (length = optJSONArray.length()) > 0) {
                emptyList = new ArrayList<>(length);
                for (int i2 = 0; i2 < length; i2++) {
                    try {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                        emptyList.add(new InboxMessage(jSONObject2, jSONObject2.optBoolean("isDeleted")));
                    } catch (Exception e2) {
                        com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "Failed to parse inbox message", new Object[0]);
                    }
                }
            }
            b(emptyList);
            a(jSONObject.optString("waterMark"));
        } catch (Exception e3) {
            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e3, "Failed to parse inbox messages response", new Object[0]);
            a(-1, "Failed to parse response");
        }
    }

    void a(String str) {
        if (!com.salesforce.marketingcloud.util.j.f(str)) {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Could not convert watermark to a date", new Object[0]);
            return;
        }
        synchronized (this.l) {
            this.d.e().edit().putString(p, str).apply();
        }
    }

    void a(int i2, String str) {
        com.salesforce.marketingcloud.g.c(InboxMessageManager.TAG, "Request failed: %d - %s", Integer.valueOf(i2), str);
        new Handler(Looper.getMainLooper()).post(new k());
    }

    void a(List<InboxMessage> list) {
        synchronized (this.i) {
            if (!this.i.isEmpty()) {
                for (InboxMessageManager.InboxResponseListener inboxResponseListener : this.i) {
                    if (inboxResponseListener != null) {
                        try {
                            inboxResponseListener.onInboxMessagesChanged(list);
                        } catch (Exception e2) {
                            com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e2, "%s threw an exception while processing the inbox messages response", inboxResponseListener.getClass().getName());
                        }
                    }
                }
            }
        }
    }

    void a(com.salesforce.marketingcloud.http.b bVar) {
        if (bVar.q() != null) {
            this.j.c(a.EnumC0042a.g);
            this.k.b().execute(new a("inbox_status_updated", new Object[0], bVar.q()));
        }
    }

    private void a(boolean z) {
        this.k.b().execute(new b("fetch_inbox_messages", new Object[0], z));
    }
}
