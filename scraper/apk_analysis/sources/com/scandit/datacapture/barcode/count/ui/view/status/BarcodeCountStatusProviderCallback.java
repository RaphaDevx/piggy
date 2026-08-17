package com.scandit.datacapture.barcode.count.ui.view.status;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusProviderCallback;
import com.scandit.datacapture.tools.internal.sdk.NativeImpl;
import com.scandit.datacapture.tools.internal.sdk.ProxyFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0003H\u0097\u0001J\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0097\u0001¨\u0006\n"}, d2 = {"Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProviderCallback;", "Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProviderCallbackProxy;", "impl", "Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountStatusProviderCallback;", "(Lcom/scandit/datacapture/barcode/count/internal/module/capture/NativeBarcodeCountStatusProviderCallback;)V", "_impl", "onStatusReady", "", "statusResult", "Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusResult;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountStatusProviderCallback implements BarcodeCountStatusProviderCallbackProxy {
    private final /* synthetic */ BarcodeCountStatusProviderCallbackProxyAdapter a;

    /* JADX WARN: Multi-variable type inference failed */
    public BarcodeCountStatusProviderCallback(NativeBarcodeCountStatusProviderCallback impl) {
        Intrinsics.checkNotNullParameter(impl, "impl");
        this.a = new BarcodeCountStatusProviderCallbackProxyAdapter(impl, null, 2, 0 == true ? 1 : 0);
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusProviderCallbackProxy
    @NativeImpl
    /* renamed from: _impl */
    public NativeBarcodeCountStatusProviderCallback getA() {
        return this.a.getA();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusProviderCallbackProxy
    @ProxyFunction
    public void onStatusReady(BarcodeCountStatusResult statusResult) {
        Intrinsics.checkNotNullParameter(statusResult, "statusResult");
        this.a.onStatusReady(statusResult);
    }
}
