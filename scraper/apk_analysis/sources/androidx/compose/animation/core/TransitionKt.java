package androidx.compose.animation.core;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import com.salesforce.marketingcloud.analytics.stats.b;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.IntCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Transition.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a-\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001a\u0002H\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a3\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00040\n2\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\u000b\u001a\u0088\u0001\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00032*\b\n\u0010\u0010\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010\u0016\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u000e0\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001a\u001a\u0085\u0001\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\r\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00032*\b\n\u0010\u0010\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010\u0016\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001c0\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010\u001a\u001a\u0085\u0001\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\r\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00032*\b\n\u0010\u0010\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010\u0016\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010\u001a\u001a\u0088\u0001\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\r\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00032*\b\n\u0010\u0010\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010\u0016\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u001f0\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001a\u001a\u0088\u0001\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\r\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00032*\b\n\u0010\u0010\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010\u0016\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020!0\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001a\u001a\u0088\u0001\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\r\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00032*\b\n\u0010\u0010\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010\u0016\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020#0\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001a\u001a\u0085\u0001\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\r\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00032*\b\n\u0010\u0010\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010\u0016\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020%0\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010\u001a\u001a\u0088\u0001\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\r\"\u0004\b\u0000\u0010\u000f*\b\u0012\u0004\u0012\u0002H\u000f0\u00032*\b\n\u0010\u0010\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010\u0016\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020'0\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0002\u0010\u001a\u001a©\u0001\u0010(\u001a\b\u0012\u0004\u0012\u0002H\u00040\r\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0004\"\b\b\u0002\u0010)*\u00020**\b\u0012\u0004\u0012\u0002H\u000f0\u00032\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H)0,2*\b\n\u0010\u0010\u001a$\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010\u0016\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u0002H\u00040\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010-\u001a_\u0010.\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u000f0\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\u0010/\u001a\"\u0012\u0013\u0012\u0011H\u000f¢\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(0\u0012\u0004\u0012\u0002H\u00040\u0011¢\u0006\u0002\b\u0014H\u0087\bø\u0001\u0001¢\u0006\u0002\u00101\u001aA\u00102\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u000f0\u00032\u0006\u00103\u001a\u0002H\u00042\u0006\u0010\u0005\u001a\u0002H\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\u00104\u001aa\u00105\u001a\u0018\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H)06R\b\u0012\u0004\u0012\u0002H\u000f0\u0003\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0004\"\b\b\u0002\u0010)*\u00020**\b\u0012\u0004\u0012\u0002H\u000f0\u00032\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H)0,2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\u00107\u001am\u00108\u001a\b\u0012\u0004\u0012\u0002H\u00040\r\"\u0004\b\u0000\u0010\u000f\"\u0004\b\u0001\u0010\u0004\"\b\b\u0002\u0010)*\u00020**\b\u0012\u0004\u0012\u0002H\u000f0\u00032\u0006\u00109\u001a\u0002H\u00042\u0006\u0010:\u001a\u0002H\u00042\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H\u00040\u00132\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H)0,2\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010<\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006="}, d2 = {"AnimationDebugDurationScale", "", "updateTransition", "Landroidx/compose/animation/core/Transition;", ExifInterface.GPS_DIRECTION_TRUE, "targetState", "label", "", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "transitionState", "Landroidx/compose/animation/core/MutableTransitionState;", "(Landroidx/compose/animation/core/MutableTransitionState;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "animateDp", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/unit/Dp;", ExifInterface.LATITUDE_SOUTH, "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "targetValueByState", "Lkotlin/ParameterName;", "name", SentryThread.JsonKeys.STATE, "(Landroidx/compose/animation/core/Transition;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "animateFloat", "", "animateInt", "animateIntOffset", "Landroidx/compose/ui/unit/IntOffset;", "animateIntSize", "Landroidx/compose/ui/unit/IntSize;", "animateOffset", "Landroidx/compose/ui/geometry/Offset;", "animateRect", "Landroidx/compose/ui/geometry/Rect;", "animateSize", "Landroidx/compose/ui/geometry/Size;", "animateValue", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "Landroidx/compose/animation/core/AnimationVector;", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Lkotlin/jvm/functions/Function3;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "createChildTransition", "transformToChildState", "parentState", "(Landroidx/compose/animation/core/Transition;Ljava/lang/String;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition;", "createChildTransitionInternal", "initialState", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/core/Transition;", "createDeferredAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "(Landroidx/compose/animation/core/Transition;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)Landroidx/compose/animation/core/Transition$DeferredAnimation;", "createTransitionAnimation", "initialValue", "targetValue", "animationSpec", "(Landroidx/compose/animation/core/Transition;Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "animation-core_release"}, k = 2, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class TransitionKt {
    public static final int AnimationDebugDurationScale = 1;

    public static final <T> Transition<T> updateTransition(T t, String str, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(1641299311);
        ComposerKt.sourceInformation(composer, "C(updateTransition)P(1)67@2753L51,68@2820L22,69@2847L224:Transition.kt#pdpnli");
        if ((i2 & 2) != 0) {
            str = null;
        }
        composer.startReplaceableGroup(-3687241);
        ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Transition(t, str);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        final Transition<T> transition = (Transition) rememberedValue;
        transition.animateTo$animation_core_release(t, composer, (i & 8) | 48 | (i & 14));
        EffectsKt.DisposableEffect(transition, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                final Transition<T> transition2 = transition;
                return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        Transition.this.onTransitionEnd$animation_core_release();
                    }
                };
            }
        }, composer, 6);
        composer.endReplaceableGroup();
        return transition;
    }

    public static final <T> Transition<T> updateTransition(MutableTransitionState<T> transitionState, String str, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transitionState, "transitionState");
        composer.startReplaceableGroup(1641302990);
        ComposerKt.sourceInformation(composer, "C(updateTransition)P(1)151@6460L94,154@6570L38,155@6613L224:Transition.kt#pdpnli");
        if ((i2 & 2) != 0) {
            str = null;
        }
        composer.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean changed = composer.changed(transitionState);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Transition((MutableTransitionState) transitionState, str);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        final Transition<T> transition = (Transition) rememberedValue;
        transition.animateTo$animation_core_release(transitionState.getTargetState(), composer, 0);
        EffectsKt.DisposableEffect(transition, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                final Transition<T> transition2 = transition;
                return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$updateTransition$2$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        Transition.this.onTransitionEnd$animation_core_release();
                    }
                };
            }
        }, composer, 0);
        composer.endReplaceableGroup();
        return transition;
    }

    @InternalAnimationApi
    public static final <S, T, V extends AnimationVector> Transition<S>.DeferredAnimation<T, V> createDeferredAnimation(final Transition<S> transition, TwoWayConverter<T, V> typeConverter, String str, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        composer.startReplaceableGroup(-44508555);
        ComposerKt.sourceInformation(composer, "C(createDeferredAnimation)P(1)656@25809L58,657@25872L102:Transition.kt#pdpnli");
        if ((i2 & 2) != 0) {
            str = "DeferredAnimation";
        }
        composer.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean changed = composer.changed(transition);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Transition.DeferredAnimation(transition, typeConverter, str);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        final Transition<S>.DeferredAnimation<T, V> deferredAnimation = (Transition.DeferredAnimation) rememberedValue;
        EffectsKt.DisposableEffect(deferredAnimation, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                final Transition<S> transition2 = transition;
                final Transition<S>.DeferredAnimation<T, V> deferredAnimation2 = deferredAnimation;
                return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createDeferredAnimation$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        Transition.this.removeAnimation$animation_core_release(deferredAnimation2);
                    }
                };
            }
        }, composer, 8);
        if (transition.isSeeking()) {
            deferredAnimation.setupSeeking$animation_core_release();
        }
        composer.endReplaceableGroup();
        return deferredAnimation;
    }

    @ExperimentalTransitionApi
    public static final <S, T> Transition<T> createChildTransition(Transition<S> transition, String str, Function3<? super S, ? super Composer, ? super Integer, ? extends T> transformToChildState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(transformToChildState, "transformToChildState");
        composer.startReplaceableGroup(1117104315);
        ComposerKt.sourceInformation(composer, "C(createChildTransition)689@27313L36,690@27373L41,691@27437L39,692@27488L63:Transition.kt#pdpnli");
        if ((i2 & 1) != 0) {
            str = "ChildTransition";
        }
        String str2 = str;
        int i3 = i & 14;
        composer.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean changed = composer.changed(transition);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = transition.getCurrentState();
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        int i4 = (i >> 3) & b.p;
        Transition<T> createChildTransitionInternal = createChildTransitionInternal(transition, transformToChildState.invoke(rememberedValue, composer, Integer.valueOf(i4)), transformToChildState.invoke(transition.getTargetState(), composer, Integer.valueOf(i4)), str2, composer, i3 | ((i << 6) & 7168));
        composer.endReplaceableGroup();
        return createChildTransitionInternal;
    }

    public static final <S, T> Transition<T> createChildTransitionInternal(final Transition<S> transition, T t, T t2, String label, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(label, "label");
        composer.startReplaceableGroup(-382165928);
        ComposerKt.sourceInformation(composer, "C(createChildTransitionInternal)P(!1,2)702@27746L86,706@27838L141,713@27996L25:Transition.kt#pdpnli");
        composer.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean changed = composer.changed(transition);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Transition(new MutableTransitionState(t), label);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        final Transition<T> transition2 = (Transition) rememberedValue;
        EffectsKt.DisposableEffect(transition2, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                transition.addTransition$animation_core_release(transition2);
                final Transition<S> transition3 = transition;
                final Transition<T> transition4 = transition2;
                return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createChildTransitionInternal$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        Transition.this.removeTransition$animation_core_release(transition4);
                    }
                };
            }
        }, composer, 0);
        transition2.updateTarget$animation_core_release(t2, composer, ((i >> 3) & 8) | ((i >> 6) & 14));
        composer.endReplaceableGroup();
        return transition2;
    }

    public static final <S, T, V extends AnimationVector> State<T> animateValue(Transition<S> transition, TwoWayConverter<T, V> typeConverter, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<T>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, ? extends T> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        if ((i2 & 2) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<T>>() { // from class: androidx.compose.animation.core.TransitionKt$animateValue$1
                public final SpringSpec<T> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(-251236366);
                    SpringSpec<T> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }
            };
        }
        if ((i2 & 4) != 0) {
            str = "ValueAnimation";
        }
        String str2 = str;
        S currentState = transition.getCurrentState();
        int i3 = (i >> 9) & b.p;
        State<T> createTransitionAnimation = createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i3)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i3)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i >> 3) & b.p)), typeConverter, str2, composer, (i & 14) | (57344 & (i << 9)) | ((i << 6) & 458752));
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }

    public static final <S, T, V extends AnimationVector> State<T> createTransitionAnimation(final Transition<S> transition, T t, T t2, FiniteAnimationSpec<T> animationSpec, TwoWayConverter<T, V> typeConverter, String label, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(animationSpec, "animationSpec");
        Intrinsics.checkNotNullParameter(typeConverter, "typeConverter");
        Intrinsics.checkNotNullParameter(label, "label");
        composer.startReplaceableGroup(460678807);
        ComposerKt.sourceInformation(composer, "C(createTransitionAnimation)P(1,3!1,4)767@30507L499,790@31352L166:Transition.kt#pdpnli");
        composer.startReplaceableGroup(-3686930);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean changed = composer.changed(transition);
        Object rememberedValue = composer.rememberedValue();
        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
            rememberedValue = new Transition.TransitionAnimationState(transition, t, AnimationStateKt.createZeroVectorFrom(typeConverter, t2), typeConverter, label);
            composer.updateRememberedValue(rememberedValue);
        }
        composer.endReplaceableGroup();
        final Transition.TransitionAnimationState transitionAnimationState = (Transition.TransitionAnimationState) rememberedValue;
        if (transition.isSeeking()) {
            transitionAnimationState.updateInitialAndTargetValue$animation_core_release(t, t2, animationSpec);
        } else {
            transitionAnimationState.updateTargetValue$animation_core_release(t2, animationSpec);
        }
        EffectsKt.DisposableEffect(transitionAnimationState, new Function1<DisposableEffectScope, DisposableEffectResult>() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DisposableEffectResult invoke(DisposableEffectScope DisposableEffect) {
                Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
                transition.addAnimation$animation_core_release(transitionAnimationState);
                final Transition<S> transition2 = transition;
                final Transition<S>.TransitionAnimationState<T, V> transitionAnimationState2 = transitionAnimationState;
                return new DisposableEffectResult() { // from class: androidx.compose.animation.core.TransitionKt$createTransitionAnimation$1$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public void dispose() {
                        Transition.this.removeAnimation$animation_core_release(transitionAnimationState2);
                    }
                };
            }
        }, composer, 0);
        composer.endReplaceableGroup();
        return transitionAnimationState;
    }

    public static final <S> State<Float> animateFloat(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Float>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Float> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(1399888154);
        ComposerKt.sourceInformation(composer, "C(animateFloat)P(2)833@33385L78:Transition.kt#pdpnli");
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Float>>() { // from class: androidx.compose.animation.core.TransitionKt$animateFloat$1
                public final SpringSpec<Float> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(-87752119);
                    SpringSpec<Float> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ SpringSpec<Float> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "FloatAnimation";
        }
        String str2 = str;
        TwoWayConverter<Float, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(FloatCompanionObject.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        S currentState = transition.getCurrentState();
        int i6 = (i5 >> 9) & b.p;
        State<Float> createTransitionAnimation = createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i6)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i6)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i5 >> 3) & b.p)), vectorConverter, str2, composer, (i5 & 14) | ((i5 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }

    public static final <S> State<Dp> animateDp(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Dp>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Dp> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(-307434655);
        ComposerKt.sourceInformation(composer, "C(animateDp)P(2)864@35099L75:Transition.kt#pdpnli");
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Dp>>() { // from class: androidx.compose.animation.core.TransitionKt$animateDp$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ SpringSpec<Dp> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }

                public final SpringSpec<Dp> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(252670850);
                    SpringSpec<Dp> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Dp.m3005boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Dp.INSTANCE)), 3, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "DpAnimation";
        }
        String str2 = str;
        TwoWayConverter<Dp, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(Dp.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        S currentState = transition.getCurrentState();
        int i6 = (i5 >> 9) & b.p;
        State<Dp> createTransitionAnimation = createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i6)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i6)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i5 >> 3) & b.p)), vectorConverter, str2, composer, (i5 & 14) | ((i5 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }

    public static final <S> State<Offset> animateOffset(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Offset>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Offset> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(-336092818);
        ComposerKt.sourceInformation(composer, "C(animateOffset)P(2)895@36839L79:Transition.kt#pdpnli");
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Offset>>() { // from class: androidx.compose.animation.core.TransitionKt$animateOffset$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ SpringSpec<Offset> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }

                public final SpringSpec<Offset> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(1800989719);
                    SpringSpec<Offset> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Offset.m1014boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Offset.INSTANCE)), 3, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "OffsetAnimation";
        }
        String str2 = str;
        TwoWayConverter<Offset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Offset.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        S currentState = transition.getCurrentState();
        int i6 = (i5 >> 9) & b.p;
        State<Offset> createTransitionAnimation = createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i6)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i6)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i5 >> 3) & b.p)), vectorConverter, str2, composer, (i5 & 14) | ((i5 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }

    public static final <S> State<Size> animateSize(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Size>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Size> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(888405837);
        ComposerKt.sourceInformation(composer, "C(animateSize)P(2)926@38568L77:Transition.kt#pdpnli");
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Size>>() { // from class: androidx.compose.animation.core.TransitionKt$animateSize$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ SpringSpec<Size> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }

                public final SpringSpec<Size> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(1723940594);
                    SpringSpec<Size> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, Size.m1082boximpl(VisibilityThresholdsKt.getVisibilityThreshold(Size.INSTANCE)), 3, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "SizeAnimation";
        }
        String str2 = str;
        TwoWayConverter<Size, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(Size.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        S currentState = transition.getCurrentState();
        int i6 = (i5 >> 9) & b.p;
        State<Size> createTransitionAnimation = createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i6)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i6)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i5 >> 3) & b.p)), vectorConverter, str2, composer, (i5 & 14) | ((i5 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }

    public static final <S> State<IntOffset> animateIntOffset(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntOffset>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, IntOffset> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(-1397283030);
        ComposerKt.sourceInformation(composer, "C(animateIntOffset)P(2)957@40324L82:Transition.kt#pdpnli");
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<IntOffset>>() { // from class: androidx.compose.animation.core.TransitionKt$animateIntOffset$1
                public final SpringSpec<IntOffset> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(-2136569491);
                    SpringSpec<IntOffset> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntOffset.m3076boximpl(IntOffsetKt.IntOffset(1, 1)), 3, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ SpringSpec<IntOffset> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "IntOffsetAnimation";
        }
        String str2 = str;
        TwoWayConverter<IntOffset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntOffset.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        S currentState = transition.getCurrentState();
        int i6 = (i5 >> 9) & b.p;
        State<IntOffset> createTransitionAnimation = createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i6)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i6)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i5 >> 3) & b.p)), vectorConverter, str2, composer, (i5 & 14) | ((i5 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }

    public static final <S> State<Integer> animateInt(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Integer>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Integer> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(-941425960);
        ComposerKt.sourceInformation(composer, "C(animateInt)P(2)988@42027L76:Transition.kt#pdpnli");
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Integer>>() { // from class: androidx.compose.animation.core.TransitionKt$animateInt$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ SpringSpec<Integer> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }

                public final SpringSpec<Integer> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(847165563);
                    SpringSpec<Integer> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, 1, 3, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "IntAnimation";
        }
        String str2 = str;
        TwoWayConverter<Integer, AnimationVector1D> vectorConverter = VectorConvertersKt.getVectorConverter(IntCompanionObject.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        S currentState = transition.getCurrentState();
        int i6 = (i5 >> 9) & b.p;
        State<Integer> createTransitionAnimation = createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i6)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i6)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i5 >> 3) & b.p)), vectorConverter, str2, composer, (i5 & 14) | ((i5 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }

    public static final <S> State<IntSize> animateIntSize(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<IntSize>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, IntSize> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(-520512900);
        ComposerKt.sourceInformation(composer, "C(animateIntSize)P(2)1018@43756L80:Transition.kt#pdpnli");
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<IntSize>>() { // from class: androidx.compose.animation.core.TransitionKt$animateIntSize$1
                public final SpringSpec<IntSize> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(-1158360657);
                    SpringSpec<IntSize> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, IntSize.m3119boximpl(IntSizeKt.IntSize(1, 1)), 3, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ SpringSpec<IntSize> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "IntSizeAnimation";
        }
        String str2 = str;
        TwoWayConverter<IntSize, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(IntSize.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        S currentState = transition.getCurrentState();
        int i6 = (i5 >> 9) & b.p;
        State<IntSize> createTransitionAnimation = createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i6)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i6)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i5 >> 3) & b.p)), vectorConverter, str2, composer, (i5 & 14) | ((i5 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }

    public static final <S> State<Rect> animateRect(Transition<S> transition, Function3<? super Transition.Segment<S>, ? super Composer, ? super Integer, ? extends FiniteAnimationSpec<Rect>> function3, String str, Function3<? super S, ? super Composer, ? super Integer, Rect> targetValueByState, Composer composer, int i, int i2) {
        Intrinsics.checkNotNullParameter(transition, "<this>");
        Intrinsics.checkNotNullParameter(targetValueByState, "targetValueByState");
        composer.startReplaceableGroup(887348432);
        ComposerKt.sourceInformation(composer, "C(animateRect)P(2)1048@45482L77:Transition.kt#pdpnli");
        if ((i2 & 1) != 0) {
            function3 = new Function3<Transition.Segment<S>, Composer, Integer, SpringSpec<Rect>>() { // from class: androidx.compose.animation.core.TransitionKt$animateRect$1
                public final SpringSpec<Rect> invoke(Transition.Segment<S> segment, Composer composer2, int i3) {
                    Intrinsics.checkNotNullParameter(segment, "$this$null");
                    composer2.startReplaceableGroup(-1189590787);
                    SpringSpec<Rect> spring$default = AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.INSTANCE), 3, null);
                    composer2.endReplaceableGroup();
                    return spring$default;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ SpringSpec<Rect> invoke(Object obj, Composer composer2, Integer num) {
                    return invoke((Transition.Segment) obj, composer2, num.intValue());
                }
            };
        }
        if ((i2 & 2) != 0) {
            str = "RectAnimation";
        }
        String str2 = str;
        TwoWayConverter<Rect, AnimationVector4D> vectorConverter = VectorConvertersKt.getVectorConverter(Rect.INSTANCE);
        int i3 = i & 14;
        int i4 = i << 3;
        int i5 = i3 | (i4 & 896) | (i4 & 7168) | (i4 & 57344);
        composer.startReplaceableGroup(1847721733);
        ComposerKt.sourceInformation(composer, "C(animateValue)P(3,2)751@29971L32,752@30026L31,753@30082L23,755@30118L89:Transition.kt#pdpnli");
        S currentState = transition.getCurrentState();
        int i6 = (i5 >> 9) & b.p;
        State<Rect> createTransitionAnimation = createTransitionAnimation(transition, targetValueByState.invoke(currentState, composer, Integer.valueOf(i6)), targetValueByState.invoke(transition.getTargetState(), composer, Integer.valueOf(i6)), function3.invoke(transition.getSegment(), composer, Integer.valueOf((i5 >> 3) & b.p)), vectorConverter, str2, composer, (i5 & 14) | ((i5 << 6) & 458752));
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return createTransitionAnimation;
    }
}
