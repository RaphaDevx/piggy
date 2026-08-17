package com.salesforce.marketingcloud.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.room.RoomDatabase;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.util.j;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class NotificationOpenActivity extends FragmentActivity {
    private static final String b = g.a("NotificationOpenActivity");
    private BroadcastReceiver a;

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NotificationOpenActivity.this.a();
        }
    }

    public static Intent a(Context context, Bundle bundle) {
        return new Intent(context, (Class<?>) NotificationOpenActivity.class).setAction(NotificationManager.ACTION_NOTIFICATION_CLICKED).putExtras(bundle).setFlags(8388608);
    }

    private void b(Context context, Bundle bundle) {
        context.sendBroadcast(new Intent(com.salesforce.marketingcloud.notifications.a.n).putExtras(bundle).setPackage(context.getPackageName()));
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() == null) {
            a();
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(com.salesforce.marketingcloud.notifications.a.o);
        intentFilter.setPriority(RoomDatabase.MAX_BIND_PARAMETER_CNT);
        a aVar = new a();
        this.a = aVar;
        ContextCompat.registerReceiver(this, aVar, intentFilter, 4);
        PowerManager.WakeLock wakeLock = null;
        try {
            try {
                PowerManager powerManager = (PowerManager) getSystemService("power");
                String str = b;
                wakeLock = powerManager.newWakeLock(1, str);
                wakeLock.setReferenceCounted(false);
                wakeLock.acquire(TimeUnit.SECONDS.toMillis(30L));
                if (!j.a(500L, 50L) || MarketingCloudSdk.getInstance() == null) {
                    g.e(str, "MarketingCloudSdk#init must be called in your application's onCreate", new Object[0]);
                } else if (NotificationManager.ACTION_NOTIFICATION_CLICKED.equals(getIntent().getAction())) {
                    b(getApplicationContext(), getIntent().getExtras());
                }
                if (!wakeLock.isHeld()) {
                    return;
                }
            } catch (Exception e) {
                g.b(b, e, "Encountered exception while handling action: %s", getIntent().getAction());
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

    protected void a() {
        BroadcastReceiver broadcastReceiver = this.a;
        if (broadcastReceiver != null) {
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException unused) {
                g.e(b, "com.salesforce.marketingcloud.notifications.open.RECEIVED Receiver is not registered.", new Object[0]);
            }
        }
        if (isFinishing()) {
            return;
        }
        finish();
    }
}
