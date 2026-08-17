package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.source.CameraPosition;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ObservableProperty;
import kotlin.reflect.KProperty;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.data.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0434m extends ObservableProperty {
    public final /* synthetic */ SparkScanStateManager a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0434m(CameraPosition cameraPosition, SparkScanStateManager sparkScanStateManager) {
        super(cameraPosition);
        this.a = sparkScanStateManager;
    }

    @Override // kotlin.properties.ObservableProperty
    public final void afterChange(KProperty property, Object obj, Object obj2) {
        Intrinsics.checkNotNullParameter(property, "property");
        com.scandit.datacapture.barcode.internal.module.spark.internal.b b = this.a.b();
        b.a.setValue(b, com.scandit.datacapture.barcode.internal.module.spark.internal.b.c[0], (CameraPosition) obj2);
    }
}
