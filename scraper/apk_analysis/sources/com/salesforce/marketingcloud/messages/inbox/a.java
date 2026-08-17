package com.salesforce.marketingcloud.messages.inbox;

import android.os.Bundle;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.analytics.g;
import com.salesforce.marketingcloud.e;
import com.salesforce.marketingcloud.http.d;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.messages.inbox.InboxMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.storage.h;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class a implements e, InboxMessageManager, com.salesforce.marketingcloud.behaviors.b, d.c, b.InterfaceC0044b {
    protected static final String n = "8";
    private static final String o = "InboxMessagingEnabled";
    private static final Object p = new Object();
    private final MarketingCloudConfig d;
    private final h e;
    private final String f;
    private final com.salesforce.marketingcloud.behaviors.c g;
    private final com.salesforce.marketingcloud.alarms.b h;
    private final d i;
    private final g j;
    private c k;
    private n l;
    private com.salesforce.marketingcloud.toggles.a m;

    /* renamed from: com.salesforce.marketingcloud.messages.inbox.a$a, reason: collision with other inner class name */
    static /* synthetic */ class C0068a {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[a.EnumC0042a.values().length];
            c = iArr;
            try {
                iArr[a.EnumC0042a.g.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            int[] iArr2 = new int[com.salesforce.marketingcloud.http.a.values().length];
            b = iArr2;
            try {
                iArr2[com.salesforce.marketingcloud.http.a.k.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[com.salesforce.marketingcloud.http.a.l.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[com.salesforce.marketingcloud.http.a.m.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr3 = new int[com.salesforce.marketingcloud.behaviors.a.values().length];
            a = iArr3;
            try {
                iArr3[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_NOTIFICATION_OPENED.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public a(MarketingCloudConfig marketingCloudConfig, h hVar, String str, com.salesforce.marketingcloud.behaviors.c cVar, com.salesforce.marketingcloud.alarms.b bVar, d dVar, n nVar, g gVar) {
        this.d = (MarketingCloudConfig) com.salesforce.marketingcloud.util.g.a(marketingCloudConfig, "MarketingCloudConfig is null.");
        this.e = (h) com.salesforce.marketingcloud.util.g.a(hVar, "Storage is null.");
        this.f = (String) com.salesforce.marketingcloud.util.g.a(str, "You must provide the Device ID.");
        this.g = (com.salesforce.marketingcloud.behaviors.c) com.salesforce.marketingcloud.util.g.a(cVar, "BehaviorManager is null.");
        this.h = (com.salesforce.marketingcloud.alarms.b) com.salesforce.marketingcloud.util.g.a(bVar, "AlarmScheduler is null.");
        this.i = (d) com.salesforce.marketingcloud.util.g.a(dVar, "RequestManager is null.");
        this.j = (g) com.salesforce.marketingcloud.util.g.a(gVar, "InboxAnalyticEventListener is null.");
        this.l = nVar;
    }

    public static boolean a(Map<String, String> map) {
        return n.equals(map.get(NotificationMessage.NOTIF_KEY_MESSAGE_TYPE));
    }

    @Override // com.salesforce.marketingcloud.d
    public final String componentName() {
        return "InboxMessageManager";
    }

    @Override // com.salesforce.marketingcloud.d
    public JSONObject componentState() {
        try {
            c cVar = this.k;
            JSONObject c = cVar != null ? cVar.c() : new JSONObject();
            c.put("inboxEnabled", isInboxEnabled());
            return c;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.salesforce.marketingcloud.e
    public void controlChannelInit(int i) {
        if (!com.salesforce.marketingcloud.b.a(i, 128)) {
            if (this.k == null && isInboxEnabled()) {
                a();
                return;
            }
            return;
        }
        this.k = null;
        c.a(this.e, this.h, this.l, com.salesforce.marketingcloud.b.c(i, 128));
        this.g.a(this);
        this.h.e(a.EnumC0042a.g);
        this.i.a(com.salesforce.marketingcloud.http.a.k);
        this.i.a(com.salesforce.marketingcloud.http.a.l);
        this.i.a(com.salesforce.marketingcloud.http.a.m);
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void deleteMessage(InboxMessage inboxMessage) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.deleteMessage(inboxMessage);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to deleteMessage() was ignored.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void disableInbox() {
        synchronized (p) {
            com.salesforce.marketingcloud.toggles.a aVar = com.salesforce.marketingcloud.toggles.a.d;
            this.m = aVar;
            this.e.e().edit().putString(o, this.m.name()).apply();
            com.salesforce.marketingcloud.g.a(InboxMessageManager.TAG, "Inbox runtime toggle set to " + aVar.name(), new Object[0]);
            this.k = null;
            tearDown(false);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void enableInbox() {
        synchronized (p) {
            if (com.salesforce.marketingcloud.b.a(com.salesforce.marketingcloud.b.a(this.e.j()), 128)) {
                return;
            }
            String str = InboxMessageManager.TAG;
            StringBuilder sb = new StringBuilder("Inbox runtime toggle set to ");
            com.salesforce.marketingcloud.toggles.a aVar = com.salesforce.marketingcloud.toggles.a.c;
            sb.append(aVar.name());
            com.salesforce.marketingcloud.g.a(str, sb.toString(), new Object[0]);
            this.m = aVar;
            this.e.e().edit().putString(o, this.m.name()).apply();
            a();
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public int getDeletedMessageCount() {
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getDeletedMessageCount();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getDeletedMessageCount() was ignored.", new Object[0]);
        return 0;
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public List<InboxMessage> getDeletedMessages() {
        List<InboxMessage> emptyList = Collections.emptyList();
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getDeletedMessages();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getDeletedMessages() was ignored.", new Object[0]);
        return emptyList;
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public int getMessageCount() {
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getMessageCount();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getMessageCount() was ignored.", new Object[0]);
        return 0;
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public List<InboxMessage> getMessages() {
        List<InboxMessage> emptyList = Collections.emptyList();
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getMessages();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getMessages() was ignored.", new Object[0]);
        return emptyList;
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public int getReadMessageCount() {
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getReadMessageCount();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getReadMessageCount() was ignored.", new Object[0]);
        return 0;
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public List<InboxMessage> getReadMessages() {
        List<InboxMessage> emptyList = Collections.emptyList();
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getReadMessages();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getReadMessages() was ignored.", new Object[0]);
        return emptyList;
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public int getUnreadMessageCount() {
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getUnreadMessageCount();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getUnreadMessageCount() was ignored.", new Object[0]);
        return 0;
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public List<InboxMessage> getUnreadMessages() {
        List<InboxMessage> emptyList = Collections.emptyList();
        c cVar = this.k;
        if (cVar != null) {
            return cVar.getUnreadMessages();
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to getUnreadMessages() was ignored.", new Object[0]);
        return emptyList;
    }

    @Override // com.salesforce.marketingcloud.e
    public void init(InitializationStatus.a aVar, int i) {
        if (a(i)) {
            a();
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public boolean isInboxEnabled() {
        return a(com.salesforce.marketingcloud.b.a(this.e.j()));
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void markAllMessagesDeleted() {
        c cVar = this.k;
        if (cVar != null) {
            cVar.markAllMessagesDeleted();
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to markAllMessagesDeleted() was ignored.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void markAllMessagesRead() {
        c cVar = this.k;
        if (cVar != null) {
            cVar.markAllMessagesRead();
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to markAllMessagesRead() was ignored.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.behaviors.b
    public void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        NotificationMessage notificationMessage;
        if (this.k != null) {
            int i = C0068a.a[aVar.ordinal()];
            if (i == 1) {
                this.k.b();
                return;
            }
            if (i == 2) {
                this.k.a();
                return;
            }
            if (i != 3) {
                if (i == 4 && (notificationMessage = (NotificationMessage) bundle.get(com.salesforce.marketingcloud.notifications.a.p)) != null) {
                    this.k.a(notificationMessage);
                    return;
                }
                return;
            }
            if (a(bundle)) {
                try {
                    this.k.a(new InboxMessage(bundle));
                } catch (Exception e) {
                    com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, e, "Failed to seed inbox_messages table with message: %s.", bundle.getString(NotificationMessage.NOTIF_KEY_ID));
                }
            }
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void refreshInbox(InboxMessageManager.InboxRefreshListener inboxRefreshListener) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.refreshInbox(inboxRefreshListener);
            return;
        }
        com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to refreshInbox() was ignored.", new Object[0]);
        if (inboxRefreshListener != null) {
            try {
                inboxRefreshListener.onRefreshComplete(false);
            } catch (Exception unused) {
                com.salesforce.marketingcloud.g.b(InboxMessageManager.TAG, "InboxRefreshListener threw an exception.", new Object[0]);
            }
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void registerInboxResponseListener(InboxMessageManager.InboxResponseListener inboxResponseListener) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.registerInboxResponseListener(inboxResponseListener);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to registerInboxResponseListener() was ignored.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void setMessageRead(InboxMessage inboxMessage) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.setMessageRead(inboxMessage);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to setMessageRead() was ignored.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.d
    public void tearDown(boolean z) {
        com.salesforce.marketingcloud.alarms.b bVar = this.h;
        if (bVar != null) {
            bVar.e(a.EnumC0042a.g);
        }
        com.salesforce.marketingcloud.behaviors.c cVar = this.g;
        if (cVar != null) {
            cVar.a(this);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void unregisterInboxResponseListener(InboxMessageManager.InboxResponseListener inboxResponseListener) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.unregisterInboxResponseListener(inboxResponseListener);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to unregisterInboxResponseListener() was ignored.", new Object[0]);
        }
    }

    private static boolean a(Bundle bundle) {
        return n.equals(bundle.getString(NotificationMessage.NOTIF_KEY_MESSAGE_TYPE));
    }

    private void a() {
        this.k = new c(this.d, this.e, this.f, this.h, this.i, this.j, this.l);
        this.i.a(com.salesforce.marketingcloud.http.a.k, this);
        this.i.a(com.salesforce.marketingcloud.http.a.l, this);
        this.i.a(com.salesforce.marketingcloud.http.a.m, this);
        this.h.a(this, a.EnumC0042a.g);
        this.g.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_FOREGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_BACKGROUNDED, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_NOTIFICATION_OPENED));
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void deleteMessage(String str) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.deleteMessage(str);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to deleteMessage() was ignored.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.inbox.InboxMessageManager
    public void setMessageRead(String str) {
        c cVar = this.k;
        if (cVar != null) {
            cVar.setMessageRead(str);
        } else {
            com.salesforce.marketingcloud.g.e(InboxMessageManager.TAG, "Inbox messaging is disabled.  Call to setMessageRead() was ignored.", new Object[0]);
        }
    }

    a(c cVar) {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = cVar;
    }

    @Override // com.salesforce.marketingcloud.http.d.c
    public void a(com.salesforce.marketingcloud.http.b bVar, com.salesforce.marketingcloud.http.e eVar) {
        if (this.k != null) {
            int i = C0068a.b[bVar.p().ordinal()];
            if (i == 1 || i == 2) {
                if (eVar.o()) {
                    this.k.a(eVar);
                    return;
                } else {
                    this.k.a(eVar.j(), eVar.m());
                    return;
                }
            }
            if (i != 3) {
                return;
            }
            if (eVar.o()) {
                this.k.a(bVar);
            } else {
                this.k.b(eVar.j(), eVar.m());
            }
        }
    }

    @Override // com.salesforce.marketingcloud.alarms.b.InterfaceC0044b
    public void a(a.EnumC0042a enumC0042a) {
        if (this.k == null || C0068a.c[enumC0042a.ordinal()] != 1) {
            return;
        }
        this.k.e();
    }

    private boolean a(int i) {
        if (com.salesforce.marketingcloud.b.a(i, 128)) {
            return false;
        }
        if (this.m == null) {
            this.m = a((com.salesforce.marketingcloud.toggles.a) null);
        }
        com.salesforce.marketingcloud.toggles.a aVar = this.m;
        return aVar == com.salesforce.marketingcloud.toggles.a.c || (aVar == com.salesforce.marketingcloud.toggles.a.b && this.d.inboxEnabled());
    }

    private com.salesforce.marketingcloud.toggles.a a(com.salesforce.marketingcloud.toggles.a aVar) {
        if (aVar != null) {
            return aVar;
        }
        String string = this.e.e().getString(o, null);
        if (string == null) {
            return com.salesforce.marketingcloud.toggles.a.b;
        }
        return com.salesforce.marketingcloud.toggles.a.valueOf(string);
    }
}
