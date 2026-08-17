package com.scandit.datacapture.barcode.internal.module.spark.ui.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.spark.ui.E;
import com.scandit.datacapture.barcode.internal.module.ui.animation.PulsingView;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewDefaults;
import com.scandit.datacapture.barcode.spark.internal.module.capture.NativeSparkScanViewStateHelper;
import com.scandit.datacapture.barcode.spark.ui.SparkScanViewState;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import com.scandit.datacapture.core.internal.sdk.extensions.NativeColorExtensionsKt;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class f extends FrameLayout {
    public final k a;
    public i b;
    public c c;
    public Bitmap d;
    public Integer e;
    public Integer f;
    public Integer g;
    public Integer h;
    public final PulsingView i;
    public final com.scandit.datacapture.barcode.internal.module.ui.i j;
    public final RelativeLayout k;
    public final ImageView l;
    public boolean m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Context context, Bitmap bitmap, Integer num, Integer num2, Integer num3, Integer num4, n touchListener) {
        super(context);
        int i;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(touchListener, "touchListener");
        this.a = touchListener;
        this.c = a.a;
        this.d = bitmap;
        this.e = num2;
        this.f = num;
        this.g = num3;
        this.h = num4;
        int intValue = ((Number) E.o.getValue()).intValue();
        int intValue2 = ((Number) E.q.getValue()).intValue();
        if (num4 != null) {
            i = num4.intValue();
        } else {
            NativeColor defaultTriggerButtonAnimationColor = NativeSparkScanViewDefaults.defaultTriggerButtonAnimationColor();
            Intrinsics.checkNotNullExpressionValue(defaultTriggerButtonAnimationColor, "defaultTriggerButtonAnimationColor(...)");
            i = NativeColorExtensionsKt.toInt(defaultTriggerButtonAnimationColor);
        }
        Intrinsics.checkNotNullParameter(this, "container");
        int[] gradientColors = {com.scandit.datacapture.barcode.internal.module.extensions.a.a(i, 0), com.scandit.datacapture.barcode.internal.module.extensions.a.a(i, 0), i};
        Intrinsics.checkNotNullParameter(this, "container");
        Intrinsics.checkNotNullParameter(gradientColors, "gradientColors");
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        PulsingView pulsingView = new PulsingView(context2, intValue, intValue2, gradientColors);
        addView(pulsingView, 0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(intValue, intValue);
        layoutParams.gravity = 17;
        pulsingView.setLayoutParams(layoutParams);
        this.i = pulsingView;
        com.scandit.datacapture.barcode.internal.module.ui.i iVar = new com.scandit.datacapture.barcode.internal.module.ui.i(context);
        iVar.d = ((Number) E.p.getValue()).intValue();
        iVar.requestLayout();
        this.j = iVar;
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setBackgroundResource(R.drawable.sc_spark_scan_capture_button_background);
        relativeLayout.setClipChildren(false);
        relativeLayout.addView(iVar, 0, new FrameLayout.LayoutParams(-1, -1));
        Intrinsics.checkNotNullParameter(this, "<set-?>");
        touchListener.b = this;
        e eVar = new e(this);
        Intrinsics.checkNotNullParameter(eVar, "<set-?>");
        touchListener.r = eVar;
        relativeLayout.setOnTouchListener(touchListener);
        this.k = relativeLayout;
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageDrawable(new BitmapDrawable(context.getResources(), this.d));
        this.l = imageView;
        setClipChildren(false);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(((Number) E.u.getValue()).intValue(), ((Number) E.v.getValue()).intValue());
        layoutParams2.gravity = 17;
        Unit unit = Unit.INSTANCE;
        addView(relativeLayout, layoutParams2);
        Lazy lazy = E.w;
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
        layoutParams3.gravity = 17;
        addView(imageView, layoutParams3);
        b();
        c();
    }

    public final void a(Bitmap bitmap) {
        this.d = bitmap;
        this.l.setImageDrawable(new BitmapDrawable(getContext().getResources(), this.d));
    }

    public final void b() {
        int i;
        int i2;
        Drawable background = this.k.getBackground();
        if (this.c instanceof a) {
            NativeColor defaultTriggerButtonCollapsedColor = NativeSparkScanViewDefaults.defaultTriggerButtonCollapsedColor();
            Intrinsics.checkNotNullExpressionValue(defaultTriggerButtonCollapsedColor, "defaultTriggerButtonCollapsedColor(...)");
            i = NativeColorExtensionsKt.toInt(defaultTriggerButtonCollapsedColor);
        } else {
            NativeColor defaultTriggerButtonExpandedColor = NativeSparkScanViewDefaults.defaultTriggerButtonExpandedColor();
            Intrinsics.checkNotNullExpressionValue(defaultTriggerButtonExpandedColor, "defaultTriggerButtonExpandedColor(...)");
            i = NativeColorExtensionsKt.toInt(defaultTriggerButtonExpandedColor);
        }
        Integer num = this.c instanceof a ? this.f : this.e;
        if (num != null) {
            i = num.intValue();
        }
        int i3 = SparkScanViewCaptureButtonContainer.l;
        if (this.m) {
            i = Color.argb(RangesKt.coerceIn((int) (Color.alpha(i) * 1.25d), 0, 255), Color.red(i), Color.green(i), Color.blue(i));
        }
        Intrinsics.checkNotNull(background, "null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        Drawable findDrawableByLayerId = ((LayerDrawable) background).findDrawableByLayerId(R.id.button_background);
        Intrinsics.checkNotNull(findDrawableByLayerId, "null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        ((GradientDrawable) findDrawableByLayerId).setColor(ColorStateList.valueOf(i));
        Integer num2 = this.g;
        if (num2 != null) {
            this.l.setColorFilter(num2.intValue());
        } else {
            this.l.setColorFilter((ColorFilter) null);
        }
        float floatValue = this.m ? ((Number) E.l.getValue()).floatValue() : this.c instanceof a ? ((Number) E.k.getValue()).floatValue() : 0.0f;
        com.scandit.datacapture.barcode.internal.module.ui.i iVar = this.j;
        iVar.c.setElevation(floatValue);
        iVar.c.clearAnimation();
        Integer num3 = this.h;
        if (num3 != null) {
            i2 = num3.intValue();
        } else {
            NativeColor defaultTriggerButtonAnimationColor = NativeSparkScanViewDefaults.defaultTriggerButtonAnimationColor();
            Intrinsics.checkNotNullExpressionValue(defaultTriggerButtonAnimationColor, "defaultTriggerButtonAnimationColor(...)");
            i2 = NativeColorExtensionsKt.toInt(defaultTriggerButtonAnimationColor);
        }
        PulsingView pulsingView = this.i;
        pulsingView.getClass();
        pulsingView.a(new int[]{com.scandit.datacapture.barcode.internal.module.extensions.a.a(i2, 0), com.scandit.datacapture.barcode.internal.module.extensions.a.a(i2, 0), i2});
        pulsingView.invalidate();
        c cVar = this.c;
        if ((cVar instanceof b) && NativeSparkScanViewStateHelper.isCaptureEnabledState(((b) cVar).a)) {
            this.i.start();
        } else {
            this.i.stop();
        }
    }

    public final void c() {
        int intValue;
        int intValue2;
        int intValue3;
        int intValue4;
        if (this.c instanceof a) {
            int i = E.a;
            intValue = ((Number) E.u.getValue()).intValue();
        } else {
            int i2 = E.a;
            intValue = ((Number) E.n.getValue()).intValue();
        }
        if (this.c instanceof a) {
            int i3 = E.a;
            intValue2 = ((Number) E.v.getValue()).intValue();
        } else {
            int i4 = E.a;
            intValue2 = ((Number) E.o.getValue()).intValue();
        }
        if (this.c instanceof a) {
            int i5 = E.a;
            intValue3 = ((Number) E.u.getValue()).intValue();
        } else {
            int i6 = E.a;
            intValue3 = ((Number) E.p.getValue()).intValue();
        }
        if (this.c instanceof a) {
            int i7 = E.a;
            intValue4 = ((Number) E.v.getValue()).intValue();
        } else {
            int i8 = E.a;
            intValue4 = ((Number) E.q.getValue()).intValue();
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(intValue, intValue2);
        }
        layoutParams.width = intValue;
        layoutParams.height = intValue2;
        setLayoutParams(layoutParams);
        RelativeLayout relativeLayout = this.k;
        ViewGroup.LayoutParams layoutParams2 = relativeLayout.getLayoutParams();
        if (layoutParams2 == null) {
            layoutParams2 = new ViewGroup.LayoutParams(intValue, intValue2);
        }
        layoutParams2.width = intValue3;
        layoutParams2.height = intValue4;
        relativeLayout.setLayoutParams(layoutParams2);
        requestLayout();
        this.k.requestLayout();
    }

    @Override // android.view.View
    public final float getElevation() {
        return this.j.getElevation();
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        this.j.setElevation(f);
    }

    public final void a() {
        setContentDescription(getContext().getString(R.string.sc_spark_scan_collapsed_button));
        this.c = a.a;
        c();
        b();
    }

    public final void a(SparkScanViewState viewState) {
        Intrinsics.checkNotNullParameter(viewState, "viewState");
        setContentDescription(getContext().getString(R.string.sc_spark_scan_expanded_button));
        this.c = new b(viewState);
        c();
        b();
    }
}
