package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.internal.module.spark.data.C0435n;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c0 extends Lambda implements Function1 {
    public final /* synthetic */ SparkScanViewPresenter a;
    public final /* synthetic */ Function0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(SparkScanViewPresenter sparkScanViewPresenter, Function0 function0) {
        super(1);
        this.a = sparkScanViewPresenter;
        this.b = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        SparkScanViewCameraManager sparkScanViewCameraManager;
        SparkScanStateManager sparkScanStateManager;
        ((Boolean) obj).getClass();
        sparkScanViewCameraManager = this.a.e;
        sparkScanStateManager = this.a.b;
        sparkScanViewCameraManager.a(sparkScanStateManager.x(), C0435n.a);
        this.b.invoke();
        return Unit.INSTANCE;
    }
}
