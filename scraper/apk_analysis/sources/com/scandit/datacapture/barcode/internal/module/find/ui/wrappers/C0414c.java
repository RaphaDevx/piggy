package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.module.find.ui.AbstractC0406q;
import com.scandit.datacapture.barcode.internal.module.find.ui.C0411w;
import com.scandit.datacapture.barcode.internal.module.find.ui.C0418x;
import com.scandit.datacapture.barcode.internal.module.find.ui.C0419y;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0414c implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    public static final Lazy e = LazyKt.lazy(C0412a.a);
    public final Function0 a;
    public final Function0 b;
    public final Function0 c;
    public final Function1 d;

    public C0414c(C0411w shouldShow, C0418x shutterButtonReferenceViewGetter, C0419y progressBarReferenceViewGetter, Function1 isTallFormFactor) {
        Intrinsics.checkNotNullParameter(shouldShow, "shouldShow");
        Intrinsics.checkNotNullParameter(shutterButtonReferenceViewGetter, "shutterButtonReferenceViewGetter");
        Intrinsics.checkNotNullParameter(progressBarReferenceViewGetter, "progressBarReferenceViewGetter");
        Intrinsics.checkNotNullParameter(isTallFormFactor, "isTallFormFactor");
        this.a = shouldShow;
        this.b = shutterButtonReferenceViewGetter;
        this.c = progressBarReferenceViewGetter;
        this.d = isTallFormFactor;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    public final void a(View view, ViewGroup viewGroup) {
        CardsCarouselView view2 = (CardsCarouselView) view;
        RelativeLayout parent = (RelativeLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        boolean booleanValue = ((Boolean) this.a.invoke()).booleanValue();
        int id = ((View) this.b.invoke()).getId();
        int id2 = ((View) this.c.invoke()).getId();
        boolean booleanValue2 = ((Boolean) this.d.invoke(parent)).booleanValue();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (booleanValue2) {
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(2, id);
            Lazy lazy = e;
            int intValue = ((Number) lazy.getValue()).intValue();
            Lazy lazy2 = AbstractC0406q.b;
            layoutParams.setMargins(intValue, ((Number) lazy2.getValue()).intValue(), ((Number) lazy.getValue()).intValue(), ((Number) lazy2.getValue()).intValue());
        } else {
            layoutParams.width = ((Number) AbstractC0406q.c.getValue()).intValue();
            Lazy lazy3 = AbstractC0406q.b;
            layoutParams.setMargins(0, ((Number) lazy3.getValue()).intValue(), 0, ((Number) lazy3.getValue()).intValue());
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(14);
        }
        Intrinsics.checkNotNullParameter(layoutParams, "<this>");
        layoutParams.addRule(3, id2);
        view2.setVisibility(booleanValue ? 0 : 4);
        view2.setLayoutParams(layoutParams);
        view2.prepareButtonBar();
    }
}
