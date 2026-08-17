package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.view.View;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final /* synthetic */ class r extends FunctionReferenceImpl implements Function1 {
    public r() {
        super(1, com.scandit.datacapture.barcode.internal.module.ui.a.a, com.scandit.datacapture.barcode.internal.module.ui.a.class, "isTallFormFactor", "isTallFormFactor(Landroid/view/View;)Z", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        View p0 = (View) obj;
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((com.scandit.datacapture.barcode.internal.module.ui.a) this.receiver).getClass();
        return Boolean.valueOf(com.scandit.datacapture.barcode.internal.module.ui.a.a(p0));
    }
}
