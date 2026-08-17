package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class O0 implements K0 {
    public final BarcodeCountViewUiLayoutHelper a;
    public boolean b;
    public String c;
    public Function0 d;
    public com.scandit.datacapture.barcode.internal.module.count.ui.o e;
    public final z1 f;

    public O0(C0353h layoutHelper, RelativeLayout container) {
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = layoutHelper;
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        this.b = barcodeCountViewDefaults.getShouldShowExitButton();
        this.c = barcodeCountViewDefaults.getSingleScanButtonContentDescription();
        this.d = N0.a;
        this.f = new z1(container);
    }

    public final void a(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.c = value;
        com.scandit.datacapture.barcode.internal.module.count.ui.o oVar = this.e;
        if (oVar == null) {
            return;
        }
        oVar.setContentDescription(value);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.f.b(this.e, new M0(this));
    }

    public final RelativeLayout.LayoutParams c() {
        Context context = ((RelativeLayout) this.f.a).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(12);
            layoutParams.bottomMargin = this.a.g();
            layoutParams.addRule(21);
            layoutParams.setMarginEnd(this.a.i());
            return layoutParams;
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(20);
        int i = AbstractC0341b.a;
        layoutParams2.setMarginStart(AbstractC0341b.d);
        layoutParams2.addRule(12);
        layoutParams2.bottomMargin = this.a.i();
        return layoutParams2;
    }

    public static final void a(O0 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d.invoke();
    }

    public final com.scandit.datacapture.barcode.internal.module.count.ui.o a(Context context) {
        com.scandit.datacapture.barcode.internal.module.count.ui.o oVar = new com.scandit.datacapture.barcode.internal.module.count.ui.o(context);
        oVar.setLayoutParams(c());
        oVar.setVisibility(4);
        oVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.O0$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                O0.a(O0.this, view);
            }
        });
        oVar.setContentDescription(this.c);
        oVar.setId(AbstractC0341b.j);
        this.e = oVar;
        return oVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.count.ui.o oVar;
        com.scandit.datacapture.barcode.internal.module.count.ui.o oVar2 = this.e;
        if (oVar2 != null) {
            oVar2.setVisibility(this.b ? 0 : 4);
        }
        if (this.b && (oVar = this.e) != null) {
            oVar.setLayoutParams(c());
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.o oVar3 = this.e;
        if (oVar3 == null) {
            return;
        }
        oVar3.setLayoutParams(c());
    }
}
