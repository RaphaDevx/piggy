package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview;

import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.RectUtilsKt;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final /* synthetic */ class d extends FunctionReferenceImpl implements Function2 {
    public d(Object obj) {
        super(2, obj, f.class, "shouldAllowStatusIconTap", "shouldAllowStatusIconTap(Lcom/scandit/datacapture/core/common/geometry/Point;I)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Point p0 = (Point) obj;
        int intValue = ((Number) obj2).intValue();
        Intrinsics.checkNotNullParameter(p0, "p0");
        f fVar = (f) this.receiver;
        int indexOf = CollectionsKt.indexOf(fVar.j.keySet(), Integer.valueOf(intValue));
        Collection values = fVar.j.values();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj3 : values) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            a aVar = (a) obj3;
            if (i > indexOf && RectUtilsKt.contains(aVar.b, p0)) {
                arrayList.add(obj3);
            }
            i = i2;
        }
        return Boolean.valueOf(arrayList.isEmpty());
    }
}
