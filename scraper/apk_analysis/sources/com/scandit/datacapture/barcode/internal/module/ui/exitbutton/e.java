package com.scandit.datacapture.barcode.internal.module.ui.exitbutton;

import android.view.ViewGroup;
import android.widget.RelativeLayout;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e implements ExitButtonWrapper {
    public final com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d a;
    public Function0 b;
    public RelativeLayout c;
    public final Lazy d;

    public e(b factory, h positioner) {
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(positioner, "positioner");
        this.a = positioner;
        this.d = LazyKt.lazy(new d(factory, this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.exitbutton.ExitButtonWrapper
    public final void a(ViewGroup viewGroup, c data) {
        RelativeLayout parent = (RelativeLayout) viewGroup;
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(data, "data");
        this.c = parent;
        parent.addView((com.scandit.datacapture.barcode.internal.module.ui.b) this.d.getValue());
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = (com.scandit.datacapture.barcode.internal.module.ui.b) this.d.getValue();
        ((com.scandit.datacapture.barcode.internal.module.ui.b) this.d.getValue()).setVisibility(!data.a ? 4 : 0);
        bVar.a(true);
        a();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.exitbutton.ExitButtonWrapper
    public final void a(com.scandit.datacapture.barcode.pick.ui.c cVar) {
        this.b = cVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.exitbutton.ExitButtonWrapper
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d dVar = this.a;
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = (com.scandit.datacapture.barcode.internal.module.ui.b) this.d.getValue();
        RelativeLayout relativeLayout = this.c;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("parent");
            relativeLayout = null;
        }
        dVar.a(bVar, relativeLayout);
    }
}
