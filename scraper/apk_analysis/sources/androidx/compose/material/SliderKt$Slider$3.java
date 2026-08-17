package androidx.compose.material;

import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableKt$draggable$1;
import androidx.compose.foundation.gestures.DraggableKt$draggable$2;
import androidx.compose.foundation.gestures.Orientation;
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
final class SliderKt$Slider$3 extends Lambda implements Function3<BoxWithConstraintsScope, Composer, Integer, Unit> {
    final /* synthetic */ int $$dirty;
    final /* synthetic */ SliderColors $colors;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ Function0<Unit> $onValueChangeFinished;
    final /* synthetic */ State<Function1<Float, Unit>> $onValueChangeState;
    final /* synthetic */ List<Float> $tickFractions;
    final /* synthetic */ float $value;
    final /* synthetic */ ClosedFloatingPointRange<Float> $valueRange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SliderKt$Slider$3(ClosedFloatingPointRange<Float> closedFloatingPointRange, int i, float f, MutableInteractionSource mutableInteractionSource, boolean z, List<Float> list, SliderColors sliderColors, State<? extends Function1<? super Float, Unit>> state, Function0<Unit> function0) {
        super(3);
        this.$valueRange = closedFloatingPointRange;
        this.$$dirty = i;
        this.$value = f;
        this.$interactionSource = mutableInteractionSource;
        this.$enabled = z;
        this.$tickFractions = list;
        this.$colors = sliderColors;
        this.$onValueChangeState = state;
        this.$onValueChangeFinished = function0;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Unit invoke(BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, Integer num) {
        invoke(boxWithConstraintsScope, composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(BoxWithConstraintsScope BoxWithConstraints, Composer composer, int i) {
        int i2;
        float f;
        Modifier sliderPressModifier;
        Modifier draggable;
        float calcFraction;
        Intrinsics.checkNotNullParameter(BoxWithConstraints, "$this$BoxWithConstraints");
        ComposerKt.sourceInformation(composer, "C160@7743L7,170@8132L24,171@8181L49,172@8260L258,179@8528L69,181@8630L618,204@9646L55,211@9987L201:Slider.kt#jmzs0o");
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
            composer.startReplaceableGroup(-723524056);
            ComposerKt.sourceInformation(composer, "C(rememberCoroutineScope)475@19849L144:Effects.kt#9igjgp");
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer));
                composer.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                rememberedValue = compositionScopedCoroutineScopeCanceller;
            }
            composer.endReplaceableGroup();
            final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) rememberedValue).getCoroutineScope();
            composer.endReplaceableGroup();
            float f2 = this.$value;
            ClosedFloatingPointRange<Float> closedFloatingPointRange = this.$valueRange;
            composer.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composer, "C(remember):Composables.kt#9igjgp");
            Object rememberedValue2 = composer.rememberedValue();
            final float f3 = 0.0f;
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                rememberedValue2 = SnapshotStateKt.mutableStateOf$default(Float.valueOf(invoke$scaleToOffset(closedFloatingPointRange, 0.0f, m2977getMaxWidthimpl, f2)), null, 2, null);
                composer.updateRememberedValue(rememberedValue2);
            }
            composer.endReplaceableGroup();
            final MutableState mutableState = (MutableState) rememberedValue2;
            Object valueOf = Float.valueOf(0.0f);
            Object valueOf2 = Float.valueOf(m2977getMaxWidthimpl);
            final ClosedFloatingPointRange<Float> closedFloatingPointRange2 = this.$valueRange;
            final State<Function1<Float, Unit>> state = this.$onValueChangeState;
            composer.startReplaceableGroup(-3686095);
            ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
            boolean changed = composer.changed(valueOf) | composer.changed(valueOf2) | composer.changed(closedFloatingPointRange2);
            Object rememberedValue3 = composer.rememberedValue();
            if (changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                f = 0.0f;
                rememberedValue3 = new SliderDraggableState(new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$3$draggableState$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Float f4) {
                        invoke(f4.floatValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(float f4) {
                        float invoke$scaleToUserValue;
                        MutableState<Float> mutableState2 = mutableState;
                        mutableState2.setValue(Float.valueOf(RangesKt.coerceIn(mutableState2.getValue().floatValue() + f4, f3, m2977getMaxWidthimpl)));
                        Function1<Float, Unit> value = state.getValue();
                        invoke$scaleToUserValue = SliderKt$Slider$3.invoke$scaleToUserValue(f3, m2977getMaxWidthimpl, closedFloatingPointRange2, mutableState.getValue().floatValue());
                        value.invoke(Float.valueOf(invoke$scaleToUserValue));
                    }
                });
                composer.updateRememberedValue(rememberedValue3);
            } else {
                f = 0.0f;
            }
            composer.endReplaceableGroup();
            final SliderDraggableState sliderDraggableState = (SliderDraggableState) rememberedValue3;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$valueRange, f, m2977getMaxWidthimpl);
            ClosedFloatingPointRange<Float> closedFloatingPointRange3 = this.$valueRange;
            float f4 = this.$value;
            int i3 = this.$$dirty;
            final float f5 = f;
            SliderKt.CorrectValueSideEffect(anonymousClass1, closedFloatingPointRange3, mutableState, f4, composer, ((i3 >> 9) & b.p) | 384 | ((i3 << 9) & 7168));
            final List<Float> list = this.$tickFractions;
            final Function0<Unit> function0 = this.$onValueChangeFinished;
            State rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(new Function1<Float, Unit>() { // from class: androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Float f6) {
                    invoke(f6.floatValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(float f6) {
                    float snapValueToTick;
                    Function0<Unit> function02;
                    float floatValue = mutableState.getValue().floatValue();
                    snapValueToTick = SliderKt.snapValueToTick(floatValue, list, f5, m2977getMaxWidthimpl);
                    if (floatValue != snapValueToTick) {
                        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(sliderDraggableState, floatValue, snapValueToTick, f6, function0, null), 3, null);
                    } else {
                        if (sliderDraggableState.isDragging() || (function02 = function0) == null) {
                            return;
                        }
                        function02.invoke();
                    }
                }

                /* compiled from: Slider.kt */
                @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                @DebugMetadata(c = "androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1$1", f = "Slider.kt", i = {}, l = {187}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: androidx.compose.material.SliderKt$Slider$3$gestureEndAction$1$1, reason: invalid class name */
                static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ float $current;
                    final /* synthetic */ SliderDraggableState $draggableState;
                    final /* synthetic */ Function0<Unit> $onValueChangeFinished;
                    final /* synthetic */ float $target;
                    final /* synthetic */ float $velocity;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    AnonymousClass1(SliderDraggableState sliderDraggableState, float f, float f2, float f3, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.$draggableState = sliderDraggableState;
                        this.$current = f;
                        this.$target = f2;
                        this.$velocity = f3;
                        this.$onValueChangeFinished = function0;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.$draggableState, this.$current, this.$target, this.$velocity, this.$onValueChangeFinished, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        Object animateToTarget;
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            this.label = 1;
                            animateToTarget = SliderKt.animateToTarget(this.$draggableState, this.$current, this.$target, this.$velocity, this);
                            if (animateToTarget == coroutine_suspended) {
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
            SliderDraggableState sliderDraggableState2 = sliderDraggableState;
            sliderPressModifier = SliderKt.sliderPressModifier(Modifier.INSTANCE, sliderDraggableState2, this.$interactionSource, m2977getMaxWidthimpl, z, mutableState, rememberUpdatedState, this.$enabled);
            Orientation orientation = Orientation.Horizontal;
            boolean isDragging = sliderDraggableState.isDragging();
            Modifier.Companion companion = Modifier.INSTANCE;
            boolean z2 = this.$enabled;
            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            composer.startReplaceableGroup(-3686930);
            ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
            boolean changed2 = composer.changed(rememberUpdatedState);
            Object rememberedValue4 = composer.rememberedValue();
            if (changed2 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                rememberedValue4 = (Function3) new SliderKt$Slider$3$drag$1$1(rememberUpdatedState, null);
                composer.updateRememberedValue(rememberedValue4);
            }
            composer.endReplaceableGroup();
            draggable = DraggableKt.draggable(companion, sliderDraggableState2, orientation, (r20 & 4) != 0 ? true : z2, (r20 & 8) != 0 ? null : mutableInteractionSource, (r20 & 16) != 0 ? false : isDragging, (r20 & 32) != 0 ? new DraggableKt$draggable$1(null) : null, (r20 & 64) != 0 ? new DraggableKt$draggable$2(null) : (Function3) rememberedValue4, (r20 & 128) != 0 ? false : z);
            calcFraction = SliderKt.calcFraction(this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue(), RangesKt.coerceIn(this.$value, this.$valueRange.getStart().floatValue(), this.$valueRange.getEndInclusive().floatValue()));
            boolean z3 = this.$enabled;
            List<Float> list2 = this.$tickFractions;
            SliderColors sliderColors = this.$colors;
            MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
            Modifier then = sliderPressModifier.then(draggable);
            int i4 = this.$$dirty;
            SliderKt.SliderImpl(z3, calcFraction, list2, sliderColors, m2977getMaxWidthimpl, mutableInteractionSource2, then, composer, ((i4 >> 9) & 14) | 512 | ((i4 >> 15) & 7168) | ((i4 >> 6) & 458752));
            return;
        }
        composer.skipToGroupEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float invoke$scaleToUserValue(float f, float f2, ClosedFloatingPointRange<Float> closedFloatingPointRange, float f3) {
        float scale;
        scale = SliderKt.scale(f, f2, f3, closedFloatingPointRange.getStart().floatValue(), closedFloatingPointRange.getEndInclusive().floatValue());
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
    /* renamed from: androidx.compose.material.SliderKt$Slider$3$1, reason: invalid class name */
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
            return SliderKt$Slider$3.invoke$scaleToOffset(this.$valueRange, this.$minPx, this.$maxPx, f);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Float invoke(Float f) {
            return Float.valueOf(invoke(f.floatValue()));
        }
    }
}
