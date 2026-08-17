package com.tealium.internal.j;

import com.tealium.internal.data.Dispatch;
import com.tealium.internal.listeners.PopulateDispatchListener;

/* compiled from: PopulateDispatchMessenger.java */
/* loaded from: classes2.dex */
public class p extends n<PopulateDispatchListener> {
    private final Dispatch b;

    public p(Dispatch dispatch) {
        super(PopulateDispatchListener.class);
        this.b = dispatch;
        if (dispatch == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(PopulateDispatchListener populateDispatchListener) {
        populateDispatchListener.onPopulateDispatch(this.b);
    }
}
