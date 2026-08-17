package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectionMode.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000bJ-\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\r\u0010\u000bj\u0002\b\u000ej\u0002\b\u000f\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/text/selection/SelectionMode;", "", "(Ljava/lang/String;I)V", "areHandlesCrossed", "", "bounds", "Landroidx/compose/ui/geometry/Rect;", "start", "Landroidx/compose/ui/geometry/Offset;", "end", "areHandlesCrossed-2x9bVx0$foundation_release", "(Landroidx/compose/ui/geometry/Rect;JJ)Z", "isSelected", "isSelected-2x9bVx0$foundation_release", "Vertical", "Horizontal", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public enum SelectionMode {
    Vertical { // from class: androidx.compose.foundation.text.selection.SelectionMode.Vertical
        @Override // androidx.compose.foundation.text.selection.SelectionMode
        /* renamed from: isSelected-2x9bVx0$foundation_release */
        public boolean mo577isSelected2x9bVx0$foundation_release(Rect bounds, long start, long end) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            if (Offset.m1026getYimpl(end) < bounds.getTop()) {
                return false;
            }
            if ((Offset.m1025getXimpl(end) >= bounds.getLeft() || Offset.m1026getYimpl(end) >= bounds.getBottom()) && Offset.m1026getYimpl(start) < bounds.getBottom()) {
                return Offset.m1025getXimpl(start) < bounds.getRight() || Offset.m1026getYimpl(start) < bounds.getTop();
            }
            return false;
        }

        @Override // androidx.compose.foundation.text.selection.SelectionMode
        /* renamed from: areHandlesCrossed-2x9bVx0$foundation_release */
        public boolean mo576areHandlesCrossed2x9bVx0$foundation_release(Rect bounds, long start, long end) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            if (Offset.m1026getYimpl(start) < bounds.getTop() || Offset.m1026getYimpl(start) >= bounds.getBottom() || Offset.m1026getYimpl(end) < bounds.getTop() || Offset.m1026getYimpl(end) >= bounds.getBottom()) {
                if (Offset.m1026getYimpl(start) > Offset.m1026getYimpl(end)) {
                    return true;
                }
            } else if (Offset.m1025getXimpl(start) > Offset.m1025getXimpl(end)) {
                return true;
            }
            return false;
        }
    },
    Horizontal { // from class: androidx.compose.foundation.text.selection.SelectionMode.Horizontal
        @Override // androidx.compose.foundation.text.selection.SelectionMode
        /* renamed from: isSelected-2x9bVx0$foundation_release */
        public boolean mo577isSelected2x9bVx0$foundation_release(Rect bounds, long start, long end) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            if (Offset.m1025getXimpl(end) < bounds.getLeft()) {
                return false;
            }
            if ((Offset.m1026getYimpl(end) >= bounds.getTop() || Offset.m1025getXimpl(end) >= bounds.getRight()) && Offset.m1025getXimpl(start) < bounds.getRight()) {
                return Offset.m1026getYimpl(start) < bounds.getBottom() || Offset.m1025getXimpl(start) < bounds.getLeft();
            }
            return false;
        }

        @Override // androidx.compose.foundation.text.selection.SelectionMode
        /* renamed from: areHandlesCrossed-2x9bVx0$foundation_release */
        public boolean mo576areHandlesCrossed2x9bVx0$foundation_release(Rect bounds, long start, long end) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            if (Offset.m1025getXimpl(start) < bounds.getLeft() || Offset.m1025getXimpl(start) >= bounds.getRight() || Offset.m1025getXimpl(end) < bounds.getLeft() || Offset.m1025getXimpl(end) >= bounds.getRight()) {
                if (Offset.m1025getXimpl(start) > Offset.m1025getXimpl(end)) {
                    return true;
                }
            } else if (Offset.m1026getYimpl(start) > Offset.m1026getYimpl(end)) {
                return true;
            }
            return false;
        }
    };

    /* synthetic */ SelectionMode(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* renamed from: areHandlesCrossed-2x9bVx0$foundation_release, reason: not valid java name */
    public abstract boolean mo576areHandlesCrossed2x9bVx0$foundation_release(Rect bounds, long start, long end);

    /* renamed from: isSelected-2x9bVx0$foundation_release, reason: not valid java name */
    public abstract boolean mo577isSelected2x9bVx0$foundation_release(Rect bounds, long start, long end);

    /* renamed from: values, reason: to resolve conflict with enum method */
    public static SelectionMode[] valuesCustom() {
        SelectionMode[] valuesCustom = values();
        return (SelectionMode[]) Arrays.copyOf(valuesCustom, valuesCustom.length);
    }
}
