package com.scandit.datacapture.barcode.internal.module.ui.viewpositioner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Segment;
import com.scandit.datacapture.core.internal.sdk.extensions.PointExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b implements d {
    public final Function0 a;
    public final Function0 b;
    public final Function0 c;

    public b(Function0 referenceQuadGetter, Function0 anchorGetter, Function0 sizeGetter) {
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
        Point point;
        FrameLayout parent = (FrameLayout) viewGroup;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(parent, "parent");
        Quadrilateral quadrilateral = (Quadrilateral) this.a.invoke();
        if (quadrilateral == null) {
            return;
        }
        Anchor anchor = (Anchor) this.b.invoke();
        Size2 size2 = (Size2) this.c.invoke();
        if (size2 == null) {
            Intrinsics.checkNotNullParameter(view, "view");
            view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            size2 = new Size2(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
        int[] iArr = a.a;
        int i = iArr[anchor.ordinal()];
        if (i == 1) {
            Point bottomLeft = quadrilateral.getBottomLeft();
            Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
            Point bottomRight = quadrilateral.getBottomRight();
            Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
            segment = new Segment(bottomLeft, bottomRight);
        } else if (i == 2) {
            Point topLeft = quadrilateral.getTopLeft();
            Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
            Point topRight = quadrilateral.getTopRight();
            Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
            segment = new Segment(topLeft, topRight);
        } else if (i == 3) {
            Point bottomRight2 = quadrilateral.getBottomRight();
            Intrinsics.checkNotNullExpressionValue(bottomRight2, "getBottomRight(...)");
            Point topRight2 = quadrilateral.getTopRight();
            Intrinsics.checkNotNullExpressionValue(topRight2, "getTopRight(...)");
            segment = new Segment(bottomRight2, topRight2);
        } else {
            if (i != 4) {
                throw new IllegalArgumentException("Unsupported anchor: " + anchor);
            }
            Point bottomLeft2 = quadrilateral.getBottomLeft();
            Intrinsics.checkNotNullExpressionValue(bottomLeft2, "getBottomLeft(...)");
            Point topLeft2 = quadrilateral.getTopLeft();
            Intrinsics.checkNotNullExpressionValue(topLeft2, "getTopLeft(...)");
            segment = new Segment(bottomLeft2, topLeft2);
        }
        int i2 = iArr[anchor.ordinal()];
        if (i2 == 1) {
            point = new Point(size2.getWidth() / 2, 0.0f);
        } else if (i2 == 2) {
            point = new Point(size2.getWidth() / 2, size2.getHeight());
        } else if (i2 == 3) {
            point = new Point(0.0f, size2.getHeight() / 2);
        } else {
            if (i2 != 4) {
                throw new IllegalArgumentException("Unsupported anchor: " + anchor);
            }
            point = new Point(size2.getWidth(), size2.getHeight() / 2);
        }
        Point minus = PointExtensionsKt.minus(segment.getCenter(), point);
        view.setX(minus.getX());
        view.setY(minus.getY());
    }
}
