package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.j, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0338j extends Lambda implements Function1 {
    public final /* synthetic */ r a;
    public final /* synthetic */ TrackedBarcode b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0338j(r rVar, TrackedBarcode trackedBarcode) {
        super(1);
        this.a = rVar;
        this.b = trackedBarcode;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        TrackedBarcode it = (TrackedBarcode) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        return (Brush) this.a.b.get(Integer.valueOf(this.b.getGlobalId$scandit_barcode_capture()));
    }
}
