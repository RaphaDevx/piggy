package com.scandit.datacapture.frameworks.barcode.pick.listeners;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickStatusIconStyle;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleCustomViewAsyncCallback;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleCustomViewProvider;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleCustomViewResponse;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleRequest;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.tealium.library.DataSources;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodePickViewHighlightStyleCustomViewProvider.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016J\u0006\u0010\u000f\u001a\u00020\u000bJ\"\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewHighlightStyleCustomViewProvider;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleCustomViewProvider;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodePickViewHighlightStyleCustomViewProvider.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleCustomViewAsyncCallback;", "customViewForRequest", "", "request", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleRequest;", PluginMethod.RETURN_CALLBACK, "dispose", "finishViewForRequest", "requestId", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Landroid/view/View;", "statusIconStyle", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickStatusIconStyle;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodePickViewHighlightStyleCustomViewProvider implements BarcodePickViewHighlightStyleCustomViewProvider {
    private static final String FIELD_REQUEST = "request";
    private static final String FIELD_REQUEST_ID = "requestId";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_VIEW_FOR_REQUEST = "BarcodePickViewHighlightStyleCustomViewProvider.viewForRequest";
    private final ConcurrentHashMap<Integer, BarcodePickViewHighlightStyleCustomViewAsyncCallback> cache;
    private final Emitter emitter;
    private final int viewId;

    public FrameworksBarcodePickViewHighlightStyleCustomViewProvider(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleCustomViewProvider
    public void customViewForRequest(BarcodePickViewHighlightStyleRequest request, BarcodePickViewHighlightStyleCustomViewAsyncCallback callback) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(callback, "callback");
        int hashCode = request.hashCode();
        this.emitter.emit(ON_VIEW_FOR_REQUEST, MapsKt.mutableMapOf(TuplesKt.to("requestId", Integer.valueOf(hashCode)), TuplesKt.to("request", request.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        this.cache.put(Integer.valueOf(hashCode), callback);
    }

    public final void finishViewForRequest(int requestId, View view, BarcodePickStatusIconStyle statusIconStyle) {
        BarcodePickViewHighlightStyleCustomViewAsyncCallback barcodePickViewHighlightStyleCustomViewAsyncCallback = this.cache.get(Integer.valueOf(requestId));
        if (barcodePickViewHighlightStyleCustomViewAsyncCallback == null) {
            return;
        }
        barcodePickViewHighlightStyleCustomViewAsyncCallback.onData(new BarcodePickViewHighlightStyleCustomViewResponse(view, statusIconStyle));
    }

    public final void dispose() {
        this.cache.clear();
    }
}
