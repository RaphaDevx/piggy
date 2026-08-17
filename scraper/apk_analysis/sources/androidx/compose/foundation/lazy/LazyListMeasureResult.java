package androidx.compose.foundation.lazy;

import androidx.compose.ui.layout.Placeable;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListMeasureResult.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0000\u0018\u00002\u00020\u0001B\u0086\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000b\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0005¢\u0006\u0002\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\"\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010\u0017\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\"R\u0014\u0010\u0016\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0014\u0010\u0015\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\"R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001c¨\u0006+"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "Landroidx/compose/foundation/lazy/LazyListLayoutInfo;", "firstVisibleItem", "Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "firstVisibleItemScrollOffset", "", "canScrollForward", "", "consumedScroll", "", "composedButNotVisibleItems", "", "layoutWidth", "layoutHeight", "placementBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "visibleItemsInfo", "Landroidx/compose/foundation/lazy/LazyListItemInfo;", "viewportStartOffset", "viewportEndOffset", "totalItemsCount", "(Landroidx/compose/foundation/lazy/LazyMeasuredItem;IZFLjava/util/List;IILkotlin/jvm/functions/Function1;Ljava/util/List;III)V", "getCanScrollForward", "()Z", "getComposedButNotVisibleItems", "()Ljava/util/List;", "getConsumedScroll", "()F", "getFirstVisibleItem", "()Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "getFirstVisibleItemScrollOffset", "()I", "getLayoutHeight", "getLayoutWidth", "getPlacementBlock", "()Lkotlin/jvm/functions/Function1;", "getTotalItemsCount", "getViewportEndOffset", "getViewportStartOffset", "getVisibleItemsInfo", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyListMeasureResult implements LazyListLayoutInfo {
    private final boolean canScrollForward;
    private final List<LazyMeasuredItem> composedButNotVisibleItems;
    private final float consumedScroll;
    private final LazyMeasuredItem firstVisibleItem;
    private final int firstVisibleItemScrollOffset;
    private final int layoutHeight;
    private final int layoutWidth;
    private final Function1<Placeable.PlacementScope, Unit> placementBlock;
    private final int totalItemsCount;
    private final int viewportEndOffset;
    private final int viewportStartOffset;
    private final List<LazyListItemInfo> visibleItemsInfo;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyListMeasureResult(LazyMeasuredItem lazyMeasuredItem, int i, boolean z, float f, List<LazyMeasuredItem> list, int i2, int i3, Function1<? super Placeable.PlacementScope, Unit> placementBlock, List<? extends LazyListItemInfo> visibleItemsInfo, int i4, int i5, int i6) {
        Intrinsics.checkNotNullParameter(placementBlock, "placementBlock");
        Intrinsics.checkNotNullParameter(visibleItemsInfo, "visibleItemsInfo");
        this.firstVisibleItem = lazyMeasuredItem;
        this.firstVisibleItemScrollOffset = i;
        this.canScrollForward = z;
        this.consumedScroll = f;
        this.composedButNotVisibleItems = list;
        this.layoutWidth = i2;
        this.layoutHeight = i3;
        this.placementBlock = placementBlock;
        this.visibleItemsInfo = visibleItemsInfo;
        this.viewportStartOffset = i4;
        this.viewportEndOffset = i5;
        this.totalItemsCount = i6;
    }

    public final LazyMeasuredItem getFirstVisibleItem() {
        return this.firstVisibleItem;
    }

    public final int getFirstVisibleItemScrollOffset() {
        return this.firstVisibleItemScrollOffset;
    }

    public final boolean getCanScrollForward() {
        return this.canScrollForward;
    }

    public final float getConsumedScroll() {
        return this.consumedScroll;
    }

    public final List<LazyMeasuredItem> getComposedButNotVisibleItems() {
        return this.composedButNotVisibleItems;
    }

    public final int getLayoutWidth() {
        return this.layoutWidth;
    }

    public final int getLayoutHeight() {
        return this.layoutHeight;
    }

    public final Function1<Placeable.PlacementScope, Unit> getPlacementBlock() {
        return this.placementBlock;
    }

    @Override // androidx.compose.foundation.lazy.LazyListLayoutInfo
    public List<LazyListItemInfo> getVisibleItemsInfo() {
        return this.visibleItemsInfo;
    }

    @Override // androidx.compose.foundation.lazy.LazyListLayoutInfo
    public int getViewportStartOffset() {
        return this.viewportStartOffset;
    }

    @Override // androidx.compose.foundation.lazy.LazyListLayoutInfo
    public int getViewportEndOffset() {
        return this.viewportEndOffset;
    }

    @Override // androidx.compose.foundation.lazy.LazyListLayoutInfo
    public int getTotalItemsCount() {
        return this.totalItemsCount;
    }
}
