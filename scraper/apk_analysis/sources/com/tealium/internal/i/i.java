package com.tealium.internal.i;

import com.tealium.collect.listeners.VisitorProfileUpdateListener;
import com.tealium.collect.visitor.VisitorProfile;
import com.tealium.internal.j.n;

/* compiled from: VisitorProfileUpdateMessenger.java */
/* loaded from: classes2.dex */
final class i extends n<VisitorProfileUpdateListener> {
    private final VisitorProfile b;
    private final VisitorProfile c;

    public i(VisitorProfile visitorProfile, VisitorProfile visitorProfile2) {
        super(VisitorProfileUpdateListener.class);
        this.b = visitorProfile;
        this.c = visitorProfile2;
    }

    @Override // com.tealium.internal.j.n
    public void a(VisitorProfileUpdateListener visitorProfileUpdateListener) {
        visitorProfileUpdateListener.onVisitorProfileUpdated(this.b, this.c);
    }
}
