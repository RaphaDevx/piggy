package com.scandit.datacapture.barcode.internal.module.pick.ui.pickviewfactories;

import android.content.Context;
import android.view.ViewGroup;
import com.scandit.datacapture.barcode.internal.module.pick.capture.A;
import com.scandit.datacapture.barcode.internal.module.pick.ui.BarcodePickBasicOverlay;
import com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.BarcodePickDrawer;
import com.scandit.datacapture.barcode.internal.sdk.pick.ui.BarcodePickViewSettingsDefaults;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickView;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyle;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import com.scandit.datacapture.core.capture.DataCaptureContext;
import com.scandit.datacapture.core.common.geometry.Size2;
import com.scandit.datacapture.core.internal.sdk.utils.PixelExtensionsKt;
import com.scandit.datacapture.core.source.CameraPosition;
import com.scandit.datacapture.core.source.CameraSettings;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public abstract class e {
    public static BarcodePickView a(ViewGroup viewGroup, DataCaptureContext dataCaptureContext, BarcodePick barcodePick, BarcodePickViewSettings viewSettings, CameraSettings cameraSettings, BarcodePickViewHighlightStyle barcodePickViewHighlightStyle, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.brushprovider.j brushProvider) {
        Context context = viewGroup.getContext();
        Intrinsics.checkNotNull(context);
        BarcodePickBasicOverlay barcodePickBasicOverlay = new BarcodePickBasicOverlay(context, barcodePick, viewSettings);
        com.scandit.datacapture.barcode.internal.module.pick.ui.datacaptureview.j a = a.a(context, viewSettings);
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.e iconProvider = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.iconprovider.e.b;
        Intrinsics.checkNotNullParameter(brushProvider, "brushProvider");
        Intrinsics.checkNotNullParameter(iconProvider, "iconProvider");
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c cVar = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightstyle.elementsprovider.c(null, brushProvider, iconProvider, false);
        CameraPosition cameraPosition = CameraPosition.WORLD_FACING;
        Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
        Intrinsics.checkNotNullParameter(cameraSettings, "cameraSettings");
        A a2 = new A(cameraPosition, cameraSettings);
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a aVar = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.a.a;
        b bVar = new b(barcodePick);
        c cVar2 = new c(a);
        Intrinsics.checkNotNullParameter(viewSettings, "settings");
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.n a3 = com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.s.a(barcodePickBasicOverlay, bVar, barcodePickViewHighlightStyle, cVar2, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.brush.j(viewSettings.getDrawDebugInfo(), new Size2(BarcodePickViewSettingsDefaults.getHighlightSizeToTapAreaFactor() * viewSettings.getMinimumHighlightWidthPx$scandit_barcode_capture(), BarcodePickViewSettingsDefaults.getHighlightSizeToTapAreaFactor() * viewSettings.getMinimumHighlightHeightPx$scandit_barcode_capture()), PixelExtensionsKt.pxFromDp$default(viewSettings.maxIconSize$scandit_barcode_capture(), (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(viewSettings.minIconSize$scandit_barcode_capture(), (Context) null, 1, (Object) null), PixelExtensionsKt.pxFromDp$default(BarcodePickViewSettingsDefaults.getIconPadding(), (Context) null, 1, (Object) null), new Size2(viewSettings.getMinimumHighlightWidthPx$scandit_barcode_capture(), viewSettings.getMinimumHighlightHeightPx$scandit_barcode_capture())), cVar, null, 4032);
        d dVar = new d(a);
        Intrinsics.checkNotNullParameter(viewSettings, "settings");
        BarcodePickDrawer[] drawers = {a3, com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered.e.a(barcodePickBasicOverlay, dVar, new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.filtered.f(new Size2(viewSettings.getMinimumHighlightWidthPx$scandit_barcode_capture(), viewSettings.getMinimumHighlightHeightPx$scandit_barcode_capture()), viewSettings.getFilterHighlightSettings()))};
        Intrinsics.checkNotNullParameter(aVar, "<this>");
        Intrinsics.checkNotNullParameter(drawers, "drawers");
        com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.combined.a aVar2 = new com.scandit.datacapture.barcode.internal.module.pick.ui.highlightdrawer.combined.a(ArraysKt.toList(drawers));
        Context context2 = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        Intrinsics.checkNotNullParameter(context2, "context");
        com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.b factory = new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.b(context2);
        com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c cVar3 = com.scandit.datacapture.barcode.internal.module.ui.viewpositioner.c.a;
        Intrinsics.checkNotNullParameter(cVar3, "<this>");
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton.d positioner = new com.scandit.datacapture.barcode.internal.module.pick.ui.shutterbutton.d(viewSettings.getUiButtonsOffset());
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(positioner, "positioner");
        com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.i iVar = new com.scandit.datacapture.barcode.internal.module.ui.shutterbutton.i(factory, positioner);
        Context context3 = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
        Intrinsics.checkNotNullParameter(context3, "context");
        com.scandit.datacapture.barcode.internal.module.ui.exitbutton.b factory2 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.b(context3);
        Intrinsics.checkNotNullParameter(cVar3, "<this>");
        com.scandit.datacapture.barcode.internal.module.ui.exitbutton.h positioner2 = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.h();
        Intrinsics.checkNotNullParameter(factory2, "factory");
        Intrinsics.checkNotNullParameter(positioner2, "positioner");
        com.scandit.datacapture.barcode.internal.module.ui.exitbutton.e eVar = new com.scandit.datacapture.barcode.internal.module.ui.exitbutton.e(factory2, positioner2);
        Context context4 = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
        com.scandit.datacapture.barcode.internal.module.pick.ui.f loadingViewFactory = a.b(context, viewSettings);
        Context context5 = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
        Intrinsics.checkNotNullParameter(context5, "context");
        com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.k guidanceViewFactory = new com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.k(context5);
        Intrinsics.checkNotNullParameter(context4, "context");
        Intrinsics.checkNotNullParameter(loadingViewFactory, "loadingViewFactory");
        Intrinsics.checkNotNullParameter(guidanceViewFactory, "guidanceViewFactory");
        return new BarcodePickView(context, viewGroup, dataCaptureContext, barcodePick, viewSettings, a2, cVar, aVar2, a, iVar, eVar, new com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.g(context4, loadingViewFactory, guidanceViewFactory), barcodePickBasicOverlay);
    }
}
