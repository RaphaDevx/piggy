package com.scandit.datacapture.barcode.internal.module.ui.popover;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.ui.popover.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0485g extends LinearLayout {
    public static final Lazy d = LazyKt.lazy(C0479a.a);
    public static final Lazy e = LazyKt.lazy(C0480b.a);
    public static final Lazy f = LazyKt.lazy(C0481c.a);
    public static final Lazy g = LazyKt.lazy(C0483e.a);
    public static final Lazy h = LazyKt.lazy(C0482d.a);
    public final Function0 a;
    public final ImageView b;
    public final TextView c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0485g(Context context, C0489k onClick) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        this.a = onClick;
        ImageView imageView = new ImageView(context);
        this.b = imageView;
        TextView textView = new TextView(context);
        this.c = textView;
        setOrientation(1);
        setGravity(16);
        textView.setMaxLines(2);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(1);
        Lazy lazy = e;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
        layoutParams.gravity = 1;
        Unit unit = Unit.INSTANCE;
        addView(imageView, layoutParams);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = ((Number) f.getValue()).intValue();
        Lazy lazy2 = h;
        layoutParams2.setMarginStart(((Number) lazy2.getValue()).intValue());
        layoutParams2.setMarginEnd(((Number) lazy2.getValue()).intValue());
        addView(textView, layoutParams2);
    }

    public final void a(LinearPopoverButtonViewSettings buttonSettings) {
        Intrinsics.checkNotNullParameter(buttonSettings, "buttonSettings");
        TextView textView = this.c;
        textView.setTextColor(buttonSettings.f());
        textView.setTypeface(buttonSettings.i());
        textView.setTextSize(2, buttonSettings.g());
        this.b.setImageDrawable(buttonSettings.d());
        String value = buttonSettings.h();
        Intrinsics.checkNotNullParameter(value, "value");
        this.c.setText(value);
        setContentDescription(buttonSettings.b());
        if (buttonSettings.b() == null) {
            setImportantForAccessibility(2);
        }
        Integer a = buttonSettings.a();
        if (a != null) {
            setBackgroundColor(a.intValue());
        }
        setEnabled(buttonSettings.c());
        setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.ui.popover.g$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0485g.a(C0485g.this, view);
            }
        });
        setClickable(buttonSettings.j());
        if (buttonSettings.j()) {
            ViewExtensionsKt.addRippleForeground$default(this, null, 1, null);
        } else {
            setForeground(null);
        }
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(z ? 1.0f : 0.3f);
    }

    public static final void a(C0485g this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.a.invoke();
    }
}
