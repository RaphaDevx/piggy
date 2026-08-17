package com.scandit.datacapture.barcode.internal.module.spark.ui;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.k, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0447k extends Lambda implements Function0 {
    public static final C0447k a = new C0447k();

    public C0447k() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(64, (Context) null, 1, (Object) null));
    }
}
