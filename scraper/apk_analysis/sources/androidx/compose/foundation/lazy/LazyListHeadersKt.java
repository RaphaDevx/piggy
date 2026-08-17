package androidx.compose.foundation.lazy;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListHeaders.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001aF\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00052\u0006\u0010\n\u001a\u00020\tH\u0000¨\u0006\u000b"}, d2 = {"findOrComposeLazyListHeader", "Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "composedVisibleItems", "", "notUsedButComposedItems", "", "itemProvider", "Landroidx/compose/foundation/lazy/LazyMeasuredItemProvider;", "headerIndexes", "", "startContentPadding", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListHeadersKt {
    public static final LazyMeasuredItem findOrComposeLazyListHeader(List<LazyMeasuredItem> composedVisibleItems, List<LazyMeasuredItem> list, LazyMeasuredItemProvider itemProvider, List<Integer> headerIndexes, int i) {
        int i2;
        int i3;
        LazyMeasuredItem lazyMeasuredItem;
        Intrinsics.checkNotNullParameter(composedVisibleItems, "composedVisibleItems");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        Intrinsics.checkNotNullParameter(headerIndexes, "headerIndexes");
        int index = ((LazyMeasuredItem) CollectionsKt.first((List) composedVisibleItems)).getIndex();
        int size = headerIndexes.size() - 1;
        int i4 = -1;
        int i5 = -1;
        if (size >= 0) {
            int i6 = 0;
            while (true) {
                int i7 = i6 + 1;
                if (headerIndexes.get(i6).intValue() > index) {
                    break;
                }
                i4 = headerIndexes.get(i6).intValue();
                i5 = ((i7 < 0 || i7 > CollectionsKt.getLastIndex(headerIndexes)) ? -1 : headerIndexes.get(i7)).intValue();
                if (i7 > size) {
                    break;
                }
                i6 = i7;
            }
        }
        int size2 = composedVisibleItems.size() - 1;
        LazyMeasuredItem lazyMeasuredItem2 = null;
        LazyMeasuredItem lazyMeasuredItem3 = null;
        if (size2 >= 0) {
            int i8 = 0;
            i2 = Integer.MIN_VALUE;
            i3 = Integer.MIN_VALUE;
            while (true) {
                int i9 = i8 + 1;
                LazyMeasuredItem lazyMeasuredItem4 = composedVisibleItems.get(i8);
                if (lazyMeasuredItem4.getIndex() == i4) {
                    i2 = lazyMeasuredItem4.getOffset();
                    lazyMeasuredItem3 = lazyMeasuredItem4;
                } else if (lazyMeasuredItem4.getIndex() == i5) {
                    i3 = lazyMeasuredItem4.getOffset();
                }
                if (i9 > size2) {
                    break;
                }
                i8 = i9;
            }
        } else {
            i2 = Integer.MIN_VALUE;
            i3 = Integer.MIN_VALUE;
        }
        if (i4 == -1) {
            return null;
        }
        if (lazyMeasuredItem3 == null) {
            if (list != null) {
                int size3 = list.size() - 1;
                if (size3 >= 0) {
                    int i10 = 0;
                    while (true) {
                        int i11 = i10 + 1;
                        lazyMeasuredItem = list.get(i10);
                        if (lazyMeasuredItem.getIndex() == i4) {
                            break;
                        }
                        if (i11 > size3) {
                            break;
                        }
                        i10 = i11;
                    }
                }
                lazyMeasuredItem = null;
                LazyMeasuredItem lazyMeasuredItem5 = lazyMeasuredItem;
                if (lazyMeasuredItem5 != null) {
                    composedVisibleItems.add(0, lazyMeasuredItem5);
                    lazyMeasuredItem2 = lazyMeasuredItem5;
                }
            }
            if (lazyMeasuredItem2 == null) {
                lazyMeasuredItem3 = itemProvider.m398getAndMeasureZjPyQlc(DataIndex.m371constructorimpl(i4));
                composedVisibleItems.add(0, lazyMeasuredItem3);
            } else {
                lazyMeasuredItem3 = lazyMeasuredItem2;
            }
        }
        int max = i2 != Integer.MIN_VALUE ? Math.max(-i, i2) : -i;
        if (i3 != Integer.MIN_VALUE) {
            max = Math.min(max, i3 - lazyMeasuredItem3.getSize());
        }
        lazyMeasuredItem3.setOffset(max);
        return lazyMeasuredItem3;
    }
}
