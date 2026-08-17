package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.c, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0392c extends Lambda implements Function0 {
    public static final C0392c a = new C0392c();

    public C0392c() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Float.valueOf(PixelExtensionsKt.pxFromDp$default(5.0f, (Context) null, 1, (Object) null));
    }
}
