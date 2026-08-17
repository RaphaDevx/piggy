package androidx.compose.foundation.lazy;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LazyMeasuredItemProvider.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B8\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\rø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001b\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018R\u001c\u0010\u000f\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0019"}, d2 = {"Landroidx/compose/foundation/lazy/LazyMeasuredItemProvider;", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "", "scope", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "itemsProvider", "Landroidx/compose/foundation/lazy/LazyListItemsProvider;", "itemContentFactory", "Landroidx/compose/foundation/lazy/LazyListItemContentFactory;", "measuredItemFactory", "Landroidx/compose/foundation/lazy/MeasuredItemFactory;", "(JZLandroidx/compose/ui/layout/SubcomposeMeasureScope;Landroidx/compose/foundation/lazy/LazyListItemsProvider;Landroidx/compose/foundation/lazy/LazyListItemContentFactory;Landroidx/compose/foundation/lazy/MeasuredItemFactory;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "childConstraints", "getChildConstraints-msEJaDk", "()J", "J", "getAndMeasure", "Landroidx/compose/foundation/lazy/LazyMeasuredItem;", FirebaseAnalytics.Param.INDEX, "Landroidx/compose/foundation/lazy/DataIndex;", "getAndMeasure-ZjPyQlc", "(I)Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyMeasuredItemProvider {
    private final long childConstraints;
    private final LazyListItemContentFactory itemContentFactory;
    private final LazyListItemsProvider itemsProvider;
    private final MeasuredItemFactory measuredItemFactory;
    private final SubcomposeMeasureScope scope;

    public /* synthetic */ LazyMeasuredItemProvider(long j, boolean z, SubcomposeMeasureScope subcomposeMeasureScope, LazyListItemsProvider lazyListItemsProvider, LazyListItemContentFactory lazyListItemContentFactory, MeasuredItemFactory measuredItemFactory, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, z, subcomposeMeasureScope, lazyListItemsProvider, lazyListItemContentFactory, measuredItemFactory);
    }

    private LazyMeasuredItemProvider(long j, boolean z, SubcomposeMeasureScope subcomposeMeasureScope, LazyListItemsProvider lazyListItemsProvider, LazyListItemContentFactory lazyListItemContentFactory, MeasuredItemFactory measuredItemFactory) {
        this.scope = subcomposeMeasureScope;
        this.itemsProvider = lazyListItemsProvider;
        this.itemContentFactory = lazyListItemContentFactory;
        this.measuredItemFactory = measuredItemFactory;
        this.childConstraints = ConstraintsKt.Constraints$default(0, z ? Constraints.m2977getMaxWidthimpl(j) : Integer.MAX_VALUE, 0, z ? Integer.MAX_VALUE : Constraints.m2976getMaxHeightimpl(j), 5, null);
    }

    /* renamed from: getChildConstraints-msEJaDk, reason: not valid java name and from getter */
    public final long getChildConstraints() {
        return this.childConstraints;
    }

    /* renamed from: getAndMeasure-ZjPyQlc, reason: not valid java name */
    public final LazyMeasuredItem m398getAndMeasureZjPyQlc(int index) {
        Object key = this.itemsProvider.getKey(index);
        List<Measurable> subcompose = this.scope.subcompose(key, this.itemContentFactory.getContent(index, key));
        int size = subcompose.size();
        Placeable[] placeableArr = new Placeable[size];
        for (int i = 0; i < size; i++) {
            placeableArr[i] = subcompose.get(i).mo2510measureBRTryo0(getChildConstraints());
        }
        return this.measuredItemFactory.mo389createItemHK0c1C0(index, key, placeableArr);
    }
}
