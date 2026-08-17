package androidx.compose.foundation.text;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextLayoutHelper.kt */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001au\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0014\u0010\u001a\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006H\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001c"}, d2 = {"canReuse", "", "Landroidx/compose/ui/text/TextLayoutResult;", "text", "Landroidx/compose/ui/text/AnnotatedString;", "style", "Landroidx/compose/ui/text/TextStyle;", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "maxLines", "", "softWrap", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "resourceLoader", "Landroidx/compose/ui/text/font/Font$ResourceLoader;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "canReuse-7_7YC6M", "(Landroidx/compose/ui/text/TextLayoutResult;Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Ljava/util/List;IZILandroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/font/Font$ResourceLoader;J)Z", "canReuseLayout", Request.JsonKeys.OTHER, "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TextLayoutHelperKt {
    /* renamed from: canReuse-7_7YC6M, reason: not valid java name */
    public static final boolean m524canReuse7_7YC6M(TextLayoutResult canReuse, AnnotatedString text, TextStyle style, List<AnnotatedString.Range<Placeholder>> placeholders, int i, boolean z, int i2, Density density, LayoutDirection layoutDirection, Font.ResourceLoader resourceLoader, long j) {
        Intrinsics.checkNotNullParameter(canReuse, "$this$canReuse");
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(placeholders, "placeholders");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(resourceLoader, "resourceLoader");
        TextLayoutInput layoutInput = canReuse.getLayoutInput();
        if (Intrinsics.areEqual(layoutInput.getText(), text) && canReuseLayout(layoutInput.getStyle(), style) && Intrinsics.areEqual(layoutInput.getPlaceholders(), placeholders) && layoutInput.getMaxLines() == i && layoutInput.getSoftWrap() == z && TextOverflow.m2958equalsimpl0(layoutInput.getOverflow(), i2) && Intrinsics.areEqual(layoutInput.getDensity(), density) && layoutInput.getLayoutDirection() == layoutDirection && Intrinsics.areEqual(layoutInput.getResourceLoader(), resourceLoader) && Constraints.m2979getMinWidthimpl(j) == Constraints.m2979getMinWidthimpl(layoutInput.getConstraints())) {
            return !(z || TextOverflow.m2958equalsimpl0(i2, TextOverflow.INSTANCE.m2963getEllipsisgIe3tQ8())) || Constraints.m2977getMaxWidthimpl(j) == Constraints.m2977getMaxWidthimpl(layoutInput.getConstraints());
        }
        return false;
    }

    public static final boolean canReuseLayout(TextStyle textStyle, TextStyle other) {
        Intrinsics.checkNotNullParameter(textStyle, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return TextUnit.m3145equalsimpl0(textStyle.getFontSize(), other.getFontSize()) && Intrinsics.areEqual(textStyle.getFontWeight(), other.getFontWeight()) && Intrinsics.areEqual(textStyle.getFontStyle(), other.getFontStyle()) && Intrinsics.areEqual(textStyle.getFontSynthesis(), other.getFontSynthesis()) && Intrinsics.areEqual(textStyle.getFontFamily(), other.getFontFamily()) && Intrinsics.areEqual(textStyle.getFontFeatureSettings(), other.getFontFeatureSettings()) && TextUnit.m3145equalsimpl0(textStyle.getLetterSpacing(), other.getLetterSpacing()) && Intrinsics.areEqual(textStyle.getBaselineShift(), other.getBaselineShift()) && Intrinsics.areEqual(textStyle.getTextGeometricTransform(), other.getTextGeometricTransform()) && Intrinsics.areEqual(textStyle.getLocaleList(), other.getLocaleList()) && Color.m1255equalsimpl0(textStyle.getBackground(), other.getBackground()) && Intrinsics.areEqual(textStyle.getTextAlign(), other.getTextAlign()) && Intrinsics.areEqual(textStyle.getTextDirection(), other.getTextDirection()) && TextUnit.m3145equalsimpl0(textStyle.getLineHeight(), other.getLineHeight()) && Intrinsics.areEqual(textStyle.getTextIndent(), other.getTextIndent());
    }
}
