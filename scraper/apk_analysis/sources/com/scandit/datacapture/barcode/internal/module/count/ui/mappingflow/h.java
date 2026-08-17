package com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow;

import android.content.Context;
import android.view.ViewGroup;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class h extends Lambda implements Function1 {
    public final /* synthetic */ j a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(j jVar) {
        super(1);
        this.a = jVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context context = (Context) obj;
        Intrinsics.checkNotNullParameter(context, "context");
        e eVar = new e(context);
        j jVar = this.a;
        eVar.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        jVar.b = eVar;
        return eVar;
    }
}
