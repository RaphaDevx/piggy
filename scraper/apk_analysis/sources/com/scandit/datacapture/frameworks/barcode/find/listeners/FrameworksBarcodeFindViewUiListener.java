package com.scandit.datacapture.frameworks.barcode.find.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItem;
import com.scandit.datacapture.barcode.find.ui.BarcodeFindViewUiListener;
import com.scandit.datacapture.frameworks.core.events.Emitter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FrameworksBarcodeFindViewUiListener.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/listeners/FrameworksBarcodeFindViewUiListener;", "Lcom/scandit/datacapture/barcode/find/ui/BarcodeFindViewUiListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeFindViewUiListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "onFinishButtonTapped", "", FrameworksBarcodeFindViewUiListener.FIELD_FOUND_ITEMS, "", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindItem;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeFindViewUiListener implements BarcodeFindViewUiListener {
    private static final String FIELD_FOUND_ITEMS = "foundItems";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_FINISH_BUTTON_TAPPED_EVENT_NAME = "BarcodeFindViewUiListener.onFinishButtonTapped";
    private final Emitter emitter;
    private final int viewId;

    public FrameworksBarcodeFindViewUiListener(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
    }

    @Override // com.scandit.datacapture.barcode.find.ui.BarcodeFindViewUiListener
    public void onFinishButtonTapped(Set<BarcodeFindItem> foundItems) {
        Intrinsics.checkNotNullParameter(foundItems, "foundItems");
        if (this.emitter.hasViewSpecificListenersForEvent(this.viewId, ON_FINISH_BUTTON_TAPPED_EVENT_NAME)) {
            Emitter emitter = this.emitter;
            Pair[] pairArr = new Pair[2];
            Set<BarcodeFindItem> set = foundItems;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                arrayList.add(((BarcodeFindItem) it.next()).getSearchOptions().getBarcodeData());
            }
            pairArr[0] = TuplesKt.to(FIELD_FOUND_ITEMS, arrayList);
            pairArr[1] = TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId));
            emitter.emit(ON_FINISH_BUTTON_TAPPED_EVENT_NAME, MapsKt.mutableMapOf(pairArr));
        }
    }
}
