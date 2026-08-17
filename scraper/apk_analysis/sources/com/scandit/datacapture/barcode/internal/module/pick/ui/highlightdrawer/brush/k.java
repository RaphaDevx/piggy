package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import android.graphics.Path;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Vector;
import com.scandit.datacapture.core.internal.sdk.extensions.PointExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class k {
    public static final Quadrilateral a(Quadrilateral quadrilateral, Size2 minSize) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Intrinsics.checkNotNullParameter(minSize, "minSize");
        Vector vector = new Vector(QuadrilateralUtilsKt.getCenterLeft(quadrilateral), QuadrilateralUtilsKt.getCenterRight(quadrilateral));
        Vector vector2 = new Vector(QuadrilateralUtilsKt.getTopCenter(quadrilateral), QuadrilateralUtilsKt.getBottomCenter(quadrilateral));
        float f = vector.getIo.sentry.SentryEnvelopeItemHeader.JsonKeys.LENGTH java.lang.String();
        float f2 = vector2.getIo.sentry.SentryEnvelopeItemHeader.JsonKeys.LENGTH java.lang.String();
        if (f >= minSize.getWidth() && f2 >= minSize.getHeight()) {
            return quadrilateral;
        }
        float max = Math.max(f, minSize.getWidth());
        float max2 = Math.max(f2, minSize.getHeight());
        Quadrilateral Quadrilateral = QuadrilateralUtilsKt.Quadrilateral(PointExtensionsKt.plus(QuadrilateralUtilsKt.getCenter(quadrilateral), new Point((-max) / 2.0f, (-max2) / 2.0f)), new Size2(max, max2));
        return QuadrilateralUtilsKt.rotatedByVector(Quadrilateral, QuadrilateralUtilsKt.getCenter(Quadrilateral), vector);
    }

    public static final Path a(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Path path = new Path();
        path.moveTo(quadrilateral.getTopLeft().getX(), quadrilateral.getTopLeft().getY());
        path.lineTo(quadrilateral.getTopRight().getX(), quadrilateral.getTopRight().getY());
        path.lineTo(quadrilateral.getBottomRight().getX(), quadrilateral.getBottomRight().getY());
        path.lineTo(quadrilateral.getBottomLeft().getX(), quadrilateral.getBottomLeft().getY());
        path.close();
        return path;
    }
}
