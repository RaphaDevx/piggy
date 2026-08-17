package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.SequencesKt;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0310m extends Lambda implements Function0 {
    public final /* synthetic */ C0316t a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ boolean c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0310m(C0316t c0316t, boolean z, boolean z2) {
        super(0);
        this.a = c0316t;
        this.b = z;
        this.c = z2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        G g = this.a.q;
        boolean z = this.b;
        boolean z2 = this.c;
        g.c = z;
        for (L l : SequencesKt.flatMapIterable(CollectionsKt.asSequence(g.b.values()), F.a)) {
            if (!g.h.a) {
                if (z) {
                    l.getClass();
                    L.b(l, false);
                    l.b(z2);
                    l.a(z2);
                } else if (g.g == com.scandit.datacapture.barcode.internal.module.count.ui.status.n.b) {
                    L.b(l.b, z2);
                    l.c.a(z2);
                } else {
                    l.getClass();
                    L.a(l, false);
                }
            }
        }
        return Unit.INSTANCE;
    }
}
