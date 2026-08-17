package com.scandit.datacapture.core.internal.sdk.ui.cardscarousel;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class f extends Lambda implements Function0 {
    public static final f a = new f();

    public f() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return Integer.valueOf(PixelExtensionsKt.pxFromDp$default(8, (Context) null, 1, (Object) null));
    }
}
