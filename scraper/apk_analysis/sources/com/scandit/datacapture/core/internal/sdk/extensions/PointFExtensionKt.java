package com.scandit.datacapture.core.internal.sdk.extensions;

import android.graphics.PointF;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¨\u0006\u0004"}, d2 = {"distance", "", "Landroid/graphics/PointF;", Request.JsonKeys.OTHER, "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PointFExtensionKt {
    public static final float distance(PointF pointF, PointF other) {
        Intrinsics.checkNotNullParameter(pointF, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        float f = pointF.x - other.x;
        float f2 = pointF.y - other.y;
        return (float) Math.sqrt((f2 * f2) + (f * f));
    }
}
