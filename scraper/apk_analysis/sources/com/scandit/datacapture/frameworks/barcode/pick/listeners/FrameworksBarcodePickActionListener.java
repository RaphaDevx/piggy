package com.scandit.datacapture.frameworks.barcode.pick.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.getcapacitor.PluginMethod;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickActionCallback;
import com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListener;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodePickActionListener.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\nH\u0016J\u0018\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickActionListener;", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickActionListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodePickActionListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "events", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/scandit/datacapture/barcode/pick/capture/BarcodePickActionCallback;", "finishPickAction", "", FrameworksBarcodePickActionListener.FIELD_ITEM_DATA, "result", "", "onPick", PluginMethod.RETURN_CALLBACK, "onUnpick", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodePickActionListener implements BarcodePickActionListener {
    public static final String DID_PICK_EVENT_NAME = "BarcodePickActionListener.didPick";
    public static final String DID_UNPICK_EVENT_NAME = "BarcodePickActionListener.didUnpick";
    private static final String FIELD_ITEM_DATA = "itemData";
    private static final String FIELD_VIEW_ID = "viewId";
    private final Emitter emitter;
    private final ConcurrentHashMap<String, BarcodePickActionCallback> events;
    private final int viewId;

    public FrameworksBarcodePickActionListener(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
        this.events = new ConcurrentHashMap<>();
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListener
    public void onPick(String itemData, BarcodePickActionCallback callback) {
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.events.put(itemData, callback);
        this.emitter.emit(DID_PICK_EVENT_NAME, MapsKt.mutableMapOf(TuplesKt.to(FIELD_ITEM_DATA, itemData), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }

    @Override // com.scandit.datacapture.barcode.pick.capture.BarcodePickActionListener
    public void onUnpick(String itemData, BarcodePickActionCallback callback) {
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.events.put(itemData, callback);
        this.emitter.emit(DID_UNPICK_EVENT_NAME, MapsKt.mutableMapOf(TuplesKt.to(FIELD_ITEM_DATA, itemData), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
    }

    public final void finishPickAction(String itemData, boolean result) {
        Intrinsics.checkNotNullParameter(itemData, "itemData");
        BarcodePickActionCallback orDefault = this.events.getOrDefault(itemData, null);
        this.events.remove(itemData);
        if (orDefault != null) {
            orDefault.onFinish(result);
        }
    }
}
