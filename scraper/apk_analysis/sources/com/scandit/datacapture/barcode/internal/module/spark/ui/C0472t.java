package com.scandit.datacapture.barcode.internal.module.spark.ui;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.t, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0472t extends Lambda implements Function0 {
    public static final C0472t a = new C0472t();

    public C0472t() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(8, (Context) null, 1, (Object) null));
    }
}
