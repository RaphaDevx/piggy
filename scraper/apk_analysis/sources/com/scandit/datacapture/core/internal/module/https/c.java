package com.scandit.datacapture.core.internal.module.https;

import com.scandit.datacapture.core.internal.module.https.scandit.C0538j;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class c extends NativeHttpsSessionFactory {
    @Override // com.scandit.datacapture.core.internal.module.https.NativeHttpsSessionFactory
    public final NativeHttpsSession create(NativeHttpsSessionConfiguration config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return new C0538j(config);
    }
}
