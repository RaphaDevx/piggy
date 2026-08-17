package com.scandit.datacapture.barcode.selection.capture;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0003¨\u0006\u0004"}, d2 = {"toJson", "", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionFreezeBehavior;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionTapBehavior;", "scandit-barcode-capture"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeSelectionTapSelectionKt {
    public static final /* synthetic */ String toJson(BarcodeSelectionFreezeBehavior barcodeSelectionFreezeBehavior) {
        Intrinsics.checkNotNullParameter(barcodeSelectionFreezeBehavior, "<this>");
        return BarcodeSelectionFreezeBehaviorSerializer.toJson(barcodeSelectionFreezeBehavior);
    }

    public static final /* synthetic */ String toJson(BarcodeSelectionTapBehavior barcodeSelectionTapBehavior) {
        Intrinsics.checkNotNullParameter(barcodeSelectionTapBehavior, "<this>");
        return BarcodeSelectionTapBehaviorSerializer.toJson(barcodeSelectionTapBehavior);
    }
}
