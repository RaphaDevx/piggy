package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.t0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0377t0 implements InterfaceC0364m0 {
    public final BarcodeCountViewUiLayoutHelper a;
    public boolean b;
    public boolean c;
    public Function0 d;
    public com.scandit.datacapture.barcode.internal.module.ui.b e;
    public String f;
    public final z1 g;

    public C0377t0(C0353h layoutHelper, RelativeLayout container) {
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = layoutHelper;
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        barcodeCountViewDefaults.getExitButtonContentDescription();
        this.d = C0372q0.a;
        this.f = barcodeCountViewDefaults.getRestartButtonText();
        this.g = new z1(container);
    }

    public static final void a(C0377t0 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d.invoke();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.g.b(this.e, new C0368o0(this));
    }

    public final RelativeLayout.LayoutParams c() {
        Context context = ((RelativeLayout) this.g.a).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            int i = AbstractC0341b.a;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(AbstractC0341b.a, AbstractC0341b.b);
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

    public final com.scandit.datacapture.barcode.internal.module.ui.b a(Context context) {
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = new com.scandit.datacapture.barcode.internal.module.ui.b(context);
        bVar.setLayoutParams(c());
        bVar.setVisibility(4);
        bVar.b.setImageResource(R.drawable.sc_ic_undo);
        bVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.t0$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0377t0.a(C0377t0.this, view);
            }
        });
        bVar.a(this.f);
        bVar.setContentDescription(BarcodeCountViewDefaults.INSTANCE.getExitButtonContentDescription());
        this.e = bVar;
        return bVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.ui.b bVar = this.e;
        if (bVar != null) {
            bVar.setVisibility(!this.b ? 4 : 0);
        }
        com.scandit.datacapture.barcode.internal.module.ui.b bVar2 = this.e;
        if (bVar2 != null) {
            bVar2.setEnabled(this.c);
        }
        com.scandit.datacapture.barcode.internal.module.ui.b bVar3 = this.e;
        if (bVar3 == null) {
            return;
        }
        bVar3.setLayoutParams(c());
    }
}
