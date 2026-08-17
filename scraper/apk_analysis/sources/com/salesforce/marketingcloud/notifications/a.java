package com.salesforce.marketingcloud.notifications;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.analytics.j;
import com.salesforce.marketingcloud.e;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.push.f;
import com.salesforce.marketingcloud.storage.h;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class a extends NotificationManager implements e {
    public static final String n = "com.salesforce.marketingcloud.notifications.OPENED";
    public static final String o = "com.salesforce.marketingcloud.notifications.open.RECEIVED";
    public static final String p = "com.salesforce.marketingcloud.notifications.MESSAGE";
    public static final int q = -1;
    static final String r = "com.salesforce.marketingcloud.notifications.EXTRA_OPEN_INTENT";
    static final String s = "com.salesforce.marketingcloud.notifications.EXTRA_AUTO_CANCEL";
    static final String t = "com.marketingcloud.salesforce.notifications.TAG";
    static final String u = "com.marketingcloud.salesforce.notifications.ENABLED";
    static final String v = "notification_id_key";
    final com.salesforce.marketingcloud.notifications.b f;
    final Context g;
    private final h h;
    private final j j;
    private NotificationManager.ShouldShowNotificationListener k;
    private BroadcastReceiver l;
    private boolean m = true;
    private final Set<NotificationManager.NotificationMessageDisplayedListener> i = new ArraySet();

    /* renamed from: com.salesforce.marketingcloud.notifications.a$a, reason: collision with other inner class name */
    class C0072a extends Thread {
        final /* synthetic */ NotificationMessage b;
        final /* synthetic */ b c;

        C0072a(NotificationMessage notificationMessage, b bVar) {
            this.b = notificationMessage;
            this.c = bVar;
        }

        /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0056  */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r4 = this;
                com.salesforce.marketingcloud.notifications.a r0 = com.salesforce.marketingcloud.notifications.a.this     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                com.salesforce.marketingcloud.notifications.b r1 = r0.f     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                android.content.Context r0 = r0.g     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                com.salesforce.marketingcloud.notifications.NotificationMessage r2 = r4.b     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                androidx.core.app.NotificationCompat$Builder r0 = r1.setupNotificationBuilder(r0, r2)     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                com.salesforce.marketingcloud.notifications.a r1 = com.salesforce.marketingcloud.notifications.a.this     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                android.content.Context r1 = r1.g     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                java.lang.String r2 = "notification"
                java.lang.Object r1 = r1.getSystemService(r2)     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                android.app.NotificationManager r1 = (android.app.NotificationManager) r1     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                if (r1 == 0) goto L51
                java.lang.String r2 = "com.marketingcloud.salesforce.notifications.TAG"
                com.salesforce.marketingcloud.notifications.NotificationMessage r3 = r4.b     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                int r3 = r3.notificationId()     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                android.app.Notification r0 = r0.build()     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                r1.notify(r2, r3, r0)     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                com.salesforce.marketingcloud.notifications.a r0 = com.salesforce.marketingcloud.notifications.a.this     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                com.salesforce.marketingcloud.notifications.NotificationMessage r1 = r4.b     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                r0.b(r1)     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                com.salesforce.marketingcloud.notifications.NotificationMessage r0 = r4.b     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                int r0 = r0.notificationId()     // Catch: java.lang.Exception -> L37 com.salesforce.marketingcloud.push.f -> L43
                goto L52
            L37:
                r0 = move-exception
                java.lang.String r1 = com.salesforce.marketingcloud.notifications.NotificationManager.d
                r2 = 0
                java.lang.Object[] r2 = new java.lang.Object[r2]
                java.lang.String r3 = "Unable to show notification due to an exception thrown by Android."
                com.salesforce.marketingcloud.g.b(r1, r0, r3, r2)
                goto L51
            L43:
                r0 = move-exception
                com.salesforce.marketingcloud.notifications.a r1 = com.salesforce.marketingcloud.notifications.a.this
                com.salesforce.marketingcloud.analytics.j r1 = com.salesforce.marketingcloud.notifications.a.m3716$$Nest$fgetj(r1)
                com.salesforce.marketingcloud.notifications.NotificationMessage r2 = r4.b
                java.lang.String r2 = r2.id
                r1.a(r0, r2)
            L51:
                r0 = -1
            L52:
                com.salesforce.marketingcloud.notifications.a$b r4 = r4.c
                if (r4 == 0) goto L59
                r4.a(r0)
            L59:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.notifications.a.C0072a.run():void");
        }
    }

    public interface b {
        void a(int i);
    }

    class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.a(NotificationManager.d, "Received null intent", new Object[0]);
                return;
            }
            context.sendBroadcast(new Intent(a.o).setPackage(context.getPackageName()));
            String action = intent.getAction();
            if (action == null) {
                g.a(NotificationManager.d, "Received null action", new Object[0]);
            } else if (a.n.equals(action)) {
                a.this.a(context, NotificationManager.extractMessage(intent), (PendingIntent) intent.getParcelableExtra(a.r), intent.getExtras(), intent.getBooleanExtra(a.s, true));
            } else {
                g.a(NotificationManager.d, "Received unknown action: %s", action);
            }
        }
    }

    a(Context context, h hVar, com.salesforce.marketingcloud.notifications.b bVar, j jVar) {
        this.g = context;
        this.h = hVar;
        this.f = bVar;
        this.j = (j) com.salesforce.marketingcloud.util.g.a(jVar, "MessageAnalyticEventListener is null.");
    }

    public static a a(Context context, h hVar, NotificationCustomizationOptions notificationCustomizationOptions, j jVar) {
        return new a(context, hVar, new com.salesforce.marketingcloud.notifications.b(notificationCustomizationOptions.smallIconResId, notificationCustomizationOptions.launchIntentProvider, notificationCustomizationOptions.notificationBuilder, notificationCustomizationOptions.channelIdProvider), jVar);
    }

    @Override // com.salesforce.marketingcloud.notifications.NotificationManager
    public final synchronized boolean areNotificationsEnabled() {
        return this.m;
    }

    void b(NotificationMessage notificationMessage) {
        synchronized (this.i) {
            if (!this.i.isEmpty()) {
                for (NotificationManager.NotificationMessageDisplayedListener notificationMessageDisplayedListener : this.i) {
                    if (notificationMessageDisplayedListener != null) {
                        try {
                            notificationMessageDisplayedListener.onNotificationMessageDisplayed(notificationMessage);
                        } catch (Exception e) {
                            g.b(NotificationManager.d, e, "%s threw an exception while processing notification message (%s)", notificationMessageDisplayedListener.getClass().getName(), notificationMessage.id());
                        }
                    }
                }
            }
        }
        try {
            this.j.b(notificationMessage);
        } catch (Exception e2) {
            g.b(NotificationManager.d, e2, "Failed to log analytics for message displayed.", new Object[0]);
        }
    }

    @Override // com.salesforce.marketingcloud.d
    public final String componentName() {
        return "NotificationManager";
    }

    @Override // com.salesforce.marketingcloud.d
    public final JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("notificationsEnabled", areNotificationsEnabled());
            jSONObject.put("displayMetrics", this.g.getResources().getDisplayMetrics().toString());
            jSONObject.put("Dpi Density", this.g.getResources().getDisplayMetrics().densityDpi);
            NotificationManager.ShouldShowNotificationListener shouldShowNotificationListener = this.k;
            if (shouldShowNotificationListener != null) {
                jSONObject.put("shouldShowNotificationListener", shouldShowNotificationListener.getClass().getName());
            }
        } catch (JSONException e) {
            g.b(NotificationManager.d, e, "Unable to create component state for %s", componentName());
        }
        return jSONObject;
    }

    @Override // com.salesforce.marketingcloud.e
    public void controlChannelInit(int i) {
    }

    @Override // com.salesforce.marketingcloud.notifications.NotificationManager
    public final synchronized void disableNotifications() {
        if (this.m) {
            this.m = false;
            a();
        }
    }

    @Override // com.salesforce.marketingcloud.notifications.NotificationManager
    public final synchronized void enableNotifications() {
        if (this.m) {
            return;
        }
        this.m = true;
        a();
    }

    @Override // com.salesforce.marketingcloud.e
    public final void init(InitializationStatus.a aVar, int i) {
        this.m = this.h.e().getBoolean(u, true);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(n);
        c cVar = new c();
        this.l = cVar;
        ContextCompat.registerReceiver(this.g, cVar, intentFilter, 4);
    }

    @Override // com.salesforce.marketingcloud.notifications.NotificationManager
    public final void registerNotificationMessageDisplayedListener(NotificationManager.NotificationMessageDisplayedListener notificationMessageDisplayedListener) {
        if (notificationMessageDisplayedListener == null) {
            return;
        }
        synchronized (this.i) {
            this.i.add(notificationMessageDisplayedListener);
        }
    }

    @Override // com.salesforce.marketingcloud.notifications.NotificationManager
    public void setShouldShowNotificationListener(NotificationManager.ShouldShowNotificationListener shouldShowNotificationListener) {
        this.k = shouldShowNotificationListener;
    }

    @Override // com.salesforce.marketingcloud.d
    public final void tearDown(boolean z) {
        if (z) {
            a(this.g);
        }
        Context context = this.g;
        if (context != null) {
            context.unregisterReceiver(this.l);
        }
    }

    @Override // com.salesforce.marketingcloud.notifications.NotificationManager
    public final void unregisterNotificationMessageDisplayedListener(NotificationManager.NotificationMessageDisplayedListener notificationMessageDisplayedListener) {
        synchronized (this.i) {
            this.i.remove(notificationMessageDisplayedListener);
        }
    }

    private void a(Context context) {
        if (this.h == null) {
            return;
        }
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        int i = this.h.e().getInt(v, -1);
        for (int i2 = 0; i >= 0 && i2 < 100; i2++) {
            from.cancel(t, i);
            i--;
        }
    }

    private void a() {
        h hVar = this.h;
        if (hVar != null) {
            hVar.e().edit().putBoolean(u, this.m).apply();
        }
    }

    public synchronized void a(NotificationMessage notificationMessage, b bVar) {
        boolean z;
        if (!areNotificationsEnabled()) {
            g.a(NotificationManager.d, "Notifications are not enabled.  Message %s will not be displayed", notificationMessage.id());
            if (bVar != null) {
                bVar.a(-1);
            }
            return;
        }
        if (TextUtils.getTrimmedLength(notificationMessage.alert()) == 0) {
            g.a(NotificationManager.d, "Notifications with no alert message are not shown.", new Object[0]);
            if (bVar != null) {
                bVar.a(-1);
            }
            return;
        }
        if (notificationMessage.notificationId() >= 0) {
            if (bVar != null) {
                bVar.a(-1);
            }
            return;
        }
        NotificationManager.ShouldShowNotificationListener shouldShowNotificationListener = this.k;
        if (shouldShowNotificationListener != null) {
            try {
                z = shouldShowNotificationListener.shouldShowNotification(notificationMessage);
            } catch (Exception e) {
                g.b(NotificationManager.d, e, "%s threw an exception while processing shouldShowNotification() for messageId: %s", this.k.getClass().getName(), notificationMessage.id());
                z = true;
            }
            try {
                this.j.a(notificationMessage, z);
            } catch (Exception e2) {
                g.b(NotificationManager.d, e2, "Failed to log Should Show Notification analytic for messageId: %s", notificationMessage.id());
            }
        } else {
            z = true;
        }
        if (z) {
            SharedPreferences e3 = this.h.e();
            com.salesforce.marketingcloud.internal.j.a(notificationMessage, e3.getInt(v, 0));
            e3.edit().putInt(v, notificationMessage.notificationId() < Integer.MAX_VALUE ? notificationMessage.notificationId() + 1 : 0).apply();
            new C0072a(notificationMessage, bVar).start();
        } else {
            g.a(NotificationManager.d, "%s responded false to shouldShowNotification() for messageId: %s", this.k.getClass().getName(), notificationMessage.id());
            if (bVar != null) {
                bVar.a(-1);
            }
        }
    }

    NotificationCompat.Builder a(NotificationMessage notificationMessage) {
        try {
            return this.f.setupNotificationBuilder(this.g, notificationMessage);
        } catch (f e) {
            this.j.a(e, notificationMessage.id);
            return null;
        }
    }

    void a(Context context, NotificationMessage notificationMessage, PendingIntent pendingIntent, Bundle bundle, boolean z) {
        String str = NotificationManager.d;
        g.a(str, "Notification open Event Logged for id : (%s)", notificationMessage.id());
        this.j.a(notificationMessage);
        int i = bundle.getInt(com.salesforce.marketingcloud.push.b.f, -1);
        String string = bundle.getString(com.salesforce.marketingcloud.push.b.g, null);
        String string2 = bundle.getString(com.salesforce.marketingcloud.push.b.h, null);
        if (i >= 0 && string != null) {
            this.j.a(notificationMessage, i, string, string2);
            g.a(str, "Notification click Event Logged for id : (%s)", notificationMessage.id());
        }
        if (pendingIntent != null) {
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                g.b(NotificationManager.d, e, "Failed to send notification's open action PendingIntent.", new Object[0]);
            }
        }
        if (z) {
            NotificationManager.cancelNotificationMessage(context, notificationMessage);
        }
        if (Build.VERSION.SDK_INT <= 30) {
            context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(p, notificationMessage);
        com.salesforce.marketingcloud.behaviors.c.a(context, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_SDK_NOTIFICATION_OPENED, bundle2);
    }
}
