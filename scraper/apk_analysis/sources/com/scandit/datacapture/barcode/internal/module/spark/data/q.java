package com.scandit.datacapture.barcode.internal.module.spark.data;

import com.scandit.datacapture.core.internal.sdk.source.NativeAndroidCamera;
import com.scandit.datacapture.core.source.Camera;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class q extends Lambda implements Function1 {
    public final /* synthetic */ O a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(O o) {
        super(1);
        this.a = o;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        NativeAndroidCamera a;
        NativeAndroidCamera a2;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Camera camera = this.a.f;
        if (camera != null && (a2 = camera.getA()) != null) {
            a2.setBatterySavingMode(booleanValue);
        }
        Camera camera2 = this.a.g;
        if (camera2 != null && (a = camera2.getA()) != null) {
            a.setBatterySavingMode(booleanValue);
        }
        return Unit.INSTANCE;
    }
}
