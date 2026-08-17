package com.tealium.internal.j;

import com.tealium.internal.listeners.DisableListener;
import com.tealium.library.Tealium;

/* compiled from: DisableMessenger.java */
/* loaded from: classes2.dex */
public class h extends n<DisableListener> {
    private final Tealium b;

    public h(Tealium tealium) {
        super(DisableListener.class);
        this.b = tealium;
        if (tealium == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(DisableListener disableListener) {
        disableListener.onDisable(this.b);
    }
}
