package com.scandit.datacapture.core.internal.module.https.scandit;

import com.scandit.datacapture.core.internal.module.https.NativeHttpsResponse;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i0 implements j0 {
    public final NativeHttpsResponse a;

    public i0(NativeHttpsResponse response) {
        Intrinsics.checkNotNullParameter(response, "response");
        this.a = response;
    }
}
