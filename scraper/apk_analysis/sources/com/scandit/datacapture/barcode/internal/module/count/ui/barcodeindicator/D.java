package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.content.Context;
import android.view.ViewGroup;
import com.scandit.datacapture.core.common.geometry.Point;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class D extends Lambda implements Function0 {
    public final /* synthetic */ G a;
    public final /* synthetic */ E b;
    public final /* synthetic */ int c;
    public final /* synthetic */ float d;
    public final /* synthetic */ int e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D(G g, E e, int i, float f, int i2) {
        super(0);
        this.a = g;
        this.b = e;
        this.c = i;
        this.d = f;
        this.e = i2;
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
        int i = this.c;
        float f = this.d;
        int i2 = this.e;
        G g = this.a;
        Point a = E.a(i, e.a, f, i2, e.c);
        l.a(e.d);
        G.a(g, l, a, f);
        return l;
    }
}
