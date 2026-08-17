package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* renamed from: com.scandit.datacapture.barcode.internal.module.find.ui.p, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0405p extends Lambda implements Function0 {
    public static final C0405p a = new C0405p();

    public C0405p() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Float.valueOf(PixelExtensionsKt.pxFromDp$default(40.0f, (Context) null, 1, (Object) null));
    }
}
