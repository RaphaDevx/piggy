package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

/* loaded from: classes2.dex */
public final class L extends ObservableProperty {
    public final /* synthetic */ N a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L(Integer num, N n) {
        super(num);
        this.a = n;
    }

    @Override // kotlin.properties.ObservableProperty
    public final void afterChange(KProperty property, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (Intrinsics.areEqual(obj, obj2)) {
            return;
        }
        this.a.d();
    }
}
