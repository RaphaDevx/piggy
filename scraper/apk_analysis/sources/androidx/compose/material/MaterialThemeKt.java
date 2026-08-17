package androidx.compose.material;

import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.material.ripple.RippleThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.analytics.stats.b;
import com.samsung.android.sdk.samsungpay.v2.payment.PaymentManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MaterialTheme.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"MaterialTheme", "", "colors", "Landroidx/compose/material/Colors;", "typography", "Landroidx/compose/material/Typography;", "shapes", "Landroidx/compose/material/Shapes;", FirebaseAnalytics.Param.CONTENT, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material/Colors;Landroidx/compose/material/Typography;Landroidx/compose/material/Shapes;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MaterialThemeKt {
    public static final void MaterialTheme(Colors colors, Typography typography, Shapes shapes, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i, final int i2) {
        final Colors colors2;
        int i3;
        Typography typography2;
        Shapes shapes2;
        final Shapes shapes3;
        final Typography typography3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer startRestartGroup = composer.startRestartGroup(-1505114095);
        ComposerKt.sourceInformation(startRestartGroup, "C(MaterialTheme)P(!1,3,2)59@2947L6,60@2998L10,61@3045L6,*64@3120L184,69@3367L16,70@3410L45,73@3581L4,71@3460L456:MaterialTheme.kt#jmzs0o");
        if ((i & 14) == 0) {
            if ((i2 & 1) == 0) {
                colors2 = colors;
                if (startRestartGroup.changed(colors2)) {
                    i6 = 4;
                    i3 = i6 | i;
                }
            } else {
                colors2 = colors;
            }
            i6 = 2;
            i3 = i6 | i;
        } else {
            colors2 = colors;
            i3 = i;
        }
        if ((i & b.p) == 0) {
            if ((i2 & 2) == 0) {
                typography2 = typography;
                if (startRestartGroup.changed(typography2)) {
                    i5 = 32;
                    i3 |= i5;
                }
            } else {
                typography2 = typography;
            }
            i5 = 16;
            i3 |= i5;
        } else {
            typography2 = typography;
        }
        if ((i & 896) == 0) {
            if ((i2 & 4) == 0) {
                shapes2 = shapes;
                if (startRestartGroup.changed(shapes2)) {
                    i4 = 256;
                    i3 |= i4;
                }
            } else {
                shapes2 = shapes;
            }
            i4 = 128;
            i3 |= i4;
        } else {
            shapes2 = shapes;
        }
        if ((i2 & 8) != 0) {
            i3 |= 3072;
        } else if ((i & 7168) == 0) {
            i3 |= startRestartGroup.changed(content) ? 2048 : 1024;
        }
        if (((i3 & 5851) ^ 1170) != 0 || !startRestartGroup.getSkipping()) {
            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                startRestartGroup.skipCurrentGroup();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    i3 &= PaymentManager.ERROR_ADDRESS_UPDATED_TIME_OUT;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
            } else {
                startRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    colors2 = MaterialTheme.INSTANCE.getColors(startRestartGroup, 0);
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    typography2 = MaterialTheme.INSTANCE.getTypography(startRestartGroup, 0);
                    i3 &= PaymentManager.ERROR_ADDRESS_UPDATED_TIME_OUT;
                }
                if ((i2 & 4) != 0) {
                    shapes2 = MaterialTheme.INSTANCE.getShapes(startRestartGroup, 0);
                    i3 &= -897;
                }
                startRestartGroup.endDefaults();
            }
            final int i7 = i3;
            final Typography typography4 = typography2;
            Shapes shapes4 = shapes2;
            startRestartGroup.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(startRestartGroup, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = r14.m670copypvPzIIM((r43 & 1) != 0 ? r14.m678getPrimary0d7_KjU() : 0L, (r43 & 2) != 0 ? r14.m679getPrimaryVariant0d7_KjU() : 0L, (r43 & 4) != 0 ? r14.m680getSecondary0d7_KjU() : 0L, (r43 & 8) != 0 ? r14.m681getSecondaryVariant0d7_KjU() : 0L, (r43 & 16) != 0 ? r14.m671getBackground0d7_KjU() : 0L, (r43 & 32) != 0 ? r14.m682getSurface0d7_KjU() : 0L, (r43 & 64) != 0 ? r14.m672getError0d7_KjU() : 0L, (r43 & 128) != 0 ? r14.m675getOnPrimary0d7_KjU() : 0L, (r43 & 256) != 0 ? r14.m676getOnSecondary0d7_KjU() : 0L, (r43 & 512) != 0 ? r14.m673getOnBackground0d7_KjU() : 0L, (r43 & 1024) != 0 ? r14.m677getOnSurface0d7_KjU() : 0L, (r43 & 2048) != 0 ? r14.m674getOnError0d7_KjU() : 0L, (r43 & 4096) != 0 ? colors2.isLight() : false);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            startRestartGroup.endReplaceableGroup();
            Colors colors3 = (Colors) rememberedValue;
            ColorsKt.updateColorsFrom(colors3, colors2);
            CompositionLocalKt.CompositionLocalProvider(new ProvidedValue[]{ColorsKt.getLocalColors().provides(colors3), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh(startRestartGroup, 0))), IndicationKt.getLocalIndication().provides(RippleKt.m922rememberRipple9IZ8Weo(false, 0.0f, 0L, startRestartGroup, 0, 7)), RippleThemeKt.getLocalRippleTheme().provides(MaterialRippleTheme.INSTANCE), ShapesKt.getLocalShapes().provides(shapes4), TextSelectionColorsKt.getLocalTextSelectionColors().provides(MaterialTextSelectionColorsKt.rememberTextSelectionColors(colors3, startRestartGroup, 0)), TypographyKt.getLocalTypography().provides(typography4)}, ComposableLambdaKt.composableLambda(startRestartGroup, -819894159, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.MaterialThemeKt$MaterialTheme$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i8) {
                    ComposerKt.sourceInformation(composer2, "C80@3849L61:MaterialTheme.kt#jmzs0o");
                    if (((i8 & 11) ^ 2) == 0 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                    } else {
                        TextKt.ProvideTextStyle(Typography.this.getBody1(), content, composer2, (i7 >> 6) & b.p);
                    }
                }
            }), startRestartGroup, 56);
            shapes3 = shapes4;
            typography3 = typography4;
        } else {
            startRestartGroup.skipToGroupEnd();
            typography3 = typography2;
            shapes3 = shapes2;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.MaterialThemeKt$MaterialTheme$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i8) {
                MaterialThemeKt.MaterialTheme(Colors.this, typography3, shapes3, content, composer2, i | 1, i2);
            }
        });
    }
}
