package androidx.compose.foundation.text;

import androidx.compose.ui.text.TextRangeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StringHelpers.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\u001a\u001c\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"findParagraphEnd", "", "", "startIndex", "findParagraphStart", "getParagraphBoundary", "Landroidx/compose/ui/text/TextRange;", FirebaseAnalytics.Param.INDEX, "(Ljava/lang/String;I)J", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class StringHelpersKt {
    public static final int findParagraphStart(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int i2 = i - 1;
        if (1 > i2) {
            return 0;
        }
        while (true) {
            int i3 = i2 - 1;
            if (str.charAt(i2 - 1) == '\n') {
                return i2;
            }
            if (1 > i3) {
                return 0;
            }
            i2 = i3;
        }
    }

    public static final int findParagraphEnd(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int i2 = i + 1;
        int length = str.length();
        if (i2 < length) {
            while (true) {
                int i3 = i2 + 1;
                if (str.charAt(i2) == '\n') {
                    return i2;
                }
                if (i3 >= length) {
                    break;
                }
                i2 = i3;
            }
        }
        return str.length();
    }

    public static final long getParagraphBoundary(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return TextRangeKt.TextRange(findParagraphStart(str, i), findParagraphEnd(str, i));
    }
}
