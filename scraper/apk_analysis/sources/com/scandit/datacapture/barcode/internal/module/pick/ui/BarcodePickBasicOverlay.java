package com.scandit.datacapture.barcode.internal.module.pick.ui;

import android.content.Context;
import com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.BarcodePickGuidanceHandler;
import com.scandit.datacapture.barcode.pick.capture.BarcodePick;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewSettings;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay;
import com.scandit.datacapture.core.ui.DataCaptureView;
import com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes2.dex */
public final class BarcodePickBasicOverlay extends ViewBasedDataCaptureOverlay implements DataCaptureOverlay {
    private final /* synthetic */ a a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BarcodePickBasicOverlay(Context context, BarcodePick mode, BarcodePickViewSettings viewSettings) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        NativeBarcodePickBasicOverlay create = NativeBarcodePickBasicOverlay.create(mode._impl$scandit_barcode_capture(), viewSettings.getA());
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        this.a = new a(create);
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay
    public final void _cleanupViews() {
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    /* renamed from: _dataCaptureOverlayImpl */
    public final NativeDataCaptureOverlay getC() {
        return this.a.c;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay
    public final void _setDataCaptureView(DataCaptureView view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    public final void a(BarcodePickViewSettings viewSettings) {
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        a aVar = this.a;
        aVar.getClass();
        Intrinsics.checkNotNullParameter(viewSettings, "viewSettings");
        NativeBarcodePickViewSettings a = viewSettings.getA();
        aVar.b.put(Reflection.getOrCreateKotlinClass(NativeBarcodePickViewSettings.class), null, a, viewSettings);
        aVar.a.applyViewSettings(a);
    }

    public final void a(BarcodePickGuidanceHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.a.a.setGuidanceHandler(new com.scandit.datacapture.barcode.internal.module.pick.ui.guidances.h(handler));
    }

    public final NativeBarcodePickBasicOverlay a() {
        return this.a.a;
    }
}
