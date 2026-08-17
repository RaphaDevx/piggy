package com.tealium.internal.j;

import com.tealium.internal.data.Dispatch;
import com.tealium.internal.listeners.DispatchPurgedListener;

/* compiled from: DispatchPurgedMessenger.java */
/* loaded from: classes2.dex */
public class i extends n<DispatchPurgedListener> {
    private final Dispatch b;

    public i(Dispatch dispatch) {
        super(DispatchPurgedListener.class);
        if (dispatch == null) {
            throw new IllegalArgumentException();
        }
        this.b = dispatch;
    }

    @Override // com.tealium.internal.j.n
    public void a(DispatchPurgedListener dispatchPurgedListener) {
        dispatchPurgedListener.onDispatchPurged(this.b);
    }
}
