package com.scandit.datacapture.barcode.internal.sdk;

import com.scandit.datacapture.barcode.count.capture.list.TargetBarcode;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeTargetBarcode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class e extends Lambda implements Function0 {
    public final /* synthetic */ NativeTargetBarcode a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(NativeTargetBarcode nativeTargetBarcode) {
        super(0);
        this.a = nativeTargetBarcode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new TargetBarcode(this.a);
    }
}
