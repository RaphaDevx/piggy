package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer;

import android.graphics.RectF;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import kotlin.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class n {
    public static final Quadrilateral a(Quadrilateral quadrilateral, BarcodePickViewHighlightStyle style, Size2 minSize) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(minSize, "minSize");
        if (style instanceof BarcodePickViewHighlightStyle.Rectangular) {
            return com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.k.a(quadrilateral, minSize);
        }
        if (style instanceof BarcodePickViewHighlightStyle.RectangularWithIcons) {
            return com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.k.a(quadrilateral, minSize);
        }
        if (!(style instanceof BarcodePickViewHighlightStyle.Dot) && !(style instanceof BarcodePickViewHighlightStyle.DotWithIcons)) {
            if (!(style instanceof BarcodePickViewHighlightStyle.CustomView)) {
                throw new NoWhenBranchMatchedException();
            }
            Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
            RectF rectF = new RectF(ComparisonsKt.minOf(quadrilateral.getTopLeft().getX(), quadrilateral.getTopRight().getX(), quadrilateral.getBottomRight().getX(), quadrilateral.getBottomLeft().getX()), ComparisonsKt.minOf(quadrilateral.getTopLeft().getY(), quadrilateral.getTopRight().getY(), quadrilateral.getBottomRight().getY(), quadrilateral.getBottomLeft().getY()), ComparisonsKt.maxOf(quadrilateral.getTopLeft().getX(), quadrilateral.getTopRight().getX(), quadrilateral.getBottomRight().getX(), quadrilateral.getBottomLeft().getX()), ComparisonsKt.maxOf(quadrilateral.getTopLeft().getY(), quadrilateral.getTopRight().getY(), quadrilateral.getBottomRight().getY(), quadrilateral.getBottomLeft().getY()));
            float height = rectF.height();
            float width = rectF.width();
            if (width > minSize.getWidth() && height > minSize.getHeight()) {
                return QuadrilateralUtilsKt.Quadrilateral(rectF);
            }
            float f = 2;
            rectF.inset(-(Math.max(0.0f, minSize.getWidth() - width) / f), -(Math.max(0.0f, minSize.getHeight() - height) / f));
            return QuadrilateralUtilsKt.Quadrilateral(rectF);
        }
        return a(quadrilateral);
    }

    public static final Quadrilateral a(Quadrilateral quadrilateral) {
        Point center = QuadrilateralUtilsKt.getCenter(quadrilateral);
        float x = center.getX();
        Lazy lazy = k.a;
        float floatValue = x - ((Number) lazy.getValue()).floatValue();
        float floatValue2 = ((Number) lazy.getValue()).floatValue() + center.getX();
        float y = center.getY() - ((Number) lazy.getValue()).floatValue();
        float floatValue3 = ((Number) lazy.getValue()).floatValue() + center.getY();
        return new Quadrilateral(new Point(floatValue, y), new Point(floatValue2, y), new Point(floatValue2, floatValue3), new Point(floatValue, floatValue3));
    }
}
