package com.scandit.datacapture.core.common.geometry;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.module.serialization.NativeStructSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\u0001¨\u0006\b"}, d2 = {"MarginsWithUnit", "Lcom/scandit/datacapture/core/common/geometry/MarginsWithUnit;", "margin", "", "unit", "Lcom/scandit/datacapture/core/common/geometry/MeasureUnit;", "toJson", "", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MarginsWithUnitUtilsKt {
    public static final MarginsWithUnit MarginsWithUnit(float f, MeasureUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return new MarginsWithUnit(new FloatWithUnit(f, unit), new FloatWithUnit(f, unit), new FloatWithUnit(f, unit), new FloatWithUnit(f, unit));
    }

    public static final /* synthetic */ String toJson(MarginsWithUnit marginsWithUnit) {
        Intrinsics.checkNotNullParameter(marginsWithUnit, "<this>");
        String marginsWithUnitToJson = NativeStructSerializer.marginsWithUnitToJson(marginsWithUnit);
        Intrinsics.checkNotNullExpressionValue(marginsWithUnitToJson, "marginsWithUnitToJson(...)");
        return marginsWithUnitToJson;
    }
}
