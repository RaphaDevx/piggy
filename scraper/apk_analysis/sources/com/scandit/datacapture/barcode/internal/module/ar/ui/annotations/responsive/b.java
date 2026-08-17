package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.responsive;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation;
import com.scandit.datacapture.barcode.internal.module.ar.ui.NativeBarcodeArAnnotationVariation;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b extends FrameLayout {
    public final BarcodeArAnnotation a;
    public final BarcodeArAnnotation b;
    public final View c;
    public final View d;
    public View e;
    public View f;
    public ValueAnimator g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(Context context, BarcodeArAnnotation closeUp, BarcodeArAnnotation faraway) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(closeUp, "closeUp");
        Intrinsics.checkNotNullParameter(faraway, "faraway");
        this.a = closeUp;
        this.b = faraway;
        View createView = closeUp.createView();
        this.c = createView;
        View createView2 = faraway.createView();
        this.d = createView2;
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setClickable(false);
        setFocusable(false);
        setImportantForAccessibility(2);
        createView.setVisibility(8);
        createView2.setVisibility(8);
        addView(createView);
        addView(createView2);
    }

    public final void a(Quadrilateral barcodeLocation, Quadrilateral quadrilateral, NativeBarcodeArAnnotationVariation variation) {
        View view;
        ValueAnimator valueAnimator;
        Intrinsics.checkNotNullParameter(barcodeLocation, "barcodeLocation");
        Intrinsics.checkNotNullParameter(variation, "variation");
        int i = a.a[variation.ordinal()];
        if (i == 1) {
            view = null;
        } else if (i == 2) {
            view = this.c;
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            view = this.d;
        }
        View view2 = this.e;
        if (view2 == null) {
            this.e = view;
            if (view != null) {
                view.setVisibility(0);
            }
            if (Intrinsics.areEqual(this.e, this.f)) {
                this.f = null;
            }
        } else if (!Intrinsics.areEqual(view, view2) && ((valueAnimator = this.g) == null || !valueAnimator.isRunning())) {
            this.f = this.e;
            this.e = view;
            if (view != null) {
                view.setAlpha(0.0f);
            }
            View view3 = this.e;
            if (view3 != null) {
                view3.setVisibility(0);
            }
            ValueAnimator valueAnimator2 = this.g;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            final ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            ofFloat.setDuration(250L);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.responsive.b$$ExternalSyntheticLambda0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    b.a(b.this, ofFloat, valueAnimator3);
                }
            });
            ofFloat.start();
            this.g = ofFloat;
        }
        this.a.update(barcodeLocation, quadrilateral, this.c);
        this.b.update(barcodeLocation, quadrilateral, this.d);
    }

    public static final void a(b this$0, ValueAnimator valueAnimator, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue();
        View view = this$0.f;
        if (view != null) {
            view.setAlpha(1 - floatValue);
        }
        View view2 = this$0.e;
        if (view2 != null) {
            view2.setAlpha(floatValue);
        }
        if (floatValue == 1.0f) {
            View view3 = this$0.f;
            if (view3 != null) {
                view3.setVisibility(8);
            }
            valueAnimator.removeAllUpdateListeners();
        }
    }
}
