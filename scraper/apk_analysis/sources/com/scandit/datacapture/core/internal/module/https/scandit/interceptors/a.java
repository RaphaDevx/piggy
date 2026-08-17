package com.scandit.datacapture.core.internal.module.https.scandit.interceptors;

import com.scandit.datacapture.core.internal.module.https.b;
import com.scandit.datacapture.core.internal.module.https.d;
import com.scandit.datacapture.core.internal.module.https.scandit.client.l;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements l {
    public final b a;
    public final boolean b;

    public a(boolean z) {
        d connectivity = new d();
        Intrinsics.checkNotNullParameter(connectivity, "connectivity");
        this.a = connectivity;
        this.b = z;
    }

    public final String toString() {
        return "NetworkTypeInterceptor{connectivity=" + this.a + ", allowCellularAccess=" + this.b + '}';
    }
}
