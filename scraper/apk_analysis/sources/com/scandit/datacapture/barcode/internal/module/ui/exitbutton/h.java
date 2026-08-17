package com.scandit.datacapture.barcode.internal.module.ui.exitbutton;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class h implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    public static final Lazy a = LazyKt.lazy(f.a);
    public static final Lazy b = LazyKt.lazy(g.a);

    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    public final void a(View view, ViewGroup viewGroup) {
        com.scandit.datacapture.barcode.internal.module.ui.b view2 = (com.scandit.datacapture.barcode.internal.module.ui.b) view;
        RelativeLayout parent = (RelativeLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (parent.getHeight() >= parent.getWidth()) {
            view2.setGravity(16);
            Lazy lazy = com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.d.a;
            layoutParams.addRule(6, ((Number) lazy.getValue()).intValue());
            layoutParams.addRule(8, ((Number) lazy.getValue()).intValue());
            layoutParams.addRule(1, ((Number) lazy.getValue()).intValue());
            layoutParams.setMargins(((Number) b.getValue()).intValue(), 0, 0, 0);
        } else {
            view2.setGravity(1);
            Lazy lazy2 = com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.d.a;
            layoutParams.addRule(5, ((Number) lazy2.getValue()).intValue());
            layoutParams.addRule(7, ((Number) lazy2.getValue()).intValue());
            layoutParams.addRule(2, ((Number) lazy2.getValue()).intValue());
            layoutParams.setMargins(0, 0, 0, ((Number) a.getValue()).intValue());
        }
        view2.setLayoutParams(layoutParams);
    }
}
