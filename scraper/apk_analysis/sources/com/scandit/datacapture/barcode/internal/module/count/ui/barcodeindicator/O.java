package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class O {
    public static final LinkedHashMap a = new LinkedHashMap();
    public static final ExecutorService b = Executors.newSingleThreadExecutor();

    public static void a(final LinkedHashMap items) {
        Intrinsics.checkNotNullParameter(items, "items");
        b.execute(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.O$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                O.a(items);
            }
        });
    }

    public static final void b(int i) {
        com.scandit.datacapture.barcode.internal.module.count.ui.status.e eVar;
        Integer num;
        for (Map.Entry entry : a.entrySet()) {
            int intValue = ((Number) entry.getKey()).intValue();
            com.scandit.datacapture.barcode.internal.module.count.ui.status.b bVar = (com.scandit.datacapture.barcode.internal.module.count.ui.status.b) entry.getValue();
            if ((bVar instanceof com.scandit.datacapture.barcode.internal.module.count.ui.status.e) && (num = (eVar = (com.scandit.datacapture.barcode.internal.module.count.ui.status.e) bVar).b) != null && num.intValue() == i) {
                a.put(Integer.valueOf(intValue), new com.scandit.datacapture.barcode.internal.module.count.ui.status.e(eVar.a, null));
            }
        }
    }

    public static Map c() {
        Object obj = b.submit(new Callable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.O$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return O.d();
            }
        }).get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (Map) obj;
    }

    public static final Map d() {
        return MapsKt.toMap(a);
    }

    public static boolean e() {
        Object obj = b.submit(new Callable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.O$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return O.f();
            }
        }).get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return ((Boolean) obj).booleanValue();
    }

    public static final Boolean f() {
        return Boolean.valueOf(!a.isEmpty());
    }

    public static final void a(Map items) {
        Intrinsics.checkNotNullParameter(items, "$items");
        LinkedHashMap linkedHashMap = a;
        linkedHashMap.clear();
        linkedHashMap.putAll(items);
    }

    public static void a(final int i) {
        b.execute(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.O$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                O.b(i);
            }
        });
    }

    public static void a() {
        b.execute(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.O$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                O.b();
            }
        });
    }

    public static final void b() {
        a.clear();
    }
}
