package com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.combined;

import com.scandit.datacapture.barcode.batch.data.TrackedObject;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickState;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class a implements BarcodePickDrawer {
    public final List a;

    public a(List drawers) {
        Intrinsics.checkNotNullParameter(drawers, "drawers");
        this.a = drawers;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(TrackedObject track, BarcodePickState pickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((BarcodePickDrawer) it.next()).a(track, pickState);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void b(TrackedObject track, BarcodePickState pickState) {
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pickState, "pickState");
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((BarcodePickDrawer) it.next()).b(track, pickState);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void start() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((BarcodePickDrawer) it.next()).start();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void stop() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((BarcodePickDrawer) it.next()).stop();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void b() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((BarcodePickDrawer) it.next()).b();
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(int i) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((BarcodePickDrawer) it.next()).a(i);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(List tracks) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((BarcodePickDrawer) it.next()).a(tracks);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final void a(BarcodePickViewHighlightStyle highlightStyle) {
        Intrinsics.checkNotNullParameter(highlightStyle, "highlightStyle");
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((BarcodePickDrawer) it.next()).a(highlightStyle);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer
    public final boolean a() {
        List list = this.a;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((BarcodePickDrawer) it.next()).a()) {
                    return true;
                }
            }
        }
        return false;
    }
}
