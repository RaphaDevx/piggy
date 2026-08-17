package com.scandit.datacapture.barcode.internal.module.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i extends ViewGroup {
    public final Outline a;
    public final Path b;
    public final View c;
    public float d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Outline outline = new Outline();
        outline.setAlpha(1.0f);
        this.a = outline;
        this.b = new Path();
        View view = new View(context);
        this.c = view;
        addView(view);
        view.setOutlineProvider(new h(this));
        setClipChildren(false);
        setClipToPadding(false);
        view.setClipToOutline(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (canvas.isHardwareAccelerated()) {
            canvas.save();
            canvas.clipOutPath(this.b);
            super.dispatchDraw(canvas);
            canvas.restore();
        }
    }

    @Override // android.view.View
    public final float getElevation() {
        return this.c.getElevation();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.c.layout(i, i2, i3, i4);
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.c.measure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f = this.d;
        this.b.rewind();
        this.b.addRoundRect(getLeft(), getTop(), measuredWidth, measuredHeight, f, f, Path.Direction.CW);
        this.a.setRoundRect(0, 0, measuredWidth, measuredHeight, f);
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        this.c.setElevation(f);
        this.c.clearAnimation();
    }
}
