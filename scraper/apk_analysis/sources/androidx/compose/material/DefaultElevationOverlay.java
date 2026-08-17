package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: ElevationOverlay.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\n"}, d2 = {"Landroidx/compose/material/DefaultElevationOverlay;", "Landroidx/compose/material/ElevationOverlay;", "()V", "apply", "Landroidx/compose/ui/graphics/Color;", TypedValues.Custom.S_COLOR, "elevation", "Landroidx/compose/ui/unit/Dp;", "apply-7g2Lkgo", "(JFLandroidx/compose/runtime/Composer;I)J", "material_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class DefaultElevationOverlay implements ElevationOverlay {
    public static final DefaultElevationOverlay INSTANCE = new DefaultElevationOverlay();

    private DefaultElevationOverlay() {
    }

    @Override // androidx.compose.material.ElevationOverlay
    /* renamed from: apply-7g2Lkgo, reason: not valid java name */
    public long mo712apply7g2Lkgo(long j, float f, Composer composer, int i) {
        long m739calculateForegroundColorCLU3JFs;
        ComposerKt.sourceInformationMarkerStart(composer, -1272525387, "C(apply)P(0:c#ui.graphics.Color,1:c#ui.unit.Dp)69@2742L6:ElevationOverlay.kt#jmzs0o");
        Colors colors = MaterialTheme.INSTANCE.getColors(composer, 0);
        if (Dp.m3006compareTo0680j_4(f, Dp.m3007constructorimpl(0)) > 0 && !colors.isLight()) {
            composer.startReplaceableGroup(-1272525241);
            ComposerKt.sourceInformation(composer, "71@2841L42");
            m739calculateForegroundColorCLU3JFs = ElevationOverlayKt.m739calculateForegroundColorCLU3JFs(j, f, composer, i & WebSocketProtocol.PAYLOAD_SHORT);
            j = ColorKt.m1300compositeOverOWjLjI(m739calculateForegroundColorCLU3JFs, j);
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(-1272525098);
            composer.endReplaceableGroup();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return j;
    }
}
