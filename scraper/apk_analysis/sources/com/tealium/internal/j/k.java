package com.tealium.internal.j;

import com.tealium.internal.data.Dispatch;
import com.tealium.internal.listeners.DispatchReadyListener;

/* compiled from: DispatchReadyMessenger.java */
/* loaded from: classes2.dex */
public final class k extends n<DispatchReadyListener> {
    private final Dispatch b;

    public k(Dispatch dispatch) {
        super(DispatchReadyListener.class);
        this.b = dispatch;
        if (dispatch == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(DispatchReadyListener dispatchReadyListener) {
        dispatchReadyListener.onDispatchReady(this.b);
    }
}
