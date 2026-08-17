package com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class e extends FrameLayout {
    public static final int g = PixelExtensionsKt.pxFromDp$default(-20, (Context) null, 1, (Object) null);
    public static final int h = PixelExtensionsKt.pxFromDp$default(-8, (Context) null, 1, (Object) null);
    public static final int i = PixelExtensionsKt.pxFromDp$default(4, (Context) null, 1, (Object) null);
    public static final int j = PixelExtensionsKt.pxFromDp$default(28, (Context) null, 1, (Object) null);
    public static final int k = PixelExtensionsKt.pxFromDp$default(320, (Context) null, 1, (Object) null);
    public static final int l = PixelExtensionsKt.pxFromDp$default(460, (Context) null, 1, (Object) null);
    public static final int m = Integer.MIN_VALUE;
    public int a;
    public final View b;
    public final TextView c;
    public final ImageView d;
    public final List e;
    public ValueAnimator f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = -1;
        setBackgroundColor(m);
        View inflate = LayoutInflater.from(context).inflate(R.layout.sc_barcode_count_grid_splash, (ViewGroup) this, false);
        addView(inflate, new FrameLayout.LayoutParams(k, l));
        this.b = inflate;
        this.c = (TextView) findViewById(R.id.sc_textview);
        View findViewById = findViewById(R.id.sc_phone);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.d = (ImageView) findViewById;
        List listOf = CollectionsKt.listOf((Object[]) new b[]{new b(6, Integer.valueOf(R.id.sc_grid)), new b(5, Integer.valueOf(R.id.sc_grid))});
        int i2 = h;
        int i3 = g;
        c cVar = new c(listOf, new Rect(i2, i3, 0, 0));
        c cVar2 = new c(CollectionsKt.listOf((Object[]) new b[]{new b(6, Integer.valueOf(R.id.sc_grid)), new b(7, Integer.valueOf(R.id.sc_grid))}), new Rect(0, i3, i2, 0));
        List listOf2 = CollectionsKt.listOf((Object[]) new b[]{new b(), new b(5, Integer.valueOf(R.id.sc_grid))});
        int i4 = j;
        int i5 = i;
        this.e = CollectionsKt.listOf((Object[]) new c[]{cVar, cVar2, new c(listOf2, new Rect(i4, i5, 0, 0)), new c(CollectionsKt.listOf((Object[]) new b[]{new b(), new b(7, Integer.valueOf(R.id.sc_grid))}), new Rect(0, i5, i4, 0)), new c(CollectionsKt.listOf((Object[]) new b[]{new b(8, Integer.valueOf(R.id.sc_grid)), new b(5, Integer.valueOf(R.id.sc_grid))}), new Rect(i2, 0, 0, i3)), new c(CollectionsKt.listOf((Object[]) new b[]{new b(8, Integer.valueOf(R.id.sc_grid)), new b(7, Integer.valueOf(R.id.sc_grid))}), new Rect(0, 0, i2, i3))});
        a(0);
    }

    public final void a(int i2) {
        if (this.a == i2 || i2 >= this.e.size()) {
            return;
        }
        this.a = i2;
        c cVar = (c) this.e.get(i2);
        Rect rect = cVar.b;
        ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        ViewExtensionsKt.removeAllRules(layoutParams2);
        layoutParams2.setMargins(rect.left, rect.top, rect.right, rect.bottom);
        for (b bVar : cVar.a) {
            Integer num = bVar.b;
            if (num == null) {
                layoutParams2.addRule(bVar.a);
            } else {
                layoutParams2.addRule(bVar.a, num.intValue());
            }
        }
        this.d.setLayoutParams(layoutParams2);
    }

    public final void a(com.scandit.datacapture.barcode.internal.module.count.ui.mode.c onFinish) {
        Intrinsics.checkNotNullParameter(onFinish, "onFinish");
        a();
        a(0);
        int size = this.e.size() + 1;
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = i2;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(Arrays.copyOf(iArr, size));
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow.e$$ExternalSyntheticLambda0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                e.a(e.this, valueAnimator);
            }
        });
        ofInt.setDuration(5000L);
        ofInt.setInterpolator(new LinearInterpolator());
        ofInt.addListener(new d(onFinish));
        ofInt.start();
        this.f = ofInt;
    }

    public static final void a(e this$0, ValueAnimator it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Object animatedValue = it.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        this$0.a(((Integer) animatedValue).intValue());
    }

    public final void a() {
        ValueAnimator valueAnimator = this.f;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        this.f = null;
    }

    public final void a(Size previewSize, int i2) {
        Intrinsics.checkNotNullParameter(previewSize, "previewSize");
        View view = this.b;
        Intrinsics.checkNotNullExpressionValue(view, "subview");
        Size viewDesiredSize = new Size(k, l);
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(viewDesiredSize, "viewDesiredSize");
        Intrinsics.checkNotNullParameter(previewSize, "previewSize");
        float coerceIn = RangesKt.coerceIn(i2 / viewDesiredSize.getHeight(), 0.0f, 1.0f);
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setScaleX(coerceIn);
        view.setScaleY(coerceIn);
        view.setY(((previewSize.getHeight() - (viewDesiredSize.getHeight() * coerceIn)) / 2.0f) + (-RangesKt.coerceAtLeast((previewSize.getHeight() - i2) / 2.0f, 0.0f)));
        view.setX((previewSize.getWidth() - (viewDesiredSize.getWidth() * coerceIn)) / 2.0f);
    }
}
