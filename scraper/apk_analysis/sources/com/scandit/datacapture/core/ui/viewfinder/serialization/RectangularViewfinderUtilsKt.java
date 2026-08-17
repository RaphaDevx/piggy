package com.scandit.datacapture.core.ui.viewfinder.serialization;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.module.serialization.NativeEnumSerializer;
import com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderLineStyle;
import com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderLineStyleKt;
import com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderStyle;
import com.scandit.datacapture.core.ui.viewfinder.RectangularViewfinderStyleKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0003¨\u0006\u0004"}, d2 = {"toJson", "", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderLineStyle;", "Lcom/scandit/datacapture/core/ui/viewfinder/RectangularViewfinderStyle;", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RectangularViewfinderUtilsKt {
    public static final /* synthetic */ String toJson(RectangularViewfinderStyle rectangularViewfinderStyle) {
        Intrinsics.checkNotNullParameter(rectangularViewfinderStyle, "<this>");
        String rectangularViewfinderStyleToString = NativeEnumSerializer.rectangularViewfinderStyleToString(RectangularViewfinderStyleKt.toNative(rectangularViewfinderStyle));
        Intrinsics.checkNotNullExpressionValue(rectangularViewfinderStyleToString, "rectangularViewfinderStyleToString(...)");
        return rectangularViewfinderStyleToString;
    }

    public static final /* synthetic */ String toJson(RectangularViewfinderLineStyle rectangularViewfinderLineStyle) {
        Intrinsics.checkNotNullParameter(rectangularViewfinderLineStyle, "<this>");
        String rectangularViewfinderLineStyleToString = NativeEnumSerializer.rectangularViewfinderLineStyleToString(RectangularViewfinderLineStyleKt.toNative(rectangularViewfinderLineStyle));
        Intrinsics.checkNotNullExpressionValue(rectangularViewfinderLineStyleToString, "rectangularViewfinderLineStyleToString(...)");
        return rectangularViewfinderLineStyleToString;
    }
}
