package com.scandit.datacapture.barcode.internal.module.spark.ui.toast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.spark.ui.E;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class w extends FrameLayout {
    public final TextView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        View.inflate(context, R.layout.sc_spark_scan_toast_view, this);
        float[] fArr = new float[8];
        for (int i = 0; i < 8; i++) {
            int i2 = E.a;
            fArr[i] = ((Number) E.y.getValue()).intValue();
        }
        setBackground(new ShapeDrawable(new RoundRectShape(fArr, null, null)));
        setVisibility(4);
        View findViewById = findViewById(R.id.toastText);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.a = (TextView) findViewById;
    }

    public final void a(String text, int i, int i2) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.a.setText(text);
        Drawable background = getBackground();
        Intrinsics.checkNotNull(background, "null cannot be cast to non-null type android.graphics.drawable.ShapeDrawable");
        ((ShapeDrawable) background).getPaint().setColor(i);
        this.a.setTextColor(i2);
        if (getVisibility() == 4) {
            setAlpha(0.0f);
            setVisibility(0);
            a(1.0f, new v(this));
        } else {
            animate().cancel();
            setAlpha(1.0f);
            setVisibility(0);
        }
    }

    public final void a(float f, Function0 function0) {
        animate().alpha(f).setListener(new t(function0));
    }
}
