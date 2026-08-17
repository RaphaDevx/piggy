package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class Z0 implements S0 {
    public final BarcodeCountViewUiLayoutHelper a;
    public boolean b;
    public Function0 c;
    public com.scandit.datacapture.barcode.internal.module.count.ui.p d;
    public final z1 e;

    public Z0(C0353h layoutHelper, RelativeLayout container) {
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = layoutHelper;
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        this.b = barcodeCountViewDefaults.getShouldShowExitButton();
        barcodeCountViewDefaults.getStatusModeButtonContentDescription();
        this.c = W0.a;
        this.e = new z1(container);
    }

    public final void a(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        com.scandit.datacapture.barcode.internal.module.count.ui.p pVar = this.d;
        if (pVar == null) {
            return;
        }
        pVar.setContentDescription(value);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.e.b(this.d, new U0(this));
    }

    public final RelativeLayout.LayoutParams c() {
        Context context = ((RelativeLayout) this.e.a).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(10);
            layoutParams.topMargin = this.a.g();
            layoutParams.addRule(21);
            layoutParams.setMarginEnd(this.a.i());
            return layoutParams;
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(21);
        int i = AbstractC0341b.a;
        layoutParams2.setMarginEnd(AbstractC0341b.d);
        layoutParams2.addRule(12);
        layoutParams2.bottomMargin = this.a.i();
        return layoutParams2;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.count.ui.p pVar;
        com.scandit.datacapture.barcode.internal.module.count.ui.p pVar2 = this.d;
        if (pVar2 != null) {
            pVar2.setVisibility(this.b ? 0 : 4);
        }
        if (this.b && (pVar = this.d) != null) {
            pVar.setLayoutParams(c());
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.p pVar3 = this.d;
        if (pVar3 == null) {
            return;
        }
        pVar3.setLayoutParams(c());
    }
}
