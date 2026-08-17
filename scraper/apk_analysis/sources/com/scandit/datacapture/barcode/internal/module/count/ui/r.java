package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class r extends LinearLayout {
    public static final int h = PixelExtensionsKt.pxFromDp$default(-2, (Context) null, 1, (Object) null);
    public static final int i = PixelExtensionsKt.pxFromDp$default(-5, (Context) null, 1, (Object) null);
    public q a;
    public final ImageView b;
    public final ImageView c;
    public final ImageView d;
    public ViewPropertyAnimator e;
    public ViewPropertyAnimator f;
    public ViewPropertyAnimator g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = q.a;
        setOrientation(1);
        ImageView imageView = new ImageView(context);
        addView(imageView);
        this.b = imageView;
        ImageView imageView2 = new ImageView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, h, 0, 0);
        imageView2.setLayoutParams(layoutParams);
        addView(imageView2);
        this.c = imageView2;
        ImageView imageView3 = new ImageView(context);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, i, 0, 0);
        imageView3.setLayoutParams(layoutParams2);
        addView(imageView3);
        this.d = imageView3;
    }

    public final void a() {
        ViewPropertyAnimator viewPropertyAnimator = this.e;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.f;
        if (viewPropertyAnimator2 != null) {
            viewPropertyAnimator2.cancel();
        }
        ViewPropertyAnimator viewPropertyAnimator3 = this.g;
        if (viewPropertyAnimator3 != null) {
            viewPropertyAnimator3.cancel();
        }
        int ordinal = this.a.ordinal();
        long j = 0;
        long j2 = 667;
        if (ordinal == 0) {
            j = 667;
            j2 = 0;
        } else if (ordinal != 1) {
            throw new NoWhenBranchMatchedException();
        }
        this.e = a(this.b, j);
        this.f = a(this.c, 333L);
        this.g = a(this.d, j2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPropertyAnimator viewPropertyAnimator = this.e;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.f;
        if (viewPropertyAnimator2 != null) {
            viewPropertyAnimator2.cancel();
        }
        ViewPropertyAnimator viewPropertyAnimator3 = this.g;
        if (viewPropertyAnimator3 != null) {
            viewPropertyAnimator3.cancel();
        }
        this.e = null;
        this.f = null;
        this.g = null;
    }

    public final ViewPropertyAnimator a(final View view, long j) {
        view.setAlpha(1.0f);
        ViewPropertyAnimator withEndAction = view.animate().setDuration(1667L).setInterpolator(new LinearInterpolator()).alpha(0.0f).setStartDelay(j).withEndAction(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.r$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                r.a(r.this, view);
            }
        });
        withEndAction.start();
        Intrinsics.checkNotNullExpressionValue(withEndAction, "with(...)");
        return withEndAction;
    }

    public static final void a(r this$0, View this_with) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_with, "$this_with");
        this$0.a(this_with, 0L);
    }
}
