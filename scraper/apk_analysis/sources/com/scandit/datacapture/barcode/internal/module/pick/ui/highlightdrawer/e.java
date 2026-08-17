package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer;

import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public final /* synthetic */ Map a;
    public final /* synthetic */ Map b;
    public final /* synthetic */ List c;
    public final /* synthetic */ f d;
    public final /* synthetic */ List e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2, List list, f fVar, List list2) {
        super(0);
        this.a = linkedHashMap;
        this.b = linkedHashMap2;
        this.c = list;
        this.d = fVar;
        this.e = list2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Map map = this.a;
        f fVar = this.d;
        for (Map.Entry entry : map.entrySet()) {
            fVar.e.a((TrackedObject) entry.getKey(), (BarcodePickState) entry.getValue());
        }
        Map map2 = this.b;
        f fVar2 = this.d;
        for (Map.Entry entry2 : map2.entrySet()) {
            fVar2.e.b((TrackedObject) entry2.getKey(), (BarcodePickState) entry2.getValue());
        }
        List list = this.c;
        f fVar3 = this.d;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            fVar3.e.a(((Number) it.next()).intValue());
        }
        this.d.e.a(this.e);
        f fVar4 = this.d;
        fVar4.c.invoke(Boolean.valueOf(fVar4.e.a()));
        return Unit.INSTANCE;
    }
}
