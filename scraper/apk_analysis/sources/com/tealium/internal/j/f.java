package com.tealium.internal.j;

import com.tealium.internal.listeners.CollectHttpErrorListener;

/* compiled from: CollectHttpErrorMessenger.java */
/* loaded from: classes2.dex */
public class f extends n<CollectHttpErrorListener> {
    private final String b;
    private final Throwable c;

    public f(String str, Throwable th) {
        super(CollectHttpErrorListener.class);
        this.b = str;
        if (str != null) {
            this.c = th;
            if (th != null) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override // com.tealium.internal.j.n
    public void a(CollectHttpErrorListener collectHttpErrorListener) {
        collectHttpErrorListener.onCollectHttpError(this.b, this.c);
    }
}
