package com.scandit.datacapture.core.internal.module.https.scandit.client;

import com.scandit.datacapture.core.internal.module.https.scandit.X;
import com.scandit.datacapture.core.internal.module.https.scandit.Y;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c implements Y {
    public final /* synthetic */ e a;

    public c(e eVar) {
        this.a = eVar;
    }

    @Override // com.scandit.datacapture.core.internal.module.https.scandit.Y
    public final void a(X event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Iterator it = this.a.f.iterator();
        while (it.hasNext()) {
            try {
                ((Y) it.next()).a(event);
            } catch (Throwable unused) {
            }
        }
    }
}
