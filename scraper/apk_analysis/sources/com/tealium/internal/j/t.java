package com.tealium.internal.j;

import com.tealium.internal.listeners.SessionStartedListener;

/* compiled from: SessionStartedMessenger.java */
/* loaded from: classes2.dex */
public class t extends n<SessionStartedListener> {
    private String b;

    public t(String str) {
        super(SessionStartedListener.class);
        this.b = str;
    }

    @Override // com.tealium.internal.j.n
    public void a(SessionStartedListener sessionStartedListener) {
        sessionStartedListener.onSessionStarted(this.b);
    }
}
