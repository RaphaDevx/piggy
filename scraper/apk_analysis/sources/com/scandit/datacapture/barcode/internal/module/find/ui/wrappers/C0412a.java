package com.scandit.datacapture.barcode.internal.module.find.ui.wrappers;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.a, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0412a extends Lambda implements Function0 {
    public static final C0412a a = new C0412a();

    public C0412a() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(8, (Context) null, 1, (Object) null));
    }
}
