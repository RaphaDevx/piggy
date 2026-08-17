package com.scandit.datacapture.core.internal.module.source.api2;

import android.view.Surface;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class t0 extends Lambda implements Function0 {
    public final /* synthetic */ u0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t0(u0 u0Var) {
        super(0);
        this.a = u0Var;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new Surface(this.a.b);
    }
}
