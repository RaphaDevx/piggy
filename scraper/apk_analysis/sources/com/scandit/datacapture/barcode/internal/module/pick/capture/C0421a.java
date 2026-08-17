package com.scandit.datacapture.barcode.internal.module.pick.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.pick.capture.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0421a extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodePick a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0421a(NativeBarcodePick nativeBarcodePick) {
        super(0);
        this.a = nativeBarcodePick;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        NativeBarcodePickSession session = this.a.getSession();
        Intrinsics.checkNotNullExpressionValue(session, "getSession(...)");
        return session;
    }
}
