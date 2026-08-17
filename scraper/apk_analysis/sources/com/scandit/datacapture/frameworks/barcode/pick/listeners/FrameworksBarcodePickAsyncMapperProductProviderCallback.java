package com.scandit.datacapture.frameworks.barcode.pick.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderCallback;
import com.scandit.datacapture.barcode.pick.data.BarcodePickProductProviderCallback;
import com.scandit.datacapture.barcode.pick.data.BarcodePickProductProviderCallbackItem;
import com.scandit.datacapture.frameworks.barcode.pick.data.BarcodePickProductProviderCallbackItemData;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.scandit.datacapture.frameworks.core.events.EventForResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodePickAsyncMapperProductProviderCallback.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001e\u0010\u0010\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickAsyncMapperProductProviderCallback;", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickAsyncMapperProductProviderCallback;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodePickAsyncMapperProductProviderCallback.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "onProductIdentifiersForItemsEvent", "Lcom/scandit/datacapture/frameworks/core/events/EventForResult;", "", "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProviderCallbackItem;", "cancel", "", "finishOnProductIdentifierForItems", "barcodePickProductProviderCallbackItemsJson", "", "productIdentifierForItems", FrameworksBarcodePickAsyncMapperProductProviderCallback.FIELD_ITEMS_DATA, PluginMethod.RETURN_CALLBACK, "Lcom/scandit/datacapture/barcode/pick/data/BarcodePickProductProviderCallback;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodePickAsyncMapperProductProviderCallback implements BarcodePickAsyncMapperProductProviderCallback {
    private static final String FIELD_ITEMS_DATA = "itemsData";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_PRODUCT_IDENTIFIERS_FOR_ITEMS_EVENT_NAME = "BarcodePickAsyncMapperProductProviderCallback.onProductIdentifierForItems";
    private final Emitter emitter;
    private final EventForResult<List<BarcodePickProductProviderCallbackItem>> onProductIdentifiersForItemsEvent;
    private final int viewId;

    public FrameworksBarcodePickAsyncMapperProductProviderCallback(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
        this.onProductIdentifiersForItemsEvent = new EventForResult<>(ON_PRODUCT_IDENTIFIERS_FOR_ITEMS_EVENT_NAME, null, null, 6, null);
    }

    @Override // com.scandit.datacapture.barcode.pick.data.BarcodePickAsyncMapperProductProviderCallback
    public void productIdentifierForItems(List<String> itemsData, BarcodePickProductProviderCallback callback) {
        Intrinsics.checkNotNullParameter(itemsData, "itemsData");
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.onData(this.onProductIdentifiersForItemsEvent.emit(this.emitter, MapsKt.mutableMapOf(TuplesKt.to(FIELD_ITEMS_DATA, itemsData), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))), CollectionsKt.emptyList(), -1L));
    }

    public final void finishOnProductIdentifierForItems(String barcodePickProductProviderCallbackItemsJson) {
        Intrinsics.checkNotNullParameter(barcodePickProductProviderCallbackItemsJson, "barcodePickProductProviderCallbackItemsJson");
        this.onProductIdentifiersForItemsEvent.submitResult(new BarcodePickProductProviderCallbackItemData(barcodePickProductProviderCallbackItemsJson).getItems());
    }

    public final void cancel() {
        this.onProductIdentifiersForItemsEvent.cancel();
    }
}
