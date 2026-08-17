package androidx.compose.animation;

import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EnterExitTransition.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001BF\u0012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003R\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tø\u0001\u0000¢\u0006\u0002\u0010\fJ#\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001cø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ)\u0010\u001f\u001a\u00020 *\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'R*\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003R\b\u0012\u0004\u0012\u00020\u00070\u0006ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R1\u0010\u0012\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00150\u0013¢\u0006\u0002\b\u0016ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006("}, d2 = {"Landroidx/compose/animation/SlideModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "lazyAnimation", "Landroidx/compose/animation/core/Transition$DeferredAnimation;", "Landroidx/compose/ui/unit/IntOffset;", "Landroidx/compose/animation/core/AnimationVector2D;", "Landroidx/compose/animation/core/Transition;", "Landroidx/compose/animation/EnterExitState;", "slideIn", "Landroidx/compose/runtime/State;", "Landroidx/compose/animation/Slide;", "slideOut", "(Landroidx/compose/animation/core/Transition$DeferredAnimation;Landroidx/compose/runtime/State;Landroidx/compose/runtime/State;)V", "getLazyAnimation", "()Landroidx/compose/animation/core/Transition$DeferredAnimation;", "getSlideIn", "()Landroidx/compose/runtime/State;", "getSlideOut", "transitionSpec", "Lkotlin/Function1;", "Landroidx/compose/animation/core/Transition$Segment;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "Lkotlin/ExtensionFunctionType;", "getTransitionSpec", "()Lkotlin/jvm/functions/Function1;", "targetValueByState", "targetState", "fullSize", "Landroidx/compose/ui/unit/IntSize;", "targetValueByState-oFUgxo0", "(Landroidx/compose/animation/EnterExitState;J)J", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "animation_release"}, k = 1, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
final class SlideModifier implements LayoutModifier {
    private final Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> lazyAnimation;
    private final State<Slide> slideIn;
    private final State<Slide> slideOut;
    private final Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<IntOffset>> transitionSpec;

