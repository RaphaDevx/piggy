package com.scandit.datacapture.core.internal.sdk.common.geometry;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.geometry.Point;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\u001a\u0016\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"intersectionWithLineOf", "Lcom/scandit/datacapture/core/common/geometry/Point;", "Lcom/scandit/datacapture/core/internal/sdk/common/geometry/Segment;", Request.JsonKeys.OTHER, "moveAlongNormal", "distance", "", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SegmentKt {
    public static final Point intersectionWithLineOf(Segment segment, Segment other) {
        Intrinsics.checkNotNullParameter(segment, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        if (segment.getSlope() == other.getSlope()) {
            return null;
        }
        float x = segment.getA().getX();
        float y = segment.getA().getY();
        float x2 = segment.getB().getX();
        float y2 = segment.getB().getY();
        float x3 = other.getA().getX();
        float y3 = other.getA().getY();
        float x4 = other.getB().getX();
        float y4 = other.getB().getY() - y3;
        float f = x2 - x;
        float f2 = x4 - x3;
        float f3 = y2 - y;
        float f4 = (y4 * f) - (f2 * f3);
        if (f4 == 0.0f) {
            return null;
        }
        float f5 = (((y - y3) * f2) - ((x - x3) * y4)) / f4;
        return new Point((f * f5) + x, (f5 * f3) + y);
    }

    public static final Segment moveAlongNormal(Segment segment, float f) {
        Intrinsics.checkNotNullParameter(segment, "<this>");
        Vector normalized = segment.vector().normalized();
        float dy = normalized.getDy();
        float f2 = dy * f;
        float f3 = (-normalized.getDx()) * f;
        return new Segment(new Point(segment.getA().getX() + f2, segment.getA().getY() + f3), new Point(segment.getB().getX() + f2, segment.getB().getY() + f3));
    }
}
