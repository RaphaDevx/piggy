package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.module.find.ui.AbstractC0406q;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class m implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    public final Function0 a;
    public final Function0 b;

    public m(com.scandit.datacapture.barcode.internal.module.find.ui.C shouldShow, com.scandit.datacapture.barcode.internal.module.find.ui.D dataCaptureViewGetter) {
        Intrinsics.checkNotNullParameter(shouldShow, "shouldShow");
        Intrinsics.checkNotNullParameter(dataCaptureViewGetter, "dataCaptureViewGetter");
        this.a = shouldShow;
        this.b = dataCaptureViewGetter;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    public final void a(View view, ViewGroup viewGroup) {
        com.scandit.datacapture.barcode.internal.module.ui.e view2 = (com.scandit.datacapture.barcode.internal.module.ui.e) view;
        RelativeLayout parent = (RelativeLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        boolean booleanValue = ((Boolean) this.a.invoke()).booleanValue();
        View view3 = (View) this.b.invoke();
        int width = view3.getWidth();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(0, -2);
        if (width > 0) {
            int id = view3.getId();
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(18, id);
            int id2 = view3.getId();
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(19, id2);
        }
        Intrinsics.checkNotNullParameter(layoutParams, "<this>");
        layoutParams.addRule(10);
        Lazy lazy = AbstractC0406q.c;
        if (width < ((Number) lazy.getValue()).intValue()) {
            Lazy lazy2 = AbstractC0406q.b;
            layoutParams.setMargins(((Number) lazy2.getValue()).intValue(), ((Number) lazy2.getValue()).intValue(), ((Number) lazy2.getValue()).intValue(), 0);
        } else {
            int intValue = (width - ((Number) lazy.getValue()).intValue()) / 2;
            layoutParams.setMargins(intValue, ((Number) AbstractC0406q.b.getValue()).intValue(), intValue, 0);
        }
        view2.setVisibility(booleanValue ? 0 : 4);
        view2.setLayoutParams(layoutParams);
    }
}
