package com.tealium.internal.j;

import android.text.TextUtils;
import com.tealium.internal.listeners.TraceUpdateListener;

/* compiled from: TraceUpdateMessenger.java */
/* loaded from: classes2.dex */
public class u extends n<TraceUpdateListener> {
    private final String b;
    private final boolean c;

    public u(String str, boolean z) {
        super(TraceUpdateListener.class);
        this.b = TextUtils.isEmpty(str) ? null : str;
        this.c = z;
    }

    @Override // com.tealium.internal.j.n
    public void a(TraceUpdateListener traceUpdateListener) {
        traceUpdateListener.onTraceUpdate(this.b, this.c);
    }
}
