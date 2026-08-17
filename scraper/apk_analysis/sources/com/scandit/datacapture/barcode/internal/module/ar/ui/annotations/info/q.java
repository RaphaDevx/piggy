package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import android.content.Context;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class q implements p {
    public final Context a;
    public final Function0 b;

    public q(Context context, Function0 dataFactory) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataFactory, "dataFactory");
        this.a = context;
        this.b = dataFactory;
    }
}
