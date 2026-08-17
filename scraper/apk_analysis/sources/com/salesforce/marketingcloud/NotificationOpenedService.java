package com.salesforce.marketingcloud;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class NotificationOpenedService extends IntentService {
    private static final String a = g.a("NotificationOpenedService");

    public NotificationOpenedService() {
        super(a);
    }

    private static void a(Context context, Bundle bundle) {
        context.sendBroadcast(new Intent(com.salesforce.marketingcloud.notifications.a.n).putExtras(bundle).setPackage(context.getPackageName()));
    }

    public static Intent b(Context context, Bundle bundle) {
        return new Intent(context, (Class<?>) NotificationOpenedService.class).setAction(NotificationManager.ACTION_NOTIFICATION_CLICKED).putExtras(bundle);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        PowerManager.WakeLock wakeLock = null;
        try {
            try {
                PowerManager powerManager = (PowerManager) getSystemService("power");
                String str = a;
                wakeLock = powerManager.newWakeLock(1, str);
                wakeLock.setReferenceCounted(false);
                wakeLock.acquire(TimeUnit.SECONDS.toMillis(30L));
                if (!com.salesforce.marketingcloud.util.j.a(500L, 50L) || MarketingCloudSdk.getInstance() == null) {
                    g.e(str, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
                } else if (NotificationManager.ACTION_NOTIFICATION_CLICKED.equals(intent.getAction())) {
                    a(getApplicationContext(), intent.getExtras());
                }
                if (!wakeLock.isHeld()) {
                    return;
                }
            } catch (Exception e) {
                g.b(a, e, "Encountered exception while handling action: %s", intent.getAction());
                if (0 == 0 || !wakeLock.isHeld()) {
                    return;
                }
            }
            try {
                wakeLock.release();
            } catch (Exception unused) {
            }
        } catch (Throwable th) {
            if (0 != 0 && wakeLock.isHeld()) {
                try {
                    wakeLock.release();
                } catch (Exception unused2) {
                }
            }
            throw th;
        }
    }
}
