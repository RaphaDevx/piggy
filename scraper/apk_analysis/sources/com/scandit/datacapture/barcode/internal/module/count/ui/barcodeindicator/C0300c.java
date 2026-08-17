package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0300c extends Lambda implements Function1 {
    public final /* synthetic */ Function2 a;
    public final /* synthetic */ TrackedBarcode b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0300c(C0307j c0307j, TrackedBarcode trackedBarcode) {
        super(1);
        this.a = c0307j;
        this.b = trackedBarcode;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        W statusParam = (W) obj;
        Intrinsics.checkNotNullParameter(statusParam, "statusParam");
        return (Brush) this.a.invoke(this.b, statusParam);
    }
}
