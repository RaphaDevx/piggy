package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountStatus;
import com.scandit.datacapture.core.common.geometry.Point;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class G {
    public final ViewGroup a;
    public final LinkedHashMap b;
    public boolean c;
    public int d;
    public int e;
    public final LinkedHashMap f;
    public com.scandit.datacapture.barcode.internal.module.count.ui.status.n g;
    public A h;

    public G(FrameLayout indicatorLayer) {
        Intrinsics.checkNotNullParameter(indicatorLayer, "indicatorLayer");
        this.a = indicatorLayer;
        this.b = new LinkedHashMap();
        this.d = -1;
        this.e = -1;
        this.f = new LinkedHashMap();
        this.g = com.scandit.datacapture.barcode.internal.module.count.ui.status.n.a;
        this.h = new A(false, false);
    }

    public static final void a(G g, L l, Point point, float f) {
        g.getClass();
        S.a.getClass();
        float floatValue = ((Number) Q.b.getValue()).floatValue() / 2;
        l.setX(point.getX() - floatValue);
        l.setY(point.getY() - floatValue);
        l.setScaleX(f);
        l.setScaleY(f);
    }

    public final void a() {
        G g = this;
        if (O.e()) {
            int i = 0;
            g.h = new A(false, false);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : O.c().entrySet()) {
                int intValue = ((Number) entry.getKey()).intValue();
                com.scandit.datacapture.barcode.internal.module.count.ui.status.b bVar = (com.scandit.datacapture.barcode.internal.module.count.ui.status.b) entry.getValue();
                if (!Intrinsics.areEqual(bVar, com.scandit.datacapture.barcode.internal.module.count.ui.status.d.a)) {
                    if (Intrinsics.areEqual(bVar, com.scandit.datacapture.barcode.internal.module.count.ui.status.c.a)) {
                        Integer num = (Integer) g.f.get(Integer.valueOf(intValue));
                        if (num != null) {
                            g.a(linkedHashMap, num.intValue(), bVar);
                        }
                    } else if (bVar instanceof com.scandit.datacapture.barcode.internal.module.count.ui.status.e) {
                        Integer num2 = ((com.scandit.datacapture.barcode.internal.module.count.ui.status.e) bVar).b;
                        if (num2 != null) {
                            g.a(linkedHashMap, num2.intValue(), bVar);
                        } else {
                            Integer num3 = (Integer) g.f.get(Integer.valueOf(intValue));
                            if (num3 != null) {
                                g.a(linkedHashMap, num3.intValue(), bVar);
                            }
                        }
                    }
                }
            }
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                int intValue2 = ((Number) entry2.getKey()).intValue();
                Set statuses = (Set) entry2.getValue();
                E e = (E) g.b.get(Integer.valueOf(intValue2));
                if (e != null) {
                    Intrinsics.checkNotNullParameter(statuses, "statuses");
                    while (e.e.size() > statuses.size()) {
                        L l = (L) CollectionsKt.removeLastOrNull(e.e);
                        ViewParent parent = l != null ? l.getParent() : null;
                        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                        if (viewGroup != null) {
                            viewGroup.removeView(l);
                        }
                    }
                    int size = e.e.size();
                    float a = E.a(e.b, size);
                    if (!statuses.isEmpty()) {
                        List sorted = CollectionsKt.sorted(statuses);
                        G g2 = e.f;
                        int i2 = i;
                        for (Object obj : sorted) {
                            int i3 = i2 + 1;
                            if (i2 < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            BarcodeCountStatus barcodeCountStatus = (BarcodeCountStatus) obj;
                            G g3 = g2;
                            L a2 = e.a(i2, new D(g2, e, i2, a, size));
                            I i4 = I.a;
                            a2.getClass();
                            Intrinsics.checkNotNullParameter(i4, "<set-?>");
                            a2.e = i4;
                            com.scandit.datacapture.barcode.internal.module.count.ui.status.e statusInternal = new com.scandit.datacapture.barcode.internal.module.count.ui.status.e(barcodeCountStatus, null);
                            Intrinsics.checkNotNullParameter(statusInternal, "statusInternal");
                            a2.f = statusInternal;
                            com.scandit.datacapture.barcode.internal.module.count.ui.status.m mVar = a2.c;
                            mVar.getClass();
                            Intrinsics.checkNotNullParameter(statusInternal, "statusInternal");
                            com.scandit.datacapture.barcode.internal.module.count.ui.status.m.e.runOnMainThread(new com.scandit.datacapture.barcode.internal.module.count.ui.status.g(mVar, statusInternal));
                            if (g3.c) {
                                i = 0;
                                L.b(a2, false);
                                a2.b(true);
                                a2.a(true);
                            } else {
                                i = 0;
                                L.a(a2, false);
                            }
                            g2 = g3;
                            i2 = i3;
                        }
                    } else if (e.f.g == com.scandit.datacapture.barcode.internal.module.count.ui.status.n.b) {
                        L a3 = e.a(i, new C(e.f, e, a, size));
                        I i5 = I.a;
                        a3.getClass();
                        Intrinsics.checkNotNullParameter(i5, "<set-?>");
                        a3.e = i5;
                        L.b(a3.b, true);
                        a3.c.a(true);
                    }
                    g = this;
                }
            }
        }
    }

    public final void a(LinkedHashMap clusterMap, int i, com.scandit.datacapture.barcode.internal.module.count.ui.status.b statusInternal) {
        Intrinsics.checkNotNullParameter(clusterMap, "clusterMap");
        Intrinsics.checkNotNullParameter(statusInternal, "statusInternal");
        int ordinal = this.g.ordinal();
        if (ordinal == 0) {
            if (Intrinsics.areEqual(statusInternal, com.scandit.datacapture.barcode.internal.module.count.ui.status.d.a)) {
                return;
            }
            if (Intrinsics.areEqual(statusInternal, com.scandit.datacapture.barcode.internal.module.count.ui.status.c.a)) {
                Integer valueOf = Integer.valueOf(i);
                Object obj = clusterMap.get(valueOf);
                if (obj == null) {
                    obj = EnumSet.noneOf(BarcodeCountStatus.class);
                    Intrinsics.checkNotNullExpressionValue(obj, "noneOf(...)");
                    clusterMap.put(valueOf, obj);
                }
                ((Set) obj).add(BarcodeCountStatus.NOT_AVAILABLE);
                return;
            }
            if (statusInternal instanceof com.scandit.datacapture.barcode.internal.module.count.ui.status.e) {
                Integer valueOf2 = Integer.valueOf(i);
                Object obj2 = clusterMap.get(valueOf2);
                if (obj2 == null) {
                    obj2 = EnumSet.noneOf(BarcodeCountStatus.class);
                    Intrinsics.checkNotNullExpressionValue(obj2, "noneOf(...)");
                    clusterMap.put(valueOf2, obj2);
                }
                ((Set) obj2).add(((com.scandit.datacapture.barcode.internal.module.count.ui.status.e) statusInternal).a);
                return;
            }
            return;
        }
        if (ordinal != 1) {
            return;
        }
        Integer valueOf3 = Integer.valueOf(i);
        Object obj3 = clusterMap.get(valueOf3);
        if (obj3 == null) {
            obj3 = EnumSet.noneOf(BarcodeCountStatus.class);
            Intrinsics.checkNotNullExpressionValue(obj3, "noneOf(...)");
            clusterMap.put(valueOf3, obj3);
        }
        Set set = (Set) obj3;
        if (Intrinsics.areEqual(statusInternal, com.scandit.datacapture.barcode.internal.module.count.ui.status.d.a)) {
            return;
        }
        if (Intrinsics.areEqual(statusInternal, com.scandit.datacapture.barcode.internal.module.count.ui.status.c.a)) {
            set.add(BarcodeCountStatus.NOT_AVAILABLE);
            return;
        }
        if (statusInternal instanceof com.scandit.datacapture.barcode.internal.module.count.ui.status.e) {
            BarcodeCountStatus barcodeCountStatus = ((com.scandit.datacapture.barcode.internal.module.count.ui.status.e) statusInternal).a;
            if (barcodeCountStatus == BarcodeCountStatus.NONE) {
                barcodeCountStatus = null;
            }
            if (barcodeCountStatus != null) {
                set.add(barcodeCountStatus);
            }
        }
    }
}
