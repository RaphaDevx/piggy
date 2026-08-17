package com.scandit.datacapture.frameworks.barcode.pick.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleAsyncCallback;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleAsyncProvider;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleRequest;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleResponse;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodePickViewHighlightStyleAsyncProvider.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewHighlightStyleAsyncProvider;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleAsyncProvider;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodePickViewHighlightStyleAsyncProvider.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleAsyncCallback;", "dispose", "", "finishStyleForRequest", "requestId", "responseJson", "", "styleForRequest", "request", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewHighlightStyleRequest;", PluginMethod.RETURN_CALLBACK, "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FrameworksBarcodePickViewHighlightStyleAsyncProvider implements BarcodePickViewHighlightStyleAsyncProvider {
    private static final String FIELD_REQUEST = "request";
    private static final String FIELD_REQUEST_ID = "requestId";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_STYLE_FOR_REQUEST = "BarcodePickViewHighlightStyleAsyncProvider.styleForRequest";
    private final ConcurrentHashMap<Integer, BarcodePickViewHighlightStyleAsyncCallback> cache;
    private final Emitter emitter;
    private final int viewId;

    public FrameworksBarcodePickViewHighlightStyleAsyncProvider(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
        this.cache = new ConcurrentHashMap<>();
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewHighlightStyleAsyncProvider
    public void styleForRequest(BarcodePickViewHighlightStyleRequest request, BarcodePickViewHighlightStyleAsyncCallback callback) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(callback, "callback");
        int hashCode = request.hashCode();
        this.emitter.emit(ON_STYLE_FOR_REQUEST, MapsKt.mutableMapOf(TuplesKt.to("requestId", Integer.valueOf(hashCode)), TuplesKt.to("request", request.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        this.cache.put(Integer.valueOf(hashCode), callback);
    }

    public final void finishStyleForRequest(int requestId, String responseJson) {
        BarcodePickViewHighlightStyleAsyncCallback barcodePickViewHighlightStyleAsyncCallback = this.cache.get(Integer.valueOf(requestId));
        if (barcodePickViewHighlightStyleAsyncCallback == null) {
            return;
        }
        if (responseJson == null) {
            barcodePickViewHighlightStyleAsyncCallback.onData(null);
        } else {
            barcodePickViewHighlightStyleAsyncCallback.onData(BarcodePickViewHighlightStyleResponse.INSTANCE.fromJson(responseJson));
        }
    }

    public final void dispose() {
        this.cache.clear();
    }
}
