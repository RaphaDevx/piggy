package com.scandit.datacapture.frameworks.barcode.ar.listeners;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlight;
import com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlightProvider;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.frameworks.barcode.ar.caches.BarcodeArAugmentationsCache;
import com.scandit.datacapture.frameworks.barcode.ar.data.HighlightCallbackData;
import com.scandit.datacapture.frameworks.barcode.ar.parsers.BarcodeArHighlightParser;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: FrameworksBarcodeArHighlightProvider.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J \u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0010R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/ar/listeners/FrameworksBarcodeArHighlightProvider;", "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlightProvider;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeArHighlightProvider.FIELD_VIEW_ID, "", "highlightParser", "Lcom/scandit/datacapture/frameworks/barcode/ar/parsers/BarcodeArHighlightParser;", "cache", "Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;ILcom/scandit/datacapture/frameworks/barcode/ar/parsers/BarcodeArHighlightParser;Lcom/scandit/datacapture/frameworks/barcode/ar/caches/BarcodeArAugmentationsCache;)V", "finishHighlightForBarcode", "", "context", "Landroid/content/Context;", "highlightResponse", "", "highlightForBarcode", FrameworksBarcodeArHighlightProvider.FIELD_BARCODE, "Lcom/scandit/datacapture/barcode/data/Barcode;", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/ar/ui/highlight/BarcodeArHighlightProvider$Callback;", "updateHighlight", "highlightJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodeArHighlightProvider implements BarcodeArHighlightProvider {
    private static final String FIELD_BARCODE = "barcode";
    private static final String FIELD_BARCODE_ID = "barcodeId";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_HIGHLIGHT_FOR_BARCODE = "BarcodeArHighlightProvider.highlightForBarcode";
    private final BarcodeArAugmentationsCache cache;
    private final Emitter emitter;
    private final BarcodeArHighlightParser highlightParser;
    private final int viewId;

    public FrameworksBarcodeArHighlightProvider(Emitter emitter, int i, BarcodeArHighlightParser highlightParser, BarcodeArAugmentationsCache cache) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        Intrinsics.checkNotNullParameter(highlightParser, "highlightParser");
        Intrinsics.checkNotNullParameter(cache, "cache");
        this.emitter = emitter;
        this.viewId = i;
        this.highlightParser = highlightParser;
        this.cache = cache;
    }

    @Override // com.scandit.datacapture.barcode.ar.ui.highlight.BarcodeArHighlightProvider
    public void highlightForBarcode(Context context, Barcode barcode, BarcodeArHighlightProvider.Callback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.cache.addHighlightProviderCallback(ExtensionsKt.getUniqueId(barcode), new HighlightCallbackData(barcode, callback));
        this.emitter.emit(ON_HIGHLIGHT_FOR_BARCODE, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE, barcode.toJson()), TuplesKt.to(FIELD_BARCODE_ID, ExtensionsKt.getUniqueId(barcode)), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }

    public final void finishHighlightForBarcode(Context context, String highlightResponse) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(highlightResponse, "highlightResponse");
        JSONObject jSONObject = new JSONObject(highlightResponse);
        String string = jSONObject.getString(FIELD_BARCODE_ID);
        BarcodeArAugmentationsCache barcodeArAugmentationsCache = this.cache;
        Intrinsics.checkNotNull(string);
        HighlightCallbackData highlightProviderCallback = barcodeArAugmentationsCache.getHighlightProviderCallback(string);
        if (highlightProviderCallback == null) {
            return;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("highlight");
        if (optJSONObject == null) {
            highlightProviderCallback.getCallback().onData(null);
            return;
        }
        BarcodeArHighlight barcodeArHighlight = this.highlightParser.get(context, highlightProviderCallback.getBarcode(), optJSONObject);
        if (barcodeArHighlight != null) {
            this.cache.addHighlight(string, barcodeArHighlight);
        }
        highlightProviderCallback.getCallback().onData(barcodeArHighlight);
    }

    public final void updateHighlight(String highlightJson) {
        Intrinsics.checkNotNullParameter(highlightJson, "highlightJson");
        JSONObject jSONObject = new JSONObject(highlightJson);
        String string = jSONObject.getString(FIELD_BARCODE_ID);
        BarcodeArAugmentationsCache barcodeArAugmentationsCache = this.cache;
        Intrinsics.checkNotNull(string);
        BarcodeArHighlight highlight = barcodeArAugmentationsCache.getHighlight(string);
        if (highlight == null) {
            return;
        }
        this.highlightParser.updateHighlight(highlight, jSONObject);
    }
}
