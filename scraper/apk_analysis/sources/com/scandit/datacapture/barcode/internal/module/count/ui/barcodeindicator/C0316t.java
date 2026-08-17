package com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.scandit.datacapture.barcode.R;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.batch.internal.module.data.NativeTrackedBarcode;
import com.scandit.datacapture.barcode.count.capture.list.BarcodeCountCaptureListSession;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayColorScheme;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountBasicOverlayStyle;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountNotInListStatus;
import com.scandit.datacapture.barcode.count.internal.module.capture.NativeBarcodeCountStatusItem;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountNotInListActionSettings;
import com.scandit.datacapture.barcode.count.ui.view.BarcodeCountStatus;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewLayerManager;
import com.scandit.datacapture.barcode.internal.module.count.ui.BarcodeCountViewSettings;
import com.scandit.datacapture.barcode.internal.module.count.ui.handlers.BarcodeCountBrushHandler;
import com.scandit.datacapture.core.common.geometry.Point;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelper;
import com.scandit.datacapture.core.internal.sdk.common.async.MainThreadHelperImpl;
import com.scandit.datacapture.core.internal.sdk.common.geometry.QuadrilateralUtilsKt;
import com.scandit.datacapture.core.ui.style.Brush;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* renamed from: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.t, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0316t implements BarcodeIndicatorPresenter {
    public final BarcodeCountViewLayerManager a;
    public final S b;
    public final InterfaceC0318v c;
    public final NativeBarcodeCountBasicOverlayStyle d;
    public final BarcodeCountBrushHandler e;
    public final Function4 f;
    public final MainThreadHelper g;
    public final com.scandit.datacapture.barcode.internal.module.count.ui.cluster.s h;
    public long i;
    public boolean j;
    public List k;
    public List l;
    public List m;
    public boolean n;
    public NativeBarcodeCountBasicOverlayColorScheme o;
    public final Lazy p;
    public final G q;
    public Map r;
    public Map s;
    public Map t;
    public Map u;
    public List v;
    public boolean w;
    public com.scandit.datacapture.barcode.internal.module.count.ui.status.n x;
    public final com.scandit.datacapture.barcode.internal.module.ui.k y;
    public final C0304g z;

    public C0316t(com.scandit.datacapture.barcode.internal.module.count.ui.g layerManager, U trackedBarcodeHelper, C0321y popupPresenter, NativeBarcodeCountBasicOverlayStyle style, com.scandit.datacapture.barcode.internal.module.count.ui.handlers.r brushHandler, com.scandit.datacapture.barcode.internal.module.count.ui.e onBarcodeViewClickedListener, com.scandit.datacapture.barcode.internal.module.count.ui.cluster.B clusterPresenter) {
        MainThreadHelperImpl mainThreadHelper = new MainThreadHelperImpl();
        Intrinsics.checkNotNullParameter(layerManager, "layerManager");
        Intrinsics.checkNotNullParameter(trackedBarcodeHelper, "trackedBarcodeHelper");
        Intrinsics.checkNotNullParameter(popupPresenter, "popupPresenter");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(brushHandler, "brushHandler");
        Intrinsics.checkNotNullParameter(onBarcodeViewClickedListener, "onBarcodeViewClickedListener");
        Intrinsics.checkNotNullParameter(mainThreadHelper, "mainThreadHelper");
        Intrinsics.checkNotNullParameter(clusterPresenter, "clusterPresenter");
        this.a = layerManager;
        this.b = trackedBarcodeHelper;
        this.c = popupPresenter;
        this.d = style;
        this.e = brushHandler;
        this.f = onBarcodeViewClickedListener;
        this.g = mainThreadHelper;
        this.h = clusterPresenter;
        this.k = CollectionsKt.emptyList();
        this.l = CollectionsKt.emptyList();
        this.m = CollectionsKt.emptyList();
        this.o = NativeBarcodeCountBasicOverlayColorScheme.DEFAULT;
        this.p = LazyKt.lazy(new C0305h(this));
        this.q = new G(layerManager.j);
        clusterPresenter.b();
        clusterPresenter.a(style, this.o);
        this.r = new LinkedHashMap();
        this.s = new LinkedHashMap();
        this.t = MapsKt.emptyMap();
        this.u = MapsKt.emptyMap();
        this.v = CollectionsKt.emptyList();
        this.x = com.scandit.datacapture.barcode.internal.module.count.ui.status.n.a;
        this.y = new com.scandit.datacapture.barcode.internal.module.ui.k(new C0312o(this), new C0313p(this), new C0314q(this));
        this.z = new C0304g(this);
    }

    public final TrackedBarcode a(com.scandit.datacapture.barcode.internal.module.count.capture.n cluster) {
        Object obj;
        Intrinsics.checkNotNullParameter(cluster, "cluster");
        Iterator it = cluster.b.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                int i = a((TrackedBarcode) next, N.a).a;
                do {
                    Object next2 = it.next();
                    int i2 = a((TrackedBarcode) next2, N.a).a;
                    if (i < i2) {
                        next = next2;
                        i = i2;
                    }
                } while (it.hasNext());
            }
            obj = next;
        } else {
            obj = null;
        }
        return (TrackedBarcode) obj;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final boolean b() {
        return this.w;
    }

    public final void c() {
        Iterator it = this.r.entrySet().iterator();
        while (it.hasNext()) {
            L l = (L) ((Map.Entry) it.next()).getValue();
            ViewParent parent = l.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(l);
            }
        }
        this.r.clear();
        this.s.clear();
        this.g.runOnMainThread(new C0308k(this));
    }

    public final N d(TrackedBarcode trackedBarcode) {
        List list = this.v;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((TrackedBarcode) it.next()).getGlobalId$scandit_barcode_capture() == trackedBarcode.getGlobalId$scandit_barcode_capture()) {
                    return N.c;
                }
            }
        }
        Boolean bool = (Boolean) this.u.get(Integer.valueOf(trackedBarcode.getGlobalId$scandit_barcode_capture()));
        return bool != null ? bool.booleanValue() : false ? N.a : N.b;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void reset() {
        O.a();
        this.t = MapsKt.emptyMap();
        this.v = CollectionsKt.emptyList();
        c();
        com.scandit.datacapture.barcode.internal.module.count.ui.barcodeoverlay.a aVar = (com.scandit.datacapture.barcode.internal.module.count.ui.barcodeoverlay.a) this.p.getValue();
        if (aVar != null) {
            aVar.b = CollectionsKt.emptyList();
        }
        this.g.runOnMainThread(new C0309l(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void b(boolean z, boolean z2) {
        for (L l : this.r.values()) {
            if (l.g == W.f) {
                if (z) {
                    L.b(l, z2);
                } else {
                    L.a(l, z2);
                }
            }
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(boolean z) {
        this.j = z;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(NativeBarcodeCountBasicOverlayColorScheme value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.o = value;
        ((com.scandit.datacapture.barcode.internal.module.count.ui.cluster.B) this.h).a(this.d, value);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(BarcodeCountCaptureListSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        List<TrackedBarcode> correctBarcodes = session.getCorrectBarcodes();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(correctBarcodes, 10));
        Iterator<T> it = correctBarcodes.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((TrackedBarcode) it.next()).getGlobalId$scandit_barcode_capture()));
        }
        this.k = arrayList;
        List<TrackedBarcode> acceptedBarcodes = session.getAcceptedBarcodes();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(acceptedBarcodes, 10));
        Iterator<T> it2 = acceptedBarcodes.iterator();
        while (it2.hasNext()) {
            arrayList2.add(Integer.valueOf(((TrackedBarcode) it2.next()).getGlobalId$scandit_barcode_capture()));
        }
        this.l = arrayList2;
        List<TrackedBarcode> rejectedBarcodes = session.getRejectedBarcodes();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(rejectedBarcodes, 10));
        Iterator<T> it3 = rejectedBarcodes.iterator();
        while (it3.hasNext()) {
            arrayList3.add(Integer.valueOf(((TrackedBarcode) it3.next()).getGlobalId$scandit_barcode_capture()));
        }
        this.m = arrayList3;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void b(boolean z) {
        this.g.runOnMainThread(new C0311n(this, z));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void b(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        c(barcode);
    }

    public final void c(TrackedBarcode barcode) {
        L l;
        int i;
        Integer valueOf;
        L l2 = (L) this.r.get(Integer.valueOf(barcode.getGlobalId$scandit_barcode_capture()));
        if (l2 != null) {
            NativeBarcodeCountBasicOverlayStyle style = this.d;
            W status = a(barcode, d(barcode));
            NativeBarcodeCountBasicOverlayColorScheme colorScheme = this.o;
            C0307j dotBrushProvider = new C0307j(this.e);
            Intrinsics.checkNotNullParameter(style, "style");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
            Intrinsics.checkNotNullParameter(barcode, "barcode");
            Intrinsics.checkNotNullParameter(dotBrushProvider, "dotBrushProvider");
            l2.g = status;
            int i2 = AbstractC0301d.a;
            C0317u normalView = l2.b;
            Intrinsics.checkNotNullParameter(normalView, "normalView");
            Intrinsics.checkNotNullParameter(style, "style");
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
            Intrinsics.checkNotNullParameter(barcode, "barcode");
            Intrinsics.checkNotNullParameter(dotBrushProvider, "dotBrushProvider");
            boolean a = AbstractC0301d.a(barcode, style, status, dotBrushProvider);
            EnumC0298a a2 = AbstractC0301d.a(style, status);
            C0300c c0300c = new C0300c(dotBrushProvider, barcode);
            int ordinal = a2.ordinal();
            Y y = null;
            if (ordinal == 0) {
                Integer valueOf2 = Integer.valueOf(R.drawable.sc_ic_barcode_dot);
                Brush brush = (Brush) c0300c.invoke(status);
                normalView.a(valueOf2, brush != null ? Integer.valueOf(brush.getFillColor()) : null, colorScheme);
            } else if (ordinal == 1) {
                Integer a3 = AbstractC0301d.a(status, colorScheme);
                int ordinal2 = status.ordinal();
                if (ordinal2 != 1) {
                    valueOf = ordinal2 != 2 ? null : Integer.valueOf(AbstractC0301d.b);
                } else {
                    valueOf = Integer.valueOf(AbstractC0301d.a);
                }
                if (valueOf == null) {
                    normalView.a(a3, colorScheme);
                } else {
                    normalView.a(a3, valueOf, colorScheme);
                }
            } else if (ordinal == 2) {
                Brush brush2 = (Brush) c0300c.invoke(status);
                normalView.a(AbstractC0301d.a(status, colorScheme), brush2 != null ? Integer.valueOf(brush2.getFillColor()) : null, colorScheme);
            }
            boolean z = status == W.f && a;
            Y y2 = normalView.b;
            if (y2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("warningBackground");
            } else {
                y = y2;
            }
            y.setVisibility(!z ? 8 : 0);
            int i3 = R.string.sc_barcode_count_indicator_content_description_barcode_data;
            String data = barcode.getBarcode().getData();
            int ordinal3 = status.ordinal();
            if (ordinal3 == 0) {
                i = R.string.sc_barcode_count_scan_status_scanned;
            } else if (ordinal3 == 1) {
                i = R.string.sc_barcode_count_not_in_list_status_accepted;
            } else if (ordinal3 == 2) {
                i = R.string.sc_barcode_count_not_in_list_status_rejected;
            } else if (ordinal3 == 3) {
                i = R.string.sc_barcode_count_list_status_not_in_list;
            } else if (ordinal3 == 4) {
                i = R.string.sc_barcode_count_scan_status_unscanned;
            } else {
                if (ordinal3 != 5) {
                    throw new NoWhenBranchMatchedException();
                }
                i = R.string.sc_barcode_count_scan_status_filtered;
            }
            normalView.a(i3, data, i);
        }
        if (this.x != com.scandit.datacapture.barcode.internal.module.count.ui.status.n.b || (l = (L) this.r.get(Integer.valueOf(barcode.getGlobalId$scandit_barcode_capture()))) == null) {
            return;
        }
        l.b(false);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(FrameLayout captureView) {
        Intrinsics.checkNotNullParameter(captureView, "captureView");
        if (captureView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("touchCaptureView");
            captureView = null;
        }
        captureView.setOnTouchListener(new View.OnTouchListener() { // from class: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.t$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return C0316t.a(C0316t.this, view, motionEvent);
            }
        });
    }

    public static final boolean a(C0316t this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        com.scandit.datacapture.barcode.internal.module.ui.k kVar = this$0.y;
        Intrinsics.checkNotNull(motionEvent);
        boolean a = kVar.a(motionEvent);
        if (!a) {
            view.performClick();
        }
        return a;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(TrackedBarcode trackedBarcode, NativeBarcodeCountNotInListStatus status) {
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        Intrinsics.checkNotNullParameter(status, "status");
        C0321y c0321y = (C0321y) this.c;
        c0321y.getClass();
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        Intrinsics.checkNotNullParameter(status, "status");
        c0321y.d = status;
        c0321y.e = trackedBarcode;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(TrackedBarcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        InterfaceC0318v interfaceC0318v = this.c;
        int globalId$scandit_barcode_capture = barcode.getGlobalId$scandit_barcode_capture();
        C0321y c0321y = (C0321y) interfaceC0318v;
        TrackedBarcode trackedBarcode = c0321y.e;
        if (trackedBarcode == null || trackedBarcode.getGlobalId$scandit_barcode_capture() != globalId$scandit_barcode_capture) {
            return;
        }
        c0321y.e = null;
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(BarcodeCountViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        if (viewSettings.w()) {
            com.scandit.datacapture.barcode.internal.module.count.ui.status.n nVar = com.scandit.datacapture.barcode.internal.module.count.ui.status.n.b;
            this.x = nVar;
            G g = this.q;
            g.getClass();
            Intrinsics.checkNotNullParameter(nVar, "<set-?>");
            g.g = nVar;
        } else {
            com.scandit.datacapture.barcode.internal.module.count.ui.status.n nVar2 = com.scandit.datacapture.barcode.internal.module.count.ui.status.n.a;
            this.x = nVar2;
            G g2 = this.q;
            g2.getClass();
            Intrinsics.checkNotNullParameter(nVar2, "<set-?>");
            g2.g = nVar2;
        }
        InterfaceC0318v interfaceC0318v = this.c;
        BarcodeCountNotInListActionSettings settings = viewSettings.b();
        NativeBarcodeCountBasicOverlayColorScheme colorScheme = this.o;
        C0321y c0321y = (C0321y) interfaceC0318v;
        c0321y.getClass();
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
        com.scandit.datacapture.barcode.internal.module.count.ui.popover.h hVar = c0321y.f;
        hVar.getClass();
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(colorScheme, "colorScheme");
        Lazy lazy = com.scandit.datacapture.barcode.internal.module.count.ui.popover.h.f;
        hVar.a(com.scandit.datacapture.barcode.internal.module.count.ui.popover.f.a(hVar.a, settings, colorScheme, hVar.b));
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x0259, code lost:
    
        if (r7 == null) goto L57;
     */
    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(long r38, java.util.ArrayList r40, java.util.ArrayList r41, java.util.ArrayList r42, java.util.ArrayList r43) {
        /*
            Method dump skipped, instructions count: 1050
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.C0316t.a(long, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList):void");
    }

    public static final void a(C0316t c0316t, List list, ArrayList arrayList) {
        Map map = c0316t.s;
        c0316t.s = c0316t.r;
        c0316t.r = map;
        map.clear();
        Iterator it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            V trackedBarcodeInfo = (V) it.next();
            TrackedBarcode trackedBarcode = (TrackedBarcode) c0316t.t.get(Integer.valueOf(trackedBarcodeInfo.a));
            if (trackedBarcode != null) {
                L indicatorView = (L) c0316t.s.remove(Integer.valueOf(trackedBarcodeInfo.a));
                if (indicatorView == null) {
                    FrameLayout g = c0316t.a.g();
                    S.a.getClass();
                    int floatValue = (int) ((Number) Q.b.getValue()).floatValue();
                    L l = new L(c0316t.a.e(), c0316t.x);
                    l.setLayoutParams(new FrameLayout.LayoutParams(floatValue, floatValue));
                    g.addView(l);
                    z = true;
                    indicatorView = l;
                }
                I i = I.a;
                Intrinsics.checkNotNullParameter(i, "<set-?>");
                indicatorView.e = i;
                c0316t.r.put(Integer.valueOf(trackedBarcodeInfo.a), indicatorView);
                S.a.getClass();
                float floatValue2 = ((Number) Q.b.getValue()).floatValue() / 2;
                indicatorView.setX(trackedBarcodeInfo.c.getX() - floatValue2);
                indicatorView.setY(trackedBarcodeInfo.c.getY() - floatValue2);
                indicatorView.setScaleX(trackedBarcodeInfo.d);
                indicatorView.setScaleY(trackedBarcodeInfo.d);
                c0316t.c(trackedBarcode);
                C0321y c0321y = (C0321y) c0316t.c;
                c0321y.getClass();
                Intrinsics.checkNotNullParameter(indicatorView, "indicatorView");
                Intrinsics.checkNotNullParameter(trackedBarcodeInfo, "trackedBarcodeInfo");
                TrackedBarcode trackedBarcode2 = c0321y.e;
                if (trackedBarcode2 != null && trackedBarcode2.getGlobalId$scandit_barcode_capture() == trackedBarcodeInfo.a) {
                    ViewParent parent = indicatorView.getParent();
                    Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                    if (!Intrinsics.areEqual((ViewGroup) parent, c0321y.b.b())) {
                        ViewParent parent2 = indicatorView.getParent();
                        Intrinsics.checkNotNull(parent2, "null cannot be cast to non-null type android.view.ViewGroup");
                        ((ViewGroup) parent2).removeView(indicatorView);
                        c0321y.b.b().addView(indicatorView);
                    }
                }
                if (c0321y.e == null) {
                    ViewParent parent3 = indicatorView.getParent();
                    Intrinsics.checkNotNull(parent3, "null cannot be cast to non-null type android.view.ViewGroup");
                    if (Intrinsics.areEqual((ViewGroup) parent3, c0321y.b.b())) {
                        ViewParent parent4 = indicatorView.getParent();
                        Intrinsics.checkNotNull(parent4, "null cannot be cast to non-null type android.view.ViewGroup");
                        ((ViewGroup) parent4).removeView(indicatorView);
                        c0321y.b.g().addView(indicatorView);
                    }
                }
            }
        }
        if (z && c0316t.n) {
            c0316t.n = false;
            if (O.e()) {
                c0316t.g.runOnMainThread(new C0315s(c0316t));
            }
        }
        if (c0316t.x == com.scandit.datacapture.barcode.internal.module.count.ui.status.n.a) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                z zVar = (z) it2.next();
                int i2 = zVar.b;
                L l2 = (L) c0316t.s.remove(Integer.valueOf(i2));
                if (l2 == null) {
                    FrameLayout g2 = c0316t.a.g();
                    S.a.getClass();
                    int floatValue3 = (int) ((Number) Q.b.getValue()).floatValue();
                    L l3 = new L(c0316t.a.e(), c0316t.x);
                    l3.setLayoutParams(new FrameLayout.LayoutParams(floatValue3, floatValue3));
                    g2.addView(l3);
                    l2 = l3;
                }
                I i3 = I.b;
                Intrinsics.checkNotNullParameter(i3, "<set-?>");
                l2.e = i3;
                c0316t.r.put(Integer.valueOf(i2), l2);
                S.a.getClass();
                float floatValue4 = ((Number) Q.b.getValue()).floatValue() / 2;
                Point center = QuadrilateralUtilsKt.getCenter(zVar.d);
                l2.setX(center.getX() - floatValue4);
                l2.setY(center.getY() - floatValue4);
                l2.setScaleX(zVar.f);
                l2.setScaleY(zVar.f);
                l2.a(zVar);
            }
        }
        for (Map.Entry entry : c0316t.s.entrySet()) {
            int intValue = ((Number) entry.getKey()).intValue();
            L l4 = (L) entry.getValue();
            ViewParent parent5 = l4.getParent();
            Intrinsics.checkNotNull(parent5, "null cannot be cast to non-null type android.view.ViewGroup");
            ((ViewGroup) parent5).removeView(l4);
            C0321y c0321y2 = (C0321y) c0316t.c;
            TrackedBarcode trackedBarcode3 = c0321y2.e;
            if (trackedBarcode3 != null && trackedBarcode3.getGlobalId$scandit_barcode_capture() == intValue) {
                c0321y2.e = null;
            }
        }
        c0316t.s.clear();
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(boolean z, boolean z2) {
        this.w = z;
        for (L l : this.r.values()) {
            if (l.g != W.f) {
                if (z) {
                    l.b(z2);
                } else {
                    L.b(l.b, z2);
                    l.c.a(z2);
                }
            }
        }
        com.scandit.datacapture.barcode.internal.module.count.ui.cluster.B b = (com.scandit.datacapture.barcode.internal.module.count.ui.cluster.B) this.h;
        b.j = this.x == com.scandit.datacapture.barcode.internal.module.count.ui.status.n.b || !z;
        b.a();
        this.g.runOnMainThread(new C0310m(this, z, z2));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a() {
        a(CollectionsKt.emptyList());
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(List statusList) {
        Intrinsics.checkNotNullParameter(statusList, "statusList");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List mutableList = CollectionsKt.toMutableList((Collection) this.t.keySet());
        Iterator it = statusList.iterator();
        while (it.hasNext()) {
            NativeBarcodeCountStatusItem nativeBarcodeCountStatusItem = (NativeBarcodeCountStatusItem) it.next();
            NativeTrackedBarcode trackedBarcode = nativeBarcodeCountStatusItem.getTrackedBarcode();
            Intrinsics.checkNotNullExpressionValue(trackedBarcode, "getTrackedBarcode(...)");
            int globalId$scandit_barcode_capture = new TrackedBarcode(trackedBarcode).getBarcode().getGlobalId$scandit_barcode_capture();
            Integer valueOf = Integer.valueOf(globalId$scandit_barcode_capture);
            BarcodeCountStatus status = nativeBarcodeCountStatusItem.getStatus();
            Intrinsics.checkNotNullExpressionValue(status, "getStatus(...)");
            linkedHashMap.put(valueOf, new com.scandit.datacapture.barcode.internal.module.count.ui.status.e(status, nativeBarcodeCountStatusItem.getTrackedBarcode().getClusterIdentifier()));
            mutableList.remove(Integer.valueOf(globalId$scandit_barcode_capture));
        }
        Iterator it2 = mutableList.iterator();
        while (it2.hasNext()) {
            linkedHashMap.put(Integer.valueOf(((Number) it2.next()).intValue()), com.scandit.datacapture.barcode.internal.module.count.ui.status.c.a);
        }
        O.a(linkedHashMap);
        this.g.runOnMainThread(new C0315s(this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.count.ui.barcodeindicator.BarcodeIndicatorPresenter
    public final void a(int i) {
        O.a(i);
        this.n = true;
    }

    public final W a(TrackedBarcode trackedBarcode, N scanStatus) {
        M m;
        NativeBarcodeCountNotInListStatus nativeBarcodeCountNotInListStatus;
        Intrinsics.checkNotNullParameter(trackedBarcode, "trackedBarcode");
        Intrinsics.checkNotNullParameter(scanStatus, "scanStatus");
        int ordinal = scanStatus.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return W.f;
            }
            if (ordinal == 2) {
                return W.g;
            }
            throw new NoWhenBranchMatchedException();
        }
        int globalId$scandit_barcode_capture = trackedBarcode.getGlobalId$scandit_barcode_capture();
        if (!this.j) {
            m = M.a;
        } else if (this.k.contains(Integer.valueOf(globalId$scandit_barcode_capture))) {
            m = M.b;
        } else {
            m = M.c;
        }
        int ordinal2 = m.ordinal();
        if (ordinal2 == 0 || ordinal2 == 1) {
            return W.b;
        }
        if (ordinal2 == 2) {
            int globalId$scandit_barcode_capture2 = trackedBarcode.getGlobalId$scandit_barcode_capture();
            if (this.j && !this.k.contains(Integer.valueOf(globalId$scandit_barcode_capture2))) {
                if (this.l.contains(Integer.valueOf(globalId$scandit_barcode_capture2))) {
                    nativeBarcodeCountNotInListStatus = NativeBarcodeCountNotInListStatus.ACCEPTED;
                } else if (this.m.contains(Integer.valueOf(globalId$scandit_barcode_capture2))) {
                    nativeBarcodeCountNotInListStatus = NativeBarcodeCountNotInListStatus.REJECTED;
                } else {
                    nativeBarcodeCountNotInListStatus = NativeBarcodeCountNotInListStatus.NONE;
                }
            } else {
                nativeBarcodeCountNotInListStatus = NativeBarcodeCountNotInListStatus.NONE;
            }
            int i = AbstractC0303f.b[nativeBarcodeCountNotInListStatus.ordinal()];
            if (i == 1) {
                return W.e;
            }
            if (i == 2) {
                return W.c;
            }
            if (i == 3) {
                return W.d;
            }
            throw new NoWhenBranchMatchedException();
        }
        throw new NoWhenBranchMatchedException();
    }
}
