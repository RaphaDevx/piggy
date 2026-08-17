package com.scandit.datacapture.frameworks.barcode.count.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusProviderCallback;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarcodeCountStatusProviderRequest.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountStatusProviderRequest;", "", "barcodes", "", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProviderCallback;", "requestId", "", "(Ljava/util/List;Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProviderCallback;Ljava/lang/String;)V", "getBarcodes", "()Ljava/util/List;", "getCallback", "()Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProviderCallback;", "getRequestId", "()Ljava/lang/String;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountStatusProviderRequest {
    public static final String ID = "requestId";
    private final List<TrackedBarcode> barcodes;
    private final BarcodeCountStatusProviderCallback callback;
    private final String requestId;

    public BarcodeCountStatusProviderRequest(List<TrackedBarcode> barcodes, BarcodeCountStatusProviderCallback callback, String requestId) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.barcodes = barcodes;
        this.callback = callback;
        this.requestId = requestId;
    }

    public final List<TrackedBarcode> getBarcodes() {
        return this.barcodes;
    }

    public final BarcodeCountStatusProviderCallback getCallback() {
        return this.callback;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ BarcodeCountStatusProviderRequest(java.util.List r1, com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusProviderCallback r2, java.lang.String r3, int r4, kotlin.jvm.internal.DefaultConstructorMarker r5) {
        /*
            r0 = this;
            r4 = r4 & 4
            if (r4 == 0) goto L11
            java.util.UUID r3 = java.util.UUID.randomUUID()
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "toString(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
        L11:
            r0.<init>(r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.frameworks.barcode.count.data.BarcodeCountStatusProviderRequest.<init>(java.util.List, com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusProviderCallback, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getRequestId() {
        return this.requestId;
    }
}
