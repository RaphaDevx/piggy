package com.scandit.datacapture.core.internal.module.source.api2;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.core.internal.module.source.api2.l, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0569l extends Lambda implements Function0 {
    public final /* synthetic */ C0573p a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0569l(C0573p c0573p) {
        super(0);
        this.a = c0573p;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        C0573p c0573p = this.a;
        if (c0573p.o == 1) {
            c0573p.startSingleShotFocusInArea(c0573p.p);
        } else {
            c0573p.startContinuousFocusInArea(c0573p.p);
        }
        return Unit.INSTANCE;
    }
}
