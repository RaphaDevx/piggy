package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class w extends Lambda implements Function0 {
    public static final w a = new w();

    public w() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf((((Number) E.r.getValue()).intValue() - PixelExtensionsKt.pxFromDp$default(1, (Context) null, 1, (Object) null)) + 2);
    }
}
