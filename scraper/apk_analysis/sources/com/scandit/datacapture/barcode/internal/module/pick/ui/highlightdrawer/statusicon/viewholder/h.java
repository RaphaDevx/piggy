package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder;

import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.internal.sdk.ui.interpolators.EaseInOutSineInterpolator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public final class h implements c {
    public static final Lazy p = LazyKt.lazy(d.a);
    public final Function2 a;
    public final View b;
    public final View c;
    public final View d;
    public final ImageView e;
    public final ImageView f;
    public final ImageView g;
    public final TextView h;
    public int i;
    public int j;
    public com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.a k;
    public BarcodePickState l;
    public Function2 m;
    public Function1 n;
    public a o;

    public h(View container, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.a initialDrawData, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.a layoutRefresher) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(initialDrawData, "initialDrawData");
        Intrinsics.checkNotNullParameter(layoutRefresher, "layoutRefresher");
        this.a = layoutRefresher;
        this.k = initialDrawData;
        this.m = g.a;
        this.o = a.a;
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.sc_pick_status, (ViewGroup) null, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.b = inflate;
        View findViewById = inflate.findViewById(R.id.bubble);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.c = findViewById;
        View findViewById2 = inflate.findViewById(R.id.arrow);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.e = (ImageView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.iconContainer);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.d = findViewById3;
        View findViewById4 = inflate.findViewById(R.id.icon);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
        this.g = (ImageView) findViewById4;
        View findViewById5 = inflate.findViewById(R.id.iconBackground);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
        this.f = (ImageView) findViewById5;
        View findViewById6 = inflate.findViewById(R.id.text);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
        TextView textView = (TextView) findViewById6;
        this.h = textView;
        inflate.setOnTouchListener(new f(this));
        textView.setTypeface(Typeface.create(null, 600, false));
        textView.setPadding(0, 0, 0, 0);
        a();
    }

    public final void a() {
        a aVar = a.a;
        Intrinsics.checkNotNullParameter(aVar, "<set-?>");
        this.o = aVar;
        this.j = 0;
        TextView textView = this.h;
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.width = 0;
        marginLayoutParams.setMarginStart(0);
        marginLayoutParams.setMarginEnd(0);
        textView.setLayoutParams(marginLayoutParams);
    }

    public final void a(int i, int i2) {
        this.b.setX(i);
        this.b.setY(i2);
    }

    public final void a(String str) {
        if (str != null && !StringsKt.isBlank(str)) {
            TextView textView = this.h;
            if (str.length() > 20) {
                StringBuilder sb = new StringBuilder();
                String substring = str.substring(0, 20);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                sb.append(substring);
                sb.append(Typography.ellipsis);
                str = sb.toString();
            }
            textView.setText(str);
            return;
        }
        this.h.setText((CharSequence) null);
    }

    public final void a(a aVar, final Function0 function0) {
        final int coerceAtMost;
        int i;
        this.b.bringToFront();
        a aVar2 = a.c;
        Intrinsics.checkNotNullParameter(aVar2, "<set-?>");
        this.o = aVar2;
        final boolean z = aVar == a.b;
        if (z) {
            coerceAtMost = 0;
        } else {
            int measureText = (int) this.h.getPaint().measureText(this.h.getText().toString());
            ViewParent parent = this.b.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            coerceAtMost = RangesKt.coerceAtMost(measureText, ((viewGroup != null ? viewGroup.getWidth() : 0) - this.i) - ((Number) p.getValue()).intValue());
        }
        if (z) {
            int measureText2 = (int) this.h.getPaint().measureText(this.h.getText().toString());
            ViewParent parent2 = this.b.getParent();
            ViewGroup viewGroup2 = parent2 instanceof ViewGroup ? (ViewGroup) parent2 : null;
            i = RangesKt.coerceAtMost(measureText2, ((viewGroup2 != null ? viewGroup2.getWidth() : 0) - this.i) - ((Number) p.getValue()).intValue());
        } else {
            i = 0;
        }
        final int i2 = i - coerceAtMost;
        final int intValue = z ? 0 : ((Number) p.getValue()).intValue();
        final int intValue2 = (z ? ((Number) p.getValue()).intValue() : 0) - intValue;
        final float f = z ? 0.0f : 1.0f;
        final float f2 = (z ? 1.0f : 0.0f) - f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                h.a(coerceAtMost, i2, intValue, intValue2, f, f2, this, z, function0, valueAnimator);
            }
        });
        ofFloat.setDuration(250L);
        ofFloat.setInterpolator(new EaseInOutSineInterpolator());
        ofFloat.start();
    }

    public static final void a(int i, int i2, int i3, int i4, float f, float f2, h this$0, boolean z, Function0 onFinished, ValueAnimator valueAnimator) {
        a aVar;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(onFinished, "$onFinished");
        Intrinsics.checkNotNullParameter(valueAnimator, "valueAnimator");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Float");
        float floatValue = ((Float) animatedValue).floatValue();
        int i5 = (int) ((i2 * floatValue) + i);
        int i6 = (int) ((i4 * floatValue) + i3);
        TextView textView = this$0.h;
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.width = i5;
        marginLayoutParams.leftMargin = i6;
        textView.setLayoutParams(marginLayoutParams);
        this$0.h.setAlpha((f2 * floatValue) + f);
        this$0.j = i5 + i6;
        this$0.a.invoke(this$0, this$0.k);
        if (floatValue == 1.0f) {
            if (z) {
                aVar = a.b;
            } else {
                aVar = a.a;
            }
            Intrinsics.checkNotNullParameter(aVar, "<set-?>");
            this$0.o = aVar;
            onFinished.invoke();
        }
    }
}
