package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.module.ui.animation.PulsingView;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class n extends FrameLayout {
    public static final /* synthetic */ int e = 0;
    public final l a;
    public final i b;
    public final ImageView c;
    public final PulsingView d;

    public n(Context context, int i, int i2, l lVar) {
        super(context);
        this.a = lVar;
        this.b = new i();
        int pxFromDp$default = PixelExtensionsKt.pxFromDp$default(i2, (Context) null, 1, (Object) null);
        int pxFromDp$default2 = PixelExtensionsKt.pxFromDp$default(i, (Context) null, 1, (Object) null);
        setClickable(true);
        setFocusable(true);
        Intrinsics.checkNotNullParameter(this, "container");
        int[] gradientColors = {com.scandit.datacapture.barcode.internal.module.extensions.a.a(-1, 0), com.scandit.datacapture.barcode.internal.module.extensions.a.a(-1, 0), -1};
        Intrinsics.checkNotNullParameter(this, "container");
        Intrinsics.checkNotNullParameter(gradientColors, "gradientColors");
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        PulsingView pulsingView = new PulsingView(context2, pxFromDp$default, pxFromDp$default2, gradientColors);
        addView(pulsingView, 0);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(pxFromDp$default, pxFromDp$default);
        layoutParams.gravity = 17;
        pulsingView.setLayoutParams(layoutParams);
        this.d = pulsingView;
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.sc_button_shutter);
        imageView.setAdjustViewBounds(true);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(pxFromDp$default2, pxFromDp$default2);
        layoutParams2.gravity = 17;
        addView(imageView, layoutParams2);
        this.c = imageView;
        addOnAttachStateChangeListener(new m(this));
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        super.setEnabled(z);
        setClickable(z);
        setFocusable(z);
        this.c.setAlpha(z ? 1.0f : 0.5f);
    }
}
