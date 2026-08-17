package com.scandit.datacapture.barcode.internal.module.ui.shutterbutton;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.ui.animation.PulsingView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f extends FrameLayout implements Animatable {
    public static final Lazy b = LazyKt.lazy(e.a);
    public final PulsingView a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Context context) {
        super(context, null, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        setForeground(context.getDrawable(R.drawable.sc_button_shutter_play_pause));
        setClipChildren(false);
        int intValue = ((Number) b.getValue()).intValue();
        int intrinsicWidth = getForeground().getIntrinsicWidth();
        Intrinsics.checkNotNullParameter(this, "container");
        int[] gradientColors = {com.scandit.datacapture.barcode.internal.module.extensions.a.a(-1, 0), com.scandit.datacapture.barcode.internal.module.extensions.a.a(-1, 0), -1};
        Intrinsics.checkNotNullParameter(this, "container");
        Intrinsics.checkNotNullParameter(gradientColors, "gradientColors");
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        PulsingView pulsingView = new PulsingView(context2, intValue, intrinsicWidth, gradientColors);
        addView(pulsingView, 0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(intValue, intValue);
        layoutParams.gravity = 17;
        pulsingView.setLayoutParams(layoutParams);
        this.a = pulsingView;
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.a.isRunning();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(getForeground().getIntrinsicWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(getForeground().getIntrinsicHeight(), BasicMeasure.EXACTLY));
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        this.a.start();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        this.a.stop();
    }
}
