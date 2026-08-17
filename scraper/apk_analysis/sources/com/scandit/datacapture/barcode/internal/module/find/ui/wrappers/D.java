package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler;
import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategoryHandler;
import com.scandit.datacapture.barcode.internal.module.find.ui.AbstractC0406q;
import com.scandit.datacapture.barcode.internal.module.find.ui.G;
import com.scandit.datacapture.barcode.internal.module.find.ui.H;
import com.scandit.datacapture.barcode.internal.module.find.ui.I;
import com.scandit.datacapture.barcode.internal.module.find.ui.J;
import com.scandit.datacapture.barcode.internal.module.find.ui.K;
import com.scandit.datacapture.barcode.internal.module.find.ui.L;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class D implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    public static final Lazy j = LazyKt.lazy(z.a);
    public static final Lazy k = LazyKt.lazy(A.a);
    public static final Lazy l = LazyKt.lazy(w.a);
    public static final Lazy m = LazyKt.lazy(y.a);
    public static final Lazy n = LazyKt.lazy(x.a);
    public static final Lazy o = LazyKt.lazy(v.a);
    public final Function0 a;
    public final Function0 b;
    public final Function0 c;
    public final ScreenSizeCategoryHandler d;
    public final PreviewResolutionRatioHandler e;
    public final Function0 f;
    public final Function0 g;
    public final Function0 h;
    public final Function1 i;

    public D(G shouldShow, H dataCaptureViewGetter, I shutterButtonViewGetter, ScreenSizeCategoryHandler screenSizeHandler, PreviewResolutionRatioHandler previewResolutionHandler, L isShowingProgressBar, K isShowingTorchButton, J torchButtonPosition, Function1 isTallFormFactor) {
        Intrinsics.checkNotNullParameter(shouldShow, "shouldShow");
        Intrinsics.checkNotNullParameter(dataCaptureViewGetter, "dataCaptureViewGetter");
        Intrinsics.checkNotNullParameter(shutterButtonViewGetter, "shutterButtonViewGetter");
        Intrinsics.checkNotNullParameter(screenSizeHandler, "screenSizeHandler");
        Intrinsics.checkNotNullParameter(previewResolutionHandler, "previewResolutionHandler");
        Intrinsics.checkNotNullParameter(isShowingProgressBar, "isShowingProgressBar");
        Intrinsics.checkNotNullParameter(isShowingTorchButton, "isShowingTorchButton");
        Intrinsics.checkNotNullParameter(torchButtonPosition, "torchButtonPosition");
        Intrinsics.checkNotNullParameter(isTallFormFactor, "isTallFormFactor");
        this.a = shouldShow;
        this.b = dataCaptureViewGetter;
        this.c = shutterButtonViewGetter;
        this.d = screenSizeHandler;
        this.e = previewResolutionHandler;
        this.f = isShowingProgressBar;
        this.g = isShowingTorchButton;
        this.h = torchButtonPosition;
        this.i = isTallFormFactor;
    }

    public static void a(RelativeLayout.LayoutParams layoutParams, boolean z) {
        Intrinsics.checkNotNullParameter(layoutParams, "<this>");
        layoutParams.addRule(10);
        Intrinsics.checkNotNullParameter(layoutParams, "<this>");
        layoutParams.addRule(20);
        Lazy lazy = n;
        layoutParams.setMarginStart(((Number) lazy.getValue()).intValue());
        int intValue = ((Number) lazy.getValue()).intValue();
        layoutParams.topMargin = intValue;
        if (z) {
            layoutParams.topMargin = intValue + ((int) ((Number) AbstractC0406q.a.getValue()).floatValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x022b  */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27, types: [int] */
    /* JADX WARN: Type inference failed for: r0v30 */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.View, android.widget.LinearLayout, com.scandit.datacapture.barcode.internal.module.find.ui.camera.e, java.lang.Object] */
    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(android.view.View r19, android.view.ViewGroup r20) {
        /*
            Method dump skipped, instructions count: 586
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.D.a(android.view.View, android.view.ViewGroup):void");
    }
}
