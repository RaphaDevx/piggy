package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import java.util.Collection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.n, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0311n extends Lambda implements Function0 {
    public final /* synthetic */ C0316t a;
    public final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0311n(C0316t c0316t, boolean z) {
        super(0);
        this.a = c0316t;
        this.b = z;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Collection<L> values = this.a.r.values();
        boolean z = this.b;
        for (L l : values) {
            L.b(l.d, z);
            if (l.a() == J.a) {
                l.c.a(false, z);
            }
        }
        G g = this.a.q;
        boolean z2 = this.b;
        g.getClass();
        g.h = new A(true, z2);
        return Unit.INSTANCE;
    }
}
