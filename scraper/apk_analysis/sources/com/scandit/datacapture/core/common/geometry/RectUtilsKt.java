package com.scandit.datacapture.core.common.geometry;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.module.serialization.NativeStructSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u0012\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0001\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\u0002¨\u0006\b"}, d2 = {"center", "Lcom/scandit/datacapture/core/common/geometry/Point;", "Lcom/scandit/datacapture/core/common/geometry/Rect;", "contains", "", "point", "toJson", "", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RectUtilsKt {
    public static final Point center(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Point point = rect.origin;
        float f = point.x;
        Size2 size2 = rect.size;
        float f2 = 2;
        return new Point((size2.width / f2) + f, (size2.height / f2) + point.y);
    }

    public static final boolean contains(Rect rect, Point point) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(point, "point");
        float f = point.x;
        Point point2 = rect.origin;
        float f2 = point2.x;
        if (f >= f2) {
            Size2 size2 = rect.size;
            if (f < f2 + size2.width) {
                float f3 = point.y;
                float f4 = point2.y;
                if (f3 >= f4 && f3 < f4 + size2.height) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final /* synthetic */ String toJson(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        String rectToJson = NativeStructSerializer.rectToJson(rect);
        Intrinsics.checkNotNullExpressionValue(rectToJson, "rectToJson(...)");
        return rectToJson;
    }
}
