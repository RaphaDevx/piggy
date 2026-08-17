package com.scandit.datacapture.barcode.internal.module.count.ui;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListStatus;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.M;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.N;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function4 {
    public final /* synthetic */ BarcodeCountBasicOverlay a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(BarcodeCountBasicOverlay barcodeCountBasicOverlay) {
        super(4);
        this.a = barcodeCountBasicOverlay;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        TrackedBarcode barcode = (TrackedBarcode) obj;
        N scanStatus = (N) obj2;
        M listStatus = (M) obj3;
        NativeBarcodeCountNotInListStatus notInListStatus = (NativeBarcodeCountNotInListStatus) obj4;
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(scanStatus, "scanStatus");
        Intrinsics.checkNotNullParameter(listStatus, "listStatus");
        Intrinsics.checkNotNullParameter(notInListStatus, "notInListStatus");
        int ordinal = scanStatus.ordinal();
        if (ordinal == 0) {
            int ordinal2 = listStatus.ordinal();
            if (ordinal2 == 0 || ordinal2 == 1) {
                this.a.didTapScannedBarcode(barcode);
            } else if (ordinal2 == 2) {
                int i = d.a[notInListStatus.ordinal()];
                if (i == 1) {
                    this.a.didTapAcceptedBarcode(barcode);
                } else if (i != 2) {
                    this.a.didTapNotInListBarcode(barcode);
                } else {
                    this.a.didTapRejectedBarcode(barcode);
                }
            }
        } else if (ordinal == 1) {
            this.a.didTapUnscannedBarcode(barcode);
        } else if (ordinal == 2) {
            this.a.didTapFilteredBarcode(barcode);
        }
        return Unit.INSTANCE;
    }
}
