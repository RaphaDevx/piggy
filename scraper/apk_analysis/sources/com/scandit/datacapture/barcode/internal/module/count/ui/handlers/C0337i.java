package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.W;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0337i extends Lambda implements Function1 {
    public final /* synthetic */ W a;
    public final /* synthetic */ r b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0337i(W w, r rVar) {
        super(1);
        this.a = w;
        this.b = rVar;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TrackedBarcode it = (TrackedBarcode) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        int ordinal = this.a.ordinal();
        if (ordinal == 1) {
            return this.b.a.brushForAcceptedBarcodeNotInList(it);
        }
        if (ordinal == 2) {
            return this.b.a.brushForRejectedBarcodeNotInList(it);
        }
        if (ordinal == 3) {
            return this.b.a.brushForTrackedBarcodeNotInList(it);
        }
        throw new IllegalStateException(("Unreachable " + this.a).toString());
    }
}
