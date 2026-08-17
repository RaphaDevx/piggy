package com.scandit.datacapture.core.internal.sdk.common.geometry;

import android.graphics.Rect;
import android.graphics.RectF;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.module.common.geometry.NativeRectToQuadConverter;
import com.scandit.datacapture.core.internal.sdk.extensions.PointExtensionsKt;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\u001a!\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006\u001a!\u0010\t\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\n\u001a!\u0010\r\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000e\u001a\u0019\u0010\u0011\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0019\u0010\u0015\u001a\u00020\u0014*\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u0019\u0010\u0017\u001a\u00020\u000f*\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0001¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0011\u0010\u0019\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b\u001b\u0010\u001a\u001a\u0015\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001d\u0010\u001e\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u00012\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b\u001e\u0010#\u001a\u0011\u0010%\u001a\u00020$*\u00020\u0000¢\u0006\u0004\b%\u0010&\u001a\u0019\u0010(\u001a\u00020\u0000*\u00020\u00002\u0006\u0010'\u001a\u00020\u000f¢\u0006\u0004\b(\u0010\u0012\u001a\u0011\u0010)\u001a\u00020\u0000*\u00020\u0000¢\u0006\u0004\b)\u0010\u001a\u001a\u0019\u0010*\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u000f¢\u0006\u0004\b*\u0010\u0012\u001a\u0011\u0010,\u001a\u00020+*\u00020\u0000¢\u0006\u0004\b,\u0010-\"\u0017\u00102\u001a\u00020\u00008\u0006¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0015\u00105\u001a\u00020\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b3\u00104\"\u0015\u00108\u001a\u00020\u000f*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b6\u00107\"\u0015\u0010:\u001a\u00020\u000f*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b9\u00107\"\u0015\u0010<\u001a\u00020\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b;\u00104\"\u0015\u0010>\u001a\u00020\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b=\u00104\"\u0015\u0010@\u001a\u00020\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\b?\u00104\"\u0015\u0010B\u001a\u00020\u0001*\u00020\u00008F¢\u0006\u0006\u001a\u0004\bA\u00104¨\u0006C"}, d2 = {"Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "Lcom/scandit/datacapture/core/common/geometry/Point;", "pivot", "", "degrees", "rotatedDegrees", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/core/common/geometry/Point;I)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "", "radians", "rotatedRadians", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/core/common/geometry/Point;D)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Vector;", "vector", "rotatedByVector", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/core/common/geometry/Point;Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Vector;)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "", "scale", "scaled", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;F)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "point", "", "contains", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/core/common/geometry/Point;)Z", "distance", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;Lcom/scandit/datacapture/core/common/geometry/Point;)F", "boundingBox", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "orderVerticesByPosition", "Landroid/graphics/RectF;", "rectF", "Quadrilateral", "(Landroid/graphics/RectF;)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "origin", "Lcom/scandit/datacapture/core/common/geometry/Size2;", RRWebVideoEvent.JsonKeys.SIZE, "(Lcom/scandit/datacapture/core/common/geometry/Point;Lcom/scandit/datacapture/core/common/geometry/Size2;)Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "Landroid/graphics/Rect;", "boundingBoxRect", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;)Landroid/graphics/Rect;", "scaleFactor", "scaleCentered", "squared", "grow", "Lcom/scandit/datacapture/core/common/geometry/Rect;", "toRect", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;)Lcom/scandit/datacapture/core/common/geometry/Rect;", "a", "Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "getQUADRILATERAL_ZERO", "()Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;", "QUADRILATERAL_ZERO", "getCenter", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;)Lcom/scandit/datacapture/core/common/geometry/Point;", "center", "getWidth", "(Lcom/scandit/datacapture/core/common/geometry/Quadrilateral;)F", "width", "getHeight", "height", "getCenterLeft", "centerLeft", "getCenterRight", "centerRight", "getTopCenter", "topCenter", "getBottomCenter", "bottomCenter", "scandit-capture-core"}, k = 2, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class QuadrilateralUtilsKt {
    private static final Quadrilateral a = new Quadrilateral(PointUtilsKt.getPOINT_ZERO(), PointUtilsKt.getPOINT_ZERO(), PointUtilsKt.getPOINT_ZERO(), PointUtilsKt.getPOINT_ZERO());

    public static final Quadrilateral Quadrilateral(RectF rectF) {
        Intrinsics.checkNotNullParameter(rectF, "rectF");
        return new Quadrilateral(new Point(rectF.left, rectF.top), new Point(rectF.right, rectF.top), new Point(rectF.right, rectF.bottom), new Point(rectF.left, rectF.bottom));
    }

    public static final Quadrilateral boundingBox(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        float minOf = ComparisonsKt.minOf(quadrilateral.getTopLeft().getX(), quadrilateral.getTopRight().getX(), quadrilateral.getBottomRight().getX(), quadrilateral.getBottomLeft().getX());
        float maxOf = ComparisonsKt.maxOf(quadrilateral.getTopLeft().getX(), quadrilateral.getTopRight().getX(), quadrilateral.getBottomRight().getX(), quadrilateral.getBottomLeft().getX());
        float minOf2 = ComparisonsKt.minOf(quadrilateral.getTopLeft().getY(), quadrilateral.getTopRight().getY(), quadrilateral.getBottomRight().getY(), quadrilateral.getBottomLeft().getY());
        float maxOf2 = ComparisonsKt.maxOf(quadrilateral.getTopLeft().getY(), quadrilateral.getTopRight().getY(), quadrilateral.getBottomRight().getY(), quadrilateral.getBottomLeft().getY());
        return new Quadrilateral(new Point(minOf, minOf2), new Point(maxOf, minOf2), new Point(maxOf, maxOf2), new Point(minOf, maxOf2));
    }

    public static final Rect boundingBoxRect(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        return new Rect((int) ComparisonsKt.minOf(quadrilateral.getTopLeft().getX(), quadrilateral.getTopRight().getX(), quadrilateral.getBottomRight().getX(), quadrilateral.getBottomLeft().getX()), (int) ComparisonsKt.minOf(quadrilateral.getTopLeft().getY(), quadrilateral.getTopRight().getY(), quadrilateral.getBottomRight().getY(), quadrilateral.getBottomLeft().getY()), (int) ComparisonsKt.maxOf(quadrilateral.getTopLeft().getX(), quadrilateral.getTopRight().getX(), quadrilateral.getBottomRight().getX(), quadrilateral.getBottomLeft().getX()), (int) ComparisonsKt.maxOf(quadrilateral.getTopLeft().getY(), quadrilateral.getTopRight().getY(), quadrilateral.getBottomRight().getY(), quadrilateral.getBottomLeft().getY()));
    }

    public static final boolean contains(Quadrilateral quadrilateral, Point point) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Intrinsics.checkNotNullParameter(point, "point");
        return NativeQuadrilateralUtils.quadContains(quadrilateral, point);
    }

    public static final float distance(Quadrilateral quadrilateral, Point point) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Intrinsics.checkNotNullParameter(point, "point");
        return NativeQuadrilateralUtils.quadDistance(quadrilateral, point);
    }

    public static final Point getBottomCenter(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        return PointExtensionsKt.div(PointExtensionsKt.plus(bottomLeft, bottomRight), 2.0f);
    }

    public static final Point getCenter(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Point quadGetCenter = NativeQuadrilateralUtils.quadGetCenter(quadrilateral);
        Intrinsics.checkNotNullExpressionValue(quadGetCenter, "quadGetCenter(...)");
        return quadGetCenter;
    }

    public static final Point getCenterLeft(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        return PointExtensionsKt.div(PointExtensionsKt.plus(topLeft, bottomLeft), 2.0f);
    }

    public static final Point getCenterRight(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        return PointExtensionsKt.div(PointExtensionsKt.plus(topRight, bottomRight), 2.0f);
    }

    public static final float getHeight(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        return NativeQuadrilateralUtils.quadGetHeight(quadrilateral);
    }

    public static final Quadrilateral getQUADRILATERAL_ZERO() {
        return a;
    }

    public static final Point getTopCenter(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        return PointExtensionsKt.div(PointExtensionsKt.plus(topLeft, topRight), 2.0f);
    }

    public static final float getWidth(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        return NativeQuadrilateralUtils.quadGetWidth(quadrilateral);
    }

    public static final Quadrilateral grow(Quadrilateral quadrilateral, float f) {
        Point intersectionWithLineOf;
        Point intersectionWithLineOf2;
        Point intersectionWithLineOf3;
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        Segment moveAlongNormal = SegmentKt.moveAlongNormal(new Segment(topLeft, topRight), f);
        Point topRight2 = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight2, "getTopRight(...)");
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        Segment moveAlongNormal2 = SegmentKt.moveAlongNormal(new Segment(topRight2, bottomRight), f);
        Point bottomRight2 = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight2, "getBottomRight(...)");
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        Segment moveAlongNormal3 = SegmentKt.moveAlongNormal(new Segment(bottomRight2, bottomLeft), f);
        Point bottomLeft2 = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft2, "getBottomLeft(...)");
        Point topLeft2 = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft2, "getTopLeft(...)");
        Segment moveAlongNormal4 = SegmentKt.moveAlongNormal(new Segment(bottomLeft2, topLeft2), f);
        Point intersectionWithLineOf4 = SegmentKt.intersectionWithLineOf(moveAlongNormal, moveAlongNormal4);
        return (intersectionWithLineOf4 == null || (intersectionWithLineOf = SegmentKt.intersectionWithLineOf(moveAlongNormal, moveAlongNormal2)) == null || (intersectionWithLineOf2 = SegmentKt.intersectionWithLineOf(moveAlongNormal3, moveAlongNormal2)) == null || (intersectionWithLineOf3 = SegmentKt.intersectionWithLineOf(moveAlongNormal3, moveAlongNormal4)) == null) ? quadrilateral : new Quadrilateral(intersectionWithLineOf4, intersectionWithLineOf, intersectionWithLineOf2, intersectionWithLineOf3);
    }

    public static final Quadrilateral orderVerticesByPosition(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Quadrilateral quadOrderVerticesByPosition = NativeQuadrilateralUtils.quadOrderVerticesByPosition(quadrilateral);
        Intrinsics.checkNotNullExpressionValue(quadOrderVerticesByPosition, "quadOrderVerticesByPosition(...)");
        return quadOrderVerticesByPosition;
    }

    public static final Quadrilateral rotatedByVector(Quadrilateral quadrilateral, Point pivot, Vector vector) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Intrinsics.checkNotNullParameter(pivot, "pivot");
        Intrinsics.checkNotNullParameter(vector, "vector");
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point rotatedByVector = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedByVector(topLeft, pivot, vector);
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        Point rotatedByVector2 = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedByVector(topRight, pivot, vector);
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        Point rotatedByVector3 = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedByVector(bottomRight, pivot, vector);
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        return new Quadrilateral(rotatedByVector, rotatedByVector2, rotatedByVector3, com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedByVector(bottomLeft, pivot, vector));
    }

    public static final Quadrilateral rotatedDegrees(Quadrilateral quadrilateral, Point pivot, int i) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Intrinsics.checkNotNullParameter(pivot, "pivot");
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point rotatedDegrees = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedDegrees(topLeft, pivot, i);
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        Point rotatedDegrees2 = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedDegrees(topRight, pivot, i);
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        Point rotatedDegrees3 = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedDegrees(bottomRight, pivot, i);
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        return new Quadrilateral(rotatedDegrees, rotatedDegrees2, rotatedDegrees3, com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedDegrees(bottomLeft, pivot, i));
    }

    public static final Quadrilateral rotatedRadians(Quadrilateral quadrilateral, Point pivot, double d) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Intrinsics.checkNotNullParameter(pivot, "pivot");
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point rotatedRadians = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedRadians(topLeft, pivot, d);
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        Point rotatedRadians2 = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedRadians(topRight, pivot, d);
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        Point rotatedRadians3 = com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedRadians(bottomRight, pivot, d);
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        return new Quadrilateral(rotatedRadians, rotatedRadians2, rotatedRadians3, com.scandit.datacapture.core.common.geometry.PointUtilsKt.rotatedRadians(bottomLeft, pivot, d));
    }

    public static final Quadrilateral scaleCentered(Quadrilateral quadrilateral, float f) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point topLeft2 = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft2, "getTopLeft(...)");
        float f2 = f - 1.0f;
        Point plus = PointExtensionsKt.plus(topLeft, PointExtensionsKt.times(PointExtensionsKt.minus(topLeft2, getCenter(quadrilateral)), f2));
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        Point topRight2 = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight2, "getTopRight(...)");
        Point plus2 = PointExtensionsKt.plus(topRight, PointExtensionsKt.times(PointExtensionsKt.minus(topRight2, getCenter(quadrilateral)), f2));
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        Point bottomRight2 = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight2, "getBottomRight(...)");
        Point plus3 = PointExtensionsKt.plus(bottomRight, PointExtensionsKt.times(PointExtensionsKt.minus(bottomRight2, getCenter(quadrilateral)), f2));
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        Point bottomLeft2 = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft2, "getBottomLeft(...)");
        return new Quadrilateral(plus, plus2, plus3, PointExtensionsKt.plus(bottomLeft, PointExtensionsKt.times(PointExtensionsKt.minus(bottomLeft2, getCenter(quadrilateral)), f2)));
    }

    public static final Quadrilateral scaled(Quadrilateral quadrilateral, float f) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Point topLeft = quadrilateral.getTopLeft();
        Intrinsics.checkNotNullExpressionValue(topLeft, "getTopLeft(...)");
        Point scaled = com.scandit.datacapture.core.common.geometry.PointUtilsKt.scaled(topLeft, f);
        Point topRight = quadrilateral.getTopRight();
        Intrinsics.checkNotNullExpressionValue(topRight, "getTopRight(...)");
        Point scaled2 = com.scandit.datacapture.core.common.geometry.PointUtilsKt.scaled(topRight, f);
        Point bottomRight = quadrilateral.getBottomRight();
        Intrinsics.checkNotNullExpressionValue(bottomRight, "getBottomRight(...)");
        Point scaled3 = com.scandit.datacapture.core.common.geometry.PointUtilsKt.scaled(bottomRight, f);
        Point bottomLeft = quadrilateral.getBottomLeft();
        Intrinsics.checkNotNullExpressionValue(bottomLeft, "getBottomLeft(...)");
        return new Quadrilateral(scaled, scaled2, scaled3, com.scandit.datacapture.core.common.geometry.PointUtilsKt.scaled(bottomLeft, f));
    }

    public static final Quadrilateral squared(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        Vector vector = new Vector(getCenterLeft(quadrilateral), getCenterRight(quadrilateral));
        Vector vector2 = new Vector(getTopCenter(quadrilateral), getBottomCenter(quadrilateral));
        float f = vector.getIo.sentry.SentryEnvelopeItemHeader.JsonKeys.LENGTH java.lang.String();
        float f2 = vector2.getIo.sentry.SentryEnvelopeItemHeader.JsonKeys.LENGTH java.lang.String();
        Point center = getCenter(quadrilateral);
        Point point = new Point(center.getX() - (f / 2.0f), center.getY() - (f2 / 2.0f));
        return rotatedByVector(new Quadrilateral(point, new Point(point.getX() + f, point.getY()), new Point(point.getX() + f, point.getY() + f2), new Point(point.getX(), point.getY() + f2)), center, vector);
    }

    public static final com.scandit.datacapture.core.common.geometry.Rect toRect(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "<this>");
        com.scandit.datacapture.core.common.geometry.Rect computeRectFromQuadrilateral = NativeRectToQuadConverter.computeRectFromQuadrilateral(quadrilateral);
        Intrinsics.checkNotNullExpressionValue(computeRectFromQuadrilateral, "computeRectFromQuadrilateral(...)");
        return computeRectFromQuadrilateral;
    }

    public static final Quadrilateral Quadrilateral(Point origin, Size2 size) {
        Intrinsics.checkNotNullParameter(origin, "origin");
        Intrinsics.checkNotNullParameter(size, "size");
        return new Quadrilateral(new Point(origin.getX(), origin.getY()), new Point(size.getWidth() + origin.getX(), origin.getY()), new Point(size.getWidth() + origin.getX(), size.getHeight() + origin.getY()), new Point(origin.getX(), size.getHeight() + origin.getY()));
    }
}
