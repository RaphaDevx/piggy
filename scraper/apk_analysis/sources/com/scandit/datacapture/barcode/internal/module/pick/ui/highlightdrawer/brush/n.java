package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush;

import android.content.Context;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class n extends FrameLayout implements BarcodePickDrawer {
    public BarcodePickViewHighlightStyle a;
    public final b b;
    public final j c;
    public final com.scandit.datacapture.barcode.internal.module.ui.j d;
    public final Map e;
    public final Paint f;
    public final Paint g;
    public final Function1 h;
    public final Map i;
    public final List j;
    public final Map k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(BarcodePickBasicOverlay container, BarcodePickViewHighlightStyle highlightStyle, b drawDataFactory, j drawSettings, com.scandit.datacapture.barcode.internal.module.ui.j touchEventHandler, Map drawingData, Paint tapPaint, Paint boundingBoxPaint, Function1 statusIconViewDrawerProvider) {
        super(container.getContext());
        HashMap brushViewMap = new HashMap();
        ArrayList viewCachePool = new ArrayList();
        LinkedHashMap statusIconViewDrawers = new LinkedHashMap();
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        Intrinsics.checkNotNullParameter(drawDataFactory, "drawDataFactory");
        Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
        Intrinsics.checkNotNullParameter(touchEventHandler, "touchEventHandler");
        Intrinsics.checkNotNullParameter(drawingData, "drawingData");
        Intrinsics.checkNotNullParameter(tapPaint, "tapPaint");
        Intrinsics.checkNotNullParameter(boundingBoxPaint, "boundingBoxPaint");
        Intrinsics.checkNotNullParameter(statusIconViewDrawerProvider, "statusIconViewDrawerProvider");
        Intrinsics.checkNotNullParameter(brushViewMap, "brushViewMap");
        Intrinsics.checkNotNullParameter(viewCachePool, "viewCachePool");
        Intrinsics.checkNotNullParameter(statusIconViewDrawers, "statusIconViewDrawers");
        this.a = highlightStyle;
        this.b = drawDataFactory;
        this.c = drawSettings;
        this.d = touchEventHandler;
        this.e = drawingData;
        this.f = tapPaint;
        this.g = boundingBoxPaint;
        this.h = statusIconViewDrawerProvider;
        this.i = brushViewMap;
        this.j = viewCachePool;
        this.k = statusIconViewDrawers;
        container.addView(this, new FrameLayout.LayoutParams(-1, -1));
        setElevation(PixelExtensionsKt.pxFromDp$default(4.0f, (Context) null, 1, (Object) null));
        setClickable(true);
        setImportantForAccessibility(2);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final boolean a() {
        return false;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void b(TrackedObject track, BarcodePickState pickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        a a = ((i) this.b).a(track, pickState, this.a);
        this.e.put(Integer.valueOf(track.getIdentifier()), a);
        if (this.i.get(Integer.valueOf(track.getIdentifier())) == null) {
            a(track, a);
        } else {
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.b bVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.b) this.i.get(Integer.valueOf(track.getIdentifier()));
            if (bVar != null) {
                ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.c) bVar).a(a);
            }
        }
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) this.k.get(Integer.valueOf(track.getIdentifier()));
        if (eVar != null) {
            eVar.a(track, pickState);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        return ((com.scandit.datacapture.barcode.internal.module.ui.k) this.d).a(event);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void start() {
        for (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar : this.k.values()) {
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
        Iterator it = this.i.entrySet().iterator();
        while (it.hasNext()) {
            removeView(((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.c) ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.b) ((Map.Entry) it.next()).getValue())).a);
        }
        Iterator it2 = this.k.entrySet().iterator();
        while (it2.hasNext()) {
            ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) ((Map.Entry) it2.next()).getValue()).a();
        }
        this.j.clear();
        this.e.clear();
        this.i.clear();
        Iterator it3 = this.k.values().iterator();
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
        if (this.i.containsKey(Integer.valueOf(track.getIdentifier()))) {
            b(track, pickState);
            return;
        }
        a a = ((i) this.b).a(track, pickState, this.a);
        this.e.put(Integer.valueOf(track.getIdentifier()), a);
        Unit unit = Unit.INSTANCE;
        a(track, a);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void b() {
        Iterator it = this.k.values().iterator();
        while (it.hasNext()) {
            ((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) it.next()).g = true;
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(BarcodePickViewHighlightStyle highlightStyle) {
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        this.a = highlightStyle;
        for (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar : this.k.values()) {
            eVar.getClass();
            Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
            eVar.d = highlightStyle;
        }
    }

    public final void a(TrackedObject track, a initialDrawData) {
        BarcodePickStatusIconStyle a;
        Object obj = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.b) CollectionsKt.removeFirstOrNull(this.j);
        if (obj == null) {
            j drawSettings = this.c;
            Paint tapPaint = this.f;
            Paint boundingBoxPaint = this.g;
            Intrinsics.checkNotNullParameter(this, "container");
            Intrinsics.checkNotNullParameter(initialDrawData, "initialDrawData");
            Intrinsics.checkNotNullParameter(drawSettings, "drawSettings");
            Intrinsics.checkNotNullParameter(tapPaint, "tapPaint");
            Intrinsics.checkNotNullParameter(boundingBoxPaint, "boundingBoxPaint");
            obj = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.c(this, initialDrawData, drawSettings, tapPaint, boundingBoxPaint);
        }
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.c cVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.c) obj;
        addView(cVar.a, new ViewGroup.LayoutParams(-1, -1));
        cVar.a(initialDrawData);
        this.i.put(Integer.valueOf(track.getIdentifier()), obj);
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) this.h.invoke(cVar.a);
        if (eVar != null) {
            l block = new l(this);
            Intrinsics.checkNotNullParameter(block, "block");
            eVar.j = block;
            BarcodePickState pickState = initialDrawData.e;
            Intrinsics.checkNotNullParameter(track, "track");
            Intrinsics.checkNotNullParameter(pickState, "pickState");
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.viewholder.h hVar = eVar.h;
            BarcodePickState barcodePickState = hVar != null ? hVar.l : null;
            com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.i a2 = eVar.c.a(track.getData(), pickState, barcodePickState);
            if (a2 != null && (a = a2.a()) != null) {
                eVar.a(track, a, eVar.e.a(track, pickState, barcodePickState));
            }
            eVar.i = new m(this);
            this.k.put(Integer.valueOf(track.getIdentifier()), eVar);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(int i) {
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e eVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.statusicon.e) this.k.remove(Integer.valueOf(i));
        if (eVar != null) {
            eVar.a();
        }
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.b bVar = (com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.b) this.i.remove(Integer.valueOf(i));
        if (bVar != null) {
            removeView(((com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.viewholder.c) bVar).a);
            this.j.add(bVar);
        }
        this.e.remove(Integer.valueOf(i));
    }
}
