package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.graphics.PointF;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItem;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindKt;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindProxy;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindViewUiListener;
import com.scandit.datacapture.barcode.internal.module.find.capture.BarcodeFindCameraManager;
import com.scandit.datacapture.barcode.internal.module.find.capture.NativeBarcodeFindItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* loaded from: classes2.dex */
public final class x0 implements BarcodeFindViewPresenter {
    public final BarcodeFindProxy a;
    public final BarcodeFindViewLayout b;
    public final BarcodeFindCameraManager c;
    public v0 d;
    public v0 e;
    public Map f;
    public Set g;
    public List h;
    public Map i;
    public boolean j;
    public final Object k;
    public BarcodeFindViewUiListener l;
    public boolean m;

    public x0(BarcodeFindProxy barcodeFind, u0 barcodeFindViewLayout, com.scandit.datacapture.barcode.internal.module.find.capture.c cameraManager) {
        Intrinsics.checkNotNullParameter(barcodeFind, "barcodeFind");
        Intrinsics.checkNotNullParameter(barcodeFindViewLayout, "barcodeFindViewLayout");
        Intrinsics.checkNotNullParameter(cameraManager, "cameraManager");
        this.a = barcodeFind;
        this.b = barcodeFindViewLayout;
        this.c = cameraManager;
        v0 v0Var = v0.c;
        this.d = v0Var;
        this.e = v0Var;
        this.f = MapsKt.emptyMap();
        this.g = SetsKt.emptySet();
        this.h = CollectionsKt.emptyList();
        this.i = MapsKt.emptyMap();
        this.k = new Object();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void a(BarcodeFindViewUiListener barcodeFindViewUiListener) {
        synchronized (this.k) {
            this.l = barcodeFindViewUiListener;
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void b() {
        int ordinal = this.e.ordinal();
        if (ordinal == 0) {
            a();
        } else if (ordinal == 1) {
            h();
        } else {
            if (ordinal != 2) {
                return;
            }
            h();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void c() {
        this.e = v0.b;
        this.b.n();
        this.b.g(false);
        this.b.f(false);
        this.b.a(this.f);
        this.b.f();
        this.b.o();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void d() {
        this.j = false;
        this.c.b();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final boolean e() {
        return this.m;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void f() {
        this.j = true;
        v0 v0Var = this.d;
        v0 v0Var2 = this.e;
        if (v0Var == v0Var2) {
            if (v0Var2 == v0.a) {
                this.c.e();
                return;
            }
            return;
        }
        int ordinal = v0Var.ordinal();
        if (ordinal == 0) {
            this.a.start();
        } else if (ordinal == 1) {
            this.a.pause();
        } else {
            if (ordinal != 2) {
                return;
            }
            this.a.stop();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void g() {
        this.d = v0.c;
        if (this.j) {
            this.a.stop();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void h() {
        this.d = v0.a;
        if (this.j) {
            this.a.start();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void i() {
        g();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void onSearchStarted() {
        v0 v0Var = v0.a;
        this.e = v0Var;
        this.b.d();
        this.b.g(false);
        this.b.f(true);
        this.b.a();
        this.b.g();
        if (this.e != v0Var || !this.m) {
            this.b.o();
            return;
        }
        Float c = this.c.c();
        if (c != null) {
            this.b.a(CollectionsKt.listOf((Object[]) new Float[]{c, Float.valueOf(1.0f)}), this.c.d());
        } else {
            this.b.o();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void a(boolean z) {
        this.m = z;
        if (!z) {
            this.b.o();
            return;
        }
        if (this.e != v0.a || !z) {
            this.b.o();
            return;
        }
        Float c = this.c.c();
        if (c != null) {
            this.b.a(CollectionsKt.listOf((Object[]) new Float[]{c, Float.valueOf(1.0f)}), this.c.d());
        } else {
            this.b.o();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void a() {
        this.d = v0.b;
        if (this.j) {
            this.a.pause();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00d8, code lost:
    
        if (r5 == null) goto L29;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.util.Collection r12, java.util.Collection r13, kotlin.jvm.functions.Function1 r14) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.find.ui.x0.a(java.util.Collection, java.util.Collection, kotlin.jvm.functions.Function1):void");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void a(Collection barcodes) {
        BarcodeFindViewUiListener barcodeFindViewUiListener;
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        this.e = v0.c;
        this.b.n();
        this.b.g(false);
        this.b.f(false);
        this.b.o();
        synchronized (this.k) {
            barcodeFindViewUiListener = this.l;
        }
        if (barcodeFindViewUiListener != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = barcodes.iterator();
            while (it.hasNext()) {
                NativeBarcodeFindItem nativeBarcodeFindItem = (NativeBarcodeFindItem) it.next();
                Map map = this.i;
                byte[] findableData = nativeBarcodeFindItem.getFindableData();
                Intrinsics.checkNotNullExpressionValue(findableData, "getFindableData(...)");
                BarcodeFindItem barcodeFindItem = (BarcodeFindItem) map.get(BarcodeFindKt.toKeyable(findableData));
                if (barcodeFindItem != null) {
                    arrayList.add(barcodeFindItem);
                }
            }
            this.b.a(new w0(barcodeFindViewUiListener, CollectionsKt.toSet(arrayList)));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void a(float f) {
        this.c.a(f);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewPresenter
    public final void a(BarcodeFindItem barcodeFindItem) {
        if (this.e == v0.a || barcodeFindItem == null) {
            return;
        }
        this.b.b(barcodeFindItem);
        this.b.a(barcodeFindItem);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.listener.SearchedItemsUpdateListener
    public final void a(LinkedHashMap searchedItems) {
        Object obj;
        Intrinsics.checkNotNullParameter(searchedItems, "searchedItems");
        this.i = searchedItems;
        Map map = this.f;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (searchedItems.containsKey(BarcodeFindKt.toKeyable(((BarcodeFindItem) entry.getValue()).getSearchOptions().getBarcodeRawData()))) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Set set = this.g;
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BarcodeFindItem barcodeFindItem = (BarcodeFindItem) searchedItems.get(BarcodeFindKt.toKeyable((byte[]) it.next()));
            Pair pair = barcodeFindItem != null ? TuplesKt.to(String.valueOf(barcodeFindItem.hashCode()), barcodeFindItem) : null;
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        Map plus = MapsKt.plus(linkedHashMap, arrayList);
        List<y0> list = this.h;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (y0 y0Var : list) {
            Iterator it2 = plus.values().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                byte[] barcodeRawData = ((BarcodeFindItem) obj).getSearchOptions().getBarcodeRawData();
                byte[] bytes = y0Var.c.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                if (Arrays.equals(barcodeRawData, bytes)) {
                    break;
                }
            }
            PointF position = y0Var.a;
            String barcodeData = y0Var.c;
            Intrinsics.checkNotNullParameter(position, "position");
            Intrinsics.checkNotNullParameter(barcodeData, "barcodeData");
            arrayList2.add(new y0(position, (BarcodeFindItem) obj, barcodeData));
        }
        this.b.a(searchedItems, plus);
        this.b.a(arrayList2);
        this.b.a(plus.size(), searchedItems.size());
        this.f = plus;
        this.h = arrayList2;
    }
}
