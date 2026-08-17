package androidx.compose.foundation.lazy;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListScrollPosition.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J#\u0010\u0018\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u0005J%\u0010\u001b\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0003H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u0005J\u000e\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\"R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R)\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\fR\u001e\u0010\u0015\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListScrollPosition;", "", "initialIndex", "", "initialScrollOffset", "(II)V", "hadFirstNotEmptyLayout", "", "<set-?>", "Landroidx/compose/foundation/lazy/DataIndex;", FirebaseAnalytics.Param.INDEX, "getIndex-jQJCoq8", "()I", "I", "indexState", "Landroidx/compose/runtime/MutableState;", "lastKnownFirstItemKey", "observableIndex", "getObservableIndex", "observableScrollOffset", "getObservableScrollOffset", "scrollOffset", "getScrollOffset", "scrollOffsetState", "requestPosition", "", "requestPosition-AhXoVpI", "update", "update-AhXoVpI", "updateFromMeasureResult", "measureResult", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "updateScrollPositionIfTheFirstItemWasMoved", "itemsProvider", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", "Companion", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListScrollPosition {
    private static final Companion Companion = new Companion(null);
    private boolean hadFirstNotEmptyLayout;
    private int index;
    private final MutableState<Integer> indexState;
    private Object lastKnownFirstItemKey;
    private int scrollOffset;
    private final MutableState<Integer> scrollOffsetState;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public LazyListScrollPosition() {
        /*
            r3 = this;
            r0 = 3
            r1 = 0
            r2 = 0
            r3.<init>(r2, r2, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListScrollPosition.<init>():void");
    }

    public LazyListScrollPosition(int i, int i2) {
        this.index = DataIndex.m371constructorimpl(i);
        this.scrollOffset = i2;
        this.indexState = SnapshotStateKt.mutableStateOf$default(Integer.valueOf(getIndex()), null, 2, null);
        this.scrollOffsetState = SnapshotStateKt.mutableStateOf$default(Integer.valueOf(this.scrollOffset), null, 2, null);
    }

    public /* synthetic */ LazyListScrollPosition(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    /* renamed from: getIndex-jQJCoq8, reason: not valid java name and from getter */
    public final int getIndex() {
        return this.index;
    }

    public final int getScrollOffset() {
        return this.scrollOffset;
    }

    public final int getObservableIndex() {
        return this.indexState.getValue().intValue();
    }

    public final int getObservableScrollOffset() {
        return this.scrollOffsetState.getValue().intValue();
    }

    public final void updateFromMeasureResult(LazyListMeasureResult measureResult) {
        Intrinsics.checkNotNullParameter(measureResult, "measureResult");
        LazyMeasuredItem firstVisibleItem = measureResult.getFirstVisibleItem();
        this.lastKnownFirstItemKey = firstVisibleItem == null ? null : firstVisibleItem.getKey();
        if (this.hadFirstNotEmptyLayout || measureResult.getTotalItemsCount() > 0) {
            this.hadFirstNotEmptyLayout = true;
            LazyMeasuredItem firstVisibleItem2 = measureResult.getFirstVisibleItem();
            m392updateAhXoVpI(DataIndex.m371constructorimpl(firstVisibleItem2 == null ? 0 : firstVisibleItem2.getIndex()), measureResult.getFirstVisibleItemScrollOffset());
        }
    }

    /* renamed from: requestPosition-AhXoVpI, reason: not valid java name */
    public final void m394requestPositionAhXoVpI(int index, int scrollOffset) {
        m392updateAhXoVpI(index, scrollOffset);
        this.lastKnownFirstItemKey = null;
    }

    public final void updateScrollPositionIfTheFirstItemWasMoved(LazyListItemsProvider itemsProvider) {
        Intrinsics.checkNotNullParameter(itemsProvider, "itemsProvider");
        m392updateAhXoVpI(Companion.m396findLazyListIndexByKeyKXnL5Ic(this.lastKnownFirstItemKey, getIndex(), itemsProvider), this.scrollOffset);
    }

    /* renamed from: update-AhXoVpI, reason: not valid java name */
    private final void m392updateAhXoVpI(int index, int scrollOffset) {
        if (!(((float) index) >= 0.0f)) {
            throw new IllegalArgumentException(("Index should be non-negative (" + index + ')').toString());
        }
        if (!(((float) scrollOffset) >= 0.0f)) {
            throw new IllegalArgumentException(("scrollOffset should be non-negative (" + scrollOffset + ')').toString());
        }
        if (!DataIndex.m374equalsimpl0(index, getIndex())) {
            this.index = index;
            this.indexState.setValue(Integer.valueOf(index));
        }
        if (scrollOffset != this.scrollOffset) {
            this.scrollOffset = scrollOffset;
            this.scrollOffsetState.setValue(Integer.valueOf(scrollOffset));
        }
    }

    /* compiled from: LazyListScrollPosition.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListScrollPosition$Companion;", "", "()V", "findLazyListIndexByKey", "Landroidx/compose/foundation/lazy/DataIndex;", "key", "lastKnownIndex", "itemsProvider", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", "findLazyListIndexByKey-KXnL5Ic", "(Ljava/lang/Object;ILandroidx/compose/foundation/lazy/LazyListItemsProvider;)I", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: findLazyListIndexByKey-KXnL5Ic, reason: not valid java name */
        public final int m396findLazyListIndexByKeyKXnL5Ic(Object key, int lastKnownIndex, LazyListItemsProvider itemsProvider) {
            if (key == null) {
                return lastKnownIndex;
            }
            int itemsCount = itemsProvider.getItemsCount();
            if (lastKnownIndex < itemsCount && Intrinsics.areEqual(key, itemsProvider.getKey(lastKnownIndex))) {
                return lastKnownIndex;
            }
            int min = Math.min(itemsCount - 1, lastKnownIndex - 1);
            int i = lastKnownIndex + 1;
            while (true) {
                if (min < 0 && i >= itemsCount) {
                    return lastKnownIndex;
                }
                if (min >= 0) {
                    if (Intrinsics.areEqual(key, itemsProvider.getKey(min))) {
                        return DataIndex.m371constructorimpl(min);
                    }
                    min--;
                }
                if (i < itemsCount) {
                    if (Intrinsics.areEqual(key, itemsProvider.getKey(i))) {
                        return DataIndex.m371constructorimpl(i);
                    }
                    i++;
                }
            }
        }
    }
}
