package com.scandit.datacapture.barcode.internal.module.find.ui;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItem;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindViewDefaults;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindViewSettings;
import com.scandit.datacapture.barcode.find.ui.PreviewResolutionRatioHandler;
import com.scandit.datacapture.barcode.find.ui.ScreenSizeCategoryHandler;
import com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.C0414c;
import com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.C0415d;
import com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.C0416e;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.module.ui.NativeToastHintStyle;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.ui.cardscarousel.CardsCarouselView;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintHolderV2;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintPresenterV2;
import com.scandit.datacapture.core.internal.sdk.ui.hint.ToastHint;
import com.scandit.datacapture.core.ui.DataCaptureView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.PropertyReference0Impl;

/* loaded from: classes2.dex */
public final class u0 implements BarcodeFindViewLayout {
    public final DataCaptureView a;
    public final ScreenSizeCategoryHandler b;
    public final PreviewResolutionRatioHandler c;
    public final C0390a d;
    public final com.scandit.datacapture.barcode.internal.module.ui.n e;
    public final com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.f f;
    public final com.scandit.datacapture.barcode.internal.module.ui.b g;
    public final com.scandit.datacapture.barcode.internal.module.ui.e h;
    public final CardsCarouselView i;
    public final com.scandit.datacapture.barcode.internal.module.find.ui.camera.e j;
    public final com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.u k;
    public final HintHolderV2 l;
    public final HintPresenterV2 m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public String t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public Anchor y;
    public final int z;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v2, types: [com.scandit.datacapture.barcode.internal.module.find.ui.G, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v6, types: [com.scandit.datacapture.barcode.internal.module.find.ui.I, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v7, types: [com.scandit.datacapture.barcode.internal.module.find.ui.s, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v4, types: [com.scandit.datacapture.barcode.internal.module.find.ui.t, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v4, types: [com.scandit.datacapture.barcode.internal.module.find.ui.v, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v6, types: [com.scandit.datacapture.barcode.internal.module.find.ui.u, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v22, types: [com.scandit.datacapture.barcode.internal.module.find.ui.C, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v24, types: [com.scandit.datacapture.barcode.internal.module.find.ui.w, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v26, types: [com.scandit.datacapture.barcode.internal.module.find.ui.H, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.scandit.datacapture.barcode.internal.module.find.ui.E, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.scandit.datacapture.barcode.internal.module.find.ui.z, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.scandit.datacapture.barcode.internal.module.find.ui.D, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v5, types: [com.scandit.datacapture.barcode.internal.module.find.ui.x, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.scandit.datacapture.barcode.internal.module.find.ui.J, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v10, types: [com.scandit.datacapture.barcode.internal.module.find.ui.F, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v14, types: [com.scandit.datacapture.barcode.internal.module.find.ui.y, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v15, types: [com.scandit.datacapture.barcode.internal.module.find.ui.K, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v6, types: [com.scandit.datacapture.barcode.internal.module.find.ui.A, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.scandit.datacapture.barcode.internal.module.find.ui.B, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v7, types: [com.scandit.datacapture.barcode.internal.module.find.ui.L, java.lang.Object] */
    public u0(Context context, BarcodeFindViewSettings settings, DataCaptureView dataCaptureView, ScreenSizeCategoryHandler screenSizeHandler, PreviewResolutionRatioHandler resolutionRatioHandler) {
        RelativeLayout.LayoutParams layoutParams;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        Intrinsics.checkNotNullParameter(screenSizeHandler, "screenSizeCategoryHandler");
        Intrinsics.checkNotNullParameter(resolutionRatioHandler, "cameraPreviewRatioHandler");
        this.a = dataCaptureView;
        this.b = screenSizeHandler;
        this.c = resolutionRatioHandler;
        C0390a c0390a = new C0390a(context);
        c0390a.setId(R.id.sc_find_dots_view);
        c0390a.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        c0390a.e = settings.getInListItemColor();
        c0390a.f = settings.getNotInListItemColor();
        this.d = c0390a;
        com.scandit.datacapture.barcode.internal.module.ui.n nVar = new com.scandit.datacapture.barcode.internal.module.ui.n(context);
        nVar.setId(R.id.sc_find_guidance_view);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        nVar.setLayoutParams(layoutParams2);
        nVar.setVisibility(8);
        this.e = nVar;
        com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.f view = new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.f(context);
        view.setId(R.id.sc_find_shutter_button_view);
        this.f = view;
        com.scandit.datacapture.barcode.internal.module.ui.b view2 = new com.scandit.datacapture.barcode.internal.module.ui.b(context);
        view2.setId(R.id.sc_find_exit_button_view);
        view2.b.setImageResource(R.drawable.sc_ic_exit);
        view2.a(true);
        this.g = view2;
        com.scandit.datacapture.barcode.internal.module.ui.e view3 = new com.scandit.datacapture.barcode.internal.module.ui.e(context);
        view3.setId(R.id.sc_find_progress_bar);
        view3.b = 0;
        view3.b();
        view3.a = 0;
        view3.b();
        view3.setVisibility(4);
        view3.c = settings.getProgressBarStartColor();
        view3.b();
        view3.d = settings.getProgressBarFinishColor();
        view3.b();
        this.h = view3;
        CardsCarouselView view4 = new CardsCarouselView(context, null, 0, 6, null);
        view4.setId(R.id.sc_find_carousel_view);
        view4.setUiListener(new r(this));
        this.i = view4;
        com.scandit.datacapture.barcode.internal.module.find.ui.camera.e view5 = new com.scandit.datacapture.barcode.internal.module.find.ui.camera.e(context);
        view5.setId(R.id.sc_find_zoom_control);
        view5.setVisibility(4);
        this.j = view5;
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.e eVar = com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f.e;
        ?? shouldShow = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.E
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return Boolean.valueOf(((u0) this.receiver).o);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((u0) this.receiver).a(((Boolean) obj).booleanValue());
            }
        };
        ?? dataCaptureViewGetter = new PropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.F
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((u0) this.receiver).a;
            }
        };
        com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.r isTallFormFactor = new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.r();
        Intrinsics.checkNotNullParameter(eVar, "<this>");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(shouldShow, "shouldShow");
        Intrinsics.checkNotNullParameter(screenSizeHandler, "sizeCategoryHandler");
        Intrinsics.checkNotNullParameter(resolutionRatioHandler, "previewResolutionHandler");
        Intrinsics.checkNotNullParameter(dataCaptureViewGetter, "dataCaptureViewGetter");
        Intrinsics.checkNotNullParameter(isTallFormFactor, "isTallFormFactor");
        final com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f(new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.q(shouldShow, screenSizeHandler, resolutionRatioHandler, isTallFormFactor, dataCaptureViewGetter), new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.s(view));
        ?? shouldShow2 = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.z
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return Boolean.valueOf(((u0) this.receiver).p);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((u0) this.receiver).e(((Boolean) obj).booleanValue());
            }
        };
        ?? isShowingProgressBar = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.A
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return Boolean.valueOf(((u0) this.receiver).q);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((u0) this.receiver).b(((Boolean) obj).booleanValue());
            }
        };
        ?? shutterButtonViewGetter = new MutablePropertyReference0Impl(fVar) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.B
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f) this.receiver).b();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar2 = (com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f) this.receiver;
                View view6 = (View) obj;
                fVar2.getClass();
                Intrinsics.checkNotNullParameter(view6, "<set-?>");
                fVar2.c = view6;
            }
        };
        com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.k isTallFormFactor2 = new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.k();
        Intrinsics.checkNotNullParameter(eVar, "<this>");
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(shouldShow2, "shouldShow");
        Intrinsics.checkNotNullParameter(screenSizeHandler, "screenSizeHandler");
        Intrinsics.checkNotNullParameter(shutterButtonViewGetter, "shutterButtonViewGetter");
        Intrinsics.checkNotNullParameter(isShowingProgressBar, "isShowingProgressBar");
        Intrinsics.checkNotNullParameter(isTallFormFactor2, "isTallFormFactor");
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar2 = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f(new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.j(shouldShow2, isTallFormFactor2, screenSizeHandler, isShowingProgressBar, shutterButtonViewGetter), new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.l(view2));
        ?? shouldShow3 = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.C
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return Boolean.valueOf(((u0) this.receiver).q);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((u0) this.receiver).b(((Boolean) obj).booleanValue());
            }
        };
        ?? dataCaptureViewGetter2 = new PropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.D
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((u0) this.receiver).a;
            }
        };
        Intrinsics.checkNotNullParameter(eVar, "<this>");
        Intrinsics.checkNotNullParameter(view3, "view");
        Intrinsics.checkNotNullParameter(shouldShow3, "shouldShow");
        Intrinsics.checkNotNullParameter(dataCaptureViewGetter2, "dataCaptureViewGetter");
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar3 = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f(new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.m(shouldShow3, dataCaptureViewGetter2), new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.n(view3));
        ?? shouldShow4 = new PropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.w
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                u0 u0Var = (u0) this.receiver;
                return Boolean.valueOf(u0Var.s && u0Var.r);
            }
        };
        ?? shutterButtonReferenceViewGetter = new MutablePropertyReference0Impl(fVar) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.x
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f) this.receiver).b();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar4 = (com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f) this.receiver;
                View view6 = (View) obj;
                fVar4.getClass();
                Intrinsics.checkNotNullParameter(view6, "<set-?>");
                fVar4.c = view6;
            }
        };
        ?? progressBarReferenceViewGetter = new PropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.y
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((u0) this.receiver).h;
            }
        };
        C0415d isTallFormFactor3 = new C0415d();
        Intrinsics.checkNotNullParameter(eVar, "<this>");
        Intrinsics.checkNotNullParameter(view4, "view");
        Intrinsics.checkNotNullParameter(shouldShow4, "shouldShow");
        Intrinsics.checkNotNullParameter(shutterButtonReferenceViewGetter, "shutterButtonReferenceViewGetter");
        Intrinsics.checkNotNullParameter(progressBarReferenceViewGetter, "progressBarReferenceViewGetter");
        Intrinsics.checkNotNullParameter(isTallFormFactor3, "isTallFormFactor");
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar4 = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f(new C0414c(shouldShow4, shutterButtonReferenceViewGetter, progressBarReferenceViewGetter, isTallFormFactor3), new C0416e(view4));
        ?? shouldShow5 = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.G
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return Boolean.valueOf(((u0) this.receiver).n);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                u0 u0Var = (u0) this.receiver;
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (u0Var.n == booleanValue) {
                    return;
                }
                u0Var.n = booleanValue;
                ViewExtensionsKt.runOnMainThread(u0Var.j, new i0(u0Var));
            }
        };
        ?? dataCaptureViewGetter3 = new PropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.H
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((u0) this.receiver).a;
            }
        };
        ?? shutterButtonViewGetter2 = new MutablePropertyReference0Impl(fVar) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.I
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f) this.receiver).b();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar5 = (com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f) this.receiver;
                View view6 = (View) obj;
                fVar5.getClass();
                Intrinsics.checkNotNullParameter(view6, "<set-?>");
                fVar5.c = view6;
            }
        };
        ?? torchButtonPosition = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.J
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((u0) this.receiver).y;
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((u0) this.receiver).a((Anchor) obj);
            }
        };
        ?? isShowingTorchButton = new PropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.K
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                u0 u0Var = (u0) this.receiver;
                return Boolean.valueOf(u0Var.x && u0Var.w);
            }
        };
        ?? isShowingProgressBar2 = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.L
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return Boolean.valueOf(((u0) this.receiver).q);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((u0) this.receiver).b(((Boolean) obj).booleanValue());
            }
        };
        com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.E isTallFormFactor4 = new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.E();
        Intrinsics.checkNotNullParameter(eVar, "<this>");
        Intrinsics.checkNotNullParameter(view5, "view");
        Intrinsics.checkNotNullParameter(shouldShow5, "shouldShow");
        Intrinsics.checkNotNullParameter(dataCaptureViewGetter3, "dataCaptureViewGetter");
        Intrinsics.checkNotNullParameter(shutterButtonViewGetter2, "shutterButtonViewGetter");
        Intrinsics.checkNotNullParameter(screenSizeHandler, "screenSizeHandler");
        Intrinsics.checkNotNullParameter(resolutionRatioHandler, "previewResolutionHandler");
        Intrinsics.checkNotNullParameter(isShowingProgressBar2, "isShowingProgressBar");
        Intrinsics.checkNotNullParameter(isShowingTorchButton, "isShowingTorchButton");
        Intrinsics.checkNotNullParameter(torchButtonPosition, "torchButtonPosition");
        Intrinsics.checkNotNullParameter(isTallFormFactor4, "isTallFormFactor");
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f fVar5 = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f(new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.D(shouldShow5, dataCaptureViewGetter3, shutterButtonViewGetter2, screenSizeHandler, resolutionRatioHandler, isShowingProgressBar2, isShowingTorchButton, torchButtonPosition, isTallFormFactor4), new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.F(view5));
        ?? isShowingProgressBar3 = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.s
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return Boolean.valueOf(((u0) this.receiver).q);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((u0) this.receiver).b(((Boolean) obj).booleanValue());
            }
        };
        ?? isShowingTorchButton2 = new PropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.t
            @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                u0 u0Var = (u0) this.receiver;
                return Boolean.valueOf(u0Var.x && u0Var.w);
            }
        };
        ?? torchButtonPosition2 = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.u
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((u0) this.receiver).y;
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((u0) this.receiver).a((Anchor) obj);
            }
        };
        ?? isShowingFinishButton = new MutablePropertyReference0Impl(this) { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.v
            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return Boolean.valueOf(((u0) this.receiver).p);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((u0) this.receiver).e(((Boolean) obj).booleanValue());
            }
        };
        com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.h isTallFormFactor5 = new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.h();
        Intrinsics.checkNotNullParameter(eVar, "<this>");
        Intrinsics.checkNotNullParameter(dataCaptureView, "dataCaptureView");
        Intrinsics.checkNotNullParameter(screenSizeHandler, "screenSizeHandler");
        Intrinsics.checkNotNullParameter(resolutionRatioHandler, "previewResolutionHandler");
        Intrinsics.checkNotNullParameter(isShowingProgressBar3, "isShowingProgressBar");
        Intrinsics.checkNotNullParameter(isShowingTorchButton2, "isShowingTorchButton");
        Intrinsics.checkNotNullParameter(isShowingFinishButton, "isShowingFinishButton");
        Intrinsics.checkNotNullParameter(torchButtonPosition2, "torchButtonPosition");
        Intrinsics.checkNotNullParameter(isTallFormFactor5, "isTallFormFactor");
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f dataCaptureViewWrapper = new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f(new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.g(screenSizeHandler, resolutionRatioHandler, isShowingProgressBar3, isShowingTorchButton2, isShowingFinishButton, torchButtonPosition2, isTallFormFactor5), new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.i(dataCaptureView));
        List dependantViewWrappers = CollectionsKt.listOf((Object[]) new com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f[]{fVar, fVar2, fVar3, fVar4, fVar5});
        Intrinsics.checkNotNullParameter(resolutionRatioHandler, "resolutionRatioHandler");
        Intrinsics.checkNotNullParameter(dataCaptureViewWrapper, "dataCaptureViewWrapper");
        Intrinsics.checkNotNullParameter(dependantViewWrappers, "dependantViewWrappers");
        this.k = new com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.u(resolutionRatioHandler, dataCaptureViewWrapper, dependantViewWrappers);
        HintHolderV2 implementation = HintHolderV2.INSTANCE.implementation(context);
        Intrinsics.checkNotNull(implementation, "null cannot be cast to non-null type android.view.View");
        View view6 = (View) implementation;
        if (ViewExtensionsKt.getOrientation(view6) == 1) {
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(3, R.id.sc_find_progress_bar);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        }
        view6.setLayoutParams(layoutParams);
        this.l = implementation;
        this.m = new HintPresenterV2(implementation);
        this.o = true;
        this.p = true;
        this.s = true;
        this.t = BarcodeFindViewDefaults.getDefaultTextForCollapseCardsButton();
        this.u = true;
        this.v = true;
        this.y = BarcodeFindViewDefaults.getDefaultTorchControlPosition();
        this.z = context.getResources().getColor(R.color.sc_barcode_find_card_background_color, context.getTheme());
        dataCaptureView.setVisibility(4);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(boolean z) {
        if (this.o == z) {
            return;
        }
        this.o = z;
        ViewExtensionsKt.runOnMainThread(this.f, new e0(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final boolean b() {
        return this.o;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final boolean c() {
        return this.x;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void d(boolean z) {
        if (this.s == z) {
            return;
        }
        this.s = z;
        ViewExtensionsKt.runOnMainThread(this.i, new c0(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final boolean e() {
        return this.p;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void f() {
        if (this.w) {
            this.w = false;
            ViewExtensionsKt.runOnMainThread(this.a, new p0(this));
        }
        ViewExtensionsKt.runOnMainThread(this.a, new O(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void g() {
        if (!this.w) {
            this.w = true;
            ViewExtensionsKt.runOnMainThread(this.a, new p0(this));
        }
        ViewExtensionsKt.runOnMainThread(this.a, new m0(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final boolean getShouldShowHints() {
        return this.u;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final boolean h() {
        return this.s;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final Anchor i() {
        return this.y;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final boolean j() {
        return this.v;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void k() {
        this.k.b();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void l() {
        ViewExtensionsKt.runOnMainThread(this.e, new N(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void m() {
        this.a.post(new Runnable() { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.u0$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                u0.a(u0.this);
            }
        });
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final PointF mapFramePointToView(Point point) {
        Intrinsics.checkNotNullParameter(point, "point");
        Point mapFramePointToView = this.a.mapFramePointToView(point);
        return new PointF(mapFramePointToView.getX(), mapFramePointToView.getY());
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void n() {
        ViewExtensionsKt.runOnMainThread(this.f, X.a);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void o() {
        P block = new P(this);
        Intrinsics.checkNotNullParameter(block, "block");
        ViewExtensionsKt.runOnMainThread(this.a, new T(block));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final boolean p() {
        return this.q;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final String q() {
        return this.t;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void setShouldShowHints(boolean z) {
        if (this.u == z) {
            return;
        }
        this.u = z;
        if (z) {
            return;
        }
        this.m.hideAllToasts();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void b(boolean z) {
        if (this.q == z) {
            return;
        }
        this.q = z;
        ViewExtensionsKt.runOnMainThread(this.h, new f0(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void c(boolean z) {
        if (this.x == z) {
            return;
        }
        this.x = z;
        ViewExtensionsKt.runOnMainThread(this.a, new g0(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void e(boolean z) {
        if (this.p == z) {
            return;
        }
        this.p = z;
        ViewExtensionsKt.runOnMainThread(this.g, new d0(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void h(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        ViewExtensionsKt.runOnMainThread(this.e, new h0(this, z));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a() {
        if (this.r) {
            this.r = false;
            ViewExtensionsKt.runOnMainThread(this.i, new o0(this));
        }
        ViewExtensionsKt.runOnMainThread(this.i, new M(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void d(String str) {
        if (Intrinsics.areEqual(this.t, str)) {
            return;
        }
        this.t = str;
        ViewExtensionsKt.runOnMainThread(this.i, new q0(this, str));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void b(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (this.v) {
            ViewExtensionsKt.runOnMainThread(this.e, new k0(this, text));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void c(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.m.hideToast(tag);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void f(boolean z) {
        ViewExtensionsKt.runOnMainThread(this.d, new U(this, z));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void g(boolean z) {
        ViewExtensionsKt.runOnMainThread(this.f, new W(z));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void b(BarcodeFindItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        ViewExtensionsKt.runOnMainThread(this.i, new Q(this, item));
    }

    public static final void b(BarcodeFindViewPresenter presenter, View view) {
        Intrinsics.checkNotNullParameter(presenter, "$presenter");
        presenter.i();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void d() {
        ViewExtensionsKt.runOnMainThread(this.f, Y.a);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(Map foundItems) {
        Intrinsics.checkNotNullParameter(foundItems, "foundItems");
        if (!this.r) {
            this.r = true;
            ViewExtensionsKt.runOnMainThread(this.i, new o0(this));
        }
        ViewExtensionsKt.runOnMainThread(this.i, new j0(this, foundItems));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (this.v) {
            ViewExtensionsKt.runOnMainThread(this.e, new l0(this, text));
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(Anchor value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.y == value) {
            return;
        }
        this.y = value;
        ViewExtensionsKt.runOnMainThread(this.a, new r0(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(RelativeLayout parent, com.scandit.datacapture.barcode.internal.module.find.ui.overlay.a overlay) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        overlay.a(this.d);
        overlay.a(this.e);
        com.scandit.datacapture.barcode.internal.module.find.ui.wrappers.u uVar = this.k;
        uVar.getClass();
        Intrinsics.checkNotNullParameter(parent, "parent");
        if (!uVar.e) {
            uVar.e = true;
            uVar.b.a(parent);
            if (uVar.d && uVar.e && uVar.f) {
                uVar.b.b().setVisibility(0);
                Iterator it = uVar.c.iterator();
                while (it.hasNext()) {
                    ((com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.f) it.next()).a(uVar.b.a());
                }
            }
        }
        Object obj = this.l;
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.view.View");
        parent.addView((View) obj);
        this.a.addListener(new Z(this));
    }

    public static final void a(u0 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.k.b();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(ArrayList dotsInfo) {
        Intrinsics.checkNotNullParameter(dotsInfo, "dotsInfo");
        ViewExtensionsKt.runOnMainThread(this.d, new s0(this, dotsInfo));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(BarcodeFindItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        ViewExtensionsKt.runOnMainThread(this.d, new S(this, item));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(LinkedHashMap searchedItems, Map foundItems) {
        Intrinsics.checkNotNullParameter(searchedItems, "searchedItems");
        Intrinsics.checkNotNullParameter(foundItems, "foundItems");
        ViewExtensionsKt.runOnMainThread(this.i, new t0(this, searchedItems, foundItems));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(int i, int i2) {
        ViewExtensionsKt.runOnMainThread(this.h, new V(this, i, i2));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(final BarcodeFindViewPresenter presenter) {
        Intrinsics.checkNotNullParameter(presenter, "presenter");
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.u0$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                u0.a(BarcodeFindViewPresenter.this, view);
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.scandit.datacapture.barcode.internal.module.find.ui.u0$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                u0.b(BarcodeFindViewPresenter.this, view);
            }
        });
        this.d.a = new a0(presenter);
        this.j.c = new b0(presenter);
    }

    public static final void a(BarcodeFindViewPresenter presenter, View view) {
        Intrinsics.checkNotNullParameter(presenter, "$presenter");
        presenter.b();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(String hint, NativeToastHintStyle style, String tag) {
        Intrinsics.checkNotNullParameter(hint, "hint");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.m.showToast(new ToastHint(style, hint, tag, false, 8, null));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(w0 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        ViewExtensionsKt.runOnMainThread(this.a, new T(block));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.BarcodeFindViewLayout
    public final void a(List zoomLevels, float f) {
        Intrinsics.checkNotNullParameter(zoomLevels, "zoomLevels");
        ViewExtensionsKt.runOnMainThread(this.j, new n0(this, zoomLevels, f));
    }
}
