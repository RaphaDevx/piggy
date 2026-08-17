package com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.f;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.extensions.ContextExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.FloatWithUnitExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.utils.ObjectOverlayUtilsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class d implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    public static final Lazy b = LazyKt.lazy(a.a);
    public static final Lazy c = LazyKt.lazy(c.a);
    public static final Lazy d = LazyKt.lazy(b.a);
    public final FloatWithUnit a;

    public d(FloatWithUnit offset) {
        Intrinsics.checkNotNullParameter(offset, "offset");
        this.a = offset;
    }

    public final int a(Context context, int i, boolean z) {
        int i2;
        int intValue;
        if (i == 0) {
            return (int) FloatWithUnitExtensionsKt.toPixels((FloatWithUnit) b.getValue(), 0.0f, ContextExtensionsKt.getDisplayDensity(context));
        }
        int coerceAtLeast = RangesKt.coerceAtLeast((int) FloatWithUnitExtensionsKt.toPixels(this.a, i, ContextExtensionsKt.getDisplayDensity(context)), 0);
        if (z) {
            i2 = i / 2;
            intValue = ((Number) c.getValue()).intValue();
        } else {
            i2 = i / 2;
            intValue = ((Number) d.getValue()).intValue();
        }
        return RangesKt.coerceAtMost(coerceAtLeast, i2 - intValue);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    public final void a(View view, ViewGroup viewGroup) {
        f view2 = (f) view;
        RelativeLayout parent = (RelativeLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Context context = view2.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Size2 size = ObjectOverlayUtilsKt.getSize(parent);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (size.getHeight() >= size.getWidth()) {
            layoutParams.addRule(12);
            layoutParams.addRule(14);
            layoutParams.setMargins(0, 0, 0, a(context, MathKt.roundToInt(Math.max(size.getWidth(), size.getHeight())), true));
        } else {
            layoutParams.addRule(21);
            layoutParams.addRule(15);
            layoutParams.setMargins(0, 0, a(context, MathKt.roundToInt(Math.max(size.getWidth(), size.getHeight())), false), 0);
        }
        view2.setLayoutParams(layoutParams);
    }
}