    /* compiled from: EnterExitTransition.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EnterExitState.valuesCustom().length];
            iArr[EnterExitState.Visible.ordinal()] = 1;
            iArr[EnterExitState.PreEnter.ordinal()] = 2;
            iArr[EnterExitState.PostExit.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SlideModifier(Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> lazyAnimation, State<Slide> slideIn, State<Slide> slideOut) {
        Intrinsics.checkNotNullParameter(lazyAnimation, "lazyAnimation");
        Intrinsics.checkNotNullParameter(slideIn, "slideIn");
        Intrinsics.checkNotNullParameter(slideOut, "slideOut");
        this.lazyAnimation = lazyAnimation;
        this.slideIn = slideIn;
        this.slideOut = slideOut;
        this.transitionSpec = new Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<IntOffset>>() { // from class: androidx.compose.animation.SlideModifier$transitionSpec$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final FiniteAnimationSpec<IntOffset> invoke(Transition.Segment<EnterExitState> segment) {
                SpringSpec springSpec;
                SpringSpec springSpec2;
                SpringSpec springSpec3;
                Intrinsics.checkNotNullParameter(segment, "$this$null");
                if (segment.isTransitioningTo(EnterExitState.PreEnter, EnterExitState.Visible)) {
                    Slide value = SlideModifier.this.getSlideIn().getValue();
                    if (value != null) {
                        return value.getAnimationSpec();
                    }
                    springSpec3 = EnterExitTransitionKt.defaultOffsetAnimationSpec;
                    return springSpec3;
                }
                if (!segment.isTransitioningTo(EnterExitState.Visible, EnterExitState.PostExit)) {
                    springSpec = EnterExitTransitionKt.defaultOffsetAnimationSpec;
                    return springSpec;
                }
                Slide value2 = SlideModifier.this.getSlideOut().getValue();
                if (value2 != null) {
                    return value2.getAnimationSpec();
                }
                springSpec2 = EnterExitTransitionKt.defaultOffsetAnimationSpec;
                return springSpec2;
            }
        };
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public boolean all(Function1<? super Modifier.Element, Boolean> function1) {
        return LayoutModifier.DefaultImpls.all(this, function1);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public boolean any(Function1<? super Modifier.Element, Boolean> function1) {
        return LayoutModifier.DefaultImpls.any(this, function1);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public <R> R foldIn(R r, Function2<? super R, ? super Modifier.Element, ? extends R> function2) {
        return (R) LayoutModifier.DefaultImpls.foldIn(this, r, function2);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public <R> R foldOut(R r, Function2<? super Modifier.Element, ? super R, ? extends R> function2) {
        return (R) LayoutModifier.DefaultImpls.foldOut(this, r, function2);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return LayoutModifier.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return LayoutModifier.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return LayoutModifier.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return LayoutModifier.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, intrinsicMeasurable, i);
    }

    @Override // androidx.compose.ui.Modifier
    public Modifier then(Modifier modifier) {
        return LayoutModifier.DefaultImpls.then(this, modifier);
    }

    public final Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> getLazyAnimation() {
        return this.lazyAnimation;
    }

    public final State<Slide> getSlideIn() {
        return this.slideIn;
    }

    public final State<Slide> getSlideOut() {
        return this.slideOut;
    }

    public final Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<IntOffset>> getTransitionSpec() {
        return this.transitionSpec;
    }

    /* renamed from: targetValueByState-oFUgxo0, reason: not valid java name */
    public final long m107targetValueByStateoFUgxo0(EnterExitState targetState, long fullSize) {
        Intrinsics.checkNotNullParameter(targetState, "targetState");
        Slide value = this.slideIn.getValue();
        IntOffset invoke = value == null ? null : value.getSlideOffset().invoke(IntSize.m3119boximpl(fullSize));
        long m3095getZeronOccac = invoke == null ? IntOffset.INSTANCE.m3095getZeronOccac() : invoke.getPackedValue();
        Slide value2 = this.slideOut.getValue();
        IntOffset invoke2 = value2 != null ? value2.getSlideOffset().invoke(IntSize.m3119boximpl(fullSize)) : null;
        long m3095getZeronOccac2 = invoke2 == null ? IntOffset.INSTANCE.m3095getZeronOccac() : invoke2.getPackedValue();
        int i = WhenMappings.$EnumSwitchMapping$0[targetState.ordinal()];
        if (i == 1) {
            return IntOffset.INSTANCE.m3095getZeronOccac();
        }
        if (i == 2) {
            return m3095getZeronOccac;
        }
        if (i == 3) {
            return m3095getZeronOccac2;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo56measure3p2s80s(MeasureScope receiver, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(receiver, "$receiver");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        final Placeable mo2510measureBRTryo0 = measurable.mo2510measureBRTryo0(j);
        final long IntSize = IntSizeKt.IntSize(mo2510measureBRTryo0.getWidth(), mo2510measureBRTryo0.getHeight());
        return MeasureScope.DefaultImpls.layout$default(receiver, mo2510measureBRTryo0.getWidth(), mo2510measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SlideModifier$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope layout) {
                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                Transition<EnterExitState>.DeferredAnimation<IntOffset, AnimationVector2D> lazyAnimation = SlideModifier.this.getLazyAnimation();
                Function1<Transition.Segment<EnterExitState>, FiniteAnimationSpec<IntOffset>> transitionSpec = SlideModifier.this.getTransitionSpec();
                final SlideModifier slideModifier = SlideModifier.this;
                final long j2 = IntSize;
                Placeable.PlacementScope.m2551placeWithLayeraW9wM$default(layout, mo2510measureBRTryo0, lazyAnimation.animate(transitionSpec, new Function1<EnterExitState, IntOffset>() { // from class: androidx.compose.animation.SlideModifier$measure$1$slideOffset$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ IntOffset invoke(EnterExitState enterExitState) {
                        return IntOffset.m3076boximpl(m108invokeBjo55l4Bjo55l4(enterExitState));
                    }

                    /* renamed from: invoke-Bjo55l4-Bjo55l4, reason: not valid java name */
                    public final long m108invokeBjo55l4Bjo55l4(EnterExitState it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return SlideModifier.this.m107targetValueByStateoFUgxo0(it, j2);
                    }
                }).getValue().getPackedValue(), 0.0f, null, 6, null);
            }
        }, 4, null);
    }
}
