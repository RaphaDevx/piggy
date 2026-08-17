package com.salesforce.marketingcloud.messages.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.google.firebase.messaging.RemoteMessage;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MCService;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.alarms.b;
import com.salesforce.marketingcloud.analytics.j;
import com.salesforce.marketingcloud.behaviors.c;
import com.salesforce.marketingcloud.e;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.internal.CompressionUtility;
import com.salesforce.marketingcloud.internal.n;
import com.salesforce.marketingcloud.k;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.messages.push.PushMessageManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.notifications.a;
import com.salesforce.marketingcloud.push.f;
import com.salesforce.marketingcloud.push.i;
import com.salesforce.marketingcloud.storage.h;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class a extends PushMessageManager implements e, b.InterfaceC0044b {
    private static final String A = "content-available";
    private static final String B = "_c";
    private static final String C = "_p";
    private static final long D = TimeUnit.HOURS.toMillis(48);
    static final String y = "et_push_enabled";
    private static final String z = "last_push_token_refresh";
    private final Context j;
    private final com.salesforce.marketingcloud.notifications.a k;
    private final com.salesforce.marketingcloud.alarms.b l;
    private final Set<PushMessageManager.SilentPushListener> m;
    private final h n;
    private final String o;
    private final Set<PushMessageManager.PushTokenRefreshListener> p;
    private final j q;
    private final n r;
    private final i s;
    com.salesforce.marketingcloud.push.h t;
    private o u;
    private int v;
    private BroadcastReceiver w;
    private boolean x;

    /* renamed from: com.salesforce.marketingcloud.messages.push.a$a, reason: collision with other inner class name */
    class RunnableC0071a implements Runnable {
        final /* synthetic */ Map b;

        RunnableC0071a(Map map) {
            this.b = map;
        }

        @Override // java.lang.Runnable
        public void run() {
            NotificationMessage a;
            c.a(a.this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_PUSH_RECEIVED, a.b((Map<String, String>) this.b));
            if (k.a((Map<String, String>) this.b)) {
                g.d(PushMessageManager.i, "Sync handler push received.", new Object[0]);
                return;
            }
            if (!a.this.isPushEnabled()) {
                g.a(PushMessageManager.i, "Push Messaging is disabled.  Ignoring message.", new Object[0]);
                return;
            }
            if (this.b.containsKey(a.A)) {
                a.this.c(this.b);
                return;
            }
            if (this.b.containsKey(a.B)) {
                a.this.d(this.b);
                return;
            }
            try {
                a = com.salesforce.marketingcloud.internal.j.a(CompressionUtility.INSTANCE.decompress(this.b));
            } catch (f e) {
                g.b(PushMessageManager.i, e, "Unable to decompress push message", new Object[0]);
                String str = (String) this.b.get(NotificationMessage.NOTIF_KEY_ID);
                if (str != null) {
                    a.this.q.a(e, str);
                }
            } catch (Exception e2) {
                g.b(PushMessageManager.i, e2, "Unable to show push notification", new Object[0]);
            }
            if (TextUtils.isEmpty(a.alert().trim())) {
                g.a(PushMessageManager.i, "Message (%s) was received but does not have an alert message.", a.id());
                return;
            }
            a.this.t.b(a);
            a.this.k.a(a, (a.b) null);
            a.this.q.a(this.b);
        }
    }

    class b extends BroadcastReceiver {
        b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.d(PushMessageManager.i, "Received null intent", new Object[0]);
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                g.d(PushMessageManager.i, "Received null action", new Object[0]);
            } else if (action.equals(PushMessageManager.d)) {
                a.this.a(intent.getExtras());
            } else {
                g.a(PushMessageManager.i, "Received unknown action: %s", action);
            }
        }
    }

    public a(Context context, h hVar, com.salesforce.marketingcloud.notifications.a aVar, com.salesforce.marketingcloud.alarms.b bVar, String str, j jVar, o oVar, n nVar) {
        i iVar = i.a;
        this.s = iVar;
        this.j = (Context) com.salesforce.marketingcloud.util.g.a(context, "Content is null");
        this.n = (h) com.salesforce.marketingcloud.util.g.a(hVar, "Storage is null");
        this.k = (com.salesforce.marketingcloud.notifications.a) com.salesforce.marketingcloud.util.g.a(aVar, "NotificationManager is null");
        this.l = (com.salesforce.marketingcloud.alarms.b) com.salesforce.marketingcloud.util.g.a(bVar, "AlarmScheduler is null");
        this.o = str;
        this.q = jVar;
        this.u = oVar;
        this.r = nVar;
        this.m = new ArraySet();
        this.p = new ArraySet();
        iVar.a(oVar);
        this.t = new com.salesforce.marketingcloud.push.h(oVar);
    }

    public static void a(Context context, boolean z2, String str, String str2) {
        context.sendBroadcast(new Intent(PushMessageManager.d).putExtra(PushMessageManager.e, z2).putExtra(PushMessageManager.f, str).putExtra(PushMessageManager.h, str2).setPackage(context.getPackageName()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle b(Map<String, String> map) {
        Bundle bundle = new Bundle();
        if (!map.isEmpty()) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bundle.putString(entry.getKey(), entry.getValue());
            }
        }
        return bundle;
    }

    private void c() {
        this.w = new b();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PushMessageManager.d);
        ContextCompat.registerReceiver(this.j, this.w, intentFilter, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Map<String, String> map) {
        map.remove(B);
        map.remove(C);
        e(map);
    }

    private void e(Map<String, String> map) {
        synchronized (this.m) {
            for (PushMessageManager.SilentPushListener silentPushListener : this.m) {
                if (silentPushListener != null) {
                    try {
                        silentPushListener.silentPushReceived(map);
                    } catch (Exception e) {
                        g.b(PushMessageManager.i, e, "%s threw an exception while processing the silent push message", silentPushListener.getClass().getName());
                    }
                }
            }
        }
    }

    private void f(Map<String, String> map) {
        if (map == null || g(map)) {
            return;
        }
        this.r.a().submit(new RunnableC0071a(map));
    }

    private boolean g(Map<String, String> map) {
        if (com.salesforce.marketingcloud.b.a(this.v, 4)) {
            g.a(PushMessageManager.i, "Blocking push message.  Received a push message when the push feature is blocked.", new Object[0]);
            return true;
        }
        if (!com.salesforce.marketingcloud.b.a(this.v, 128) || !com.salesforce.marketingcloud.messages.inbox.a.a(map)) {
            return false;
        }
        g.a(PushMessageManager.i, "Blocking push message.  Received an inbox message when the inbox feature is blocked.", new Object[0]);
        return true;
    }

    @Override // com.salesforce.marketingcloud.d
    public String componentName() {
        return "PushMessageManager";
    }

    @Override // com.salesforce.marketingcloud.d
    public JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pushEnabled", this.x);
            jSONObject.put("pushPermissionsAllowed", NotificationManagerCompat.from(this.j).areNotificationsEnabled());
            synchronized (this.p) {
                if (!this.p.isEmpty()) {
                    JSONArray jSONArray = new JSONArray();
                    for (PushMessageManager.PushTokenRefreshListener pushTokenRefreshListener : this.p) {
                        if (pushTokenRefreshListener != null) {
                            jSONArray.put(pushTokenRefreshListener.getClass().getName());
                        }
                    }
                    jSONObject.put("tokenRefreshListeners", jSONArray);
                }
            }
            jSONObject.put("debugInfo", getPushDebugInfo());
        } catch (JSONException e) {
            g.b(PushMessageManager.i, e, "Unable to create component state for $s", componentName());
        }
        return jSONObject;
    }

    @Override // com.salesforce.marketingcloud.e
    public void controlChannelInit(int i) {
        if (!com.salesforce.marketingcloud.b.a(i, 4)) {
            if (com.salesforce.marketingcloud.b.a(this.v, 4)) {
                this.v = i;
                c();
                this.l.a(this, a.EnumC0042a.f);
                enablePush();
                String str = this.o;
                if (str != null) {
                    MCService.b(this.j, str);
                    return;
                }
                return;
            }
            return;
        }
        disablePush();
        BroadcastReceiver broadcastReceiver = this.w;
        if (broadcastReceiver != null) {
            this.j.unregisterReceiver(broadcastReceiver);
        }
        com.salesforce.marketingcloud.alarms.b bVar = this.l;
        a.EnumC0042a enumC0042a = a.EnumC0042a.f;
        bVar.e(enumC0042a);
        this.l.d(enumC0042a);
        if (com.salesforce.marketingcloud.b.c(i, 4)) {
            com.salesforce.marketingcloud.storage.b c = this.n.c();
            c.a(com.salesforce.marketingcloud.storage.b.i);
            c.a(com.salesforce.marketingcloud.storage.b.e);
        }
        this.v = i;
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public synchronized void disablePush() {
        if (this.x && !com.salesforce.marketingcloud.b.a(this.v, 4)) {
            this.x = false;
            a();
            d();
        }
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public synchronized void enablePush() {
        if (com.salesforce.marketingcloud.b.a(this.v, 4)) {
            return;
        }
        this.x = true;
        a();
        d();
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public JSONObject getPushDebugInfo() {
        try {
            return com.salesforce.marketingcloud.messages.push.b.a(this.j, this.o, this.n.c().b(com.salesforce.marketingcloud.storage.b.e, null));
        } catch (Exception e) {
            g.b(PushMessageManager.i, e, "Unable to acquire push debug info.", new Object[0]);
            return new JSONObject();
        }
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public String getPushToken() {
        return this.n.c().b(com.salesforce.marketingcloud.storage.b.e, null);
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public boolean handleMessage(RemoteMessage remoteMessage) {
        if (!PushMessageManager.isMarketingCloudPush(remoteMessage)) {
            g.a(PushMessageManager.i, "Message was not sent from the Marketing Cloud.  Message ignored.", new Object[0]);
            return false;
        }
        Map<String, String> data = remoteMessage.getData();
        data.put("messageDateUtc", com.salesforce.marketingcloud.util.j.a(new Date(remoteMessage.getSentTime())));
        f(data);
        return true;
    }

    @Override // com.salesforce.marketingcloud.e
    public void init(InitializationStatus.a aVar, int i) {
        this.v = i;
        if (com.salesforce.marketingcloud.b.b(i, 4)) {
            this.x = this.n.e().getBoolean(y, true);
            c();
            com.salesforce.marketingcloud.alarms.b bVar = this.l;
            a.EnumC0042a enumC0042a = a.EnumC0042a.f;
            bVar.a(this, enumC0042a);
            if (this.o == null) {
                g.e(PushMessageManager.i, "No sender id was provided during initialization.  You will not receive push messages until a token is manually set.", new Object[0]);
                this.l.d(enumC0042a);
                this.n.c().a(com.salesforce.marketingcloud.storage.b.i);
                return;
            }
            b();
            if (!this.o.equals(this.n.c().b(com.salesforce.marketingcloud.storage.b.i, null))) {
                g.d(PushMessageManager.i, "Sender Id has changed.  Refresh system token.", new Object[0]);
                MCService.b(this.j, this.o);
            } else if (this.n.e().getLong(z, 0L) + D < System.currentTimeMillis()) {
                g.d(PushMessageManager.i, "Push token refresh cool down expired.  Refresh system token.", new Object[0]);
                MCService.b(this.j, this.o);
            }
        }
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public synchronized boolean isPushEnabled() {
        return this.x;
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public void registerSilentPushListener(PushMessageManager.SilentPushListener silentPushListener) {
        if (silentPushListener == null) {
            return;
        }
        synchronized (this.m) {
            this.m.add(silentPushListener);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public void registerTokenRefreshListener(PushMessageManager.PushTokenRefreshListener pushTokenRefreshListener) {
        if (pushTokenRefreshListener == null) {
            return;
        }
        synchronized (this.p) {
            this.p.add(pushTokenRefreshListener);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public void setPushToken(String str) {
        if (com.salesforce.marketingcloud.b.b(this.v, 4)) {
            if (str == null) {
                g.b(PushMessageManager.i, "Provided pushToken was null", new Object[0]);
                return;
            }
            if (this.o != null) {
                g.a(PushMessageManager.i, "Setting the SenderId during SDK initialization and setting the push token will cause conflicts in the system and could prevent the device from receiving push messages.", new Object[0]);
            }
            com.salesforce.marketingcloud.storage.b c = this.n.c();
            c.a(com.salesforce.marketingcloud.storage.b.i);
            c.a(com.salesforce.marketingcloud.storage.b.e, str);
            this.l.d(a.EnumC0042a.f);
            b(str);
        }
    }

    @Override // com.salesforce.marketingcloud.d
    public void tearDown(boolean z2) {
        BroadcastReceiver broadcastReceiver = this.w;
        if (broadcastReceiver != null) {
            this.j.unregisterReceiver(broadcastReceiver);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public void unregisterSilentPushListener(PushMessageManager.SilentPushListener silentPushListener) {
        synchronized (this.m) {
            this.m.remove(silentPushListener);
        }
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public void unregisterTokenRefreshListener(PushMessageManager.PushTokenRefreshListener pushTokenRefreshListener) {
        synchronized (this.p) {
            this.p.remove(pushTokenRefreshListener);
        }
    }

    private void b() {
        JSONArray optJSONArray = getPushDebugInfo().optJSONArray("messagingService");
        if (optJSONArray == null || optJSONArray.length() <= 2) {
            return;
        }
        g.e(PushMessageManager.i, "Possible Multiple Push Provider implementation issue detected in your application. This may lead to the malfunctioning of the Push SDK.", new Object[0]);
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            int optInt = optJSONObject.optInt("priority");
            String optString = optJSONObject.optString("name");
            if (optInt > -1) {
                g.d(PushMessageManager.i, optString + " is having higher priority than the Push SDK", new Object[0]);
            }
        }
    }

    private void d() {
        h hVar = this.n;
        if (hVar != null) {
            hVar.e().edit().putBoolean(y, this.x).apply();
        }
    }

    public static boolean a(Map<String, String> map) {
        if (map.containsKey(NotificationMessage.NOTIF_KEY_ID) && map.containsKey(NotificationMessage.NOTIF_KEY_MESSAGE_TYPE)) {
            if (map.containsKey("messageDateUtc")) {
                return true;
            }
            g.c(PushMessageManager.i, "Optional key is missing for Delivery Receipt", new Object[0]);
            return true;
        }
        g.e(PushMessageManager.i, "Mandatory keys are missing, Delivery Receipt Event cannot be processed", new Object[0]);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:6:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(java.util.Map<java.lang.String, java.lang.String> r5) {
        /*
            r4 = this;
            java.lang.String r0 = "content-available"
            java.lang.Object r0 = r5.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L1b
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> Lf
            goto L1c
        Lf:
            r1 = move-exception
            java.lang.String r2 = com.salesforce.marketingcloud.messages.push.PushMessageManager.i
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r3 = "Unable to parse content available flag: %s"
            com.salesforce.marketingcloud.g.b(r2, r1, r3, r0)
        L1b:
            r0 = 0
        L1c:
            r1 = 1
            if (r0 != r1) goto L22
            r4.e(r5)
        L22:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.messages.push.a.c(java.util.Map):void");
    }

    @Override // com.salesforce.marketingcloud.messages.push.PushMessageManager
    public boolean handleMessage(Map<String, String> map) {
        if (!PushMessageManager.isMarketingCloudPush(map)) {
            g.a(PushMessageManager.i, "Message was not sent from the Marketing Cloud.  Message ignored.", new Object[0]);
            return false;
        }
        f(map);
        return true;
    }

    void a(Bundle bundle) {
        com.salesforce.marketingcloud.storage.b c = this.n.c();
        if (bundle.getBoolean(PushMessageManager.e, false)) {
            String string = bundle.getString(PushMessageManager.h, "");
            c.a(com.salesforce.marketingcloud.storage.b.e, string);
            c.a(com.salesforce.marketingcloud.storage.b.i, bundle.getString(PushMessageManager.f, ""));
            b(string);
            this.l.d(a.EnumC0042a.f);
            this.n.e().edit().putLong(z, System.currentTimeMillis()).apply();
            a(string);
            return;
        }
        c.a(com.salesforce.marketingcloud.storage.b.i);
        this.l.b(a.EnumC0042a.f);
    }

    private void b(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(PushMessageManager.h, str);
        c.a(this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_TOKEN_REFRESHED, bundle);
    }

    @Override // com.salesforce.marketingcloud.alarms.b.InterfaceC0044b
    public void a(a.EnumC0042a enumC0042a) {
        String str;
        if (enumC0042a != a.EnumC0042a.f || (str = this.o) == null) {
            return;
        }
        MCService.b(this.j, str);
    }

    private void a(String str) {
        synchronized (this.p) {
            for (PushMessageManager.PushTokenRefreshListener pushTokenRefreshListener : this.p) {
                if (pushTokenRefreshListener != null) {
                    try {
                        pushTokenRefreshListener.onTokenRefreshed(str);
                    } catch (Exception e) {
                        g.b(PushMessageManager.i, e, "%s threw an exception while processing the token refresh", pushTokenRefreshListener.getClass().getName());
                    }
                }
            }
        }
    }

    private void a() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(PushMessageManager.g, this.x);
        c.a(this.j, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_CUSTOMER_PUSH_MESSAGING_TOGGLED, bundle);
    }
}
