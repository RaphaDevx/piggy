package com.scandit.datacapture.frameworks.barcode.ar.listeners;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotation;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationProvider;
import com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArPopoverAnnotation;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.frameworks.barcode.ar.caches.BarcodeArAugmentationsCache;
import com.scandit.datacapture.frameworks.barcode.ar.data.AnnotationCallbackData;
import com.scandit.datacapture.frameworks.barcode.ar.parsers.BarcodeArAnnotationParser;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: FrameworksBarcodeArAnnotationProvider.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0016\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u0015R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArAnnotationProvider;", "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationProvider;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeArAnnotationProvider.FIELD_VIEW_ID, "", "annotationParser", "Lcom/scandit/datacapture/frameworks/barcode/ar/parsers/BarcodeArAnnotationParser;", "cache", "Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;ILcom/scandit/datacapture/frameworks/barcode/ar/parsers/BarcodeArAnnotationParser;Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;)V", "annotationForBarcode", "", "context", "Landroid/content/Context;", FrameworksBarcodeArAnnotationProvider.FIELD_BARCODE, "Lcom/scandit/datacapture/barcode/data/Barcode;", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/ar/ui/annotations/BarcodeArAnnotationProvider$Callback;", "finishAnnotationForBarcode", "annotationResponse", "", "updateAnnotation", "annotationJson", "updateBarcodeArPopoverButtonAtIndex", "updateJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeArAnnotationProvider implements BarcodeArAnnotationProvider {
    private static final String FIELD_BARCODE = "barcode";
    private static final String FIELD_BARCODE_ID = "barcodeId";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_ANNOTATION_FOR_BARCODE = "BarcodeArAnnotationProvider.annotationForBarcode";
    private final BarcodeArAnnotationParser annotationParser;
    private final BarcodeArAugmentationsCache cache;
    private final Emitter emitter;
    private final int viewId;

    public FrameworksBarcodeArAnnotationProvider(Emitter emitter, int i, BarcodeArAnnotationParser annotationParser, BarcodeArAugmentationsCache cache) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(annotationParser, "annotationParser");
        Intrinsics.checkNotNullParameter(cache, "cache");
        this.emitter = emitter;
        this.viewId = i;
        this.annotationParser = annotationParser;
        this.cache = cache;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.annotations.BarcodeArAnnotationProvider
    public void annotationForBarcode(Context context, Barcode barcode, BarcodeArAnnotationProvider.Callback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.cache.addAnnotationProviderCallback(ExtensionsKt.getUniqueId(barcode), new AnnotationCallbackData(barcode, callback));
        this.emitter.emit(ON_ANNOTATION_FOR_BARCODE, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE, barcode.toJson()), TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(barcode)), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }

    public final void finishAnnotationForBarcode(Context context, String annotationResponse) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(annotationResponse, "annotationResponse");
        JSONObject jSONObject = new JSONObject(annotationResponse);
        String string = jSONObject.getString(FIELD_BARCODE_ID);
        BarcodeArAugmentationsCache barcodeArAugmentationsCache = this.cache;
        Intrinsics.checkNotNull(string);
        AnnotationCallbackData annotationProviderCallback = barcodeArAugmentationsCache.getAnnotationProviderCallback(string);
        if (annotationProviderCallback == null) {
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("annotation");
        if (optJSONObject == null) {
            annotationProviderCallback.getCallback().onData(null);
            return;
        }
        BarcodeArAnnotation barcodeArAnnotation = this.annotationParser.get(context, optJSONObject, annotationProviderCallback.getBarcode());
        if (barcodeArAnnotation != null) {
            this.cache.addAnnotation(string, barcodeArAnnotation);
        }
        annotationProviderCallback.getCallback().onData(barcodeArAnnotation);
    }

    public final void updateAnnotation(String annotationJson) {
        Intrinsics.checkNotNullParameter(annotationJson, "annotationJson");
        JSONObject jSONObject = new JSONObject(annotationJson);
        String string = jSONObject.getString(FIELD_BARCODE_ID);
        BarcodeArAugmentationsCache barcodeArAugmentationsCache = this.cache;
        Intrinsics.checkNotNull(string);
        BarcodeArAnnotation annotation = barcodeArAugmentationsCache.getAnnotation(string);
        if (annotation == null) {
            return;
        }
        this.annotationParser.updateAnnotation(annotation, jSONObject);
    }

    public final void updateBarcodeArPopoverButtonAtIndex(String updateJson) {
        Intrinsics.checkNotNullParameter(updateJson, "updateJson");
        JSONObject jSONObject = new JSONObject(updateJson);
        String string = jSONObject.getString(FIELD_BARCODE_ID);
        BarcodeArAugmentationsCache barcodeArAugmentationsCache = this.cache;
        Intrinsics.checkNotNull(string);
        BarcodeArAnnotation annotation = barcodeArAugmentationsCache.getAnnotation(string);
        BarcodeArPopoverAnnotation barcodeArPopoverAnnotation = annotation instanceof BarcodeArPopoverAnnotation ? (BarcodeArPopoverAnnotation) annotation : null;
        if (barcodeArPopoverAnnotation == null) {
            return;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("button");
        BarcodeArAnnotationParser barcodeArAnnotationParser = this.annotationParser;
        Intrinsics.checkNotNull(jSONObject2);
        barcodeArAnnotationParser.updateBarcodeArPopoverButton(barcodeArPopoverAnnotation, jSONObject2);
    }
}
