package com.tealium.internal.j;

import com.tealium.internal.data.Dispatch;
import com.tealium.internal.listeners.BulkDispatchSendListener;
import java.util.List;

/* compiled from: BulkDispatchSendMessenger.java */
/* loaded from: classes2.dex */
public class e extends n<BulkDispatchSendListener> {
    private final List<Dispatch> b;

    public e(List<Dispatch> list) {
        super(BulkDispatchSendListener.class);
        this.b = list;
        if (list == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override // com.tealium.internal.j.n
    public void a(BulkDispatchSendListener bulkDispatchSendListener) {
        bulkDispatchSendListener.onBulkDispatchSend(this.b);
    }
}
