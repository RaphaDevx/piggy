package com.scandit.datacapture.core.internal.sdk.battery;

import com.scandit.datacapture.core.source.BatterySavingMode;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function1 {
    public final /* synthetic */ b a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(b bVar) {
        super(1);
        this.a = bVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Function1 function1;
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        b bVar = this.a;
        if (bVar.b == BatterySavingMode.AUTO && (function1 = bVar.c) != null) {
            function1.invoke(bool);
        }
        return Unit.INSTANCE;
    }
}
