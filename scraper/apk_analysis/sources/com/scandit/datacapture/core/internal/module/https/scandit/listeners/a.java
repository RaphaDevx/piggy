package com.scandit.datacapture.core.internal.module.https.scandit.listeners;

import com.scandit.datacapture.core.internal.module.https.NativeHttpsSession;
import com.scandit.datacapture.core.internal.module.https.NativeHttpsSessionDelegate;
import com.scandit.datacapture.core.internal.module.https.scandit.V;
import com.scandit.datacapture.core.internal.module.https.scandit.X;
import com.scandit.datacapture.core.internal.module.https.scandit.Y;
import com.scandit.datacapture.core.internal.module.https.scandit.g0;
import com.scandit.datacapture.core.internal.module.https.scandit.j0;
import com.scandit.datacapture.core.internal.module.https.scandit.l0;
import java.lang.ref.WeakReference;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements Y {
    public final WeakReference a;

    public a(NativeHttpsSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        WeakReference session2 = new WeakReference(session);
        Intrinsics.checkNotNullParameter(session2, "session");
        this.a = session2;
    }

    @Override // com.scandit.datacapture.core.internal.module.https.scandit.Y
    public final void a(X event) {
        NativeHttpsSessionDelegate delegate;
        Intrinsics.checkNotNullParameter(event, "event");
        NativeHttpsSession nativeHttpsSession = (NativeHttpsSession) this.a.get();
        if (nativeHttpsSession == null) {
            return;
        }
        V v = event instanceof V ? (V) event : null;
        if (v == null) {
            return;
        }
        l0 l0Var = v.b;
        if (((l0Var instanceof j0) || (l0Var instanceof g0)) && (delegate = nativeHttpsSession.getDelegate()) != null) {
            delegate.didComplete(nativeHttpsSession, v.a);
        }
    }
}
