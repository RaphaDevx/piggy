package com.tealium.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tealium.internal.data.Dispatch;
import com.tealium.internal.listeners.DisableListener;
import com.tealium.internal.listeners.PopulateDispatchListener;
import com.tealium.library.DataSources;

/* compiled from: BatteryObserver.java */
/* loaded from: classes2.dex */
final class b implements PopulateDispatchListener, DisableListener {
    private final BroadcastReceiver a;
    private final Context b;
    private volatile int c;
    private volatile boolean d;
    private volatile boolean e;

    /* compiled from: BatteryObserver.java */
    class a extends BroadcastReceiver {
        final /* synthetic */ com.tealium.internal.d a;

        a(com.tealium.internal.d dVar) {
            this.a = dVar;
        }

        private void a() {
            this.a.b(new com.tealium.internal.j.d(b.this.e));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            int intExtra = intent.getIntExtra("level", -1);
            int intExtra2 = intent.getIntExtra("scale", -1);
            int intExtra3 = intent.getIntExtra("status", -1);
            boolean z = true;
            if (intExtra == -1 || intExtra2 == -1 || intExtra2 == 0) {
                b.this.c = -1;
                b.this.e = false;
                a();
            } else {
                b.this.c = Math.round((intExtra / intExtra2) * 100.0f);
                boolean z2 = b.this.c <= 15;
                if (b.this.e ^ z2) {
                    b.this.e = z2;
                    a();
                }
            }
            if (intExtra3 != -1) {
                b bVar = b.this;
                if (intExtra3 != 2 && intExtra3 != 5) {
                    z = false;
                }
                bVar.d = z;
            }
        }
    }

    b(Context context, com.tealium.internal.d dVar) {
        if (context == null) {
            throw new IllegalArgumentException();
        }
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        BroadcastReceiver a2 = a(dVar);
        this.a = a2;
        a2.onReceive(applicationContext, applicationContext.registerReceiver(a2, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
    }

    @Override // com.tealium.internal.listeners.DisableListener
    public void onDisable(Tealium tealium) {
        this.b.unregisterReceiver(this.a);
    }

    @Override // com.tealium.internal.listeners.PopulateDispatchListener
    public void onPopulateDispatch(Dispatch dispatch) {
        if (this.c != -1) {
            dispatch.put(DataSources.Key.DEVICE_BATTERY_PERCENT, this.c + "");
        }
        dispatch.put(DataSources.Key.DEVICE_ISCHARGING, this.d + "");
    }

    private BroadcastReceiver a(com.tealium.internal.d dVar) {
        if (dVar != null) {
            return new a(dVar);
        }
        throw new IllegalArgumentException();
    }
}
