package com.scandit.datacapture.barcode.internal.module.count.ui.mappingflow;

import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0353h;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.z1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j implements f {
    public final BarcodeCountViewUiLayoutHelper a;
    public e b;
    public final z1 c;

    public j(FrameLayout container, C0353h layoutHelper) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        this.a = layoutHelper;
        this.c = new z1(container);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        int b = this.a.b();
        e eVar = this.b;
        if (eVar != null) {
            eVar.a(this.a.e(), b);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.c.b(this.b, new i(this));
    }
}
