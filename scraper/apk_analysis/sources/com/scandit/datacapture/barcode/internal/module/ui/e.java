package com.scandit.datacapture.barcode.internal.module.ui;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import java.util.Arrays;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* loaded from: classes2.dex */
public final class e extends LinearLayout {
    public static final Lazy g = LazyKt.lazy(c.a);
    public int a;
    public int b;
    public int c;
    public int d;
    public final ProgressBar e;
    public final TextView f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        this.c = -449920562;
        this.d = -443031322;
        setOrientation(0);
        setGravity(16);
        ProgressBar progressBar = new ProgressBar(context, null, R.attr.progressBarStyleHorizontal);
        progressBar.setIndeterminate(false);
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(-1, PixelExtensionsKt.pxFromDp$default(7, (Context) null, 1, (Object) null), 1.0f));
        progressBar.setProgressDrawable(a());
        progressBar.setImportantForAccessibility(2);
        addView(progressBar);
        this.e = progressBar;
        TextView textView = new TextView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(PixelExtensionsKt.pxFromDp$default(36, (Context) null, 1, (Object) null), -2);
        layoutParams.leftMargin = PixelExtensionsKt.pxFromDp$default(8, (Context) null, 1, (Object) null);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(GravityCompat.END);
        textView.setTextColor(-1);
        textView.setTextSize(1, 12.0f);
        textView.setImportantForAccessibility(2);
        addView(textView);
        this.f = textView;
    }

    public final LayerDrawable a() {
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        float[] fArr = new float[8];
        for (int i = 0; i < 8; i++) {
            fArr[i] = ((Number) g.getValue()).floatValue();
        }
        shapeDrawable.setShape(new RoundRectShape(fArr, null, null));
        shapeDrawable.getPaint().setColor(872415231);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{this.c, this.d});
        gradientDrawable.setCornerRadius(((Number) g.getValue()).floatValue());
        gradientDrawable.setGradientType(0);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shapeDrawable, new ScaleDrawable(gradientDrawable, GravityCompat.START, 1.0f, -1.0f)});
        layerDrawable.setId(0, R.id.background);
        layerDrawable.setId(1, R.id.progress);
        return layerDrawable;
    }

    public final void b() {
        this.e.setProgressDrawable(a());
        this.e.setMax(this.b);
        this.e.setProgress(this.a, true);
        TextView textView = this.f;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getContext().getString(com.scandit.datacapture.barcode.R.string.sc_progressbar_count_format);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String format = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(this.a), Integer.valueOf(this.b)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        textView.setText(format);
        setContentDescription(getResources().getString(com.scandit.datacapture.barcode.R.string.sc_progress_bar_content_description, Integer.valueOf(this.a), Integer.valueOf(this.b)));
    }
}
