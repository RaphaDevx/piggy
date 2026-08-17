package androidx.compose.foundation.text;

import androidx.compose.ui.text.Paragraph;
import androidx.compose.ui.text.ParagraphKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;

/* compiled from: TextFieldDelegate.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001a<\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a\f\u0010\u0011\u001a\u00020\u0001*\u00020\u0012H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"DefaultWidthCharCount", "", "EmptyTextReplacement", "", "getEmptyTextReplacement", "()Ljava/lang/String;", "computeSizeForDefaultText", "Landroidx/compose/ui/unit/IntSize;", "style", "Landroidx/compose/ui/text/TextStyle;", "density", "Landroidx/compose/ui/unit/Density;", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "text", "maxLines", "(Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/Font$ResourceLoader;Ljava/lang/String;I)J", "toIntPx", "", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextFieldDelegateKt {
    public static final int DefaultWidthCharCount = 10;
    private static final String EmptyTextReplacement = StringsKt.repeat("H", 10);

    public static final String getEmptyTextReplacement() {
        return EmptyTextReplacement;
    }

    public static /* synthetic */ long computeSizeForDefaultText$default(TextStyle textStyle, Density density, Font.ResourceLoader resourceLoader, String str, int i, int i2, Object obj) {
        if ((i2 & 8) != 0) {
            str = EmptyTextReplacement;
        }
        if ((i2 & 16) != 0) {
            i = 1;
        }
        return computeSizeForDefaultText(textStyle, density, resourceLoader, str, i);
    }

    public static final long computeSizeForDefaultText(TextStyle style, Density density, Font.ResourceLoader resourceLoader, String text, int i) {
        Paragraph Paragraph;
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(resourceLoader, "resourceLoader");
        Intrinsics.checkNotNullParameter(text, "text");
        Paragraph = ParagraphKt.Paragraph(text, style, (r19 & 4) != 0 ? CollectionsKt.emptyList() : CollectionsKt.emptyList(), (r19 & 8) != 0 ? CollectionsKt.emptyList() : null, (r19 & 16) != 0 ? Integer.MAX_VALUE : i, (r19 & 32) != 0 ? false : false, Float.POSITIVE_INFINITY, density, resourceLoader);
        return IntSizeKt.IntSize(toIntPx(Paragraph.getMinIntrinsicWidth()), toIntPx(Paragraph.getHeight()));
    }

    private static final int toIntPx(float f) {
        return MathKt.roundToInt((float) Math.ceil(f));
    }
}
