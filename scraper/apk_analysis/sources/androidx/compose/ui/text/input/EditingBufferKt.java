package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: EditingBuffer.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", TypedValues.AttributesType.S_TARGET, "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class EditingBufferKt {
    /* renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m2823updateRangeAfterDeletepWDy79M(long j, long j2) {
        int m2762getLengthimpl;
        int m2764getMinimpl = TextRange.m2764getMinimpl(j);
        int m2763getMaximpl = TextRange.m2763getMaximpl(j);
        if (TextRange.m2768intersects5zctL8(j2, j)) {
            if (TextRange.m2756contains5zctL8(j2, j)) {
                m2764getMinimpl = TextRange.m2764getMinimpl(j2);
                m2763getMaximpl = m2764getMinimpl;
            } else {
                if (TextRange.m2756contains5zctL8(j, j2)) {
                    m2762getLengthimpl = TextRange.m2762getLengthimpl(j2);
                } else if (TextRange.m2757containsimpl(j2, m2764getMinimpl)) {
                    m2764getMinimpl = TextRange.m2764getMinimpl(j2);
                    m2762getLengthimpl = TextRange.m2762getLengthimpl(j2);
                } else {
                    m2763getMaximpl = TextRange.m2764getMinimpl(j2);
                }
                m2763getMaximpl -= m2762getLengthimpl;
            }
        } else if (m2763getMaximpl > TextRange.m2764getMinimpl(j2)) {
            m2764getMinimpl -= TextRange.m2762getLengthimpl(j2);
            m2762getLengthimpl = TextRange.m2762getLengthimpl(j2);
            m2763getMaximpl -= m2762getLengthimpl;
        }
        return TextRangeKt.TextRange(m2764getMinimpl, m2763getMaximpl);
    }
}
