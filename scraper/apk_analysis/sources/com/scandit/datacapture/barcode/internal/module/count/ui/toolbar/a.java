package com.scandit.datacapture.barcode.internal.module.count.ui.toolbar;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a extends LinearLayout {
    public static final int f = Color.parseColor("#FBC02C");
    public static final int g = Color.parseColor("#FFFFFF");
    public final ImageView a;
    public final TextView b;
    public String c;
    public String d;
    public boolean e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.c = "";
        this.d = "";
        View inflate = View.inflate(context, R.layout.sc_barcode_count_toolbar_item, this);
        View findViewById = inflate.findViewById(R.id.icon);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.a = (ImageView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.title);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.b = (TextView) findViewById2;
    }

    public final void a() {
        if (this.e) {
            this.a.setSelected(true);
            ImageView imageView = this.a;
            int i = f;
            imageView.setColorFilter(i);
            this.b.setTextColor(i);
            this.b.setText(this.c);
            return;
        }
        this.a.setSelected(false);
        ImageView imageView2 = this.a;
        int i2 = g;
        imageView2.setColorFilter(i2);
        this.b.setTextColor(i2);
        this.b.setText(this.d);
    }
}
