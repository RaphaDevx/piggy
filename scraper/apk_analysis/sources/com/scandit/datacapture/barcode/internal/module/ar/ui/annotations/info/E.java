package com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewDefaults;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArBodyElementDataHolder;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArHeaderFooterElementDataHolder;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotationDataHolder;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationAnchor;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class E extends FrameLayout {
    public static final Lazy q = LazyKt.lazy(y.a);
    public static final Lazy r = LazyKt.lazy(x.a);
    public static final Lazy s = LazyKt.lazy(w.a);
    public static final Lazy t = LazyKt.lazy(v.a);
    public final Function0 a;
    public final InterfaceC0293a b;
    public final p c;
    public final p d;
    public final RelativeLayout e;
    public final com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a f;
    public C0295c g;
    public final FrameLayout h;
    public u i;
    public final FrameLayout j;
    public u k;
    public final FrameLayout l;
    public Quadrilateral m;
    public BarcodeArInfoAnnotationAnchor n;
    public Size2 o;
    public final com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.b p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E(Context context, com.scandit.datacapture.barcode.ar.ui.annotations.b commonDataFactory, C0294b bodyElementFactory, q headerElementFactory, q footerElementFactory) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(commonDataFactory, "commonDataFactory");
        Intrinsics.checkNotNullParameter(bodyElementFactory, "bodyElementFactory");
        Intrinsics.checkNotNullParameter(headerElementFactory, "headerElementFactory");
        Intrinsics.checkNotNullParameter(footerElementFactory, "footerElementFactory");
        this.a = commonDataFactory;
        this.b = bodyElementFactory;
        this.c = headerElementFactory;
        this.d = footerElementFactory;
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setId(R.id.sc_ar_info_annotation_view_container);
        relativeLayout.setClipChildren(true);
        relativeLayout.setClipToOutline(true);
        this.e = relativeLayout;
        com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a aVar = new com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a(context);
        this.f = aVar;
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setId(R.id.sc_ar_info_annotation_body_container);
        this.h = frameLayout;
        FrameLayout frameLayout2 = new FrameLayout(context);
        frameLayout2.setId(R.id.sc_ar_info_annotation_header_container);
        this.j = frameLayout2;
        FrameLayout frameLayout3 = new FrameLayout(context);
        frameLayout3.setId(R.id.sc_ar_info_annotation_footer_container);
        this.l = frameLayout3;
        this.n = BarcodeArViewDefaults.getDefaultInfoAnnotationAnchor();
        A referenceQuadGetter = new A(this);
        B anchorGetter = new B(this);
        C sizeGetter = new C(this);
        Intrinsics.checkNotNullParameter(com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c.a, "<this>");
        Intrinsics.checkNotNullParameter(referenceQuadGetter, "referenceQuadGetter");
        Intrinsics.checkNotNullParameter(anchorGetter, "anchorGetter");
        Intrinsics.checkNotNullParameter(sizeGetter, "sizeGetter");
        this.p = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.b(referenceQuadGetter, anchorGetter, sizeGetter);
        setImportantForAccessibility(2);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        Lazy lazy = t;
        int intValue = ((Number) lazy.getValue()).intValue();
        Lazy lazy2 = s;
        layoutParams.setMargins(intValue, ((Number) lazy2.getValue()).intValue(), ((Number) lazy.getValue()).intValue(), ((Number) lazy2.getValue()).intValue());
        Unit unit = Unit.INSTANCE;
        addView(relativeLayout, layoutParams);
        relativeLayout.addView(frameLayout2, new RelativeLayout.LayoutParams(-1, -2));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, frameLayout2.getId());
        relativeLayout.addView(frameLayout, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(3, frameLayout.getId());
        relativeLayout.addView(frameLayout3, layoutParams3);
        addView(aVar, new FrameLayout.LayoutParams(((Number) q.getValue()).intValue(), ((Number) r.getValue()).intValue()));
        a();
        c();
        b();
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    }

    public final void a(boolean z, boolean z2, boolean z3, boolean z4) {
        if (z4) {
            final BarcodeArInfoAnnotationDataHolder barcodeArInfoAnnotationDataHolder = (BarcodeArInfoAnnotationDataHolder) this.a.invoke();
            setContentDescription(getResources().getString(R.string.sc_barcode_ar_annotation_info_content_description, barcodeArInfoAnnotationDataHolder.getBarcodeData()));
            if (barcodeArInfoAnnotationDataHolder.isEntireAnnotationTappable()) {
                setImportantForAccessibility(1);
                ViewExtensionsKt.addRippleForeground$default(this.e, null, 1, null);
                this.e.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.info.E$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        E.a(BarcodeArInfoAnnotationDataHolder.this, view);
                    }
                });
            }
            getLayoutParams().width = (((Number) t.getValue()).intValue() * 2) + barcodeArInfoAnnotationDataHolder.getWidth().toPixels$scandit_barcode_capture();
            this.e.setOutlineProvider(new D(barcodeArInfoAnnotationDataHolder));
            com.scandit.datacapture.barcode.internal.module.ar.ui.annotations.tip.a aVar = this.f;
            int tipColor = barcodeArInfoAnnotationDataHolder.getTipColor();
            boolean hasTip = barcodeArInfoAnnotationDataHolder.getHasTip();
            aVar.getClass();
            aVar.setVisibility(!hasTip ? 8 : 0);
            aVar.setColorFilter(tipColor, PorterDuff.Mode.SRC_IN);
            this.e.setClickable(barcodeArInfoAnnotationDataHolder.isEntireAnnotationTappable());
        }
        if (z) {
            a();
        }
        if (z2) {
            c();
        }
        if (z3) {
            b();
        }
        requestLayout();
    }

    public final void b() {
        u headerFooterView = this.k;
        if (headerFooterView == null) {
            q qVar = (q) this.d;
            qVar.getClass();
            u uVar = new u(qVar.a);
            uVar.setId(R.id.sc_ar_info_annotation_footer);
            this.k = uVar;
            this.l.addView(uVar);
            headerFooterView = uVar;
        }
        q qVar2 = (q) this.d;
        qVar2.getClass();
        Intrinsics.checkNotNullParameter(headerFooterView, "headerFooterView");
        headerFooterView.a((BarcodeArHeaderFooterElementDataHolder) qVar2.b.invoke());
    }

    public final void c() {
        u headerFooterView = this.i;
        if (headerFooterView == null) {
            q qVar = (q) this.c;
            qVar.getClass();
            u uVar = new u(qVar.a);
            uVar.setId(R.id.sc_ar_info_annotation_header);
            this.i = uVar;
            this.j.addView(uVar);
            headerFooterView = uVar;
        }
        q qVar2 = (q) this.c;
        qVar2.getClass();
        Intrinsics.checkNotNullParameter(headerFooterView, "headerFooterView");
        headerFooterView.a((BarcodeArHeaderFooterElementDataHolder) qVar2.b.invoke());
    }

    public static final void a(BarcodeArInfoAnnotationDataHolder data, View view) {
        Intrinsics.checkNotNullParameter(data, "$data");
        data.getAnnotationTapped().invoke();
    }

    public final void a() {
        C0295c view = this.g;
        if (view == null) {
            C0294b c0294b = (C0294b) this.b;
            c0294b.getClass();
            C0295c c0295c = new C0295c(c0294b.a);
            this.g = c0295c;
            this.h.addView(c0295c);
            view = c0295c;
        }
        C0294b c0294b2 = (C0294b) this.b;
        c0294b2.getClass();
        Intrinsics.checkNotNullParameter(view, "view");
        view.a((BarcodeArBodyElementDataHolder) c0294b2.b.invoke());
    }
}
