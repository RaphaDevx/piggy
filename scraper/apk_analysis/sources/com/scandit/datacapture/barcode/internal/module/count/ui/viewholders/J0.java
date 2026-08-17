package com.scandit.datacapture.barcode.internal.module.count.ui.viewholders;

import android.content.Context;
import android.util.Size;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.utils.ObjectOverlayUtilsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class J0 implements InterfaceC0385x0 {
    public static final int m = PixelExtensionsKt.pxFromDp$default(com.salesforce.marketingcloud.analytics.stats.b.p, (Context) null, 1, (Object) null);
    public final BarcodeCountViewUiLayoutHelper a;
    public final FrameLayout b;
    public boolean c;
    public boolean d;
    public Function1 e;
    public final z1 f;
    public final z1 g;
    public com.scandit.datacapture.barcode.internal.module.count.ui.n h;
    public com.scandit.datacapture.barcode.internal.module.count.ui.n i;
    public Boolean j;
    public boolean k;
    public final Lazy l;

    public J0(C0353h layoutHelper, RelativeLayout shutterButtonContainer, FrameLayout floatingShutterButtonContainer) {
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(shutterButtonContainer, "shutterButtonContainer");
        Intrinsics.checkNotNullParameter(floatingShutterButtonContainer, "floatingShutterButtonContainer");
        this.a = layoutHelper;
        this.b = floatingShutterButtonContainer;
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        this.c = barcodeCountViewDefaults.getShouldShowShutterButton();
        this.d = barcodeCountViewDefaults.getShouldShowFloatingShutterButton();
        barcodeCountViewDefaults.getShutterButtonContentDescription();
        barcodeCountViewDefaults.getFloatingShutterButtonContentDescription();
        this.e = D0.a;
        this.f = new z1(shutterButtonContainer);
        this.g = new z1(floatingShutterButtonContainer);
        this.l = LazyKt.lazy(new C0(this));
    }

    public static final void c(J0 this$0) {
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.d && (nVar = this$0.i) != null) {
            this$0.a(ObjectOverlayUtilsKt.getSize(this$0.g.a));
            com.scandit.datacapture.barcode.internal.module.count.ui.a aVar = (com.scandit.datacapture.barcode.internal.module.count.ui.a) this$0.l.getValue();
            if (aVar != null) {
                nVar.b.c = aVar;
                nVar.setX(aVar.a());
                nVar.setY(aVar.b());
            }
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar2 = this$0.h;
        if (nVar2 == null) {
            return;
        }
        nVar2.setLayoutParams(this$0.c());
    }

    public final void a(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar = this.i;
        if (nVar == null) {
            return;
        }
        nVar.setContentDescription(value);
    }

    public final void b(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar = this.h;
        if (nVar == null) {
            return;
        }
        nVar.setContentDescription(value);
    }

    public static final float b(J0 j0) {
        return (float) ((j0.a.e().getHeight() * 0.6d) - (m / 2));
    }

    public final void a(Size2 size2) {
        com.scandit.datacapture.barcode.internal.module.count.ui.a aVar;
        if (size2 == null || (aVar = (com.scandit.datacapture.barcode.internal.module.count.ui.a) this.l.getValue()) == null) {
            return;
        }
        Size size = new Size((int) size2.getWidth(), (int) size2.getHeight());
        Intrinsics.checkNotNullParameter(size, "<set-?>");
        aVar.d = size;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void b() {
        this.f.b(this.h, new B0(this));
        this.g.b(this.i, new C0389z0(this));
    }

    public final com.scandit.datacapture.barcode.internal.module.count.ui.n b(Context context) {
        int i = com.scandit.datacapture.barcode.internal.module.count.ui.n.e;
        com.scandit.datacapture.barcode.internal.module.count.ui.l mode = com.scandit.datacapture.barcode.internal.module.count.ui.l.a;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mode, "mode");
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar = new com.scandit.datacapture.barcode.internal.module.count.ui.n(context, 80, 140, mode);
        nVar.setId(100);
        nVar.setVisibility(4);
        nVar.setLayoutParams(c());
        nVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.J0$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                J0.b(J0.this, view);
            }
        });
        nVar.setContentDescription(BarcodeCountViewDefaults.INSTANCE.getShutterButtonContentDescription());
        this.h = nVar;
        return nVar;
    }

    public final RelativeLayout.LayoutParams c() {
        Context context = ((RelativeLayout) this.f.a).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (com.scandit.datacapture.barcode.internal.module.extensions.b.a(context)) {
            int i = AbstractC0341b.a;
            int i2 = AbstractC0341b.c;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, i2);
            layoutParams.addRule(15);
            layoutParams.addRule(21);
            layoutParams.setMarginEnd(this.a.c());
            return layoutParams;
        }
        int i3 = AbstractC0341b.a;
        int i4 = AbstractC0341b.c;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i4, i4);
        layoutParams2.addRule(14);
        layoutParams2.addRule(12);
        layoutParams2.bottomMargin = this.a.c();
        return layoutParams2;
    }

    public static final float a(J0 j0) {
        return j0.a.e().getWidth() - m;
    }

    public static final void b(J0 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.k = false;
        this$0.e.invoke(Boolean.FALSE);
    }

    public final com.scandit.datacapture.barcode.internal.module.count.ui.n a(Context context) {
        int i = com.scandit.datacapture.barcode.internal.module.count.ui.n.e;
        com.scandit.datacapture.barcode.internal.module.count.ui.l mode = com.scandit.datacapture.barcode.internal.module.count.ui.l.b;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mode, "mode");
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar = new com.scandit.datacapture.barcode.internal.module.count.ui.n(context, 56, com.salesforce.marketingcloud.analytics.stats.b.p, mode);
        int i2 = m;
        nVar.setLayoutParams(new FrameLayout.LayoutParams(i2, i2, 8388661));
        nVar.setVisibility(4);
        nVar.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.J0$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                J0.a(J0.this, view);
            }
        });
        nVar.setContentDescription(BarcodeCountViewDefaults.INSTANCE.getFloatingShutterButtonContentDescription());
        this.i = nVar;
        return nVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c
    public final void a() {
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar = this.h;
        if (nVar != null) {
            nVar.setVisibility(this.c ? 0 : 4);
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.n nVar2 = this.i;
        if (nVar2 != null) {
            nVar2.setVisibility(this.d ? 0 : 4);
        }
        Boolean bool = this.j;
        if (bool != null) {
            AbstractC0379u0.a(this, bool.booleanValue());
        }
        this.b.post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.J0$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                J0.c(J0.this);
            }
        });
    }

    public static final void a(J0 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.k = false;
        this$0.e.invoke(Boolean.TRUE);
    }
}
