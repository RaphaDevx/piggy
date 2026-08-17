package com.scandit.datacapture.barcode.internal.module.find.ui;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class W extends Lambda implements Function1 {
    public final /* synthetic */ boolean a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public W(boolean z) {
        super(1);
        this.a = z;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.f it = (com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.f) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        if (this.a) {
            it.a.start();
        } else {
            it.a.stop();
        }
        return Unit.INSTANCE;
    }
}
