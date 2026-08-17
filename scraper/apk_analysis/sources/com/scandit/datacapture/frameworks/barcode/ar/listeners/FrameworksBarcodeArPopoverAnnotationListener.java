package com.scandit.datacapture.frameworks.barcode.ar.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArPopoverAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationButton;
import com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationListener;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeArPopoverAnnotationListener.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArPopoverAnnotationListener;", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeArPopoverAnnotationListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "onPopoverButtonTapped", "", "popover", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArPopoverAnnotation;", "button", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/popover/BarcodeArPopoverAnnotationButton;", FrameworksBarcodeArPopoverAnnotationListener.FIELD_BUTTON_INDEX, "onPopoverTapped", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeArPopoverAnnotationListener implements BarcodeArPopoverAnnotationListener {
    private static final String FIELD_BARCODE_ID = "barcodeId";
    private static final String FIELD_BUTTON_INDEX = "buttonIndex";
    public static final String FIELD_DID_TAP_POPOVER = "BarcodeArPopoverAnnotationListener.didTapPopover";
    public static final String FIELD_ON_DID_TAP_POPOEVER_BUTTON = "BarcodeArPopoverAnnotationListener.didTapPopoverButton";
    private static final String FIELD_VIEW_ID = "viewId";
    private final Emitter emitter;
    private final int viewId;

    public FrameworksBarcodeArPopoverAnnotationListener(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationListener
    public void onPopoverTapped(BarcodeArPopoverAnnotation popover) {
        Intrinsics.checkNotNullParameter(popover, "popover");
        this.emitter.emit(FIELD_DID_TAP_POPOVER, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(popover.getBarcode())), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.popover.BarcodeArPopoverAnnotationListener
    public void onPopoverButtonTapped(BarcodeArPopoverAnnotation popover, BarcodeArPopoverAnnotationButton button, int buttonIndex) {
        Intrinsics.checkNotNullParameter(popover, "popover");
        Intrinsics.checkNotNullParameter(button, "button");
        this.emitter.emit(FIELD_ON_DID_TAP_POPOEVER_BUTTON, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(popover.getBarcode())), TuplesKt.to(FIELD_BUTTON_INDEX, Integer.valueOf(buttonIndex)), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }
}
