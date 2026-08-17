package com.scandit.datacapture.barcode.internal.module.spark.internal;

import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class a extends ObservableProperty {
    public final /* synthetic */ b a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(Object obj, b bVar) {
        super(obj);
        this.a = bVar;
    }

    @Override // kotlin.properties.ObservableProperty
    public final void afterChange(KProperty property, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (Intrinsics.areEqual(obj2, obj)) {
            return;
        }
        b bVar = this.a;
        Iterator it = bVar.b.iterator();
        while (it.hasNext()) {
            ((x) it.next()).a(bVar.a.getValue(bVar, b.c[0]));
        }
    }
}
