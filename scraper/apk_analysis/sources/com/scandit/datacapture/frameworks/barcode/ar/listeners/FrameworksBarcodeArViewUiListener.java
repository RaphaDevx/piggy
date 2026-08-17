package com.scandit.datacapture.frameworks.barcode.ar.listeners;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.ar.capture.BarcodeAr;
import com.scandit.datacapture.barcode.ar.ui.BarcodeArViewUiListener;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlight;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeArViewUiListener.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArViewUiListener;", "Lcom/scandit/datacapture/barcode/ar/ui/BarcodeArViewUiListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeArViewUiListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "onHighlightForBarcodeTapped", "", "barcodeAr", "Lcom/scandit/datacapture/barcode/ar/capture/BarcodeAr;", FrameworksBarcodeArViewUiListener.FIELD_BARCODE, "Lcom/scandit/datacapture/barcode/data/Barcode;", "highlight", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlight;", "highlightView", "Landroid/view/View;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeArViewUiListener implements BarcodeArViewUiListener {
    private static final String FIELD_BARCODE = "barcode";
    private static final String FIELD_BARCODE_ID = "barcodeId";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_HIGHLIGHT_FOR_BARCODE_TAPPED = "BarcodeArViewUiListener.didTapHighlightForBarcode";
    private final Emitter emitter;
    private final int viewId;

    public FrameworksBarcodeArViewUiListener(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.BarcodeArViewUiListener
    public void onHighlightForBarcodeTapped(BarcodeAr barcodeAr, Barcode barcode, BarcodeArHighlight highlight, View highlightView) {
        Intrinsics.checkNotNullParameter(barcodeAr, "barcodeAr");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(highlight, "highlight");
        Intrinsics.checkNotNullParameter(highlightView, "highlightView");
        this.emitter.emit(ON_HIGHLIGHT_FOR_BARCODE_TAPPED, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE, barcode.toJson()), TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(barcode)), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }
}
