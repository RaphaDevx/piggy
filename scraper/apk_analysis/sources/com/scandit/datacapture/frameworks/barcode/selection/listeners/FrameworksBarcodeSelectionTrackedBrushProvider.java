package com.scandit.datacapture.frameworks.barcode.selection.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.data.Barcode;
import com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionBrushProvider;
import com.scandit.datacapture.core.ui.style.Brush;
import com.scandit.datacapture.core.ui.style.BrushDeserializer;
import com.scandit.datacapture.frameworks.barcode.extensions.ExtensionsKt;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeSelectionTrackedBrushProvider.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\nR\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/selection/listeners/FrameworksBarcodeSelectionTrackedBrushProvider;", "Lcom/scandit/datacapture/barcode/selection/capture/BarcodeSelectionBrushProvider;", "eventEmitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;)V", "brushForBarcode", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "Lcom/scandit/datacapture/core/ui/style/Brush;", "cachedBrushes", "", "", FrameworksBarcodeSelectionTrackedBrushProvider.FIELD_BARCODE, "Lcom/scandit/datacapture/barcode/data/Barcode;", "clearCache", "", "onFinishCallback", "brushJson", "selectionIdentifier", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeSelectionTrackedBrushProvider implements BarcodeSelectionBrushProvider {
    public static final String BRUSH_FOR_TRACKED_BARCODE_EVENT_NAME = "BarcodeSelectionTrackedBrushProvider.brushForBarcode";
    private static final String FIELD_BARCODE = "barcode";
    private final EventForResult<Brush> brushForBarcode;
    private final Map<String, Brush> cachedBrushes;
    private final Emitter eventEmitter;

    public FrameworksBarcodeSelectionTrackedBrushProvider(Emitter eventEmitter) {
        Intrinsics.checkNotNullParameter(eventEmitter, "eventEmitter");
        this.eventEmitter = eventEmitter;
        this.cachedBrushes = new LinkedHashMap();
        this.brushForBarcode = new EventForResult<>(BRUSH_FOR_TRACKED_BARCODE_EVENT_NAME, null, null, 6, null);
    }

    @Override // com.scandit.datacapture.barcode.selection.capture.BarcodeSelectionBrushProvider
    public Brush brushForBarcode(Barcode barcode) {
        Intrinsics.checkNotNullParameter(barcode, "barcode");
        if (!this.eventEmitter.hasListenersForEvent(BRUSH_FOR_TRACKED_BARCODE_EVENT_NAME)) {
            return null;
        }
        if (this.cachedBrushes.containsKey(ExtensionsKt.selectionIdentifier(barcode))) {
            return this.cachedBrushes.get(ExtensionsKt.selectionIdentifier(barcode));
        }
        return (Brush) EventForResult.emit$default(this.brushForBarcode, this.eventEmitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_BARCODE, barcode.toJson())), null, 0L, 8, null);
    }

    public final void onFinishCallback(String brushJson, String selectionIdentifier) {
        Brush brush;
        EventForResult<Brush> eventForResult = this.brushForBarcode;
        if (brushJson != null) {
            brush = BrushDeserializer.fromJson(brushJson);
            Map<String, Brush> map = this.cachedBrushes;
            if (selectionIdentifier == null) {
                selectionIdentifier = "";
            }
            map.put(selectionIdentifier, brush);
        } else {
            brush = null;
        }
        eventForResult.submitResult(brush);
    }

    public final void clearCache() {
        this.cachedBrushes.clear();
    }
}
