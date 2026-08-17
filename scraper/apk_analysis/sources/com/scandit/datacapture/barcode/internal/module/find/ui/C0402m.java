package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.ui.animation.PulsingView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0402m extends FrameLayout implements Animatable {
    public static final Lazy f = LazyKt.lazy(C0397h.a);
    public static final Lazy g = LazyKt.lazy(C0394e.a);
    public static final Lazy h = LazyKt.lazy(C0393d.a);
    public static final Lazy i = LazyKt.lazy(C0392c.a);
    public static final Lazy j = LazyKt.lazy(C0395f.a);
    public static final Lazy k = LazyKt.lazy(C0396g.a);
    public final Paint a;
    public final Paint b;
    public final Paint c;
    public final PulsingView d;
    public boolean e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0402m(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.sc_barcode_find_dot_default, context.getTheme()));
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.a = paint;
        Paint paint2 = new Paint();
        paint2.setStyle(style);
        paint2.setColor(-1);
        this.b = paint2;
        Paint paint3 = new Paint();
        paint3.setStyle(style);
        paint3.setColor(-1);
        this.c = paint3;
        setClickable(true);
        setFocusable(true);
        setElevation(((Number) j.getValue()).floatValue());
        setClipChildren(false);
        int intValue = ((Number) k.getValue()).intValue();
        int color = paint.getColor();
        int[] gradientColors = {com.scandit.datacapture.barcode.internal.module.extensions.a.a(color, 0), com.scandit.datacapture.barcode.internal.module.extensions.a.a(color, 230)};
        Intrinsics.checkNotNullParameter(this, "container");
        Intrinsics.checkNotNullParameter(gradientColors, "gradientColors");
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        PulsingView pulsingView = new PulsingView(context2, intValue, 0, gradientColors);
        addView(pulsingView, 0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(intValue, intValue);
        layoutParams.gravity = 17;
        pulsingView.setLayoutParams(layoutParams);
        this.d = pulsingView;
        setWillNotDraw(false);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.draw(canvas);
        if (this.e) {
            canvas.drawCircle(C0398i.b(), C0398i.b(), ((Number) h.getValue()).floatValue(), this.c);
        } else {
            canvas.drawCircle(C0398i.b(), C0398i.b(), C0398i.b(), this.b);
            canvas.drawCircle(C0398i.b(), C0398i.b(), C0398i.b() - ((Number) i.getValue()).floatValue(), this.a);
        }
    }

    @Override // android.view.View
    public final int getMinimumHeight() {
        return C0398i.a();
    }

    @Override // android.view.View
    public final int getMinimumWidth() {
        return C0398i.a();
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.d.isRunning();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i2, int i3) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(C0398i.a(), BasicMeasure.EXACTLY);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i2, int i3, int i4, int i5) {
        setOutlineProvider(new C0391b(this, i2, i3));
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        this.d.start();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.d.stop();
    }
}
