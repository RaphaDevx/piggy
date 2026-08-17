package com.scandit.datacapture.barcode.internal.module.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* loaded from: classes2.dex */
public final class b extends RelativeLayout {
    public int a;
    public final ImageView b;
    public final TextView c;
    public final TextView d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        ImageView imageView = new ImageView(context, null, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(PixelExtensionsKt.pxFromDp(40, context), PixelExtensionsKt.pxFromDp(40, context));
        layoutParams.addRule(10);
        layoutParams.addRule(14);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setId(RelativeLayout.generateViewId());
        this.b = imageView;
        TextView textView = new TextView(context);
        textView.setTextSize(2, 12.0f);
        textView.setTextColor(textView.getResources().getColorStateList(R.color.sc_icon_button_text, context.getTheme()));
        textView.setGravity(17);
        textView.setMaxWidth(PixelExtensionsKt.pxFromDp(40, context));
        textView.setMaxLines(2);
        textView.setHyphenationFrequency(0);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(12);
        layoutParams2.addRule(14);
        textView.setLayoutParams(layoutParams2);
        this.c = textView;
        TextView textView2 = new TextView(context);
        textView2.setBackground(textView2.getResources().getDrawable(R.drawable.sc_icon_button_badge, context.getTheme()));
        textView2.setTextSize(2, 12.0f);
        textView2.setTextColor(-1);
        textView2.setGravity(17);
        int pxFromDp = PixelExtensionsKt.pxFromDp(1, context);
        textView2.setPadding(pxFromDp, pxFromDp, pxFromDp, pxFromDp);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(17, imageView.getId());
        layoutParams3.addRule(6, imageView.getId());
        textView2.setLayoutParams(layoutParams3);
        textView2.setVisibility(8);
        this.d = textView2;
        setClickable(true);
        setFocusable(true);
        addView(imageView);
        addView(textView);
        addView(textView2);
        a(true);
    }

    public final void a(int i) {
        if (this.a != i) {
            this.a = i;
            if (i <= 0) {
                this.d.setText((CharSequence) null);
                this.d.setVisibility(8);
                return;
            }
            this.d.setVisibility(0);
            String valueOf = String.valueOf(i);
            if (valueOf.length() > 3) {
                this.d.setText(getResources().getString(R.string.sc_button_list_badge_max, Integer.valueOf(((int) Math.pow(10.0d, 3)) - 1)));
            } else {
                this.d.setText(valueOf);
            }
            int length = valueOf.length();
            int i2 = length != 1 ? length != 2 ? length != 3 ? -14 : -12 : -10 : -8;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            int pxFromDp = PixelExtensionsKt.pxFromDp(i2, context);
            int i3 = length != 1 ? length != 2 ? length != 3 ? 38 : 27 : 20 : 18;
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            int pxFromDp2 = PixelExtensionsKt.pxFromDp(i3, context2);
            TextView textView = this.d;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(pxFromDp2, -2);
            layoutParams.setMarginStart(pxFromDp);
            layoutParams.addRule(17, this.b.getId());
            layoutParams.addRule(6, this.b.getId());
            textView.setLayoutParams(layoutParams);
        }
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        this.b.setEnabled(z);
        this.c.setEnabled(z);
    }

    public final void a(boolean z) {
        if (z) {
            this.b.setBackgroundResource(R.drawable.sc_icon_button_background_light);
        } else {
            this.b.setBackgroundResource(R.drawable.sc_icon_button_background_dark);
        }
        this.b.setImageTintList(new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{-2130706433, z ? -15591911 : -1}));
    }

    public final void a(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.c.setText(value);
        this.c.setVisibility((value == null || StringsKt.isBlank(value)) ? 8 : 0);
    }
}
