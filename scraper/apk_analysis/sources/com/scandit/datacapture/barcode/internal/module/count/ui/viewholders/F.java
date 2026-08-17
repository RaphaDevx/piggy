package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class F implements A {
    public final BarcodeCountViewUiLayoutHelper a;
    public boolean b;
    public Function0 c;
    public com.scandit.datacapture.barcode.internal.module.ui.b d;
    public final z1 e;

    public F(C0353h layoutHelper, RelativeLayout container) {
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = layoutHelper;
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        this.b = barcodeCountViewDefaults.getShouldShowListButton();
        barcodeCountViewDefaults.getListButtonContentDescription();
        this.c = D.a;
        this.e = new z1(container);
    }

    public final void a(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = this.d;
        if (bVar == null) {
            return;
        }
        bVar.setContentDescription(value);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.e.b(this.d, new C(this));
    }

    public final RelativeLayout.LayoutParams c() {
        Context context = ((RelativeLayout) this.e.a).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            int i = AbstractC0341b.a;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(AbstractC0341b.a, -2);
            layoutParams.addRule(11);
            layoutParams.addRule(12);
            layoutParams.bottomMargin = this.a.f();
            layoutParams.setMarginEnd(this.a.a());
            return layoutParams;
        }
        int i2 = AbstractC0341b.a;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(AbstractC0341b.a, AbstractC0341b.b);
        layoutParams2.addRule(9);
        layoutParams2.addRule(12);
        layoutParams2.setMarginStart(this.a.f());
        layoutParams2.bottomMargin = this.a.a();
        return layoutParams2;
    }

    public static final void a(F this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.invoke();
    }

    public final com.scandit.datacapture.barcode.internal.module.ui.b a(Context context) {
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = new com.scandit.datacapture.barcode.internal.module.ui.b(context);
        bVar.setLayoutParams(c());
        bVar.b.setImageResource(R.drawable.sc_ic_list);
        bVar.setVisibility(4);
        bVar.setContentDescription(BarcodeCountViewDefaults.INSTANCE.getListButtonContentDescription());
        bVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                F.a(F.this, view);
            }
        });
        this.d = bVar;
        return bVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = this.d;
        if (bVar != null) {
            bVar.setVisibility(!this.b ? 4 : 0);
        }
        com.scandit.datacapture.barcode.internal.module.ui.b bVar2 = this.d;
        if (bVar2 == null) {
            return;
        }
        bVar2.setLayoutParams(c());
    }

    public final void a(int i, Function0 function0) {
        Unit unit;
        synchronized (this) {
            com.scandit.datacapture.barcode.internal.module.ui.b bVar = this.d;
            if (bVar == null || i != bVar.a) {
                if (bVar != null) {
                    this.e.a(new E(this, i, function0));
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    function0.invoke();
                }
            } else {
                function0.invoke();
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }
}
