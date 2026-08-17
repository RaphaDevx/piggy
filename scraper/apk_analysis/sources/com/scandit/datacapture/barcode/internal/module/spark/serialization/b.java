package com.scandit.datacapture.barcode.internal.module.spark.serialization;

import com.scandit.datacapture.barcode.spark.ui.SparkScanToastSettings;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class b extends Lambda implements Function0 {
    public static final b a = new b();

    public b() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return new SparkScanToastSettings(false, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }
}
