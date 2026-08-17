package androidx.compose.foundation.lazy;

import androidx.compose.foundation.ExperimentalFoundationApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: LazyGrid.kt */
@ExperimentalFoundationApi
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/lazy/GridCells;", "", "()V", "Adaptive", "Fixed", "Landroidx/compose/foundation/lazy/GridCells$Fixed;", "Landroidx/compose/foundation/lazy/GridCells$Adaptive;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public abstract class GridCells {
    public static final int $stable = 0;

    public /* synthetic */ GridCells(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private GridCells() {
    }

    /* compiled from: LazyGrid.kt */
    @ExperimentalFoundationApi
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/foundation/lazy/GridCells$Fixed;", "Landroidx/compose/foundation/lazy/GridCells;", "count", "", "(I)V", "getCount", "()I", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Fixed extends GridCells {
        public static final int $stable = 0;
        private final int count;

        public Fixed(int i) {
            super(null);
            this.count = i;
        }

        public final int getCount() {
            return this.count;
        }
    }

    /* compiled from: LazyGrid.kt */
    @ExperimentalFoundationApi
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u001c\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/lazy/GridCells$Adaptive;", "Landroidx/compose/foundation/lazy/GridCells;", "minSize", "Landroidx/compose/ui/unit/Dp;", "(FLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getMinSize-D9Ej5fM", "()F", "F", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Adaptive extends GridCells {
        public static final int $stable = 0;
        private final float minSize;

        public /* synthetic */ Adaptive(float f, DefaultConstructorMarker defaultConstructorMarker) {
            this(f);
        }

        private Adaptive(float f) {
            super(null);
            this.minSize = f;
        }

        /* renamed from: getMinSize-D9Ej5fM, reason: not valid java name and from getter */
        public final float getMinSize() {
            return this.minSize;
        }
    }
}
