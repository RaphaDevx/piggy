package com.scandit.datacapture.core.internal.module.source.dynamicresolution;

import android.content.Context;
import android.widget.Toast;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class k extends Lambda implements Function0 {
    public final /* synthetic */ Context a;
    public final /* synthetic */ String b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(Context context, String str) {
        super(0);
        this.a = context;
        this.b = str;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Toast.makeText(this.a, "Dynamic resolution - " + this.b, 0).show();
        return Unit.INSTANCE;
    }
}
