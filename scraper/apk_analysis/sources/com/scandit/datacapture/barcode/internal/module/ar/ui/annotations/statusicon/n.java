package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.statusicon;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Segment;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class n implements com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d {
    public final Function0 a;
    public final Function0 b;
    public final Function0 c;

    public n(p referenceQuadGetter, q anchorGetter, r sizeGetter) {
        Intrinsics.checkNotNullParameter(referenceQuadGetter, "referenceQuadGetter");
        Intrinsics.checkNotNullParameter(anchorGetter, "anchorGetter");
        Intrinsics.checkNotNullParameter(sizeGetter, "sizeGetter");
        this.a = referenceQuadGetter;
        this.b = anchorGetter;
        this.c = sizeGetter;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.d
    public final void a(View view, ViewGroup viewGroup) {
        Segment segment;
        Pair pair;
        s view2 = (s) view;
        FrameLayout parent = (FrameLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Quadrilateral quadrilateral = (Quadrilateral) this.a.invoke();
        if (quadrilateral == null) {
            return;
        }
        Anchor anchor = (Anchor) this.b.invoke();
        Size2 size2 = (Size2) this.c.invoke();
        if (size2 == null) {
            Intrinsics.checkNotNullParameter(view2, "view");
            view2.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            size2 = new Size2(view2.getMeasuredWidth(), view2.getMeasuredHeight());
        }
        int[] iArr = m.a;
        int i = iArr[anchor.ordinal()];
        if (i == 1) {
            Point topLeft = quadrilateral.getTopLeft();
            Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
            Point topRight = quadrilateral.getTopRight();
            Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
            segment = new Segment(topLeft, topRight);
        } else if (i == 2) {
            Point bottomLeft = quadrilateral.getBottomLeft();
            Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
            Point bottomRight = quadrilateral.getBottomRight();
            Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
            segment = new Segment(bottomLeft, bottomRight);
        } else if (i == 3) {
            Point topLeft2 = quadrilateral.getTopLeft();
            Intrinsics.checkNotNullExpressionValue(topLeft2, "getTopLeft(...)");
            Point bottomLeft2 = quadrilateral.getBottomLeft();
            Intrinsics.checkNotNullExpressionValue(bottomLeft2, "getBottomLeft(...)");
            segment = new Segment(topLeft2, bottomLeft2);
        } else {
            if (i != 4) {
                throw new IllegalArgumentException("Unsupported anchor: " + anchor);
            }
            Point topRight2 = quadrilateral.getTopRight();
            Intrinsics.checkNotNullExpressionValue(topRight2, "getTopRight(...)");
            Point bottomRight2 = quadrilateral.getBottomRight();
            Intrinsics.checkNotNullExpressionValue(bottomRight2, "getBottomRight(...)");
            segment = new Segment(topRight2, bottomRight2);
        }
        Point center = segment.getCenter();
        int i2 = iArr[anchor.ordinal()];
        if (i2 == 1) {
            pair = TuplesKt.to(Float.valueOf(center.getX() - ((Number) l.e.getValue()).floatValue()), Float.valueOf(center.getY() - size2.getHeight()));
        } else if (i2 == 2) {
            pair = TuplesKt.to(Float.valueOf(center.getX() - ((Number) l.e.getValue()).floatValue()), Float.valueOf(center.getY()));
        } else if (i2 == 3) {
            pair = TuplesKt.to(Float.valueOf(center.getX() - size2.getWidth()), Float.valueOf(center.getY() - (size2.getHeight() / 2)));
        } else {
            if (i2 != 4) {
                throw new IllegalArgumentException("Unsupported anchor: " + anchor);
            }
            pair = TuplesKt.to(Float.valueOf(center.getX()), Float.valueOf(center.getY() - (size2.getHeight() / 2)));
        }
        float floatValue = ((Number) pair.component1()).floatValue();
        float floatValue2 = ((Number) pair.component2()).floatValue();
        view2.setX(floatValue);
        view2.setY(floatValue2);
        if (anchor == Anchor.TOP_CENTER || anchor == Anchor.BOTTOM_CENTER) {
            view2.a.setX((center.getX() - floatValue) - ((Number) l.f.getValue()).floatValue());
        }
    }
}
