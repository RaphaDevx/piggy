package androidx.compose.material;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.salesforce.marketingcloud.analytics.stats.b;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Slider.kt */
@Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class SliderKt$RangeSlider$2 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
    final /* synthetic */ int $$dirty;
    final /* synthetic */ SliderColors $colors;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ MutableInteractionSource $endInteractionSource;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ Function0<Unit> $onValueChangeFinished;
    final /* synthetic */ State<Function1<ClosedFloatingPointRange<Float>, Unit>> $onValueChangeState;
    final /* synthetic */ MutableInteractionSource $startInteractionSource;
    final /* synthetic */ List<Float> $tickFractions;
    final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;
    final /* synthetic */ ClosedFloatingPointRange<Float> $values;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SliderKt$RangeSlider$2(ClosedFloatingPointRange<Float> closedFloatingPointRange, ClosedFloatingPointRange<Float> closedFloatingPointRange2, int i, Modifier modifier, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, boolean z, State<? extends Function1<? super ClosedFloatingPointRange<Float>, Unit>> state, List<Float> list, SliderColors sliderColors, Function0<Unit> function0) {
        super(3);
        this.$valueRange = closedFloatingPointRange;
        this.$values = closedFloatingPointRange2;
        this.$$dirty = i;
        this.$modifier = modifier;
        this.$startInteractionSource = mutableInteractionSource;
        this.$endInteractionSource = mutableInteractionSource2;
        this.$enabled = z;
        this.$onValueChangeState = state;
        this.$tickFractions = list;
        this.$colors = sliderColors;
        this.$onValueChangeFinished = function0;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
        invoke(boxWithConstraintsScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer composer, int i) {
        int i2;
        Modifier rangeSliderPressDragModifier;
        float calcFraction;
        float calcFraction2;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation(composer, "C279@13015L7,289@13439L56,290@13523L63,292@13596L81,293@13686L86,295@13794L24,296@13850L944,330@15106L481,350@16039L284:Slider.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = i | (composer.changed(BoxWithConstraints) ? 4 : 2);
        } else {
            i2 = i;
        }
        if (((i2 & 91) ^ 18) != 0 || !composer.getSkipping()) {
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object consume = composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composer);
            boolean z = consume == LayoutDirection.Rtl;
            final float m2977getMaxWidthimpl = Constraints.m2977getMaxWidthimpl(BoxWithConstraints.getConstraints());
            ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$values;
            ClosedFloatingPointRange<Float> closedFloatingPointRange2 = this.$valueRange;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            final float f = 0.0f;
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = SnapshotStateKt.mutableStateOf$default(Float.valueOf(invoke$scaleToOffset(closedFloatingPointRange2, 0.0f, m2977getMaxWidthimpl, closedFloatingPointRange.getStart().floatValue())), null, 2, null);
                composer.updateRememberedValue(rememberedValue);
            }
            composer.endReplaceableGroup();
            final MutableState mutableState = (MutableState) rememberedValue;
            ClosedFloatingPointRange<Float> closedFloatingPointRange3 = this.$values;
            ClosedFloatingPointRange<Float> closedFloatingPointRange4 = this.$valueRange;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue2 = composer.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = SnapshotStateKt.mutableStateOf$default(Float.valueOf(invoke$scaleToOffset(closedFloatingPointRange4, 0.0f, m2977getMaxWidthimpl, closedFloatingPointRange3.getEndInclusive().floatValue())), null, 2, null);
                composer.updateRememberedValue(rememberedValue2);
            }
            composer.endReplaceableGroup();
            final MutableState mutableState2 = (MutableState) rememberedValue2;
            SliderKt.CorrectValueSideEffect(new AnonymousClass1(this.$valueRange, 0.0f, m2977getMaxWidthimpl), this.$valueRange, mutableState, this.$values.getStart().floatValue(), composer, ((this.$$dirty >> 9) & b.p) | 384);
            SliderKt.CorrectValueSideEffect(new AnonymousClass2(this.$valueRange, 0.0f, m2977getMaxWidthimpl), this.$valueRange, mutableState2, this.$values.getEndInclusive().floatValue(), composer, ((this.$$dirty >> 9) & b.p) | 384);
            composer.startReplaceableGroup(-723524056);
            ComposerKt.sourceInformation(composer, "C(rememberCoroutineScope)475@19849L144:Effects.kt#9igjgp");
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue3 = composer.rememberedValue();
            if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                rememberedValue3 = compositionScopedCoroutineScopeCanceller;
            }
            composer.endReplaceableGroup();
            final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue3).getCoroutineScope();
            composer.endReplaceableGroup();
            final List<Float> list = this.$tickFractions;
            final Function0<Unit> function0 = this.$onValueChangeFinished;
            final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state = this.$onValueChangeState;
            final ClosedFloatingPointRange<Float> closedFloatingPointRange5 = this.$valueRange;
            State rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(new Function1<Boolean, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z2) {
                    float snapValueToTick;
                    float floatValue = (z2 ? mutableState : mutableState2).getValue().floatValue();
                    snapValueToTick = SliderKt.snapValueToTick(floatValue, list, f, m2977getMaxWidthimpl);
                    if (floatValue != snapValueToTick) {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(floatValue, snapValueToTick, function0, z2, mutableState, mutableState2, state, f, m2977getMaxWidthimpl, closedFloatingPointRange5, null), 3, null);
                        return;
                    }
                    Function0<Unit> function02 = function0;
                    if (function02 == null) {
                        return;
                    }
                    function02.invoke();
                }

                /* compiled from: Slider.kt */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                @DebugMetadata(c = "androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1$1", f = "Slider.kt", i = {}, l = {307}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material.SliderKt$RangeSlider$2$gestureEndAction$1$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ float $current;
                    final /* synthetic */ boolean $isStart;
                    final /* synthetic */ float $maxPx;
                    final /* synthetic */ float $minPx;
                    final /* synthetic */ Function0<Unit> $onValueChangeFinished;
                    final /* synthetic */ State<Function1<ClosedFloatingPointRange<Float>, Unit>> $onValueChangeState;
                    final /* synthetic */ MutableState<Float> $rawOffsetEnd;
                    final /* synthetic */ MutableState<Float> $rawOffsetStart;
                    final /* synthetic */ float $target;
                    final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    AnonymousClass1(float f, float f2, Function0<Unit> function0, boolean z, MutableState<Float> mutableState, MutableState<Float> mutableState2, State<? extends Function1<? super ClosedFloatingPointRange<Float>, Unit>> state, float f3, float f4, ClosedFloatingPointRange<Float> closedFloatingPointRange, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$current = f;
                        this.$target = f2;
                        this.$onValueChangeFinished = function0;
                        this.$isStart = z;
                        this.$rawOffsetStart = mutableState;
                        this.$rawOffsetEnd = mutableState2;
                        this.$onValueChangeState = state;
                        this.$minPx = f3;
                        this.$maxPx = f4;
                        this.$valueRange = closedFloatingPointRange;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$current, this.$target, this.$onValueChangeFinished, this.$isStart, this.$rawOffsetStart, this.$rawOffsetEnd, this.$onValueChangeState, this.$minPx, this.$maxPx, this.$valueRange, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        TweenSpec tweenSpec;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            Animatable Animatable$default = AnimatableKt.Animatable$default(this.$current, 0.0f, 2, null);
                            Float boxFloat = Boxing.boxFloat(this.$target);
                            tweenSpec = SliderKt.SliderToTickAnimation;
                            Float boxFloat2 = Boxing.boxFloat(0.0f);
                            final boolean z = this.$isStart;
                            final MutableState<Float> mutableState = this.$rawOffsetStart;
                            final MutableState<Float> mutableState2 = this.$rawOffsetEnd;
                            final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state = this.$onValueChangeState;
                            final float f = this.$minPx;
                            final float f2 = this.$maxPx;
                            final ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$valueRange;
                            this.label = 1;
                            if (Animatable$default.animateTo(boxFloat, tweenSpec, boxFloat2, new Function1<Animatable<Float, AnimationVector1D>, Unit>() { // from class: androidx.compose.material.SliderKt.RangeSlider.2.gestureEndAction.1.1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Animatable<Float, AnimationVector1D> animatable) {
                                    invoke2(animatable);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Animatable<Float, AnimationVector1D> animateTo) {
                                    ClosedFloatingPointRange<Float> invoke$scaleToUserValue;
                                    Intrinsics.checkNotNullParameter(animateTo, "$this$animateTo");
                                    (z ? mutableState : mutableState2).setValue(animateTo.getValue());
                                    Function1<ClosedFloatingPointRange<Float>, Unit> value = state.getValue();
                                    invoke$scaleToUserValue = SliderKt$RangeSlider$2.invoke$scaleToUserValue(f, f2, closedFloatingPointRange, RangesKt.rangeTo(mutableState.getValue().floatValue(), mutableState2.getValue().floatValue()));
                                    value.invoke(invoke$scaleToUserValue);
                                }
                            }, this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else {
                            if (i != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                        }
                        Function0<Unit> function0 = this.$onValueChangeFinished;
                        if (function0 != null) {
                            function0.invoke();
                        }
                        return Unit.INSTANCE;
                    }
                }
            }, composer, 0);
            Modifier modifier = this.$modifier;
            MutableInteractionSource mutableInteractionSource = this.$startInteractionSource;
            MutableInteractionSource mutableInteractionSource2 = this.$endInteractionSource;
            MutableState mutableState3 = mutableState;
            MutableState mutableState4 = mutableState2;
            boolean z2 = this.$enabled;
            ClosedFloatingPointRange<Float> closedFloatingPointRange6 = this.$valueRange;
            Float valueOf = Float.valueOf(0.0f);
            Float valueOf2 = Float.valueOf(m2977getMaxWidthimpl);
            final State<Function1<ClosedFloatingPointRange<Float>, Unit>> state2 = this.$onValueChangeState;
            final ClosedFloatingPointRange<Float> closedFloatingPointRange7 = this.$valueRange;
            Object[] objArr = {mutableState, valueOf, mutableState2, valueOf2, state2, closedFloatingPointRange7};
            composer.startReplaceableGroup(-3685570);
            ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
            int i3 = 0;
            boolean z3 = false;
            while (i3 < 6) {
                Object obj = objArr[i3];
                i3++;
                z3 |= composer.changed(obj);
            }
            Object rememberedValue4 = composer.rememberedValue();
            if (z3 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                final float f2 = 0.0f;
                rememberedValue4 = (Function2) new Function2<Boolean, Float, Unit>() { // from class: androidx.compose.material.SliderKt$RangeSlider$2$pressDrag$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Float f3) {
                        invoke(bool.booleanValue(), f3.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z4, float f3) {
                        ClosedFloatingPointRange<Float> invoke$scaleToUserValue;
                        if (z4) {
                            MutableState<Float> mutableState5 = mutableState;
                            mutableState5.setValue(Float.valueOf(RangesKt.coerceIn(mutableState5.getValue().floatValue() + f3, f2, mutableState2.getValue().floatValue())));
                        } else {
                            MutableState<Float> mutableState6 = mutableState2;
                            mutableState6.setValue(Float.valueOf(RangesKt.coerceIn(mutableState6.getValue().floatValue() + f3, mutableState.getValue().floatValue(), m2977getMaxWidthimpl)));
                        }
                        Function1<ClosedFloatingPointRange<Float>, Unit> value = state2.getValue();
                        invoke$scaleToUserValue = SliderKt$RangeSlider$2.invoke$scaleToUserValue(f2, m2977getMaxWidthimpl, closedFloatingPointRange7, RangesKt.rangeTo(mutableState.getValue().floatValue(), mutableState2.getValue().floatValue()));
                        value.invoke(invoke$scaleToUserValue);
                    }
                };
                composer.updateRememberedValue(rememberedValue4);
            }
            composer.endReplaceableGroup();
            rangeSliderPressDragModifier = SliderKt.rangeSliderPressDragModifier(modifier, mutableInteractionSource, mutableInteractionSource2, mutableState3, mutableState4, z2, z, m2977getMaxWidthimpl, closedFloatingPointRange6, rememberUpdatedState, (Function2) rememberedValue4);
            float coerceIn = RangesKt.coerceIn(this.$values.getStart().floatValue(), this.$valueRange.getStart().floatValue(), this.$values.getEndInclusive().floatValue());
            float coerceIn2 = RangesKt.coerceIn(this.$values.getEndInclusive().floatValue(), this.$values.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue());
            calcFraction = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), coerceIn);
            calcFraction2 = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), coerceIn2);
            boolean z4 = this.$enabled;
            List<Float> list2 = this.$tickFractions;
            SliderColors sliderColors = this.$colors;
            MutableInteractionSource mutableInteractionSource3 = this.$startInteractionSource;
            MutableInteractionSource mutableInteractionSource4 = this.$endInteractionSource;
            Modifier then = rangeSliderPressDragModifier.then(this.$modifier);
            int i4 = this.$$dirty;
            SliderKt.RangeSliderImpl(z4, calcFraction, calcFraction2, list2, sliderColors, m2977getMaxWidthimpl, mutableInteractionSource3, mutableInteractionSource4, then, composer, ((i4 >> 9) & 14) | 14159872 | ((i4 >> 9) & 57344));
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ClosedFloatingPointRange<Float> invoke$scaleToUserValue(float f, float f2, ClosedFloatingPointRange<Float> closedFloatingPointRange, ClosedFloatingPointRange<Float> closedFloatingPointRange2) {
        ClosedFloatingPointRange<Float> scale;
        scale = SliderKt.scale(f, f2, (ClosedFloatingPointRange<Float>) closedFloatingPointRange2, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
        return scale;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invoke$scaleToOffset(ClosedFloatingPointRange<Float> closedFloatingPointRange, float f, float f2, float f3) {
        float scale;
        scale = SliderKt.scale(closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue(), f3, f, f2);
        return scale;
    }

    /* compiled from: Slider.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: androidx.compose.material.SliderKt$RangeSlider$2$1, reason: invalid class name */
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Float, Float> {
        final /* synthetic */ float $maxPx;
        final /* synthetic */ float $minPx;
        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ClosedFloatingPointRange<Float> closedFloatingPointRange, float f, float f2) {
            super(1, Intrinsics.Kotlin.class, "scaleToOffset", "invoke$scaleToOffset(Lkotlin/ranges/ClosedFloatingPointRange;FFF)F", 0);
            this.$valueRange = closedFloatingPointRange;
            this.$minPx = f;
            this.$maxPx = f2;
        }

        public final float invoke(float f) {
            return SliderKt$RangeSlider$2.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, f);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Float invoke(Float f) {
            return Float.valueOf(invoke(f.floatValue()));
        }
    }

    /* compiled from: Slider.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: androidx.compose.material.SliderKt$RangeSlider$2$2, reason: invalid class name */
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Float, Float> {
        final /* synthetic */ float $maxPx;
        final /* synthetic */ float $minPx;
        final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ClosedFloatingPointRange<Float> closedFloatingPointRange, float f, float f2) {
            super(1, Intrinsics.Kotlin.class, "scaleToOffset", "invoke$scaleToOffset(Lkotlin/ranges/ClosedFloatingPointRange;FFF)F", 0);
            this.$valueRange = closedFloatingPointRange;
            this.$minPx = f;
            this.$maxPx = f2;
        }

        public final float invoke(float f) {
            return SliderKt$RangeSlider$2.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, f);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Float invoke(Float f) {
            return Float.valueOf(invoke(f.floatValue()));
        }
    }
}
