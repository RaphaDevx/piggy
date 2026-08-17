package com.scandit.datacapture.core.internal.sdk.extensions;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.core.internal.sdk.common.graphics.NativeColor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001¨\u0006\u0004"}, d2 = {"toInt", "", "Lcom/scandit/datacapture/core/internal/sdk/common/graphics/NativeColor;", "toNativeColor", "scandit-capture-core"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NativeColorExtensionsKt {
    public static final int toInt(NativeColor nativeColor) {
        Intrinsics.checkNotNullParameter(nativeColor, "<this>");
        c cVar = c.a;
        int intValue = ((Number) cVar.invoke(Float.valueOf(nativeColor.getA()))).intValue() << 24;
        int intValue2 = ((Number) cVar.invoke(Float.valueOf(nativeColor.getR()))).intValue() << 16;
        return ((Number) cVar.invoke(Float.valueOf(nativeColor.getB()))).intValue() | intValue | intValue2 | (((Number) cVar.invoke(Float.valueOf(nativeColor.getG()))).intValue() << 8);
    }

    public static final NativeColor toNativeColor(int i) {
        d dVar = d.a;
        return new NativeColor(((Number) dVar.invoke(Integer.valueOf((16711680 & i) >>> 16))).floatValue(), ((Number) dVar.invoke(Integer.valueOf((65280 & i) >>> 8))).floatValue(), ((Number) dVar.invoke(Integer.valueOf(i & 255))).floatValue(), ((Number) dVar.invoke(Integer.valueOf(((-16777216) & i) >>> 24))).floatValue());
    }
}
