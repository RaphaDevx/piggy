package com.scandit.datacapture.barcode.batch.capture;

import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTracking;
import com.scandit.datacapture.barcode.batch.internal.module.capture.NativeBarcodeTrackingSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.batch.capture.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0261a extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeTracking a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0261a(NativeBarcodeTracking nativeBarcodeTracking) {
        super(0);
        this.a = nativeBarcodeTracking;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        NativeBarcodeTrackingSession session = this.a.getSession();
        Intrinsics.checkNotNullExpressionValue(session, "getSession(...)");
        return session;
    }
}
