package com.scandit.datacapture.barcode.internal.module.count.ui.cluster;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class C extends Lambda implements Function0 {
    public static final C a = new C();

    public C() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(-2, (Context) null, 1, (Object) null));
    }
}
