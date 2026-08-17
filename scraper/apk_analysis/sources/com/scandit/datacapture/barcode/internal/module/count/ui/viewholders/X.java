package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class X implements O {
    public boolean a;
    public com.scandit.datacapture.barcode.internal.module.count.ui.k b;
    public com.scandit.datacapture.barcode.internal.module.count.ui.k c;
    public final z1 d;

    public X(FrameLayout container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = BarcodeCountViewDefaults.INSTANCE.getShouldShowUserGuidanceView();
        this.d = new z1(container);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.d.b(this.b, new T(this));
        this.d.b(this.c, new Q(this));
    }
}
