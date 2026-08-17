package com.scandit.datacapture.barcode.internal.module.ui.popover;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class N extends Lambda implements Function0 {
    public static final N a = new N();

    public N() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(12, (Context) null, 1, (Object) null));
    }
}
