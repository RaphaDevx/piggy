package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.AnnotatedString;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: Selectable.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J(\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&J\n\u0010\u0014\u001a\u0004\u0018\u00010\rH&JM\u0010\u0015\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH&ø\u0001\u0000ø\u0001\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u001fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006 "}, d2 = {"Landroidx/compose/foundation/text/selection/Selectable;", "", "selectableId", "", "getSelectableId", "()J", "getBoundingBox", "Landroidx/compose/ui/geometry/Rect;", TypedValues.CycleType.S_WAVE_OFFSET, "", "getHandlePosition", "Landroidx/compose/ui/geometry/Offset;", "selection", "Landroidx/compose/foundation/text/selection/Selection;", "isStartHandle", "", "getHandlePosition-dBAh8RU", "(Landroidx/compose/foundation/text/selection/Selection;Z)J", "getLayoutCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getSelectAllSelection", "getSelection", "startPosition", "endPosition", "containerLayoutCoordinates", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "previousSelection", "getSelection-Xn-YAUg", "(JJLandroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/foundation/text/selection/SelectionAdjustment;Landroidx/compose/foundation/text/selection/Selection;Z)Landroidx/compose/foundation/text/selection/Selection;", "getText", "Landroidx/compose/ui/text/AnnotatedString;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public interface Selectable {
    Rect getBoundingBox(int offset);

    /* renamed from: getHandlePosition-dBAh8RU */
    long mo544getHandlePositiondBAh8RU(Selection selection, boolean isStartHandle);

    LayoutCoordinates getLayoutCoordinates();

    Selection getSelectAllSelection();

    long getSelectableId();

    /* renamed from: getSelection-Xn-YAUg */
    Selection mo545getSelectionXnYAUg(long startPosition, long endPosition, LayoutCoordinates containerLayoutCoordinates, SelectionAdjustment adjustment, Selection previousSelection, boolean isStartHandle);

    AnnotatedString getText();

    /* compiled from: Selectable.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class DefaultImpls {
        /* renamed from: getSelection-Xn-YAUg$default, reason: not valid java name */
        public static /* synthetic */ Selection m547getSelectionXnYAUg$default(Selectable selectable, long j, long j2, LayoutCoordinates layoutCoordinates, SelectionAdjustment selectionAdjustment, Selection selection, boolean z, int i, Object obj) {
            if (obj == null) {
                return selectable.mo545getSelectionXnYAUg(j, j2, layoutCoordinates, selectionAdjustment, (i & 16) != 0 ? null : selection, (i & 32) != 0 ? true : z);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSelection-Xn-YAUg");
        }
    }
}
