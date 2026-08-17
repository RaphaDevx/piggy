package com.scandit.datacapture.barcode.internal.module.ui.popover;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.ui.popover.d, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0482d extends Lambda implements Function0 {
    public static final C0482d a = new C0482d();

    public C0482d() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(10, (Context) null, 1, (Object) null));
    }
}
