package androidx.compose.foundation.text.selection;

import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KAnnotatedElement;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;

/* compiled from: TextSelectionDelegate.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a=\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\n\u0010\u000b\u001a0\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a0\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aW\u0010\u0016\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0006H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 \u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"adjustSelection", "Landroidx/compose/ui/text/TextRange;", "textLayoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "textRange", "isStartHandle", "", "previousHandlesCrossed", "adjustment", "Landroidx/compose/foundation/text/selection/SelectionAdjustment;", "adjustSelection-Le-punE", "(Landroidx/compose/ui/text/TextLayoutResult;JZZLandroidx/compose/foundation/text/selection/SelectionAdjustment;)J", "ensureAtLeastOneChar", TypedValues.CycleType.S_WAVE_OFFSET, "", "lastOffset", "(IIZZ)J", "getSelectionHandleCoordinates", "Landroidx/compose/ui/geometry/Offset;", "isStart", "areHandlesCrossed", "(Landroidx/compose/ui/text/TextLayoutResult;IZZ)J", "processCrossComposable", "startPosition", "endPosition", "rawStartOffset", "rawEndOffset", "bounds", "Landroidx/compose/ui/geometry/Rect;", "containsWholeSelectionStart", "containsWholeSelectionEnd", "processCrossComposable-xrV--mA", "(JJIIILandroidx/compose/ui/geometry/Rect;ZZ)Landroidx/compose/ui/text/TextRange;", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextSelectionDelegateKt {
    /* renamed from: processCrossComposable-xrV--mA, reason: not valid java name */
    public static final TextRange m590processCrossComposablexrVmA(long j, long j2, int i, int i2, int i3, Rect bounds, boolean z, boolean z2) {
        int i4;
        int i5;
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        boolean mo576areHandlesCrossed2x9bVx0$foundation_release = SelectionMode.Vertical.mo576areHandlesCrossed2x9bVx0$foundation_release(bounds, j, j2);
        boolean mo577isSelected2x9bVx0$foundation_release = SelectionMode.Vertical.mo577isSelected2x9bVx0$foundation_release(bounds, mo576areHandlesCrossed2x9bVx0$foundation_release ? j2 : j, mo576areHandlesCrossed2x9bVx0$foundation_release ? j : j2);
        if (!mo577isSelected2x9bVx0$foundation_release || z) {
            i4 = i;
        } else {
            i4 = mo576areHandlesCrossed2x9bVx0$foundation_release ? Math.max(i3, 0) : 0;
        }
        if (!mo577isSelected2x9bVx0$foundation_release || z2) {
            i5 = i2;
        } else {
            i5 = mo576areHandlesCrossed2x9bVx0$foundation_release ? 0 : Math.max(i3, 0);
        }
        if (i4 == -1 || i5 == -1) {
            return null;
        }
        return TextRange.m2754boximpl(TextRangeKt.TextRange(i4, i5));
    }

    /* renamed from: adjustSelection-Le-punE, reason: not valid java name */
    public static final long m589adjustSelectionLepunE(TextLayoutResult textLayoutResult, long j, boolean z, boolean z2, SelectionAdjustment adjustment) {
        KAnnotatedElement kAnnotatedElement;
        Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
        Intrinsics.checkNotNullParameter(adjustment, "adjustment");
        int length = textLayoutResult.getLayoutInput().getText().getText().length();
        if (adjustment == SelectionAdjustment.NONE || length == 0) {
            return j;
        }
        if (adjustment == SelectionAdjustment.CHARACTER) {
            return !TextRange.m2760getCollapsedimpl(j) ? j : ensureAtLeastOneChar(TextRange.m2766getStartimpl(j), StringsKt.getLastIndex(textLayoutResult.getLayoutInput().getText()), z, z2);
        }
        if (adjustment == SelectionAdjustment.WORD) {
            kAnnotatedElement = (KFunction) new TextSelectionDelegateKt$adjustSelection$boundaryFun$1(textLayoutResult);
        } else {
            kAnnotatedElement = (KFunction) new TextSelectionDelegateKt$adjustSelection$boundaryFun$2(textLayoutResult.getLayoutInput().getText().getText());
        }
        int i = length - 1;
        Function1 function1 = (Function1) kAnnotatedElement;
        long packedValue = ((TextRange) function1.invoke(Integer.valueOf(RangesKt.coerceIn(TextRange.m2766getStartimpl(j), 0, i)))).getPackedValue();
        long packedValue2 = ((TextRange) function1.invoke(Integer.valueOf(RangesKt.coerceIn(TextRange.m2761getEndimpl(j), 0, i)))).getPackedValue();
        return TextRangeKt.TextRange(TextRange.m2765getReversedimpl(j) ? TextRange.m2761getEndimpl(packedValue) : TextRange.m2766getStartimpl(packedValue), TextRange.m2765getReversedimpl(j) ? TextRange.m2766getStartimpl(packedValue2) : TextRange.m2761getEndimpl(packedValue2));
    }

    private static final long ensureAtLeastOneChar(int i, int i2, boolean z, boolean z2) {
        if (i2 == 0) {
            return TextRangeKt.TextRange(i, i);
        }
        if (i == 0) {
            if (z) {
                return TextRangeKt.TextRange(1, 0);
            }
            return TextRangeKt.TextRange(0, 1);
        }
        if (i == i2) {
            if (z) {
                return TextRangeKt.TextRange(i2 - 1, i2);
            }
            return TextRangeKt.TextRange(i2, i2 - 1);
        }
        if (z) {
            if (!z2) {
                return TextRangeKt.TextRange(i - 1, i);
            }
            return TextRangeKt.TextRange(i + 1, i);
        }
        if (!z2) {
            return TextRangeKt.TextRange(i, i + 1);
        }
        return TextRangeKt.TextRange(i, i - 1);
    }

    public static final long getSelectionHandleCoordinates(TextLayoutResult textLayoutResult, int i, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(textLayoutResult, "textLayoutResult");
        return OffsetKt.Offset(textLayoutResult.getHorizontalPosition(i, textLayoutResult.getBidiRunDirection(((!z || z2) && (z || !z2)) ? Math.max(i + (-1), 0) : i) == textLayoutResult.getParagraphDirection(i)), textLayoutResult.getLineBottom(textLayoutResult.getLineForOffset(i)));
    }
}
