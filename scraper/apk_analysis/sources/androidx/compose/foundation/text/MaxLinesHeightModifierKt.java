package androidx.compose.foundation.text;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MaxLinesHeightModifier.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"maxLinesHeight", "Landroidx/compose/ui/Modifier;", "maxLines", "", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "foundation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class MaxLinesHeightModifierKt {
    public static final Modifier maxLinesHeight(Modifier modifier, final int i, final TextStyle textStyle) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(textStyle, "textStyle");
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.text.MaxLinesHeightModifierKt$maxLinesHeight$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName("maxLinesHeight");
                inspectorInfo.getProperties().set("maxLines", Integer.valueOf(i));
                inspectorInfo.getProperties().set("textStyle", textStyle);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.text.MaxLinesHeightModifierKt$maxLinesHeight$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier composed, Composer composer, int i2) {
                Intrinsics.checkNotNullParameter(composed, "$this$composed");
                composer.startReplaceableGroup(-1924217056);
                ComposerKt.sourceInformation(composer, "C49@1714L7,50@1763L7,51@1818L7,55@1991L328,64@2350L390:MaxLinesHeightModifier.kt#423gt5");
                int i3 = i;
                int i4 = 0;
                if (!(i3 > 0)) {
                    throw new IllegalArgumentException("maxLines must be greater than 0".toString());
                }
                if (i3 == Integer.MAX_VALUE) {
                    Modifier.Companion companion = Modifier.INSTANCE;
                    composer.endReplaceableGroup();
                    return companion;
                }
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object consume = composer.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Density density = (Density) consume;
                ProvidableCompositionLocal<Font.ResourceLoader> localFontLoader = CompositionLocalsKt.getLocalFontLoader();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object consume2 = composer.consume(localFontLoader);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Font.ResourceLoader resourceLoader = (Font.ResourceLoader) consume2;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object consume3 = composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd(composer);
                LayoutDirection layoutDirection = (LayoutDirection) consume3;
                TextStyle textStyle2 = textStyle;
                Object[] objArr = {density, resourceLoader, textStyle2, layoutDirection};
                composer.startReplaceableGroup(-3685570);
                ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
                int i5 = 0;
                boolean z = false;
                while (i5 < 4) {
                    Object obj = objArr[i5];
                    i5++;
                    z |= composer.changed(obj);
                }
                Object rememberedValue = composer.rememberedValue();
                if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                    rememberedValue = Integer.valueOf(IntSize.m3126getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText(TextStyleKt.resolveDefaults(textStyle2, layoutDirection), density, resourceLoader, TextFieldDelegateKt.getEmptyTextReplacement(), 1)));
                    composer.updateRememberedValue(rememberedValue);
                }
                composer.endReplaceableGroup();
                int intValue = ((Number) rememberedValue).intValue();
                TextStyle textStyle3 = textStyle;
                Object[] objArr2 = {density, resourceLoader, textStyle3, layoutDirection};
                composer.startReplaceableGroup(-3685570);
                ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
                boolean z2 = false;
                while (i4 < 4) {
                    Object obj2 = objArr2[i4];
                    i4++;
                    z2 |= composer.changed(obj2);
                }
                Object rememberedValue2 = composer.rememberedValue();
                if (z2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue2 = Integer.valueOf(IntSize.m3126getHeightimpl(TextFieldDelegateKt.computeSizeForDefaultText(TextStyleKt.resolveDefaults(textStyle3, layoutDirection), density, resourceLoader, TextFieldDelegateKt.getEmptyTextReplacement() + '\n' + TextFieldDelegateKt.getEmptyTextReplacement(), 2)));
                    composer.updateRememberedValue(rememberedValue2);
                }
                composer.endReplaceableGroup();
                Modifier m345heightInVpY3zN4$default = SizeKt.m345heightInVpY3zN4$default(Modifier.INSTANCE, 0.0f, density.mo225toDpu2uoSUM(intValue + ((((Number) rememberedValue2).intValue() - intValue) * (i - 1))), 1, null);
                composer.endReplaceableGroup();
                return m345heightInVpY3zN4$default;
            }
        });
    }
}
