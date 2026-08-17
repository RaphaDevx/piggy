package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.e, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0302e {
    public final TrackedBarcode a;
    public final V b;
    public final float c;

    public C0302e(TrackedBarcode barcode, V overlayInfo, float f) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(overlayInfo, "overlayInfo");
        this.a = barcode;
        this.b = overlayInfo;
        this.c = f;
    }
}
