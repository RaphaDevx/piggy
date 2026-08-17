package com.scandit.datacapture.barcode.ar.serialization;

import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class l extends Lambda implements Function0 {
    public static final l a = new l();

    public l() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return BarcodeAr.INSTANCE.createRecommendedCameraSettings();
    }
}
