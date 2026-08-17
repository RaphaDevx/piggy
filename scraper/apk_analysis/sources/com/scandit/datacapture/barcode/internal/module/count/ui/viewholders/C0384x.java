package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.x, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0384x implements r {
    public final BarcodeCountViewUiLayoutHelper a;
    public boolean b;
    public Function0 c;
    public com.scandit.datacapture.barcode.internal.module.ui.b d;
    public String e;
    public boolean f;
    public final z1 g;

    public C0384x(C0353h layoutHelper, RelativeLayout container) {
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = layoutHelper;
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        this.b = barcodeCountViewDefaults.getShouldShowExitButton();
        barcodeCountViewDefaults.getExitButtonContentDescription();
        this.c = C0378u.a;
        this.e = barcodeCountViewDefaults.getExitButtonText();
        this.f = true;
        this.g = new z1(container);
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
        this.g.b(this.d, new C0376t(this));
    }

    public final RelativeLayout.LayoutParams c() {
        Context context = ((RelativeLayout) this.g.a).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            int i = AbstractC0341b.a;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(AbstractC0341b.a, AbstractC0341b.b);
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            layoutParams.topMargin = this.a.f();
            layoutParams.setMarginEnd(this.a.a());
            return layoutParams;
        }
        int i2 = AbstractC0341b.a;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(AbstractC0341b.a, AbstractC0341b.b);
        layoutParams2.addRule(11);
        layoutParams2.addRule(12);
        layoutParams2.setMarginEnd(this.a.f());
        layoutParams2.bottomMargin = this.a.a();
        return layoutParams2;
    }

    public static final void a(C0384x this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.invoke();
    }

    public final com.scandit.datacapture.barcode.internal.module.ui.b a(Context context) {
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = new com.scandit.datacapture.barcode.internal.module.ui.b(context);
        bVar.setLayoutParams(c());
        bVar.setVisibility(4);
        bVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.x$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0384x.a(C0384x.this, view);
            }
        });
        bVar.b.setImageResource(R.drawable.sc_ic_exit);
        bVar.a(this.e);
        bVar.setContentDescription(BarcodeCountViewDefaults.INSTANCE.getExitButtonContentDescription());
        bVar.a(this.f);
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
        if (bVar2 != null) {
            bVar2.setLayoutParams(c());
        }
        com.scandit.datacapture.barcode.internal.module.ui.b bVar3 = this.d;
        if (bVar3 == null) {
            return;
        }
        bVar3.a(this.f);
    }
}
