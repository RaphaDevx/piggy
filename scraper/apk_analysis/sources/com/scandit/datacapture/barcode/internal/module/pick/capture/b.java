package com.scandit.datacapture.barcode.internal.module.pick.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodePick a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(NativeBarcodePick nativeBarcodePick) {
        super(0);
        this.a = nativeBarcodePick;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        NativeBarcodePickScanningSession scanningSession = this.a.getSession().getScanningSession();
        Intrinsics.checkNotNullExpressionValue(scanningSession, "getScanningSession(...)");
        return scanningSession;
    }
}
