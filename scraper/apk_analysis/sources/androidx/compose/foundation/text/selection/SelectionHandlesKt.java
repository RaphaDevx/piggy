package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: SelectionHandles.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001d\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000b\"\u0019\u0010\u0000\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0019\u0010\u0005\u001a\u00020\u0001X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0006\u0010\u0003\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\f"}, d2 = {"HANDLE_HEIGHT", "Landroidx/compose/ui/unit/Dp;", "getHANDLE_HEIGHT", "()F", "F", "HANDLE_WIDTH", "getHANDLE_WIDTH", "getAdjustedCoordinates", "Landroidx/compose/ui/geometry/Offset;", "position", "getAdjustedCoordinates-k-4lQ0M", "(J)J", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SelectionHandlesKt {
    private static final float HANDLE_HEIGHT;
    private static final float HANDLE_WIDTH;

    public static final float getHANDLE_WIDTH() {
        return HANDLE_WIDTH;
    }

    public static final float getHANDLE_HEIGHT() {
        return HANDLE_HEIGHT;
    }

    /* renamed from: getAdjustedCoordinates-k-4lQ0M, reason: not valid java name */
    public static final long m554getAdjustedCoordinatesk4lQ0M(long j) {
        return OffsetKt.Offset(Offset.m1025getXimpl(j), Offset.m1026getYimpl(j) - 1.0f);
    }

    static {
        float f = 25;
        HANDLE_WIDTH = Dp.m3007constructorimpl(f);
        HANDLE_HEIGHT = Dp.m3007constructorimpl(f);
    }
}
