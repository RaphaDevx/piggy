package com.scandit.datacapture.core.internal.module.ui.control.layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.extensions.AnchorExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.ContextExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.FloatWithUnitExtensionsKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d implements c {
    public static final Anchor[] a;
    public static final Anchor[] b;
    public static final Anchor[] c;
    public static final Anchor[] d;

    static {
        Anchor anchor = Anchor.TOP_LEFT;
        Anchor anchor2 = Anchor.TOP_CENTER;
        Anchor anchor3 = Anchor.CENTER_LEFT;
        Anchor anchor4 = Anchor.CENTER;
        Anchor anchor5 = Anchor.BOTTOM_LEFT;
        Anchor anchor6 = Anchor.BOTTOM_CENTER;
        a = new Anchor[]{anchor, anchor2, anchor3, anchor4, anchor5, anchor6};
        Anchor anchor7 = Anchor.TOP_RIGHT;
        Anchor anchor8 = Anchor.CENTER_RIGHT;
        Anchor anchor9 = Anchor.BOTTOM_RIGHT;
        b = new Anchor[]{anchor7, anchor8, anchor9};
        c = new Anchor[]{anchor7, anchor2, anchor};
        d = new Anchor[]{anchor3, anchor4, anchor8, anchor5, anchor6, anchor9};
    }

    public final void a(View view, Anchor anchor, PointWithUnit offset, Size2 containerSize) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        Intrinsics.checkNotNullParameter(offset, "offset");
        Intrinsics.checkNotNullParameter(containerSize, "containerSize");
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        float displayDensity = ContextExtensionsKt.getDisplayDensity(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(view.getLayoutParams());
        layoutParams.gravity = AnchorExtensionsKt.toGravity(anchor);
        FloatWithUnit x = offset.getX();
        Intrinsics.checkNotNullExpressionValue(x, "getX(...)");
        float width = containerSize.getWidth();
        if (ArraysKt.contains(a, anchor)) {
            layoutParams.setMarginStart((int) FloatWithUnitExtensionsKt.toPixels(x, width, displayDensity));
        }
        FloatWithUnit x2 = offset.getX();
        Intrinsics.checkNotNullExpressionValue(x2, "getX(...)");
        float width2 = containerSize.getWidth();
        if (ArraysKt.contains(b, anchor)) {
            layoutParams.setMarginEnd((int) FloatWithUnitExtensionsKt.toPixels(x2, width2, displayDensity));
        }
        FloatWithUnit y = offset.getY();
        Intrinsics.checkNotNullExpressionValue(y, "getY(...)");
        float height = containerSize.getHeight();
        if (ArraysKt.contains(c, anchor)) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = (int) FloatWithUnitExtensionsKt.toPixels(y, height, displayDensity);
        }
        FloatWithUnit y2 = offset.getY();
        Intrinsics.checkNotNullExpressionValue(y2, "getY(...)");
        float height2 = containerSize.getHeight();
        if (ArraysKt.contains(d, anchor)) {
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = (int) FloatWithUnitExtensionsKt.toPixels(y2, height2, displayDensity);
        }
        view.setLayoutParams(layoutParams);
        view.invalidate();
    }
}
