package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered;

import android.graphics.Canvas;
import android.view.View;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.filter.ui.overlay.BarcodeFilterHighlightSettings;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.m;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.BarcodePickBrush;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.k;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.p;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.ui.style.Brush;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class d extends View implements BarcodePickDrawer {
    public final b a;
    public final Map b;
    public boolean c;
    public List d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(BarcodePickBasicOverlay container, b drawDataFactory, Map drawingData) {
        super(container.getContext());
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(drawDataFactory, "drawDataFactory");
        Intrinsics.checkNotNullParameter(drawingData, "drawingData");
        this.a = drawDataFactory;
        this.b = drawingData;
        container.addView(this, new FrameLayout.LayoutParams(-1, -1));
        setClickable(false);
        this.d = CollectionsKt.emptyList();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(int i) {
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final boolean a() {
        return false;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void b(TrackedObject track, BarcodePickState pickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (this.c) {
            for (a aVar : this.b.values()) {
                m mVar = aVar.a;
                if (mVar != null) {
                    canvas.drawPath(aVar.b.a, mVar.a);
                    canvas.drawPath(aVar.b.a, mVar.b);
                }
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void start() {
        this.c = true;
        invalidate();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void stop() {
        this.c = false;
        this.b.clear();
        invalidate();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(TrackedObject track, BarcodePickState pickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void b() {
        this.c = true;
        invalidate();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(BarcodePickViewHighlightStyle highlightStyle) {
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(List tracks) {
        BarcodePickBrush pVar;
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        if (this.c) {
            Iterator it = tracks.iterator();
            while (it.hasNext()) {
                TrackedObject track = (TrackedObject) it.next();
                Map map = this.b;
                Integer valueOf = Integer.valueOf(track.getIdentifier());
                c cVar = (c) this.a;
                cVar.getClass();
                Intrinsics.checkNotNullParameter(track, "track");
                BarcodeFilterHighlightSettings barcodeFilterHighlightSettings = cVar.b.b;
                Brush brush = barcodeFilterHighlightSettings != null ? barcodeFilterHighlightSettings.getBrush() : null;
                if (brush == null) {
                    pVar = k.b;
                } else {
                    Intrinsics.checkNotNullParameter(brush, "brush");
                    pVar = new p(brush);
                }
                Quadrilateral a = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.k.a((Quadrilateral) cVar.a.invoke(track.getLocationIgnoringLicense$scandit_barcode_capture()), cVar.b.a);
                Intrinsics.checkNotNullParameter(pVar, "<this>");
                map.put(valueOf, new a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.a.a(pVar, CollectionsKt.emptyList()), new h(a)));
            }
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(tracks, 10));
            Iterator it2 = tracks.iterator();
            while (it2.hasNext()) {
                arrayList.add(Integer.valueOf(((TrackedObject) it2.next()).getIdentifier()));
            }
            List list = this.d;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : list) {
                if (!arrayList.contains(Integer.valueOf(((Number) obj).intValue()))) {
                    arrayList2.add(obj);
                }
            }
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                this.b.remove(Integer.valueOf(((Number) it3.next()).intValue()));
            }
            this.d = arrayList;
            invalidate();
        }
    }
}
