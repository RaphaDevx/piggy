package com.scandit.datacapture.barcode.internal.module.spark.ui;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.m, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0449m extends Lambda implements Function0 {
    public static final C0449m a = new C0449m();

    public C0449m() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(200, (Context) null, 1, (Object) null));
    }
}
