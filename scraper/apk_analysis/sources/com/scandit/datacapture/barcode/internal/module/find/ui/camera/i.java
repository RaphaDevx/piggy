package com.scandit.datacapture.barcode.internal.module.find.ui.camera;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.Button;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class i extends Button {
    public static final int c = Color.parseColor("#40000000");
    public static final int d = Color.parseColor("#FFFBC02C");
    public static final int e = Color.parseColor("#FFFFFFFF");
    public static final Lazy f = LazyKt.lazy(f.a);
    public static final Lazy g = LazyKt.lazy(g.a);
    public final DecimalFormat a;
    public float b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        DecimalFormat decimalFormat = new DecimalFormat("0.#", new DecimalFormatSymbols(Locale.ENGLISH));
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        this.a = decimalFormat;
        setPadding(0, 0, 0, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(c);
        gradientDrawable.setCornerRadius(90.0f);
        setBackground(gradientDrawable);
        setAllCaps(false);
        this.b = 1.0f;
    }

    public final void a(float f2) {
        String format = this.a.format(Float.valueOf(f2));
        if (!isSelected()) {
            setText(format);
            return;
        }
        setText(format + 'x');
    }

    @Override // android.widget.TextView, android.view.View
    public final void setSelected(boolean z) {
        int i;
        Typeface create;
        ViewGroup.LayoutParams layoutParams;
        float f2;
        super.setSelected(z);
        if (z) {
            i = d;
            create = Typeface.create(getTypeface(), 1);
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            layoutParams = getLayoutParams();
            Lazy lazy = f;
            layoutParams.width = ((Number) lazy.getValue()).intValue();
            layoutParams.height = ((Number) lazy.getValue()).intValue();
            Intrinsics.checkNotNullExpressionValue(layoutParams, "also(...)");
            f2 = 13.0f;
        } else {
            i = e;
            create = Typeface.create(getTypeface(), 500, false);
            Intrinsics.checkNotNullExpressionValue(create, "create(...)");
            layoutParams = getLayoutParams();
            Lazy lazy2 = g;
            layoutParams.width = ((Number) lazy2.getValue()).intValue();
            layoutParams.height = ((Number) lazy2.getValue()).intValue();
            Intrinsics.checkNotNullExpressionValue(layoutParams, "also(...)");
            f2 = 12.0f;
        }
        setTextColor(i);
        setTextSize(2, f2);
        setTypeface(create);
        setLayoutParams(layoutParams);
        a(this.b);
    }
}
