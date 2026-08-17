package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.selection.Selection;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.compose.ui.unit.IntSize;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: MultiWidgetSelectionDelegate.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001aG\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001aO\u0010\u0017\u001a\u0004\u0018\u00010\u00012\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u00192\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u001d\u001a\u00020\u0006H\u0000ø\u0001\u0000\u001a)\u0010\u001e\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u0019H\u0000ø\u0001\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"getAssembledSelectionInfo", "Landroidx/compose/foundation/text/selection/Selection;", "startOffset", "", "endOffset", "handlesCrossed", "", "selectableId", "", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "getRefinedSelectionRange", "Landroidx/compose/ui/text/TextRange;", "rawStartOffset", "rawEndOffset", "startPosition", "Landroidx/compose/ui/geometry/Offset;", "endPosition", "bounds", "Landroidx/compose/ui/geometry/Rect;", "lastOffset", "getRefinedSelectionRange-pGV3PM0", "(IIJJLandroidx/compose/ui/geometry/Rect;I)Landroidx/compose/ui/text/TextRange;", "getTextSelectionInfo", "selectionCoordinates", "Lkotlin/Pair;", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "previousSelection", "isStartHandle", "getTextSelectionRange", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MultiWidgetSelectionDelegateKt {
    public static /* synthetic */ Selection getTextSelectionInfo$default(TextLayoutResult textLayoutResult, Pair pair, long j, SelectionAdjustment selectionAdjustment, Selection selection, boolean z, int i, Object obj) {
        if ((i & 16) != 0) {
            selection = null;
        }
        Selection selection2 = selection;
        if ((i & 32) != 0) {
            z = true;
        }
        return getTextSelectionInfo(textLayoutResult, pair, j, selectionAdjustment, selection2, z);
    }

    public static final Selection getTextSelectionInfo(TextLayoutResult textLayoutResult, Pair<Offset, Offset> selectionCoordinates, long j, SelectionAdjustment adjustment, Selection selection, boolean z) {
        Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
        Intrinsics.checkNotNullParameter(selectionCoordinates, "selectionCoordinates");
        Intrinsics.checkNotNullParameter(adjustment, "adjustment");
        TextRange textSelectionRange = getTextSelectionRange(textLayoutResult, selectionCoordinates);
        if (textSelectionRange == null) {
            return null;
        }
        long m589adjustSelectionLepunE = TextSelectionDelegateKt.m589adjustSelectionLepunE(textLayoutResult, textSelectionRange.getPackedValue(), z, selection == null ? false : selection.getHandlesCrossed(), adjustment);
        return getAssembledSelectionInfo(TextRange.m2766getStartimpl(m589adjustSelectionLepunE), TextRange.m2761getEndimpl(m589adjustSelectionLepunE), TextRange.m2765getReversedimpl(m589adjustSelectionLepunE), j, textLayoutResult);
    }

    public static final TextRange getTextSelectionRange(TextLayoutResult textLayoutResult, Pair<Offset, Offset> selectionCoordinates) {
        Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
        Intrinsics.checkNotNullParameter(selectionCoordinates, "selectionCoordinates");
        long packedValue = selectionCoordinates.getFirst().getPackedValue();
        long packedValue2 = selectionCoordinates.getSecond().getPackedValue();
        Rect rect = new Rect(0.0f, 0.0f, IntSize.m3127getWidthimpl(textLayoutResult.getSize()), IntSize.m3126getHeightimpl(textLayoutResult.getSize()));
        int length = textLayoutResult.getLayoutInput().getText().getText().length();
        return m546getRefinedSelectionRangepGV3PM0(rect.m1051containsk4lQ0M(OffsetKt.Offset(Offset.m1025getXimpl(packedValue), Offset.m1026getYimpl(packedValue))) ? RangesKt.coerceIn(textLayoutResult.m2749getOffsetForPositionk4lQ0M(packedValue), 0, length) : -1, rect.m1051containsk4lQ0M(OffsetKt.Offset(Offset.m1025getXimpl(packedValue2), Offset.m1026getYimpl(packedValue2))) ? RangesKt.coerceIn(textLayoutResult.m2749getOffsetForPositionk4lQ0M(packedValue2), 0, length) : -1, packedValue, packedValue2, rect, length);
    }

    /* renamed from: getRefinedSelectionRange-pGV3PM0, reason: not valid java name */
    private static final TextRange m546getRefinedSelectionRangepGV3PM0(int i, int i2, long j, long j2, Rect rect, int i3) {
        boolean z = i >= 0;
        boolean z2 = i2 >= 0;
        if (z && z2) {
            return TextRange.m2754boximpl(TextRangeKt.TextRange(i, i2));
        }
        return TextSelectionDelegateKt.m590processCrossComposablexrVmA(j, j2, i, i2, i3, rect, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Selection getAssembledSelectionInfo(int i, int i2, boolean z, long j, TextLayoutResult textLayoutResult) {
        return new Selection(new Selection.AnchorInfo(textLayoutResult.getBidiRunDirection(i), i, j), new Selection.AnchorInfo(textLayoutResult.getBidiRunDirection(Math.max(i2 - 1, 0)), i2, j), z);
    }
}
