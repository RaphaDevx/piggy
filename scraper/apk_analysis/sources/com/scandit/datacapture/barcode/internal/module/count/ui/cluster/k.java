package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class k extends Lambda implements Function1 {
    public static final k a = new k();

    public k() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        NativeCluster it = (NativeCluster) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }
}
