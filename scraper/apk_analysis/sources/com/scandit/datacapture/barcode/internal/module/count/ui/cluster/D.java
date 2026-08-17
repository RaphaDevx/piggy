package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.graphics.Path;
import com.scandit.datacapture.barcode.tracking.internal.module.data.NativeCluster;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* loaded from: classes2.dex */
public final class D {
    public static final Lazy f = LazyKt.lazy(C.a);
    public final NativeCluster a;
    public final Quadrilateral b;
    public final int c;
    public final Point d;
    public final Path e;

    /* JADX WARN: Type inference failed for: r6v5, types: [java.util.Iterator, kotlin.collections.IntIterator] */
    public D(NativeCluster nativeCluster, Quadrilateral location, int i) {
        Intrinsics.checkNotNullParameter(location, "location");
        this.a = nativeCluster;
        this.b = location;
        this.c = i;
        Point topLeft = location.getTopLeft();
        Point[] pointArr = {topLeft, location.getTopRight(), location.getBottomRight(), location.getBottomLeft()};
        int lastIndex = ArraysKt.getLastIndex(pointArr);
        if (lastIndex != 0) {
            float x = topLeft.getX() - topLeft.getY();
            ?? it = new IntRange(1, lastIndex).iterator();
            while (it.hasNext()) {
                Point point = pointArr[it.nextInt()];
                float x2 = point.getX() - point.getY();
                if (Float.compare(x, x2) < 0) {
                    topLeft = point;
                    x = x2;
                }
            }
        }
        Intrinsics.checkNotNullExpressionValue(topLeft, "maxBy(...)");
        this.d = new Point(topLeft.getX() + ((Number) f.getValue()).intValue(), topLeft.getY());
        this.e = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.k.a(this.b);
    }
}
