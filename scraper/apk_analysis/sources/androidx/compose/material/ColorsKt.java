package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.sentry.protocol.Request;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Colors.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u001d\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u001a\u008b\u0001\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u008b\u0001\u0010\u001c\u001a\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001b\u001a\u001f\u0010\t\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\n\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0014\u0010 \u001a\u00020!*\u00020\u00022\u0006\u0010\"\u001a\u00020\u0002H\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00028Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"LocalColors", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/Colors;", "getLocalColors", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "primarySurface", "Landroidx/compose/ui/graphics/Color;", "getPrimarySurface", "(Landroidx/compose/material/Colors;)J", "contentColorFor", "backgroundColor", "contentColorFor-ek8zF_U", "(JLandroidx/compose/runtime/Composer;I)J", "darkColors", "primary", "primaryVariant", "secondary", "secondaryVariant", "background", "surface", "error", "onPrimary", "onSecondary", "onBackground", "onSurface", "onError", "darkColors-2qZNXz8", "(JJJJJJJJJJJJ)Landroidx/compose/material/Colors;", "lightColors", "lightColors-2qZNXz8", "contentColorFor-4WTKRHQ", "(Landroidx/compose/material/Colors;J)J", "updateColorsFrom", "", Request.JsonKeys.OTHER, "material_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ColorsKt {
    private static final ProvidableCompositionLocal<Colors> LocalColors = CompositionLocalKt.staticCompositionLocalOf(new Function0<Colors>() { // from class: androidx.compose.material.ColorsKt$LocalColors$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Colors invoke() {
            Colors m699lightColors2qZNXz8;
            m699lightColors2qZNXz8 = ColorsKt.m699lightColors2qZNXz8((r43 & 1) != 0 ? ColorKt.Color(4284612846L) : 0L, (r43 & 2) != 0 ? ColorKt.Color(4281794739L) : 0L, (r43 & 4) != 0 ? ColorKt.Color(4278442694L) : 0L, (r43 & 8) != 0 ? ColorKt.Color(4278290310L) : 0L, (r43 & 16) != 0 ? Color.INSTANCE.m1291getWhite0d7_KjU() : 0L, (r43 & 32) != 0 ? Color.INSTANCE.m1291getWhite0d7_KjU() : 0L, (r43 & 64) != 0 ? ColorKt.Color(4289724448L) : 0L, (r43 & 128) != 0 ? Color.INSTANCE.m1291getWhite0d7_KjU() : 0L, (r43 & 256) != 0 ? Color.INSTANCE.m1280getBlack0d7_KjU() : 0L, (r43 & 512) != 0 ? Color.INSTANCE.m1280getBlack0d7_KjU() : 0L, (r43 & 1024) != 0 ? Color.INSTANCE.m1280getBlack0d7_KjU() : 0L, (r43 & 2048) != 0 ? Color.INSTANCE.m1291getWhite0d7_KjU() : 0L);
            return m699lightColors2qZNXz8;
        }
    });

    /* renamed from: lightColors-2qZNXz8, reason: not valid java name */
    public static final Colors m699lightColors2qZNXz8(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12) {
        return new Colors(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, true, null);
    }

    /* renamed from: darkColors-2qZNXz8$default, reason: not valid java name */
    public static /* synthetic */ Colors m698darkColors2qZNXz8$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, int i, Object obj) {
        long Color = (i & 1) != 0 ? ColorKt.Color(4290479868L) : j;
        long Color2 = (i & 2) != 0 ? ColorKt.Color(4281794739L) : j2;
        long Color3 = (i & 4) != 0 ? ColorKt.Color(4278442694L) : j3;
        return m697darkColors2qZNXz8(Color, Color2, Color3, (i & 8) != 0 ? Color3 : j4, (i & 16) != 0 ? ColorKt.Color(4279374354L) : j5, (i & 32) != 0 ? ColorKt.Color(4279374354L) : j6, (i & 64) != 0 ? ColorKt.Color(4291782265L) : j7, (i & 128) != 0 ? Color.INSTANCE.m1280getBlack0d7_KjU() : j8, (i & 256) != 0 ? Color.INSTANCE.m1280getBlack0d7_KjU() : j9, (i & 512) != 0 ? Color.INSTANCE.m1291getWhite0d7_KjU() : j10, (i & 1024) != 0 ? Color.INSTANCE.m1291getWhite0d7_KjU() : j11, (i & 2048) != 0 ? Color.INSTANCE.m1280getBlack0d7_KjU() : j12);
    }

    /* renamed from: darkColors-2qZNXz8, reason: not valid java name */
    public static final Colors m697darkColors2qZNXz8(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12) {
        return new Colors(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, false, null);
    }

    public static final long getPrimarySurface(Colors colors) {
        Intrinsics.checkNotNullParameter(colors, "<this>");
        return colors.isLight() ? colors.m678getPrimary0d7_KjU() : colors.m682getSurface0d7_KjU();
    }

    /* renamed from: contentColorFor-4WTKRHQ, reason: not valid java name */
    public static final long m695contentColorFor4WTKRHQ(Colors contentColorFor, long j) {
        Intrinsics.checkNotNullParameter(contentColorFor, "$this$contentColorFor");
        if (!Color.m1255equalsimpl0(j, contentColorFor.m678getPrimary0d7_KjU()) && !Color.m1255equalsimpl0(j, contentColorFor.m679getPrimaryVariant0d7_KjU())) {
            if (!Color.m1255equalsimpl0(j, contentColorFor.m680getSecondary0d7_KjU()) && !Color.m1255equalsimpl0(j, contentColorFor.m681getSecondaryVariant0d7_KjU())) {
                return Color.m1255equalsimpl0(j, contentColorFor.m671getBackground0d7_KjU()) ? contentColorFor.m673getOnBackground0d7_KjU() : Color.m1255equalsimpl0(j, contentColorFor.m682getSurface0d7_KjU()) ? contentColorFor.m677getOnSurface0d7_KjU() : Color.m1255equalsimpl0(j, contentColorFor.m672getError0d7_KjU()) ? contentColorFor.m674getOnError0d7_KjU() : Color.INSTANCE.m1290getUnspecified0d7_KjU();
            }
            return contentColorFor.m676getOnSecondary0d7_KjU();
        }
        return contentColorFor.m675getOnPrimary0d7_KjU();
    }

    /* renamed from: contentColorFor-ek8zF_U, reason: not valid java name */
    public static final long m696contentColorForek8zF_U(long j, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -161205715, "C(contentColorFor)P(0:c#ui.graphics.Color)*296@11462L6,296@11533L7:Colors.kt#jmzs0o");
        long m695contentColorFor4WTKRHQ = m695contentColorFor4WTKRHQ(MaterialTheme.INSTANCE.getColors(composer, 0), j);
        if (m695contentColorFor4WTKRHQ == Color.INSTANCE.m1290getUnspecified0d7_KjU()) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object consume = composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composer);
            m695contentColorFor4WTKRHQ = ((Color) consume).getValue();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return m695contentColorFor4WTKRHQ;
    }

    public static final void updateColorsFrom(Colors colors, Colors other) {
        Intrinsics.checkNotNullParameter(colors, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        colors.m690setPrimary8_81llA$material_release(other.m678getPrimary0d7_KjU());
        colors.m691setPrimaryVariant8_81llA$material_release(other.m679getPrimaryVariant0d7_KjU());
        colors.m692setSecondary8_81llA$material_release(other.m680getSecondary0d7_KjU());
        colors.m693setSecondaryVariant8_81llA$material_release(other.m681getSecondaryVariant0d7_KjU());
        colors.m683setBackground8_81llA$material_release(other.m671getBackground0d7_KjU());
        colors.m694setSurface8_81llA$material_release(other.m682getSurface0d7_KjU());
        colors.m684setError8_81llA$material_release(other.m672getError0d7_KjU());
        colors.m687setOnPrimary8_81llA$material_release(other.m675getOnPrimary0d7_KjU());
        colors.m688setOnSecondary8_81llA$material_release(other.m676getOnSecondary0d7_KjU());
        colors.m685setOnBackground8_81llA$material_release(other.m673getOnBackground0d7_KjU());
        colors.m689setOnSurface8_81llA$material_release(other.m677getOnSurface0d7_KjU());
        colors.m686setOnError8_81llA$material_release(other.m674getOnError0d7_KjU());
        colors.setLight$material_release(other.isLight());
    }

    public static final ProvidableCompositionLocal<Colors> getLocalColors() {
        return LocalColors;
    }
}
