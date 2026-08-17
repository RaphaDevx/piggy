package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class t extends Lambda implements Function0 {
    public static final t a = new t();

    public t() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Float.valueOf(PixelExtensionsKt.pxFromDp$default(12.0f, (Context) null, 1, (Object) null));
    }
}
