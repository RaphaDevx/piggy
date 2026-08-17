package com.salesforce.marketingcloud.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.salesforce.marketingcloud.InitializationStatus;
import com.salesforce.marketingcloud.MCReceiver;
import com.salesforce.marketingcloud.alarms.a;
import com.salesforce.marketingcloud.f;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.storage.h;
import com.salesforce.marketingcloud.util.j;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class b extends f implements com.salesforce.marketingcloud.behaviors.b {
    public static final String j = "com.salesforce.marketingcloud.ACTION_ALARM_WAKE_EVENT";
    public static final String k = "com.salesforce.marketingcloud.WAKE_FOR_ALARM";
    static final String l = "pending_alarms";
    static final String m = g.a("AlarmScheduler");
    private static final long n = 0;
    private final Map<a.EnumC0042a, InterfaceC0044b> d = new HashMap();
    private final com.salesforce.marketingcloud.behaviors.c e;
    BroadcastReceiver f;
    private Context g;
    private h h;
    private SharedPreferences i;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[com.salesforce.marketingcloud.behaviors.a.values().length];
            a = iArr;
            try {
                iArr[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_BOOT_COMPLETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* renamed from: com.salesforce.marketingcloud.alarms.b$b, reason: collision with other inner class name */
    public interface InterfaceC0044b {
        void a(a.EnumC0042a enumC0042a);
    }

    class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                g.d(b.m, "Received null intent", new Object[0]);
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                g.d(b.m, "Received null action", new Object[0]);
                return;
            }
            Bundle extras = intent.getExtras();
            if (extras == null) {
                g.d(b.m, "Intent had no extras", new Object[0]);
                return;
            }
            if (!action.equals(b.j)) {
                g.a(b.m, "Received unknown action: %s", action);
                return;
            }
            String string = extras.getString("com.salesforce.marketingcloud.WAKE_FOR_ALARM", null);
            if (string != null) {
                g.d(b.m, "ACTION_ALARM_WAKE_EVENT had extra: %s", string);
                try {
                    b.this.a(a.EnumC0042a.valueOf(string));
                } catch (IllegalArgumentException unused) {
                    g.e(b.m, "Woke for an unknown alarm: %s", string);
                }
            }
        }
    }

    public b(Context context, h hVar, com.salesforce.marketingcloud.behaviors.c cVar) {
        this.g = context;
        this.h = hVar;
        this.e = (com.salesforce.marketingcloud.behaviors.c) com.salesforce.marketingcloud.util.g.a(cVar, "BehaviorManager is null");
        this.i = hVar.e();
    }

    private static PendingIntent a(Context context, String str, Integer num) {
        return PendingIntent.getBroadcast(context, num.intValue(), MCReceiver.a(context, str), j.a(134217728));
    }

    public void b(a.EnumC0042a... enumC0042aArr) {
        for (a.EnumC0042a enumC0042a : enumC0042aArr) {
            a(enumC0042a, false);
        }
    }

    public boolean c(a.EnumC0042a enumC0042a) {
        return enumC0042a.b().g() && a(enumC0042a, true);
    }

    @Override // com.salesforce.marketingcloud.d
    public final String componentName() {
        return "AlarmScheduler";
    }

    @Override // com.salesforce.marketingcloud.d
    public final JSONObject componentState() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            for (a.EnumC0042a enumC0042a : a.EnumC0042a.values()) {
                if (a(enumC0042a, currentTimeMillis)) {
                    jSONObject2.put(enumC0042a.name(), j.a(new Date(this.i.getLong(enumC0042a.b().a(), 0L) + this.i.getLong(enumC0042a.b().c(), 0L))));
                }
            }
            jSONObject.put(l, jSONObject2);
        } catch (JSONException e) {
            g.b(m, e, "Failed to generate Component State JSONObject.", new Object[0]);
        }
        return jSONObject;
    }

    public void d(a.EnumC0042a... enumC0042aArr) {
        for (a.EnumC0042a enumC0042a : enumC0042aArr) {
            c(enumC0042a);
            a(enumC0042a);
            try {
                ((AlarmManager) this.g.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(a(this.g, enumC0042a.name(), Integer.valueOf(enumC0042a.b().b())));
                g.a(m, "Reset %s alarm.", enumC0042a.name());
            } catch (Exception e) {
                g.e(m, e, "Could not cancel %s alarm.", enumC0042a.name());
            }
        }
    }

    public void e(a.EnumC0042a... enumC0042aArr) {
        synchronized (this.d) {
            for (a.EnumC0042a enumC0042a : enumC0042aArr) {
                this.d.remove(enumC0042a);
            }
        }
    }

    @Override // com.salesforce.marketingcloud.behaviors.b
    public final void onBehavior(com.salesforce.marketingcloud.behaviors.a aVar, Bundle bundle) {
        int i = a.a[aVar.ordinal()];
        if (i == 1 || i == 2) {
            a(bundle.getLong("timestamp"));
        }
    }

    @Override // com.salesforce.marketingcloud.f, com.salesforce.marketingcloud.d
    public final void tearDown(boolean z) {
        if (z) {
            d(a.EnumC0042a.values());
        }
        Context context = this.g;
        if (context != null) {
            context.unregisterReceiver(this.f);
        }
        this.e.a(this);
    }

    public void c(a.EnumC0042a... enumC0042aArr) {
        for (a.EnumC0042a enumC0042a : enumC0042aArr) {
            g.a(m, "Resetting %s Alarm Interval.", enumC0042a.name());
            this.i.edit().putLong(enumC0042a.b().c(), 0L).apply();
        }
    }

    public void a(InterfaceC0044b interfaceC0044b, a.EnumC0042a... enumC0042aArr) {
        synchronized (this.d) {
            for (a.EnumC0042a enumC0042a : enumC0042aArr) {
                this.d.put(enumC0042a, interfaceC0044b);
            }
        }
    }

    final long b(a.EnumC0042a enumC0042a) {
        long e;
        long j2 = this.i.getLong(enumC0042a.b().c(), 0L);
        if (j2 == 0) {
            e = enumC0042a.b().d();
        } else {
            e = (long) (j2 * enumC0042a.b().e());
        }
        if (e <= enumC0042a.b().f()) {
            return e;
        }
        long f = enumC0042a.b().f();
        g.a(m, "%s MAX INTERVAL exceeded. Setting interval to %s milliseconds.", enumC0042a.name(), Long.valueOf(f));
        return f;
    }

    @Override // com.salesforce.marketingcloud.f
    protected final void a(InitializationStatus.a aVar) {
        this.e.a(this, EnumSet.of(com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_DEVICE_BOOT_COMPLETE, com.salesforce.marketingcloud.behaviors.a.BEHAVIOR_APP_PACKAGE_REPLACED));
        this.f = new c();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(j);
        ContextCompat.registerReceiver(this.g, this.f, intentFilter, 4);
    }

    private boolean a(a.EnumC0042a enumC0042a, boolean z) {
        if (!enumC0042a.a(this.h)) {
            g.a(m, "shouldCreateAlarm() for %s Alarm was FALSE.  Aborting alarm creation.", enumC0042a.name());
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long b = b(enumC0042a);
        if (a(enumC0042a, currentTimeMillis)) {
            if (z) {
                return false;
            }
            g.a(m, "%s Send Pending ... will send at %s", enumC0042a.name(), j.a(new Date(this.h.e().getLong(enumC0042a.b().a(), 0L) + b)));
            return false;
        }
        g.a(m, "No pending %s Alarm. Creating one ...", enumC0042a.name());
        a(enumC0042a, currentTimeMillis, b);
        a(this.g, enumC0042a, z ? 1000L : b, currentTimeMillis);
        return true;
    }

    public boolean a(a.EnumC0042a enumC0042a, long j2) {
        return this.i.getLong(enumC0042a.b().a(), 0L) > j2 - this.i.getLong(enumC0042a.b().c(), 0L);
    }

    private void a(a.EnumC0042a enumC0042a, long j2, long j3) {
        g.a(m, "Setting the %s Alarm Flag ...", enumC0042a.name());
        this.i.edit().putLong(enumC0042a.b().a(), j2).putLong(enumC0042a.b().c(), j3).apply();
    }

    void a(a.EnumC0042a... enumC0042aArr) {
        for (a.EnumC0042a enumC0042a : enumC0042aArr) {
            g.a(m, "Resetting %s Alarm Active Flag to FALSE", enumC0042a.name());
            this.i.edit().putLong(enumC0042a.b().a(), 0L).apply();
        }
    }

    void a(Context context, a.EnumC0042a enumC0042a, long j2, long j3) {
        boolean canScheduleExactAlarms;
        PendingIntent a2 = a(context, enumC0042a.name(), Integer.valueOf(enumC0042a.b().b()));
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        long j4 = j3 + j2;
        String a3 = j.a(new Date(j4));
        try {
            if (Build.VERSION.SDK_INT >= 31) {
                canScheduleExactAlarms = alarmManager.canScheduleExactAlarms();
                if (canScheduleExactAlarms) {
                    alarmManager.setExact(0, j4, a2);
                } else {
                    alarmManager.set(0, j4, a2);
                }
            } else {
                alarmManager.setExact(0, j4, a2);
            }
            g.d(m, "%s Alarm scheduled to wake at %s.", enumC0042a.name(), a3);
        } catch (Exception e) {
            g.e(m, e, "Failed to schedule alarm %s for %s", enumC0042a.name(), a3);
        }
    }

    private void a(long j2) {
        for (a.EnumC0042a enumC0042a : a.EnumC0042a.values()) {
            com.salesforce.marketingcloud.alarms.a b = enumC0042a.b();
            long j3 = this.i.getLong(b.a(), 0L);
            if (j3 > 0) {
                if (a(enumC0042a, j2)) {
                    a(this.g, enumC0042a, this.i.getLong(b.c(), b.d()), j3);
                } else {
                    a(enumC0042a);
                }
            }
        }
    }

    void a(a.EnumC0042a enumC0042a) {
        a(enumC0042a);
        InterfaceC0044b interfaceC0044b = this.d.get(enumC0042a);
        if (interfaceC0044b != null) {
            interfaceC0044b.a(enumC0042a);
        }
    }
}
