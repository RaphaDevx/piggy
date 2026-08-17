package com.scandit.datacapture.core.ui.control;

import android.view.ViewGroup;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function1 {
    public final /* synthetic */ LinearControlGroup a;
    public final /* synthetic */ Control b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(LinearControlGroup linearControlGroup, Control control) {
        super(1);
        this.a = linearControlGroup;
        this.b = control;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        ViewGroup it = (ViewGroup) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.get_view().removeView(this.b.get_view());
        return Unit.INSTANCE;
    }
}
