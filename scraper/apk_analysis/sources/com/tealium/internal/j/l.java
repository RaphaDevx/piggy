package com.tealium.internal.j;

import com.tealium.internal.data.Dispatch;
import com.tealium.internal.listeners.DispatchSendListener;

/* compiled from: DispatchSendMessenger.java */
/* loaded from: classes2.dex */
public class l extends n<DispatchSendListener> {
    private final Dispatch b;

    public l(Dispatch dispatch) {
        super(DispatchSendListener.class);
        this.b = dispatch;
        if (dispatch == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(DispatchSendListener dispatchSendListener) {
        dispatchSendListener.onDispatchSend(this.b);
    }
}
