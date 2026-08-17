package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.W;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class q extends Lambda implements Function1 {
    public final /* synthetic */ W a;
    public final /* synthetic */ r b;
    public final /* synthetic */ TrackedBarcode c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(W w, r rVar, TrackedBarcode trackedBarcode) {
        super(1);
        this.a = w;
        this.b = rVar;
        this.c = trackedBarcode;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        boolean containsKey;
        TrackedBarcode it = (TrackedBarcode) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        int ordinal = this.a.ordinal();
        if (ordinal == 1) {
            containsKey = this.b.e.containsKey(Integer.valueOf(this.c.getGlobalId$scandit_barcode_capture()));
        } else if (ordinal == 2) {
            containsKey = this.b.f.containsKey(Integer.valueOf(this.c.getGlobalId$scandit_barcode_capture()));
        } else {
            if (ordinal != 3) {
                throw new IllegalStateException(("Unreachable " + this.a).toString());
            }
            containsKey = this.b.d.containsKey(Integer.valueOf(this.c.getGlobalId$scandit_barcode_capture()));
        }
        return Boolean.valueOf(containsKey);
    }
}
