package com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview;

import android.content.Context;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnitUtilsKt;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f {
    public static final PointWithUnit e;
    public static final PointWithUnit f;
    public static final PointWithUnit g;
    public static final PointWithUnit h;
    public static final List i;
    public static final List j;
    public static final Map k;
    public static final Anchor l;
    public static final Anchor m;
    public static final Anchor n;
    public List a;
    public final Lazy b;
    public final Lazy c;
    public final Lazy d;

    static {
        MeasureUnit measureUnit = MeasureUnit.DIP;
        e = PointWithUnitUtilsKt.PointWithUnit(16.0f, 16.0f, measureUnit);
        f = PointWithUnitUtilsKt.PointWithUnit(0.0f, 16.0f, measureUnit);
        g = PointWithUnitUtilsKt.PointWithUnit(16.0f, 0.0f, measureUnit);
        h = PointWithUnitUtilsKt.PointWithUnit(40.0f, 32.0f, measureUnit);
        Anchor anchor = Anchor.TOP_LEFT;
        Anchor anchor2 = Anchor.TOP_CENTER;
        Anchor anchor3 = Anchor.TOP_RIGHT;
        Anchor anchor4 = Anchor.BOTTOM_LEFT;
        i = CollectionsKt.listOf((Object[]) new Anchor[]{anchor, anchor2, anchor3, anchor4});
        j = CollectionsKt.listOf((Object[]) new Anchor[]{anchor, anchor2, anchor3, anchor4});
        Pair pair = TuplesKt.to(anchor, anchor4);
        Anchor anchor5 = Anchor.CENTER_LEFT;
        Pair pair2 = TuplesKt.to(anchor2, anchor5);
        Pair pair3 = TuplesKt.to(anchor3, anchor);
        Anchor anchor6 = Anchor.BOTTOM_CENTER;
        Pair pair4 = TuplesKt.to(anchor5, anchor6);
        Anchor anchor7 = Anchor.CENTER;
        Pair pair5 = TuplesKt.to(anchor7, anchor7);
        Anchor anchor8 = Anchor.CENTER_RIGHT;
        Pair pair6 = TuplesKt.to(anchor8, anchor2);
        Anchor anchor9 = Anchor.BOTTOM_RIGHT;
        k = MapsKt.mapOf(pair, pair2, pair3, pair4, pair5, pair6, TuplesKt.to(anchor4, anchor9), TuplesKt.to(anchor6, anchor8), TuplesKt.to(anchor9, anchor3));
        l = anchor4;
        m = anchor;
        n = anchor;
    }

    public f(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = CollectionsKt.emptyList();
        this.b = LazyKt.lazy(new e(context));
        this.c = LazyKt.lazy(new d(context));
        this.d = LazyKt.lazy(new c(context));
    }
}
