package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.popover;

import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationAnchor;
import com.scandit.datacapture.core.common.geometry.Anchor;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class c extends Lambda implements Function0 {
    public final /* synthetic */ e a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(e eVar) {
        super(0);
        this.a = eVar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        BarcodeArPopoverAnnotationAnchor barcodeArPopoverAnnotationAnchor = this.a.c;
        Intrinsics.checkNotNullParameter(barcodeArPopoverAnnotationAnchor, "<this>");
        int i = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.a.c[barcodeArPopoverAnnotationAnchor.ordinal()];
        if (i == 1) {
            return Anchor.TOP_CENTER;
        }
        if (i == 2) {
            return Anchor.BOTTOM_CENTER;
        }
        if (i == 3) {
            return Anchor.CENTER_LEFT;
        }
        if (i == 4) {
            return Anchor.CENTER_RIGHT;
        }
        throw new NoWhenBranchMatchedException();
    }
}
