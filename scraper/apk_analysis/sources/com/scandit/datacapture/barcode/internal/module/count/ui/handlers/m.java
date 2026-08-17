package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.W;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class m extends Lambda implements Function1 {
    public final /* synthetic */ W a;
    public final /* synthetic */ r b;
    public final /* synthetic */ TrackedBarcode c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(W w, r rVar, TrackedBarcode trackedBarcode) {
        super(1);
        this.a = w;
        this.b = rVar;
        this.c = trackedBarcode;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TrackedBarcode it = (TrackedBarcode) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        int ordinal = this.a.ordinal();
        if (ordinal == 1) {
            return (Brush) this.b.e.get(Integer.valueOf(this.c.getGlobalId$scandit_barcode_capture()));
        }
        if (ordinal == 2) {
            return (Brush) this.b.f.get(Integer.valueOf(this.c.getGlobalId$scandit_barcode_capture()));
        }
        if (ordinal == 3) {
            return (Brush) this.b.d.get(Integer.valueOf(this.c.getGlobalId$scandit_barcode_capture()));
        }
        throw new IllegalStateException(("Unreachable " + this.a).toString());
    }
}
