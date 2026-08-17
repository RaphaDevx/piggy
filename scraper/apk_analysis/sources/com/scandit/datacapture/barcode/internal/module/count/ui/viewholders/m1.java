package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class m1 extends ObservableProperty {
    public final /* synthetic */ n1 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m1(Boolean bool, n1 n1Var) {
        super(bool);
        this.a = n1Var;
    }

    @Override // kotlin.properties.ObservableProperty
    public final void afterChange(KProperty property, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (Intrinsics.areEqual(obj, obj2)) {
            return;
        }
        ((Boolean) obj2).getClass();
        this.a.c();
    }
}
