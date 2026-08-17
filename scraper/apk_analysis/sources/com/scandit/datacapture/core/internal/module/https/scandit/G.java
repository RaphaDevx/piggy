package com.scandit.datacapture.core.internal.module.https.scandit;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class G extends Lambda implements Function0 {
    public final /* synthetic */ I a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public G(I i) {
        super(0);
        this.a = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        I i = this.a;
        i.g = a0.b;
        i.b();
        return Unit.INSTANCE;
    }
}
