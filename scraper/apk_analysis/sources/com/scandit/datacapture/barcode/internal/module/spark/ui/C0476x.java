package com.scandit.datacapture.barcode.internal.module.spark.ui;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.spark.ui.x, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0476x extends Lambda implements Function0 {
    public static final C0476x a = new C0476x();

    public C0476x() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Float.valueOf(PixelExtensionsKt.pxFromDp$default(6.0f, (Context) null, 1, (Object) null));
    }
}
