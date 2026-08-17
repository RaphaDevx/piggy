package com.scandit.datacapture.barcode.internal.module.ui.popover;

import android.util.Size;
import kotlin.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class K implements InterfaceC0493o {
    public int b;
    public int c;
    public EnumC0488j a = EnumC0488j.b;
    public EnumC0494p d = EnumC0494p.a;
    public Size e = new Size(0, 0);

    public static final void b(K k, O o, P p) {
        k.getClass();
        k.a = EnumC0488j.a;
        k.b = o.a + o.c;
        k.c = o.b - Q.a(p.a);
        k.d = EnumC0494p.d;
    }

    public static final void c(K k, O o, P p) {
        k.getClass();
        k.a = EnumC0488j.b;
        k.b = o.a - Q.b(p.b);
        k.c = (o.b - o.d) - p.b.getHeight();
        k.d = EnumC0494p.b;
    }

    public static final void d(K k, O o, P p) {
        k.getClass();
        k.a = EnumC0488j.b;
        k.b = o.a - Q.b(p.b);
        k.c = o.b + o.d;
        k.d = EnumC0494p.a;
    }

    public final L a(O indicatorData, P popoverSizes) {
        Function2 a;
        Intrinsics.checkNotNullParameter(indicatorData, "indicatorData");
        Intrinsics.checkNotNullParameter(popoverSizes, "popoverSizes");
        int i = indicatorData.b;
        Lazy lazy = O.e;
        boolean z = i > popoverSizes.b.getHeight() + (((Number) lazy.getValue()).intValue() + indicatorData.d);
        boolean z2 = indicatorData.b < this.e.getHeight() - (popoverSizes.b.getHeight() + (((Number) lazy.getValue()).intValue() + indicatorData.d));
        boolean z3 = (indicatorData.a > Q.b(popoverSizes.b)) && (indicatorData.a < this.e.getWidth() - Q.b(popoverSizes.b));
        boolean z4 = indicatorData.a > (popoverSizes.a.getWidth() + ((Number) lazy.getValue()).intValue()) + indicatorData.c;
        boolean z5 = indicatorData.a < this.e.getWidth() - ((popoverSizes.a.getWidth() + ((Number) lazy.getValue()).intValue()) + indicatorData.c);
        boolean z6 = (indicatorData.b > Q.a(popoverSizes.a)) && (indicatorData.b < this.e.getHeight() - Q.a(popoverSizes.a));
        boolean z7 = z && z3;
        boolean z8 = z2 && z3;
        boolean z9 = z4 && z6;
        boolean z10 = z5 && z6;
        int ordinal = this.d.ordinal();
        if (ordinal == 0) {
            a = z8 ? new A(this) : z7 ? new C(this) : z10 ? new D(this) : z9 ? new E(this) : new F(this);
        } else if (ordinal == 1) {
            a = z7 ? new G(this) : z8 ? new H(this) : z10 ? new I(this) : z9 ? new J(this) : new C0495q(this);
        } else if (ordinal == 2) {
            a = z8 ? new r(this) : z7 ? new s(this) : z9 ? new t(this) : z10 ? new u(this) : new v(this);
        } else {
            if (ordinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
            a = z8 ? new w(this) : z7 ? new x(this) : z10 ? new y(this) : z9 ? new z(this) : new B(this);
        }
        a.invoke(indicatorData, popoverSizes);
        EnumC0488j enumC0488j = this.a;
        int i2 = this.b;
        int i3 = this.c;
        int i4 = indicatorData.a;
        int i5 = indicatorData.b;
        int i6 = indicatorData.c;
        int i7 = indicatorData.d;
        return new L(enumC0488j, i2, i3, i4 + i6 < 0 || i5 + i7 < 0 || i4 - i6 > this.e.getWidth() || i5 - i7 > this.e.getHeight());
    }

    public static final void a(K k, O o, P p) {
        k.getClass();
        k.a = EnumC0488j.a;
        k.b = (o.a - o.c) - p.a.getWidth();
        k.c = o.b - Q.a(p.a);
        k.d = EnumC0494p.c;
    }
}
