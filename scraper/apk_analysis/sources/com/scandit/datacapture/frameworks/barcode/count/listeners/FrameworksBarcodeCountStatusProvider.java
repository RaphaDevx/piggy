package com.scandit.datacapture.frameworks.barcode.count.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.batch.data.TrackedBarcode;
import com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusProvider;
import com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusProviderCallback;
import com.scandit.datacapture.frameworks.barcode.count.data.BarcodeCountStatusProviderRequest;
import com.scandit.datacapture.frameworks.barcode.count.data.BarcodeCountStatusProviderResult;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeCountStatusProvider.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/count/listeners/FrameworksBarcodeCountStatusProvider;", "Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProvider;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeCountStatusProvider.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "events", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/scandit/datacapture/frameworks/barcode/count/data/BarcodeCountStatusProviderRequest;", "onStatusRequested", "", FrameworksBarcodeCountStatusProvider.FIELD_BARCODES, "", "Lcom/scandit/datacapture/barcode/batch/data/TrackedBarcode;", PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/count/ui/view/status/BarcodeCountStatusProviderCallback;", "submitCallbackResult", "resultJson", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeCountStatusProvider implements BarcodeCountStatusProvider {
    private static final String FIELD_BARCODES = "barcodes";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_STATUS_REQUESTED = "BarcodeCountStatusProvider.onStatusRequested";
    private final Emitter emitter;
    private final ConcurrentHashMap<String, BarcodeCountStatusProviderRequest> events;
    private final int viewId;

    public FrameworksBarcodeCountStatusProvider(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
        this.events = new ConcurrentHashMap<>();
    }

    @Override // com.scandit.datacapture.barcode.count.ui.view.status.BarcodeCountStatusProvider
    public void onStatusRequested(List<TrackedBarcode> barcodes, BarcodeCountStatusProviderCallback callback) {
        Intrinsics.checkNotNullParameter(barcodes, "barcodes");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.emitter.hasViewSpecificListenersForEvent(this.viewId, ON_STATUS_REQUESTED)) {
            BarcodeCountStatusProviderRequest barcodeCountStatusProviderRequest = new BarcodeCountStatusProviderRequest(barcodes, callback, null, 4, null);
            this.events.put(barcodeCountStatusProviderRequest.getRequestId(), barcodeCountStatusProviderRequest);
            Emitter emitter = this.emitter;
            Pair[] pairArr = new Pair[3];
            List<TrackedBarcode> list = barcodes;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((TrackedBarcode) it.next()).toJson());
            }
            pairArr[0] = TuplesKt.to(FIELD_BARCODES, CollectionsKt.toList(arrayList));
            pairArr[1] = TuplesKt.to("requestId", barcodeCountStatusProviderRequest.getRequestId());
            pairArr[2] = TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId));
            emitter.emit(ON_STATUS_REQUESTED, MapsKt.mutableMapOf(pairArr));
        }
    }

    public final void submitCallbackResult(String resultJson) {
        Intrinsics.checkNotNullParameter(resultJson, "resultJson");
        BarcodeCountStatusProviderResult createFromJson = BarcodeCountStatusProviderResult.INSTANCE.createFromJson(resultJson);
        BarcodeCountStatusProviderRequest remove = this.events.remove(createFromJson.getRequestId());
        if (remove == null) {
            return;
        }
        remove.getCallback().onStatusReady(createFromJson.get(remove.getBarcodes()));
    }
}
