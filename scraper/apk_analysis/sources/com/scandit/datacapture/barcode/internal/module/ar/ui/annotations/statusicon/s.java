package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.statusicon;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.ar.ui.annotations.statusicon.BarcodeArStatusIconAnnotationAnchor;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.common.geometry.Size2UtilsKt;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import kotlin.Lazy;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class s extends RelativeLayout {
    public final com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a a;
    public final a b;
    public Quadrilateral c;
    public BarcodeArStatusIconAnnotationAnchor d;
    public Size2 e;
    public final n f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(Context context, Barcode barcode) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a aVar = new com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a(context);
        this.a = aVar;
        a aVar2 = new a(context, barcode);
        this.b = aVar2;
        this.d = BarcodeArViewDefaults.getDefaultStatusIconAnnotationAnchor();
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c cVar = com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c.a;
        p referenceQuadGetter = new p(this);
        q anchorGetter = new q(this);
        r sizeGetter = new r(this);
        Intrinsics.checkNotNullParameter(cVar, "<this>");
        Intrinsics.checkNotNullParameter(referenceQuadGetter, "referenceQuadGetter");
        Intrinsics.checkNotNullParameter(anchorGetter, "anchorGetter");
        Intrinsics.checkNotNullParameter(sizeGetter, "sizeGetter");
        this.f = new n(referenceQuadGetter, anchorGetter, sizeGetter);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(15);
        Unit unit = Unit.INSTANCE;
        addView(aVar2, layoutParams);
        addView(aVar, new RelativeLayout.LayoutParams(-2, -2));
        setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    }

    public final void a(Quadrilateral quadrilateral, BarcodeArStatusIconAnnotationAnchor anchor) {
        Intrinsics.checkNotNullParameter(quadrilateral, "quadrilateral");
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (getWidth() <= 0 || getHeight() <= 0) {
            return;
        }
        ViewParent parent = getParent();
        FrameLayout frameLayout = parent instanceof FrameLayout ? (FrameLayout) parent : null;
        if (frameLayout == null) {
            return;
        }
        this.d = anchor;
        this.c = quadrilateral;
        this.e = Size2UtilsKt.Size2(getWidth(), getHeight());
        this.f.a(this, frameLayout);
        com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.c.a(this.a, com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.b.a(anchor));
        com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.c.b(this.a, com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.b.a(anchor));
        com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a aVar = this.a;
        Anchor anchor2 = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.b.a(anchor);
        int i = R.id.sc_ar_status_icon_text_container;
        Intrinsics.checkNotNullParameter(aVar, "<this>");
        Intrinsics.checkNotNullParameter(anchor2, "anchor");
        ViewGroup.LayoutParams layoutParams = aVar.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        ViewExtensionsKt.removeAllRules(layoutParams2);
        int i2 = com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.b.a[anchor2.ordinal()];
        if (i2 == 1) {
            layoutParams2.addRule(10);
        } else if (i2 == 2) {
            layoutParams2.addRule(12);
        } else if (i2 == 3) {
            layoutParams2.addRule(15);
            layoutParams2.addRule(20);
        } else {
            if (i2 != 4) {
                throw new IllegalArgumentException("Unsupported anchor: " + anchor2);
            }
            layoutParams2.addRule(15);
            layoutParams2.addRule(1, i);
        }
        aVar.setLayoutParams(layoutParams2);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Quadrilateral quadrilateral = this.c;
        if (quadrilateral != null) {
            a(quadrilateral, this.d);
        }
    }

    public final void a(BarcodeArStatusIconAnnotationAnchor anchor, boolean z) {
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        a aVar = this.b;
        aVar.getClass();
        Intrinsics.checkNotNullParameter(anchor, "anchor");
        if (anchor == BarcodeArStatusIconAnnotationAnchor.RIGHT) {
            aVar.removeAllViews();
            aVar.addView(aVar.a);
            aVar.addView(aVar.b);
            aVar.a.setGravity(8388629);
        } else {
            aVar.removeAllViews();
            aVar.addView(aVar.b);
            aVar.addView(aVar.a);
            aVar.a.setGravity(8388627);
        }
        ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
        layoutParams2.removeRule(1);
        layoutParams2.removeRule(20);
        if (z) {
            Lazy lazy = l.a;
            float intValue = (((Number) l.h.getValue()).intValue() - ((Number) l.g.getValue()).intValue()) / 2.0f;
            int i = o.a[anchor.ordinal()];
            if (i == 3) {
                layoutParams2.addRule(1, R.id.sc_ar_status_icon_tip);
                layoutParams2.leftMargin = (int) intValue;
            } else if (i == 4) {
                layoutParams2.addRule(20);
                layoutParams2.rightMargin = (int) intValue;
            }
            this.b.setLayoutParams(layoutParams2);
        }
    }
}
