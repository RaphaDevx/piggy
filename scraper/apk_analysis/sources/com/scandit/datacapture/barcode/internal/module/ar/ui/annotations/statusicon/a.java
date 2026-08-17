package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.statusicon;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends LinearLayout {
    public final TextView a;
    public final ImageView b;
    public boolean c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Context context, Barcode barcode) {
        super(context);
        TextView textView = new TextView(context);
        ImageView iconView = new ImageView(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(textView, "textView");
        Intrinsics.checkNotNullParameter(iconView, "iconView");
        this.a = textView;
        this.b = iconView;
        this.c = true;
        setId(R.id.sc_ar_status_icon_text_container);
        setOrientation(0);
        setGravity(17);
        setElevation(((Number) l.a.getValue()).floatValue());
        setContentDescription(getResources().getString(R.string.sc_barcode_ar_annotation_status_icon_content_description, barcode.getData()));
        setBackgroundResource(R.drawable.sc_barcode_ar_status_icon_view_background);
        iconView.setId(R.id.sc_ar_status_icon_image);
        Lazy lazy = l.b;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
        Lazy lazy2 = l.c;
        layoutParams.topMargin = ((Number) lazy2.getValue()).intValue();
        layoutParams.bottomMargin = ((Number) lazy2.getValue()).intValue();
        layoutParams.setMarginEnd(((Number) lazy2.getValue()).intValue());
        layoutParams.setMarginStart(((Number) lazy2.getValue()).intValue());
        Unit unit = Unit.INSTANCE;
        addView(iconView, layoutParams);
        textView.setId(R.id.sc_ar_status_icon_text);
        textView.setTextSize(15.0f);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setHorizontallyScrolling(true);
        textView.setMaxLines(1);
        addView(textView, new LinearLayout.LayoutParams(0, -2));
        ViewExtensionsKt.addRippleForeground$default(this, null, 1, null);
    }

    public final void a() {
        CharSequence text = this.a.getText();
        if (text == null || text.length() == 0) {
            return;
        }
        this.a.measure(0, 0);
        int i = this.a.getLayoutParams().width;
        int measuredWidth = this.c ? this.a.getMeasuredWidth() + ((Number) l.d.getValue()).intValue() : 0;
        this.c = !this.c;
        ValueAnimator ofInt = ValueAnimator.ofInt(i, measuredWidth);
        ofInt.setDuration(250L);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.statusicon.a$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                a.a(a.this, valueAnimator);
            }
        });
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.start();
    }

    public static final void a(a this$0, ValueAnimator animation) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(animation, "animation");
        ViewGroup.LayoutParams layoutParams = this$0.a.getLayoutParams();
        Object animatedValue = animation.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        layoutParams.width = ((Integer) animatedValue).intValue();
        this$0.a.requestLayout();
    }
}
