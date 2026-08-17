package com.scandit.datacapture.barcode.internal.module.count.ui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.scandit.datacapture.core.internal.sdk.ui.hint.HintHolderV2;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class g implements BarcodeCountViewLayerManager {
    public final Context a;
    public final FrameLayout b;
    public final RelativeLayout c;
    public final RelativeLayout d;
    public final FrameLayout e;
    public final FrameLayout f;
    public final FrameLayout g;
    public final FrameLayout h;
    public final FrameLayout i;
    public final FrameLayout j;
    public final FrameLayout k;
    public final FrameLayout l;
    public final HintHolderV2 m;
    public final View n;
    public final List o;
    public final FrameLayout.LayoutParams p;
    public final RelativeLayout.LayoutParams q;

    public g(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = context;
        this.b = new FrameLayout(context);
        this.c = new RelativeLayout(context);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        this.d = relativeLayout;
        FrameLayout frameLayout = new FrameLayout(context);
        this.e = frameLayout;
        FrameLayout frameLayout2 = new FrameLayout(context);
        this.f = frameLayout2;
        this.g = new FrameLayout(context);
        FrameLayout frameLayout3 = new FrameLayout(context);
        this.h = frameLayout3;
        this.i = new FrameLayout(context);
        this.j = new FrameLayout(context);
        FrameLayout frameLayout4 = new FrameLayout(context);
        this.k = frameLayout4;
        this.l = new FrameLayout(context);
        HintHolderV2 implementation = HintHolderV2.INSTANCE.implementation(context);
        this.m = implementation;
        View asView = implementation.asView();
        this.n = asView;
        this.o = CollectionsKt.listOf((Object[]) new View[]{frameLayout, relativeLayout, frameLayout2, frameLayout3, frameLayout4, asView});
        this.p = new FrameLayout.LayoutParams(-1, -1);
        this.q = new RelativeLayout.LayoutParams(-1, -1);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final FrameLayout a() {
        return this.i;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final FrameLayout b() {
        return this.k;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final FrameLayout c() {
        return this.g;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final FrameLayout d() {
        return this.b;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final Context e() {
        return this.a;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final RelativeLayout f() {
        return this.c;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final FrameLayout g() {
        return this.j;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final FrameLayout h() {
        return this.l;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final HintHolderV2 i() {
        return this.m;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final void a(FrameLayout overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        overlay.addView(this.i, this.p);
        overlay.addView(this.j, this.p);
        overlay.addView(this.b, this.p);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final void a(RelativeLayout container) {
        Intrinsics.checkNotNullParameter(container, "container");
        container.addView(this.n, this.q);
        container.addView(this.e, this.q);
        container.addView(this.c, this.q);
        container.addView(this.g, this.q);
        container.addView(this.d, this.q);
        container.addView(this.f, this.q);
        container.addView(this.h, this.q);
        container.addView(this.l, this.q);
        container.addView(this.k, this.q);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager
    public final void a(RelativeLayout.LayoutParams floatLayerLayoutParams) {
        Intrinsics.checkNotNullParameter(floatLayerLayoutParams, "floatLayerLayoutParams");
        Iterator it = this.o.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setLayoutParams(floatLayerLayoutParams);
        }
    }
}
