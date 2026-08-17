package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.PointExtensionsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.o, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final /* synthetic */ class C0312o extends FunctionReferenceImpl implements Function1 {
    public C0312o(Object obj) {
        super(1, obj, C0316t.class, "closestBarcodeToLocation", "closestBarcodeToLocation(Lcom/scandit/datacapture/core/common/geometry/Point;)Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Object next;
        Point p0 = (Point) obj;
        Intrinsics.checkNotNullParameter(p0, "p0");
        C0316t c0316t = (C0316t) this.receiver;
        List<TrackedBarcode> plus = CollectionsKt.plus(c0316t.t.values(), (Iterable) c0316t.v);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(plus, 10));
        for (TrackedBarcode trackedBarcode : plus) {
            S s = c0316t.b;
            V a = ((U) s).a(trackedBarcode);
            arrayList.add(new C0302e(trackedBarcode, a, PointExtensionsKt.dotProductSquared(PointExtensionsKt.minus(a.c, p0))));
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                float f = ((C0302e) next).c;
                do {
                    Object next2 = it.next();
                    float f2 = ((C0302e) next2).c;
                    if (Float.compare(f, f2) > 0) {
                        next = next2;
                        f = f2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        C0302e c0302e = (C0302e) next;
        if (c0302e == null) {
            return null;
        }
        int ordinal = c0316t.d(c0302e.a).ordinal();
        boolean z = true;
        if (ordinal == 0 || ordinal == 1) {
            S.a.getClass();
            float floatValue = ((Number) Q.b.getValue()).floatValue();
            V v = c0302e.b;
            float f3 = ((floatValue * v.d) / 2) * 1.5f;
            if (PointExtensionsKt.dotProductSquared(PointExtensionsKt.minus(v.c, p0)) > f3 * f3) {
                z = false;
            }
        } else {
            if (ordinal != 2) {
                throw new NoWhenBranchMatchedException();
            }
            z = QuadrilateralUtilsKt.contains(c0302e.b.b, p0);
        }
        if (!z) {
            c0302e = null;
        }
        if (c0302e != null) {
            return c0302e.a;
        }
        return null;
    }
}
