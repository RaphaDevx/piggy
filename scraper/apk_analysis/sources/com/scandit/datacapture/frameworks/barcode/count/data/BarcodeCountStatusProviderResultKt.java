package com.scandit.datacapture.frameworks.barcode.count.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountStatus;
import kotlin.Metadata;

/* compiled from: BarcodeCountStatusProviderResult.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"toBarcodeCountStatus", "Lcom/scandit/datacapture/barcode/count/ui/view/BarcodeCountStatus;", "", "scandit-datacapture-frameworks-barcode_release"}, k = 2, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BarcodeCountStatusProviderResultKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final BarcodeCountStatus toBarcodeCountStatus(String str) {
        switch (str.hashCode()) {
            case -1834264542:
                if (str.equals("lowStock")) {
                    return BarcodeCountStatus.LOW_STOCK;
                }
                break;
            case -1309235419:
                if (str.equals("expired")) {
                    return BarcodeCountStatus.EXPIRED;
                }
                break;
            case -629572298:
                if (str.equals("notAvailable")) {
                    return BarcodeCountStatus.NOT_AVAILABLE;
                }
                break;
            case -607427472:
                if (str.equals("fragile")) {
                    return BarcodeCountStatus.FRAGILE;
                }
                break;
            case -190055607:
                if (str.equals("qualityCheck")) {
                    return BarcodeCountStatus.QUALITY_CHECK;
                }
                break;
            case 113405357:
                if (str.equals("wrong")) {
                    return BarcodeCountStatus.WRONG;
                }
                break;
            case 985221495:
                if (str.equals("expiringSoon")) {
                    return BarcodeCountStatus.EXPIRING_SOON;
                }
                break;
        }
        return BarcodeCountStatus.NONE;
    }
}
