package com.tealium.internal.j;

import com.tealium.internal.listeners.NewSessionListener;

/* compiled from: NewSessionMessenger.java */
/* loaded from: classes2.dex */
public class o extends n<NewSessionListener> {
    private String b;

    public o(String str) {
        super(NewSessionListener.class);
        this.b = str;
    }

    @Override // com.tealium.internal.j.n
    public void a(NewSessionListener newSessionListener) {
        newSessionListener.onNewSession(this.b);
    }
}
