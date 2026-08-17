package com.scandit.datacapture.core.internal.sdk.extensions;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.common.geometry.FloatWithUnit;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0005"}, d2 = {"toPixels", "", "Lcom/scandit/datacapture/core/common/geometry/FloatWithUnit;", "relativeSize", "density", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FloatWithUnitExtensionsKt {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MeasureUnit.values().length];
            try {
                iArr[MeasureUnit.PIXEL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MeasureUnit.DIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MeasureUnit.FRACTION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final float toPixels(FloatWithUnit floatWithUnit, float f, float f2) {
        Intrinsics.checkNotNullParameter(floatWithUnit, "<this>");
        MeasureUnit unit = floatWithUnit.getUnit();
        int i = unit == null ? -1 : WhenMappings.$EnumSwitchMapping$0[unit.ordinal()];
        if (i == -1) {
            throw new IllegalStateException("Unit of a FloatWithUnit has to be non-null".toString());
        }
        if (i == 1) {
            return floatWithUnit.getValue();
        }
        if (i == 2) {
            return floatWithUnit.getValue() * f2;
        }
        if (i == 3) {
            return floatWithUnit.getValue() * f;
        }
        throw new NoWhenBranchMatchedException();
    }
}
