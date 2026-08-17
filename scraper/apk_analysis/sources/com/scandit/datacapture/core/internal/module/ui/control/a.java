package com.scandit.datacapture.core.internal.module.ui.control;

import android.content.Context;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.Lazy;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class a extends Lambda implements Function0 {
    public static final a a = new a();

    public a() {
        super(0);
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Lazy lazy;
        int pxFromDp$default = PixelExtensionsKt.pxFromDp$default(32, (Context) null, 1, (Object) null);
        lazy = ToggleImageButton.e;
        return Integer.valueOf(pxFromDp$default + ((int) (((Number) lazy.getValue()).floatValue() * 2)));
    }
}
