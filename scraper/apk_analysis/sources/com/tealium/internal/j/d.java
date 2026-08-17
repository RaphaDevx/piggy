package com.tealium.internal.j;

import com.tealium.internal.listeners.BatteryUpdateListener;

/* compiled from: BatteryUpdateMessenger.java */
/* loaded from: classes2.dex */
public final class d extends n<BatteryUpdateListener> {
    private final boolean b;

    public d(boolean z) {
        super(BatteryUpdateListener.class);
        this.b = z;
    }

    @Override // com.tealium.internal.j.n
    public void a(BatteryUpdateListener batteryUpdateListener) {
        batteryUpdateListener.onBatteryUpdate(this.b);
    }
}
