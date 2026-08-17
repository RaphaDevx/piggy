package com.scandit.datacapture.barcode.internal.sdk;

import com.scandit.datacapture.barcode.internal.module.pick.capture.BarcodePickInternal;
import com.scandit.datacapture.barcode.internal.module.pick.capture.NativeBarcodePick;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class g extends Lambda implements Function0 {
    public final /* synthetic */ NativeBarcodePick a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(NativeBarcodePick nativeBarcodePick) {
        super(0);
        this.a = nativeBarcodePick;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new BarcodePickInternal(this.a);
    }
}
