package com.scandit.datacapture.barcode.internal.module.spark.ui;

import com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class Z extends Lambda implements Function0 {
    public final /* synthetic */ SparkScanViewPresenter a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Z(SparkScanViewPresenter sparkScanViewPresenter) {
        super(0);
        this.a = sparkScanViewPresenter;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        SparkScanViewMiniPreview sparkScanViewMiniPreview;
        sparkScanViewMiniPreview = this.a.f;
        sparkScanViewMiniPreview.g();
        return Unit.INSTANCE;
    }
}
