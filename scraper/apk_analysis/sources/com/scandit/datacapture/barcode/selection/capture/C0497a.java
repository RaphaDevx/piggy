package com.scandit.datacapture.barcode.selection.capture;

import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelection;
import com.scandit.datacapture.barcode.selection.internal.module.capture.NativeBarcodeSelectionSession;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.selection.capture.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0497a extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodeSelection a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0497a(NativeBarcodeSelection nativeBarcodeSelection) {
        super(0);
        this.a = nativeBarcodeSelection;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        NativeBarcodeSelectionSession session = this.a.getSession();
        Intrinsics.checkNotNullExpressionValue(session, "getSession(...)");
        return session;
    }
}
