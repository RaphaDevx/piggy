package androidx.compose.animation;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.salesforce.marketingcloud.analytics.stats.b;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a=\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u001a\u0088\u0001\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000b2*\b\n\u0010\f\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u000f0\r¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132&\u0010\u0014\u001a\"\u0012\u0013\u0012\u0011H\n¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00020\r¢\u0006\u0002\b\u0010H\u0087\bø\u0001\u0000ø\u0001\u0002¢\u0006\u0002\u0010\u0018\u0082\u0002\u0012\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0005\b\u009920\u0001¨\u0006\u0019"}, d2 = {"animateColor", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/animation/core/InfiniteTransition;", "initialValue", "targetValue", "animationSpec", "Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "animateColor-RIQooxk", "(Landroidx/compose/animation/core/InfiniteTransition;JJLandroidx/compose/animation/core/InfiniteRepeatableSpec;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", ExifInterface.LATITUDE_SOUTH, "Landroidx/compose/animation/core/Transition;", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "label", "", "targetValueByState", "Lkotlin/ParameterName;", "name", SentryThread.JsonKeys.STATE, "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animation_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TransitionKt {
    public static final <S> State<Color> animateColor(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Color>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Color> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(-1462136984);
        ComposerKt.sourceInformation(composer, "C(animateColor)P(2)68@3224L31,69@3291L70,73@3374L70:Transition.kt#xbi5r1");
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Color>>() { // from class: androidx.compose.animation.TransitionKt$animateColor$1
                public final SpringSpec<Color> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(1459830167);
                    SpringSpec<Color> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ SpringSpec<Color> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "ColorAnimation";
        }
        String str2 = str;
        ColorSpace m1258getColorSpaceimpl = Color.m1258getColorSpaceimpl(targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf((i >> 6) & b.p)).getValue());
        composer.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean changed = composer.changed(m1258getColorSpaceimpl);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(m1258getColorSpaceimpl);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        int i3 = (i & 14) | 64;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        S currentState = transition.getCurrentState();
        int i6 = (i5 >> 9) & b.p;
        State<Color> createTransitionAnimation = androidx.compose.animation.core.TransitionKt.createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i6)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i6)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i5 >> 3) & b.p)), (TwoWayConverter) rememberedValue, str2, composer, (i5 & 14) | 32768 | ((i5 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }

    /* renamed from: animateColor-RIQooxk, reason: not valid java name */
    public static final State<Color> m109animateColorRIQooxk(InfiniteTransition animateColor, long j, long j2, InfiniteRepeatableSpec<Color> animationSpec, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(animateColor, "$this$animateColor");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        composer.startReplaceableGroup(-1462135610);
        ComposerKt.sourceInformation(composer, "C(animateColor)P(1:c#ui.graphics.Color,2:c#ui.graphics.Color)99@4480L72,102@4564L65:Transition.kt#xbi5r1");
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(Color.m1258getColorSpaceimpl(j2));
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        State<Color> animateValue = InfiniteTransitionKt.animateValue(animateColor, Color.m1244boximpl(j), Color.m1244boximpl(j2), (TwoWayConverter) rememberedValue, animationSpec, composer, InfiniteTransition.$stable | 4096 | (i & 14) | (i & b.p) | (i & 896) | (InfiniteRepeatableSpec.$stable << 12) | ((i << 3) & 57344));
        composer.endReplaceableGroup();
        return animateValue;
    }
}
