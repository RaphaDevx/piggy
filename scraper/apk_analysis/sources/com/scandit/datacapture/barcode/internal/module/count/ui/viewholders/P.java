package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.widget.FrameLayout;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes2.dex */
public final class P extends Lambda implements Function1 {
    public final /* synthetic */ X a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public P(X x) {
        super(1);
        this.a = x;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Context it = (Context) obj;
        Intrinsics.checkNotNullParameter(it, "it");
        X x = this.a;
        x.getClass();
        com.scandit.datacapture.barcode.internal.module.count.ui.k kVar = new com.scandit.datacapture.barcode.internal.module.count.ui.k(it);
        kVar.setLayoutParams(new FrameLayout.LayoutParams(PixelExtensionsKt.pxFromDp$default(100, (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(100, (Context) null, 1, (Object) null), 17));
        kVar.c = true;
        x.c = kVar;
        return kVar;
    }
}
