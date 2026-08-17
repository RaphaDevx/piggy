package com.scandit.datacapture.barcode.capture;

import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCapture;
import com.scandit.datacapture.barcode.internal.module.capture.NativeBarcodeCaptureSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.capture.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0271c extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeCapture a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0271c(NativeBarcodeCapture nativeBarcodeCapture) {
        super(0);
        this.a = nativeBarcodeCapture;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        NativeBarcodeCaptureSession session = this.a.getSession();
        Intrinsics.checkNotNullExpressionValue(session, "getSession(...)");
        return session;
    }
}
