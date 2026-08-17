package com.scandit.datacapture.barcode.ar.serialization;

import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationTrigger;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/scandit/datacapture/barcode/ar/serialization/BarcodeArAnnotationTriggerDeserializer;", "", "", "json", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "fromJson", "(Ljava/lang/String;)Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationTrigger;", "scandit-barcode-capture"}, k = 1, mv = {1, 9, 0})
/* loaded from: classes2.dex */
public final class BarcodeArAnnotationTriggerDeserializer {
    public static final BarcodeArAnnotationTriggerDeserializer INSTANCE = new BarcodeArAnnotationTriggerDeserializer();

    private BarcodeArAnnotationTriggerDeserializer() {
    }

    @JvmStatic
    public static final BarcodeArAnnotationTrigger fromJson(String json) {
        Intrinsics.checkNotNullParameter(json, "json");
        int hashCode = json.hashCode();
        if (hashCode != -227406257) {
            if (hashCode != 436173045) {
                if (hashCode == 1646060317 && json.equals("barcodeScan")) {
                    return BarcodeArAnnotationTrigger.BARCODE_SCAN;
                }
            } else if (json.equals("highlightTapAndBarcodeScan")) {
                return BarcodeArAnnotationTrigger.HIGHLIGHT_TAP_AND_BARCODE_SCAN;
            }
        } else if (json.equals("highlightTap")) {
            return BarcodeArAnnotationTrigger.HIGHLIGHT_TAP;
        }
        throw new IllegalArgumentException("No match found for ".concat(json));
    }
}
