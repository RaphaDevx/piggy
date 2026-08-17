package com.scandit.datacapture.barcode.internal.module.count.ui.handlers;

import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.feedback.BarcodeCountFeedback;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.ui.BarcodeCountViewInternalUiListener;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountScreenRatioForUi;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountToolbarSettings;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewSettings;
import com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalMode;
import com.scandit.datacapture.barcode.internal.module.count.ui.toolbar.BarcodeCountToolbarView;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.AbstractC0379u0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.BarcodeCountViewUiLayoutHelper;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0344c0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0346d0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0348e0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0353h;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0355i;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0358j0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0360k0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0362l0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0367o;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0371q;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0373r0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0375s0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0377t0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0380v;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0382w;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0384x;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0386y;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0343c;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0350f0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0359k;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0364m0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.InterfaceC0385x0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.J0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.K;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.K0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.O;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.O0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.P0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.S0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.X;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.Y;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.Z0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.a1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.b1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.h1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.i1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.n1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.o1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.s1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.y1;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.internal.sdk.utils.ObjectOverlayUtilsKt;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class J implements BarcodeCountUiPresenter {
    public boolean A;
    public boolean B;
    public boolean C;
    public final u D;
    public BarcodeCountInternalMode E;
    public final InterfaceC0385x0 a;
    public final com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.A b;
    public final com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.r c;
    public final Y d;
    public final InterfaceC0350f0 e;
    public final InterfaceC0364m0 f;
    public final InterfaceC0359k g;
    public final K0 h;
    public final S0 i;
    public final i1 j;
    public final b1 k;
    public final s1 l;
    public final O m;
    public final com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.G n;
    public final BarcodeCountViewUiLayoutHelper o;
    public final BarcodeCountBasicOverlay p;
    public final String q;
    public final List r;
    public t s;
    public BarcodeCountViewInternalUiListener t;
    public boolean u;
    public int v;
    public boolean w;
    public final Handler x;
    public BarcodeCountViewSettings y;
    public boolean z;

    public J(J0 shutterButtonsViewHolder, com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F listButtonViewHolder, C0384x exitButtonViewHolder, C0348e0 nextButtonViewHolder, C0362l0 redoScanButtonViewHolder, C0377t0 restartButtonViewHolder, C0371q clearButtonViewHolder, O0 singleScanButtonViewHolder, Z0 statusButtonViewHolder, n1 torchButtonWrapper, h1 toolbarViewHolder, y1 guidanceViewHolder, X loadingViewHolder, K listProgressBarViewHolder, C0353h layoutHelper, BarcodeCountBasicOverlay overlay, com.scandit.datacapture.barcode.internal.module.count.ui.cluster.B clusterPresenter, String loadingText) {
        Intrinsics.checkNotNullParameter(shutterButtonsViewHolder, "shutterButtonsViewHolder");
        Intrinsics.checkNotNullParameter(listButtonViewHolder, "listButtonViewHolder");
        Intrinsics.checkNotNullParameter(exitButtonViewHolder, "exitButtonViewHolder");
        Intrinsics.checkNotNullParameter(nextButtonViewHolder, "nextButtonViewHolder");
        Intrinsics.checkNotNullParameter(redoScanButtonViewHolder, "redoScanButtonViewHolder");
        Intrinsics.checkNotNullParameter(restartButtonViewHolder, "restartButtonViewHolder");
        Intrinsics.checkNotNullParameter(clearButtonViewHolder, "clearButtonViewHolder");
        Intrinsics.checkNotNullParameter(singleScanButtonViewHolder, "singleScanButtonViewHolder");
        Intrinsics.checkNotNullParameter(statusButtonViewHolder, "statusButtonViewHolder");
        Intrinsics.checkNotNullParameter(torchButtonWrapper, "torchButtonWrapper");
        Intrinsics.checkNotNullParameter(toolbarViewHolder, "toolbarViewHolder");
        Intrinsics.checkNotNullParameter(guidanceViewHolder, "guidanceViewHolder");
        Intrinsics.checkNotNullParameter(loadingViewHolder, "loadingViewHolder");
        Intrinsics.checkNotNullParameter(listProgressBarViewHolder, "listProgressBarViewHolder");
        Intrinsics.checkNotNullParameter(layoutHelper, "layoutHelper");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        Intrinsics.checkNotNullParameter(clusterPresenter, "clusterPresenter");
        Intrinsics.checkNotNullParameter(loadingText, "loadingText");
        this.a = shutterButtonsViewHolder;
        this.b = listButtonViewHolder;
        this.c = exitButtonViewHolder;
        this.d = nextButtonViewHolder;
        this.e = redoScanButtonViewHolder;
        this.f = restartButtonViewHolder;
        this.g = clearButtonViewHolder;
        this.h = singleScanButtonViewHolder;
        this.i = statusButtonViewHolder;
        this.j = torchButtonWrapper;
        this.k = toolbarViewHolder;
        this.l = guidanceViewHolder;
        this.m = loadingViewHolder;
        this.n = listProgressBarViewHolder;
        this.o = layoutHelper;
        this.p = overlay;
        this.q = loadingText;
        this.r = CollectionsKt.listOf((Object[]) new InterfaceC0343c[]{shutterButtonsViewHolder, listButtonViewHolder, exitButtonViewHolder, nextButtonViewHolder, redoScanButtonViewHolder, restartButtonViewHolder, clearButtonViewHolder, singleScanButtonViewHolder, statusButtonViewHolder, torchButtonWrapper, toolbarViewHolder, guidanceViewHolder, loadingViewHolder, listProgressBarViewHolder, clusterPresenter});
        this.x = new Handler(Looper.getMainLooper());
        this.y = new BarcodeCountViewSettings();
        this.D = new u(this);
        v listener = new v(this);
        Intrinsics.checkNotNullParameter(listener, "listener");
        clusterPresenter.i = listener;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(com.scandit.datacapture.barcode.count.ui.view.i iVar) {
        this.s = iVar;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void b(boolean z) {
        this.u = z;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final BarcodeCountViewInternalUiListener c() {
        return this.t;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void d(boolean z) {
        ((K) this.n).b = z;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void e(boolean z) {
        AbstractC0379u0.a(this.a, z);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void f() {
        AbstractC0379u0.a(this.a, false);
        AbstractC0379u0.b(this.a, true);
        com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.A a = this.b;
        C0386y onFinished = C0386y.a;
        com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F f = (com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F) a;
        f.getClass();
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        f.a(0, onFinished);
        ((K) this.n).b = false;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final BarcodeCountToolbarView g() {
        return ((h1) this.k).f;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void h() {
        J0 j0 = (J0) this.a;
        this.z = j0.d;
        j0.d = false;
        ((C0371q) this.g).b = false;
        ((O0) this.h).b = false;
        ((Z0) this.i).b = false;
        n1 n1Var = (n1) this.j;
        n1Var.d.setValue(n1Var, n1.h[0], Boolean.FALSE);
        ((h1) this.k).d = false;
        a();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void i() {
        J0 j0 = (J0) this.a;
        j0.k = false;
        j0.e.invoke(Boolean.FALSE);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final int j() {
        return this.v;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void k() {
        ((J0) this.a).d = this.z;
        ((C0371q) this.g).b = this.y.n();
        ((O0) this.h).b = this.y.v();
        ((Z0) this.i).b = this.y.x();
        i1 i1Var = this.j;
        n1 n1Var = (n1) i1Var;
        n1Var.d.setValue(n1Var, n1.h[0], Boolean.valueOf(this.y.z()));
        ((h1) this.k).d = this.y.y();
        a();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void l() {
        this.C = false;
        P0.b((Z0) this.i, false);
        P0.a((Z0) this.i, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
    
        r4 = r4.i;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m() {
        /*
            r4 = this;
            r0 = 1
            r4.A = r0
            r4.B = r0
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.t r0 = r4.s
            if (r0 == 0) goto L19
            com.scandit.datacapture.barcode.count.ui.view.i r0 = (com.scandit.datacapture.barcode.count.ui.view.i) r0
            java.lang.ref.WeakReference r0 = r0.a
            java.lang.Object r0 = r0.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r0 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r0
            if (r0 == 0) goto L19
            r1 = 0
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$onStatusModeEnabled(r0, r1)
        L19:
            int r0 = r4.v
            if (r0 != 0) goto L46
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.t r0 = r4.s
            if (r0 == 0) goto L53
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.I r1 = new com.scandit.datacapture.barcode.internal.module.count.ui.handlers.I
            r1.<init>(r4)
            com.scandit.datacapture.barcode.count.ui.view.i r0 = (com.scandit.datacapture.barcode.count.ui.view.i) r0
            java.lang.String r4 = "onFocus"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.ref.WeakReference r4 = r0.a
            java.lang.Object r4 = r4.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r4 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r4
            if (r4 == 0) goto L53
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountCameraHandler r4 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getCameraHandler$p(r4)
            if (r4 == 0) goto L53
            com.scandit.datacapture.barcode.count.ui.view.h r0 = new com.scandit.datacapture.barcode.count.ui.view.h
            r0.<init>(r1)
            r4.a(r0)
            goto L53
        L46:
            android.os.Handler r0 = r4.x
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J$$ExternalSyntheticLambda0 r1 = new com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J$$ExternalSyntheticLambda0
            r1.<init>()
            int r4 = r4.v
            long r2 = (long) r4
            r0.postDelayed(r1, r2)
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J.m():void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x001f, code lost:
    
        r2 = r2.i;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void a(com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J r2) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.t r0 = r2.s
            if (r0 == 0) goto L2d
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.I r1 = new com.scandit.datacapture.barcode.internal.module.count.ui.handlers.I
            r1.<init>(r2)
            com.scandit.datacapture.barcode.count.ui.view.i r0 = (com.scandit.datacapture.barcode.count.ui.view.i) r0
            java.lang.String r2 = "onFocus"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            java.lang.ref.WeakReference r2 = r0.a
            java.lang.Object r2 = r2.get()
            com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView r2 = (com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView) r2
            if (r2 == 0) goto L2d
            com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountCameraHandler r2 = com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView.access$getCameraHandler$p(r2)
            if (r2 == 0) goto L2d
            com.scandit.datacapture.barcode.count.ui.view.h r0 = new com.scandit.datacapture.barcode.count.ui.view.h
            r0.<init>(r1)
            r2.a(r0)
        L2d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J.a(com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J):void");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final boolean b() {
        return this.w;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void c(boolean z) {
        AbstractC0379u0.b(this.a, z);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final boolean e() {
        b1 b1Var = this.k;
        a1 preference = a1.c;
        h1 h1Var = (h1) b1Var;
        h1Var.getClass();
        Intrinsics.checkNotNullParameter(preference, "preference");
        return h1Var.a.getBoolean(h1.a(preference), false);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void b(BarcodeCountViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        b1 b1Var = this.k;
        a1 preference = a1.c;
        h1 h1Var = (h1) b1Var;
        h1Var.getClass();
        Intrinsics.checkNotNullParameter(preference, "preference");
        viewSettings.d(h1Var.a.getBoolean(h1.a(preference), false));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void d() {
        y1 y1Var = (y1) this.l;
        y1Var.getClass();
        o1.b(y1Var, false, "");
        o1.a(y1Var, false, "");
        this.C = true;
        P0.b((Z0) this.i, false);
        P0.a((Z0) this.i, true);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(BarcodeCountViewInternalUiListener barcodeCountViewInternalUiListener) {
        this.t = barcodeCountViewInternalUiListener;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(int i) {
        this.v = i;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(boolean z) {
        this.w = z;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(BarcodeCountViewLayerManager layerManager, BarcodeCount mode) {
        NativeBarcodeCountBasicOverlayColorScheme nativeBarcodeCountBasicOverlayColorScheme;
        Intrinsics.checkNotNullParameter(layerManager, "layerManager");
        Intrinsics.checkNotNullParameter(mode, "mode");
        mode.getInternalListeners$scandit_barcode_capture().add(0, this.D);
        mode._setStatusListener$scandit_barcode_capture(new x(this));
        this.p.setGuidanceHandler$scandit_barcode_capture(new w(this));
        BarcodeCountBasicOverlay barcodeCountBasicOverlay = this.p;
        h1 h1Var = (h1) this.k;
        h1Var.getClass();
        boolean z = h1Var.a.getBoolean(h1.a(a1.d), false);
        if (z) {
            nativeBarcodeCountBasicOverlayColorScheme = NativeBarcodeCountBasicOverlayColorScheme.ACCESSIBLE;
        } else if (!z) {
            nativeBarcodeCountBasicOverlayColorScheme = NativeBarcodeCountBasicOverlayColorScheme.DEFAULT;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        barcodeCountBasicOverlay.setColorScheme$scandit_barcode_capture(nativeBarcodeCountBasicOverlayColorScheme);
        InterfaceC0385x0 interfaceC0385x0 = this.a;
        z zVar = new z(this);
        J0 j0 = (J0) interfaceC0385x0;
        j0.getClass();
        Intrinsics.checkNotNullParameter(zVar, "<set-?>");
        j0.e = zVar;
        com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.A a = this.b;
        A a2 = new A(this);
        com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F f = (com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F) a;
        f.getClass();
        Intrinsics.checkNotNullParameter(a2, "<set-?>");
        f.c = a2;
        com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.r rVar = this.c;
        B b = new B(this);
        C0384x c0384x = (C0384x) rVar;
        c0384x.getClass();
        Intrinsics.checkNotNullParameter(b, "<set-?>");
        c0384x.c = b;
        Y y = this.d;
        C c = new C(this);
        C0348e0 c0348e0 = (C0348e0) y;
        c0348e0.getClass();
        Intrinsics.checkNotNullParameter(c, "<set-?>");
        c0348e0.c = c;
        InterfaceC0350f0 interfaceC0350f0 = this.e;
        D d = new D(this);
        C0362l0 c0362l0 = (C0362l0) interfaceC0350f0;
        c0362l0.getClass();
        Intrinsics.checkNotNullParameter(d, "<set-?>");
        c0362l0.c = d;
        InterfaceC0364m0 interfaceC0364m0 = this.f;
        E e = new E(this);
        C0377t0 c0377t0 = (C0377t0) interfaceC0364m0;
        c0377t0.getClass();
        Intrinsics.checkNotNullParameter(e, "<set-?>");
        c0377t0.d = e;
        InterfaceC0359k interfaceC0359k = this.g;
        F f2 = new F(this);
        C0371q c0371q = (C0371q) interfaceC0359k;
        c0371q.getClass();
        Intrinsics.checkNotNullParameter(f2, "<set-?>");
        c0371q.d = f2;
        K0 k0 = this.h;
        G g = new G(this);
        O0 o0 = (O0) k0;
        o0.getClass();
        Intrinsics.checkNotNullParameter(g, "<set-?>");
        o0.d = g;
        this.i.getClass();
        S0 s0 = this.i;
        H h = new H(this);
        Z0 z0 = (Z0) s0;
        z0.getClass();
        Intrinsics.checkNotNullParameter(h, "<set-?>");
        z0.c = h;
        ((h1) this.k).b = new y(this);
        this.o.d();
        Iterator it = this.r.iterator();
        while (it.hasNext()) {
            ((InterfaceC0343c) it.next()).b();
        }
        com.scandit.datacapture.barcode.internal.module.ui.n nVar = ((y1) this.l).b;
        if (nVar != null) {
            nVar.setVisibility(8);
            nVar.d = null;
        }
        X x = (X) this.m;
        com.scandit.datacapture.barcode.internal.module.count.ui.k kVar = x.b;
        if (kVar != null) {
            kVar.setVisibility(4);
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.k kVar2 = x.c;
        if (kVar2 == null) {
            return;
        }
        kVar2.setVisibility(4);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(BarcodeCountInternalMode internalMode) {
        Intrinsics.checkNotNullParameter(internalMode, "internalMode");
        this.E = internalMode;
        a(this.y, internalMode);
        a();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a() {
        this.o.d();
        Iterator it = this.r.iterator();
        while (it.hasNext()) {
            ((InterfaceC0343c) it.next()).a();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(int i, int i2) {
        K k = (K) this.n;
        k.d.a(new com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.J(k, i2, i));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(BarcodeCountViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        this.y = viewSettings;
        BarcodeCountInternalMode barcodeCountInternalMode = this.E;
        if (barcodeCountInternalMode != null) {
            a(viewSettings, barcodeCountInternalMode);
        }
    }

    public final void a(BarcodeCountViewSettings barcodeCountViewSettings, BarcodeCountInternalMode barcodeCountInternalMode) {
        this.p.setShouldShowScanAreaGuides(barcodeCountViewSettings.t());
        this.p.setShouldShowStatusIconsOnScan(barcodeCountViewSettings.w());
        this.p.setRecognizedBrush(barcodeCountViewSettings.k());
        this.p.setNotInListBrush(barcodeCountViewSettings.j());
        this.p.setAcceptedNotInListBrush(barcodeCountViewSettings.a());
        this.p.setRejectedNotInListBrush(barcodeCountViewSettings.l());
        this.p.setShouldShowHints(barcodeCountViewSettings.q());
        this.p.setFilterSettings(barcodeCountViewSettings.g());
        String E = barcodeCountViewSettings.E();
        if (E != null) {
            this.p.setTextForBarcodesNotInListDetectedHint(E);
        }
        String K = barcodeCountViewSettings.K();
        if (K != null) {
            this.p.setTextForTapShutterToScanHint(K);
        }
        String I = barcodeCountViewSettings.I();
        if (I != null) {
            this.p.setTextForScanningHint(I);
        }
        String G = barcodeCountViewSettings.G();
        if (G != null) {
            this.p.setTextForMoveCloserAndRescanHint(G);
        }
        String H = barcodeCountViewSettings.H();
        if (H != null) {
            this.p.setTextForMoveFurtherAndRescanHint(H);
        }
        String L = barcodeCountViewSettings.L();
        if (L != null) {
            this.p.setTextForTapToUncountHint(L);
        }
        String F = barcodeCountViewSettings.F();
        if (F != null) {
            this.p.setTextForClusteringGestureHint(F);
        }
        String J = barcodeCountViewSettings.J();
        if (J != null) {
            this.p.setTextForScreenCleanedUpHint(J);
        }
        ((J0) this.a).c = barcodeCountViewSettings.u();
        ((J0) this.a).d = barcodeCountViewSettings.p();
        ((J0) this.a).b(barcodeCountViewSettings.B());
        ((J0) this.a).a(barcodeCountViewSettings.h());
        ((com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F) this.b).b = barcodeCountViewSettings.r();
        ((com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F) this.b).a(barcodeCountViewSettings.i());
        ((C0384x) this.c).b = barcodeCountViewSettings.o();
        com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.r rVar = this.c;
        String text = barcodeCountViewSettings.f();
        C0384x c0384x = (C0384x) rVar;
        c0384x.getClass();
        Intrinsics.checkNotNullParameter(text, "value");
        C0380v onFinished = C0380v.a;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        c0384x.e = text;
        c0384x.g.a(new C0382w(c0384x, text, onFinished));
        ((C0384x) this.c).a(barcodeCountViewSettings.e());
        ((C0384x) this.c).f = true;
        ((C0371q) this.g).b = barcodeCountViewSettings.n();
        InterfaceC0359k interfaceC0359k = this.g;
        String text2 = barcodeCountViewSettings.d();
        C0371q c0371q = (C0371q) interfaceC0359k;
        c0371q.getClass();
        Intrinsics.checkNotNullParameter(text2, "value");
        C0355i onFinished2 = C0355i.a;
        Intrinsics.checkNotNullParameter(text2, "text");
        Intrinsics.checkNotNullParameter(onFinished2, "onFinished");
        c0371q.f = text2;
        c0371q.g.a(new C0367o(c0371q, text2, onFinished2));
        ((C0371q) this.g).a(barcodeCountViewSettings.c());
        ((O0) this.h).b = barcodeCountViewSettings.v();
        ((O0) this.h).a(barcodeCountViewSettings.C());
        ((Z0) this.i).b = barcodeCountViewSettings.x() && !barcodeCountViewSettings.w();
        ((Z0) this.i).a(barcodeCountViewSettings.D());
        i1 i1Var = this.j;
        Set createSetBuilder = SetsKt.createSetBuilder();
        if (this.y.v()) {
            createSetBuilder.add(Anchor.BOTTOM_LEFT);
        }
        if (this.y.x()) {
            createSetBuilder.add(Anchor.BOTTOM_RIGHT);
        }
        Set build = SetsKt.build(createSetBuilder);
        n1 n1Var = (n1) i1Var;
        n1Var.getClass();
        Intrinsics.checkNotNullParameter(build, "<set-?>");
        n1Var.f = build;
        n1 n1Var2 = (n1) this.j;
        n1Var2.d.setValue(n1Var2, n1.h[0], Boolean.valueOf(barcodeCountViewSettings.z()));
        i1 i1Var2 = this.j;
        Anchor N = barcodeCountViewSettings.N();
        n1 n1Var3 = (n1) i1Var2;
        n1Var3.getClass();
        Intrinsics.checkNotNullParameter(N, "<set-?>");
        n1Var3.e = N;
        ((h1) this.k).d = barcodeCountViewSettings.y();
        b1 b1Var = this.k;
        BarcodeCountToolbarSettings settings = barcodeCountViewSettings.M();
        h1 h1Var = (h1) b1Var;
        h1Var.getClass();
        Intrinsics.checkNotNullParameter(settings, "settings");
        h1Var.e = settings.clone$scandit_barcode_capture();
        BarcodeCountToolbarView barcodeCountToolbarView = h1Var.f;
        if (barcodeCountToolbarView != null) {
            barcodeCountToolbarView.a(settings);
        }
        ((y1) this.l).a = barcodeCountViewSettings.A();
        ((X) this.m).a = barcodeCountViewSettings.A();
        ((K) this.n).a = barcodeCountViewSettings.s();
        ((C0362l0) this.e).b = false;
        ((C0377t0) this.f).b = false;
        if (Intrinsics.areEqual(barcodeCountInternalMode, com.scandit.datacapture.barcode.internal.module.count.ui.mode.a.a) || !(barcodeCountInternalMode instanceof com.scandit.datacapture.barcode.internal.module.count.ui.mode.b)) {
            return;
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.mode.b bVar = (com.scandit.datacapture.barcode.internal.module.count.ui.mode.b) barcodeCountInternalMode;
        this.p.setShouldShowScanAreaGuides(false);
        ((com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F) this.b).b = false;
        ((O0) this.h).b = false;
        ((Z0) this.i).b = false;
        Y y = this.d;
        String text3 = bVar.a.getNextButtonText();
        C0348e0 c0348e0 = (C0348e0) y;
        c0348e0.getClass();
        Intrinsics.checkNotNullParameter(text3, "value");
        C0344c0 onFinished3 = C0344c0.a;
        Intrinsics.checkNotNullParameter(text3, "text");
        Intrinsics.checkNotNullParameter(onFinished3, "onFinished");
        c0348e0.e = text3;
        c0348e0.f.a(new C0346d0(c0348e0, text3, onFinished3));
        InterfaceC0350f0 interfaceC0350f0 = this.e;
        String text4 = bVar.a.getRedoScanButtonText();
        C0362l0 c0362l0 = (C0362l0) interfaceC0350f0;
        c0362l0.getClass();
        Intrinsics.checkNotNullParameter(text4, "value");
        C0358j0 onFinished4 = C0358j0.a;
        Intrinsics.checkNotNullParameter(text4, "text");
        Intrinsics.checkNotNullParameter(onFinished4, "onFinished");
        c0362l0.e = text4;
        c0362l0.f.a(new C0360k0(c0362l0, text4, onFinished4));
        InterfaceC0364m0 interfaceC0364m0 = this.f;
        String text5 = bVar.a.getRestartButtonText();
        C0377t0 c0377t0 = (C0377t0) interfaceC0364m0;
        c0377t0.getClass();
        Intrinsics.checkNotNullParameter(text5, "value");
        C0373r0 onFinished5 = C0373r0.a;
        Intrinsics.checkNotNullParameter(text5, "text");
        Intrinsics.checkNotNullParameter(onFinished5, "onFinished");
        c0377t0.f = text5;
        c0377t0.g.a(new C0375s0(c0377t0, text5, onFinished5));
        com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.r rVar2 = this.c;
        String text6 = bVar.a.getFinishButtonText();
        C0384x c0384x2 = (C0384x) rVar2;
        c0384x2.getClass();
        Intrinsics.checkNotNullParameter(text6, "value");
        Intrinsics.checkNotNullParameter(text6, "text");
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        c0384x2.e = text6;
        c0384x2.g.a(new C0382w(c0384x2, text6, onFinished));
        ((C0384x) this.c).f = true;
        ((C0371q) this.g).b = false;
        int ordinal = bVar.b.ordinal();
        if (ordinal == 0) {
            ((C0348e0) this.d).b = true;
            ((C0384x) this.c).b = false;
            ((C0362l0) this.e).b = false;
            ((X) this.m).a = false;
            this.p.setShouldShowHints(false);
            ((y1) this.l).a = false;
            ((C0377t0) this.f).b = true;
            return;
        }
        if (ordinal == 1) {
            ((C0348e0) this.d).b = true;
            ((C0384x) this.c).b = false;
            ((C0362l0) this.e).b = false;
            ((C0377t0) this.f).b = true;
            return;
        }
        if (ordinal != 2) {
            return;
        }
        ((C0348e0) this.d).b = false;
        ((C0384x) this.c).b = true;
        ((C0362l0) this.e).b = true;
        ((C0377t0) this.f).b = false;
        ((X) this.m).a = false;
        this.p.setShouldShowHints(false);
        ((y1) this.l).a = false;
        J0 j0 = (J0) this.a;
        j0.c = false;
        j0.d = false;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(Size screenSize, Size previewSize) {
        Intrinsics.checkNotNullParameter(screenSize, "screenSize");
        Intrinsics.checkNotNullParameter(previewSize, "previewSize");
        this.o.a(previewSize);
        J0 j0 = (J0) this.a;
        j0.a(ObjectOverlayUtilsKt.getSize(j0.b));
        a();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(boolean z, boolean z2) {
        b1 b1Var = this.k;
        boolean z3 = z && (z2 || this.o.h() == BarcodeCountScreenRatioForUi.SMALL || this.w);
        BarcodeCountToolbarView barcodeCountToolbarView = ((h1) b1Var).f;
        if (barcodeCountToolbarView == null) {
            return;
        }
        barcodeCountToolbarView.a(z3);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(BarcodeCountFeedback feedback) {
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        h1 h1Var = (h1) this.k;
        h1Var.getClass();
        Intrinsics.checkNotNullParameter(feedback, "feedback");
        h1Var.a(h1.a(a1.a), feedback.getSuccess().getSound() != null);
        h1Var.a(h1.a(a1.b), feedback.getSuccess().getVibration() != null);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountUiPresenter
    public final void a(BarcodeCount mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        h1 h1Var = (h1) this.k;
        h1Var.getClass();
        Intrinsics.checkNotNullParameter(mode, "mode");
        mode.setFeedback(BarcodeCountFeedback.INSTANCE.defaultFeedback$scandit_barcode_capture(h1Var.a.getBoolean("barcode_count_toolbar_audio_feedback_key", false), h1Var.a.getBoolean("barcode_count_toolbar_haptic_feedback_key", false)));
    }
}
