package com.scandit.datacapture.core.ui.control;

import android.view.View;
import android.widget.LinearLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function1 {
    public final /* synthetic */ LinearLayout a;
    public final /* synthetic */ View b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(LinearLayout linearLayout, View view) {
        super(1);
        this.a = linearLayout;
        this.b = view;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        LinearLayout it = (LinearLayout) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.addView(this.b);
        return Unit.INSTANCE;
    }
}
