package com.scandit.datacapture.barcode.internal.module.spark.ui;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.i, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0445i extends Lambda implements Function0 {
    public static final C0445i a = new C0445i();

    public C0445i() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Float.valueOf(PixelExtensionsKt.pxFromDp$default(24.0f, (Context) null, 1, (Object) null));
    }
}
