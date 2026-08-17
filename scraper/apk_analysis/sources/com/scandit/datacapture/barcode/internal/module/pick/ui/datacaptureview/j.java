package com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.Anchor;
import com.scandit.datacapture.core.common.geometry.PointWithUnit;
import com.scandit.datacapture.core.common.geometry.Quadrilateral;
import com.scandit.datacapture.core.internal.sdk.extensions.ViewExtensionsKt;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.control.Control;
import com.scandit.datacapture.core.ui.control.LinearControlGroupBuilder;
import com.scandit.datacapture.core.ui.control.LinearControlGroupOrientation;
import com.scandit.datacapture.core.ui.control.TorchSwitchControl;
import com.scandit.datacapture.core.ui.control.ZoomSwitchControl;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class j implements BarcodePickDataCaptureViewWrapper {
    public final g a;
    public final BarcodePickViewSettings b;
    public final Lazy c;
    public final f d;

    public j(Context context, h factory, BarcodePickViewSettings settings) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.a = factory;
        this.b = settings;
        this.c = LazyKt.lazy(new i(this));
        Intrinsics.checkNotNullParameter(context, "context");
        this.d = new f(context);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper
    public final void a(com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.f listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        ((DataCaptureView) this.c.getValue()).addListener(listener);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper
    public final void b(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        ((DataCaptureView) this.c.getValue()).addOverlay(overlay);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper
    public final Quadrilateral mapFrameQuadrilateralToView(Quadrilateral quadrilateral) {
        Intrinsics.checkNotNullParameter(quadrilateral, "quadrilateral");
        return ((DataCaptureView) this.c.getValue()).mapFrameQuadrilateralToView(quadrilateral);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper
    public final void a(ViewGroup container, RelativeLayout.LayoutParams params) {
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(params, "params");
        container.addView((DataCaptureView) this.c.getValue(), params);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper
    public final void b() {
        Control control;
        PointWithUnit pointWithUnit;
        LinearControlGroupOrientation linearControlGroupOrientation;
        f fVar = this.d;
        BarcodePickViewSettings settings = this.b;
        Context context = ((DataCaptureView) this.c.getValue()).getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        boolean a = com.scandit.datacapture.barcode.internal.module.extensions.b.a(context);
        fVar.getClass();
        Intrinsics.checkNotNullParameter(settings, "settings");
        TorchSwitchControl torchSwitchControl = (TorchSwitchControl) fVar.c.getValue();
        boolean showTorchButton = settings.getShowTorchButton();
        Anchor torchButtonPosition = settings.getTorchButtonPosition();
        if (!f.j.contains(torchButtonPosition)) {
            torchButtonPosition = null;
        }
        if (torchButtonPosition == null) {
            torchButtonPosition = f.m;
        }
        if (a && (torchButtonPosition = (Anchor) f.k.get(torchButtonPosition)) == null) {
            torchButtonPosition = f.n;
        }
        a aVar = new a(torchSwitchControl, showTorchButton, torchButtonPosition);
        ZoomSwitchControl zoomSwitchControl = (ZoomSwitchControl) fVar.b.getValue();
        boolean showZoomButton = settings.getShowZoomButton();
        Anchor zoomButtonPosition = settings.getZoomButtonPosition();
        Anchor anchor = f.i.contains(zoomButtonPosition) ? zoomButtonPosition : null;
        if (anchor == null) {
            anchor = f.l;
        }
        if (a && (anchor = (Anchor) f.k.get(anchor)) == null) {
            anchor = f.n;
        }
        List listOf = CollectionsKt.listOf((Object[]) new a[]{aVar, new a(zoomSwitchControl, showZoomButton, anchor)});
        ViewExtensionsKt.removeFromSuperview(((ZoomSwitchControl) fVar.b.getValue()).get_view());
        ViewExtensionsKt.removeFromSuperview(((TorchSwitchControl) fVar.c.getValue()).get_view());
        ArrayList arrayList = new ArrayList();
        for (Object obj : listOf) {
            if (((a) obj).b) {
                arrayList.add(obj);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            Anchor anchor2 = ((a) next).c;
            Object obj2 = linkedHashMap.get(anchor2);
            if (obj2 == null) {
                obj2 = new ArrayList();
                linkedHashMap.put(anchor2, obj2);
            }
            ((List) obj2).add(next);
        }
        ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            Anchor anchor3 = (Anchor) entry.getKey();
            List list = (List) entry.getValue();
            if (list.size() > 1) {
                LinearControlGroupBuilder linearControlGroupBuilder = (LinearControlGroupBuilder) fVar.d.getValue();
                if (a) {
                    linearControlGroupOrientation = LinearControlGroupOrientation.HORIZONTAL;
                } else {
                    linearControlGroupOrientation = LinearControlGroupOrientation.VERTICAL;
                }
                LinearControlGroupBuilder orientation = linearControlGroupBuilder.setOrientation(linearControlGroupOrientation);
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(((a) it2.next()).a);
                }
                control = orientation.setControls(arrayList3).build();
            } else {
                control = ((a) CollectionsKt.first(list)).a;
            }
            if (anchor3 == Anchor.TOP_CENTER) {
                pointWithUnit = f.f;
            } else if (anchor3 == Anchor.CENTER_LEFT) {
                pointWithUnit = f.g;
            } else {
                int i = b.a[anchor3.ordinal()];
                if (i != 1 && i != 2) {
                    pointWithUnit = f.e;
                } else {
                    pointWithUnit = f.h;
                }
            }
            arrayList2.add(new k(control, anchor3, pointWithUnit));
        }
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it3 = arrayList2.iterator();
        while (it3.hasNext()) {
            arrayList4.add(((k) it3.next()).a);
        }
        fVar.a = arrayList4;
        Iterator it4 = arrayList2.iterator();
        while (it4.hasNext()) {
            k kVar = (k) it4.next();
            ((DataCaptureView) this.c.getValue()).addControl(kVar.a, kVar.b, kVar.c);
        }
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper
    public final void a(DataCaptureOverlay overlay) {
        Intrinsics.checkNotNullParameter(overlay, "overlay");
        ((DataCaptureView) this.c.getValue()).removeOverlay(overlay);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper
    public final void a(DataCaptureContext dataCaptureContext) {
        ((DataCaptureView) this.c.getValue()).setDataCaptureContext(dataCaptureContext);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper
    public final void a(float f) {
        ViewParent parent = ((DataCaptureView) this.c.getValue()).getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        if (viewGroup == null) {
            return;
        }
        int height = viewGroup.getHeight();
        int width = viewGroup.getWidth();
        if (height > width) {
            height = (int) (width * f);
        } else {
            width = (int) (height * f);
        }
        DataCaptureView dataCaptureView = (DataCaptureView) this.c.getValue();
        ViewGroup.LayoutParams layoutParams = ((DataCaptureView) this.c.getValue()).getLayoutParams();
        layoutParams.height = height;
        layoutParams.width = width;
        dataCaptureView.setLayoutParams(layoutParams);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.BarcodePickDataCaptureViewWrapper
    public final void a() {
        List list = this.d.a;
        DataCaptureView dataCaptureView = (DataCaptureView) this.c.getValue();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            dataCaptureView.removeControl((Control) it.next());
        }
    }
}
