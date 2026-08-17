package com.scandit.datacapture.barcode.internal.module.find.ui.overlay;

import android.content.Context;
import android.view.View;
import com.scandit.datacapture.barcode.find.capture.BarcodeFind;
import com.scandit.datacapture.barcode.internal.module.find.ui.NativeBarcodeFindBasicOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.NativeDataCaptureOverlay;
import com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay;
import com.scandit.datacapture.core.ui.DataCaptureView;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes2.dex */
public final class b extends ViewBasedDataCaptureOverlay implements c, BarcodeFindBasicOverlay {
    public final /* synthetic */ d a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(Context context, BarcodeFind barcodeFind) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcodeFind, "barcodeFind");
        NativeBarcodeFindBasicOverlay create = NativeBarcodeFindBasicOverlay.create(barcodeFind.getA());
        Intrinsics.checkNotNullExpressionValue(create, "create(...)");
        this.a = new d(create);
    }

    @Override // com.scandit.datacapture.core.ui.overlay.DataCaptureOverlay, com.scandit.datacapture.barcode.batch.ui.overlay.BarcodeBatchAdvancedOverlayProxy
    /* renamed from: _dataCaptureOverlayImpl */
    public final NativeDataCaptureOverlay getC() {
        return this.a.b;
    }

    @Override // com.scandit.datacapture.core.internal.sdk.ui.overlay.ViewBasedDataCaptureOverlay
    public final void _setDataCaptureView(DataCaptureView view) {
        Intrinsics.checkNotNullParameter(view, "view");
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.BarcodeFindBasicOverlay
    public final void a(BarcodeFindGuidanceHandler handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.a.a.setGuidanceHandler(new g(handler, this));
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void b(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        d dVar = this.a;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        dVar.a.setTextForItemListUpdatedHint(text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void c(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        d dVar = this.a;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        dVar.a.setTextForTapShutterToResumeScreenHint(text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void d(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        d dVar = this.a;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        dVar.a.setTextForPointAtBarcodesToSearchHint(text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void e(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        d dVar = this.a;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        dVar.a.setTextForAllItemsFoundSuccessfullyHint(text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void f(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        d dVar = this.a;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        dVar.a.setTextForTapShutterToPauseScreenHint(text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void g(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        d dVar = this.a;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        dVar.a.setTextForItemListUpdatedWhenPausedHint(text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.c
    public final void a(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        d dVar = this.a;
        dVar.getClass();
        Intrinsics.checkNotNullParameter(text, "text");
        dVar.a.setTextForMoveCloserToBarcodesHint(text);
    }

    @Override // com.scandit.datacapture.barcode.internal.module.find.ui.overlay.a
    public final void a(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        addView(view);
    }
}
