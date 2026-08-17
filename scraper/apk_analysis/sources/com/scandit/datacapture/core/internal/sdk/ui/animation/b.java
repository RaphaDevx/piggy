package com.scandit.datacapture.core.internal.sdk.ui.animation;

import android.view.View;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function1 {
    public final /* synthetic */ View a;
    public final /* synthetic */ Function1 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(View view, Function1 function1) {
        super(1);
        this.a = view;
        this.b = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        AnimationSpecKt.applySpec(this.a, (AnimationSpec) this.b.invoke(Float.valueOf(((Number) obj).floatValue())));
        return Unit.INSTANCE;
    }
}
