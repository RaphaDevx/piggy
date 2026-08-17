package com.tealium.internal.j;

import com.tealium.internal.listeners.RequestFlushListener;

/* compiled from: RequestFlushMessenger.java */
/* loaded from: classes2.dex */
public class s extends n<RequestFlushListener> {
    private String b;

    public s(String str) {
        super(RequestFlushListener.class);
        this.b = str;
    }

    @Override // com.tealium.internal.j.n
    public void a(RequestFlushListener requestFlushListener) {
        requestFlushListener.onRequestFlush(this.b);
    }
}
