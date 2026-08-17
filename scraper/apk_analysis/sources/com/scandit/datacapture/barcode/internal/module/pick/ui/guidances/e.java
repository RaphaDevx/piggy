package com.scandit.datacapture.barcode.internal.module.pick.ui.guidances;

import android.widget.FrameLayout;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function1 {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ g b;
    public final /* synthetic */ String c;
    public final /* synthetic */ boolean d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(boolean z, g gVar, String str, boolean z2) {
        super(1);
        this.a = z;
        this.b = gVar;
        this.c = str;
        this.d = z2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        g it = (g) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        if (this.a) {
            i iVar = (i) this.b.e.getValue();
            String text = this.c;
            boolean z = this.d;
            l lVar = (l) iVar;
            lVar.getClass();
            Intrinsics.checkNotNullParameter(text, "text");
            if (z) {
                lVar.a.a(com.scandit.datacapture.barcode.internal.module.ui.l.c, text);
            } else {
                lVar.a.a(com.scandit.datacapture.barcode.internal.module.ui.l.d, text);
            }
            g gVar = this.b;
            if (!gVar.d) {
                gVar.addView(((l) ((i) gVar.e.getValue())).a, new FrameLayout.LayoutParams(-2, -2, 17));
            }
        } else {
            g gVar2 = this.b;
            if (gVar2.d) {
                gVar2.removeView(((l) ((i) gVar2.e.getValue())).a);
            }
        }
        this.b.d = this.a;
        return Unit.INSTANCE;
    }
}
