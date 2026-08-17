package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.g, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0396g extends Lambda implements Function0 {
    public static final C0396g a = new C0396g();

    public C0396g() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Lazy lazy = C0402m.f;
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(28, (Context) null, 1, (Object) null) + C0398i.a());
    }
}
