package com.scandit.datacapture.frameworks.barcode.ar.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArInfoAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationBodyComponent;
import com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeArInfoAnnotationListener.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J \u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArInfoAnnotationListener;", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeArInfoAnnotationListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "onInfoAnnotationFooterTapped", "", "annotation", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArInfoAnnotation;", "onInfoAnnotationHeaderTapped", "onInfoAnnotationLeftIconTapped", "component", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/info/BarcodeArInfoAnnotationBodyComponent;", FrameworksBarcodeArInfoAnnotationListener.FIELD_COMPONENT_INDEX, "onInfoAnnotationRightIconTapped", "onInfoAnnotationTapped", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeArInfoAnnotationListener implements BarcodeArInfoAnnotationListener {
    private static final String FIELD_BARCODE_ID = "barcodeId";
    private static final String FIELD_COMPONENT_INDEX = "componentIndex";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_DID_TAP_INFO_ANNOTATION_FOOTER = "BarcodeArInfoAnnotationListener.didTapInfoAnnotationFooter";
    public static final String ON_DID_TAP_INFO_ANNOTATION_HEADER = "BarcodeArInfoAnnotationListener.didTapInfoAnnotationHeader";
    public static final String ON_DID_TAP_INFO_ANNOTATION_ICON = "BarcodeArInfoAnnotationListener.didTapInfoAnnotation";
    public static final String ON_DID_TAP_INFO_ANNOTATION_LEFT_ICON = "BarcodeArInfoAnnotationListener.didTapInfoAnnotationLeftIcon";
    public static final String ON_DID_TAP_INFO_ANNOTATION_RIGHT_ICON = "BarcodeArInfoAnnotationListener.didTapInfoAnnotationRightIcon";
    private final Emitter emitter;
    private final int viewId;

    public FrameworksBarcodeArInfoAnnotationListener(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public void onInfoAnnotationFooterTapped(BarcodeArInfoAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        this.emitter.emit(ON_DID_TAP_INFO_ANNOTATION_FOOTER, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(annotation.getBarcode())), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public void onInfoAnnotationHeaderTapped(BarcodeArInfoAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        this.emitter.emit(ON_DID_TAP_INFO_ANNOTATION_HEADER, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(annotation.getBarcode())), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public void onInfoAnnotationTapped(BarcodeArInfoAnnotation annotation) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        this.emitter.emit(ON_DID_TAP_INFO_ANNOTATION_ICON, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(annotation.getBarcode())), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public void onInfoAnnotationLeftIconTapped(BarcodeArInfoAnnotation annotation, BarcodeArInfoAnnotationBodyComponent component, int componentIndex) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(component, "component");
        this.emitter.emit(ON_DID_TAP_INFO_ANNOTATION_LEFT_ICON, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(annotation.getBarcode())), TuplesKt.to(FIELD_COMPONENT_INDEX, Integer.valueOf(componentIndex)), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.info.BarcodeArInfoAnnotationListener
    public void onInfoAnnotationRightIconTapped(BarcodeArInfoAnnotation annotation, BarcodeArInfoAnnotationBodyComponent component, int componentIndex) {
        Intrinsics.checkNotNullParameter(annotation, "annotation");
        Intrinsics.checkNotNullParameter(component, "component");
        this.emitter.emit(ON_DID_TAP_INFO_ANNOTATION_RIGHT_ICON, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(annotation.getBarcode())), TuplesKt.to(FIELD_COMPONENT_INDEX, Integer.valueOf(componentIndex)), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }
}
