package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class k extends ObservableProperty {
    public final /* synthetic */ n a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(Object obj, n nVar) {
        super(obj);
        this.a = nVar;
    }

    @Override // kotlin.properties.ObservableProperty
    public final void afterChange(KProperty property, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (Intrinsics.areEqual(obj, obj2)) {
            return;
        }
        this.a.b();
    }
}
