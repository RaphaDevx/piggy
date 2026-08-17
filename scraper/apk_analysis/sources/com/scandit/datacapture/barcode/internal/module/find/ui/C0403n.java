package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.n, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0403n extends Lambda implements Function0 {
    public static final C0403n a = new C0403n();

    public C0403n() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(350, (Context) null, 1, (Object) null));
    }
}
