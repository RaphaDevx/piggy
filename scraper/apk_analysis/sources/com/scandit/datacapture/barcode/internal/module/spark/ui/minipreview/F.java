package com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.internal.module.spark.data.InterfaceC0432k;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanStateManager;
import com.scandit.datacapture.barcode.internal.module.spark.data.SparkScanViewCameraManager;
import com.scandit.datacapture.barcode.internal.module.spark.internal.SparkScanInternal;
import com.scandit.datacapture.barcode.internal.module.spark.ui.C0439c;
import com.scandit.datacapture.barcode.internal.module.spark.ui.C0440d;
import com.scandit.datacapture.barcode.spark.capture.SparkScan;
import com.scandit.datacapture.barcode.spark.capture.SparkScanViewDefaults;
import com.scandit.datacapture.barcode.spark.internal.module.ui.NativeSparkScanOverlayStyle;
import com.scandit.datacapture.barcode.spark.ui.SparkScanMiniPreviewSize;
import com.scandit.datacapture.barcode.spark.ui.SparkScanScanningMode;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.MeasureUnit;
import com.scandit.datacapture.core.common.geometry.PointWithUnitUtilsKt;
import com.scandit.datacapture.core.internal.sdk.CoreNativeTypeFactory;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.TorchState;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.LogoStyle;
import com.scandit.datacapture.core.ui.gesture.FocusGesture;
import com.scandit.datacapture.core.ui.style.Brush;
import io.sentry.rrweb.RRWebVideoEvent;
import java.lang.ref.WeakReference;
import kotlin.Lazy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class F extends RelativeLayout implements InterfaceC0432k, SparkScanViewMiniPreview {
    public final DataCaptureContext a;
    public final SparkScanStateManager b;
    public final SparkScanViewCameraManager c;
    public ViewGroup d;
    public final C0466o e;
    public final z f;
    public final p g;
    public final C0459h h;
    public final r i;
    public final C0455f j;
    public final N k;
    public final C0450a l;
    public final t m;
    public final C0439c n;
    public final com.scandit.datacapture.barcode.internal.module.spark.smartscan.ui.a o;
    public final C0452c p;
    public DataCaptureView q;
    public final C0451b r;
    public final com.scandit.datacapture.barcode.internal.module.ui.i s;
    public Brush t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(Context context, DataCaptureContext dataCaptureContext, SparkScan sparkScan, SparkScanStateManager stateManager, SparkScanViewCameraManager cameraManager) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataCaptureContext, "dataCaptureContext");
        Intrinsics.checkNotNullParameter(sparkScan, "sparkScan");
        Intrinsics.checkNotNullParameter(stateManager, "stateManager");
        Intrinsics.checkNotNullParameter(cameraManager, "cameraManager");
        this.a = dataCaptureContext;
        this.b = stateManager;
        this.c = cameraManager;
        Intrinsics.checkNotNullParameter(this, "container");
        Intrinsics.checkNotNullParameter(this, "toolbarInfo");
        this.e = new C0466o(this, this);
        this.f = new z(this);
        this.g = new p(this);
        this.h = new C0459h(this);
        this.i = new r(this);
        this.j = new C0455f(this);
        this.k = new N(context, stateManager);
        this.l = new C0450a(context);
        this.m = new t(this, stateManager);
        SparkScanInternal mode = sparkScan.get_sparkScanInternal();
        Intrinsics.checkNotNullParameter(mode, "mode");
        C0439c c0439c = new C0439c(mode, NativeSparkScanOverlayStyle.FRAME);
        this.n = c0439c;
        SparkScanInternal mode2 = sparkScan.get_sparkScanInternal();
        Intrinsics.checkNotNullParameter(mode2, "mode");
        this.o = new com.scandit.datacapture.barcode.internal.module.spark.smartscan.ui.a(mode2);
        this.p = new C0452c(context);
        this.q = null;
        C0451b c0451b = new C0451b(context);
        c0451b.setVisibility(8);
        this.r = c0451b;
        com.scandit.datacapture.barcode.internal.module.ui.i iVar = new com.scandit.datacapture.barcode.internal.module.ui.i(context);
        Lazy lazy = com.scandit.datacapture.barcode.internal.module.spark.ui.E.m;
        iVar.d = ((Number) lazy.getValue()).floatValue();
        iVar.requestLayout();
        this.s = iVar;
        this.t = SparkScanViewDefaults.getDefaultBrush();
        this.u = SparkScanViewDefaults.getDefaultZoomSwitchControlVisible();
        this.v = SparkScanViewDefaults.getDefaultPreviewSizeControlVisible();
        this.w = SparkScanViewDefaults.getDefaultPreviewCloseControlVisible();
        this.x = SparkScanViewDefaults.getDefaultTorchControlVisible();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(((Number) lazy.getValue()).floatValue());
        gradientDrawable.setShape(0);
        setBackground(gradientDrawable);
        setClipChildren(false);
        setDescendantFocusability(393216);
        setVisibility(4);
        iVar.setElevation(((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.k.getValue()).floatValue());
        a(stateManager.s());
        Brush p0 = this.t;
        Intrinsics.checkNotNullParameter(p0, "<set-?>");
        C0440d c0440d = c0439c.a;
        c0440d.getClass();
        Intrinsics.checkNotNullParameter(p0, "p0");
        c0440d.a.setBrushForRecognizedBarcodes(CoreNativeTypeFactory.INSTANCE.convert(p0));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void A() {
        if (getVisibility() == 4) {
            return;
        }
        this.j.a(new C(this));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void B() {
        /*
            Method dump skipped, instructions count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.F.B():void");
    }

    public final void C() {
        C0459h c0459h = this.h;
        boolean z = this.w;
        if (!z) {
            if (z) {
                c0459h.getClass();
                return;
            }
            RelativeLayout relativeLayout = (RelativeLayout) c0459h.b.get();
            if (relativeLayout != null) {
                relativeLayout.removeView(c0459h.c);
                return;
            }
            return;
        }
        RelativeLayout relativeLayout2 = (RelativeLayout) c0459h.b.get();
        if (relativeLayout2 != null) {
            if (G.a(relativeLayout2, c0459h.c)) {
                c0459h.c.bringToFront();
                return;
            }
            C0458g c0458g = c0459h.c;
            Lazy lazy = com.scandit.datacapture.barcode.internal.module.spark.ui.E.z;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
            layoutParams.addRule(11);
            layoutParams.addRule(10);
            Unit unit = Unit.INSTANCE;
            relativeLayout2.addView(c0458g, layoutParams);
        }
    }

    public final void D() {
        C0466o c0466o = this.e;
        boolean z = this.v && this.b.c();
        if (!z) {
            if (z) {
                c0466o.getClass();
                return;
            }
            ViewGroup viewGroup = (ViewGroup) c0466o.b.get();
            if (viewGroup != null) {
                viewGroup.removeView(c0466o.f);
                return;
            }
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) c0466o.b.get();
        if (viewGroup2 != null) {
            if (G.a(viewGroup2, c0466o.f)) {
                c0466o.f.bringToFront();
                return;
            }
            C0464m c0464m = c0466o.f;
            Lazy lazy = com.scandit.datacapture.barcode.internal.module.spark.ui.E.z;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
            layoutParams.addRule(9);
            layoutParams.addRule(8, com.scandit.datacapture.barcode.internal.module.spark.ui.E.d);
            Unit unit = Unit.INSTANCE;
            viewGroup2.addView(c0464m, layoutParams);
        }
    }

    public final void E() {
        r rVar = this.i;
        boolean z = this.b.a() != CameraPosition.USER_FACING && this.x && this.b.x() != TorchState.AUTO && (this.c.isTorchAvailable() || this.c.a() == null);
        if (!z) {
            if (z) {
                rVar.getClass();
                return;
            }
            RelativeLayout relativeLayout = (RelativeLayout) rVar.b.get();
            if (relativeLayout != null) {
                relativeLayout.removeView(rVar.c);
                return;
            }
            return;
        }
        RelativeLayout relativeLayout2 = (RelativeLayout) rVar.b.get();
        if (relativeLayout2 != null) {
            if (G.a(relativeLayout2, rVar.c)) {
                rVar.c.bringToFront();
                return;
            }
            q qVar = rVar.c;
            Lazy lazy = com.scandit.datacapture.barcode.internal.module.spark.ui.E.z;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
            layoutParams.addRule(9);
            layoutParams.addRule(10);
            Unit unit = Unit.INSTANCE;
            relativeLayout2.addView(qVar, layoutParams);
        }
    }

    public final void F() {
        z zVar = this.f;
        boolean z = this.u;
        if (!z) {
            if (z) {
                zVar.getClass();
                return;
            }
            RelativeLayout relativeLayout = (RelativeLayout) zVar.b.get();
            if (relativeLayout != null) {
                relativeLayout.removeView(zVar.c);
                Unit unit = Unit.INSTANCE;
                return;
            }
            return;
        }
        RelativeLayout relativeLayout2 = (RelativeLayout) zVar.b.get();
        if (relativeLayout2 != null) {
            if (G.a(relativeLayout2, zVar.c)) {
                zVar.c.bringToFront();
                return;
            }
            y yVar = zVar.c;
            Lazy lazy = com.scandit.datacapture.barcode.internal.module.spark.ui.E.z;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(((Number) lazy.getValue()).intValue(), ((Number) lazy.getValue()).intValue());
            layoutParams.addRule(11);
            layoutParams.addRule(8, com.scandit.datacapture.barcode.internal.module.spark.ui.E.d);
            Unit unit2 = Unit.INSTANCE;
            relativeLayout2.addView(yVar, layoutParams);
        }
    }

    public final void G() {
        Pair a;
        C0466o c0466o = this.e;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams layoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams;
        SparkScanMiniPreviewSize miniPreviewSize = this.b.o();
        boolean C = this.b.C();
        c0466o.getClass();
        Intrinsics.checkNotNullParameter(layoutParams2, "layoutParams");
        Intrinsics.checkNotNullParameter(miniPreviewSize, "miniPreviewSize");
        ViewGroup viewGroup = (ViewGroup) c0466o.d.get();
        if (viewGroup == null) {
            return;
        }
        int i = AbstractC0465n.a[miniPreviewSize.ordinal()];
        if (i == 1) {
            a = C0466o.a(viewGroup, C);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            a = c0466o.b(viewGroup, C);
        }
        int intValue = ((Number) a.component1()).intValue();
        int intValue2 = ((Number) a.component2()).intValue();
        layoutParams2.width = intValue;
        layoutParams2.height = intValue2;
        Intrinsics.checkNotNullParameter(miniPreviewSize, "<set-?>");
        c0466o.e = miniPreviewSize;
    }

    public final void H() {
        this.k.f();
        N n = this.k;
        n.setVisibility(!n.c() ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = this.l.getLayoutParams();
        RelativeLayout.LayoutParams layoutParams2 = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            return;
        }
        layoutParams2.bottomMargin = this.k.c() ? -((int) ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.m.getValue()).floatValue()) : 0;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void a(x xVar) {
        this.f.a = xVar;
        this.e.a = xVar;
        this.h.a = xVar;
        this.i.a = xVar;
        this.m.b = xVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void b() {
        this.j.b = true;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void c(Integer num) {
        N n = this.k;
        n.o.setValue(n, N.q[1], num);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void d(boolean z) {
        N n = this.k;
        n.e.setVisibility(!z ? 8 : 0);
        n.e();
        H();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean e() {
        return this.k.g.getVisibility() == 0;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void f(boolean z) {
        this.w = z;
        C();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void g(boolean z) {
        N n = this.k;
        n.f.setVisibility(!z ? 8 : 0);
        n.e();
        H();
    }

    @Override // android.view.View
    public final float getElevation() {
        return this.s.getElevation();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void h(boolean z) {
        N n = this.k;
        n.c.setVisibility(!z ? 8 : 0);
        n.e();
        H();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void i(boolean z) {
        this.v = z;
        D();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void j(boolean z) {
        N n = this.k;
        n.g.setVisibility(!z ? 8 : 0);
        n.e();
        H();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void k(boolean z) {
        N n = this.k;
        n.h.setVisibility(!z ? 8 : 0);
        n.e();
        H();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void l(boolean z) {
        this.x = z;
        E();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void m(boolean z) {
        this.n.a.a.enableSingleScanMode(z);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean n() {
        return this.v;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void o() {
        this.r.setVisibility(8);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean p() {
        return this.k.d.getVisibility() == 0;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean q() {
        return this.x;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void r() {
        this.p.setAlpha(0.95f);
        this.p.setVisibility(0);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean s() {
        return this.u;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void setBrush(Brush p0) {
        Intrinsics.checkNotNullParameter(p0, "value");
        C0439c c0439c = this.n;
        c0439c.getClass();
        Intrinsics.checkNotNullParameter(p0, "<set-?>");
        C0440d c0440d = c0439c.a;
        c0440d.getClass();
        Intrinsics.checkNotNullParameter(p0, "p0");
        c0440d.a.setBrushForRecognizedBarcodes(CoreNativeTypeFactory.INSTANCE.convert(p0));
        this.t = p0;
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        this.s.setElevation(f);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final Integer t() {
        N n = this.k;
        return (Integer) n.o.getValue(n, N.q[1]);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean u() {
        return this.k.e.getVisibility() == 0;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean v() {
        return this.w;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.H
    public final boolean w() {
        return this.k.c();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void x() {
        this.p.setAlpha(0.0f);
        this.p.setVisibility(8);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean y() {
        return this.k.f.getVisibility() == 0;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean z() {
        return this.k.c.getVisibility() == 0;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void b(Integer num) {
        N n = this.k;
        n.p.setValue(n, N.q[2], num);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void f() {
        this.j.a();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final Integer i() {
        N n = this.k;
        return (Integer) n.p.getValue(n, N.q[2]);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void l() {
        this.j.a(new D(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void c(boolean z) {
        if (z) {
            b(false);
        }
        C0455f c0455f = this.j;
        c0455f.a();
        c0455f.c = null;
        if (getVisibility() != 0) {
            DataCaptureView dataCaptureView = this.q;
            if (dataCaptureView != null) {
                dataCaptureView.removeOverlay(this.n);
                this.l.removeView(dataCaptureView);
            }
            DataCaptureView.Companion companion = DataCaptureView.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            DataCaptureView newInstance = companion.newInstance(context, this.a);
            newInstance.setZoomGesture(null);
            newInstance.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            newInstance.setLogoStyle(LogoStyle.MINIMAL);
            newInstance.setLogoAnchor(Anchor.BOTTOM_CENTER);
            this.q = newInstance;
            this.l.addView(newInstance, new RelativeLayout.LayoutParams(-1, -1));
            DataCaptureView dataCaptureView2 = this.q;
            if (dataCaptureView2 != null) {
                dataCaptureView2.addOverlay(this.n);
            }
            setVisibility(0);
        }
        g();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void e(boolean z) {
        N n = this.k;
        n.d.setVisibility(!z ? 8 : 0);
        n.e();
        H();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void m() {
        post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.F$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                F.b(F.this);
            }
        });
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void b(boolean z) {
        Pair a;
        Pair currentAndNextSize;
        C0466o c0466o = this.e;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams layoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams;
        SparkScanMiniPreviewSize miniPreviewSize = this.b.o();
        boolean C = this.b.C();
        c0466o.getClass();
        Intrinsics.checkNotNullParameter(layoutParams2, "layoutParams");
        Intrinsics.checkNotNullParameter(miniPreviewSize, "miniPreviewSize");
        ViewGroup viewGroup = (ViewGroup) c0466o.d.get();
        if (viewGroup == null) {
            currentAndNextSize = TuplesKt.to(new Size(-1, -1), new Size(-1, -1));
        } else {
            Size size = new Size(layoutParams2.width, layoutParams2.height);
            int i = AbstractC0465n.a[miniPreviewSize.ordinal()];
            if (i == 1) {
                a = C0466o.a(viewGroup, C);
            } else if (i == 2) {
                a = c0466o.b(viewGroup, C);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            int intValue = ((Number) a.component1()).intValue();
            int intValue2 = ((Number) a.component2()).intValue();
            Intrinsics.checkNotNullParameter(miniPreviewSize, "<set-?>");
            c0466o.e = miniPreviewSize;
            currentAndNextSize = TuplesKt.to(size, new Size(intValue, intValue2));
        }
        ViewGroup viewGroup2 = this.d;
        if (viewGroup2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(RRWebVideoEvent.JsonKeys.CONTAINER);
            viewGroup2 = null;
        }
        Size correctedViewSize = ViewExtensionsKt.getCorrectedViewSize(viewGroup2, this.b.C());
        int height = correctedViewSize.getHeight();
        int width = correctedViewSize.getWidth();
        t tVar = this.m;
        tVar.getClass();
        Intrinsics.checkNotNullParameter(currentAndNextSize, "currentAndNextSize");
        PointF a2 = tVar.a.a(width, height, currentAndNextSize);
        C0455f c0455f = this.j;
        ViewGroup.LayoutParams layoutParams3 = getLayoutParams();
        Intrinsics.checkNotNull(layoutParams3, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        c0455f.a((ViewGroup.MarginLayoutParams) layoutParams3, a2, (Size) currentAndNextSize.getFirst(), (Size) currentAndNextSize.getSecond(), z, new E(this));
        z zVar = this.f;
        SparkScanMiniPreviewSize miniPreviewSize2 = this.b.o();
        zVar.getClass();
        Intrinsics.checkNotNullParameter(miniPreviewSize2, "miniPreviewSize");
        zVar.c.a(miniPreviewSize2);
        C0459h c0459h = this.h;
        SparkScanMiniPreviewSize miniPreviewSize3 = this.b.o();
        c0459h.getClass();
        Intrinsics.checkNotNullParameter(miniPreviewSize3, "miniPreviewSize");
        c0459h.c.a(miniPreviewSize3);
        r rVar = this.i;
        SparkScanMiniPreviewSize miniPreviewSize4 = this.b.o();
        rVar.getClass();
        Intrinsics.checkNotNullParameter(miniPreviewSize4, "miniPreviewSize");
        rVar.c.a(miniPreviewSize4);
        C0466o c0466o2 = this.e;
        SparkScanMiniPreviewSize miniPreviewSize5 = this.b.o();
        c0466o2.getClass();
        Intrinsics.checkNotNullParameter(miniPreviewSize5, "miniPreviewSize");
        c0466o2.f.a(miniPreviewSize5);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final boolean d() {
        return this.k.h.getVisibility() == 0;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void g() {
        H();
        F();
        D();
        C();
        E();
        z zVar = this.f;
        boolean B = this.b.B();
        y yVar = zVar.c;
        StateListDrawable drawable = AbstractC0463l.a(yVar.b, B);
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        yVar.a.setImageDrawable(drawable);
        C0459h c0459h = this.h;
        boolean j = this.b.j();
        C0458g c0458g = c0459h.c;
        Context context = c0458g.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        StateListDrawable drawable2 = AbstractC0463l.a(context);
        Intrinsics.checkNotNullParameter(drawable2, "drawable");
        c0458g.a.setImageDrawable(drawable2);
        c0458g.setEnabled(!j);
        r rVar = this.i;
        TorchState torchState = this.b.x();
        rVar.getClass();
        Intrinsics.checkNotNullParameter(torchState, "torchState");
        q qVar = rVar.c;
        Context context2 = qVar.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        StateListDrawable drawable3 = AbstractC0463l.a(context2, torchState);
        Intrinsics.checkNotNullParameter(drawable3, "drawable");
        qVar.a.setImageDrawable(drawable3);
        C0466o c0466o = this.e;
        SparkScanMiniPreviewSize miniPreviewSize = c0466o.e;
        c0466o.getClass();
        Intrinsics.checkNotNullParameter(miniPreviewSize, "miniPreviewSize");
        C0464m c0464m = c0466o.f;
        c0464m.getClass();
        Intrinsics.checkNotNullParameter(miniPreviewSize, "miniPreviewSize");
        StateListDrawable drawable4 = AbstractC0463l.a(c0464m.b, miniPreviewSize);
        Intrinsics.checkNotNullParameter(drawable4, "drawable");
        c0464m.a.setImageDrawable(drawable4);
        a(this.b.s());
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.toast.x
    public final void h() {
        this.g.h();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void j() {
        this.f.a = null;
        this.e.a = null;
        this.i.a = null;
        this.h.a = null;
        this.b.b(this);
        removeAllViews();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void a(A a) {
        this.k.b = a;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void a(boolean z) {
        this.u = z;
        F();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final Integer a() {
        N n = this.k;
        return (Integer) n.n.getValue(n, N.q[0]);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void a(Integer num) {
        N n = this.k;
        n.n.setValue(n, N.q[0], num);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void a(ViewGroup parentContainer) {
        Intrinsics.checkNotNullParameter(parentContainer, "container");
        if (getParent() != null) {
            return;
        }
        this.d = parentContainer;
        C0466o c0466o = this.e;
        c0466o.getClass();
        Intrinsics.checkNotNullParameter(parentContainer, "parentContainer");
        c0466o.d = new WeakReference(parentContainer);
        parentContainer.addView(this);
        this.b.a(this);
        setId(com.scandit.datacapture.barcode.internal.module.spark.ui.E.a);
        View view = this.k;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.t.getValue()).intValue());
        layoutParams.addRule(12);
        Unit unit = Unit.INSTANCE;
        addView(view, layoutParams);
        View view2 = this.l;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(2, com.scandit.datacapture.barcode.internal.module.spark.ui.E.b);
        layoutParams2.bottomMargin = this.k.c() ? -((int) ((Number) com.scandit.datacapture.barcode.internal.module.spark.ui.E.m.getValue()).floatValue()) : 0;
        addView(view2, layoutParams2);
        View view3 = this.m;
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams3.addRule(8, com.scandit.datacapture.barcode.internal.module.spark.ui.E.d);
        addView(view3, layoutParams3);
        addView(this.p, new RelativeLayout.LayoutParams(-1, -1));
        addView(this.r, new RelativeLayout.LayoutParams(-1, -1));
        addView(this.s, 0, new RelativeLayout.LayoutParams(-1, -1));
        post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.F$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                F.a(F.this);
            }
        });
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void c() {
        this.r.bringToFront();
        this.r.setVisibility(0);
    }

    public static final void a(F this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.g();
        this$0.G();
        this$0.B();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void a(float f) {
        this.s.setElevation(f);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void a(Brush brush, boolean z) {
        Intrinsics.checkNotNullParameter(brush, "brush");
        C0439c c0439c = this.n;
        c0439c.getClass();
        Intrinsics.checkNotNullParameter(brush, "brush");
        C0440d c0440d = c0439c.a;
        c0440d.getClass();
        Intrinsics.checkNotNullParameter(brush, "brush");
        c0440d.a.setBrushForErrorBarcodes(CoreNativeTypeFactory.INSTANCE.convert(brush));
        this.n.a.a.onErrorFeedbackEmitted(z);
        if (this.b.h()) {
            this.o.a.a.onErrorFeedbackEmitted(z);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.toast.x
    public final void a(String text, int i, int i2) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.j.a(new D(this));
        this.g.a(text, i, i2);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.data.InterfaceC0432k
    public final void a(SparkScanScanningMode previousMode, SparkScanScanningMode newMode) {
        Intrinsics.checkNotNullParameter(previousMode, "previousMode");
        Intrinsics.checkNotNullParameter(newMode, "newMode");
        a(newMode);
    }

    public final void a(SparkScanScanningMode sparkScanScanningMode) {
        this.n.a.a.setShouldDrawViewFinder(sparkScanScanningMode instanceof SparkScanScanningMode.Target);
        DataCaptureView dataCaptureView = this.q;
        if (dataCaptureView != null) {
            if (this.b.h()) {
                dataCaptureView.removeOverlay(this.n);
                dataCaptureView.addOverlay(this.o);
            } else {
                dataCaptureView.removeOverlay(this.o);
                dataCaptureView.addOverlay(this.n);
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.spark.ui.minipreview.SparkScanViewMiniPreview
    public final void k() {
        FocusGesture focusGesture;
        DataCaptureView dataCaptureView = this.q;
        if (dataCaptureView == null || (focusGesture = dataCaptureView.getFocusGesture()) == null) {
            return;
        }
        focusGesture.triggerFocus(PointWithUnitUtilsKt.PointWithUnit(0.5f, 0.5f, MeasureUnit.FRACTION));
    }

    public static final void b(F this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.G();
        this$0.B();
    }
}
