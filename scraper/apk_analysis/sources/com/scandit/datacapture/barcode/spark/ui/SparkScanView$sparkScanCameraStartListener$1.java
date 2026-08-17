package com.scandit.datacapture.barcode.spark.ui;

import com.scandit.datacapture.barcode.internal.module.spark.data.InterfaceC0429h;
import com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0004¨\u0006\u0006"}, d2 = {"com/scandit/datacapture/barcode/spark/ui/SparkScanView$sparkScanCameraStartListener$1", "Lcom/scandit/datacapture/barcode/internal/module/spark/data/h;", "", "onCameraOnStart", "()V", "onCameraOnEnd", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class SparkScanView$sparkScanCameraStartListener$1 implements InterfaceC0429h {
    final /* synthetic */ SparkScanView a;

    SparkScanView$sparkScanCameraStartListener$1(SparkScanView sparkScanView) {
        this.a = sparkScanView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(SparkScanView this$0) {
        SparkScanViewMiniPreview sparkScanViewMiniPreview;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        sparkScanViewMiniPreview = this$0.e;
        sparkScanViewMiniPreview.x();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(SparkScanView this$0) {
        SparkScanViewMiniPreview sparkScanViewMiniPreview;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        sparkScanViewMiniPreview = this$0.e;
        sparkScanViewMiniPreview.r();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.InterfaceC0429h
    public void onCameraOnEnd() {
        final SparkScanView sparkScanView = this.a;
        sparkScanView.post(new Runnable() { // from class: com.scandit.datacapture.barcode.spark.ui.SparkScanView$sparkScanCameraStartListener$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SparkScanView$sparkScanCameraStartListener$1.a(SparkScanView.this);
            }
        });
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.InterfaceC0429h
    public void onCameraOnStart() {
        final SparkScanView sparkScanView = this.a;
        sparkScanView.post(new Runnable() { // from class: com.scandit.datacapture.barcode.spark.ui.SparkScanView$sparkScanCameraStartListener$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SparkScanView$sparkScanCameraStartListener$1.b(SparkScanView.this);
            }
        });
    }
}
