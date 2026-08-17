package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.PointExtensionsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class p extends Lambda implements Function1 {
    public final /* synthetic */ Map a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(Map map) {
        super(1);
        this.a = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Quadrilateral quadrilateral;
        Point location = (Point) obj;
        Intrinsics.checkNotNullParameter(location, "location");
        Collection<a> values = this.a.values();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : values) {
            if (QuadrilateralUtilsKt.contains(((a) obj2).b.a, location)) {
                arrayList.add(obj2);
            }
        }
        a aVar = null;
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            if (it.hasNext()) {
                ?? next = it.next();
                if (it.hasNext()) {
                    float distanceSquared = PointExtensionsKt.distanceSquared(QuadrilateralUtilsKt.getCenter(((a) next).b.a), location);
                    do {
                        Object next2 = it.next();
                        float distanceSquared2 = PointExtensionsKt.distanceSquared(QuadrilateralUtilsKt.getCenter(((a) next2).b.a), location);
                        next = next;
                        if (Float.compare(distanceSquared, distanceSquared2) > 0) {
                            next = next2;
                            distanceSquared = distanceSquared2;
                        }
                    } while (it.hasNext());
                }
                aVar = next;
            }
            return aVar;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj3 : values) {
            v vVar = ((a) obj3).c;
            if (vVar != null && (quadrilateral = vVar.a) != null && QuadrilateralUtilsKt.contains(quadrilateral, location)) {
                arrayList2.add(obj3);
            }
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        ArrayList arrayList3 = new ArrayList();
        float f = Float.MIN_VALUE;
        boolean z = false;
        boolean z2 = false;
        float f2 = Float.MIN_VALUE;
        for (a aVar2 : values) {
            BarcodePickState barcodePickState = aVar2.e;
            if (barcodePickState == BarcodePickState.TO_PICK) {
                z = true;
            }
            BarcodePickState barcodePickState2 = BarcodePickState.IGNORE;
            if (barcodePickState == barcodePickState2) {
                z2 = true;
            }
            f2 = Math.max(f2, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.h.a(barcodePickState));
            if (aVar2.e != barcodePickState2) {
                arrayList3.add(aVar2);
                f = Math.max(f, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.h.a(aVar2.e));
            }
        }
        if (!z || !z2) {
            f = f2;
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : values) {
            if (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.h.a(((a) obj4).e) == f) {
                arrayList4.add(obj4);
            }
        }
        Iterator it2 = arrayList4.iterator();
        if (it2.hasNext()) {
            ?? next3 = it2.next();
            if (it2.hasNext()) {
                float distanceSquared3 = PointExtensionsKt.distanceSquared(QuadrilateralUtilsKt.getCenter(((a) next3).b.a), location);
                do {
                    Object next4 = it2.next();
                    float distanceSquared4 = PointExtensionsKt.distanceSquared(QuadrilateralUtilsKt.getCenter(((a) next4).b.a), location);
                    next3 = next3;
                    if (Float.compare(distanceSquared3, distanceSquared4) > 0) {
                        next3 = next4;
                        distanceSquared3 = distanceSquared4;
                    }
                } while (it2.hasNext());
            }
            aVar = next3;
        }
        return aVar;
    }
}
