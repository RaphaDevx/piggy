package com.tealium.internal.j;

import com.tealium.internal.data.Dispatch;
import com.tealium.internal.listeners.DispatchQueuedListener;

/* compiled from: DispatchQueuedMessenger.java */
/* loaded from: classes2.dex */
public class j extends n<DispatchQueuedListener> {
    private final Dispatch b;

    public j(Dispatch dispatch) {
        super(DispatchQueuedListener.class);
        this.b = dispatch;
        if (dispatch == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(DispatchQueuedListener dispatchQueuedListener) {
        dispatchQueuedListener.onDispatchQueued(this.b);
    }
}
