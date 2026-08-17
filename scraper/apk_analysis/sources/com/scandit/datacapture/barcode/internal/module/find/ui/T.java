package com.scandit.datacapture.barcode.internal.module.find.ui;

import com.scandit.datacapture.core.ui.DataCaptureView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class T extends Lambda implements Function1 {
    public final /* synthetic */ Function0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T(Function0 function0) {
        super(1);
        this.a = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DataCaptureView it = (DataCaptureView) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        this.a.invoke();
        return Unit.INSTANCE;
    }
}
