package com.scandit.datacapture.core.internal.module.ui.control.layout;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ ControlLayout a;
    public final /* synthetic */ View b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(ControlLayout controlLayout, View view) {
        super(1);
        this.a = controlLayout;
        this.b = view;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ControlLayout it = (ControlLayout) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.removeView(this.b);
        return Unit.INSTANCE;
    }
}
