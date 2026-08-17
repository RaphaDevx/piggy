package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListStatus;
import com.scandit.datacapture.barcode.internal.module.ui.popover.C0491m;
import com.scandit.datacapture.barcode.internal.module.ui.popover.EnumC0488j;
import com.scandit.datacapture.barcode.internal.module.ui.popover.InterfaceC0492n;
import com.scandit.datacapture.barcode.internal.module.ui.popover.InterfaceC0493o;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.internal.sdk.common.geometry.Vector;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class r extends Lambda implements Function0 {
    public final /* synthetic */ C0316t a;
    public final /* synthetic */ long b;
    public final /* synthetic */ List c;
    public final /* synthetic */ List d;
    public final /* synthetic */ List e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(C0316t c0316t, long j, List list, ArrayList arrayList, ArrayList arrayList2) {
        super(0);
        this.a = c0316t;
        this.b = j;
        this.c = list;
        this.d = arrayList;
        this.e = arrayList2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Unit unit;
        C0321y c0321y;
        TrackedBarcode trackedBarcode;
        C0316t c0316t = this.a;
        long j = c0316t.i;
        long j2 = this.b;
        if (j != j2) {
            c0316t.i = j2;
            c0316t.c();
        }
        C0316t.a(this.a, this.c, (ArrayList) this.d);
        C0316t c0316t2 = this.a;
        List infos = this.e;
        com.scandit.datacapture.barcode.internal.module.count.ui.barcodeoverlay.a aVar = (com.scandit.datacapture.barcode.internal.module.count.ui.barcodeoverlay.a) c0316t2.p.getValue();
        if (aVar != null) {
            Intrinsics.checkNotNullParameter(infos, "infos");
            aVar.b = infos;
            aVar.invalidate();
        }
        C0321y c0321y2 = (C0321y) this.a.c;
        TrackedBarcode trackedBarcode2 = c0321y2.e;
        if (trackedBarcode2 != null) {
            V barcodeInfo = ((U) c0321y2.c).a(trackedBarcode2);
            com.scandit.datacapture.barcode.internal.module.count.ui.popover.h hVar = c0321y2.f;
            NativeBarcodeCountNotInListStatus barcodeState = c0321y2.d;
            hVar.getClass();
            Intrinsics.checkNotNullParameter(barcodeState, "barcodeState");
            Intrinsics.checkNotNullParameter(barcodeInfo, "barcodeInfo");
            boolean a = ((C0491m) hVar.c).a(0, barcodeState != NativeBarcodeCountNotInListStatus.REJECTED);
            boolean a2 = ((C0491m) hVar.c).a(1, barcodeState != NativeBarcodeCountNotInListStatus.ACCEPTED);
            if (a || a2) {
                hVar.e = null;
            }
            int floatValue = (int) (((Number) com.scandit.datacapture.barcode.internal.module.count.ui.popover.h.f.getValue()).floatValue() * barcodeInfo.d);
            int x = (int) barcodeInfo.c.getX();
            int y = (int) barcodeInfo.c.getY();
            Lazy lazy = com.scandit.datacapture.barcode.internal.module.ui.popover.O.f;
            com.scandit.datacapture.barcode.internal.module.ui.popover.O o = new com.scandit.datacapture.barcode.internal.module.ui.popover.O(x, y, ((Number) lazy.getValue()).intValue() + floatValue, ((Number) lazy.getValue()).intValue() + floatValue);
            InterfaceC0493o interfaceC0493o = hVar.d;
            com.scandit.datacapture.barcode.internal.module.ui.popover.P p = hVar.e;
            if (p == null) {
                p = new com.scandit.datacapture.barcode.internal.module.ui.popover.P(((C0491m) hVar.c).b(EnumC0488j.a), ((C0491m) hVar.c).b(EnumC0488j.b));
                hVar.e = p;
            }
            com.scandit.datacapture.barcode.internal.module.ui.popover.L a3 = ((com.scandit.datacapture.barcode.internal.module.ui.popover.K) interfaceC0493o).a(o, p);
            ((C0491m) hVar.c).a(a3.a);
            InterfaceC0492n interfaceC0492n = hVar.c;
            int i = a3.b;
            int i2 = a3.c;
            C0491m c0491m = (C0491m) interfaceC0492n;
            ViewGroup.LayoutParams layoutParams = c0491m.getLayoutParams();
            FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
            if (layoutParams2 != null) {
                layoutParams2.leftMargin = i;
                layoutParams2.topMargin = i2;
                Lazy lazy2 = C0491m.d;
                layoutParams2.rightMargin = ((Number) lazy2.getValue()).intValue();
                layoutParams2.bottomMargin = ((Number) lazy2.getValue()).intValue();
            } else {
                layoutParams2 = null;
            }
            c0491m.setLayoutParams(layoutParams2);
            if (a3.d && (c0321y = (C0321y) ((C0320x) hVar.b).a.get()) != null && (trackedBarcode = c0321y.e) != null) {
                c0321y.a.didCancelNotInListBarcode(trackedBarcode);
            }
            C0491m c0491m2 = (C0491m) hVar.c;
            c0491m2.getClass();
            c0491m2.setVisibility(0);
            c0321y2.b.b().setVisibility(0);
            c0321y2.b.h().setVisibility(0);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null && c0321y2.b.b().getVisibility() != 8) {
            c0321y2.b.b().setVisibility(8);
            c0321y2.b.h().setVisibility(8);
            C0491m c0491m3 = (C0491m) c0321y2.f.c;
            c0491m3.getClass();
            c0491m3.setVisibility(4);
        }
        G g = this.a.q;
        List<z> clusterDataList = this.d;
        g.getClass();
        Intrinsics.checkNotNullParameter(clusterDataList, "clusterDataList");
        HashSet hashSet = new HashSet();
        g.f.clear();
        for (z zVar : clusterDataList) {
            Iterator it = zVar.c.iterator();
            while (it.hasNext()) {
                g.f.put(Integer.valueOf(((Number) it.next()).intValue()), Integer.valueOf(zVar.a));
            }
            hashSet.add(Integer.valueOf(zVar.a));
            Quadrilateral quadrilateral = zVar.d;
            Vector vector = new Vector(QuadrilateralUtilsKt.getTopCenter(quadrilateral), QuadrilateralUtilsKt.getBottomCenter(quadrilateral));
            float f = vector.getIo.sentry.SentryEnvelopeItemHeader.JsonKeys.LENGTH java.lang.String();
            Vector normalizedVector = vector.normalized();
            Point center = QuadrilateralUtilsKt.getCenter(quadrilateral);
            LinkedHashMap linkedHashMap = g.b;
            Integer valueOf = Integer.valueOf(zVar.a);
            Object obj = linkedHashMap.get(valueOf);
            if (obj == null) {
                E e = new E(g, center, f, normalizedVector, zVar);
                linkedHashMap.put(valueOf, e);
                obj = e;
            }
            E e2 = (E) obj;
            Intrinsics.checkNotNullParameter(center, "center");
            Intrinsics.checkNotNullParameter(normalizedVector, "normalizedVector");
            int size = e2.e.size();
            float a4 = E.a(f, size);
            ArrayList arrayList = e2.e;
            G g2 = e2.f;
            Iterator it2 = arrayList.iterator();
            int i3 = 0;
            while (it2.hasNext()) {
                Object next = it2.next();
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                G.a(g2, (L) next, E.a(i3, center, a4, size, normalizedVector), a4);
                i3 = i4;
            }
            e2.a = center;
            e2.b = f;
            e2.c = normalizedVector;
        }
        Iterator it3 = g.b.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry entry = (Map.Entry) it3.next();
            int intValue = ((Number) entry.getKey()).intValue();
            E e3 = (E) entry.getValue();
            if (!hashSet.contains(Integer.valueOf(intValue))) {
                Iterator it4 = e3.e.iterator();
                while (it4.hasNext()) {
                    L l = (L) it4.next();
                    ViewParent parent = l.getParent();
                    ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
                    if (viewGroup != null) {
                        viewGroup.removeView(l);
                    }
                }
                e3.e.clear();
                it3.remove();
            }
        }
        boolean z = (clusterDataList.size() == g.d && g.b.size() == g.e) ? false : true;
        if (z) {
            g.d = clusterDataList.size();
            g.e = g.b.size();
        }
        if (z) {
            g.a();
        }
        if (!g.b.isEmpty() && g.h.a) {
            for (E e4 : g.b.values()) {
                boolean z2 = g.h.b;
                if (e4.e.isEmpty()) {
                    int size2 = e4.d.c.size();
                    float a5 = E.a(e4.b, size2);
                    List list = e4.d.c;
                    G g3 = e4.f;
                    int i5 = 0;
                    for (Object obj2 : list) {
                        int i6 = i5 + 1;
                        if (i5 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        ((Number) obj2).intValue();
                        L a6 = e4.a(i5, new B(g3, e4, i5, a5, size2));
                        int i7 = L.h;
                        L.b(a6.d, true);
                        if (a6.a() == J.a) {
                            a6.c.a(false, true);
                        }
                        i5 = i6;
                    }
                } else {
                    Iterator it5 = e4.e.iterator();
                    while (it5.hasNext()) {
                        L l2 = (L) it5.next();
                        L.b(l2.d, z2);
                        if (l2.a() == J.a) {
                            l2.c.a(false, z2);
                        }
                    }
                }
            }
        }
        return Unit.INSTANCE;
    }
}
