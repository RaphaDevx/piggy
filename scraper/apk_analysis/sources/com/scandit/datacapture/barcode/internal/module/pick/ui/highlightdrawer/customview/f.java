package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.BarcodePickElementsCache;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class f extends FrameLayout implements BarcodePickDrawer {
    public final ViewGroup a;
    public final boolean b;
    public final com.scandit.datacapture.barcode.internal.module.ui.j c;
    public final BarcodePickViewHighlightStyle d;
    public final j e;
    public final b f;
    public final BarcodePickElementsCache g;
    public final Function1 h;
    public final Paint i;
    public final Map j;
    public final Map k;
    public final List l;
    public final Map m;
    public boolean n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(BarcodePickBasicOverlay container, boolean z, com.scandit.datacapture.barcode.internal.module.ui.j touchEventHandler, BarcodePickViewHighlightStyle.CustomView highlightStyle, j drawSettings, c drawDataFactory, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.f cache, Function1 statusIconViewDrawerProvider, Paint tapPaint, Map drawingData) {
        super(container.getContext());
        LinkedHashMap viewContainers = new LinkedHashMap();
        ArrayList viewContainerCache = new ArrayList();
        LinkedHashMap statusIconViewDrawers = new LinkedHashMap();
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(touchEventHandler, "touchEventHandler");
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        Intrinsics.checkNotNullParameter(drawDataFactory, "drawDataFactory");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(statusIconViewDrawerProvider, "statusIconViewDrawerProvider");
        Intrinsics.checkNotNullParameter(tapPaint, "tapPaint");
        Intrinsics.checkNotNullParameter(drawingData, "drawingData");
        Intrinsics.checkNotNullParameter(viewContainers, "viewContainers");
        Intrinsics.checkNotNullParameter(viewContainerCache, "viewContainerCache");
        Intrinsics.checkNotNullParameter(statusIconViewDrawers, "statusIconViewDrawers");
        this.a = container;
        this.b = z;
        this.c = touchEventHandler;
        this.d = highlightStyle;
        this.e = drawSettings;
        this.f = drawDataFactory;
        this.g = cache;
        this.h = statusIconViewDrawerProvider;
        this.i = tapPaint;
        this.j = drawingData;
        this.k = viewContainers;
        this.l = viewContainerCache;
        this.m = statusIconViewDrawers;
        container.addView(this, new FrameLayout.LayoutParams(-1, -1));
        setElevation(PixelExtensionsKt.pxFromDp$default(4.0f, (Context) null, 1, (Object) null));
        setWillNotDraw(false);
        setClickable(true);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final boolean a() {
        return false;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void b(TrackedObject track, BarcodePickState pickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        if (this.n) {
            c(track, pickState);
            invalidate();
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) this.m.get(Integer.valueOf(track.getIdentifier()));
            if (eVar != null) {
                eVar.a(track, pickState);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x032b A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(com.scandit.datacapture.barcode.batch.data.TrackedObject r19, com.scandit.datacapture.barcode.pick.capture.BarcodePickState r20) {
        /*
            Method dump skipped, instructions count: 812
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.customview.f.c(com.scandit.datacapture.barcode.batch.data.TrackedObject, com.scandit.datacapture.barcode.pick.capture.BarcodePickState):void");
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (this.n && this.e.a) {
            Iterator it = this.j.values().iterator();
            while (it.hasNext()) {
                canvas.drawPath(((a) it.next()).c.b, this.i);
            }
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return ((com.scandit.datacapture.barcode.internal.module.ui.k) this.c).a(event);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void start() {
        this.n = true;
        invalidate();
        for (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar : this.m.values()) {
            eVar.g = false;
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h hVar = eVar.h;
            if (hVar != null) {
                com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.b onFinished = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.b.a;
                Intrinsics.checkNotNullParameter(onFinished, "onFinished");
                if (hVar.o == com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.a.b) {
                    hVar.a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.a.a, onFinished);
                } else {
                    onFinished.invoke();
                }
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void stop() {
        this.n = false;
        Iterator it = this.j.values().iterator();
        while (it.hasNext()) {
            View view = ((a) it.next()).a;
            view.setOnClickListener(null);
            ViewExtensionsKt.removeFromSuperview(view);
        }
        this.j.clear();
        Iterator it2 = this.m.values().iterator();
        while (it2.hasNext()) {
            ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) it2.next()).a();
        }
        this.m.clear();
        Iterator it3 = this.m.values().iterator();
        while (it3.hasNext()) {
            ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) it3.next()).a();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(List tracks) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(TrackedObject track, BarcodePickState pickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        if (this.n) {
            c(track, pickState);
            invalidate();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void b() {
        this.n = true;
        invalidate();
        Iterator it = this.m.values().iterator();
        while (it.hasNext()) {
            ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) it.next()).g = true;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(int i) {
        if (this.n) {
            b(i);
            invalidate();
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) this.m.get(Integer.valueOf(i));
            if (eVar != null) {
                eVar.a();
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(BarcodePickViewHighlightStyle highlightStyle) {
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        for (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar : this.m.values()) {
            eVar.getClass();
            Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
            eVar.d = highlightStyle;
        }
    }

    public final void b(int i) {
        View view;
        a aVar = (a) this.j.remove(Integer.valueOf(i));
        if (aVar != null && (view = aVar.a) != null) {
            view.setOnClickListener(null);
            ViewExtensionsKt.removeFromSuperview(view);
        }
        FrameLayout frameLayout = (FrameLayout) this.k.remove(Integer.valueOf(i));
        if (frameLayout != null) {
            frameLayout.removeAllViews();
            ViewExtensionsKt.removeFromSuperview(frameLayout);
            this.l.add(frameLayout);
        }
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) this.m.get(Integer.valueOf(i));
        if (eVar != null) {
            eVar.a();
        }
        this.m.remove(Integer.valueOf(i));
    }

    public final FrameLayout a(Context context, int i) {
        FrameLayout frameLayout = (FrameLayout) this.k.get(Integer.valueOf(i));
        if (frameLayout == null && (frameLayout = (FrameLayout) CollectionsKt.removeFirstOrNull(this.l)) == null) {
            frameLayout = new FrameLayout(context);
            this.k.put(Integer.valueOf(i), frameLayout);
            if (frameLayout.getParent() == null) {
                this.a.addView(frameLayout, new ViewGroup.LayoutParams(-1, -1));
            }
        }
        return frameLayout;
    }
}
