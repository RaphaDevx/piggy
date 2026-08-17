package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.q, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0371q implements InterfaceC0359k {
    public final BarcodeCountViewUiLayoutHelper a;
    public boolean b;
    public String c;
    public Function0 d;
    public com.scandit.datacapture.barcode.internal.module.count.ui.h e;
    public String f;
    public final z1 g;

    public C0371q(C0353h layoutHelper, RelativeLayout container) {
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(container, "container");
        this.a = layoutHelper;
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        this.b = barcodeCountViewDefaults.getShouldShowClearHighlightsButton();
        this.c = barcodeCountViewDefaults.getListButtonContentDescription();
        this.d = C0365n.a;
        this.f = barcodeCountViewDefaults.getClearHighlightsButtonText();
        this.g = new z1(container);
    }

    public final void a(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.c = value;
        com.scandit.datacapture.barcode.internal.module.count.ui.h hVar = this.e;
        if (hVar == null) {
            return;
        }
        hVar.setContentDescription(value);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.g.b(this.e, new C0363m(this));
    }

    public final RelativeLayout.LayoutParams c() {
        Context context = ((RelativeLayout) this.g.a).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(15);
            layoutParams.addRule(21);
            layoutParams.setMarginEnd(this.a.i());
            return layoutParams;
        }
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(14);
        layoutParams2.addRule(12);
        layoutParams2.bottomMargin = this.a.i();
        return layoutParams2;
    }

    public static final void a(C0371q this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d.invoke();
    }

    public final com.scandit.datacapture.barcode.internal.module.count.ui.h a(Context context) {
        com.scandit.datacapture.barcode.internal.module.count.ui.h hVar = new com.scandit.datacapture.barcode.internal.module.count.ui.h(context);
        hVar.setLayoutParams(c());
        com.scandit.datacapture.barcode.internal.module.count.ui.h hVar2 = this.e;
        if (hVar2 != null) {
            Context context2 = ((RelativeLayout) this.g.a).getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            hVar2.setRotation(com.scandit.datacapture.barcode.internal.module.extensions.b.a(context2) ? 270.0f : 0.0f);
        }
        hVar.setVisibility(4);
        hVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.q$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                C0371q.a(C0371q.this, view);
            }
        });
        hVar.a.setText(this.f);
        hVar.setContentDescription(this.c);
        this.e = hVar;
        return hVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.count.ui.h hVar;
        com.scandit.datacapture.barcode.internal.module.count.ui.h hVar2 = this.e;
        if (hVar2 != null) {
            hVar2.setVisibility(this.b ? 0 : 4);
        }
        if (this.b && (hVar = this.e) != null) {
            Context context = ((RelativeLayout) this.g.a).getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            hVar.setRotation(com.scandit.datacapture.barcode.internal.module.extensions.b.a(context) ? 270.0f : 0.0f);
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.h hVar3 = this.e;
        if (hVar3 != null) {
            hVar3.setLayoutParams(c());
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.h hVar4 = this.e;
        if (hVar4 != null) {
            RelativeLayout.LayoutParams c = c();
            Context context2 = ((RelativeLayout) this.g.a).getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context2)) {
                c.setMarginEnd(c.getMarginEnd() - ((hVar4.getWidth() - hVar4.getHeight()) / 2));
            }
            hVar4.setLayoutParams(c);
        }
    }
}
