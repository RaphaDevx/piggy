package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.k, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0308k extends Lambda implements Function0 {
    public final /* synthetic */ C0316t a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0308k(C0316t c0316t) {
        super(0);
        this.a = c0316t;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        G g = this.a.q;
        for (E e : g.b.values()) {
            Iterator it = e.e.iterator();
            while (it.hasNext()) {
                L l = (L) it.next();
                ViewParent parent = l.getParent();
                ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                if (viewGroup != null) {
                    viewGroup.removeView(l);
                }
            }
            e.e.clear();
        }
        g.b.clear();
        return Unit.INSTANCE;
    }
}
