package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler;
import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategory;
import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategoryHandler;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class q implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    public static final Lazy f = LazyKt.lazy(o.a);
    public final Function0 a;
    public final ScreenSizeCategoryHandler b;
    public final PreviewResolutionRatioHandler c;
    public final Function1 d;
    public final Function0 e;

    public q(com.scandit.datacapture.barcode.internal.module.find.ui.E shouldShow, ScreenSizeCategoryHandler sizeCategoryHandler, PreviewResolutionRatioHandler previewResolutionHandler, Function1 isTallFormFactor, com.scandit.datacapture.barcode.internal.module.find.ui.F dataCaptureViewGetter) {
        Intrinsics.checkNotNullParameter(shouldShow, "shouldShow");
        Intrinsics.checkNotNullParameter(sizeCategoryHandler, "sizeCategoryHandler");
        Intrinsics.checkNotNullParameter(previewResolutionHandler, "previewResolutionHandler");
        Intrinsics.checkNotNullParameter(isTallFormFactor, "isTallFormFactor");
        Intrinsics.checkNotNullParameter(dataCaptureViewGetter, "dataCaptureViewGetter");
        this.a = shouldShow;
        this.b = sizeCategoryHandler;
        this.c = previewResolutionHandler;
        this.d = isTallFormFactor;
        this.e = dataCaptureViewGetter;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    public final void a(View view, ViewGroup viewGroup) {
        FrameLayout view2 = (FrameLayout) view;
        RelativeLayout parent = (RelativeLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        boolean booleanValue = ((Boolean) this.a.invoke()).booleanValue();
        boolean closeToFourByThree = this.c.getCloseToFourByThree();
        boolean booleanValue2 = ((Boolean) this.d.invoke(parent)).booleanValue();
        ScreenSizeCategory screenCategory = this.b.getScreenCategory();
        int id = ((View) this.e.invoke()).getId();
        if (booleanValue2) {
            if (!closeToFourByThree || screenCategory == ScreenSizeCategory.SMALL) {
                Intrinsics.checkNotNullParameter(layoutParams, "<this>");
                layoutParams.addRule(8, id);
                layoutParams.bottomMargin = ((Number) f.getValue()).intValue();
                Intrinsics.checkNotNullParameter(layoutParams, "<this>");
                layoutParams.addRule(14);
            } else {
                Intrinsics.checkNotNullParameter(layoutParams, "<this>");
                layoutParams.addRule(12);
                Intrinsics.checkNotNullParameter(layoutParams, "<this>");
                layoutParams.addRule(3, id);
                Intrinsics.checkNotNullParameter(layoutParams, "<this>");
                layoutParams.addRule(14);
            }
        } else if (!closeToFourByThree || screenCategory == ScreenSizeCategory.SMALL) {
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(19, id);
            layoutParams.setMarginEnd(((Number) f.getValue()).intValue());
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(15);
        } else {
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(21);
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(1, id);
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(15);
        }
        view2.setVisibility(!booleanValue ? 4 : 0);
        view2.setLayoutParams(layoutParams);
    }
}
