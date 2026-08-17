package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class y1 implements s1 {
    public boolean a;
    public com.scandit.datacapture.barcode.internal.module.ui.n b;
    public final z1 c;

    public y1(FrameLayout container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = BarcodeCountViewDefaults.INSTANCE.getShouldShowUserGuidanceView();
        this.c = new z1(container);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.c.b(this.b, new u1(this));
    }
}
