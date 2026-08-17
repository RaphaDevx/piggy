package androidx.compose.material;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: Typography.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\t"}, d2 = {"LocalTypography", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/Typography;", "getLocalTypography", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "withDefaultFontFamily", "Landroidx/compose/ui/text/TextStyle;", "default", "Landroidx/compose/ui/text/font/FontFamily;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TypographyKt {
    private static final ProvidableCompositionLocal<Typography> LocalTypography = CompositionLocalKt.staticCompositionLocalOf(new Function0<Typography>() { // from class: androidx.compose.material.TypographyKt$LocalTypography$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Typography invoke() {
            return new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextStyle withDefaultFontFamily(TextStyle textStyle, FontFamily fontFamily) {
        TextStyle m2775copyHL5avdY;
        if (textStyle.getFontFamily() != null) {
            return textStyle;
        }
        m2775copyHL5avdY = textStyle.m2775copyHL5avdY((r44 & 1) != 0 ? textStyle.getColor() : 0L, (r44 & 2) != 0 ? textStyle.getFontSize() : 0L, (r44 & 4) != 0 ? textStyle.fontWeight : null, (r44 & 8) != 0 ? textStyle.getFontStyle() : null, (r44 & 16) != 0 ? textStyle.getFontSynthesis() : null, (r44 & 32) != 0 ? textStyle.fontFamily : fontFamily, (r44 & 64) != 0 ? textStyle.fontFeatureSettings : null, (r44 & 128) != 0 ? textStyle.getLetterSpacing() : 0L, (r44 & 256) != 0 ? textStyle.getBaselineShift() : null, (r44 & 512) != 0 ? textStyle.textGeometricTransform : null, (r44 & 1024) != 0 ? textStyle.localeList : null, (r44 & 2048) != 0 ? textStyle.getBackground() : 0L, (r44 & 4096) != 0 ? textStyle.textDecoration : null, (r44 & 8192) != 0 ? textStyle.shadow : null, (r44 & 16384) != 0 ? textStyle.getTextAlign() : null, (r44 & 32768) != 0 ? textStyle.getTextDirection() : null, (r44 & 65536) != 0 ? textStyle.getLineHeight() : 0L, (r44 & 131072) != 0 ? textStyle.textIndent : null);
        return m2775copyHL5avdY;
    }

    public static final ProvidableCompositionLocal<Typography> getLocalTypography() {
        return LocalTypography;
    }
}
