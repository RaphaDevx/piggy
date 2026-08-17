package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.source.TorchState;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.l, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0433l extends ObservableProperty {
    public final /* synthetic */ SparkScanStateManager a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0433l(TorchState torchState, SparkScanStateManager sparkScanStateManager) {
        super(torchState);
        this.a = sparkScanStateManager;
    }

    @Override // kotlin.properties.ObservableProperty
    public final void afterChange(KProperty property, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(property, "property");
        com.scandit.datacapture.barcode.internal.module.spark.internal.b y = this.a.y();
        y.a.setValue(y, com.scandit.datacapture.barcode.internal.module.spark.internal.b.c[0], (TorchState) obj2);
    }
}
