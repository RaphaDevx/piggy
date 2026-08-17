package com.scandit.datacapture.core.common.geometry;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.module.serialization.NativeStructSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toJson", "", "Lcom/scandit/datacapture/core/common/geometry/RectWithUnit;", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RectWithUnitUtilsKt {
    public static final /* synthetic */ String toJson(RectWithUnit rectWithUnit) {
        Intrinsics.checkNotNullParameter(rectWithUnit, "<this>");
        String rectWithUnitToJson = NativeStructSerializer.rectWithUnitToJson(rectWithUnit);
        Intrinsics.checkNotNullExpressionValue(rectWithUnitToJson, "rectWithUnitToJson(...)");
        return rectWithUnitToJson;
    }
}
