package com.scandit.datacapture.barcode.internal.module.find.ui;

import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class P extends Lambda implements Function0 {
    public final /* synthetic */ u0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P(u0 u0Var) {
        super(0);
        this.a = u0Var;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        u0 u0Var = this.a;
        if (u0Var.n) {
            u0Var.n = false;
            ViewExtensionsKt.runOnMainThread(u0Var.j, new i0(u0Var));
        }
        return Unit.INSTANCE;
    }
}
