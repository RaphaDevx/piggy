package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: MultiWidgetSelectionDelegate.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J(\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0013H\u0016JI\u0010\u001a\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016ø\u0001\u0000ø\u0001\u0002¢\u0006\u0004\b!\u0010\"J\b\u0010#\u001a\u00020$H\u0016R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006%"}, d2 = {"Landroidx/compose/foundation/text/selection/MultiWidgetSelectionDelegate;", "Landroidx/compose/foundation/text/selection/Selectable;", "selectableId", "", "coordinatesCallback", "Lkotlin/Function0;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "layoutResultCallback", "Landroidx/compose/ui/text/TextLayoutResult;", "(JLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "getSelectableId", "()J", "getBoundingBox", "Landroidx/compose/ui/geometry/Rect;", TypedValues.CycleType.S_WAVE_OFFSET, "", "getHandlePosition", "Landroidx/compose/ui/geometry/Offset;", "selection", "Landroidx/compose/foundation/text/selection/Selection;", "isStartHandle", "", "getHandlePosition-dBAh8RU", "(Landroidx/compose/foundation/text/selection/Selection;Z)J", "getLayoutCoordinates", "getSelectAllSelection", "getSelection", "startPosition", "endPosition", "containerLayoutCoordinates", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "previousSelection", "getSelection-Xn-YAUg", "(JJLandroidx/compose/ui/layout/LayoutCoordinates;Landroidx/compose/foundation/text/selection/SelectionAdjustment;Landroidx/compose/foundation/text/selection/Selection;Z)Landroidx/compose/foundation/text/selection/Selection;", "getText", "Landroidx/compose/ui/text/AnnotatedString;", "foundation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MultiWidgetSelectionDelegate implements Selectable {
    private final Function0<LayoutCoordinates> coordinatesCallback;
    private final Function0<TextLayoutResult> layoutResultCallback;
    private final long selectableId;

    /* JADX WARN: Multi-variable type inference failed */
    public MultiWidgetSelectionDelegate(long j, Function0<? extends LayoutCoordinates> coordinatesCallback, Function0<TextLayoutResult> layoutResultCallback) {
        Intrinsics.checkNotNullParameter(coordinatesCallback, "coordinatesCallback");
        Intrinsics.checkNotNullParameter(layoutResultCallback, "layoutResultCallback");
        this.selectableId = j;
        this.coordinatesCallback = coordinatesCallback;
        this.layoutResultCallback = layoutResultCallback;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public long getSelectableId() {
        return this.selectableId;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    /* renamed from: getSelection-Xn-YAUg, reason: not valid java name */
    public Selection mo545getSelectionXnYAUg(long startPosition, long endPosition, LayoutCoordinates containerLayoutCoordinates, SelectionAdjustment adjustment, Selection previousSelection, boolean isStartHandle) {
        TextLayoutResult invoke;
        Intrinsics.checkNotNullParameter(containerLayoutCoordinates, "containerLayoutCoordinates");
        Intrinsics.checkNotNullParameter(adjustment, "adjustment");
        LayoutCoordinates layoutCoordinates = getLayoutCoordinates();
        if (layoutCoordinates == null || (invoke = this.layoutResultCallback.invoke()) == null) {
            return null;
        }
        long mo2523localPositionOfR5De75A = containerLayoutCoordinates.mo2523localPositionOfR5De75A(layoutCoordinates, Offset.INSTANCE.m1041getZeroF1C5BW0());
        return MultiWidgetSelectionDelegateKt.getTextSelectionInfo(invoke, new Pair(Offset.m1014boximpl(Offset.m1029minusMKHz9U(startPosition, mo2523localPositionOfR5De75A)), Offset.m1014boximpl(Offset.m1029minusMKHz9U(endPosition, mo2523localPositionOfR5De75A))), getSelectableId(), adjustment, previousSelection, isStartHandle);
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public Selection getSelectAllSelection() {
        Selection assembledSelectionInfo;
        TextLayoutResult invoke = this.layoutResultCallback.invoke();
        if (invoke == null) {
            return null;
        }
        assembledSelectionInfo = MultiWidgetSelectionDelegateKt.getAssembledSelectionInfo(0, invoke.getLayoutInput().getText().length(), false, getSelectableId(), invoke);
        return assembledSelectionInfo;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    /* renamed from: getHandlePosition-dBAh8RU, reason: not valid java name */
    public long mo544getHandlePositiondBAh8RU(Selection selection, boolean isStartHandle) {
        TextLayoutResult invoke;
        Intrinsics.checkNotNullParameter(selection, "selection");
        if ((isStartHandle && selection.getStart().getSelectableId() != getSelectableId()) || (!isStartHandle && selection.getEnd().getSelectableId() != getSelectableId())) {
            return Offset.INSTANCE.m1041getZeroF1C5BW0();
        }
        if (getLayoutCoordinates() != null && (invoke = this.layoutResultCallback.invoke()) != null) {
            return TextSelectionDelegateKt.getSelectionHandleCoordinates(invoke, (isStartHandle ? selection.getStart() : selection.getEnd()).getOffset(), isStartHandle, selection.getHandlesCrossed());
        }
        return Offset.INSTANCE.m1041getZeroF1C5BW0();
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public LayoutCoordinates getLayoutCoordinates() {
        LayoutCoordinates invoke = this.coordinatesCallback.invoke();
        if (invoke == null || !invoke.isAttached()) {
            return null;
        }
        return invoke;
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public AnnotatedString getText() {
        TextLayoutResult invoke = this.layoutResultCallback.invoke();
        return invoke == null ? new AnnotatedString("", null, null, 6, null) : invoke.getLayoutInput().getText();
    }

    @Override // androidx.compose.foundation.text.selection.Selectable
    public Rect getBoundingBox(int offset) {
        TextLayoutResult invoke = this.layoutResultCallback.invoke();
        return invoke == null ? Rect.INSTANCE.getZero() : invoke.getBoundingBox(RangesKt.coerceIn(offset, 0, invoke.getLayoutInput().getText().getText().length() - 1));
    }
}
