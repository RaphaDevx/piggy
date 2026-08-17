package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Vector;
import com.scandit.datacapture.core.internal.sdk.extensions.PointExtensionsKt;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* loaded from: classes2.dex */
public final class E {
    public Point a;
    public float b;
    public Vector c;
    public final z d;
    public final ArrayList e;
    public final /* synthetic */ G f;

    public E(G g, Point center, float f, Vector normalizedVector, z clusterIndicatorInfo) {
        Intrinsics.checkNotNullParameter(center, "center");
        Intrinsics.checkNotNullParameter(normalizedVector, "normalizedVector");
        Intrinsics.checkNotNullParameter(clusterIndicatorInfo, "clusterIndicatorInfo");
        this.f = g;
        this.a = center;
        this.b = f;
        this.c = normalizedVector;
        this.d = clusterIndicatorInfo;
        this.e = new ArrayList();
    }

    public static float a(float f, int i) {
        S.a.getClass();
        return RangesKt.coerceIn((f / (((Number) Q.b.getValue()).floatValue() * i)) * 1.2f, 0.5f, 1.0f);
    }

    public static Point a(int i, Point point, float f, int i2, Vector vector) {
        S.a.getClass();
        float floatValue = ((Number) Q.b.getValue()).floatValue() * (((-i2) / 2.0f) + i + 0.5f) * f;
        return PointExtensionsKt.plus(point, new Point(vector.getDx() * floatValue, vector.getDy() * floatValue));
    }

    public final L a(int i, Function0 function0) {
        L l = (L) CollectionsKt.getOrNull(this.e, i);
        if (l != null) {
            return l;
        }
        Object invoke = function0.invoke();
        G g = this.f;
        L l2 = (L) invoke;
        g.a.addView(l2);
        this.e.add(l2);
        if (g.c) {
            l2.b(false);
            l2.a(true);
        } else {
            L.b(l2.b, true);
            l2.c.a(true);
        }
        return l2;
    }
}
