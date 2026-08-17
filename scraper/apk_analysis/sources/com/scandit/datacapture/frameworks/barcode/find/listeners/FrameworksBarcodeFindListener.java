package com.scandit.datacapture.frameworks.barcode.find.listeners;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindItem;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindListener;
import com.scandit.datacapture.barcode.find.capture.BarcodeFindSession;
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

/* compiled from: FrameworksBarcodeFindListener.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\b\u0010\f\u001a\u00020\bH\u0016J\u0016\u0010\r\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/scandit/datacapture/frameworks/barcode/find/listeners/FrameworksBarcodeFindListener;", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindListener;", "emitter", "Lcom/scandit/datacapture/frameworks/core/events/Emitter;", FrameworksBarcodeFindListener.FIELD_VIEW_ID, "", "(Lcom/scandit/datacapture/frameworks/core/events/Emitter;I)V", "onSearchPaused", "", FrameworksBarcodeFindListener.FIELD_FOUND_ITEMS, "", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindItem;", "onSearchStarted", "onSearchStopped", "onSessionUpdated", "session", "Lcom/scandit/datacapture/barcode/find/capture/BarcodeFindSession;", "Companion", "scandit-datacapture-frameworks-barcode_release"}, k = 1, mv = {1, 9, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class FrameworksBarcodeFindListener implements BarcodeFindListener {
    private static final String FIELD_FOUND_ITEMS = "foundItems";
    private static final String FIELD_SESSION = "session";
    private static final String FIELD_VIEW_ID = "viewId";
    public static final String ON_SEARCH_PAUSED_EVENT_NAME = "BarcodeFindListener.onSearchPaused";
    public static final String ON_SEARCH_STARTED_EVENT_NAME = "BarcodeFindListener.onSearchStarted";
    public static final String ON_SEARCH_STOPPED_EVENT_NAME = "BarcodeFindListener.onSearchStopped";
    public static final String ON_SESSION_UPDATED_EVENT_NAME = "BarcodeFindListener.didUpdateSession";
    private final Emitter emitter;
    private final int viewId;

    public FrameworksBarcodeFindListener(Emitter emitter, int i) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emitter = emitter;
        this.viewId = i;
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindListener
    public void onSearchStarted() {
        if (this.emitter.hasViewSpecificListenersForEvent(this.viewId, ON_SEARCH_STARTED_EVENT_NAME)) {
            this.emitter.emit(ON_SEARCH_STARTED_EVENT_NAME, MapsKt.mutableMapOf(TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindListener
    public void onSearchPaused(Set<BarcodeFindItem> foundItems) {
        Intrinsics.checkNotNullParameter(foundItems, "foundItems");
        if (this.emitter.hasViewSpecificListenersForEvent(this.viewId, ON_SEARCH_STARTED_EVENT_NAME)) {
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
            emitter.emit(ON_SEARCH_PAUSED_EVENT_NAME, MapsKt.mutableMapOf(pairArr));
        }
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindListener
    public void onSearchStopped(Set<BarcodeFindItem> foundItems) {
        Intrinsics.checkNotNullParameter(foundItems, "foundItems");
        if (this.emitter.hasViewSpecificListenersForEvent(this.viewId, ON_SEARCH_STOPPED_EVENT_NAME)) {
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
            emitter.emit(ON_SEARCH_STOPPED_EVENT_NAME, MapsKt.mutableMapOf(pairArr));
        }
    }

    @Override // com.scandit.datacapture.barcode.find.capture.BarcodeFindListener
    public void onSessionUpdated(BarcodeFindSession session) {
        Intrinsics.checkNotNullParameter(session, "session");
        if (this.emitter.hasViewSpecificListenersForEvent(this.viewId, ON_SESSION_UPDATED_EVENT_NAME)) {
            this.emitter.emit(ON_SESSION_UPDATED_EVENT_NAME, MapsKt.mutableMapOf(TuplesKt.to("session", session.toJson()), TuplesKt.to(FIELD_VIEW_ID, Integer.valueOf(this.viewId))));
        }
    }
}
