package com.scandit.datacapture.core.common.geometry;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.module.serialization.NativeStructSerializer;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Vector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\u001c\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u0014\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\n\u0010\u000e\u001a\u00020\u000f*\u00020\u0001¨\u0006\u0010"}, d2 = {"rotatedByVector", "Lcom/scandit/datacapture/core/common/geometry/Point;", "pivot", "vector", "Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Vector;", "rotatedDegrees", "degrees", "", "rotatedRadians", "radians", "", "scaled", "scale", "", "toJson", "", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PointUtilsKt {
    public static final Point rotatedByVector(Point point, Point pivot, Vector vector) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        Intrinsics.checkNotNullParameter(pivot, "pivot");
        Intrinsics.checkNotNullParameter(vector, "vector");
        Vector normalized = vector.normalized();
        float dy = normalized.getDy();
        float dx = normalized.getDx();
        float f = point.x;
        float f2 = pivot.x;
        float f3 = f - f2;
        float f4 = point.y;
        float f5 = pivot.y;
        float f6 = f4 - f5;
        return new Point(((f3 * dx) - (f6 * dy)) + f2, (f6 * dx) + (f3 * dy) + f5);
    }

    public static final Point rotatedDegrees(Point point, Point pivot, int i) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        Intrinsics.checkNotNullParameter(pivot, "pivot");
        double radians = Math.toRadians(i);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        float f = point.x;
        float f2 = pivot.x;
        double d = f - f2;
        float f3 = point.y;
        float f4 = pivot.y;
        double d2 = f3 - f4;
        return new Point((float) (((cos * d) - (sin * d2)) + f2), (float) ((cos * d2) + (sin * d) + f4));
    }

    public static final Point rotatedRadians(Point point, Point pivot, double d) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        Intrinsics.checkNotNullParameter(pivot, "pivot");
        double sin = Math.sin(d);
        double cos = Math.cos(d);
        float f = point.x;
        float f2 = pivot.x;
        double d2 = f - f2;
        float f3 = point.y;
        float f4 = pivot.y;
        double d3 = f3 - f4;
        return new Point((float) (((d2 * cos) - (d3 * sin)) + f2), (float) ((d3 * cos) + (d2 * sin) + f4));
    }

    public static final Point scaled(Point point, float f) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        return new Point(point.x * f, point.y * f);
    }

    public static final /* synthetic */ String toJson(Point point) {
        Intrinsics.checkNotNullParameter(point, "<this>");
        String pointToJson = NativeStructSerializer.pointToJson(point);
        Intrinsics.checkNotNullExpressionValue(pointToJson, "pointToJson(...)");
        return pointToJson;
    }
}
