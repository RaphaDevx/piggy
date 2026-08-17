package com.scandit.datacapture.barcode.internal.module.count.capture;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class InternalBarcodeCountSessionCallback {
    private final Function0 a;
    private final Function0 b;
    private final Function2 c;

    public InternalBarcodeCountSessionCallback(com.scandit.datacapture.barcode.count.capture.c reset, com.scandit.datacapture.barcode.count.capture.d toJson, com.scandit.datacapture.barcode.count.capture.e getSpatialMap) {
        Intrinsics.checkNotNullParameter(reset, "reset");
        Intrinsics.checkNotNullParameter(toJson, "toJson");
        Intrinsics.checkNotNullParameter(getSpatialMap, "getSpatialMap");
        this.a = reset;
        this.b = toJson;
        this.c = getSpatialMap;
    }

    public final Function2 a() {
        return this.c;
    }

    public final Function0 b() {
        return this.a;
    }

    public final Function0 c() {
        return this.b;
    }
}
