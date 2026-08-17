package androidx.compose.foundation.lazy;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyMeasuredItem.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u001e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0003R\u0011\u0010\u0016\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001fR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010 R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0018R\u0011\u0010#\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u000e\u0010\u0012\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "Landroidx/compose/foundation/lazy/LazyListItemInfo;", FirebaseAnalytics.Param.INDEX, "", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "isVertical", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "reverseLayout", "startContentPadding", "endContentPadding", "spacing", "key", "", "(I[Landroidx/compose/ui/layout/Placeable;ZLandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZIIILjava/lang/Object;)V", "crossAxisSize", "getCrossAxisSize", "()I", "getIndex", "getKey", "()Ljava/lang/Object;", TypedValues.CycleType.S_WAVE_OFFSET, "getOffset", "setOffset", "(I)V", "[Landroidx/compose/ui/layout/Placeable;", RRWebVideoEvent.JsonKeys.SIZE, "getSize", "sizeWithSpacings", "getSizeWithSpacings", "place", "", "scope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "layoutWidth", "layoutHeight", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class LazyMeasuredItem implements LazyListItemInfo {
    private final int crossAxisSize;
    private final int endContentPadding;
    private final Alignment.Horizontal horizontalAlignment;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final LayoutDirection layoutDirection;
    private int offset;
    private final Placeable[] placeables;
    private final boolean reverseLayout;
    private final int size;
    private final int sizeWithSpacings;
    private final int spacing;
    private final int startContentPadding;
    private final Alignment.Vertical verticalAlignment;

    public LazyMeasuredItem(int i, Placeable[] placeables, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z2, int i2, int i3, int i4, Object key) {
        Intrinsics.checkNotNullParameter(placeables, "placeables");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(key, "key");
        this.index = i;
        this.placeables = placeables;
        this.isVertical = z;
        this.horizontalAlignment = horizontal;
        this.verticalAlignment = vertical;
        this.layoutDirection = layoutDirection;
        this.reverseLayout = z2;
        this.startContentPadding = i2;
        this.endContentPadding = i3;
        this.spacing = i4;
        this.key = key;
        int i5 = 0;
        int i6 = 0;
        for (Placeable placeable : placeables) {
            i5 += this.isVertical ? placeable.getHeight() : placeable.getWidth();
            i6 = Math.max(i6, !this.isVertical ? placeable.getHeight() : placeable.getWidth());
        }
        this.size = i5;
        this.sizeWithSpacings = getSize() + this.spacing;
        this.crossAxisSize = i6;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public Object getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getSize() {
        return this.size;
    }

    public final int getSizeWithSpacings() {
        return this.sizeWithSpacings;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public final void place(Placeable.PlacementScope scope, int layoutWidth, int layoutHeight) {
        int offset;
        int width;
        Intrinsics.checkNotNullParameter(scope, "scope");
        int i = this.isVertical ? layoutHeight : layoutWidth;
        if (this.reverseLayout) {
            offset = (i - getOffset()) - getSize();
        } else {
            offset = getOffset();
        }
        int lastIndex = this.reverseLayout ? ArraysKt.getLastIndex(this.placeables) : 0;
        while (true) {
            boolean z = this.reverseLayout;
            if (z) {
                if (lastIndex < 0) {
                    return;
                }
            } else if (lastIndex >= this.placeables.length) {
                return;
            }
            Placeable placeable = this.placeables[lastIndex];
            lastIndex = z ? lastIndex - 1 : lastIndex + 1;
            if (this.isVertical) {
                Alignment.Horizontal horizontal = this.horizontalAlignment;
                if (horizontal == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                int align = horizontal.align(placeable.getWidth(), layoutWidth, this.layoutDirection);
                if (placeable.getHeight() + offset > (-this.startContentPadding) && offset < this.endContentPadding + layoutHeight) {
                    Placeable.PlacementScope.placeWithLayer$default(scope, placeable, align, offset, 0.0f, null, 12, null);
                }
                width = placeable.getHeight();
            } else {
                Alignment.Vertical vertical = this.verticalAlignment;
                if (vertical == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                int align2 = vertical.align(placeable.getHeight(), layoutHeight);
                if (placeable.getWidth() + offset > (-this.startContentPadding) && offset < this.endContentPadding + layoutWidth) {
                    Placeable.PlacementScope.placeRelativeWithLayer$default(scope, placeable, offset, align2, 0.0f, null, 12, null);
                }
                width = placeable.getWidth();
            }
            offset += width;
        }
    }
}
