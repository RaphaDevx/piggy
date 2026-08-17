package androidx.compose.foundation.lazy;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;

/* compiled from: LazyListMeasure.kt */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001ab\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a\u009f\u0001\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\t\u001a\u00020\n2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006&"}, d2 = {"calculateItemsOffsets", "", "items", "", "Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "mainAxisLayoutSize", "", "usedMainAxisSize", "itemsScrollOffset", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "measureLazyList", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "itemsCount", "itemProvider", "Landroidx/compose/foundation/lazy/LazyMeasuredItemProvider;", "mainAxisMaxSize", "startContentPadding", "endContentPadding", "firstVisibleItemIndex", "Landroidx/compose/foundation/lazy/DataIndex;", "firstVisibleItemScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "headerIndexes", "measureLazyList-9CW8viI", "(ILandroidx/compose/foundation/lazy/LazyMeasuredItemProvider;IIIIIFJZLjava/util/List;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListMeasureKt {
    /* JADX WARN: Removed duplicated region for block: B:102:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x022a  */
    /* renamed from: measureLazyList-9CW8viI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.foundation.lazy.LazyListMeasureResult m390measureLazyList9CW8viI(int r25, androidx.compose.foundation.lazy.LazyMeasuredItemProvider r26, int r27, int r28, int r29, int r30, int r31, float r32, long r33, boolean r35, java.util.List<java.lang.Integer> r36, androidx.compose.foundation.layout.Arrangement.Vertical r37, androidx.compose.foundation.layout.Arrangement.Horizontal r38, boolean r39, androidx.compose.ui.unit.Density r40, androidx.compose.ui.unit.LayoutDirection r41) {
        /*
            Method dump skipped, instructions count: 646
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListMeasureKt.m390measureLazyList9CW8viI(int, androidx.compose.foundation.lazy.LazyMeasuredItemProvider, int, int, int, int, int, float, long, boolean, java.util.List, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, boolean, androidx.compose.ui.unit.Density, androidx.compose.ui.unit.LayoutDirection):androidx.compose.foundation.lazy.LazyListMeasureResult");
    }

    private static final void calculateItemsOffsets(List<LazyMeasuredItem> list, int i, int i2, int i3, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LayoutDirection layoutDirection) {
        int i4 = 0;
        boolean z3 = i2 < i;
        if (z3) {
            if (!(i3 == 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        if (z3) {
            int size = list.size();
            int[] iArr = new int[size];
            for (int i5 = 0; i5 < size; i5++) {
                iArr[i5] = list.get(!z2 ? i5 : (size - i5) - 1).getSize();
            }
            int[] iArr2 = new int[size];
            for (int i6 = 0; i6 < size; i6++) {
                iArr2[i6] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                vertical.arrange(density, i, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                horizontal.arrange(density, i, iArr, layoutDirection, iArr2);
            }
            int i7 = 0;
            while (i4 < size) {
                int i8 = iArr2[i4];
                int i9 = i7 + 1;
                if (z2) {
                    i7 = (size - i7) - 1;
                }
                LazyMeasuredItem lazyMeasuredItem = list.get(i7);
                if (z2) {
                    i8 = (i - i8) - lazyMeasuredItem.getSize();
                }
                lazyMeasuredItem.setOffset(i8);
                i4++;
                i7 = i9;
            }
            return;
        }
        int size2 = list.size() - 1;
        if (size2 < 0) {
            return;
        }
        int i10 = i3;
        while (true) {
            int i11 = i4 + 1;
            LazyMeasuredItem lazyMeasuredItem2 = list.get(i4);
            lazyMeasuredItem2.setOffset(i10);
            i10 += lazyMeasuredItem2.getSizeWithSpacings();
            if (i11 > size2) {
                return;
            } else {
                i4 = i11;
            }
        }
    }
}
