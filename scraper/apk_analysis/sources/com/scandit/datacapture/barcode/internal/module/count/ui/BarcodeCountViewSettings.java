package com.scandit.datacapture.barcode.internal.module.count.ui;

import com.scandit.datacapture.barcode.count.ui.overlay.BarcodeCountBasicOverlay;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountNotInListActionSettings;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountToolbarSettings;
import com.scandit.datacapture.barcode.filter.ui.overlay.BarcodeFilterHighlightSettings;
import com.scandit.datacapture.barcode.internal.sdk.count.ui.BarcodeCountViewDefaults;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.ui.style.Brush;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class BarcodeCountViewSettings {
    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private String H;
    private String I;
    private String J;
    private String K;
    private String L;
    private BarcodeCountToolbarSettings M;
    private BarcodeCountNotInListActionSettings N;
    private boolean a;
    private Brush b;
    private Brush c;
    private Brush d;
    private Brush e;
    private BarcodeFilterHighlightSettings f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private Anchor r;
    private boolean s;
    private boolean t;
    private String u;
    private String v;
    private boolean w;
    private String x;
    private String y;
    private String z;

    public BarcodeCountViewSettings() {
        BarcodeCountViewDefaults barcodeCountViewDefaults = BarcodeCountViewDefaults.INSTANCE;
        this.a = barcodeCountViewDefaults.getShouldDisableModeOnExitButtonTapped();
        barcodeCountViewDefaults.getTapToUncountEnabled();
        BarcodeCountBasicOverlay.Companion companion = BarcodeCountBasicOverlay.INSTANCE;
        this.b = companion.defaultRecognizedBrush();
        this.c = companion.defaultNotInListBrush();
        this.d = companion.defaultAcceptedNotInListBrush();
        this.e = companion.defaultRejectedNotInListBrush();
        this.g = barcodeCountViewDefaults.getShouldShowClearHighlightsButton();
        this.h = barcodeCountViewDefaults.getShouldShowExitButton();
        this.i = barcodeCountViewDefaults.getShouldShowFloatingShutterButton();
        this.j = barcodeCountViewDefaults.getShouldShowListButton();
        this.k = barcodeCountViewDefaults.getShouldShowShutterButton();
        this.l = barcodeCountViewDefaults.getShouldShowSingleScanButton();
        this.m = barcodeCountViewDefaults.getShouldShowStatusModeButton();
        this.n = barcodeCountViewDefaults.getShouldShowToolbar();
        this.o = barcodeCountViewDefaults.getShouldShowUserGuidanceView();
        this.p = barcodeCountViewDefaults.getShouldShowListProgressBar();
        this.q = barcodeCountViewDefaults.getShouldShowTorchControl();
        this.r = barcodeCountViewDefaults.getTorchControlPosition();
        this.s = barcodeCountViewDefaults.getShouldShowScanAreaGuides();
        this.t = barcodeCountViewDefaults.getShouldShowHints();
        this.u = barcodeCountViewDefaults.getClearHighlightsButtonText();
        this.v = barcodeCountViewDefaults.getExitButtonText();
        this.w = barcodeCountViewDefaults.getShouldShowStatusIconsOnScan();
        this.x = barcodeCountViewDefaults.getClearHighlightsButtonContentDescription();
        this.y = barcodeCountViewDefaults.getExitButtonContentDescription();
        this.z = barcodeCountViewDefaults.getFloatingShutterButtonContentDescription();
        this.A = barcodeCountViewDefaults.getListButtonContentDescription();
        this.B = barcodeCountViewDefaults.getSingleScanButtonContentDescription();
        this.C = barcodeCountViewDefaults.getShutterButtonContentDescription();
        this.D = barcodeCountViewDefaults.getStatusModeButtonContentDescription();
        this.M = new BarcodeCountToolbarSettings();
        this.N = new BarcodeCountNotInListActionSettings();
    }

    public final boolean A() {
        return this.o;
    }

    public final String B() {
        return this.C;
    }

    public final String C() {
        return this.B;
    }

    public final String D() {
        return this.D;
    }

    public final String E() {
        return this.E;
    }

    public final String F() {
        return this.K;
    }

    public final String G() {
        return this.H;
    }

    public final String H() {
        return this.I;
    }

    public final String I() {
        return this.G;
    }

    public final String J() {
        return this.L;
    }

    public final String K() {
        return this.F;
    }

    public final String L() {
        return this.J;
    }

    public final BarcodeCountToolbarSettings M() {
        return this.M;
    }

    public final Anchor N() {
        return this.r;
    }

    public final void a(boolean z) {
        this.a = z;
    }

    public final void b(Brush brush) {
        this.c = brush;
    }

    public final void c(Brush brush) {
        this.b = brush;
    }

    public final void d(Brush brush) {
        this.e = brush;
    }

    public final void e(boolean z) {
        this.t = z;
    }

    public final void f(boolean z) {
        this.j = z;
    }

    public final BarcodeFilterHighlightSettings g() {
        return this.f;
    }

    public final void h(boolean z) {
        this.s = z;
    }

    public final void i(boolean z) {
        this.k = z;
    }

    public final Brush j() {
        return this.c;
    }

    public final Brush k() {
        return this.b;
    }

    public final Brush l() {
        return this.e;
    }

    public final boolean m() {
        return this.a;
    }

    public final boolean n() {
        return this.g;
    }

    public final boolean o() {
        return this.h;
    }

    public final boolean p() {
        return this.i;
    }

    public final boolean q() {
        return this.t;
    }

    public final boolean r() {
        return this.j;
    }

    public final boolean s() {
        return this.p;
    }

    public final boolean t() {
        return this.s;
    }

    public final boolean u() {
        return this.k;
    }

    public final boolean v() {
        return this.l;
    }

    public final boolean w() {
        return this.w;
    }

    public final boolean x() {
        return this.m;
    }

    public final boolean y() {
        return this.n;
    }

    public final boolean z() {
        return this.q;
    }

    public final Brush a() {
        return this.d;
    }

    public final void b(boolean z) {
        this.g = z;
    }

    public final void c(boolean z) {
        this.h = z;
    }

    public final void d(boolean z) {
        this.i = z;
    }

    public final String e() {
        return this.y;
    }

    public final String f() {
        return this.v;
    }

    public final void g(boolean z) {
        this.p = z;
    }

    public final String h() {
        return this.z;
    }

    public final String i() {
        return this.A;
    }

    public final void j(boolean z) {
        this.l = z;
    }

    public final void k(boolean z) {
        this.w = z;
    }

    public final void l(boolean z) {
        this.m = z;
    }

    public final void m(boolean z) {
        this.n = z;
    }

    public final void n(boolean z) {
        this.q = z;
    }

    public final void o(boolean z) {
        this.o = z;
    }

    public final void p(String str) {
        this.F = str;
    }

    public final void q(String str) {
        this.J = str;
    }

    public final void a(Brush brush) {
        this.d = brush;
    }

    public final void b(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.u = str;
    }

    public final String c() {
        return this.x;
    }

    public final String d() {
        return this.u;
    }

    public final void e(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.z = str;
    }

    public final void f(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.A = str;
    }

    public final void g(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.C = str;
    }

    public final void h(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.B = str;
    }

    public final void i(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.D = str;
    }

    public final void j(String str) {
        this.E = str;
    }

    public final void k(String str) {
        this.K = str;
    }

    public final void l(String str) {
        this.H = str;
    }

    public final void m(String str) {
        this.I = str;
    }

    public final void n(String str) {
        this.G = str;
    }

    public final void o(String str) {
        this.L = str;
    }

    public final void a(BarcodeFilterHighlightSettings barcodeFilterHighlightSettings) {
        this.f = barcodeFilterHighlightSettings;
    }

    public final BarcodeCountNotInListActionSettings b() {
        return this.N;
    }

    public final void c(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.y = str;
    }

    public final void d(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.v = str;
    }

    public final void a(Anchor anchor) {
        Intrinsics.checkNotNullParameter(anchor, "<set-?>");
        this.r = anchor;
    }

    public final void a(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.x = str;
    }

    public final void a(BarcodeCountToolbarSettings barcodeCountToolbarSettings) {
        Intrinsics.checkNotNullParameter(barcodeCountToolbarSettings, "<set-?>");
        this.M = barcodeCountToolbarSettings;
    }

    public final void a(BarcodeCountNotInListActionSettings barcodeCountNotInListActionSettings) {
        Intrinsics.checkNotNullParameter(barcodeCountNotInListActionSettings, "<set-?>");
        this.N = barcodeCountNotInListActionSettings;
    }
}
