package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotationDataHolder;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class D extends ViewOutlineProvider {
    public final /* synthetic */ BarcodeArInfoAnnotationDataHolder a;

    public D(BarcodeArInfoAnnotationDataHolder barcodeArInfoAnnotationDataHolder) {
        this.a = barcodeArInfoAnnotationDataHolder;
    }

    @Override // android.view.ViewOutlineProvider
    public final void getOutline(View view, Outline outline) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(outline, "outline");
        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), this.a.getWidth().toPaddingAndCornerRadius$scandit_barcode_capture());
    }
}
