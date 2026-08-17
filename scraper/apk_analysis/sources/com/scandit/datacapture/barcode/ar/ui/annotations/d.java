package com.scandit.datacapture.barcode.ar.ui.annotations;

import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationWidthPreset;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class d extends Lambda implements Function0 {
    public final /* synthetic */ BarcodeArInfoAnnotation a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(BarcodeArInfoAnnotation barcodeArInfoAnnotation) {
        super(0);
        this.a = barcodeArInfoAnnotation;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        int backgroundColor = this.a.getBackgroundColor();
        BarcodeArInfoAnnotationWidthPreset width = this.a.getWidth();
        List<BarcodeArInfoAnnotationBodyComponent> body = this.a.getBody();
        BarcodeArInfoAnnotation barcodeArInfoAnnotation = this.a;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(body, 10));
        int i = 0;
        for (Object obj : body) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            BarcodeArInfoAnnotationBodyComponent barcodeArInfoAnnotationBodyComponent = (BarcodeArInfoAnnotationBodyComponent) obj;
            arrayList.add(new BarcodeArBodyElementRowDataHolder(barcodeArInfoAnnotationBodyComponent, barcodeArInfoAnnotation.isEntireAnnotationTappable(), barcodeArInfoAnnotation.getWidth(), new c(barcodeArInfoAnnotation, i, barcodeArInfoAnnotationBodyComponent)));
            i = i2;
        }
        return new BarcodeArBodyElementDataHolder(backgroundColor, width, arrayList);
    }
}
