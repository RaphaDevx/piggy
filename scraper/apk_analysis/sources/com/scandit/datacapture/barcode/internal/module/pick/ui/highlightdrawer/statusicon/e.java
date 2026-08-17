package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.j;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickElementsCache;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.i;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.internal.sdk.ui.icon.ScanditIconUtilsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.scandit.datacapture.core.ui.icon.ScanditIcon;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class e {
    public final FrameLayout a;
    public final j b;
    public final com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.j c;
    public BarcodePickViewHighlightStyle d;
    public final com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.c e;
    public boolean f;
    public boolean g;
    public h h;
    public Function1 i;
    public Function2 j;

    public e(FrameLayout container, j styleBehaviour, BarcodePickElementsCache cache, BarcodePickViewHighlightStyle highlightStyle, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.c drawDataFactory) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(styleBehaviour, "styleBehaviour");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        Intrinsics.checkNotNullParameter(drawDataFactory, "drawDataFactory");
        this.a = container;
        this.b = styleBehaviour;
        this.c = cache;
        this.d = highlightStyle;
        this.e = drawDataFactory;
        this.f = false;
    }

    public static final boolean a(e eVar, View view) {
        eVar.getClass();
        return view.getY() >= 0.0f && eVar.g;
    }

    public final void a(TrackedObject track, BarcodePickState pickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        h hVar = this.h;
        BarcodePickState barcodePickState = hVar != null ? hVar.l : null;
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.a a = this.e.a(track, pickState, barcodePickState);
        i a2 = this.c.a(track.getData(), pickState, barcodePickState);
        if (a2 == null) {
            h hVar2 = this.h;
            if (hVar2 != null) {
                a(hVar2, a);
                return;
            }
            return;
        }
        BarcodePickStatusIconStyle a3 = a2.a();
        if (a3 == null && this.h != null) {
            a();
            return;
        }
        if (a3 != null && this.h == null) {
            a(track, a3, a);
            return;
        }
        h hVar3 = this.h;
        if (hVar3 != null) {
            a(hVar3, a);
        }
    }

    public final void a(TrackedObject trackedObject, BarcodePickStatusIconStyle style, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.a initialDrawData) {
        FrameLayout container = this.a;
        a layoutRefresher = new a(this);
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(initialDrawData, "initialDrawData");
        Intrinsics.checkNotNullParameter(layoutRefresher, "layoutRefresher");
        h hVar = new h(container, initialDrawData, layoutRefresher);
        Intrinsics.checkNotNullParameter(style, "style");
        if (style instanceof BarcodePickStatusIconStyle.BarcodePickStatusIconStyleWithIcon) {
            BarcodePickStatusIconStyle.BarcodePickStatusIconStyleWithIcon barcodePickStatusIconStyleWithIcon = (BarcodePickStatusIconStyle.BarcodePickStatusIconStyleWithIcon) style;
            hVar.a(barcodePickStatusIconStyleWithIcon.getText());
            hVar.g.setImageBitmap(barcodePickStatusIconStyleWithIcon.getIcon());
        } else if (style instanceof BarcodePickStatusIconStyle.BarcodePickStatusIconStyleWithColors) {
            BarcodePickStatusIconStyle.BarcodePickStatusIconStyleWithColors barcodePickStatusIconStyleWithColors = (BarcodePickStatusIconStyle.BarcodePickStatusIconStyleWithColors) style;
            hVar.a(barcodePickStatusIconStyleWithColors.getText());
            int iconBackgroundColor = barcodePickStatusIconStyleWithColors.getIconBackgroundColor();
            int iconColor = barcodePickStatusIconStyleWithColors.getIconColor();
            ImageView imageView = hVar.f;
            PorterDuff.Mode mode = PorterDuff.Mode.MULTIPLY;
            imageView.setColorFilter(iconBackgroundColor, mode);
            hVar.g.setColorFilter(iconColor, mode);
        } else if (style instanceof BarcodePickStatusIconStyle.BarcodePickStatusIconStyleWithScanditIcon) {
            BarcodePickStatusIconStyle.BarcodePickStatusIconStyleWithScanditIcon barcodePickStatusIconStyleWithScanditIcon = (BarcodePickStatusIconStyle.BarcodePickStatusIconStyleWithScanditIcon) style;
            hVar.a(barcodePickStatusIconStyleWithScanditIcon.getText());
            ScanditIcon icon = barcodePickStatusIconStyleWithScanditIcon.getIcon();
            ImageView imageView2 = hVar.g;
            Context context = imageView2.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            imageView2.setImageDrawable(ScanditIconUtilsKt.getDrawable(icon, context));
        }
        this.a.addView(hVar.b, new ViewGroup.LayoutParams(-2, -2));
        a(hVar, initialDrawData);
        this.h = hVar;
        Function2 function2 = this.j;
        if (function2 != null) {
            Intrinsics.checkNotNullParameter(function2, "<set-?>");
            hVar.m = function2;
        }
        hVar.n = new b(this, trackedObject);
    }

    public final void a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.c cVar, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.drawdata.a drawData) {
        h hVar = (h) cVar;
        hVar.getClass();
        Intrinsics.checkNotNullParameter(drawData, "drawData");
        BarcodePickState barcodePickState = hVar.k.c;
        if (barcodePickState != drawData.c) {
            hVar.l = barcodePickState;
        }
        hVar.k = drawData;
        View view = hVar.b;
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        view.setVisibility(!(viewGroup != null && viewGroup.getWidth() > 0) ? 8 : 0);
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.behaviour.a b = this.b.b(cVar, drawData);
        hVar.a(b.b, b.a);
        boolean z = b.c;
        int i = b.d;
        hVar.e.setVisibility(z ? 0 : 8);
        if (z) {
            ImageView imageView = hVar.e;
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            marginLayoutParams.topMargin = PixelExtensionsKt.pxFromDp$default(-1, (Context) null, 1, (Object) null);
            marginLayoutParams.leftMargin = i;
            imageView.setLayoutParams(marginLayoutParams);
        }
        int a = this.b.a(cVar, drawData);
        int i2 = (int) (a * 0.14285715f);
        int i3 = a - (i2 * 2);
        View view2 = hVar.d;
        ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams2, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
        marginLayoutParams2.height = i3;
        marginLayoutParams2.width = i3;
        view2.setLayoutParams(marginLayoutParams2);
        View view3 = hVar.c;
        ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
        layoutParams3.height = a;
        view3.setLayoutParams(layoutParams3);
        hVar.c.setPadding(i2, 0, i2, 0);
        hVar.h.setTextSize(0, i3);
        hVar.i = a;
    }

    public final void a() {
        h hVar = this.h;
        if (hVar != null) {
            this.a.removeView(hVar.b);
            hVar.a();
            Unit unit = Unit.INSTANCE;
        }
        this.h = null;
    }
}
