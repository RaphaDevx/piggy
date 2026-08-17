package com.scandit.datacapture.frameworks.barcode.pick.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickView;
import com.scandit.datacapture.barcode.pick.ui.BarcodePickViewUiListener;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import com.tealium.library.DataSources;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodePickViewUiListener.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/pick/listeners/FrameworksBarcodePickViewUiListener;", "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickViewUiListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodePickViewUiListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "onFinishButtonTapped", "", DataSources.EventTypeValue.VIEW_EVENT_TYPE, "Lcom/scandit/datacapture/barcode/pick/ui/BarcodePickView;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodePickViewUiListener implements BarcodePickViewUiListener {
    public static final String DID_TAP_FINISH_BUTTON_EVENT = "BarcodePickViewUiListener.didTapFinishButton";
    private static final String FIELD_VIEW_ID = "viewId";
    private final Emitter emitter;
    private final int viewId;

    public FrameworksBarcodePickViewUiListener(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
    }

    @Override // com.scandit.datacapture.barcode.pick.ui.BarcodePickViewUiListener
    public void onFinishButtonTapped(BarcodePickView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this.emitter.hasViewSpecificListenersForEvent(this.viewId, DID_TAP_FINISH_BUTTON_EVENT)) {
            this.emitter.emit(DID_TAP_FINISH_BUTTON_EVENT, MapsKt.mutableMapOf(TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }
}
