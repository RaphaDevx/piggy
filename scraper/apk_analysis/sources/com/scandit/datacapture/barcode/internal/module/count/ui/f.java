package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.content.Context;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.count.capture.BarcodeCount;
import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountView;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewStyle;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountViewStyleExtensionKt;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.C0316t;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.C0321y;
import com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.U;
import com.scandit.datacapture.barcode.internal.module.count.ui.cluster.B;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.J;
import com.scandit.datacapture.barcode.internal.module.count.ui.mode.BarcodeCountInternalMode;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0339a;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0348e0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0353h;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0362l0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0371q;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0377t0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.C0384x;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.F;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.J0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.K;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.O0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.X;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.Z0;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.c1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.h1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.n1;
import com.scandit.datacapture.barcode.internal.module.count.ui.viewholders.y1;
import com.scandit.datacapture.core.ui.DataCaptureView;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class f {
    public static final BarcodeCountView a(Context context, DataCaptureView dataCaptureView, BarcodeCount mode, BarcodeCountViewStyle style, BarcodeCountInternalMode internalMode) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(internalMode, "internalMode");
        BarcodeCountBasicOverlay newInstance = BarcodeCountBasicOverlay.INSTANCE.newInstance(dataCaptureView, mode, BarcodeCountViewStyleExtensionKt.toOverlayStyle(style));
        com.scandit.datacapture.barcode.internal.module.count.ui.handlers.r rVar = new com.scandit.datacapture.barcode.internal.module.count.ui.handlers.r(newInstance);
        g gVar = new g(context);
        C0339a c0339a = new C0339a(dataCaptureView);
        C0353h c0353h = new C0353h();
        J0 j0 = new J0(c0353h, gVar.c, gVar.f);
        F f = new F(c0353h, gVar.c);
        C0384x c0384x = new C0384x(c0353h, gVar.c);
        C0348e0 c0348e0 = new C0348e0(c0353h, gVar.c);
        C0362l0 c0362l0 = new C0362l0(c0353h, gVar.c);
        C0377t0 c0377t0 = new C0377t0(c0353h, gVar.c);
        C0371q c0371q = new C0371q(c0353h, gVar.d);
        O0 o0 = new O0(c0353h, gVar.d);
        Z0 z0 = new Z0(c0353h, gVar.d);
        n1 n1Var = new n1(context, c0353h, c0339a);
        h1 h1Var = new h1(gVar.h, c1.a(context, mode));
        y1 y1Var = new y1(gVar.e);
        X x = new X(gVar.e);
        K k = new K(gVar.g);
        B b = new B(context, mode, newInstance, gVar, new c(dataCaptureView));
        String string = context.getString(R.string.sc_loading);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        J j = new J(j0, f, c0384x, c0348e0, c0362l0, c0377t0, c0371q, o0, z0, n1Var, h1Var, y1Var, x, k, c0353h, newInstance, b, string);
        U u = new U(dataCaptureView);
        return new BarcodeCountView(context, mode, gVar, c0339a, newInstance, c0353h, j, rVar, new C0316t(gVar, u, new C0321y(context, newInstance, gVar, u), newInstance.getStyle(), rVar, new e(newInstance), b), new com.scandit.datacapture.barcode.internal.module.count.ui.mode.d(j, gVar, mode, c0353h), internalMode, null, null, 6144, null);
    }
}
