package com.scandit.datacapture.barcode.internal.module.pick.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e extends FrameLayout {
    public static final int d = Color.parseColor("#66000000");
    public static final int e = Color.parseColor("#CC121619");
    public static final Lazy f = LazyKt.lazy(c.a);
    public static final Lazy g = LazyKt.lazy(b.a);
    public final String a;
    public final String b;
    public final TextView c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Context context, String textForPicking, String textForUnpicking) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(textForPicking, "textForPicking");
        Intrinsics.checkNotNullParameter(textForUnpicking, "textForUnpicking");
        this.a = textForPicking;
        this.b = textForUnpicking;
        setPadding(d.a(), d.a(), d.a(), d.a());
        setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.pick.ui.e$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                e.a(view);
            }
        });
        setBackgroundColor(d);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setPadding(d.a(), d.a(), d.a(), d.a());
        linearLayout.setBackgroundColor(e);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(8388627);
        linearLayout.setElevation(((Number) g.getValue()).floatValue());
        linearLayout.addView(new ProgressBar(context), new LinearLayout.LayoutParams(-2, -2));
        linearLayout.addView(new View(context), new LinearLayout.LayoutParams(d.a(), 0));
        TextView textView = new TextView(context);
        textView.setTextColor(-1);
        this.c = textView;
        linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        addView(linearLayout, new FrameLayout.LayoutParams(context.getResources().getDimensionPixelSize(R.dimen.sc_loading_dialog), -2, 17));
    }

    public static final void a(View view) {
    }
}
