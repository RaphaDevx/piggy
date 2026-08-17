package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.l0, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0362l0 implements InterfaceC0350f0 {
    public final BarcodeCountViewUiLayoutHelper a;
    public boolean b;
    public Function0 c;
    public com.scandit.datacapture.barcode.internal.module.ui.b d;
    public String e;
    public final z1 f;

    public C0362l0(C0353h layoutHelper, RelativeLayout container) {
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = layoutHelper;
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        barcodeCountViewDefaults.getExitButtonContentDescription();
        this.c = C0356i0.a;
        this.e = barcodeCountViewDefaults.getRedoScanButtonText();
        this.f = new z1(container);
    }

    public static final void a(C0362l0 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c.invoke();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.f.b(this.d, new C0354h0(this));
    }

    public final RelativeLayout.LayoutParams c() {
        Context context = ((RelativeLayout) this.f.a).getContext();
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
        bVar.b.setImageResource(R.drawable.sc_ic_redo);
        bVar.setVisibility(4);
        bVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.l0$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0362l0.a(C0362l0.this, view);
            }
        });
        bVar.a(this.e);
        bVar.setContentDescription(BarcodeCountViewDefaults.INSTANCE.getExitButtonContentDescription());
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
}
