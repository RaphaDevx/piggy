package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import java.util.Iterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final /* synthetic */ class l extends FunctionReferenceImpl implements Function2 {
    public l(Object obj) {
        super(2, obj, n.class, "shouldAllowStatusIconTap", "shouldAllowStatusIconTap(Lcom/scandit/datacapture/core/common/geometry/Point;I)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        a aVar;
        v vVar;
        Quadrilateral quadrilateral;
        Point p0 = (Point) obj;
        int intValue = ((Number) obj2).intValue();
        Intrinsics.checkNotNullParameter(p0, "p0");
        n nVar = (n) this.receiver;
        Iterator it = nVar.e.keySet().iterator();
        boolean z = false;
        boolean z2 = false;
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            int intValue2 = ((Number) it.next()).intValue();
            if (intValue2 != intValue) {
                if (z2 && (aVar = (a) nVar.e.get(Integer.valueOf(intValue2))) != null && (vVar = aVar.b) != null && (quadrilateral = vVar.a) != null && QuadrilateralUtilsKt.contains(quadrilateral, p0)) {
                    break;
                }
            } else {
                z2 = true;
            }
        }
        return Boolean.valueOf(z);
    }
}
