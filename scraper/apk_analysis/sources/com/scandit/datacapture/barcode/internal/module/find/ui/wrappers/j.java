package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategory;
import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategoryHandler;
import com.scandit.datacapture.barcode.internal.module.find.ui.AbstractC0406q;
import com.scandit.datacapture.barcode.internal.module.find.ui.C0420z;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    public final Function0 a;
    public final Function1 b;
    public final ScreenSizeCategoryHandler c;
    public final Function0 d;
    public final Function0 e;

    public j(C0420z shouldShow, Function1 isTallFormFactor, ScreenSizeCategoryHandler screenSizeHandler, com.scandit.datacapture.barcode.internal.module.find.ui.A isShowingProgressBar, com.scandit.datacapture.barcode.internal.module.find.ui.B shutterButtonViewGetter) {
        Intrinsics.checkNotNullParameter(shouldShow, "shouldShow");
        Intrinsics.checkNotNullParameter(isTallFormFactor, "isTallFormFactor");
        Intrinsics.checkNotNullParameter(screenSizeHandler, "screenSizeHandler");
        Intrinsics.checkNotNullParameter(isShowingProgressBar, "isShowingProgressBar");
        Intrinsics.checkNotNullParameter(shutterButtonViewGetter, "shutterButtonViewGetter");
        this.a = shouldShow;
        this.b = isTallFormFactor;
        this.c = screenSizeHandler;
        this.d = isShowingProgressBar;
        this.e = shutterButtonViewGetter;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    public final void a(View view, ViewGroup viewGroup) {
        FrameLayout view2 = (FrameLayout) view;
        RelativeLayout parent = (RelativeLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(0, 0);
        boolean booleanValue = ((Boolean) this.a.invoke()).booleanValue();
        boolean booleanValue2 = ((Boolean) this.b.invoke(parent)).booleanValue();
        ScreenSizeCategory screenCategory = this.c.getScreenCategory();
        boolean booleanValue3 = ((Boolean) this.d.invoke()).booleanValue();
        int id = ((View) this.e.invoke()).getId();
        if (booleanValue2) {
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(1, id);
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(21);
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(8, id);
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(6, id);
        } else {
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(18, id);
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(19, id);
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(2, id);
            Intrinsics.checkNotNullParameter(layoutParams, "<this>");
            layoutParams.addRule(10);
            if (booleanValue3 && screenCategory == ScreenSizeCategory.SMALL) {
                layoutParams.topMargin = (int) ((Number) AbstractC0406q.a.getValue()).floatValue();
            }
        }
        view2.setVisibility(booleanValue ? 0 : 4);
        view2.setLayoutParams(layoutParams);
    }
}
