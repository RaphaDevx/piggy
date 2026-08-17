package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class K implements G {
    public static final int e = PixelExtensionsKt.pxFromDp$default(16, (Context) null, 1, (Object) null);
    public static final int f = PixelExtensionsKt.pxFromDp$default(80, (Context) null, 1, (Object) null);
    public boolean a;
    public boolean b;
    public com.scandit.datacapture.barcode.internal.module.ui.e c;
    public final z1 d;

    public K(FrameLayout container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = BarcodeCountViewDefaults.INSTANCE.getShouldShowListProgressBar();
        this.d = new z1(container);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.ui.e eVar;
        com.scandit.datacapture.barcode.internal.module.ui.e eVar2 = this.c;
        if (eVar2 != null) {
            eVar2.setVisibility((this.b && this.a) ? 0 : 4);
        }
        if (this.b && this.a && (eVar = this.c) != null) {
            eVar.setLayoutParams(c());
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.d.b(this.c, new I(this));
    }

    public final FrameLayout.LayoutParams c() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, PixelExtensionsKt.pxFromDp$default(48, (Context) null, 1, (Object) null), 48);
        Context context = ((FrameLayout) this.d.a).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        int i = com.scandit.datacapture.barcode.internal.module.extensions.b.a(context) ? f : e;
        layoutParams.setMarginStart(i);
        layoutParams.setMarginEnd(i);
        return layoutParams;
    }
}
