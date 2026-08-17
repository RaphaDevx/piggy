package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.content.Context;
import android.view.GestureDetector;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function0 {
    public final /* synthetic */ Context a;
    public final /* synthetic */ j b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Context context, j jVar) {
        super(0);
        this.a = context;
        this.b = jVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new GestureDetector(this.a, this.b.d);
    }
}
