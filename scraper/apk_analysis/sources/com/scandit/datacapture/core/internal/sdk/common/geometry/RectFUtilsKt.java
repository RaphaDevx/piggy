package com.scandit.datacapture.core.internal.sdk.common.geometry;

import android.graphics.RectF;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"scaleCentered", "Landroid/graphics/RectF;", "scaleFactor", "", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RectFUtilsKt {
    public static final RectF scaleCentered(RectF rectF, float f) {
        Intrinsics.checkNotNullParameter(rectF, "<this>");
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();
        float f2 = rectF.left;
        float f3 = f - 1.0f;
        float f4 = ((f2 - centerX) * f3) + f2;
        float f5 = rectF.top;
        float f6 = ((f5 - centerY) * f3) + f5;
        float f7 = rectF.right;
        float f8 = rectF.bottom;
        return new RectF(f4, f6, ((f7 - centerX) * f3) + f7, ((f8 - centerY) * f3) + f8);
    }
}
