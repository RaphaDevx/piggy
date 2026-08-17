package com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow;

import android.content.Context;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class m extends FrameLayout {
    public static final Lazy c = LazyKt.lazy(l.a);
    public static final Lazy d = LazyKt.lazy(k.a);
    public final View a;
    public final TextView b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setBackgroundColor(Integer.MIN_VALUE);
        View inflate = LayoutInflater.from(context).inflate(R.layout.sc_barcode_count_grid_step_back_guidance, (ViewGroup) this, false);
        addView(inflate, new FrameLayout.LayoutParams(((Number) c.getValue()).intValue(), ((Number) d.getValue()).intValue()));
        this.a = inflate;
        this.b = (TextView) findViewById(R.id.sc_textview);
    }

    public final void a(Size previewSize, int i) {
        Intrinsics.checkNotNullParameter(previewSize, "previewSize");
        View view = this.a;
        Intrinsics.checkNotNullExpressionValue(view, "subview");
        Size viewDesiredSize = new Size(((Number) c.getValue()).intValue(), ((Number) d.getValue()).intValue());
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(viewDesiredSize, "viewDesiredSize");
        Intrinsics.checkNotNullParameter(previewSize, "previewSize");
        float coerceIn = RangesKt.coerceIn(i / viewDesiredSize.getHeight(), 0.0f, 1.0f);
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setScaleX(coerceIn);
        view.setScaleY(coerceIn);
        view.setY(((previewSize.getHeight() - (viewDesiredSize.getHeight() * coerceIn)) / 2.0f) + (-RangesKt.coerceAtLeast((previewSize.getHeight() - i) / 2.0f, 0.0f)));
        view.setX((previewSize.getWidth() - (viewDesiredSize.getWidth() * coerceIn)) / 2.0f);
    }
}
