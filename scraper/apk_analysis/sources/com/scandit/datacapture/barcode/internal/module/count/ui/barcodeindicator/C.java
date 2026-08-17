package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.content.Context;
import android.view.ViewGroup;
import com.scandit.datacapture.core.common.geometry.Point;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class C extends Lambda implements Function0 {
    public final /* synthetic */ G a;
    public final /* synthetic */ E b;
    public final /* synthetic */ float c;
    public final /* synthetic */ int d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(G g, E e, float f, int i) {
        super(0);
        this.a = g;
        this.b = e;
        this.c = f;
        this.d = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        S.a.getClass();
        int floatValue = (int) ((Number) Q.b.getValue()).floatValue();
        Context context = this.a.a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        L l = new L(context, this.a.g);
        l.setLayoutParams(new ViewGroup.LayoutParams(floatValue, floatValue));
        E e = this.b;
        float f = this.c;
        int i = this.d;
        G g = this.a;
        Point a = E.a(0, e.a, f, i, e.c);
        l.a(e.d);
        G.a(g, l, a, f);
        return l;
    }
}
