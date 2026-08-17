package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class k extends FrameLayout {
    public final j a;
    public final TextView b;
    public boolean c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        j jVar = new j(context, null, 0);
        jVar.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        this.a = jVar;
        addView(jVar);
        TextView textView = new TextView(context, null, 0);
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
        b.a(textView);
        this.b = textView;
        addView(textView);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.c) {
            j.a(this.a);
        }
    }
}
