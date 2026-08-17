package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: Snackbar.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u00068Gø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u00068Gø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000b"}, d2 = {"Landroidx/compose/material/SnackbarDefaults;", "", "()V", "SnackbarOverlayAlpha", "", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "getBackgroundColor", "(Landroidx/compose/runtime/Composer;I)J", "primaryActionColor", "getPrimaryActionColor", "material_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class SnackbarDefaults {
    public static final int $stable = 0;
    public static final SnackbarDefaults INSTANCE = new SnackbarDefaults();
    private static final float SnackbarOverlayAlpha = 0.8f;

    private SnackbarDefaults() {
    }

    public final long getBackgroundColor(Composer composer, int i) {
        composer.startReplaceableGroup(-465860777);
        ComposerKt.sourceInformation(composer, "C201@8484L6,203@8598L6:Snackbar.kt#jmzs0o");
        long m1300compositeOverOWjLjI = ColorKt.m1300compositeOverOWjLjI(Color.m1253copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m677getOnSurface0d7_KjU(), SnackbarOverlayAlpha, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColors(composer, 0).m682getSurface0d7_KjU());
        composer.endReplaceableGroup();
        return m1300compositeOverOWjLjI;
    }

    public final long getPrimaryActionColor(Composer composer, int i) {
        long m679getPrimaryVariant0d7_KjU;
        composer.startReplaceableGroup(894573386);
        ComposerKt.sourceInformation(composer, "C222@9546L6:Snackbar.kt#jmzs0o");
        Colors colors = MaterialTheme.INSTANCE.getColors(composer, 0);
        if (colors.isLight()) {
            m679getPrimaryVariant0d7_KjU = ColorKt.m1300compositeOverOWjLjI(Color.m1253copywmQWz5c$default(colors.m682getSurface0d7_KjU(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null), colors.m678getPrimary0d7_KjU());
        } else {
            m679getPrimaryVariant0d7_KjU = colors.m679getPrimaryVariant0d7_KjU();
        }
        composer.endReplaceableGroup();
        return m679getPrimaryVariant0d7_KjU;
    }
}
